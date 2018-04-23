package org.modules.services.directory.file;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dao.document.ContentDao;
import org.dao.document.ContentDaoFactory;
import org.dto.ContentDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.modules.services.directory.ApplicationModuleBean;
import org.modules.services.directory.DirectoryListenerConfigBean;
import org.modules.services.directory.DirectoryListenerConfigFactory;
import org.rmt2.constants.ApiTransactionCodes;
import org.rmt2.jaxb.HeaderType;
import org.rmt2.jaxb.MediaApplicationLinkRequest;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.util.JaxbPayloadFactory;

import com.SystemException;
import com.api.BatchFileException;
import com.api.config.ConfigConstants;
import com.api.foundation.TransactionApiException;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.messaging.webservice.router.MessageRoutingException;
import com.util.RMT2Date;
import com.util.RMT2File;
import com.util.RMT2String;
import com.util.RMT2Utility;

/**
 * An implementation of {@link MediaFileDaoProcessor} for processing multiple
 * media files in a single batch.
 * <p>
 * Generally the file will be located in the directory designated as the inbound
 * directory in <i>MimeConfig_XXX.properties</i> file.
 * 
 * @author rterrell
 * 
 */
public class BatchMediaFileProcessorImpl extends AbstractMediaFileProcessorImpl {

    private static Logger logger = Logger.getLogger(BatchMediaFileProcessorImpl.class);

    private DirectoryListenerConfigBean config;

    private List<String> mimeMsgs;

    private int moduleId;

    private Date startTime;

    private Date endTime;

    /**
     * Default constructor which sets up or obtains the configuration for the
     * media file environment.
     * <p>
     * This class depends on the existence of a <i>MimeConfig_XXX.properties</i>
     * file containing the necessary configuration information for batch
     * processing media files. It expects to pickup one or more files from a
     * inbound directory specified in the <i>MimeConfig_XXX.properties</i>,
     * persist the media document record to the <i>document</i> table for each
     * media file, link the media document to some arbitrary record of the Home
     * application, archive the source data file, and send a file drop report to
     * the user designated in the home application's MIME configuration.
     */
    public BatchMediaFileProcessorImpl() {
        super();
        try {
            this.config = DirectoryListenerConfigFactory.getDocumentListenerConfigBeanInstance();
        } catch (Exception e) {
            this.msg = "Error instantiating BatchMediaFileProcessorDaoImpl object";
            throw new SystemException(this.msg, e);
        }

        // Initialize MIME message collection
        this.mimeMsgs = new ArrayList<String>();
        return;
    }

    /**
     * Setup conntection to the Home Application DAO.
     * 
     * @throws BatchFileException
     *             when the attempt to establish a connection for either system
     *             fails.
     */
    public synchronized void initConnection() throws BatchFileException {
        return;
    }

    /**
     * Obtains a list of file names from the file system based on the wildcard
     * specifications configured for each application module via the internal
     * MIME configuration.
     * 
     * @return List<String> a list of String values representing file names
     * @throws SystemException
     *             when the internal MIME configuration is invalid or null
     */
    public synchronized List<String> getFileListing() {
        if (this.config == null) {
            this.msg = "Media file application configuration instance is invalid or null";
            throw new SystemException(this.msg);
        }

        // Get file pattern by targeting the module using module id
        ApplicationModuleBean mod = this.config.getModules().get(this.moduleId);
        StringBuffer wildcards = new StringBuffer();
        wildcards.append(mod.getFilePattern());
        List<String> fileListing = RMT2File.getDirectoryListing(config.getInboundDir(), wildcards.toString());
        return fileListing;
    }

