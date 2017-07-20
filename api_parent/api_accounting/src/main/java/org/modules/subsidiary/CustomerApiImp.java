package org.modules.subsidiary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.AccountingConst;
import org.AccountingConst.SubsidiaryType;
import org.apache.log4j.Logger;
import org.dao.subsidiary.CustomerDao;
import org.dao.subsidiary.SubsidiaryComparator;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dto.AccountDto;
import org.dto.CustomerDto;
import org.dto.CustomerXactHistoryDto;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.modules.generalledger.GeneralLedgerApiFactory;
import org.modules.generalledger.GlAccountApi;

import com.InvalidDataException;
import com.api.persistence.DaoClient;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * An implementation of {@link CustomerApi} which provides functionality that
 * creates, updates, deletes, and queries customer information from the customer
 * data source.
 * 
 * @author Roy Terrell
 * 
 */
class CustomerApiImp extends AbstractSubsidiaryApiImpl<CustomerDto, CustomerXactHistoryDto> implements CustomerApi {
    private static final Logger logger = Logger.getLogger(CustomerApiImp.class);

    private SubsidiaryDaoFactory daoFact;
    private CustomerDao dao;

    /**
     * Creates a CustomerApiImp object.
     */
    public CustomerApiImp() {
        super();
        this.dao = this.daoFact.createRmt2OrmCustomerDao();
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates a CustomerApiImp object.
     * 
     * @param appName
     */
    public CustomerApiImp(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmCustomerDao(appName);
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an CustomerApiImp initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param connection
     */
    protected CustomerApiImp(DaoClient connection) {
        super(connection);
        this.dao = this.daoFact.createRmt2OrmCustomerDao(this.getSharedDao());
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

    /**
     * Retireve a list of customer subsidiary accounts combined with common contact data. 
     * <p>
     * The results of this method will typically be a product of merging subsidiary 
     * accounts with their address book data based on related <i>business id</i>.
     * 
     * @param criteria an instance of {@link CustomerDto}
     * @return List of CustomerDto or null if <i>criteria</i> contains no 
     *         selection criteria values
     * @throws SubsidiaryException
     *           <i>criteria</i> is null or general data access error.
     */
    @Override
    protected List<CustomerDto> getSubsidiaryInfo(CustomerDto criteria) throws SubsidiaryException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Selection criteria is required when querying combined customer data", e);
        }
        
        boolean useCustomerParms = false;
        boolean useContactParms = false;
        useContactParms = (criteria.getTaxId() != null || 
                           criteria.getContactName() != null || 
                           criteria.getPhoneCompany() != null);

        useCustomerParms = (criteria.getAccountNo() != null || 
                            criteria.getCustomerId() > 0 || 
                            criteria.getContactId() > 0);
        
        Map<Integer, SubsidiaryContactInfoDto> contactResults = null;
        List<CustomerDto> customerResults = null;
        List<Integer> businessIdList = null;
        
        // Determine the query sequence for obtaining combined customer/common contact data.
        // local-to-remote or remote-to-local.
        if (useContactParms && !useCustomerParms) {
            // First, fetch common contact data and then customer specifc data.
            contactResults = this.getContactInfo(criteria);
            // Get list of business id's to use for fetching customer records.
            if (contactResults != null) {
                businessIdList = new ArrayList<Integer>(contactResults.keySet());
                criteria.setContactIdList(businessIdList);
                customerResults = this.get(criteria);    
            }
        }
        else if (useCustomerParms) {
            // First fetch customer specific data and then common contact data
            customerResults = this.get(criteria);
            if (customerResults != null) {
                // Get list of business id's to use for fetching common contact records.
                businessIdList = new ArrayList<Integer>();
                for (CustomerDto item : customerResults) {
                    businessIdList.add(item.getContactId());
                }
                // Use list of business id's along with contact criteria to fetch common contact records.
                // If there is no common contact criteria, then just use the list of buisness id's as criteeria.
                criteria.setContactIdList(businessIdList);
                contactResults = this.getContactInfo(criteria);
            }
        }
        List<CustomerDto> results = this.mergeContactInfo(customerResults, contactResults);
        return results;
    }

    /**
     * Combines the a list of customer subsidiary data with a list of common contact data.
     * 
     * @param subsidiary
     * @param contact
     * @return a List<CustomerDto> sorted by contact name or null when 
     *          either <i>subsidiaries</i> or <i>contacts</i> equal null
     *          or if there is nothing to merge.
     */
    @Override
    protected List<CustomerDto> mergeContactInfo(List<CustomerDto> subsidiaries,
            Map<Integer, SubsidiaryContactInfoDto> contacts) {
        try {
            Verifier.verifyNotNull(subsidiaries);
            Verifier.verifyNotNull(contacts);
        }
        catch (VerifyException e) {
            return null;
        }
        List<CustomerDto> mergedCustomers = new ArrayList<CustomerDto>();
        for (CustomerDto customer : subsidiaries) {
            SubsidiaryContactInfoDto contact = null;
            if (contacts != null) {
                contact = contacts.get(customer.getContactId());
                if (contact == null) {
                    continue;
                }
            }

            customer.setContactName(contact.getContactName());
            customer.setContactPhone(contact.getContactPhone());
            customer.setContactFirstname(contact.getContactFirstname());
            customer.setContactLastname(contact.getContactLastname());
            customer.setContactExt(contact.getContactExt());
            customer.setTaxId(contact.getTaxId());
            customer.setAddrId(contact.getAddrId());
            customer.setAddr1(contact.getAddr1());
            customer.setAddr2(contact.getAddr2());
            customer.setAddr3(contact.getAddr3());
            customer.setAddr4(contact.getAddr4());
            customer.setCity(contact.getCity());
            customer.setState(contact.getState());
            customer.setZip(contact.getZip());
            customer.setZipext(contact.getZipext());
            customer.setShortName(contact.getShortName());
            mergedCustomers.add(customer);
        }

        // return null if no customers are found.
        if (mergedCustomers.size() == 0) {
            return null;
        }

        // Sort the list by name
        SubsidiaryComparator comp = new SubsidiaryComparator();
        Collections.sort(mergedCustomers, comp);
        comp = null;
        return mergedCustomers;
    }
    
    /**
     * Obtain the customer's account balance.
     * 
     * @param customerId
     *            The customer's unique id.
     * @return double as the customer's balance.
     * @throws CustomerApiException
     *             General database errors.
     */
    @Override
    public double getBalance(Integer customerId) throws SubsidiaryException {
        // SubsidiaryDaoFactory f = new SubsidiaryDaoFactory();
        // CustomerDao dao = f.createRmt2OrmCustomerDao();
        try {
            Verifier.verifyNotNull(customerId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Customer Id is required", e);
        }
        try {
            Verifier.verifyPositive(customerId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Customer Id must be greater than zero", e);
        }
        
        try {
            double results = dao.calculateBalance(customerId);
            return Math.abs(results);
        } catch (Exception e) {
            this.msg = "Unable to retrieve balance for customer : "
                    + customerId;
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
        // finally {
        // f = null;
        // dao.close();
        // dao = null;
        // }
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#getByUid(int)
     */
    @Override
    public CustomerDto getByUid(Integer uid) throws CustomerApiException {
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(
                null, null);
        criteria.setCustomerId(uid);
        List<CustomerDto> results;
        try {
            results = this.getSubsidiaryInfo(criteria);
        } catch (SubsidiaryException e) {
            throw new CustomerApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Customer record by UID, ");
            msgBuf.append(uid);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many customer entities returned for UID, ");
            msgBuf.append(uid);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new CustomerApiException(msgBuf.toString());
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#getByBusinessId(int)
     */
    @Override
    public CustomerDto getByBusinessId(Integer businessId)
            throws CustomerApiException {
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(
                null, null);
        criteria.setContactId(businessId);
        List<CustomerDto> results;
        try {
            results = this.getSubsidiaryInfo(criteria);
        } catch (SubsidiaryException e) {
            throw new CustomerApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Customer record using business id, ");
            msgBuf.append(businessId);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many customer entities returned for business id, ");
            msgBuf.append(businessId);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new CustomerApiException(msgBuf.toString());
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#getByCustomerId(int)
     */
    @Override
    public CustomerDto getByCustomerId(Integer customerId)
            throws CustomerApiException {
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(
                null, null);
        criteria.setCustomerId(customerId);
        List<CustomerDto> results;
        try {
            results = this.getSubsidiaryInfo(criteria);
        } catch (SubsidiaryException e) {
            throw new CustomerApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Customer record using customer id, ");
            msgBuf.append(customerId);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many customer entities returned for customer id, ");
            msgBuf.append(customerId);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new CustomerApiException(msgBuf.toString());
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#getByAcctNo(java.lang.String)
     */
    @Override
    public List<CustomerDto> getByAcctNo(String acctNo)
            throws CustomerApiException {
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(
                null, null);
        criteria.setAccountNo(acctNo);
        List<CustomerDto> results;
        try {
            results = this.getSubsidiaryInfo(criteria);
        } catch (SubsidiaryException e) {
            throw new CustomerApiException(e);
        }
        StringBuffer msgBuf = new StringBuffer();
        if (results == null) {
            msgBuf.append("Customer record using customer account number, ");
            msgBuf.append(acctNo);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many customer entities returned for customer account number, ");
            msgBuf.append(acctNo);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new CustomerApiException(msgBuf.toString());
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#get(org.dto.CustomerDto)
     */
    @Override
    public List<CustomerDto> get(CustomerDto criteria)
            throws CustomerApiException {
        // SubsidiaryDaoFactory f = new SubsidiaryDaoFactory();
        // CustomerDao dao = f.createRmt2OrmCustomerDao();
        try {
            return dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Error retrieving customer data using multi property selection criteria";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
        // finally {
        // f = null;
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#update(org.dto.CustomerDto)
     */
    @Override
    public int update(CustomerDto customer) throws CustomerApiException {
        if (customer == null) {
            this.msg = "Error updating customer: customer object is null";
            logger.error(this.msg);
            throw new CustomerApiException(this.msg);
        }
        if (customer.getCustomerId() <= 0) {
            this.prepareNewCustomer(customer);
        }
        else {
            this.prepareExistingCustomer(customer);
        }

        this.validate(customer);

        // Perform the actual update
        // SubsidiaryDaoFactory f = new SubsidiaryDaoFactory();
        // CustomerDao dao = f.createRmt2OrmCustomerDao();
        dao.setDaoUser(this.getApiUser());
        try {
            // dao.beginTrans();
            int rc = dao.maintain(customer);
            // dao.commitTrans();
            return rc;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Error persiting customer profile changes";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
        // finally {
        // f = null;
        // dao.close();
        // dao = null;
        // }

        // TODO: In the future, add logic to update address book contact profile
        // with data changes assoicated with this customer
    }

    private void prepareNewCustomer(CustomerDto customer)
            throws CustomerApiException {
        // Build and assign the account number for the new customer
        String acctNo = this.buildAccountNo(customer.getContactId(),
                SubsidiaryType.CUSTOMER);
        customer.setAccountNo(acctNo);

        // Assign an account id to the new customer
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi();
        AccountDto acctDto;
        try {
            acctDto = api
                    .getAccountByExactName(AccountingConst.ACCT_NAME_ACCTRCV);
        } catch (Exception e) {
            this.msg = "Error retrieving customer GL Account info to verify correct account type before creating a new customer";
            logger.error(this.msg);
            throw new CustomerApiException(this.msg);
        }
        if (acctDto == null) {
            this.msg = "General ledger account could not be found for the customer account";
            logger.error(this.msg);
            throw new CustomerApiException(this.msg);
        }
        // Be sure that we chose the correcte GL Account
        if (acctDto.getAcctTypeId() != AccountingConst.ACCT_TYPE_ASSET) {
            this.msg = "An invalid account type was selected for the customer account";
            logger.error(this.msg);
            throw new CustomerApiException(this.msg);
        }
        // Assign the GL Account id.
        customer.setAcctId(acctDto.getAcctId());
        return;
    }

    private void prepareExistingCustomer(CustomerDto deltaCust)
            throws CustomerApiException {
        CustomerDto oldCust = this.getByCustomerId(deltaCust.getCustomerId());
        // Set modifyable fields
        deltaCust.setCustomerId(oldCust.getCustomerId());
        deltaCust.setAccountNo(oldCust.getAccountNo());
        deltaCust.setAcctId(oldCust.getAcctId());
        deltaCust.setContactId(oldCust.getContactId());
        deltaCust.setDateCreated(oldCust.getDateCreated());
        deltaCust.setDateUpdated(oldCust.getDateUpdated());
        deltaCust.setUpdateUserId(oldCust.getUpdateUserId());
        deltaCust.setIpCreated(oldCust.getIpCreated());
        deltaCust.setIpUpdated(oldCust.getIpUpdated());
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#validate(org.dto.CustomerDto)
     */
    @Override
    public void validate(CustomerDto cust) throws CustomerApiException {
        try {
            Verifier.verifyNotNull(cust);
        }
        catch (VerifyException e) {
            this.msg = "Customer DTO object cannot be null";
            throw new CustomerApiException(this.msg);
        }
        try {
            Verifier.verifyPositive(cust.getContactId());
        }
        catch (VerifyException e) {
            this.msg = "Customer DTO object must be assinged a business id";
            throw new CustomerApiException(this.msg);
        }
        try {
            Verifier.verifyPositive(cust.getAcctId());
        }
        catch (VerifyException e) {
            this.msg = "Customer DTO object must be assinged a account id";
            throw new CustomerApiException(this.msg);
        }
        try {
            Verifier.verifyNotNull(cust.getAccountNo());
        }
        catch (VerifyException e) {
            this.msg = "Customer DTO object must be assinged an account number";
            throw new CustomerApiException(this.msg);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#delete(org.dto.CustomerDto)
     */
    @Override
    public int delete(CustomerDto customer) throws CustomerApiException {
        // Perform the actual delete
        // SubsidiaryDaoFactory f = new SubsidiaryDaoFactory();
        // CustomerDao dao = f.createRmt2OrmCustomerDao();
        try {
            // dao.beginTrans();
            int rc = dao.delete(customer.getCustomerId());
            // dao.commitTrans();
            return rc;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Error persiting customer profile changes";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
        // finally {
        // f = null;
        // dao.close();
        // dao = null;
        // }
    }

    /**
     * Get transacton history for a particular customer account.
     * 
     * @param subsidiaryId
     *            the unique id of the customer account
     * @return a List of {@link CustomerXactHistoryDto} objects representing the
     *         transaction history of the customer.
     * @throws SubsidiaryException
     */
    @Override
    public List<CustomerXactHistoryDto> getTransactionHistory(int subsidiaryId)
            throws SubsidiaryException {
        // SubsidiaryDaoFactory f = new SubsidiaryDaoFactory();
        // CustomerDao dao = f.createRmt2OrmCustomerDao();
        List<CustomerXactHistoryDto> results;
        try {
            results = dao.fetchTransactionHistory(subsidiaryId);
            // dao.commitTrans();
            return results;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Error persiting customer profile changes";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
        // finally {
        // f = null;
        // dao.close();
        // dao = null;
        // }
    }
}
