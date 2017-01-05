package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the ip_block database table/view.
 * 
 * @author auto generated.
 */
public class IpBlock extends OrmBean {

    // Property name constants that belong to respective DataSource, IpBlockView

    /**
     * The property name constant equivalent to property, IpStart, of respective
     * DataSource view.
     */
    public static final String PROP_IPSTART = "IpStart";
    /**
     * The property name constant equivalent to property, IpEnd, of respective
     * DataSource view.
     */
    public static final String PROP_IPEND = "IpEnd";
    /**
     * The property name constant equivalent to property, IpLoc, of respective
     * DataSource view.
     */
    public static final String PROP_IPLOC = "IpLoc";

    /** The javabean property equivalent of database column ip_block.ip_start */
    private double ipStart;
    /** The javabean property equivalent of database column ip_block.ip_end */
    private double ipEnd;
    /** The javabean property equivalent of database column ip_block.ip_loc */
    private int ipLoc;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public IpBlock() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable ipStart
     */
    public void setIpStart(double value) {
        this.ipStart = value;
    }

    /**
     * Gets the value of member variable ipStart
     */
    public double getIpStart() {
        return this.ipStart;
    }

    /**
     * Sets the value of member variable ipEnd
     */
    public void setIpEnd(double value) {
        this.ipEnd = value;
    }

    /**
     * Gets the value of member variable ipEnd
     */
    public double getIpEnd() {
        return this.ipEnd;
    }

    /**
     * Sets the value of member variable ipLoc
     */
    public void setIpLoc(int value) {
        this.ipLoc = value;
    }

    /**
     * Gets the value of member variable ipLoc
     */
    public int getIpLoc() {
        return this.ipLoc;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}