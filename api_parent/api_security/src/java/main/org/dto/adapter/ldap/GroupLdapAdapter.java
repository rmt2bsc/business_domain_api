package org.dto.adapter.ldap;

import java.util.ArrayList;

import org.dao.mapping.orm.ldap.LdapUserGroup;
import org.dto.DefaultUserAdapter;

import com.RMT2Constants;

/**
 * Adapts a LDAP <i>UserGroup</i> object to an <i>UserDto</i>.
 * 
 * @author rterrell
 * 
 */
class GroupLdapAdapter extends DefaultUserAdapter {

    private LdapUserGroup u;

    private GroupLdapAdapter() {
        super();
        return;
    }

    /**
     * Create a GroupRmt2OrmAdapter using an instance of <i>UserGroup</i>.
     * 
     * @param group
     *            an instance of {@link LdapUserGroup}
     */
    protected GroupLdapAdapter(LdapUserGroup group) {
        this();
        if (group == null) {
            group = new LdapUserGroup();
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
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setGroupId(int)
     */
    @Override
    public void setGroupId(int grpId) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getGroupDescription()
     */
    @Override
    public String getGrp() {
        if (this.u.getCn() != null && this.u.getCn().size() > 0) {
            return this.u.getCn().get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultUserRmt2OrmAdapter#setGroupDescription(java.lang.String)
     */
    @Override
    public void setGrp(String value) {
        this.u.setCn(new ArrayList<String>());
        this.u.getCn().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getGrpDescription()
     */
    @Override
    public String getGrpDescription() {
        if (this.u.getDescription() != null
                && this.u.getDescription().size() > 0) {
            return this.u.getDescription().get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.ldap.DefaultUserLdapAdapter#setGrpDescription(java.lang.String)
     */
    @Override
    public void setGrpDescription(String value) {
        this.u.setDescription(new ArrayList<String>());
        this.u.getDescription().add(value);
    }
}
