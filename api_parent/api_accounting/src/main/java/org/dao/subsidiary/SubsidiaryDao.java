package org.dao.subsidiary;

import java.util.List;
import java.util.Map;

import org.dto.SubsidiaryContactInfoDto;
import org.dto.SubsidiaryDto;
import org.dto.SubsidiaryXactHistoryDto;

import com.api.persistence.DaoClient;

/**
 * Common DAO interface for common account subsidiary data.
 * 
 * @author Roy Terrell
 * 
 */
public interface SubsidiaryDao extends DaoClient {

    /**
     * Fetches the transaction history of a subsidiary account
     * 
     * @param subsidiaryId
     *            An int value representing the query to execute. The query must
     *            comply to the rules of the underlying data source.
     * @return a list of arbitrary objects or null when the query returns an
     *         empty result set.
     * @throws SubsidiaryDaoException
     */
    <T> List<T> fetchTransactionHistory(int subsidiaryId)
            throws SubsidiaryDaoException;

    /**
     * Calculate and return the balance of the subsidary.
     * 
     * @param subsidiaryId
     *            the unique id of the subsidiary. This is typically the
     *            customer id or creditor id in regards to the specific
     *            implementation.
     * @return the balance as a double
     * @throws SubsidiaryDaoException
     */
    double calculateBalance(int subsidiaryId) throws SubsidiaryDaoException;

    /**
     * Fetches common subsidiary contact data based on the selection criteria
     * containted in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SubsidiaryContactInfoDto} which each
     *            property found to have a value will serve as selection
     *            criteria.
     * @return Map <Integer, {@link SubsidiaryContactInfoDto}> in which the map
     *         is keyed by business id.
     * @throws SubsidiaryDaoException
     */
    Map<Integer, SubsidiaryContactInfoDto> fetch(
            SubsidiaryContactInfoDto criteria) throws SubsidiaryDaoException;

    /**
     * Fetches subsidiary contact data without common contact info based on the
     * selection criteria containted in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SubsidiaryDto} which each property found
     *            to have a value will serve as selection criteria.
     * @return List of arbitrary objects
     * @throws SubsidiaryDaoException
     */
    <T> List<T> fetchDomain(SubsidiaryDto criteria)
            throws SubsidiaryDaoException;

    /**
     * Creates a new or modifies an existing common subsidiary contact data
     * based on the contents of <i>contact</i>.
     * 
     * @param contact
     *            an instance of {@link SubsidiaryContactInfoDto} containing the
     *            data that will update the subsiary's common contact profile.
     *            criteria.
     * @return The total number of items effected by the transaction
     * @throws SubsidiaryDaoException
     */
    int maintain(SubsidiaryContactInfoDto contact)
            throws SubsidiaryDaoException;

    /**
     * Associate a transaction history item to a subsidiary account.
     * 
     * @param subXact
     *            an instnace of {@link SubsidiaryXactHistoryDto}
     * @return the activity id of the association.
     * @throws SubsidiaryDaoException
     */
    int maintain(SubsidiaryXactHistoryDto subXact)
            throws SubsidiaryDaoException;

    // /**
    // * Sets the date subsidiary was created.
    // *
    // * @param date
    // * an instance of {@link java.util.Date}
    // */
    // void setDateCreated(java.util.Date date);
    //
    // /**
    // * Gets the date subsidiary was created
    // */
    // java.util.Date getDateCreated();
    //
    // /**
    // * Sets the date subsidiary was modifed.
    // *
    // * @param date
    // * an instance of {@link java.util.Date}
    // */
    // void setDateUpdated(java.util.Date date);
    //
    // /**
    // * Gets the date the subsidiary was modified.
    // */
    // java.util.Date getDateUpdated();
    //
    // /**
    // * Sets the login id of the person or process that created or modified the
    // * subsidiary.
    // *
    // * @param userName
    // * an instance of {@link String}
    // */
    // void setUpdateUserId(String userName);
    //
    // /**
    // * Gets the login id of the person or process that created or modified the
    // * subsidiary.
    // */
    // String getUpdateUserId();
    //
    // /**
    // * Sets the IP address of the owner creating a new subsidiary.
    // *
    // * @param value
    // * String
    // */
    // void setIpCreated(String value);
    //
    // /**
    // * Gets the IP address of the owner creating a new subsidiary.
    // *
    // * @return String
    // */
    // String getIpCreated();
    //
    // /**
    // * Sets the IP address of the owner updating an existing subsidiary.
    // *
    // * @param value
    // * String
    // */
    // void setIpUpdated(String value);
    //
    // /**
    // * Gets the IP address of the owner updating an existing subsidiary.
    // *
    // * @return String
    // */
    // String getIpUpdated();
    //
    // /**
    // * Builds an account number for the subsidirary.
    // *
    // * @param businessId
    // * the business id of the subsidiary.
    // * @return String as the account number
    // */
    // String buildAccountNo(int businessId);
    //
    // /**
    // * Obtain the customer's balance.
    // *
    // * @param businessId
    // * The subsiary's business id.
    // * @return double as the subsidiary's balance.
    // * @throws SubsidiaryDaoException
    // * General database errors.
    // */
    // double calculateBalance(int businessId) throws SubsidiaryDaoException;

}
