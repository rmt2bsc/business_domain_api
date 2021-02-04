package org.dto.converter.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Country;
import org.dao.mapping.orm.rmt2.GeneralCodes;
import org.dao.mapping.orm.rmt2.GeneralCodesGroup;
import org.dao.mapping.orm.rmt2.VwCodes;
import org.dao.mapping.orm.rmt2.VwStateCountry;
import org.dao.mapping.orm.rmt2.VwZipcode;
import org.dao.mapping.orm.rmt2.Zipcode;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.IpLocationDto;
import org.dto.LookupCodeDto;
import org.dto.PersonalContactDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;
import org.modules.lookup.LookupDataApi;
import org.modules.lookup.LookupDataApiException;
import org.modules.lookup.LookupDataApiFactory;
import org.modules.postal.PostalApi;
import org.modules.postal.PostalApiException;
import org.modules.postal.PostalApiFactory;
import org.rmt2.jaxb.AddressType;
import org.rmt2.jaxb.BusinessType;
import org.rmt2.jaxb.CitytypeType;
import org.rmt2.jaxb.CodeDetailType;
import org.rmt2.jaxb.CommonContactType;
import org.rmt2.jaxb.ContacttypeType;
import org.rmt2.jaxb.CountryType;
import org.rmt2.jaxb.GenerationType;
import org.rmt2.jaxb.IpDetails;
import org.rmt2.jaxb.LookupCodeType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.PersonType;
import org.rmt2.jaxb.StateType;
import org.rmt2.jaxb.TimezoneType;
import org.rmt2.jaxb.ZipcodeFullType;
import org.rmt2.jaxb.ZipcodeType;

import com.RMT2Base;
import com.api.util.RMT2Date;

/**
 * A factory for creating JAXB objects derived from contact DTO's.
 * 
 * @author Roy Terrell
 * 
 */
public class ContactsJaxbFactory extends RMT2Base {

    /**
     * Creates a ContactsJaxbFactory that is capable of functioning without the
     * use of an external datasource.
     */
    public ContactsJaxbFactory() {
        super();
    }

    /**
     * Creates a new JAXB BusinessType instance.
     * 
     * @return {@link com.xml.schema.bindings.BusinessType BusinessType}
     */
    public BusinessType createBusinessTypeInstance() {
        ObjectFactory f = new ObjectFactory();
        BusinessType bt = f.createBusinessType();
        bt.setBusinessId(BigInteger.valueOf(0));

        CodeDetailType cdt = this.getCodeDetail();
        bt.setEntityType(cdt);
        cdt = this.getCodeDetail();
        bt.setServiceType(cdt);

        bt.setLongName(null);
        bt.setShortName(null);
        bt.setContactFirstname(null);
        bt.setContactLastname(null);
        bt.setContactPhone(null);
        bt.setContactExt(null);
        bt.setContactEmail(null);
        bt.setTaxId(null);
        bt.setWebsite(null);

        AddressType at = this.getAddress();
        bt.setAddress(at);
        return bt;
    }

    /**
     * Creates a new CodeDetailType instance.
     * 
     * @return {@link com.xml.schema.bindings.CodeDetailType CodeDetailType}
     */
    public CodeDetailType getCodeDetail() {
        ObjectFactory f = new ObjectFactory();
        CodeDetailType cdt = f.createCodeDetailType();
        cdt.setCodeId(BigInteger.valueOf(0));
        // cdt.setCodeGroupId(BigInteger.valueOf(0));
        cdt.setLongdesc(null);
        cdt.setShortdesc(null);
        return cdt;
    }

    /**
     * 
     * @return
     */
    public AddressType getAddress() {
        ObjectFactory f = new ObjectFactory();
        AddressType at = f.createAddressType();
        at.setAddrId(BigInteger.valueOf(0));
        at.setPersonId(BigInteger.valueOf(0));
        at.setBusinessId(BigInteger.valueOf(0));
        at.setAddr1(null);
        at.setAddr2(null);
        at.setAddr3(null);
        at.setAddr4(null);

        ZipcodeType z = this.getZipcode();
        at.setZip(z);

        at.setZipExt(BigInteger.ZERO);
        at.setPhoneCell(null);
        at.setPhoneFax(null);
        at.setPhoneHome(null);
        at.setPhoneMain(null);
        at.setPhonePager(null);
        at.setPhoneWork(null);
        at.setPhoneWorkExt(null);
        return at;
    }

    /**
     * Create a new ZipcodeType instance and return the instance to the user.
     * 
     * @return {@link com.xml.schema.bindings.ZipcodeType ZipcodeType}
     */
    public ZipcodeType getZipcode() {
        ObjectFactory f = new ObjectFactory();
        ZipcodeType zip = f.createZipcodeType();
        zip.setZipId(BigInteger.valueOf(0));
        zip.setZipcode(BigInteger.valueOf(0));
        zip.setAreaCode(null);
        zip.setCity(null);
        zip.setCityAliasAbbr(null);
        zip.setCityAliasName(null);
        zip.setCountyName(null);
        zip.setState(null);
        return zip;
    }

    /**
     * Creates a List of BusinessType objects from data contained in the list of
     * BusinessContactDto objects.
     * 
     * @param contact
     *            List of {@link BusinessContactDto}
     * @return List of {@link com.xml.schema.bindings.BusinessType BusinessType}
     */
    public List<BusinessType> createBusinessTypeInstance(List<ContactDto> contacts) {
        List<BusinessType> list = new ArrayList<BusinessType>();
        for (ContactDto item : contacts) {
            if (item instanceof BusinessContactDto) {
                list.add(this.createBusinessTypeInstance((BusinessContactDto) item));
            }

        }
        return list;
    }

