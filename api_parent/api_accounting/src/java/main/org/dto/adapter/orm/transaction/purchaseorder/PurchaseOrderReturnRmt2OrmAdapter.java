package org.dto.adapter.orm.transaction.purchaseorder;

import org.dao.mapping.orm.rmt2.PurchaseOrderReturn;
import org.dto.PurchaseOrderReturnDto;

import com.RMT2Base;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * object <i>purchase_order_return</i> table.
 * 
 * @author rterrell
 * 
 */
class PurchaseOrderReturnRmt2OrmAdapter extends RMT2Base implements
        PurchaseOrderReturnDto {

    private PurchaseOrderReturn por;

    /**
     * Create a PurchaseOrderReturnRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected PurchaseOrderReturnRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a PurchaseOrderReturnRmt2OrmAdapter that adapts data coming from
     * the purchase_order_return.
     * 
     * @param r
     *            an instance of {@link PurchaseOrderReturn} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected PurchaseOrderReturnRmt2OrmAdapter(PurchaseOrderReturn r) {
        if (r == null) {
            r = new PurchaseOrderReturn();
        }
        this.por = r;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnDto#setPoReturnId(int)
     */
    @Override
    public void setPoReturnId(int value) {
        this.por.setPoReturnId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnDto#getPoReturnId()
     */
    @Override
    public int getPoReturnId() {
        return this.por.getPoReturnId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnDto#setPoId(int)
     */
    @Override
    public void setPoId(int value) {
        this.por.setPoId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnDto#getPoId()
     */
    @Override
    public int getPoId() {
        return this.por.getPoId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnDto#setXactId(int)
     */
    @Override
    public void setXactId(int value) {
        this.por.setXactId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnDto#getXactId()
     */
    @Override
    public int getXactId() {
        return this.por.getXactId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnDto#setReason(java.lang.String)
     */
    @Override
    public void setReason(String value) {
        this.por.setReason(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnDto#getReason()
     */
    @Override
    public String getReason() {
        return this.por.getReason();
    }
}
