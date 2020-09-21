package org.modules.services.document.directory.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.modules.MediaConstants;
import org.modules.document.DocumentContentApi;
import org.modules.document.DocumentContentApiFactory;

import com.RMT2Constants;
import com.api.BatchFileException;
import com.api.foundation.AbstractTransactionApiImpl;

/**
 * An implementation of {@link MediaFileDaoProcessor} for managing a single
 * media file at a time.
 * 
 * @author rterrell
 * 
 */
abstract class AbstractMediaFileProcessorImpl extends AbstractTransactionApiImpl implements MediaFileProcessor {

    // extends AbstractMediaFileProcessorDaoImp {

    private static Logger logger = Logger.getLogger(AbstractMediaFileProcessorImpl.class);
    private List<String> errorMessages;

    /**
     * Creates a SingleMediaFileProcessorDaoImpl object.
     * 
     */
    protected AbstractMediaFileProcessorImpl() {
        super();
        // Initialize MIME message collection
        this.errorMessages = new ArrayList<>();
        return;
    }

    /**
     * Adds the meta data and content of a single media document to the database.
     * 
     * @param fileName
     *            the filename of the document that is to be added to the
     *            system.
     * @return int the primary key value from the <i>Content</i> table
     *         representing the recently added MIME document record.
     * @throws BatchFileException
     *             An error occurred adding the document to the MIME database,
     *             or an error occurs associating the MIME document record with
     *             the target record of the home application, or <i>fileName</i>
     *             is not in the correct format.
     */
    @Override
    public Integer processSingleFile(String fileName, Object parent) throws BatchFileException {
        int contentId = 0;
        try {
            // Save media file data to the content table
            DocumentContentApiFactory dcApiFact = new DocumentContentApiFactory();
            DocumentContentApi dcApi = dcApiFact.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME);
            contentId = dcApi.add(fileName);
            return contentId;
        } catch (Exception e) {
            this.msg = "Error adding media content, " + fileName + ", to the content database";
            throw new MediaFileOperationException(this.msg, e);
        } 
    }

    /**
     * Subbed method
     * 
     * @throws BatchFileException
     *             when the attempt to establish a connection for either system
     *             fails.
     */
    public synchronized void initConnection() throws BatchFileException {
        return;
    }
    
    /**
     * Stubbed method
     */
    @Override
    public void close() {
        return;
    }
    
    /**
     * Adds a message to the list of error messages.
     * 
     * @param msg
     */
    protected void addErrorMessage(String msg) {
        this.errorMessages.add(msg);
    }
    
    /**
     * Return the list of error messages.
     * 
     * @return Always return a valid List messages even if empty, never null.
     */
    @Override
    public List<String> getErrorMessages() {
        return this.errorMessages;
    }
    
    
    @Override
    public void initConnection(Object extSource) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public void initConnection(String configFile1, String configFile2) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public Object processDirectory(File dir, Object parent) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public Object processSingleFile(File file, Object parent) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
    
    @Override
    public Object processFiles(List<String> files, Object parent) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public boolean isFilesAvailable() {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
}
