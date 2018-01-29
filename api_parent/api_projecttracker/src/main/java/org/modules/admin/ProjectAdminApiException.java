package org.modules.admin;

import com.InvalidDataException;

/**
 * Handles administrative related Project Tracker errors
 * 
 * @author Roy Terrell
 * 
 */
public class ProjectAdminApiException extends InvalidDataException {

    private static final long serialVersionUID = 2653509661759927388L;

    /**
     * 
     */
    public ProjectAdminApiException() {
        super();
    }

    /**
     * @param msg
     */
    public ProjectAdminApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public ProjectAdminApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public ProjectAdminApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
