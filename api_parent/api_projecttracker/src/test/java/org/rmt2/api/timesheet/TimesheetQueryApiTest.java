package org.rmt2.api.timesheet;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.mapping.orm.rmt2.VwTimesheetEventList;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
import org.dao.timesheet.TimesheetConst;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetDto;
import org.dto.TimesheetHistDto;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.dto.adapter.orm.TimesheetObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.admin.ProjectAdminApi;
import org.modules.admin.ProjectAdminApiException;
import org.modules.admin.ProjectAdminApiFactory;
import org.modules.timesheet.TimesheetApi;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2Date;

/**
 * Test the query functionality of the Timesheet module of the Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TimesheetQueryApiTest extends TimesheetMockData {
    
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
    public void testSuccess_Fetch_Single_Timesheet() {
        ProjTimesheet mockCriteria = new ProjTimesheet();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        TimesheetDto results = null;
        try {
            results = api.get(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        Assert.assertEquals(1110, results.getClientId());
        Assert.assertEquals(1234, results.getProjId());
        Assert.assertEquals(2220, results.getEmpId());
        Assert.assertEquals("INVREF1230", results.getInvoiceRefNo());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getBeginPeriod());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), results.getEndPeriod());
        Assert.assertEquals("ExtReNo1000", results.getExtRef());
        Assert.assertEquals("Comments" + results.getTimesheetId(), results.getComments());
        Assert.assertEquals("0000000111", results.getDisplayValue());
        Assert.assertEquals(results.getTimesheetId(), results.getDocumentId());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getDateCreated());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), results.getDateUpdated());
        Assert.assertEquals("testuser", results.getUpdateUserId());
        Assert.assertEquals("1.2.3.4", results.getIpCreated());
        Assert.assertEquals("1.2.3.4", results.getIpUpdated());
    }
    
    @Test
    public void testSuccess_Fetch_Multiple_Timesheet() {
        ProjTimesheet mockCriteria = new ProjTimesheet();
        mockCriteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTimesheetMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple timesheet case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        TimesheetDto criteria = TimesheetObjectFactory.createTimesheetDtoInstance(null);
        List<TimesheetDto> results = null;
        try {
            criteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
            results = api.get(criteria);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TimesheetDto item = results.get(ndx);
            Assert.assertEquals((ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID + ndx), item.getTimesheetId());
            Assert.assertEquals(1110, item.getClientId());
            Assert.assertEquals(1234, item.getProjId());
            Assert.assertEquals(2220, item.getEmpId());
            Assert.assertEquals(("INVREF123" + ndx), item.getInvoiceRefNo());
            Assert.assertEquals(("ExtReNo100" + ndx), item.getExtRef());
            Assert.assertEquals(("Comments" + item.getTimesheetId()), item.getComments());
            Assert.assertEquals(("000000011" + (ndx + 1)), item.getDisplayValue());
            Assert.assertEquals(item.getTimesheetId(), item.getDocumentId());
            
            switch (ndx) {
                case 0:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getDateUpdated());
                    break;
                case 1:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-08"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-14"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-08"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-14"), item.getDateUpdated());
                    break;
                    
                case 2:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-15"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-21"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-15"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-21"), item.getDateUpdated());
                    break;
                case 3:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-22"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-28"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-22"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-28"), item.getDateUpdated());
                    break;
                case 4:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-29"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-02-04"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-29"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-02-04"), item.getDateUpdated());
                    break;
            }
            Assert.assertEquals("testuser", item.getUpdateUserId());
            Assert.assertEquals("1.2.3.4", item.getIpCreated());
            Assert.assertEquals("1.2.3.4", item.getIpUpdated());
        }
    }
    
    
    @Test
    public void testSuccess_Fetch_Single_Extended_Timesheet() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single extended timesheet case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        TimesheetDto results = null;
        try {
            results = api.getExt(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        Assert.assertEquals(1110, results.getClientId());
        Assert.assertEquals(1234, results.getProjId());
        Assert.assertEquals(2220, results.getEmpId());
        Assert.assertEquals("INVREF1230", results.getInvoiceRefNo());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getBeginPeriod());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), results.getEndPeriod());
        Assert.assertEquals("ExtReNo1000", results.getExtRef());
        Assert.assertEquals("Comments" + results.getTimesheetId(), results.getComments());
        Assert.assertEquals("0000000111", results.getDisplayValue());
        Assert.assertEquals(results.getTimesheetId(), results.getDocumentId());
        Assert.assertNull(results.getDateCreated());
        Assert.assertNull(results.getDateUpdated());
        Assert.assertNull(results.getUpdateUserId());
        Assert.assertNull(results.getIpCreated());
        Assert.assertNull(results.getIpUpdated());
        Assert.assertEquals(3330, results.getEmployeeManagerId());
        Assert.assertEquals("DRAFT", results.getStatusName());
        Assert.assertEquals("ACCT-111", results.getClientAccountNo());
        Assert.assertEquals(40, results.getBillHrs(), 0);
        Assert.assertEquals(0, results.getNonBillHrs(), 0);
        Assert.assertEquals(70.00, results.getEmployeeHourlyRate(), 0);
        Assert.assertEquals(80.00, results.getEmployeeHourlyOverRate(), 0);
        Assert.assertEquals(TimesheetConst.STATUS_DRAFT, results.getStatusId());
        Assert.assertEquals(results.getStatusName() + "Description", results.getStatusDescription());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getStatusEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), results.getStatusEndDate());
        Assert.assertEquals(222, results.getEmployeeTypeId());
        Assert.assertEquals(5555, results.getStatusHistId());
        Assert.assertEquals(results.getEmployeeLastname() + ", " + results.getEmployeeFirstname(), results.getEmployeeFullName());
    }
    
    
    @Test
    public void testSuccess_Fetch_Multiple_Extended_Timesheet() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        TimesheetDto criteria = TimesheetObjectFactory.createTimesheetDtoInstance(null);
        List<TimesheetDto> results = null;
        try {
            criteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
            results = api.getExt(criteria);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TimesheetDto item = results.get(ndx);
            Assert.assertEquals((ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID + ndx), item.getTimesheetId());
            Assert.assertEquals(1110, item.getClientId());
            Assert.assertEquals(1234, item.getProjId());
            Assert.assertEquals(2220, item.getEmpId());
            Assert.assertEquals(("INVREF123" + ndx), item.getInvoiceRefNo());
            Assert.assertEquals(("ExtReNo100" + ndx), item.getExtRef());
            Assert.assertEquals(("Comments" + item.getTimesheetId()), item.getComments());
            Assert.assertEquals(("000000011" + (ndx + 1)), item.getDisplayValue());
            Assert.assertEquals(item.getTimesheetId(), item.getDocumentId());
            
            switch (ndx) {
                case 0:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getStatusEndDate());
                    break;
                case 1:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-08"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-14"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-08"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-14"), item.getStatusEndDate());
                    break;
                    
                case 2:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-15"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-21"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-15"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-21"), item.getStatusEndDate());
                    break;
                case 3:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-22"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-28"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-22"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-28"), item.getStatusEndDate());
                    break;
                case 4:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-29"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-02-04"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-29"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-02-04"), item.getStatusEndDate());
                    break;
            }
            // Test extended data
            Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_MANAGER_ID, item.getEmployeeManagerId());
            Assert.assertEquals("DRAFT", item.getStatusName());
            Assert.assertEquals("ACCT-111", item.getClientAccountNo());
            Assert.assertEquals(40, item.getBillHrs(), 0);
            Assert.assertEquals(0, item.getNonBillHrs(), 0);
            Assert.assertEquals(70.00, item.getEmployeeHourlyRate(), 0);
            Assert.assertEquals(80.00, item.getEmployeeHourlyOverRate(), 0);
            Assert.assertEquals(TimesheetConst.STATUS_DRAFT, item.getStatusId());
            Assert.assertEquals(item.getStatusName() + "Description", item.getStatusDescription());
            
            Assert.assertEquals(222, item.getEmployeeTypeId());
            Assert.assertEquals(5555, item.getStatusHistId());
            Assert.assertEquals(item.getEmployeeLastname() + ", " + item.getEmployeeFirstname(), item.getEmployeeFullName());
        }
    }
    
    @Test
    public void testSuccess_Fetch_Client_Approved() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setClientId(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        mockCriteria.setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
        try {
            // Changed mock data to represent an approved timesheet
            this.mockVwTimesheetSingle.get(0).setStatusName("APPROVED");
            this.mockVwTimesheetSingle.get(0).setStatusDescription("APPROVEDDescription");
            this.mockVwTimesheetSingle.get(0).setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single extended timesheet case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<TimesheetDto> results = null;
        try {
            results = api.getClientApproved(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TimesheetDto item = results.get(ndx);
            Assert.assertEquals((ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID + ndx), item.getTimesheetId());
            Assert.assertEquals(1110, item.getClientId());
            Assert.assertEquals(1234, item.getProjId());
            Assert.assertEquals(2220, item.getEmpId());
            Assert.assertEquals(("INVREF123" + ndx), item.getInvoiceRefNo());
            Assert.assertEquals(("ExtReNo100" + ndx), item.getExtRef());
            Assert.assertEquals(("Comments" + item.getTimesheetId()), item.getComments());
            Assert.assertEquals(("000000011" + (ndx + 1)), item.getDisplayValue());
            Assert.assertEquals(item.getTimesheetId(), item.getDocumentId());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getBeginPeriod());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getEndPeriod());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getStatusEffectiveDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getStatusEndDate());
            // Test extended data
            Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_MANAGER_ID, item.getEmployeeManagerId());
            Assert.assertEquals("APPROVED", item.getStatusName());
            Assert.assertEquals("ACCT-111", item.getClientAccountNo());
            Assert.assertEquals(40, item.getBillHrs(), 0);
            Assert.assertEquals(0, item.getNonBillHrs(), 0);
            Assert.assertEquals(70.00, item.getEmployeeHourlyRate(), 0);
            Assert.assertEquals(80.00, item.getEmployeeHourlyOverRate(), 0);
            Assert.assertEquals(TimesheetConst.STATUS_APPROVED, item.getStatusId());
            Assert.assertEquals(item.getStatusName() + "Description", item.getStatusDescription());
            
            Assert.assertEquals(222, item.getEmployeeTypeId());
            Assert.assertEquals(5555, item.getStatusHistId());
            Assert.assertEquals(item.getEmployeeLastname() + ", " + item.getEmployeeFirstname(), item.getEmployeeFullName());
        }
    }
    
    @Test
    public void testSuccess_Fetch_Simple_ProjectTask_By_TimesheetId() {
        ProjProjectTask mockCriteria = new ProjProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjProjectTaskMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project-task case setup failed");
        }

        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        
        List<ProjectTaskDto> results = null;
        try {
            results = api.getProjectTaskByTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectTaskDto obj = results.get(ndx);
            Assert.assertEquals(obj.getProjectTaskId(), (ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + ndx));
            Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
            Assert.assertEquals(obj.getTaskId(), (ProjectTrackerMockDataFactory.TEST_TASK_ID + ndx));
        }
    }
 
    @Test
    public void testSuccess_Fetch_Extended_ProjectTask_By_TimesheetId() {
        // Stub all project-task fetch.
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project-task case setup failed");
        }

        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<ProjectTaskDto> results = null;
        try {
            results = api.getProjectTaskExtByTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectTaskDto obj = results.get(ndx);
            Assert.assertEquals(obj.getProjectTaskId(), (ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + ndx));
            Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
            Assert.assertEquals(obj.getTaskId(), (ProjectTrackerMockDataFactory.TEST_TASK_ID + ndx));
            Assert.assertEquals(obj.getClientId(), ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
            Assert.assertEquals(obj.getProjectDescription(), ProjectTrackerMockDataFactory.TEST_PROJECT_NAME);
            Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-01-01"));
            Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-01-07"));
            Assert.assertEquals(obj.getTaskDescription(), ProjectTrackerMockDataFactory.TEST_TASK_NAMES[ndx]);
            Assert.assertEquals(obj.getTaskBillable(), (ndx <= 3 ? 1 : 0));
        }
    }
  
    
    @Test
    public void testSuccess_Fetch_ProjectEvent_All() {
        // Stub all  project-event fetch.
        VwTimesheetEventList mockCriteria = new VwTimesheetEventList();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetEventListFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project-event case setup failed");
        }

        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectEventDto criteria = ProjectObjectFactory.createProjectEventDtoInstance(null);
        List<ProjectEventDto> results = null;
        try {
            results = api.getProjectEvent(criteria);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectEventDto obj = results.get(ndx);
            Assert.assertEquals(obj.getProjectTaskId(), (ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + ndx));
            Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
            Assert.assertEquals(obj.getTaskId(), (ProjectTrackerMockDataFactory.TEST_TASK_ID + ndx));
            Assert.assertEquals(obj.getClientId(), ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
            Assert.assertEquals(obj.getProjectDescription(), ProjectTrackerMockDataFactory.TEST_PROJECT_NAME);
            Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-01-01"));
            Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-01-07"));
            Assert.assertEquals(obj.getTaskDescription(), ProjectTrackerMockDataFactory.TEST_TASK_NAMES[ndx]);
            Assert.assertEquals(obj.getTaskBillable(), (ndx <= 3 ? 1 : 0));
        }
    }
    
    @Test
    public void testSuccess_Fetch_ProjectEvent_Single() {
        // Stub single project-event fetch.
        VwTimesheetEventList mockCriteria = new VwTimesheetEventList();
        mockCriteria.setEventId(ProjectTrackerMockDataFactory.TEST_EVENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetEventListFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single project-event case setup failed");
        }

        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectEventDto criteria = ProjectObjectFactory.createProjectEventDtoInstance(null);
        criteria.setEventId(ProjectTrackerMockDataFactory.TEST_EVENT_ID);
        List<ProjectEventDto> results = null;
        try {
            results = api.getProjectEvent(criteria);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ProjectEventDto obj = results.get(0);
        Assert.assertEquals(obj.getProjectTaskId(), ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
        Assert.assertEquals(obj.getTaskId(), ProjectTrackerMockDataFactory.TEST_TASK_ID);
        Assert.assertEquals(obj.getClientId(), ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        Assert.assertEquals(obj.getProjectDescription(), ProjectTrackerMockDataFactory.TEST_PROJECT_NAME);
        Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-01-01"));
        Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-01-07"));
        Assert.assertEquals(obj.getTaskDescription(), ProjectTrackerMockDataFactory.TEST_TASK_NAMES[0]);
        Assert.assertEquals(obj.getTaskBillable(), 1);
    }
    
    @Test
    public void testSuccess_Fetch_ProjectEvent_By_TimesheetId() {
        VwTimesheetEventList mockCriteria = new VwTimesheetEventList();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                      .thenReturn(this.mockVwTimesheetEventListFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet events case setup failed");
        }

        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<ProjectEventDto> results = null;
        try {
            results = api.getEventByTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectEventDto obj = results.get(ndx);
            Assert.assertEquals(obj.getProjectTaskId(), (ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + ndx));
            Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
            Assert.assertEquals(obj.getTaskId(), (ProjectTrackerMockDataFactory.TEST_TASK_ID + ndx));
            Assert.assertEquals(obj.getClientId(), ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
            Assert.assertEquals(obj.getProjectDescription(), ProjectTrackerMockDataFactory.TEST_PROJECT_NAME);
            Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-01-01"));
            Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-01-07"));
            Assert.assertEquals(obj.getTaskDescription(), ProjectTrackerMockDataFactory.TEST_TASK_NAMES[ndx]);
            Assert.assertEquals(obj.getTaskBillable(), (ndx <= 3 ? 1 : 0));
        }
    }
    
    @Test
    public void testSuccess_Fetch_Current_Status() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }

        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        TimesheetHistDto results = null;
        try {
            results = api.getCurrentStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID, results.getStatusHistId());
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, results.getTimesheetId());
        Assert.assertEquals(TimesheetConst.STATUS_APPROVED, results.getStatusId());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-09"), results.getStatusEffectiveDate());
        Assert.assertNull(results.getStatusEndDate());
        Assert.assertEquals("testuser", results.getUpdateUserId());
        Assert.assertEquals("1.2.3.4", results.getIpCreated());
        Assert.assertEquals("1.2.3.4", results.getIpUpdated());
    }
 }