package org.modules.services.document.directory;

import org.apache.log4j.Logger;
import org.modules.services.document.DocumentProcessingService;

import com.RMT2Base;

/**
 * Listener for processing media files in the designated drop directory and
 * adding them to the mime database.
 * 
 * @author Roy Terrell
 * 
 */
public class DirectoryInboundDocumentListener extends RMT2Base implements Runnable {
    private static Logger logger = Logger.getLogger(DirectoryInboundDocumentListener.class);

    private volatile boolean continueToRun;

    private DocumentProcessingService srvc;

    /**
     * Creates an DocumentInboundDirectoryListener object initialized with one
     * or more application module configurations.
     */
    public DirectoryInboundDocumentListener() {
        this.init();
        DirectoryInboundDocumentListener.logger.info("Logger initialized");
        return;
    }

    /**
     * Get MIME configuration from MimeConfig.properties (Each application
     * should provide this file)
     */
    public void init() {
        logger.info("Begin Inbound Directory Listener Logging...");

        DirectoryInboundDocumentListener.logger.info("Initializing media file listener");
        this.srvc = new DocumentProcessingService();
        this.continueToRun = true;
    }

    /**
     * A separate thread for processing media files in the designated drop
     * directory.
     * <p>
     * First, It checks if the archive directory is locatable and is accessible.
     * If not accessible, then the thread is aborted. Next, an indefinite loop
     * is entered which processes one or more files in the inbound directory
     * that may available, and then goes into sleep mode for the amount of time
     * specified in the application's media file configuration.
     */
    public void run() {
        DirectoryInboundDocumentListener.logger.info("Starting media file listener");

        // More than likely the archive destination is a network share. Verify
        // if user has the correct permissions to access archive share.
        if (!this.srvc.isMediaFileDirAccessible()) {
            return;
        }
        DirectoryInboundDocumentListener.logger.info("Media file listener started successfully");

        while (this.continueToRun) {
            try {
                this.srvc.processMultiMediaFiles();
            } catch (Exception e) {
                this.msg = "Error occurred processing media files in drop directory";
                DirectoryInboundDocumentListener.logger.error(this.msg, e);
            }

            // Put the thread to sleep...
            try {
                DirectoryInboundDocumentListener.logger.info("Media file listener sleeping...");
                Thread.sleep(this.srvc.getDirectoryPollFrequency());
            } catch (InterruptedException e) {
                // Do nothing...This thread sleep was interrupted by another
                // thread.
            }
        }

        DirectoryInboundDocumentListener.logger.info("Media file listener has stopped");
    }

    /**
     * Signals to the current thread to terminate processing loop.
     */
    public void stop() {
        this.continueToRun = false;
    }

    /**
     * Indicates whether or not the thread is still running.
     * 
     * @return boolean <i>true<i/> when thread is running and <i>false<i/> when
     *         thread has stopped
     */
    public boolean getStatus() {
        return this.continueToRun;
    }
}
