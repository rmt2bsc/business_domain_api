package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.AvProjectType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.audiovideo.AvMediaMockDataFactory;

/**
 * Test of AvProjectType ORM class
 * 
 * @author roy.terrell
 *
 */
public class AvProjectTypeOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        AvProjectType o1 = AvMediaMockDataFactory.createOrmAvProjectType(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                "ProjectType" + AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        AvProjectType o1 = new AvProjectType();
        AvProjectType o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AvMediaMockDataFactory.createOrmAvProjectType(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                "ProjectType" + AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
        o2 = new AvProjectType();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjectTypeId(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("ProjectType" + AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        AvProjectType o1 = AvMediaMockDataFactory.createOrmAvProjectType(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                "ProjectType" + AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
        AvProjectType o2 = AvMediaMockDataFactory.createOrmAvProjectType(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                "ProjectType" + AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
