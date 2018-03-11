package org.modules.employee;

import java.util.List;

import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EmployeeTitleDto;
import org.dto.EmployeeTypeDto;
import org.dto.ProjectEmployeeDto;

import com.api.foundation.TransactionApi;

/**
 * An API interface for managing project employees.
 * 
 * @author Roy Terrell
 * 
 */
public interface EmployeeApi extends TransactionApi {

    /**
     * Finds Employee by employee Id.
     * 
     * @param empId
     *            The employee id.
     * @return an {@link EmployeeDto} object representing the employee when
     *         found. Otherwise, null is returned.
     * @throws EmployeeApiException
     */
    EmployeeDto getEmployee(Integer empId) throws EmployeeApiException;

    /**
     * Find Employees using selection criteria.
     * 
     * @param criteria
     *            The employee id.
     * @return a List of {@link EmployeeDto} objects when found. Otherwise, null
     *         is returned.
     * @throws EmployeeApiException
     */
    List<EmployeeDto> getEmployee(EmployeeDto criteria) throws EmployeeApiException;

    /**
     * Retrieves the master list of employee titles.
     * 
     * @return a List of {@link EmployeeTitleDto} objects or null when not
     *         found.
     * @throws EmployeeApiException
     */
    List<EmployeeTitleDto> getEmployeeTitles() throws EmployeeApiException;

    /**
     * Retrieves the master list of employee types.
     * 
     * @return a List of {@link EmployeeTypeDto} objects or null when not found.
     * @throws EmployeeApiException
     */
    List<EmployeeTypeDto> getEmployeeTypes() throws EmployeeApiException;

    /**
     * Retrieves all managers
     * 
     * @return a List of {@link EmployeeDto} objects when found. Otherwise, null
     *         is returned.
     * @throws EmployeeApiException
     */
    List<EmployeeDto> getManagers() throws EmployeeApiException;

    /**
     * Retrieves a list of all clients that have projects assigned to a
     * particular employee.
     * 
     * @param empId
     *            the employee id.
     * @return A List of {@link ClientDto} objects or null if nothing is found.
     * @throws EmployeeApiException
     */
    List<ClientDto> getClients(Integer empId) throws EmployeeApiException;

    /**
     * Retrieves all projects relative to a given employee
     * 
     * @param empId
     *            The employee id
     * @return a List of {@link ProjectEmployeeDto} objects. Otherwise, null is
     *         returned.
     * @throws EmployeeApiException
     */
    List<ProjectEmployeeDto> getProjects(Integer empId) throws EmployeeApiException;

    /**
     * Retrieves a single employee project profile.
     * 
     * @param empProjId
     *            The employee project id
     * @return a {@link ProjectEmployeeDto} object representing the employee
     *         project. Otherwise, null is returned.
     * @throws EmployeeApiException
     */
    ProjectEmployeeDto getProject(Integer empProjId) throws EmployeeApiException;

    /**
     * Retrieves a single employee/project related to a given employee and
     * project
     * 
     * @param empId
     *            The employee id
     * @param projId
     *            The project id
     * @return a {@link ProjectEmployeeDto} object representing the
     *         employee/project when found. Otherwise, null is returned.
     * @throws EmployeeApiException
     */
    ProjectEmployeeDto getProject(Integer empId, Integer projId) throws EmployeeApiException;

    /**
     * Creates a new or updates an existing employee by applying any changes
     * that exist in <i>employee</i>.
     * 
     * @param employee
     *            an instance of {@link EmployeeDto}
     * @return The id when a new employee is created or the total number of
     *         existing employees effect by the transaction.
     * @throws EmployeeApiException
     *             If <i>employee</i> fails validations or a general data access
     *             error occurs.
     */
    int update(EmployeeDto employee) throws EmployeeApiException;

    /**
     * Creates a new or updates an existing project/employee by applying any
     * changes that exist in <i>projEmployee</i>.
     * 
     * @param projEmployee
     *            an instance of {@link projEmployee}
     * @return The id when a new project/employee is created or the total number
     *         of existing project/employees effect by the transaction.
     * @throws EmployeeApiException
     *             If <i>projEmployee</i> fails validations or a general data
     *             access error occurs.
     */
    int update(ProjectEmployeeDto projEmployee) throws EmployeeApiException;
}
