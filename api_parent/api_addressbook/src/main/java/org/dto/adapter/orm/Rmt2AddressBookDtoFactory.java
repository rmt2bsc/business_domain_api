package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Business;
import org.dao.mapping.orm.rmt2.CityType;
import org.dao.mapping.orm.rmt2.Country;
import org.dao.mapping.orm.rmt2.GeneralCodes;
import org.dao.mapping.orm.rmt2.GeneralCodesGroup;
import org.dao.mapping.orm.rmt2.IpLocation;
import org.dao.mapping.orm.rmt2.Person;
import org.dao.mapping.orm.rmt2.State;
import org.dao.mapping.orm.rmt2.TimeZone;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCodes;
import org.dao.mapping.orm.rmt2.VwCommonContact;
import org.dao.mapping.orm.rmt2.VwPersonAddress;
import org.dao.mapping.orm.rmt2.VwStateCountry;
import org.dao.mapping.orm.rmt2.Zipcode;
import org.dto.AddressDto;
import org.dto.BusinessContactDto;
import org.dto.CityTypeDto;
import org.dto.ContactDto;
import org.dto.CountryDto;
import org.dto.CountryRegionDto;
import org.dto.IpLocationDto;
import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;
import org.dto.PersonalContactDto;
import org.dto.RegionDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;

import com.RMT2Base;

/**
 * A factory containing several adapters which function to create Address Book
 * API specific entity beans.
 * 
 * @author Roy Terrell.
 * 
 */
public class Rmt2AddressBookDtoFactory extends RMT2Base {

    /**
     * Creates a instance of class <i>AddressDto</i> based on <i>Address</i>
     * instance.
     * 
     * @return an instance of {@link AddressDto}.
     */
    public static final AddressDto getNewAddressInstance() {
        Address obj = new Address();
        return Rmt2AddressBookDtoFactory.getAddressDtoInstance(obj);
    }

    /**
     * Creates an instance of <i>AddressDto</i> using a valid <i>Address</i>
     * object.
     * 
     * @param addr
     *            an instance of {@link Address}
     * @return an instance of {@link AddressDto}
     */
    public static final AddressDto getAddressDtoInstance(Address addr) {
        AddressDto dto = new AddressRmt2OrmAdapter(addr);
        return dto;
    }

    /**
     * Creates a new instance of class <i>BusinessContactDto</i>.
     * <p>
     * The entityTypeId and servTypeId properties will default to an "unknow"
     * code respective to their code groups.
     * 
     * @return an instance of {@link BusinessContactDto}.
     */
    public static final BusinessContactDto getNewBusinessInstance() {
        BusinessContactDto dto = Rmt2AddressBookDtoFactory.getBusinessInstance(null, null);
        dto.setEntityTypeId(-100);
        dto.setServTypeId(-110);
        return dto;
    }

    /**
     * Creates an instance of <i>BusinessContactDto</i> using valid
     * <i>VwBusinessAddress</i> object.
     * 
     * @param bus
     *            an instance of {@link VwBusinessAddress}
     * @return an instance of {@link BusinessContactDto}
     */
    public static final BusinessContactDto getBusinessInstance(VwBusinessAddress bus) {
        BusinessContactDto dto = new BusinessRmt2OrmAdapter(bus);
        return dto;
    }

    /**
     * Creates an instance of <i>BusinessContactDto</i> using valid
     * <i>Business</i> and <i>Address</i> objects.
     * 
     * @param bus
     *            an instance of {@link Business}
     * @param addr
     *            an instance of {@link Address}
     * @return an instance of {@link BusinessContactDto}
     */
    public static final BusinessContactDto getBusinessInstance(Business bus, Address addr) {
        BusinessContactDto dto = new BusinessRmt2OrmAdapter(bus, addr);
        return dto;
    }

    /**
     * Creates a new instance of class <i>PersonalContactDto</i>.
     * 
     * @return an instance of {@link PersonalContactDto}.
     */
    public static final PersonalContactDto getNewPersonInstance() {
        return Rmt2AddressBookDtoFactory.getPersonInstance(null, null);
    }

    /**
     * Creates an instance of <i>PersonalContactDto</i> using valid
     * <i>VwPersonAddress</i> object.
     * 
     * @param per
     *            an instance of {@link VwPersonAddress}
     * @return an instance of {@link PersonalContactDto}
     */
    public static final PersonalContactDto getPersonInstance(VwPersonAddress per) {
        PersonalContactDto dto = new PersonalRmt2OrmAdapter(per);
        return dto;
    }

