package org.rmt2.api.audiovideo;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.MediaConstants;
import org.modules.audiovideo.Mp3ReaderIdentityNotConfiguredException;
import org.modules.audiovideo.batch.AvBatchFileFactory;
import org.modules.audiovideo.batch.AvBatchFileProcessorApi;
import org.modules.audiovideo.batch.AvSourceNotADirectoryException;
import org.modules.audiovideo.batch.BatchFileProcessException;
import org.modules.audiovideo.batch.InvalidBatchRootDirectoryException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.BatchFileException;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2File;

/**
 * Test the audio/video batch file import module functionality in the Media API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, RMT2File.class, SmtpFactory.class })
public class AudioVideoBatchImportApiTest extends AvMediaMockData {
    
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        when(this.mockPersistenceClient.retrieveList(isA(AvGenre.class)))
                .thenReturn(this.mockAvGenreData);
        
        // Setup stub for SMTP mocking
        SmtpApi mockSmtpApi = Mockito.mock(SmtpApi.class);
        PowerMockito.mockStatic(SmtpFactory.class);
        try {
            when(SmtpFactory.getSmtpInstance()).thenReturn(mockSmtpApi);
        }
        catch (Exception e) {
            Assert.fail("Failed to stub SmtpFactory.getSmtpInstance method");
        }
        try {
            when(mockSmtpApi.sendMessage(isA(EmailMessageBean.class))).thenReturn(1);  
            doNothing().when(mockSmtpApi).close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("The mocking of TimesheetTransmissionApi's send method failed");
        }
    }

    private List<AvArtist> setupMockSingleArtist(int artistId, String name) {
        List<AvArtist> list = new ArrayList<>();
        AvArtist o = AvMediaMockDataFactory.createOrmAvArtist(artistId, name);
        list.add(o);
        return list;
    }
    
    private List<AvProject> setupMockSingleProject(int projId, int artistId, String projName) {
            List<AvProject> list = new ArrayList<>();
            int ndx = AvMediaMockDataFactory.TEST_PROJECT_ID;
            AvProject o = AvMediaMockDataFactory.createOrmAvProject(projId,
                    artistId,
                    AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                    AvMediaMockDataFactory.TEST_GENRE_ID,
                    AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, projName, 2018,
                    "/FilePath/" + ndx, "ProjectFileName" + ndx);
            list.add(o);    
            return list;
    }
    
    private void setupStubsForProcessingNewData() {
        List<AvArtist> bassAddictionArtist = this.setupMockSingleArtist(AvMediaMockDataFactory.TEST_ARTIST_ID + 2, "Bass Addiction");
        when(this.mockPersistenceClient.retrieveList(isA(AvArtist.class)))
                .thenReturn(null, null, null, bassAddictionArtist, null, null);
        
        int artist = AvMediaMockDataFactory.TEST_ARTIST_ID;
        when(this.mockPersistenceClient.insertRow(isA(AvArtist.class), eq(true)))
                .thenReturn(artist++, artist++, artist++, artist++, artist);

        List<AvProject> bassAddictionProject = this.setupMockSingleProject(
                AvMediaMockDataFactory.TEST_PROJECT_ID + 2,
                AvMediaMockDataFactory.TEST_ARTIST_ID + 2, "Bass Addiction");
        List<AvProject> djKicksProject = this.setupMockSingleProject(
                AvMediaMockDataFactory.TEST_PROJECT_ID + 3,
                AvMediaMockDataFactory.TEST_ARTIST_ID + 5, "DJ-KiCKS");
        when(this.mockPersistenceClient.retrieveList(isA(AvProject.class)))
                .thenReturn(null, null, null, null, null, null, bassAddictionProject, null, null, null, djKicksProject);

        int projectId = AvMediaMockDataFactory.TEST_PROJECT_ID;
        when(this.mockPersistenceClient.insertRow(isA(AvProject.class), eq(true)))
              .thenReturn(projectId++, projectId++, projectId++, projectId);
        
        
        when(this.mockPersistenceClient.retrieveList(isA(AvTracks.class))).thenReturn(null);
        int trackId = AvMediaMockDataFactory.TEST_TRACK_ID;
        when(this.mockPersistenceClient.insertRow(isA(AvTracks.class), eq(true)))
              .thenReturn(trackId++, trackId++, trackId++, trackId++, trackId++, trackId);
        
    }
    
    private void setupStubsForProcessingExistingData() {
        when(this.mockPersistenceClient.updateRow(isA(AvArtist.class))).thenReturn(1);
        when(this.mockPersistenceClient.updateRow(isA(AvProject.class))).thenReturn(1);
        when(this.mockPersistenceClient.updateRow(isA(AvTracks.class))).thenReturn(1);
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
    public void testSuccess_Batch_Import() {
        // Setup mock stubs for creating new audio entries.
        this.setupStubsForProcessingNewData();
        // Get full directory path for relative path which resides on the classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        
        AvBatchFileFactory f = new AvBatchFileFactory();
        int results = 0;
        try {
            AvBatchFileProcessorApi api = f.createApiInstance(dir);
            results = api.processBatch();
        }
        catch (BatchFileException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        Assert.assertEquals(6, results);
    }
    
  
    @Test
    public void testError_Null_Directory() {
        String dir = null;
        AvBatchFileFactory f = new AvBatchFileFactory();
        try {
            f.createApiInstance(dir);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
            Assert.assertEquals("Could not instantiate Audio/Video batch file loader class", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof InvalidBatchRootDirectoryException);
            Assert.assertEquals("The root directory path is invalid or null", e.getCause().getMessage());

        }
    }
    
    
    @Test
    public void testError_File_As_Directory() {
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        dir += "/Aaliyah/One In A Million/Aaliyah-One In A Million-17-Came To Give Love (Outro).mp3";
        AvBatchFileFactory f = new AvBatchFileFactory();
        try {
            f.createApiInstance(dir);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
            Assert.assertEquals("Could not instantiate Audio/Video batch file loader class", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof AvSourceNotADirectoryException);
            String msg = " is required to be a directory for Audio Video Batch process";
            Assert.assertTrue(e.getCause().getMessage().contains(msg));

        }
    }
    
    @Test
    public void testError_Create_MP3Reader_Invalid__Config_mp3ReaderToUse() {
        String invalidImplCode = "9999";
        
        // Get full directory path for relative path which resides on the classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        AvBatchFileFactory f = new AvBatchFileFactory();
        try {
            AvBatchFileProcessorApi api = f.createApiInstance(dir);
            AvBatchFileProcessorApi apiSpy = Mockito.spy(api);
            
            Properties mockConfig = Mockito.mock(Properties.class);
            when(apiSpy.getConfig()).thenReturn(mockConfig);
            when(mockConfig.getProperty(MediaConstants.MP3_READER_TO_USE_CONFIG_KEY)).thenReturn(invalidImplCode);
            
            apiSpy.processBatch();
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
            Assert.assertEquals("An error occurred trying to identify the MP3Reader implemetation to use", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof Mp3ReaderIdentityNotConfiguredException);
//            String msg = "An invalid MP3 reader implementation code was specified in configuration: " + invalidImplCode;
//            Assert.assertEquals(msg, e.getCause().getMessage());

        }
    }
    
    @Test
    public void testError_Create_MP3Reader_NonNumeric_Config_mp3ReaderToUse() {
        String invalidImplCode = "T9999";
        
        // Get full directory path for relative path which resides on the classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        AvBatchFileFactory f = new AvBatchFileFactory();
        try {
            AvBatchFileProcessorApi api = f.createApiInstance(dir);
            AvBatchFileProcessorApi apiSpy = Mockito.spy(api);
            
            Properties mockConfig = Mockito.mock(Properties.class);
            when(apiSpy.getConfig()).thenReturn(mockConfig);
            when(mockConfig.getProperty(MediaConstants.MP3_READER_TO_USE_CONFIG_KEY)).thenReturn(invalidImplCode);
            
            apiSpy.processBatch();
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
            Assert.assertEquals("An error occurred trying to identify the MP3Reader implemetation to use", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof Mp3ReaderIdentityNotConfiguredException);
//            String msg = "A non-numeric code was discovered to be configured for the MP3 reader implementation to use for this API [" + invalidImplCode + "]";
//            Assert.assertEquals(msg, e.getCause().getMessage());

        }
    }
        
    @Test
    public void testError_Create_MP3Reader_Null_Config_Object() {
        // Get full directory path for relative path which resides on the classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        AvBatchFileFactory f = new AvBatchFileFactory();
        try {
            AvBatchFileProcessorApi api = f.createApiInstance(dir);
            AvBatchFileProcessorApi apiSpy = Mockito.spy(api);
            
            when(apiSpy.getConfig()).thenReturn(null);
            
            apiSpy.processBatch();
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
            Assert.assertEquals("An error occurred trying to identify the MP3Reader implemetation to use", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof Mp3ReaderIdentityNotConfiguredException);
//            String msg = "A general error occurred attempting to read configuration for MP3 reader implementation";
//            Assert.assertEquals(msg, e.getCause().getMessage());

        }
    }
}