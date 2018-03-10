package org.rmt2.api;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProject;
import org.dao.mapping.orm.rmt2.ProjTask;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dao.mapping.orm.rmt2.VwTimesheetEventList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;

import com.util.RMT2Date;

public class ProjectTrackerMockDataFactory {

    /**
     * 
     * @param clientId
     * @param businessId
     * @param name
     * @param billRate
     * @param otBillRate
     * @param acctNo
     * @param firstName
     * @param lastName
     * @param phone
     * @param email
     * @return
     */
    public static final ProjClient createMockOrmProjClient(int clientId,
            int businessId, String name, double billRate, double otBillRate,
            String acctNo, String firstName, String lastName, String phone,
            String email) {
        ProjClient o = new ProjClient();
        o.setClientId(clientId);
        o.setBusinessId(businessId);
        o.setName(name);
        o.setBillRate(billRate);
        o.setOtBillRate(otBillRate);
        o.setAccountNo(acctNo);
        o.setContactFirstname(firstName);
        o.setContactLastname(lastName);
        o.setContactPhone(phone);
        o.setContactEmail(email);
        return o;
    }

    /**
     * 
     * @param projId
     * @param clientId
     * @param description
     * @param effectiveDate
     * @param endDate
     * @return
     */
    public static final ProjProject createMockOrmProjProject(int projId,
            int clientId, String description, String effectiveDate,
            String endDate) {
        ProjProject o = new ProjProject();
        o.setProjId(projId);
        o.setClientId(clientId);
        o.setDescription(description);
        o.setEffectiveDate(RMT2Date.stringToDate(effectiveDate));
        o.setEndDate(RMT2Date.stringToDate(endDate));
        return o;
    }
    
    /**
     * 
     * @param empId
     * @param empTypeId
     * @param isManager
     * @param managerId
     * @param empTitleId
     * @param loginId
     * @param startDate
     * @param termDate
     * @param loginName
     * @param firstName
     * @param lastName
     * @param ssn
     * @param companyName
     * @return
     */
    public static final ProjEmployee createMockOrmProjEmployee(int empId,
            int empTypeId, int isManager, int managerId, int empTitleId,
            int loginId, String startDate, String termDate, String loginName,
            String firstName, String lastName, String ssn, String companyName) {
        ProjEmployee o = new ProjEmployee();
        o.setEmpId(empId);
        o.setEmpTypeId(empTypeId);
        o.setIsManager(isManager);
        o.setManagerId(managerId);
        o.setEmpTitleId(empTitleId);
        o.setLoginId(loginId);
        o.setStartDate(RMT2Date.stringToDate(startDate));
        o.setTerminationDate(termDate == null ? null : RMT2Date.stringToDate(termDate));
        o.setLoginName(loginName);
        o.setFirstname(firstName);
        o.setLastname(lastName);
        o.setSsn(ssn);
        o.setCompanyName(companyName);
        return o;
    }
    
    
    /**
     * 
     * @param empTitleId
     * @param description
     * @return
     */
    public static final ProjEmployeeTitle createMockOrmProjEmployeeTitle(int empTitleId, String description) {
        ProjEmployeeTitle o = new ProjEmployeeTitle();
        o.setEmpTitleId(empTitleId);
        o.setDescription(description);
        return o;
    }
    
    /**
     * 
     * @param empTypeId
     * @param description
     * @return
     */
    public static final ProjEmployeeType createMockOrmProjEmployeeType(int empTypeId, String description) {
        ProjEmployeeType o = new ProjEmployeeType();
        o.setEmpTypeId(empTypeId);
        o.setDescription(description);
        return o;
    }
    
