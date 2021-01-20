package org.rmt2.api.document;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.dao.document.ContentDaoException;
import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.MediaConstants;
import org.modules.MediaModuleException;
import org.modules.document.DocumentContentApi;
import org.modules.document.DocumentContentApiFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.NotFoundException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2File;

/**
 * Test the document module functionality of the Media API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, RMT2File.class })
public class DocumentMediaQueryApiTest extends DocumentMediaMockData {
    private String outDir;
    public static final String TEST_MEDIA_PROJ_PATH = "media/document";
    private static final String TEST_USER_SESSION_ID = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String TEST_DOWNLOADED_FILE_PATH;
    private static String TEST_INPUT_MEDIA_FULL_PATH;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        outDir = RMT2File.loadAppConfigProperties("config.Media-AppParms").getString("media_output_location");
        
        // Setup stubs for fetching data successfully
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                .thenReturn(this.mockSingleContent);

        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
                .thenReturn(this.mockMultipleMimeTypeList);
        
        when(this.mockPersistenceClient.retrieveObject(isA(MimeTypes.class)))
                .thenReturn(this.mockSingleMimeTypes);

        // Create user session work area for storing temporary media files
        // locally
        String userSessionWorkArea = RMT2File.createUserSessionWorkArea();
        System.setProperty("SerialPath", userSessionWorkArea);
        // TEST_DOWNLOADED_FILE_PATH = System.getProperty("SerialPath") +
        // File.separator + TEST_USER_SESSION_ID + File.separator;
        TEST_DOWNLOADED_FILE_PATH = System.getProperty("SerialPath") + File.separator;

        // NOTE: For all intents and purposes of unit testing, this is the full
        // path of the file which resides within the directory structure of the
        // Media API project. In production runtime, the directory of media file
        // will be relative to the user's FTP home directory.
        TEST_INPUT_MEDIA_FULL_PATH = RMT2File.resolveRelativeFilePath(DocumentMediaUpdateApiTest.TEST_MEDIA_PROJ_PATH);

        this.copyMediaTestFilesToUserWorkArea();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        try {
            if (RMT2File.verifyFile(TEST_DOWNLOADED_FILE_PATH + "image.jpg") == RMT2File.FILE_IO_EXIST) {
                RMT2File.deleteFile(TEST_DOWNLOADED_FILE_PATH + "image.jpg");
            }
        } catch (Exception e) {

        }
        return;
    }

    private void copyMediaTestFilesToUserWorkArea() {
        // Create session directory if it does not exists.
        File downLoadDir = new File(TEST_DOWNLOADED_FILE_PATH);
        boolean mkdirRc = false;
        if (!downLoadDir.exists()) {
            mkdirRc = downLoadDir.mkdir();
        }
        try {
            if (RMT2File.verifyFile(TEST_DOWNLOADED_FILE_PATH + "image.jpg") == RMT2File.FILE_IO_NOTEXIST) {
                RMT2File.copyFile(TEST_INPUT_MEDIA_FULL_PATH + File.separator + "image.jpg", TEST_DOWNLOADED_FILE_PATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
   
    @Test
    public void testSuccess_Fetch_Content_From_Database() {
        DocumentContentApi api = DocumentContentApiFactory.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, true);
        ContentDto results = null;
        try {
            results = api.get(DocumentMediaMockDataFactory.TEST_CONTENT_ID);
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        Assert.assertNotNull(results.getImageData());
        Assert.assertTrue(results.getImageData().length > 5000);
    }
    
    @Test
    public void testSuccess_Fetch_Content_From_Database_NotFound() {
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                .thenReturn(null);

        DocumentContentApi api =
                DocumentContentApiFactory.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME,
                        true);
        ContentDto results = null;
        try {
            results = api.get(DocumentMediaMockDataFactory.TEST_CONTENT_ID);
        } catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }

        Assert.assertNull(results);
    }

    @Test
    public void testSuccess_Fetch_Content_From_File() {
        // We want the API to actually fetch a file from the test/resources
        // source folder on the build path.
        Content contentFile =
                DocumentMediaMockDataFactory.createOrmContent(DocumentMediaMockDataFactory.TEST_CONTENT_ID,
                        DocumentMediaMockDataFactory.TEST_MIME_TYPE_ID, "media/document/",
                        "image.jpg",
                        1024, 5555, "Media");
        contentFile.setImageData(null);
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                .thenReturn(contentFile);

        DocumentContentApi api =
                DocumentContentApiFactory.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME,
                        false);
        ContentDto results = null;
        try {
            results = api.get(DocumentMediaMockDataFactory.TEST_CONTENT_ID);
        } catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }

        Assert.assertNotNull(results);
        byte[] expectedImage =
                RMT2File.getFileContentsAsBytes("media/document/image.jpg");
        // Save expected image to disc for visual inspection
        // String filePath = outDir + "@@image.jpg";
        // RMT2File.outputFile(expectedImage, filePath);
        Assert.assertTrue(Arrays.equals(expectedImage, results.getImageData()));
    }

    @Test
    public void testSuccess_Fetch_MimeTypes_Multiple() {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api =
                f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        List<MimeTypeDto> results = null;
        try {
            MimeTypeDto criteria = Rmt2MediaDtoFactory.getMimeTypeInstance(null);
            results = api.getMimeType(criteria);
        } catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }

        Assert.assertNotNull(results);
    }

    @Test
    public void testSuccess_Fetch_MimeTypes_Single() {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api =
                f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        MimeTypeDto results = null;
        try {
            results =
                    api.getMimeType(DocumentMediaMockDataFactory.TEST_MIME_TYPE_ID);
        } catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }

        Assert.assertNotNull(results);
    }

    @Test
    public void testError_Fetch_From_Database_Database_Access_Fault() {
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                .thenThrow(new DatabaseException("A database error occurred"));

        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO
        // implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.get(DocumentMediaMockDataFactory.TEST_CONTENT_ID);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to retrieve media document identified by document id: "
                    +
                    +DocumentMediaMockDataFactory.TEST_CONTENT_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            Assert.assertEquals("DAO error occurred fetching media content record by content id, "
                    +
                    +DocumentMediaMockDataFactory.TEST_CONTENT_ID,
                    e.getCause().getMessage());
        }
    }

    @Test
    public void testError_Fetch_From_FileSystem_Database_Access_Fault() {
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                .thenThrow(new DatabaseException("A database error occurred"));

        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO
        // implementation.
        DocumentContentApi api =
                f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        try {
            api.get(DocumentMediaMockDataFactory.TEST_CONTENT_ID);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to retrieve media document identified by document id: "
                    +
                    +DocumentMediaMockDataFactory.TEST_CONTENT_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            Assert.assertEquals("DAO error occurred fetching media content record by content id, "
                    + DocumentMediaMockDataFactory.TEST_CONTENT_ID,
                    e.getCause().getMessage());
        }
    }

    @Test
    public void testError_Fetch_From_FileSystem_Extract_File_Content_Fault()
    {
        PowerMockito.mockStatic(RMT2File.class);
        when(RMT2File.getFileContentsAsBytes(isA(String.class)))
                .thenThrow(new NotFoundException("External File is not found"));

        // Test default constructor which should employ the database DAO
        // implementation.
        DocumentContentApi api =
                DocumentContentApiFactory.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME,
                        false);
        try {
            api.get(DocumentMediaMockDataFactory.TEST_CONTENT_ID);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to retrieve media document identified by document id: "
                    +
                    +DocumentMediaMockDataFactory.TEST_CONTENT_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            Assert.assertEquals("Unable to fetch media content from the file system for content id, "
                    + DocumentMediaMockDataFactory.TEST_CONTENT_ID,
                    e.getCause().getMessage());
            Assert.assertTrue(e.getCause().getCause() instanceof NotFoundException);
            Assert.assertEquals("External File is not found",
                    e.getCause().getCause().getMessage());
        }
    }
}