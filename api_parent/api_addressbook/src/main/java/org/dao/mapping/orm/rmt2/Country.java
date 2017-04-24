package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the country database table/view.
 * 
 * @author auto generated.
 */
public class Country extends OrmBean {

    // Property name constants that belong to respective DataSource, CountryView

    /**
     * The property name constant equivalent to property, CountryId, of
     * respective DataSource view.
     */
    public static final String PROP_COUNTRYID = "CountryId";
    /**
     * The property name constant equivalent to property, Name, of respective
     * DataSource view.
     */
    public static final String PROP_NAME = "Name";
    /**
     * The property name constant equivalent to property, CntryVoidInd, of
     * respective DataSource view.
     */
    public static final String PROP_CNTRYVOIDIND = "CntryVoidInd";
    /**
     * The property name constant equivalent to property, Code, of respective
     * DataSource view.
     */
    public static final String PROP_CODE = "Code";

    /**
     * The javabean property equivalent of database column country.country_id
     */
    private int countryId;
    /** The javabean property equivalent of database column country.name */
    private String name;
    /**
     * The javabean property equivalent of database column
     * country.cntry_void_ind
     */
    private String cntryVoidInd;
    /** The javabean property equivalent of database column country.code */
    private String code;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public Country() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable countryId
     */
    public void setCountryId(int value) {
        this.countryId = value;
    }

    /**
     * Gets the value of member variable countryId
     */
    public int getCountryId() {
        return this.countryId;
    }

    /**
     * Sets the value of member variable name
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of member variable name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of member variable cntryVoidInd
     */
    public void setCntryVoidInd(String value) {
        this.cntryVoidInd = value;
    }

    /**
     * Gets the value of member variable cntryVoidInd
     */
    public String getCntryVoidInd() {
        return this.cntryVoidInd;
    }

    /**
     * Sets the value of member variable lookup
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of member variable lookup
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}