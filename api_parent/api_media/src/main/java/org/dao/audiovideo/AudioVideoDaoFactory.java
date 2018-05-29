package org.dao.audiovideo;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating audio video DAO objects.
 * 
 * @author Roy Terrell
 * 
 */
public class AudioVideoDaoFactory extends RMT2Base {

    /**
     * Default contructor
     */
    public AudioVideoDaoFactory() {
        return;
    }

    /**
     * Creates an instance of the {@link AudioVideoDao} using the RMT2 ORM basic
     * DAO implementation.
     * 
     * @return an instance of {@link AudioVideoDao}
     */
    public AudioVideoDao createRmt2OrmDaoInstance() {
        AudioVideoDao dao = new BasicRmt2OrmAudioVideoDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of the {@link AudioVideoDao} using the RMT2 ORM basic
     * DAO implementation.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link AudioVideoDao}
     */
    public AudioVideoDao createRmt2OrmDaoInstance(String appName) {
        AudioVideoDao dao = new BasicRmt2OrmAudioVideoDaoImpl(appName);
        return dao;
    }
    
    /**
     * Creates an instance of the {@link AudioVideoDao} using the RMT2 ORM basic
     * DAO implementation.
     * 
     * @param dao
     *            an instnace of {@link PersistenceClient}
     * @return an instance of {@link AudioVideoDao}
     */
    public AudioVideoDao createRmt2OrmDaoInstance(DaoClient dao) {
        AudioVideoDao d = new BasicRmt2OrmAudioVideoDaoImpl(dao.getClient());
        return d;
    }
}
