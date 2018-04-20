package org.modules.services.directory.file;

import java.util.List;

import com.RMT2Constants;
import com.api.BatchFileException;

/**
 * 
 * @author roy.terrell
 *
 */
public class SingleMediaFileProcessorImpl extends AbstractMediaFileProcessorImpl {

    @Override
    public void setModuleId(int moduleId) {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
        
    }

    @Override
    public void sendDropReport() throws FileDropReportException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public boolean isFilesAvailable() {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public void initConnection() throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public List<String> getFileListing() {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public int processBatch() throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public Object processFiles(List<String> files, Object parent) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    

}
