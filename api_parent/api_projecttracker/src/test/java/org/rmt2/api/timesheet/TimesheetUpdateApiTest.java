package org.rmt2.api.timesheet;

//import static org.mockito.Matchers.eq;
//import static org.mockito.Matchers.isA;
//import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.persistence.AbstractDaoClientImpl;
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
    public void testSuccess_Approve() {
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Change_Status() {
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