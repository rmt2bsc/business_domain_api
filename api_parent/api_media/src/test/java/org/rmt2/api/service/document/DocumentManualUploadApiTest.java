package org.rmt2.api.service.document;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.dto.ContentDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.document.DocumentContentApi;
import org.modules.document.DocumentContentApiFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.document.DocumentMediaMockData;
import org.rmt2.api.document.DocumentMediaMockDataFactory;

import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2File;

/**
 * Test the manual document uplaod functionality of the Media API Service
 * module.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        RMT2File.class, DocumentContentApi.class, SmtpFactory.class })
public class DocumentManualUploadApiTest extends DocumentMediaMockData {
    Logger logger = Logger.getLogger(DocumentManualUploadApiTest.class);
    
    private static final String CONFIG_FILE = "config.BatchImportConfig_TEST";
    private static final String TEST_FILENAME = "AdobeFile.pdf";
    private static String TEST_FILE_DIR;
    private byte[] fileContent;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        // Defaults to loading MimeConfig_TEST.properties
        
        // Setup stubs for meta data updates
        when(this.mockPersistenceClient.insertRow(isA(Content.class), eq(true)))
                .thenReturn(DocumentMediaMockDataFactory.TEST_CONTENT_ID);
        
        this.mockSingleMimeTypeList.add(this.mockMultipleMimeTypeList.get(1));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
                .thenReturn(this.mockSingleMimeTypeList);

        // Get test file contents
        TEST_FILE_DIR = this.getSourceDirectory();
        this.fileContent = RMT2File.getFileContentsAsBytes(TEST_FILE_DIR + TEST_FILENAME);
        
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

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private String getSourceDirectory() throws Exception {
       String OS = System.getProperty("os.name").toLowerCase();
       boolean win = (OS.indexOf("win") >= 0);
       String srcDir = RMT2File.getCurrentDirectory();
        logger.info("File Dir: " + srcDir);
       if (win) {
            srcDir += "\\src\\test\\resources\\media\\document\\";
       }
       else {
            srcDir += "/src/test/resources/media/document/";
       }
        logger.info("Test source file directory: " + srcDir);
       
        return srcDir;
   }
   
   
    @Test
    public void testSuccess_Add_FileContents() {
        ContentDto media = Rmt2MediaDtoFactory.getContentInstance("mime", "maint", TEST_FILENAME, this.fileContent);
        DocumentContentApi api = DocumentContentApiFactory.createMediaContentApi();
        int contentId = 0;
        try {
            contentId = api.add(media);
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(DocumentMediaMockDataFactory.TEST_CONTENT_ID, contentId);
    }

}