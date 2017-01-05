package org.dto.adapter.ldap;

import java.util.ArrayList;

import org.dao.mapping.orm.ldap.LdapResource;

import org.dto.DefaultResourceAdapter;
import org.dto.ResourceDto;

import com.RMT2Constants;

/**
 * Adapts a LDAP <i>LdapResource</i> object to an <i>ResourceDto</i>.
 * 
 * @author rterrell
 * 
 */
class ResourceLdapAdapter extends DefaultResourceAdapter implements ResourceDto {

    private LdapResource r;

    /**
     * Create a ResourceRmt2OrmAdapter using an instance of <i>UserResource</i>.
     * 
     * @param rsrc
     *            an instance of {@link LdapResource}
     */
    protected ResourceLdapAdapter(LdapResource rsrc) {
        super();
        if (rsrc == null) {
            rsrc = new LdapResource();
        }
        this.r = rsrc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setName(java.lang.String)
     */
    @Override
    public void setName(String value) {
        this.r.setCn(new ArrayList<String>());
        this.r.getCn().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getName()
     */
    @Override
    public String getName() {
        if (this.r.getCn() != null && this.r.getCn().size() > 0) {
            return this.r.getCn().get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceAdapter#setTypeDescription(java.lang.String)
     */
    @Override
    public void setTypeDescription(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceAdapter#getTypeDescription()
     */
    @Override
    public String getTypeDescription() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceAdapter#setSubTypeName(java.lang.String)
     */
    @Override
    public void setSubTypeName(String value) {
        this.r.setOu(new ArrayList<String>());
        this.r.getOu().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceAdapter#getSubTypeName()
     */
    @Override
    public String getSubTypeName() {
        if (this.r.getOu() != null && this.r.getOu().size() > 0) {
            return this.r.getOu().get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceAdapter#setSubTypeDescription(java.lang.String)
     */
    @Override
    public void setSubTypeDescription(String value) {
        this.r.setDescription(new ArrayList<String>());
        this.r.getDescription().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceAdapter#getSubTypeDescription()
     */
    @Override
    public String getSubTypeDescription() {
        if (this.r.getDescription() != null
                && this.r.getDescription().size() > 0) {
            return this.r.getDescription().get(0);
        }
        return null;
    }

    // /* (non-Javadoc)
    // * @see org.dto.DefaultResourceRmt2OrmAdapter#setUrl(java.lang.String)
    // */
    // @Override
    // public void setRequestUrl(String value) {
    // this.r.setRequestUrl(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.DefaultResourceRmt2OrmAdapter#getUrl()
    // */
    // @Override
    // public String getRequestUrl() {
    // return this.r.getRequestUrl();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.DefaultResourceAdapter#setReplyMsgId(java.lang.String)
    // */
    // @Override
    // public void setReplyMsgId(String value) {
    // this.r.setReplyMsgId(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.DefaultResourceAdapter#getReplyMsgId()
    // */
    // @Override
    // public String getReplyMsgId() {
    // return this.r.getReplyMsgId();
    // }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setDescription(java.lang.String)
     */
    @Override
    public void setDescription(String value) {
        this.r.setDescription(new ArrayList<String>());
        this.r.getDescription().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getDescription()
     */
    @Override
    public String getDescription() {
        if (this.r.getDescription() != null
                && this.r.getDescription().size() > 0) {
            return this.r.getDescription().get(0);
        }
        return null;
    }

    // /* (non-Javadoc)
    // * @see org.dto.DefaultResourceRmt2OrmAdapter#setSecured(boolean)
    // */
    // @Override
    // public void setSecured(int value) {
    // this.r.setIsSecured(String.valueOf(value));
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.DefaultResourceRmt2OrmAdapter#getSecured()
    // */
    // @Override
    // public int getSecured() {
    // try {
    // return Integer.parseInt(this.r.getIsSecured());
    // }
    // catch (NumberFormatException e) {
    // return 0;
    // }
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.DefaultResourceRmt2OrmAdapter#getHost()
    // */
    // @Override
    // public String getHost() {
    // return this.r.getHost();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.DefaultResourceRmt2OrmAdapter#setHost(java.lang.String)
    // */
    // @Override
    // public void setHost(String hostName) {
    // this.r.setHost(hostName);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.DefaultResourceRmt2OrmAdapter#getRouterType()
    // */
    // @Override
    // public String getRouterType() {
    // return this.r.getRouterType();
    // }
    //
    // /* (non-Javadoc)
    // * @see
    // org.dto.DefaultResourceRmt2OrmAdapter#setRouterType(java.lang.String)
    // */
    // @Override
    // public void setRouterType(String routerType) {
    // this.r.setRouterType(routerType);
    // }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setUid(int)
     */
    @Override
    public void setUid(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getUid()
     */
    @Override
    public int getUid() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setTypeId(int)
     */
    @Override
    public void setTypeId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getTypeId()
     */
    @Override
    public int getTypeId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setSubTypeId(int)
     */
    @Override
    public void setSubTypeId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeId()
     */
    @Override
    public int getSubTypeId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
