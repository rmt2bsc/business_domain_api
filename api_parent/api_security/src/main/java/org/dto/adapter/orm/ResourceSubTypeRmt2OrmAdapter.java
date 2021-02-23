package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.UserResourceSubtype;
import org.dto.DefaultResourceAdapter;
import org.dto.ResourceDto;

/**
 * Adapts an RMT2 ORM <i>UserResourceSubtype</i> object to an
 * <i>ResourceDto</i>.
 * 
 * @author rterrell
 * 
 */
class ResourceSubTypeRmt2OrmAdapter extends DefaultResourceAdapter implements
        ResourceDto {

    private UserResourceSubtype urst;

    /**
     * Create a ResourceSubTypeRmt2OrmAdapter using an instance of
     * <i>UserResourceSubtype</i>.
     * 
     * @param rsrcSubType
     *            an instance of {@link UserResourceSubtype}
     */
    protected ResourceSubTypeRmt2OrmAdapter(UserResourceSubtype rsrcSubType) {
        super();
        if (rsrcSubType == null) {
            rsrcSubType = new UserResourceSubtype();
        }
        this.urst = rsrcSubType;
        this.dateCreated = rsrcSubType.getDateCreated();
        this.dateUpdated = rsrcSubType.getDateUpdated();
        this.updateUserId = rsrcSubType.getUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setSubTypeId(int)
     */
    @Override
    public void setSubTypeId(int value) {
        this.urst.setRsrcSubtypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeId()
     */
    @Override
    public int getSubTypeId() {
        return this.urst.getRsrcSubtypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setSubTypeName(java.lang.String)
     */
    @Override
    public void setSubTypeName(String value) {
        this.urst.setName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeName()
     */
    @Override
    public String getSubTypeName() {
        return this.urst.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setSubTypeDescription(java.lang
     * .String)
     */
    @Override
    public void setSubTypeDescription(String value) {
        this.urst.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeDescription()
     */
    @Override
    public String getSubTypeDescription() {
        return this.urst.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setTypeId(int)
     */
    @Override
    public void setTypeId(int value) {
        this.urst.setRsrcTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getTypeId()
     */
    @Override
    public int getTypeId() {
        return this.urst.getRsrcTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getUpdateUserId()
     */
    @Override
    public String getUpdateUserId() {
        return this.urst.getUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.api.foundation.TransactionDtoImpl#setUpdateUserId(java.lang.String)
     */
    @Override
    public void setUpdateUserId(String updateUserId) {
        this.urst.setUserId(updateUserId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateCreated()
     */
    @Override
    public Date getDateCreated() {
        return this.urst.getDateCreated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateCreated(java.util.Date)
     */
    @Override
    public void setDateCreated(Date dateCreated) {
        this.urst.setDateCreated(dateCreated);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateUpdated()
     */
    @Override
    public Date getDateUpdated() {
        return this.urst.getDateUpdated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateUpdated(java.util.Date)
     */
    @Override
    public void setDateUpdated(Date dateUpdated) {
        this.urst.setDateUpdated(dateUpdated);
    }
}
