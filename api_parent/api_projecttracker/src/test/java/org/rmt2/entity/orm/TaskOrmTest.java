package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.ProjTask;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

/**
 * Test of Task ORM class
 * 
 * @author roy.terrell
 *
 */
public class TaskOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjTask o1 = ProjectTrackerMockDataFactory.createMockOrmProjTask(1000, "TaskDescription", true);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjTask o1 = new ProjTask();
        ProjTask o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjTask(1000, "TaskDescription", true);
        o2 = new ProjTask();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTaskId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("TaskDescription");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBillable(1);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjTask o1 = ProjectTrackerMockDataFactory.createMockOrmProjTask(1000, "TaskDescription", true);;
        ProjTask o2 = ProjectTrackerMockDataFactory.createMockOrmProjTask(1000, "TaskDescription", true);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
