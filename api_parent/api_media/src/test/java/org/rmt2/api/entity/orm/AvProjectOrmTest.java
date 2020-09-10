package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.AvProject;
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
public class AvProjectOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        AvProject o1 = AvMediaMockDataFactory.createOrmAvProject(AvMediaMockDataFactory.TEST_PROJECT_ID,
                AvMediaMockDataFactory.TEST_ARTIST_ID, AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID, AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, 2018, "/FilePath/"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        AvProject o1 = new AvProject();
        AvProject o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AvMediaMockDataFactory.createOrmAvProject(AvMediaMockDataFactory.TEST_PROJECT_ID,
                AvMediaMockDataFactory.TEST_ARTIST_ID, AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID, AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, 2018, "/FilePath/"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID);
        o2 = new AvProject();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjectId(AvMediaMockDataFactory.TEST_PROJECT_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setArtistId(AvMediaMockDataFactory.TEST_ARTIST_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setProjectTypeId(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setGenreId(AvMediaMockDataFactory.TEST_GENRE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setMediaTypeId(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setYear(2018);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTitle("Title" + AvMediaMockDataFactory.TEST_PROJECT_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setTotalTime(125);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setArtWorkPath("/FilePath/" + AvMediaMockDataFactory.TEST_PROJECT_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setArtWorkFilename("ProjectFileName" + AvMediaMockDataFactory.TEST_PROJECT_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setProjectComments("ProjectCommentsFor" + AvMediaMockDataFactory.TEST_PROJECT_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setCost(12.99);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setRipped(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setMasterDupId(AvMediaMockDataFactory.TEST_PROJECT_ID + 1000);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        AvProject o1 = AvMediaMockDataFactory.createOrmAvProject(AvMediaMockDataFactory.TEST_PROJECT_ID,
                AvMediaMockDataFactory.TEST_ARTIST_ID, AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID, AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, 2018, "/FilePath/"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID);

        AvProject o2 = AvMediaMockDataFactory.createOrmAvProject(AvMediaMockDataFactory.TEST_PROJECT_ID,
                AvMediaMockDataFactory.TEST_ARTIST_ID, AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID, AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, 2018, "/FilePath/"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
