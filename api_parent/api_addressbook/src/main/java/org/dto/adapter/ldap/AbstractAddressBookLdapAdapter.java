package org.dto.adapter.ldap;

import java.util.Date;

import com.RMT2Constants;
import com.api.foundation.AbstractBaseDtoImpl;

/**
 * @author Roy Terrell
 * 
 */
public abstract class AbstractAddressBookLdapAdapter extends
        AbstractBaseDtoImpl {

    /**
     * Default Constructor
     */
    public AbstractAddressBookLdapAdapter() {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#setDateCreated(java.util.Date)
     */
    public void setDateCreated(Date date) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#getDateCreated()
     */
    public Date getDateCreated() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#setDateUpdated(java.util.Date)
     */
    public void setDateUpdated(Date date) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#getDateUpdated()
     */
    public Date getDateUpdated() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#setUpdateUserId(java.lang.String)
     */
    public void setUpdateUserId(String userName) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#getUpdateUserId()
     */
    public String getUpdateUserId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#setIpCreated(java.lang.String)
     */
    public void setIpCreated(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#getIpCreated()
     */
    public String getIpCreated() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#setIpUpdated(java.lang.String)
     */
    public void setIpUpdated(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.foundation.TransactionDto#getIpUpdated()
     */
    public String getIpUpdated() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
}
