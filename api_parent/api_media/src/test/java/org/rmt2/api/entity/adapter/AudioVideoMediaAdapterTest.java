package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvMediaType;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvProjectType;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dto.ArtistDto;
import org.dto.GenreDto;
import org.dto.MediaTypeDto;
import org.dto.ProjectDto;
import org.dto.ProjectTypeDto;
import org.dto.TracksDto;
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

        Assert.assertEquals(AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getId());
        Assert.assertEquals("Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getName());

        try {
            AvArtist nullParm = null;
            dto = Rmt2MediaDtoFactory.getAvArtistInstance(nullParm);
            dto.setId(AvMediaMockDataFactory.TEST_ARTIST_ID);
            dto.setName("Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID);

            Assert.assertEquals(AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getId());
            Assert.assertEquals("Artist" + AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getName());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for Artist Adapater");
        }
    }

    @Test
    public void testOrmProject() {
        AvProject o1 = AvMediaMockDataFactory.createOrmAvProject(AvMediaMockDataFactory.TEST_PROJECT_ID,
                AvMediaMockDataFactory.TEST_ARTIST_ID, AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID, AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, 2018, "/FilePath/"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_PROJECT_ID);
        ProjectDto dto = Rmt2MediaDtoFactory.getAvProjectInstance(o1);

        Assert.assertEquals(AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getArtistId());
        Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getProjectId());
        Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID, dto.getProjectTypeId());
        Assert.assertEquals(AvMediaMockDataFactory.TEST_GENRE_ID, dto.getGenreId());
        Assert.assertEquals(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, dto.getMediaTypeId());
        Assert.assertEquals("Title" + AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getTitle());
        Assert.assertEquals("producer_name", dto.getProducer());
        Assert.assertEquals(125, dto.getTotalTime());
        Assert.assertEquals(2018, dto.getYear());
        Assert.assertEquals("/FilePath/" + AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getArtWorkPath());
        Assert.assertEquals("ProjectFileName" + AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getArtWorkFilename());
        Assert.assertEquals(12.99, dto.getCost(), 0);
        Assert.assertEquals("ProjectCommentsFor" + AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getComments());
        Assert.assertEquals(1, dto.getRippedInd());
        Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECT_ID + 1000, dto.getMasterDupId());

        try {
            AvProject nullParm = null;
            dto = Rmt2MediaDtoFactory.getAvProjectInstance(nullParm);
            dto.setProjectId(AvMediaMockDataFactory.TEST_PROJECT_ID);
            dto.setArtistId(AvMediaMockDataFactory.TEST_ARTIST_ID);
            dto.setProjectTypeId(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID);
            dto.setGenreId(AvMediaMockDataFactory.TEST_GENRE_ID);
            dto.setMediaTypeId(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID);
            dto.setTitle("Title" + AvMediaMockDataFactory.TEST_PROJECT_ID);
            dto.setYear(2018);
            dto.setArtWorkPath("/FilePath/" + AvMediaMockDataFactory.TEST_PROJECT_ID);
            dto.setArtWorkFilename("ProjectFileName" + AvMediaMockDataFactory.TEST_PROJECT_ID);
            dto.setCost(12.99);
            dto.setComments("ProjectCommentsFor" + AvMediaMockDataFactory.TEST_PROJECT_ID);
            dto.setRippedInd(1);
            dto.setMasterDupId(AvMediaMockDataFactory.TEST_PROJECT_ID + 1000);
            dto.setProducer("producer_name");
            dto.setTotalTime(125);

            Assert.assertEquals(AvMediaMockDataFactory.TEST_ARTIST_ID, dto.getArtistId());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getProjectId());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECTTYPE_ID, dto.getProjectTypeId());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_GENRE_ID, dto.getGenreId());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, dto.getMediaTypeId());
            Assert.assertEquals("Title" + AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getTitle());
            Assert.assertEquals(2018, dto.getYear());
            Assert.assertEquals("/FilePath/" + AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getArtWorkPath());
            Assert.assertEquals("ProjectFileName" + AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getArtWorkFilename());
            Assert.assertEquals(12.99, dto.getCost(), 0);
            Assert.assertEquals("ProjectCommentsFor" + AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getComments());
            Assert.assertEquals(1, dto.getRippedInd());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECT_ID + 1000, dto.getMasterDupId());
            Assert.assertEquals("producer_name", dto.getProducer());
            Assert.assertEquals(125, dto.getTotalTime());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for AV Project Adapater");
        }
    }

    @Test
    public void testOrmTrack() {
        int ndx = AvMediaMockDataFactory.TEST_TRACK_ID;
        int trackNo = 1;
        AvTracks o1 = AvMediaMockDataFactory.createOrmAvTracks(AvMediaMockDataFactory.TEST_TRACK_ID,
                AvMediaMockDataFactory.TEST_PROJECT_ID, trackNo, "Track" + trackNo, 5, 30, 00, "1", "/FilePath/"
                        + AvMediaMockDataFactory.TEST_TRACK_ID, "ProjectFileName"
                        + AvMediaMockDataFactory.TEST_TRACK_ID);
        TracksDto dto = Rmt2MediaDtoFactory.getAvTrackInstance(o1);

        Assert.assertEquals(AvMediaMockDataFactory.TEST_TRACK_ID, dto.getTrackId());
        Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getProjectId());
        Assert.assertEquals(AvMediaMockDataFactory.TEST_GENRE_ID, dto.getGenreId());
        Assert.assertEquals(trackNo, dto.getTrackNumber());
        Assert.assertEquals(5, dto.getTrackHours());
        Assert.assertEquals(30, dto.getTrackMinutes());
        Assert.assertEquals(0, dto.getTrackSeconds());
        Assert.assertEquals("1", dto.getTrackDisc());
        Assert.assertEquals("/FilePath/" + AvMediaMockDataFactory.TEST_TRACK_ID, dto.getLocPath());
        Assert.assertEquals("ProjectFileName" + AvMediaMockDataFactory.TEST_TRACK_ID, dto.getLocFilename());
        Assert.assertEquals("ServerName", dto.getLocServername());
        Assert.assertEquals("www.rmt2.net", dto.getLocRootPath());
        Assert.assertEquals("multimedia", dto.getLocSharename());
        Assert.assertEquals("john smith", dto.getTrackComposer());
        Assert.assertEquals("john smith", dto.getTrackProducer());
        Assert.assertEquals("john smith", dto.getTrackLyricist());

        try {
            AvTracks nullParm = null;
            dto = Rmt2MediaDtoFactory.getAvTrackInstance(nullParm);
            dto.setTrackId(AvMediaMockDataFactory.TEST_TRACK_ID);
            dto.setProjectId(AvMediaMockDataFactory.TEST_PROJECT_ID);
            dto.setGenreId(AvMediaMockDataFactory.TEST_GENRE_ID);
            dto.setTrackNumber(trackNo);
            dto.setTrackHours(5);
            dto.setTrackMinutes(30);
            dto.setTrackSeconds(0);
            dto.setTrackDisc("1");
            dto.setLocPath("/FilePath/" + AvMediaMockDataFactory.TEST_TRACK_ID);
            dto.setLocFilename("ProjectFileName" + AvMediaMockDataFactory.TEST_TRACK_ID);
            dto.setLocServername("ServerName");
            dto.setLocRootPath("www.rmt2.net");
            dto.setLocSharename("multimedia");
            dto.setComments("CommentsFor" + AvMediaMockDataFactory.TEST_TRACK_ID);
            dto.setTrackComposer("john smith");
            dto.setTrackLyricist("john smith");
            dto.setTrackProducer("john smith");

            Assert.assertEquals(AvMediaMockDataFactory.TEST_TRACK_ID, dto.getTrackId());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECT_ID, dto.getProjectId());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_GENRE_ID, dto.getGenreId());
            Assert.assertEquals(trackNo, dto.getTrackNumber());
            Assert.assertEquals(5, dto.getTrackHours());
            Assert.assertEquals(30, dto.getTrackMinutes());
            Assert.assertEquals(0, dto.getTrackSeconds());
            Assert.assertEquals("1", dto.getTrackDisc());
            Assert.assertEquals("/FilePath/" + AvMediaMockDataFactory.TEST_TRACK_ID, dto.getLocPath());
            Assert.assertEquals("ProjectFileName" + AvMediaMockDataFactory.TEST_TRACK_ID, dto.getLocFilename());
            Assert.assertEquals("ServerName", dto.getLocServername());
            Assert.assertEquals("www.rmt2.net", dto.getLocRootPath());
            Assert.assertEquals("multimedia", dto.getLocSharename());
            Assert.assertEquals("john smith", dto.getTrackComposer());
            Assert.assertEquals("john smith", dto.getTrackProducer());
            Assert.assertEquals("john smith", dto.getTrackLyricist());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for AV Tracks Adapater");
        }
    }
}
