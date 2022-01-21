package org.dao.transaction;

import java.util.List;

import org.dto.CommonXactDto;
import org.dto.XactCategoryDto;
import org.dto.XactCodeDto;
import org.dto.XactCodeGroupDto;
import org.dto.XactDto;
import org.dto.XactTypeDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.XactTypeItemDto;

import com.api.persistence.DaoClient;

/**
 * Common transaction interface that manages information pertaining to base
 * transactions, transaction category, transaction type, transaction lookup
 * groups transaction lookup codes, and transaction type item activity.
 * 
 * @author Roy Terrell
 * 
 */
public interface XactDao extends DaoClient {

    /**
     * Retrieves common transaction data using selection criteria
     * 
     * @param criteria
     *            An instance of {@link CommonXactDto} representing the
     *            selection criteria to limit result set
     * @return {@link Xact} A List of {@link CommonXactDto} objects or null if
     *         no data is found
     * @throws XactDaoException
     */
    List<CommonXactDto> fetchXact(CommonXactDto criteria) throws XactDaoException;

    /**
     * Retrieves transaction data using selection criteria
     * 
     * @param criteria
     *            An instance of {@link XactDto} representing the selection
     *            criteria to limit result set
     * @return {@link Xact} A List of {@link XactDto} objects or null if no data
     *         is found
     * @throws XactDaoException
     */
    List<XactDto> fetchXact(XactDto criteria) throws XactDaoException;

    /**
     * Retrieves a list of transacstion category data based on custom criteria
     * supplied by the user.
     * 
     * @param criteria
     *            An instance of {@link XactCategoryDto} representing the
     *            selection criteria to limit result set
     * @return A List of {@link XactCategoryDto} objects or null if no data is
     *         found
     * @throws XactDaoException
     */
    List<XactCategoryDto> fetchCategory(XactCategoryDto criteria) throws XactDaoException;

    /**
     * Retrieves a list of transaction code group data based custom criteria
     * supplied by the user. User is responsible for setting the base view and
     * class so that the API will know what data to retrieve.
     * 
     * @param criteria
     *            An instance of {@link XactCodeGroupDto} representing the
     *            selection criteria to limit result set
     * @return A List of {@link XactCodeGroupDto} objects or null if no data is
     *         found
     * @throws XactDaoException
     */
    List<XactCodeGroupDto> fetchGroup(XactCodeGroupDto criteria) throws XactDaoException;

    /**
     * Retrieves a list of transaction code data based custom criteria supplied
     * by the user. User is responsible for setting the base view and class so
     * that the API will know what data to retrieve.
     * 
     * @param criteria
     *            An instance of {@link XactCodeDto} representing the selection
     *            criteria to limit result set
     * @return A List of {@link XactCodeDto} objects or null if no data is found
     * @throws XactDaoException
     */
    List<XactCodeDto> fetchCode(XactCodeDto criteria) throws XactDaoException;

    /**
     * Retrieves an ArrayList of transaction type objects based custom criteria
     * supplied by the user.
     * 
     * @param criteria
     *            An instance of {@link XactTypeDto} representing the selection
     *            criteria to limit result set
     * @return A List of {@link XactTypeDto} objects or null if no data is found
     * @throws XactDaoException
     */
    List<XactTypeDto> fetchType(XactTypeDto criteria) throws XactDaoException;

    /**
     * Retrieves one or more transaction type items related to a transaction
     * type id.
     * 
     * @param criteria
     *            An instance of {@link XactTypeItemDto} representing the
     *            selection criteria to limit result set
     * @return A List of {@link XactTypeItemDto} objects or null if no data is
     *         found
     * @throws XactDaoException
     */
    List<XactTypeItemDto> fetchXactTypeItem(XactTypeItemDto criteria) throws XactDaoException;

    /**
     * Retrieves one or more transaction type item activity objects related to
     * xactId.
     * 
     * @param criteria
     *            An instance of {@link XactTypeItemActivityDto} representing
     *            the selection criteria to limit result set
     * @return A List of {@link XactTypeItemActivityDto} objects or null if no
     *         data is found
     * @throws XactDaoException
     */
    List<XactTypeItemActivityDto> fetchXactTypeItemActivity(XactTypeItemActivityDto criteria) throws XactDaoException;

    /**
     * Retrieves one or more extended transaction type item activity objects
     * related to xactId.
     * 
     * @param criteria
     *            An instance of {@link XactTypeItemActivityDto} representing
     *            the selection criteria to limit result set
     * @return A List of {@link XactTypeItemActivityDto} objects or null if no
     *         data is found
     * @throws XactDaoException
     */
    List<XactTypeItemActivityDto> fetchXactTypeItemActivityExt(XactTypeItemActivityDto criteria) throws XactDaoException;

    /**
     * Creates a common transaction.
     * 
     * @param xact
     *            The transaction object to be managed.
     * @param items
     *            A list of transaction Type Item Activity objects
     * @return The id of the newly formed transaction.
     * @throws XactDaoException
     */
    int maintain(XactDto xact, List<XactTypeItemActivityDto> items) throws XactDaoException;

    /**
     * Updates an existing transaction entry.
     * 
     * @param xact
     *            The target transaction.
     * @return total number of rows effected.
     * @throws XactDaoException
     */
    int maintain(XactDto xact) throws XactDaoException;
    
    /**
     * Deletes one or more transaction entries.
     * 
     * @param transactionIdList
     *            A List of Integer.
     * @return total number of rows effected.
     * @throws XactDaoException
     */
    int delete(List<Integer> transactionIdList) throws XactDaoException;
}
