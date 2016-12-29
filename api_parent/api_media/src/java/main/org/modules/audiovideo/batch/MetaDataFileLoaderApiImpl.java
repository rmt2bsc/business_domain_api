package org.modules.audiovideo.batch;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.NullEnumeration;
import org.dao.audiovideo.AudioVideoConstants;
import org.dao.audiovideo.AudioVideoDao;
import org.dao.audiovideo.AudioVideoDaoException;
import org.dao.audiovideo.AudioVideoDaoFactory;
import org.dao.audiovideo.AvCombinedProjectBean;
import org.dao.audiovideo.AvFileExtractionException;
import org.dao.audiovideo.AvInvalidSourceFileException;
import org.dao.audiovideo.AvProjectDataValidationException;
import org.dao.audiovideo.AvTrackDataValidationException;
import org.dao.audiovideo.MP3ApiInstantiationException;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import com.RMT2Constants;
import com.SystemException;

import com.api.BatchFileException;

import com.api.config.ConfigConstants;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.foundation.TransactionApi;

import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;

import com.api.persistence.DatabaseException;

import com.util.RMT2Date;
import com.util.RMT2File;

/**
 * An file loader implementation of {@link AudioVideoBatchFileProcessorApi}
 * which extracts meta data from audio/video files and imports the data to
 * various tables in the database.
 * <p>
 * The tables targeted for the data import are <i>av_artist</i>,
 * <i>av_project</i>, and <i>av_trackes</i>.
 * 
 * @author Roy Terrell
 * 
 */
