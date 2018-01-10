package org.dto.adapter.orm.transaction.purchaseorder;

import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.PurchaseOrderItems;
import org.dao.mapping.orm.rmt2.VwVendorItemPurchaseOrderItem;
import org.dto.PurchaseOrderItemDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * objects <i>purchase_order_items</i> table and
 * <i>vw_vendor_item_purchase_order_item</i> view.
 * 
 * @author Roy Terrell
 * 
 */
class PurchaseOrderItemRmt2OrmAdapter extends PurchaseOrderRmt2OrmAdapter
        implements PurchaseOrderItemDto {

    private PurchaseOrderItems i;

    private VwVendorItemPurchaseOrderItem vipo;

    /**
     * Create a PurchaseOrderItemRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected PurchaseOrderItemRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a PurchaseOrderItemRmt2OrmAdapter that adapts data coming from the
     * purchase_order_items table.
     * 
     * @param item
     *            an instance of {@link PurchaseOrderItems} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected PurchaseOrderItemRmt2OrmAdapter(PurchaseOrderItems item) {
        this(item, null);
        return;
    }

    /**
     * Create a PurchaseOrderItemRmt2OrmAdapter that adapts data coming from the
     * purchase_order_items and purchase_order tables.
     * 
     * @param item
     *            an instance of {@link PurchaseOrderItems} or null when the
     *            desired arises to create a newly instantiated instance.
     * @param po
     *            an instance of {@link PurchaseOrder} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected PurchaseOrderItemRmt2OrmAdapter(PurchaseOrderItems item, PurchaseOrder po) {
        super(po);
        if (item == null) {
            item = new PurchaseOrderItems();
        }
        this.i = item;
        this.dateCreated = item.getDateCreated();
        this.dateUpdated = item.getDateUpdated();
        this.updateUserId = item.getUserId();
        return;
    }

    /**
     * Create a PurchaseOrderItemRmt2OrmAdapter that adapts data coming from the
     * vw_vendor_item_purchase_order_item view.
     * 
     * @param vipo
     *            an instance of {@link VwVendorItemPurchaseOrderItem} or null
     *            when the desired arises to create a newly instantiated
     *            instance.
     */
    protected PurchaseOrderItemRmt2OrmAdapter(VwVendorItemPurchaseOrderItem vi) {
        this();
        if (vi == null) {
            vi = new VwVendorItemPurchaseOrderItem();
        }
        this.i = null;
        this.vipo = vi;
        return;
    }
    
    /**
     * Create a PurchaseOrderItemRmt2OrmAdapter that adapts data coming from the
     * purchase_order_items and purchase_order tables and the
     * vw_vendor_item_purchase_order_item view.
     * 
     * @param item
     *            an instance of {@link PurchaseOrderItems} or null when the
     *            desired arises to create a newly instantiated instance.
     * @param vipo
     *            an instance of {@link VwVendorItemPurchaseOrderItem} or null
     *            when the desired arises to create a newly instantiated
     *            instance.
     * @param po
     *            an instance of {@link PurchaseOrder} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected PurchaseOrderItemRmt2OrmAdapter(PurchaseOrderItems item,
            VwVendorItemPurchaseOrderItem vi, PurchaseOrder po) {
        super(po);
        if (item == null) {
            item = new PurchaseOrderItems();
        }
        if (vi == null) {
            vi = new VwVendorItemPurchaseOrderItem();
        }
        this.i = item;
        this.vipo = vi;
        this.dateCreated = item.getDateCreated();
        this.dateUpdated = item.getDateUpdated();
        this.updateUserId = item.getUserId();
        return;
    }

    /* (non-Javadoc)
     * @see org.dto.PurchaseOrderItemDto#setPoItemId(int)
     */
    @Override
    public void setPoItemId(int value) {
        if (this.i != null) {
            this.i.setPoItemId(value);    
        }
    }

    /* (non-Javadoc)
     * @see org.dto.PurchaseOrderItemDto#getPoItemId()
     */
    @Override
    public int getPoItemId() {
        if (this.i != null) {
            return this.i.getPoItemId();    
        }
        return 0;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setItemId(int)
     */
    @Override
    public void setItemId(int value) {
        if (this.i != null) {
            this.i.setItemId(value);    
        } else if (this.vipo != null) {
            this.vipo.setItemId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getItemId()
     */
    @Override
    public int getItemId() {
        if (this.i != null) {
            return this.i.getItemId();    
        } else if (this.vipo != null) {
            return this.vipo.getItemId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setActualUnitCost(double)
     */
    @Override
    public void setActualUnitCost(double value) {
        if (this.i != null) {
            this.i.setUnitCost(value);    
        } else if (this.vipo != null) {
            this.vipo.setUnitCost(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getActualUnitCost()
     */
    @Override
    public double getActualUnitCost() {
        if (this.i != null) {
            return this.i.getUnitCost();    
        } else if (this.vipo != null) {
            return this.vipo.getUnitCost();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setQtyOrdered(int)
     */
    @Override
    public void setQtyOrdered(int value) {
        if (this.i != null) {
            this.i.setQty(value);  
        } else if (this.vipo != null) {
            this.vipo.setQtyOrderd(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getQtyOrdered()
     */
    @Override
    public int getQtyOrdered() {
        if (this.i != null) {
            return this.i.getQty();            
        } else if (this.vipo != null) {
            return this.vipo.getQtyOrderd();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setQtyRcvd(int)
     */
    @Override
    public void setQtyRcvd(int value) {
        if (this.i != null) {
            this.i.setQtyRcvd(value);    
        } else if (this.vipo != null) {
            this.vipo.setQtyReceived(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getQtyRcvd()
     */
    @Override
    public int getQtyRcvd() {
        if (this.i != null) {
            return this.i.getQtyRcvd();            
        } else if (this.vipo != null) {
            return this.vipo.getQtyReceived();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setQtyRtn(int)
     */
    @Override
    public void setQtyRtn(int value) {
        if (this.i != null) {
            this.i.setQtyRtn(value);    
        } else if (this.vipo != null) {
            this.vipo.setQtyReturned(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getQtyRtn()
     */
    @Override
    public int getQtyRtn() {
        if (this.i != null) {
            return this.i.getQtyRtn();            
        } else if (this.vipo != null) {
            return this.vipo.getQtyReturned();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setVendorItemNo(java.lang.String)
     */
    @Override
    public void setVendorItemNo(String value) {
        if (vipo != null) {
            this.vipo.setVendorItemNo(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorItemNo()
     */
    @Override
    public String getVendorItemNo() {
        if (vipo != null) {
            return this.vipo.getVendorItemNo();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setVendorItemSerialNo(java.lang.String)
     */
    @Override
    public void setVendorItemSerialNo(String value) {
        if (vipo != null) {
            this.vipo.setItemSerialNo(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorItemSerialNo()
     */
    @Override
    public String getVendorItemSerialNo() {
        if (vipo != null) {
            return this.vipo.getItemSerialNo();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setVendorQtyOnHand(int)
     */
    @Override
    public void setVendorQtyOnHand(int value) {
        if (vipo != null) {
            this.vipo.setQtyOnHand(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorQtyOnHand()
     */
    @Override
    public int getVendorQtyOnHand() {
        if (vipo != null) {
            return this.vipo.getQtyOnHand();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setVendorUnitCost(double)
     */
    @Override
    public void setVendorUnitCost(double value) {
        if (vipo != null) {
            this.vipo.setUnitCost(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorUnitCost()
     */
    @Override
    public double getVendorUnitCost() {
        if (vipo != null) {
            return this.vipo.getUnitCost();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setVendorMarkup(double)
     */
    @Override
    public void setVendorMarkup(double value) {
        if (vipo != null) {
            this.vipo.setMarkup(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorMarkup()
     */
    @Override
    public double getVendorMarkup() {
        if (vipo != null) {
            return this.vipo.getMarkup();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setVendorOverrideRetail(int)
     */
    @Override
    public void setVendorOverrideRetail(int value) {
        if (vipo != null) {
            this.vipo.setOverrideRetail(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorOverrideRetail()
     */
    @Override
    public int getVendorOverrideRetail() {
        if (vipo != null) {
            return this.vipo.getOverrideRetail();
        }
        return 0;
    }

    @Override
    public void setPoId(int value) {
        try {
            super.setPoId(value);
        }
        catch (Exception e) {
            if (this.i != null) {
                this.i.setPoId(value);
            }
            else {
                this.vipo.setPoId(value);    
            }
        }
    }

    @Override
    public int getPoId() {
        try {
            return super.getPoId();
        }
        catch (Exception e) {
            if (this.i != null) {
                return this.i.getPoId();
            }
            else {
                return this.vipo.getPoId();    
            }
        }
    }

    @Override
    public void setCreditorId(int value) {
        try {
            super.setCreditorId(value);
        }
        catch (Exception e) {
            this.vipo.setVendorId(value);    
        }
    }

    @Override
    public int getCreditorId() {
        try {
            return super.getCreditorId();
        }
        catch (Exception e) {
            return this.vipo.getVendorId();    
        }
    }

    @Override
    public void setEntityName(String value) {
        try {
            super.setEntityName(value);    
        }
        catch (Exception e) {
            this.vipo.setDescription(value);
        }
    }

    @Override
    public String getEntityName() {
        if (this.vipo != null) {
            return this.vipo.getDescription();
        }
        return null;
    }

}
