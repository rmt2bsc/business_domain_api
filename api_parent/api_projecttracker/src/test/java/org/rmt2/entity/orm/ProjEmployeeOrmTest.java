package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.ProjEmployee;
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
public class ProjEmployeeOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjEmployee o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5000, 201, 1, 3333, 101, 999991,
                "2010-01-01", "2018-01-01", "login_name_1", "first_name_1", "last_name_1",
                "111-11-5000", "ABC Company");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjEmployee o1 = new ProjEmployee();
        ProjEmployee o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5000, 201, 1, 3333, 101, 999991,
                "2010-01-01", "2018-01-01", "login_name_1", "first_name_1", "last_name_1",
                "111-11-5000", "ABC Company");
        o2 = new ProjEmployee();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmpId(5000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmpTypeId(201);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setIsManager(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setManagerId(3333);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmpTitleId(101);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLoginId(999991);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSsn("111-11-5000");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setStartDate(RMT2Date.stringToDate("2010-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTerminationDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLoginName("login_name_1");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setFirstname("first_name_1");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLastname("last_name_1");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setCompanyName("ABC Company");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjEmployee o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5000, 201, 1, 3333, 101, 999991,
                "2010-01-01", "2018-01-01", "login_name_1", "first_name_1", "last_name_1",
                "111-11-5000", "ABC Company");
        ProjEmployee o2 = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5000, 201, 1, 3333, 101, 999991,
                "2010-01-01", "2018-01-01", "login_name_1", "first_name_1", "last_name_1",
                "111-11-5000", "ABC Company");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