    /**
     * Creates a BusinessType object from data contained in a ContactDto object.
     * 
     * @param contact
     *            {@link ContactDto}
     * @return {@link com.xml.schema.bindings.BusinessType BusinessType}
     */
    public BusinessType createBusinessTypeInstance(ContactDto contact) {
        if (contact instanceof BusinessContactDto) {
            return this.createBusinessTypeInstance((BusinessContactDto) contact);
        }
        ObjectFactory f = new ObjectFactory();
        BusinessType bt = f.createBusinessType();
        bt.setBusinessId(BigInteger.valueOf(contact.getContactId()));

        bt.setLongName(contact.getContactName());
        bt.setContactEmail(contact.getContactEmail());

        AddressType at = this.getAddress(contact);
        bt.setAddress(at);
        return bt;
    }

    /**
     * Creates a BusinessType object from data contained in a BusinessContactDto
     * object.
     * 
     * @param contact
     *            {@link BusinessContactDto}
     * @return {@link com.xml.schema.bindings.BusinessType BusinessType}
     */
    public BusinessType createBusinessTypeInstance(BusinessContactDto contact) {
        ObjectFactory f = new ObjectFactory();
        BusinessType bt = f.createBusinessType();
        bt.setBusinessId(BigInteger.valueOf(contact.getContactId()));

        try {
            CodeDetailType cdt = this.getCodeDetail(contact.getEntityTypeId());
            bt.setEntityType(cdt);
            cdt = this.getCodeDetail(contact.getServTypeId());
            bt.setServiceType(cdt);
        } catch (LookupDataApiException e) {
            // Do nothing...
        }

        bt.setLongName(contact.getContactName());
        bt.setShortName(contact.getShortName());
        bt.setContactFirstname(contact.getContactFirstname());
        bt.setContactLastname(contact.getContactLastname());
        bt.setContactPhone(contact.getContactPhone());
        bt.setContactExt(contact.getContactExt());
        bt.setContactEmail(contact.getContactEmail());
        bt.setTaxId(contact.getTaxId());
        bt.setWebsite(contact.getWebsite());

        AddressType at = this.getAddress(contact);
        bt.setAddress(at);
        return bt;
    }

    /**
     * Creates a List of PersonType objects from data contained in the list of
     * PersonalContactDto objects.
     * 
     * @param contact
     *            List of {@link PersonalContactDto}
     * @return List of {@link com.xml.schema.bindings.BusinessType PersonType}
     */
    public List<PersonType> createPersonalTypeInstance(List<ContactDto> contacts) {
        List<PersonType> list = new ArrayList<>();
        for (ContactDto item : contacts) {
            if (item instanceof PersonalContactDto) {
                list.add(this.createPersonalTypeInstance((PersonalContactDto) item));
            }
        }
        return list;
    }
    
    /**
     * Creates a PersonType object from data contained in a ContactDto object.
     * 
     * @param contact
     *            {@link ContactDto}
     * @return {@link com.xml.schema.bindings.PersonType PersonType}
     */
    public PersonType createPersonalTypeInstance(ContactDto contact) {
        if (contact instanceof PersonalContactDto) {
            return this.createPersonalTypeInstance((PersonalContactDto) contact);
        }
        ObjectFactory f = new ObjectFactory();
        PersonType pt = f.createPersonType();
        pt.setPersonId(BigInteger.valueOf(contact.getContactId()));

        pt.setShortName(contact.getContactName());
        pt.setEmail(contact.getContactEmail());

        AddressType at = this.getAddress(contact);
        pt.setAddress(at);
        return pt;
    }
    
    /**
     * Creates a PersonType object from data contained in a PersonalContactDto
     * object.
     * 
     * @param contact
     *            {@link PersonalContactDto}
     * @return {@link com.xml.schema.bindings.PersonType PersonType}
     */
    public PersonType createPersonalTypeInstance(PersonalContactDto contact) {
        ObjectFactory f = new ObjectFactory();
        PersonType pt = f.createPersonType();

        // Bind primary key
        pt.setPersonId(BigInteger.valueOf(contact.getContactId()));

        // Bind various CodeDetailType types.
        try {
            // Bind title
            CodeDetailType cdt = this.getCodeDetail(contact.getTitle());
            pt.setTitle(cdt);
            // Bind gender
            cdt = this.getCodeDetail(contact.getGenderId());
            pt.setGender(cdt);
            // Bind marital status
            cdt = this.getCodeDetail(contact.getMaritalStatusId());
            pt.setMaritalStatus(cdt);
            // Bind race
            cdt = this.getCodeDetail(contact.getRaceId());
            pt.setRace(cdt);
        } catch (LookupDataApiException e) {
            // Do nothing...
        }

        pt.setFirstName(contact.getFirstname());
        pt.setMidName(contact.getMidname());
        pt.setLastName(contact.getLastname());
        pt.setMaidenName(contact.getMaidenname());
        pt.setShortName(contact.getContactName());
        pt.setBirthDate(RMT2Date.formatDate(contact.getBirthDate(), "MM/dd/yyyy"));
        pt.setEmail(contact.getContactEmail());
        pt.setSsn(contact.getSsn());

        // Bind generation
        try {
            GenerationType gt = GenerationType.fromValue(contact.getGeneration());
            pt.setGeneration(gt);
        } catch (Exception e) {
            // ...Do nothing
        }

        AddressType at = this.getAddress(contact);
        pt.setAddress(at);
        return pt;
    }

