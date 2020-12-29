package org.rmt2.api.audiovideo;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
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
import com.api.messaging.MessagingResourceFactory;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.messaging.ftp.FtpApi;
import com.api.messaging.ftp.FtpFactory;
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
@PrepareForTest({ AbstractDaoClientImpl.class, FtpFactory.class, MessagingResourceFactory.class, Rmt2OrmClientFactory.class,
        RMT2File.class, SmtpFactory.class })
public class RemoteAudioBatchFileImportApiTest extends AvMediaMockData {
    
    private static final String PROP_NAME_MP3_READER_IMPL_TO_USE = "MP3_READER_IMPL_TO_USE";
    private static final String TEST_USER_SESSION_ID = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // private static final String TEST_DOWNLOADED_ROOTDIR = "/temp/";
    private static final String TEST_PARM_SERVER = "RMTDALDEV77";
    private static final String TEST_PARM_SHARENAME = "home";
    private static final String TEST_PARM_ROOT_PATH = "royterrell";
    private static final int TEST_FILE_COUNT = 5;
    private static String TEST_DOWNLOADED_FILE_PATH;

    private FtpApi mockFtp;
    private String testMediaFileDir;
    private List<String> mockDataFileNames;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        // Create user session work area for storing temporary media files
        // locally
        String userSessionWorkArea = RMT2File.createUserSessionWorkArea();
        System.setProperty("SerialPath", userSessionWorkArea);
        TEST_DOWNLOADED_FILE_PATH = System.getProperty("SerialPath") + File.separator + TEST_USER_SESSION_ID + File.separator;

