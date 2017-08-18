package org.modules.transaction.receipts;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.transaction.receipts.CashReceiptDao;
import org.dao.transaction.receipts.CashReceiptDaoException;
import org.dao.transaction.receipts.CashReceiptDaoFactory;
import org.dao.transaction.sales.SalesOrderDao;
import org.dao.transaction.sales.SalesOrderDaoFactory;
import org.dto.SalesOrderDto;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.modules.transaction.AbstractXactApiImpl;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;

import com.api.config.ConfigConstants;
import com.api.messaging.MessageException;
import com.api.messaging.MessageManager;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.persistence.DaoClient;
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
public class CashReceiptApiImpl extends AbstractXactApiImpl implements
        CashReceiptApi {

    private static final Logger logger = Logger
            .getLogger(CashReceiptApiImpl.class);

    private SalesOrderDaoFactory daoFact;

    private SalesOrderDao dao;

    /**
     * Creates an CashReceiptApiImpl which creates a stand alone connection.
     */
    CashReceiptApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmDao();
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an CashReceiptApiImpl which creates a stand alone connection.
     */
    protected CashReceiptApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
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
     * @throws CashReceiptApiException
     */
    public void applyCustomerPayment(SalesOrderDto order, double amount)
            throws CashReceiptApiException {
        XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        xact.setXactAmount(amount);
        xact.setXactReason("Full payment received for sales order #"
                + order.getSalesOrderId());
        xact.setXactTypeId(XactConst.XACT_TYPE_CASHPAY);

        // Create cash receipt transaction for the given sales order.
        // being reversed.
        try {
            this.createCashPayment(xact, order.getCustomerId());
        } catch (CashReceiptApiException e) {
            this.msg = "Unable to apply customer payment sales order, "
                    + order.getSalesOrderId();
            logger.error(this.msg);
            throw new CashReceiptApiException(this.msg, e);
        }

        // Send email confirmation
        try {
            this.emailPaymentConfirmation(order.getSalesOrderId(),
                    xact.getXactId());
        } catch (CashReceiptApiException e) {
            this.msg = "Error notifying customer of payment confirmation via SMTP for sales order id, "
                    + order.getSalesOrderId();
            logger.error(this.msg);
            throw new CashReceiptApiException(this.msg, e);
        }
    }

    /**
     * Creates a customer payment transaction or reverses and existing customer
     * payment transaction. If the transaction id that is encapsulated in xact
     * is 0, then a new customer payment transaction is created. Otherwise, an
     * existing customer payment transaction is reversed.
     * 
     * @param xact
     *            Source transaction.
     * @param customerId
     *            The id of the customer of this transaction.
     * @return The id of the sales order payment transaction.
     * @throws CashReceiptApiException
     */
    @Override
    public int createCashPayment(XactDto xact, int customerId)
            throws CashReceiptApiException {

        if (xact == null) {
            this.msg = "Transaction object cannot be null";
            logger.error(this.msg);
            throw new CashReceiptApiException(this.msg);
        }
        // Customer Id must be a value greater than zero.
        if (customerId <= 0) {
            this.msg = "Customer id is invalid";
            logger.error(this.msg);
            throw new CashReceiptApiException(this.msg);
        }

        // Determine if we are creating or reversing the customer payment.
        int xactId = 0;
        if (xact.getXactId() <= 0) {
            xactId = this.createCustomerPayment(xact, customerId);
        }
        else {
            xactId = this.reverseCustomerPayment(xact, customerId);
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
    protected int createCustomerPayment(XactDto xact, int customerId)
            throws CashReceiptApiException {
        int xactId = 0;
        double xactAmount = 0;

        xact.setXactTypeId(XactConst.XACT_TYPE_CASHPAY);
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
    protected int reverseCustomerPayment(XactDto xact, int customerId)
            throws CashReceiptApiException {
        int xactId = 0;
        double xactAmount = 0;

        try {
            // Cannot reverse payment transaction that has been finalized
            if (!this.isModifiable(xact)) {
                msg = "Customer Payment cannot be reversed since it is already finalized";
                logger.error(msg);
                throw new CashReceiptApiException(msg);
            }

            this.finalizeXact(xact);
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
    protected void preReverse(XactDto xact,
            List<XactTypeItemActivityDto> xactItems) {
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
     * @throws XactException
     *             Validation error occurred.
     */
    protected void postValidate(XactDto xact) throws XactApiException {
        // Ensure that reason has a value
        if (xact.getXactReason() == null || xact.getXactReason().equals("")) {
            this.msg = "Transaction reason cannot be blank";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
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
     * @throws CashReceiptApiException
     */
    public void emailPaymentConfirmation(int salesOrderId, int xactId)
            throws CashReceiptApiException {

        CashReceiptDaoFactory fact = new CashReceiptDaoFactory();
        CashReceiptDao dao = fact.createRmt2OrmDao(this.dao);
        String custData;
        try {
            custData = dao.buildPaymentConfirmation(salesOrderId, xactId);
        } catch (CashReceiptDaoException e) {
            this.msg = "Unable to retreive customer payment email confirmation body";
            logger.error(this.msg);
            throw new CashReceiptApiException(this.msg);
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
            throw new CashReceiptApiException(e);
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
            return;
        }
        try {
            api.sendMessage(msg);
            api.close();
            this.msg = "Customer payment confirmation was sent via email successfully";
        } catch (MessageException e) {
            this.msg = "Customer payment confirmation error.  "
                    + e.getMessage();
            throw new CashReceiptApiException(this.msg, e);
        }
    }

}