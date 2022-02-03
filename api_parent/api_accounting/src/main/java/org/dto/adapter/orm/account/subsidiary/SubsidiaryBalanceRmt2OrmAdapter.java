package org.dto.adapter.orm.account.subsidiary;

import org.dao.mapping.orm.rmt2.VwCustomerBalance;
import org.dto.SubsidiaryBalanceDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the
 * database view, <i>VwCustomerBalance</i>.
 * 
 * @author rterrell
 * 
 */
class SubsidiaryBalanceRmt2OrmAdapter implements SubsidiaryBalanceDto {

    private VwCustomerBalance cust;

    /**
     * Create a SubsidiaryBalanceRmt2OrmAdapter that adapts data pertaining to the customer's
     * balance.
     * <p>
     * This adapter can be expanded to manage creditor balances as well.
     * 
     * @param entity
     *            an instance of {@link VwCustomerBalance} or null when the desire arises
     *            to create a newly instantiated object.
     */
    protected SubsidiaryBalanceRmt2OrmAdapter(VwCustomerBalance entity) {
        if (entity == null) {
            this.cust = new VwCustomerBalance();
        }
        this.cust = entity;
        
        return;
    }

    /**
     * Set customer id
     */
    @Override
    public void setSubsidiaryId(int value) {
        this.cust.setCustomerId(value);
    }

    /**
     * Get customer id
     */
    @Override
    public int getSubsidiaryId() {
        return this.cust.getCustomerId();
    }


    @Override
    public void setBalance(double value) {
        this.cust.setBalance(value);
    }

    @Override
    public double getBalance() {
        return this.cust.getBalance();
    }

}
