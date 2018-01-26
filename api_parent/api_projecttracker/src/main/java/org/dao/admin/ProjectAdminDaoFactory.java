package org.dao.admin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeProject;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProject;
import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.dao.mapping.orm.rmt2.ProjTask;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dao.mapping.orm.rmt2.VwProjectClient;
import org.dao.mapping.orm.rmt2.VwTimesheetEventList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EmployeeTitleDto;
import org.dto.EmployeeTypeDto;
import org.dto.EventDto;
import org.dto.ProjectClientDto;
import org.dto.ProjectDto;
import org.dto.ProjectEmployeeDto;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TaskDto;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * Factory class for creating Project Admin DAO related objects.
 * 
 * @author Roy Terrell
 * 
 */
public class ProjectAdminDaoFactory extends RMT2Base {

    /**
     * Default constructor
     */
    public ProjectAdminDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>ProjectAdminDao</i> using the RMT2 transaction
     * ORM implementation.
     * 
     * @return an instance of {@link ProjectAdminDao}
     */
    public ProjectAdminDao createRmt2OrmDao() {
        ProjectAdminDao dao = new Rmt2ProjectAdminDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>ProjectAdminDao</i> using the RMT2 transaction
     * ORM implementation.
     * 
     * @param the
     *            name of the application to obtain DAO
     * 
     * @return an instance of {@link ProjectAdminDao}
     */
    public ProjectAdminDao createRmt2OrmDao(String appName) {
        ProjectAdminDao dao = new Rmt2ProjectAdminDaoImpl(appName);
        return dao;
    }

    /**
     * Creates an instance of <i>ProjectAdminDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     * 
     * @return an instance of {@link ProjectAdminDao}
     */
    public ProjectAdminDao createRmt2OrmDao(DaoClient dao) {
        ProjectAdminDao d = new Rmt2ProjectAdminDaoImpl(dao.getClient());
        d.setDaoUser(dao.getDaoUser());
        return d;
    }

    /**
     * Creates and returns an <i>ProjClient</i> object containing selection
     * criteria obtained from an instance of <i>ClientDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ClientDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>clientId</li>
     *            <li>businessId</li>
     *            <li>accountNo</li>
     *            <li>clientName</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjClient}
     */
    public static final ProjClient createCriteria(ClientDto criteria) {
        ProjClient obj = new ProjClient();
        if (criteria != null) {
            if (criteria.getClientId() > 0) {
                obj.addCriteria(ProjClient.PROP_CLIENTID, criteria.getClientId());
            }
            if (criteria.getBusinessId() > 0) {
                obj.addCriteria(ProjClient.PROP_BUSINESSID, criteria.getBusinessId());
            }
            if (criteria.getAccountNo() != null) {
                obj.addLikeClause(ProjClient.PROP_ACCOUNTNO, criteria.getAccountNo());
            }
            if (criteria.getClientName() != null) {
                obj.addLikeClause(ProjClient.PROP_NAME, criteria.getClientName());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ProjProject</i> object containing selection
     * criteria obtained from an instance of <i>ProjectDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ProjectDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>clientId</li>
     *            <li>projId</li>
     *            <li>projDescription</li>
     *            <li>effectiveDate</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjProject}
     */
    public static final ProjProject createCriteria(ProjectDto criteria) {
        ProjProject obj = new ProjProject();
        if (criteria != null) {
            if (criteria.getClientId() > 0) {
                obj.addCriteria(ProjProject.PROP_CLIENTID, criteria.getClientId());
            }
            if (criteria.getProjId() > 0) {
                obj.addCriteria(ProjProject.PROP_PROJID, criteria.getProjId());
            }
            if (criteria.getProjectDescription() != null) {
                obj.addLikeClause(ProjProject.PROP_DESCRIPTION, criteria.getProjectDescription());
            }
            if (criteria.getProjectEffectiveDate() != null) {
                obj.addCriteria(ProjProject.PROP_EFFECTIVEDATE, criteria.getProjectEffectiveDate());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ProjEmployee</i> object containing selection
     * criteria obtained from an instance of <i>EmployeeDto</i>.
     * 
     * @param criteria
     *            an instance of {@link EmployeeDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>employeeId</li>
     *            <li>empTypeId</li>
     *            <li>empTitleId</li>
     *            <li>managerId</li>
     *            <li>startDate</li>
     *            <li>terminationDate</li>
     *            <li>firstName</li>
     *            <li>lastName</li>
     *            <li>ssn</li>
     *            <li>email</li>
     *            <li>loginName</li>
     *            <li>companyName</li>
     *            <li>isManager</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjEmployee}
     */
    public static final ProjEmployee createCriteria(EmployeeDto criteria) {
        ProjEmployee obj = new ProjEmployee();
        if (criteria != null) {
            if (criteria.getEmployeeId() > 0) {
                obj.addCriteria(ProjEmployee.PROP_EMPID,
                        criteria.getEmployeeId());
            }
            if (criteria.getEmployeeTypeId() > 0) {
                obj.addCriteria(ProjEmployee.PROP_EMPTYPEID,
                        criteria.getEmployeeTypeId());
            }
            if (criteria.getEmployeeTitleId() > 0) {
                obj.addCriteria(ProjEmployee.PROP_EMPTITLEID,
                        criteria.getEmployeeTitleId());
            }
            if (criteria.getManagerId() > 0) {
                obj.addCriteria(ProjEmployee.PROP_MANAGERID,
                        criteria.getManagerId());
            }
            if (criteria.getStartDate() != null) {
                obj.addCriteria(ProjEmployee.PROP_STARTDATE,
                        criteria.getStartDate());
            }
            if (criteria.getTerminationDate() != null) {
                obj.addCriteria(ProjEmployee.PROP_TERMINATIONDATE,
                        criteria.getTerminationDate());
            }
            if (criteria.getEmployeeFirstname() != null) {
                obj.addLikeClause(ProjEmployee.PROP_FIRSTNAME,
                        criteria.getEmployeeFirstname());
            }
            if (criteria.getEmployeeLastname() != null) {
                obj.addLikeClause(ProjEmployee.PROP_LASTNAME,
                        criteria.getEmployeeLastname());
            }
            if (criteria.getSsn() != null) {
                obj.addLikeClause(ProjEmployee.PROP_SSN, criteria.getSsn());
            }
            if (criteria.getEmployeeEmail() != null) {
                obj.addLikeClause(ProjEmployee.PROP_EMAIL,
                        criteria.getEmployeeEmail());
            }
            if (criteria.getLoginName() != null) {
                obj.addLikeClause(ProjEmployee.PROP_LOGINNAME,
                        criteria.getLoginName());
            }
            if (criteria.getEmployeeCompanyName() != null) {
                obj.addLikeClause(ProjEmployee.PROP_COMPANYNAME,
                        criteria.getEmployeeCompanyName());
            }
            if (criteria.getIsManager() == 1) {
                obj.addCriteria(ProjEmployee.PROP_ISMANAGER,
                        criteria.getIsManager());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ProjEmployeeTitle</i> object containing
     * selection criteria obtained from an instance of <i>EmployeeTitleDto</i>.
     * 
     * @param criteria
     *            an instance of {@link EmployeeTitleDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>employeeTitleId</li>
     *            <li>employeeTitleDescription</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjEmployeeTitle}
     */
    public static final ProjEmployeeTitle createCriteria(
            EmployeeTitleDto criteria) {
        ProjEmployeeTitle obj = new ProjEmployeeTitle();
        if (criteria != null) {
            if (criteria.getEmployeeTitleId() > 0) {
                obj.addCriteria(ProjEmployeeTitle.PROP_EMPTITLEID,
                        criteria.getEmployeeTitleId());
            }
            if (criteria.getEmployeeTitleDescription() != null) {
                obj.addLikeClause(ProjEmployeeTitle.PROP_DESCRIPTION,
                        criteria.getEmployeeTitleDescription());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ProjEmployeeType</i> object containing
     * selection criteria obtained from an instance of <i>EmployeeTypeDto</i>.
     * 
     * @param criteria
     *            an instance of {@link EmployeeTypeDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>employeeTypeId</li>
     *            <li>employeeTypeDescription</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjEmployeeType}
     */
    public static final ProjEmployeeType createCriteria(EmployeeTypeDto criteria) {
        ProjEmployeeType obj = new ProjEmployeeType();
        if (criteria != null) {
            if (criteria.getEmployeeTypeId() > 0) {
                obj.addCriteria(ProjEmployeeType.PROP_EMPTYPEID,
                        criteria.getEmployeeTypeId());
            }
            if (criteria.getEmployeeTypeDescription() != null) {
                obj.addLikeClause(ProjEmployeeType.PROP_DESCRIPTION,
                        criteria.getEmployeeTypeDescription());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ProjTask</i> object containing selection
     * criteria obtained from an instance of <i>TaskDto</i>.
     * 
     * @param criteria
     *            an instance of {@link TaskDto} which the following properties
     *            are recognized:
     *            <ul>
     *            <li>taskId</li>
     *            <li>taskDescription</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjTask}
     */
    public static final ProjTask createCriteria(TaskDto criteria) {
        ProjTask obj = new ProjTask();
        if (criteria != null) {
            if (criteria.getTaskId() > 0) {
                obj.addCriteria(ProjTask.PROP_TASKID, criteria.getTaskId());
            }
            if (criteria.getTaskDescription() != null) {
                obj.addLikeClause(ProjTask.PROP_DESCRIPTION, criteria.getTaskDescription());
            }
            // TODO: Make preparations to handle billable as selection criteria.
            // if (criteria.getTaskBillable() != null) {
            // obj.addLikeClause(ProjTask.PROP_DESCRIPTION,
            // criteria.getTaskDescription());
            // }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ProjEvent</i> object containing selection
     * criteria obtained from an instance of <i>EventDto</i>.
     * 
     * @param criteria
     *            an instance of {@link EventDto} which the following properties
     *            are recognized:
     *            <ul>
     *            <li>eventId</li>
     *            <li>projectTaskId</li>
     *            <li>eventDate</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjEvent}
     */
    public static final ProjEvent createCriteria(EventDto criteria) {
        ProjEvent obj = new ProjEvent();
        if (criteria != null) {
            if (criteria.getEventId() > 0) {
                obj.addCriteria(ProjEvent.PROP_EVENTID, criteria.getEventId());
            }
            if (criteria.getProjectTaskId() > 0) {
                obj.addCriteria(ProjEvent.PROP_PROJECTTASKID, criteria.getProjectTaskId());
            }
            if (criteria.getEventDate() != null) {
                obj.addCriteria(ProjEvent.PROP_EVENTDATE, criteria.getEventDate());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwProjectClient</i> object containing selection
     * criteria obtained from an instance of <i>ProjectClientDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ProjectClientDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>clientId</li>
     *            <li>businessId</li>
     *            <li>projId</li>
     *            <li>clientName</li>
     *            <li>projectDescription</li>
     *            <li>effectiveDate</li>
     *            <li>endDate</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VwProjectClient}
     */
    public static final VwProjectClient createCriteria(ProjectClientDto criteria) {
        VwProjectClient obj = new VwProjectClient();
        if (criteria != null) {
            if (criteria.getClientId() > 0) {
                obj.addCriteria(VwProjectClient.PROP_CLIENTID,
                        criteria.getClientId());
            }
            if (criteria.getBusinessId() > 0) {
                obj.addCriteria(VwProjectClient.PROP_BUSINESSID,
                        criteria.getBusinessId());
            }
            if (criteria.getProjId() > 0) {
                obj.addCriteria(VwProjectClient.PROP_PROJID,
                        criteria.getProjId());
            }
            if (criteria.getClientName() != null) {
                obj.addLikeClause(VwProjectClient.PROP_NAME,
                        criteria.getClientName());
            }
            if (criteria.getProjectDescription() != null) {
                obj.addLikeClause(VwProjectClient.PROP_DESCRIPTION,
                        criteria.getProjectDescription());
            }
            if (criteria.getProjectEffectiveDate() != null) {
                obj.addCriteria(VwProjectClient.PROP_EFFECTIVEDATE,
                        criteria.getProjectEffectiveDate());
            }
            if (criteria.getProjectEndDate() != null) {
                obj.addCriteria(VwProjectClient.PROP_ENDDATE,
                        criteria.getProjectEndDate());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwEmployeeProjects</i> object containing
     * selection criteria obtained from an instance of
     * <i>ProjectEmployeeDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ProjectEmployeeDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>empId</li>
     *            <li>projId</li>
     *            <li>empProjId</li>
     *            <li>businessId</li>
     *            <li>clientId</li>
     *            <li>projEmpEffectiveDate</li>
     *            <li>rojEmpEndDate</li>
     *            <li>clientName</li>
     *            <li>accountNo</li>
     *            <li>projectName</li>
     *            <li>projEffectiveDate</li>
     *            <li>projEndDate</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VwEmployeeProjects}
     */
    public static final VwEmployeeProjects createCriteria(
            ProjectEmployeeDto criteria) {
        VwEmployeeProjects obj = new VwEmployeeProjects();
        if (criteria != null) {
            if (criteria.getEmpId() > 0) {
                obj.addCriteria(VwEmployeeProjects.PROP_EMPID,
                        criteria.getEmpId());
            }
            if (criteria.getProjId() > 0) {
                obj.addCriteria(VwEmployeeProjects.PROP_PROJID,
                        criteria.getProjId());
            }
            if (criteria.getEmpProjId() > 0) {
                obj.addCriteria(VwEmployeeProjects.PROP_EMPPROJID,
                        criteria.getEmpProjId());
            }
            if (criteria.getBusinessId() > 0) {
                obj.addCriteria(VwEmployeeProjects.PROP_BUSINESSID,
                        criteria.getBusinessId());
            }
            if (criteria.getClientId() > 0) {
                obj.addCriteria(VwEmployeeProjects.PROP_CLIENTID,
                        criteria.getClientId());
            }
            if (criteria.getProjEmpEffectiveDate() != null) {
                obj.addCriteria(VwEmployeeProjects.PROP_PROJEMPEFFECTIVEDATE,
                        criteria.getProjEmpEffectiveDate());
            }
            if (criteria.getProjEmpEndDate() != null) {
                obj.addCriteria(VwEmployeeProjects.PROP_PROJEMPENDDATE,
                        criteria.getProjEmpEndDate());
            }
            if (criteria.getClientName() != null) {
                obj.addLikeClause(VwEmployeeProjects.PROP_CLIENTNAME,
                        criteria.getClientName());
            }
            if (criteria.getAccountNo() != null) {
                obj.addLikeClause(VwEmployeeProjects.PROP_ACCOUNTNO,
                        criteria.getAccountNo());
            }
            if (criteria.getProjectDescription() != null) {
                obj.addLikeClause(VwEmployeeProjects.PROP_PROJECTNAME,
                        criteria.getProjectDescription());
            }
            if (criteria.getProjectEffectiveDate() != null) {
                obj.addCriteria(VwEmployeeProjects.PROP_PROJEFFECTIVEDATE,
                        criteria.getProjectEffectiveDate());
            }
            if (criteria.getProjectEndDate() != null) {
                obj.addCriteria(VwEmployeeProjects.PROP_PROJENDDATE,
                        criteria.getProjectEndDate());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ProjProjectTask</i> object containing selection
     * criteria obtained from an instance of <i>ProjectTaskDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ProjectTaskDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>projectTaskId</li>
     *            <li>projId</li>
     *            <li>taskId</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjProjectTask}
     */
    public static final ProjProjectTask createCriteria(ProjectTaskDto criteria) {
        ProjProjectTask obj = new ProjProjectTask();
        if (criteria != null) {
            if (criteria.getProjectTaskId() > 0) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_PROJECTTASKID,
                        criteria.getProjectTaskId());
            }
            if (criteria.getProjId() > 0) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_PROJECTID,
                        criteria.getProjId());
            }
            if (criteria.getTaskId() > 0) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_TASKID,
                        criteria.getTaskId());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwTimesheetProjectTask</i> object containing
     * selection criteria obtained from an instance of <i>ProjectTaskDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ProjectTaskDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>projectTaskId</li>
     *            <li>projId</li>
     *            <li>taskId</li>
     *            <li>clientId</li>
     *            <li>projectDescription</li>
     *            <li>taskDescription</li>
     *            <li>projectEffectiveDate</li>
     *            <li>projectEndDate</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VwTimesheetProjectTask}
     */
    public static final VwTimesheetProjectTask createCriteriaExt(
            ProjectTaskDto criteria) {
        VwTimesheetProjectTask obj = new VwTimesheetProjectTask();
        if (criteria != null) {
            if (criteria.getProjectTaskId() > 0) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_PROJECTTASKID,
                        criteria.getProjectTaskId());
            }
            if (criteria.getProjId() > 0) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_PROJECTID,
                        criteria.getProjId());
            }
            if (criteria.getTaskId() > 0) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_TASKID,
                        criteria.getTaskId());
            }
            if (criteria.getClientId() > 0) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_CLIENTID,
                        criteria.getClientId());
            }
            if (criteria.getProjectDescription() != null) {
                obj.addLikeClause(VwTimesheetProjectTask.PROP_PROJECTNAME,
                        criteria.getProjectDescription());
            }
            if (criteria.getTaskDescription() != null) {
                obj.addLikeClause(VwTimesheetProjectTask.PROP_TASKNAME,
                        criteria.getTaskDescription());
            }
            if (criteria.getProjectEffectiveDate() != null) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_EFFECTIVEDATE,
                        criteria.getProjectEffectiveDate());
            }
            if (criteria.getProjectEndDate() != null) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_ENDDATE,
                        criteria.getProjectEndDate());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwTimesheetEventList</i> object containing
     * selection criteria obtained from an instance of <i>ProjectEventDto</i>.
     * <p>
     * This criteria building method does not recognize any timemsheet reltated
     * items. For example, timesheetId cannot be included as part of the
     * predicate. See
     * {@link org.dao.timesheet.TimesheetDaoFactory#createCriteria(ProjectEventDto)}
     * when the desire to use timesheet related data items as part of the
     * criteria.
     * 
     * @param criteria
     *            an instance of {@link ProjectEventDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>eventId</li> <li>projectTaskId</li> <li>projId</li> <li>
     *            taskId</li> <li>clientId</li> <li>eventDate</li> <li>
     *            projectDescription</li> <li>taskDescription</li> <li>
     *            projectEffectiveDate</li> <li>projectEndDate</li> <li>criteria
     *            - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VwTimesheetEventList}
     */
    public static final VwTimesheetEventList createCriteria(
            ProjectEventDto criteria) {
        VwTimesheetEventList obj = new VwTimesheetEventList();
        if (criteria != null) {
            if (criteria.getEventId() > 0) {
                obj.addCriteria(VwTimesheetEventList.PROP_EVENTID,
                        criteria.getEventId());
            }
            if (criteria.getProjId() > 0) {
                obj.addCriteria(VwTimesheetEventList.PROP_PROJECTID,
                        criteria.getProjId());
            }
            if (criteria.getTaskId() > 0) {
                obj.addCriteria(VwTimesheetEventList.PROP_TASKID,
                        criteria.getTaskId());
            }
            if (criteria.getClientId() > 0) {
                obj.addCriteria(VwTimesheetEventList.PROP_CLIENTID,
                        criteria.getClientId());
            }
            if (criteria.getEventDate() != null) {
                obj.addCriteria(VwTimesheetEventList.PROP_EVENTDATE,
                        criteria.getEventDate());
            }
            if (criteria.getProjectDescription() != null) {
                obj.addLikeClause(VwTimesheetEventList.PROP_PROJECTNAME,
                        criteria.getProjectDescription());
            }
            if (criteria.getTaskDescription() != null) {
                obj.addLikeClause(VwTimesheetEventList.PROP_TASKNAME,
                        criteria.getTaskDescription());
            }
            if (criteria.getProjectEffectiveDate() != null) {
                obj.addCriteria(VwTimesheetEventList.PROP_EFFECTIVEDATE,
                        criteria.getProjectEffectiveDate());
            }
            if (criteria.getProjectEndDate() != null) {
                obj.addCriteria(VwTimesheetEventList.PROP_ENDDATE,
                        criteria.getProjectEndDate());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Create a ProjClient object from a ClientDto object
     * 
     * @param dto
     *            an instance of {@link ClientDto}
     * @return an instance of {@link ProjClient}
     */
    public static final ProjClient createOrm(ClientDto dto) {
        ProjClient obj = new ProjClient();
        obj.setAccountNo(dto.getAccountNo());
        obj.setBillRate(dto.getClientBillRate());
        obj.setBusinessId(dto.getBusinessId());
        obj.setClientId(dto.getClientId());
        obj.setContactEmail(dto.getClientContactEmail());
        obj.setContactExt(dto.getClientContactExt());
        obj.setContactFirstname(dto.getClientContactFirstname());
        obj.setContactLastname(dto.getClientContactLastname());
        obj.setContactPhone(dto.getClientContactPhone());
        obj.setOtBillRate(dto.getClientOtBillRate());
        obj.setName(dto.getClientName());
        obj.setDateCreated(dto.getDateCreated());
        obj.setDateUpdated(dto.getDateUpdated());
        obj.setUserId(dto.getUpdateUserId());
        return obj;
    }

    /**
     * Create a ProjProject object from a ProjectDto object.
     * 
     * @param dto
     *            an instance of {@link ProjectDto}
     * @return an instance of {@link ProjProject}
     */
    public static final ProjProject createOrm(ProjectDto dto) {
        ProjProject obj = new ProjProject();
        obj.setProjId(dto.getProjId());
        obj.setClientId(dto.getClientId());
        obj.setDescription(dto.getProjectDescription());
        obj.setEffectiveDate(dto.getProjectEffectiveDate());
        obj.setEndDate(dto.getProjectEndDate());
        obj.setDateCreated(dto.getDateCreated());
        obj.setDateUpdated(dto.getDateUpdated());
        obj.setUserId(dto.getUpdateUserId());
        return obj;
    }

    /**
     * Create a ProjEmployee object from a EmployeeDto object.
     * 
     * @param dto
     *            an instance of {@link EmployeeDto}
     * @return an instance of {@link ProjEmployee}
     */
    public static final ProjEmployee createOrm(EmployeeDto dto) {
        ProjEmployee obj = new ProjEmployee();
        obj.setEmpId(dto.getEmployeeId());
        obj.setEmpTitleId(dto.getEmployeeTitleId());
        obj.setEmpTypeId(dto.getEmployeeTypeId());
        obj.setManagerId(dto.getManagerId());
        obj.setLoginId(dto.getLoginId());
        obj.setLoginName(dto.getLoginName());
        obj.setStartDate(dto.getStartDate());
        obj.setTerminationDate(dto.getTerminationDate());
        obj.setFirstname(dto.getEmployeeFirstname());
        obj.setLastname(dto.getEmployeeLastname());
        obj.setSsn(dto.getSsn());
        obj.setEmail(dto.getEmployeeEmail());
        obj.setCompanyName(dto.getEmployeeCompanyName());
        obj.setIsManager(dto.getIsManager());
        obj.setDateCreated(dto.getDateCreated());
        obj.setDateUpdated(dto.getDateUpdated());
        obj.setUserId(dto.getUpdateUserId());
        return obj;
    }

    /**
     * Create a ProjEmployeeTitle object from a EmployeeTitleDto object.
     * 
     * @param dto
     *            an instance of {@link EmployeeTitleDto}
     * @return an instance of {@link ProjEmployeeTitle}
     */
    public static final ProjEmployeeTitle createOrm(EmployeeTitleDto dto) {
        ProjEmployeeTitle obj = new ProjEmployeeTitle();
        obj.setEmpTitleId(dto.getEmployeeTitleId());
        obj.setDescription(dto.getEmployeeTitleDescription());
        return obj;
    }

    /**
     * Create a ProjEmployeeType object from a EmployeeTypeDto object.
     * 
     * @param dto
     *            an instance of {@link EmployeeTypeDto}
     * @return an instance of {@link ProjEmployeeType}
     */
    public static final ProjEmployeeType createOrm(EmployeeTypeDto dto) {
        ProjEmployeeType obj = new ProjEmployeeType();
        obj.setEmpTypeId(dto.getEmployeeTypeId());
        obj.setDescription(dto.getEmployeeTypeDescription());
        return obj;
    }

    /**
     * Create a ProjTask object from a TaskDto object.
     * 
     * @param dto
     *            an instance of {@link TaskDto}
     * @return an instance of {@link ProjTask}
     */
    public static final ProjTask createOrm(TaskDto dto) {
        ProjTask obj = new ProjTask();
        obj.setTaskId(dto.getTaskId());
        obj.setDescription(dto.getTaskDescription());
        obj.setBillable(dto.getTaskBillable());
        obj.setDateCreated(dto.getDateCreated());
        obj.setDateUpdated(dto.getDateUpdated());
        obj.setUserId(dto.getUpdateUserId());
        return obj;
    }

    /**
     * Create a ProjEmployeeProject object from a ProjectEmployeeDto object.
     * 
     * @param dto
     *            an instance of {@link ProjectEmployeeDto}
     * @return an instance of {@link ProjEmployeeProject}
     */
    public static final ProjEmployeeProject createOrm(ProjectEmployeeDto dto) {
        ProjEmployeeProject obj = new ProjEmployeeProject();
        obj.setEmpProjId(dto.getEmpProjId());
        obj.setEmpId(dto.getEmpId());
        obj.setProjId(dto.getProjId());
        obj.setEffectiveDate(dto.getProjEmpEffectiveDate());
        obj.setEndDate(dto.getProjEmpEndDate());
        obj.setHourlyRate(dto.getClientBillRate());
        obj.setHourlyOverRate(dto.getClientOtBillRate());
        obj.setFlatRate(dto.getFlatRate());
        obj.setComments(dto.getComments());
        obj.setDateCreated(dto.getDateCreated());
        obj.setDateUpdated(dto.getDateUpdated());
        obj.setUserId(dto.getUpdateUserId());
        obj.setIpCreated(dto.getIpCreated());
        obj.setIpUpdated(dto.getIpUpdated());
        return obj;
    }

    /**
     * Create a ProjProjectTask object from a ProjectTaskDto object.
     * 
     * @param dto
     *            an instance of {@link ProjectTaskDto}
     * @return an instance of {@link ProjProjectTask}
     */
    public static final ProjProjectTask createOrm(ProjectTaskDto dto) {
        ProjProjectTask obj = new ProjProjectTask();
        obj.setProjectTaskId(dto.getProjectTaskId());
        obj.setTaskId(dto.getTaskId());
        obj.setProjId(dto.getProjId());
        obj.setTimesheetId(dto.getTimesheetId());
        return obj;
    }

    /**
     * Creates a <i>ProjEvent</i> ORM object from the <i>EventDto</i>.
     * 
     * @param evt
     *            an instance of {@link EventDto}
     * @return {@link ProjEvent} object
     */
    public static final ProjEvent createOrm(EventDto evt) {
        ProjEvent obj = new ProjEvent();
        obj.setEventId(evt.getEventId());
        obj.setProjectTaskId(evt.getProjectTaskId());
        obj.setEventDate(evt.getEventDate());
        obj.setHours(evt.getEventHours());
        obj.setDateCreated(evt.getDateCreated());
        obj.setDateUpdated(evt.getDateUpdated());
        obj.setUserId(evt.getUpdateUserId());
        return obj;
    }

    /**
     * Returns a List of client id's from a List of <i>ClientDto</i> objects.
     * <p>
     * The clinet id is synonymous to the customer id in the accounting system.
     * 
     * @param client
     *            List of {@link ClientDto}
     * @return List <BigInteger>
     */
    public static final List<BigInteger> getCustomerId(List<ClientDto> client) {
        if (client == null) {
            return null;
        }
        List<BigInteger> list = new ArrayList<BigInteger>();
        for (ClientDto item : client) {
            list.add(BigInteger.valueOf(item.getClientId()));
        }
        return list;
    }

//    /**
//     * Merges customer information contained in the result set of type,
//     * RSCustomerSearch, to a list of ClientDto objects.
//     * 
//     * @param contacts
//     *            List of {@link ClientDto} objects
//     * @param customerInfo
//     *            An instnace of {@link RSCustomerSearch}
//     * @throws InvalidDataException
//     *             When <i>contacts</i> is null
//     */
//    public static final void mergeExtendedContactInfo(List<ClientDto> contacts,
//            RSCustomerSearch customerInfo) throws InvalidDataException {
//
//        if (contacts == null) {
//            throw new InvalidDataException(
//                    "List of ClientDto is null or invalid for merge extended contact info operation");
//        }
//        Map<Integer, CustomerType> custTypeMap = ProjectAdminDaoFactory
//                .createCustomerTypeMap(customerInfo);
//        if (custTypeMap == null) {
//            // Nothing to merge
//            return;
//        }
//
//        for (ClientDto c : contacts) {
//            CustomerType cust = custTypeMap.get(c.getClientId());
//            if (cust == null) {
//                continue;
//            }
//            c.setAccountNo(cust.getAccountNo());
//            if (cust.getContactDetails() != null) {
//                c.setBusinessId(cust.getContactDetails().getBusinessId()
//                        .intValue());
//                c.setClientName(cust.getContactDetails().getLongName());
//                c.setClientContactEmail(cust.getContactDetails()
//                        .getContactEmail());
//                c.setClientContactExt(cust.getContactDetails().getContactExt());
//                c.setClientContactFirstname(cust.getContactDetails()
//                        .getContactFirstname());
//                c.setClientContactLastname(cust.getContactDetails()
//                        .getContactLastname());
//                c.setClientContactPhone(cust.getContactDetails()
//                        .getContactPhone());
//            }
//        }
//        return;
//    }
//
//    /**
//     * Creates a Map of {@link CustomerType} objects from the web service result
//     * set type, RSCustomerSearch.
//     * <p>
//     * The newly created Map is keyed by customer id.
//     * 
//     * @param customerInfo
//     *            An instance of {@link RSCustomerSearch}
//     * @return Map<Integer, CustomerType>
//     */
//    public static final Map<Integer, CustomerType> createCustomerTypeMap(
//            RSCustomerSearch customerInfo) {
//        if (customerInfo == null) {
//            return null;
//        }
//        List<CustomerType> list = customerInfo.getCustomerList();
//        Map<Integer, CustomerType> map = new HashMap<Integer, CustomerType>();
//        for (CustomerType cust : list) {
//            map.put(cust.getCustomerId().intValue(), cust);
//        }
//        return map;
//    }
}
