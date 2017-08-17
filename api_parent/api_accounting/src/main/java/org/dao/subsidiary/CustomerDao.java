package org.dao.subsidiary;

import java.util.List;

import org.dto.CustomerDto;

/**
 * Contract for accessing and managing data related to customers.
 * 
 * @author Roy Terrell
 * 
 */
public interface CustomerDao extends SubsidiaryDao {

    /**
     * Fetches a single customer record by customer id.
     * 
     * @param customerId
     *         The unique identifier of the customer record.
     * @return an instance of {@link CustomerDto} or null when not found.
     * @throws CustomerDaoException
     */
    CustomerDto fetch(int customerId) throws CustomerDaoException;
    
    /**
     * Fetches one or more customer records based on the supplied selection
     * criteria.
     * 
     * @param criteria
     *            an instance of {@link CustomerDto} containing data that is
     *            used to build selection criteria
     * @return a list of {@link CustomerDto} objects or null when the query
     *         returns an empty result set.
     * @throws CustomerDaoException
     */
    List<CustomerDto> fetch(CustomerDto criteria) throws CustomerDaoException;

    /**
     * Modifies an existing customer residing in some external data source.
     * 
     * @param cust
     *            an instance of {@link CustomerDto} representing the customer
     *            to be updated.
     * @return the number of customer entities effected
     * @throws CustomerDaoException
     */
    int maintain(CustomerDto cust) throws CustomerDaoException;

    /**
     * Delete customer from some external data soruce
     * 
     * @param custId
     *            the initernal unique id of the creditor targeted for deletion.
     * @return the number of customer entities effected
     * @throws CustomerDaoException
     */
    int delete(int custId) throws CustomerDaoException;

    // /**
    // * Validate a single customer instance.
    // *
    // * @param cust
    // * an instance of {@link CustomerDto} representing the customer
    // * that is to be valdiated
    // * @throws CustomerDaoException
    // */
    // void validate(CustomerDto cust) throws CustomerDaoException;

    // /**
    // * Fetches one or more customer records using a selection criteria
    // predicate
    // * that is compliant to the external data soure.
    // *
    // * @param criteria
    // * A String representing the selection predicate to be applied to
    // * the query that complies to the rules of the underlying data
    // * source.
    // * @return a list of {@link CustomerDto} objects or null when the query
    // * returns an empty result set.
    // * @throws CustomerDaoException
    // */
    // List<CustomerDto> fetch(String criteria) throws CustomerDaoException;
}
