package org.dao.document.file;

import java.util.List;

import com.api.BatchFileException;
import com.api.persistence.DaoClient;

/**
 * 
 * @author roy.terrell
 *
 */
public interface HomeAppDao extends DaoClient {

    /**
     * 
     * @param moduleId
     * @param contentId
     * @param fileNameTokens
     * @throws BatchFileException
     */
    void update(int moduleId, int contentId, List<String> fileNameTokens) throws BatchFileException;
    
    /**
     * 
     * @return
     */
    boolean isAddLink();
}
