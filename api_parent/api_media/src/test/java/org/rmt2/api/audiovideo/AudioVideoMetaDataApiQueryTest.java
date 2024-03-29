package org.rmt2.api.audiovideo;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dao.audiovideo.AudioVideoDaoException;
import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvMediaType;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvProjectType;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dao.mapping.orm.rmt2.VwAudioVideoArtists;
import org.dto.ArtistDto;
import org.dto.GenreDto;
import org.dto.MediaTypeDto;
import org.dto.ProjectDto;
import org.dto.ProjectTypeDto;
import org.dto.TracksDto;
import org.dto.VwArtistDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.MediaConstants;
import org.modules.audiovideo.AudioVideoApi;
import org.modules.audiovideo.AudioVideoApiException;
import org.modules.audiovideo.AudioVideoFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2File;

/**
 * Test the audio/video meta data query functionality in the Media API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, RMT2File.class })
public class AudioVideoMetaDataApiQueryTest extends AvMediaMockData {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        when(this.mockPersistenceClient.retrieveList(isA(AvGenre.class)))
                .thenReturn(this.mockAvGenreData);
        when(this.mockPersistenceClient.retrieveList(isA(AvProjectType.class)))
                .thenReturn(this.mockAvProjectTypeData);
        when(this.mockPersistenceClient.retrieveList(isA(AvMediaType.class)))
                .thenReturn(this.mockAvMediaTypeData);
        when(this.mockPersistenceClient.retrieveList(isA(AvArtist.class)))
                .thenReturn(this.mockAvArtistData);
        when(this.mockPersistenceClient.retrieveList(isA(AvProject.class)))
                .thenReturn(this.mockAvProjectData);
        when(this.mockPersistenceClient.retrieveList(isA(AvTracks.class)))
                .thenReturn(this.mockAvTracksData);
        when(this.mockPersistenceClient.retrieveList(isA(VwAudioVideoArtists.class)))
                .thenReturn(this.mockVwAudioVideoArtistsData);
    }
    
    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    @Test
    public void testSuccess_Fetch_Genre() {
        GenreDto criteria = Rmt2MediaDtoFactory.getAvGenreInstance(null);
        List<GenreDto> results = null;
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            results = api.getGenre(criteria);
        } catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }

        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            int key = (AvMediaMockDataFactory.TEST_GENRE_ID + ndx);
            Assert.assertEquals(key, results.get(ndx).getUid());
            String name = "Genre" + key;
            Assert.assertEquals(name, results.get(ndx).getDescritpion());
        }
    }

    @Test
    public void testSuccess_Fetch_ProjectType() {
        ProjectTypeDto criteria = Rmt2MediaDtoFactory.getAvProjectTypeInstance(null);
        List<ProjectTypeDto> results = null;
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            results = api.getProjectType(criteria);
        } catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }

        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            int key = (AvMediaMockDataFactory.TEST_PROJECTTYPE_ID + ndx);
            Assert.assertEquals(key, results.get(ndx).getUid());
            String name = "ProjectType" + key;
            Assert.assertEquals(name, results.get(ndx).getDescritpion());
        }
    }

    @Test
    public void testSuccess_Fetch_MediaType() {
        MediaTypeDto criteria = Rmt2MediaDtoFactory.getAvMediaTypeInstance(null);
        List<MediaTypeDto> results = null;
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            results = api.getMediaType(criteria);
        } catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }

        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            int key = (AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID + ndx);
            Assert.assertEquals(key, results.get(ndx).getUid());
            String name = "MediaType" + key;
            Assert.assertEquals(name, results.get(ndx).getDescritpion());
        }
    }

    @Test
    public void testSuccess_Artist_Fetch() {
        AudioVideoApi api = null;
        ArtistDto criteria = Rmt2MediaDtoFactory.getAvArtistInstance(null);
        List<ArtistDto> results = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            results = api.getArtist(criteria);
        }
        catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }
    
    @Test
    public void testError_Artist_Fetch_DB_Access_Fault() {
        String errMsg = "A Database error occurred retrieving artists data";
        when(this.mockPersistenceClient.retrieveList(isA(AvArtist.class)))
               .thenThrow(new DatabaseException(errMsg));
        
        AudioVideoApi api = null;
        ArtistDto criteria = Rmt2MediaDtoFactory.getAvArtistInstance(null);
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.getArtist(criteria);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testValidation_Artist_Fetch_Null_Data_Object() {
        String errMsg = "Artist criteria object is required";
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.getArtist(null);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    
    @Test
    public void testSuccess_Album_Fetch() {
        AudioVideoApi api = null;
        ProjectDto criteria = Rmt2MediaDtoFactory.getAvProjectInstance(null);
        List<ProjectDto> results = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            results = api.getProject(criteria);
        }
        catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }
    
    @Test
    public void testError_Album_Fetch_DB_Access_Fault() {
        String errMsg = "A Database error occurred retrieving project/album data";
        when(this.mockPersistenceClient.retrieveList(isA(AvProject.class)))
               .thenThrow(new DatabaseException(errMsg));
        
        AudioVideoApi api = null;
        ProjectDto criteria = Rmt2MediaDtoFactory.getAvProjectInstance(null);
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.getProject(criteria);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testValidation_Album_Fetch_Null_Data_Object() {
        String errMsg = "Project/Album criteria object is required";
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.getProject(null);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Tracks_Fetch() {
        AudioVideoApi api = null;
        TracksDto criteria = Rmt2MediaDtoFactory.getAvTrackInstance(null);
        List<TracksDto> results = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            results = api.getTracks(criteria);
        }
        catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }
    
    @Test
    public void testError_Track_Fetch_DB_Access_Fault() {
        String errMsg = "A Database error occurred retrieving project track(s) data";
        when(this.mockPersistenceClient.retrieveList(isA(AvTracks.class)))
               .thenThrow(new DatabaseException(errMsg));
        
        AudioVideoApi api = null;
        TracksDto criteria = Rmt2MediaDtoFactory.getAvTrackInstance(null);
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.getTracks(criteria);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testValidation_Track_Fetch_Null_Data_Object() {
        String errMsg = "Track criteria object is required";
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.getTracks(null);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }

    @Test
    public void testSuccess_Consolidated_Artist_Fetch() {
        AudioVideoApi api = null;
        VwArtistDto criteria = Rmt2MediaDtoFactory.getVwAudioVideoArtistsInstance(null);
        List<VwArtistDto> results = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            results = api.getConsolidatedArtist(criteria);
        } catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }

        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());

        int ndx = 0;
        for (VwArtistDto item : results) {
            Assert.assertTrue(item.isPrimaryArtist());
            Assert.assertEquals(1, item.getProjectTypeId());
            Assert.assertEquals("Audio", item.getProjectTypeName());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_ARTIST_ID + ndx, item.getArtistId());
            Assert.assertEquals("Artist" + (AvMediaMockDataFactory.TEST_ARTIST_ID + ndx), item.getArtistName());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECT_ID + ndx, item.getProjectId());
            Assert.assertEquals("Project Name" + ndx, item.getProjectName());
            Assert.assertEquals(AvMediaMockDataFactory.TEST_TRACK_ID + ndx, item.getTrackId());
            Assert.assertEquals("Track Name" + ndx, item.getTrackName());
            Assert.assertEquals("Track Comments", item.getTrackComments());
            Assert.assertEquals("Project Comments", item.getProjectComments());
            ndx++;
        }
    }

    @Test
    public void testError_Consolidated_Artist_Fetch_DB_Access_Fault() {
        String errMsg = "A Database error occurred retrieving consolidated artists data";
        when(this.mockPersistenceClient.retrieveList(isA(VwAudioVideoArtists.class)))
                .thenThrow(new DatabaseException(errMsg));

        AudioVideoApi api = null;
        VwArtistDto criteria = Rmt2MediaDtoFactory.getVwAudioVideoArtistsInstance(null);
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.getConsolidatedArtist(criteria);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getMessage());
        }
    }

    @Test
    public void testValidation_Consolidated_Artist_Fetch_Null_Data_Object() {
        String errMsg = "Consolidated artist criteria object is required";
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.getConsolidatedArtist(null);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
}