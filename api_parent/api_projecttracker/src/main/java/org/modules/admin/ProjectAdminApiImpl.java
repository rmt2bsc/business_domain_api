package org.modules.admin;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.ProjecttrackerDaoException;
import org.dao.admin.ProjectAdminDao;
import org.dao.admin.ProjectAdminDaoException;
import org.dao.admin.ProjectAdminDaoFactory;
import org.dto.ClientDto;
import org.dto.EventDto;
import org.dto.Project2Dto;
import org.dto.ProjectClientDto;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TaskDto;
import org.modules.ProjectTrackerApiConst;
import org.rmt2.constants.ApiHeaderNames;
import org.rmt2.constants.ApiTransactionCodes;
import org.rmt2.jaxb.AddressBookRequest;
import org.rmt2.jaxb.AddressBookResponse;
import org.rmt2.jaxb.BusinessContactCriteria;
import org.rmt2.jaxb.BusinessType;
import org.rmt2.jaxb.ContactCriteriaGroup;
import org.rmt2.jaxb.ContactDetailGroup;
import org.rmt2.jaxb.HeaderType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.ReplyStatusType;
import org.rmt2.util.HeaderTypeBuilder;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.config.ConfigConstants;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.foundation.TransactionApiException;
import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.messaging.webservice.router.MessageRoutingException;
import com.api.persistence.DaoClient;
import com.api.util.RMT2Date;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * Api Implementation of {@link ProjectApi} that manages the creation, querying,
 * updating, and deletion of project entities.
 * 
 * @author Roy Terrell
 * 
 */
public class ProjectAdminApiImpl extends AbstractTransactionApiImpl implements ProjectAdminApi {

    private static final Logger logger = Logger.getLogger(ProjectAdminApiImpl.class);

    private ProjectAdminDaoFactory daoFact;

    private ProjectAdminDao dao;


    /**
     * Creates a ProjectAdminApiImpl object in which the configuration is
     * identified by the name of a given application.
     * 
     * @param appName
     */
    protected ProjectAdminApiImpl(String appName) {
        super(appName);
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        this.dao.setDaoUser(this.apiUser);
        logger.info("ProjectAdminApiImpl created with DAO, " + this.dao.getClass().getSimpleName() + ", for application, " + appName);
        return;
    }

    /**
     * Creates an ProjectApiImpl initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param dao
     */
    protected ProjectAdminApiImpl(DaoClient dao) {
        super(ProjectTrackerApiConst.DEFAULT_CONTEXT_NAME, dao);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
        logger.info("ProjectAdminApiImpl created with outside Api DAO, " + this.dao.getClass().getSimpleName());
    }

    /**
     * 
     * @param appName
     * @param dao
     */
    protected ProjectAdminApiImpl(String appName, DaoClient dao) {
        super(appName, dao);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
        logger.info("ProjectAdminApiImpl created with outside Api DAO, " + this.dao.getClass().getSimpleName() + " identified by application name, " + appName);
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

    @Override
    public List<ClientDto> getClient(ClientDto criteria) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Client criteria is required");
        }

        List<ClientDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchClient(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving client(s) by selection criteria: ");
            buf.append(criteria.toString());
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectAdminApi#getProject(org.dto.Project2Dto)
     */
    @Override
    public List<Project2Dto> getProject(Project2Dto criteria) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Project criteria is required");
        }

        List<Project2Dto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProject(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project(s) by selection criteria: ");
            buf.append(criteria.toString());
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectAdminApi#getProjectExt(org.dto.Project2Dto)
     */
    @Override
    public List<ProjectClientDto> getProjectExt(ProjectClientDto criteria) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Project/Client criteria is required");
        }

        List<ProjectClientDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectClient(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project/client(s) by selection criteria: ");
            buf.append(criteria.toString());
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new ProjectAdminApiException(e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectAdminApi#getTask(org.dto.TaskDto)
     */
    @Override
    public List<TaskDto> getTask(TaskDto criteria) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Task criteria is required");
        }

        List<TaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving task(s) by selection criteria: ");
            buf.append(criteria.toString());
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectAdminApi#getEvent(org.dto.EventDto)
     */
    @Override
    public List<EventDto> getEvent(EventDto criteria, Date beginDate, Date endDate) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Event criteria is required");
        }
        List<EventDto> results;
        
