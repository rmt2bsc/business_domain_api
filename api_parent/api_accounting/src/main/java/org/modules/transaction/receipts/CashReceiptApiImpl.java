package org.modules.transaction.receipts;

import java.util.List;

import org.AccountingConst.SubsidiaryType;
import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.subsidiary.CustomerDao;
import org.dao.subsidiary.SubsidiaryDaoException;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dao.transaction.sales.SalesOrderDao;
import org.dao.transaction.sales.SalesOrderDaoException;
import org.dao.transaction.sales.SalesOrderDaoFactory;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.CustomerDto;
import org.dto.SalesOrderDto;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.dto.adapter.orm.account.subsidiary.CustomerExt;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;
import org.modules.contacts.ContactsApi;
import org.modules.contacts.ContactsApiException;
import org.modules.contacts.ContactsApiFactory;
import org.modules.transaction.AbstractXactApiImpl;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;

import com.InvalidDataException;
import com.api.persistence.DaoClient;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * API implementation of CashReceiptApi for managing cash receipts transactions.
 * <p>
 * When a cash receipt is created, the base transaction amount is posted to the
 * xact table as a positive value, which increases the company's cash status,
 * and the customer activity amount is posted as a negative value which
 * decreases the value of the customer's account. Conversely, when a cash
 * receipt reversed, the base transaction amount is posted to the xact table as
 * a negative value, and the customer activity amount is posted as positive
 * value which decreases and increases the value of the company's revenue and
 * the customer's account, respectively.
 * 
 * @author Roy Terrell
 * 
 */
public class CashReceiptApiImpl extends AbstractXactApiImpl implements CashReceiptApi {

    private static final Logger logger = Logger.getLogger(CashReceiptApiImpl.class);

    private SalesOrderDaoFactory daoFact;

    private SalesOrderDao dao;


