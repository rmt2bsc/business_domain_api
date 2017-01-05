package org.dto.adapter.orm.account.generalledger;

import org.dao.mapping.orm.rmt2.GlAccountCategory;
import org.dao.mapping.orm.rmt2.GlAccountTypes;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dao.mapping.orm.rmt2.VwAccount;
import org.dto.AccountCategoryDto;
import org.dto.AccountDto;
import org.dto.AccountExtDto;
import org.dto.AccountTypeDto;

import com.RMT2Base;

/**
 * A factory containing several adapters which function to create various
 * objects related to the GL Account maintenance.
 * 
 * @author Roy Terrell.
 * 
 */
public class Rmt2AccountDtoFactory extends RMT2Base {

    /**
     * Create an instance of <i>AccountDto</i>.
     * <p>
     * A brand new instance of AccountDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of AccountDto.
     * 
     * @param ormBean
     *            an instance of {@link GlAccounts}
     * @return an instance of {@link AccountDto}.
     */
    public static final AccountDto createAccountInstance(GlAccounts ormBean) {
        return new AccountRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>AccountTypeDto</i>.
     * <p>
     * A brand new instance of AccountDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * AccountTypeDto.
     * 
     * @param ormBean
     *            an instance of {@link GlAccountTypes}
     * @return an instance of {@link AccountTypeDto}.
     */
    public static final AccountTypeDto createAccountTypeInstance(
            GlAccountTypes ormBean) {
        return new AccountRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>AccountCategoryDto</i>.
     * <p>
     * A brand new instance of AccountCategoryDto is created when <i>ormBean</i>
     * is null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * AccountCategoryDto.
     * 
     * @param ormBean
     *            an instance of {@link GlAccountCategory}
     * @return an instance of {@link AccountCategoryDto}.
     */
    public static final AccountCategoryDto createAccountCategoryInstance(
            GlAccountCategory ormBean) {
        return new AccountRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>AccountExtDto</i>.
     * <p>
     * A brand new instance of AccountExtDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * AccountExtDto.
     * 
     * @param ormBean
     *            an instance of {@link VwAccount}
     * @return an instance of {@link AccountExtDto}.
     */
    public static final AccountExtDto createAccountExtInstance(VwAccount ormBean) {
        return new AccountRmt2OrmAdapter(ormBean);
    }
}
