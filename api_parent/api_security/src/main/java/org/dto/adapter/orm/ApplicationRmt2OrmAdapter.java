package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.Application;
import org.dto.DefaultApplicationAdpater;

import com.api.util.RMT2Money;

/**
 * Adapts an RMT2 ORM <i>Application</i> object to an <i>ApplicationDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
class ApplicationRmt2OrmAdapter extends DefaultApplicationAdpater {

    private Application app;

    /**
     * Default Constructor
     */
    public ApplicationRmt2OrmAdapter() {
        super();
        return;
    }

    /**
     * Create a ApplicationRmt2OrmAdapter using an instance of
     * <i>Application</i>.
     * 
     * @param appOrm
     *            an instance of {@link Application}
     */
    protected ApplicationRmt2OrmAdapter(Application appOrm) {
        this();
        if (appOrm == null) {
            appOrm = new Application();
        }
        this.app = appOrm;
        this.setUpdateUserId(appOrm.getUserId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setAppName(java.lang.String)
     */
    @Override
    public void setAppName(String value) {
        this.app.setName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getAppName()
     */
    @Override
    public String getAppName() {
        return this.app.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultApplicationAdpater#setAppDescription(java.lang.String)
     */
    @Override
    public void setAppDescription(String value) {
        this.app.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getAppDescription()
     */
    @Override
    public String getAppDescription() {
        return this.app.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setApplicationId(int)
     */
    @Override
    public void setApplicationId(int value) {
        this.app.setAppId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getApplicationId()
     */
    @Override
    public int getApplicationId() {
        return this.app.getAppId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setActive(java.lang.String)
     */
    @Override
    public void setActive(String value) {
        if (value != null && RMT2Money.isNumeric(value)) {
            this.app.setActive(Integer.valueOf(value));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getActive()
     */
    @Override
    public String getActive() {
        return String.valueOf(this.app.getActive());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getUpdateUserId()
     */
    @Override
    public String getUpdateUserId() {
        return this.app.getUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.api.foundation.TransactionDtoImpl#setUpdateUserId(java.lang.String)
     */
    @Override
    public void setUpdateUserId(String updateUserId) {
        this.app.setUserId(updateUserId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateCreated()
     */
    @Override
    public Date getDateCreated() {
        return this.app.getDateCreated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateCreated(java.util.Date)
     */
    @Override
    public void setDateCreated(Date dateCreated) {
        this.app.setDateCreated(dateCreated);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateUpdated()
     */
    @Override
    public Date getDateUpdated() {
        return this.app.getDateUpdated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateUpdated(java.util.Date)
     */
    @Override
    public void setDateUpdated(Date dateUpdated) {
        this.app.setDateUpdated(dateUpdated);
    }
}
