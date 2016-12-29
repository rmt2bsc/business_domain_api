package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the item_master_status database table/view.
 * 
 * @author auto generated.
 */
public class ItemMasterStatus extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // ItemMasterStatusView

    /**
     * The property name constant equivalent to property, ItemStatusId, of
     * respective DataSource view.
     */
    public static final String PROP_ITEMSTATUSID = "ItemStatusId";
    /**
     * The property name constant equivalent to property, Description, of
     * respective DataSource view.
     */
    public static final String PROP_DESCRIPTION = "Description";
    /**
     * The property name constant equivalent to property, DateCreated, of
     * respective DataSource view.
     */
    public static final String PROP_DATECREATED = "DateCreated";
    /**
     * The property name constant equivalent to property, DateUpdated, of
     * respective DataSource view.
     */
    public static final String PROP_DATEUPDATED = "DateUpdated";
    /**
     * The property name constant equivalent to property, UserId, of respective
     * DataSource view.
     */
    public static final String PROP_USERID = "UserId";

    /**
     * The javabean property equivalent of database column
     * item_master_status.item_status_id
     */
    private int itemStatusId;
    /**
     * The javabean property equivalent of database column
     * item_master_status.description
     */
    private String description;
    /**
     * The javabean property equivalent of database column
     * item_master_status.date_created
     */
    private java.util.Date dateCreated;
    /**
     * The javabean property equivalent of database column
     * item_master_status.date_updated
     */
    private java.util.Date dateUpdated;
    /**
     * The javabean property equivalent of database column
     * item_master_status.user_id
     */
    private String userId;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public ItemMasterStatus() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable itemStatusId
     */
    public void setItemStatusId(int value) {
        this.itemStatusId = value;
    }

    /**
     * Gets the value of member variable itemStatusId
     */
    public int getItemStatusId() {
        return this.itemStatusId;
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
     * Sets the value of member variable dateCreated
     */
    public void setDateCreated(java.util.Date value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of member variable dateCreated
     */
    public java.util.Date getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Sets the value of member variable dateUpdated
     */
    public void setDateUpdated(java.util.Date value) {
        this.dateUpdated = value;
    }

    /**
     * Gets the value of member variable dateUpdated
     */
    public java.util.Date getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Sets the value of member variable userId
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of member variable userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}