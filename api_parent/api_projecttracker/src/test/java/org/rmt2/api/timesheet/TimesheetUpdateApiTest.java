package org.rmt2.api.timesheet;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.admin.ProjectAdminDaoException;
import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
import org.dao.timesheet.TimesheetConst;
import org.dao.timesheet.TimesheetDaoException;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetDto;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.dto.adapter.orm.TimesheetObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.ProjectTrackerApiConst;
import org.modules.admin.ProjectAdminApiException;
import org.modules.employee.EmployeeApiException;
import org.modules.timesheet.InvalidEventException;
import org.modules.timesheet.InvalidProjectTaskException;
import org.modules.timesheet.InvalidTimesheetException;
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
import com.NotFoundException;
import com.SystemException;
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

    private TimesheetDto buildTimesheetDto(boolean newTimesheet) {
        ProjTimesheet ormTs = this.mockProjTimesheetSingle.get(0);
        if (newTimesheet) {
            ormTs.setTimesheetId(0);
            ormTs.setProjId(0);
            ormTs.setDisplayValue(null);
        }
        TimesheetDto ts = TimesheetObjectFactory.createTimesheetDtoInstance(ormTs);
        return ts;
    }
    
    private Map<ProjectTaskDto, List<EventDto>> buildTimesheetHoursDtoMap(boolean newTimesheet) {
        Map<ProjectTaskDto, List<EventDto>> hours = new HashMap<>(); 
        for (ProjProjectTask pt : this.mockProjProjectTaskMultiple) {
            if (newTimesheet) {
                pt.setTimesheetId(0);
                pt.setProjectTaskId(0);
            }
            ProjectTaskDto ptDto = ProjectObjectFactory.createProjectTaskDtoInstance(pt);
            List<EventDto> eventsDto = this.buildTimesheetEventDtoList(pt.getProjectTaskId(), newTimesheet);
            hours.put(ptDto, eventsDto);
        }
        return hours;
    }
    
    private List<EventDto> buildTimesheetEventDtoList(int projectTaskId, boolean newTimesheet) {
        List<ProjEvent> projEvents = createMockMultiple_Day_Task_Events(projectTaskId);
        List<EventDto> eventsDto = new ArrayList<>();
        for (ProjEvent evt : projEvents) {
            if (newTimesheet) {
                evt.setEventId(0);
            }
            EventDto evtDto = ProjectObjectFactory.createEventDtoInstance(evt);
            eventsDto.add(evtDto);
        }
        return eventsDto;
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
    public void testSuccess_Update_New_Timesheet() {
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheet.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheet.class), eq(true)))
                 .thenReturn(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjProjectTask.class), eq(true)))
                            .thenReturn(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID,
                                    ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 1,
                                    ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 2,
                                    ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 3,
                                    ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 4);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert project-task case setup failed");
        }
        
        try {
            int ndx = 0;
            when(this.mockPersistenceClient.insertRow(isA(ProjEvent.class), eq(true)))
                            .thenReturn(ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert project-task case setup failed");
        }
        
        // Stub timesheet current status
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
            TimesheetDto ts = this.buildTimesheetDto(true);
            Map<ProjectTaskDto, List<EventDto>> hours = this.buildTimesheetHoursDtoMap(true); 
            results = api.updateTimesheet(ts, hours);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Update of timesheet case setup failed");
        }
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, results);
    }
    
    @Test
    public void testSuccess_Update_Existing_Timesheet() {
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheet.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheet.class), eq(true)))
                 .thenReturn(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjProjectTask.class), eq(true)))
                            .thenReturn(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID,
                                    ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 1,
                                    ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 2,
                                    ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 3,
                                    ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 4);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert project-task case setup failed");
        }
        
        try {
            int ndx = 0;
            when(this.mockPersistenceClient.insertRow(isA(ProjEvent.class), eq(true)))
                            .thenReturn(ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert project-task case setup failed");
        }
        
        // Stub timesheet current status
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
            TimesheetDto ts = this.buildTimesheetDto(false);
            Map<ProjectTaskDto, List<EventDto>> hours = this.buildTimesheetHoursDtoMap(false); 
            results = api.updateTimesheet(ts, hours);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Update of timesheet case setup failed");
        }
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, results);
    }
    
    @Test
    public void testError_Update_Timesheet_DB_Access_Fault() {
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheet.class), eq(true)))
                .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            TimesheetDto ts = this.buildTimesheetDto(true);
            Map<ProjectTaskDto, List<EventDto>> hours = this.buildTimesheetHoursDtoMap(true); 
            api.updateTimesheet(ts, hours);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("Unable to save timesheet due to a DAO error", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Timesheet_Null_Timesheet_Object() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            Map<ProjectTaskDto, List<EventDto>> hours = this.buildTimesheetHoursDtoMap(true); 
            api.updateTimesheet(null, hours);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            Assert.assertTrue(e instanceof InvalidTimesheetException);
            Assert.assertEquals("The timesheet instance is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Timesheet_ClientId_Negative() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            TimesheetDto ts = this.buildTimesheetDto(true);
            ts.setClientId(-1234);
            Map<ProjectTaskDto, List<EventDto>> hours = this.buildTimesheetHoursDtoMap(true); 
            api.updateTimesheet(ts, hours);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            Assert.assertTrue(e instanceof InvalidTimesheetException);
            Assert.assertEquals("Timesheet must be associated with a client", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Timesheet_ClientId_Zero() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            TimesheetDto ts = this.buildTimesheetDto(true);
            ts.setClientId(0);
            Map<ProjectTaskDto, List<EventDto>> hours = this.buildTimesheetHoursDtoMap(true); 
            api.updateTimesheet(ts, hours);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            Assert.assertTrue(e instanceof InvalidTimesheetException);
            Assert.assertEquals("Timesheet must be associated with a client", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Timesheet_EmployeeId_Negative() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            TimesheetDto ts = this.buildTimesheetDto(true);
            ts.setEmpId(-1234);
            Map<ProjectTaskDto, List<EventDto>> hours = this.buildTimesheetHoursDtoMap(true); 
            api.updateTimesheet(ts, hours);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            Assert.assertTrue(e instanceof InvalidTimesheetException);
            Assert.assertEquals("Timesheet must be associated with an employee", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Timesheet_EmployeeId_Zero() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            TimesheetDto ts = this.buildTimesheetDto(true);
            ts.setEmpId(0);
            Map<ProjectTaskDto, List<EventDto>> hours = this.buildTimesheetHoursDtoMap(true); 
            api.updateTimesheet(ts, hours);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            Assert.assertTrue(e instanceof InvalidTimesheetException);
            Assert.assertEquals("Timesheet must be associated with an employee", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Timesheet_Hours_Map_Null() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            TimesheetDto ts = this.buildTimesheetDto(true);
            api.updateTimesheet(ts, null);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            Assert.assertTrue(e instanceof InvalidTimesheetException);
            Assert.assertEquals("Timesheet hours are required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Timesheet_Hours_Map_Empty() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            TimesheetDto ts = this.buildTimesheetDto(true);
            Map<ProjectTaskDto, List<EventDto>> hours = new HashMap<>(); 
            api.updateTimesheet(ts, hours);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            Assert.assertTrue(e instanceof InvalidTimesheetException);
            Assert.assertEquals("Timesheet hours are required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Create_Project_Task() {
        try {
            this.mockProjProjectTaskSingle.get(0).setProjectTaskId(0);
            when(this.mockPersistenceClient.insertRow(isA(ProjProjectTask.class), eq(true)))
                            .thenReturn(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert project-task case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        ProjectTaskDto projTask = ProjectObjectFactory.createProjectTaskDtoInstance(this.mockProjProjectTaskSingle.get(0));
        int results = 0;
        try {
            results = api.updateProjectTask(projTask);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Update of timesheet case setup failed");
        }
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, results);
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, projTask.getProjectTaskId());
    }
    
    @Test
    public void testError_Update_Project_Task_DB_Access_Fault() {
        try {
            this.mockProjProjectTaskSingle.get(0).setProjectTaskId(0);
            when(this.mockPersistenceClient.insertRow(isA(ProjProjectTask.class), eq(true)))
                   .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert project-task case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        ProjectTaskDto projTask = ProjectObjectFactory.createProjectTaskDtoInstance(this.mockProjProjectTaskSingle.get(0));
        try {
            api.updateProjectTask(projTask);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("A DAO error occurred updating a timesheet's Project/Task", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            Assert.assertEquals("Unable to create timesheet project/task due to database errors", e.getCause().getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Project_Task_Null_ProjectTask_Object() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.updateProjectTask(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidProjectTaskException);
            Assert.assertEquals("ProejctTask data object is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Project_Task_Negative_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        ProjectTaskDto projTask = ProjectObjectFactory.createProjectTaskDtoInstance(this.mockProjProjectTaskSingle.get(0));
        try {
            projTask.setProjectTaskId(-1234);
            api.updateProjectTask(projTask);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidProjectTaskException);
            Assert.assertEquals("ProejctTask Id canot be negative", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Project_Task_Negative_ProjectId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        ProjectTaskDto projTask = ProjectObjectFactory.createProjectTaskDtoInstance(this.mockProjProjectTaskSingle.get(0));
        try {
            projTask.setProjId(-1234);
            api.updateProjectTask(projTask);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidProjectTaskException);
            Assert.assertEquals("Proejct Id is required and must be greater than zero when creating Project-Task", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Project_Task_Zero_ProjectId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        ProjectTaskDto projTask = ProjectObjectFactory.createProjectTaskDtoInstance(this.mockProjProjectTaskSingle.get(0));
        try {
            projTask.setProjId(0);
            api.updateProjectTask(projTask);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidProjectTaskException);
            Assert.assertEquals("Proejct Id is required and must be greater than zero when creating Project-Task", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Project_Task_Negative_TaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        ProjectTaskDto projTask = ProjectObjectFactory.createProjectTaskDtoInstance(this.mockProjProjectTaskSingle.get(0));
        try {
            projTask.setTaskId(-1234);
            api.updateProjectTask(projTask);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidProjectTaskException);
            Assert.assertEquals("Task Id is required and must be greater than zero when creating Project-Task", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Project_Task_Zero_TaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        ProjectTaskDto projTask = ProjectObjectFactory.createProjectTaskDtoInstance(this.mockProjProjectTaskSingle.get(0));
        try {
            projTask.setTaskId(0);
            api.updateProjectTask(projTask);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidProjectTaskException);
            Assert.assertEquals("Task Id is required and must be greater than zero when creating Project-Task", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Create_Single_Event() {
        when(this.mockPersistenceClient.insertRow(isA(ProjEvent.class), eq(true)))
            .thenReturn(ProjectTrackerMockDataFactory.TEST_EVENT_ID);
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        EventDto event = ProjectObjectFactory.createEventDtoInstance(this.mockProjEventFetchSingle.get(0));
        int results = 0;
        try {
            event.setEventId(0);
            results = api.updateEvent(event);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Insert of timesheet event failed");
        }
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_EVENT_ID, results);
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_EVENT_ID, event.getEventId());
    }
    
    @Test
    public void testSuccess_Update_Single_Event() {
        when(this.mockPersistenceClient.updateRow(isA(ProjEvent.class))).thenReturn(1);
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        EventDto event = ProjectObjectFactory.createEventDtoInstance(this.mockProjEventFetchSingle.get(0));
        int results = 0;
        try {
            results = api.updateEvent(event);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Insert of timesheet event failed");
        }
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Update_Event_DB_Access_Fault() {
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjEvent.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update event case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        EventDto event = ProjectObjectFactory.createEventDtoInstance(this.mockProjEventFetchSingle.get(0));
        try {
            api.updateEvent(event);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("A DAO error occurred updating a timesheet's Event", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            Assert.assertEquals("Unable to update project event due to database errors", e.getCause().getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Event_Null_Event_Object() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.updateEvent(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidEventException);
            Assert.assertEquals("Event object is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Event_Negative_EventId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        EventDto event = ProjectObjectFactory.createEventDtoInstance(this.mockProjEventFetchSingle.get(0));
        try {
            event.setEventId(-1234);
            api.updateEvent(event);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidEventException);
            Assert.assertEquals("Event id cannot be negative", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Event_Negative_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        EventDto event = ProjectObjectFactory.createEventDtoInstance(this.mockProjEventFetchSingle.get(0));
        try {
            event.setProjectTaskId(-1234);
            api.updateEvent(event);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidEventException);
            Assert.assertEquals("Event project/task id is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Event_Zero_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        EventDto event = ProjectObjectFactory.createEventDtoInstance(this.mockProjEventFetchSingle.get(0));
        try {
            event.setProjectTaskId(0);
            api.updateEvent(event);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidEventException);
            Assert.assertEquals("Event project/task id is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Update_Event_Null_EventDate() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        EventDto event = ProjectObjectFactory.createEventDtoInstance(this.mockProjEventFetchSingle.get(0));
        try {
            event.setEventDate(null);
            api.updateEvent(event);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof InvalidEventException);
            Assert.assertEquals("Event date is required", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testSuccess_Create_Multiple_Events() {
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setProjectTaskId(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetProjectTaskFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single project-task case setup failed");
        }
        try {
            int ndx = 0;
            when(this.mockPersistenceClient.insertRow(isA(ProjEvent.class), eq(true)))
                            .thenReturn(ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++,
                                    ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx++);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert event case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<EventDto> events = this.buildTimesheetEventDtoList(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, true);
        int results = 0;
        try {
            results = api.updateEvent(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, events);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Insert of timesheet event failed");
        }
        Assert.assertEquals(7, results);
        for (int ndx = 0; ndx < events.size(); ndx++) {
            EventDto dto = events.get(ndx);
            Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx, dto.getEventId());
        }
    }
    
    @Test
    public void testSuccess_Update_Multiple_Events() {
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setProjectTaskId(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetProjectTaskFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single project-task case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjEvent.class))).thenReturn(5);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update event case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<EventDto> events = this.buildTimesheetEventDtoList(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, false);
        int results = 0;
        try {
            results = api.updateEvent(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, events);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Insert of timesheet event failed");
        }
        Assert.assertEquals(7, results);
        for (int ndx = 0; ndx < events.size(); ndx++) {
            EventDto dto = events.get(ndx);
            Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_EVENT_ID + ndx, dto.getEventId());
        }
    }
    
    @Test
    public void testError_Update_Multiple_Events_DB_Access_Fault_During_Validation() {
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setProjectTaskId(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single project-task case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<EventDto> events = this.buildTimesheetEventDtoList(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, false);
        try {
            api.updateEvent(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, events);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SystemException);
            Assert.assertTrue(e.getCause() instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof ProjectAdminDaoException);
            Assert.assertEquals("A DAO error occurred validating project task entity existence", e.getMessage());

        }
    }
    
    @Test
    public void testError_Update_Multiple_Events_DB_Access_Fault_After_Validation() {
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setProjectTaskId(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetProjectTaskFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single project-task case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjEvent.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update event case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<EventDto> events = this.buildTimesheetEventDtoList(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, false);
        try {
            api.updateEvent(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, events);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals("A DAO error occurred updating a timesheet's Event", e.getMessage());

        }
    }
    
    @Test
    public void testValidation_Update_Multiple_Events_Null_Event_List() {
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setProjectTaskId(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetProjectTaskFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single project-task case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.updateEvent(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(
                    "The collection containing the lists of project events must be valid in order to assoicate multiple events with a project task",
                    e.getMessage());
        }
    }
        
    @Test
    public void testValidation_Update_Multiple_Events_ProjectTask_Not_Found() {
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setProjectTaskId(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single project-task case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<EventDto> events = this.buildTimesheetEventDtoList(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, false);
        try {
            api.updateEvent(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, events);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof NotFoundException);
            Assert.assertEquals("project/task id, " + ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID
                    + ", must exist in the system in order to assoicate multiple events with a project task",
                    e.getMessage());
        }
    }   
    
    @Test
    public void testValidation_Update_Multiple_Events_Null_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<EventDto> events = this.buildTimesheetEventDtoList(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, false);
        try {
            api.updateEvent(null, events);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Project Task Id is required", e.getMessage());
        }
    }   
    
    @Test
    public void testValidation_Update_Multiple_Events_Negative_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<EventDto> events = this.buildTimesheetEventDtoList(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, false);
        try {
            api.updateEvent(-1234, events);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("A project/task id must be valid in order to assoicate multiple events with a project task",
                    e.getMessage());
        }
    }   
    
    @Test
    public void testValidation_Update_Multiple_Events_Zero_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        List<EventDto> events = this.buildTimesheetEventDtoList(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID, false);
        try {
            api.updateEvent(0, events);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("A project/task id must be valid in order to assoicate multiple events with a project task",
                    e.getMessage());
        }
    }   
    
    @Test
    public void testSuccess_Delete_Event_By_Event_id() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete single event case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.deleteEvent(ProjectTrackerMockDataFactory.TEST_EVENT_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Delete of single timesheet event failed");
        }
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Delete_Event_By_Event_id_DB_Access_Fault() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete single event case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteEvent(ProjectTrackerMockDataFactory.TEST_EVENT_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void testValidation_Delete_Event_By_Event_id_Null_EventId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteEvent(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Event Id is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Delete_Event_By_Event_id_Negative_EventId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteEvent(-1234);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Event Id cannot be negative", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Delete_Events_By_ProjectTaskId() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenReturn(5);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete multiple events case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.deleteEvents(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Delete of multiple timesheet event failed");
        }
        Assert.assertEquals(5, results);
    }
    
    @Test
    public void testValidation_Delete_Event_By_Event_id_Null_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteEvents(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Project Task Id is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Delete_Event_By_Event_id_Negative_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteEvents(-1234);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Project Task Id cannot be negative", e.getMessage());
        }
    }
    
    @Test
    public void testError_Delete_Events_By_ProjectTaskId_DB_Access_Fault() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete single event case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteEvents(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void testSuccess_Delete_ProjectTask_By_ProjectTaskId() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenReturn(5);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete multiple events case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjProjectTask.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete project task case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.deleteProjectTask(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Delete of timesheet project task failed");
        }
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Delete_ProjectTask_By_ProjectTaskId_DB_Access_Fault() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenReturn(5);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete multiple events case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjProjectTask.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete project task case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteProjectTask(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Delete_ProjectTask_By_ProjectTaskId_Null_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteProjectTask(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Project Task Id is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Delete_ProjectTask_By_ProjectTaskId_Negative_ProjectTaskId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteProjectTask(-1234);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Project Task Id cannot be negative", e.getMessage());
        }
    }

    @Test
    public void testSuccess_Delete_ProjectTasks_By_Timesheet_Id() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenReturn(5);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete multiple events case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjProjectTask.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete project task case setup failed");
        }
        
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
        int results = 0;
        try {
            results = api.deleteProjectTasks(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Delete of project tasks by timesheet failed");
        }
        Assert.assertEquals(5, results);
    }

    @Test
    public void testError_Delete_ProjectTasks_By_Timesheet_Id_DB_Access_Fault() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenReturn(5);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete multiple events case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjProjectTask.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete project task case setup failed");
        }
        
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
        try {
            api.deleteProjectTasks(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void testValidation_Delete_ProjectTask_By_TimesheetId_Null_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteProjectTasks(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Delete_ProjectTask_By_TimesheetId_Negative_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteProjectTasks(-1234);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Delete_Timesheet() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenReturn(5);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete multiple events case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjProjectTask.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete project task case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjTimesheet.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete timesheet case setup failed");
        }
        
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
        int results = 0;
        try {
            results = api.deleteTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Delete of timesheet failed");
        }
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Delete_Timesheet_DB_Access_Fault() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjEvent.class))).thenReturn(5);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete multiple events case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjProjectTask.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete project task case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjTimesheet.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete timesheet case setup failed");
        }
        
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
        try {
            api.deleteTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Delete_Timesheet_Null_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteTimesheet(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Delete_Timesheet_Negative_TimesheetId() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.deleteTimesheet(-1234);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative", e.getMessage());
        }
    }

    @Test
    public void testSuccess_Send_Timesheet() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.send(null, null, null, null);
        } catch (TimesheetTransmissionException | TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Timesheet transmission test failed");
        }
        Assert.assertEquals(0, results);
    }

    @Test
    public void testSuccess_Set_Current_Project_Id() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        api.setCurrentProjectId(ProjectTrackerMockDataFactory.TEST_PROJ_ID);
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_PROJ_ID, api.getCurrentProjectId());
    }

}