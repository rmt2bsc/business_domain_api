package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.UserGroup;
import org.dto.DefaultUserAdapter;

/**
 * Adapts an RMT2 ORM <i>UserGroup</i> object to an <i>UserDto</i>.
 * 
 * @author rterrell
 * 
 */
class GroupRmt2OrmAdapter extends DefaultUserAdapter {

    private UserGroup u;

    private GroupRmt2OrmAdapter() {
        super();
        return;
    }

    /**
     * Create a GroupRmt2OrmAdapter using an instance of <i>UserGroup</i>.
     * 
     * @param group
     *            an instance of {@link UserGroup}
     */
    protected GroupRmt2OrmAdapter(UserGroup group) {
        this();
        if (group == null) {
            group = new UserGroup();
        }
        this.u = group;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getGroupId()
     */
    @Override
    public int getGroupId() {
        return this.u.getGrpId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setGroupId(int)
     */
    @Override
    public void setGroupId(int grpId) {
        this.u.setGrpId(grpId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getGroupDescription()
     */
    @Override
    public String getGrp() {
        return this.u.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultUserRmt2OrmAdapter#setGroupDescription(java.lang.String)
     */
    @Override
    public void setGrp(String value) {
        this.u.setDescription(value);
    }

    @Override
    public String getGrpDescription() {
        return this.u.getDescription();
    }

    @Override
    public void setGrpDescription(String value) {
        this.u.setDescription(value);
    }
    
    @Override
    public String getUpdateUserId() {
        return this.u.getUserId();
    }

    @Override
    public void setUpdateUserId(String updateUserId) {
        this.u.setUserId(updateUserId);
    }

    @Override
    public Date getDateCreated() {
        return this.u.getDateCreated();
    }

    @Override
    public void setDateCreated(Date dateCreated) {
        this.u.setDateCreated(dateCreated);
    }

    @Override
    public Date getDateUpdated() {
        return this.u.getDateUpdated();
    }

    @Override
    public void setDateUpdated(Date dateUpdated) {
        this.u.setDateUpdated(dateUpdated);
    }
}
