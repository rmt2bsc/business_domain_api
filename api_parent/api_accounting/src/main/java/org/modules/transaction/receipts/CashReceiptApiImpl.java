package org.modules.transaction.receipts;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.subsidiary.CustomerDao;
import org.dao.subsidiary.SubsidiaryDaoException;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dao.transaction.XactDao;
import org.dao.transaction.XactDaoException;
import org.dao.transaction.XactDaoFactory;
import org.dao.transaction.receipts.CashReceiptDaoException;
import org.dao.transaction.sales.SalesOrderDao;
import org.dao.transaction.sales.SalesOrderDaoException;
import org.dao.transaction.sales.SalesOrderDaoFactory;
import org.dto.CustomerDto;
import org.dto.SalesOrderDto;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.account.subsidiary.CustomerExt;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;
import org.modules.transaction.AbstractXactApiImpl;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;

import com.InvalidDataException;
import com.api.config.ConfigConstants;
import com.api.messaging.MessageException;
import com.api.messaging.MessageManager;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.persistence.DaoClient;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;
import com.api.xml.RMT2XmlUtility;

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
        
        //  Cash payment amound cannot be null
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
            super.createSubsidiaryActivity(customerId, xactId, xactAmount);
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
     *             If customer payment transction is final or a general
     *             transction error occurs.
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
            super.createSubsidiaryActivity(customerId, xactId, xactAmount);
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
     * anything for the transction reason, then the method is aborted which will
     * allow postValidateXact to catch the error. the transaction amount is
     * applied to the xact table as is.
     * 
     * @param xact
     *            The target transaction
     */
    protected void preCreateXact(XactDto xact) {
        super.preCreateXact(xact);
        // if (xact.getXactTenderId() == 0) {
        // xact.setNull("tenderId");
        // }
        if (xact.getXactReason() == null || xact.getXactReason().equals("")) {
            return;
        }
        // Only modify reason and accept transaction amount as is for actual
        // cash receipts tranasctions.
        // For reversals, the negative multiplier would have been appliedd prior
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

    /**
     * Emails a payment confirmation message to the email address on the
     * customer's profile.
     * 
     * @param salesOrderId
     *            the sales order id
     * @param xactId
     *            the transaction id
     * @return true upon success and false in the event the transport service
     *         could not be initialized.
     * @throws CashReceiptApiException
     * @throws PaymentEmailConfirmationException
     */
    public boolean emailPaymentConfirmation(Integer salesOrderId, Integer xactId) throws CashReceiptApiException {
        //  Customer id cannot be null
        try {
            Verifier.verifyNotNull(salesOrderId);
        }
        catch (VerifyException e ) {
            this.msg = "Sales order Id is required";
            throw new InvalidDataException(this.msg, e);
        }
        
        // Customer id must be greater than zero
        try {
            Verifier.verifyPositive(salesOrderId);
        }
        catch (VerifyException e ) {
            this.msg = "Customer Id must be a value greater than zero";
            throw new InvalidDataException(this.msg, e);
        }
        
        //  Transaction id cannot be null
        try {
            Verifier.verifyNotNull(xactId);
        }
        catch (VerifyException e ) {
            this.msg = "Transaction Id is required";
            throw new InvalidDataException(this.msg, e);
        }
        
        // Transaction id must be greater than zero
        try {
            Verifier.verifyPositive(xactId);
        }
        catch (VerifyException e ) {
            this.msg = "Transaction Id must be a value greater than zero";
            throw new InvalidDataException(this.msg, e);
        }
        
        String custData;
        try {
            custData = this.buildPaymentConfirmation(salesOrderId, xactId);
        } catch (CashReceiptDaoException e) {
            this.msg = "Unable to retreive customer payment email confirmation body";
            logger.error(this.msg);
            throw new CashReceiptApiException(this.msg, e);
        }
        String appRoot = "c:/tmp/";
        StringBuffer xmlBuf = new StringBuffer();
        xmlBuf.append(MessageManager.MSG_OPEN_TAG);
        xmlBuf.append(MessageManager.MSG_OPEN_APPROOT_TAG);
        xmlBuf.append(appRoot);
        xmlBuf.append(MessageManager.MSG_CLOSE_APPROOT_TAG);
        xmlBuf.append("<pageTitle>");
        xmlBuf.append("Customer Payment Confirmation");
        xmlBuf.append("</pageTitle>");
        xmlBuf.append(custData);
        xmlBuf.append(MessageManager.MSG_CLOSE_TAG);
        String xml = xmlBuf.toString();

        // Transform XML to HTML document
        String appFilePath = "/email/";
        RMT2XmlUtility xsl = RMT2XmlUtility.getInstance();
        String xslFile = appFilePath + "CustomerPaymentConfirmation.xsl";
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            xsl.transform(xslFile, xml.toString(), baos);
        } catch (Exception e) {
            this.msg = "XSL Customer Payment Email transformation failed for resource, "
                    + xslFile + " due to a System error.  " + e.getMessage();
            logger.error(this.msg);
            throw new PaymentEmailConfirmationException(this.msg, e);
        } finally {
            xsl = null;
        }

        // Get results of transformation
        String html = baos.toString();

        // Build email message
        String emailSubject = "RMT2 Business Systems Corp Account Payment Confirmation";
        EmailMessageBean msg = new EmailMessageBean();
        msg.setFromAddress(System.getProperty(ConfigConstants.OWNER_EMAIL));
        // msg.setToAddress(((CustomerExt) this.customerExt).getContactEmail());
        msg.setToAddress("royterrell@hotmail.com");

        msg.setSubject(emailSubject);
        msg.setBody(html, EmailMessageBean.HTML_CONTENT);

        // Send Email message to intended recipient
        SmtpApi api = SmtpFactory.getSmtpInstance();
        if (api == null) {
            return false;
        }
        try {
            api.sendMessage(msg);
            api.close();
            this.msg = "Customer payment confirmation was sent via email successfully";
            return true;
        } catch (MessageException e) {
            this.msg = "Customer payment confirmation error.  " + e.getMessage();
            throw new PaymentEmailConfirmationException(this.msg, e);
        }
    }

    /**
     * Creates customer payment confirmation message.
     * 
     * @param salesOrderId
     * @param xactId
     * @return
     * @throws CashReceiptApiException
     */
    @Override
    public String buildPaymentConfirmation(Integer salesOrderId, Integer xactId) throws CashReceiptApiException {
        //  Customer id cannot be null
        try {
            Verifier.verifyNotNull(salesOrderId);
        }
        catch (VerifyException e ) {
            this.msg = "Sales order Id is required";
            throw new InvalidDataException(this.msg, e);
        }
        
        // Customer id must be greater than zero
        try {
            Verifier.verifyPositive(salesOrderId);
        }
        catch (VerifyException e ) {
            this.msg = "Customer Id must be a value greater than zero";
            throw new InvalidDataException(this.msg, e);
        }
        
        //  Transaction id cannot be null
        try {
            Verifier.verifyNotNull(xactId);
        }
        catch (VerifyException e ) {
            this.msg = "Transaction Id is required";
            throw new InvalidDataException(this.msg, e);
        }
        
        // Transaction id must be greater than zero
        try {
            Verifier.verifyPositive(xactId);
        }
        catch (VerifyException e ) {
            this.msg = "Transaction Id must be a value greater than zero";
            throw new InvalidDataException(this.msg, e);
        }
        
        Xact xact = null;
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        criteria.setXactId(xactId);
        try {
            XactDaoFactory xactDaoFactory = new XactDaoFactory();
            XactDao xactDao = xactDaoFactory.createRmt2OrmXactDao(this.getSharedDao());
            xactDao.setDaoUser(this.getApiUser());
            List<XactDto> xactDto = xactDao.fetchXact(criteria);
            if (xactDto != null && xactDto.size() == 1) {
                xact = XactDaoFactory.createXact(xactDto.get(0));
            }
            else {
                this.msg = "Transaction was not found: " + xactId;
                throw new CashReceiptApiException(this.msg);
            }
        } catch (XactDaoException e) {
            throw new CashReceiptApiException(e);
        }

        SalesOrder so = null;
        SalesOrderDaoFactory soDaoFact = new SalesOrderDaoFactory();
        SalesOrderDao soDao = soDaoFact.createRmt2OrmDao(this.getSharedDao());
        SalesOrderDto soCriteria = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(null);
        soCriteria.setSalesOrderId(salesOrderId);
        try {
            List<SalesOrderDto> soDto = soDao.fetchSalesOrder(soCriteria);
            if (soDto != null && soDto.size() == 1) {
                so = SalesOrderDaoFactory.createOrmSalesOrder(soDto.get(0));
            }
            else {
                this.msg = "Sales order was not found: " + salesOrderId;
                throw new CashReceiptApiException(this.msg);
            }
        } catch (SalesOrderDaoException e) {
            throw new CashReceiptApiException(e);
        }

        CustomerExt cust = null;
        SubsidiaryDaoFactory subDaoFact = new SubsidiaryDaoFactory();
        CustomerDao custDao = subDaoFact.createRmt2OrmCustomerDao(this.getSharedDao());
        CustomerDto custCriteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
        custCriteria.setCustomerId(so.getCustomerId());
        try {
            List<CustomerDto> custDto = custDao.fetch(custCriteria);
            if (custDto != null && custDto.size() == 1) {
                cust = SubsidiaryDaoFactory.createCustomerExtBean(custDto.get(0));
                double bal = custDao.calculateBalance(cust.getCustomerId());
                cust.setBalance(bal);
            }
            else {
                this.msg = "Unable to perform cash receipts confirmation due to customer, " + so.getCustomerId()
                        + ", was not found";
                throw new CashReceiptApiException(this.msg);
            }
        } catch (SubsidiaryDaoException e) {
            throw new CashReceiptApiException(e);
        }

        StringBuffer xmlBuf = new StringBuffer();
        xmlBuf.append(cust.toXml());
        xmlBuf.append(so.toXml());
        xmlBuf.append(xact.toXml());
        return xmlBuf.toString();
    }
}