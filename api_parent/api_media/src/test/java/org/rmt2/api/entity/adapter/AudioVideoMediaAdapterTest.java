package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvMediaType;
import org.dao.mapping.orm.rmt2.AvProjectType;
import org.dto.ArtistDto;
import org.dto.GenreDto;
import org.dto.MediaTypeDto;
import org.dto.ProjectTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.audiovideo.AvMediaMockDataFactory;

/**
 * Test adapters pertaining to the Audio/Video module.
 * 
 * @author roy.terrell
 *
 */
public class AudioVideoMediaAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrmGenre() {
        AvGenre o1 = AvMediaMockDataFactory.createOrmAvGenre(AvMediaMockDataFactory.TEST_GENRE_ID,
                "Genre" + AvMediaMockDataFactory.TEST_GENRE_ID);
        GenreDto dto = Rmt2MediaDtoFactory.getAvGenreInstance(o1);
        
        Assert.assertEquals(AvMediaMockDataFactory.TEST_GENRE_ID, dto.getUid());
        Assert.assertEquals("Genre" + AvMediaMockDataFactory.TEST_GENRE_ID, dto.getDescritpion());
        
        try {
            AvGenre nullParm = null;
            dto = Rmt2MediaDtoFactory.getAvGenreInstance(nullParm);
            dto.setUid(AvMediaMockDataFactory.TEST_GENRE_ID);
            dto.setDescription("Genre" + AvMediaMockDataFactory.TEST_GENRE_ID);

            Assert.assertEquals(AvMediaMockDataFactory.TEST_GENRE_ID, dto.getUid());
            Assert.assertEquals("Genre" + AvMediaMockDataFactory.TEST_GENRE_ID, dto.getDescritpion());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for Genre Adapater");
        }
    }

    @Test
    public void testOrmMediaType() {
        AvMediaType o1 = AvMediaMockDataFactory.createOrmAvMediaType(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID,
                "MediaType" + AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
        MediaTypeDto dto = Rmt2MediaDtoFactory.getAvMediaTypeInstance(o1);

        Assert.assertEquals(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, dto.getUid());
        Assert.assertEquals("MediaType" + AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, dto.getDescritpion());

        try {
            AvMediaType nullParm = null;
            dto = Rmt2MediaDtoFactory.getAvMediaTypeInstance(nullParm);
            dto.setUid(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
            dto.setDescription("MediaType" + AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);

            Assert.assertEquals(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, dto.getUid());
            Assert.assertEquals("MediaType" + AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, dto.getDescritpion());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for Media Type Adapater");
        }
    }

    @Test
    public void testOrmProjectType() {
        AvProjectType o1 = AvMediaMockDataFactory.createOrmAvProjectType(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                "ProjectType" + AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
        ProjectTypeDto dto = Rmt2MediaDtoFactory.getAvProjectTypeInstance(o1);

        Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID, dto.getUid());
        Assert.assertEquals("ProjectType" + AvMediaMockDataFactory.TEST_PROJECTTYPE_ID, dto.getDescritpion());

        try {
            AvProjectType nullParm = null;
            dto = Rmt2MediaDtoFactory.getAvProjectTypeInstance(nullParm);
            dto.setUid(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
            dto.setDescription("ProjectType" + AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);

            Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID, dto.getUid());
            Assert.assertEquals("ProjectType" + AvMediaMockDataFactory.TEST_PROJECTTYPE_ID, dto.getDescritpion());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for Project Type Adapater");
        }
    }

    @Test
    public void testOrmArtist() {
        AvArtist o1 = AvMediaMockDataFactory.createOrmAvArtist(AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID);
        ArtistDto dto = Rmt2MediaDtoFactory.getAvArtistInstance(o1);

        Assert.assertEquals(AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getUid());
        Assert.assertEquals("ProjectType" + AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getDescritpion());

        try {
            AvArtist nullParm = null;
            dto = Rmt2MediaDtoFactory.getAvArtistInstance(nullParm);
            dto.setUid(AvMediaMockDataFactory.TEST_ARTIST_ID);
            dto.setDescription("Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID);

            Assert.assertEquals(AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getUid());
            Assert.assertEquals("Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getDescritpion());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for Artist Adapater");
        }
    }
}
