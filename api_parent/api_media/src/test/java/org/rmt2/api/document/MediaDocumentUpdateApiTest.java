package org.rmt2.api.document;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import org.dao.document.ContentDaoException;
import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.MediaConstants;
import org.modules.MediaModuleException;
import org.modules.document.DocumentContentApi;
import org.modules.document.DocumentContentApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.MediaMockData;
import org.rmt2.api.MediaMockDataFactory;

import com.InvalidDataException;
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
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class MediaDocumentUpdateApiTest extends MediaMockData {
    private String outDir;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        outDir = RMT2File.loadAppConfigProperties("config.Media-AppParms").getString("media_output_location");
        
        // Setup stubs for meta data updates
        when(this.mockPersistenceClient.insertRow(isA(Content.class), eq(true)))
                .thenReturn(MediaMockDataFactory.TEST_CONTENT_ID);

        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
                .thenReturn(this.mockMultipleMimeTypeList);
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
    public void testSuccess_Add_Image_As_File() {
        this.mockSingleMimeTypeList.add(this.mockMultipleMimeTypeList.get(0));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockSingleMimeTypeList);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        int contentId = 0;
        try {
            contentId = api.add("media/document/image.jpg");
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(MediaMockDataFactory.TEST_CONTENT_ID, contentId);
        
        // Verify that file was created
        try {
            // Give the thread a chance to create the file.
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String filePath = outDir + "image.jpg";
        Assert.assertEquals(RMT2File.FILE_IO_EXIST, RMT2File.verifyFile(filePath));
        RMT2File.deleteFile(filePath);
    }
    
    @Test
    public void testSuccess_Add_Audio_As_File() {
        this.mockSingleMimeTypeList.add(this.mockMultipleMimeTypeList.get(5));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockSingleMimeTypeList);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        int contentId = 0;
        try {
            contentId = api.add("media/document/Audio.mp3");
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(MediaMockDataFactory.TEST_CONTENT_ID, contentId);
       
        // Verify that file was created
        try {
            // Give the thread a chance to create the file.
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String filePath = outDir + "Audio.mp3";
        Assert.assertEquals(RMT2File.FILE_IO_EXIST, RMT2File.verifyFile(filePath));
        RMT2File.deleteFile(filePath);
    }
    
    @Test
    public void testSuccess_Add_Image_To_Database() {
        this.mockSingleMimeTypeList.add(this.mockMultipleMimeTypeList.get(1));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockSingleMimeTypeList);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        int contentId = 0;
        try {
            contentId = api.add("media/document/AdobeFile.pdf");
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(MediaMockDataFactory.TEST_CONTENT_ID, contentId);
    }
    
    @Test
    public void testError_Add_To_Database_SaveContent_DB_Access_Fault() throws Exception {
        this.mockSingleMimeTypeList.add(this.mockMultipleMimeTypeList.get(1));

        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockSingleMimeTypeList);
        
        when(this.mockPersistenceClient.insertRow(isA(Content.class), eq(true)))
                .thenThrow(new DatabaseException("A database error occurre"));
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.add("media/document/AdobeFile.pdf");
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to add media document as a database recrod or as an external file",
                    e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            String errMsg = "Error adding media meta data and content to the content table";
            Assert.assertEquals(errMsg, e.getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Add_To_Database_SaveContent_Null_ResultSet_Returned() throws Exception {
        this.mockSingleMimeTypeList.add(this.mockMultipleMimeTypeList.get(1));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
                 .thenReturn(this.mockSingleMimeTypeList);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        int rc = 0;
        try {
            rc = api.add("media/document/AdobeFile.pdf");
        }
        catch (Exception e) {
            Assert.fail("Did not expect an exception to occur");
        }
        
        Assert.assertEquals(MediaMockDataFactory.TEST_CONTENT_ID, rc);
    }
    
    @Test
    public void testValidation_Add_To_Database_Input_FilePath_Null() throws Exception {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.add(null);
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("File Path is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Add_To_Database_Input_File_NotFound() throws Exception {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.add("media/document/file_not_exist.pdf");
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof MediaModuleException);
            String errMsg = "Unable to add media document as a database recrod or as an external file due to input file cannot be located";
            Assert.assertEquals(errMsg, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof NotFoundException);
            Assert.assertEquals("media/document/file_not_exist.pdf is not found", e.getCause().getMessage());
        }
    }
    
    @Test
    public void testValidation_Add_To_Database_Input_File_No_Extension() throws Exception {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.add("media/document/FileNoExt");
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            String errMsg = "File name is required to have an extension for the purposes of identifying/validationg the MIME type";
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Add_To_Database_Input_FilE_Invalid_Extension() throws Exception {
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class))).thenReturn(null);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.add("media/document/InvalidFileExt.123");
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            String errMsg = "File, InvalidFileExt.123, was not persisted due to file extension is not registered in the MIME database";
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Delete_From_Database_Only() {
        when(this.mockPersistenceClient.deleteRow(isA(Content.class))).thenReturn(1);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        int rows = 0;
        try {
            rows = api.delete(MediaMockDataFactory.TEST_CONTENT_ID);
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(1, rows);
    }
    
    @Test
    public void testError_Delete_From_Database_Only_Database_Access_Fault() {
        when(this.mockPersistenceClient.deleteRow(isA(Content.class)))
                .thenThrow(new DatabaseException("A database error occurred"));
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.delete(MediaMockDataFactory.TEST_CONTENT_ID);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to delete media document identified by document id: "
                            + MediaMockDataFactory.TEST_CONTENT_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            Assert.assertEquals("Error deleting media content from the content table [contentId="
                            + MediaMockDataFactory.TEST_CONTENT_ID + "]",
                    e.getCause().getMessage());
        }
    }
    
    @Test
    public void testValidation_Delete_Zero_ContentId() {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.delete(0);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Content Id must be greater than zero", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Delete_Negative_ContentId() {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.delete(-1234);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Content Id must be greater than zero", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Delete_From_Database_And_FileSystem() {
        // Create real file to be deleted from the file system.
        byte[] expectedImage = RMT2File.getFileContentsAsBytes("media/document/image.jpg");
        String filePath = outDir + "@@@image.jpg";
        RMT2File.outputFile(expectedImage, filePath);
        
        this.mockSingleContent.setFilepath(outDir);
        this.mockSingleContent.setFilename("@@@image.jpg");
        this.mockSingleContent.setImageData(null);
        when(this.mockPersistenceClient.retrieveObject(isA(Content.class)))
                .thenReturn(this.mockSingleContent);
        when(this.mockPersistenceClient.deleteRow(isA(Content.class))).thenReturn(1);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test constructor which targets the file system DAO implementation.
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        int rows = 0;
        try {
            rows = api.delete(MediaMockDataFactory.TEST_CONTENT_ID);
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(2, rows);
    }
    
    @Test
    public void testError_Delete_From_Database__And_FileSystem_Database_Access_Fault() {
        when(this.mockPersistenceClient.deleteRow(isA(Content.class)))
                .thenThrow(new DatabaseException("A database error occurred"));
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        try {
            api.delete(MediaMockDataFactory.TEST_CONTENT_ID);
            Assert.fail("An exception was expected to be thrown");
        }
        catch (Exception e) {
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to delete media document identified by document id: "
                            + MediaMockDataFactory.TEST_CONTENT_ID, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            Assert.assertEquals("Error deleting media content from the content table [contentId="
                            + MediaMockDataFactory.TEST_CONTENT_ID + "]",
                    e.getCause().getMessage());
        }
    }
}