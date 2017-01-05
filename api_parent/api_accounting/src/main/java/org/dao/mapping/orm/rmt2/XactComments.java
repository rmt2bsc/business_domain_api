package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the xact_comments database table/view.
 * 
 * @author auto generated.
 */
public class XactComments extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // XactCommentsView

    /**
     * The property name constant equivalent to property, XactCommentId, of
     * respective DataSource view.
     */
    public static final String PROP_XACTCOMMENTID = "XactCommentId";
    /**
     * The property name constant equivalent to property, XactId, of respective
     * DataSource view.
     */
    public static final String PROP_XACTID = "XactId";
    /**
     * The property name constant equivalent to property, Note, of respective
     * DataSource view.
     */
    public static final String PROP_NOTE = "Note";
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
     * xact_comments.xact_comment_id
     */
    private int xactCommentId;
    /**
     * The javabean property equivalent of database column xact_comments.xact_id
     */
    private int xactId;
    /** The javabean property equivalent of database column xact_comments.note */
    private String note;
    /**
     * The javabean property equivalent of database column
     * xact_comments.date_created
     */
    private java.util.Date dateCreated;
    /**
     * The javabean property equivalent of database column
     * xact_comments.date_updated
     */
    private java.util.Date dateUpdated;
    /**
     * The javabean property equivalent of database column xact_comments.user_id
     */
    private String userId;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public XactComments() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable xactCommentId
     */
    public void setXactCommentId(int value) {
        this.xactCommentId = value;
    }

    /**
     * Gets the value of member variable xactCommentId
     */
    public int getXactCommentId() {
        return this.xactCommentId;
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
     * Sets the value of member variable note
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of member variable note
     */
    public String getNote() {
        return this.note;
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