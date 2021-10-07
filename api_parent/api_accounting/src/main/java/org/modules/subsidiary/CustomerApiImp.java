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
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * An implementation of {@link CustomerApi} which provides functionality that
 * creates, updates, deletes, and queries customer information from the customer
 * data source.
 * 
 * @author Roy Terrell
 * 
 */
class CustomerApiImp extends AbstractSubsidiaryApiImpl<CustomerDto> implements CustomerApi {
    private static final Logger logger = Logger.getLogger(CustomerApiImp.class);

    private CustomerDao dao;


    /**
     * Creates a CustomerApiImp object in which the configuration is identified
     * by the name of a given application.
     * 
     * @param appName
     */
    public CustomerApiImp(String appName) {
        super();
        this.dao = SubsidiaryDaoFactory.createRmt2OrmCustomerDao(appName);
        this.setSharedDao(this.dao);
        this.dao.setDaoUser(this.apiUser);
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
        this.dao = SubsidiaryDaoFactory.createRmt2OrmCustomerDao(this.getSharedDao());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.RMT2Base#init()
     */
    @Override
    public void init() {
        super.init();
    }

    @Override
    public CustomerDto get(Integer customerId) throws CustomerApiException {
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
            return dao.fetch(customerId);
        } catch (Exception e) {
            this.msg = "DAO error retrieving single customer";
            throw new CustomerApiException(this.msg, e);
        }
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
                criteria.getContactId() > 0 ||
                criteria.getPhoneCompany() != null);

