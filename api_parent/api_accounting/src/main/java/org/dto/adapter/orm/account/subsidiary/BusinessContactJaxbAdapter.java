package org.dto.adapter.orm.account.subsidiary;

import java.math.BigInteger;

import org.dto.AbstractSubsidiaryAdapter;
import org.dto.SubsidiaryContactInfoDto;
import org.rmt2.jaxb.AddressType;
import org.rmt2.jaxb.BusinessType;
import org.rmt2.jaxb.CodeDetailType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.ZipcodeType;

/**
 * A JAXB to DTO implementation that adapts data pertaining a
 * {@link BusinessType} object to a business contact DTO.
 * 
 * @author Roy Terrell
 * 
 */
class BusinessContactJaxbAdapter extends AbstractSubsidiaryAdapter implements
        SubsidiaryContactInfoDto {

    private BusinessType b;
    private AddressType at;
    private ZipcodeType zct;

    /**
     * Create a BusinessContactJaxbAdapter that adapts data coming from the
     * BusinessType object.
     * 
     * @param contact
     *            an instance of {@link BusinessType} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    public BusinessContactJaxbAdapter(BusinessType contact) {
        super();
        ObjectFactory f = new ObjectFactory();
        if (contact == null) {
            contact = f.createBusinessType();
        }
        this.b = contact;

        this.at = contact.getAddress() == null ? f.createAddressType()
                : contact.getAddress();
        this.zct = at.getZip() == null ? f.createZipcodeType() : at.getZip();
        return;
    }

    /**
     * Stub method
     */
    @Override
    public void setContactName(String value) {
        b.setLongName(value);
    }

    /**
     * Always return null.
     */
    @Override
    public String getContactName() {
        return b.getLongName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#setBusinessId(int)
     */
    @Override
    public void setContactId(int value) {
        this.b.setBusinessId(BigInteger.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#getBusinessId()
     */
    @Override
    public int getContactId() {
        return this.b.getBusinessId() == null ? 0 : this.b.getBusinessId()
                .intValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setShortName(java.lang.String)
     */
    @Override
    public void setShortName(String value) {
        b.setShortName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getShortName()
     */
    @Override
    public String getShortName() {
        return b.getShortName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setContactEmail(java.lang.String)
     */
    @Override
    public void setContactEmail(String value) {
        b.setContactEmail(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getContactEmail()
     */
    @Override
    public String getContactEmail() {
        return b.getContactEmail();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setEntityTypeId(int)
     */
    @Override
    public void setEntityTypeId(int value) {
        if (b.getEntityType() == null) {
            b.setEntityType(new CodeDetailType());
        }
        b.getEntityType().setCodeId(BigInteger.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getEntityTypeId()
     */
    @Override
    public int getEntityTypeId() {
        if (b.getEntityType() != null) {
            return b.getEntityType().getCodeId() == null ? 0 : b
                    .getEntityType().getCodeId().intValue();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setServTypeId(int)
     */
    @Override
    public void setServTypeId(int value) {
        if (b.getServiceType() == null) {
            b.setServiceType(new CodeDetailType());
        }
        b.getServiceType().setCodeId(BigInteger.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getServTypeId()
     */
    @Override
    public int getServTypeId() {
        if (b.getServiceType() != null) {
            return b.getServiceType().getCodeId() == null ? 0 : b
                    .getServiceType().getCodeId().intValue();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.SubsidiaryContactInfoDto#setContactFirstname(java.lang.String)
     */
    @Override
    public void setContactFirstname(String value) {
        b.setContactFirstname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getContactFirstname()
     */
    @Override
    public String getContactFirstname() {
        return b.getContactFirstname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.SubsidiaryContactInfoDto#setContactLastname(java.lang.String)
     */
    @Override
    public void setContactLastname(String value) {
        b.setContactLastname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getContactLastname()
     */
    @Override
    public String getContactLastname() {
        return b.getContactLastname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setContactPhone(java.lang.String)
     */
    @Override
    public void setContactPhone(String value) {
        b.setContactPhone(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getContactPhone()
     */
    @Override
    public String getContactPhone() {
        return b.getContactPhone();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setContactExt(java.lang.String)
     */
    @Override
    public void setContactExt(String value) {
        b.setContactExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getContactExt()
     */
    @Override
    public String getContactExt() {
        return b.getContactExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setTaxId(java.lang.String)
     */
    @Override
    public void setTaxId(String value) {
        b.setTaxId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getTaxId()
     */
    @Override
    public String getTaxId() {
        return b.getTaxId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setWebsite(java.lang.String)
     */
    @Override
    public void setWebsite(String value) {
        b.setWebsite(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getWebsite()
     */
    @Override
    public String getWebsite() {
        return b.getWebsite();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setAddrId(int)
     */
    @Override
    public void setAddrId(int value) {
        if (at != null) {
            at.setAddrId(BigInteger.valueOf(value));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getAddrId()
     */
    @Override
    public int getAddrId() {
        if (at != null) {
            return at.getAddrId() == null ? 0 : at.getAddrId().intValue();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setAddr1(java.lang.String)
     */
    @Override
    public void setAddr1(String value) {
        at.setAddr1(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getAddr1()
     */
    @Override
    public String getAddr1() {
        return at.getAddr1();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setAddr2(java.lang.String)
     */
    @Override
    public void setAddr2(String value) {
        at.setAddr2(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getAddr2()
     */
    @Override
    public String getAddr2() {
        return at.getAddr2();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setAddr3(java.lang.String)
     */
    @Override
    public void setAddr3(String value) {
        at.setAddr3(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getAddr3()
     */
    @Override
    public String getAddr3() {
        return at.getAddr3();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setAddr4(java.lang.String)
     */
    @Override
    public void setAddr4(String value) {
        at.setAddr4(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getAddr4()
     */
    @Override
    public String getAddr4() {
        return at.getAddr4();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setZip(int)
     */
    @Override
    public void setZip(int value) {
        if (zct != null) {
            zct.setZipcode(BigInteger.valueOf(value));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getZip()
     */
    @Override
    public int getZip() {
        if (zct != null) {
            return zct.getZipcode() == null ? 0 : zct.getZipcode().intValue();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setZipext(int)
     */
    @Override
    public void setZipext(int value) {
        at.setZipExt(BigInteger.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getZipext()
     */
    @Override
    public int getZipext() {
        return at.getZipExt() == null ? 0 : at.getZipExt().intValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        if (zct != null) {
            zct.setCity(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getCity()
     */
    @Override
    public String getCity() {
        if (zct != null) {
            return zct.getCity();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setState(java.lang.String)
     */
    @Override
    public void setState(String value) {
        if (zct != null) {
            zct.setState(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getState()
     */
    @Override
    public String getState() {
        if (zct != null) {
            // UI-28: Added return statement so that Zip State can be included
            // in search results.
            return zct.getState();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setPhoneHome(java.lang.String)
     */
    @Override
    public void setPhoneHome(String value) {
        at.setPhoneMain(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneHome()
     */
    @Override
    public String getPhoneHome() {
        return at.getPhoneMain();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setPhoneWork(java.lang.String)
     */
    @Override
    public void setPhoneWork(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneWork()
     */
    @Override
    public String getPhoneWork() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setPhoneExt(java.lang.String)
     */
    @Override
    public void setPhoneExt(String value) {
        b.setContactExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneExt()
     */
    @Override
    public String getPhoneExt() {
        return b.getContactExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setPhoneCompany(java.lang.String)
     */
    @Override
    public void setPhoneCompany(String value) {
        this.b.setContactPhone(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneCompany()
     */
    @Override
    public String getPhoneCompany() {
        return this.b.getContactPhone();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setPhoneCell(java.lang.String)
     */
    @Override
    public void setPhoneCell(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneCell()
     */
    @Override
    public String getPhoneCell() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setPhoneFax(java.lang.String)
     */
    @Override
    public void setPhoneFax(String value) {
        at.setPhoneFax(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneFax()
     */
    @Override
    public String getPhoneFax() {
        return at.getPhoneFax();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setPhonePager(java.lang.String)
     */
    @Override
    public void setPhonePager(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhonePager()
     */
    @Override
    public String getPhonePager() {
        return null;
    }
}
