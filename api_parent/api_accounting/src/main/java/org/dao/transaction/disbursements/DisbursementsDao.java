package org.dao.transaction.disbursements;

import java.util.List;

import org.dao.transaction.XactDao;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;

/**
 * Interface for accessing and managing data pertaining to disbursement
 * transactions.
 * 
 * @author Roy Terrell
 * 
 */
public interface DisbursementsDao extends XactDao {

    /**
     * Query cash disbursements by transaction
     * 
     * @param criteria
     * @return
     * @throws DisbursementsDaoException
     */
    List<XactDto> fetchDisbursmentByXact(XactDto criteria) throws DisbursementsDaoException;

    /**
     * Query cash disbursements by transaction item.
     * 
     * @param criteria
     * @return
     * @throws DisbursementsDaoException
     */
    List<XactTypeItemActivityDto> fetchDisbursmentByXactItem(XactTypeItemActivityDto criteria)
            throws DisbursementsDaoException;

    // /**
    // * Creates a general cash disbursement transaction or reverses and
    // existing
    // * cash disbursement transaction.
    // *
    // * @param xact
    // * The target transaction
    // * @param items
    // * The transaction items
    // * @returnint
    // * @throws DisbursementsDaoException
    // */
    // int maintain(XactDto xact, List<XactTypeItemActivityDto> items)
    // throws DisbursementsDaoException;
    //
    // /**
    // * Creates a creditor related cash disbursement transaction or reverses
    // and
    // * existing one.
    // *
    // * @param xact
    // * The target transaction
    // * @param items
    // * The transaction items
    // * @param creditorId
    // * The id of creditor assoicated with the transaction
    // * @return int
    // * @throws DisbursementsDaoException
    // */
    // int maintain(XactDto xact, List<XactTypeItemActivityDto> items, int
    // creditorId)
    // throws DisbursementsDaoException;

}
