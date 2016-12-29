package org.dao.generalledger;

import org.dao.mapping.orm.rmt2.GlAccountCategory;
import org.dao.mapping.orm.rmt2.GlAccountTypes;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dto.AccountCategoryDto;
import org.dto.AccountDto;
import org.dto.AccountTypeDto;

import com.RMT2Base;

/**
 * Factory class for creating general ledger DAO objects.
 * 
 * @author Roy Terrell
 * 
 */
public class GeneralLedgerDaoFactory extends RMT2Base {

    /**
     * Default constructory
     */
    public GeneralLedgerDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>GeneralLedgerDao</i> using the RMT2 ORm basic
     * maintenance implementation.
     * 
     * @return an instance of {@link GeneralLedgerDao}
     */
    public GeneralLedgerDao createRmt2OrmDao() {
        GeneralLedgerDao dao = new BasicGeneralLedgerMaintDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>GeneralLedgerDao</i> using the RMT2 ORm basic
     * maintenance implementation.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link GeneralLedgerDao}
     */
    public GeneralLedgerDao createRmt2OrmDao(String appName) {
        GeneralLedgerDao dao = new BasicGeneralLedgerMaintDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a GlAccount object using an instance of AccountDto as the source
     * of data.
     * 
     * @param dto
     *            an instance of {@link AccountDto}
     * @return an instance of {@link GlAccounts}
     */
    public GlAccounts createRmt2OrmAccountBean(AccountDto dto) {
        if (dto == null) {
            return null;
        }

        GlAccounts a = new GlAccounts();
        a.setAcctId(dto.getAcctId());
        a.setAcctTypeId(dto.getAcctTypeId());
        a.setAcctCatgId(dto.getAcctCatgId());
        a.setAcctSeq(dto.getAcctSeq());
        a.setAcctNo(dto.getAcctNo());
        a.setName(dto.getAcctName());
        a.setCode(dto.getAcctCode());
        a.setDescription(dto.getAcctDescription());
        a.setAcctBaltypeId(dto.getBalanceTypeId());
        a.setDateCreated(dto.getDateCreated());
        a.setDateUpdated(dto.getDateUpdated());
        a.setUserId(dto.getUpdateUserId());
        return a;
    }

    /**
     * Creates a GlAccountCategory object using an instance of
     * AccountCategoryDto as the source of data.
     * 
     * @param dto
     *            an instance of {@link AccountCategoryDto}
     * @return an instance of {@link GlAccountCategory}
     */
    public GlAccountCategory createRmt2OrmCategoryBean(AccountCategoryDto dto) {
        if (dto == null) {
            return null;
        }

        GlAccountCategory a = new GlAccountCategory();
        a.setAcctCatgId(dto.getAcctCatgId());
        a.setAcctTypeId(dto.getAcctTypeId());
        a.setDescription(dto.getAcctCatgDescription());
        a.setDateCreated(dto.getDateCreated());
        a.setDateUpdated(dto.getDateUpdated());
        a.setUserId(dto.getUpdateUserId());
        return a;
    }

    /**
     * Creates a GlAccountTypes object using an instance of AccountTypeDto as
     * the source of data.
     * 
     * @param dto
     *            an instance of {@link AccountTypeDto}
     * @return an instance of {@link GlAccountTypes}
     */
    public GlAccountTypes createRmt2OrmAccountTypeBean(AccountTypeDto dto) {
        if (dto == null) {
            return null;
        }

        GlAccountTypes a = new GlAccountTypes();
        a.setAcctTypeId(dto.getAcctTypeId());
        a.setDescription(dto.getAcctTypeDescription());
        a.setAcctBaltypeId(dto.getBalanceTypeId());
        a.setDateCreated(dto.getDateCreated());
        a.setDateUpdated(dto.getDateUpdated());
        a.setUserId(dto.getUpdateUserId());
        return a;
    }
}