    /**
     * Creates a List of CommonContactType objects from data contained in the list of
     * ContactDto objects.
     * 
     * @param contact
     *            List of {@link ContactDto}
     * @return List of {@link com.xml.schema.bindings.CommonContactType CommonContactType}
     */
    public List<CommonContactType> createCommonContactTypeInstance(List<ContactDto> contacts) {
        List<CommonContactType> list = new ArrayList<>();
        for (ContactDto item : contacts) {
            if (item instanceof PersonalContactDto) {
                list.add(this.createCommonContactTypeInstance(item));
            }
        }
        return list;
    }
    
    /**
     * Creates a CommonContactType object from data contained in a ContactDto
     * object.
     * 
     * @param contact
     *            {@link ContactDto}
     * @return {@link com.xml.schema.bindings.CommonContactType CommonContactType}
     */
    public CommonContactType createCommonContactTypeInstance(ContactDto contact) {
        ObjectFactory f = new ObjectFactory();
        CommonContactType cct = f.createCommonContactType();

        // Bind primary key
        cct.setContactId(BigInteger.valueOf(contact.getContactId()));
        cct.setContactEmail(contact.getContactEmail());
        cct.setContactName(contact.getContactName());
        cct.setContactType(ContacttypeType.valueOf(contact.getContactType()));
        
        AddressType at = this.getAddress(contact);
        cct.setAddress(at);
        return cct;
    }
    
    /**
     * Creates a CodeDetailType instance usning a GeneralCodes id, <i>code</i>.
     * The parameter, <i>code</i>, is used to fetch the record from the database
     * and migrate the data to an instance of CodeDetailType, which is returned
     * to the caller.
     * 
     * @param code
     *            an integer value representing the primary key of the row to
     *            fetch from the GenrealCodes table.
     * @return {@link com.xml.schema.bindings.CodeDetailType CodeDetailType} or
     *         null when code = zero.
     * @throws GeneralCodeException
     */
    public CodeDetailType getCodeDetail(int code) throws LookupDataApiException {
        if (code == 0) {
            return null;
        }
        ObjectFactory f = new ObjectFactory();
        LookupDataApiFactory luFactory = new LookupDataApiFactory();
        LookupDataApi codes = luFactory.createApi();
        LookupCodeDto dto = (LookupCodeDto) codes.getCode(code);
        CodeDetailType cdt = f.createCodeDetailType();
        cdt.setCodeId(BigInteger.valueOf(dto.getCodeId()));
        cdt.setLongdesc(dto.getCodeLongName());
        cdt.setShortdesc(dto.getCodeShortName());
        codes.close();
        return cdt;
    }

    /**
     * Creates a JAXB BusinessType instance that is initialized by
     * <i>VwBusinessAddress</i> data.
     * 
     * @param addr
     *            {@link com.bean.VwBusinessAddress VwBusinessAddress}
     * @return {@link com.xml.schema.bindings.AddressType AddressType}
     */
    public AddressType getAddress(ContactDto contact) {
        ObjectFactory f = new ObjectFactory();
        AddressType at = f.createAddressType();
        at.setAddrId(BigInteger.valueOf(contact.getAddrId()));
        if (contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS)) {
            at.setBusinessId(BigInteger.valueOf(contact.getContactId()));
        }
        else {
            at.setPersonId(BigInteger.valueOf(contact.getContactId()));
        }

