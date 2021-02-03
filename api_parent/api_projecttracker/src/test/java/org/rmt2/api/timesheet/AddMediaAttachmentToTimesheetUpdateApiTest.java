package org.rmt2.api.timesheet;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.timesheet.TimesheetDaoException;
import org.dto.TimesheetDto;
import org.dto.adapter.orm.TimesheetObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.timesheet.TimesheetApi;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class, TimesheetApiFactory.class })
public class AddMediaAttachmentToTimesheetUpdateApiTest extends TimesheetMockData {

    private static final int APPROVE_STATUS_ID = 4;
    private static final int DECLINE_STATUS_ID = 5;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        try {
            when(this.mockPersistenceClient.retrieveObject(isA(ProjTimesheet.class)))
                    .thenReturn(this.mockProjTimesheetSingle.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }

        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheet.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet case setup failed");
        }
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

    @Test
    public void test_Success() {
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            TimesheetDto ts = this.buildTimesheetDto(false);
            results = api.updateTimesheet(ts);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
            Assert.fail("Update of timesheet case setup failed");
        }
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void test_API_Error() {
        try {
            when(this.mockPersistenceClient.retrieveObject(isA(ProjTimesheet.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet API Error mock setup failed");
        }

        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            TimesheetDto ts = this.buildTimesheetDto(false);
            api.updateTimesheet(ts);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals("Unable to update timesheet due to DAO error", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof TimesheetDaoException);
            e.printStackTrace();
        }
    }
}