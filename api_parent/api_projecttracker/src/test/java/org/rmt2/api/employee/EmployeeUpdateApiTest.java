package org.rmt2.api.employee;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeProject;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dto.EmployeeDto;
import org.dto.ProjectEmployeeDto;
import org.dto.adapter.orm.EmployeeObjectFactory;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.employee.EmployeeApi;
import org.modules.employee.EmployeeApiException;
import org.modules.employee.EmployeeApiFactory;
import org.modules.employee.InvalidEmployeeException;
import org.modules.employee.InvalidProjectEmployeeException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectTrackerMockData;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Test the update functionality of the Employee module of the Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class EmployeeUpdateApiTest extends ProjectTrackerMockData {
    
    private static final int TEST_EMPLOYEE_ID = 5000;
    private static final int TEST_PROJ_ID = 2220;
    private static final int TEST_EMP_PROJ_ID = 55551;
    
    private static final int TEST_CLIENT_ID = 1000;
    private static final int TEST_LOGIN_ID = 999991;
    private static final int TEST_EMPLOYEE_TITLE_ID = 101;
    private static final int TEST_MANAGER_ID = 3333;
    private static final int TEST_BUSINESS_ID = 1350;
    
    private static final String TEST_COMPANY_NAME = "ABC Company";

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
    public void testSuccess_Create_Employee() {
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjEmployee.class), eq(true))).thenReturn(TEST_EMPLOYEE_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert employee case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto emp = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockEmployeeFetchSingle.get(0));
        // Make sure employee is new
        emp.setEmployeeId(0);
        int results = 0;
        try {
            results = api.update(emp);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(TEST_EMPLOYEE_ID, results);
        // Verify that the DAO assinged the new employee id to the said employee object
        Assert.assertEquals(TEST_EMPLOYEE_ID, emp.getEmployeeId());
    }
    
    @Test
    public void testError_Create_Employee_DB_Access_Fault() {
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjEmployee.class), eq(true)))
                  .thenThrow(new DatabaseException("A database error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert employee case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto emp = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockEmployeeFetchSingle.get(0));
        // Make sure employee is new
        emp.setEmployeeId(0);
        try {
            api.update(emp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof EmployeeApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Employee_Null_Employee_Object() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto emp = null;
        try {
            api.update(emp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Employee_Zero_EmployeeTitleId() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto emp = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockEmployeeFetchSingle.get(0));
        emp.setEmployeeTitleId(0);
        try {
            api.update(emp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Employee_Negative_EmployeeTitleId() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto emp = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockEmployeeFetchSingle.get(0));
        emp.setEmployeeTitleId(-12);
        try {
            api.update(emp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Employee_Zero_EmployeeTypeId() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto emp = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockEmployeeFetchSingle.get(0));
        emp.setEmployeeTypeId(0);
        try {
            api.update(emp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Employee_Negative_EmployeeTypeId() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto emp = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockEmployeeFetchSingle.get(0));
        emp.setEmployeeTypeId(-1299);
        try {
            api.update(emp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Modify_Employee() {
        // Stub all employee fetch.
        ProjEmployee mockCriteria = new ProjEmployee();
        mockCriteria.setEmpId(TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockEmployeeFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjEmployee.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update employee case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        EmployeeDto emp = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockEmployeeFetchSingle.get(0));
        int results = 0;
        try {
            results = api.update(emp);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testSuccess_Create_Project_Employee() {
        VwEmployeeProjects mockCriteria = new VwEmployeeProjects();
        mockCriteria.setEmpId(TEST_EMPLOYEE_ID);
        mockCriteria.setProjId(TEST_PROJ_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjEmployeeProject.class), eq(true)))
                            .thenReturn(TEST_EMP_PROJ_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert employee project case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto projEmp = ProjectObjectFactory.createEmployeeProjectDtoInstance(this.mockVwEmployeeProjectsFetchSingle.get(0));
        // Make sure employee is new
        projEmp.setEmpProjId(0);
        int results = 0;
        try {
            results = api.update(projEmp);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(TEST_EMP_PROJ_ID, results);
        // Verify that the DAO assinged the new employee id to the said employee object
        Assert.assertEquals(TEST_EMP_PROJ_ID, projEmp.getEmpProjId());
    }

    @Test
    public void testError_Create_Project_Employee_DB_Access_Fault() {
        VwEmployeeProjects mockCriteria = new VwEmployeeProjects();
        mockCriteria.setEmpId(TEST_EMPLOYEE_ID);
        mockCriteria.setProjId(TEST_PROJ_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjEmployeeProject.class), eq(true)))
                   .thenThrow(new DatabaseException("A database error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert employee project case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto projEmp = ProjectObjectFactory.createEmployeeProjectDtoInstance(this.mockVwEmployeeProjectsFetchSingle.get(0));
        // Make sure employee is new
        projEmp.setEmpProjId(0);
        try {
            api.update(projEmp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof EmployeeApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Project_Employee_Null_ProjectEmployee_Object() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto projEmp = null;
        try {
            api.update(projEmp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidProjectEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Project_Employee_Zero_EmployeeId() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto projEmp = ProjectObjectFactory.createEmployeeProjectDtoInstance(this.mockVwEmployeeProjectsFetchSingle.get(0));
        projEmp.setEmpId(0);
        try {
            api.update(projEmp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidProjectEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Project_Employee_Negative_EmployeeId() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto projEmp = ProjectObjectFactory.createEmployeeProjectDtoInstance(this.mockVwEmployeeProjectsFetchSingle.get(0));
        projEmp.setEmpId(-1234);
        try {
            api.update(projEmp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidProjectEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Project_Employee_Zero_ProjectId() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto projEmp = ProjectObjectFactory.createEmployeeProjectDtoInstance(this.mockVwEmployeeProjectsFetchSingle.get(0));
        projEmp.setProjId(0);
        try {
            api.update(projEmp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidProjectEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Create_Project_Employee_Negative_ProjectId() {
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto projEmp = ProjectObjectFactory.createEmployeeProjectDtoInstance(this.mockVwEmployeeProjectsFetchSingle.get(0));
        projEmp.setProjId(-1234);
        try {
            api.update(projEmp);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidProjectEmployeeException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Modify_Project_Employee() {
        VwEmployeeProjects mockCriteria = new VwEmployeeProjects();
        mockCriteria.setEmpProjId(TEST_EMP_PROJ_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjEmployeeProject.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update employee project case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto projEmp = ProjectObjectFactory.createEmployeeProjectDtoInstance(this.mockVwEmployeeProjectsFetchSingle.get(0));
        int results = 0;
        try {
            results = api.update(projEmp);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results);
        // Verify that the DAO assinged the new employee id to the said employee object
        Assert.assertEquals(TEST_EMP_PROJ_ID, projEmp.getEmpProjId());
    }
    
    @Test
    public void testValidation_Create_Project_Employee_ProjEmployee_Combination_Exists() {
        VwEmployeeProjects mockCriteria = new VwEmployeeProjects();
        mockCriteria.setEmpId(TEST_EMPLOYEE_ID);
        mockCriteria.setProjId(TEST_PROJ_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto projEmp = ProjectObjectFactory
                .createEmployeeProjectDtoInstance(this.mockVwEmployeeProjectsFetchSingle.get(0));

        // Make sure employee is new
        projEmp.setEmpProjId(0);
        try {
            api.update(projEmp);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidProjectEmployeeException);
            e.printStackTrace();
        }
    }
 }