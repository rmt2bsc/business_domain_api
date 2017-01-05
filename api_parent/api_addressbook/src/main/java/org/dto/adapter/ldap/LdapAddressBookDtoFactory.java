package org.dto.adapter.ldap;

import org.dao.mapping.orm.ldap.LdapLookup;
import org.dao.mapping.orm.ldap.LdapCountry;
import org.dao.mapping.orm.ldap.LdapIp;
import org.dao.mapping.orm.ldap.LdapRegion;
import org.dao.mapping.orm.ldap.LdapTimezone;
import org.dao.mapping.orm.ldap.LdapZipcode;
import org.dto.CountryDto;
import org.dto.IpLocationDto;
import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;
import org.dto.RegionDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;

import com.RMT2Base;

/**
 * A factory containing several adapters which function to create Address Book
 * API specific entity beans from JNDI/LDAP objects.
 * 
 * @author Roy Terrell.
 * 
 */
public class LdapAddressBookDtoFactory extends RMT2Base {

    /**
     * Creates an instance of <i>ZipcodeDto</i> using a valid <i>LdapZipcode</i>
     * object.
     * 
     * @param zip
     *            an instance of {@link LdapZipcode}
     * @return an instance of {@link ZipcodeDto}
     */
    public static final ZipcodeDto getZipCodeInstance(LdapZipcode zip) {
        ZipcodeDto dto = new ZipCodeLdapAdapter(zip);
        return dto;
    }

    /**
     * Creates an instance of <i>TimeZoneDto</i> using a valid
     * <i>LdapTimezone</i> object.
     * 
     * @param timezone
     *            an instance of {@link LdapTimezone}
     * @return an instance of {@link TimeZoneDto}
     */
    public static final TimeZoneDto getTimezoneInstance(LdapTimezone timezone) {
        TimeZoneDto dto = new TimezoneLdapAdapter(timezone);
        return dto;
    }

    /**
     * Creates an instance of <i>IpLocationDto</i> using a valid <i>LdapIp</i>
     * object.
     * 
     * @param ip
     *            an instance of {@link LdapIp}
     * @return an instance of {@link IpLocationDto}
     */
    public static final IpLocationDto getIpAddressInstance(LdapIp ip) {
        IpLocationDto dto = new IpLocationLdapAdapter(ip);
        return dto;
    }

    /**
     * Creates an instance of <i>CountryDto</i> using a valid <i>LdapCountry</i>
     * object.
     * 
     * @param country
     *            an instance of {@link LdapCountry}
     * @return an instance of {@link CountryDto}
     */
    public static final CountryDto getCountryInstance(LdapCountry country) {
        CountryDto dto = new CountryLdapAdapter(country);
        return dto;
    }

    /**
     * Creates an instance of <i>RegionDto</i> using a valid <i>LdapRegion</i>
     * object.
     * 
     * @param region
     *            an instance of {@link LdapRegion}
     * @return an instance of {@link RegionDto}
     */
    public static final RegionDto getRegionInstance(LdapRegion region) {
        RegionDto dto = new RegionLdapAdapter(region);
        return dto;
    }

    /**
     * Creates an instance of <i>LookupExtDto</i> using a valid
     * <i>LdapLookup</i> object.
     * 
     * @param item
     *            an instance of {@link LdapLookup}
     * @return an instance of {@link LookupExtDto}
     */
    public static final LookupExtDto getLookupInstance(LdapLookup item) {
        LookupExtDto dto = new LookupLdapAdapter(item);
        return dto;
    }

    /**
     * Creates an instance of <i>LookupGroupDto</i> using a valid
     * <i>LdapLookupGroup</i> object.
     * 
     * @param grp
     *            an instance of {@link LdapLookup}
     * @return an instance of {@link LookupGroupDto}
     */
    public static final LookupGroupDto getLookupGroupInstance(LdapLookup grp) {
        LookupGroupDto dto = new LookupLdapAdapter(grp);
        return dto;
    }

    /**
     * Creates an instance of <i>LookupCodeDto</i> using a valid
     * <i>LdapLookupDetail</i> object.
     * 
     * @param code
     *            an instance of {@link LdapLookup}
     * @return an instance of {@link LookupCodeDto}
     */
    public static final LookupCodeDto getLookupCodeInstance(LdapLookup code) {
        LookupCodeDto dto = new LookupLdapAdapter(code);
        return dto;
    }
}
