package org.modules.timesheet;

import org.modules.ProjectTrackerModuleException;

/**
 * Handles exceptions regarding timesheet transmissions from one party to
 * another.
 * 
 * @author Roy Terrell
 * 
 */
public class TimesheetTransmissionException extends
        ProjectTrackerModuleException {
    private static final long serialVersionUID = -8851874846044566159L;

    public TimesheetTransmissionException() {
        super();
    }

    public TimesheetTransmissionException(String msg) {
        super(msg);
    }

    public TimesheetTransmissionException(Exception e) {
        super(e);
    }

    public TimesheetTransmissionException(String msg, Exception e) {
        super(msg, e);
    }
}
