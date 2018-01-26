package org.modules.admin;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.ProjecttrackerDaoException;
import org.dao.admin.ProjectAdminDao;
import org.dao.admin.ProjectAdminDaoFactory;
import org.dto.ClientDto;
import org.dto.EventDto;
import org.dto.ProjectDto;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TaskDto;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.modules.ProjectTrackerApiConst;

import com.InvalidDataException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;
import com.util.RMT2Date;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * Api Implementation of {@link ProjectApi} that manages the creation, querying,
 * updating, and deletion of project entities.
 * 
 * @author Roy Terrell
 * 
 */
class ProjectAdminApiImpl extends AbstractTransactionApiImpl implements ProjectAdminApi {

    private static final Logger logger = Logger.getLogger(ProjectAdminApiImpl.class);

    private ProjectAdminDaoFactory daoFact;

    private ProjectAdminDao dao;

    /**
     * Creates an ProjectApiImpl which creates a stand alone connection.
     */
    protected ProjectAdminApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmDao();
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an ProjectApiImpl which creates based on the identified
     * application.
     */
    protected ProjectAdminApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an ProjectApiImpl initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param dao
     */
    protected ProjectAdminApiImpl(DaoClient dao) {
        super(dao);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.RMT2Base#init()
     */
    @Override
    public void init() {
        super.init();
        this.daoFact = new ProjectAdminDaoFactory();
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getAllClients()
     */
    @Override
    public List<ClientDto> getAllClients() throws ProjectAdminApiException {
        ClientDto criteria = null;
        List<ClientDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchClient(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving all project clients");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getClient(int)
     */
    @Override
    public ClientDto getClient(Integer clientId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(clientId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client Id is required");
        }
        try {
            Verifier.verifyPositive(clientId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client Id must be greater than zero");
        }
        
        ClientDto criteria = ProjectObjectFactory.createClientDtoInstance(null);
        criteria.setClientId(clientId);
        List<ClientDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchClient(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving all project clients");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }

        try {
            Verifier.verifyTrue(results.size() == 1);
            return results.get(0);
        }
        catch (VerifyException e) {
            buf.append("Error: Query method is expecting a single client object to be returned using client id, ");
            buf.append(clientId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see org.modules.admin.ProjectApi#getClientExt(int)
//     */
//    @Override
//    public ClientDto getClientExt(Integer clientId) throws ProjectApiException {
//        try {
//            Verifier.verifyNotNull(clientId);
//        }
//        catch (VerifyException e) {
//            throw new InvalidDataException("Client Id is required");
//        }
//        try {
//            Verifier.verifyPositive(clientId);
//        }
//        catch (VerifyException e) {
//            throw new InvalidDataException("Client Id must be greater than zero");
//        }
//        
//        ClientDto criteria = ProjectObjectFactory.createClientDtoInstance(null);
//        criteria.setClientId(clientId);
//        List<ClientDto> results;
//        StringBuilder buf = new StringBuilder();
//        try {
//            results = dao.fetchClientExt(criteria);
//            if (results == null) {
//                return null;
//            }
//        } catch (ProjecttrackerDaoException e) {
//            buf.append("Database error occurred retrieving all project clients");
//            this.msg = buf.toString();
//            throw new ProjectApiException(this.msg, e);
//        }
//
//        try {
//            Verifier.verifyTrue(results.size() == 1);
//            return results.get(0);
//        }
//        catch (VerifyException e) {
//            buf.append("Error: Query method is expecting a single client extended object to be returned using client id, ");
//            buf.append(clientId);
//            buf.append(".  Instead ");
//            buf.append(results.size());
//            buf.append("  were returned.");
//            this.msg = buf.toString();
//            throw new ProjectApiException(this.msg);
//        }
//    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see org.modules.admin.ProjectApi#getClientExt()
//     */
//    @Override
//    public List<ClientDto> getAllClientExt() throws ProjectApiException {
//        ClientDto criteria = null;
//        List<ClientDto> results;
//        StringBuilder buf = new StringBuilder();
//        try {
//            results = dao.fetchClientExt(criteria);
//            if (results == null) {
//                return null;
//            }
//        } catch (ProjecttrackerDaoException e) {
//            buf.append("Database error occurred retrieving all extended clients");
//            this.msg = buf.toString();
//            throw new ProjectApiException(this.msg, e);
//        }
//        return results;
//    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getAllProjects()
     */
    @Override
    public List<ProjectDto> getAllProjects() throws ProjectAdminApiException {
        ProjectDto criteria = null;
        List<ProjectDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProject(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving all projects");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProject(int)
     */
    @Override
    public ProjectDto getProject(Integer projectId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(projectId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Id is required");
        }
        try {
            Verifier.verifyPositive(projectId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Id must be greater than zero");
        }
        
        ProjectDto criteria = ProjectObjectFactory.createProjectDtoInstance(null);
        criteria.setProjId(projectId);
        List<ProjectDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProject(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving a single project by project id, ");
            buf.append(projectId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        
        try {
            Verifier.verifyTrue(results.size() == 1);
            return results.get(0);
        }
        catch (VerifyException e) {
            buf.append("Error: Query method is expecting a single project object to be returned using project id, ");
            buf.append(projectId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectByClientId(int)
     */
    @Override
    public List<ProjectDto> getProjectByClientId(Integer clientId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(clientId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client Id is required");
        }
        try {
            Verifier.verifyPositive(clientId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client Id must be greater than zero");
        }
        
        ProjectDto criteria = ProjectObjectFactory.createProjectDtoInstance(null);
        criteria.setClientId(clientId);
        List<ProjectDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProject(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving projecta by client id, ");
            buf.append(clientId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getTask(int)
     */
    @Override
    public TaskDto getTask(Integer taskId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(taskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Task Id is required");
        }
        try {
            Verifier.verifyPositive(taskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Task Id must be greater than zero");
        }
        
        TaskDto criteria = ProjectObjectFactory.createTaskDtoInstance(null);
        criteria.setTaskId(taskId);
        List<TaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving a single task by task id, ");
            buf.append(taskId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        try {
            Verifier.verifyTrue(results.size() == 1);
            return results.get(0);
        }
        catch (VerifyException e) {
            buf.append("Error: Query method is expecting a single task object to be returned using task id, ");
            buf.append(taskId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getAllTasks()
     */
    @Override
    public List<TaskDto> getAllTasks() throws ProjectAdminApiException {
        TaskDto criteria = null;
        List<TaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving all tasks");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getTasks(boolean)
     */
    @Override
    public List<TaskDto> getTasks(Boolean billable) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(billable);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Billable indicator is required");
        }
        TaskDto criteria = ProjectObjectFactory.createTaskDtoInstance(null);
        criteria.setTaskBillable(billable ? ProjectTrackerApiConst.TASK_BILLABLE_FLAG
                : ProjectTrackerApiConst.TASK_NONBILLABLE_FLAG);
        List<TaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving all tasks based on billable flag, ");
            buf.append(billable);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTask(int)
     */
    @Override
    public ProjectTaskDto getProjectTask(Integer projectTaskId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(projectTaskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project task Id is required");
        }
        try {
            Verifier.verifyPositive(projectTaskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Task Id must be greater than zero");
        }
        
        ProjectTaskDto criteria = ProjectObjectFactory.createProjectTaskExtendedDtoInstance(null);
        criteria.setProjectTaskId(projectTaskId);
        List<ProjectTaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving a single project/task by project task id, ");
            buf.append(projectTaskId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectAdminApiException(this.msg, e);
        }
        try {
            Verifier.verifyTrue(results.size() == 1);
            return results.get(0);
        }
        catch (VerifyException e) {
            buf.append("Error: Query method is expecting a single task object to be returned using project task id, ");
            buf.append(projectTaskId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTaskByClient(int)
     */
    @Override
    public List<ProjectTaskDto> getProjectTaskByClient(Integer clientId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(clientId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client Id is required");
        }
        try {
            Verifier.verifyPositive(clientId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client Id must be greater than zero");
        }
        
        ProjectTaskDto criteria = ProjectObjectFactory.createProjectTaskExtendedDtoInstance(null);
        criteria.setClientId(clientId);
        List<ProjectTaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project/task objects by client id, ");
            buf.append(clientId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTaskByProject(int)
     */
    @Override
    public List<ProjectTaskDto> getProjectTaskByProject(Integer projectId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(projectId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Id is required");
        }
        try {
            Verifier.verifyPositive(projectId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Id must be greater than zero");
        }
        
        ProjectTaskDto criteria = ProjectObjectFactory.createProjectTaskExtendedDtoInstance(null);
        criteria.setProjId(projectId);
        List<ProjectTaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project/task objects by project id, ");
            buf.append(projectId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTaskByTask(int)
     */
    @Override
    public List<ProjectTaskDto> getProjectTaskByTask(Integer taskId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(taskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Id is required");
        }
        try {
            Verifier.verifyPositive(taskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Task Id must be greater than zero");
        }
        
        ProjectTaskDto criteria = ProjectObjectFactory.createProjectTaskExtendedDtoInstance(null);
        criteria.setTaskId(taskId);
        List<ProjectTaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project/task objects by task id, ");
            buf.append(taskId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTask(int, int)
     */
    @Override
    public List<ProjectTaskDto> getProjectTask(Integer projectId, Integer taskId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(projectId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Id is required");
        }
        try {
            Verifier.verifyPositive(projectId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Id must be greater than zero");
        }
        try {
            Verifier.verifyNotNull(taskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Task Id is required");
        }
        try {
            Verifier.verifyPositive(taskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Task Id must be greater than zero");
        }
        
        ProjectTaskDto criteria = ProjectObjectFactory.createProjectTaskExtendedDtoInstance(null);
        criteria.setTaskId(taskId);
        criteria.setProjId(projectId);
        List<ProjectTaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project/task objects by task id, ");
            buf.append(taskId);
            buf.append(", and project id, ");
            buf.append(projectId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEvent(int)
     */
    @Override
    public EventDto getEvent(Integer eventId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(eventId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Event Id is required");
        }
        try {
            Verifier.verifyPositive(eventId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Event Id must be greater than zero");
        }
        
        EventDto criteria = ProjectObjectFactory.createEventDtoInstance(null);
        criteria.setEventId(eventId);
        List<EventDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEvent(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving a single event by event id, ");
            buf.append(eventId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        try {
            Verifier.verifyTrue(results.size() == 1);
            return results.get(0);
        }
        catch (VerifyException e) {
            buf.append("Error: Query method is expecting a single event object to be returned using event id, ");
            buf.append(eventId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEvent(java.util.Date)
     */
    @Override
    public List<EventDto> getEvent(Date eventDate) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(eventDate);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Event Date is required");
        }
        EventDto criteria = ProjectObjectFactory.createEventDtoInstance(null);
        criteria.setEventDate(eventDate);
        List<EventDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEvent(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving events by event date, ");
            buf.append(RMT2Date.formatDate(eventDate, "MM-dd-yyyy"));
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEvent(java.util.Date,
     * java.util.Date)
     */
    @Override
    public List<EventDto> getEvent(Date beginDate, Date endDate) throws ProjectAdminApiException {
        try {
            Verifier.verifyTrue(beginDate != null && endDate != null);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Begin Date or End Date or both are required");
        }

        // Setup date range predicate
        StringBuffer sql = new StringBuffer();
        if (beginDate != null && endDate == null) {
            sql.append(" event_date >= ");
            sql.append(RMT2Date.formatDate(beginDate, "yyyy-MM-dd"));
        }
        if (beginDate == null && endDate != null) {
            sql.append(" event_date <= ");
            sql.append(RMT2Date.formatDate(endDate, "yyyy-MM-dd"));
        }
        if (beginDate != null && endDate != null) {
            sql.append(" event_date between( ");
            sql.append(RMT2Date.formatDate(beginDate, "yyyy-MM-dd"));
            sql.append(" and ");
            sql.append(RMT2Date.formatDate(endDate, "yyyy-MM-dd"));
            sql.append(") ");
        }
        EventDto criteria = ProjectObjectFactory.createEventDtoInstance(null);
        criteria.setCriteria(sql.toString());
        List<EventDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEvent(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving events by event date filter, ");
            buf.append(sql.toString());
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEventByProjectTask(int)
     */
    @Override
    public List<EventDto> getEventByProjectTask(Integer projectTaskId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(projectTaskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Task Id is required");
        }
        try {
            Verifier.verifyPositive(projectTaskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Task Id must be greater than zero");
        }
        
        EventDto criteria = ProjectObjectFactory.createEventDtoInstance(null);
        criteria.setProjectTaskId(projectTaskId);
        List<EventDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEvent(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving events by project task id, ");
            buf.append(projectTaskId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEventByProject(int)
     */
    @Override
    public List<ProjectEventDto> getEventByProject(Integer projectId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(projectId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Id is required");
        }
        try {
            Verifier.verifyPositive(projectId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project Id must be greater than zero");
        }
        
        ProjectEventDto criteria = ProjectObjectFactory.createProjectEventDtoInstance(null);
        criteria.setProjId(projectId);
        List<ProjectEventDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectEvent(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving events by project/event project id, ");
            buf.append(projectId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEventByTask(int)
     */
    @Override
    public List<ProjectEventDto> getEventByTask(Integer taskId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(taskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Task Id is required");
        }
        try {
            Verifier.verifyPositive(taskId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Task Id must be greater than zero");
        }
        
        ProjectEventDto criteria = ProjectObjectFactory.createProjectEventDtoInstance(null);
        criteria.setTaskId(taskId);
        List<ProjectEventDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectEvent(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving events by project/event task id, ");
            buf.append(taskId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEventByClient(int)
     */
    @Override
    public List<ProjectEventDto> getEventByClient(Integer clientId) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(clientId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client Id is required");
        }
        try {
            Verifier.verifyPositive(clientId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client Id must be greater than zero");
        }
        
        ProjectEventDto criteria = ProjectObjectFactory.createProjectEventDtoInstance(null);
        criteria.setClientId(clientId);
        List<ProjectEventDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectEvent(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving events by project/event client id, ");
            buf.append(clientId);
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /**
     * Validates a client object.
     * 
     * @param client
     *            an instance of {@link ClientDto}
     * @throws InvalidClientIdentifierException
     *             <i>clinet</i> is null, the client id property of
     *             <i>clinet</i> is less than or equal to zero, or <i>clinet</i>
     *             does not exist in the accounting system.
     */
    protected void validateClient(ClientDto client) throws InvalidClientIdentifierException {
        try {
            Verifier.verifyNotNull(client);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client data is required");
        }
        try {
            Verifier.verifyPositive(client.getClientId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("The client's id must be greater than zero");
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#updateClient(org.dto.ClientDto)
     */
    private int addClient(ClientDto client) throws ProjectAdminApiException {
        this.validateClient(client);
        this.dao.insertClient(client);
        return client.getClientId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#updateProject(org.dto.ProjectDto)
     */
    @Override
    public int updateProject(ProjectDto project) throws ProjectAdminApiException {
        // Validate project object
        this.validateProject(project);

        // Make API call to determine client's existence. When new, add to the
        // database. If client does not exist in the Proj_Client table then add.
        int clientId = project.getClientId();
        ClientDto client;
        try {
            // Check if client exists locally
            client = this.getClient(clientId);
        } catch (ProjectAdminApiException e) {
            this.msg = "Error fetching client: " + clientId;
            throw new ProjectAdminApiException(this.msg, e);
        }

        // TODO: Uncomment once getClientExt has been figured out.
//        if (client == null) {
//            // Try to create client row
//            client = this.getClientExt(clientId);
//            if (client == null) {
//                this.msg = "Error updating Project due to client does not exists as a customer in the accounting system.  Client Id is "
//                        + clientId;
//                throw new ProjectApiException(this.msg);
//            }
//            this.addClient(client);
//        }

        // Begin to process project data.
        int rc = this.dao.maintainProject(project);
        return rc;
    }

    /**
     * Validates a project object.
     * 
     * @param proj
     *            The project object that is being validated.
     * @throws InvalidProjectException
     *             <i>proj</i> is null or no data values are provided for
     *             <i>proj</i>'s properties, description and/or effective date.
     */
    protected void validateProject(ProjectDto proj) throws InvalidProjectException {
        try {
            Verifier.verifyNotNull(proj);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project data is required");
        }
        try {
            Verifier.verifyNotNull(proj.getProjectDescription());
        }
        catch (VerifyException e) {
            throw new InvalidProjectException("Project description is required");
        }
        try {
            Verifier.verifyNotNull(proj.getProjectEffectiveDate());
        }
        catch (VerifyException e) {
            throw new InvalidProjectException("Project effective date is required");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#updateTask(org.dto.TaskDto)
     */
    @Override
    public int updateTask(TaskDto task) throws ProjectAdminApiException {
        this.validateTask(task);
        int rc = this.dao.maintainTask(task);
        return rc;
    }

    /**
     * Validates a Task object.
     * 
     * @param task
     *            The task object that is being validated.
     * @throws InvalidTaskException
     *             <i>task</i> is null or <i>task</i>'s description property has
     *             no value.
     */
    protected void validateTask(TaskDto task) throws InvalidTaskException {
        try {
            Verifier.verifyNotNull(task);
        }
        catch (VerifyException e) {
            throw new InvalidTaskException("Task data is required");
        }
        try {
            Verifier.verifyNotNull(task.getTaskDescription());
        }
        catch (VerifyException e) {
            throw new InvalidTaskException("Task Description is required");
        }
    }

}
