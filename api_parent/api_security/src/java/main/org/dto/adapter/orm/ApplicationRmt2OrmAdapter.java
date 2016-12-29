package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.Application;
import org.dto.DefaultApplicationAdpater;

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
}
