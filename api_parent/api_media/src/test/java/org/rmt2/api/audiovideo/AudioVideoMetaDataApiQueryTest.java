package org.rmt2.api.audiovideo;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dao.audiovideo.AudioVideoDaoException;
import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dto.ArtistDto;
import org.dto.ProjectDto;
import org.dto.TracksDto;
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
import com.util.RMT2File;

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
        when(this.mockPersistenceClient.retrieveList(isA(AvArtist.class)))
                .thenReturn(this.mockAvArtistData);
        when(this.mockPersistenceClient.retrieveList(isA(AvProject.class)))
                .thenReturn(this.mockAvProjectData);
        when(this.mockPersistenceClient.retrieveList(isA(AvTracks.class)))
                .thenReturn(this.mockAvTracksData);
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
}