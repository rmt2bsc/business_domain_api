package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.MimeTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.document.DocumentMediaMockDataFactory;

/**
 * Test of MimeTypes ORM class
 * 
 * @author roy.terrell
 *
 */
public class MimeTypeOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        MimeTypes o1 = DocumentMediaMockDataFactory.createOrmMimeTypes(101, ".jpg", "image/jpg");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        MimeTypes o1 = new MimeTypes();
        MimeTypes o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = DocumentMediaMockDataFactory.createOrmMimeTypes(101, ".jpg", "image/jpg");
        o2 = new MimeTypes();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setMimeTypeId(101);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setFileExt(".jpg");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setMediaType("image/jpg");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        MimeTypes o1 = DocumentMediaMockDataFactory.createOrmMimeTypes(101, ".jpg", "image/jpg");
        MimeTypes o2 = DocumentMediaMockDataFactory.createOrmMimeTypes(101, ".jpg", "image/jpg");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
