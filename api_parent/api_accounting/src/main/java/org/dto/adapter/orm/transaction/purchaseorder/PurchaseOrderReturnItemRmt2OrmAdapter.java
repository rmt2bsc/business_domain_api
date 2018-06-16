package org.dto.adapter.orm.transaction.purchaseorder;

import org.dao.mapping.orm.rmt2.PurchaseOrderItemReturn;
import org.dto.PurchaseOrderReturnItemDto;

import com.RMT2Base;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * object <i>purchase_order_item_return</i> table.
 * 
 * @author rterrell
 * 
 */
class PurchaseOrderReturnItemRmt2OrmAdapter extends RMT2Base implements
        PurchaseOrderReturnItemDto {

    private PurchaseOrderItemReturn i;

    /**
     * Create a PurchaseOrderReturnItemRmt2OrmAdapter without performing any
     * data adaptations
     */
    protected PurchaseOrderReturnItemRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a PurchaseOrderReturnItemRmt2OrmAdapter that adapts data coming
     * from the purchase_order_item_return table.
     * 
     * @param item
     *            an instance of {@link PurchaseOrderItemReturn} or null when
     *            the desired arises to create a newly instantiated instance.
     */
    protected PurchaseOrderReturnItemRmt2OrmAdapter(PurchaseOrderItemReturn item) {
        if (item == null) {
            item = new PurchaseOrderItemReturn();
        }
        this.i = item;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnItemDto#setPoItemReturnId(int)
     */
    @Override
    public void setPoItemReturnId(int value) {
        this.i.setPoItemReturnId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnItemDto#getPoItemReturnId()
     */
    @Override
    public int getPoItemReturnId() {
        return this.i.getPoItemReturnId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnItemDto#setPoReturnId(int)
     */
    @Override
    public void setPoReturnId(int value) {
        this.i.setPoReturnId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnItemDto#getPoReturnId()
     */
    @Override
    public int getPoReturnId() {
        return this.i.getPoReturnId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnItemDto#setItemId(int)
     */
    @Override
    public void setItemId(int value) {
        this.i.setItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnItemDto#getItemId()
     */
    @Override
    public int getItemId() {
        return this.i.getItemId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnItemDto#setQtyRtn(int)
     */
    @Override
    public void setQtyRtn(int value) {
        this.i.setQtyRtn(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderReturnItemDto#getQtyRtn()
     */
    @Override
    public int getQtyRtn() {
        return this.i.getQtyRtn();
    }
}
