package org.rmt2.api.timesheet;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.timesheet.TimesheetConst;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.ProjectTrackerApiConst;
import org.modules.timesheet.TimesheetApi;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.InvalidDataException;
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
        ResultSet.class })
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
        Assert.fail("Implement test case");
    }
    
    @Test
    public void testSuccess_Decline() {
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
    public void testSuccess_Submit_Timesheet() {
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
    public void testSuccess_Update_Project_Task() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Update_Timesheet() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Set_Current_Project_Id() {
        Assert.fail("Implement test case");
    }

}