package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.VwAudioVideoArtists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.audiovideo.AvMediaMockDataFactory;

/**
 * Test of VwAudioVideoArtists ORM class
 * 
 * @author roy.terrell
 *
 */
public class VwAudioVideoArtistOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwAudioVideoArtists o1 = AvMediaMockDataFactory.createOrmVwAudioVideoArtists(AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID, AvMediaMockDataFactory.TEST_PROJECT_ID, "Project Name",
                AvMediaMockDataFactory.TEST_TRACK_ID, "Track Name", true, 1234);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwAudioVideoArtists o1 = new VwAudioVideoArtists();
        VwAudioVideoArtists o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AvMediaMockDataFactory.createOrmVwAudioVideoArtists(AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID, AvMediaMockDataFactory.TEST_PROJECT_ID, "Project Name",
                AvMediaMockDataFactory.TEST_TRACK_ID, "Track Name", true, 1);

        o2 = new VwAudioVideoArtists();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setArtistId(AvMediaMockDataFactory.TEST_ARTIST_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setArtist("Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setProjectId(AvMediaMockDataFactory.TEST_PROJECT_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setProjectId(AvMediaMockDataFactory.TEST_PROJECT_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setProjectTitle("Project Name");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackId(AvMediaMockDataFactory.TEST_TRACK_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackDiscNumber("1");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackNumber(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackTitle("Track Name");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setPrimaryArtist(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setProjectTypeId(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setProjectComments("Project Comments");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackComments("Track Comments");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setGenreId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setContentId(200);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setMediaTypeId(3);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setYear(1999);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setMasterDupId(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setRipped(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setCost(9.99);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setContentPath("//servername/directory_path/");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setContentFilename("mediafile.mp3");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setArtWorkPath("//servername/directory_path/");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setArtWorkFilename("artworkfile.jpg");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTotalTime(40);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setProducer("Producer Name");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackHours(0);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackMinutes(5);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTrackSeconds(45);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setProjectTypeName("Audio");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwAudioVideoArtists o1 = AvMediaMockDataFactory.createOrmVwAudioVideoArtists(AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID, AvMediaMockDataFactory.TEST_PROJECT_ID, "Project Name",
                AvMediaMockDataFactory.TEST_TRACK_ID, "Track Name", true, 1);
        VwAudioVideoArtists o2 = AvMediaMockDataFactory.createOrmVwAudioVideoArtists(AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID, AvMediaMockDataFactory.TEST_PROJECT_ID, "Project Name",
                AvMediaMockDataFactory.TEST_TRACK_ID, "Track Name", true, 1);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
