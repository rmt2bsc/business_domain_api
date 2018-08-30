package org.modules.transaction.purchases.creditor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dao.transaction.purchases.creditor.CreditorPurchasesDao;
import org.dao.transaction.purchases.creditor.CreditorPurchasesDaoException;
import org.dao.transaction.purchases.creditor.CreditorPurchasesDaoFactory;
import org.dto.CreditorDto;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.XactCreditChargeDto;
import org.dto.XactCustomCriteriaDto;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.dto.adapter.orm.transaction.purchases.creditor.Rmt2CreditChargeDtoFactory;
import org.modules.CommonAccountingConst;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.CreditorApiException;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.subsidiary.SubsidiaryException;
import org.modules.transaction.AbstractXactApiImpl;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;

import com.InvalidDataException;
import com.NotFoundException;
import com.RMT2RuntimeException;
import com.api.persistence.DaoClient;
import com.api.persistence.DatabaseException;
import com.api.util.RMT2Date;
import com.api.util.RMT2String;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * Api Implementation of CreditorPurchasesApi that manages creditor purchase
 * transactions.
 * 
 * @author Roy Terrell
 * 
 */
class CreditorPurchasesApiImpl extends AbstractXactApiImpl implements CreditorPurchasesApi {

    private static final Logger logger = Logger.getLogger(CreditorPurchasesApiImpl.class);

    private static final String DATA_UNAVAILABLE_INDICATOR = "Unavailable";

    private CreditorPurchasesDaoFactory daoFact;

    private CreditorPurchasesDao dao;