    /**
     * 
     * @param empProjId
     * @param projId
     * @param projName
     * @param clientId
     * @param clientName
     * @param businessId
     * @param acctNo
     * @param empId
     * @param projEffectiveDate
     * @param projEndDate
     * @param projEmpEffectiveDate
     * @param projEmpEndDate
     * @param payRate
     * @param otPayRate
     * @param flatRate
     * @param clientBillRate
     * @param clientOtBillRate
     * @return
     */
    public static final VwEmployeeProjects createMockOrmVwEmployeeProjects(int empProjId, int projId, 
            String projName, int clientId, String clientName, int businessId,
            String acctNo, int empId, String projEffectiveDate,
            String projEndDate, String projEmpEffectiveDate,
            String projEmpEndDate, double payRate, double otPayRate,
            double flatRate, double clientBillRate, double clientOtBillRate) {
        VwEmployeeProjects o = new VwEmployeeProjects();
        o.setEmpProjId(empProjId);
        o.setProjId(projId);
        o.setProjectName(projName);
        o.setClientId(clientId);
        o.setClientName(clientName);
        o.setBusinessId(businessId);
        o.setAccountNo(acctNo);
        o.setEmpId(empId);
        o.setProjEffectiveDate(RMT2Date.stringToDate(projEffectiveDate));
        o.setProjEndDate(RMT2Date.stringToDate(projEndDate));
        o.setProjempEffectiveDate(RMT2Date.stringToDate(projEmpEffectiveDate));
        o.setProjempEndDate(RMT2Date.stringToDate(projEmpEndDate));
        o.setPayRate(payRate);
        o.setOtPayRate(otPayRate);
        o.setFlatRate(flatRate);
        o.setClientBillRate(clientBillRate);
        o.setClientOtBillRate(clientOtBillRate);
        o.setComments("Comments for Employee Project Id: " + empProjId);
        return o;
    }
    
    /**
     * 
     * @param eventId
     * @param projectTaskId
     * @param eventDate
     * @param hours
     * @return
     */
    public static final ProjEvent createMockOrmProjEvent(int eventId, int projectTaskId, String eventDate, double hours) {
        ProjEvent o = new ProjEvent();
        o.setEventId(eventId);
        o.setProjectTaskId(projectTaskId);
        o.setEventDate(RMT2Date.stringToDate(eventDate));
        o.setHours(hours);
        return o;
    }
    
   /**
    * 
    * @param eventId
    * @param eventDate
    * @param hours
    * @param projectTaskId
    * @param timesheetId
    * @param projectId
    * @param projectName
    * @param taskId
    * @param taskName
    * @param clientId
    * @param effectiveDate
    * @param endDate
    * @param billable
    * @return
    */
    public static final VwTimesheetEventList createMockOrmVwTimesheetEventList(
            int eventId, String eventDate, double hours, int projectTaskId,
            int timesheetId, int projectId, String projectName, int taskId,
            String taskName, int clientId, String effectiveDate, String endDate,
            boolean billable) {
        VwTimesheetEventList o = new VwTimesheetEventList();
        o.setEventId(eventId);
        o.setProjectTaskId(projectTaskId);
        o.setEventDate(RMT2Date.stringToDate(eventDate));
        o.setEventDateCreated(RMT2Date.stringToDate(eventDate));
        o.setHours(hours);
        o.setTimesheetId(timesheetId);
        o.setProjectId(projectId);
        o.setProjectName(projectName);
        o.setTaskId(taskId);
        o.setTaskName(taskName);
        o.setClientId(clientId);
        o.setEffectiveDate(RMT2Date.stringToDate(effectiveDate));
        o.setEndDate(RMT2Date.stringToDate(endDate));
        o.setBillable(billable ? 1 : 0);
        return o;
    }
    
    /**
     * 
     * @param taskId
     * @param description
     * @param billable
     * @return
     */
    public static final ProjTask createMockOrmProjTask(int taskId, String description, boolean billable) {
        ProjTask o = new ProjTask();
       o.setTaskId(taskId);
       o.setDescription(description);
       o.setBillable(billable ? 1 : 0);
        return o;
    }
    
    /**
     * 
     * @param projectTaskId
     * @param timesheetId
     * @param projectId
     * @param taskId
     * @param clientId
     * @param projectName
     * @param effectiveDate
     * @param endDate
     * @param taskName
     * @param billable
     * @return
     */
    public static final VwTimesheetProjectTask createMockOrmVwTimesheetProjectTask(
            int projectTaskId, int timesheetId, int projectId, int taskId,
            int clientId, String projectName, String effectiveDate,
            String endDate, String taskName, boolean billable) {
        VwTimesheetProjectTask o = new VwTimesheetProjectTask();
        o.setProjectTaskId(projectTaskId);
        o.setTimesheetId(timesheetId);
        o.setProjectId(projectId);
        o.setProjectName(projectName);
        o.setTaskId(taskId);
        o.setTaskName(taskName);
        o.setClientId(clientId);
        o.setEffectiveDate(RMT2Date.stringToDate(effectiveDate));
        o.setEndDate(RMT2Date.stringToDate(endDate));
        o.setBillable(billable ? 1 : 0);
        return o;
    }
    
}
