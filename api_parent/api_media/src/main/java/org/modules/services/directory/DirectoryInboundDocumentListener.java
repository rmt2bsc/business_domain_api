package org.modules.services.directory;

import org.apache.log4j.Logger;
import org.modules.services.DocumentProcessingService;

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

    private boolean continueToRun;

    private DocumentProcessingService srvc;
    
//    private DirectoryListenerConfigBean config;

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
//        this.config = DirectoryListenerConfigFactory.getConfigInstance();
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

//    /*
//     * Verify if user has the correct permissions to access archive share. <p>
//     * More than likely the archive destination is a network share.
//     * 
//     * @return true if user has permission to access the directory where the
//     * incoming media files to be prcoessed are located.
//     */
//    public boolean isMediaFileDirAccessible() {
//        // More than likely the archive destination is a network share. Verify
//        // if user has the
//        // correct permissions to access archive share.
//        this.config.setArchiveLocal(true);
//        if (this.config.getArchiveDir().startsWith("//")) {
//            // This is a network share and not a directory that is local to this
//            // machine
//            if (!RMT2File.isNetworkShareAccessible(this.config.getArchiveDir())) {
//                StringBuffer msgBuf = new StringBuffer();
//                msgBuf.append("Media file thread failed to start.   The media file archive destination, ");
//                msgBuf.append(this.config.getArchiveDir());
//                msgBuf.append(", is a network share and is inaccessible to the user account currently logged on to this machine.  Check permissions for this network share.");
//                DirectoryInboundDocumentListener.logger
//                        .fatal(msgBuf.toString());
//                // Abort thread.
//                return false;
//            }
//            this.config.setArchiveLocal(false);
//            logger.info("Connecting to shared resource, "
//                    + config.getArchiveDir() + ", to archive images remotely");
//        }
//        return true;
//    }
//
//    /**
//     * Uses the <i>MediaFileDaoProcessor</i> to process any media files that may
//     * exist in the designated drop directory.
//     * 
//     * @return The total number of files processed.
//     * @throws FileDropProcessingException
//     */
//    public int processMultiMediaFiles() throws FileDropProcessingException {
//        MediaFileDaoProcessor processor = MediaFileFactory.createBatchFileProcessor();
//        int fileCount = 0;
//        try {
//            DirectoryInboundDocumentListener.logger.info("Media file listener commencing batch process...");
//            processor.initConnection();
//            fileCount = processor.processBatch();
//            return fileCount;
//        } catch (Exception e) {
//            DirectoryInboundDocumentListener.logger.error(e);
//            throw new FileDropProcessingException(e);
//        } finally {
//            if (processor != null) {
//                processor.close();
//            }
//        }
//    }
//
//    public int processSingleMediaFiles(String fileName) throws MediaFileOperationDaoException {
//        if (fileName == null || fileName.equals(RMT2String.spaces(fileName.length()))) {
//            throw new MediaFileOperationDaoException("Input file name cannot be null or spaces");
//        }
//        MediaFileDaoProcessor processor = MediaFileFactory.createSingleFileProcessor();
//        int fileCount = 0;
//        try {
//            DirectoryInboundDocumentListener.logger.info("Media file listener looking for files to process...");
//            processor.initConnection();
//            Integer rc = (Integer) processor.processSingleFile(fileName, null);
//            fileCount = rc.intValue();
//            if (fileCount > 0) {
//                String msgCount = fileName + " media file was processed successfully";
//                DirectoryInboundDocumentListener.logger.info(msgCount);
//                // Attempt to send report.
//                if (this.config.isEmailResults()) {
//                    try {
//                        processor.sendDropReport();
//                    } catch (Exception e) {
//                        // Do nothing
//                    }
//                }
//            }
//            return fileCount;
//        } catch (Exception e) {
//            DirectoryInboundDocumentListener.logger.error(e);
//            throw new MediaFileOperationDaoException(e);
//        } finally {
//            if (processor != null) {
//                processor.close();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        DirectoryInboundDocumentListener l = new DirectoryInboundDocumentListener();
//        Thread t = new Thread(l);
//        t.start();
//    }

}
