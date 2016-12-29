package org.modules.timesheet;

import org.modules.ProjectTrackerModuleException;

/**
 * Handles exceptions regarding timesheet transmission data validation errors
 * 
 * @author Roy Terrell
 * 
 */
public class TimesheetTransmissionValidationException extends
        ProjectTrackerModuleException {
    private static final long serialVersionUID = -8851874846044566159L;

    public TimesheetTransmissionValidationException() {
        super();
    }

    public TimesheetTransmissionValidationException(String msg) {
        super(msg);
    }

    public TimesheetTransmissionValidationException(Exception e) {
        super(e);
    }

    public TimesheetTransmissionValidationException(String msg, Exception e) {
        super(msg, e);
    }
}
