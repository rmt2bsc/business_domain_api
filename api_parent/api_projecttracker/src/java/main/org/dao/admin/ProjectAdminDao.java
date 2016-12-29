package org.dao.admin;

import java.util.List;

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

import com.api.persistence.DaoClient;

/**
 * Contract for creating, accessing and managing data pertaining to projects.
 * This includes data for entities such as clients, employees, projects, and
 * tasks.
 * 
 * @author Roy Tererll
 * 
 */
public interface ProjectAdminDao extends DaoClient {

    /**
     * Fetches one or more project clients using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all clients, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link ClientDto} to filter results or null to
     *            fetch all accounts.
     * @return A List of {@link ClientDto} or null if no data is found.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    List<ClientDto> fetchClient(ClientDto criteria)
            throws ProjectAdminDaoException;

    /**
     * Fetches one or more project clients including contact inforatmion using
     * selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all clients, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link ClientDto} to filter results or null to
     *            fetch all accounts.
     * @return A List of {@link ClientDto} or null if no data is found.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    List<ClientDto> fetchClientExt(ClientDto criteria)
            throws ProjectAdminDaoException;

    /**
     * Fetches one or more projects using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all projects, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link ProjectDto} to filter results or null to
     *            fetch all projects.
     * @return A List of {@link ProjectDto} or null if no data is found.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    List<ProjectDto> fetchProject(ProjectDto criteria)
            throws ProjectAdminDaoException;

    /**
     * Fetches one or more employees using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all employees, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link EmployeeDto} to filter results or null
     *            to fetch all employees.
     * @return A List of {@link EmployeeDto} or null if no data is found.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    List<EmployeeDto> fetchEmployee(EmployeeDto criteria)
            throws EmployeeDaoException;

    /**
     * Fetches one or more employee titles using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all employee titles, pass <i>criteria</i> as
     * a null value.
     * 
     * @param criteria
     *            an instance of {@link EmployeeTitleDto} to filter results or
     *            null to fetch all employee titles.
     * @return A List of {@link EmployeeTitleDto} or null if no data is found.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    List<EmployeeTitleDto> fetchEmployeeTitle(EmployeeTitleDto criteria)
            throws EmployeeDaoException;

    /**
     * Fetches one or more employee types using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all employee types, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link EmployeeTypeDto} to filter results or
     *            null to fetch all employee types.
     * @return A List of {@link EmployeeTypeDto} or null if no data is found.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    List<EmployeeTypeDto> fetchEmployeeType(EmployeeTypeDto criteria)
            throws EmployeeDaoException;

    /**
     * Fetches one or more tasks using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all tasks, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link TaskDto} to filter results or null to
     *            fetch all tasks.
     * @return A List of {@link TaskDto} or null if no data is found.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    List<TaskDto> fetchTask(TaskDto criteria) throws ProjectAdminDaoException;

    /**
     * Fetches one or more project events using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all project events, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link EventDto} to filter results or null to
     *            fetch all events.
     * @return A List of {@link EventDto} or null if no data is found.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    List<EventDto> fetchEvent(EventDto criteria)
            throws ProjectAdminDaoException;

    /**
     * Fetches one or more project/client using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all project/clients, pass <i>criteria</i> as
     * a null value.
     * 
     * @param criteria
     *            an instance of {@link ProjectClientDto} to filter results or
     *            null to fetch all project/clients.
     * @return A List of {@link ProjectClientDto} or null if no data is found.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    List<ProjectClientDto> fetchProjectClient(ProjectClientDto criteria)
            throws ProjectAdminDaoException;

    /**
     * Fetches one or more project/employees using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all project/employees, pass <i>criteria</i>
     * as a null value.
     * 
     * @param criteria
     *            an instance of {@link ProjectEmployeeDto} to filter results or
     *            null to fetch all project/employees.
     * @return A List of {@link ProjectEmployeeDto} or null if no data is found.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    List<ProjectEmployeeDto> fetchProjectEmployee(ProjectEmployeeDto criteria)
            throws EmployeeDaoException;

    /**
     * Fetches one or more project/tasks using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all project/tasks, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link ProjectTaskDto} to filter results or
     *            null to fetch all project/tasks.
     * @return A List of {@link ProjectTaskDto} or null if no data is found.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    List<ProjectTaskDto> fetchProjectTask(ProjectTaskDto criteria)
            throws ProjectAdminDaoException;

    /**
     * Fetches one or more project/event using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all project/event, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link ProjectEventDto} to filter results or
     *            null to fetch all project/event.
     * @return A List of {@link ProjectEventDto} or null if no data is found.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    List<ProjectEventDto> fetchProjectEvent(ProjectEventDto criteria)
            throws ProjectAdminDaoException;

    /**
     * Creates a new client.
     * 
     * @param obj
     *            an instance of {@link ClientDto} representing the client to be
     *            created.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    void insertClient(ClientDto obj) throws ProjectAdminDaoException;

    /**
     * Modifies an existing client.
     * 
     * @param obj
     *            an instance of {@link ClientDto} representing the client to be
     *            updated.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    void updateClient(ClientDto obj) throws ProjectAdminDaoException;

    /**
     * Creates a new or updates an existing project.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable.
     * 
     * @param obj
     *            an instance of {@link ProjectDto} representing the project to
     *            be updated.
     * @return total number of projects updated or the id of the new project
     *         created.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    int maintainProject(ProjectDto obj) throws ProjectAdminDaoException;

    /**
     * Creates a new or updates an existing employee.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable.
     * 
     * @param obj
     *            an instance of {@link EmployeeDto} representing the employee
     *            to be updated.
     * @return total number of employees updated or the id of the new employee
     *         created.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    int maintainEmployee(EmployeeDto obj) throws EmployeeDaoException;

    /**
     * Creates a new or updates an existing employee type codes.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable.
     * 
     * @param obj
     *            an instance of {@link EmployeeTypeDto} representing the
     *            employee to be updated.
     * @return total number of employees updated or the id of the new employee
     *         type created.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    int maintainEmployeeType(EmployeeTypeDto obj) throws EmployeeDaoException;

    /**
     * Creates a new or updates an existing employee title codes.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable.
     * 
     * @param obj
     *            an instance of {@link EmployeeTitleDto} representing the
     *            employee to be updated.
     * @return total number of employees updated or the id of the new employee
     *         title created.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    int maintainEmployeeTitle(EmployeeTitleDto obj) throws EmployeeDaoException;

    /**
     * Creates a new or updates an existing task.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable.
     * 
     * @param obj
     *            an instance of {@link TaskDto} representing the task to be
     *            updated.
     * @return total number of tasks updated or the id of the new task created.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    int maintainTask(TaskDto obj) throws ProjectAdminDaoException;

    /**
     * Creates a new or updates an existing project employee.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable.
     * 
     * @param obj
     *            an instance of {@link TaskDto} representing the
     *            project/employee to be updated.
     * @return total number of project employees updated or the id of the new
     *         project employee created.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    int maintainProjectEmployee(ProjectEmployeeDto obj)
            throws EmployeeDaoException;

    /**
     * Creates a new project task.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable.
     * 
     * @param obj
     *            an instance of {@link ProjectTaskDto} representing the project
     *            task to be updated.
     * @return the id of the new project task created.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    int createProjectTask(ProjectTaskDto obj) throws ProjectAdminDaoException;

    /**
     * Deletes one or more project clients using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all clients, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link ClientDto} used as a filter to target a
     *            particular set of clients to delete.
     * @return total number of clients deleted.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    int deleteClient(ClientDto criteria) throws ProjectAdminDaoException;

    /**
     * Deletes one or more projects using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all projects, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link ProjectDto} used as a filter to target a
     *            particular set of projects to delete.
     * @return total number of projects deleted.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    int deleteProject(ProjectDto criteria) throws ProjectAdminDaoException;

    /**
     * Deletes one or more employees using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all employees, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link EmployeeDto} used as a filter to target
     *            a particular set of employees to delete.
     * @return total number of employees deleted.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    int deleteEmployee(EmployeeDto criteria) throws EmployeeDaoException;

    /**
     * Deletes one or more employee titles using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all employee titles, pass <i>criteria</i> as
     * a null value.
     * 
     * @param criteria
     *            an instance of {@link EmployeeTitleDto} used as a filter to
     *            target a particular set of employee titles to delete.
     * @return total number of employee titles deleted.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    int deleteEmployeeTitle(EmployeeTitleDto criteria)
            throws EmployeeDaoException;

    /**
     * Deletes one or more employee types using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all employee types, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link EmployeeTypeDto} used as a filter to
     *            target a particular set of employee types to delete.
     * @return total number of employee types deleted.
     * @throws EmployeeDaoException
     *             general data access errors
     */
    int deleteEmployeeType(EmployeeTypeDto criteria)
            throws EmployeeDaoException;

    /**
     * Deletes one or more tasks using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all tasks, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link TaskDto} used as a filter to target a
     *            particular set of tasks to delete.
     * @return total number of tasks deleted.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    int deleteTask(TaskDto criteria) throws ProjectAdminDaoException;

    /**
     * Deletes one or more project tasks using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all project tasks, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link ProjectTaskDto} used as a filter to
     *            target a particular set of project tasks to delete.
     * @return total number of project tasks deleted.
     * @throws ProjectAdminDaoException
     *             general data access errors
     */
    int deleteProjectTask(ProjectTaskDto criteria)
            throws ProjectAdminDaoException;

}
