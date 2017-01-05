package org.modules.transaction.disbursements;

import java.util.List;

import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.modules.transaction.XactApi;

/**
 * An API interface form managing disbursement transactions.
 * 
 * @author Roy Terrell
 * 
 */
public interface DisbursementsApi extends XactApi {

    /**
     * Fetch cash disbursements by transaction
     * 
     * @param criteria
     * @return
     * @throws DisbursementsApiException
     */
    List<XactDto> getByTransaction(String criteria)
            throws DisbursementsApiException;

    /**
     * Fetch cash disbursements by transaction item.
     * 
     * @param criteria
     * @return
     * @throws DisbursementsDaoException
     */
    List<XactTypeItemActivityDto> getByTransactionItem(String criteria)
            throws DisbursementsApiException;

    /**
     * Creates a general cash disbursement transaction or reverses and existing
     * cash disbursement transaction.
     * 
     * @param xact
     *            The target transaction
     * @param items
     *            The transaction items
     * @returnint
     * @throws DisbursementsDaoException
     */
    int updateDisbursement(XactDto xact, List<XactTypeItemActivityDto> items)
            throws DisbursementsApiException;

    /**
     * Creates a creditor related cash disbursement transaction or reverses and
     * existing one.
     * 
     * @param xact
     *            The target transaction
     * @param items
     *            The transaction items
     * @param creditorId
     *            The id of creditor assoicated with the transaction
     * @return int
     * @throws DisbursementsDaoException
     */
    int updateDisbursement(XactDto xact, List<XactTypeItemActivityDto> items,
            int creditorId) throws DisbursementsApiException;
}
