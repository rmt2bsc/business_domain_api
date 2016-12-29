package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.Application;
import org.dto.DefaultCategoryAdapter;


/**
 * Adapts an RMT2 ORM <i>Application</i> object to an <i>CategoryDto</i>.
 * 
 * @author rterrell
 *
 */
class AppRmt2OrmAdapter extends DefaultCategoryAdapter {
    
    private Application app;
    
    
    
    private AppRmt2OrmAdapter() {
	super();
	return;
    }
    
    /**
     * Create a AppRmt2OrmAdapter using an instance of <i>Application</i>.
     * 
     * @param appOrm
     *          an instance of {@link Application}
     */
    protected AppRmt2OrmAdapter(Application appOrm) {
	this();
	this.app = appOrm;
	this.setUpdateUserId(appOrm.getUserId());
    }


    /* (non-Javadoc)
     * @see org.dto.CategoryDto#setApplicationId(int)
     */
    @Override
    public void setApplicationId(int value) {
	this.app.setAppId(value);
    }

    /* (non-Javadoc)
     * @see org.dto.CategoryDto#getApplicationId()
     */
    @Override
    public int getApplicationId() {
	return this.app.getAppId();
    }
    
    /* (non-Javadoc)
     * @see org.dto.CategoryDto#setAppName(java.lang.String)
     */
    @Override
    public void setAppName(String value) {
	this.app.setName(value);
    }

    /* (non-Javadoc)
     * @see org.dto.CategoryDto#getAppName()
     */
    @Override
    public String getAppName() {
	return this.app.getName();
    }

    /* (non-Javadoc)
     * @see org.dto.CategoryDto#setAppDescription(java.lang.String)
     */
    @Override
    public void setAppDescription(String value) {
	this.app.setDescription(value);
    }

    /* (non-Javadoc)
     * @see org.dto.CategoryDto#getAppDescription()
     */
    @Override
    public String getAppDescription() {
	return this.app.getDescription();
    }

}
