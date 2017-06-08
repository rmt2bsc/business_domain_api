package org.modules.generalledger;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.generalledger.GeneralLedgerDao;
import org.dao.generalledger.GeneralLedgerDaoFactory;
import org.dto.AccountCategoryDto;
import org.dto.AccountDto;
import org.dto.AccountTypeDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;
import org.modules.TooManyItemsReturnedApiException;

import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.CannotProceedException;

/**
 * 
 * @author Roy Terrell
 * 
 */
class BasicAccountMaintApiImpl extends AbstractTransactionApiImpl implements
        GlAccountApi {

    private static final Logger logger = Logger
            .getLogger(BasicAccountMaintApiImpl.class);

    private GeneralLedgerDaoFactory factory;

    private String appName;

    /**
     * Creates a BasicAccountMaintApiImpl object assoicated with the default
     * GeneralLedgerDaoFactory object.
     */
    protected BasicAccountMaintApiImpl() {
        this.factory = new GeneralLedgerDaoFactory();
        this.appName = null;
    }

    /**
     * Creates a BasicAccountMaintApiImpl object assoicated with the default
     * GeneralLedgerDaoFactory object.
     */
    protected BasicAccountMaintApiImpl(String appName) {
        this.factory = new GeneralLedgerDaoFactory();
        this.appName = appName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.generalledger.GlAccountApi#getAccount(org.dto.AccountDto)
     */
    @Override
    public List<AccountDto> getAccount(AccountDto criteria)
            throws GeneralLedgerApiException {
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            List<AccountDto> results = dao.fetchAccount(criteria);
            logger.info("Total number of GL Account objects obtained: "
                    + results.size());
            return results;
        } catch (Exception e) {
            this.msg = "Error occurred retrieving GL Account objects";
            logger.error(this.msg, e);
            throw new GeneralLedgerApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.generalledger.GlAccountApi#getAccount(int)
     */
    @Override
    public AccountDto getAccount(int accountId)
            throws GeneralLedgerApiException {
        StringBuffer msgBuf = new StringBuffer();
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            AccountDto criteria = Rmt2AccountDtoFactory
                    .createAccountInstance(null);
            criteria.setAcctId(accountId);
            List<AccountDto> results = dao.fetchAccount(criteria);
            if (results == null) {
                msgBuf.append("General ledger account object, ");
                msgBuf.append(accountId);
                msgBuf.append(", was not found ");
                logger.warn(msgBuf);
                return null;
            }
            if (results.size() > 1) {
                msgBuf.append("Too many General ledger account objects [");
                msgBuf.append(results.size());
                msgBuf.append("] were retrieved when only one object is expected to be returned");
                logger.error(msgBuf);
                throw new TooManyItemsReturnedApiException(msgBuf.toString());
            }
            msgBuf.append("General ledger account object, ");
            msgBuf.append(accountId);
            msgBuf.append(", was retrieved ");
            logger.info(msgBuf);
            return results.get(0);
        } catch (Exception e) {
            this.msg = "Error occurred retrieving GL Account objects";
            logger.error(this.msg, e);
            throw new GeneralLedgerApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.generalledger.GlAccountApi#getAccountByExactName(java.lang
     * .String)
     */
    @Override
    public AccountDto getAccountByExactName(String accountName)
            throws GeneralLedgerApiException {
        StringBuffer msgBuf = new StringBuffer();
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            AccountDto criteria = Rmt2AccountDtoFactory
                    .createAccountInstance(null);
            criteria.setAcctName(accountName);
            List<AccountDto> results = dao.fetchAccountExact(criteria);
            if (results == null) {
                msgBuf.append("General ledger account name, ");
                msgBuf.append(accountName);
                msgBuf.append(", was not found ");
                logger.warn(msgBuf);
                return null;
            }
            if (results.size() > 1) {
                msgBuf.append("Too many General ledger account objects [");
                msgBuf.append(results.size());
                msgBuf.append("] were retrieved when only one object is expected to be returned");
                logger.error(msgBuf);
                throw new GeneralLedgerApiException(msgBuf.toString());
            }
            msgBuf.append("General ledger account name, ");
            msgBuf.append(accountName);
            msgBuf.append(", was retrieved ");
            logger.info(msgBuf);
            return results.get(0);
        } catch (Exception e) {
            this.msg = "Error occurred retrieving GL Account objects";
            logger.error(this.msg, e);
            throw new GeneralLedgerApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.generalledger.GlAccountApi#getAccountType(org.dto.AccountTypeDto
     * )
     */
    @Override
    public List<AccountTypeDto> getAccountType(AccountTypeDto criteria)
            throws GeneralLedgerApiException {
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            List<AccountTypeDto> results = dao.fetchType(criteria);
            logger.info("Total number of GL Account Type objects obtained: "
                    + results.size());
            return results;
        } catch (Exception e) {
            this.msg = "Error occurred retrieving GL Account Type objects";
            logger.error(this.msg, e);
            throw new GeneralLedgerApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.generalledger.GlAccountApi#getAccountType(int)
     */
    @Override
    public AccountTypeDto getAccountType(int accountTypeId)
            throws GeneralLedgerApiException {
        StringBuffer msgBuf = new StringBuffer();
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            AccountTypeDto criteria = Rmt2AccountDtoFactory
                    .createAccountTypeInstance(null);
            criteria.setAcctTypeId(accountTypeId);
            List<AccountTypeDto> results = dao.fetchType(criteria);
            if (results == null) {
                msgBuf.append("General ledger account type object, ");
                msgBuf.append(accountTypeId);
                msgBuf.append(", was not found ");
                logger.warn(msgBuf);
                return null;
            }
            if (results.size() > 1) {
                msgBuf.append("Too many General ledger account type objects [");
                msgBuf.append(results.size());
                msgBuf.append("] were retrieved when only one object is expected to be returned");
                logger.error(msgBuf);
                throw new GeneralLedgerApiException(msgBuf.toString());
            }
            msgBuf.append("General ledger account type object, ");
            msgBuf.append(accountTypeId);
            msgBuf.append(", was retrieved ");
            logger.info(msgBuf);
            return results.get(0);
        } catch (Exception e) {
            this.msg = "Error occurred retrieving GL Account Type objects";
            logger.error(this.msg, e);
            throw new GeneralLedgerApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.generalledger.GlAccountApi#getAccountCategory(org.dto.
     * AccountCategoryDto)
     */
    @Override
    public List<AccountCategoryDto> getAccountCategory(
            AccountCategoryDto criteria) throws GeneralLedgerApiException {
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            List<AccountCategoryDto> results = dao.fetchCategory(criteria);
            logger.info("Total number of GL Account Category objects obtained: "
                    + results.size());
            return results;
        } catch (Exception e) {
            this.msg = "Error occurred retrieving GL Account Category objects";
            logger.error(this.msg, e);
            throw new GeneralLedgerApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.generalledger.GlAccountApi#getAccountCategory(int)
     */
    @Override
    public AccountCategoryDto getAccountCategory(int accountCategoryId)
            throws GeneralLedgerApiException {
        StringBuffer msgBuf = new StringBuffer();
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            AccountCategoryDto criteria = Rmt2AccountDtoFactory
                    .createAccountCategoryInstance(null);
            criteria.setAcctCatgId(accountCategoryId);
            List<AccountCategoryDto> results = dao.fetchCategory(criteria);
            if (results == null) {
                msgBuf.append("General ledger account category object, ");
                msgBuf.append(accountCategoryId);
                msgBuf.append(", was not found ");
                logger.warn(msgBuf);
                return null;
            }
            if (results.size() > 1) {
                msgBuf.append("Too many General ledger account category objects [");
                msgBuf.append(results.size());
                msgBuf.append("] were retrieved when only one object is expected to be returned");
                logger.error(msgBuf);
                throw new GeneralLedgerApiException(msgBuf.toString());
            }
            msgBuf.append("General ledger account type object, ");
            msgBuf.append(accountCategoryId);
            msgBuf.append(", was retrieved ");
            logger.info(msgBuf);
            return results.get(0);
        } catch (Exception e) {
            this.msg = "Error occurred retrieving GL Account Category objects";
            logger.error(this.msg, e);
            throw new GeneralLedgerApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.generalledger.GlAccountApi#updateAccount(org.dto.AccountDto)
     */
    @Override
    public int updateAccount(AccountDto account)
            throws GeneralLedgerApiException {
        this.validateAccount(account);
        int rc = 0;
        StringBuffer msgBuf = new StringBuffer();
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            dao.beginTrans();
            rc = dao.maintainAccount(account);
            dao.commitTrans();
            msgBuf.append("GL Account, ");
            msgBuf.append(account.getAcctName());
            msgBuf.append(", was updated successfully");
            logger.info(msgBuf);
            return rc;
        } catch (Exception e) {
            dao.rollbackTrans();
            msgBuf.append("Error occurred updating GL Account, ");
            msgBuf.append(account.getAcctName());
            logger.error(msgBuf, e);
            throw new GeneralLedgerApiException(msgBuf.toString(), e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.generalledger.GlAccountApi#updateCategory(org.dto.
     * AccountCategoryDto)
     */
    @Override
    public int updateCategory(AccountCategoryDto category)
            throws GeneralLedgerApiException {
        this.validateCategory(category);
        int rc = 0;
        StringBuffer msgBuf = new StringBuffer();
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            dao.beginTrans();
            rc = dao.maintainCategory(category);
            dao.commitTrans();
            msgBuf.append("GL Account Category, ");
            msgBuf.append(category.getAcctCatgDescription());
            msgBuf.append(", was updated successfully");
            logger.info(msgBuf);
            return rc;
        } catch (Exception e) {
            dao.rollbackTrans();
            msgBuf.append("Error occurred updating GL Account Category, ");
            msgBuf.append(category.getAcctCatgDescription());
            logger.error(msgBuf, e);
            throw new GeneralLedgerApiException(msgBuf.toString(), e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.generalledger.GlAccountApi#deleteAccount(int)
     */
    @Override
    public int deleteAccount(int acctId) throws GeneralLedgerApiException {
        int rc = 0;
        StringBuffer msgBuf = new StringBuffer();
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            dao.beginTrans();
            rc = dao.deleteAccount(acctId);
            dao.commitTrans();
            msgBuf.append("GL Account, ");
            msgBuf.append(acctId);
            msgBuf.append(", was removed successfully");
            logger.info(msgBuf);
            return rc;
        } catch (Exception e) {
            dao.rollbackTrans();
            msgBuf.append("Error occurred removing GL Account, ");
            msgBuf.append(acctId);
            logger.error(msgBuf, e);
            throw new GeneralLedgerApiException(msgBuf.toString(), e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.generalledger.GlAccountApi#deleteCategory(int)
     */
    @Override
    public int deleteCategory(int catgId) throws GeneralLedgerApiException {
        int rc = 0;
        StringBuffer msgBuf = new StringBuffer();
        GeneralLedgerDao dao = this.factory.createRmt2OrmDao(this.appName);
        dao.setDaoUser(this.apiUser);
        try {
            dao.beginTrans();
            rc = dao.deleteCategory(catgId);
            dao.commitTrans();
            msgBuf.append("GL Account Category, ");
            msgBuf.append(catgId);
            msgBuf.append(", was removed successfully");
            logger.info(msgBuf);
            return rc;
        } catch (Exception e) {
            dao.rollbackTrans();
            msgBuf.append("Error occurred removing GL Account Category, ");
            msgBuf.append(catgId);
            logger.error(msgBuf, e);
            throw new GeneralLedgerApiException(msgBuf.toString(), e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Validates a GL Account object for data persistence.
     * <p>
     * Commonly, it requires values for account type, account category, account
     * code, description, name, and balance type. An existence test is conducted
     * for the account when <i>obj</i> is targeted for update. A valid account
     * requires that the code and name do not already exist.
     * 
     * @param acct
     *            an instance of {@link AccountDto} that will be validated
     * @throws CannotProceedException
     *             When any validation errors occurs.
     * @throws GeneralLedgerApiException
     *             General API access errors
     */
    protected void validateAccount(AccountDto acct)
            throws CannotProceedException, GeneralLedgerApiException {
        if (acct == null) {
            this.msg = "Account object cannot be null";
            throw new CannotProceedException(this.msg);
        }
        List<AccountDto> old = null;
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        if (acct.getAcctId() > 0) {
            criteria.setAcctId(acct.getAcctId());
            old = this.getAccount(criteria);
            if (old == null) {
                this.msg = "Account does not exist, [account id="
                        + acct.getAcctId() + "]";
                throw new CannotProceedException(this.msg);
            }
            if (old.size() > 1) {
                this.msg = "Found a database anomolly since the account id, "
                        + acct.getAcctId()
                        + ", exists multiple times in the gl_account table";
                throw new CannotProceedException(this.msg);
            }
        }

        if (acct.getAcctTypeId() == 0) {
            this.msg = "Account must be assoicated with an account type";
            throw new CannotProceedException(this.msg);
        }
        if (acct.getAcctCatgId() == 0) {
            this.msg = "Account must be assoicated with an account category";
            throw new CannotProceedException(this.msg);
        }
        if (acct.getAcctName() == null
                || acct.getAcctName().trim().length() <= 0) {
            this.msg = "Account must be assigned a name";
            throw new CannotProceedException(this.msg);
        }
        if (acct.getAcctCode() == null
                || acct.getAcctCode().trim().length() <= 0) {
            this.msg = "Account must have a code";
            throw new CannotProceedException(this.msg);
        }
        if (acct.getAcctDescription() == null
                || acct.getAcctDescription().trim().length() <= 0) {
            this.msg = "Account must have a description";
            throw new CannotProceedException(this.msg);
        }
        if (acct.getBalanceTypeId() == 0) {
            this.msg = "Account must have a balance type";
            throw new CannotProceedException(this.msg);
        }

        // determine if GL Account Name is not Duplicated
        if (acct.getAcctId() == 0
                || (old != null && !acct.getAcctName().equalsIgnoreCase(
                        old.get(0).getAcctName()))) {
            criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
            criteria.setAcctName(acct.getAcctName());
            old = this.getAccount(criteria);
            if (old != null && old.size() > 0) {
                this.msg = "Duplicate GL account name";
                throw new CannotProceedException(this.msg);
            }
            // Determine if GL Account code is not duplicated for new accounts
            if (acct.getAcctId() == 0
                    || (old != null && !acct.getAcctCode().equalsIgnoreCase(
                            old.get(0).getAcctCode()))) {
                criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
                criteria.setAcctCode(acct.getAcctCode());
                old = this.getAccount(criteria);
                if (old != null && old.size() > 0) {
                    this.msg = "Duplicate GL account code";
                    throw new CannotProceedException(this.msg);
                }
            }
        }
        return;
    }

    /**
     * Validates an account category object for data persistence.
     * <p>
     * It is required for an account category to have an account type id and a
     * description. When <i>obj</i> is targeted for a databae update, a check is
     * performed to determine if <i>obj</i> exist in the database.
     * 
     * @param acctCatg
     *            an instance of {@link AccountCategoryDto} which is to be
     *            validated.
     * @throws CannotProceedException
     *             if any of the validations fail.
     * @throws GeneralLedgerApiException
     *             General API access errors
     */
    protected void validateCategory(AccountCategoryDto acctCatg)
            throws CannotProceedException, GeneralLedgerApiException {
        List<AccountCategoryDto> old = null;
        AccountCategoryDto criteria = Rmt2AccountDtoFactory
                .createAccountCategoryInstance(null);
        if (acctCatg.getAcctCatgId() > 0) {
            criteria.setAcctCatgId(acctCatg.getAcctCatgId());
            old = this.getAccountCategory(criteria);
            if (old == null) {
                this.msg = "Account Category does not exist";
                throw new CannotProceedException(this.msg);
            }
        }
        if (acctCatg.getAcctTypeId() <= 0) {
            this.msg = "Account Category account type is invalid";
            throw new CannotProceedException(this.msg);
        }

        String catgName = acctCatg.getAcctCatgDescription();
        if (catgName == null || catgName.length() <= 0) {
            this.msg = "Account Category Name must contain a value";
            throw new CannotProceedException(this.msg);
        }
        return;
    }

    /**
     * Validates an account type by using <i>glAcctTypeId</i> to query the
     * database for its existence.
     * 
     * @param glAcctTypeId
     *            The GL aacount type id.
     * @return true when account type exists and false, otherwise.
     * @throws CannotProceedException
     *             General database errors.
     * @throws GeneralLedgerApiException
     *             General API access errors
     */
    protected void validateAccountType(AccountTypeDto acctType)
            throws CannotProceedException, GeneralLedgerApiException {
        List<AccountTypeDto> old = null;
        AccountTypeDto criteria = Rmt2AccountDtoFactory
                .createAccountTypeInstance(null);
        if (acctType.getAcctTypeId() > 0) {
            criteria.setAcctTypeId(acctType.getAcctTypeId());
            old = this.getAccountType(criteria);
            if (old == null) {
                this.msg = "Account Type does not exist";
                throw new CannotProceedException(this.msg);
            }
        }
        String typeName = acctType.getAcctTypeDescription();
        if (typeName == null || typeName.length() <= 0) {
            this.msg = "Account type Description must contain a value";
            throw new CannotProceedException(this.msg);
        }
        return;
    }

}
