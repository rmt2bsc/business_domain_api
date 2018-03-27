package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

/**
 * Test of ProjProjectTask ORM class
 * 
 * @author roy.terrell
 *
 */
public class ProjProjectTaskOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjProjectTask o1 = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(1000, 2000, 3000, 4000);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjProjectTask o1 = new ProjProjectTask();
        ProjProjectTask o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(1000, 2000, 3000, 4000);
        o2 = new ProjProjectTask();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjectTaskId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTaskId(2000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTimesheetId(3000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjId(4000);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjProjectTask o1 = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(1000, 2000, 3000, 4000);
        ProjProjectTask o2 = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(1000, 2000, 3000, 4000);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
