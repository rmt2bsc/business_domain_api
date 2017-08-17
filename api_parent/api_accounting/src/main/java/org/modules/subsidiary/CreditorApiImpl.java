package org.modules.subsidiary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.AccountingConst;
import org.AccountingConst.SubsidiaryType;
import org.apache.log4j.Logger;
import org.dao.subsidiary.CreditorDao;
import org.dao.subsidiary.SubsidiaryComparator;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dto.AccountDto;
import org.dto.CreditorDto;
import org.dto.CreditorTypeDto;
import org.dto.CreditorXactHistoryDto;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.modules.generalledger.GeneralLedgerApiFactory;
import org.modules.generalledger.GlAccountApi;

import com.InvalidDataException;
import com.api.persistence.DaoClient;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * An implementation of {@link CreditorApi} which provides functionality that
 * creates, updates, deletes, and queries creditor information from the creditor
 * data source.
 * 
 * @author Roy Terrell
 * 
 */
class CreditorApiImpl extends AbstractSubsidiaryApiImpl<CreditorDto> implements CreditorApi {
    private static final Logger logger = Logger.getLogger(CreditorApiImpl.class);

    private SubsidiaryDaoFactory daoFact;
    private CreditorDao dao;

    /**
     * Creates a CreditorApiImpl object.
     */
    public CreditorApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmCreditorDao();
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates a CreditorApiImpl object.
     * 
     * @param appName
     */
    public CreditorApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmCreditorDao(appName);
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an CreditorApiImpl initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param connection
     */
    protected CreditorApiImpl(DaoClient connection) {
        super(connection);
        this.dao = this.daoFact.createRmt2OrmCreditorDao(this.getSharedDao());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.RMT2Base#init()
     */
    @Override
    public void init() {
        super.init();
        this.daoFact = new SubsidiaryDaoFactory();
    }

    @Override
    public CreditorDto get(Integer creditorId) throws CreditorApiException {
        try {
            Verifier.verifyNotNull(creditorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id is required", e);
        }
        try {
            Verifier.verifyPositive(creditorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id must be greater than zero", e);
        }
        try {
            return dao.fetch(creditorId);
        } catch (Exception e) {
            this.msg = "Error retrieving single creditor by creditor id, " + creditorId;
            logger.error(this.msg, e);
            throw new CreditorApiException(e);
        }
    }
    
    /**
     * Retireve a list of creditor subsidiary accounts combined with common contact data. 
     * <p>
     * The results of this method will typically be a product of merging subsidiary 
     * accounts with their address book data based on related <i>business id</i>.
     * 
     * @param criteria an instance of {@link CreditorDto}
     * @return List of CreditorDto or null if <i>criteria</i> contains no 
     *         selection criteria values
     * @throws SubsidiaryException
     *           <i>criteria</i> is null or general data access error.
     */
    @Override
    protected List<CreditorDto> getSubsidiaryInfo(CreditorDto criteria) throws SubsidiaryException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Selection criteria is required when querying combined creditor data", e);
        }
        
        boolean useCreditorParms = false;
        boolean useContactParms = false;
        useContactParms = (criteria.getTaxId() != null || 
                           criteria.getContactName() != null || 
                           criteria.getPhoneCompany() != null);

        useCreditorParms = (criteria.getAccountNo() != null || 
                            criteria.getCreditorId() > 0 || 
                            criteria.getContactId() > 0 || 
                            criteria.getCreditorTypeId() > 0);
        
        Map<Integer, SubsidiaryContactInfoDto> contactResults = null;
        List<CreditorDto> creditorResults = null;
        List<Integer> businessIdList = null;
        
        // Determine the query sequence for obtaining combined creditor/common contact data.
        // local-to-remote or remote-to-local.
        if (useContactParms && !useCreditorParms) {
            // First, fetch common contact data and then creditor specifc data.
            contactResults = this.getContactInfo(criteria);
            // Get list of business id's to use for fetching creditor records.
            if (contactResults != null) {
                businessIdList = new ArrayList<Integer>(contactResults.keySet());
                CreditorDto criteria2 = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
                criteria2.setContactIdList(businessIdList);
                creditorResults = this.get(criteria2);    
            }
        }
        else if (useCreditorParms) {
            // First fetch creditor specific data and then common contact data
            creditorResults = this.get(criteria);
            if (creditorResults != null) {
                // Get list of business id's to use for fetching common contact records.
                businessIdList = new ArrayList<Integer>();
                for (CreditorDto item : creditorResults) {
                    businessIdList.add(item.getContactId());
                }
                // Use list of business id's along with contact criteria to fetch common contact records.
                // If there is no common contact criteria, then just use the list of buisness id's as criteeria.
                CreditorDto criteria2 = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
                criteria2.setContactIdList(businessIdList);
                contactResults = this.getContactInfo(criteria2);
            }
        }
        List<CreditorDto> results = this.mergeContactInfo(creditorResults, contactResults);
        return results;
    }

    /**
     * Combines the a list of creditor subsidiary data with a list of common contact data.
     * 
     * @param subsidiary
     * @param contact
     * @return a List<CreditorDto> sorted by contact name or null when 
     *          either <i>subsidiaries</i> or <i>contacts</i> equal null
     *          or if there is nothing to merge.
     */
    @Override
    protected List<CreditorDto> mergeContactInfo(List<CreditorDto> subsidiaries,
            Map<Integer, SubsidiaryContactInfoDto> contacts) {
        try {
            Verifier.verifyNotNull(subsidiaries);
            Verifier.verifyNotNull(contacts);
        }
        catch (VerifyException e) {
            return null;
        }
        List<CreditorDto> mergedCreditors = new ArrayList<CreditorDto>();
        for (CreditorDto creditor : subsidiaries) {
            SubsidiaryContactInfoDto contact = null;
            if (contacts != null) {
                contact = contacts.get(creditor.getContactId());
                if (contact == null) {
                    continue;
                }
            }

            creditor.setContactName(contact.getContactName());
            creditor.setContactPhone(contact.getContactPhone());
            creditor.setContactFirstname(contact.getContactFirstname());
            creditor.setContactLastname(contact.getContactLastname());
            creditor.setContactExt(contact.getContactExt());
            creditor.setTaxId(contact.getTaxId());
            creditor.setAddrId(contact.getAddrId());
            creditor.setAddr1(contact.getAddr1());
            creditor.setAddr2(contact.getAddr2());
            creditor.setAddr3(contact.getAddr3());
            creditor.setAddr4(contact.getAddr4());
            creditor.setCity(contact.getCity());
            creditor.setState(contact.getState());
            creditor.setZip(contact.getZip());
            creditor.setZipext(contact.getZipext());
            creditor.setShortName(contact.getShortName());
            mergedCreditors.add(creditor);
        }

        // return null if no creditors are found.
        if (mergedCreditors.size() == 0) {
            return null;
        }

        // Sort the list by name
        SubsidiaryComparator comp = new SubsidiaryComparator();
        Collections.sort(mergedCreditors, comp);
        comp = null;
        return mergedCreditors;
    }
    
    /**
     * Obtain the creditor's account balance.
     * 
     * @param customerId
     *            The creditor's unique id.
     * @return double as the creditor's balance.
     * @throws CreditorApiException
     *             General database errors.
     */
    @Override
    public double getBalance(Integer creditorId) throws SubsidiaryException {
        try {
            Verifier.verifyNotNull(creditorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id is required", e);
        }
        try {
            Verifier.verifyPositive(creditorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id must be greater than zero", e);
        }
        
        try {
            double results = dao.calculateBalance(creditorId);
            return Math.abs(results);
        } catch (Exception e) {
            this.msg = "Unable to retrieve balance for creditor : " + creditorId;
            logger.error(this.msg, e);
            throw new CreditorApiException(e);
        }
    }


    @Override
    public List<CreditorDto> get(CreditorDto criteria) throws CreditorApiException {
        try {
            Verifier.verifyNotNull(criteria);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor selection criteria is required", e);
        }
        try {
            return dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Error retrieving creditor data using multi property selection criteria";
            logger.error(this.msg, e);
            throw new CreditorApiException(e);
        }
    }

    
    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CreditorApi#getByUid(int)
     */
    @Override
    public CreditorDto getByUid(Integer uid) throws CreditorApiException {
        try {
            Verifier.verifyNotNull(uid);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Unique identifier is required", e);
        }
        try {
            Verifier.verifyPositive(uid);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Unique identifier  must be greater than zero", e);
        }
        
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        criteria.setCreditorId(uid);
        List<CreditorDto> results;
        try {
            results = this.getSubsidiaryInfo(criteria);
        } catch (SubsidiaryException e) {
            throw new CreditorApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Creditor record by UID, ");
            msgBuf.append(uid);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many creditor entities returned for UID, ");
            msgBuf.append(uid);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new CreditorApiException(msgBuf.toString());
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CreditorApi#getByBusinessId(int)
     */
    @Override
    public CreditorDto getByBusinessId(Integer businessId) throws CreditorApiException {
        try {
            Verifier.verifyNotNull(businessId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Business Id is required", e);
        }
        try {
            Verifier.verifyPositive(businessId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Business Id must be greater than zero", e);
        }
        
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        criteria.setContactId(businessId);
        List<CreditorDto> results;
        try {
            results = this.getSubsidiaryInfo(criteria);
        } catch (SubsidiaryException e) {
            throw new CreditorApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Creditor record by business id, ");
            msgBuf.append(businessId);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many creditor entities returned for business id, ");
            msgBuf.append(businessId);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new CreditorApiException(msgBuf.toString());
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CreditorApi#getByCreditorId(int)
     */
    @Override
    public CreditorDto getByCreditorId(Integer creditorId) throws CreditorApiException {
        try {
            Verifier.verifyNotNull(creditorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id is required", e);
        }
        try {
            Verifier.verifyPositive(creditorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id must be greater than zero", e);
        }
        
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        criteria.setCreditorId(creditorId);
        List<CreditorDto> results;
        try {
            results = this.getSubsidiaryInfo(criteria);
        } catch (SubsidiaryException e) {
            throw new CreditorApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Creditor record by creditor id, ");
            msgBuf.append(creditorId);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many creditor entities returned for creditor id, ");
            msgBuf.append(creditorId);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new CreditorApiException(msgBuf.toString());
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CreditorApi#getByCreditorType(int)
     */
    @Override
    public List<CreditorDto> getByCreditorType(Integer creditorTypeId)
            throws CreditorApiException {
        try {
            Verifier.verifyNotNull(creditorTypeId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Type Id is required", e);
        }
        try {
            Verifier.verifyPositive(creditorTypeId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Type Id must be greater than zero", e);
        }
        
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(
                null, null);
        criteria.setCreditorTypeId(creditorTypeId);
        List<CreditorDto> results;
        try {
            results = this.getSubsidiaryInfo(criteria);
        } catch (SubsidiaryException e) {
            throw new CreditorApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Creditor record(s) by creditor type id, ");
            msgBuf.append(creditorTypeId);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Creditor object(s) were retrieved by creditor type id, ");
        msgBuf.append(creditorTypeId);
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CreditorApi#getByAcctNo(java.lang.String)
     */
    @Override
    public List<CreditorDto> getByAcctNo(String acctNo) throws CreditorApiException {
        try {
            Verifier.verifyNotNull(acctNo);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Account number is required", e);
        }
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(
                null, null);
        criteria.setAccountNo(acctNo);
        List<CreditorDto> results;
        try {
            results = this.getSubsidiaryInfo(criteria);
        } catch (SubsidiaryException e) {
            throw new CreditorApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Creditor record(s) by account number, ");
            msgBuf.append(acctNo);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Creditor object(s) were retrieved by account number, ");
        msgBuf.append(acctNo);
        logger.info(msgBuf);
        return results;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CreditorApi#getCreditorType()
     */
    @Override
    public List<CreditorTypeDto> getCreditorType() throws CreditorApiException {
        CreditorTypeDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorTypeInstance(null);
        List<CreditorTypeDto> results = null;
        try {
            results = dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Error retrieving all creditor type data";
            logger.error(this.msg, e);
            throw new CreditorApiException(e);
        }
        
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Creditor type record(s) were not found");
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Creditor type object(s) were retrieved");
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CreditorApi#getCreditorType(int)
     */
    @Override
    public CreditorTypeDto getCreditorType(Integer creditorTypeId) throws CreditorApiException {
        try {
            Verifier.verifyNotNull(creditorTypeId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Type Id is required", e);
        }
        try {
            Verifier.verifyPositive(creditorTypeId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Type Id must be greater than zero", e);
        }
        
        CreditorTypeDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorTypeInstance(null);
        criteria.setEntityId(creditorTypeId);
        List<CreditorTypeDto> results = null;
        try {
            results = dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Error retrieving creditor type data using multi property selection criteria";
            logger.error(this.msg, e);
            throw new CreditorApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Creditor type record by creditor type id, ");
            msgBuf.append(creditorTypeId);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many creditor type entities returned for creditor type id, ");
            msgBuf.append(creditorTypeId);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new CreditorApiException(msgBuf.toString());
        }
        return results.get(0);
    }

    /**
     * Get transacton history for a particular creditor account.
     * 
     * @param creditorId
     *            the unique id of the creditor account
     * @return a List of {@link CreditorXactHistoryDto} objects representing the
     *         transaction history of the creditor.
     * @throws CreditorApiException
     */
    @Override
    public List<CreditorXactHistoryDto> getTransactionHistory(Integer creditorId)
            throws CreditorApiException {
        try {
            Verifier.verifyNotNull(creditorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id is required", e);
        }
        try {
            Verifier.verifyPositive(creditorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id must be greater than zero", e);
        }
        
        List<CreditorXactHistoryDto> results;
        try {
            results = dao.fetchTransactionHistory(creditorId);
            dao.commitTrans();
            return results;
        } catch (Exception e) {
            dao.rollbackTrans();
            this.msg = "Error persiting creditor profile changes";
            logger.error(this.msg, e);
            throw new CreditorApiException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CreditorApi#update(org.dto.CreditorDto)
     */
    @Override
    public int update(CreditorDto creditor) throws CreditorApiException {
        try {
            Verifier.verifyNotNull(creditor);
        } catch (VerifyException e) {
            this.msg = "Creditor API update error: creditor object is required.  Be sure that is not null.";
            throw new CreditorApiException(this.msg);
        }

        if (creditor.getCreditorId() == 0) {
            this.prepareNewCreditor(creditor);
        }
        else {
            this.prepareExistingCreditor(creditor);
        }

        this.validate(creditor);

        // Perform the actual update
        dao.setDaoUser(this.getApiUser());
        try {
            int rc = dao.maintain(creditor);
            return rc;
        } catch (Exception e) {
            this.msg = "Error persiting creditor profile changes";
            logger.error(this.msg, e);
            throw new CreditorApiException(e);
        }

        // TODO: In the future, add logic to update address book contact profile
        // with data changes assoicated with this creditor
    }

    private void prepareNewCreditor(CreditorDto creditor) throws CreditorApiException {
        // Build and assign the account number for the new creditor
        String acctNo = this.buildAccountNo(creditor.getContactId(), SubsidiaryType.CREDITOR);
        creditor.setAccountNo(acctNo);

        // Assign an account id to the new customer
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi();
        AccountDto acctDto;
        try {
            acctDto = api.getAccountByExactName(AccountingConst.ACCT_NAME_ACCTPAY);
        } catch (Exception e) {
            this.msg = "Unable to retrieve GL Account information for new creditor";
            logger.error(this.msg);
            throw new NewCreditorSetupFailureException(this.msg, e);
        }
        if (acctDto == null) {
            this.msg = "Accounts Payable General ledger account could not be found for the purpose of assigning to creditor";
            logger.error(this.msg);
            throw new NewCreditorSetupFailureException(this.msg);
        }
        // Be sure that we chose the correcte GL Account
        if (acctDto.getAcctTypeId() != AccountingConst.ACCT_TYPE_LIABILITY) {
            this.msg = "The GL Account assoicated with creditor contains an incorrect account type id.  The account type expected is " + AccountingConst.ACCT_TYPE_LIABILITY;
            logger.error(this.msg);
            throw new NewCreditorSetupFailureException(this.msg);
        }
        // Assign the GL Account id.
        creditor.setAcctId(acctDto.getAcctId());
        return;
    }

    private void prepareExistingCreditor(CreditorDto deltaCred) throws CreditorApiException {
        CreditorDto oldCred = this.getByCreditorId(deltaCred.getCreditorId());
        if (oldCred == null) {
            throw new CreditorNotFoundException("Creditor was not found by creditor id: " + deltaCred.getCreditorId());
        }
        // Set fields that are typically not modified by user
        deltaCred.setCreditorId(oldCred.getCreditorId());
        deltaCred.setAccountNo(oldCred.getAccountNo());
        deltaCred.setAcctId(oldCred.getAcctId());
        deltaCred.setContactId(oldCred.getContactId());
        deltaCred.setDateCreated(oldCred.getDateCreated());
        deltaCred.setDateUpdated(oldCred.getDateUpdated());
        deltaCred.setUpdateUserId(oldCred.getUpdateUserId());
        deltaCred.setIpCreated(oldCred.getIpCreated());
        deltaCred.setIpUpdated(oldCred.getIpUpdated());
        return;
    }

    /**
     * Deletes a single creditor's profile based on value of the creditor id
     * property of <i>creditor</i>.
     * 
     * @param creditor
     *            an instance of {@link CreditorDto} where the property of
     *            interest is the creditorId.
     * @return the total number of entities effected by the transaction.
     * @throws CreditorApiException
     *             <i>creditor</i> is null
     */
    @Override
    public int delete(CreditorDto creditor) throws CreditorApiException {
        if (creditor == null) {
            throw new CreditorApiException("Creditor API delete error:  Creditor object cannot be null");
        }
        // Perform the actual delete
        try {
            int rc = dao.delete(creditor.getCreditorId());
            return rc;
        } catch (Exception e) {
            this.msg = "Error persiting creditor profile changes";
            logger.error(this.msg, e);
            throw new CreditorApiException(e);
        }
    }

    /**
     * Validate a single creditor instance.
     * <p>
     * In order to pass validations, the creditor object cannot be null, and the
     * contact id (business id), account id, creditor type id, and account
     * number must all be assigned.
     * 
     * @param cust
     *            an instance of {@link CreditorDto} representing the creditor
     *            that is to be valdiated
     * @throws {@link InvalidDataException}
     *         <ul>
     *         <li><i>cred</i> is null.</li> <li>contact id property is less
     *         than or equal to zero.</li> <li> account id property is less than
     *         or equal to zero.</li> <li> creditor type id property is less
     *         than or equal to zero.</li> <li>account number is null.</li>
     *         </ul>
     */
    protected void validate(CreditorDto cred) {
        try {
            Verifier.verifyNotNull(cred);
        } catch (VerifyException e) {
            this.msg = "Creditor object is required";
            throw new InvalidDataException(this.msg);
        }
        try {
            Verifier.verifyPositive(cred.getContactId());
        }
        catch (VerifyException e) {
            this.msg = "Creditor business id/contact id is required";
            throw new InvalidDataException(this.msg);
        }
        try {
            Verifier.verifyPositive(cred.getAcctId());
        }
        catch (VerifyException e) {
            this.msg = "Creditor account id is required";
            throw new InvalidDataException(this.msg);
        }
        try {
            Verifier.verifyPositive(cred.getCreditorTypeId());
        }
        catch (VerifyException e) {
            this.msg = "Creditor type id is required";
            throw new InvalidDataException(this.msg);
        }
        try {
            Verifier.verifyNotNull(cred.getAccountNo());
        }
        catch (VerifyException e) {
            this.msg = "Creditor account number is required";
            throw new InvalidDataException(this.msg);
        }
        return;
    }
}
