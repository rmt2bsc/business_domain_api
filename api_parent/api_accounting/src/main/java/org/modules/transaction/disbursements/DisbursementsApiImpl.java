package org.modules.transaction.disbursements;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.transaction.disbursements.DisbursementsDao;
import org.dao.transaction.disbursements.DisbursementsDaoException;
import org.dao.transaction.disbursements.DisbursementsDaoFactory;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.modules.transaction.AbstractXactApiImpl;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;

import com.api.persistence.DaoClient;

/**
 * Class provides an implementation for managing disbursement transactions.
 * 
 * @author Roy Terrell
 * 
 */
public class DisbursementsApiImpl extends AbstractXactApiImpl implements
        DisbursementsApi {

    private static final Logger logger = Logger
            .getLogger(DisbursementsApiImpl.class);

    private DisbursementsDaoFactory daoFact;

    private DisbursementsDao dao;

    private boolean creditorDisb;

    /**
     * Creates an DisbursementsApiImpl which creates a stand alone connection.
     */
    DisbursementsApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmDao();
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an DisbursementsApiImpl which creates a stand alone connection.
     * 
     * @param appName
     */
    protected DisbursementsApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an DisbursementsApiImpl initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param connection
     */
    protected DisbursementsApiImpl(DaoClient connection) {
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
        this.daoFact = new DisbursementsDaoFactory();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.disbursements.DisbursementsApi#getByTransaction
     * (java.lang.String)
     */
    @Override
    public List<XactDto> getByTransaction(String criteria)
            throws DisbursementsApiException {
        String selectCriteria = this.parseCriteria(criteria);
        List<XactDto> results;
        try {
            results = dao.fetchDisbursmentByXact(selectCriteria);
            if (results == null) {
                return null;
            }
            return results;
        } catch (DisbursementsDaoException e) {
            StringBuilder buf = new StringBuilder();
            buf.append("Database error occurred retrieving disbursements by transaction type using selection criteria, ");
            buf.append(selectCriteria);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new DisbursementsApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.disbursements.DisbursementsApi#getByTransactionItem
     * (java.lang.String)
     */
    @Override
    public List<XactTypeItemActivityDto> getByTransactionItem(String criteria)
            throws DisbursementsApiException {
        String selectCriteria = this.parseCriteria(criteria);
        List<XactTypeItemActivityDto> results;
        try {
            results = dao.fetchDisbursmentByXactItem(selectCriteria);
            if (results == null) {
                return null;
            }
            return results;
        } catch (DisbursementsDaoException e) {
            StringBuilder buf = new StringBuilder();
            buf.append("Database error occurred retrieving disbursements by transaction item type using selection criteria, ");
            buf.append(selectCriteria);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new DisbursementsApiException(this.msg, e);
        }
    }

    /**
     * Interprets the incoming criteria, which can be in any format, and creates
     * meaningful selection criteria that is usable by the target DAO
     * implementation.
     * 
     * @param criteria
     *            String of the criteria tat is to be interpreted
     * @return
     */
    private String parseCriteria(String criteria) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.disbursements.DisbursementsApi#updateDisbursement
     * (org.dto.XactDto, java.util.List)
     */
    @Override
    public int updateDisbursement(XactDto xact,
            List<XactTypeItemActivityDto> items)
            throws DisbursementsApiException {
        // Identify this transaction as a non-creditor cash disbursement
        this.creditorDisb = false;
        int xactId = 0;
        if (xact.getXactId() <= 0) {
            xact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
            xactId = this.createDisbursement(xact, items);
        }
        else {
            xactId = this.reverseDisbursement(xact, items);
        }
        return xactId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.disbursements.DisbursementsApi#updateDisbursement
     * (org.dto.XactDto, java.util.List, int)
     */
    @Override
    public int updateDisbursement(XactDto xact,
            List<XactTypeItemActivityDto> items, int creditorId)
            throws DisbursementsApiException {
        // Identify this transaction as a creditor cash disbursement
        this.creditorDisb = true;
        int xactId = 0;
        if (xact.getXactId() <= 0) {
            xact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBACCT);
            xactId = this.createDisbursement(xact, items);
        }
        else {
            xactId = this.reverseDisbursement(xact, items);
        }

        // At this point a transaction was successfully created, and we need to
        // reflect that tranaction
        // in the creditor's activity table. Since the creditor activity amount
        // will always post as an
        // offset to the base transaction amount, take the revised base
        // transaction amount an reverse it.
        double xactAmount = xact.getXactAmount();
        try {
            // Create creditor activity (transaction history) regarding
            // the disbursement.
            super.createSubsidiaryTransaction(creditorId, xactId, xactAmount);
        } catch (XactApiException e) {
            throw new DisbursementsApiException(e);
        }
        return xactId;
    }

    /**
     * Creates a general cash disbursement transasction
     * 
     * @param xact
     *            The disbursement transaction to be added to the database.
     * @param items
     *            A List of {@link XactTypeItemActivityDto} objects.
     * @return The id of the new transaction.
     * @throws DisbursementsApiException
     */
    private int createDisbursement(XactDto xact,
            List<XactTypeItemActivityDto> items)
            throws DisbursementsApiException {
        int xactId = 0;
        try {
            xact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
            xactId = this.update(xact, items);
            return xactId;
        } catch (XactApiException e) {
            throw new DisbursementsApiException(e);
        }
    }

    /**
     * Reverses a disbursement and finalizes the source tranaction
     * 
     * @param xact
     *            disbursement transaction.
     * @param items
     *            A List of {@link XactTypeItemActivityDto} objects.
     * @return The id of the reversed transaction.
     * @throws DisbursementsApiException
     *             If customer payment transction is final or a general
     *             transction error occurs.
     */
    private int reverseDisbursement(XactDto xact,
            List<XactTypeItemActivityDto> items)
            throws DisbursementsApiException {
        int xactId = 0;
        try {
            // Cannot reverse payment transaction that has been finalized
            if (!this.isTransModifiable(xact)) {
                msg = "Cash Disbursement cannot be reversed since it is already finalized";
                logger.error(msg);
                throw new DisbursementsApiException(msg);
            }

            this.finalizeXact(xact);
            xact.setXactDate(new java.util.Date());
            if (xact.getXactTenderId() == 0) {
                xact.setXactTenderId(XactConst.TENDER_CASH);
            }
            xactId = this.reverse(xact, items);
            return xactId;
        } catch (XactApiException e) {
            throw new DisbursementsApiException(e);
        }
    }

    /**
     * Ensures that the cash disbursement amount is a negative value.
     * <p>
     * Cash disbursement transactions require the reversal multiplier to be
     * applied which will yield a negative amount representing cash outgoing.
     * The reversal of an existing Cash Disbursement transaction requires the
     * reversal mulitplier to be applied which offsets the orginal transaction.
     * 
     * @param _xact
     *            The target transaction
     */
    protected void preCreateXact(XactDto xact) {
        double xactAmount = 0;
        super.preCreateXact(xact);
        // Ensure that cash disbursement transaction is posted to the base
        // transaction table as a negative amount.
        if (xact.getXactSubtypeId() == 0) {
            xactAmount = xact.getXactAmount() * XactConst.REVERSE_MULTIPLIER;
            xact.setXactAmount(xactAmount);
        }
        return;
    }

    /**
     * This method checks if <i>xactItems</i> of the general cash disbursement
     * transaction is not null and contains at least one element with an
     * instance of {@link XactTypeItemActivityDto}. If successful, basic
     * validations from the ancestor are performed for <i>xact</> and
     * <i>xactItems</>.
     * 
     * @param xact
     *            {@link XactDto} instance.
     * @param xactItems
     *            A List of {@link XactTypeItemActivityDto} instances.
     * @throws XactApiException
     *             When <i>xact</i> does not meet basic validation requirements,
     *             <i>xactItems</i> is null or is empty, or basic validations
     *             fail.
     * @see {@link AbstractXactApiImpl#validate(XactDto, List)}
     */
    @Override
    public void validate(XactDto xact, List<XactTypeItemActivityDto> xactItems)
            throws XactApiException {
        // Transaction items are only required for general cash disbursement
        // type transactions
        if (!this.creditorDisb) {
            if (xactItems == null || xactItems.size() == 0) {
                this.msg = "Non-Creditor cash disbursement transaction must contain at least one item detail";
                logger.error(this.msg);
                throw new XactApiException(this.msg);
            }
        }

        // Perform common validations
        super.validate(xact, xactItems);
    }

    /**
     * Ensures that the base of the transaction meets general Cash Disbursement
     * validations. The following validations must be satified:
     * <ul>
     * <li>Transaction date must have a value</li>
     * <li>Transaction date is a valid date</li>
     * <li>Transaction date is not greater than curent date</li>
     * <li>Transaction tender is entered</li>
     * <li>Transaction tender's negotiable instrument number is entered, if
     * applicable.</li>
     * <li>Transaction amount must be entered</li>
     * <li>Transaction reason is entered</li>
     * </ul>
     * 
     * @param xact
     *            The transaction object to be validated.
     * @throws XactApiException
     *             Validation error occurred.
     */
    protected void postValidateXact(XactDto xact) throws XactApiException {
        super.postValidate(xact);
        java.util.Date today = new java.util.Date();

        // Verify that transaction date has a value.
        if (xact.getXactDate() == null) {
            this.msg = "Transaction must be associated with a date";
            logger.error(this.msg);
            throw new XactApiException(this.msg);

        }
        // Verify that the transacton date value is valid
        if (xact.getXactDate().getTime() > today.getTime()) {
            this.msg = "Transaction date cannot be greater than the current date";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }

        // Verify that transaction tender has a value and is valid.
        if (xact.getXactTenderId() <= 0) {
            this.msg = "Transaction must be associated with a tender";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        // Verify that the transaction's tender is assoicated with a negotiable
        // instrument number, if applicable
        switch (xact.getXactTenderId()) {
            case XactConst.TENDER_CHECK:
            case XactConst.TENDER_COMPANY_CREDIT:
            case XactConst.TENDER_CREDITCARD:
            case XactConst.TENDER_DEBITCARD:
            case XactConst.TENDER_INSURANCE:
            case XactConst.TENDER_MONEYORDER:
                if (xact.getXactNegInstrNo() == null
                        || xact.getXactNegInstrNo().equals("")) {
                    this.msg = "Transaction tender must be associated with a tender number";
                    logger.error(this.msg);
                    throw new XactApiException(this.msg);
                }
        }

        // Ensure that reaso is entered.
        if (xact.getXactReason() == null || xact.getXactReason().equals("")) {
            this.msg = "Transaction reason cannot be blank...Save failed.";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        return;
    }

}