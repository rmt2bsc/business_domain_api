package org.modules.generalledger;

import java.util.List;

import org.dto.AccountCategoryDto;
import org.dto.AccountDto;
import org.dto.AccountTypeDto;

import com.api.foundation.TransactionApi;

/**
 * An contract for executing transactions related to the General Ledger Account
 * module.
 * 
 * @author Roy Terrell
 * 
 */
public interface GlAccountApi extends TransactionApi {

    /**
     * Returns one or more general ledger account objects based on various forms
     * of selection criteria.
     * <p>
     * To select all accounts, pass <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountDto} to filter results or null to
     *            fetch all accounts.
     * @return A List of {@link AccountDto} or null if no data is found.
     * @throws GeneralLedgerApiException
     *             data access errors
     */
    List<AccountDto> getAccount(AccountDto criteria)
            throws GeneralLedgerApiException;

    /**
     * Returns a single general ledger account object based on its internal
     * unique indentifier.
     * 
     * @param accountId
     *            the internal unique identifier of the object to retrieve.
     * @return an instance of {@link AccountDto} or null if the object cannot be
     *         found
     * @throws GeneralLedgerApiException
     *             data access errors.
     */
    AccountDto getAccount(int accountId) throws GeneralLedgerApiException;

    /**
     * 
     * @param accountName
     * @return
     * @throws GeneralLedgerApiException
     */
    AccountDto getAccountByExactName(String accountName)
            throws GeneralLedgerApiException;

    /**
     * Returns one or more general ledger account type objects based on various
     * forms of selection criteria.
     * <p>
     * To select all account types, pass <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountTypeDto} to filter results or
     *            null to fetch all accounts.
     * @return A List of {@link AccountTypeDto} or null if no data is found.
     * @throws GeneralLedgerApiException
     *             data access errors
     */
    List<AccountTypeDto> getAccountType(AccountTypeDto criteria)
            throws GeneralLedgerApiException;

    /**
     * Returns a single general ledger account type object based on its internal
     * unique indentifier.
     * 
     * @param accountTypeId
     *            the internal unique identifier of the object to retrieve.
     * @return an instance of {@link AccountTypeDto} or null if the object
     *         cannot be found
     * @throws GeneralLedgerApiException
     *             data access errors.
     */
    AccountTypeDto getAccountType(int accountTypeId)
            throws GeneralLedgerApiException;

    /**
     * Returns one or more general ledger account category objects based on
     * various forms of selection criteria.
     * <p>
     * To select all account categories, pass <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountCategoryDto} to filter results or
     *            null to fetch all accounts.
     * @return A List of {@link AccountCategoryDto} or null if no data is found.
     * @throws GeneralLedgerApiException
     *             data access errors
     */
    List<AccountCategoryDto> getAccountCategory(AccountCategoryDto criteria)
            throws GeneralLedgerApiException;

    /**
     * Returns a single general ledger account category object based on its
     * internal unique indentifier.
     * 
     * @param accountCategoryId
     *            the internal unique identifier of the object to retrieve.
     * @return an instance of {@link AccountCategoryDto} or null if the object
     *         cannot be found
     * @throws GeneralLedgerApiException
     *             data access errors.
     */
    AccountCategoryDto getAccountCategory(int accountCategoryId)
            throws GeneralLedgerApiException;

    /**
     * Updates a GL Account object by either creating a new account or modifying
     * an existing account.
     * 
     * @param account
     *            an instance of {@link AccountDto} which contains the account
     *            changes to apply.
     * @return an int value representing either the unique identifier of the
     *         account added, or the total number of accounts effected by the
     *         update operation.
     * @throws GeneralLedgerApiException
     */
    int updateAccount(AccountDto account) throws GeneralLedgerApiException;

    /**
     * Updates a GL Account object by either creating a new category or
     * modifying an existing category.
     * 
     * @param category
     *            an instance of {@link AccountCategoryDto} which contains the
     *            category changes to apply.
     * @return an int value representing either the unique identifier of the
     *         category added, or the total number of categories effected by the
     *         update operation.
     * @throws GeneralLedgerApiException
     */
    int updateCategory(AccountCategoryDto category)
            throws GeneralLedgerApiException;

    /**
     * Removes a general ledger account.
     * 
     * @param acctId
     *            The id of the account to delete.
     * @return int
     * @throws GeneralLedgerApiException
     */
    int deleteAccount(int acctId) throws GeneralLedgerApiException;

    /**
     * Removes a general ledger category.
     * 
     * @param acctId
     *            The id of the category to delete.
     * @return int
     * @throws GeneralLedgerApiException
     */
    int deleteCategory(int catgId) throws GeneralLedgerApiException;
}
