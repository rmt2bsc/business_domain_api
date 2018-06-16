package org.dto.adapter.orm.inventory;

import org.dao.mapping.orm.rmt2.VwItemAssociations;
import org.dto.ItemAssociationDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * view <i>vw_item_associations</i>.
 * 
 * @author rterrell
 * 
 */
class ItemAssocRmt2OrmAdapter implements ItemAssociationDto {

    private VwItemAssociations i;

    /**
     * Create a ItemAssocRmt2OrmAdapter without performing any data adaptations
     */
    protected ItemAssocRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a ItemAssocRmt2OrmAdapter that adapts data coming from the
     * vw_item_associations view.
     * 
     * @param item
     *            an instance of {@link VwItemAssociations} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected ItemAssocRmt2OrmAdapter(VwItemAssociations item) {
        if (item == null) {
            item = new VwItemAssociations();
        }
        this.i = item;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#setAssociationId(int)
     */
    @Override
    public void setAssociationId(int value) {
        this.i.setAssocId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#getAssociationId()
     */
    @Override
    public int getAssociationId() {
        return this.i.getAssocId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#setAssociationItemId(int)
     */
    @Override
    public void setAssociationItemId(int value) {
        this.i.setAssocItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#getAssociationItemId()
     */
    @Override
    public int getAssociationItemId() {
        return this.i.getAssocItemId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#setItemId(int)
     */
    @Override
    public void setItemId(int value) {
        this.i.setItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#getItemId()
     */
    @Override
    public int getItemId() {
        return this.i.getItemId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#setItemCost(double)
     */
    @Override
    public void setItemCost(double value) {
        this.i.setItemCost(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#getItemCost()
     */
    @Override
    public double getItemCost() {
        return this.i.getItemCost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#setOrderQty(int)
     */
    @Override
    public void setOrderQty(double value) {
        this.i.setOrderQty(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#getOrderQty()
     */
    @Override
    public double getOrderQty() {
        return this.i.getOrderQty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#setAssociationType(java.lang.String)
     */
    @Override
    public void setAssociationType(String value) {
        this.i.setAssocType(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemAssociationDto#getAssociationType()
     */
    @Override
    public String getAssociationType() {
        return this.i.getAssocType();
    }

}
