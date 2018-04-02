package org.rmt2.api.timesheet;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.Map;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
import org.dao.timesheet.TimesheetConst;
import org.dao.timesheet.TimesheetDaoException;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.TimesheetDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.ProjectTrackerApiConst;
import org.modules.admin.ProjectAdminApiException;
import org.modules.employee.EmployeeApiException;
import org.modules.timesheet.TimesheetApi;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.modules.timesheet.TimesheetTransmissionApi;
import org.modules.timesheet.TimesheetTransmissionException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.InvalidDataException;
import com.api.messaging.email.EmailMessageBean;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Test the update functionality of the Timesheet module of the Project Tracker
 * Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class, TimesheetApiFactory.class })
public class TimesheetUpdateApiTest extends TimesheetMockData {

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
    public void testSuccess_Change_Status_Existing() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to DRAFT.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.changeTimesheetStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, TimesheetConst.STATUS_SUBMITTED);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        
        Assert.assertEquals(TimesheetConst.STATUS_DRAFT, results);
    }
    
    @Test
    public void testSuccess_Change_Status_New() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.changeTimesheetStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, TimesheetConst.STATUS_DRAFT);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        
        Assert.assertEquals(TimesheetConst.STATUS_NEW, results);
    }

    @Test
    public void testError_Change_Status_DB_Error_Fault() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.changeTimesheetStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, TimesheetConst.STATUS_SUBMITTED);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("Database error occurred retrieving timesheet event(s) by timesheet id: "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Change_Status_Invalid_Status_Movement() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to DRAFT.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.changeTimesheetStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, TimesheetConst.STATUS_APPROVED);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals(
                    "Timesheet status can only change to Approved or Declined when the current status is Submitted",
                    e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Change_Status_Invalid_New_Status_Provided() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to DRAFT.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.changeTimesheetStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                    ProjectTrackerMockDataFactory.TEST_INVALID_TIMESHEET_STATUS_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("An invalid timesheet status code was provided ["
                            + ProjectTrackerMockDataFactory.TEST_INVALID_TIMESHEET_STATUS_ID
                            + "]", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Change_Status_Null_New_Status() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to DRAFT.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.changeTimesheetStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("New timesheet status id is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Change_Status_Null_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.changeTimesheetStatus(null, ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Change_Status_Negative_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.changeTimesheetStatus(-1234, ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Change_Status_Zero_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.changeTimesheetStatus(0, ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " must be greater than zero", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Approve() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to SUBMITTED.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_SUBMITTED);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.approve(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        
        Assert.assertEquals(TimesheetConst.STATUS_SUBMITTED, results);
    }
    
    @Test
    public void testError_Approve_DB_Error_Fault() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.approve(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("Database error occurred retrieving timesheet event(s) by timesheet id: "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Approve_Invalid_Status_Movement() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to DRAFT.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.approve(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals(
                    "Timesheet status can only change to Approved or Declined when the current status is Submitted",
                    e.getMessage());
            e.printStackTrace();
        }
    }
    

    @Test
    public void testValidation_Approve_Null_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.approve(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Approve_Negative_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.approve(-1234);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Approve_Zero_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.approve(0);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " must be greater than zero", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Decline() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to SUBMITTED.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_SUBMITTED);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.decline(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        
        Assert.assertEquals(TimesheetConst.STATUS_SUBMITTED, results);
    }

    @Test
    public void testError_Decline_DB_Error_Fault() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.decline(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("Database error occurred retrieving timesheet event(s) by timesheet id: "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Decline_Invalid_Status_Movement() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to DRAFT.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.decline(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("Timesheet status can only change to Approved or Declined when the current status is Submitted",
                    e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Decline_Null_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.decline(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Decline_Negative_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.decline(-1234);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Decline_Zero_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.decline(0);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " must be greater than zero", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Submit_Timesheet() {
        // Setup timesheet load stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet data for timesheet load process case setup failed");
        }
        VwTimesheetProjectTask mockProjTaskCriteria = new VwTimesheetProjectTask();
        mockProjTaskCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockProjTaskCriteria)))
                    .thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task data for timesheet load process case setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEvent.class)))
                    .thenReturn(createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 1),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 2),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 3),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 4));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all events data for timesheet load process case setup failed");
        }
        
        // Setup stubs for change timesheet status
        ProjTimesheetHist mockCurrentStatusCriteria = new ProjTimesheetHist();
        mockCurrentStatusCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCurrentStatusCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        // Stub employee and manager fetch.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEmployee.class))).thenReturn(this.mockEmployeeFetchSingle, this.mockManagerFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch employee and manager case setup failed");
        }
        
        // Stub single client fetch.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjClient.class))).thenReturn(this.mockClientFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        // Mock and stub TimesheetTransmissionApi...no need to test its internals here.
        TimesheetTransmissionApi mockTimesheetTransmissionApi = Mockito.mock(TimesheetTransmissionApi.class);
        PowerMockito.mockStatic(TimesheetApiFactory.class);
        when(TimesheetApiFactory.createTransmissionApi()).thenReturn(mockTimesheetTransmissionApi);
        EmailMessageBean mockEmailMsgBean = new EmailMessageBean();
        try {
            when(mockTimesheetTransmissionApi.createConfirmationMessage(
                    isA(TimesheetDto.class), isA(EmployeeDto.class),
                    isA(EmployeeDto.class), isA(ClientDto.class),
                    isA(Map.class))).thenReturn(mockEmailMsgBean);    
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("The mocking of TimesheetTransmissionApi's createConfirmationMessage method failed");
        }
        try {
            when(mockTimesheetTransmissionApi.send(eq(mockEmailMsgBean))).thenReturn(1);    
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("The mocking of TimesheetTransmissionApi's send method failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.submit(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Submit_Timesheet_Send_To_User() {
        // Setup timesheet load stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet data for timesheet load process case setup failed");
        }
        VwTimesheetProjectTask mockProjTaskCriteria = new VwTimesheetProjectTask();
        mockProjTaskCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockProjTaskCriteria)))
                    .thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task data for timesheet load process case setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEvent.class)))
                    .thenReturn(createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 1),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 2),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 3),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 4));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all events data for timesheet load process case setup failed");
        }
        
        // Setup stubs for change timesheet status
        ProjTimesheetHist mockCurrentStatusCriteria = new ProjTimesheetHist();
        mockCurrentStatusCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCurrentStatusCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        // Stub employee and manager fetch.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEmployee.class))).thenReturn(this.mockEmployeeFetchSingle, this.mockManagerFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch employee and manager case setup failed");
        }
        
        // Stub single client fetch.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjClient.class))).thenReturn(this.mockClientFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        // Mock and stub TimesheetTransmissionApi...no need to test its internals here.
        TimesheetTransmissionApi mockTimesheetTransmissionApi = Mockito.mock(TimesheetTransmissionApi.class);
        PowerMockito.mockStatic(TimesheetApiFactory.class);
        when(TimesheetApiFactory.createTransmissionApi()).thenReturn(mockTimesheetTransmissionApi);
        EmailMessageBean mockEmailMsgBean = new EmailMessageBean();
        try {
            when(mockTimesheetTransmissionApi.createConfirmationMessage(
                    isA(TimesheetDto.class), isA(EmployeeDto.class),
                    isA(EmployeeDto.class), isA(ClientDto.class),
                    isA(Map.class))).thenReturn(mockEmailMsgBean);    
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("The mocking of TimesheetTransmissionApi's createConfirmationMessage method failed");
        }
        try {
            when(mockTimesheetTransmissionApi.send(eq(mockEmailMsgBean))).thenThrow(TimesheetTransmissionException.class);   
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("The mocking of TimesheetTransmissionApi's send method failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.submit(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("SMTP error occurred attempting to send timesheet: 0000000111", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof TimesheetTransmissionException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Submit_Timesheet_Create_Confirmation_Email() {
        // Setup timesheet load stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet data for timesheet load process case setup failed");
        }
        VwTimesheetProjectTask mockProjTaskCriteria = new VwTimesheetProjectTask();
        mockProjTaskCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockProjTaskCriteria)))
                    .thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task data for timesheet load process case setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEvent.class)))
                    .thenReturn(createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 1),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 2),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 3),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 4));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all events data for timesheet load process case setup failed");
        }
        
        // Setup stubs for change timesheet status
        ProjTimesheetHist mockCurrentStatusCriteria = new ProjTimesheetHist();
        mockCurrentStatusCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCurrentStatusCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        // Stub employee and manager fetch.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEmployee.class))).thenReturn(this.mockEmployeeFetchSingle, this.mockManagerFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch employee and manager case setup failed");
        }
        
        // Stub single client fetch.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjClient.class))).thenReturn(this.mockClientFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        // Mock and stub TimesheetTransmissionApi...no need to test its internals here.
        TimesheetTransmissionApi mockTimesheetTransmissionApi = Mockito.mock(TimesheetTransmissionApi.class);
        PowerMockito.mockStatic(TimesheetApiFactory.class);
        when(TimesheetApiFactory.createTransmissionApi()).thenReturn(mockTimesheetTransmissionApi);
        try {
            when(mockTimesheetTransmissionApi.createConfirmationMessage(
                    isA(TimesheetDto.class), isA(EmployeeDto.class),
                    isA(EmployeeDto.class), isA(ClientDto.class),
                    isA(Map.class))).thenThrow(new TimesheetTransmissionException("Timesheet SMTP transmission validation error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("The mocking of TimesheetTransmissionApi's createConfirmationMessage method failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.submit(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("SMTP error occurred attempting to send timesheet: 0000000111", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof TimesheetTransmissionException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Submit_DB_Access_Fault_For_Client_Fetch() {
        // Setup timesheet load stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet data for timesheet load process case setup failed");
        }
        VwTimesheetProjectTask mockProjTaskCriteria = new VwTimesheetProjectTask();
        mockProjTaskCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockProjTaskCriteria)))
                    .thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task data for timesheet load process case setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEvent.class)))
                    .thenReturn(createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 1),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 2),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 3),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 4));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all events data for timesheet load process case setup failed");
        }
        
        // Setup stubs for change timesheet status
        ProjTimesheetHist mockCurrentStatusCriteria = new ProjTimesheetHist();
        mockCurrentStatusCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCurrentStatusCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        // Stub employee and manager fetch.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEmployee.class))).thenReturn(this.mockEmployeeFetchSingle, this.mockManagerFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch employee and manager case setup failed");
        }
        
        // Stub single client fetch.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjClient.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.submit(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("Data access error fetching timesheet's client profile: " + ProjectTrackerMockDataFactory.TEST_CLIENT_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ProjectAdminApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Submit_DB_Access_Fault_For_Employee_Fetch() {
        // Setup timesheet load stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet data for timesheet load process case setup failed");
        }
        VwTimesheetProjectTask mockProjTaskCriteria = new VwTimesheetProjectTask();
        mockProjTaskCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockProjTaskCriteria)))
                    .thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task data for timesheet load process case setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEvent.class)))
                    .thenReturn(createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 1),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 2),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 3),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 4));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all events data for timesheet load process case setup failed");
        }
        
        // Setup stubs for change timesheet status
        ProjTimesheetHist mockCurrentStatusCriteria = new ProjTimesheetHist();
        mockCurrentStatusCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
            when(this.mockPersistenceClient.retrieveList(eq(mockCurrentStatusCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        // Stub employee and manager fetch.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEmployee.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch employee and manager case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.submit(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("Data access error fetching timesheet's employee profile: " + ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof EmployeeApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Submit_DB_Access_Fault_For_Timesheet_Load() {
        // Setup timesheet load stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet data for timesheet load process case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.submit(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("Database error occurred retrieving single extended timesheet by id, " + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Submit_Null_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.submit(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Submit_Negative_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.submit(-1234);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Submit_Zero_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.submit(0);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " must be greater than zero", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Update_Timesheet() {
        Assert.fail("Implement test case");
    }
    
    @Test
    public void testSuccess_Update_Project_Task() {
        Assert.fail("Implement test case");
    }
    
    @Test
    public void testSuccess_Update_Single_Event() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Update_Multiple_Events() {
        Assert.fail("Implement test case");
    }
    
    @Test
    public void testSuccess_Delete_Event_By_Event_id() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Delete_Events_By_ProjectTaskId() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Delete_ProjectTask_By_ProjectTaskId() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Delete_ProjectTasks_By_Timesheet_Id() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Delete_Timesheet() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Send_Timesheet() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Set_Current_Project_Id() {
        Assert.fail("Implement test case");
    }

}