package org.rmt2.api.document;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

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

import com.api.persistence.AbstractDaoClientImpl;
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
        Connection mockDbConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        
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
}