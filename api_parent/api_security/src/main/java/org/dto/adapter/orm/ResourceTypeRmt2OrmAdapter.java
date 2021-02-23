package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.UserResourceType;
import org.dto.DefaultResourceAdapter;
import org.dto.ResourceDto;

/**
 * Adapts an RMT2 ORM <i>UserResourceType</i> object to an <i>ResourceDto</i>.
 * 
 * @author rterrell
 * 
 */
class ResourceTypeRmt2OrmAdapter extends DefaultResourceAdapter implements
        ResourceDto {

    private UserResourceType urt;

    /**
     * Create a ResourceTypeRmt2OrmAdapter using an instance of
     * <i>UserResourceType</i>.
     * 
     * @param rsrcType
     *            an instance of {@link UserResourceType}
     */
    protected ResourceTypeRmt2OrmAdapter(UserResourceType rsrcType) {
        super();
        if (rsrcType == null) {
            rsrcType = new UserResourceType();
        }
        this.urt = rsrcType;
        this.dateCreated = rsrcType.getDateCreated();
        this.dateUpdated = rsrcType.getDateUpdated();
        this.updateUserId = rsrcType.getUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setTypeId(int)
     */
    @Override
    public void setTypeId(int value) {
        this.urt.setRsrcTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getTypeId()
     */
    @Override
    public int getTypeId() {
        return this.urt.getRsrcTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setTypeDescription(java.lang.String
     * )
     */
    @Override
    public void setTypeDescription(String value) {
        this.urt.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getTypeDescription()
     */
    @Override
    public String getTypeDescription() {
        return this.urt.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getUpdateUserId()
     */
    @Override
    public String getUpdateUserId() {
        return this.urt.getUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.api.foundation.TransactionDtoImpl#setUpdateUserId(java.lang.String)
     */
    @Override
    public void setUpdateUserId(String updateUserId) {
        this.urt.setUserId(updateUserId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateCreated()
     */
    @Override
    public Date getDateCreated() {
        return this.urt.getDateCreated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateCreated(java.util.Date)
     */
    @Override
    public void setDateCreated(Date dateCreated) {
        this.urt.setDateCreated(dateCreated);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateUpdated()
     */
    @Override
    public Date getDateUpdated() {
        return this.urt.getDateUpdated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateUpdated(java.util.Date)
     */
    @Override
    public void setDateUpdated(Date dateUpdated) {
        this.urt.setDateUpdated(dateUpdated);
    }

}
