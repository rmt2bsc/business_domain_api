package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.ProjEvent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.util.RMT2Date;

/**
 * Test of ProjEvent ORM class
 * 
 * @author roy.terrell
 *
 */
public class ProjEventOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjEvent o1 =  ProjectTrackerMockDataFactory.createMockOrmProjEvent(123401, 444441, "2018-01-01", 8);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjEvent o1 = new ProjEvent();
        ProjEvent o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjEvent(123401, 444441, "2018-01-01", 8);
        o2 = new ProjEvent();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjectTaskId(444441);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEventId(123401);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEventDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setHours(8);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjEvent o1 = ProjectTrackerMockDataFactory.createMockOrmProjEvent(123401, 444441, "2018-01-01", 8);
        ProjEvent o2 = ProjectTrackerMockDataFactory.createMockOrmProjEvent(123401, 444441, "2018-01-01", 8);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
