package org.modules.admin;

import org.modules.ProjectTrackerModuleException;

/**
 * Handles administrative related Project Tracker errors
 * 
 * @author Roy Terrell
 * 
 */
public class ProjectApiException extends ProjectTrackerModuleException {

    private static final long serialVersionUID = 2653509661759927388L;

    /**
     * 
     */
    public ProjectApiException() {
        super();
    }

    /**
     * @param msg
     */
    public ProjectApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public ProjectApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public ProjectApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