    /**
     * Creates an instance of <i>PersonalContactDto</i> using valid
     * <i>Person</i> and <i>Address</i> objects.
     * 
     * @param per
     *            an instance of {@link Person}
     * @param addr
     *            an instance of {@link Address}
     * @return an instance of {@link PersonalContactDto}
     */
    public static final PersonalContactDto getPersonInstance(Person per, Address addr) {
        PersonalContactDto dto = new PersonalRmt2OrmAdapter(per, addr);
        return dto;
    }

    /**
     * Creates a new instance of class <i>PersonalContactDto</i>.
     * 
     * @return an instance of {@link ContactDto}
     */
    public static final ContactDto getNewContactInstance() {
        return Rmt2AddressBookDtoFactory.getContactInstance(null);
    }

    /**
     * Creates an instance of <i>ContactDto</i> using a valid
     * <i>VwCommonContact</i> object.
     * 
     * @param contact
     *            an instance of {@link VwCommonContact}
     * @return an instance of {@link ContactDto}
     */
    public static final ContactDto getContactInstance(VwCommonContact contact) {
        ContactDto dto = new CommonContactRmt2OrmAdapter(contact);
        return dto;
    }

    /**
     * Creates a new instance of class <i>PersonalContactDto</i>.
     * 
     * @return an instance of {@link ContactDto}
     */
    public static final ZipcodeDto getNewZipCodeInstance() {
        Zipcode obj = null;
        return Rmt2AddressBookDtoFactory.getZipCodeInstance(obj);
    }

    /**
     * Creates an instance of <i>ZipcodeDto</i> using a valid <i>Zipcode</i>
     * object.
     * 
     * @param zip
     *            an instance of {@link Zipcode}
     * @return an instance of {@link ZipcodeDto}
     */
    public static final ZipcodeDto getZipCodeInstance(Zipcode zip) {
        ZipcodeDto dto = new PostalDataRmt2OrmAdapter(zip);
        return dto;
    }

    /**
     * Creates an instance of <i>ZipcodeDto</i> using valid <i>Zipcode</i>,
     * <i>TimeZone</i>, and <i>CityType</i> objects.
     * 
     * @param zip
     *            an instance of {@link Zipcode}
     * @param timeZone
     *            an instance of {@link TimeZone}
     * @param cityType
     *            an instance of {@link CityType}
     * @return an instance of {@link ZipcodeDto}
     */
    public static final ZipcodeDto getZipCodeInstance(Zipcode zip, TimeZone timeZone, CityType cityType) {
        ZipcodeDto dto = new PostalDataRmt2OrmAdapter(zip, timeZone, cityType);
        return dto;
    }

    /**
     * Creates a new instance of class <i>TimeZoneDto</i>.
     * 
     * @return an instance of {@link TimeZoneDto}
     */
    public static final TimeZoneDto getNewTimezoneInstance() {
        TimeZone obj = null;
        return Rmt2AddressBookDtoFactory.getTimezoneInstance(obj);
    }

    /**
     * Creates an instance of <i>TimeZoneDto</i> using a valid <i>TimeZone</i>
     * object.
     * 
     * @param tz
     *            an instance of {@link TimeZone}
     * @return an instance of {@link TimeZoneDto}
     */
    public static final TimeZoneDto getTimezoneInstance(TimeZone tz) {
        TimeZoneDto dto = new PostalDataRmt2OrmAdapter(tz);
        return dto;
    }

    /**
     * Creates a new instance of class <i>CityTypeDto</i>.
     * 
     * @return an instance of {@link CityTypeDto}
     */
    public static final CityTypeDto getNewCitTypeInstance() {
        CityType obj = null;
        return Rmt2AddressBookDtoFactory.getCityTypeInstance(obj);
    }

    /**
     * Creates an instance of <i>CityTypeDto</i> using a valid <i>CityType</i>
     * object.
     * 
     * @param cityType
     *            an instance of {@link CityType}
     * @return an instance of {@link CityTypeDto}
     */
    public static final CityTypeDto getCityTypeInstance(CityType cityType) {
        CityTypeDto dto = new PostalDataRmt2OrmAdapter(cityType);
        return dto;
    }

    /**
     * Creates a new instance of class <i>CountryDto</i>.
     * 
     * @return an instance of {@link CountryDto}
     */
    public static final CountryDto getNewCountryInstance() {
        Country obj = null;
        return Rmt2AddressBookDtoFactory.getCountryInstance(obj);
    }

    /**
     * Creates an instance of <i>CountryDto</i> using a valid <i>Country</i>
     * object.
     * 
     * @param country
     *            an instance of {@link Country}
     * @return an instance of {@link CountryDto}
     */
    public static final CountryDto getCountryInstance(Country country) {
        CountryDto dto = new PostalDataRmt2OrmAdapter(country);
        return dto;
    }

