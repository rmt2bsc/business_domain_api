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
public interface ProjectAdminApi extends TransactionApi {
    
    /**
     * Obtains a list of all clients based on criteria selected.
     * 
     * @param criteria
     *            an instance of {@link ClientDto} representing selection
     *            criteria.
     * @return A List of {@link ClientDto} objects or null if nothing is found.
     * @throws ProjectApiException
     */
    List<ClientDto> getClient(ClientDto criteria) throws ProjectAdminApiException;

    /**
     * Find list of projects based on selection criteria provided.
     * 
     * @param criteria
     *            an instance of {@link ProjectDto} representing selection
     *            criteria.
     * @return A List of {@link ProjectDto} objects or null if nothing is found.
     * @throws ProjectAdminApiException
     */
    List<ProjectDto> getProject(ProjectDto criteria) throws ProjectAdminApiException;

    /**
     * Find list of tasks based on selection criteria provided.
     * 
     * @param criteria
     *            an instance of {@link TaskDto} representing selection
     *            criteria.
     * @return A List of {@link TaskDto} objects or null if nothing is found.
     * @throws ProjectAdminApiException
     */
    List<TaskDto> getTask(TaskDto criteria) throws ProjectAdminApiException;

    /**
     * Retrieves a project task record
     * 
     * @param projectTaskId
     *            The id of the project-task record
     * @return An {@link ProjectTaskDto} object or null if not found.
     * @throws ProjectApiException
     */
    ProjectTaskDto getProjectTask(Integer projectTaskId) throws ProjectAdminApiException;

    /**
     * Retrieves one or more extended project task records by client
     * 
     * @param clientId
     *            The id of the client
     * @return A List of {@link ProjectTaskDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectTaskDto> getProjectTaskByClient(Integer clientId) throws ProjectAdminApiException;

    /**
     * Retrieves one or more extended project task records by project
     * 
     * @param projectId
     *            The id of the timesheet
     * @return A List of {@link ProjectTaskDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectTaskDto> getProjectTaskByProject(Integer projectId) throws ProjectAdminApiException;

    /**
     * Retrieves one or more extended project task records by task
     * 
     * @param taskId
     *            The id of the task
     * @return A List of {@link ProjectTaskDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectTaskDto> getProjectTaskByTask(Integer taskId) throws ProjectAdminApiException;

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
    List<ProjectTaskDto> getProjectTask(Integer projectId, Integer taskId) throws ProjectAdminApiException;

    /**
     * Retrieves a timesheet event record
     * 
     * @param eventId
     *            The id of the event.
     * @return An {@link EventDto} object or null if not found..
     * @throws ProjectApiException
     */
    EventDto getEvent(Integer eventId) throws ProjectAdminApiException;

    /**
     * Retrieves event records by event date
     * 
     * @param eventDate
     *            The event date to filter data
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<EventDto> getEvent(Date eventDate) throws ProjectAdminApiException;

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
    List<EventDto> getEvent(Date beginDate, Date endDate) throws ProjectAdminApiException;

    /**
     * Retrieves one or more event records using Project-Task Id
     * 
     * @param projectTaskId
     *            The id of the project-task
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<EventDto> getEventByProjectTask(Integer projectTaskId) throws ProjectAdminApiException;

    /**
     * Retrieves timesheet extended event records by client
     * 
     * @param clientId
     *            The id of the client
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws TimesheetApiException
     */
    List<ProjectEventDto> getEventByClient(Integer clientId) throws ProjectAdminApiException;

    /**
     * Retrieves extended timesheet event records by project
     * 
     * @param projectId
     *            The id of the project
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectEventDto> getEventByProject(Integer projectId) throws ProjectAdminApiException;

    /**
     * Retrieves extended event records by task
     * 
     * @param taskId
     *            The id of the task
     * @return A List of {@link EventDto} objects or null if not found.
     * @throws ProjectApiException
     */
    List<ProjectEventDto> getEventByTask(Integer taskId) throws ProjectAdminApiException;

    /**
     * Creates new or updates an existing project.
     * 
     * @param project
     *            An instance of {@link ProjectDto}
     * @return The id of the new project created or the total number of existing
     *         projects modified.
     * @throws ProjectApiException
     */
    int updateProject(ProjectDto project) throws ProjectAdminApiException;

    /**
     * Creates new or updates an existing project task
     * 
     * @param task
     *            An instance of {@link TaskDto}
     * @return The id of the new task created or the total number of existing
     *         tasks modified.
     * @throws ProjectApiException
     */
    int updateTask(TaskDto task) throws ProjectAdminApiException;
}
