package org.dto.adapter.orm.transaction.purchaseorder;

import org.dao.mapping.orm.rmt2.PurchaseOrderStatus;
import org.dto.PurchaseOrderStatusDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table <i>purchase_order_status</i>.
 * 
 * @author rterrell
 * 
 */
class PurchaseOrderStatusRmt2OrmAdapter extends TransactionDtoImpl implements
        PurchaseOrderStatusDto {

    private PurchaseOrderStatus s;

    /**
     * Create a PurchaseOrderStatusRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected PurchaseOrderStatusRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a PurchaseOrderStatusRmt2OrmAdapter that adapts data coming from
     * the purchase_order_status table
     * 
     * @param stat
     *            an instance of {@link PurchaseOrderStatus} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected PurchaseOrderStatusRmt2OrmAdapter(PurchaseOrderStatus stat) {
        if (stat == null) {
            stat = new PurchaseOrderStatus();
        }
        this.s = stat;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusDto#setPoStatusId(int)
     */
    @Override
    public void setPoStatusId(int value) {
        this.s.setPoStatusId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusDto#getPoStatusId()
     */
    @Override
    public int getPoStatusId() {
        return this.s.getPoStatusId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.PurchaseOrderStatusDto#setPoStatusDescription(java.lang.String)
     */
    @Override
    public void setPoStatusDescription(String value) {
        this.s.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusDto#getPoStatusDescription()
     */
    @Override
    public String getPoStatusDescription() {
        return this.s.getDescription();
    }

}
