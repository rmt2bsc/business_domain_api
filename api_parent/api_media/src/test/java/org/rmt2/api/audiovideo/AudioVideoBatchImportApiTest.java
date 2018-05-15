package org.rmt2.api.audiovideo;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dao.mapping.orm.rmt2.Content;
import org.dto.ContentDto;
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
import org.rmt2.api.document.DocumentMediaMockDataFactory;

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
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, RMT2File.class })
public class AudioVideoBatchImportApiTest extends AvMediaMockData {
    
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    private void setupStubsForProcessingNewData() {
        when(this.mockPersistenceClient.retrieveList(isA(AvArtist.class))).thenReturn(null);

        int artist = AvMediaMockDataFactory.TEST_ARTIST_ID;
        when(this.mockPersistenceClient.insertRow(isA(AvArtist.class),
                eq(true))).thenReturn(artist++, artist++, artist++, artist++, artist++, artist);

        when(this.mockPersistenceClient.retrieveList(isA(AvProject.class)))
                .thenReturn(null, null, null, this.mockAvProjectData, null, this.mockAvProjectData);

        int projectId = AvMediaMockDataFactory.TEST_PROJECT_ID;
        when(this.mockPersistenceClient.insertRow(isA(AvProject.class),
                eq(true))).thenReturn(projectId++, projectId++, projectId++, projectId);
        
        int trackId = AvMediaMockDataFactory.TEST_TRACK_ID;
        when(this.mockPersistenceClient.insertRow(isA(AvTracks.class),
                eq(true))).thenReturn(trackId++, trackId++, trackId++, trackId++, trackId++, trackId);
        
    }
    
    private void setupStubsForProcessingExistingData() {
        when(this.mockPersistenceClient.updateRow(isA(AvArtist.class)))
                .thenReturn(1);
        when(this.mockPersistenceClient.updateRow(isA(AvProject.class))).thenReturn(1);
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
            results = api.get(DocumentMediaMockDataFactory.TEST_CONTENT_ID);
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
            results = api.get(DocumentMediaMockDataFactory.TEST_CONTENT_ID);
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNull(results);
    }
    
   
}