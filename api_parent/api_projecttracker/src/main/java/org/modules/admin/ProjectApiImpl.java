package org.modules.admin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.ProjecttrackerDaoException;
import org.dao.admin.ProjectAdminDao;
import org.dao.admin.ProjectAdminDaoException;
import org.dao.admin.ProjectAdminDaoFactory;
import org.dto.ClientDto;
import org.dto.EventDto;
import org.dto.ProjectDto;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TaskDto;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.modules.ProjectTrackerApiConst;
import org.rmt2.jaxb.CustomerCriteriaType;
import org.rmt2.jaxb.HeaderType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.RQCustomerSearch;
import org.rmt2.jaxb.RSCustomerSearch;
import org.rmt2.util.JaxbPayloadFactory;

import com.api.foundation.AbstractTransactionApiImpl;
import com.api.messaging.webservice.WebServiceConstants;
import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.messaging.webservice.router.MessageRoutingException;
import com.api.persistence.DaoClient;
import com.util.RMT2Date;

/**
 * Api Implementation of {@link ProjectApi} that manages the creation, querying,
 * updating, and deletion of project entities.
 * 
 * @author Roy Terrell
 * 
 */
class ProjectApiImpl extends AbstractTransactionApiImpl implements ProjectApi {

    private static final Logger logger = Logger.getLogger(ProjectApiImpl.class);

    private ProjectAdminDaoFactory daoFact;

    private ProjectAdminDao dao;

