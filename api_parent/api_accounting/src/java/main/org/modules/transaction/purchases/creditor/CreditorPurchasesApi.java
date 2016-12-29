package org.modules.transaction.purchases.creditor;

import java.util.Date;
import java.util.List;

import org.dto.XactCreditChargeDto;
import org.dto.XactTypeItemActivityDto;
import org.modules.transaction.XactApi;

/**
 * Api interface for managing creditor purchase transactions.
 * <p>
 * A <b>creditor purchase</b> transaction can be described as the exchange of
 * goods and services from a merchant on credit generally using a credit card at
 * the time the transaction occurs.
 * <p>
 * When a credit charge is created, the base transaction amount is posted to the
 * xact table as a positive value, which represents an increase the company's
 * liability to its creditor. When a credit charge is reversed, the base
 * transaction amount is posted to the xact table as a negative value which
 * decreases the value of the company's liability to its creditor.
 * <p>
 * Credit Charge transactions require an addtional posting of transaction
 * amounts to reflect creditor activity which offsets the base transaction
 * amount. When a credit charge is created, the creditor activity amount is
 * posted as a positive value which increases the value of the creditor's
 * account. Conversely, when a credit charge is reversed, the creditor activity
 * amount is posted as negative value decreasing the value of the creditor's
 * account.
 * 
 * @author Roy Terrell
 * 
 */
public interface CreditorPurchasesApi extends XactApi {

    /**
     * Retrieve a single credit purchase transaction by transaction id.
     * 
     * @param xactId
     *            id of the transaction
     * @return an instance of {@link XactCreditChargeDto} or null if no data is
     *         found
     * @throws CreditorPurchasesApiException
     */
    XactCreditChargeDto get(int xactId) throws CreditorPurchasesApiException;

    /**
     * Retrieve one or more credit purchase transactions by transaction date.
     * 
     * @param xactDate
     *            the date of the transaction
     * @return A List of {@link XactCreditChargeDto} objects or null if no data
     *         is found
     * @throws CreditorPurchasesApiException
     */
    List<XactCreditChargeDto> get(Date xactDate)
            throws CreditorPurchasesApiException;

    /**
     * Retrieve one or more credit purchase transactions by custom selection
     * criteria.
     * 
     * @param criteria
     *            A String containing selection criteria that is compatible to
     *            the data source.
     * @return A List of {@link XactCreditChargeDto} objects or null if no data
     *         is found
     * @throws CreditorPurchasesApiException
     */
    List<XactCreditChargeDto> get(String criteria)
            throws CreditorPurchasesApiException;

    /**
     * Retrieve one or more credit purchase transactions by creditor id.
     * 
     * @param creditorId
     *            the id of the creditor
     * @return A List of {@link XactCreditChargeDto} objects or null if no data
     *         is found
     * @throws CreditorPurchasesApiException
     */
    List<XactCreditChargeDto> getByCreditor(int creditorId)
            throws CreditorPurchasesApiException;

    /**
     * Retrieve one or more credit purchase transactions by account number.
     * 
     * @param accountNo
     *            the account number of the creditor
     * @return A List of {@link XactCreditChargeDto} objects or null if no data
     *         is found
     * @throws CreditorPurchasesApiException
     */
    List<XactCreditChargeDto> getByAcctNo(String accountNo)
            throws CreditorPurchasesApiException;

    /**
     * Retrieve one or more credit purchase transactions by transaction
     * confirmation number.
     * 
     * @param confirmNo
     *            the confirmation number of a given transaction
     * @return A List of {@link XactCreditChargeDto} objects or null if no data
     *         is found
     * @throws CreditorPurchasesApiException
     */
    List<XactCreditChargeDto> getByConfirmNo(String confirmNo)
            throws CreditorPurchasesApiException;

    /**
     * Retrieve one or more credit purchase transactions by transaction reason.
     * 
     * @param reason
     *            The reason or source description of the transaction.
     * @return A List of {@link XactCreditChargeDto} objects or null if no data
     *         is found
     * @throws CreditorPurchasesApiException
     */
    List<XactCreditChargeDto> getByReason(String reason)
            throws CreditorPurchasesApiException;

    /**
     * Retrieves base creditor purchases transaction objects using
     * <i>criteria</i> as a filter.
     * 
     * @param criteria
     *            an instance of {@link XactCreditChargeDto}
     * @return A List of {@link XactCreditChargeDto} objects
     * @throws CreditorPurchasesApiException
     *             TODO
     * @throws CreditorPurchasesApiException
     */
    List<XactCreditChargeDto> get(XactCreditChargeDto criteria)
            throws CreditorPurchasesApiException;

    /**
     * Retrieves all related detail items of a credit purchase transaction.
     * 
     * @param xactId
     *            the id of the transaction to obtain related items.
     * @return A List of {@link XactTypeItemActivityDto} objects
     * @throws CreditorPurchasesApiException
     *             TODO
     */
    List<XactTypeItemActivityDto> getItems(int xactId)
            throws CreditorPurchasesApiException;

    /**
     * Creates a new or reverses an existing creditor purchase transaction.
     * 
     * @param xact
     *            An instance of {@link XactCreditChargeDto}
     * @param items
     *            A List of {@link XactTypeItemActivityDto} objects representing
     *            the transaction details.
     * @return The id of the transaction created.
     * @throws CreditorPurchasesApiException
     */
    int update(XactCreditChargeDto xact, List<XactTypeItemActivityDto> items)
            throws CreditorPurchasesApiException;

}
