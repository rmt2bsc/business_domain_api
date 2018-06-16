package org.modules;

import com.RMT2Exception;

/**
 * Handles general Project Tracker errors.
 * 
 * @author Roy Terrell
 * 
 */
public class ProjectTrackerModuleException extends RMT2Exception {
    private static final long serialVersionUID = 2969536074770899864L;

    public ProjectTrackerModuleException() {
        super();
    }

    public ProjectTrackerModuleException(String msg) {
        super(msg);
    }

    public ProjectTrackerModuleException(Exception e) {
        super(e);
    }

    public ProjectTrackerModuleException(String msg, Throwable e) {
        super(msg, e);
    }
}
