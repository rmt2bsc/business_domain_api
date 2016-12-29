package org.dto.adapter.ldap;

import java.util.ArrayList;

import org.dao.mapping.orm.ldap.LdapAppRoles;
import org.dao.mapping.orm.ldap.LdapRoles;
import org.dao.mapping.orm.ldap.LdapUser;
import org.dto.CategoryDto;
import org.dto.DefaultUserAdapter;

import com.RMT2Constants;

/**
 * @author Roy Terrell
 * 
 */
class CategoryLdapAdapter extends DefaultUserAdapter implements CategoryDto {

    private LdapRoles r;

    private LdapAppRoles ar;

    private LdapUser u;

    public CategoryLdapAdapter(LdapUser user) {
        if (user == null) {
            user = new LdapUser();
        }
        this.u = user;
    }

    public CategoryLdapAdapter(LdapRoles roles) {
        if (roles == null) {
            roles = new LdapRoles();
        }
        this.r = roles;
    }

    public CategoryLdapAdapter(LdapAppRoles appRoles) {
        if (appRoles == null) {
            appRoles = new LdapAppRoles();
        }
        this.ar = appRoles;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#setAppRoleCode(java.lang.String)
     */
    @Override
    public void setAppRoleCode(String value) {
        if (this.ar != null) {
            this.ar.setCn(new ArrayList<String>());
            this.ar.getCn().add(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#getAppRoleCode()
     */
    @Override
    public String getAppRoleCode() {
        if (this.ar != null) {
            if (this.ar.getCn() != null && this.ar.getCn().size() > 0) {
                return this.ar.getCn().get(0);
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#setAppRoleName(java.lang.String)
     */
    @Override
    public void setAppRoleName(String value) {
        if (this.ar != null) {
            this.ar.setAppRoleName(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#getAppRoleName()
     */
    @Override
    public String getAppRoleName() {
        if (this.ar != null) {
            return this.ar.getAppRoleName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#setAppRoleDescription(java.lang.String)
     */
    @Override
    public void setAppRoleDescription(String value) {
        if (this.ar != null) {
            this.ar.setDescription(new ArrayList<String>());
            this.ar.getDescription().add(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#getAppRoleDescription()
     */
    @Override
    public String getAppRoleDescription() {
        if (this.ar != null) {
            if (this.ar.getDescription() != null
                    && this.ar.getDescription().size() > 0) {
                return this.ar.getDescription().get(0);
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#setRoleName(java.lang.String)
     */
    @Override
    public void setRoleName(String value) {
        if (this.ar != null) {
            this.ar.setRoleId(value);
        }
        else if (this.r != null) {
            this.r.setCn(new ArrayList<String>());
            this.r.getCn().add(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#getRoleName()
     */
    @Override
    public String getRoleName() {
        if (this.ar != null) {
            return this.ar.getRoleId();
        }
        else if (this.r != null) {
            if (this.r.getCn() != null && this.r.getCn().size() > 0) {
                return this.r.getCn().get(0);
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#setAppName(java.lang.String)
     */
    @Override
    public void setAppName(String value) {
        if (this.ar != null) {
            this.ar.setAppId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#getAppName()
     */
    @Override
    public String getAppName() {
        if (this.ar != null) {
            return this.ar.getAppId();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#setRoleDescription(java.lang.String)
     */
    @Override
    public void setRoleDescription(String value) {
        if (this.r != null) {
            this.r.setDescription(new ArrayList<String>());
            this.r.getDescription().add(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CategoryDto#getRoleDescription()
     */
    @Override
    public String getRoleDescription() {
        if (this.r != null) {
            if (this.r.getDescription() != null
                    && this.r.getDescription().size() > 0) {
                return this.r.getDescription().get(0);
            }
        }
        return null;
    }

    /**
     * Not supported
     */
    @Override
    public void setAppRoleId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public int getAppRoleId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public void setApplicationId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public int getApplicationId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public void setRoleId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public int getRoleId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public void setAppDescription(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public String getAppDescription() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setUsername(java.lang.String)
     */
    @Override
    public void setUsername(String value) {
        this.u.setLoginid(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getUsername()
     */
    @Override
    public String getUsername() {
        return this.u.getLoginid();
    }

}
