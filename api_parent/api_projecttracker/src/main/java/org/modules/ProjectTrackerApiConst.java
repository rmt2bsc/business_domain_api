package org.modules;

/**
 * Constants for the Project Tracker API module.
 * 
 * @author Roy Terrell
 * 
 */
public class ProjectTrackerApiConst {
    public static final String DEFAULT_CONTEXT_NAME = "ProjectTracker";

    /** Flag indicating a billable task */
    public static final int TASK_BILLABLE_FLAG = 1;

    /** Flag indicating a non-billable task */
    public static final int TASK_NONBILLABLE_FLAG = 0;

    /** Flag indicating an employee is a manager */
    public static final int EMPLOYEE_MANAGER_FLAG = 1;

    /** Flag indicating an employee is not a manager */
    public static final int EMPLOYEE_NON_MANAGER_FLAG = 0;
}