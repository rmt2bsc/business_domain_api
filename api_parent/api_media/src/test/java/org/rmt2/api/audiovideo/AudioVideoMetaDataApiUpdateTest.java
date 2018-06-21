package org.rmt2.api.audiovideo;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.audiovideo.AudioVideoDaoException;
import org.dao.mapping.orm.rmt2.AvArtist;
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
import com.api.util.RMT2File;

/**
 * Test the audio/video meta data update functionality in the Media API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, RMT2File.class })
public class AudioVideoMetaDataApiUpdateTest extends AvMediaMockData {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
       
        // Mock stubs for inserts
        when(this.mockPersistenceClient.insertRow(isA(AvArtist.class), eq(true)))
                .thenReturn(AvMediaMockDataFactory.TEST_NEW_ARTIST_ID);
        when(this.mockPersistenceClient.insertRow(isA(AvProject.class), eq(true)))
                .thenReturn(AvMediaMockDataFactory.TEST_NEW_PROJECT_ID);
        when(this.mockPersistenceClient.insertRow(isA(AvTracks.class), eq(true)))
                .thenReturn(AvMediaMockDataFactory.TEST_NEW_TRACK_ID);
        
        // Mock stubs for updates
        when(this.mockPersistenceClient.updateRow(isA(AvArtist.class)))
                .thenReturn(AvMediaMockDataFactory.TEST_UPDATE_RC);
        when(this.mockPersistenceClient.updateRow(isA(AvProject.class)))
                .thenReturn(AvMediaMockDataFactory.TEST_UPDATE_RC);
        when(this.mockPersistenceClient.updateRow(isA(AvTracks.class)))
                .thenReturn(AvMediaMockDataFactory.TEST_UPDATE_RC);
    }

    private List<AvArtist> setupMockDataSingleArtist(int artistId, String name) {
        List<AvArtist> list = new ArrayList<>();
        AvArtist o = AvMediaMockDataFactory.createOrmAvArtist(artistId, name);
        list.add(o);
        return list;
    }
    
    private List<AvProject> setupMockDataSingleProject(int projId, int artistId, String projName) {
            List<AvProject> list = new ArrayList<>();
        AvProject o = AvMediaMockDataFactory.createOrmAvProject(projId,
                artistId, AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID,
                AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, projName, 2018,
                "/FilePath/" + projId, "ProjectFileName" + projId);
            list.add(o);    
            return list;
    }
    
    private List<AvTracks> setupMockDataSingleTrack(int trackId, int projId, int trackNo, String title) {
        List<AvTracks> list = new ArrayList<>();
        AvTracks o = AvMediaMockDataFactory.createOrmAvTracks(trackId, projId,
                trackNo, title, 5, 30, 00, "1", "/FilePath/" + trackId,
                "ProjectFileName" + trackId);
        list.add(o);
        return list;
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
    public void testSuccess_Artist_Create() {
        List<AvArtist> list = this.setupMockDataSingleArtist(0, "New Artist");
        AudioVideoApi api = null;
        ArtistDto obj = Rmt2MediaDtoFactory.getAvArtistInstance(list.get(0));
        int rc = 0;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            rc = api.updateArtist(obj);
        }
        catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_NEW_ARTIST_ID, rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_NEW_ARTIST_ID, obj.getId());
    }
    
    @Test
    public void testError_Artist_Create_DB_Access_Fault() {
        String errMsg = "A Database error occurred creating artist";
        when(this.mockPersistenceClient.insertRow(isA(AvArtist.class), eq(true)))
               .thenThrow(new DatabaseException(errMsg));
        
        List<AvArtist> list = this.setupMockDataSingleArtist(0, "New Artist");
        AudioVideoApi api = null;
        ArtistDto obj = Rmt2MediaDtoFactory.getAvArtistInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateArtist(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testSuccess_Artist_Update() {
        List<AvArtist> list = this.setupMockDataSingleArtist(
                AvMediaMockDataFactory.TEST_ARTIST_ID, "Existing Artist");
        AudioVideoApi api = null;
        ArtistDto obj = Rmt2MediaDtoFactory.getAvArtistInstance(list.get(0));
        int rc = 0;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            rc = api.updateArtist(obj);
        }
        catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_UPDATE_RC, rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_ARTIST_ID, obj.getId());
    }
    
    @Test
    public void testError_Artist_Update_DB_Access_Fault() {
        String errMsg = "A Database error occurred updating artist";
        when(this.mockPersistenceClient.updateRow(isA(AvArtist.class)))
               .thenThrow(new DatabaseException(errMsg));
        
        List<AvArtist> list = this.setupMockDataSingleArtist(AvMediaMockDataFactory.TEST_ARTIST_ID, "Existing Artist");
        AudioVideoApi api = null;
        ArtistDto obj = Rmt2MediaDtoFactory.getAvArtistInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateArtist(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Artist_Delete() {
        List<AvArtist> list = this.setupMockDataSingleArtist(AvMediaMockDataFactory.TEST_ARTIST_ID, "Exisitng Artist");
        AudioVideoApi api = null;
        ArtistDto obj = Rmt2MediaDtoFactory.getAvArtistInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.deleteArtist(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    
    @Test
    public void testValidation_Artist_With_Null_Data_Object() {
        String errMsg = "Artist criteria object is required";
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateArtist(null);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Artist_With_Null_Name() {
        String errMsg = "Artist name is required";
        List<AvArtist> list = this.setupMockDataSingleArtist(0, null);
        AudioVideoApi api = null;
        ArtistDto obj = Rmt2MediaDtoFactory.getAvArtistInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateArtist(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Artist_With_Empty_Name() {
        String errMsg = "Artist name is required";
        List<AvArtist> list = this.setupMockDataSingleArtist(0, "");
        AudioVideoApi api = null;
        ArtistDto obj = Rmt2MediaDtoFactory.getAvArtistInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateArtist(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Album_Create() {
        List<AvProject> list = this.setupMockDataSingleProject(0,
                AvMediaMockDataFactory.TEST_ARTIST_ID, "New Project/Album");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        int rc = 0;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            rc = api.updateProject(obj);
        }
        catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_NEW_PROJECT_ID, rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_NEW_PROJECT_ID, obj.getProjectId());
    }
    
    @Test
    public void testError_Album_Create_DB_Access_Fault() {
        String errMsg = "A Database error occurred creating project/album";
        when(this.mockPersistenceClient.insertRow(isA(AvProject.class), eq(true)))
               .thenThrow(new DatabaseException(errMsg));
        
        List <AvProject> list = this.setupMockDataSingleProject(0, AvMediaMockDataFactory.TEST_ARTIST_ID, "New Project/Album");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateProject(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testSuccess_Album_Update() {
        List<AvProject> list = this.setupMockDataSingleProject(
                AvMediaMockDataFactory.TEST_PROJECT_ID,
                AvMediaMockDataFactory.TEST_ARTIST_ID, "New Project/Album");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        int rc = 0;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            rc = api.updateProject(obj);
        }
        catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_UPDATE_RC, rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECT_ID, obj.getProjectId());
        Assert.assertEquals(AvMediaMockDataFactory.TEST_ARTIST_ID, obj.getArtistId());
    }
    
    @Test
    public void testError_Album_Update_DB_Access_Fault() {
        String errMsg = "A Database error occurred updating project/album";
        when(this.mockPersistenceClient.updateRow(isA(AvProject.class)))
               .thenThrow(new DatabaseException(errMsg));
        
        List<AvProject> list = this.setupMockDataSingleProject(
                AvMediaMockDataFactory.TEST_PROJECT_ID,
                AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Existing Project/Album");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateProject(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Album_Delete() {
        List<AvProject> list = this.setupMockDataSingleProject(
                AvMediaMockDataFactory.TEST_PROJECT_ID,
                AvMediaMockDataFactory.TEST_ARTIST_ID,
                "Existing Project/Album");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.deleteProject(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    @Test
    public void testValidation_Album_With_Null_Data_Object() {
        String errMsg = "Project/Album criteria object is required";
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateProject(null);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Album_With_Invalid_ArtistId() {
        String errMsg = "Project/Album artist id is required";
        List <AvProject> list = this.setupMockDataSingleProject(0, 0, "New Project/Album");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateProject(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Album_With_Invalid_ProjectTypeId() {
        String errMsg = "Project/Album project type id is required";
        List<AvProject> list = this.setupMockDataSingleProject(0,
                AvMediaMockDataFactory.TEST_ARTIST_ID, "New Project/Album");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        obj.setProjectTypeId(0);
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateProject(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Album_With_Invalid_GenreId() {
        String errMsg = "Project/Album genre id is required";
        List<AvProject> list = this.setupMockDataSingleProject(0,
                AvMediaMockDataFactory.TEST_ARTIST_ID, "New Project/Album");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        obj.setGenreId(0);
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateProject(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Album_With_Invalid_MediaTypeId() {
        String errMsg = "Project/Album media type id is required";
        List<AvProject> list = this.setupMockDataSingleProject(0,
                AvMediaMockDataFactory.TEST_ARTIST_ID, "New Project/Album");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        obj.setMediaTypeId(0);
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateProject(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Album_With_Null_Title() {
        String errMsg = "Project/Album title is required";
        List<AvProject> list = this.setupMockDataSingleProject(0,
                AvMediaMockDataFactory.TEST_ARTIST_ID, null);
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateProject(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Album_With_Empty_Title() {
        String errMsg = "Project/Album title is required";
        List <AvProject> list = this.setupMockDataSingleProject(0, AvMediaMockDataFactory.TEST_ARTIST_ID, "");
        AudioVideoApi api = null;
        ProjectDto obj = Rmt2MediaDtoFactory.getAvProjectInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateProject(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Tracks_Create() {
        List<AvTracks> list = this.setupMockDataSingleTrack(0,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 1, "Track Title");
        AudioVideoApi api = null;
        TracksDto obj = Rmt2MediaDtoFactory.getAvTrackInstance(list.get(0));
        int rc = 0;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            rc = api.updateTrack(obj);
        }
        catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_NEW_TRACK_ID, rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_NEW_TRACK_ID, obj.getTrackId());
    }
    
    @Test
    public void testError_Track_Create_DB_Access_Fault() {
        String errMsg = "A Database error occurred creating track";
        when(this.mockPersistenceClient.insertRow(isA(AvTracks.class), eq(true)))
               .thenThrow(new DatabaseException(errMsg));
        
        List<AvTracks> list = this.setupMockDataSingleTrack(0,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 1, "Track Title");
        AudioVideoApi api = null;
        TracksDto obj = Rmt2MediaDtoFactory.getAvTrackInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateTrack(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testSuccess_Tracks_Update() {
        List<AvTracks> list = this.setupMockDataSingleTrack(
                AvMediaMockDataFactory.TEST_TRACK_ID,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 1, "Track Title");
        AudioVideoApi api = null;
        TracksDto obj = Rmt2MediaDtoFactory.getAvTrackInstance(list.get(0));
        int rc = 0;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            rc = api.updateTrack(obj);
        }
        catch (AudioVideoApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_TRACK_ID, rc);
        Assert.assertEquals(AvMediaMockDataFactory.TEST_TRACK_ID, obj.getTrackId());
        Assert.assertEquals(AvMediaMockDataFactory.TEST_PROJECT_ID, obj.getProjectId());
    }
    
    @Test
    public void testError_Track_Update_DB_Access_Fault() {
        String errMsg = "A Database error occurred updating track";
        when(this.mockPersistenceClient.updateRow(isA(AvTracks.class)))
               .thenThrow(new DatabaseException(errMsg));
        
        List<AvTracks> list = this.setupMockDataSingleTrack(
                AvMediaMockDataFactory.TEST_TRACK_ID,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 1, "Track Title");
        AudioVideoApi api = null;
        TracksDto obj = Rmt2MediaDtoFactory.getAvTrackInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateTrack(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AudioVideoApiException);
            Assert.assertTrue(e.getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof AudioVideoDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(errMsg, e.getCause().getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Track_Delete() {
        List<AvTracks> list = this.setupMockDataSingleTrack(
                AvMediaMockDataFactory.TEST_TRACK_ID,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 1, "Track Title");
        AudioVideoApi api = null;
        TracksDto obj = Rmt2MediaDtoFactory.getAvTrackInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.deleteTracks(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    @Test
    public void testValidation_Track_With_Null_Data_Object() {
        String errMsg = "Track criteria object is required";
        AudioVideoApi api = null;
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateTrack(null);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Track_With_Invalid_ProjectId() {
        String errMsg = "Track project id is required";
        List <AvTracks> list = this.setupMockDataSingleTrack(0, 0, 1, "Track Title");
        AudioVideoApi api = null;
        TracksDto obj = Rmt2MediaDtoFactory.getAvTrackInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateTrack(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Track_With_Invalid_TrackNumber() {
        String errMsg = "Track number is required";
        List<AvTracks> list = this.setupMockDataSingleTrack(0,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 0, "Track Title");
        AudioVideoApi api = null;
        TracksDto obj = Rmt2MediaDtoFactory.getAvTrackInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateTrack(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Track_With_Null_TrackTitle() {
        String errMsg = "Track title is required";
        List<AvTracks> list = this.setupMockDataSingleTrack(0,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 0, null);
        AudioVideoApi api = null;
        TracksDto obj = Rmt2MediaDtoFactory.getAvTrackInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateTrack(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Track_With_Empty_TrackTitle() {
        String errMsg = "Track title is required";
        List<AvTracks> list = this.setupMockDataSingleTrack(0,
                AvMediaMockDataFactory.TEST_PROJECT_ID, 0, "");
        AudioVideoApi api = null;
        TracksDto obj = Rmt2MediaDtoFactory.getAvTrackInstance(list.get(0));
        try {
            api = AudioVideoFactory.createApi(MediaConstants.APP_NAME);
            api.updateTrack(obj);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
}