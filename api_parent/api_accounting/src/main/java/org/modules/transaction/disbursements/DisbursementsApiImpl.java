package org.modules.transaction.disbursements;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.transaction.disbursements.DisbursementsDao;
import org.dao.transaction.disbursements.DisbursementsDaoException;
import org.dao.transaction.disbursements.DisbursementsDaoFactory;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.modules.CommonAccountingConst;
import org.modules.transaction.AbstractXactApiImpl;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;

import com.InvalidDataException;
import com.api.persistence.DaoClient;
import com.api.util.RMT2String2;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * Class provides an implementation for managing disbursement transactions.
 * 
 * @author Roy Terrell
 * 
 */
public class DisbursementsApiImpl extends AbstractXactApiImpl implements DisbursementsApi {

    private static final Logger logger = Logger.getLogger(DisbursementsApiImpl.class);

    private DisbursementsDaoFactory daoFact;

    private DisbursementsDao dao;

    private boolean creditorDisb;

    /**
     * Creates an DisbursementsApiImpl which creates a stand alone connection.
     */
    DisbursementsApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an DisbursementsApiImpl which creates a stand alone connection.
     * 
     * @param appName
     *            the application name which should also translate to the JDBC
     *            Datasource name.
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
    public List<XactDto> get(XactDto criteria, String customCriteria) throws DisbursementsApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Base Transaction criteria object cannot be null", e);
        }
        
        // Cannot return all cash disbursements transactions
        try {
            Verifier.verify(this.isXactCriteriaPropertySet(criteria));
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Cannot return all cash disbursements transactions.  At least one transaction criteria property must be set to filter data", e);
        }
        
        String sqlCriteria = this.parseCriteria(customCriteria);
        List<XactDto> results;
        try {
            criteria.setCriteria(sqlCriteria);
            results = dao.fetchDisbursmentByXact(criteria);
            if (results == null) {
                return null;
            }
            return results;
        } catch (DisbursementsDaoException e) {
            StringBuilder buf = new StringBuilder();
            buf.append("Database error occurred retrieving disbursements by transaction type using selection criteria, ");
            buf.append(criteria.toString());
            buf.append((sqlCriteria == null ? "No custom criteria" : sqlCriteria));
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
    public List<XactTypeItemActivityDto> getItems(XactTypeItemActivityDto criteria, String customCriteria)
            throws DisbursementsApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Transaction Type Item Activity criteria object cannot be null", e);
        }
        // Cannot return all cash disbursements transactions items
        try {
            Verifier.verify(this.isXactCriteriaPropertySet(criteria));
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Cannot return all cash disbursements transactions.  At least one transaction criteria property must be set to filter data", e);
        }
        
        String sqlCriteria = this.parseCriteria(customCriteria);
        List<XactTypeItemActivityDto> results;
        try {
            criteria.setCriteria(sqlCriteria);
            results = dao.fetchDisbursmentByXactItem(criteria);
            if (results == null) {
                return null;
            }
            return results;
        } catch (DisbursementsDaoException e) {
            StringBuilder buf = new StringBuilder();
            buf.append("Database error occurred retrieving disbursements by transaction item type using selection criteria, ");
            buf.append(criteria);
            buf.append(sqlCriteria);
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
        return criteria;
    }

    private boolean isXactCriteriaPropertySet(XactDto criteria) {
        short criteriaCount = 0;
        if (criteria.getXactId() > 0) {
            criteriaCount++;
        }
        if (criteria.getXactDate() != null) {
            criteriaCount++;
        }
        if (criteria.getXactTypeId() > 0) {
            criteriaCount++;
        }
        if (criteria.getXactCatgId() > 0) {
            criteriaCount++;
        }
        if (RMT2String2.isNotEmpty(criteria.getXactConfirmNo())) {
            criteriaCount++;
        }
        if (criteria.getXactTenderId() > 0) {
            criteriaCount++;
        }
        if (RMT2String2.isNotEmpty(criteria.getCriteria())) {
            criteriaCount++;
        }
        return (criteriaCount > 0);
    }
    
    private boolean isXactCriteriaPropertySet(XactTypeItemActivityDto criteria) {
        short criteriaCount = 0;
        if (criteria.getXactId() > 0) {
            criteriaCount++;
        }
        if (criteria.getXactTypeItemActvId() > 0) {
            criteriaCount++;
        }
        if (criteria.getXactItemId() > 0) {
            criteriaCount++;
        }
        if (criteria.getActivityAmount() > 0) {
            criteriaCount++;
        }
        if (RMT2String2.isNotEmpty(criteria.getXactTypeItemActvName())) {
            criteriaCount++;
        }
        if (RMT2String2.isNotEmpty(criteria.getCriteria())) {
            criteriaCount++;
        }
        return (criteriaCount > 0);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.disbursements.DisbursementsApi#updateDisbursement
     * (org.dto.XactDto, java.util.List)
     */
    @Override
    public int updateTrans(XactDto xact, List<XactTypeItemActivityDto> items) throws DisbursementsApiException {
        // Identify this transaction as a non-creditor cash disbursement
        this.creditorDisb = false;
        
        try {
            this.validate(xact, items);    
        }
        catch (Exception e) {
            throw new DisbursementsApiException("Basic Cash Disbursement input data is not valid", e);
        }
        
        // Transaction type must be cash disbursement
        try {
            Verifier.verify( xact.getXactTypeId() == XactConst.XACT_TYPE_CASH_DISBURSE);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Update transaction failed due to transaction type is required to be cash disbursement", e);
        }
        
        int newXactId = 0;
        if (xact.getXactId() == 0) {
            xact.setXactTypeId(XactConst.XACT_TYPE_CASH_DISBURSE);
            newXactId = this.createDisbursement(xact, items);
        }
        else {
            newXactId = this.reverseDisbursement(xact, items);
        }
        return newXactId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.disbursements.DisbursementsApi#updateDisbursement
     * (org.dto.XactDto, java.util.List, int)
     */
    @Override
    public int updateTrans(XactDto xact, List<XactTypeItemActivityDto> items, Integer creditorId)
            throws DisbursementsApiException {
        
        // Identify this transaction as a creditor cash disbursement
        this.creditorDisb = true;
        
        try {
            this.validate(xact, items, creditorId);
        }
        catch (Exception e) {
            throw new DisbursementsApiException("Creditor Cash Disbursement input data is not valid", e);
        }
        
        // Transaction type must be cash disbursement
        try {
            Verifier.verify(xact.getXactTypeId() == XactConst.XACT_TYPE_CASH_DISBURSE_ACCOUNT);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Update transaction failed due to transaction type must be account cash disbursement", e);
        }
        
        int newXctId = 0;
        if (xact.getXactId() == 0) {
            newXctId = this.createDisbursement(xact, items);
        }
        else {
            newXctId = this.reverseDisbursement(xact, items);
        }

        // At this point a transaction was successfully created, and we need to
        // reflect that tranaction in the creditor's activity table. Since the 
        // creditor activity amount will always post as an offset to the base 
        // transaction amount, take the revised base transaction amount an 
        // reverse it.
        double xactAmount = xact.getXactAmount();
        try {
            // Create creditor activity (transaction history) regarding
            // the disbursement.  The usual valdiations will take place in this call.
            super.createSubsidiaryActivity(creditorId, newXctId, xactAmount);
        } catch (XactApiException e) {
            throw new DisbursementsApiException("Unable to process cash disbursement transaction", e);
        }
        return newXctId;
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
    private int createDisbursement(XactDto xact,  List<XactTypeItemActivityDto> items)
            throws DisbursementsApiException {
        int xactId = 0;
        try {
            // Make base transaction amount negative
            xact.setXactAmount(xact.getXactAmount() * XactConst.REVERSE_MULTIPLIER);
            xactId = super.update(xact, items);
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
    private int reverseDisbursement(XactDto xact, List<XactTypeItemActivityDto> items) throws DisbursementsApiException {
        // Cannot reverse payment transaction that has been finalized
        boolean xactModifiable = false;
        try {
            xactModifiable = this.isModifiable(xact);
        }  catch (InvalidDataException e) {
            throw new DisbursementsApiException("Failed to reverse Cash Disbursement due invalid data", e);
        }
        if (!xactModifiable) {
            msg = "Cash Disbursement transaction is already finalized";
            throw new DisbursementsApiException(msg);
        }
        
        // Reverse transaction
        int newXactId = 0;
        try {
            if (xact.getXactDate() == null) {
                xact.setXactDate(new java.util.Date());    
            }
            if (xact.getXactTenderId() == 0) {
                xact.setXactTenderId(XactConst.TENDER_CASH);
            }
            newXactId = this.reverse(xact, items);
        } catch (XactApiException e) {
            throw new DisbursementsApiException("Error reversing Cash Disbursement transaction", e);
        }
        
        // Finalize Transaction
        try {
            this.finalizeXact(xact);
        } catch (XactApiException e) {
            throw new DisbursementsApiException("Error finalizing Cash Disbursement transaction after reversal", e);
        }
        
        return newXactId;
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
    @Override
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
     * This method checks the whether the cash disbursement transaction data is
     * valid.
     * <p>
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
     * @throws InvalidDataException
     *             When <i>xact</i> does not meet basic validation requirements,
     *             <i>xactItems</i> is null or is empty, or basic validations
     *             fail.
     * @see {@link AbstractXactApiImpl#validate(XactDto, List)}
     */
    @Override
    protected void validate(XactDto xact, List<XactTypeItemActivityDto> xactItems) {
        // Transaction items are only required for general cash disbursement
        // type transactions
        if (!this.creditorDisb) {
            if (xactItems == null || xactItems.size() == 0) {
                this.msg = "Non-Creditor cash disbursement transaction must contain at least one item detail";
                logger.error(this.msg);
                throw new InvalidDataException(this.msg);
            }
        }

        // Perform common validations
        super.validate(xact, xactItems);
    }

    /**
     * This method checks the whether the cash disbursement transaction data is
     * valid.
     * <p>
     * if <i>xactItems</i> of the general cash disbursement transaction is not
     * null, contains at least one element with an number. If successful, basic
     * validations from the ancestor are performed for <i>xact</> and instance
     * of {@link XactTypeItemActivityDto}, and the creditor id is a valid
     * <i>xactItems</>.
     * 
     * @param xact
     *            {@link XactDto} instance.
     * @param xactItems
     *            A List of {@link XactTypeItemActivityDto} instances.
     * @param creditorId
     *            the id of the creditor
     * @throws InvalidDataException
     *             When <i>xact</i> does not meet basic validation requirements,
     *             <i>xactItems</i> is null or is empty, creditor id is null or
     *             not greater than zero, or basic validations fail.
     * @see {@link AbstractXactApiImpl#validate(XactDto, List)}
     */
    protected void validate(XactDto xact, List<XactTypeItemActivityDto> xactItems, Integer creditorId) {
        this.validate(xact, xactItems);

        // Validate Creditor Id
        try {
            Verifier.verifyNotNull(creditorId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id for cash Disbursement cannot be null", e);
        }
        try {
            Verifier.verifyPositive(creditorId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id for cash Disbursement must be greater than zero", e);
        }
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
    @Override
    protected void postValidate(XactDto xact) {
        super.postValidate(xact);

        java.util.Date today = new java.util.Date();

        // Verify that transaction date has a value.
        try {
            Verifier.verifyNotNull(xact.getXactDate());
        } catch (VerifyException e) {
            throw new InvalidDataException("Cash disbursement transaction date cannot be null", e);
        }

        // Verify that the transacton date value is valid
        try {
            Verifier.verify(xact.getXactDate().getTime() <= today.getTime());
        } catch (VerifyException e) {
            throw new InvalidDataException("Cash disbursement transaction date cannot be greater than the current date",
                    e);
        }

        // Verify that transaction tender has a value and is valid.
        try {
            Verifier.verifyPositive(xact.getXactTenderId());
        } catch (VerifyException e) {
            throw new InvalidDataException("Cash disbursement tender id must be greater than zero", e);
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
                try {
                    Verifier.verifyNotEmpty(xact.getXactNegInstrNo());
                } catch (VerifyException e) {
                    throw new InvalidDataException(
                            "Cash disbursement transaction tender must be associated with a tender number", e);
                }
        }

        // Ensure that transaction reason is entered.
        try {
            Verifier.verifyNotEmpty(xact.getXactReason());
        } catch (VerifyException e) {
            throw new InvalidDataException("Cash disbursement transaction reason cannot be null or empty", e);
        }
        return;
    }

}
