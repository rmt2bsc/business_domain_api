package org.modules.transaction;

import org.dao.transaction.XactDao;
import org.dao.transaction.XactDaoFactory;
import org.dto.XactCustomCriteriaDto;
import org.modules.CommonAccountingConst;

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
     * implementation and creating a new Xact DAO connection.
     * 
     * @return an instance of {@link XactApi} or null if <i>connection</i> is
     *         null.
     */
    public static final XactApi createDefaultXactApi() {
        XactDaoFactory daoFact = new XactDaoFactory();
        XactDao dao = daoFact.createRmt2OrmXactDao(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
        DefaultXactApiImpl api = new DefaultXactApiImpl(dao);
        api.setApiUser(dao.getDaoUser());
        return api;
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
    public static final XactApi createDefaultXactApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        DefaultXactApiImpl api = new DefaultXactApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

    /**
     * Create a transaction custom criteria object
     * 
     * @return {@link XactCustomCriteriaDto}
     */
    public static final XactCustomCriteriaDto createCustomCriteriaInstance() {
        XactCustomCriteriaDto dto = new XactCustomCriteriaImpl();
        return dto;
    }
}
