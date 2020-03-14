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
import com.api.util.RMT2Date;
import com.api.util.UserTimestamp;

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
    public List<AccountDto> fetchAccount(AccountDto criteria) throws DatabaseException {
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
    public int getNextAccountSeq(AccountDto acct) throws DatabaseException {
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
                // Get next category id
                int nextSeq = this.getNextCategoryId(category);
                a.setAcctCatgId(nextSeq);
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
        this.client.insertRow(obj, false);
        return obj.getAcctCatgId();
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
    public int getNextCategoryId(AccountCategoryDto catg) {
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
}
