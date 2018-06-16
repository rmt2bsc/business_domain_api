package org.modules.transaction;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the common Transaction API
 * module
 * 
 * @author Roy Terrell
 * 
 */
public class XactApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public XactApiFactory() {
        return;
    }

     /**
     * Creates an instance of {@link XactApi} using the default transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link XactApi} or null if <i>connection</i> is
     *         null.
     */
    public XactApi createDefaultXactApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        DefaultXactApiImpl api = new DefaultXactApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

}
