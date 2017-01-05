package org.dto.adapter.ldap;

import java.util.ArrayList;

import org.dao.mapping.orm.ldap.LdapTimezone;
import org.dto.TimeZoneDto;

/**
 * Adapts a JNDI/LDAP <i>Zip Code</i> object to an <i>TimeZoneDto</i> object.
 * 
 * @author Roy Terrell
 * 
 */
class TimezoneLdapAdapter extends AbstractAddressBookLdapAdapter implements
        TimeZoneDto {

    private LdapTimezone t;

    /**
     * Create a TimezoneLdapAdapter using an instance of <i>LdapTimezone</i>.
     * 
     * @param timezone
     *            an instance of {@link LdapTimezone} or null for the purpose of
     *            creating a new Zip Code object
     */
    protected TimezoneLdapAdapter(LdapTimezone timezone) {
        if (timezone == null) {
            timezone = new LdapTimezone();
        }
        this.t = timezone;
    }

    /**
     * Not Supported
     */
    @Override
    public void setTimeZoneId(int value) {
        if (this.t.getUid() == null) {
            this.t.setUid(new ArrayList<String>());
        }
        this.t.getUid().add(String.valueOf(value));
    }

    /**
     * Not Supported
     */
    @Override
    public int getTimeZoneId() {
        if (this.t.getUid() == null) {
            return 0;
        }
        int val;
        try {
            val = Integer.parseInt(this.t.getUid().get(0));
        } catch (NumberFormatException e) {
            val = 0;
        }
        return val;
    }

    @Override
    public void setTimeZoneDescr(String value) {
        if (this.t.getDescription() == null) {
            this.t.setDescription(new ArrayList<String>());
        }
        this.t.getDescription().add(value);
    }

    @Override
    public String getTimeZoneDescr() {
        if (this.t.getDescription() == null) {
            return null;
        }
        return this.t.getDescription().get(0);
    }

}
