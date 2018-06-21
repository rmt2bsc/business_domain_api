package org.dao.admin;

import java.util.ArrayList;
import java.util.List;

import org.dao.AbstractProjecttrackerDaoImpl;
import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeProject;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProject;
import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.dao.mapping.orm.rmt2.ProjTask;
import org.dao.mapping.orm.rmt2.VwEmployeeExt;
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
import org.dto.adapter.orm.EmployeeObjectFactory;
import org.dto.adapter.orm.ProjectObjectFactory;

import com.api.persistence.PersistenceClient;
import com.api.util.RMT2Date;
import com.api.util.UserTimestamp;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * An implementation of {@link ProjectAdminDao}. It provides functionality that
 * inserts, updates, deletes, and queries administrative project entities.
 * <p>
 * Administrative project enties include clients, projects, employees, tasks,
 * and events data.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2ProjectAdminDaoImpl extends AbstractProjecttrackerDaoImpl implements ProjectAdminDao {

    /**
     * Creates a Rmt2ProjectAdminDaoImpl object with its own persistent client.
     */
    protected Rmt2ProjectAdminDaoImpl() {
        return;
    }

    /**
     * @param appName
     */
    protected Rmt2ProjectAdminDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Creates a Rmt2DisbursementDaoImpl object with a shared persistent client.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     */
    protected Rmt2ProjectAdminDaoImpl(PersistenceClient client) {
        super(client);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchClient(org.dto.ClientDto)
     */
    @Override
    public List<ClientDto> fetchClient(ClientDto criteria) throws ProjectAdminDaoException {
        ProjClient obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ProjClient.PROP_NAME, ProjClient.ORDERBY_ASCENDING);

        List<ProjClient> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new ProjectAdminDaoException(e);
        }

        List<ClientDto> list = new ArrayList<ClientDto>();
        for (ProjClient item : results) {
            ClientDto dto = ProjectObjectFactory.createClientDtoInstance(item);
            list.add(dto);
        }
        return list;
    }



    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchProject(org.dto.ProjectDto)
     */
    @Override
    public List<ProjectDto> fetchProject(ProjectDto criteria) throws ProjectAdminDaoException {
        ProjProject obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ProjProject.PROP_DESCRIPTION, ProjProject.ORDERBY_ASCENDING);
        obj.addOrderBy(ProjProject.PROP_PROJID, ProjProject.ORDERBY_ASCENDING);

        List<ProjProject> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new ProjectAdminDaoException(e);
        }

        List<ProjectDto> list = new ArrayList<ProjectDto>();
        for (ProjProject item : results) {
            ProjectDto dto = ProjectObjectFactory.createProjectDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchEmployee(org.dto.EmployeeDto)
     */
    @Override
    public List<EmployeeDto> fetchEmployee(EmployeeDto criteria) throws EmployeeDaoException {
        ProjEmployee obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ProjEmployee.PROP_LASTNAME, ProjEmployee.ORDERBY_ASCENDING);
        obj.addOrderBy(ProjEmployee.PROP_FIRSTNAME, ProjEmployee.ORDERBY_ASCENDING);
        obj.addOrderBy(ProjEmployee.PROP_EMPID, ProjEmployee.ORDERBY_ASCENDING);

        List<ProjEmployee> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new EmployeeDaoException(e);
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>();
        for (ProjEmployee item : results) {
            EmployeeDto dto = EmployeeObjectFactory.createEmployeeDtoInstance(item);
            list.add(dto);
        }
        return list;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchEmployee(org.dto.EmployeeDto)
     */
    @Override
    public List<EmployeeDto> fetchEmployeeExt(EmployeeDto criteria) throws EmployeeDaoException {
        VwEmployeeExt obj = ProjectAdminDaoFactory.createCriteriaExt(criteria);
        obj.addOrderBy(ProjEmployee.PROP_LASTNAME, ProjEmployee.ORDERBY_ASCENDING);
        obj.addOrderBy(ProjEmployee.PROP_FIRSTNAME, ProjEmployee.ORDERBY_ASCENDING);
        obj.addOrderBy(ProjEmployee.PROP_EMPID, ProjEmployee.ORDERBY_ASCENDING);

        List<VwEmployeeExt> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new EmployeeDaoException(e);
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>();
        for (VwEmployeeExt item : results) {
            EmployeeDto dto = EmployeeObjectFactory.createEmployeeExtendedDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchEmployeeTitle(org.dto.EmployeeTitleDto)
     */
    @Override
    public List<EmployeeTitleDto> fetchEmployeeTitle(EmployeeTitleDto criteria) throws EmployeeDaoException {
        ProjEmployeeTitle obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ProjEmployeeTitle.PROP_DESCRIPTION, ProjEmployeeTitle.ORDERBY_ASCENDING);

        List<ProjEmployeeTitle> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new EmployeeDaoException(e);
        }

        List<EmployeeTitleDto> list = new ArrayList<EmployeeTitleDto>();
        for (ProjEmployeeTitle item : results) {
            EmployeeTitleDto dto = EmployeeObjectFactory.createEmployeeTitleDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchEmployeeType(org.dto.EmployeeTypeDto)
     */
    @Override
    public List<EmployeeTypeDto> fetchEmployeeType(EmployeeTypeDto criteria) throws EmployeeDaoException {
        ProjEmployeeType obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ProjEmployeeType.PROP_DESCRIPTION, ProjEmployeeType.ORDERBY_ASCENDING);

        List<ProjEmployeeType> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new EmployeeDaoException(e);
        }

        List<EmployeeTypeDto> list = new ArrayList<EmployeeTypeDto>();
        for (ProjEmployeeType item : results) {
            EmployeeTypeDto dto = EmployeeObjectFactory.createEmployeeTypeDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchTask(org.dto.TaskDto)
     */
    @Override
    public List<TaskDto> fetchTask(TaskDto criteria) throws ProjectAdminDaoException {
        ProjTask obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ProjTask.PROP_DESCRIPTION, ProjTask.ORDERBY_ASCENDING);

        List<ProjTask> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new ProjectAdminDaoException(e);
        }

        List<TaskDto> list = new ArrayList<TaskDto>();
        for (ProjTask item : results) {
            TaskDto dto = ProjectObjectFactory.createTaskDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchEvent(org.dto.EventDto)
     */
    @Override
    public List<EventDto> fetchEvent(EventDto criteria) throws ProjectAdminDaoException {
        ProjEvent obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ProjEvent.PROP_EVENTDATE, ProjEvent.ORDERBY_ASCENDING);
        obj.addOrderBy(ProjEvent.PROP_PROJECTTASKID, ProjEvent.ORDERBY_ASCENDING);
        obj.addOrderBy(ProjEvent.PROP_EVENTID, ProjEvent.ORDERBY_ASCENDING);

        List<ProjEvent> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new ProjectAdminDaoException(e);
        }

        List<EventDto> list = new ArrayList<EventDto>();
        for (ProjEvent item : results) {
            EventDto dto = ProjectObjectFactory.createEventDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchProjectClient(org.dto.ProjectClientDto)
     */
    @Override
    public List<ProjectClientDto> fetchProjectClient(ProjectClientDto criteria) throws ProjectAdminDaoException {
        VwProjectClient obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(VwProjectClient.PROP_NAME, VwProjectClient.ORDERBY_ASCENDING);
        obj.addOrderBy(VwProjectClient.PROP_DESCRIPTION, VwProjectClient.ORDERBY_ASCENDING);

        List<VwProjectClient> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new ProjectAdminDaoException(e);
        }

        List<ProjectClientDto> list = new ArrayList<ProjectClientDto>();
        for (VwProjectClient item : results) {
            ProjectClientDto dto = ProjectObjectFactory.createProjectClientDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.ProjectAdminDao#fetchProjectEmployee(org.dto.ProjectEmployeeDto)
     */
    @Override
    public List<ProjectEmployeeDto> fetchProjectEmployee(ProjectEmployeeDto criteria) throws EmployeeDaoException {
        VwEmployeeProjects obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(VwEmployeeProjects.PROP_CLIENTNAME, VwProjectClient.ORDERBY_ASCENDING);
        obj.addOrderBy(VwEmployeeProjects.PROP_PROJECTNAME, VwProjectClient.ORDERBY_ASCENDING);
        obj.addOrderBy(VwEmployeeProjects.PROP_EMPID, VwProjectClient.ORDERBY_ASCENDING);

        List<VwEmployeeProjects> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new EmployeeDaoException(e);
        }

        List<ProjectEmployeeDto> list = new ArrayList<ProjectEmployeeDto>();
        for (VwEmployeeProjects item : results) {
            ProjectEmployeeDto dto = ProjectObjectFactory.createEmployeeProjectDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchProjectTask(org.dto.ProjectTaskDto)
     */
    @Override
    public List<ProjectTaskDto> fetchProjectTask(ProjectTaskDto criteria) throws ProjectAdminDaoException {
        VwTimesheetProjectTask obj = ProjectAdminDaoFactory.createCriteriaExt(criteria);
        obj.addOrderBy(VwTimesheetProjectTask.PROP_PROJECTNAME, VwProjectClient.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetProjectTask.PROP_TASKNAME, VwProjectClient.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetProjectTask.PROP_EFFECTIVEDATE, VwProjectClient.ORDERBY_DESCENDING);

        List<VwTimesheetProjectTask> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new ProjectAdminDaoException(e);
        }

        List<ProjectTaskDto> list = new ArrayList<ProjectTaskDto>();
        for (VwTimesheetProjectTask item : results) {
            ProjectTaskDto dto = ProjectObjectFactory.createProjectTaskExtendedDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#fetchProjectEvent(org.dto.ProjectEventDto)
     */
    @Override
    public List<ProjectEventDto> fetchProjectEvent(ProjectEventDto criteria) throws ProjectAdminDaoException {
        VwTimesheetEventList obj = ProjectAdminDaoFactory.createCriteria(criteria);
        obj.addOrderBy(VwTimesheetEventList.PROP_PROJECTNAME, VwProjectClient.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetEventList.PROP_TASKNAME, VwProjectClient.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetEventList.PROP_EFFECTIVEDATE, VwProjectClient.ORDERBY_DESCENDING);

        List<VwTimesheetEventList> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new ProjectAdminDaoException(e);
        }

        List<ProjectEventDto> list = new ArrayList<ProjectEventDto>();
        for (VwTimesheetEventList item : results) {
            ProjectEventDto dto = ProjectObjectFactory.createProjectEventDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    @Override
    public int maintainClient(ClientDto obj) throws ProjectAdminDaoException {
        if (obj == null) {
            throw new EmployeeDaoException("Client DTO cannot be null during add/update operation");
        }
        ProjClient client = ProjectAdminDaoFactory.createOrm(obj);
        int rc = 0;
        try {
            Verifier.verifyPositive(client.getClientId());
            rc = this.updateClient(client);
        }
        catch (VerifyException e) {
            rc = this.insertClient(client);
        }
        return rc;
    }
    
    private int insertClient(ProjClient client) throws ProjectAdminDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            client.setDateCreated(ut.getDateCreated());
            client.setDateUpdated(ut.getDateCreated());
            client.setUserId(ut.getLoginId());
            int rc = this.client.insertRow(client, true);
            return rc;
        } catch (Exception e) {
            throw new ProjectAdminDaoException("Client database add operation failed", e);
        }
    }

    private int updateClient(ProjClient client) throws ProjectAdminDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            client.setDateUpdated(ut.getDateCreated());
            client.setUserId(ut.getLoginId());
            client.addCriteria(ProjClient.PROP_CLIENTID, client.getClientId());
            int rc = this.client.updateRow(client);
            return rc;
        } catch (Exception e) {
            throw new ProjectAdminDaoException("Client database change operation failed", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#maintainProject(org.dto.ProjectDto)
     */
    @Override
    public int maintainProject(ProjectDto obj) throws ProjectAdminDaoException {
        if (obj == null) {
            throw new ProjectAdminDaoException("Project DTO cannot be null during add/update operation");
        }
        ProjProject proj = ProjectAdminDaoFactory.createOrm(obj);
        if (proj.getClientId() == 0) {
            proj.setNull("ProjClientId");
        }
        // Begin to process project data.
        int rc = 0;
        try {
            Verifier.verifyPositive(proj.getProjId());
            rc = this.updateProject(proj);
        }
        catch (VerifyException e) {
            rc = this.insertProject(proj);
        }
        return rc;
    }

    /**
     * Add project to the database.
     * 
     * @param proj
     *            The project object.
     * @return The new project id.
     * @throws ProjectAdminDaoException
     *             when a validation or database access error occurs.
     */
    private int insertProject(ProjProject proj) throws ProjectAdminDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            proj.setDateCreated(ut.getDateCreated());
            proj.setDateUpdated(ut.getDateCreated());
            proj.setUserId(ut.getLoginId());
            int projId = this.client.insertRow(proj, true);
            proj.setProjId(projId);
            return projId;
        } catch (Exception e) {
            throw new ProjectAdminDaoException("Project database add operation failed", e);
        }
    }

    /**
     * Updates an existing project to the database.
     * 
     * @param proj
     *            The project object.
     * @return The total number of rows effected by the transaction.
     * @throws ProjectAdminDaoException
     *             when a validation or database access error occurs.
     */
    private int updateProject(ProjProject proj) throws ProjectAdminDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            proj.setDateUpdated(ut.getDateCreated());
            proj.setUserId(ut.getLoginId());
            proj.addCriteria(ProjProject.PROP_PROJID, proj.getProjId());
            int rows = this.client.updateRow(proj);
            return rows;
        } catch (Exception e) {
            throw new ProjectAdminDaoException("Project database add operation failed", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#maintainEmployee(org.dto.EmployeeDto)
     */
    @Override
    public int maintainEmployee(EmployeeDto obj) throws EmployeeDaoException {
        ProjEmployee emp = ProjectAdminDaoFactory.createOrm(obj);
        int rc = 0;
        if (emp.getEmpId() == 0) {
            rc = this.insertEmployee(emp);
            obj.setEmployeeId(rc);
        }
        else {
            rc = this.updateEmployee(emp);
        }
        return rc;
    }

    /**
     * Creates an Employee and persist the changes in the database.
     * <p>
     * A personal profile is expected to have been created prior to processing
     * <i>emp</i>.
     * 
     * @param emp
     *            The employee object.
     * @return The new employee id.
     * @throws EmployeeDaoException
     *             when a validation or database access error occurs.
     */
    private int insertEmployee(ProjEmployee emp) throws EmployeeDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            emp.setDateCreated(ut.getDateCreated());
            emp.setDateUpdated(ut.getDateCreated());
            emp.setUserId(ut.getLoginId());
            if (emp.getManagerId() == 0) {
                emp.setNull(ProjEmployee.PROP_MANAGERID);
            }
            int empId = this.client.insertRow(emp, true);
            emp.setEmpId(empId);
            return empId;
        } catch (Exception e) {
            throw new EmployeeDaoException("Employee database add operation failed", e);
        }
    }

    /**
     * Updates an existing Employee by applying any changes made to <i>emp</i>
     * to the database.
     * 
     * @param emp
     *            The employee data object.
     * @return the total number of transactions effected
     * @throws EmployeeDaoException
     *             If employee profile does not contain an employee type or an
     *             employee title, or if a general database error occurs.
     */
    private int updateEmployee(ProjEmployee emp) throws EmployeeDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            emp.setDateCreated(ut.getDateCreated());
            emp.setDateUpdated(ut.getDateCreated());
            emp.setUserId(ut.getLoginId());
            if (emp.getManagerId() == 0) {
                emp.setNull(ProjEmployee.PROP_MANAGERID);
            }
            emp.addCriteria(ProjEmployee.PROP_EMPID, emp.getEmpId());
            int rows = this.client.updateRow(emp);
            return rows;
        } catch (Exception e) {
            throw new EmployeeDaoException("Employee database update operation failed", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.admin.ProjectAdminDao#maintainEmployeeType(org.dto.EmployeeTypeDto
     * )
     */
    @Override
    public int maintainEmployeeType(EmployeeTypeDto obj) throws EmployeeDaoException {
        if (obj == null) {
            throw new EmployeeDaoException("Employee Type DTO cannot be null during add/update operation");
        }
        ProjEmployeeType empType = ProjectAdminDaoFactory.createOrm(obj);
        int rc = 0;
        if (empType.getEmpTypeId() == 0) {
            rc = this.insertEmployeeType(empType);
        }
        else {
            rc = this.updateEmployeeType(empType);
        }
        return rc;
    }

    /**
     * Creates an Employee Type and persist the changes in the database.
     * <p>
     * A profile is expected to have been created prior to processing
     * <i>emp</i>.
     * 
     * @param emp
     *            The employee type object.
     * @return The new employee type id.
     * @throws EmployeeDaoException
     *             when a validation or database access error occurs.
     */
    private int insertEmployeeType(ProjEmployeeType empType) throws EmployeeDaoException {
        try {
            int empId = this.client.insertRow(empType, true);
            empType.setEmpTypeId(empId);
            return empId;
        } catch (Exception e) {
            throw new EmployeeDaoException("Employee Type database add operation failed", e);
        }
    }

    /**
     * Updates an Employee Type and persist the changes in the database.
     * <p>
     * A profile is expected to have been created prior to processing
     * <i>emp</i>.
     * 
     * @param emp
     *            The employee type object.
     * @return The Total number rows effected by transaction.
     * @throws EmployeeDaoException
     *             when a validation or database access error occurs.
     */
    private int updateEmployeeType(ProjEmployeeType empType) throws EmployeeDaoException {
        try {
            empType.addCriteria(ProjEmployeeType.PROP_EMPTYPEID, empType.getEmpTypeId());
            int empId = this.client.updateRow(empType);
            empType.setEmpTypeId(empId);
            return empId;
        } catch (Exception e) {
            throw new EmployeeDaoException("Employee Type database add operation failed", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.admin.ProjectAdminDao#maintainEmployeeTitle(org.dto.EmployeeTitleDto
     * )
     */
    @Override
    public int maintainEmployeeTitle(EmployeeTitleDto obj) throws EmployeeDaoException {
        if (obj == null) {
            throw new EmployeeDaoException("Employee Title DTO cannot be null during add/update operation");
        }
        ProjEmployeeTitle empType = ProjectAdminDaoFactory.createOrm(obj);
        int rc = 0;
        if (empType.getEmpTitleId() == 0) {
            rc = this.insertEmployeeTitle(empType);
        }
        else {
            rc = this.updateEmployeeTitle(empType);
        }
        return rc;
    }

    /**
     * Creates an Employee Title and persist the changes in the database.
     * <p>
     * A profile is expected to have been created prior to processing
     * <i>emp</i>.
     * 
     * @param empTitle
     *            The employee title object.
     * @return The new employee title id.
     * @throws EmployeeDaoException
     *             when a validation or database access error occurs.
     */
    private int insertEmployeeTitle(ProjEmployeeTitle empTitle) throws EmployeeDaoException {
        try {
            int empId = this.client.insertRow(empTitle, true);
            empTitle.setEmpTitleId(empId);
            return empId;
        } catch (Exception e) {
            throw new EmployeeDaoException("Employee Title database add operation failed", e);
        }
    }

    /**
     * Updates an Employee Title and persist the changes in the database.
     * <p>
     * A profile is expected to have been created prior to processing
     * <i>emp</i>.
     * 
     * @param empTitle
     *            The employee title object.
     * @return The Total number rows effected by transaction.
     * @throws EmployeeDaoException
     *             when a validation or database access error occurs.
     */
    private int updateEmployeeTitle(ProjEmployeeTitle empTitle) throws EmployeeDaoException {
        try {
            empTitle.addCriteria(ProjEmployeeTitle.PROP_EMPTITLEID, empTitle.getEmpTitleId());
            int empId = this.client.updateRow(empTitle);
            empTitle.setEmpTitleId(empId);
            return empId;
        } catch (Exception e) {
            throw new EmployeeDaoException("Employee Title database add operation failed", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#maintainTask(org.dto.TaskDto)
     */
    @Override
    public int maintainTask(TaskDto obj) throws ProjectAdminDaoException {
        if (obj == null) {
            throw new ProjectAdminDaoException("Task DTO cannot be null during add/update operation");
        }
        ProjTask task = ProjectAdminDaoFactory.createOrm(obj);
        int rc = 0;
        try {
            Verifier.verifyPositive(task.getTaskId());
            rc = this.updateTask(task);
        }
        catch (VerifyException e) {
            rc = this.insertTask(task);
        }
        return rc;
    }

    /**
     * Creates an Task and persist the changes in the database.
     * <p>
     * A profile is expected to have been created prior to processing
     * <i>task</i>.
     * 
     * @param task
     *            The task object.
     * @return The new task id.
     * @throws ProjectAdminDaoException
     *             when a validation or database access error occurs.
     */
    private int insertTask(ProjTask emp) throws ProjectAdminDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            emp.setDateCreated(ut.getDateCreated());
            emp.setDateUpdated(ut.getDateCreated());
            emp.setUserId(ut.getLoginId());
            int taskId = this.client.insertRow(emp, true);
            emp.setTaskId(taskId);
            return taskId;
        } catch (Exception e) {
            throw new ProjectAdminDaoException("Task database add operation failed", e);
        }
    }

    /**
     * Updates an existing Task by applying any changes made to <i>task</i> to
     * the database.
     * 
     * @param task
     *            The task data object.
     * @return the total number of transactions effected
     * @throws ProjectAdminDaoException
     *             if a general database error occurs.
     */
    private int updateTask(ProjTask task) throws ProjectAdminDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            task.setDateUpdated(ut.getDateCreated());
            task.setUserId(ut.getLoginId());
            task.addCriteria(ProjTask.PROP_TASKID, task.getTaskId());
            int rows = this.client.updateRow(task);
            return rows;
        } catch (Exception e) {
            throw new ProjectAdminDaoException("Task database update operation failed", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.ProjectAdminDao#maintainProjectEmployee(org.dto.ProjectEmployeeDto
     * )
     */
    @Override
    public int maintainProjectEmployee(ProjectEmployeeDto obj) throws EmployeeDaoException {
        ProjEmployeeProject projEmp = ProjectAdminDaoFactory.createOrm(obj);
        int rc = 0;
        if (projEmp.getEmpProjId() == 0) {
            rc = this.insertProjectEmployee(projEmp);
            obj.setEmpProjId(rc);
        }
        else {
            rc = this.updateProjectEmployee(projEmp);
        }
        return rc;
    }

    /**
     * Creates an Project/Employee and persist the changes in the database.
     * 
     * @param projEmp
     *            The project employee data object.
     * @return int The new project employee id
     * @throws EmployeeDaoException
     *             a general database error occurs.
     */
    private int insertProjectEmployee(ProjEmployeeProject projEmp) throws EmployeeDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            projEmp.setDateCreated(ut.getDateCreated());
            projEmp.setDateUpdated(ut.getDateCreated());
            projEmp.setUserId(ut.getLoginId());
            projEmp.setIpCreated(ut.getIpAddr());
            projEmp.setIpUpdated(ut.getIpAddr());
            int projEmpId = this.client.insertRow(projEmp, true);
            projEmp.setEmpProjId(projEmpId);
            return projEmpId;
        } catch (Exception e) {
            throw new EmployeeDaoException("Project/Employee database insert operation failed", e);
        }
    }

    /**
     * Updates an existing Project/Employee by applying any changes made to
     * <i>projEmp</i> to the database.
     * 
     * @param projEmp
     *            The project employee data object.
     * @return int The project employee id
     * @throws EmployeeDaoException
     *             a general database error occurs.
     */
    private int updateProjectEmployee(ProjEmployeeProject projEmp) throws EmployeeDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            projEmp.setDateUpdated(ut.getDateCreated());
            projEmp.setUserId(ut.getLoginId());
            projEmp.setIpUpdated(ut.getIpAddr());
            projEmp.addCriteria(ProjEmployeeProject.PROP_EMPID, projEmp.getEmpId());
            projEmp.addCriteria(ProjEmployeeProject.PROP_EMPPROJID, projEmp.getEmpProjId());
            int rows = this.client.updateRow(projEmp);
            return rows;
        } catch (Exception e) {
            throw new EmployeeDaoException("Project/Employee database update operation failed", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#maintainProjectTask(org.dto.ProjectTaskDto)
     */
    @Override
    public int createProjectTask(ProjectTaskDto obj) throws ProjectAdminDaoException {
        if (obj == null) {
            throw new ProjectAdminDaoException("Project/Task DTO cannot be null during add operation");
        }
        ProjProjectTask projEmp = ProjectAdminDaoFactory.createOrm(obj);
        try {
            int projTaskId = this.client.insertRow(projEmp, true);
            projEmp.setProjectTaskId(projTaskId);
            return projTaskId;
        } catch (Exception e) {
            throw new ProjectAdminDaoException("Project/Employee database insert operation failed", e);
        }
    }

        /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#deleteClient(org.dto.ClientDto)
     */
    @Override
    public int deleteClient(ClientDto criteria) throws ProjectAdminDaoException {
        ProjClient obj = ProjectAdminDaoFactory.createCriteria(criteria);
        return this.deleteObject(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#deleteProject(org.dto.ProjectDto)
     */
    @Override
    public int deleteProject(ProjectDto criteria) throws ProjectAdminDaoException {
        ProjProject obj = ProjectAdminDaoFactory.createCriteria(criteria);
        return this.deleteObject(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#deleteEmployee(org.dto.EmployeeDto)
     */
    @Override
    public int deleteEmployee(EmployeeDto criteria) throws EmployeeDaoException {
        ProjEmployee obj = ProjectAdminDaoFactory.createCriteria(criteria);
        return this.deleteObject(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.ProjectAdminDao#deleteEmployeeTitle(org.dto.EmployeeTitleDto)
     */
    @Override
    public int deleteEmployeeTitle(EmployeeTitleDto criteria) throws EmployeeDaoException {
        ProjEmployeeTitle obj = ProjectAdminDaoFactory.createCriteria(criteria);
        return this.deleteObject(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#deleteEmployeeType(org.dto.EmployeeTypeDto)
     */
    @Override
    public int deleteEmployeeType(EmployeeTypeDto criteria) throws EmployeeDaoException {
        ProjEmployeeType obj = ProjectAdminDaoFactory.createCriteria(criteria);
        return this.deleteObject(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#deleteTask(org.dto.TaskDto)
     */
    @Override
    public int deleteTask(TaskDto criteria) throws ProjectAdminDaoException {
        ProjTask obj = ProjectAdminDaoFactory.createCriteria(criteria);
        return this.deleteObject(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.ProjectAdminDao#deleteProjectTask(org.dto.ProjectTaskDto)
     */
    @Override
    public int deleteProjectTask(ProjectTaskDto criteria) throws ProjectAdminDaoException {
        ProjProjectTask obj = ProjectAdminDaoFactory.createCriteria(criteria);
        return this.deleteObject(obj);
    }

    

    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dao.admin.ProjectAdminDao#fetchClientExt(org.dto.ClientDto)
    // */
    // @Override
    // public List<ClientDto> fetchClientExt(ClientDto criteria) throws
    // ProjectAdminDaoException {
    //
    // // Get local results and build list of business id's for web service
    // // call.
    // List<ClientDto> contactList = this.fetchClient(criteria);
    // RSCustomerSearch custInfo = this.getCustomerInfo(contactList);
    // try {
    // ProjectAdminDaoFactory.mergeExtendedContactInfo(contactList,
    // custInfo);
    // } catch (InvalidDataException e) {
    // return null;
    // }
    // return contactList;
    // }
    //
    // /**
    // * Retrieves customer information for a single client id and returns the
    // * data as a web service reply.
    // *
    // * @param clientId
    // * the unique id of the client
    // * @return {@link RSCustomerSearch}
    // */
    // private RSCustomerSearch getCustomerInfo(int clientId) {
    // ClientDto obj = ProjectObjectFactory.createClientDtoInstance(null);
    // obj.setClientId(clientId);
    // List<ClientDto> contactList = new ArrayList<ClientDto>();
    // contactList.add(obj);
    // return this.getCustomerInfo(contactList);
    // }
    //
    // /**
    // * Retrieves customer information for each client id that exist in
    // * <i>contactList</i> and returns the data as a web service reply.
    // *
    // * @param contactList
    // * a List of {@link ClientDto} objects
    // * @return {@link RSCustomerSearch}
    // */
    // private RSCustomerSearch getCustomerInfo(List<ClientDto> contactList) {
    // List<BigInteger> custIdList = ProjectAdminDaoFactory
    // .getCustomerId(contactList);
    //
    // // Create Web service request
    // String serviceName = "RQ_customer_search";
    // ObjectFactory f = new ObjectFactory();
    // RQCustomerSearch ws = f.createRQCustomerSearch();
    // HeaderType header = JaxbPayloadFactory.createHeader("routing", "app",
    // "module", serviceName,
    // WebServiceConstants.MSG_TRANSPORT_MODE_SYNC, "REQUEST",
    // this.getDaoUser());
    // ws.setHeader(header);
    //
    // // Setup customer criteria without setting any properties.
    // CustomerCriteriaType custCriteria = f.createCustomerCriteriaType();
    // custCriteria.getCustomerId().addAll(custIdList);
    // ws.setCriteriaData(custCriteria);
    //
    // // Setup JMS synchronous call
    // MessageRouterHelper router = new MessageRouterHelper();
    // Object resultsObj;
    // try {
    // resultsObj = router.routeSerialMessage(serviceName, ws);
    // } catch (MessageRoutingException e) {
    // this.msg = "Error invoking JMS for service, " + serviceName;
    // throw new ProjectAdminDaoException(this.msg, e);
    // } finally {
    // router = null;
    // }
    //
    // RSCustomerSearch results = null;
    // if (resultsObj != null && resultsObj instanceof RSCustomerSearch) {
    // results = (RSCustomerSearch) resultsObj;
    // }
    // return results;
    // }
}
