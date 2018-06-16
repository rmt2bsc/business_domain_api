package org.dao.mapping.orm.ldap;

/**
 * @author Roy Terrell
 *
 */
public class LdapLookupGroup extends LdapCommonLookup {

    private String ou;
    
    /**
     * 
     */
    public LdapLookupGroup() {
	super();
    }

    /**
     * @return the ou
     */
    public String getOu() {
        return ou;
    }

    /**
     * @param ou the ou to set
     */
    public void setOu(String ou) {
        this.ou = ou;
    }

}
