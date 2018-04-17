package org.rmt2.api.document;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.dao.document.ContentDaoException;
import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
public class MediaDocumentApiTest extends MediaMockData {
    private String outDir;
    private Connection mockDbConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        mockDbConnection = Mockito.mock(Connection.class);
        mockStatement = Mockito.mock(Statement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        
        outDir = RMT2File.loadAppConfigProperties("config.Media-AppParms").getString("media_output_location");
        
        // Setuop stubs for meta data updates
        when(this.mockPersistenceClient.insertRow(isA(Content.class), eq(true)))
                .thenReturn(MediaMockDataFactory.TEST_CONTENT_ID);

        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
                .thenReturn(this.mockClientFetchMimeTypeMultiple);
        
        // Setup stubs for content updates to database
        when(this.mockPersistenceClient.getConnection()).thenReturn(mockDbConnection);
        when(mockDbConnection.createStatement(eq(ResultSet.TYPE_FORWARD_ONLY),
                eq(ResultSet.CONCUR_UPDATABLE))).thenReturn(mockStatement);
        when(mockStatement.executeQuery(isA(String.class))).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        doNothing().when(mockResultSet).updateBytes(eq("image_data"), isA(byte[].class));
        doNothing().when(mockResultSet).updateRow();
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
        this.mockClientFetchMimeTypeSingle.add(this.mockClientFetchMimeTypeMultiple.get(0));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockClientFetchMimeTypeSingle);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        int contentId = 0;
        try {
            contentId = api.add("media/image.jpg");
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
        this.mockClientFetchMimeTypeSingle.add(this.mockClientFetchMimeTypeMultiple.get(5));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockClientFetchMimeTypeSingle);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        int contentId = 0;
        try {
            contentId = api.add("media/Audio.mp3");
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
        this.mockClientFetchMimeTypeSingle.add(this.mockClientFetchMimeTypeMultiple.get(1));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockClientFetchMimeTypeSingle);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        int contentId = 0;
        try {
            contentId = api.add("media/AdobeFile.pdf");
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(MediaMockDataFactory.TEST_CONTENT_ID, contentId);
    }
    
    @Test
    public void testError_Add_To_Database_SaveMetaData_DB_Access_Fault() {
        this.mockClientFetchMimeTypeSingle.add(this.mockClientFetchMimeTypeMultiple.get(1));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockClientFetchMimeTypeSingle);
        
        when(this.mockPersistenceClient.insertRow(isA(Content.class), eq(true)))
        .thenThrow(new DatabaseException("A database access error occurred"));
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.add("media/AdobeFile.pdf");
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to add media document as a database recrod or as an external file", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            Assert.assertEquals("Error adding media meta data to the content table", e.getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Add_To_Database_SaveContent_DB_Access_Fault() throws Exception {
        this.mockClientFetchMimeTypeSingle.add(this.mockClientFetchMimeTypeMultiple.get(1));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockClientFetchMimeTypeSingle);
        
        when(mockStatement.executeQuery(isA(String.class)))
                .thenThrow(new SQLException("A database error occurre"));
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.add("media/AdobeFile.pdf");
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to add media document as a database recrod or as an external file",
                    e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            String errMsg = "Error occurred saving media binary data to the content table [contentId="
                    + MediaMockDataFactory.TEST_CONTENT_ID + "]";
            Assert.assertEquals(errMsg, e.getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Add_To_Database_SaveContent_DB_Access_Fault_Closing_Statement() throws Exception {
        this.mockClientFetchMimeTypeSingle.add(this.mockClientFetchMimeTypeMultiple.get(1));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockClientFetchMimeTypeSingle);
        
        doThrow(new SQLException("Error closing SQL statement")).when(mockStatement).close();
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.add("media/AdobeFile.pdf");
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof MediaModuleException);
            Assert.assertEquals("Unable to add media document as a database recrod or as an external file",
                    e.getMessage());
            Assert.assertTrue(e.getCause() instanceof ContentDaoException);
            String errMsg = "An error occurred attempting to close either SQL Statement or ResultSet objects";
            Assert.assertEquals(errMsg, e.getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Add_To_Database_SaveContent_Null_ResultSet_Returned() throws Exception {
        this.mockClientFetchMimeTypeSingle.add(this.mockClientFetchMimeTypeMultiple.get(1));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockClientFetchMimeTypeSingle);
        
        when(mockStatement.executeQuery(isA(String.class))).thenReturn(null);
        
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        int rc = 0;
        try {
            rc = api.add("media/AdobeFile.pdf");
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
            api.add("media/file_not_exist.pdf");
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof MediaModuleException);
            String errMsg = "Unable to add media document as a database recrod or as an external file due to input file cannot be located";
            Assert.assertEquals(errMsg, e.getMessage());
            Assert.assertTrue(e.getCause() instanceof NotFoundException);
            Assert.assertEquals("media/file_not_exist.pdf is not found", e.getCause().getMessage());
        }
    }
    
    @Test
    public void testValidation_Add_To_Database_Input_File_No_Extension() throws Exception {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        // Test default constructor which should employ the database DAO implementation.
        DocumentContentApi api = f.createMediaContentApi();
        try {
            api.add("media/FileNoExt");
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
            api.add("media/InvalidFileExt.123");
            Assert.fail("Expected an exception to occur");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            String errMsg = "File, InvalidFileExt.123, was not persisted due to file extension is not registered in the MIME database";
            Assert.assertEquals(errMsg, e.getMessage());
        }
    }
    
}