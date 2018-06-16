package org.dao.resources;

import org.dao.SecurityDaoException;

/**
 * Manages DAO error pertaining to resource data accessibility.
 * 
 * @author RTerrell
 * 
 */
public class ResourceDaoException extends SecurityDaoException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public ResourceDaoException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public ResourceDaoException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public ResourceDaoException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public ResourceDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
