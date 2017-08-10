package org.dto.adapter.orm.account.generalledger;

import java.util.Date;

import org.dao.mapping.orm.rmt2.GlAccountCategory;
import org.dao.mapping.orm.rmt2.GlAccountTypes;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dao.mapping.orm.rmt2.VwAccount;
import org.dto.AccountCategoryDto;
import org.dto.AccountDto;
import org.dto.AccountExtDto;
import org.dto.AccountTypeDto;

import com.RMT2Constants;
import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * tables <i>gl_accounts</i>, <i>gl_account_types</i>, and
 * <i>gl_account_category</i>.
 * <p>
 * Also adapts the database view, <i>vw_accounts</i>.
 * 
 * @author rterrell
 * 
 */
class AccountRmt2OrmAdapter extends TransactionDtoImpl implements AccountDto,
        AccountTypeDto, AccountCategoryDto, AccountExtDto {

    private GlAccounts a;

    private GlAccountTypes at;

    private GlAccountCategory ac;

    private VwAccount ae;

    /**
     * Create a AccountRmt2OrmAdapter without performing any data adaptations
     */
    protected AccountRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a AccountRmt2OrmAdapter that adapts data coming from the
     * gl_accounts table
     * 
     * @param obj
     *            an instance of {@link GlAccounts}
     */
    protected AccountRmt2OrmAdapter(GlAccounts obj) {
        this.initAccount(obj);
    }

    /**
     * Create a AccountRmt2OrmAdapter that adapts data coming from the
     * gl_account_types table
     * 
     * @param obj
     *            an instance of {@link GlAccountTypes} or null when the desire
     *            arises to create a newly instantiated instance.
     */
    protected AccountRmt2OrmAdapter(GlAccountTypes obj) {
        this.initAccountType(obj);
    }

    /**
     * Create a AccountRmt2OrmAdapter that adapts data coming from the
     * gl_account_category table
     * 
     * @param obj
     *            an instance of {@link GlAccountCategory} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected AccountRmt2OrmAdapter(GlAccountCategory obj) {
        this.initAccountCategory(obj);
    }

    /**
     * Create a AccountRmt2OrmAdapter that adapts data coming from the
     * vw_accounts view
     * 
     * @param obj
     *            an instance of {@link VwAccount} or null when the desire
     *            arises to create a newly instantiated instance.
     */
    protected AccountRmt2OrmAdapter(VwAccount obj) {
        this.initAccountView(obj);
    }

    /**
     * Create a AccountRmt2OrmAdapter that adapts data coming from the instances
     * GlAccounts, GlAccountTypes, GlAccountCategory, and VwAccount.
     * 
     * @param acct
     *            an instance of {@link GlAccounts} or null.
     * @param acctType
     *            an instance of {@link GlAccountTypes} or null.
     * @param acctCatg
     *            an instance of {@link GlAccountCategory} or null.
     * @param acctVw
     *            an instance of {@link VwAccount} or null.
     */
    protected AccountRmt2OrmAdapter(GlAccounts acct, GlAccountTypes acctType,
            GlAccountCategory acctCatg, VwAccount acctVw) {
        this.initAccount(acct);
        this.initAccountType(acctType);
        this.initAccountCategory(acctCatg);
        this.initAccountView(acctVw);
        return;
    }

    /**
     * Initializes a AccountRmt2OrmAdapter that adapts data coming from the
     * gl_accounts table
     * 
     * @param obj
     *            an instance of {@link GlAccounts}
     */
    private void initAccount(GlAccounts obj) {
        if (obj == null) {
            obj = new GlAccounts();
        }
        this.a = obj;
        this.dateCreated = this.a.getDateCreated();
        this.dateUpdated = this.a.getDateUpdated();
        this.updateUserId = this.a.getUserId();
        this.at = null;
        this.ac = null;
        return;
    }

    /**
     * Initializes a AccountRmt2OrmAdapter that adapts data coming from the
     * gl_account_types table
     * 
     * @param obj
     *            an instance of {@link GlAccountTypes} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    private void initAccountType(GlAccountTypes obj) {
        if (obj == null) {
            obj = new GlAccountTypes();
        }
        this.at = obj;
        this.dateCreated = this.at.getDateCreated();
        this.dateUpdated = this.at.getDateUpdated();
        this.updateUserId = this.at.getUserId();
        this.a = null;
        this.ac = null;
        return;
    }

    /**
     * Initializes a AccountRmt2OrmAdapter that adapts data coming from the
     * gl_account_category table
     * 
     * @param obj
     *            an instance of {@link GlAccountCategory} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    private void initAccountCategory(GlAccountCategory obj) {
        if (obj == null) {
            obj = new GlAccountCategory();
        }
        this.ac = obj;
        this.dateCreated = this.ac.getDateCreated();
        this.dateUpdated = this.ac.getDateUpdated();
        this.updateUserId = this.ac.getUserId();
        this.a = null;
        this.at = null;
        return;
    }

    /**
     * Initializes a AccountRmt2OrmAdapter that adapts data coming from the
     * vw_accounts view
     * 
     * @param obj
     *            an instance of {@link VwAccount} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    private void initAccountView(VwAccount obj) {
        // Setup account view
        if (obj == null) {
            obj = new VwAccount();
        }
        this.ae = obj;

        // Setup account
        this.a = new GlAccounts();
        this.a.setAcctId(obj.getId());
        this.a.setAcctTypeId(obj.getAcctTypeId());
        this.a.setAcctCatgId(obj.getAcctCatId());
        this.a.setAcctSeq(obj.getAcctSeq());
        this.a.setName(obj.getName());
        this.a.setCode(obj.getCode());
        this.a.setDescription(obj.getDescription());
        this.a.setAcctBaltypeId(obj.getBalanceTypeId());

        // Setup account type
        this.at = new GlAccountTypes();
        this.at.setAcctTypeId(obj.getAcctTypeId());
        this.at.setDescription(obj.getAccttypedescr());
        this.at.setAcctBaltypeId(obj.getBalanceTypeId());

        // Setup account category
        this.ac = new GlAccountCategory();
        this.ac.setAcctCatgId(obj.getAcctCatId());
        this.ac.setAcctTypeId(obj.getAcctTypeId());
        this.ac.setDescription(obj.getAcctcatgdescr());

        // set timestamp values
        this.dateCreated = this.ae.getDateCreated();
        this.dateUpdated = this.ae.getDateUpdated();
        this.updateUserId = this.ae.getUserId();
        return;
    }

    /**
     * Sets the gl account id
     * 
     * @param value
     *            int
     */
    @Override
    public void setEntityId(int value) {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Returns the gl account id
     * 
     * @return int
     */
    @Override
    public int getEntityId() {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Sets the gl account name
     * 
     * @param value
     *            String
     */
    @Override
    public void setEntityName(String value) {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Returns the gl account name
     * 
     * @return String
     */
    @Override
    public String getEntityName() {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Sets the gl account id
     * 
     * @param value
     *            int
     */
    @Override
    public void setAcctId(int value) {
        this.a.setAcctId(value);
    }

    /**
     * Returns the gl account id
     * 
     * @return int
     */
    @Override
    public int getAcctId() {
        return this.a.getAcctId();
    }

    /**
     * Sets the gl account name
     * 
     * @param value
     *            String
     */
    @Override
    public void setAcctName(String value) {
        this.a.setName(value);
    }

    /**
     * Returns the gl account name
     * 
     * @return String
     */
    @Override
    public String getAcctName() {
        return this.a.getName();
    }

    /**
     * GL Account entites do not have IP address timestamp properties.
     */
    @Override
    public void setIpCreated(String value) {
        return;
    }

    /**
     * Always returns null since GL Account entites do not have IP address
     * timestamp properties.
     */
    @Override
    public String getIpCreated() {
        return null;
    }

    /**
     * GL Account entites do not have IP address timestamp properties.
     */
    @Override
    public void setIpUpdated(String value) {
        return;
    }

    /**
     * Always returns null since GL Account entites do not have IP address
     * timestamp properties.
     */
    @Override
    public String getIpUpdated() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#setAcctTypeId(int)
     */
    @Override
    public void setAcctTypeId(int value) {
        if (this.a != null) {
            this.a.setAcctTypeId(value);
        }
        else if (this.at != null) {
            this.at.setAcctTypeId(value);
        }
        else if (this.ac != null) {
            this.ac.setAcctTypeId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#getAcctTypeId()
     */
    @Override
    public int getAcctTypeId() {
        if (this.a != null) {
            return this.a.getAcctTypeId();
        }
        else if (this.at != null) {
            return this.at.getAcctTypeId();
        }
        else if (this.ac != null) {
            return this.ac.getAcctTypeId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#setAcctCatgId(int)
     */
    @Override
    public void setAcctCatgId(int value) {
        if (this.a != null) {
            this.a.setAcctCatgId(value);
        }
        else if (this.ac != null) {
            this.ac.setAcctCatgId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#getAcctCatgId()
     */
    @Override
    public int getAcctCatgId() {
        if (this.a != null) {
            return this.a.getAcctCatgId();
        }
        else if (this.ac != null) {
            return this.ac.getAcctCatgId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#setAcctSeq(int)
     */
    @Override
    public void setAcctSeq(int value) {
        this.a.setAcctSeq(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#getAcctSeq()
     */
    @Override
    public int getAcctSeq() {
        return this.a.getAcctSeq();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#setAcctNo(java.lang.String)
     */
    @Override
    public void setAcctNo(String value) {
        this.a.setAcctNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#getAcctNo()
     */
    @Override
    public String getAcctNo() {
        return this.a.getAcctNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#setAcctCode(java.lang.String)
     */
    @Override
    public void setAcctCode(String value) {
        this.a.setCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#getAcctCode()
     */
    @Override
    public String getAcctCode() {
        return this.a.getCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#setAcctDescription(java.lang.String)
     */
    @Override
    public void setAcctDescription(String value) {
        this.a.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#getAcctDescription()
     */
    @Override
    public String getAcctDescription() {
        return this.a.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#setBalanceTypeId(int)
     */
    @Override
    public void setBalanceTypeId(int value) {
        if (this.a != null) {
            this.a.setAcctBaltypeId(value);
        }
        else if (this.at != null) {
            this.at.setAcctBaltypeId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#getBalanceTypeId()
     */
    @Override
    public int getBalanceTypeId() {
        if (this.a != null) {
            return this.a.getAcctBaltypeId();
        }
        else if (this.at != null) {
            return this.at.getAcctBaltypeId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#setAcctTypeDescription(java.lang.String)
     */
    @Override
    public void setAcctTypeDescription(String value) {
        this.at.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#getAcctTypeDescription()
     */
    @Override
    public String getAcctTypeDescription() {
        return this.at.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#setAcctCatgDescription(java.lang.String)
     */
    @Override
    public void setAcctCatgDescription(String value) {
        this.ac.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AccountDto#getAcctCatgDescription()
     */
    @Override
    public String getAcctCatgDescription() {
        return this.ac.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getUpdateUserId()
     */
    @Override
    public String getUpdateUserId() {
        return super.getUpdateUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.api.foundation.TransactionDtoImpl#setUpdateUserId(java.lang.String)
     */
    @Override
    public void setUpdateUserId(String updateUserId) {
        super.setUpdateUserId(updateUserId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateCreated()
     */
    @Override
    public Date getDateCreated() {
        return super.getDateCreated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateCreated(java.util.Date)
     */
    @Override
    public void setDateCreated(Date dateCreated) {
        super.setDateCreated(dateCreated);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateUpdated()
     */
    @Override
    public Date getDateUpdated() {
        return super.getDateUpdated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateUpdated(java.util.Date)
     */
    @Override
    public void setDateUpdated(Date dateUpdated) {
        super.setDateUpdated(dateUpdated);
    }
}
