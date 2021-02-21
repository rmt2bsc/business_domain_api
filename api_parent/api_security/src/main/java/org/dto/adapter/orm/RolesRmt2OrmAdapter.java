package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.Roles;
import org.dto.DefaultCategoryAdapter;

/**
 * Adapts an RMT2 ORM <i>Roles</i> object to an <i>CategoryDto</i>.
 * 
 * @author rterrell
 * 
 */
class RolesRmt2OrmAdapter extends DefaultCategoryAdapter {

    private Roles r;

    private RolesRmt2OrmAdapter() {
        super();
        return;
    }

    /**
     * Create a RolesRmt2OrmAdapter using an instance of <i>Roles</i>.
     * 
     * @param role
     *            an instance of {@link Roles}
     */
    public RolesRmt2OrmAdapter(Roles role) {
        this();
        if (role == null) {
            role = new Roles();
        }
        this.r = role;
        this.setUpdateUserId(role.getUserId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setRoleId(int)
     */
    @Override
    public void setRoleId(int value) {
        this.r.setRoleId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getRoleId()
     */
    @Override
    public int getRoleId() {
        return this.r.getRoleId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setRoleName(java.lang.String)
     */
    @Override
    public void setRoleName(String value) {
        this.r.setName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getRoleName()
     */
    @Override
    public String getRoleName() {
        return this.r.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setRoleDescription(java.lang.String
     * )
     */
    @Override
    public void setRoleDescription(String value) {
        this.r.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getRoleDescription()
     */
    @Override
    public String getRoleDescription() {
        return this.r.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getUpdateUserId()
     */
    @Override
    public String getUpdateUserId() {
        return this.r.getUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.api.foundation.TransactionDtoImpl#setUpdateUserId(java.lang.String)
     */
    @Override
    public void setUpdateUserId(String updateUserId) {
        this.r.setUserId(updateUserId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateCreated()
     */
    @Override
    public Date getDateCreated() {
        return this.r.getDateCreated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateCreated(java.util.Date)
     */
    @Override
    public void setDateCreated(Date dateCreated) {
        this.r.setDateCreated(dateCreated);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#getDateUpdated()
     */
    @Override
    public Date getDateUpdated() {
        return this.r.getDateUpdated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDtoImpl#setDateUpdated(java.util.Date)
     */
    @Override
    public void setDateUpdated(Date dateUpdated) {
        this.r.setDateUpdated(dateUpdated);
    }
}