        // NOTE: For all intents and purposes of unit testing, this is the full
        // path of the file which resides within the directory structure of the
        // Media API project. In production runtime, the directory of media file
        // will be relative to the user's FTP home directory.
        this.testMediaFileDir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);

        // Setup mock data
        this.mockDataFileNames = new ArrayList<>();
        this.mockDataFileNames.add(this.testMediaFileDir + File.separator
                + "Aaliyah\\One In A Million\\Aaliyah-One In A Million-17-Came To Give Love (Outro).mp3");
        this.mockDataFileNames.add(this.testMediaFileDir + File.separator
                + "Alexander O'Neal\\Greatest Hits\\A Broken Heart Can Mend.mp3");
        this.mockDataFileNames.add(this.testMediaFileDir + File.separator
                + "Bass Addiction\\Bass Apocolypse  World Bass War, Vol. 1\\Dance Shake and Swing.mp3");
        this.mockDataFileNames.add(this.testMediaFileDir + File.separator
                + "Bass Addiction\\Bass Apocolypse  World Bass War, Vol. 1\\Der Ausserts Bass Erfahrung.mp3");
        this.mockDataFileNames.add(this.testMediaFileDir + File.separator
                + "Various Artists\\DJ-KiCKS\\Eli Gold-DJ-KiCKS-10-Throw That (Interlude).mp3");

        List<String> mockImageFileNames = new ArrayList<>();
        mockImageFileNames.add("folder.jpg");

        // Copy test media files to user's work area
        this.copyMediaTestFilesToUserWorkArea();

        // Setup FTP mocking
        this.mockFtp = Mockito.mock(FtpApi.class);
        PowerMockito.mockStatic(FtpFactory.class);

        when(FtpFactory.getInstance(isA(String.class), isA(String.class),
                isA(String.class), isA(String.class),
                isA(String.class))).thenReturn(mockFtp);

        when(mockFtp.listDirectory(isA(String.class), eq(true))).thenReturn(mockDataFileNames);
        when(mockFtp.listDirectory(isA(String.class), eq(false))).thenReturn(mockImageFileNames);
        when(mockFtp.downloadFile(isA(String.class))).thenReturn(
                TEST_DOWNLOADED_FILE_PATH + RMT2File.getFileName(mockDataFileNames.get(0)),
                TEST_DOWNLOADED_FILE_PATH + RMT2File.getFileName(mockDataFileNames.get(1)),
                TEST_DOWNLOADED_FILE_PATH + RMT2File.getFileName(mockDataFileNames.get(2)),
                TEST_DOWNLOADED_FILE_PATH + RMT2File.getFileName(mockDataFileNames.get(3)),
                TEST_DOWNLOADED_FILE_PATH + RMT2File.getFileName(mockDataFileNames.get(4)));

        when(mockFtp.isDirectory(isA(String.class))).thenReturn(true);
        when(mockFtp.getUserSessionWorkArea()).thenReturn(TEST_DOWNLOADED_FILE_PATH);

        // Setup up Persistence mocking
        when(this.mockPersistenceClient.retrieveList(isA(AvGenre.class)))
                .thenReturn(this.mockAvGenreData);

        // Setup stub for SMTP mocking
        SmtpApi mockSmtpApi = Mockito.mock(SmtpApi.class);
        PowerMockito.mockStatic(SmtpFactory.class);
        try {
            when(SmtpFactory.getSmtpInstance()).thenReturn(mockSmtpApi);
        } catch (Exception e) {
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

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();

        // Use as a backup plan to ensure that user work area is cleaned up.
        FileUtils.deleteDirectory(new File(TEST_DOWNLOADED_FILE_PATH));
        return;
    }

    private void copyMediaTestFilesToUserWorkArea() {
        // Create session directory if it does not exists.
        File downLoadDir = new File(TEST_DOWNLOADED_FILE_PATH);
        boolean mkdirRc = false;
        if (!downLoadDir.exists()) {
            mkdirRc = downLoadDir.mkdir();
        }

        for (String testMediaFile : this.mockDataFileNames) {
            // String file = this.testMediaFileDir + File.separator +
            // testMediaFile;
            // String file = testMediaFile;
            try {
                // RMT2File.copyFile(file, TEST_DOWNLOADED_FILE_PATH);
                RMT2File.copyFile(testMediaFile, TEST_DOWNLOADED_FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
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


    @Test
    public void testSuccess_Batch_Import() {
        // Setup mock stubs for creating new audio entries.
        this.setupStubsForProcessingNewData();
        // Get full directory path for relative path which resides on the
        // classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);

        int results = 0;
        AvBatchFileProcessorApi api = null;
        AvBatchImportParameters parms = new AvBatchImportParameters();
        parms.setServerName(TEST_PARM_SERVER);
        parms.setShareName(TEST_PARM_SHARENAME);
        parms.setRootPath(TEST_PARM_ROOT_PATH);
        parms.setPath(dir);
        parms.setSessionId(TEST_USER_SESSION_ID);
        try {
            api = AvBatchFileFactory.createRemoteAudioBatchImportApiInstance(parms);
            Whitebox.setInternalState(api, PROP_NAME_MP3_READER_IMPL_TO_USE, null);
            results = api.processBatch();
        } catch (BatchFileException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        } finally {
            api.close();
        }

        Assert.assertNotNull(results);
        Assert.assertEquals(TEST_FILE_COUNT, results);
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
        parms.setServerName(TEST_PARM_SERVER);
        parms.setShareName(TEST_PARM_SHARENAME);
        parms.setRootPath(TEST_PARM_ROOT_PATH);
        parms.setPath(dir);
        parms.setSessionId(TEST_USER_SESSION_ID);
        try {
            AvBatchFileFactory.createRemoteAudioBatchImportApiInstance(parms);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof BatchFileProcessException);
        }
    }
    
    
    @Test
    public void testValidation_File_As_Directory() {
        try {
            when(this.mockFtp.isDirectory(isA(String.class))).thenReturn(false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        dir += "/Aaliyah/One In A Million/Aaliyah-One In A Million-17-Came To Give Love (Outro).mp3";
        AvBatchImportParameters parms = new AvBatchImportParameters();
        parms.setServerName(TEST_PARM_SERVER);
        parms.setShareName(TEST_PARM_SHARENAME);
        parms.setRootPath(TEST_PARM_ROOT_PATH);
        parms.setPath(dir);
        parms.setSessionId(TEST_USER_SESSION_ID);
        try {
            AvBatchFileFactory.createRemoteAudioBatchImportApiInstance(parms);
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
        parms.setServerName(TEST_PARM_SERVER);
        parms.setShareName(TEST_PARM_SHARENAME);
        parms.setRootPath(TEST_PARM_ROOT_PATH);
        parms.setPath(dir);
        parms.setSessionId(TEST_USER_SESSION_ID);
        AvBatchFileProcessorApi apiSpy = null;
        try {
            AvBatchFileProcessorApi api = AvBatchFileFactory.createRemoteAudioBatchImportApiInstance(parms);
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
        parms.setServerName(TEST_PARM_SERVER);
        parms.setShareName(TEST_PARM_SHARENAME);
        parms.setRootPath(TEST_PARM_ROOT_PATH);
        parms.setPath(dir);
        parms.setSessionId(TEST_USER_SESSION_ID);
        AvBatchFileProcessorApi apiSpy = null;
        try {
            AvBatchFileProcessorApi api = AvBatchFileFactory.createRemoteAudioBatchImportApiInstance(parms);
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
        parms.setServerName(TEST_PARM_SERVER);
        parms.setShareName(TEST_PARM_SHARENAME);
        parms.setRootPath(TEST_PARM_ROOT_PATH);
        parms.setPath(dir);
        parms.setSessionId(TEST_USER_SESSION_ID);
        AvBatchFileProcessorApi apiSpy = null;
        try {
            AvBatchFileProcessorApi api = AvBatchFileFactory.createRemoteAudioBatchImportApiInstance(parms);
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
        parms.setServerName(TEST_PARM_SERVER);
        parms.setShareName(TEST_PARM_SHARENAME);
        parms.setRootPath(TEST_PARM_ROOT_PATH);
        parms.setPath(dir);
        parms.setSessionId(TEST_USER_SESSION_ID);
        
        int results = 0;
        AvBatchFileProcessorApi api = null;
        try {
            api = AvBatchFileFactory.createRemoteAudioBatchImportApiInstance(parms);
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
        Assert.assertEquals(TEST_FILE_COUNT, results);
        Assert.assertEquals(results, api.getErrorCount());
        Assert.assertEquals(0, api.getSuccessCount());
        Assert.assertNotNull(api.getErrorMessages());
        Assert.assertEquals(results, api.getErrorMessages().size());
    }
}