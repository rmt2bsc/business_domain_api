package org.dao.generalledger;

import java.util.List;

import org.dto.AccountCategoryDto;
import org.dto.AccountDto;
import org.dto.AccountExtDto;
import org.dto.AccountTypeDto;

import com.api.persistence.DaoClient;
import com.api.persistence.DatabaseException;

/**
 * Contract for accessing and managing the General Ledger account, account type,
 * and account categeory data.
 * 
 * @author Roy Tererll
 * 
 */
public interface GeneralLedgerDao extends DaoClient {

    /**
     * Fetches one or more general ledger accounts objects using selection
     * criteria.
     * <p>
     * This method will use property values as wildcards for selection criteria
     * where applicable. To select all accounts, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link AccountDto} to filter results or null to
     *            fetch all accounts.
     * @return A List of {@link AccountDto} or null if no data is found.
     * @throws DatabaseException
     *             general data access errors
     */
    List<AccountDto> fetchAccount(AccountDto criteria) throws DatabaseException;

    /**
     * Fetches one or more general ledger accounts objects using selection
     * criteria.
     * <p>
     * This method will use property values to build exact match predicates for
     * queries selection criteria where applicable. To select all accounts, pass
     * <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountDto} to filter results or null to
     *            fetch all accounts.
     * @return A List of {@link AccountDto} or null if no data is found.
     * @throws DatabaseException
     *             general data access errors
     */
    List<AccountDto> fetchAccountExact(AccountDto criteria)
            throws DatabaseException;

    /**
     * Fetches one or more general ledger account category objects using
     * selection criteria.
     * <p>
     * To select all categories, pass <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountCategoryDto} to filter results or
     *            null to fetch all accounts.
     * @return A List of {@link AccountCategoryDto} or null if no data is found.
     * @throws DatabaseException
     *             general data access errors
     */
    List<AccountCategoryDto> fetchCategory(AccountCategoryDto criteria)
            throws DatabaseException;

    /**
     * Fetches one or more general ledger account type objects using selection
     * criteria.
     * <p>
     * To select all account types, pass <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountTypeDto} to filter results or
     *            null to fetch all accounts.
     * @return A List of {@link AccountTypeDto} or null if no data is found.
     * @throws DatabaseException
     *             general data access errors
     */
    List<AccountTypeDto> fetchType(AccountTypeDto criteria)
            throws DatabaseException;

    /**
     * Fetches one or more general ledger account extension objects using
     * selection criteria.
     * <p>
     * To select all account extensions, pass <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountExtDto} to filter results or null
     *            to fetch all accounts.
     * @return A List of {@link AccountExtDto} or null if no data is found.
     * @throws DatabaseException
     *             general data access errors
     */
    List<AccountExtDto> fetchAccountExt(AccountExtDto criteria)
            throws DatabaseException;

    /**
     * Adds a new or modifies an existing general ledger account.
     * 
     * @param account
     *            an instance of {@link AccountDto} which contains the account
     *            changes to apply.
     * @return an int value representing either the unique identifier of the
     *         account added, or the total number of accounts effected by the
     *         update operation.
     * @throws DatabaseException
     */
    int maintainAccount(AccountDto account) throws DatabaseException;

    /**
     * Deletes a general ledger account.
     * 
     * @param acctId
     *            The id of the account to delete.
     * @return int
     * @throws DatabaseException
     */
    int deleteAccount(int acctId) throws DatabaseException;

    /**
     * Adds a new or modifies an existing general ledger category.
     * 
     * @param category
     *            an instance of {@link AccountCategoryDto} which contains the
     *            category changes to apply.
     * @return an int value representing either the unique identifier of the
     *         category added, or the total number of categories effected by the
     *         update operation.
     * @throws DatabaseException
     */
    int maintainCategory(AccountCategoryDto category) throws DatabaseException;

    /**
     * Deletes a general ledger category.
     * 
     * @param acctId
     *            The id of the category to delete.
     * @return int
     * @throws DatabaseException
     */
    int deleteCategory(int catgId) throws DatabaseException;
    
    /**
     * Retrieves the next account sequence number for the account.
     * <p>
     * Sequence numbers of generated based specific implmentation.
     * 
     * @param acct
     *            an instance of {@link GlAccounts}
     * @return The next sequence number.
     * @throws DatabaseException
     */
   int getNextAccountSeq(AccountDto account);
}
