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

import com.api.persistence.DaoClient;

/**
 * An implementation of {@link CreditorApi} which provides functionality that
 * creates, updates, deletes, and queries creditor information from the creditor
 * data source.
 * 
 * @author Roy Terrell
 * 
 */
class CreditorApiImpl extends AbstractSubsidiaryApiImpl<CreditorDto, CreditorXactHistoryDto> implements CreditorApi {
    private static final Logger logger = Logger
            .getLogger(CreditorApiImpl.class);

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
    protected List<CreditorDto> getSubsidiaryInfo(CreditorDto criteria) throws SubsidiaryException {
        // Fetch common contact data
        Map<Integer, SubsidiaryContactInfoDto> contactResults = this.getContactInfo(criteria);
        // Fetch creditor specific data
        List<CreditorDto> creditorResults = this.get(criteria);
        // Merge results
        List<CreditorDto> results = this.mergeContactInfo(creditorResults, contactResults);
        return results;
    }

    /**
     * Combines the a list of subsidiary data with a list of common contact data.
     * 
     * @param subsidiary
     * @param contact
     * @return a List<CreditorDto> sorted by contact name
     */
    @Override
    protected List<CreditorDto> mergeContactInfo(List<CreditorDto> subsidiaries,
            Map<Integer, SubsidiaryContactInfoDto> contacts) {
        if (subsidiaries == null) {
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
            else {
                // Continue to build creditor DTO when contact data is not
                // available
                contact = Rmt2SubsidiaryDtoFactory
                        .createSubsidiaryInstance(null);
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
    public double getBalance(int creditorId) throws SubsidiaryException {
        try {
            double results = dao.calculateBalance(creditorId);
            return Math.abs(results);
        } catch (Exception e) {
            this.msg = "Unable to retrieve balance for creditor : "
                    + creditorId;
            logger.error(this.msg, e);
            throw new CreditorApiException(e);
        }
    }


    @Override
    public List<CreditorDto> get(CreditorDto criteria) throws CreditorApiException {
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
    public CreditorDto getByUid(int uid) throws CreditorApiException {
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(
                null, null);
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
    public CreditorDto getByBusinessId(int businessId)
            throws CreditorApiException {
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(
                null, null);
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
    public CreditorDto getByCreditorId(int creditorId)
            throws CreditorApiException {
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(
                null, null);
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
    public List<CreditorDto> getByCreditorType(int creditorTypeId)
            throws CreditorApiException {
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
    public List<CreditorDto> getByAcctNo(String acctNo)
            throws CreditorApiException {
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
        List<CreditorTypeDto> results = this.getCreditorType();
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
    public CreditorTypeDto getCreditorType(int creditorTypeId) throws CreditorApiException {
        CreditorTypeDto criteria = Rmt2SubsidiaryDtoFactory
                .createCreditorInstance(null);
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
     * @param subsidiaryId
     *            the unique id of the creditor account
     * @return a List of {@link CreditorXactHistoryDto} objects representing the
     *         transaction history of the creditor.
     * @throws SubsidiaryException
     */
    @Override
    public List<CreditorXactHistoryDto> getTransactionHistory(int subsidiaryId)
            throws SubsidiaryException {
        List<CreditorXactHistoryDto> results;
        try {
            results = dao.fetchTransactionHistory(subsidiaryId);
            dao.commitTrans();
            return results;
        } catch (Exception e) {
            dao.rollbackTrans();
            this.msg = "Error persiting creditor profile changes";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CreditorApi#update(org.dto.CreditorDto)
     */
    @Override
    public int update(CreditorDto creditor) throws CreditorApiException {
        if (creditor == null) {
            this.msg = "Creditor API update error: creditor object is null";
            logger.error(this.msg);
            throw new CreditorApiException(this.msg);
        }
        if (creditor.getCreditorId() <= 0) {
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

    private void prepareNewCreditor(CreditorDto creditor)
            throws CreditorApiException {
        // Build and assign the account number for the new creditor
        String acctNo = this.buildAccountNo(creditor.getContactId(),
                SubsidiaryType.CREDITOR);
        creditor.setAccountNo(acctNo);

        // Assign an account id to the new customer
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi();
        AccountDto acctDto;
        try {
            acctDto = api
                    .getAccountByExactName(AccountingConst.ACCT_NAME_ACCTPAY);
        } catch (Exception e) {
            this.msg = "Error retrieving creditor GL Account info to verify correct account type before creating a new customer";
            logger.error(this.msg);
            throw new CreditorApiException(this.msg);
        }
        if (acctDto == null) {
            this.msg = "General ledger account could not be found for the creditor account";
            logger.error(this.msg);
            throw new CreditorApiException(this.msg);
        }
        // Be sure that we chose the correcte GL Account
        if (acctDto.getAcctTypeId() != AccountingConst.ACCT_TYPE_LIABILITY) {
            this.msg = "An invalid account type was selected for the creditor account";
            logger.error(this.msg);
            throw new CreditorApiException(this.msg);
        }
        // Assign the GL Account id.
        creditor.setAcctId(acctDto.getAcctId());
        return;
    }

    private void prepareExistingCreditor(CreditorDto deltaCred)
            throws CreditorApiException {
        CreditorDto oldCred = this.getByCreditorId(deltaCred.getCreditorId());
        // Set modifyable fields
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
            throw new CreditorApiException(
                    "Creditor API delete error.  Creditor object cannot be null");
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
     * @throws CreditorApiException
     *             <ul>
     *             <li><i>cred</i> is null.</li> <li>contact id property is less
     *             than or equal to zero.</li> <li>account id property is less
     *             than or equal to zero.</li> <li>creditor type id property is
     *             less than or equal to zero.</li> <li>account number is null.
     *             </li>
     *             </ul>
     */
    @Override
    public void validate(CreditorDto cred) throws CreditorApiException {
        if (cred == null) {
            this.msg = "Creditor DTO object cannot be null";
            throw new CreditorApiException(this.msg);
        }
        if (cred.getContactId() <= 0) {
            this.msg = "Creditor DTO object must be assinged a business id";
            throw new CreditorApiException(this.msg);
        }
        if (cred.getAcctId() <= 0) {
            this.msg = "Creditor DTO object must be assinged an account id";
            throw new CreditorApiException(this.msg);
        }
        if (cred.getCreditorTypeId() <= 0) {
            this.msg = "Creditor DTO object must be assinged a creditor type id";
            throw new CreditorApiException(this.msg);
        }
        if (cred.getAccountNo() == null) {
            this.msg = "Creditor DTO object must be assinged an account number";
            throw new CreditorApiException(this.msg);
        }
        return;
    }
}
