package org.dao.subsidiary;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.AccountingSqlConst;
import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.CustomerActivity;
import org.dao.mapping.orm.rmt2.VwCustomerXactHist;
import org.dto.CustomerDto;
import org.dto.CustomerXactHistoryDto;
import org.dto.SubsidiaryXactHistoryDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;

import com.api.persistence.DatabaseException;
import com.api.persistence.PersistenceClient;
import com.api.util.RMT2Date;
import com.api.util.RMT2String;
import com.api.util.UserTimestamp;

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

    @Override
    public CustomerDto fetch(int customerId) throws CustomerDaoException {
        // Retrieve customer data
        Customer criteria = new Customer();
        criteria.setCustomerId(customerId);
        try {
            Customer results = (Customer) this.client.retrieveObject(criteria);
            if (results == null) {
                return null;
            }
            // Package data
            CustomerDto dto = Rmt2SubsidiaryDtoFactory.createCustomerInstance(results, null);
            return dto;
        } catch (DatabaseException e) {
            throw new CustomerDaoException("Database Error fetching customer data by customer id, " + customerId, e);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.CustomerDao#fetch(org.dto.CustomerDto)
     */
    @Override
    public List<CustomerDto> fetch(CustomerDto criteria) throws CustomerDaoException {
       
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
        List<Customer> results = this.fetch(ormCust);
        if (results == null) {
            return null;
        }
        // Package data
        List<CustomerDto> list = new ArrayList<CustomerDto>();;
        for (Customer item : results) {
            CustomerDto dto = Rmt2SubsidiaryDtoFactory.createCustomerInstance(item, null);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches the customer's transaction history from the
     * <i>customer_activitiy</i> table.
     * <p>
     * The returned dataset is ordered by transaction date and transaction id in
     * descending order.
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
            throw new CustomerDaoException(e);
        }

        List<CustomerXactHistoryDto> list = new ArrayList<CustomerXactHistoryDto>();
        for (VwCustomerXactHist item : results) {
            CustomerXactHistoryDto dto = Rmt2SubsidiaryDtoFactory
                    .createCustomerTransactionInstance(item);
            list.add(dto);
        }
        return list;
    }

    private List<Customer> fetch(Customer criteria) throws CustomerDaoException {
        // Retrieve customer data from the database
        List<Customer> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null || results.isEmpty()) {
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
    public double calculateBalance(int customerId) throws SubsidiaryDaoException {
        String sql = RMT2String.replace(AccountingSqlConst.SQL_CUSTOMER_BALANCE,
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

    

}
