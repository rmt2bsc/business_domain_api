package org.modules.subsidiary;

import java.util.List;

import org.dto.CustomerDto;
import org.dto.CustomerXactHistoryDto;

/**
 * An interface that provides the method prototypes required to be implemented
 * in order to fetch, persist, and validate customer objects.
 * 
 * @author Roy Terrell
 * 
 */
public interface CustomerApi extends SubsidiaryApi {

    /**
     * Fetch customer object using its id.
     * 
     * @param id
     *            customer's internal unique id
     * @return An instance of {@link CustomerDto} or null when no data is found.
     * @throws CustomerApiException
     */
    CustomerDto getByUid(Integer id) throws CustomerApiException;

    /**
     * Fetch the business contact information by business id.
     * 
     * @param businessId
     *            The internal unique id of the business.
     * @return An instance of {@link CustomerDto} or null when no data is found.
     * @throws CustomerApiException
     */
    CustomerDto getByBusinessId(Integer businessId) throws CustomerApiException;

    /**
     * Fetch the business contact information by customer id.
     * 
     * @param customerId
     *            the customer's internal unique id.
     * @return An instance of {@link CustomerDto} or null when no data is found.
     * @throws CustomerApiException
     */
    CustomerDto getByCustomerId(Integer customerId) throws CustomerApiException;

    /**
     * Fetch customer(s) using an account number.
     * 
     * @param acctNo
     *            The customer's account number
     * @returnA List {@link CustomerDto} or null when no data is found.
     * @throws CustomerApiException
     */
    List<CustomerDto> getByAcctNo(String acctNo) throws CustomerApiException;

    /**
     * Fetch the customer/business contact information using customer criteria
     * instance as the source of selection criteria.
     * 
     * @param criteria
     * @return A List {@link CustomerDto} or null when no data is found.
     * @throws CustomerApiException
     */
    List<CustomerDto> get(CustomerDto criteria) throws CustomerApiException;

    /**
     * Get transacton history for a particular customer account.
     * 
     * @param customerId
     *            the unique id of the customer account
     * @return a List of {@link CustomerXactHistoryDto} objects representing the
     *         transaction history of the customer.
     * @throws CustomerApiException
     */
    List<CustomerXactHistoryDto> getTransactionHistory(Integer customerId) throws CustomerApiException;
    
    /**
     * Creates a new or modifies an existing customer's profile.
     * <p>
     * An insert is performed when the customerId property of <i>customer</i> is
     * equal to zero. When the customerId property is greater than zero, an
     * update is performed.
     * 
     * @param customer
     *            an instance of {@link CustomerDto}
     * @return the total number of entities effected by the transaction.
     * @throws CustomerApiException
     */
    int update(CustomerDto customer) throws CustomerApiException;

    /**
     * Deletes a customer's profile.
     * 
     * @param customer
     *            an instance of {@link CustomerDto}
     * @return the total number of entities effected by the transaction.
     * @throws CustomerApiException
     */
    int delete(CustomerDto customer) throws CustomerApiException;

    /**
     * Validate a single customer instance.
     * 
     * @param cust
     *            an instance of {@link CustomerDto} representing the customer
     *            that is to be valdiated
     * @throws CustomerApiException
     */
    void validate(CustomerDto cust) throws CustomerApiException;
}
