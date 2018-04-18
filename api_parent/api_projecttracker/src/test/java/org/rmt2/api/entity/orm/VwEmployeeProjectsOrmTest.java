package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.util.RMT2Date;

/**
 * Test of VwEmployeeProjects ORM class
 * 
 * @author roy.terrell
 *
 */
public class VwEmployeeProjectsOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwEmployeeProjects o1 = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55551, 2220, "Project 2220",
                1000, "1000 Company", 1350, "000-111", 5000,
                "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwEmployeeProjects o1 = new VwEmployeeProjects();
        VwEmployeeProjects o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55551, 2220, "Project 2220",
                1000, "1000 Company", 1350, "000-111", 5000,
                "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        o2 = new VwEmployeeProjects();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmpProjId(55551);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjId(2220);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjectName("Project 2220");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setClientId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setClientName("1000 Company");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBusinessId(1350);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAccountNo("000-111");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmpId(5000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjEndDate(RMT2Date.stringToDate("2018-02-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjEffectiveDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjempEndDate(RMT2Date.stringToDate("2018-02-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjempEffectiveDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setPayRate(50.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setOtPayRate(55.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setFlatRate(0.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setClientBillRate(70.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setClientOtBillRate(80.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setComments("Comments for Employee Project Id: " + o2.getEmpProjId());
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwEmployeeProjects o1 = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55551, 2220, "Project 2220",
                1000, "1000 Company", 1350, "000-111", 5000,
                "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        VwEmployeeProjects o2 = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55551, 2220, "Project 2220",
                1000, "1000 Company", 1350, "000-111", 5000,
                "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