        useCustomerParms = (criteria.getAccountNo() != null ||
                criteria.getDescription() != null ||
                criteria.getCustomerId() > 0);
        
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
                CustomerDto criteria2 = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
                criteria2.setContactIdList(businessIdList);
                customerResults = this.get(criteria2);    
            }
        }
        // Fetch using customer related criteria or fetch all customers
        else if (useCustomerParms || (!useCustomerParms && !useContactParms)) {
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
                CustomerDto criteria2 = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
                criteria2.setContactIdList(businessIdList);
                contactResults = this.getContactInfo(criteria2);
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
    protected List<CustomerDto> mergeContactInfo(List<CustomerDto> subsidiaries, Map<Integer, SubsidiaryContactInfoDto> contacts) {
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
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#getByUid(int)
     */
    @Override
    public CustomerDto getByUid(Integer uid) throws CustomerApiException {
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
    public CustomerDto getByBusinessId(Integer businessId) throws CustomerApiException {
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
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
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
    public CustomerDto getByCustomerId(Integer customerId) throws CustomerApiException {
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
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
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
    public List<CustomerDto> getByAcctNo(String acctNo) throws CustomerApiException {
        try {
            Verifier.verifyNotNull(acctNo);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Customer Account number is required", e);
        }
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
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
    public List<CustomerDto> get(CustomerDto criteria) throws CustomerApiException {
        try {
            Verifier.verifyNotNull(criteria);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Customer selection criteria is required", e);
        }
        try {
            return dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Error retrieving customer data using multi property selection criteria";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#getExt(org.dto.CustomerDto)
     */
    @Override
    public List<CustomerDto> getExt(CustomerDto criteria) throws CustomerApiException {
        try {
            Verifier.verifyNotNull(criteria);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Customer selection criteria is required", e);
        }
        try {
            return this.getSubsidiaryInfo(criteria);
        } catch (Exception e) {
            this.msg = "Error retrieving customer data using multi property selection criteria";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.subsidiary.CustomerApi#update(org.dto.CustomerDto)
     */
    @Override
    public int update(CustomerDto customer) throws CustomerApiException {
        try {
            Verifier.verifyNotNull(customer);
        } catch (VerifyException e) {
            this.msg = "Customer API update error: customer object is required.  Be sure that is not null.";
            throw new CustomerApiException(this.msg);
        }
        if (customer.getCustomerId() == 0) {
            this.prepareNewCustomer(customer);
        }
        else {
            this.prepareExistingCustomer(customer);
        }

        this.validate(customer);

        // Perform the actual update
        dao.setDaoUser(this.getApiUser());
        try {
            int rc = dao.maintain(customer);
            return rc;
        } catch (Exception e) {
            this.msg = "Error persiting customer profile changes";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
        // TODO: In the future, add logic to update address book contact profile
        // with data changes assoicated with this customer
    }

    private void prepareNewCustomer(CustomerDto customer) throws CustomerApiException {
        // Build and assign the account number for the new customer
        String acctNo = this.buildAccountNo(customer.getContactId(), SubsidiaryType.CUSTOMER);
        customer.setAccountNo(acctNo);

        // Assign an account id to the new customer
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi();
        AccountDto acctDto;
        try {
            acctDto = api.getAccountByExactName(AccountingConst.ACCT_NAME_ACCTRCV);
        } catch (Exception e) {
            this.msg = "Unable to retrieve GL Account information for new customer";
            logger.error(this.msg);
            throw new NewCustomerSetupFailureException(this.msg);
        }
        if (acctDto == null) {
            this.msg = "Accounts Payable General ledger account could not be found for the purpose of assigning to customer";
            logger.error(this.msg);
            throw new NewCustomerSetupFailureException(this.msg);
        }
        // Be sure that we chose the correcte GL Account
        if (acctDto.getAcctTypeId() != AccountingConst.ACCT_TYPE_ASSET) {
            this.msg = "An invalid general ledger account type was selected for the customer account";
            logger.error(this.msg);
            throw new NewCustomerSetupFailureException(this.msg);
        }
        // Assign the GL Account id.
        customer.setAcctId(acctDto.getAcctId());
        return;
    }

    private void prepareExistingCustomer(CustomerDto deltaCust) throws CustomerApiException {
        CustomerDto oldCust = this.getByCustomerId(deltaCust.getCustomerId());
        if (oldCust == null) {
            throw new CustomerNotFoundException("Customer was not found by creditor id: " + deltaCust.getCustomerId());
        }
        // Set modifyable fields. Ignore fields customer id, account no, acct
        // id, gate created, and ip created in which these fields should never
        // be modified post initial creation.
        deltaCust.setCustomerId(oldCust.getCustomerId());
        deltaCust.setAccountNo(oldCust.getAccountNo());
        deltaCust.setAcctId(oldCust.getAcctId());
        deltaCust.setDateCreated(oldCust.getDateCreated());
        deltaCust.setIpCreated(oldCust.getIpCreated());
        return;
    }

    /**
     * Validate a single customer instance.
     * <p>
     * In order to pass validations, the customer object cannot be null, and the
     * contact id (business id), account id, and account number must all be
     * assigned.
     * 
     * @param cust
     *            an instance of {@link CustomerDto} representing the customer
     *            that is to be valdiated
     * @throws {@link InvalidDataException}
     *         <ul>
     *         <li><i>cust</i> is null.</li> <li>contact id property is less
     *         than or equal to zero.</li> <li> account id property is less than
     *         or equal to zero.</li><li>account number is null.</li>
     *         </ul>
     */
    protected void validate(CustomerDto cust) {
        try {
            Verifier.verifyNotNull(cust);
        } catch (VerifyException e) {
            this.msg = "Customer object is required.";
            throw new InvalidDataException(this.msg);
        }
        try {
            Verifier.verifyPositive(cust.getContactId());
        }
        catch (VerifyException e) {
            this.msg = "Customer business id/contact id is required";
            throw new InvalidDataException(this.msg);
        }
        try {
            Verifier.verifyPositive(cust.getAcctId());
        }
        catch (VerifyException e) {
            this.msg = "Customer account id is required";
            throw new InvalidDataException(this.msg);
        }
        try {
            Verifier.verifyNotNull(cust.getAccountNo());
        }
        catch (VerifyException e) {
            this.msg = "Customer account number is required";
            throw new InvalidDataException(this.msg);
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
        try {
            int rc = dao.delete(customer.getCustomerId());
            return rc;
        } catch (Exception e) {
            this.msg = "Error persiting customer profile changes";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
    }

    /**
     * Get transacton history for a particular customer account.
     * 
     * @param customerId
     *            the unique id of the customer account
     * @return a List of {@link CustomerXactHistoryDto} objects representing the
     *         transaction history of the customer.
     * @throws SubsidiaryException
     */
    @Override
    public List<CustomerXactHistoryDto> getTransactionHistory(Integer customerId) throws CustomerApiException {
        try {
            Verifier.verifyNotNull(customerId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id is required", e);
        }
        try {
            Verifier.verifyPositive(customerId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Creditor Id must be greater than zero", e);
        }
        List<CustomerXactHistoryDto> results;
        try {
            results = dao.fetchTransactionHistory(customerId);
            return results;
        } catch (Exception e) {
            this.msg = "Error persiting customer profile changes";
            logger.error(this.msg, e);
            throw new CustomerApiException(e);
        }
    }
}
