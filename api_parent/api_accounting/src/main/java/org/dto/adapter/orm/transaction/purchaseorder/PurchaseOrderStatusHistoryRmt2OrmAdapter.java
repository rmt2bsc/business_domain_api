package org.dto.adapter.orm.transaction.purchaseorder;

import java.util.Date;

import org.dao.mapping.orm.rmt2.PurchaseOrderStatus;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatusHist;
import org.dto.PurchaseOrderStatusHistDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table <i>purchase_order_status_hist</i>.
 * 
 * @author rterrell
 * 
 */
class PurchaseOrderStatusHistoryRmt2OrmAdapter extends
        PurchaseOrderStatusRmt2OrmAdapter implements PurchaseOrderStatusHistDto {

    private PurchaseOrderStatusHist h;

    /**
     * Create a PurchaseOrderStatusHistoryRmt2OrmAdapter without performing any
     * data adaptations
     */
    public PurchaseOrderStatusHistoryRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a PurchaseOrderStatusRmt2OrmAdapter that adapts data coming from
     * the purchase_order_status_hist table
     * 
     * @param hist
     *            an instance of {@link PurchaseOrderStatusHist} or null when
     *            the desired arises to create a newly instantiated instance.
     * @param stat
     *            an instance of {@link PurchaseOrderStatus} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    public PurchaseOrderStatusHistoryRmt2OrmAdapter(
            PurchaseOrderStatusHist hist, PurchaseOrderStatus stat) {
        super(stat);
        if (hist == null) {
            hist = new PurchaseOrderStatusHist();
        }
        this.h = hist;
        this.updateUserId = hist.getUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusHistDto#setPoStatusHistId(int)
     */
    @Override
    public void setPoStatusHistId(int value) {
        this.h.setPoStatusHistId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusHistDto#getPoStatusHistId()
     */
    @Override
    public int getPoStatusHistId() {
        return this.h.getPoStatusHistId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusHistDto#setPoId(int)
     */
    @Override
    public void setPoId(int value) {
        this.h.setPoId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusHistDto#getPoId()
     */
    @Override
    public int getPoId() {
        return this.h.getPoId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusHistDto#setEffectiveDate(java.util.Date)
     */
    @Override
    public void setEffectiveDate(Date value) {
        this.h.setEffectiveDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusHistDto#getEffectiveDate()
     */
    @Override
    public Date getEffectiveDate() {
        return this.h.getEffectiveDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusHistDto#setEndDate(java.util.Date)
     */
    @Override
    public void setEndDate(Date value) {
        this.h.setEndDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderStatusHistDto#getEndDate()
     */
    @Override
    public Date getEndDate() {
        return this.h.getEndDate();
    }
}
