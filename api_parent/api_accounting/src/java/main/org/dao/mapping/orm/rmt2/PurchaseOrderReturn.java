package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the purchase_order_return database table/view.
 * 
 * @author auto generated.
 */
public class PurchaseOrderReturn extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // PurchaseOrderReturnView

    /**
     * The property name constant equivalent to property, PoReturnId, of
     * respective DataSource view.
     */
    public static final String PROP_PORETURNID = "PoReturnId";
    /**
     * The property name constant equivalent to property, PoId, of respective
     * DataSource view.
     */
    public static final String PROP_POID = "PoId";
    /**
     * The property name constant equivalent to property, XactId, of respective
     * DataSource view.
     */
    public static final String PROP_XACTID = "XactId";
    /**
     * The property name constant equivalent to property, Reason, of respective
     * DataSource view.
     */
    public static final String PROP_REASON = "Reason";

    /**
     * The javabean property equivalent of database column
     * purchase_order_return.po_return_id
     */
    private int poReturnId;
    /**
     * The javabean property equivalent of database column
     * purchase_order_return.po_id
     */
    private int poId;
    /**
     * The javabean property equivalent of database column
     * purchase_order_return.xact_id
     */
    private int xactId;
    /**
     * The javabean property equivalent of database column
     * purchase_order_return.reason
     */
    private String reason;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public PurchaseOrderReturn() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable poReturnId
     */
    public void setPoReturnId(int value) {
        this.poReturnId = value;
    }

    /**
     * Gets the value of member variable poReturnId
     */
    public int getPoReturnId() {
        return this.poReturnId;
    }

    /**
     * Sets the value of member variable poId
     */
    public void setPoId(int value) {
        this.poId = value;
    }

    /**
     * Gets the value of member variable poId
     */
    public int getPoId() {
        return this.poId;
    }

    /**
     * Sets the value of member variable xactId
     */
    public void setXactId(int value) {
        this.xactId = value;
    }

    /**
     * Gets the value of member variable xactId
     */
    public int getXactId() {
        return this.xactId;
    }

    /**
     * Sets the value of member variable reason
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of member variable reason
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}