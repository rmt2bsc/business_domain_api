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
     *  Fetch the creditor information using creditor id.
     * <p>
     * Business contact data is included in the results set.
     * 
     * @param customerId
     * @return An instance of {@link CreditorDto} or null when no data is found.
     * @throws CreditorApiException
     */
    CreditorDto get(Integer creditorId) throws CreditorApiException;
    
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
     * Fetch the creditor contact information using creditor criteria
     * instance as the source of selection criteria.
     * 
     * @param criteria
     * @return A List of {@link CreditorDto} or null when no data is found.
     * @throws CreditorApiException
     */
    List<CreditorDto> get(CreditorDto criteria) throws CreditorApiException;
    
    /**
     * Fetch the creditor/business contact information using creditor criteria
     * instance as the source of selection criteria.
     * 
     * @param criteria
     * @return A List of {@link CreditorDto} or null when no data is found.
     * @throws CreditorApiException
     */
    List<CreditorDto> getExt(CreditorDto criteria) throws CreditorApiException;

    /**
     * Fetch one or more creditor type records.
     * 
     * @param criteria
     *            instance of {@link CreditorTypeDto}
     * @return A List of {@link CreditorTypeDto} or null when no data is found.
     * @throws CreditorApiException
     */
    List<CreditorTypeDto> getCreditorType(CreditorTypeDto criteria) throws CreditorApiException;

    /**
     * Get transacton history for a particular subsidiary account.
     * 
     * @param creditorId
     *            the unique id of the creditor account
     * @return a List<E> representing the transaction history.
     * @throws CreditorApiException
     */
    List<CreditorXactHistoryDto> getTransactionHistory(Integer creditorId) throws CreditorApiException;

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
     * @throws {@link com.InvalidDataException}
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

}
