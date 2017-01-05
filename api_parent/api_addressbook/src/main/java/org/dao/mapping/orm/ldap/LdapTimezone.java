package org.dao.mapping.orm.ldap;

import java.util.List;

import com.api.ldap.beans.LdapCommonEntity;

/**
 * A bean for capturing and managing time zone data from an LDAP data source.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapTimezone extends LdapCommonEntity {

    private List<String> uid;

    /**
     * Default constructor.
     */
    public LdapTimezone() {
        super();
    }

    /**
     * @return the uid
     */
    public List<String> getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(List<String> uid) {
        this.uid = uid;
    }

}