package org.modules;

/**
 * Constants for the Project Tracker API module.
 * 
 * @author Roy Terrell
 * 
 */
public class ProjectTrackerApiConst {
    public static final String DEFAULT_CONTEXT_NAME = "ProjectTracker";
    
    public static final String APP_NAME = "ProjectTracker";
    
    public static final String APP_MODULE_ADMIN = "admin";
    
    public static final String APP_MODULE_EMPLOYEE = "employee";
    
    public static final String APP_MODULE_TIMESHEET = "timesheet";
    
    public static final String APP_MODULE_TIMESHEET_INVOICE = "timesheet_invoicing";

    /** Flag indicating a billable task */
    public static final int TASK_BILLABLE_FLAG = 1;

    /** Flag indicating a non-billable task */
    public static final int TASK_NONBILLABLE_FLAG = 0;

    /** Flag indicating an employee is a manager */
    public static final int EMPLOYEE_MANAGER_FLAG = 1;

    /** Flag indicating an employee is not a manager */
    public static final int EMPLOYEE_NON_MANAGER_FLAG = 0;

    /**
     * Parameter name for timesheet id.
     */
    public static final String PARM_NAME_TIMESHEET_ID = "Timesheet Id";

    /**
     * Parameter name for client id.
     */
    public static final String PARM_NAME_CLIENT_ID = "Client Id";

    /**
     * Parameter name for employee id.
     */
    public static final String PARM_NAME_EMPLOYEE_ID = "Employee Id";
}