    /**
     * Creates a CashReceiptApiImpl object in which the configuration is
     * identified by the name of a given application.
     * 
     * @param appName
     */
    protected CashReceiptApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        this.dao.setDaoUser(this.apiUser);
        return;
    }

    /**
     * Creates an CashReceiptApiImpl initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param connection
     */
    protected CashReceiptApiImpl(DaoClient connection) {
        super(connection);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
        this.setSharedDao(this.dao);
        this.dao.setDaoUser(this.apiUser);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.RMT2Base#init()
     */
    @Override
    public void init() {
        super.init();
        this.daoFact = new SalesOrderDaoFactory();
    }

    /**
     * Applies cash receipt transaction for the invoiced sales order and sends
     * an email confirmation to customer.
     * 
     * @param order
     * @param amount
     * @return boolean true when transaction succeeded, false otherwise.
     * @throws {@link CashReceiptApiException}
     * @throws {@link InvalidDataException}
     */
    public int applyPaymentToInvoice(SalesOrderDto salesOrder, Double cashPaymentAmount) 
            throws CashReceiptApiException {
        
        // Sales order object cannot be null
        try {
            Verifier.verifyNotNull(salesOrder);
        }
        catch (VerifyException e ) {
            this.msg = "Sales order object is required";
            throw new InvalidDataException(this.msg, e);
        }
        
        //  Cash payment amount cannot be null
        try {
            Verifier.verifyNotNull(cashPaymentAmount);
        }
        catch (VerifyException e ) {
            this.msg = "Cash receipt amount is required";
            throw new InvalidDataException(this.msg, e);
        }
        
        XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        xact.setXactAmount(cashPaymentAmount);
        xact.setXactReason("Full payment received for sales order #" + salesOrder.getSalesOrderId());
        xact.setXactTypeId(XactConst.XACT_TYPE_CASHRECEIPT);
        xact.setXactTenderId(XactConst.TENDER_CASH);

        // Create cash receipt transaction for the given sales order.
        try {
            return this.receivePayment(xact, salesOrder.getCustomerId());
        } catch (CashReceiptApiException e) {
            this.msg = "Unable to apply customer payment sales order, " + salesOrder.getSalesOrderId();
            logger.error(this.msg);
            throw new CashReceiptApiException(e);
        }
    }

    /**
     * Creates a customer payment transaction or reverses and existing customer
     * payment transaction. If <i>xact</i>'s transaction id is equal zero, then
     * a new customer payment transaction is created. Otherwise, an existing
     * customer payment transaction is reversed.
     * 
     * @param xact
     *            Source transaction.
     * @param customerId
     *            The id of the customer of this transaction.
     * @return The id of the sales order payment transaction.
     * @throws {@link CashReceiptApiException}
     * @throws {@link InvalidDataException}
     */
    @Override
    public int receivePayment(XactDto xact, Integer customerId) throws CashReceiptApiException {
        // Transaction object cannot be null
        try {
            Verifier.verifyNotNull(xact);
        }
        catch (VerifyException e ) {
            this.msg = "Cash Receipt Transaction object is required";
            throw new InvalidDataException(this.msg, e);
        }
        
        //  Customer id cannot be null
        try {
            Verifier.verifyNotNull(customerId);
        }
        catch (VerifyException e ) {
            this.msg = "Customer Id is required";
            throw new InvalidDataException(this.msg, e);
        }
        
        // Customer id must be greater than zero
        try {
            Verifier.verifyPositive(customerId);
        }
        catch (VerifyException e ) {
            this.msg = "Customer Id must be a value greater than zero";
            throw new InvalidDataException(this.msg, e);
        }

        // Verify tender is provided
        try {
            Verifier.verifyPositive(xact.getXactTenderId());
        } catch (VerifyException e) {
            this.msg = "Transaction tender is required for cash receipt operation";
            throw new InvalidDataException(this.msg, e);
        }

        // Determine if we are creating or reversing the customer payment.
        int xactId = 0;
        try {
            Verifier.verifyPositive(xact.getXactId());
            xactId = this.reverseCustomerPayment(xact, customerId);
        }
        catch (VerifyException e ) {
            xactId = this.createCustomerPayment(xact, customerId);
        }
        return xactId;
    }

    /**
     * Creates a customer payment transaction. As a rule, the transaction amount
     * is posted to the base transaction table as a positive amount, and the
     * posted as a negative amount to the customer activity table.
     * 
     * @param xact
     *            Source transaction.
     * @param customerId
     *            The id of the customer of this transaction.
     * @return The id of the new customer payment transaction.
     * @throws CashReceiptApiException
     */
    protected int createCustomerPayment(XactDto xact, int customerId) throws CashReceiptApiException {
        int xactId = 0;
        double xactAmount = 0;

        xact.setXactTypeId(XactConst.XACT_TYPE_CASHRECEIPT);
        try {
            xactId = this.update(xact, null);
            // Ensure that the customer activity is posted as a negative amount.
            xactAmount = xact.getXactAmount() * XactConst.REVERSE_MULTIPLIER;
            super.createSubsidiaryActivity(customerId, SubsidiaryType.CUSTOMER, xactId, xactAmount);
            return xactId;
        } catch (XactApiException e) {
            throw new CashReceiptApiException(e);
        }
    }

    /**
     * Reverses a customer's payment and finalizes the source tranaction
     * 
     * @param xact
     *            Source transaction.
     * @param customerId
     *            The id of the customer of this transaction.
     * @return The id of the reversed transaction.
     * @throws CashReceiptApiException
     *             If customer payment transaction is final or a general
     *             transaction error occurs.
     */
    protected int reverseCustomerPayment(XactDto xact, int customerId) throws CashReceiptApiException {
        int xactId = 0;
        double xactAmount = 0;

        try {
            // Cannot reverse payment transaction that has been finalized
            if (!this.isModifiable(xact)) {
                msg = "Customer Payment cannot be reversed since it is already finalized";
                logger.error(msg);
                throw new CashReceiptApiException(msg);
            }

            // reverse transaction
            xactId = this.reverse(xact, null);

            // Apply a reversal multiplier on the revised base transaction
            // amount which will be used to offset the customer activity.
            xactAmount = xact.getXactAmount() * XactConst.REVERSE_MULTIPLIER;
            super.createSubsidiaryActivity(customerId, SubsidiaryType.CUSTOMER, xactId, xactAmount);
            return xactId;
        } catch (XactApiException e) {
            throw new CashReceiptApiException(e);
        }
    }

    /**
     * Performs pre-initialization of the transaction instance. Basicaly, sets
     * the transaction date to null before transaction is persisted.
     * 
     * @param xact
     *            The transaction that is being reversed.
     * @param xactItems
     *            The transaction items that are to be reversed.
     */
    protected void preReverse(XactDto xact, List<XactTypeItemActivityDto> xactItems) {
        super.preReverse(xact, xactItems);
        xact.setXactDate(null);
        return;
    }

    /**
     * Prepends customer payment comments with a tag. If user did not input
     * anything for the transaction reason, then the method is aborted which will
     * allow postValidateXact to catch the error. the transaction amount is
     * applied to the xact table as is.
     * 
     * @param xact
     *            The target transaction
     */
    protected void preCreateXact(XactDto xact) {
        super.preCreateXact(xact);
        if (xact.getXactReason() == null || xact.getXactReason().equals("")) {
            return;
        }
        // Only modify reason and accept transaction amount as is for actual
        // cash receipts transactions.
        // For reversals, the negative multiplier would have been applied prior
        // to the invocation of this method.
        if (xact.getXactSubtypeId() == 0) {
            xact.setXactReason("Cash Receipt: " + xact.getXactReason());
        }
        return;
    }

    /**
     * Ensures that the transaction reason is populated.
     * 
     * @param xact
     *            The transaction object to be validated
     * @throws InvalidDataException
     *             Validation error occurred.
     */
    protected void postValidate(XactDto xact) {
        // Ensure that reason has a value
        if (xact.getXactReason() == null || xact.getXactReason().equals("")) {
            this.msg = "Transaction reason cannot be blank";
            logger.error(this.msg);
            throw new InvalidDataException(this.msg);
        }
        return;
    }


    private SalesOrder getSalesOrder(int salesOrderId) throws CashReceiptApiException {
        SalesOrderDaoFactory soDaoFact = new SalesOrderDaoFactory();
        SalesOrderDao soDao = soDaoFact.createRmt2OrmDao(this.getSharedDao());
        SalesOrderDto soCriteria = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(null);
        soCriteria.setSalesOrderId(salesOrderId);
        try {
            List<SalesOrderDto> soDto = soDao.fetchSalesOrder(soCriteria);
            if (soDto != null && soDto.size() == 1) {
                return SalesOrderDaoFactory.createOrmSalesOrder(soDto.get(0));
            }
            else {
                this.msg = "Sales order was not found: " + salesOrderId;
                throw new CashReceiptApiException(this.msg);
            }
        } catch (SalesOrderDaoException e) {
            throw new CashReceiptApiException(e);
        }
    }

    private CustomerExt getCustomer(int customerId) throws CashReceiptApiException {
        SubsidiaryDaoFactory subDaoFact = new SubsidiaryDaoFactory();
        CustomerDao custDao = subDaoFact.createRmt2OrmCustomerDao(this.getSharedDao());
        CustomerDto custCriteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
        custCriteria.setCustomerId(customerId);
        CustomerExt cust = null;
        try {
            List<CustomerDto> custList = custDao.fetch(custCriteria);
            if (custList != null && custList.size() == 1) {
                cust = SubsidiaryDaoFactory.createCustomerExtBean(custList.get(0));
                double bal = custDao.calculateBalance(cust.getCustomerId());
                cust.setBalance(bal);
                return cust;
            }
            else {
                this.msg = "Unable to fetch customer details to perform cash receipts confirmation due to customer, "
                        + customerId + ", was not found";
                throw new CashReceiptApiException(this.msg);
            }
        } catch (SubsidiaryDaoException e) {
            throw new CashReceiptApiException(e);
        }
    }

    private BusinessContactDto getBusinessContact(int salesOrderId) throws CashReceiptApiException {
        SalesOrder so = this.getSalesOrder(salesOrderId);
        CustomerExt customer = this.getCustomer(so.getCustomerId());
        ContactsApi contactsApi = ContactsApiFactory.createApi();
        BusinessContactDto criteria = Rmt2AddressBookDtoFactory.getBusinessInstance(null);
        criteria.setContactId(customer.getBusinessId());
        try {
            List<ContactDto> contacts = contactsApi.getContact(criteria);
            if (contacts != null && contacts.size() == 1 && contacts.get(0) instanceof BusinessContactDto) {
                return (BusinessContactDto) contacts.get(0);
            }
            else {
                this.msg = "Unable to fetch customer business contact details to perform cash receipts confirmation due to customer business contact data was not found for sales order, "
                        + salesOrderId;
                throw new CashReceiptApiException(this.msg);
            }
        } catch (ContactsApiException e) {
            throw new CashReceiptApiException(e);
        }
    }
}