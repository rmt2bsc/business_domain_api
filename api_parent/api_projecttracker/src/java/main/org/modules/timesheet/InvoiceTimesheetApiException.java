package org.modules.timesheet;

/**
 * Handles timesheet invoice operation errors.
 * 
 * @author Roy Terrell
 * 
 */
public class InvoiceTimesheetApiException extends TimesheetApiException {

    private static final long serialVersionUID = -8563975448348160007L;

    /**
     * 
     */
    public InvoiceTimesheetApiException() {
        super();
    }

    /**
     * @param msg
     */
    public InvoiceTimesheetApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvoiceTimesheetApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvoiceTimesheetApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