    /**
     * Creates an ProjectApiImpl which creates a stand alone connection.
     */
    protected ProjectApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmDao();
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an ProjectApiImpl which creates based on the identified
     * application.
     */
    protected ProjectApiImpl(String appName) {
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
    protected ProjectApiImpl(DaoClient dao) {
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
    public List<ClientDto> getAllClients() throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getClient(int)
     */
    @Override
    public ClientDto getClient(Integer clientId) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }

        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single client object to be returned using client id, ");
            buf.append(clientId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getClientExt(int)
     */
    @Override
    public ClientDto getClientExt(Integer clientId) throws ProjectApiException {
        ClientDto criteria = ProjectObjectFactory.createClientDtoInstance(null);
        criteria.setClientId(clientId);
        List<ClientDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchClientExt(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving all project clients");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }

        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single client extended object to be returned using client id, ");
            buf.append(clientId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getClientExt()
     */
    @Override
    public List<ClientDto> getAllClientExt() throws ProjectApiException {
        ClientDto criteria = null;
        List<ClientDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchClientExt(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving all extended clients");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getAllProjects()
     */
    @Override
    public List<ProjectDto> getAllProjects() throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProject(int)
     */
    @Override
    public ProjectDto getProject(Integer projectId) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single project object to be returned using project id, ");
            buf.append(projectId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectByClientId(int)
     */
    @Override
    public List<ProjectDto> getProjectByClientId(Integer clientId)
            throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getTask(int)
     */
    @Override
    public TaskDto getTask(Integer taskId) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single task object to be returned using task id, ");
            buf.append(taskId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getAllTasks()
     */
    @Override
    public List<TaskDto> getAllTasks() throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getTasks(boolean)
     */
    @Override
    public List<TaskDto> getTasks(Boolean billable) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTask(int)
     */
    @Override
    public ProjectTaskDto getProjectTask(Integer projectTaskId)
            throws ProjectApiException {
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
            throw new ProjectApiException(this.msg, e);
        }
        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single task object to be returned using project task id, ");
            buf.append(projectTaskId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTaskByClient(int)
     */
    @Override
    public List<ProjectTaskDto> getProjectTaskByClient(Integer clientId)
            throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTaskByProject(int)
     */
    @Override
    public List<ProjectTaskDto> getProjectTaskByProject(Integer projectId)
            throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTaskByTask(int)
     */
    @Override
    public List<ProjectTaskDto> getProjectTaskByTask(Integer taskId)
            throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getProjectTask(int, int)
     */
    @Override
    public List<ProjectTaskDto> getProjectTask(Integer projectId, Integer taskId)
            throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEvent(int)
     */
    @Override
    public EventDto getEvent(Integer eventId) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single event object to be returned using event id, ");
            buf.append(eventId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEvent(java.util.Date)
     */
    @Override
    public List<EventDto> getEvent(Date eventDate) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
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
    public List<EventDto> getEvent(Date beginDate, Date endDate) throws ProjectApiException {
        EventDto criteria = ProjectObjectFactory.createEventDtoInstance(null);

        // Setup date range predicate
        StringBuffer sql = new StringBuffer();
        if (beginDate != null && endDate == null) {
            sql.append(" event_date >= ");
            sql.append(RMT2Date.formatDate(beginDate, "yyyy-MM-dd"));
        }
        if (beginDate != null && endDate == null) {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEventByProjectTask(int)
     */
    @Override
    public List<EventDto> getEventByProjectTask(Integer projectTaskId) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEventByProject(int)
     */
    @Override
    public List<ProjectEventDto> getEventByProject(Integer projectId) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEventByTask(int)
     */
    @Override
    public List<ProjectEventDto> getEventByTask(Integer taskId) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#getEventByClient(int)
     */
    @Override
    public List<ProjectEventDto> getEventByClient(Integer clientId) throws ProjectApiException {
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
            logger.error(this.msg);
            throw new ProjectApiException(this.msg, e);
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
        if (client == null) {
            throw new InvalidClientIdentifierException("The client object cannot be null");
        }
        if (client.getClientId() <= 0) {
            throw new InvalidClientIdentifierException("The client's id is invalid.  Must be greater than zero.");
        }

        // Verify that client exist in the accounting system
        RSCustomerSearch results = this.getCustomerInfo(client.getClientId());
        if (results != null && results.getCustomerList().size() == 1) {
            // we are okay
        }
        else {
            throw new InvalidClientIdentifierException(
                    "The project client's id " + client.getClientId()
                            + ", does not exist in the accounting system.");
        }
        return;

    }

    /**
     * Retrieves customer information for a single client id and returns the
     * data as a web service reply.
     * 
     * @param clientId
     *            the unique id of the client
     * @return {@link RSCustomerSearch}
     */
    private RSCustomerSearch getCustomerInfo(int clientId) {
        ClientDto obj = ProjectObjectFactory.createClientDtoInstance(null);
        obj.setClientId(clientId);
        List<ClientDto> contactList = new ArrayList<ClientDto>();
        contactList.add(obj);
        return this.getCustomerInfo(contactList);
    }

    /**
     * Retrieves customer information for each client id that exist in
     * <i>contactList</i> and returns the data as a web service reply.
     * 
     * @param contactList
     *            a List of {@link ClientDto} objects
     * @return {@link RSCustomerSearch}
     */
    private RSCustomerSearch getCustomerInfo(List<ClientDto> contactList) {
        List<BigInteger> custIdList = ProjectAdminDaoFactory.getCustomerId(contactList);

        // Create Web service request
        String serviceName = "RQ_customer_search";
        ObjectFactory f = new ObjectFactory();
        RQCustomerSearch ws = f.createRQCustomerSearch();
        HeaderType header = JaxbPayloadFactory.createHeader("routing", "app",
                "module", serviceName,
                WebServiceConstants.MSG_TRANSPORT_MODE_SYNC, "REQUEST",
                this.getApiUser());
        ws.setHeader(header);

        // Setup customer criteria without setting any properties.
        CustomerCriteriaType custCriteria = f.createCustomerCriteriaType();
        custCriteria.getCustomerId().addAll(custIdList);
        ws.setCriteriaData(custCriteria);

        // Setup JMS synchronous call
        MessageRouterHelper router = new MessageRouterHelper();
        Object resultsObj;
        try {
            resultsObj = router.routeSerialMessage(serviceName, ws);
        } catch (MessageRoutingException e) {
            this.msg = "Error invoking JMS for service, " + serviceName;
            throw new ProjectAdminDaoException(this.msg, e);
        } finally {
            router = null;
        }

        RSCustomerSearch results = null;
        if (resultsObj != null && resultsObj instanceof RSCustomerSearch) {
            results = (RSCustomerSearch) resultsObj;
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#updateClient(org.dto.ClientDto)
     */
    private int addClient(ClientDto client) throws ProjectApiException {
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
    public int updateProject(ProjectDto project) throws ProjectApiException {
        // Validate project object
        this.validateProject(project);

        // Make API call to determine client's existence. When new, add to the
        // database. If client does not exist in the Proj_Client table then add.
        int clientId = project.getClientId();
        ClientDto client;
        try {
            // Check if client exists locally
            client = this.getClient(clientId);
        } catch (ProjectApiException e) {
            this.msg = "Error fetching client: " + clientId;
            throw new ProjectApiException(this.msg, e);
        }

        if (client == null) {
            // Try to create client row
            client = this.getClientExt(clientId);
            if (client == null) {
                this.msg = "Error updating Project due to client does not exists as a customer in the accounting system.  Client Id is "
                        + clientId;
                throw new ProjectApiException(this.msg);
            }
            this.addClient(client);
        }

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
        if (proj == null) {
            throw new InvalidProjectException(
                    "Project update operation failed due to an invalid or null ProjectDto object discovered");
        }
        if (proj.getProjectDescription() == null) {
            throw new InvalidProjectException("Project description is required");
        }

        if (proj.getProjectEffectiveDate() == null) {
            throw new InvalidProjectException("Project effective date is required");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#updateTask(org.dto.TaskDto)
     */
    @Override
    public int updateTask(TaskDto task) throws ProjectApiException {
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
        if (task == null) {
            throw new InvalidTaskException("Task object cannot be null");
        }
        if (task.getTaskDescription() == null) {
            throw new InvalidTaskException("Task Description is required");
        }
    }

}
