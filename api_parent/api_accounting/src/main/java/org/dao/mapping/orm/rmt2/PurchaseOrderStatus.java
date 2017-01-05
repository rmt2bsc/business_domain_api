package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the purchase_order_status database table/view.
 * 
 * @author auto generated.
 */
public class PurchaseOrderStatus extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // PurchaseOrderStatusView

    /**
     * The property name constant equivalent to property, PoStatusId, of
     * respective DataSource view.
     */
    public static final String PROP_POSTATUSID = "PoStatusId";
    /**
     * The property name constant equivalent to property, Description, of
     * respective DataSource view.
     */
    public static final String PROP_DESCRIPTION = "Description";

    /**
     * The javabean property equivalent of database column
     * purchase_order_status.po_status_id
     */
    private int poStatusId;
    /**
     * The javabean property equivalent of database column
     * purchase_order_status.description
     */
    private String description;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public PurchaseOrderStatus() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable poStatusId
     */
    public void setPoStatusId(int value) {
        this.poStatusId = value;
    }

    /**
     * Gets the value of member variable poStatusId
     */
    public int getPoStatusId() {
        return this.poStatusId;
    }

    /**
     * Sets the value of member variable description
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of member variable description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}