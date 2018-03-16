package org.rmt2.api.admin;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EmployeeTitleDto;
import org.dto.EmployeeTypeDto;
import org.dto.ProjectEmployeeDto;
import org.dto.adapter.orm.EmployeeObjectFactory;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.ProjectTrackerApiConst;
import org.modules.employee.EmployeeApi;
import org.modules.employee.EmployeeApiException;
import org.modules.employee.EmployeeApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectAdminApiTestData;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2Date;

/**
 * Tests the Administration module of the Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class EmployeeQueryApiTest extends ProjectAdminApiTestData {
    
    private static final int TEST_CLIENT_ID = 1000;
    private static final int TEST_LOGIN_ID = 999991;
    private static final int TEST_EMPLOYEE_TITLE_ID = 101;
    private static final int TEST_MANAGER_ID = 3333;
    private static final int TEST_EMPLOYEE_ID = 5000;
    private static final int TEST_BUSINESS_ID = 1350;
    private static final int TEST_PROJ_ID = 2220;
    private static final int TEST_EMP_PROJ_ID = 55551;
    private static final String TEST_COMPANY_NAME = "ABC Company";
    private static final String TEST_TASK_NAMES[] = new String[]{"Design and Analysis", 
            "Development", "Meetings", "Testing", "Holiday"};

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }
    
    @Test
    public void testSuccess_Fetch_All_Clients() {
        // Stub all clients fetch.
        VwEmployeeProjects mockCriteria = new VwEmployeeProjects();
        mockCriteria.setEmpId(TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwEmployeeProjectsFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee projects case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto criteria = ProjectObjectFactory.createEmployeeProjectDtoInstance(null);
        criteria.setEmpId(TEST_EMPLOYEE_ID);
        List<ClientDto> results = null;
        try {
            results = api.getClients(TEST_EMPLOYEE_ID);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ClientDto obj = results.get(ndx);
            Assert.assertEquals(obj.getClientId(), (TEST_CLIENT_ID + ndx));
            Assert.assertEquals(obj.getClientName(), (TEST_CLIENT_ID + ndx) + " Company");
        }
    }

    @Test
    public void testSuccess_Fetch_Employee_List_Using_Criteria() {
        // Stub all employee fetch.
        ProjEmployee mockCriteria = new ProjEmployee();
        mockCriteria.setManagerId(TEST_MANAGER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockEmployeeFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto criteria = EmployeeObjectFactory.createEmployeeDtoInstance(null);
        criteria.setManagerId(TEST_MANAGER_ID);
        List<EmployeeDto> results = null;
        try {
            results = api.getEmployee(criteria);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            EmployeeDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEmployeeId(), (TEST_EMPLOYEE_ID + ndx));
            Assert.assertEquals(obj.getManagerId(), TEST_MANAGER_ID);
            Assert.assertEquals(obj.getEmployeeTitleId(), (TEST_EMPLOYEE_TITLE_ID + ndx));
            Assert.assertEquals(obj.getLoginId(), (TEST_LOGIN_ID + ndx));
            int startYear = 2010 + ndx;
            Assert.assertEquals(RMT2Date.stringToDate(startYear + "-01-01"), obj.getStartDate());
            if (obj.getTerminationDate() != null) {
                Assert.assertEquals(obj.getEmployeeTypeId(), 202);
                Assert.assertEquals(obj.getIsManager(), 1);
                Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), obj.getTerminationDate());
            }
            else {
                Assert.assertEquals(obj.getEmployeeTypeId(), 201);
                Assert.assertEquals(obj.getIsManager(), 0);
            }
            int nameSeed = 1 + ndx;
            Assert.assertEquals(obj.getEmployeeFirstname(), "first_name_" + nameSeed);
            Assert.assertEquals(obj.getEmployeeLastname(), "last_name_" + nameSeed);
            Assert.assertEquals(("111-11-500" + ndx), obj.getSsn());
            Assert.assertEquals(TEST_COMPANY_NAME, obj.getEmployeeCompanyName());
        }
    }
    
    @Test
    public void testSuccess_Fetch_Single_Employee() {
        // Stub all employee fetch.
        ProjEmployee mockCriteria = new ProjEmployee();
        mockCriteria.setEmpId(TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockEmployeeFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto criteria = EmployeeObjectFactory.createEmployeeDtoInstance(null);
        criteria.setEmployeeId(TEST_EMPLOYEE_ID);
        EmployeeDto results = null;
        try {
            results = api.getEmployee(TEST_EMPLOYEE_ID);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getEmployeeId(), 5000);
        Assert.assertEquals(results.getManagerId(), 3333);
        Assert.assertEquals(results.getEmployeeTitleId(), 101);
        Assert.assertEquals(results.getLoginId(), 999991);
        Assert.assertEquals(RMT2Date.stringToDate("2010-01-01"), results.getStartDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getTerminationDate());
        Assert.assertEquals(results.getEmployeeTypeId(), 201);
        Assert.assertEquals(results.getIsManager(), 1);
        Assert.assertEquals(results.getEmployeeFirstname(), "first_name_1");
        Assert.assertEquals(results.getEmployeeLastname(), "last_name_1");
        Assert.assertEquals("111-11-5000", results.getSsn());
        Assert.assertEquals("ABC Company", results.getEmployeeCompanyName());
    }
    
    @Test
    public void testSuccess_Fetch_Employee_Titles() {
        ProjEmployeeTitle mockCriteria = new ProjEmployeeTitle();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockEmployeeTitleFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee titles case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        List<EmployeeTitleDto> results = null;
        try {
            results = api.getEmployeeTitles();
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            EmployeeTitleDto dto = results.get(ndx);
            Assert.assertEquals((101 + ndx), dto.getEmployeeTitleId());
            Assert.assertEquals(("Employee Title " + (1 + ndx)), dto.getEmployeeTitleDescription());
        }
    }
    
    @Test
    public void testSuccess_Fetch_Employee_Types() {
        ProjEmployeeType mockCriteria = new ProjEmployeeType();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockEmployeeTypeFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee types case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        List<EmployeeTypeDto> results = null;
        try {
            results = api.getEmployeeTypes();
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            EmployeeTypeDto dto = results.get(ndx);
            Assert.assertEquals((201 + ndx), dto.getEmployeeTypeId());
            Assert.assertEquals(("Employee Type " + (1 + ndx)), dto.getEmployeeTypeDescription());
        }
    }
    
    @Test
    public void testSuccess_Fetch_Managers() {
        // Set all mock employe data objects to be managers
        for (ProjEmployee emp : this.mockEmployeeFetchMultiple) {
            emp.setIsManager(ProjectTrackerApiConst.EMPLOYEE_MANAGER_FLAG);
        }
        // Stub all employee fetch.
        ProjEmployee mockCriteria = new ProjEmployee();
        mockCriteria.setIsManager(ProjectTrackerApiConst.EMPLOYEE_MANAGER_FLAG);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockEmployeeFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto criteria = EmployeeObjectFactory.createEmployeeDtoInstance(null);
        criteria.setManagerId(TEST_MANAGER_ID);
        List<EmployeeDto> results = null;
        try {
            results = api.getManagers();
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            EmployeeDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEmployeeId(), (TEST_EMPLOYEE_ID + ndx));
            Assert.assertEquals(obj.getManagerId(), TEST_MANAGER_ID);
            Assert.assertEquals(obj.getEmployeeTitleId(), (TEST_EMPLOYEE_TITLE_ID + ndx));
            Assert.assertEquals(obj.getLoginId(), (TEST_LOGIN_ID + ndx));
            Assert.assertEquals(obj.getIsManager(), 1);
            int startYear = 2010 + ndx;
            Assert.assertEquals(RMT2Date.stringToDate(startYear + "-01-01"), obj.getStartDate());
            if (obj.getTerminationDate() != null) {
                Assert.assertEquals(obj.getEmployeeTypeId(), 202);
                Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), obj.getTerminationDate());
            }
            else {
                Assert.assertEquals(obj.getEmployeeTypeId(), 201);
            }
            int nameSeed = 1 + ndx;
            Assert.assertEquals(obj.getEmployeeFirstname(), "first_name_" + nameSeed);
            Assert.assertEquals(obj.getEmployeeLastname(), "last_name_" + nameSeed);
            Assert.assertEquals(("111-11-500" + ndx), obj.getSsn());
            Assert.assertEquals(TEST_COMPANY_NAME, obj.getEmployeeCompanyName());
        }
    }
    
    @Test
    public void testSuccess_Fetch_Project_Employee_List() {
        VwEmployeeProjects mockCriteria = new VwEmployeeProjects();
        mockCriteria.setBusinessId(TEST_BUSINESS_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwEmployeeProjectsFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee projects case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto criteria = ProjectObjectFactory.createEmployeeProjectDtoInstance(null);
        criteria.setBusinessId(TEST_BUSINESS_ID);
        List<ProjectEmployeeDto> results = null;
        try {
            results = api.getProjectEmployee(criteria);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectEmployeeDto obj = results.get(ndx);
            Assert.assertEquals(obj.getClientId(), (TEST_CLIENT_ID + ndx));
            Assert.assertEquals(obj.getClientName(), (TEST_CLIENT_ID + ndx) + " Company");
            Assert.assertEquals(obj.getProjId(), (TEST_PROJ_ID + ndx));
            Assert.assertEquals(TEST_EMP_PROJ_ID + ndx, obj.getEmpProjId());
            Assert.assertEquals(TEST_EMPLOYEE_ID, obj.getEmpId());
            Assert.assertEquals(TEST_BUSINESS_ID, obj.getBusinessId());
            Assert.assertEquals(obj.getProjectDescription(), "Project 222" + ndx);
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), obj.getProjectEffectiveDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-02-01"), obj.getProjectEndDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), obj.getProjEmpEffectiveDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-02-01"), obj.getProjEmpEndDate());
        }
    }
    
    @Test
    public void testSuccess_Fetch_Project_Employee_Single() {
        VwEmployeeProjects mockCriteria = new VwEmployeeProjects();
        mockCriteria.setEmpProjId(TEST_EMP_PROJ_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto criteria = ProjectObjectFactory.createEmployeeProjectDtoInstance(null);
        criteria.setEmpProjId(TEST_EMP_PROJ_ID);
        ProjectEmployeeDto results = null;
        try {
            results = api.getProjectEmployee(TEST_EMP_PROJ_ID);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getClientId(), TEST_CLIENT_ID);
        Assert.assertEquals(results.getClientName(), TEST_CLIENT_ID + " Company");
        Assert.assertEquals(results.getProjId(), TEST_PROJ_ID);
        Assert.assertEquals(TEST_EMP_PROJ_ID, results.getEmpProjId());
        Assert.assertEquals(TEST_EMPLOYEE_ID, results.getEmpId());
        Assert.assertEquals(TEST_BUSINESS_ID, results.getBusinessId());
        Assert.assertEquals(results.getProjectDescription(), "Project 2220");
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"),
                results.getProjectEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-02-01"),
                results.getProjectEndDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"),
                results.getProjEmpEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-02-01"), results.getProjEmpEndDate());
    }
   }