        // See if we need to build a date range for event date column.
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
        criteria.setCriteria(sql.toString());
        try {
            results = dao.fetchEvent(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            StringBuilder buf = new StringBuilder();
            buf.append("Database error occurred retrieving event(s) by selection criteria");
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }

    @Override
    public List<ProjectTaskDto> getProjectTask(ProjectTaskDto criteria) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Project-Task criteria is required");
        }
        
        List<ProjectTaskDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectTask(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project/task objects by selection criteria, ");
            buf.append(criteria.toString());
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
        return results;
    }
 
    @Override
    public List<ProjectEventDto> getProjectEvent(ProjectEventDto criteria) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Project-Event criteria is required");
        }
        List<ProjectEventDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectEvent(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving events by project/event by selection criteria, ");
            buf.append(criteria.toString());
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
     * @throws NotFoundException <i>client</i> does not exists in the project tracker system.            
     */
    protected void validateClient(ClientDto client) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(client);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client data is required");
        }
        try {
            Verifier.verifyNotNegative(client.getClientId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("The client id must be greater than or equal to zero");
        }

        try {
            Verifier.verifyNotEmpty(client.getClientName());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("The client's name is required");
        }
        
        // For new clients, exit.
        if (client.getClientId() == 0) {
            return;
        }
        return;
    }

    @Override
    public int updateClient(ClientDto client) throws ProjectAdminApiException {
        int rc = this.updateClientWithoutNotification(client);
        try {
            // Logic to call web service that will send client updates to
            // the AddressBook application. This is in case that changes occurred
            // for client name, contact firstname, contact lastname, contact email,
            // et cetera. Most likely, this will be a call to some mechanism that
            // will put the intended message payload on a JMS queue / topic just as
            // the public server does when contacting one of the internal API's to
            // delagate the user's request.
            
            // Build request object with client data
            ObjectFactory f = new ObjectFactory();
            AddressBookRequest req = f.createAddressBookRequest();
            req.setHeader(this.createRequestHeader());

            // Fetch the the client's BusinessType record from the AddressBook
            // app and assign the changes from the "clinet" object so that other
            // BusinessType properties that are not visible to application will
            // not get over written. Setup business contact criteria
            BusinessContactCriteria criteria = f.createBusinessContactCriteria();
            criteria.setContactId(BigInteger.valueOf(client.getBusinessId()));
            ContactCriteriaGroup criteriaGrp = f.createContactCriteriaGroup();
            criteriaGrp.setBusinessCriteria(criteria);
            req.setCriteria(criteriaGrp);

            // Route message to business server
            AddressBookResponse r = f.createAddressBookResponse();
            Object response = this.sendMessage(ApiTransactionCodes.CONTACTS_GET, req);
            if (response != null && response instanceof AddressBookResponse) {
                r = (AddressBookResponse) response;
            }
            else {
                throw new NotFoundException("Client does not have a matching AddressBook profile: client id="
                        + client.getClientId() + ", business id=" + client.getBusinessId());
            }
            
            // Update existing BusinessType object, which was fetched from the
            // AddressBook application, with client changes
            BusinessType profile = r.getProfile().getBusinessContacts().get(0);
            profile.setLongName(client.getClientName());
            profile.setContactFirstname(client.getClientContactFirstname());
            profile.setContactLastname(client.getClientContactLastname());
            profile.setContactPhone(client.getClientContactPhone());
            profile.setContactExt(client.getClientContactExt());
            profile.setContactEmail(client.getClientContactEmail());
            ContactDetailGroup contactGrp = f.createContactDetailGroup();
            contactGrp.getBusinessContacts().add(profile);
            req.setProfile(contactGrp);

            // Send the modified BusinessType to the AddressBook application to be updated.
            ReplyStatusType reply = (ReplyStatusType) this.sendMessage(ApiTransactionCodes.CONTACTS_UPDATE, req);
            return rc;
        }
        catch (ProjectAdminDaoException e) {
            throw new ProjectAdminApiException("Unable to update Client data", e);
        }
        catch (TransactionApiException e) {
            throw new ProjectAdminApiException("Unable to perform client updates to AddressBook application", e);
        }
        catch (NotFoundException e) {
            throw new ProjectAdminApiException("Client not found in AddressBook application", e);
        }
    }
    
    @Override
    public int updateClientWithoutNotification(ClientDto client) throws ProjectAdminApiException {
        try {
            this.validateClient(client);
        } catch (Exception e) {
            throw new ProjectAdminApiException("Client validation error occurred", e);
        }
        try {
            int rc = this.dao.maintainClient(client);
            return rc;
        } catch (ProjectAdminDaoException e) {
            throw new ProjectAdminApiException("Unable to update Client data", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.admin.ProjectApi#updateProject(org.dto.Project2Dto)
     */
    @Override
    public int updateProject(Project2Dto project) throws ProjectAdminApiException {
        // Validate project object
        try {
            this.validateProject(project);    
        }
        catch (Exception e) {
            throw new ProjectAdminApiException("Project validation error occurred", e);
        }
        
        // Begin to process project data.
        try {
            int rc = this.dao.maintainProject(project);
            return rc;
        }
        catch (ProjectAdminDaoException e) {
            throw new ProjectAdminApiException("Unable to update Project data", e);
        }
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
    protected void validateProject(Project2Dto proj) throws InvalidProjectException {
        try {
            Verifier.verifyNotNull(proj);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project data is required");
        }
        try {
            Verifier.verifyNotNegative(proj.getProjId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("The project id must be greater than or equal zero");
        }
        try {
            Verifier.verifyNotEmpty(proj.getProjectDescription());
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
        try {
            this.validateTask(task);
        }
        catch (Exception e) {
            throw new ProjectAdminApiException("Task validation error occurred", e);
        }
        try {
            int rc = this.dao.maintainTask(task);
            return rc;    
        }
        catch (ProjectAdminDaoException e) {
            throw new ProjectAdminApiException("Unable to update Task data", e);
        }
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
            Verifier.verifyNotNegative(task.getTaskId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("The task id must be greater than or equal zero");
        }
        try {
            Verifier.verifyNotEmpty(task.getTaskDescription());
        }
        catch (VerifyException e) {
            throw new InvalidTaskException("Task Description is required");
        }
    }

    @Override
    public int deleteClient(ClientDto client) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(client);
        } catch (VerifyException e) {
            throw new InvalidDataException("Client criteria is required");
        }
        int rc = 0;
        StringBuilder buf = new StringBuilder();
        try {
            rc = dao.deleteClient(client);
            return rc;
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred deleting client(s) by selection criteria: ");
            buf.append(client.toString());
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
    }

    @Override
    public int deleteProject(Project2Dto project) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(project);
        } catch (VerifyException e) {
            throw new InvalidDataException("Project criteria is required");
        }
        int rc = 0;
        StringBuilder buf = new StringBuilder();
        try {
            rc = dao.deleteProject(project);
            return rc;
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred deleting project(s) by selection criteria: ");
            buf.append(project.toString());
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
    }

    @Override
    public int deleteTask(TaskDto task) throws ProjectAdminApiException {
        try {
            Verifier.verifyNotNull(task);
        } catch (VerifyException e) {
            throw new InvalidDataException("Task criteria is required");
        }
        int rc = 0;
        StringBuilder buf = new StringBuilder();
        try {
            rc = dao.deleteTask(task);
            return rc;
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred deleting task(s) by selection criteria: ");
            buf.append(task.toString());
            this.msg = buf.toString();
            throw new ProjectAdminApiException(this.msg, e);
        }
    }

    @Override
    public Object sendMessage(String messageId, Serializable payload) throws TransactionApiException {
        String msg = null;
        MessageRouterHelper helper = new MessageRouterHelper();
        try {
            return helper.routeXmlMessage(messageId, payload);
        } catch (MessageRoutingException e) {
            msg = "Error occurred routing XML message to its designated API handler";
            throw new TransactionApiException(msg, e);
        }
    }
    
    
    
    private HeaderType createRequestHeader() {
        return HeaderTypeBuilder.Builder.create()
                .withApplication(this.getConfig().getProperty(ConfigConstants.API_APP_CODE_KEY))
                .withModule(ConfigConstants.API_APP_MODULE_VALUE)
                .withMessageMode(ApiHeaderNames.MESSAGE_MODE_REQUEST)
                .withDeliveryDate(new Date())
                
                // Set these header elements with dummy values in order to be properly assigned later.
                .withTransaction(ApiHeaderNames.DUMMY_HEADER_VALUE)
                .withRouting(ApiHeaderNames.DUMMY_HEADER_VALUE)
                .withDeliveryMode(ApiHeaderNames.DUMMY_HEADER_VALUE).build();
    }
}
