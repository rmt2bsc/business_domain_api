package org.dao.mapping.orm.ldap;

import com.api.ldap.beans.LdapCommonEntity;

/**
 * ORM bean for mapping user group related data coming from a LDAP server.
 * <p>
 * Using the java bean naming specification, the accessor and mutator method
 * names can be used to determine the name of the LDAP attribute in which they
 * manage. For example, the method <i>getCn()</i> accessing the LDAP attribute,
 * <i>cn</i>.
 * <p>
 * The properties, <i>cn</i> and <i>description</i> reprsent the group name and
 * group description, respectively.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapUserGroup extends LdapCommonEntity {

    /**
     * Create a LdapUserGroup object
     */
    public LdapUserGroup() {
        super();
    }
}
