package org.modules.transaction.disbursements;

import java.util.List;

import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.modules.transaction.XactApi;

/**
 * An API interface form managing cash disbursement transactions.
 * <p>
 * A <b>cash disbursement</b> transaction can be described as the purchase goods
 * and services from a merchant on a cash basis at the time the transaction
 * occurs. Additionally, cash disbursement transactions can be tendered by
 * check, debit card, money order, or cashiers check.
 * <p>
 * When a cash disbursement is created, the base transaction amount is posted to
 * the xact table as a negative value, which represents a decrease in the
 * company's asset. When a cash disbursement is reversed, the base transaction
 * amount is posted to the xact table as a positive value which increases the
 * value of the company's asset.
 * <p>
 * Cash disbursement transactions require an addtional posting of transaction
 * amounts to reflect the details of the transction which offsets the base
 * transaction amount. When a cash disbursement is created, each transaction
 * detial item amount is posted to the XactTypeItemActivity table as a positive
 * value. Conversely, when a cash disbursement is reversed, the transaction
 * detial item amount is posted as negative value decreasing the value of the
 * asset account.
 * 
 * @author Roy Terrell
 * 
 */
public interface DisbursementsApi extends XactApi {

    /**
     * Fetch cash disbursements by transaction
     * 
     * @param criteria
     * @param customCriteria
     * @return
     * @throws DisbursementsApiException
     */
    List<XactDto> get(XactDto criteria, String customCriteria)
            throws DisbursementsApiException;

    /**
     * Fetch cash disbursements by transaction item.
     * 
     * @param criteria
     * @param customCriteria
     * @return
     * @throws DisbursementsDaoException
     */
    List<XactTypeItemActivityDto> getItems(XactTypeItemActivityDto criteria, String customCriteria)
            throws DisbursementsApiException;

    /**
     * Creates a general cash disbursement transaction or reverses an existing
     * cash disbursement transaction.
     * 
     * @param xact
     *            The target transaction
     * @param items
     *            The transaction items
     * @return int The transaction id of the newly created cash disbursement
     * @throws DisbursementsDaoException
     */
    int updateTrans(XactDto xact, List<XactTypeItemActivityDto> items) throws DisbursementsApiException;

    /**
     * Creates a cash disbursement transaction or reverses and
     * existing one in which the transaction is associated with a
     * creditor.
     * 
     * @param xact
     *            The target transaction
     * @param items
     *            The transaction items
     * @param creditorId
     *            The id of creditor assoicated with the transaction
     * @return int The transaction id of the newly created cash disbursement
     * @throws DisbursementsDaoException
     */
    int updateTrans(XactDto xact, List<XactTypeItemActivityDto> items, Integer creditorId) 
            throws DisbursementsApiException;
}
