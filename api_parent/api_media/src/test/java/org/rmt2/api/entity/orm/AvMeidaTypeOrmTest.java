package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.AvMediaType;
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
public class AvMeidaTypeOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        AvMediaType o1 = AvMediaMockDataFactory.createOrmAvMediaType(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID,
                "MediaType" + AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        AvMediaType o1 = new AvMediaType();
        AvMediaType o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AvMediaMockDataFactory.createOrmAvMediaType(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID,
                "MediaType" + AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
        o2 = new AvMediaType();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setMediaTypeId(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("MediaType" + AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        AvMediaType o1 = AvMediaMockDataFactory.createOrmAvMediaType(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID,
                "MediaType" + AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
        AvMediaType o2 = AvMediaMockDataFactory.createOrmAvMediaType(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID,
                "MediaType" + AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
