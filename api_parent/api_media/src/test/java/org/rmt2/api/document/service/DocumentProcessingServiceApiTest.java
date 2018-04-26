package org.rmt2.api.document.service;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.services.DocumentProcessingService;
import org.modules.services.directory.file.BatchMediaFileProcessorImpl;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.MediaMockData;
import org.rmt2.api.MediaMockDataFactory;
import org.rmt2.jaxb.MediaApplicationLinkRequest;

import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2File;

/**
 * Test the document processing service functionality of the Media API Service module.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        RMT2File.class, BatchMediaFileProcessorImpl.class, SmtpFactory.class })
public class DocumentProcessingServiceApiTest extends MediaMockData {
    private String outDir;
    
    Logger logger = Logger.getLogger(DocumentProcessingServiceApiTest.class);
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        // Defaults to loading MimeConfig_TEST.properties
        
        // Setup stubs for meta data updates
        when(this.mockPersistenceClient.insertRow(isA(Content.class), eq(true)))
                .thenReturn(MediaMockDataFactory.TEST_CONTENT_ID);
        
        this.mockSingleMimeTypeList.add(this.mockMultipleMimeTypeList.get(1));
        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
        .thenReturn(this.mockSingleMimeTypeList);
        
        // Mock web service call to link media content with home application
        MessageRouterHelper mockMessageRouterHelper = Mockito.mock(MessageRouterHelper.class);
        PowerMockito.whenNew(MessageRouterHelper.class).withNoArguments().thenReturn(mockMessageRouterHelper);
        when(mockMessageRouterHelper.routeXmlMessage(isA(String.class),
                isA(MediaApplicationLinkRequest.class))).thenReturn(new Object());
        
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

   private void copyFilesToDataDir() throws Exception {
       String OS = System.getProperty("os.name").toLowerCase();
       boolean win = (OS.indexOf("win") >= 0);
       String dir = RMT2File.getCurrentDirectory();
       logger.info("Listener Data Dir: " + dir);
       if (win) {
           dir += "\\src\\test\\resources\\media\\listener\\";
       }
       else {
           dir += "/src/test/resources/media/listener/";
       }
       logger.info("Listener Data Dir after UNC conversion: " + dir);
       
       // TODO:  Write a utility method in core to handle copy files with wildcards
       String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
       List<String> listing = RMT2File.getDirectoryListing(dir, "*.*");
       for (String file : listing) {
           logger.info("Copying file: " + dir + file + "...");
           RMT2File.copyFileWithOverwrite(dir + file, destDir);    
       }
   }
   
   
    @Test
    public void testSuccess_Multi_File_Processor() {
        int fileCount = 0;
        try {
            // this.copyFilesToDataDir();
            DocumentProcessingService dps = new DocumentProcessingService();
            fileCount = dps.processMultiMediaFiles();
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(5, fileCount);

        String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
        List<String> listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(0, listing.size());
//        Assert.assertTrue(Arrays.equals("ImageData".getBytes(), results.getImageData()));
    }
    
   
}