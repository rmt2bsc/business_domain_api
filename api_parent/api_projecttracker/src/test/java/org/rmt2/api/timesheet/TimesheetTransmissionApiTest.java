package org.rmt2.api.timesheet;

import java.sql.ResultSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.timesheet.TimesheetApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Test the timesheet transmission functionality of the Timesheet module of the
 * Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class, TimesheetApiFactory.class })
public class TimesheetTransmissionApiTest extends TimesheetMockData {

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
    public void testSuccess_CreateConfirmation() {
        // TODO: Please look at the success scenario of "submit" timesheet in
        // TimesheetUpdateApiTest.java for mockin examples.
        Assert.fail("Implement test case");
    }

    @Test
    public void testSuccess_Send() {
        Assert.fail("Implement test case");
    }

}