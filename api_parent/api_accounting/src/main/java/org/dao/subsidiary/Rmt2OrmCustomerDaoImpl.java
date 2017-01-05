package org.dao.subsidiary;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dao.AccountingSqlConst;
import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.CustomerActivity;
import org.dao.mapping.orm.rmt2.VwCustomerXactHist;
import org.dto.CustomerDto;
import org.dto.CustomerXactHistoryDto;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.SubsidiaryDto;
import org.dto.SubsidiaryXactHistoryDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;

import com.api.persistence.DatabaseException;
import com.api.persistence.PersistenceClient;
import com.util.RMT2Date;
import com.util.RMT2String;
import com.util.UserTimestamp;

/**
 * An implementation of {@link CustomerDao}. It provides functionality that
 * creates, updates, deletes, and queries customer subsidiary account data.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmCustomerDaoImpl extends AbstractRmt2SubsidiaryContactDaoImpl
        implements CustomerDao {

    /**
     * Construce a Rmt2OrmCustomerDaoImpl object initialized with a connection
     * to the database
     */
    public Rmt2OrmCustomerDaoImpl() {
        super();
    }

    public Rmt2OrmCustomerDaoImpl(String appName) {
        super(appName);
        return;
    }

    public Rmt2OrmCustomerDaoImpl(PersistenceClient client) {
        super(client);
        return;
    }

    /**
     * Fetches one or more customer records from the <i>customer</i> table based
     * on the supplied selection criteria.
     * <p>
     * Also, a web service targeting the cotnacts application is used to gather
     * contact related data related to the customer(s). This implementation
     * employs two fetch sequences to retrieve customer data based on the
     * selection criteria supplied: (local database, web service) and (web
     * service, local database). The following properties of <i>criteria</i> can
     * be used to build the selection criteria for the query: <i>Customer Id,
     * Business Id, Customer Name, Account Number, Tax Id, and Company
     * Phone</i>. If the criteria contains at least one property that originates
     * from the contacts application (tax id, cusstomer name, or company phone),
     * the base data is queried from the web service first and then the customer
     * related data is fetched from the local database. Otherwise, the base data
     * is fetched from the local database first and then the contact related
     * data is obtained from the web service.
     * 
     * @param criteria
     *            an instance of {@link CustomerDto} containing data that is
     *            used to build selection criteria. The following properties can
     *            be used to build the selection criteria for the query:
     *            Customer Id, Business Id, Customer Name, Account Number, Tax
     *            Id, and Company Phone. Optionally, this parameter can be null
     *            which will fetch all customer records from the customer table.
     * @return a list of {@link CustomerDto} objects or null when the query
     *         returns an empty result set.
     * @throws CustomerDaoException
     */
    @Override
    public List<CustomerDto> fetch(CustomerDto criteria)
            throws CustomerDaoException {
        List<CustomerDto> results = null;
        boolean useCustomerParms = false;
        boolean useContactParms = false;
        if (criteria != null) {
            useContactParms = (criteria.getTaxId() != null
                    || criteria.getContactName() != null || criteria
                    .getPhoneCompany() != null);

            useCustomerParms = (criteria.getAccountNo() != null
                    || criteria.getCustomerId() > 0 || criteria.getContactId() > 0);
        }

        // Determine the query sequence for obtaining the customer data.
        // local-to-remote or remote-to-local.
        if (useContactParms && !useCustomerParms) {
            // Fetch by web service first
            results = this.fetchWebServiceFirst(criteria);
        }
        else {
            // Fetch by local DB first
            results = this.fetchLocalFirst(criteria);
        }
        return results;
    }

    /**
     * Fetches customer/contact combined data from both the local database and a
     * remote data source which the matching process is driven by the results of
     * the local fetch. The remote data source is accessed via a web service.
     * 
     * @param criteria
     *            an instance of {@link CustomerDto} containing the selection
     *            criteria that is used by both the local and remote queries.
     * @return List of {@link CustomerDto} or null when no data is found
     * @throws CustomerDaoException
     */
    private List<CustomerDto> fetchLocalFirst(CustomerDto criteria)
            throws CustomerDaoException {
        // Gather customer criteria
        Customer ormCust = null;
        if (criteria != null) {
            ormCust = new Customer();
            if (criteria.getCustomerId() > 0) {
                ormCust.addCriteria(Customer.PROP_CUSTOMERID,
                        criteria.getCustomerId());
            }
            if (criteria.getContactId() > 0) {
                ormCust.addCriteria(Customer.PROP_BUSINESSID,
                        criteria.getContactId());
            }
            if (criteria.getAccountNo() != null) {
                ormCust.addCriteria(Customer.PROP_ACCOUNTNO,
                        criteria.getAccountNo());
            }
        }

        // Retrieve customer local data
        List<Customer> localResults = this.fetch(ormCust);
        if (localResults == null || localResults.isEmpty()) {
            return null;
        }
        // Obtain list of business id to be used for the web service call.
        List<String> busIdList = new ArrayList<String>();
        for (Customer item : localResults) {
            busIdList.add(String.valueOf(item.getBusinessId()));
        }
        criteria.setContactIdList(busIdList);

        // invoke web service to obtain common contact info from an outside
        // application for each business id.
        Map<Integer, SubsidiaryContactInfoDto> remoteResults;
        try {
            remoteResults = this.fetch((SubsidiaryContactInfoDto) criteria);
        } catch (Exception e) {
            remoteResults = null;
        }
        // merge the two result sets.
        List<CustomerDto> mergedCustomers = this.mergeAndSortResults(
                localResults, remoteResults);
        return mergedCustomers;
    }

    /**
     * Fetches customer/contact combined data from both the local database and a
     * remote data source which the matching process is driven by the results of
     * the remote fetch. The remote data source is accessed via a web service.
     * 
     * @param criteria
     *            an instance of {@link CustomerDto} containing the selection
     *            criteria that is used by both the local and remote queries.
     * @return List of {@link CustomerDto} or null when no data is found
     * @throws CustomerDaoException
     */
    private List<CustomerDto> fetchWebServiceFirst(CustomerDto criteria)
            throws CustomerDaoException {
        // invoke web service to obtain common contact info from an outside
        // application for each business id.
        Map<Integer, SubsidiaryContactInfoDto> remoteResults;
        try {
            remoteResults = this.fetch((SubsidiaryContactInfoDto) criteria);
        } catch (Exception e) {
            remoteResults = null;
        }

        // Add list business id's as selection criteria to build an "IN" clause.
        Customer ormCriteria = new Customer();
        // Only build "IN" clause for business id's when the remote result set
        // is not empty. Otherwise, return null since selection criteria for
        // remote query was not met.
        if (remoteResults != null && !remoteResults.isEmpty()) {
            List<String> busIdList = new ArrayList<String>();
            Iterator<Integer> iter = remoteResults.keySet().iterator();
            while (iter.hasNext()) {
                Integer busId = iter.next();
                busIdList.add(busId.toString());
            }
            String busArray[] = new String[busIdList.size()];
            busIdList.toArray(busArray);
            ormCriteria.addInClause(Customer.PROP_BUSINESSID, busArray);
        }
        else {
            return null;
        }

        // Query the local database using the business id's retrieved remotely
        List<Customer> localResults = this.fetch(ormCriteria);

        // merge the two result sets.
        List<CustomerDto> mergedCustomers = this.mergeAndSortResults(
                localResults, remoteResults);
        return mergedCustomers;
    }

    /**
     * Fetches the customer's transaction history from the
     * <i>customer_activitiy</i> table.
     * 
     * @param customerId
     *            An integer representing the customer id.
     * @return a list of {@link CustomerXactHistoryDto} objects or null when the
     *         query returns an empty result set.
     * @throws CustomerDaoException
     */
    @Override
    public List<CustomerXactHistoryDto> fetchTransactionHistory(int customerId)
            throws CustomerDaoException {
        VwCustomerXactHist obj = new VwCustomerXactHist();
        obj.addCriteria(VwCustomerXactHist.PROP_CUSTOMERID, customerId);
        obj.addOrderBy(VwCustomerXactHist.PROP_XACTDATE,
                VwCustomerXactHist.ORDERBY_DESCENDING);
        obj.addOrderBy(VwCustomerXactHist.PROP_XACTID,
                VwCustomerXactHist.ORDERBY_DESCENDING);

        List<VwCustomerXactHist> results = null;
        try {
            results = this.client.retrieveList(obj);
        } catch (DatabaseException e) {
            throw new CreditorDaoException(e);
        }

        List<CustomerXactHistoryDto> list = new ArrayList<CustomerXactHistoryDto>();
        for (VwCustomerXactHist item : results) {
            CustomerXactHistoryDto dto = Rmt2SubsidiaryDtoFactory
                    .createCustomerTransactionInstance(item);
            list.add(dto);
        }
        return list;
    }

    private List<CustomerDto> mergeAndSortResults(List<Customer> localResults,
            Map<Integer, SubsidiaryContactInfoDto> remoteResults) {
        if (localResults == null) {
            return null;
        }
        List<CustomerDto> mergedCustomers = new ArrayList<CustomerDto>();
        for (Customer cust : localResults) {
            SubsidiaryContactInfoDto contact = null;
            if (remoteResults != null) {
                contact = remoteResults.get(cust.getBusinessId());
                if (contact == null) {
                    continue;
                }
            }
            else {
                // Continue to build customer DTO when contact data is not
                // available
                contact = Rmt2SubsidiaryDtoFactory
                        .createSubsidiaryInstance(null);
            }

            CustomerDto newCust = Rmt2SubsidiaryDtoFactory
                    .createCustomerInstance(cust, null);
            newCust.setContactName(contact.getContactName() == null ? "Unavailable"
                    : contact.getContactName());
            newCust.setContactPhone(contact.getContactPhone() == null ? "Unavailable"
                    : contact.getContactPhone());
            newCust.setContactFirstname(contact.getContactFirstname() == null ? "Unavailable"
                    : contact.getContactFirstname());
            newCust.setContactLastname(contact.getContactLastname() == null ? "Unavailable"
                    : contact.getContactLastname());
            newCust.setContactExt(contact.getContactExt() == null ? "Unavailable"
                    : contact.getContactExt());
            newCust.setTaxId(contact.getTaxId() == null ? "Unavailable"
                    : contact.getTaxId());
            newCust.setAddrId(contact.getAddrId());
            newCust.setAddr1(contact.getAddr1() == null ? "Unavailable"
                    : contact.getAddr1());
            newCust.setAddr2(contact.getAddr2() == null ? "Unavailable"
                    : contact.getAddr2());
            newCust.setAddr3(contact.getAddr3() == null ? "Unavailable"
                    : contact.getAddr3());
            newCust.setAddr4(contact.getAddr4() == null ? "Unavailable"
                    : contact.getAddr4());
            newCust.setCity(contact.getCity() == null ? "Unavailable" : contact
                    .getCity());
            newCust.setState(contact.getState() == null ? "Unavailable"
                    : contact.getState());
            newCust.setZip(contact.getZip());
            newCust.setZipext(contact.getZipext());
            newCust.setShortName(contact.getShortName() == null ? "Unavailable"
                    : contact.getShortName());
            mergedCustomers.add(newCust);
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

    private List<Customer> fetch(Customer criteria) throws CustomerDaoException {
        // Retrieve customer data from the database
        List<Customer> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CustomerDaoException(e);
        }
        return results;
    }

    /**
     * Associate a transaction history item to a customer subsidiary account.
     * 
     * @param customerXact
     *            an instnace of {@link SubsidiaryXactHistoryDto}
     * @return the total number of items associated with the customer subsidiary
     *         account.
     * @throws CustomerDaoException
     */
    @Override
    public int maintain(SubsidiaryXactHistoryDto customerXact)
            throws SubsidiaryDaoException {
        if (customerXact == null) {
            throw new CustomerDaoException(
                    "Input customer subsidiary transaction item object is invalid or null");
        }
        CustomerActivity ca = SubsidiaryDaoFactory.createCustomerActivity(
                customerXact.getSubsidiaryId(), customerXact.getXactId(),
                customerXact.getActivityAmount());
        UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
        ca.setDateCreated(ut.getDateCreated());
        ca.setDateUpdated(ut.getDateCreated());
        ca.setUserId(ut.getLoginId());
        ca.setIpCreated(ut.getIpAddr());
        ca.setIpUpdated(ut.getIpAddr());

        try {
            int rc = this.client.insertRow(ca, true);
            return rc;
        } catch (DatabaseException e) {
            throw new CustomerDaoException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.CustomerDao#maintain(org.dto.CustomerDto)
     */
    @Override
    public int maintain(CustomerDto cust) throws CustomerDaoException {
        Customer orm = SubsidiaryDaoFactory.createRmt2OrmCustomerBean(cust);
        int rc = 0;
        if (orm.getCustomerId() <= 0) {
            rc = this.insert(orm);
        }
        else {
            rc = this.update(orm);
        }
        return rc;
    }

    private int insert(Customer cust) throws CustomerDaoException {
        // Handle user update timestamps
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            cust.setDateCreated(ut.getDateCreated());
            cust.setDateUpdated(ut.getDateCreated());
            cust.setUserId(ut.getLoginId());
            cust.setIpCreated(ut.getIpAddr());
            cust.setIpUpdated(ut.getIpAddr());
        } catch (Exception e) {
            throw new CustomerDaoException(e);
        }

        // Perform the actual insert of customer.
        try {
            int newKey = this.client.insertRow(cust, true);
            cust.setCustomerId(newKey);
            return newKey;
        } catch (DatabaseException e) {
            throw new CustomerDaoException(e);
        }
    }

    private int update(Customer cust) throws CustomerDaoException {
        // Handle user update timestamps
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            cust.setDateUpdated(ut.getDateCreated());
            cust.setUserId(ut.getLoginId());
            cust.setIpUpdated(ut.getIpAddr());
        } catch (Exception e) {
            throw new CustomerDaoException(e);
        }

        // Perform the actual update of customer.
        try {
            cust.addCriteria(Customer.PROP_CUSTOMERID, cust.getCustomerId());
            int rc = this.client.updateRow(cust);
            return rc;
        } catch (DatabaseException e) {
            throw new CustomerDaoException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.CustomerDao#delete(int)
     */
    @Override
    public int delete(int custId) throws CustomerDaoException {
        Customer criteria = new Customer();
        try {
            criteria.addCriteria(Customer.PROP_CUSTOMERID, custId);
            int rc = this.client.deleteRow(criteria);
            return rc;
        } catch (DatabaseException e) {
            throw new CustomerDaoException(e);
        }
    }

    /**
     * Calculate and return the customer's balance.
     * 
     * @param customerId
     *            the unique id of the customer.
     * @return the balance as a double
     * @throws CustomerDaoException
     */
    @Override
    public double calculateBalance(int customerId)
            throws SubsidiaryDaoException {
        String sql = RMT2String.replace(
                AccountingSqlConst.SQL_CUSTOMER_BALANCE,
                String.valueOf(customerId), "$1");
        double bal = 0;
        try {
            ResultSet rs = this.client.executeSql(sql);
            if (rs.next()) {
                bal = rs.getDouble("balance");
            }
            return bal;
        } catch (Exception e) {
            throw new CustomerDaoException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.SubsidiaryDao#fetchDomain(org.dto.SubsidiaryDto)
     */
    @Override
    public List<CustomerDto> fetchDomain(SubsidiaryDto criteria)
            throws SubsidiaryDaoException {
        // Gather customer criteria
        Customer ormCust = null;
        if (criteria != null) {
            ormCust = new Customer();
            if (criteria.getSubsidiaryId() > 0) {
                ormCust.addCriteria(Customer.PROP_CUSTOMERID,
                        criteria.getSubsidiaryId());
            }
            if (criteria.getContactId() > 0) {
                ormCust.addCriteria(Customer.PROP_BUSINESSID,
                        criteria.getContactId());
            }
            if (criteria.getAccountNo() != null) {
                ormCust.addCriteria(Customer.PROP_ACCOUNTNO,
                        criteria.getAccountNo());
            }
        }

        // Retrieve customer local data
        List<Customer> localResults = this.fetch(ormCust);
        return this.mergeAndSortResults(localResults, null);
    }

}