    /**
     * Creates a new instance of class <i>RegionDto</i> with country and
     * state/province data.
     * 
     * @return an instance of {@link RegionDto}
     */
    public static final RegionDto getNewRegionInstance() {
        State state = null;
        return Rmt2AddressBookDtoFactory.getRegionInstance(state);
    }

    /**
     * Creates an instance of <i>RegionDto</i> using a valid <i>State</i>
     * object.
     * 
     * @param state
     *            an instance of {@link State}
     * @return an instance of {@link RegionDto}
     */
    public static final RegionDto getRegionInstance(State state) {
        RegionDto dto = new PostalDataRmt2OrmAdapter(state);
        return dto;
    }

    /**
     * Creates a new instance of class <i>CountryRegionDto</i> with country and
     * state/province data.
     * 
     * @return an instance of {@link CountryRegionDto}
     */
    public static final CountryRegionDto getNewCountryRegionInstance() {
        VwStateCountry stateCountry = null;
        return Rmt2AddressBookDtoFactory.getCountryRegionInstance(stateCountry);
    }

    /**
     * Creates an instance of <i>CountryRegionDto</i> using valid
     * <i>VwStateCountry</i> object.
     * 
     * @param vwsc
     *            an instance of {@link VwStateCountry}
     * @return an instance of {@link CountryRegionDto}
     */
    public static final CountryRegionDto getCountryRegionInstance(VwStateCountry vwsc) {
        CountryRegionDto dto = new PostalDataRmt2OrmAdapter(vwsc);
        return dto;
    }

    /**
     * Creates a new instance of class <i>LookupCodeDto</i>.
     * 
     * @return an instance of {@link LookupCodeDto}
     */
    public static final LookupCodeDto getNewCodeInstance() {
        GeneralCodes codeOrm = null;
        return Rmt2AddressBookDtoFactory.getCodeInstance(codeOrm);
    }

    /**
     * Creates a new instance of class <i>LookupGroupDto</i>.
     * 
     * @return an instance of {@link LookupGroupDto}
     */
    public static final LookupGroupDto getNewCodeGroupInstance() {
        GeneralCodesGroup grpOrm = null;
        return Rmt2AddressBookDtoFactory.getCodeInstance(grpOrm);
    }

    /**
     * Creates a new instance of class <i>LookupExtDto</i>.
     * 
     * @return an instance of {@link LookupExtDto}
     */
    public static final LookupExtDto getNewCodeExtInstance() {
        VwCodes c = null;
        return Rmt2AddressBookDtoFactory.getCodeInstance(c);
    }

    /**
     * Creates an instance of <i>LookupExtDto</i> using a valid <i>VwCodes</i>
     * object.
     * 
     * @param lookup
     *            an instance of {@link VwCodes}
     * @return an instance of {@link LookupExtDto}
     */
    public static final LookupExtDto getCodeInstance(VwCodes lookup) {
        LookupExtDto dto = new LookupRmt2OrmAdapter(lookup);
        return dto;
    }

    /**
     * Creates an instance of <i>LookupGroupDto</i> using a valid
     * <i>GeneralCodesGroup</i> object.
     * 
     * @param grp
     *            an instance of {@link GeneralCodesGroup}
     * @return an instance of {@link LookupGroupDto}
     */
    public static final LookupGroupDto getCodeInstance(GeneralCodesGroup grp) {
        LookupGroupDto dto = new LookupRmt2OrmAdapter(grp);
        return dto;
    }

    /**
     * Creates an instance of <i>LookupExtDto</i> using valid
     * <i>GeneralCodes</i> object.
     * 
     * @param lookup
     *            an instance of {@link GeneralCodes}
     * @return an instance of {@link LookupCodeDto}
     */
    public static final LookupCodeDto getCodeInstance(GeneralCodes lookup) {
        LookupCodeDto dto = new LookupRmt2OrmAdapter(lookup);
        return dto;
    }

    /**
     * Creates a new instance of class <i>IpLocationDto</i>.
     * 
     * @return an instance of {@link IpLocationDto}
     */
    public static final IpLocationDto getNewIpLocationInstance() {
        return Rmt2AddressBookDtoFactory.getIpLocationInstance(null);
    }

    /**
     * Creates an instance of <i>IpLocationDto</i> using a valid
     * <i>IpLocation</i> object.
     * 
     * @param ip
     *            an instance of {@link IpLocation}
     * @return an instance of {@link IpLocationDto}
     */
    public static final IpLocationDto getIpLocationInstance(IpLocation ip) {
        IpLocationDto dto = new IpLocationRmt2OrmAdapter(ip);
        return dto;
    }

}
