package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the city_type database table/view.
 * 
 * @author auto generated.
 */
public class CityType extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // CityTypeView

    /**
     * The property name constant equivalent to property, CityTypeId, of
     * respective DataSource view.
     */
    public static final String PROP_CITYTYPEID = "CityTypeId";
    /**
     * The property name constant equivalent to property, Descr, of respective
     * DataSource view.
     */
    public static final String PROP_DESCR = "Descr";

    /**
     * The javabean property equivalent of database column
     * city_type.city_type_id
     */
    private String cityTypeId;
    /** The javabean property equivalent of database column city_type.descr */
    private String descr;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public CityType() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable cityTypeId
     */
    public void setCityTypeId(String value) {
        this.cityTypeId = value;
    }

    /**
     * Gets the value of member variable cityTypeId
     */
    public String getCityTypeId() {
        return this.cityTypeId;
    }

    /**
     * Sets the value of member variable descr
     */
    public void setDescr(String value) {
        this.descr = value;
    }

    /**
     * Gets the value of member variable descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}