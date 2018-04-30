package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.AvArtist;
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
public class AvArtistOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        AvArtist o1 = AvMediaMockDataFactory.createOrmAvArtist(AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        AvArtist o1 = new AvArtist();
        AvArtist o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AvMediaMockDataFactory.createOrmAvArtist(AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID);
        o2 = new AvArtist();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setArtistId(AvMediaMockDataFactory.TEST_ARTIST_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setName("Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        AvArtist o1 = AvMediaMockDataFactory.createOrmAvArtist(AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID);
        AvArtist o2 = AvMediaMockDataFactory.createOrmAvArtist(AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
