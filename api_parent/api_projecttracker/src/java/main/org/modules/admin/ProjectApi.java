package org.modules.admin;

import java.util.Date;
import java.util.List;

import org.dto.ClientDto;
import org.dto.EventDto;
import org.dto.ProjectDto;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TaskDto;

import com.api.foundation.TransactionApi;

/**
 * An API interface for managing the components of a project such as clients,
 * projects, tasks, and events.
 * <p>
 * Functional examples would be the creation and management of clients, tasks,
 * and events.
 * 
 * @author Roy Terrell
 * 
 */
public interface ProjectApi extends TransactionApi {

    /**
     * Obtains a list of all clients
     * 
     * @return A List of {@link ClientDto} objects or null if nothing is found.
     * @throws ProjectApiException
     */
    List<ClientDto> getAllClients() throws ProjectApiException;

    /**
     * Finds a single client using client id.
     * 
     * @param clientId
     *            The client's id
     * @return An {@link ClientDto} or null if nothing is found.
     * @throws ProjectApiException
     */
    ClientDto getClient(int clientId) throws ProjectApiException;

    /**
     * Find a single client containing customer data from the Accounting System
     * using client id.
     * 
     * @param clientId
     *            The client's id
     * @return A {@link ClientDto} object or null if nothing is found.
     * @throws ProjectApiException
     *             when two or more clients are returned as a result of
     *             <i>clientId</i> not being unique.
     * @throws NotFoundException
     *             when the client is not found.
     */
    ClientDto getClientExt(int clientId) throws ProjectApiException;

    /**
     * Find all clients with extended data.
     * 
     * @return A List of {@link ClientDto} objects or null if nothing is found.
     * @throws ProjectApiException
     */
    List<ClientDto> getAllClientExt() throws ProjectApiException;

    /**
     * Find all projects.
     * 
     * @return A List of {@link ProjectDto} objects or null if nothing is found.
     * @throws ProjectApiException
     */
    List<ProjectDto> getAllProjects() throws ProjectApiException;

    /**
     * Finds a project using projectId.
     * 
     * @param projectId
     *            The id of a project.
     * @return A {@link ProjectDto} object or null if nothing is found.
     * @throws ProjectApiException
     */
    ProjectDto getProject(int projectId) throws ProjectApiException;

    /**
     * Find one or more projects using the clinet's id.
     * 
     * @param clientId
     *            The id of the client.
     * @return A List of {@link ProjectDto} objects or null if nothing is found.
     * @throws ProjectApiException
     */
    List<ProjectDto> getProjectByClientId(int clientId)
            throws ProjectApiException;

    /**
     * Finds a tasks using task id.
     * 
     * @param taskId
     *            The Id of the task to locate.
     * @return {@link TaskDto} or null if nothing is found.
     * @throws ProjectApiException
     */
    TaskDto getTask(int taskId) throws ProjectApiException;

    /**
     * Returns a list of all tasks.
     * 
     * @return A List of {@link ProjectDto} objects or null if nothing is found.
     * @throws ProjectApiException
     */
    List<TaskDto> getAllTasks() throws ProjectApiException;

    /**
     * Find tasks based on the billable flag.
     * 
     * @param billable
     *            A boolean value which indicates to target billable or
     *            non-billable tasks.
     * @return List of {@link ProjectDto} objects or null if nothing is found.
     * @throws ProjectApiException
     */
    List<TaskDto> getTasks(boolean billable) throws ProjectApiException;

    /**
     * Retrieves a project task record
     * 
     * @param projectTaskId
     *            The id of the project-task record
     * @return An {@link ProjectTaskDto} object or null if not found.
     * @throws ProjectApiException
     */
    ProjectTaskDto getProjectTask(int projectTaskId) throws ProjectApiException;

    /**
     * Retrieves one or more extended project task records by client
     * 
     * @param clientId
     *            The id of the client
     * @return A List of {@link ProjectTaskDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectTaskDto> getProjectTaskByClient(int clientId)
            throws ProjectApiException;

    /**
     * Retrieves one or more extended project task records by project
     * 
     * @param projectId
     *            The id of the timesheet
     * @return A List of {@link ProjectTaskDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectTaskDto> getProjectTaskByProject(int projectId)
            throws ProjectApiException;

    /**
     * Retrieves one or more extended project task records by task
     * 
     * @param taskId
     *            The id of the task
     * @return A List of {@link ProjectTaskDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectTaskDto> getProjectTaskByTask(int taskId)
            throws ProjectApiException;

    /**
     * Retrieves one or more extended project task records by project and task
     * 
     * @param projectId
     *            The id of the project
     * @param taskId
     *            taskId The id of the task
     * @return A List of {@link ProjectTaskDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectTaskDto> getProjectTask(int projectId, int taskId)
            throws ProjectApiException;

    /**
     * Retrieves a timesheet event record
     * 
     * @param eventId
     *            The id of the event.
     * @return An {@link EventDto} object or null if not found..
     * @throws ProjectApiException
     */
    EventDto getEvent(int eventId) throws ProjectApiException;

    /**
     * Retrieves event records by event date
     * 
     * @param eventDate
     *            The event date to filter data
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<EventDto> getEvent(Date eventDate) throws ProjectApiException;

    /**
     * Retrieves event records based on a date range using the beginning and
     * ending event dates
     * 
     * @param beginDate
     *            The beginning of the date range.
     * @param endDate
     *            The ending of the date range.
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<EventDto> getEvent(Date beginDate, Date endDate)
            throws ProjectApiException;

    /**
     * Retrieves one or more event records using Project-Task Id
     * 
     * @param projectTaskId
     *            The id of the project-task
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<EventDto> getEventByProjectTask(int projectTaskId)
            throws ProjectApiException;

    /**
     * Retrieves timesheet extended event records by client
     * 
     * @param clientId
     *            The id of the client
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws TimesheetApiException
     */
    List<ProjectEventDto> getEventByClient(int clientId)
            throws ProjectApiException;

    /**
     * Retrieves extended timesheet event records by project
     * 
     * @param projectId
     *            The id of the project
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectEventDto> getEventByProject(int projectId)
            throws ProjectApiException;

    /**
     * Retrieves extended event records by task
     * 
     * @param taskId
     *            The id of the task
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectEventDto> getEventByTask(int taskId) throws ProjectApiException;

    /**
     * Creates new or updates an existing project.
     * 
     * @param project
     *            An instance of {@link ProjectDto}
     * @return The id of the new project created or the total number of existing
     *         projects modified.
     * @throws ProjectApiException
     */
    int updateProject(ProjectDto project) throws ProjectApiException;

    /**
     * Creates new or updates an existing project task
     * 
     * @param task
     *            An instance of {@link TaskDto}
     * @return The id of the new task created or the total number of existing
     *         tasks modified.
     * @throws ProjectApiException
     */
    int updateTask(TaskDto task) throws ProjectApiException;

}
