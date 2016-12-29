package org.dto.adapter.orm.transaction.sales;

import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.VwSalesorderItemsBySalesorder;
import org.dto.SalesOrderItemDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * object <i>sales_order_items</i> table.
 * 
 * @author rterrell
 * 
 */
class SalesOrderItemRmt2OrmAdapter extends SalesOrderRmt2OrmAdapter implements
        SalesOrderItemDto {

    private SalesOrderItems soi;

    private ItemMaster im;

    private ItemMasterType imt;

    /**
     * Create a SalesOrderItemRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected SalesOrderItemRmt2OrmAdapter() {
        this.im = new ItemMaster();
        this.imt = new ItemMasterType();
        return;
    }

    /**
     * Create a SalesOrderItemRmt2OrmAdapter that adapts data coming from the
     * <i>sales_order</i>.
     * 
     * @param item
     *            an instance of {@link SalesOrderItems} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected SalesOrderItemRmt2OrmAdapter(SalesOrderItems item) {
        this();

        // INitialize sales order item object
        this.updateObjHeirarchy(item);

        // Initialize sales order object
        SalesOrder so = new SalesOrder();
        so.setSoId(this.soi.getSoId());
        super.updateObjHeirarchy(so);

        return;
    }

    protected SalesOrderItemRmt2OrmAdapter(VwSalesorderItemsBySalesorder item) {
        this();

        SalesOrderItems soi = null;
        SalesOrder so = null;
        Customer cust = null;

        if (item == null) {
            this.updateObjHeirarchy(null);
        }
        else {
            // Initialize sales order item object
            soi = new SalesOrderItems();
            soi.setSoItemId(item.getSalesOrderItemId());
            soi.setSoId(item.getSoId());
            soi.setItemId(item.getItemId());
            soi.setItemNameOverride(item.getItemNameOverride());
            soi.setOrderQty(item.getOrderQty());
            soi.setBackOrderQty(item.getBackOrderQty());
            soi.setInitUnitCost(item.getInitUnitCost());
            soi.setInitMarkup(item.getInitMarkup());
            this.updateObjHeirarchy(soi);

            // Initialize sales order object.
            so = new SalesOrder();
            so.setSoId(item.getSoId());
            so.setCustomerId(item.getCustomerId());
            so.setInvoiced(item.getInvoiced());
            this.updateObjHeirarchy(so);

            // Initialize customer companion object
            cust = new Customer();
            cust.setCustomerId(item.getCustomerId());
            cust.setPersonId(item.getPersonId());
            cust.setBusinessId(item.getBusinessId());
            this.initCustomer(cust);

            // Initialize item master type object
            this.imt.setItemTypeId(item.getItemTypeId());
            this.imt.setDescription(item.getItemTypeDescr());

            // Initialize item Master object
            this.im.setItemId(item.getItemId());
            this.im.setCreditorId(item.getCreditorId());
            this.im.setDescription(item.getItemName());
            this.im.setVendorItemNo(item.getVendorItemNo());
            this.im.setItemSerialNo(item.getItemSerialNo());
            this.im.setQtyOnHand(item.getQtyOnHand());
            this.im.setUnitCost(item.getUnitCost());
            this.im.setMarkup(item.getMarkup());
            this.im.setRetailPrice(item.getRetailPrice());
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.api.foundation.TransactionDtoImpl#initDataObject(java.lang.Object)
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        super.updateObjHeirarchy(obj);
        SalesOrderItems item = null;
        if (obj == null) {
            item = new SalesOrderItems();
        }
        else if (obj instanceof SalesOrderItems) {
            item = (SalesOrderItems) obj;
        }
        else {
            return;
        }
        this.soi = item;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setSoItemId(int)
     */
    @Override
    public void setSoItemId(int value) {
        this.soi.setSoItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getSoItemId()
     */
    @Override
    public int getSoItemId() {
        return this.soi.getSoItemId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setItemId(int)
     */
    @Override
    public void setItemId(int value) {
        this.soi.setItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getItemId()
     */
    @Override
    public int getItemId() {
        return this.soi.getItemId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setItemNameOverride(java.lang.String)
     */
    @Override
    public void setItemNameOverride(String value) {
        this.soi.setItemNameOverride(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getItemNameOverride()
     */
    @Override
    public String getItemNameOverride() {
        return this.soi.getItemNameOverride();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setOrderQty(double)
     */
    @Override
    public void setOrderQty(double value) {
        this.soi.setOrderQty(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getOrderQty()
     */
    @Override
    public double getOrderQty() {
        return this.soi.getOrderQty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setBackOrderQty(double)
     */
    @Override
    public void setBackOrderQty(double value) {
        this.soi.setBackOrderQty(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getBackOrderQty()
     */
    @Override
    public double getBackOrderQty() {
        return this.soi.getBackOrderQty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setInitUnitCost(double)
     */
    @Override
    public void setInitUnitCost(double value) {
        this.soi.setInitUnitCost(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getInitUnitCost()
     */
    @Override
    public double getInitUnitCost() {
        return this.soi.getInitUnitCost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setInitMarkup(double)
     */
    @Override
    public void setInitMarkup(double value) {
        this.soi.setInitMarkup(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getInitMarkup()
     */
    @Override
    public double getInitMarkup() {
        return this.soi.getInitMarkup();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setImName(java.lang.String)
     */
    @Override
    public void setImName(String value) {
        this.im.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getImName()
     */
    @Override
    public String getImName() {
        return this.im.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setImItemTypeId(int)
     */
    @Override
    public void setImItemTypeId(int value) {
        this.imt.setItemTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getImItemTypeId()
     */
    @Override
    public int getImItemTypeId() {
        return this.imt.getItemTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setImItemTypeDescription(java.lang.String)
     */
    @Override
    public void setImItemTypeDescription(String value) {
        this.imt.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getImItemTypeDescription()
     */
    @Override
    public String getImItemTypeDescription() {
        return this.imt.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setImVendorItemNo(java.lang.String)
     */
    @Override
    public void setImVendorItemNo(String value) {
        this.im.setVendorItemNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getImVendorItemNo()
     */
    @Override
    public String getImVendorItemNo() {
        return this.im.getVendorItemNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setImSerialNo(java.lang.String)
     */
    @Override
    public void setImSerialNo(String value) {
        this.im.setItemSerialNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getImSerialNo()
     */
    @Override
    public String getImSerialNo() {
        return this.im.getItemSerialNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setImQtyOnHand(int)
     */
    @Override
    public void setImQtyOnHand(int value) {
        this.im.setQtyOnHand(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getImQtyOnHand()
     */
    @Override
    public int getImQtyOnHand() {
        return this.im.getQtyOnHand();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setImUnitCost(double)
     */
    @Override
    public void setImUnitCost(double value) {
        this.im.setUnitCost(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getImUnitCost()
     */
    @Override
    public double getImUnitCost() {
        return this.im.getUnitCost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setImMarkup(double)
     */
    @Override
    public void setImMarkup(double value) {
        this.im.setMarkup(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getImMarkup()
     */
    @Override
    public double getImMarkup() {
        return this.im.getMarkup();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#setImRetailPrice(double)
     */
    @Override
    public void setImRetailPrice(double value) {
        this.im.setRetailPrice(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderItemDto#getImRetailPrice()
     */
    @Override
    public double getImRetailPrice() {
        return this.im.getRetailPrice();
    }

}
