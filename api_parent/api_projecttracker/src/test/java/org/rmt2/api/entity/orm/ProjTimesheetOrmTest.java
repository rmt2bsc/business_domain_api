package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.api.util.RMT2Date;

/**
 * Test of ProjTimesheet ORM class
 * 
 * @author roy.terrell
 *
 */
public class ProjTimesheetOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjTimesheet o1 = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 1234, 2220, "INVREF1230",
                "2018-01-01", "2018-01-07", "ExtReNo1000");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjTimesheet o1 = new ProjTimesheet();
        ProjTimesheet o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 1234, 2220, "INVREF1230",
                "2018-01-01", "2018-01-07", "ExtReNo1000");
        o2 = new ProjTimesheet();
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
        
        o2.setDocumentId(o2.getTimesheetId());
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjTimesheet o1 = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 1234, 2220, "INVREF1230",
                "2018-01-01", "2018-01-07", "ExtReNo1000");
        ProjTimesheet o2 = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 1234, 2220, "INVREF1230",
                "2018-01-01", "2018-01-07", "ExtReNo1000");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
