package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.ProjProject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.util.RMT2Date;

/**
 * Test of Project ORM class
 * 
 * @author roy.terrell
 *
 */
public class ProjectOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjProject o1 = ProjectTrackerMockDataFactory.createMockOrmProjProject(
                1000, 2000, "ProjectDescription", "2018-01-01", "2018-02-02");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjProject o1 = new ProjProject();
        ProjProject o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjProject(
                1000, 2000, "ProjectDescription", "2018-01-01", "2018-02-02");
        o2 = new ProjProject();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setClientId(2000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("ProjectDescription");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEndDate(RMT2Date.stringToDate("2018-02-02"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEffectiveDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjProject o1 = ProjectTrackerMockDataFactory.createMockOrmProjProject(
                1000, 2000, "ProjectDescription", "2018-01-01", "2018-02-02");
        ProjProject o2 = ProjectTrackerMockDataFactory.createMockOrmProjProject(
                1000, 2000, "ProjectDescription", "2018-01-01", "2018-02-02");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
