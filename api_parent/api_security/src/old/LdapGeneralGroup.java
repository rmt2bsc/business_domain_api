package org.dao.mapping.orm.ldap;

/**
 * ORM bean for mapping general lookup code group data coming from a LDAP server.
 * 
 * @author Roy Terrell
 *
 */
public class LdapGeneralGroup extends LdapCommonEntity {
    private String uid;

    private String ou;


    /**
     * 
     */
    public LdapGeneralGroup() {
	super();
    }
    
    
    /**
     * @return the uid
     */
    public String getUid() {
	return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
	this.uid = uid;
    }

    /**
     * @param ou the ou to set
     */
    public void setOu(String ou) {
	this.ou = ou;
    }
 
}