class MetaDataFileLoaderApiImpl extends AbstractTransactionApiImpl implements
        AudioVideoBatchFileProcessorApi, TransactionApi {

    private static Logger logger = Logger
            .getLogger(MetaDataFileLoaderApiImpl.class);

    private File resourcePath;

    private List<String> fileErrorMsg;

    private Date startTime;

    private Date endTime;

    protected int successCnt;

    protected int errorCnt;

    protected int nonAvFileCnt;

    protected int totCnt;

    private int expectedFileCount;

    /**
     * Creates a MetaDataFileLoaderApiImpl that does no point to a source
     * directory for batch.
     * 
     * @throws BatchFileProcessException
     */
    protected MetaDataFileLoaderApiImpl() throws BatchFileProcessException {
        super();
        return;
    }

    /**
     * Creates a MetaDataFileLoaderApiImpl pointing to the source directory for
     * batch processing audio/video files.
     * <p>
     * User is responsible for providing the directory where batch processing
     * starts.
     * 
     * @param dirPath
     *            the complete path where to start processing audio/video files
     * @throws BatchFileProcessException
     */
    protected MetaDataFileLoaderApiImpl(String dirPath)
            throws BatchFileProcessException {
        this();
        this.initConnection(dirPath);
        MetaDataFileLoaderApiImpl.logger
                .info("Audio/Video batch processor is initialized");
    }

    /**
     * Setup connection for an arbitrary external datasource which the
     * configuration is known at implementation.
     * 
     * @param dirPath
     * 
     * @throws BatchFileProcessException
     */
    public void initConnection(Object dirPath) throws BatchFileProcessException {
        if (dirPath == null) {
            this.msg = "The root directory path is invalid or null";
            MetaDataFileLoaderApiImpl.logger.error(this.msg);
            throw new InvalidBatchRootDirectoryException(this.msg);
        }
        if (!(dirPath instanceof String)) {
            this.msg = "The root directory path must be of String datatype";
            MetaDataFileLoaderApiImpl.logger.error(this.msg);
            throw new InvalidBatchRootDirectoryException(this.msg);
        }
        this.resourcePath = new File(dirPath.toString());
        this.successCnt = 0;
        this.errorCnt = 0;
        this.totCnt = 0;
        this.nonAvFileCnt = 0;
        this.fileErrorMsg = new ArrayList<String>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#close()
     */
    @Override
    public void close() {
        return;
    }

    /**
     * Creates an audoi/video library (audio_video and audio_video_tracks
     * tables) from the files stored locally or remotely. Returns the total
     * number of tracks successfull processed. Data from the tables audio_video
     * and audio_video_tracks are deleted before processing artist's
     * directories.
     * 
     * @throws AudioVideoException
     * @throws AvBatchValidationException
     */
    public int processBatch() throws BatchFileProcessException {
        boolean useLogger = true;
        Enumeration<Object> enm = logger.getAllAppenders();
        if (enm != null && enm instanceof NullEnumeration) {
            useLogger = false;
        }

        this.startTime = new Date();
        this.validate();

        AudioVideoDaoFactory f = new AudioVideoDaoFactory();
        AudioVideoDao dao = f.createRmt2OrmDaoInstance();
        dao.setDaoUser(this.getApiUser());

        // Begin process all files
        try {
            this.expectedFileCount = dao
                    .computeTotalFileCount(this.resourcePath);
            this.msg = "Audio/Video Batch Update process started ["
                    + this.expectedFileCount + " files discovered]...";
            if (useLogger) {
                MetaDataFileLoaderApiImpl.logger.info(this.msg);
            }
            else {
                System.out.println(this.msg);
            }
            this.processDirectory(this.resourcePath, null);
            return this.totCnt;
        } catch (BatchFileProcessException e) {
            throw e;
        } finally {
            dao.close();
            dao = null;
            this.endTime = new Date();
            if (useLogger) {
                MetaDataFileLoaderApiImpl.logger.info("Batch start time: "
                        + startTime.toString());
                MetaDataFileLoaderApiImpl.logger.info("Batch end time: "
                        + endTime.toString());
                MetaDataFileLoaderApiImpl.logger
                        .info("Total Media Files Processed: " + this.totCnt);
                MetaDataFileLoaderApiImpl.logger
                        .info("Total Media Files Successfully Processed: "
                                + this.successCnt);
                MetaDataFileLoaderApiImpl.logger
                        .info("Total Media Files Unsuccessfully Processed: "
                                + this.errorCnt);
                MetaDataFileLoaderApiImpl.logger
                        .info("Total Non-Audio/Video Files encountered: "
                                + this.nonAvFileCnt);
                MetaDataFileLoaderApiImpl.logger.info("End Audio-Video Update");
            }
            else {
                System.out.println("Batch start time: " + startTime.toString());
                System.out.println("Batch end time: " + endTime.toString());
                System.out.println("Total Media Files Processed: "
                        + this.totCnt);
                System.out.println("Total Media Files Successfully Processed: "
                        + this.successCnt);
                System.out
                        .println("Total Media Files Unsuccessfully Processed: "
                                + this.errorCnt);
                System.out.println("Total Non-Audio/Video Files encountered: "
                        + this.nonAvFileCnt);
                System.out.println("End Audio-Video Update");
            }

            // Send batch report via SMTP
            try {
                this.sendAvBatchReport();
            } catch (AvBatchReportException e) {
                logger.error("Audio/Video Batch Report Failed...See Log for details");
            }
        }

    }

    /**
     * Verifies that the resource starting point for this batch process is a
     * directory in the file system.
     * 
     * @throws AvSourceNotADirectoryException
     */
    public void validate() throws BatchFileProcessException {
        if (!this.resourcePath.isDirectory()) {
            this.msg = resourcePath
                    + " is required to be a directory for Audio Video Batch process";
            MetaDataFileLoaderApiImpl.logger.log(Level.ERROR, this.msg);
            throw new AvSourceNotADirectoryException(this.msg);
        }
    }

    /**
     * Process all the high level audio/video artist directories.
     * 
     * @param mediaResource
     *            an instance of {@link File} which is required to point to the
     *            directory holding the media files to be processed.
     * @return Always returns an {@link Integer} object which equals "1".
     * @throws BatchFileProcessException
     */
    public Object processDirectory(File mediaResource, Object parent)
            throws BatchFileProcessException {
        File mediaList[];
        int itemCount = 0;

        try {
            mediaList = mediaResource.listFiles();
            itemCount = mediaList.length;
            for (int ndx = 0; ndx < itemCount; ndx++) {
                if (mediaList[ndx].isDirectory()) {
                    // Make recursive call to process next level
                    this.processDirectory(mediaList[ndx], null);
                }
                if (mediaList[ndx].isFile()) {
                    this.processSingleFile(mediaList[ndx], null);
                }
            }
            return 1;
        } catch (SecurityException e) {
            throw new BatchFileProcessException(e);
        } catch (MP3ApiInstantiationException e) {
            throw new BatchFileProcessException(e);
        }
    }

    /**
     * Initiates the media file data extraction process.
     * 
     * @param mediaFile
     *            An instance of {@link File} which points to the media file to
     *            be processed
     * @return Always returns a instance of {@link Integer} which equals "1".
     * @throws MP3ApiInstantiationException
     * @throws BatchFileProcessException
     */
    public Object processSingleFile(File mediaFile, Object parent)
            throws BatchFileProcessException {
        String pathName;
        AvCombinedProjectBean avb;
        AudioVideoDaoFactory f = new AudioVideoDaoFactory();
        AudioVideoDao avDao = f.createRmt2OrmDaoInstance();
        avDao.setDaoUser(this.getApiUser());

        pathName = mediaFile.getPath();
        logger.log(Level.DEBUG, "Processing File: " + pathName);
        try {
            avb = avDao.extractFileMetaData(mediaFile);
            if (avb != null) {
                avDao.addAudioVideoFileData(avb);
            }
            this.successCnt++;
        } catch (MP3ApiInstantiationException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName,
                    "Error creating MP3 Api", e.getMessage()));
            // throw new MP3ApiInstantiationException(e);
            this.nonAvFileCnt++;
        } catch (AvInvalidSourceFileException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName,
                    "Invalid Audio/Video Source File", e.getMessage()));
            this.errorCnt++;
        } catch (AvProjectDataValidationException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName,
                    "Audio/Video Project Data Validation", e.getMessage()));
            this.errorCnt++;
        } catch (AvTrackDataValidationException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName,
                    "Audio/Video Track Data Validation", e.getMessage()));
            this.errorCnt++;
        } catch (SystemException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName,
                    "Audio/Video System", e.getMessage()));
            this.errorCnt++;
        } catch (AvFileExtractionException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName,
                    "Audio/Video Source File Data Extraction", e.getMessage()));
            this.errorCnt++;
        } catch (AudioVideoDaoException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName,
                    "General Audio/Video", e.getMessage()));
            this.errorCnt++;
            e.printStackTrace();
        } catch (DatabaseException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName,
                    "Audio/Video Database Access", e.getMessage()));
            this.errorCnt++;
        } finally {
            this.totCnt++;
            if (avDao != null) {
                avDao.close();
                avDao = null;
            }
        }
        return 1;
    }

    /**
     * 
     * @param fileName
     * @param msgCatg
     * @param msg
     * @return
     */
    private String buildFileErrorMessage(String fileName, String msgCatg,
            String msg) {
        StringBuffer errMsg = new StringBuffer();
        errMsg.append("File: ");
        errMsg.append(fileName);
        errMsg.append("\n");
        errMsg.append("Error Category: ");
        errMsg.append(msgCatg);
        errMsg.append("\n");
        errMsg.append("Cause: ");
        if (msg == null) {
            msg = "Unknown";
        }
        errMsg.append(msg);
        errMsg.append("\n");

        String m = errMsg.toString();
        System.out.println("Error ===> " + m);
        logger.log(Level.ERROR, "Error ===> " + m);
        return m;
    }

    /**
     * Creates a report detailing the success or failure of all the files
     * processed and transmits the report via SMTP to the user designated as the
     * application's email recipient.
     * 
     * @throws AvBatchReportException
     *             problem occurred creaing or sending file drop report via
     *             SMTP.
     */
    private void sendAvBatchReport() throws AvBatchReportException {
        StringBuffer body = new StringBuffer();

        // Attempt to obtain From email address from the application's property
        // pool which is loaded at server start up.
        String fromAddr = null;
        try {
            fromAddr = RMT2File.getPropertyValue(ConfigConstants.CONFIG_APP,
                    AudioVideoConstants.BATCH_REPORT_EMAIL);
        } catch (Exception e) {
            this.msg = "Unable to obtain the recipient's email address from AppParms.properties needed to send batch report";
            throw new AvBatchReportException(this.msg, e);
        }
        if (fromAddr == null) {
            this.msg = "Unable to send batch report due to the recipient's email address is not available.   Check configuation.";
            throw new AvBatchReportException(this.msg);
        }
        String toAddr = fromAddr;
        String subject = "Audio/Video Batch Report";

        body.append("This is a report of the results of the Audio/Video Batch process\n");
        body.append("Start Time: ");
        body.append(RMT2Date
                .formatDate(this.startTime, "MM-dd-yyyy HH:mm:ss.S"));
        body.append("\n");
        body.append("End Time: ");
        body.append(RMT2Date.formatDate(this.endTime, "MM-dd-yyyy HH:mm:ss.S"));
        body.append("\n\n\n");

        body.append("Total Media Files Processed: " + this.totCnt);
        body.append("\n");
        body.append("Total Media Files Successfully Processed: "
                + this.successCnt);
        body.append("\n");
        body.append("Total Media Files Unsuccessfully Processed: "
                + this.errorCnt);
        body.append("\n\n\n");

        if (this.fileErrorMsg.size() > 0) {
            body.append("Detail report of files that failed during this batch process");
            body.append("\n");
            body.append("=============================================================");
            body.append("\n");
        }
        int count = 0;
        // Add details about each file that was processed
        for (String msg : this.fileErrorMsg) {
            count++;
            body.append(count);
            body.append(".  ");
            body.append(msg);
            body.append("\n");
        }

        // Setup bean that represents the email message.
        EmailMessageBean bean = new EmailMessageBean();
        bean.setFromAddress(fromAddr);

        // You can optionally enter multiple email addresses separated by commas
        bean.setToAddress(toAddr);
        bean.setSubject(subject);
        bean.setBody(body.toString(), EmailMessageBean.TEXT_CONTENT);

        // Declare and initialize SMTP api and allow the system to discover SMTP
        // host
        SmtpApi api = SmtpFactory.getSmtpInstance();
        // Send simple email to its intended destination
        try {
            api.sendMessage(bean);
            // Close the service.
            api.close();
        } catch (Exception e) {
            throw new AvBatchReportException(e);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#getFileListing()
     */
    public List<String> getFileListing() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#initConnection()
     */
    public void initConnection() throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#initConnection(java.lang.String,
     * java.lang.String)
     */
    public void initConnection(String arg0, String arg1)
            throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#processFiles(java.util.List)
     */
    public Object processFiles(List<String> arg0, Object parent)
            throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#processSingleFile(java.lang.String)
     */
    public Object processSingleFile(String arg0, Object parent)
            throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
