package org.dao.document;

import org.apache.log4j.Logger;

/**
 * The database media content implementation of the {@link ContentDao} interface
 * to manage meida document that lives in the <i>content</i> table.
 * <p>
 * <b>NOTE</b><br>
 * It is best to use vanilla JDBC calls instead of the ORM api due to
 * proprietary Sybase dialect.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmDatabaseMediaDaoImpl extends AbstractRmt2OrmContentDaoImpl {
    
    private static final Logger logger = Logger.getLogger(Rmt2OrmDatabaseMediaDaoImpl.class);

    /**
     * Create a Rmt2OrmSybaseEmbeddedMediaDaoImpl object
     */
    public Rmt2OrmDatabaseMediaDaoImpl() {
        super();
        return;
    }

    /**
     * Create a Rmt2OrmSybaseEmbeddedMediaDaoImpl object
     * 
     * @param appName
     *            application name
     */
    public Rmt2OrmDatabaseMediaDaoImpl(String appName) {
        super(appName);
        return;
    }

}
