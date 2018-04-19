package org.rmt2.api.document;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.dao.document.ContentDaoException;
import org.dao.document.file.MediaFileFactory;
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
import org.modules.MediaConfigurator;
import org.modules.MediaConstants;
import org.modules.MediaModuleException;
import org.modules.document.DocumentContentApi;
import org.modules.document.DocumentContentApiFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.MediaMockData;
import org.rmt2.api.MediaMockDataFactory;

import com.NotFoundException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2File;

/**
 * Test the document module functionality of the Media API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, RMT2File.class, MediaFileFactory.class })
public class MediaDocumentQueryApiTest extends MediaMockData {
    private String outDir;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        // Since we are not testing the Inbound Directory Listener process, mock its batch process for this JUnit.
        this.mockListenerBatchProcess();
        MediaConfigurator configurator = new MediaConfigurator();
        configurator.start();
        
        outDir = RMT2File.loadAppConfigProperties("config.Media-AppParms").getString("media_output_location");
        
        // Setup stubs for fetching data successfully
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                .thenReturn(this.mockSingleContent);

        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
                .thenReturn(this.mockMultipleMimeTypeList);
        
        when(this.mockPersistenceClient.retrieveObject(isA(MimeTypes.class)))
                .thenReturn(this.mockSingleMimeTypes);
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
    public void testSuccess_Fetch_Content_From_Database() {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, true);
        ContentDto results = null;
        try {
            results = api.get(MediaMockDataFactory.TEST_CONTENT_ID);
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        Assert.assertTrue(Arrays.equals("ImageData".getBytes(), results.getImageData()));
    }
    
    @Test
    public void testSuccess_Fetch_Content_From_Database_NotFound() {
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                  .thenReturn(null);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, true);
        ContentDto results = null;
        try {
            results = api.get(MediaMockDataFactory.TEST_CONTENT_ID);
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNull(results);
    }
    
    @Test
    public void testSuccess_Fetch_Content_From_File() {
        // We want the API to actually fetch a file from the test/resources source folder on the build path.
        Content contentFile = MediaMockDataFactory.createOrmContent(MediaMockDataFactory.TEST_CONTENT_ID,
                MediaMockDataFactory.TEST_MIME_TYPE_ID, "media/", "image.jpg",
                1024, 5555, "Media");
        contentFile.setImageData(null);
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
             .thenReturn(contentFile);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        ContentDto results = null;
        try {
            results = api.get(MediaMockDataFactory.TEST_CONTENT_ID);
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        byte[] expectedImage = RMT2File.getFileContentsAsBytes("media/image.jpg");
        // Save expected image to disc for visual inspection
        String filePath = outDir + "@@image.jpg";
        RMT2File.outputFile(expectedImage, filePath);
        Assert.assertTrue(Arrays.equals(expectedImage, results.getImageData()));
    }
    
    @Test
    public void testSuccess_Fetch_MimeTypes_Multiple() {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        List<MimeTypeDto> results = null;
        try {
            MimeTypeDto criteria = Rmt2MediaDtoFactory.getMimeTypeInstance(null);
            results = api.getMimeType(criteria);
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
    }
    
    @Test
    public void testSuccess_Fetch_MimeTypes_Single() {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        MimeTypeDto results = null;
        try {
            results = api.getMimeType(MediaMockDataFactory.TEST_MIME_TYPE_ID);
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
    }
    
    @Test
    public void testError_Fetch_From_Database_Database_Access_Fault() {
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                .thenThrow(new DatabaseException("A database error occurred"));
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.get(MediaMockDataFactory.TEST_CONTENT_ID);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to retrieve media document identified by document id: " +
                            + MediaMockDataFactory.TEST_CONTENT_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            Assert.assertEquals("DAO error occurred fetching media content record by content id, " +
                            + MediaMockDataFactory.TEST_CONTENT_ID , e.getCause().getMessage());
        }
    }
    
    
    @Test
    public void testError_Fetch_From_FileSystem_Database_Access_Fault() {
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                   .thenThrow(new DatabaseException("A database error occurred"));
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        try {
            api.get(MediaMockDataFactory.TEST_CONTENT_ID);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to retrieve media document identified by document id: " +
                    + MediaMockDataFactory.TEST_CONTENT_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            Assert.assertEquals("DAO error occurred fetching media content record by content id, "
                            + MediaMockDataFactory.TEST_CONTENT_ID,
                    e.getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Fetch_From_FileSystem_Extract_File_Content_Fault() {
        PowerMockito.mockStatic(RMT2File.class);
        when(RMT2File.getFileContentsAsBytes(isA(String.class)))
                .thenThrow(new NotFoundException("External File is not found"));
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        try {
            api.get(MediaMockDataFactory.TEST_CONTENT_ID);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to retrieve media document identified by document id: " +
                    + MediaMockDataFactory.TEST_CONTENT_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            Assert.assertEquals("Unable to fetch media content from the file system for content id, "
                            + MediaMockDataFactory.TEST_CONTENT_ID,
                    e.getCause().getMessage());
            Assert.assertTrue(e.getCause().getCause() instanceof NotFoundException);
            Assert.assertEquals("External File is not found", e.getCause().getCause().getMessage());
        }
    }
}