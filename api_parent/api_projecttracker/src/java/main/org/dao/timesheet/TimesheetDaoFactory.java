package org.dao.timesheet;

import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.mapping.orm.rmt2.ProjTimesheetStatus;
import org.dao.mapping.orm.rmt2.VwClientTimesheetSummary;
import org.dao.mapping.orm.rmt2.VwTimesheetEventList;
import org.dao.mapping.orm.rmt2.VwTimesheetHours;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
import org.dao.mapping.orm.rmt2.VwTimesheetSummary;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetClientDto;
import org.dto.TimesheetDto;
import org.dto.TimesheetHistDto;
import org.dto.TimesheetHoursDto;
import org.dto.TimesheetHoursSummaryDto;
import org.dto.TimesheetStatusDto;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * Factory class for creating Timesheet DAO related objects.
 * 
 * @author Roy Terrell
 * 
 */
public class TimesheetDaoFactory extends RMT2Base {

    /**
     * Default constructor
     */
    public TimesheetDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>ProjectAdminDao</i> using the RMT2 transaction
     * ORM implementation.
     * 
     * @return an instance of {@link ProjectAdminDao}
     */
    public TimesheetDao createRmt2OrmDao() {
        TimesheetDao dao = new Rmt2TimesheetDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>ProjectAdminDao</i> using the RMT2 transaction
     * ORM implementation.
     * 
     * @param appName
     *            the name of the application to obtain DAO.
     * @return an instance of {@link ProjectAdminDao}
     */
    public TimesheetDao createRmt2OrmDao(String appName) {
        TimesheetDao dao = new Rmt2TimesheetDaoImpl(appName);
        return dao;
    }

    /**
     * Creates an instance of <i>TimesheetDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     * 
     * @return an instance of {@link TimesheetDao}
     */
    public TimesheetDao createRmt2OrmDao(DaoClient dao) {
        TimesheetDao d = new Rmt2TimesheetDaoImpl(dao.getClient());
        d.setDaoUser(dao.getDaoUser());
        return d;
    }

    /**
     * Creates and returns an <i>ProjTimesheet</i> object containing selection
     * criteria obtained from an instance of <i>TimesheetDto</i>.
     * 
     * @param criteria
     *            an instance of {@link TimesheetDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>timesheetId</li>
     *            <li>clientId</li>
     *            <li>empid</li>
     *            <li>projId</li>
     *            <li>beginPeriod</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjTimesheet}
     */
    public static final ProjTimesheet createCriteria(TimesheetDto criteria) {
        ProjTimesheet obj = new ProjTimesheet();
        if (criteria != null) {
            if (criteria.getTimesheetId() > 0) {
                obj.addCriteria(ProjTimesheet.PROP_TIMESHEETID,
                        criteria.getTimesheetId());
            }
            if (criteria.getClientId() > 0) {
                obj.addCriteria(ProjTimesheet.PROP_CLIENTID,
                        criteria.getClientId());
            }
            if (criteria.getEmpId() > 0) {
                obj.addCriteria(ProjTimesheet.PROP_EMPID, criteria.getEmpId());
            }
            if (criteria.getProjId() > 0) {
                obj.addCriteria(ProjTimesheet.PROP_PROJID, criteria.getProjId());
            }
            if (criteria.getBeginPeriod() != null) {
                obj.addCriteria(ProjTimesheet.PROP_BEGINPERIOD,
                        criteria.getBeginPeriod());
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
     * 
     * @param criteria
     *            an instance of {@link ProjectEventDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>eventId</li>
     *            <li>projectTaskId</li>
     *            <li>timesheetId</li>
     *            <li>projId</li>
     *            <li>taskId</li>
     *            <li>clientId</li>
     *            <li>eventDate</li>
     *            <li>projectDescription</li>
     *            <li>taskDescription</li>
     *            <li>projectEffectiveDate</li>
     *            <li>projectEndDate</li>
     *            <li>criteria - include custom selection criteria if available</li>
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
            if (criteria.getTimesheetId() > 0) {
                obj.addCriteria(VwTimesheetEventList.PROP_TIMESHEETID,
                        criteria.getTimesheetId());
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
     * Creates and returns an <i>VwTimesheetList</i> object containing selection
     * criteria obtained from an instance of <i>TimesheetDto</i>.
     * 
     * @param criteria
     *            an instance of {@link TimesheetDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>timesheetId</li>
     *            <li>clientId</li>
     *            <li>empid</li>
     *            <li>projId</li>
     *            <li>beginPeriod</li>
     *            <li>statusId</li>
     *            <li>statusName</li>
     *            <li>employeeTypeId</li>
     *            <li>employeeFirstname</li>
     *            <li>employeeLastname</li>
     *            <li>managerId</li>
     *            <li>clientName</li>
     *            <li>clientAccountNo</li>
     *            <li>timesheetIdList</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VwTimesheetList}
     */
    public static final VwTimesheetList createCriteriaExt(TimesheetDto criteria) {
        VwTimesheetList obj = new VwTimesheetList();
        if (criteria != null) {
            if (criteria.getTimesheetId() > 0) {
                obj.addCriteria(VwTimesheetList.PROP_TIMESHEETID,
                        criteria.getTimesheetId());
            }
            if (criteria.getClientId() > 0) {
                obj.addCriteria(VwTimesheetList.PROP_CLIENTID,
                        criteria.getClientId());
            }
            if (criteria.getEmpId() > 0) {
                obj.addCriteria(VwTimesheetList.PROP_EMPID, criteria.getEmpId());
            }
            if (criteria.getProjId() > 0) {
                obj.addCriteria(VwTimesheetList.PROP_PROJID,
                        criteria.getProjId());
            }
            if (criteria.getBeginPeriod() != null) {
                obj.addCriteria(VwTimesheetList.PROP_BEGINPERIOD,
                        criteria.getBeginPeriod());
            }
            if (criteria.getStatusId() > 0) {
                obj.addCriteria(VwTimesheetList.PROP_TIMESHEETSTATUSID,
                        criteria.getStatusId());
            }
            if (criteria.getStatusName() != null) {
                obj.addLikeClause(VwTimesheetList.PROP_STATUSNAME,
                        criteria.getStatusName());
            }
            if (criteria.getEmployeeTypeId() > 0) {
                obj.addCriteria(VwTimesheetList.PROP_TYPEID,
                        criteria.getEmployeeTypeId());
            }
            if (criteria.getEmployeeFirstname() != null) {
                obj.addLikeClause(VwTimesheetList.PROP_FIRSTNAME,
                        criteria.getEmployeeFirstname());
            }
            if (criteria.getEmployeeLastname() != null) {
                obj.addLikeClause(VwTimesheetList.PROP_LASTNAME,
                        criteria.getEmployeeLastname());
            }
            if (criteria.getEmployeeManagerId() > 0) {
                obj.addCriteria(VwTimesheetList.PROP_MANAGERID,
                        criteria.getEmployeeManagerId());
            }
            if (criteria.getClientName() != null) {
                obj.addLikeClause(VwTimesheetList.PROP_CLIENTNAME,
                        criteria.getClientName());
            }
            if (criteria.getClientAccountNo() != null) {
                obj.addLikeClause(VwTimesheetList.PROP_ACCOUNTNO,
                        criteria.getClientAccountNo());
            }
            if (criteria.getTimesheetIdList() != null
                    && criteria.getTimesheetIdList().size() > 0) {
                Integer[] values = criteria.getTimesheetIdList().toArray(
                        new Integer[criteria.getTimesheetIdList().size()]);
                obj.addInClause(VwTimesheetList.PROP_TIMESHEETID, values);
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
     *            <li>timesheetId</li>
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
            if (criteria.getTimesheetId() > 0) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_TIMESHEETID,
                        criteria.getTimesheetId());
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
     *            <li>timesheetId</li>
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
            if (criteria.getTimesheetId() > 0) {
                obj.addCriteria(VwTimesheetProjectTask.PROP_TIMESHEETID,
                        criteria.getTimesheetId());
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
     * Creates and returns an <i>ProjTimesheetStatus</i> object containing
     * selection criteria obtained from an instance of
     * <i>TimesheetStatusDto</i>.
     * 
     * @param criteria
     *            an instance of {@link TimesheetStatusDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>statusId</li>
     *            <li>statusName</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjTimesheetStatus}
     */
    public static final ProjTimesheetStatus createCriteria(
            TimesheetStatusDto criteria) {
        ProjTimesheetStatus obj = new ProjTimesheetStatus();
        if (criteria != null) {
            if (criteria.getStatusId() > 0) {
                obj.addCriteria(ProjTimesheetStatus.PROP_TIMESHEETSTATUSID,
                        criteria.getStatusId());
            }
            if (criteria.getStatusName() != null) {
                obj.addLikeClause(ProjTimesheetStatus.PROP_NAME,
                        criteria.getStatusName());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ProjTimesheetHist</i> object containing
     * selection criteria obtained from an instance of <i>TimesheetHistDto</i>.
     * 
     * @param criteria
     *            an instance of {@link TimesheetHistDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>statusHistId</li>
     *            <li>statusId</li>
     *            <li>statusEffectiveDate</li>
     *            <li>statusEndDate</li>
     *            <li>currentStatusFlag</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link ProjTimesheetHist}
     */
    public static final ProjTimesheetHist createCriteria(
            TimesheetHistDto criteria) {
        ProjTimesheetHist obj = new ProjTimesheetHist();
        if (criteria != null) {
            if (criteria.getStatusHistId() > 0) {
                obj.addCriteria(ProjTimesheetHist.PROP_TIMESHEETHISTID,
                        criteria.getStatusId());
            }
            if (criteria.getStatusId() > 0) {
                obj.addCriteria(ProjTimesheetHist.PROP_TIMESHEETSTATUSID,
                        criteria.getStatusId());
            }
            if (criteria.getStatusEffectiveDate() != null) {
                obj.addCriteria(ProjTimesheetHist.PROP_EFFECTIVEDATE,
                        criteria.getStatusEffectiveDate());
            }
            if (criteria.getStatusEndDate() != null) {
                obj.addCriteria(ProjTimesheetHist.PROP_ENDDATE,
                        criteria.getStatusEndDate());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
            if (criteria.isCurrentStatusFlag()) {
                obj.addCustomCriteria(" end_date is null ");
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwClientTimesheetSummary</i> object containing
     * selection criteria obtained from an instance of
     * <i>TimesheetClientDto</i>.
     * 
     * @param criteria
     *            an instance of {@link TimesheetClientDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>clientId</li>
     *            <li>businessId</li>
     *            <li>clientName</li>
     *            <li>clientAccountNo</li>
     *            <li>statusId</li>
     *            <li>statusName</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VwClientTimesheetSummary}
     */
    public static final VwClientTimesheetSummary createCriteria(
            TimesheetClientDto criteria) {
        VwClientTimesheetSummary obj = new VwClientTimesheetSummary();
        if (criteria != null) {
            if (criteria.getClientId() > 0) {
                obj.addCriteria(VwClientTimesheetSummary.PROP_CLIENTID,
                        criteria.getClientId());
            }
            if (criteria.getBusinessId() > 0) {
                obj.addCriteria(VwClientTimesheetSummary.PROP_BUSINESSID,
                        criteria.getBusinessId());
            }
            if (criteria.getClientName() != null) {
                obj.addCriteria(VwClientTimesheetSummary.PROP_NAME,
                        criteria.getClientName());
            }
            if (criteria.getClientAccountNo() != null) {
                obj.addCriteria(VwClientTimesheetSummary.PROP_ACCOUNTNO,
                        criteria.getClientAccountNo());
            }
            if (criteria.getStatusId() > 0) {
                obj.addCriteria(VwClientTimesheetSummary.PROP_STATUSID,
                        criteria.getStatusId());
            }
            if (criteria.getStatusName() != null) {
                obj.addCriteria(VwClientTimesheetSummary.PROP_STATUSNAME,
                        criteria.getStatusName());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwTimesheetHours</i> object containing
     * selection criteria obtained from an instance of <i>TimesheetHoursDto</i>.
     * 
     * @param criteria
     *            an instance of {@link TimesheetHoursDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>timesheetId</li>
     *            <li>clientId</li>
     *            <li>empid</li>
     *            <li>projId</li>
     *            <li>beginPeriod</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VwTimesheetHours}
     */
    public static final VwTimesheetHours createCriteria(
            TimesheetHoursDto criteria) {
        VwTimesheetHours obj = new VwTimesheetHours();
        if (criteria != null) {
            if (criteria.getTimesheetId() > 0) {
                obj.addCriteria(VwTimesheetHours.PROP_TIMESHEETID,
                        criteria.getTimesheetId());
            }
            if (criteria.getClientId() > 0) {
                obj.addCriteria(VwTimesheetHours.PROP_CLIENTID,
                        criteria.getClientId());
            }
            if (criteria.getEmpId() > 0) {
                obj.addCriteria(VwTimesheetHours.PROP_EMPLOYEEID,
                        criteria.getEmpId());
            }
            if (criteria.getProjId() > 0) {
                obj.addCriteria(VwTimesheetHours.PROP_PROJECTID,
                        criteria.getProjId());
            }
            if (criteria.getBeginPeriod() != null) {
                obj.addCriteria(VwTimesheetHours.PROP_TIMESHEETBEGINPERIOD,
                        criteria.getBeginPeriod());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwTimesheetSummary</i> object containing
     * selection criteria obtained from an instance of
     * <i>TimesheetHoursSummaryDto</i>.
     * 
     * @param criteria
     *            an instance of {@link TimesheetHoursSummaryDto} which the
     *            following properties are recognized:
     *            <ul>
     *            <li>timesheetId</li>
     *            <li>employeeFullName (First name last name)</li>
     *            <li>endPeriod</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VwTimesheetSummary}
     */
    public static final VwTimesheetSummary createCriteria(
            TimesheetHoursSummaryDto criteria) {
        VwTimesheetSummary obj = new VwTimesheetSummary();
        if (criteria != null) {
            if (criteria.getTimesheetId() > 0) {
                obj.addCriteria(VwTimesheetSummary.PROP_TIMESHEETID,
                        criteria.getTimesheetId());
            }
            if (criteria.getEmployeeFullName() != null) {
                obj.addLikeClause(VwTimesheetSummary.PROP_SHORTNAME,
                        criteria.getEmployeeFullName());
            }
            if (criteria.getEndPeriod() != null) {
                obj.addCriteria(VwTimesheetSummary.PROP_ENDPERIOD,
                        criteria.getEndPeriod());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates a <i>ProjTimesheet</i> ORM object from the <i>TimesheetDto</i>.
     * 
     * @param ts
     *            an instance of {@link TimesheetDto}
     * @return {@link ProjTimesheet} object
     */
    public static final ProjTimesheet createOrm(TimesheetDto ts) {
        ProjTimesheet obj = new ProjTimesheet();
        obj.setTimesheetId(ts.getTimesheetId());
        obj.setClientId(ts.getClientId());
        obj.setEmpId(ts.getEmpId());
        obj.setDisplayValue(ts.getDisplayValue());
        obj.setBeginPeriod(ts.getBeginPeriod());
        obj.setEndPeriod(ts.getEndPeriod());
        obj.setComments(ts.getComments());
        obj.setExtRef(ts.getExtRef());
        obj.setDocumentId(ts.getDocumentId());
        obj.setProjId(ts.getProjId());
        obj.setDateCreated(ts.getDateCreated());
        obj.setDateUpdated(ts.getDateUpdated());
        obj.setUserId(ts.getUpdateUserId());
        obj.setIpCreated(ts.getIpCreated());
        obj.setIpUpdated(ts.getIpUpdated());
        return obj;
    }

    /**
     * Creates a <i>ProjProjectTask</i> ORM object from the
     * <i>ProjectTaskDto</i>.
     * 
     * @param pt
     *            an instance of {@link ProjectTaskDto}
     * @return {@link ProjProjectTask} object
     */
    public static final ProjProjectTask createOrm(ProjectTaskDto pt) {
        ProjProjectTask obj = new ProjProjectTask();
        obj.setProjectTaskId(pt.getProjectTaskId());
        obj.setProjId(pt.getProjId());
        obj.setTaskId(pt.getTaskId());
        obj.setTimesheetId(pt.getTimesheetId());
        return obj;
    }

}
