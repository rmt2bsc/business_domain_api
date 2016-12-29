package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the time_zone database table/view.
 * 
 * @author auto generated.
 */
public class TimeZone extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // TimeZoneView

    /**
     * The property name constant equivalent to property, TimeZoneId, of
     * respective DataSource view.
     */
    public static final String PROP_TIMEZONEID = "TimeZoneId";
    /**
     * The property name constant equivalent to property, Descr, of respective
     * DataSource view.
     */
    public static final String PROP_DESCR = "Descr";

    /**
     * The javabean property equivalent of database column
     * time_zone.time_zone_id
     */
    private int timeZoneId;
    /** The javabean property equivalent of database column time_zone.descr */
    private String descr;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public TimeZone() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable timeZoneId
     */
    public void setTimeZoneId(int value) {
        this.timeZoneId = value;
    }

    /**
     * Gets the value of member variable timeZoneId
     */
    public int getTimeZoneId() {
        return this.timeZoneId;
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