package org.dao.generalledger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.AccountingDaoImpl;
import org.dao.mapping.orm.rmt2.GlAccountCategory;
import org.dao.mapping.orm.rmt2.GlAccountTypes;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dao.mapping.orm.rmt2.VwAccount;
import org.dto.AccountCategoryDto;
import org.dto.AccountDto;
import org.dto.AccountExtDto;
import org.dto.AccountTypeDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;

import com.SystemException;
import com.api.persistence.CannotPersistException;
import com.api.persistence.CannotProceedException;
import com.api.persistence.CannotRemoveException;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
import com.util.RMT2Date;
import com.util.UserTimestamp;

/**
 * An RMT2 ORM implementation of the {@link GeneralLedgerDao} interface which
 * accesses general ledger account, account type, and account category data for
 * the purpose of querying, creating, modifying, and deleting GL data sets.
 * 
 * @author Roy Terrell
 * 
 */
class BasicGeneralLedgerMaintDaoImpl extends AccountingDaoImpl implements
        GeneralLedgerDao {

    /**
     * Default Constructor responsible for establishing a connection to the
     * accounting database.
     */
    protected BasicGeneralLedgerMaintDaoImpl() {
        super();
    }

    /**
     * Default Constructor responsible for establishing a connection to the
     * accounting database.
     * 
     * @param appName
     *            application name
     */
    protected BasicGeneralLedgerMaintDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Fetches a list of general ledger account records from the
     * <i>gl_accounts</i> table.
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
     *             general database access errors
     */
    @Override
    public List<AccountDto> fetchAccount(AccountDto criteria)
            throws DatabaseException {
        GlAccounts ormCriteria = new GlAccounts();
        if (criteria != null) {
            if (criteria.getAcctId() > 0) {
                ormCriteria.addCriteria(GlAccounts.PROP_ACCTID,
                        criteria.getAcctId());
            }
            if (criteria.getAcctTypeId() > 0) {
                ormCriteria.addCriteria(GlAccounts.PROP_ACCTTYPEID,
                        criteria.getAcctTypeId());
            }
            if (criteria.getAcctCatgId() > 0) {
                ormCriteria.addCriteria(GlAccounts.PROP_ACCTCATGID,
                        criteria.getAcctCatgId());
            }
            if (criteria.getAcctNo() != null) {
                ormCriteria.addLikeClause(GlAccounts.PROP_ACCTNO,
                        criteria.getAcctNo());
            }
            if (criteria.getAcctCode() != null) {
                ormCriteria.addLikeClause(GlAccounts.PROP_CODE,
                        criteria.getAcctCode());
            }
            if (criteria.getAcctName() != null) {
                ormCriteria.addLikeClause(GlAccounts.PROP_NAME,
                        criteria.getAcctName());
            }
        }

        // Retrieve data
        List<GlAccounts> results;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        // Package results into DTO
        List<AccountDto> list = new ArrayList<AccountDto>();
        for (GlAccounts item : results) {
            AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches a list of general ledger account records from the
     * <i>gl_accounts</i> table.
     * <p>
     * To select all accounts, pass <i>criteria</i> as a null value. This method
     * will use property values to build exact match predicates for queries
     * selection criteria where applicable.
     * 
     * @param criteria
     *            an instance of {@link AccountDto} to filter results or null to
     *            fetch all accounts.
     * @return A List of {@link AccountDto} or null if no data is found.
     * @throws DatabaseException
     *             general database access errors
     */
    @Override
    public List<AccountDto> fetchAccountExact(AccountDto criteria)
            throws DatabaseException {
        GlAccounts ormCriteria = new GlAccounts();
        if (criteria != null) {
            if (criteria.getAcctId() > 0) {
                ormCriteria.addCriteria(GlAccounts.PROP_ACCTID,
                        criteria.getAcctId());
            }
            if (criteria.getAcctTypeId() > 0) {
                ormCriteria.addCriteria(GlAccounts.PROP_ACCTTYPEID,
                        criteria.getAcctTypeId());
            }
            if (criteria.getAcctCatgId() > 0) {
                ormCriteria.addCriteria(GlAccounts.PROP_ACCTCATGID,
                        criteria.getAcctCatgId());
            }
            if (criteria.getAcctNo() != null) {
                ormCriteria.addCriteria(GlAccounts.PROP_ACCTNO,
                        criteria.getAcctNo());
            }
            if (criteria.getAcctCode() != null) {
                ormCriteria.addCriteria(GlAccounts.PROP_CODE,
                        criteria.getAcctCode());
            }
            if (criteria.getAcctName() != null) {
                ormCriteria.addCriteria(GlAccounts.PROP_NAME,
                        criteria.getAcctName());
            }
        }

        // Retrieve data
        List<GlAccounts> results;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        // Package results into DTO
        List<AccountDto> list = new ArrayList<AccountDto>();
        for (GlAccounts item : results) {
            AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches a list of general ledger account extension records from the
     * <i>vw_account</i> view.
     * <p>
     * To select all accounts, pass <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountExtDto} to filter results or null
     *            to fetch all accounts.
     * @return A List of {@link AccountExtDto} or null if no data is found.
     * @throws DatabaseException
     *             general database access errors
     */
    @Override
    public List<AccountExtDto> fetchAccountExt(AccountExtDto criteria)
            throws DatabaseException {
        VwAccount ormCriteria = new VwAccount();
        if (criteria != null) {
            if (criteria.getAcctId() > 0) {
                ormCriteria
                        .addCriteria(VwAccount.PROP_ID, criteria.getAcctId());
            }
            if (criteria.getAcctTypeId() > 0) {
                ormCriteria.addCriteria(VwAccount.PROP_ACCTTYPEID,
                        criteria.getAcctTypeId());
            }
            if (criteria.getAcctCatgId() > 0) {
                ormCriteria.addCriteria(VwAccount.PROP_ACCTCATID,
                        criteria.getAcctCatgId());
            }
            if (criteria.getAcctNo() != null) {
                ormCriteria.addLikeClause(VwAccount.PROP_ACCTNO,
                        criteria.getAcctNo());
            }
            if (criteria.getAcctCode() != null) {
                ormCriteria.addCriteria(VwAccount.PROP_CODE,
                        criteria.getAcctCode());
            }
            if (criteria.getAcctName() != null) {
                ormCriteria.addLikeClause(VwAccount.PROP_NAME,
                        criteria.getAcctName());
            }
            if (criteria.getAcctCatgDescription() != null) {
                ormCriteria.addLikeClause(VwAccount.PROP_ACCTCATGDESCR,
                        criteria.getAcctCatgDescription());
            }
            if (criteria.getAcctTypeDescription() != null) {
                ormCriteria.addLikeClause(VwAccount.PROP_ACCTTYPEDESCR,
                        criteria.getAcctTypeDescription());
            }
        }

        // Retrieve data
        List<VwAccount> results;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        // Package results into DTO
        List<AccountExtDto> list = new ArrayList<AccountExtDto>();
        for (VwAccount item : results) {
            AccountExtDto dto = Rmt2AccountDtoFactory
                    .createAccountExtInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches a list of general ledger account category records from the
     * <i>gl_account_category</i> table.
     * <p>
     * To select all accounts, pass <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountCategoryDto} to filter results or
     *            null to fetch all accounts.
     * @return A List of {@link AccountCategoryDto} or null if no data is found.
     * @throws DatabaseException
     *             general database access errors
     */
    @Override
    public List<AccountCategoryDto> fetchCategory(AccountCategoryDto criteria)
            throws DatabaseException {
        GlAccountCategory ormCriteria = new GlAccountCategory();
        if (criteria != null) {
            if (criteria.getAcctCatgId() > 0) {
                ormCriteria.addCriteria(GlAccountCategory.PROP_ACCTCATGID,
                        criteria.getAcctCatgId());
            }
            if (criteria.getAcctTypeId() > 0) {
                ormCriteria.addCriteria(GlAccountCategory.PROP_ACCTTYPEID,
                        criteria.getAcctTypeId());
            }
            if (criteria.getAcctCatgDescription() != null) {
                ormCriteria.addLikeClause(GlAccountCategory.PROP_DESCRIPTION,
                        criteria.getAcctCatgDescription());
            }
        }

        // Retrieve data
        List<GlAccountCategory> results;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        // Package results into DTO
        List<AccountCategoryDto> list = new ArrayList<AccountCategoryDto>();
        for (GlAccountCategory item : results) {
            AccountCategoryDto dto = Rmt2AccountDtoFactory
                    .createAccountCategoryInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches a list of general ledger account type records from the
     * <i>gl_account_types</i> table.
     * <p>
     * To select all accounts, pass <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link AccountTypeDto} to filter results or
     *            null to fetch all accounts.
     * @return A List of {@link AccountTypeDto} or null if no data is found.
     * @throws DatabaseException
     *             general database access errors
     */
    @Override
    public List<AccountTypeDto> fetchType(AccountTypeDto criteria)
            throws DatabaseException {
        GlAccountTypes ormCriteria = new GlAccountTypes();
        if (criteria != null) {
            if (criteria.getAcctTypeId() > 0) {
                ormCriteria.addCriteria(GlAccountTypes.PROP_ACCTTYPEID,
                        criteria.getAcctTypeId());
            }
            if (criteria.getAcctTypeDescription() != null) {
                ormCriteria.addLikeClause(GlAccountTypes.PROP_DESCRIPTION,
                        criteria.getAcctTypeDescription());
            }
        }

        // Retrieve data
        List<GlAccountTypes> results;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        // Package results into DTO
        List<AccountTypeDto> list = new ArrayList<AccountTypeDto>();
        for (GlAccountTypes item : results) {
            AccountTypeDto dto = Rmt2AccountDtoFactory
                    .createAccountTypeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Adds a new or modifies an existing row to the <i>gl_accouonts</i> table.
     * 
     * @param account
     *            an instance of {@link AccountDto} which contains the account
     *            changes to apply.
     * @return an int value representing either the unique identifier of the
     *         account added, or the total number of accounts effected by the
     *         update operation.
     * @throws DatabaseException
     */
    @Override
    public int maintainAccount(AccountDto account) throws DatabaseException {
        // this.validateAccount(account);
        int rc = 0;
        GeneralLedgerDaoFactory f = new GeneralLedgerDaoFactory();
        GlAccounts a = f.createRmt2OrmAccountBean(account);
        try {
            if (a.getAcctId() == 0) {
                rc = this.insertAccount(a);
                account.setAcctId(rc);
            }
            else if (a.getAcctId() > 0) {
                rc = this.updateAccount(a);
            }
            return rc;
        } catch (DatabaseException e) {
            throw new CannotPersistException(e);
        }
    }

    /**
     * Deletes a record from <i>gl_accounts</i> table based on the primary key
     * passed as <i>acctId</i>.
     * 
     * @param acctId
     *            The primary key of the record to delete.
     * @return The total number of rows effected by the transaction.
     * @throws DatabaseException
     *             record could not be deleted or general database access error.
     */
    @Override
    public int deleteAccount(int acctId) throws DatabaseException {
        GlAccounts acct = new GlAccounts();
        acct.addCriteria(GlAccounts.PROP_ACCTID, acctId);
        int rows = 0;
        try {
            rows = this.client.deleteRow(acct);
            return rows;
        } catch (DatabaseException e) {
            throw new CannotRemoveException(e);
        }
    }

    /**
     * Inserts a record into the <i>gl_accounts</i> table.
     * <p>
     * Before the actual insert operation is performed, the account number and
     * account sequence number values are automatically generated and assigned
     * to the account data object.
     * 
     * @param obj
     *            an instance of {@link GlAccounts} that is to be inserted into
     *            the database
     * @return an int value representing either the primary key of the GlAccount
     *         record just created.
     * @throws DatabaseException
     *             general database access errors or user timestamp data was not
     *             obtainable
     */
    private int insertAccount(GlAccounts obj) throws DatabaseException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateCreated(ut.getDateCreated());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
        } catch (SystemException e) {
            throw new CannotProceedException(e);
        }

        int nextSeq = this.getNextAccountSeq(obj);
        obj.setAcctSeq(nextSeq);
        String acctNo = this.buildAccountNo(obj);
        obj.setAcctNo(acctNo);
        int newId = this.client.insertRow(obj, true);
        obj.setAcctId(newId);
        return newId;
    }

    /**
     * Updates a record in the <i>gl_accounts</i> table.
     * <p>
     * Only the code, name, and descritpion columns are updated.
     * 
     * @param obj
     *            an instance of {@link GlAccounts} that is to be updated
     * @return The total number of records effected by transaction.
     * @throws DatabaseException
     *             general database access errors or user timestamp data was not
     *             obtainable
     */
    private int updateAccount(GlAccounts obj) throws DatabaseException {
        GlAccounts delta = this.getGlAccount(obj.getAcctId());
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            delta.setDateUpdated(ut.getDateCreated());
            delta.setUserId(ut.getLoginId());
        } catch (SystemException e) {
            throw new CannotProceedException(e);
        }
        delta.setAcctCatgId(obj.getAcctCatgId());
        delta.setAcctTypeId(obj.getAcctTypeId());
        delta.setCode(obj.getCode());
        delta.setName(obj.getName());
        delta.setDescription(obj.getDescription());
        delta.setAcctBaltypeId(obj.getAcctBaltypeId());
        delta.addCriteria(GlAccounts.PROP_ACCTID, obj.getAcctId());
        return this.client.updateRow(delta);
    }

    private GlAccounts getGlAccount(int acctId) throws DatabaseException {
        GlAccounts ormCriteria = new GlAccounts();
        ormCriteria.addCriteria(GlAccounts.PROP_ACCTID, acctId);
        // Retrieve data
        GlAccounts results;
        try {
            results = (GlAccounts) this.client.retrieveObject(ormCriteria);
            return results;
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }
    }

    private GlAccountCategory getGlCategory(int catgId)
            throws DatabaseException {
        GlAccountCategory ormCriteria = new GlAccountCategory();
        ormCriteria.addCriteria(GlAccountCategory.PROP_ACCTCATGID, catgId);
        // Retrieve data
        GlAccountCategory results;
        try {
            results = (GlAccountCategory) this.client
                    .retrieveObject(ormCriteria);
            return results;
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }
    }

    /**
     * Retrieves the next account sequence number for the account.
     * <p>
     * Sequence numbers of generated based on the group of GL accounts by
     * account id and account category id.
     * 
     * @param acct
     *            an instance of {@link GlAccounts}
     * @return The next sequence number.
     * @throws DatabaseException
     */
    private int getNextAccountSeq(GlAccounts acct) throws DatabaseException {
        String sql = "select max(acct_seq) next_seq from gl_accounts where acct_type_id = "
                + acct.getAcctTypeId()
                + " and acct_catg_id = "
                + acct.getAcctCatgId();
        ResultSet rs = null;
        int nextSeq = 0;
        try {
            rs = this.client.executeSql(sql);
            if (rs.next()) {
                nextSeq = rs.getInt("next_seq");
                nextSeq = (nextSeq == 0 ? 1 : ++nextSeq);
            }
            return nextSeq;
        } catch (Exception e) {
            this.msg = "Error fetching GL account next sequence number";
            throw new CannotRetrieveException(this.msg, e);
        }
    }

    /**
     * Computes a GL account number as a String using properties belonging to an
     * GlAccouonts object.
     * <p>
     * Once the account number is computed, the account number is assigned to
     * <i>obj</i>. The format of the account number goes as follows:
     * 
     * <pre>
     *   &lt;Account Type Id&gt;-&lt;Account Catgegory Id&gt;-&lt;Account Sequence Number&gt;
     * </pre
     * 
     * @param acct
     *            An instance of {@link GlAccounts}
     * @return The account number in String format.
     * @throws GeneralLedgerDaoException
     *             When the account type or account category, account sequence
     *             values are invalid.
     */
    protected String buildAccountNo(GlAccounts acct)
            throws GeneralLedgerDaoException {
        String result = "";
        String temp = "";
        int seq = acct.getAcctSeq();
        int acctType = acct.getAcctTypeId();
        int acctCat = acct.getAcctCatgId();

        // Validate Data Values
        if (acctType <= 0) {
            this.msg = "Account Number cannot be created without a valid account type code";
            throw new GeneralLedgerDaoException(this.msg);
        }
        if (acctCat <= 0) {
            this.msg = "Account Number cannot be created without a valid account category type code";
            throw new GeneralLedgerDaoException(this.msg);
        }
        if (seq <= 0) {
            this.msg = "Account Number cannot be created without a valid account sequence number";
            throw new GeneralLedgerDaoException(this.msg);
        }

        // Compute GL Account Number using the Account Type Id, Account
        // Catgegory Id, and Account Sequence Number
        result = acctType + "-" + acctCat + "-";
        if (seq >= 1 && seq <= 9) {
            temp = "00" + seq;
        }
        if (seq > 9 && seq <= 99) {
            temp = "0" + seq;
        }
        if (seq > 99 && seq <= 999) {
            temp = String.valueOf(seq);
        }
        result += temp;
        acct.setAcctNo(result);

        return result;
    }

    // /**
    // * Validates a GL Account object for data persistence.
    // * <p>
    // * Commonly, it requires values for account type, account category,
    // account
    // * code, description, name, and balance type. An existence test is
    // conducted
    // * for the account when <i>obj</i> is targeted for update. A valid account
    // * requires that the code and name do not already exist.
    // *
    // * @param acct
    // * an instance of {@link AccountDto} that will be validated
    // * @throws CannotProceedException
    // * When any validation errors occurs.
    // */
    // protected void validateAccount(AccountDto acct)
    // throws CannotProceedException {
    // if (acct == null) {
    // this.msg = "Account object cannot be null";
    // throw new CannotProceedException(this.msg);
    // }
    // List<AccountDto> old = null;
    // AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
    // if (acct.getAcctId() > 0) {
    // criteria.setAcctId(acct.getAcctId());
    // old = this.fetchAccount(criteria);
    // if (old == null) {
    // this.msg = "Account does not exist, [account id="
    // + acct.getAcctId() + "]";
    // throw new CannotProceedException(this.msg);
    // }
    // if (old.size() > 1) {
    // this.msg = "Found a database anomolly since the account id, "
    // + acct.getAcctId()
    // + ", exists multiple times in the gl_account table";
    // throw new CannotProceedException(this.msg);
    // }
    // }
    //
    // if (acct.getAcctTypeId() == 0) {
    // this.msg = "Account must be assoicated with an account type";
    // throw new CannotProceedException(this.msg);
    // }
    // if (acct.getAcctCatgId() == 0) {
    // this.msg = "Account must be assoicated with an account category";
    // throw new CannotProceedException(this.msg);
    // }
    // if (acct.getAcctName() == null
    // || acct.getAcctName().trim().length() <= 0) {
    // this.msg = "Account must be assigned a name";
    // throw new CannotProceedException(this.msg);
    // }
    // if (acct.getAcctCode() == null
    // || acct.getAcctCode().trim().length() <= 0) {
    // this.msg = "Account must have a code";
    // throw new CannotProceedException(this.msg);
    // }
    // if (acct.getAcctDescription() == null
    // || acct.getAcctDescription().trim().length() <= 0) {
    // this.msg = "Account must have a description";
    // throw new CannotProceedException(this.msg);
    // }
    // if (acct.getBalanceTypeId() == 0) {
    // this.msg = "Account must have a balance type";
    // throw new CannotProceedException(this.msg);
    // }
    //
    // // determine if GL Account Name is not Duplicated
    // if (acct.getAcctId() == 0
    // || (old != null && !acct.getAcctName().equalsIgnoreCase(
    // old.get(0).getAcctName()))) {
    // criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
    // criteria.setAcctName(acct.getAcctName());
    // old = this.fetchAccount(criteria);
    // if (old != null && old.size() > 0) {
    // this.msg = "Duplicate GL account name";
    // throw new CannotProceedException(this.msg);
    // }
    // // Determine if GL Account code is not duplicated for new accounts
    // if (acct.getAcctId() == 0
    // || (old != null && !acct.getAcctCode().equalsIgnoreCase(
    // old.get(0).getAcctCode()))) {
    // criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
    // criteria.setAcctCode(acct.getAcctCode());
    // old = this.fetchAccount(criteria);
    // if (old != null && old.size() > 0) {
    // this.msg = "Duplicate GL account code";
    // throw new CannotProceedException(this.msg);
    // }
    // }
    // }
    // return;
    // }

    // /**
    // * Validates an account type by using <i>glAcctTypeId</i> to query the
    // * database for its existence.
    // *
    // * @param glAcctTypeId
    // * The GL aacount type id.
    // * @return true when account type exists and false, otherwise.
    // * @throws CannotProceedException
    // * General database errors.
    // */
    // protected void validateAccountType(AccountTypeDto acctType)
    // throws CannotProceedException {
    // List<AccountTypeDto> old = null;
    // AccountTypeDto criteria = Rmt2AccountDtoFactory
    // .createAccountTypeInstance(null);
    // if (acctType.getAcctTypeId() > 0) {
    // criteria.setAcctTypeId(acctType.getAcctTypeId());
    // old = this.fetchType(criteria);
    // if (old == null) {
    // this.msg = "Account Type does not exist";
    // throw new CannotProceedException(this.msg);
    // }
    // }
    // String typeName = acctType.getAcctTypeDescription();
    // if (typeName == null || typeName.length() <= 0) {
    // this.msg = "Account type Description must contain a value";
    // throw new CannotProceedException(this.msg);
    // }
    // return;
    // }

    /**
     * Adds a new or modifies an existing row to the <i>gl_account_category</i>
     * table.
     * 
     * @param category
     *            an instance of {@link AccountCategoryDto} which contains the
     *            category changes to apply.
     * @return an int value representing either the unique identifier of the
     *         category added, or the total number of categories effected by the
     *         update operation.
     * @throws DatabaseException
     */
    @Override
    public int maintainCategory(AccountCategoryDto category)
            throws DatabaseException {
        // this.validateCategory(category);
        int rc = 0;
        GeneralLedgerDaoFactory f = new GeneralLedgerDaoFactory();
        GlAccountCategory a = f.createRmt2OrmCategoryBean(category);
        try {
            if (a.getAcctCatgId() == 0) {
                rc = this.insertCategory(a);
                category.setAcctCatgId(rc);
            }
            else if (a.getAcctCatgId() > 0) {
                rc = this.updateCategory(a);
            }
            return rc;
        } catch (DatabaseException e) {
            throw new CannotPersistException(e);
        }
    }

    /**
     * Inserts a record into the <i>gl_account_category</i> table.
     * 
     * @param obj
     *            an instance of {@link GlAccountCategory} that is to be
     *            inserted into the database
     * @return an int value representing either the primary key of the
     *         GlAccountCategory record just created.
     * @throws DatabaseException
     *             general database access errors or user timestamp data was not
     *             obtainable
     */
    private int insertCategory(GlAccountCategory obj) throws DatabaseException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateCreated(ut.getDateCreated());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
        } catch (SystemException e) {
            throw new CannotProceedException(e);
        }
        int newId = this.getNextCategoryId(obj);
        obj.setAcctCatgId(newId);
        this.client.insertRow(obj, false);
        obj.setAcctCatgId(newId);
        return newId;
    }

    /**
     * Retrieves the next primary key value for a category.
     * <p>
     * The next primary key is generated based on the category's account type
     * id.
     * 
     * @param acct
     *            an instance of {@link GlAccountCategory}
     * @return The next sequence number.
     * @throws DatabaseException
     */
    private int getNextCategoryId(GlAccountCategory catg)
            throws DatabaseException {
        String sql = "select max(acct_catg_id) next_seq from gl_account_category where acct_type_id = "
                + catg.getAcctTypeId();
        ResultSet rs = null;
        int nextSeq = 0;
        try {
            rs = this.client.executeSql(sql);
            if (rs.next()) {
                nextSeq = rs.getInt("next_seq");
                nextSeq = (nextSeq == 0 ? 1 : ++nextSeq);
            }
            return nextSeq;
        } catch (Exception e) {
            this.msg = "Error fetching GL account next sequence number";
            throw new CannotRetrieveException(this.msg, e);
        }
    }

    /**
     * Updates a record int the <i>gl_account_category</i> table.
     * 
     * @param obj
     *            an instance of {@link GlAccountCategory} that is to be
     *            updated.
     * @return The total number of records effected by transaction.
     * @throws DatabaseException
     *             general database access errors or user timestamp data was not
     *             obtainable
     */
    private int updateCategory(GlAccountCategory obj) throws DatabaseException {
        GlAccountCategory delta = this.getGlCategory(obj.getAcctCatgId());
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            delta.setDateUpdated(ut.getDateCreated());
            delta.setUserId(ut.getLoginId());
        } catch (SystemException e) {
            throw new CannotProceedException(e);
        }
        delta.setAcctCatgId(obj.getAcctCatgId());
        delta.setAcctTypeId(obj.getAcctTypeId());
        delta.setDescription(obj.getDescription());
        delta.addCriteria(GlAccountCategory.PROP_ACCTCATGID,
                obj.getAcctCatgId());
        return this.client.updateRow(delta);
    }

    /**
     * Deletes an account category from the database.
     * 
     * @param catgId
     *            The primary key of the account category to delete.
     * @return The total number of rows effected by the transaction.
     * @throws DatabaseException
     */
    @Override
    public int deleteCategory(int catgId) throws DatabaseException {
        GlAccountCategory catg = new GlAccountCategory();
        catg.addCriteria(GlAccountCategory.PROP_ACCTCATGID, catgId);
        int rc;
        try {
            rc = this.client.deleteRow(catg);
            return rc;
        } catch (DatabaseException e) {
            throw new CannotRemoveException(e);
        }
    }

    // /**
    // * Validates an account category object for data persistence.
    // * <p>
    // * It is required for an account category to have an account type id and a
    // * description. When <i>obj</i> is targeted for a databae update, a check
    // is
    // * performed to determine if <i>obj</i> exist in the database.
    // *
    // * @param acctCatg
    // * an instance of {@link AccountCategoryDto} which is to be
    // * validated.
    // * @throws CannotProceedException
    // * if any of the validations fail.
    // */
    // protected void validateCategory(AccountCategoryDto acctCatg)
    // throws CannotProceedException {
    // List<AccountCategoryDto> old = null;
    // AccountCategoryDto criteria = Rmt2AccountDtoFactory
    // .createAccountCategoryInstance(null);
    // if (acctCatg.getAcctCatgId() > 0) {
    // criteria.setAcctCatgId(acctCatg.getAcctCatgId());
    // old = this.fetchCategory(criteria);
    // if (old == null) {
    // this.msg = "Account Category does not exist";
    // throw new CannotProceedException(this.msg);
    // }
    // }
    // if (acctCatg.getAcctTypeId() <= 0) {
    // this.msg = "Account Category account type is invalid";
    // throw new CannotProceedException(this.msg);
    // }
    //
    // String catgName = acctCatg.getAcctCatgDescription();
    // if (catgName == null || catgName.length() <= 0) {
    // this.msg = "Account Category Name must contain a value";
    // throw new CannotProceedException(this.msg);
    // }
    // return;
    // }

}
