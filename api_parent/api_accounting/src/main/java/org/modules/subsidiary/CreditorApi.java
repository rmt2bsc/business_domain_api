package org.modules.subsidiary;

import java.util.List;

import org.dto.CreditorDto;
import org.dto.CreditorTypeDto;
import org.dto.CreditorXactHistoryDto;

/**
 * An interface that provides the method prototypes required to be implemented
 * in order to fetch, persist, and validate creditor objects.
 * 
 * @author Roy Terrell
 * 
 */
public interface CreditorApi extends SubsidiaryApi {
    /**
     * Fetch creditor object using its id.
     * 
     * @param id
     *            creditor's internal unique id
     * @return An instance of {@link CreditorDto} or null when no data is found.
     * @throws CreditorApiException
     */
    CreditorDto getByUid(Integer id) throws CreditorApiException;

    /**
     * Fetch one or more creditors based on a business contact.
     * 
     * @param businessId
     *            The internal unique id of the business.
     * @return An instance of {@link CreditorDto} or null when no data is found.
     * @throws CreditorApiException
     */
    CreditorDto getByBusinessId(Integer businessId) throws CreditorApiException;

    /**
     * Fetch creditors based on a particular creditor type.
     * 
     * @param creditorTypeId
     *            The credior type id.
     * @return A List of {@link CreditorDto} or null when no data is found.
     * @throws CreditorApiException
     */
    List<CreditorDto> getByCreditorType(Integer creditorTypeId) throws CreditorApiException;

    /**
     * Fetch creditors using an account number.
     * 
     * @param acctNo
     *            The creditor's account number
     * @return A List of {@link CreditorDto} or null when no data is found.
     * @throws CreditorApiException
     */
    List<CreditorDto> getByAcctNo(String acctNo) throws CreditorApiException;

    /**
     * Fetch the business contact information related to a particular creditor.
     * 
     * @param creditorId
     *            the creditor's internal unique id.
     * @return An instance of {@link CreditorDto} or null when no data is found.
     * @throws CreditorApiException
     */
    CreditorDto getByCreditorId(Integer creditorId) throws CreditorApiException;

    /**
     * Fetch the creditor/business contact information using creditor criteria
     * instance as the source of selection criteria.
     * 
     * @param criteria
     * @return A List of {@link CreditorDto} or null when no data is found.
     * @throws CreditorApiException
     */
    List<CreditorDto> get(CreditorDto criteria) throws CreditorApiException;

    /**
     * Fetch all creditor type records.
     * 
     * @return A List of {@link CreditorTypeDto} or null when no data is found.
     * @throws CreditorApiException
     */
    List<CreditorTypeDto> getCreditorType() throws CreditorApiException;

    /**
     * Fetch a creditor type record using its id.
     * 
     * @param creditorTypeId
     *            internal unique id of the creditor type.
     * @return An instance of {@link CreditorTypeDto} or null when no data is
     *         found.
     * @throws CreditorApiException
     */
    CreditorTypeDto getCreditorType(Integer creditorTypeId) throws CreditorApiException;
    
    /**
     * Get transacton history for a particular subsidiary account.
     * 
     * @param subsidiaryId
     *            the unique id of the subsidiary account
     * @return a List<E> representing the transaction history.
     * @throws CreditorApiException
     */
    List<CreditorXactHistoryDto> getTransactionHistory(int subsidiaryId) throws CreditorApiException;

    /**
     * Creates a new or modifies an existing creditor's profile.
     * <p>
     * An insert is performed when the creditorId property of <i>creditor</i> is
     * equal to zero. When the creditorId property is greater than zero, an
     * update is performed.
     * 
     * @param creditor
     *            an instance of {@link CreditorDto}
     * @return the total number of entities effected by the transaction.
     * @throws CreditorApiException
     */
    int update(CreditorDto creditor) throws CreditorApiException;

    /**
     * Deletes a creditor's profile.
     * 
     * @param creditor
     *            an instance of {@link CreditorDto}
     * @return the total number of entities effected by the transaction.
     * @throws CreditorApiException
     */
    int delete(CreditorDto creditor) throws CreditorApiException;

    /**
     * Validate a single creditor instance.
     * 
     * @param cust
     *            an instance of {@link CreditorDto} representing the creditor
     *            that is to be valdiated
     * @throws CreditorApiException
     */
    void validate(CreditorDto cust) throws CreditorApiException;
}
