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
