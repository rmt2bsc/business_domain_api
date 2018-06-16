package org.dto.adapter.orm;

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
}
