package org.rmt2.api.service.document;

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
import org.modules.services.document.DocumentProcessingService;
import org.modules.services.document.directory.file.BatchMediaFileProcessorImpl;
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
import com.api.messaging.webservice.router.MessageRoutingException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
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
        
        // Clear out the archive directory
        String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.archiveDir");
        List<String> listing = RMT2File.getDirectoryListing(destDir, "*.*");
        for (String file : listing) {
            logger.info("Deleting files in Archive directory");
            RMT2File.deleteFile(destDir + file);   
        }
        
        return;
    }

   private void copyFilesToDataDir() throws Exception {
       String OS = System.getProperty("os.name").toLowerCase();
       boolean win = (OS.indexOf("win") >= 0);
       String srcDir = RMT2File.getCurrentDirectory();
       logger.info("Listener Data Dir: " + srcDir);
       if (win) {
           srcDir += "\\src\\test\\resources\\media\\document\\listener\\";
       }
       else {
           srcDir += "/src/test/resources/media/document/listener/";
       }
       logger.info("Listener Data Dir after UNC conversion: " + srcDir);
       
       // TODO:  Write a utility method in core to handle copy files with wildcards
       String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
       List<String> listing = RMT2File.getDirectoryListing(srcDir, "*.*");
       for (String file : listing) {
           logger.info("Copying file: " + srcDir + file + "...");
           RMT2File.copyFileWithOverwrite(srcDir + file, destDir);    
       }
   }
   
   private void copyErrorFileToDataDir(String fileName) throws Exception {
       String OS = System.getProperty("os.name").toLowerCase();
       boolean win = (OS.indexOf("win") >= 0);
       String srcDir = RMT2File.getCurrentDirectory();
       logger.info("Listener Data Dir: " + srcDir);
       if (win) {
           srcDir += "\\src\\test\\resources\\media\\document\\listener\\bad_files\\";
       }
       else {
           srcDir += "/src/test/resources/media/document/listener/bad_files/";
       }
       logger.info("Listener Data Dir after UNC conversion: " + srcDir);
       
       String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
       logger.info("Copying Bad file: " + srcDir + fileName + "...");
       RMT2File.copyFileWithOverwrite(srcDir + fileName, destDir);   
   }
   
    @Test
    public void testSuccess_Multi_File_Processor() {
        int fileCount = 0;
        try {
            this.copyFilesToDataDir();
            DocumentProcessingService dps = new DocumentProcessingService();
            fileCount = dps.processMultiMediaFiles();
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(5, fileCount);

        // Verify that files have been deleted from the Inbound directory
        String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
        List<String> listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(0, listing.size());
        
        // Verify that files have been copied to the archive directory
        destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.archiveDir");
        listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(5, listing.size());
    }
    
    @Test
    public void testValidation_Multi_File_Processor_No_Separators_In_FileName() {
        int fileCount = 0;
        DocumentProcessingService dps = null;
        try {
            this.copyErrorFileToDataDir("acctcd113.jpg");
            dps = new DocumentProcessingService();
            fileCount = dps.processMultiMediaFiles();
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(1, fileCount);

        // Verify that files have been deleted from the Inbound directory
        String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
        List<String> listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(0, listing.size());
        
        // Verify that files have been copied to the archive directory
        destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.archiveDir");
        listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(1, listing.size());
        
        // Verfify error message was added.
        List<String> errors = dps.getBatchErrorMessages();
        Assert.assertEquals(1, errors.size());
        String msg = "Inbound File Name Validation Error: [acctcd113.jpg] The underscore character must be used as a separator in the file name"; 
        Assert.assertEquals(msg, errors.get(0));
    }
    
    @Test
    public void testValidation_Multi_File_Processor_FileName_Invalid_Format() {
        int fileCount = 0;
        DocumentProcessingService dps = null;
        try {
            this.copyErrorFileToDataDir("acct_cd_114_xxx.jpg");
            dps = new DocumentProcessingService();
            fileCount = dps.processMultiMediaFiles();
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(1, fileCount);

        // Verify that files have been deleted from the Inbound directory
        String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
        List<String> listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(0, listing.size());
        
        // Verify that files have been copied to the archive directory
        destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.archiveDir");
        listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(1, listing.size());
        
        // Verfify error message was added.
        List<String> errors = dps.getBatchErrorMessages();
        Assert.assertEquals(1, errors.size());
        String msg = "Inbound File Name Validation Error: [acct_cd_114_xxx.jpg] The file name must be in the format  <app_code>_<module>_<primary_key>.<file extension>"; 
        Assert.assertEquals(msg, errors.get(0));
    }
    
    @Test
    public void testValidation_Multi_File_Processor_FileName_NonNumeric_EntityId() {
        int fileCount = 0;
        DocumentProcessingService dps = null;
        try {
            this.copyErrorFileToDataDir("acct_cd_xxx.jpg");
            dps = new DocumentProcessingService();
            fileCount = dps.processMultiMediaFiles();
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(1, fileCount);

        // Verify that files have been deleted from the Inbound directory
        String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
        List<String> listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(0, listing.size());
        
        // Verify that files have been copied to the archive directory
        destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.archiveDir");
        listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(1, listing.size());
        
        // Verfify error message was added.
        List<String> errors = dps.getBatchErrorMessages();
        Assert.assertEquals(1, errors.size());
        String msg = "Inbound File Name Validation Error: [acct_cd_xxx.jpg] Failure to convert the primary key value [xxx] contained in the file name to a numeric value"; 
        Assert.assertEquals(msg, errors.get(0));
    }
    
    @Test
    public void testError_Multi_File_Processor_DB_Access_Fault() {
        // Setup stubs for meta data updates
        when(this.mockPersistenceClient.insertRow(isA(Content.class), eq(true)))
                .thenThrow(new DatabaseException("A database error occurred"));
        
        int fileCount = 0;
        DocumentProcessingService dps = null;
        try {
            this.copyErrorFileToDataDir("acct_cd_111.jpg");
            dps = new DocumentProcessingService();
            fileCount = dps.processMultiMediaFiles();
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(1, fileCount);

        // Verify that files have been deleted from the Inbound directory
        String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
        String inDir = destDir;
        List<String> listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(0, listing.size());
        
        // Verify that files have been copied to the archive directory
        destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.archiveDir");
        listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(1, listing.size());
        
        // Verfify error message was added.
        List<String> errors = dps.getBatchErrorMessages();
        Assert.assertEquals(1, errors.size());
        String msg = "Media file opertation error: Unable to persist media content and/or link media content to home application for file, " + inDir + "acct_cd_111.jpg"; 
        Assert.assertEquals(msg, errors.get(0));
    }
    
    @Test
    public void testError_Multi_File_Processor_WebService_Access_Fault() throws Exception {
        // Mock web service call to link media content with home application
        MessageRouterHelper mockMessageRouterHelper = Mockito.mock(MessageRouterHelper.class);
        PowerMockito.whenNew(MessageRouterHelper.class).withNoArguments().thenReturn(mockMessageRouterHelper);
        when(mockMessageRouterHelper.routeXmlMessage(isA(String.class),
                isA(MediaApplicationLinkRequest.class))).thenThrow(new MessageRoutingException("A web service error occurred"));
        
        int fileCount = 0;
        DocumentProcessingService dps = null;
        try {
            this.copyErrorFileToDataDir("acct_cd_111.jpg");
            dps = new DocumentProcessingService();
            fileCount = dps.processMultiMediaFiles();
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(1, fileCount);

        // Verify that files have been deleted from the Inbound directory
        String destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.inboundDir");
        String inDir = destDir;
        List<String> listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(0, listing.size());
        
        // Verify that files have been copied to the archive directory
        destDir = RMT2File.getPropertyValue("config.MimeConfig_TEST", "mime.archiveDir");
        listing = RMT2File.getDirectoryListing(destDir, "*.*");
        Assert.assertEquals(1, listing.size());
        
        // Verfify error message was added.
        List<String> errors = dps.getBatchErrorMessages();
        Assert.assertEquals(1, errors.size());
        String msg = "Media file opertation error: Unable to persist media content and/or link media content to home application for file, " + inDir + "acct_cd_111.jpg"; 
        Assert.assertEquals(msg, errors.get(0));
    }
}