package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

/**
 * Test of Employee Type ORM class
 * 
 * @author roy.terrell
 *
 */
public class ProjEmployeeTypeOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjEmployeeType o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeType(201, "Employee Type 1");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjEmployeeType o1 = new ProjEmployeeType();
        ProjEmployeeType o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeType(201, "Employee Type 1");
        o2 = new ProjEmployeeType();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmpTypeId(201);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("Employee Type 1");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjEmployeeType o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeType(201, "Employee Type 1");
        ProjEmployeeType o2 = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeType(201, "Employee Type 1");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