    /**
     * Checks the Inbound directory for available files regardless of the
     * modules configured.
     * 
     * @return true when files are availabe, and false otherwise.
     */
    public boolean isFilesAvailable() {
        if (this.config == null) {
            return false;
        }
        // See if one or more files exist in the Inbound directory
        List<String> fileListing = RMT2File.getDirectoryListing(config.getInboundDir(), null);
        return fileListing.size() > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#processBatch()
     */
    @Override
    public int processBatch() throws BatchFileException {
        int fileCount = 0;
        try {
            BatchMediaFileProcessorImpl.logger.info("Media file batch process looking for files in inbound directory, "
                            + this.config.getInboundDir());
            if (this.isFilesAvailable()) {
                Integer rc = (Integer) this.processFiles(null, null);
                fileCount = rc.intValue();
                if (fileCount > 0) {
                    String msgCount = fileCount + " media files in designated inbound directory were processed for all modules";
                    BatchMediaFileProcessorImpl.logger.info(msgCount);
                    // Attempt to send report.
                    if (this.config.isEmailResults()) {
                        try {
                            this.sendDropReport();
                        } catch (Exception e) {
                            // Do nothing
                        }
                    }
                }
            }
            else {
                BatchMediaFileProcessorImpl.logger.info("No files available for media file thread to process!");
            }
            return fileCount;
        } catch (Exception e) {
            BatchMediaFileProcessorImpl.logger.error(e);
            throw new FileDropProcessingException(e);
        }
    }

    /**
     * Traverses the list of file names in <i>files</i> adding the document of
     * each file to the MIME database and assoicating the MIME document record
     * with the targeted record of the home application. This method is the
     * driver of concept of processing the files dropped in the Inbound
     * directory.
     * 
     * @param files
     *            a List<String> of fiel names to process
     * @return int a count of all the files processed.
     * @throws BatchFileException
     *             file validation errors and general database errors
     */
    public synchronized Object processFiles(List<String> files, Object parent) throws BatchFileException {
        this.startTime = new Date();
        int count = 0;
        String cmd = null;
        String cmdMsg = null;

        try {
            // Establish a connection to the computer share which will be used
            // for copy/moving files remotely
            if (!this.config.isArchiveLocal()) {
                cmd = "cmd /c net use " + config.getArchiveDir();
                logger.log(Level.DEBUG, "Connecting to shared resource, " + config.getArchiveDir());
                cmdMsg = RMT2Utility.execShellCommand(cmd);
                logger.log(Level.DEBUG, cmdMsg);
            }

            // Process each module
            for (int ndx = 0; ndx < config.getModuleCount(); ndx++) {
                this.setModuleId(ndx);
                files = this.getFileListing();
                // Process all files of the current module
                if (files != null && files.size() > 0) {
                    for (String fileName : files) {
                        String fileMsg = null;
                        try {
                            logger.log(Level.INFO, "Begin proecessing file, "  + fileName);
                            Integer rc = (Integer) this.processSingleFile(fileName, null);
                            int uid = rc.intValue();
                            logger.log(Level.INFO, fileName + " was linked to its respective project module");
                            fileMsg = fileName + " was added to the MIME database successfully as " + uid;
                        } catch (Exception e) {
                            fileMsg = "[ERROR] "
                                    + fileName
                                    + " was not added to the MIME database and/or assoicated with the target record of the home application.  Cause: "
                                    + e.getMessage();
                        } finally {
                            logger.log(Level.INFO, fileMsg);
                            this.mimeMsgs.add(fileMsg);
                            logger.log(Level.INFO, "MIME Message buffer size: " + this.mimeMsgs.size());
                            count++;
                        }
                    } // end for
                    this.msg = "Media files were processed for module: " + this.moduleId;
                    logger.log(Level.INFO, this.msg);
                } // end if
            } // end for
        } catch (Exception e) {
            // Do nothing
        } finally {
            // Cancel the connection to computer share(s)
            if (!this.config.isArchiveLocal()) {
                cmd = "cmd /c net use " + config.getArchiveDir() + " /delete";
                logger.log(Level.DEBUG, "Disconnecting from shared resource, " + config.getArchiveDir());
                cmdMsg = RMT2Utility.execShellCommand(cmd);
                logger.log(Level.DEBUG, cmdMsg);
            }
        }
        this.endTime = new Date();
        return count;
    }

    /**
     * Adds media document to the database and joins the document with the
     * target record of the home application.
     * <p>
     * Successful execution of this method requires that the name of the file,
     * <i>fileName</i>, is of the correct format
     * "<app_code>_<module_code>_<primary key value of target recrod>.*". After
     * determining that <i>fileName</i> is correctly formatted, the MIME
     * document is added to the MIME database and then associated with target
     * record of the home application. The primary key value identified in
     * <i>fileName</i> is used to locate the correct home appliation record.
     * 
     * @param fileName
     *            the filename of the document that is to be added to the
     *            system.
     * @return int the primary key value from the <i>Content</i> table
     *         representing the recently added MIME document record.
     * @throws BatchFileException
     *             if an error occurs adding the document to the MIME database
     *             or and error occurs associating the MIME document record with
     *             the target record of the home application.
     * @throws InvalidMediaFileFormatDaoException
     *             if <i>fileName</i> is not in the correct format.
     * @throws SystemException
     *             problem occurs renaming and copying <i>fileName</i> to the
     *             archive destination or deleting the <i>fileName</i> from the
     *             inbound directory.
     */
    public Integer processSingleFile(String fileName, Object parent) throws BatchFileException {
        List<String> fileNamePrimaryKeyTokens = null;
        int newContentId = 0;
        try {
            // Validate the format of the file name.
            logger.info("Validate file format");
            fileNamePrimaryKeyTokens = this.verifyFileFormat(fileName);

            // Save media file data to the content table
            newContentId = super.processSingleFile(fileName, parent);

            // Assoicate MIME document with a record from the target application
            // using document id and the module code
            logger.info("Add document to HOME application");
            // Make web service call to Home App in order to link document
            // to a particular application module
            this.updateProjectModule(this.moduleId, newContentId);
            logger.info("MIME Database updates completed");
            return newContentId;
        } catch (Exception e) {
            logger.error(e.getMessage());
            // If document data has been added, delete from the database since
            // an error occurred.
            if (newContentId > 0) {
                this.deleteContent(newContentId);
            }
            throw new MediaFileOperationException(e);
        } finally {
            this.archiveFile(fileName);
        }
    }

    private void updateProjectModule(int moduleId, int contentId) throws BatchFileException {
        logger.log(Level.INFO, "Inside updateHomeApp");

        ApplicationModuleBean mod = this.config.getModules().get(moduleId);
        if (mod == null) {
            this.msg = "Failure to update target application record due to an invalid moudle configuration instance";
            logger.log(Level.ERROR, this.msg);
            throw new BatchFileException(this.msg);
        }

        ObjectFactory f = new ObjectFactory();
        MediaApplicationLinkRequest request = f.createMediaApplicationLinkRequest();
        HeaderType header = JaxbPayloadFactory.createHeader("routing", "app",
                "module", ApiTransactionCodes.MEDIA_CONTENT_APP_LINK, "ASYNC", "REQUEST", this.getApiUser());
        request.setHeader(header);

        // Setup customer's sales order that will be invoiced
       request.setContentId(BigInteger.valueOf(contentId));
       request.setEntityId(BigInteger.valueOf(Integer.getInteger(mod.getEntityUid())));
       request.setProjectName(mod.getProjectName());
       request.setModuleName(mod.getModuleName());

        // Send time sheet deatils to Accounting systsem to create and invoice sales order
        try {
            this.sendMessage(ApiTransactionCodes.MEDIA_CONTENT_APP_LINK, request);
        } catch (TransactionApiException e) {
            this.msg = "A web service problem occurred sending Media Content updates to API [project=" + mod.getProjectName() + ", module=" + mod.getModuleName() + "]";
            throw new BatchFileException(this.msg, e);
        }
    }
    
    
    private int deleteContent(int contentId) {
        ContentDaoFactory daoFactory = new ContentDaoFactory();
        ContentDao dao = daoFactory.createDatabaseMediaDaoInstance();
        try {
            dao.deleteContent(contentId);
            return 1;
        } catch (Exception e) {
            logger.error(e);
            return -1;
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Creates a <i>ContentDto</i> object using the name of <i>mediaFileName</i>
     * for the purpose of identifying the correct file name and path of the
     * media file.
     * <p>
     * The <i>ContentDto</i> properties, <i>filename</i> and <i>filepath</i>,
     * are set based on the value of <i>mediaFileName</i>. If
     * <i>mediaFileName</i> contains the file name and file extension, but the
     * path sequence is not present, then it is clear that the media file was
     * read from the inbound directory location configured in
     * <i>MimeConfig_XXX.properties</i> for drop files; hence <i>filepath</i>
     * will be set to the inbound directory path. Otherwise, the path and file
     * name of <i>mediaFileName</i> will be parsed and assigned to their
     * respective properties in the <i>ContentDto</i> object.
     * <p>
     * The <i>filename</i> will always be set to <i>mediaFileName</i> without
     * the path sequcence. If <i>mediaFileName</i> does not include the path
     * sequence, then filepath will be assinged the value of the inbound
     * directory setting of {@link FileListenerConfig}. Otherwise, filepath will
     * equal the path sequence portion of <i>mediaFileName</i>.
     * 
     * @param mediaFileName
     *            the name of the file to build Content object.
     * @return {@link ContentDto}
     */
    protected ContentDto createContentObject(String mediaFileName) {
        ContentDto mime =  Rmt2MediaDtoFactory.getContentInstance(mediaFileName);
        String fn = mime.getFilename();
        String path = mime.getFilepath();
        if (fn != null && path != null) {
            if (fn.equals(path)) {
                path = this.config.getInboundDir();
            }
        }
        mime.setFilepath(path);

        // Set app code and module code from the first two values of
        // the file name that are separated by the character, "_".
        List<String> tokens = RMT2String.getTokens(mime.getFilename(), "_");
        if (tokens != null && tokens.size() > 2) {
            mime.setAppCode(tokens.get(0));
            mime.setModuleCode(tokens.get(1));
        }
        return mime;
    }

    /**
     * Validate naming convention of the file name. Should be in the format of
     * <app_code>_<module>_<primary_key>.<file extension>
     * 
     * @param fileName
     * @return List<String> the application code, moudle code, and the primary
     *         key.
     */
    private List<String> verifyFileFormat(String fileName) throws InvalidMediaFileFormatException {
        // remove extra path info, if available
        fileName = RMT2File.getFileName(fileName);
        List<String> tokens = RMT2String.getTokens(fileName, "_");
        if (tokens == null) {
            this.msg = fileName
                    + ": Parsing error occurred...The underscore character must be used as a separator in the file name";
            logger.log(Level.ERROR, this.msg);
            throw new InvalidMediaFileFormatException(this.msg);
        }
        if (tokens.size() != 3) {
            this.msg = fileName
                    + ": Parsing error occurred...The file name must be in the format  <app_code>_<module>_<primary_key>.<file extension>";
            logger.log(Level.ERROR, this.msg);
            throw new InvalidMediaFileFormatException(this.msg);
        }

        // Validate that third element is an integer value representing the
        // primary key.
        List<String> tokens2 = RMT2String.getTokens(tokens.get(2), ".");
        try {
            Integer.parseInt(tokens2.get(0));
        } catch (NumberFormatException e) {
            this.msg = fileName
                    + ": Failure to recognize the third position in the file name as the primary key for the target datasource during the second phase of file name parsing.  The invalid value is "
                    + tokens.get(2);
            logger.log(Level.ERROR, this.msg);
            throw new InvalidMediaFileFormatException(this.msg);
        }
        // Assign the parsed primary key value to original set of tokens
        tokens.set(2, tokens2.get(0));
        return tokens;
    }

    /**
     * Renames the source file, <i>fileName</i>, and copies the renamed results
     * to the archived desination, and deletes the source file from its inbound
     * location. The file is renamed using the following format: <file
     * name>_<yyyymmdd_HHmmss_millsecs>.<original file extension>.
     * 
     * @param fileName
     *            the name of the file to archive.
     */
    private void archiveFile(String fileName) {
        ContentDto dto = this.createContentObject(fileName);

        // Rename inbound file
        List<String> fileToken = RMT2String.getTokens(dto.getFilename(), ".");
        Date timestamp = new Date();
        StringBuffer timestampStr = new StringBuffer();
        timestampStr.append("_");
        timestampStr.append(RMT2Date.formatDate(timestamp, "yyyymmdd"));
        timestampStr.append("_");
        timestampStr.append(RMT2Date.formatDate(timestamp, "HHmmss"));
        timestampStr.append("_");
        timestampStr.append(RMT2Date.formatDate(timestamp, "S"));
        timestampStr.append(".");
        String fileNameTs = fileToken.get(0) + timestampStr.toString() + fileToken.get(1);

        // Prepare for media file maintenance...
        String fromFile = dto.getFilepath() + dto.getFilename();
        String toFile = this.config.getArchiveDir() + fileNameTs;
        try {
            // Rename and copy file to archive destination
            RMT2File.copyFile(fromFile, toFile);
            // Delete file from inbound directory, if applicable.
            if (dto.getFilepath().equalsIgnoreCase(this.config.getInboundDir())) {
                RMT2File.deleteFile(this.config.getInboundDir() + fileName);
            }
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.file.MediaFileDaoProcessor#setModuleId(int)
     */
    @Override
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * Creates a report detailing the success or failure of all the files
     * processed and transmits the report via SMTP to the user designated in the
     * home application's module configuration.
     * 
     * @throws FileDropReportException
     *             problem occurred creaing or sending file drop report via
     *             SMTP.
     */
    public void sendDropReport() throws FileDropReportException {
        if (!this.config.isEmailResults()) {
            return;
        }
        logger.log(Level.INFO, "Creating MIME Drop Report...");
        StringBuffer body = new StringBuffer();

        // Attempt to obtain From email address from the application's property
        // pool which is loaded at server start up.
        String fromAddr = null;
        try {
            fromAddr = RMT2File.getPropertyValue(ConfigConstants.CONFIG_APP, ConfigConstants.OWNER_EMAIL);
        } catch (Exception ee) {
            this.msg = "FROM Email address is invalid and will probably be the root cause of transmission failure of MIME File Drop Report";
            logger.log(Level.ERROR, this.msg);
            throw new FileDropReportException(this.msg);
        }

        String toAddr = this.config.getEmailRecipients();
        if (toAddr == null) {
            this.msg = "TO Email address is invalid and will probably be the root cause of transmission failure of MIME File Drop Report";
            logger.log(Level.ERROR, this.msg);
            throw new FileDropReportException(this.msg);
        }
        String subject = "MIME Content File Drop Report";
        String appname = RMT2File.getPropertyValue(ConfigConstants.CONFIG_APP, ConfigConstants.PROPNAME_APP_NAME);
        appname = (appname == null ? "[Unknown Application]" : appname);

        body.append("The following files where dropped in the inbound directory, ");
        body.append(this.config.getInboundDir());
        body.append(", in order to be added to the MIME database and linked to various record types of the application, ");
        body.append(appname);
        body.append(".\n\n");

        body.append("Start Time: ");
        body.append(RMT2Date.formatDate(this.startTime, "MM-dd-yyyy HH:mm:ss.S"));
        body.append("\n");
        body.append("End Time: ");
        body.append(RMT2Date.formatDate(this.endTime, "MM-dd-yyyy HH:mm:ss.S"));
        body.append("\n\n\n");

        int count = 0;
        // Add details about each file that was processed
        logger.log(Level.INFO, "MIME Drop Report will detail " + this.mimeMsgs.size()
                        + " messages regarding MIME files processed");
        for (String msg : this.mimeMsgs) {
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
            logger.log(Level.INFO, "MIME Drop Report email was sent to " + toAddr);
        } catch (Exception e) {
            this.msg = "MIME Drop Report email submission failed: " + e.getMessage();
            logger.log(Level.INFO, this.msg);
            throw new FileDropReportException(this.msg);
        }
        return;
    }

    @Override
    public Object sendMessage(String messageId, Serializable payload) throws TransactionApiException {
        String msg = null;
        MessageRouterHelper helper = new MessageRouterHelper();
        try {
            return helper.routeXmlMessage(messageId, payload);
        } catch (MessageRoutingException e) {
            msg = "Error occurred routing Media content identifier to its Home Application";
            throw new TransactionApiException(msg, e);
        }
    }

    
}
