package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.VwTimesheetHours;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.util.RMT2Date;

/**
 * Test of VwTimesheetHours ORM class
 * 
 * @author roy.terrell
 *
 */
public class VmTimesheetHoursOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwTimesheetHours o1 = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-07", 8, true);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwTimesheetHours o1 = new VwTimesheetHours();
        VwTimesheetHours o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-07", 8, true);
        o2 = new VwTimesheetHours();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTimesheetId(111);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setClientId(1110);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjectId(4440);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmployeeId(2220);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTaskId(1112220);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEventId(123401);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjTaskId(444441);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setHours(8);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEventDate(RMT2Date.stringToDate("2018-01-07"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEffectiveDate(RMT2Date.stringToDate("2018-01-07"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTimesheetBeginPeriod(RMT2Date.stringToDate("2018-01-07"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEndDate(RMT2Date.stringToDate("2018-01-08"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTimesheetEndPeriod(RMT2Date.stringToDate("2018-01-08"));
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setInvoiceRefNo("InvoiceRefNo" + o2.getTimesheetId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setExtRef("ExtRefNo" + o2.getTimesheetId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBillable(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDisplayValue("0000000111");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTaskName("TaskName" + o2.getTaskId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjectName("ProjectName" + o2.getProjectId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDocumentId(o2.getTimesheetId());
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwTimesheetHours o1 = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-07", 8, true);
        VwTimesheetHours o2 = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-07", 8, true);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
