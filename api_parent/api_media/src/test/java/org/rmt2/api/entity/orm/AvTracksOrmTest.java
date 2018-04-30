package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.AvTracks;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.audiovideo.AvMediaMockDataFactory;

/**
 * Test of Content ORM class
 * 
 * @author roy.terrell
 *
 */
public class AvTracksOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        AvTracks o1 = AvMediaMockDataFactory.createOrmAvTracks(AvMediaMockDataFactory.TEST_TRACK_ID,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 1, "Track" + 1, 5, 30, 00, "1", "/FilePath/"
                        + AvMediaMockDataFactory.TEST_TRACK_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_TRACK_ID);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        AvTracks o1 = new AvTracks();
        AvTracks o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AvMediaMockDataFactory.createOrmAvTracks(AvMediaMockDataFactory.TEST_TRACK_ID,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 1, "Track" + 1, 5, 30, 00, "1", "/FilePath/"
                        + AvMediaMockDataFactory.TEST_TRACK_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_TRACK_ID);
        o2 = new AvTracks();
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackId(AvMediaMockDataFactory.TEST_TRACK_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjectId(AvMediaMockDataFactory.TEST_PROJECT_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackNumber(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackTitle("Track" + 1);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackHours(5);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTrackMinutes(30);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTrackSeconds(00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTrackDisc("1");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLocPath("/FilePath/" + AvMediaMockDataFactory.TEST_TRACK_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setLocFilename("ProjectFileName" + AvMediaMockDataFactory.TEST_TRACK_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setLocServername("ServerName");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setLocRootPath("www.rmt2.net");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLocSharename("multimedia");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setComments("CommentsFor" + AvMediaMockDataFactory.TEST_TRACK_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTrackComposer("john smith");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTrackLyricist("john smith");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTrackProducer("john smith");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        AvTracks o1 = AvMediaMockDataFactory.createOrmAvTracks(AvMediaMockDataFactory.TEST_TRACK_ID,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 1, "Track" + 1, 5, 30, 00, "1", "/FilePath/"
                        + AvMediaMockDataFactory.TEST_TRACK_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_TRACK_ID);

        AvTracks o2 = AvMediaMockDataFactory.createOrmAvTracks(AvMediaMockDataFactory.TEST_TRACK_ID,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 1, "Track" + 1, 5, 30, 00, "1", "/FilePath/"
                        + AvMediaMockDataFactory.TEST_TRACK_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_TRACK_ID);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