        at.setAddr1(contact.getAddr1());
        at.setAddr2(contact.getAddr2());
        at.setAddr3(contact.getAddr3());
        at.setAddr4(contact.getAddr4());
        try {
            ZipcodeType z = this.getZipcode(contact.getZip());
            at.setZip(z);
        } catch (PostalApiException e) {
            // Do nothing...
        }
        at.setZipExt(BigInteger.valueOf(contact.getZipext()));
        at.setPhoneCell(contact.getPhoneCell());
        at.setPhoneFax(contact.getPhoneFax());
        at.setPhoneHome(contact.getPhoneHome());
        at.setPhoneMain(contact.getPhoneCompany());
        at.setPhonePager(contact.getPhonePager());
        at.setPhoneWork(contact.getPhoneWork());
        at.setPhoneWorkExt(contact.getPhoneExt());
        return at;
    }

      /**
     * Creates a ZipcodeType instance from data obtained from the zipcode table
     * using <i>zipId</i> as the primary key. The parameter, <i>zipId</i>, is
     * used to fetch the record from the database and migrate the data to an
     * instance of ZipcodeType, which is returned to the caller.
     * 
     * @param zipId
     *            the primary key value used to fetch a row from the zipcode
     *            table
     * @return {@link com.xml.schema.bindings.ZipcodeType ZipcodeType}
     * @throws ZipcodeException
     */
    public ZipcodeType getZipcode(int zipId) throws PostalApiException {
        if (zipId == 0) {
            return null;
        }
        ObjectFactory f = new ObjectFactory();
        PostalApi api = PostalApiFactory.createApi();
        ZipcodeDto z = (ZipcodeDto) api.getZipCode(zipId);
        if (z == null) {
            return null;
        }
        ZipcodeType zip = f.createZipcodeType();
        BigInteger zipcodeVal = BigInteger.valueOf(z.getZip());
        zip.setZipId(zipcodeVal == BigInteger.ZERO ? null : zipcodeVal);
        zipcodeVal = BigInteger.valueOf(z.getZip());
        zip.setZipcode(zipcodeVal == BigInteger.ZERO ? null : zipcodeVal);

        zip.setAreaCode(z.getAreaCode());
        zip.setCity(zipcodeVal == BigInteger.ZERO ? "" : z.getCity());
        // zip.setCityAliasAbbr(z.getCityAliasAbbr());
        zip.setCityAliasName(z.getCityAliasName());
        zip.setCountyName(z.getCountyName());
        zip.setState(zipcodeVal == BigInteger.ZERO ? "" : z.getStateCode());
        return zip;
    }

    /**
     * Creates a CodeDetailType instance from an instance of GenrealCodes.
     * 
     * @param dbObj
     *            an instance of {@link com.bean.GeneralCodes GeneralCodes}
     * @return an instance of {@link com.xml.schema.bindings.CodeDetailType
     *         CodeDetailType}
     */
    public CodeDetailType createCodeDetailTypeInstance(GeneralCodes dbObj) {
        ObjectFactory f = new ObjectFactory();
        CodeDetailType cdt = f.createCodeDetailType();
        cdt.setCodeId(BigInteger.valueOf(dbObj.getCodeId()));
        cdt.setLongdesc(dbObj.getLongdesc());
        cdt.setShortdesc(dbObj.getShortdesc());
        return cdt;
    }

    /**
     * Creates a CodeDetailType instance using primitive data types.
     * 
     * @param codeId
     *            the genreal code id
     * @param shortDesc
     *            the abbreviated description of <i>codeId</i>
     * @param longDesc
     *            the long description of <i>codeId</i>
     * @return an instance of {@link com.xml.schema.bindings.CodeDetailType
     *         CodeDetailType}
     */
    public CodeDetailType createCodeDetailTypeInstance(int codeId, String shortDesc, String longDesc) {
        ObjectFactory f = new ObjectFactory();
        CodeDetailType cdt = f.createCodeDetailType();
        cdt.setCodeId(BigInteger.valueOf(codeId));
        cdt.setLongdesc(longDesc);
        cdt.setShortdesc(shortDesc);
        return cdt;
    }

    /**
     * 
     * @param id
     * @param countryName
     * @param countryCode
     * @return
     */
    public static final CountryType createCountryTypeInstance(int id, String countryName, String countryCode) {
        ObjectFactory f = new ObjectFactory();
        CountryType c = f.createCountryType();
        c.setCountryCode(countryCode);
        c.setCountryName(countryName);
        c.setCountryId(BigInteger.valueOf(id));
        return c;
    }
    
    /**
     * 
     * @param stateId
     * @param stateName
     * @param stateCode
     * @param countryId
     * @param countryName
     * @return
     */
    public static final StateType createStateTypeInstance(int stateId, String stateName, 
            String stateCode, int countryId, String countryName) {
        StateType o = createStateTypeInstance();
        o.setStateId(BigInteger.valueOf(stateId));
        o.setCountryId(BigInteger.valueOf(countryId));
        o.setStateCode(stateCode);
        o.setStateName(stateName);
        o.setCountryName(countryName);
        return o;
    }

    /**
     * 
     * @param dto
     * @return
     */
    public static final List<StateType> getStateType(List<VwStateCountry> dto) {
        if (dto == null) {
            return null;
        }
        List<StateType> stateList = new ArrayList<StateType>();
        for (VwStateCountry item : dto) {
            StateType state = getStateType(item);
            stateList.add(state);
        }
        return stateList;
    }

    /**
     * 
     * @param dto
     * @return
     */
    public static final StateType getStateType(VwStateCountry dto) {
        if (dto == null) {
            return null;
        }
        StateType state = createStateTypeInstance();
        state.setCountryId(BigInteger.valueOf(dto.getCountryId()));
        state.setCountryName(dto.getCountryName());
        state.setStateCode(dto.getStateCode());
        state.setStateId(BigInteger.valueOf(dto.getStateId()));
        state.setStateName(dto.getStateName());
        return state;
    }

    /**
     * 
     * @return
     */
    public static final StateType createStateTypeInstance() {
        ObjectFactory f = new ObjectFactory();
        StateType state = f.createStateType();
        return state;
    }

    /**
     * 
     * @param loc
     * @return
     */
    public static IpDetails getIpDetailsInstance(IpLocationDto loc) {
        ObjectFactory f = new ObjectFactory();
        IpDetails ip = f.createIpDetails();
        ip.setIpId(String.valueOf(loc.getIpRangeId()));
        ip.setIpFrom(String.valueOf(loc.getIpFrom()));
        ip.setIpTo(String.valueOf(loc.getIpTo()));
        ip.setCountryName(loc.getCountry());
        ip.setRegion(loc.getRegion());
        ip.setCity(loc.getCity());
        ip.setLatitude(String.valueOf(loc.getLatitude()));
        ip.setLongitude(String.valueOf(loc.getLongitude()));
        ip.setZip(loc.getPostalCode());
        return ip;
    }

    /**
     * 
     * @param c
     * @return
     */
    public static List<CountryType> getCountryTypeInstance(List<Country> c) {
        if (c == null) {
            return null;
        }
        ContactsJaxbFactory cf = new ContactsJaxbFactory();
        List<CountryType> list = new ArrayList<CountryType>();
        for (Country src : c) {
            CountryType ct = cf.createCountryTypeInstance(src.getCountryId(), src.getName(), src.getCode());
            list.add(ct);
        }
        return list;
    }

    /**
     * 
     * @param grp
     * @param codes
     * @return
     */
    public static LookupCodeType getLookupCodeTypeInstance(GeneralCodesGroup grp, List<GeneralCodes> codes) {
        ObjectFactory f = new ObjectFactory();
        ContactsJaxbFactory cf = new ContactsJaxbFactory();
        LookupCodeType lct = null;

        lct = f.createLookupCodeType();
        lct.setGroupId(BigInteger.valueOf(grp.getCodeGrpId()));
        lct.setLabel(grp.getDescription());

        // Build code instances for current group
        if (codes != null) {
            try {
                for (int ndx = 0; ndx < codes.size(); ndx++) {
                    GeneralCodes code = codes.get(ndx);
                    CodeDetailType cdt = cf.createCodeDetailTypeInstance(code.getCodeId(), code.getShortdesc(),
                            code.getLongdesc());
                    cdt.setLabel(code.getLongdesc());
                    lct.getCode().add(cdt);
                }
            } catch (Exception e) {
                return null;
            }
        }
        return lct;
    }

    /**
     * 
     * @param src
     * @return
     */
    public static List<LookupCodeType> getLookupCodeTypeInstance(List<VwCodes> src) {
        ObjectFactory f = new ObjectFactory();
        ContactsJaxbFactory cf = new ContactsJaxbFactory();
        List<LookupCodeType> lst = new ArrayList<LookupCodeType>();
        int prevGrpId = 0;
        LookupCodeType lct = null;
        try {
            for (int ndx = 0; ndx < src.size(); ndx++) {
                VwCodes code = src.get(ndx);
                if (prevGrpId != code.getGroupId()) {
                    // Setup new group
                    lct = f.createLookupCodeType();
                    lct.setGroupId(BigInteger.valueOf(code.getGroupId()));
                    lct.setLabel(code.getGroupDesc());
                    prevGrpId = code.getGroupId();
                }

                // Build code instances for current group
                if (code.getCodeId() > 0) {
                    CodeDetailType cdt = cf.createCodeDetailTypeInstance(code.getCodeId(), code.getCodeShortdesc(),
                            code.getCodeLongdesc());
                    cdt.setLabel(code.getCodeLongdesc());
                    lct.getCode().add(cdt);
                }
                if ((ndx + 1) == src.size() || src.get(ndx + 1).getGroupId() != prevGrpId) {
                    lst.add(lct);
                }
            }
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @param item
     * @return
     */
    public static StateType createStateTypeInstance(VwStateCountry item) {
        ObjectFactory f = new ObjectFactory();
        StateType st = f.createStateType();
        st.setCountryId(BigInteger.valueOf(item.getCountryId()));
        st.setStateId(BigInteger.valueOf(item.getStateId()));
        st.setStateCode(item.getStateCode());
        st.setStateName(item.getStateName());
        return st;
    }

    /**
     * 
     * @param items
     * @return
     */
    public static List<StateType> createStateTypeInstance(List<VwStateCountry> items) {
        List<StateType> list = new ArrayList<StateType>();
        for (VwStateCountry item : items) {
            StateType st = ContactsJaxbFactory.createStateTypeInstance(item);
            list.add(st);
        }
        return list;
    }

    // /**
    // *
    // * @param obj
    // * @param request
    // */
    // public static void updateUserSessionType(UserSessionType obj,
    // Request request) {
    // if (request != null) {
    // obj.setRemoteHost(request.getRemoteHost());
    // obj.setServerName(request.getServerName());
    // obj.setServerPort(BigInteger.valueOf(request.getServerPort()));
    // obj.setServletContext(request.getContextPath());
    // obj.setScheme(request.getScheme());
    // obj.setSessionId(request.getSession().getId());
    // }
    // return;
    // }

    // public static RMT2SessionBean getUserSession(UserSessionType obj,
    // Request request) {
    // RMT2SessionBean session;
    // try {
    // session = AuthenticationFactory.getSessionBeanInstance(
    // obj.getLoginId(), obj.getOrigAppId());
    // } catch (AuthenticationException e) {
    // throw new SystemException(e);
    // }
    // session.setLoginId(obj.getLoginId());
    //
    // session.setAuthSessionId(obj.getAuthSessionId());
    // session.setFirstName(obj.getFname());
    // session.setLastName(obj.getLname());
    // session.setAccessLevel(obj.getAccessLevel().intValue());
    // session.setGatewayInterface(obj.getGatewayInterface());
    // session.setRemoteAppName(obj.getRemoteAppName());
    // session.setSessionCreateTime(obj.getSessionCreate());
    // session.setSessionLastAccessedTime(obj.getSessionLastAccessed());
    // session.setSessionMaxInactSecs(obj.getSessionMax().intValue());
    // session.setGroupId(obj.getGroupId() == null ? 0 : Integer.parseInt(obj
    // .getGroupId()));
    //
    // if (request != null) {
    // session.setRemoteHost(request.getRemoteHost());
    // session.setRemoteAddress(request.getRemoteAddr());
    // session.setServerName(request.getServerName());
    // session.setServerPort(request.getServerPort());
    // session.setServletContext(request.getContextPath());
    // session.setScheme(request.getScheme());
    // session.setSessionId(request.getSession().getId());
    //
    // // TODO: change interface to recognize these attributes.
    // session.setServerProtocol(obj.getServerProtocol());
    // session.setServerInfo(obj.getServerInfo());
    // session.setServerSoftware(obj.getServerSoftware());
    // session.setUserAgent(obj.getUserAgent());
    // session.setLocale(obj.getLocal());
    // session.setAccept(obj.getAccept());
    // session.setAcceptLanguage(obj.getAcceptLang());
    // session.setAcceptEncoding(obj.getAcceptEncoding());
    // }
    //
    // List<String> roles = new ArrayList<String>();
    // for (AppRoleType role : obj.getRoles()) {
    // roles.add(role.getAppRoleCode());
    // }
    // session.setRoles(roles);
    // return session;
    // }
    //
    // /**
    // *
    // * @param ormList
    // * @return
    // */
    // public static List<CommonContactType> toCommonContactTypeList(
    // List<VwCommonContact> ormList, DatabaseConnectionBean con) {
    // ObjectFactory f = new ObjectFactory();
    // List<CommonContactType> list = new ArrayList<CommonContactType>();
    // for (VwCommonContact orm : ormList) {
    // CommonContactType c = f.createCommonContactType();
    // c.setContactId(BigInteger.valueOf(orm.getContactId()));
    // c.setContactName(orm.getContactName());
    // ContacttypeType ctt = ContacttypeType.fromValue(String.valueOf(orm
    // .getContactType()));
    // c.setContactType(ctt);
    // c.setContactEmail(orm.getEmail());
    //
    // AddressType at = f.createAddressType();
    // at.setAddrId(BigInteger.valueOf(orm.getAddrId()));
    // at.setAddr1(orm.getAddr1());
    // at.setAddr2(orm.getAddr2());
    // at.setAddr3(orm.getAddr3());
    // at.setAddr4(orm.getAddr4());
    // try {
    // ContactsJaxbFactory cf = new ContactsJaxbFactory(con);
    // ZipcodeType z = cf.getZipcode(orm.getAddrZip());
    // at.setZip(z);
    // } catch (ZipcodeException e) {
    // // Do nothing...
    // }
    // at.setZipExt(BigInteger.valueOf(orm.getAddrZipext()));
    // at.setPhoneCell(orm.getAddrPhoneCell());
    // at.setPhoneFax(orm.getAddrPhoneFax());
    // at.setPhoneHome(orm.getAddrPhoneHome());
    // at.setPhoneMain(orm.getAddrPhoneMain());
    // at.setPhonePager(orm.getAddrPhonePager());
    // at.setPhoneWork(orm.getAddrPhoneWork());
    // at.setPhoneWorkExt(orm.getAddrPhoneExt());
    //
    // c.setAddress(at);
    // list.add(c);
    // }
    //
    // return list;
    // }

    /**
     * Converts a single instance of ZipcodeDto to an instance of ZipcodeType.
     * 
     * @param item
     *            an instance of {@link ZipcodeDto}
     * @return an instance of {@link ZipcodeType}
     */
    public static ZipcodeType getZipShortInstance(ZipcodeDto item) {
        ObjectFactory f = new ObjectFactory();
        ZipcodeType z = f.createZipcodeType();
        z.setZipId(BigInteger.valueOf(item.getId()));
        z.setZipcode(BigInteger.valueOf(item.getZip()));
        z.setAreaCode(item.getAreaCode());
        z.setCity(item.getCity());
        z.setState(item.getStateCode());
        z.setCountyName(item.getCountyName());
        z.setCityAliasAbbr(item.getCityAliasAbbr());
        z.setCityAliasName(item.getCityAliasName());
        return z;
    }
    
    
    /**
     * Converts a single instance of Zipcode to an instance of ZipcodeType.
     * 
     * @param item
     *            an instance of {@link Zipcode}
     * @return an instance of {@link ZipcodeType}
     */
    public static ZipcodeType getZipShortInstance(Zipcode item) {
        ObjectFactory f = new ObjectFactory();
        ZipcodeType z = f.createZipcodeType();
        z.setZipId(BigInteger.valueOf(item.getZipId()));
        z.setZipcode(BigInteger.valueOf(item.getZip()));
        z.setAreaCode(item.getAreaCode());
        z.setCity(item.getCity());
        z.setState(item.getState());
        z.setCountyName(item.getCountyName());
        z.setCityAliasAbbr(item.getCityAliasAbbr());
        z.setCityAliasName(item.getCityAliasName());
        return z;
    }
    
    /**
     * Converts a single instance of VwZipcode to an instance of ZipcodeType.
     * 
     * @param item
     *            an instance of {@link VwZipcode}
     * @return an instance of {@link ZipcodeType}
     */
    public static ZipcodeType getZipShortInstance(VwZipcode item) {
        ObjectFactory f = new ObjectFactory();
        ZipcodeType z = f.createZipcodeType();
        z.setZipId(BigInteger.valueOf(item.getZipId()));
        z.setZipcode(BigInteger.valueOf(item.getZip()));
        z.setAreaCode(item.getAreaCode());
        z.setCity(item.getCity());
        z.setState(item.getState());
        z.setCountyName(item.getCountyName());
        z.setCityAliasAbbr(item.getCityAliasAbbr());
        z.setCityAliasName(item.getCityAliasName());
        return z;
    }

    /**
     * Converts a List of VwZipcode instances to a List of ZipcodeType
     * instances.
     * 
     * @param items
     * @return
     */
    public static List<ZipcodeType> getZipShortInstance(List<VwZipcode> items) {
        List<ZipcodeType> list = new ArrayList<ZipcodeType>();
        for (VwZipcode item : items) {
            ZipcodeType z = ContactsJaxbFactory.getZipShortInstance(item);
            list.add(z);
        }
        return list;
    }

    /**
     * 
     * @param items
     * @return
     */
    public static List<ZipcodeFullType> getZipFullTypeInstance(List<VwZipcode> items) {
        List<ZipcodeFullType> list = new ArrayList<ZipcodeFullType>();
        for (VwZipcode item : items) {
            ZipcodeFullType z = ContactsJaxbFactory.getZipFullTypeInstance(item);
            list.add(z);
        }
        return list;
    }

    /**
     * Converts a single instance of ZipcodeDto to an instance of ZipcodeType in
     * full format.
     * 
     * @param item
     *            an instance of {@link ZipcodeDto}
     * @return an instance of {@link ZipcodeFullType}
     */
    public static ZipcodeFullType getZipFullTypeInstance(ZipcodeDto item) {
        ObjectFactory f = new ObjectFactory();
        ZipcodeFullType z = f.createZipcodeFullType();
        z.setZipId(BigInteger.valueOf(item.getId()));
        z.setZipcode(BigInteger.valueOf(item.getZip()));
        z.setCity(item.getCity());
        z.setState(item.getStateCode());
        z.setAreaCode(item.getAreaCode());
        z.setCityAliasName(item.getCityAliasName());
        z.setCityAliasAbbr(item.getCityAliasAbbr());
        CitytypeType ctt = f.createCitytypeType();
        ctt.setCityTypeId(item.getCityTypeId());
        ctt.setCityTypeDesc(item.getCityTypDescr());
        z.setCityTypeId(ctt);
        z.setCountyName(item.getCountyName());
        z.setCountyFips(item.getCountyFips());
        TimezoneType tt = f.createTimezoneType();
        tt.setTimezoneId(BigInteger.valueOf(item.getTimeZoneId()));
        tt.setTimeszoneDesc(null);
        z.setTimeZoneId(tt);
        z.setDayLightSaving(item.getDayLightSaving());
        z.setLatitude(Double.valueOf(item.getLatitude()));
        z.setLongitude(Double.valueOf(item.getLongitude()));
        z.setElevation(Double.valueOf(item.getElevation()));
        z.setMsa(Double.valueOf(item.getMsa()));
        z.setPmsa(Double.valueOf(item.getPmsa()));
        z.setCbsa(Double.valueOf(item.getCbsa()));
        z.setCbsaDiv(Double.valueOf(item.getCbsaDiv()));
        z.setPersonsPerHousehold(Double.valueOf(item.getPersonsPerHousehold()));
        z.setZipcodePopulation(Double.valueOf(item.getZipPopulation()));
        z.setCountiesArea(Double.valueOf(item.getCountiesArea()));
        z.setHouseholdsPerZipcode(Double.valueOf(item.getHouseholdsPerZipcode()));
        z.setWhitePopulation(Double.valueOf(item.getWhitePopulation()));
        z.setBlackPopulation(Double.valueOf(item.getBlackPopulation()));
        z.setHispanicPopulation(Double.valueOf(item.getHispanicPopulation()));
        z.setIncomePerHousehold(Double.valueOf(item.getIncomePerHousehold()));
        z.setAverageHouseValue(Double.valueOf(item.getAverageHouseValue()));
        return z;
    }
    
    /**
     * Converts a single instance of Zipcode to an instance of ZipcodeType in
     * full format.
     * 
     * @param item
     *            an instance of {@link Zipcode}
     * @return an instance of {@link ZipcodeFullType}
     */
    public static ZipcodeFullType getZipFullTypeInstance(Zipcode item) {
        ObjectFactory f = new ObjectFactory();
        ZipcodeFullType z = f.createZipcodeFullType();
        z.setZipId(BigInteger.valueOf(item.getZipId()));
        z.setZipcode(BigInteger.valueOf(item.getZip()));
        z.setCity(item.getCity());
        z.setState(item.getState());
        z.setAreaCode(item.getAreaCode());
        z.setCityAliasName(item.getCityAliasName());
        z.setCityAliasAbbr(item.getCityAliasAbbr());
        CitytypeType ctt = f.createCitytypeType();
        ctt.setCityTypeId(item.getCityTypeId());
        ctt.setCityTypeDesc("Unknown");
        z.setCityTypeId(ctt);
        z.setCountyName(item.getCountyName());
        z.setCountyFips(item.getCountyFips());
        TimezoneType tt = f.createTimezoneType();
        tt.setTimezoneId(BigInteger.valueOf(item.getTimeZoneId()));
        tt.setTimeszoneDesc("Unknown");
        z.setTimeZoneId(tt);
        z.setDayLightSaving(item.getDayLightSaving());
        z.setLatitude(Double.valueOf(item.getLatitude()));
        z.setLongitude(Double.valueOf(item.getLongitude()));
        z.setElevation(Double.valueOf(item.getElevation()));
        z.setMsa(Double.valueOf(item.getMsa()));
        z.setPmsa(Double.valueOf(item.getPmsa()));
        z.setCbsa(Double.valueOf(item.getCbsa()));
        z.setCbsaDiv(Double.valueOf(item.getCbsaDiv()));
        z.setPersonsPerHousehold(Double.valueOf(item.getPersonsPerHousehold()));
        z.setZipcodePopulation(Double.valueOf(item.getZipcodePopulation()));
        z.setCountiesArea(Double.valueOf(item.getCountiesArea()));
        z.setHouseholdsPerZipcode(Double.valueOf(item.getHouseholdsPerZipcode()));
        z.setWhitePopulation(Double.valueOf(item.getWhitePopulation()));
        z.setBlackPopulation(Double.valueOf(item.getBlackPopulation()));
        z.setHispanicPopulation(Double.valueOf(item.getHispanicPopulation()));
        z.setIncomePerHousehold(Double.valueOf(item.getIncomePerHousehold()));
        z.setAverageHouseValue(Double.valueOf(item.getAverageHouseValue()));
        return z;
    }
    
    /**
     * Converts a single instance of VwZipcode to an instance of ZipcodeFullType
     * in full format.
     * 
     * @param item
     *            an instance of {@link VwZipcode}
     * @return an instance of {@link ZipcodeFullType}
     */
    public static ZipcodeFullType getZipFullTypeInstance(VwZipcode item) {
        ObjectFactory f = new ObjectFactory();
        ZipcodeFullType z = f.createZipcodeFullType();
        z.setZipId(BigInteger.valueOf(item.getZipId()));
        z.setZipcode(BigInteger.valueOf(item.getZip()));
        z.setCity(item.getCity());
        z.setState(item.getState());
        z.setAreaCode(item.getAreaCode());
        z.setCityAliasName(item.getCityAliasName());
        z.setCityAliasAbbr(item.getCityAliasAbbr());
        CitytypeType ctt = f.createCitytypeType();
        ctt.setCityTypeId(item.getCityTypeId());
        ctt.setCityTypeDesc(item.getCitytypeDescr());
        z.setCityTypeId(ctt);
        z.setCountyName(item.getCountyName());
        z.setCountyFips(item.getCountyFips());
        TimezoneType tt = f.createTimezoneType();
        tt.setTimezoneId(BigInteger.valueOf(item.getTimeZone()));
        tt.setTimeszoneDesc(item.getTimezoneDescr());
        z.setTimeZoneId(tt);
        z.setDayLightSaving(item.getDayLightSaving());
        z.setLatitude(Double.valueOf(item.getLatitude()));
        z.setLongitude(Double.valueOf(item.getLongitude()));
        z.setElevation(Double.valueOf(item.getElevation()));
        z.setMsa(Double.valueOf(item.getMsa()));
        z.setPmsa(Double.valueOf(item.getPmsa()));
        z.setCbsa(Double.valueOf(item.getCbsa()));
        z.setCbsaDiv(Double.valueOf(item.getCbsaDiv()));
        z.setPersonsPerHousehold(Double.valueOf(item.getPersonsPerHousehold()));
        z.setZipcodePopulation(Double.valueOf(item.getZipcodePopulation()));
        z.setCountiesArea(Double.valueOf(item.getCountiesArea()));
        z.setHouseholdsPerZipcode(Double.valueOf(item.getHouseholdsPerZipcode()));
        z.setWhitePopulation(Double.valueOf(item.getWhitePopulation()));
        z.setBlackPopulation(Double.valueOf(item.getBlackPopulation()));
        z.setHispanicPopulation(Double.valueOf(item.getHispanicPopulation()));
        z.setIncomePerHousehold(Double.valueOf(item.getIncomePerHousehold()));
        z.setAverageHouseValue(Double.valueOf(item.getAverageHouseValue()));
        return z;
    }

    // /**
    // *
    // * @param client
    // * @return
    // */
    // public static CommonContactCriteria createCommonSelectionCriteria(
    // ContactCriteria client) {
    // ObjectFactory f = new ObjectFactory();
    // CommonContactCriteria c = f.createCommonContactCriteria();
    //
    // try {
    // int contactId = Integer.parseInt(client.getQry_ContactId());
    // c.getContactId().add(BigInteger.valueOf(contactId));
    // } catch (NumberFormatException e) {
    // // Ignore contact id...
    // }
    //
    // c.setContactName(client.getQry_ContactName().equals("") ? null : client
    // .getQry_ContactName());
    // c.setMainPhone(client.getQry_AddrPhoneMain().equals("") ? null : client
    // .getQry_AddrPhoneMain());
    // c.setCity(client.getQry_ZipCity().equals("") ? null : client
    // .getQry_ZipCity());
    // c.setState(client.getQry_ZipState().equals("") ? null : client
    // .getQry_ZipState());
    // c.setZipcode(client.getQry_AddrZip().equals("") ? null : client
    // .getQry_AddrZip());
    // return c;
    // }

    /**
     * Converts a List of TimeZone instances to a List of TimezoneType
     * instances.
     * 
     * @param items
     * @return
     */
    public static List<TimezoneType> getTimezoneInstance(List<TimeZoneDto> items) {
        List<TimezoneType> list = new ArrayList<TimezoneType>();
        for (TimeZoneDto item : items) {
            TimezoneType z = ContactsJaxbFactory.getTimezoneInstance(item);
            list.add(z);
        }
        return list;
    }

    /**
     * Converts a single instance of TimeZone to an instance of TimezoneType.
     * 
     * @param item
     * @return
     */
    public static TimezoneType getTimezoneInstance(TimeZoneDto item) {
        ObjectFactory f = new ObjectFactory();
        TimezoneType z = f.createTimezoneType();
        z.setTimezoneId(BigInteger.valueOf(item.getTimeZoneId()));
        z.setTimeszoneDesc(item.getTimeZoneDescr());
        return z;
    }

}
