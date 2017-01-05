package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the general_codes database table/view.
 * 
 * @author auto generated.
 */
public class GeneralCodes extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // GeneralCodesView

    /**
     * The property name constant equivalent to property, CodeId, of respective
     * DataSource view.
     */
    public static final String PROP_CODEID = "CodeId";
    /**
     * The property name constant equivalent to property, CodeGrpId, of
     * respective DataSource view.
     */
    public static final String PROP_CODEGRPID = "CodeGrpId";
    /**
     * The property name constant equivalent to property, Shortdesc, of
     * respective DataSource view.
     */
    public static final String PROP_SHORTDESC = "Shortdesc";
    /**
     * The property name constant equivalent to property, Longdesc, of
     * respective DataSource view.
     */
    public static final String PROP_LONGDESC = "Longdesc";
    /**
     * The property name constant equivalent to property, GenIndValue, of
     * respective DataSource view.
     */
    public static final String PROP_GENINDVALUE = "GenIndValue";
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
     * The property name constant equivalent to property, Permcol, of respective
     * DataSource view.
     */
    public static final String PROP_PERMCOL = "Permcol";

    /**
     * The javabean property equivalent of database column general_codes.code_id
     */
    private int codeId;
    /**
     * The javabean property equivalent of database column
     * general_codes.code_grp_id
     */
    private int codeGrpId;
    /**
     * The javabean property equivalent of database column
     * general_codes.shortdesc
     */
    private String shortdesc;
    /**
     * The javabean property equivalent of database column
     * general_codes.longdesc
     */
    private String longdesc;
    /**
     * The javabean property equivalent of database column
     * general_codes.gen_ind_value
     */
    private String genIndValue;
    /**
     * The javabean property equivalent of database column
     * general_codes.date_created
     */
    private java.util.Date dateCreated;
    /**
     * The javabean property equivalent of database column
     * general_codes.date_updated
     */
    private java.util.Date dateUpdated;
    /**
     * The javabean property equivalent of database column general_codes.user_id
     */
    private String userId;
    /**
     * The javabean property equivalent of database column general_codes.permcol
     */
    private String permcol;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public GeneralCodes() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable codeId
     */
    public void setCodeId(int value) {
        this.codeId = value;
    }

    /**
     * Gets the value of member variable codeId
     */
    public int getCodeId() {
        return this.codeId;
    }

    /**
     * Sets the value of member variable codeGrpId
     */
    public void setCodeGrpId(int value) {
        this.codeGrpId = value;
    }

    /**
     * Gets the value of member variable codeGrpId
     */
    public int getCodeGrpId() {
        return this.codeGrpId;
    }

    /**
     * Sets the value of member variable shortdesc
     */
    public void setShortdesc(String value) {
        this.shortdesc = value;
    }

    /**
     * Gets the value of member variable shortdesc
     */
    public String getShortdesc() {
        return this.shortdesc;
    }

    /**
     * Sets the value of member variable longdesc
     */
    public void setLongdesc(String value) {
        this.longdesc = value;
    }

    /**
     * Gets the value of member variable longdesc
     */
    public String getLongdesc() {
        return this.longdesc;
    }

    /**
     * Sets the value of member variable genIndValue
     */
    public void setGenIndValue(String value) {
        this.genIndValue = value;
    }

    /**
     * Gets the value of member variable genIndValue
     */
    public String getGenIndValue() {
        return this.genIndValue;
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
     * Sets the value of member variable permcol
     */
    public void setPermcol(String value) {
        this.permcol = value;
    }

    /**
     * Gets the value of member variable permcol
     */
    public String getPermcol() {
        return this.permcol;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}