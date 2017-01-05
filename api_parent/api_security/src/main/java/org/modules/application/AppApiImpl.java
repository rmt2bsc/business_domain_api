package org.modules.application;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.application.AppDao;
import org.dao.application.AppDaoException;
import org.dao.application.AppDaoFactory;
import org.dto.ApplicationDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;

import com.RMT2Constants;
import com.api.foundation.AbstractTransactionApiImpl;

/**
 * Api for managing the Application module.
 * 
 * @author rterrell
 * 
 */
class AppApiImpl extends AbstractTransactionApiImpl implements AppApi {

    private static Logger logger = Logger.getLogger(AppApiImpl.class);

    /**
     * Create a ApplicationApiImpl
     */
    protected AppApiImpl() {
        super();
        logger.info("Logger intialized for class, "
                + AppApiImpl.class.getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.category.application.ApplicationApi#fetch(int)
     */
    @Override
    public ApplicationDto get(int appId) throws AppApiException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);

        // AppDaoFactory f = new AppDaoFactory();
        // AppDao dao = f.createRmt2OrmDao();
        // ApplicationDto data = null;
        // try {
        // data = dao.fetchApp(appId);
        // return data;
        // }
        // catch (AppDaoException e) {
        // this.msg = "Unable to fetch application profile by application id, "
        // + appId;
        // throw new AppApiException(this.msg, e);
        // }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.application.AppApi#fetch(java.lang.String)
     */
    @Override
    public ApplicationDto get(String appName) throws AppApiException {
        AppDaoFactory f = new AppDaoFactory();
        AppDao dao = f.createLdapDao();
        ApplicationDto data = null;
        try {
            data = dao.fetchApp(appName);
            return data;
        } catch (AppDaoException e) {
            this.msg = "Unable to fetch application profile by application name, "
                    + appName;
            throw new AppApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.category.application.ApplicationApi#fetchAll()
     */
    @Override
    public List<ApplicationDto> get() throws AppApiException {
        AppDaoFactory f = new AppDaoFactory();
        AppDao dao = f.createLdapDao();
        List<ApplicationDto> apps;
        try {
            apps = dao.fetchApp();
            return apps;
        } catch (AppDaoException e) {
            this.msg = "Unable to fetch all applications";
            throw new AppApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.category.application.ApplicationApi#create()
     */
    @Override
    public ApplicationDto create() {
        return Rmt2OrmDtoFactory.getNewAppCategoryInstance();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.category.application.ApplicationApi#update(org.dao.bean.Application)
     */
    @Override
    public int update(ApplicationDto dto) throws AppApiException {
        AppDaoFactory f = new AppDaoFactory();
        AppDao dao = null;
        try {
            dao = f.createLdapDao();
            dao.setDaoUser(this.apiUser);
            return dao.maintainApp(dto);
        } catch (AppDaoException e) {
            throw new AppApiException(
                    "Unable to execute API update for Application module", e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.category.application.ApplicationApi#delete(int)
     */
    @Override
    public int delete(int appId) throws AppApiException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);

        // AppDaoFactory f = new AppDaoFactory();
        // AppDao dao = null;
        // try {
        // dao = f.createRmt2OrmDao();
        // return dao.deleteApp(appId);
        // }
        // catch (AppDaoException e) {
        // throw new AppApiException("Application record delete failed", e);
        // }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.application.AppApi#delete(java.lang.String)
     */
    @Override
    public int delete(String appName) throws AppApiException {
        AppDaoFactory f = new AppDaoFactory();
        AppDao dao = null;
        try {
            dao = f.createLdapDao();
            return dao.deleteApp(appName);
        } catch (AppDaoException e) {
            throw new AppApiException("Application record delete failed", e);
        } finally {
            dao.close();
            dao = null;
        }
    }

}
