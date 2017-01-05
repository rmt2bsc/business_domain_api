package org.modules.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dao.ProjecttrackerDaoException;
import org.dao.admin.ProjectAdminDao;
import org.dao.admin.ProjectAdminDaoFactory;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EmployeeTitleDto;
import org.dto.EmployeeTypeDto;
import org.dto.ProjectEmployeeDto;
import org.dto.adapter.orm.EmployeeObjectFactory;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.modules.ProjectTrackerApiConst;

import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;

/**
 * Manages the creation, modifification, and querying of employee information.
 * 
 * @author Roy Terrell
 * 
 */
class EmployeeApiImpl extends AbstractTransactionApiImpl implements EmployeeApi {

    private static final Logger logger = Logger
            .getLogger(EmployeeApiImpl.class);

    private ProjectAdminDaoFactory daoFact;

    private ProjectAdminDao dao;

    /**
     * Creates an EmployeeApiImpl which creates a stand alone connection.
     */
    protected EmployeeApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmDao();
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an EmployeeApiImpl which creates a stand alone connection.
     * 
     * @param application
     *            name
     */
    protected EmployeeApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an EmployeeApiImpl initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param dao
     */
    protected EmployeeApiImpl(DaoClient dao) {
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
     * @see org.modules.employee.EmployeeApi#getEmployee(int)
     */
    @Override
    public EmployeeDto getEmployee(int empId) throws EmployeeApiException {
        EmployeeDto criteria = EmployeeObjectFactory
                .createEmployeeDtoInstance(null);
        criteria.setEmployeeId(empId);
        List<EmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving all employees");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }

        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single employee object to be returned using employee id, ");
            buf.append(empId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#getEmployee(java.lang.String)
     */
    @Override
    public List<EmployeeDto> getEmployee(String customCriteria)
            throws EmployeeApiException {
        EmployeeDto criteria = EmployeeObjectFactory
                .createEmployeeDtoInstance(null);
        criteria.setCriteria(customCriteria);
        List<EmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving employees using custom criteria: ");
            buf.append(customCriteria);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#getEmployeeByTitle(int)
     */
    @Override
    public List<EmployeeDto> getEmployeeByTitle(int empTitleId)
            throws EmployeeApiException {
        EmployeeDto criteria = EmployeeObjectFactory
                .createEmployeeDtoInstance(null);
        criteria.setEmployeeTitleId(empTitleId);
        List<EmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving employees by employee title, ");
            buf.append(empTitleId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#getManagers()
     */
    @Override
    public List<EmployeeDto> getManagers() throws EmployeeApiException {
        EmployeeDto criteria = EmployeeObjectFactory
                .createEmployeeDtoInstance(null);
        criteria.setIsManager(ProjectTrackerApiConst.EMPLOYEE_MANAGER_FLAG);
        List<EmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving employees who are managers");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#getEmployeeTitles()
     */
    @Override
    public List<EmployeeTitleDto> getEmployeeTitles()
            throws EmployeeApiException {
        EmployeeTitleDto criteria = null;
        List<EmployeeTitleDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEmployeeTitle(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving employee titles");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#getEmployeeTypes()
     */
    @Override
    public List<EmployeeTypeDto> getEmployeeTypes() throws EmployeeApiException {
        EmployeeTypeDto criteria = null;
        List<EmployeeTypeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEmployeeType(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving employee types");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#getClients(int)
     */
    @Override
    public List<ClientDto> getClients(int empId) throws EmployeeApiException {
        List<ProjectEmployeeDto> peList = this.getProjects(empId);
        if (peList == null) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        List<ClientDto> clientList = new ArrayList<ClientDto>();
        for (ProjectEmployeeDto proj : peList) {
            try {
                // Use map to eliminate duplicate entries when building client
                // list.
                String clientId = String.valueOf(proj.getClientId());
                Object result = map.put(clientId, proj.getClientName());
                // A non-null value means a duplicate was found and replaced. No
                // need to add client to List.
                if (result == null) {
                    ClientDto client = ProjectObjectFactory
                            .createClientDtoInstance(null);
                    client.setClientId(proj.getClientId());
                    client.setClientName(proj.getClientName());
                    clientList.add(client);
                }
            } catch (Exception e) {
                this.msg = "Problem building list of employee clients";
                logger.error(this.msg);
                throw new EmployeeApiException(this.msg, e);
            }
        }
        return clientList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#getEmployeeProject(int)
     */
    @Override
    public ProjectEmployeeDto getProject(int empProjId)
            throws EmployeeApiException {
        ProjectEmployeeDto criteria = ProjectObjectFactory
                .createEmployeeProjectDtoInstance(null);
        criteria.setEmpProjId(empProjId);
        List<ProjectEmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project/employee objects by employee project id, ");
            buf.append(empProjId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }
        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single project/employee object to be returned using employee project id, ");
            buf.append(empProjId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#getEmployeeProject(int, int)
     */
    @Override
    public ProjectEmployeeDto getProject(int empId, int projId)
            throws EmployeeApiException {
        ProjectEmployeeDto criteria = ProjectObjectFactory
                .createEmployeeProjectDtoInstance(null);
        criteria.setEmpId(empId);
        criteria.setProjId(projId);
        List<ProjectEmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project/employee objects by employee id, [");
            buf.append(empId);
            buf.append("] and project id [ ");
            buf.append(projId);
            buf.append("]");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }
        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single project/employee object to be returned using employee id [");
            buf.append(empId);
            buf.append("] and project id [ ");
            buf.append(projId);
            buf.append("]");
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#getEmployeeProjectByEmployee(int)
     */
    @Override
    public List<ProjectEmployeeDto> getProjects(int empId)
            throws EmployeeApiException {
        ProjectEmployeeDto criteria = ProjectObjectFactory
                .createEmployeeProjectDtoInstance(null);
        criteria.setEmpId(empId);
        List<ProjectEmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (ProjecttrackerDaoException e) {
            buf.append("Database error occurred retrieving project/employee objects by employee id, ");
            buf.append(empId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#update(org.dto.EmployeeDto)
     */
    @Override
    public int update(EmployeeDto employee) throws EmployeeApiException {
        this.validate(employee);
        int rc = 0;
        if (employee.getEmployeeId() > 0) {
            EmployeeDto delta = this.createDelta(employee);
            rc = this.dao.maintainEmployee(delta);
        }
        else {
            rc = this.dao.maintainEmployee(employee);
        }
        return rc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#update(org.dto.ProjectEmployeeDto)
     */
    @Override
    public int update(ProjectEmployeeDto projEmployee)
            throws EmployeeApiException {
        this.validate(projEmployee);
        int rc = 0;
        if (projEmployee.getEmpProjId() > 0) {
            ProjectEmployeeDto delta = this.createDelta(projEmployee);
            rc = this.dao.maintainProjectEmployee(delta);
        }
        else {
            rc = this.dao.maintainProjectEmployee(projEmployee);
        }
        return rc;
    }

    /**
     * Verifies that the employee contains a title and a type.
     * 
     * @param emp
     * @throws InvalidEmployeeException
     *             If <i>emp</i> does not contain an employee type or an
     *             employee title.
     */
    protected void validate(EmployeeDto emp) throws InvalidEmployeeException {
        if (emp.getEmployeeTitleId() == 0) {
            this.msg = "Employee profile must contain an employee title";
            throw new InvalidEmployeeException(this.msg);
        }
        if (emp.getEmployeeTypeId() == 0) {
            this.msg = "Employee profile must contain an employee type";
            throw new InvalidEmployeeException(this.msg);
        }
    }

    /**
     * Verifies that the employee has never been assinged to the specified
     * project.
     * 
     * @param projEmployee
     *            an instance of {@link ProjectEmployeeDto}
     * @throws InvalidProjectEmployeeException
     *             If <i>projEmployee</i> is null or the employee id/project id
     *             combination already exists in the system.
     */
    protected void validate(ProjectEmployeeDto projEmployee)
            throws InvalidProjectEmployeeException {
        if (projEmployee == null) {
            this.msg = "Project/Employee cannot be null";
            throw new InvalidProjectEmployeeException(this.msg);
        }
        ProjectEmployeeDto projEmpDto;
        try {
            projEmpDto = this.getProject(projEmployee.getEmpId(),
                    projEmployee.getProjId());
            if (projEmpDto != null) {
                StringBuffer buf = new StringBuffer();
                buf.append("The employee is already assigned to the specified project.  Employee Id [");
                buf.append(projEmployee.getEmpId());
                buf.append("] and Project Id [");
                buf.append(projEmployee.getProjId() + "]");
                this.msg = buf.toString();
                throw new InvalidProjectEmployeeException(this.msg);
            }
        } catch (EmployeeApiException e) {
            this.msg = "API error occurred obtaining project data by employee id and project id";
            throw new InvalidProjectEmployeeException(this.msg, e);
        }
        return;
    }

    private EmployeeDto createDelta(EmployeeDto employee)
            throws EmployeeApiException {
        EmployeeDto delta = this.getEmployee(employee.getEmployeeId());
        if (delta == null) {
            this.msg = "Employee API update failed for employee.  The employee does not exist by employee id: "
                    + employee.getEmployeeId();
            throw new InvalidEmployeeException(this.msg);
        }
        // Update employee object obtained from data store with changes.
        delta.setManagerId(employee.getManagerId());
        delta.setEmployeeTitleId(employee.getEmployeeTitleId());
        delta.setEmployeeTypeId(employee.getEmployeeTypeId());
        delta.setSsn(employee.getSsn());
        delta.setStartDate(employee.getStartDate());
        delta.setTerminationDate(employee.getTerminationDate());
        delta.setEmployeeEmail(employee.getEmployeeEmail());
        return delta;
    }

    private ProjectEmployeeDto createDelta(ProjectEmployeeDto projEmployee)
            throws EmployeeApiException {
        ProjectEmployeeDto delta = this.getProject(projEmployee.getEmpProjId());
        if (delta == null) {
            this.msg = "Employee API update failed for project/employee.  The project/employee does not exist by employee project id: "
                    + projEmployee.getEmpProjId();
            throw new InvalidProjectEmployeeException(this.msg);
        }
        // Update project/employee object obtained from data store with changes.
        delta.setProjEmpEffectiveDate(projEmployee.getProjEmpEffectiveDate());
        delta.setProjEmpEndDate(projEmployee.getProjEmpEndDate());
        delta.setHourlyRate(projEmployee.getHourlyRate());
        delta.setHourlyOverRate(projEmployee.getHourlyOverRate());
        delta.setFlatRate(projEmployee.getFlatRate());
        delta.setComments(projEmployee.getComments());
        return delta;
    }
}
