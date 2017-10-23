package org.dto.adapter.orm.transaction.purchases.creditor;

import org.dao.mapping.orm.rmt2.VwXactCreditChargeList;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.XactCreditChargeDto;

import com.RMT2Base;

/**
 * A factory containing several adapters which function to create credit
 * purchases related entities.
 * 
 * @author Roy Terrell.
 * 
 */
public class Rmt2CreditChargeDtoFactory extends RMT2Base {

    /**
     * Create an instance of <i>XactCreditChargeDto</i>.
     * 
     * @return an instance of {@link XactCreditChargeDto}.
     */
    public static final XactCreditChargeDto createCreditChargeInstance() {
        return new XactCreditChargeRmt2OrmAdapter();
    }

    /**
     * Create an instance of <i>XactCreditChargeDto</i>.
     * <p>
     * A brand new instance of XactCreditChargeDto is created when
     * <i>creditXactOrmBean</i> and <i>contactInfo</i> is null. Otherwise,
     * <i>creditXactOrmBean</i> and <i>contactInfo</i> is adapted to an instance
     * of XactCreditChargeDto.
     * 
     * @param creditXactOrmBean
     *            an instance of {@link VwXactCreditChargeList}
     * @param contactInfo
     *            an instance of {@link SubsidiaryContactInfoDto}
     * 
     * @return an instance of {@link XactCreditChargeDto}.
     */
    public static final XactCreditChargeDto createCreditChargeInstance(
            VwXactCreditChargeList creditXactOrmBean,
            SubsidiaryContactInfoDto contactInfo) {
        return new XactCreditChargeRmt2OrmAdapter(creditXactOrmBean,
                contactInfo);
    }

}
