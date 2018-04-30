package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.AvGenre;
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
public class AvGenreOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        AvGenre o1 = AvMediaMockDataFactory.createOrmAvGenre(AvMediaMockDataFactory.TEST_GENRE_ID,
                "Genre" + AvMediaMockDataFactory.TEST_GENRE_ID);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        AvGenre o1 = new AvGenre();
        AvGenre o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AvMediaMockDataFactory.createOrmAvGenre(AvMediaMockDataFactory.TEST_GENRE_ID,
                "Genre" + AvMediaMockDataFactory.TEST_GENRE_ID);
        o2 = new AvGenre();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setGenreId(AvMediaMockDataFactory.TEST_GENRE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("Genre" + AvMediaMockDataFactory.TEST_GENRE_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        AvGenre o1 = AvMediaMockDataFactory.createOrmAvGenre(AvMediaMockDataFactory.TEST_GENRE_ID,
                "Genre" + AvMediaMockDataFactory.TEST_GENRE_ID);
        AvGenre o2 = AvMediaMockDataFactory.createOrmAvGenre(AvMediaMockDataFactory.TEST_GENRE_ID,
                "Genre" + AvMediaMockDataFactory.TEST_GENRE_ID);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
