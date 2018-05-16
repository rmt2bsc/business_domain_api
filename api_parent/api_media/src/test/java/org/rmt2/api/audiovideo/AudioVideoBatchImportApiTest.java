package org.rmt2.api.audiovideo;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.audiovideo.batch.AvBatchApiFactory;
import org.modules.audiovideo.batch.AvBatchFileProcessorApi;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.BatchFileException;
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
        
        when(this.mockPersistenceClient.retrieveList(isA(AvGenre.class)))
                .thenReturn(this.mockAvGenreData);
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
    public void testSuccess_Batch_Refresh() {
        // Setup mock stubs for creating new audio entries.
        this.setupStubsForProcessingNewData();
        // Get full directory path for relative path which resides on the classpath
        String dir = RMT2File.resolveRelativeFilePath(AvMediaMockDataFactory.TEST_AUDIO_DIR);
        
        AvBatchApiFactory f = new AvBatchApiFactory();
        AvBatchFileProcessorApi api = f.createApiInstance(dir);
        int results = 0;
        try {
            results = api.processBatch();
        }
        catch (BatchFileException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        Assert.assertEquals(6, results);
    }
    
  
   
}