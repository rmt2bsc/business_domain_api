package org.modules.admin;

import java.util.Date;
import java.util.List;

import org.dto.ClientDto;
import org.dto.EventDto;
import org.dto.ProjectClientDto;
import org.dto.Project2Dto;
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
     *            an instance of {@link Project2Dto} representing selection
     *            criteria.
     * @return A List of {@link Project2Dto} objects or null if nothing is found.
     * @throws ProjectAdminApiException
     */
    List<Project2Dto> getProject(Project2Dto criteria) throws ProjectAdminApiException;

    /**
     * Find list of project/client data based on selection criteria provided.
     * 
     * @param criteria
     *            an instance of {@link ProjectClientDto} representing selection
     *            criteria.
     * @return A List of {@link ProjectClientDto} objects or null if nothing is
     *         found.
     * @throws ProjectAdminApiException
     */
    List<ProjectClientDto> getProjectExt(ProjectClientDto criteria) throws ProjectAdminApiException;

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
     * Find list of events based on selection criteria provided.
     * 
     * @param criteria
     *            an instance of {@link EventDto} representing selection
     *            criteria.
     * @param beginDate
     *            The beginning of the date range. Optional.
     * @param endDate
     *            The ending of the date range. Optional.
     * @return A List of {@link EventDto} objects or null if nothing is found.
     * @throws ProjectAdminApiException
     */
    List<EventDto> getEvent(EventDto criteria, Date beginDate, Date endDate) throws ProjectAdminApiException;
    
    /**
     * Find list of project related tasks based on selection criteria provided.
     * 
     * @param criteria
     *            an instance of {@link ProjectTaskDto} representing selection
     *            criteria.
     * @return A List of {@link ProjectTaskDto} objects or null if nothing is found.
     * @throws ProjectAdminApiException
     */
    List<ProjectTaskDto> getProjectTask(ProjectTaskDto criteria) throws ProjectAdminApiException;
    
    /**
     * Find list of project-event related tasks based on selection criteria provided.
     * 
     * @param criteria
     *            an instance of {@link ProjectEventDto} representing selection
     *            criteria.
     * @return A List of {@link ProjectEventDto} objects or null if nothing is found.
     * @throws ProjectAdminApiException
     */
    List<ProjectEventDto> getProjectEvent(ProjectEventDto criteria) throws ProjectAdminApiException;

    /**
     * Creates new or updates an existing client.
     * 
     * @param client
     *            An instance of {@link ClientDto}
     * @return The id of the new client created or the total number of existing
     *         clients modified.
     * @throws ProjectApiException
     */
    int updateClient(ClientDto client) throws ProjectAdminApiException;
    
    /**
     * Creates new or updates an existing project.
     * 
     * @param project
     *            An instance of {@link Project2Dto}
     * @return The id of the new project created or the total number of existing
     *         projects modified.
     * @throws ProjectApiException
     */
    int updateProject(Project2Dto project) throws ProjectAdminApiException;

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
    
    /**
     * Deletes one or more clients from a persistent data source based on criteria selected.
     * 
     * @param client
     *            an instance of {@link ClientDto} representing selection
     *            criteria.
     * @return the number of instances deleted
     * @throws ProjectApiException
     */
    int  deleteClient(ClientDto client) throws ProjectAdminApiException;
    
    /**
     * Deletes one or more projects from a persistent data source based on criteria selected.
     * 
     * @param project
     *            an instance of {@link Project2Dto} representing selection
     *            criteria.
     * @return the number of instances deleted
     * @throws ProjectApiException
     */
    int deleteProject(Project2Dto project) throws ProjectAdminApiException;
    
    /**
     * Deletes one or more tasks from a persistent data source based on criteria
     * selected.
     * 
     * @param task
     *            an instance of {@link TaskDto} representing selection
     *            criteria.
     * @return the number of instances deleted
     * @throws ProjectApiException
     */
    int deleteTask(TaskDto task) throws ProjectAdminApiException;
}
