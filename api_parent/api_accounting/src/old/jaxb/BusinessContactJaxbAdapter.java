package org.dto.adapter.jaxb;

import org.dto.AbstractSubsidiaryAdapter;
import org.dto.SubsidiaryContactInfoDto;

import com.services.contacts.BusinessContactBean;

/**
 * A JAXB to DTO implementation that adapts data pertaining to a business
 * contact.
 * 
 * @author Roy Terrell
 * 
 */
class BusinessContactJaxbAdapter extends AbstractSubsidiaryAdapter implements
        SubsidiaryContactInfoDto {

    private BusinessContactBean c;

    /**
     * Create a BusinessContactJaxbAdapter without performing any data
     * adaptations
     */
    protected BusinessContactJaxbAdapter() {
        return;
    }

    /**
     * Create a BusinessContactJaxbAdapter that adapts data coming from the
     * creditor table
     * 
     * @param creditor
     *            an instance of {@link Creditor} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected BusinessContactJaxbAdapter(BusinessContactBean contact) {
        if (contact == null) {
            contact = new BusinessContactBean();
        }
        this.c = contact;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setContactId(int)
     */
    @Override
    public void setContactId(int value) {
        c.setBusinessId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getContactId()
     */
    @Override
    public int getContactId() {
        return c.getBusinessId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.BusinessSubsidiaryContactDto#setContactName(java.lang.String)
     */
    @Override
    public void setContactName(String value) {
        c.setName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getContactName()
     */
    @Override
    public String getContactName() {
        return c.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.BusinessSubsidiaryContactDto#setContactEmail(java.lang.String)
     */
    @Override
    public void setContactEmail(String value) {
        c.setContactEmail(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getContactEmail()
     */
    @Override
    public String getContactEmail() {
        return c.getContactEmail();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.BusinessSubsidiaryContactDto#setContactType(java.lang.String)
     */
    @Override
    public void setContactType(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getContactType()
     */
    @Override
    public String getContactType() {
        // The contact type general code
        return "247";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setShortName(java.lang.String)
     */
    @Override
    public void setShortName(String value) {
        c.setShortname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getShortName()
     */
    @Override
    public String getShortName() {
        return c.getShortname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setServTypeId(int)
     */
    @Override
    public void setServTypeId(int value) {
        c.setServType(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getServTypeId()
     */
    @Override
    public int getServTypeId() {
        return c.getServType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.BusinessSubsidiaryContactDto#setContactFirstname(java.lang.String
     * )
     */
    @Override
    public void setContactFirstname(String value) {
        c.setContactFirstname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getContactFirstname()
     */
    @Override
    public String getContactFirstname() {
        return c.getContactFirstname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.BusinessSubsidiaryContactDto#setContactLastname(java.lang.String)
     */
    @Override
    public void setContactLastname(String value) {
        c.setContactLastname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getContactLastname()
     */
    @Override
    public String getContactLastname() {
        return c.getContactLastname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.BusinessSubsidiaryContactDto#setContactPhone(java.lang.String)
     */
    @Override
    public void setContactPhone(String value) {
        c.setContactPhone(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getContactPhone()
     */
    @Override
    public String getContactPhone() {
        return c.getContactPhone();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setContactExt(java.lang.String)
     */
    @Override
    public void setContactExt(String value) {
        c.setContactExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getContactExt()
     */
    @Override
    public String getContactExt() {
        return c.getContactExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setTaxId(java.lang.String)
     */
    @Override
    public void setTaxId(String value) {
        c.setTaxId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getTaxId()
     */
    @Override
    public String getTaxId() {
        return c.getTaxId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setWebsite(java.lang.String)
     */
    @Override
    public void setWebsite(String value) {
        c.setWebsite(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getWebsite()
     */
    @Override
    public String getWebsite() {
        return c.getWebsite();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setCategoryId(int)
     */
    @Override
    public void setCategoryId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getCategoryId()
     */
    @Override
    public int getCategoryId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#setEntityId(int)
     */
    @Override
    public void setEntityId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#getEntityId()
     */
    @Override
    public int getEntityId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#setEntityName(java.lang.String)
     */
    @Override
    public void setEntityName(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#getEntityName()
     */
    @Override
    public String getEntityName() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setEntityTypeId(int)
     */
    @Override
    public void setEntityTypeId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getEntityTypeId()
     */
    @Override
    public int getEntityTypeId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setAddrId(int)
     */
    @Override
    public void setAddrId(int value) {
        c.setAddrId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getAddrId()
     */
    @Override
    public int getAddrId() {
        return c.getAddrId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setAddr1(java.lang.String)
     */
    @Override
    public void setAddr1(String value) {
        c.setAddr1(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getAddr1()
     */
    @Override
    public String getAddr1() {
        return c.getAddr1();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setAddr2(java.lang.String)
     */
    @Override
    public void setAddr2(String value) {
        c.setAddr2(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getAddr2()
     */
    @Override
    public String getAddr2() {
        return c.getAddr2();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setAddr3(java.lang.String)
     */
    @Override
    public void setAddr3(String value) {
        c.setAddr3(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getAddr3()
     */
    @Override
    public String getAddr3() {
        return c.getAddr3();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setAddr4(java.lang.String)
     */
    @Override
    public void setAddr4(String value) {
        c.setAddr4(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getAddr4()
     */
    @Override
    public String getAddr4() {
        return c.getAddr4();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setZip(int)
     */
    @Override
    public void setZip(int value) {
        c.setAddrZip(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getZip()
     */
    @Override
    public int getZip() {
        return c.getAddrZip();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setZipext(int)
     */
    @Override
    public void setZipext(int value) {
        c.setAddrZipext(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getZipext()
     */
    @Override
    public int getZipext() {
        return c.getAddrZipext();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        c.setZipCity(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getCity()
     */
    @Override
    public String getCity() {
        return c.getZipCity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#setState(java.lang.String)
     */
    @Override
    public void setState(String value) {
        c.setZipState(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessSubsidiaryContactDto#getState()
     */
    @Override
    public String getState() {
        return c.getZipState();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setPhoneHome(java.lang.String)
     */
    @Override
    public void setPhoneHome(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneHome()
     */
    @Override
    public String getPhoneHome() {
        return null;
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
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneExt()
     */
    @Override
    public String getPhoneExt() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setPhoneCompany(java.lang.String)
     */
    @Override
    public void setPhoneCompany(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneCompany()
     */
    @Override
    public String getPhoneCompany() {
        return null;
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
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getPhoneFax()
     */
    @Override
    public String getPhoneFax() {
        return null;
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
