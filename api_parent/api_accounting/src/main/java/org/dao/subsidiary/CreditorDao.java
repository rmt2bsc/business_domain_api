package org.dao.subsidiary;

import java.util.List;

import org.dto.CreditorDto;
import org.dto.CreditorTypeDto;

/**
 * Contract for accessing and managing data related to creditors.
 * 
 * @author Roy Terrell
 * 
 */
public interface CreditorDao extends SubsidiaryDao {

    /**
     * Fetches a single creditor record by creditor id.
     * 
     * @param creditorId
     *         The unique identifier of the creditor record.
     * @return an instance of {@link CreditorDto} or null when not found.
     * @throws CreditorDaoException
     */
    CreditorDto fetch(int creditorId) throws CreditorDaoException;
    
    
    /**
     * Fetches one or more creditor records based on the supplied selection
     * criteria.
     * 
     * @param criteria
     *            an instance of {@link CreditorDto} containing data that is
     *            used to build selection criteria
     * @return a list of {@link CreditorDto} objects or null when the query
     *         returns an empty result set.
     * @throws CreditorDaoException
     */
    List<CreditorDto> fetch(CreditorDto criteria) throws CreditorDaoException;

    /**
     * Fetches one or more creditor type records based on the supplied selection
     * criteria.
     * 
     * @param criteria
     *            an instance of {@link CreditorTypeDto} containing data that is
     *            used to build selection criteria
     * @return a list of {@link CreditorTypeDto} objects or null when the query
     *         returns an empty result set.
     * @throws CreditorDaoException
     */
    List<CreditorTypeDto> fetch(CreditorTypeDto criteria)
            throws CreditorDaoException;

    /**
     * Modifies an existing creditor residing in some external data source
     * 
     * @param cred
     *            an instance of {@link CreditorDto} representing the creditor
     *            to be updated.
     * @return the number of creditor entities effected
     * @throws CreditorDaoException
     */
    int maintain(CreditorDto cred) throws CreditorDaoException;

    /**
     * Delete creditor from some external data soruce
     * 
     * @param credId
     *            the initernal unique id of the creditor targeted for deletion.
     * @return the number of creditor entities effected
     * @throws CreditorDaoException
     */
    int delete(int credId) throws CreditorDaoException;
}
