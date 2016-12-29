package org.dao.timesheet;

/**
 * Common Timesheet constants
 * 
 * @author Roy Terrell
 * 
 */
public class TimesheetConst {

    /** Timesheet status for New */
    public static final int STATUS_NEW = 0;
    /** Timesheet status for Not Submitted */
    public static final int STATUS_DRAFT = 1;
    /** Timesheet status for Submitted */
    public static final int STATUS_SUBMITTED = 2;
    /** Timesheet status for Received */
    public static final int STATUS_RECVD = 3;
    /** Timesheet status for Approved */
    public static final int STATUS_APPROVED = 4;
    /** Timesheet status for Declined */
    public static final int STATUS_DECLINED = 5;
    /** Invoiced Timesheet status code */
    public static final int STATUS_INVOICED = 6;

    // /** Not submitted timesheet status code */
    // public static final int TIMESHEET_STATUS_DRAFT = 1;
    // /** Submitted timesheet status code */
    // public static final int TIMESHEET_STATUS_SUBMITTED = 2;
    // /** Received timesheet status code */
    // public static final int TIMESHEET_STATUS_RECEIVED = 3;
    // /** Approved timesheet status code */
    // public static final int TIMESHEET_STATUS_APPROVED = 4;
    // /** Declined timesheet status code */
    // public static final int TIMESHEET_STATUS_DECLINED = 5;
    // /** Invoiced Timesheet status code */
    // public static final int TIMESHEET_STATUS_INVOICED = 6;

    /** A constant indicating the limit on regular hours per week. */
    public static final int REG_PAY_HOURS = 40;
    /** A constant indicating that the hour amount is billable */
    public static final int HOUR_TYPE_BILLABLE = 1;
    /** A constant indicating that the hour amount is not billable */
    public static final int HOUR_TYPE_NONBILLABLE = 0;

    public static final int INVOICE_STATE_NO_DATA = 0;

    /**
     * 
     */
    public TimesheetConst() {
        return;
    }

}
