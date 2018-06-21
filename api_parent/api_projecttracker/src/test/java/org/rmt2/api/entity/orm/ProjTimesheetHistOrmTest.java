package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.timesheet.TimesheetConst;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.api.util.RMT2Date;

/**
 * Test of ProjTimesheetHist ORM class
 * 
 * @author roy.terrell
 *
 */
public class ProjTimesheetHistOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjTimesheetHist o1 = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                        ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                        ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                        TimesheetConst.STATUS_DRAFT, "2018-01-01", "2018-01-10");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjTimesheetHist o1 = new ProjTimesheetHist();
        ProjTimesheetHist o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                TimesheetConst.STATUS_DRAFT, "2018-01-01", "2018-01-10");
        o2 = new ProjTimesheetHist();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTimesheetHistId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEffectiveDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEndDate(RMT2Date.stringToDate("2018-01-10"));
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjTimesheetHist o1 = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                TimesheetConst.STATUS_DRAFT, "2018-01-01", "2018-01-10");
        ProjTimesheetHist o2 = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                TimesheetConst.STATUS_DRAFT, "2018-01-01", "2018-01-10");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
