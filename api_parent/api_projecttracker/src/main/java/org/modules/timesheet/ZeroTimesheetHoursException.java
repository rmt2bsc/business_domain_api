package org.modules.timesheet;

/**
 * Handles scenario when the timesheet's hours structrue exists but the structure is empty.
 * 
 * @author Roy Terrell
 * 
 */
public class ZeroTimesheetHoursException extends TimesheetTransmissionValidationException {

    private static final long serialVersionUID = -5399306021195061423L;

    /**
     * 
     */
    public ZeroTimesheetHoursException() {
        super();
    }

    /**
     * @param msg
     */
    public ZeroTimesheetHoursException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public ZeroTimesheetHoursException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public ZeroTimesheetHoursException(String msg, Throwable e) {
        super(msg, e);
    }

}
