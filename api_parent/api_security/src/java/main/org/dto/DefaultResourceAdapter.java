package org.dto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An adapter for resource related objects.
 * 
 * @author rterrell
 * 
 */
public class DefaultResourceAdapter extends TransactionDtoImpl implements
        ResourceDto {

    private boolean querySecureFlag;

    /**
     * Create a DefaultResourceAdapter
     */
    public DefaultResourceAdapter() {
        super();
        // this.querySecureFlag = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setUid(int)
     */
    @Override
    public void setUid(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getUid()
     */
    @Override
    public int getUid() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setName(java.lang.String)
     */
    @Override
    public void setName(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getName()
     */
    @Override
    public String getName() {
        return null;
    }

    // /* (non-Javadoc)
    // * @see org.dto.ResourceDto#setUrl(java.lang.String)
    // */
    // @Override
    // public void setRequestUrl(String value) {
    // return;
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.ResourceDto#getUrl()
    // */
    // @Override
    // public String getRequestUrl() {
    // return null;
    // }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setDescription(java.lang.String)
     */
    @Override
    public void setDescription(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getDescription()
     */
    @Override
    public String getDescription() {
        return null;
    }

    // /* (non-Javadoc)
    // * @see org.dto.ResourceDto#setSecured(boolean)
    // */
    // @Override
    // public void setSecured(int value) {
    // return;
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.ResourceDto#isSecured()
    // */
    // @Override
    // public int getSecured() {
    // return 0;
    // }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setResourceTypeId(int)
     */
    @Override
    public void setTypeId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getResourceTypeId()
     */
    @Override
    public int getTypeId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setResourceTypeName(java.lang.String)
     */
    @Override
    public void setTypeDescription(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getResourceTypeName()
     */
    @Override
    public String getTypeDescription() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setResourceSubTypeId(int)
     */
    @Override
    public void setSubTypeId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getResourceSubTypeId()
     */
    @Override
    public int getSubTypeId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setResourceSubTypeCode(java.lang.String)
     */
    @Override
    public void setSubTypeDescription(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getResourceSubTypeCode()
     */
    @Override
    public String getSubTypeDescription() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setResourceSubTypeName(java.lang.String)
     */
    @Override
    public void setSubTypeName(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getResourceSubTypeName()
     */
    @Override
    public String getSubTypeName() {
        return null;
    }

    // /* (non-Javadoc)
    // * @see org.dto.ResourceDto#getHost()
    // */
    // @Override
    // public String getHost() {
    // return null;
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.ResourceDto#setHost(java.lang.String)
    // */
    // @Override
    // public void setHost(String hostName) {
    // return;
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.ResourceDto#getRouterType()
    // */
    // @Override
    // public String getRouterType() {
    // return null;
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.ResourceDto#setRouterType(java.lang.String)
    // */
    // @Override
    // public void setRouterType(String routerType) {
    // return;
    // }
    //
    // /**
    // * @return the querySecureFlag
    // */
    // public boolean isQuerySecureFlag() {
    // return querySecureFlag;
    // }
    //
    // /**
    // * @param querySecureFlag the querySecureFlag to set
    // */
    // public void setQuerySecureFlag(boolean querySecureFlag) {
    // this.querySecureFlag = querySecureFlag;
    // }
    //
    // @Override
    // public void setReplyMsgId(String value) {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // @Override
    // public String getReplyMsgId() {
    // // TODO Auto-generated method stub
    // return null;
    // }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#setQuerySecureFlag(boolean)
     */
    public void setQuerySecureFlag(boolean querySecureFlag) {
        this.querySecureFlag = querySecureFlag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#isQuerySecureFlag()
     */
    public boolean isQuerySecureFlag() {
        return this.querySecureFlag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#setRouterType(java.lang.String)
     */
    public void setRouterType(String routerType) {
        throw new UnsupportedOperationException("this method is not supported");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#getRouterType()
     */
    public String getRouterType() {
        throw new UnsupportedOperationException("this method is not supported");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#getReplyMsgId()
     */
    public String getReplyMsgId() {
        throw new UnsupportedOperationException("this method is not supported");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.WebServiceDto#setReplyMsgId(java.lang.String)
     */
    public void setReplyMsgId(String value) {
        throw new UnsupportedOperationException("this method is not supported");
    }
}
