package org.modules.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dao.ProjecttrackerDaoException;
import org.dao.admin.EmployeeDaoException;
import org.dao.admin.ProjectAdminDao;
import org.dao.admin.ProjectAdminDaoFactory;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EmployeeTitleDto;
import org.dto.EmployeeTypeDto;
import org.dto.ProjectEmployeeDto;
import org.dto.adapter.orm.EmployeeObjectFactory;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.modules.ProjectTrackerApiConst;

import com.InvalidDataException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * Manages the creation, modifification, and querying of employee information.
 * 
 * @author Roy Terrell
 * 
 */
class EmployeeApiImpl extends AbstractTransactionApiImpl implements EmployeeApi {

    private static final Logger logger = Logger.getLogger(EmployeeApiImpl.class);

    private ProjectAdminDaoFactory daoFact;

    private ProjectAdminDao dao;

    /**
     * Creates a EmployeeApiImpl object in which the configuration is identified
     * by the name of a given application.
     * 
     * @param appName
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
        super(ProjectTrackerApiConst.DEFAULT_CONTEXT_NAME, dao);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
    }

    /**
     * Creates an EmployeeApiImpl initialized with the name of the target
     * application and a shared DAO connection.
     * 
     * @param appName
     * @param dao
     */
    protected EmployeeApiImpl(String appName, DaoClient dao) {
        super(appName, dao);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
        logger.info("ProjectAdminApiImpl created with outside Api DAO, " + this.dao.getClass().getSimpleName()
                + " identified by application name, " + appName);
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
    public EmployeeDto getEmployee(Integer empId) throws EmployeeApiException {
        this.validateEmployeeId(empId);

        EmployeeDto criteria = EmployeeObjectFactory.createEmployeeDtoInstance(null);
        criteria.setEmployeeId(empId);
        List<EmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = this.getEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (EmployeeApiException e) {
            buf.append("Database error occurred retrieving all employees");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new EmployeeApiException(this.msg, e);
        }

        if (results.size() > 1) {
            buf.append("Employee query is expected to return a single employee object using employee id, ");
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
     * @see org.modules.employee.EmployeeApi#getEmployeeByTitle(int)
     */
    @Override
    public List<EmployeeDto> getEmployee(EmployeeDto criteria) throws EmployeeApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidEmployeeException("Employee criteria is required");
        }
        List<EmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (EmployeeDaoException e) {
            buf.append("Database error occurred retrieving employees using selection criteria");
            this.msg = buf.toString();
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
    public List<EmployeeDto> getEmployeeExt(EmployeeDto criteria) throws EmployeeApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidEmployeeException("Employee criteria is required");
        }
        List<EmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchEmployeeExt(criteria);
            if (results == null) {
                return null;
            }
        } catch (EmployeeDaoException e) {
            buf.append("Database error occurred retrieving employees using selection criteria");
            this.msg = buf.toString();
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
        EmployeeDto criteria = EmployeeObjectFactory.createEmployeeDtoInstance(null);
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
    public List<EmployeeTitleDto> getEmployeeTitles() throws EmployeeApiException {
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
    public List<ClientDto> getClients(Integer empId) throws EmployeeApiException {
        this.validateEmployeeId(empId);

        VwEmployeeProjects vep = new VwEmployeeProjects();
        ProjectEmployeeDto criteria = ProjectObjectFactory.createEmployeeProjectDtoInstance(vep);
        criteria.setEmpId(empId);

        List<ProjectEmployeeDto> peList = this.getProjectEmployee(criteria);
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
                    ClientDto client = ProjectObjectFactory.createClientDtoInstance(null);
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
    public ProjectEmployeeDto getProjectEmployee(Integer empProjId) throws EmployeeApiException {
        try {
            Verifier.verifyNotNull(empProjId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Empoyee-Project Id is required");
        }
        try {
            Verifier.verifyPositive(empProjId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Empoyee-Project Id must greater than zero");
        }
        VwEmployeeProjects vep = new VwEmployeeProjects();
        ProjectEmployeeDto criteria = ProjectObjectFactory.createEmployeeProjectDtoInstance(vep);
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
     * @see org.modules.employee.EmployeeApi#getEmployeeProjectByEmployee(int)
     */
    @Override
    public List<ProjectEmployeeDto> getProjectEmployee(ProjectEmployeeDto criteria) throws EmployeeApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch(VerifyException e) {
            throw new InvalidDataException("Project/Employee selection criteria object is required");
        }
        List<ProjectEmployeeDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchProjectEmployee(criteria);
            if (results == null) {
                return null;
            }
        } catch (EmployeeDaoException e) {
            buf.append("Database error occurred retrieving project/employee objects by employee id, ");
            buf.append(criteria);
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
        try {
            try {
                Verifier.verifyPositive(employee.getEmployeeId());
                // EmployeeDto delta = this.applyDelta(employee);
                rc = this.dao.maintainEmployee(employee);
            }
            catch (VerifyException e) {
                rc = this.dao.maintainEmployee(employee);
            }
            return rc;
        }
        catch (EmployeeDaoException e) {
            throw new EmployeeApiException("A DAO error occurred updating/inserting employee record", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.employee.EmployeeApi#update(org.dto.ProjectEmployeeDto)
     */
    @Override
    public int update(ProjectEmployeeDto projEmployee) throws EmployeeApiException {
        this.validate(projEmployee);
        int rc = 0;
        try {
            try {
                Verifier.verifyPositive(projEmployee.getEmpProjId());
                ProjectEmployeeDto delta = this.applyDelta(projEmployee);
                rc = this.dao.maintainProjectEmployee(delta);
            }
            catch (VerifyException e) {
                rc = this.dao.maintainProjectEmployee(projEmployee);
            }
            return rc;
        }
        catch (EmployeeDaoException e) {
            throw new EmployeeApiException("A DAO error occurred updating/inserting project employee record", e);
        }
    }

    private void validateEmployeeId(Integer empId) {
        try {
            Verifier.verifyNotNull(empId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Empoyee Id is required");
        }
        try {
            Verifier.verifyPositive(empId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Empoyee Id must greater than zero");
        }
    }

    /**
     * Verifies that the employee contains a title and a type.
     * 
     * @param emp
     * @throws InvalidEmployeeException
     *             If <i>emp</i> does not contain an employee type or an
     *             employee title.
     */
    protected void validate(EmployeeDto emp) {
        try {
            Verifier.verifyNotNull(emp);
        }
        catch (VerifyException e) {
            throw new InvalidEmployeeException("Employee criteria object is required");
        }
        try {
            Verifier.verifyPositive(emp.getEmployeeTitleId());
        }
        catch (VerifyException e) {
            this.msg = "Employee criteria object must contain an employee title";
            throw new InvalidEmployeeException(this.msg);
        }
        try {
            Verifier.verifyPositive(emp.getEmployeeTypeId());
        }
        catch (VerifyException e) {
            this.msg = "Employee criteria object must contain an employee type";
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
     *             If <i>projEmployee</i> is null, employee id and/or project id
     *             is not greater than zero, or the employee id/project id
     *             combination already exists in the system.
     */
    protected void validate(ProjectEmployeeDto projEmployee) {
        try {
            Verifier.verifyNotNull(projEmployee);
        }
        catch (VerifyException e) {
            this.msg = "Project/Employee cannot be null";
            throw new InvalidProjectEmployeeException(this.msg);
        }
        
        try {
            Verifier.verifyPositive(projEmployee.getEmpId());
        }
        catch (VerifyException e) {
            this.msg = "Employee ID is required";
            throw new InvalidProjectEmployeeException(this.msg);
        }
        
        try {
            Verifier.verifyPositive(projEmployee.getProjId());
        }
        catch (VerifyException e) {
            this.msg = "Project ID is required";
            throw new InvalidProjectEmployeeException(this.msg);
        }
        
        // If new, make sure that we are not trying to duplicate the employee/project combination
        if (projEmployee.getEmpProjId() == 0) {
            try {
                VwEmployeeProjects vep = new VwEmployeeProjects();
                ProjectEmployeeDto criteria = ProjectObjectFactory.createEmployeeProjectDtoInstance(vep);
                criteria.setEmpId(projEmployee.getEmpId());
                criteria.setProjId(projEmployee.getProjId());
                List<ProjectEmployeeDto> results = this.getProjectEmployee(criteria);
                if (results != null) {
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
        }
        return;
    }

    private ProjectEmployeeDto applyDelta(ProjectEmployeeDto projEmployee) throws EmployeeApiException {
        ProjectEmployeeDto delta = this.getProjectEmployee(projEmployee.getEmpProjId());
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
