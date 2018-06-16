package org.modules.timesheet.invoice;

/**
 * Sales order item bean for transfering timesheet data to accounting system
 * 
 * @author Roy Terrell
 */
class InvoiceDetailsBean {

    private int soItemId;
    private int itemId;
    private int soId;
    private String itemNameOverride;
    private double orderQty;
    private double backOrderQty;
    private double initUnitCost;
    private double initMarkup;

    /**
     * Default constructor.
     * 
     * @author auto generated.
     */
    public InvoiceDetailsBean() {
        super();
    }

    /**
     * Sets the value of member variable soItemId
     * 
     * @author auto generated.
     */
    public void setSoItemId(int value) {
        this.soItemId = value;
    }

    /**
     * Gets the value of member variable soItemId
     * 
     * @author Roy Terrell.
     */
    public int getSoItemId() {
        return this.soItemId;
    }

    /**
     * Sets the value of member variable itemId
     * 
     * @author auto generated.
     */
    public void setItemId(int value) {
        this.itemId = value;
    }

    /**
     * Gets the value of member variable itemId
     * 
     * @author Roy Terrell.
     */
    public int getItemId() {
        return this.itemId;
    }

    /**
     * Sets the value of member variable soId
     * 
     * @author auto generated.
     */
    public void setSoId(int value) {
        this.soId = value;
    }

    /**
     * Gets the value of member variable soId
     * 
     * @author Roy Terrell.
     */
    public int getSoId() {
        return this.soId;
    }

    /**
     * Sets the value of member variable itemNameOverride
     * 
     * @author auto generated.
     */
    public void setItemNameOverride(String value) {
        this.itemNameOverride = value;
    }

    /**
     * Gets the value of member variable itemNameOverride
     * 
     * @author Roy Terrell.
     */
    public String getItemNameOverride() {
        return this.itemNameOverride;
    }

    /**
     * Sets the value of member variable orderQty
     * 
     * @author auto generated.
     */
    public void setOrderQty(double value) {
        this.orderQty = value;
    }

    /**
     * Gets the value of member variable orderQty
     * 
     * @author Roy Terrell.
     */
    public double getOrderQty() {
        return this.orderQty;
    }

    /**
     * Sets the value of member variable backOrderQty
     * 
     * @author auto generated.
     */
    public void setBackOrderQty(double value) {
        this.backOrderQty = value;
    }

    /**
     * Gets the value of member variable backOrderQty
     * 
     * @author Roy Terrell.
     */
    public double getBackOrderQty() {
        return this.backOrderQty;
    }

    /**
     * Sets the value of member variable initUnitCost
     * 
     * @author auto generated.
     */
    public void setInitUnitCost(double value) {
        this.initUnitCost = value;
    }

    /**
     * Gets the value of member variable initUnitCost
     * 
     * @author Roy Terrell.
     */
    public double getInitUnitCost() {
        return this.initUnitCost;
    }

    /**
     * Sets the value of member variable initMarkup
     * 
     * @author auto generated.
     */
    public void setInitMarkup(double value) {
        this.initMarkup = value;
    }

    /**
     * Gets the value of member variable initMarkup
     * 
     * @author Roy Terrell.
     */
    public double getInitMarkup() {
        return this.initMarkup;
    }
}