    /**
     * Creates a CreditorPurchasesApiImpl object in which the configuration is
     * identified by the name of a given application.
     * 
     * @param appName
     */
    public CreditorPurchasesApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        this.dao.setDaoUser(this.apiUser);
        return;
    }

    /**
     * Creates an CreditorPurchasesApiImpl initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param connection
     */
    public CreditorPurchasesApiImpl(DaoClient connection) {
        super(connection);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
    }
    
    @Override
    public void init() {
        super.init();
        this.daoFact = new CreditorPurchasesDaoFactory();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.purchases.creditor.CreditorPurchasesApi#get(int)
     */
    @Override
    public XactCreditChargeDto get(Integer xactId) throws CreditorPurchasesApiException {
        try {
            Verifier.verifyNotNull(xactId);
        } catch (VerifyException e) {
            this.msg = "Transaction Id is required";
            throw new InvalidDataException(this.msg, e);
        }
        try {
            Verifier.verifyPositive(xactId);
        } catch (VerifyException e) {
            this.msg = "Transaction Id is required to be greater than zero";
            throw new InvalidDataException(this.msg, e);
        }

        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory.createCreditChargeInstance(null, null);
        criteria.setXactId(xactId);
        List<XactCreditChargeDto> results = this.get(criteria, null);
        if (results == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder();
        if (results.size() > 1) {
            buf.append("Error: Creditor purchase query returned too many rows for transaction id, ");
            buf.append(xactId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  transactions were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new CreditorPurchasesApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.purchases.creditor.CreditorPurchasesApi#get(java
     * .util.Date)
     */
    @Override
    public List<XactCreditChargeDto> get(Date xactDate) throws CreditorPurchasesApiException {
        try {
            Verifier.verifyNotNull(xactDate);
        } catch (VerifyException e) {
            this.msg = "Transaction date is required";
            throw new InvalidDataException(this.msg, e);
        }
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory
                .createCreditChargeInstance(null, null);
        criteria.setXactDate(xactDate);
        StringBuilder msgBuf = new StringBuilder();
        try {
            return this.get(criteria, null);
        } catch (Exception e) {
            msgBuf.append("Error retrieving creditor purchases by transaction date, ");
            msgBuf.append(RMT2Date.formatDate(xactDate, "MM-dd-yyyy"));
            this.msg = msgBuf.toString();
            logger.error(this.msg, e);
            throw new CreditorPurchasesApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.purchases.creditor.CreditorPurchasesApi#get(java
     * .lang.String)
     */
    @Override
    public List<XactCreditChargeDto> get(String criteria) throws CreditorPurchasesApiException {
        try {
            Verifier.verifyNotEmpty(criteria);
        } catch (VerifyException e) {
            this.msg = "Custom criteria is required and cannot be null or empty";
            throw new InvalidDataException(this.msg, e);
        }
        XactCreditChargeDto criteriaObj = Rmt2CreditChargeDtoFactory.createCreditChargeInstance(null, null);
        criteriaObj.setCriteria(criteria);
        StringBuilder msgBuf = new StringBuilder();
        try {
            return this.get(criteria);
        } catch (Exception e) {
            msgBuf.append("Error retrieving creditor purchases using custom criteria: ");
            msgBuf.append(criteria);
            this.msg = msgBuf.toString();
            logger.error(this.msg);
            throw new CreditorPurchasesApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.purchases.creditor.CreditorPurchasesApi#getByCreditor
     * (int)
     */
    @Override
    public List<XactCreditChargeDto> getByCreditor(Integer creditorId) throws CreditorPurchasesApiException {
        try {
            Verifier.verifyNotNull(creditorId);
        } catch (VerifyException e) {
            this.msg = "Creditor Id is required";
            throw new InvalidDataException(this.msg, e);
        }
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory.createCreditChargeInstance(null, null);
        criteria.setCreditorId(creditorId);
        StringBuilder msgBuf = new StringBuilder();
        try {
            return this.get(criteria, null);
        } catch (Exception e) {
            msgBuf.append("Error retrieving creditor purchases using creditor id: ");
            msgBuf.append(creditorId);
            this.msg = msgBuf.toString();
            logger.error(this.msg);
            throw new CreditorPurchasesApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.purchases.creditor.CreditorPurchasesApi#getByAcctNo
     * (java.lang.String)
     */
    @Override
    public List<XactCreditChargeDto> getByAcctNo(String accountNo) throws CreditorPurchasesApiException {
        try {
            Verifier.verifyNotEmpty(accountNo);
        } catch (VerifyException e) {
            this.msg = "Account number is required";
            throw new InvalidDataException(this.msg, e);
        }
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory.createCreditChargeInstance(null, null);
        criteria.setAccountNumber(accountNo);
        StringBuilder msgBuf = new StringBuilder();
        try {
            return this.get(criteria, null);
        } catch (Exception e) {
            msgBuf.append("Error retrieving creditor purchases using creditor account number: ");
            msgBuf.append(accountNo);
            this.msg = msgBuf.toString();
            logger.error(this.msg);
            throw new CreditorPurchasesApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.purchases.creditor.CreditorPurchasesApi#
     * getByConfirmNo(java.lang.String)
     */
    @Override
    public List<XactCreditChargeDto> getByConfirmNo(String confirmNo) throws CreditorPurchasesApiException {
        try {
            Verifier.verifyNotEmpty(confirmNo);
        } catch (VerifyException e) {
            this.msg = "Confirmation number is required";
            throw new InvalidDataException(this.msg, e);
        }
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory.createCreditChargeInstance(null, null);
        criteria.setXactConfirmNo(confirmNo);
        StringBuilder msgBuf = new StringBuilder();
        try {
            return this.get(criteria, null);
        } catch (Exception e) {
            msgBuf.append("Error retrieving creditor purchases using creditor transaction confirmation number: ");
            msgBuf.append(confirmNo);
            this.msg = msgBuf.toString();
            logger.error(this.msg);
            throw new CreditorPurchasesApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.purchases.creditor.CreditorPurchasesApi#getByReason
     * (java.lang.String)
     */
    @Override
    public List<XactCreditChargeDto> getByReason(String reason) throws CreditorPurchasesApiException {
        try {
            Verifier.verifyNotEmpty(reason);
        } catch (VerifyException e) {
            this.msg = "Transaction reason is required";
            throw new InvalidDataException(this.msg, e);
        }
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory.createCreditChargeInstance(null, null);
        criteria.setXactReason(reason);
        StringBuilder msgBuf = new StringBuilder();
        try {
            return this.get(criteria, null);
        } catch (Exception e) {
            msgBuf.append("Error retrieving creditor purchases using creditor transaction reason/source description: ");
            msgBuf.append(reason);
            this.msg = msgBuf.toString();
            logger.error(this.msg);
            throw new CreditorPurchasesApiException(this.msg, e);
        }
    }

    /**
     * Retrieves creditor purchases transaction objects, which includes contact
     * information, using <i>criteria</i> as a filter.
     * 
     * @param criteria
     *            an instance of {@link XactCreditChargeDto}
     * @return A List of {@link XactCreditChargeDto} objects
     * @throws CreditorPurchasesApiException
     */
    @Override
    public List<XactCreditChargeDto> get(XactCreditChargeDto criteria, XactCustomCriteriaDto customCriteria) throws CreditorPurchasesApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            this.msg = "Creditor criteria object is required";
            throw new InvalidDataException(this.msg, e);
        }
        
        String sqlCriteria = this.parseCriteria(customCriteria);
        StringBuilder msgBuf = new StringBuilder();
        List<XactCreditChargeDto> credXactResults;
        try {
            criteria.setCriteria(sqlCriteria);
            credXactResults = this.dao.fetch(criteria);
            if (credXactResults == null) {
                msgBuf.append("Creditor purchase data was not found using criteria object");
                logger.warn(msgBuf);
                return null;
            }
        } catch (CreditorPurchasesDaoException e) {
            msgBuf.append("Database error occurred retrieving creditor purchases by criteria object");
            this.msg = msgBuf.toString();
            logger.error(this.msg);
            throw new CreditorPurchasesApiException(this.msg, e);
        }

        // Retrieve creditor contact info
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi creditorApi = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto contactInfoCriteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
       
        // Get list of business id's to use for fetching common contact records.
        List<Integer> businessIdList = new ArrayList<Integer>();
        for (XactCreditChargeDto item : credXactResults) {
            businessIdList.add(item.getCreditorId());
        }
        contactInfoCriteria.setContactIdList(businessIdList);
        Map<Integer, SubsidiaryContactInfoDto> contactResults = null;
        try {
            contactResults = creditorApi.getContactInfo(contactInfoCriteria);
        } catch (SubsidiaryException e) {
            String msg = "Error occurred retrieving contact data for creditor purchases result set";
            logger.warn(msg);
        }
        
        try {
            Verifier.verifyNotNull(contactResults);
        }
        catch (VerifyException e) {
            // the contact fields for all transaction entries will be
            // populated with "Unavailable".
            contactResults = new HashMap<Integer, SubsidiaryContactInfoDto>();
        }

        // Add contact data to list of transactions
        this.mergeContactInfo(credXactResults, contactResults);

        msgBuf.append(credXactResults.size());
        msgBuf.append(" Creditor purchase object(s) were retrieved");
        logger.info(msgBuf);
        return credXactResults;
    }

    /**
     * Interprets the incoming criteria, which can be in any format, and creates
     * meaningful selection criteria that is usable by the target DAO
     * implementation.
     * 
     * @param criteria
     *            Instance of {@link XactCustomCriteriaDto} that is to be interpreted
     * @return
     */
    @Override
    protected String parseCriteria(XactCustomCriteriaDto criteria) {
        return null;
    }
    
    /**
     * Combines contact data to the list of creditor purchase transaction.
     * 
     * @param xactList
     *            List of {@link XactCreditChargeDto}
     * @param contactMap
     *            Map of {@link SubsidiaryContactInfoDto} keyed by business id
     */
    private void mergeContactInfo(List<XactCreditChargeDto> xactList,
            Map<Integer, SubsidiaryContactInfoDto> contactMap) {
        for (XactCreditChargeDto xact : xactList) {
            SubsidiaryContactInfoDto contact = contactMap.get(xact.getBusinessId());
            if (contact != null) {
                xact.setCreditorName(contact.getContactName());
                xact.setPhone(contact.getContactPhone());
            }
            else {
                xact.setCreditorName(DATA_UNAVAILABLE_INDICATOR);
                xact.setPhone(DATA_UNAVAILABLE_INDICATOR);
            }
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.purchases.creditor.CreditorPurchasesApi#get(int)
     */
    @Override
    public List<XactTypeItemActivityDto> getItems(Integer xactId) throws CreditorPurchasesApiException {
        try {
            Verifier.verifyNotNull(xactId);
        } catch (VerifyException e) {
            this.msg = "Transaction Id is required";
            throw new InvalidDataException(this.msg, e);
        }
        StringBuilder msgBuf = new StringBuilder();
        List<XactTypeItemActivityDto> results;
        try {
            results = this.dao.fetch(xactId);
            if (results == null) {
                msgBuf.append("Creditor purchase detail item(s) were not found by transaction id, ");
                msgBuf.append(xactId);
                logger.warn(msgBuf);
                return null;
            }
        } catch (CreditorPurchasesDaoException e) {
            msgBuf.append("Database error occurred retrieving creditor purchase item(s) by transaction Id, ");
            msgBuf.append(xactId);
            this.msg = msgBuf.toString();
            logger.error(this.msg);
            throw new CreditorPurchasesApiException(this.msg, e);
        }
        msgBuf.append(results.size());
        msgBuf.append(" Creditor purchase object(s) were retrieved by transaction id, ");
        msgBuf.append(xactId);
        logger.info(msgBuf);
        return results;
    }

    /**
     * Creates a new or reverses an existing creditor purchase transaction.
     * <p>
     * If the transaction id encapsulated in <i>xact</i> is 0, then a new
     * transaction is created. Otherwise, an existing transaction is reversed.
     * The creditor activity is always posted as an offset to the base
     * transaction amount.
     * 
     * @param xact
     *            An instance of {@link XactCreditChargeDto}
     * @param items
     *            A List of {@link XactTypeItemActivityDto} objects representing
     *            the transaction details.
     * @return The id of the transaction created.
     * @throws CreditorPurchasesApiException
     *             when <i>xact</i> is null, validation errors, or general
     *             database access errors.
     */
    @Override
    public int update(XactCreditChargeDto xact, List<XactTypeItemActivityDto> items)
            throws CreditorPurchasesApiException {

        // We have to jump ahead of ancestor validation logic and check the
        // validity of the transaction object since it is the source of the
        // transaction id in which we need.
        try {
            Verifier.verifyNotNull(xact);
        } catch (VerifyException e) {
            this.msg = "Unable to access transaction id due to Creditor purchase transaction object is null";
            logger.error(this.msg);
            throw new CreditorPurchasesApiException(this.msg, e);
        }
        
        // Transaction type must be creditor purchases
        try {
            Verifier.verify( xact.getXactTypeId() == XactConst.XACT_TYPE_CREDITOR_PURCHASE);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Update transaction failed due to transaction type is required to be creditor purchases", e);
        }

        // Determine if we are creating or reversing the cash disbursement
        int xactId = 0;
        try {
            Verifier.verifyNotPositive(xact.getXactId());
            xactId = this.createPurchase(xact, items);
        }
        catch (VerifyException e) {
            xactId = this.reversePurchase(xact, items);
        }

        // Create the subsidiary entry for the creditor purchase transaction.
        try {
            super.createSubsidiaryActivity(xact.getCreditorId(), xact.getXactId(), xact.getXactAmount());
            return xactId;
        } catch (XactApiException e) {
            msg = "Unable to create subsiary entry for creditor purchase transacton";
            logger.error(msg, e);
            throw new CreditorPurchasesApiException(msg, e);
        }
    }

    /**
     * Creates a new creditor purchase transasction.
     * 
     * @param xact
     *            The transaction to be added to the database.
     * @param items
     *            An ArrayList of random objects.
     * @return The id of the new transaction.
     * @throws CreditorPurchasesApiException
     */
    protected int createPurchase(XactCreditChargeDto xact, List<XactTypeItemActivityDto> items)
            throws CreditorPurchasesApiException {
        try {
            int xactId = 0;
            xactId = super.update(xact, items);
            return xactId;
        } catch (XactApiException e) {
            throw new CreditorPurchasesApiException(e);
        }
    }

    /**
     * Prepends credit charge comments with a tag and assigns the transaction
     * negotiable instrument property to a masked credit card number.
     * <p>
     * If user did not input anything for the transction reason, then the method
     * is aborted which will allow postValidate to catch the error.
     * 
     * @param xact
     *            An instance of {@link XactCreditChargeDto}
     */
    @Override
    protected void preCreateXact(XactDto xact) {
        double xactAmount = 0;
        super.preCreateXact(xact);

        if (xact.getXactSubtypeId() == XactConst.XACT_SUBTYPE_NOT_ASSIGNED) {
            // Assign negotiable instrument number to transactions
            try {
                this.assignNegotialbeInstrumentNumber(xact);
            } catch (Exception e) {
                throw new RMT2RuntimeException("Error assigning transaction negotiable instrument number", e);
            }
        }
        return;
    }

    private void assignNegotialbeInstrumentNumber(XactDto xact) {
        int creditorId = 0;
        if (xact instanceof XactCreditChargeDto) {
            creditorId = ((XactCreditChargeDto) xact).getCreditorId();
        }

        // Assign last four digits of credit card number
        SubsidiaryApiFactory subFact = new SubsidiaryApiFactory();
        CreditorApi credApi = subFact.createCreditorApi(this.getSharedDao());
        try {
            CreditorDto creditor = credApi.getByCreditorId(creditorId);
            if (creditor == null) {
                this.msg = "Unable to create creditor purchase transction due to creditor's profile is not found in the database using creditor id: "
                        + creditorId;
                logger.error(this.msg);
                throw new NotFoundException();
            }

            // Try to use masked external credit card number in the event
            // transaction negotiable instrument is not available.
            if (xact.getXactNegInstrNo() == null) {
                String ccNoMask = RMT2String.maskCreditCardNumber(creditor.getExtAccountNumber());
                xact.setXactNegInstrNo(ccNoMask);
            }
        } catch (CreditorApiException e) {
            this.msg = "Unable to create creditor purchase transction due to the occurrence of a database error while attempting to fetch creditor's profile from the database using creditor id: "
                    + creditorId;
            logger.error(this.msg);
            throw new DatabaseException(this.msg, e);
        } finally {
            credApi = null;
        }
    }

    /**
     * Reverses an existing credit purchase transaction.
     * 
     * @param xact
     *            The target transaction
     * @param items
     *            A List of {@link XactTypeItemActivityDto} objects.
     * @return The id of the new transaction.
     * @throws CreditorPurchasesApiException
     *             If the transaction has already bee flagged as finalized or if
     *             a general transction error occurs.
     */
    protected int reversePurchase(XactCreditChargeDto xact, List<XactTypeItemActivityDto> items)
            throws CreditorPurchasesApiException {
        boolean xactModifiable = false;
        try {
            xactModifiable = this.isModifiable(xact);
        } catch (InvalidDataException e) {
            throw new CreditorPurchasesApiException("Failed to reverse creditor purchase due invalid data", e);
        }
        if (!xactModifiable) {
            msg = "Creditor purchase transaction is already finalized";
            throw new CreditorPurchasesApiException(msg);
        }

        // Reverse transaction
        int newXactId = 0;
        try {
            newXactId = this.reverse(xact, items);
        } catch (XactApiException e) {
            throw new CreditorPurchasesApiException("Error reversing Creditor Purchases transaction", e);
        }

        // Finalize Transaction
        try {
            this.finalizeXact(xact);
        } catch (XactApiException e) {
            throw new CreditorPurchasesApiException("Error finalizing Creditor Purchase transaction after reversal", e);
        }
        return newXactId;
    }

    /**
     * Sets the transaction date prior to creating a transaction as a result of
     * reversing an existing creditor purchase transaction.
     * 
     * @param xact
     *            The transaction that is being reversed.
     * @param xactItems
     *            Transaction items to be reversed.
     */
    @Override
    protected void preReverse(XactDto xact, List<XactTypeItemActivityDto> xactItems) {
        super.preReverse(xact, xactItems);
        if (xact.getXactDate() == null) {
            xact.setXactDate(new Date());
        }
    }

    /**
     * This method checks if the creditor purchase transaction is valid and has
     * at least one line item. If successful, basic validations from the
     * ancestor are performed for <i>xact</> and <i>items</>.
     * 
     * @param xact
     *            {@link XactDto} instance.
     * @param items
     *            A List of {@link XactTypeItemActivityDto} instances.
     * @throws InvalidDataException
     *             When <i>xact</i> does not meet basic validation requirements,
     *             <i>items</i> is null or is empty, or basic validations fail.
     */
    @Override
    public void validate(XactDto xact, List<XactTypeItemActivityDto> items) {
        // Perform common validations
        super.validate(xact, items);

        // Creditor purchase requires at least one detail item. The above
        // ancestor code will allow transactions withot detail items.
        try {
            Verifier.verifyNotEmpty(items);
        } catch (VerifyException e) {
            this.msg = "Creditor purchase transaction must contain at least one line item";
            throw new InvalidDataException(this.msg, e);
        }
    }

    /**
     * Ensures that the base of the transaction meets general creditor purchase
     * validations.
     * <p>
     * The following validations must be satified:
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
     * @throws InvalidDataException
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
            throw new InvalidDataException("Creditor Purchase transaction date is required", e);
        }

        // Verify that the transacton date value is valid
        try {
            Verifier.verifyTrue(xact.getXactDate().getTime() <= today.getTime());
        } catch (VerifyException e) {
            throw new InvalidDataException("Creditor Purchase transaction date cannot be in the future", e);
        }

        // Verify that transaction tender is either a credit card or company
        // credit.
        try {
            Verifier.verifyFalse(xact.getXactTenderId() != XactConst.TENDER_COMPANY_CREDIT
                    && xact.getXactTenderId() != XactConst.TENDER_CREDITCARD);
        } catch (VerifyException e) {
            throw new InvalidDataException(
                    "Creditor Purchase Tender Type must be either Bank Credit Card or Finance Company Credit", e);
        }

        // Transaction reason is required.
        try {
            Verifier.verifyNotEmpty(xact.getXactReason());
        } catch (VerifyException e) {
            throw new InvalidDataException(
                    "Creditor purchase transaction reason/source is required...this is usually the name of the merchant or service provider",
                    e);
        }
        return;
    }
}
