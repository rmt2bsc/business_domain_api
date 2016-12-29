package org.dto;

/**
 * @author rterrell
 *
 */
public class DefaultWebServiceAdapter extends DefaultResourceAdapter {
    
    private boolean querySecureFlag;

    /**
     * 
     */
    public DefaultWebServiceAdapter() {
	super();
	this.querySecureFlag = false;
    }

    
    /* (non-Javadoc)
     * @see org.dto.ResourceDto#setUrl(java.lang.String)
     */
    public void setRequestUrl(String value) {
	return;
    }

    /* (non-Javadoc)
     * @see org.dto.ResourceDto#getUrl()
     */
    public String getRequestUrl() {
	return null;
    }
    
    /* (non-Javadoc)
     * @see org.dto.ResourceDto#setSecured(boolean)
     */
    public void setSecured(int value) {
	return;
    }

    /* (non-Javadoc)
     * @see org.dto.ResourceDto#isSecured()
     */
    public int getSecured() {
	return 0;
    }
    
    /* (non-Javadoc)
     * @see org.dto.ResourceDto#getHost()
     */
    public String getHost() {
	return null;
    }

    /* (non-Javadoc)
     * @see org.dto.ResourceDto#setHost(java.lang.String)
     */
    public void setHost(String hostName) {
	return;
    }

    /* (non-Javadoc)
     * @see org.dto.ResourceDto#getRouterType()
     */
    public String getRouterType() {
	return null;
    }

    /* (non-Javadoc)
     * @see org.dto.ResourceDto#setRouterType(java.lang.String)
     */
    public void setRouterType(String routerType) {
	return;
    }

    /**
     * @return the querySecureFlag
     */
    public boolean isQuerySecureFlag() {
        return querySecureFlag;
    }

    /**
     * @param querySecureFlag the querySecureFlag to set
     */
    public void setQuerySecureFlag(boolean querySecureFlag) {
        this.querySecureFlag = querySecureFlag;
    }

    public void setReplyMsgId(String value) {
	// TODO Auto-generated method stub
	
    }

    public String getReplyMsgId() {
	// TODO Auto-generated method stub
	return null;
    }
    
}
