package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.timesheet.TimesheetConst;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.util.RMT2Date;

/**
 * Test of VwTimesheetList ORM class
 * 
 * @author roy.terrell
 *
 */
public class VmTimesheetListOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwTimesheetList o1 = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(111, 1110, 1234, 2220,
                        "INVREF1230", "2018-01-01", "2018-01-07", "ExtReNo1000",
                        3330, "QUOTE", "ACCT-111", 40, 0, 70.00, 80.00);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwTimesheetList o1 = new VwTimesheetList();
        VwTimesheetList o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(111, 1110, 1234, 2220,
                "INVREF1230", "2018-01-01", "2018-01-07", "ExtReNo1000",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        o2 = new VwTimesheetList();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTimesheetId(111);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setClientId(1110);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjId(1234);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmpId(2220);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setInvoiceRefNo("INVREF1230");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBeginPeriod(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEndPeriod(RMT2Date.stringToDate("2018-01-07"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setExtRef("ExtReNo1000");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setComments("Comments" + o2.getTimesheetId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDisplayValue("0000000111");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setManagerId(3330);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setStatusName("DRAFT");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAccountNo("ACCT-111");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBillHrs(40);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setNonBillHrs(0);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setHourlyRate(70.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setHourlyOverRate(80.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTimesheetStatusId(TimesheetConst.STATUS_DRAFT);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setStatusDescription(o2.getStatusName() + "Description");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setStatusEffectiveDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setStatusEndDate(RMT2Date.stringToDate("2018-01-07"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTypeId(222);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjTimesheetHistId(5555);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setFirstname("FirstName" + o2.getTimesheetId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLastname("LastName" + o2.getTimesheetId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLastFirstName(o2.getLastname() + ", " + o2.getFirstname());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setClientName("ClientName" + o2.getClientId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDocumentId(o2.getTimesheetId());
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwTimesheetList o1 = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(111, 1110, 1234, 2220,
                "INVREF1230", "2018-01-01", "2018-01-07", "ExtReNo1000",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        VwTimesheetList o2 = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(111, 1110, 1234, 2220,
                "INVREF1230", "2018-01-01", "2018-01-07", "ExtReNo1000",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
