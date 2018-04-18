package org.rmt2.api.document;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

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
public class MediaDocumentQueryApiTest extends MediaMockData {
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
        
        // Setu stubs for fetching data successfully
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
    
   
}