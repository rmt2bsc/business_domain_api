package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

/**
 * Test of Project ORM class
 * 
 * @author roy.terrell
 *
 */
public class ProjEmployeeTitleOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjEmployeeTitle o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjEmployeeTitle o1 = new ProjEmployeeTitle();
        ProjEmployeeTitle o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        o2 = new ProjEmployeeTitle();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmpTitleId(101);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("Employee Title 1");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjEmployeeTitle o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        ProjEmployeeTitle o2 = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
