package org.rmt2.api.audiovideo;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
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
import org.mockito.internal.util.reflection.Whitebox;
import org.modules.MediaConstants;
import org.modules.audiovideo.Mp3ReaderIdentityNotConfiguredException;
import org.modules.audiovideo.batch.AvBatchFileFactory;
import org.modules.audiovideo.batch.AvBatchFileProcessorApi;
import org.modules.audiovideo.batch.AvBatchImportParameters;
import org.modules.audiovideo.batch.BatchFileProcessException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.BatchFileException;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2File;

/**
 * Test the audio/video batch file import module functionality in the Media API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, RMT2File.class, SmtpFactory.class })
public class LocalAudioBatchFileImportApiTest extends AvMediaMockData {
    
    private static final String PROP_NAME_MP3_READER_IMPL_TO_USE = "MP3_READER_IMPL_TO_USE";
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
        
        int results = 0;
        AvBatchFileProcessorApi api = null;
        AvBatchImportParameters parms = new AvBatchImportParameters();
        parms.setPath(dir);
        try {
            api = AvBatchFileFactory.createLocalAudioBatchImportApiInstance(parms);
            Whitebox.setInternalState(api, PROP_NAME_MP3_READER_IMPL_TO_USE, null);
            results = api.processBatch();
        }
        catch (BatchFileException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        Assert.assertEquals(6, results);
        Assert.assertEquals(0, api.getErrorCount());
        Assert.assertEquals(results, api.getSuccessCount());
        Assert.assertNotNull(api.getErrorMessages());
        Assert.assertEquals(0, api.getErrorMessages().size());
    }
    
    @Test
    public void testValidation_Null_Directory() {
        String dir = null;
        AvBatchImportParameters parms = new AvBatchImportParameters();
        parms.setPath(dir);
        try {
            AvBatchFileFactory.createLocalAudioBatchImportApiInstance(parms);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
        }
    }
    
    
    @Test
    public void testValidation_File_As_Directory() {
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        dir += "/Aaliyah/One In A Million/Aaliyah-One In A Million-17-Came To Give Love (Outro).mp3";
        AvBatchImportParameters parms = new AvBatchImportParameters();
        parms.setPath(dir);
        try {
            AvBatchFileFactory.createLocalAudioBatchImportApiInstance(parms);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
        }
    }
    
    @Test
    public void testValidation_Create_MP3Reader_Invalid_Config_mp3ReaderToUse() {
        String invalidImplCode = "9999";
        
        // Get full directory path for relative path which resides on the classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        AvBatchImportParameters parms = new AvBatchImportParameters();
        parms.setPath(dir);
        AvBatchFileProcessorApi apiSpy = null;
        try {
            AvBatchFileProcessorApi api = AvBatchFileFactory.createLocalAudioBatchImportApiInstance(parms);
            apiSpy = Mockito.spy(api);
            Whitebox.setInternalState(apiSpy, PROP_NAME_MP3_READER_IMPL_TO_USE, null);
            
            Properties mockConfig = Mockito.mock(Properties.class);
            doReturn(mockConfig).when(apiSpy).getConfig();
            when(mockConfig.getProperty(eq(MediaConstants.MP3_READER_TO_USE_CONFIG_KEY))).thenReturn(invalidImplCode);
            
            apiSpy.processBatch();
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
            Assert.assertEquals("An error occurred trying to identify the MP3Reader implemetation to use", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof Mp3ReaderIdentityNotConfiguredException);
            String msg = "An invalid MP3 reader implementation code was specified in configuration: " + invalidImplCode;
            Assert.assertEquals(msg, e.getCause().getMessage());

        }
    }
    
    @Test
    public void testValidation_Create_MP3Reader_NonNumeric_Config_mp3ReaderToUse() {
        String invalidImplCode = "TESTCODE";
        
        // Get full directory path for relative path which resides on the classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        AvBatchImportParameters parms = new AvBatchImportParameters();
        parms.setPath(dir);
        AvBatchFileProcessorApi apiSpy = null;
        try {
            AvBatchFileProcessorApi api = AvBatchFileFactory.createLocalAudioBatchImportApiInstance(parms);
            apiSpy = Mockito.spy(api);
            Whitebox.setInternalState(apiSpy, PROP_NAME_MP3_READER_IMPL_TO_USE, null);
            
            Properties mockConfig = Mockito.mock(Properties.class);
            doReturn(mockConfig).when(apiSpy).getConfig();
            when(mockConfig.getProperty(eq(MediaConstants.MP3_READER_TO_USE_CONFIG_KEY))).thenReturn(invalidImplCode);
            
            apiSpy.processBatch();
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
            Assert.assertEquals("An error occurred trying to identify the MP3Reader implemetation to use", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof Mp3ReaderIdentityNotConfiguredException);
        }
    }
        
    @Test
    public void testError_Create_MP3Reader_Null_Config_Object() {
        // Get full directory path for relative path which resides on the classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        AvBatchImportParameters parms = new AvBatchImportParameters();
        parms.setPath(dir);
        AvBatchFileProcessorApi apiSpy = null;
        try {
            AvBatchFileProcessorApi api = AvBatchFileFactory.createLocalAudioBatchImportApiInstance(parms);
            apiSpy = Mockito.spy(api);
            Whitebox.setInternalState(apiSpy, PROP_NAME_MP3_READER_IMPL_TO_USE, null);
            
            doReturn(null).when(apiSpy).getConfig();
            
            apiSpy.processBatch();
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
            Assert.assertEquals("An error occurred trying to identify the MP3Reader implemetation to use", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof Mp3ReaderIdentityNotConfiguredException);
        }
    }
    
    @Test
    public void testError_SingleFile_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveList(isA(AvArtist.class)))
        .thenThrow(new DatabaseException("A db error occurred"));
        
        // Get full directory path for relative path which resides on the classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        AvBatchImportParameters parms = new AvBatchImportParameters();
        parms.setPath(dir);
        
        int results = 0;
        AvBatchFileProcessorApi api = null;
        try {
            api = AvBatchFileFactory.createLocalAudioBatchImportApiInstance(parms);
            Whitebox.setInternalState(api, PROP_NAME_MP3_READER_IMPL_TO_USE, null);
            results = api.processBatch();
        }
        catch (BatchFileException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        // Since the exception is not thrown as a end result, we must chech the
        // totals to determine if an error occurred.
        Assert.assertNotNull(results);
        Assert.assertEquals(6, results);
        Assert.assertEquals(results, api.getErrorCount());
        Assert.assertEquals(0, api.getSuccessCount());
        Assert.assertNotNull(api.getErrorMessages());
        Assert.assertEquals(results, api.getErrorMessages().size());
    }
}