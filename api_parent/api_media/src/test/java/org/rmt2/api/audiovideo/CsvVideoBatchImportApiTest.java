package org.rmt2.api.audiovideo;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvProject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.audiovideo.batch.AvBatchFileFactory;
import org.modules.audiovideo.batch.AvBatchFileProcessorApi;
import org.modules.audiovideo.batch.AvBatchImportParameters;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.BatchFileException;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.persistence.AbstractDaoClientImpl;
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
public class CsvVideoBatchImportApiTest extends AvMediaMockData {
    
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


    private void setupStubsForProcessingNewData() {
        when(this.mockPersistenceClient.insertRow(isA(AvArtist.class), eq(true)))
                .thenReturn(AvMediaMockDataFactory.TEST_ARTIST_ID);

        int projectId = AvMediaMockDataFactory.TEST_PROJECT_ID;
        when(this.mockPersistenceClient.insertRow(isA(AvProject.class), eq(true)))
                .thenReturn(projectId++, projectId++, projectId++);
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
        
        int results = 0;
        AvBatchFileProcessorApi api = null;
        try {
            AvBatchImportParameters parms = new AvBatchImportParameters();

            // Supply any value for "path" just to satisfy business rule. Test
            // will default to "video_batch_import.txt" file within the class
            // path.
            parms.setPath("dummy_path");
            api = AvBatchFileFactory.createCsvBatchImportApiInstance(parms);
            results = api.processBatch();
        }
        catch (BatchFileException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertNotNull(results);
        // Assert.assertEquals(3, results);
        Assert.assertTrue(results > 3);
        Assert.assertEquals(0, api.getErrorCount());
        Assert.assertEquals(results, api.getSuccessCount());
        Assert.assertNotNull(api.getErrorMessages());
        Assert.assertEquals(0, api.getErrorMessages().size());
    }
}