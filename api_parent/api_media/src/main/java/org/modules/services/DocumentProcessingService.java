package org.modules.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.modules.services.directory.DirectoryListenerConfigBean;
import org.modules.services.directory.DirectoryListenerConfigFactory;
import org.modules.services.directory.file.FileDropProcessingException;
import org.modules.services.directory.file.MediaFileFactory;
import org.modules.services.directory.file.MediaFileProcessor;

import com.RMT2Base;
import com.util.RMT2File;

/**
 * Servcie for processing media documents.
 * <p>
 * This process includes identifying the meta data, storing the document's meta
 * data and image data, and assoiciating the document with a particular
 * application module.
 * 
 * @author Roy Terrell
 * 
 */
public class DocumentProcessingService extends RMT2Base {
    private static Logger logger = Logger.getLogger(DocumentProcessingService.class);
    private DirectoryListenerConfigBean config;
    private MediaFileProcessor processor;

    /**
     * Creates an DocumentInboundDirectoryListener object initialized with one
     * or more application module configurations.
     */
    public DocumentProcessingService() {
        this.init();
        DocumentProcessingService.logger.info("Logger initialized");
        return;
    }

    /**
     * Get MIME configuration from MimeConfig.properties (Each application
     * should provide this file)
     */
    public void init() {
        logger.info("Initializing Document Service...");
        DocumentProcessingService.logger.info("Initializing media file listener");
        this.config = DirectoryListenerConfigFactory.getDocumentListenerConfigBeanInstance();
        processor = MediaFileFactory.createBatchFileProcessor();
    }

    /*
     * Verify if user has the correct permissions to access archive share. <p>
     * More than likely the archive destination is a network share.
     * 
     * @return true if user has permission to access the directory where the
     * incoming media files to be prcoessed are located.
     */
    public boolean isMediaFileDirAccessible() {
        // More than likely the archive destination is a network share. Verify
        // if user has the
        // correct permissions to access archive share.
        this.config.setArchiveLocal(true);
        if (this.config.getArchiveDir().startsWith("//")) {
            // This is a network share and not a directory that is local to this
            // machine
            if (!RMT2File.isNetworkShareAccessible(this.config.getArchiveDir())) {
                StringBuffer msgBuf = new StringBuffer();
                msgBuf.append("Media file thread failed to start.   The media file archive destination, ");
                msgBuf.append(this.config.getArchiveDir());
                msgBuf.append(", is a network share and is inaccessible to the user account currently logged on to this machine.  Check permissions for this network share.");
                DocumentProcessingService.logger.fatal(msgBuf.toString());
                // Abort thread.
                return false;
            }
            this.config.setArchiveLocal(false);
            logger.info("Connecting to shared resource, " + config.getArchiveDir() + ", to archive images remotely");
        }
        return true;
    }

    /**
     * Uses the <i>MediaFileDaoProcessor</i> to process any media files that may
     * exist in the designated drop directory.
     * 
     * @return The total number of files processed.
     * @throws FileDropProcessingException
     */
    public int processMultiMediaFiles() throws FileDropProcessingException {
        int fileCount = 0;
        try {
            DocumentProcessingService.logger.info("Begin Multi Media file batch processing...");
            this.processor.initConnection();
            fileCount = this.processor.processBatch();
            return fileCount;
        } catch (Exception e) {
            DocumentProcessingService.logger.error(e);
            throw new FileDropProcessingException(e);
        } finally {
            if (this.processor != null) {
                this.processor.close();
            }
        }
    }

    /**
     * Return error messages that may have been produced from the batch operation
     * 
     * @return List<String> instance.  Should never return null.
     */
    public List<String> getBatchErrorMessages() {
        return processor.getErrorMessages();
    }
    
    /**
     * Return the directory poll frequency
     * 
     * @return int
     */
    public int getDirectoryPollFrequency() {
        return this.config.getPollFreq();
    }
}
