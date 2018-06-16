package org.dto.adapter.ldap;

import org.dao.mapping.orm.ldap.LdapWebService;
import org.dto.WebServiceDto;

/**
 * Adapts a LDAP <i>LdapWebService</i> object to an <i>WebServiceDto</i>.
 * 
 * @author Roy Terrell
 *
 */
/**
 * @author rterrell
 * 
 */
public class LdapWebServiceAdapter extends ResourceLdapAdapter implements
        WebServiceDto {

    private LdapWebService ws;

    private boolean querySecureFlag;

    /**
     * Construct a LdapWebServiceAdapter initialized with the web service object
     * to adapt.
     * 
     * @param rsrc
     *            The web service resource object
     */
    public LdapWebServiceAdapter(LdapWebService rsrc) {
        super(rsrc);
        if (rsrc == null) {
            rsrc = new LdapWebService();
        }
        this.ws = rsrc;
        this.querySecureFlag = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#setRequestUrl(java.lang.String)
     */
    @Override
    public void setRequestUrl(String value) {
        this.ws.setRequestUrl(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#getRequestUrl()
     */
    @Override
    public String getRequestUrl() {
        return this.ws.getRequestUrl();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceAdapter#setReplyMsgId(java.lang.String)
     */
    @Override
    public void setReplyMsgId(String value) {
        this.ws.setReplyMsgId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceAdapter#getReplyMsgId()
     */
    @Override
    public String getReplyMsgId() {
        return this.ws.getReplyMsgId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#setSecured(int)
     */
    @Override
    public void setSecured(Boolean value) {
        this.ws.setIsSecured(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#getSecured()
     */
    @Override
    public Boolean isSecured() {
        try {
            return Boolean.getBoolean(this.ws.getIsSecured());
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#getHost()
     */
    @Override
    public String getHost() {
        return this.ws.getHost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#setHost(java.lang.String)
     */
    @Override
    public void setHost(String hostName) {
        this.ws.setHost(hostName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceAdapter#getRouterType()
     */
    @Override
    public String getRouterType() {
        return this.ws.getRouterType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceAdapter#setRouterType(java.lang.String)
     */
    @Override
    public void setRouterType(String routerType) {
        this.ws.setRouterType(routerType);
    }

    /**
     * @return the querySecureFlag
     */
    public boolean isQuerySecureFlag() {
        return querySecureFlag;
    }

    /**
     * @param querySecureFlag
     *            the querySecureFlag to set
     */
    public void setQuerySecureFlag(boolean querySecureFlag) {
        this.querySecureFlag = querySecureFlag;
    }
}
