package org.modules.application;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.application.AppDao;
import org.dao.application.AppDaoException;
import org.dao.application.AppDaoFactory;
import org.dto.ApplicationDto;
import org.modules.SecurityConstants;

import com.InvalidDataException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * Api for managing the Application module.
 * 
 * @author rterrell
 * 
 */
class AppApiImpl extends AbstractTransactionApiImpl implements AppApi {

    private static Logger logger = Logger.getLogger(AppApiImpl.class);
    
    private AppDaoFactory daoFact;
    private AppDao dao;

    /**
     * Create a AppApiImpl using the default application name,
     * {@link MediaConstants#APP_NAME}
     */
    AppApiImpl() {
        super(SecurityConstants.APP_NAME);
        this.dao = this.daoFact.createRmt2OrmDao(SecurityConstants.APP_NAME);
        this.setSharedDao(this.dao);
        logger.info("AppApi is initialized by default constructor");
    }

    /**
     * Create a AudioVideoMetadataImpl using the specified application name.
     * 
     * @param appName
     *            the application name
     */
    AppApiImpl(String appName) {
        super(appName);
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        logger.info("AppApi is initialized by application name, " + appName);
    }

    /**
     * Create a AudioVideoMetadataImpl using DaoClient instance that is intended
     * to be shared by another related application.
     * 
     * @param dao
     *            instance of {@link DaoClient}
     */
    AppApiImpl(DaoClient dao) {
        super(dao);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
        logger.info("AppApi is initialized using a shared DAO client");
    }

    @Override
    public void init() {
        super.init();
        this.daoFact = new AppDaoFactory();
    }


    @Override
    public List<ApplicationDto> get(ApplicationDto criteria) throws AppApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Application crtieria object is required", e);
        }
        
        List<ApplicationDto> apps;
        try {
            apps = dao.fetchApp(criteria);
            return apps;
        } catch (AppDaoException e) {
            this.msg = "Unable to fetch application data";
            throw new AppApiException(this.msg, e);
        } 
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.category.application.ApplicationApi#update(org.dao.bean.Application)
     */
    @Override
    public int update(ApplicationDto app) throws AppApiException {
        try {
            return dao.maintainApp(app);
        } catch (AppDaoException e) {
            throw new AppApiException("Unable to execute API update for Application module", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.category.application.ApplicationApi#delete(int)
     */
    @Override
    public int delete(int appId) throws AppApiException {
        try {
            return dao.deleteApp(appId);
        } catch (AppDaoException e) {
            throw new AppApiException("Application record delete failed", e);
        }
    }
}
