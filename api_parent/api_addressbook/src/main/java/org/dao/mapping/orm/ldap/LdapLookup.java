package org.dao.mapping.orm.ldap;

import java.util.List;

import com.api.ldap.beans.LdapCommonEntity;

/**
 * A bean for capturing and managing group and detail code lookup data from an
 * LDAP data source.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapLookup extends LdapCommonEntity {

    private List<String> uid;

    /**
     * Default contructor
     */
    public LdapLookup() {
        return;
    }

    /**
     * Get the internal unique id of the a code group or code detail item
     * 
     * @return the uid
     */
    public List<String> getUid() {
        return uid;
    }

    /**
     * Set the internal unique id of the a code group or code detail item
     * 
     * @param uid
     *            the internal unique id
     */
    public void setUid(List<String> uid) {
        this.uid = uid;
    }

}
