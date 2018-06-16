package org.dto;

import java.util.List;

import com.api.foundation.TransactionDtoImpl;

/**
 * An abstract class for subsidiary related adapters.
 * 
 * @author Roy Terrell
 * 
 */
public abstract class AbstractSubsidiaryAdapter extends TransactionDtoImpl
        implements SubsidiaryContactInfoDto {

    private List<Integer> contactIdList;

    // private BusinessContactBean b;

    /**
     * Create a AbstractSubsidiaryAdapter that basically initializes the contact
     * id list to null.
     */
    protected AbstractSubsidiaryAdapter() {
        super();
        // if (contact == null) {
        // contact = new BusinessContactBean();
        // }
        // this.b = contact;
        this.contactIdList = null;
        return;
    }

    /**
     * Set the list of contact id's for this instance which is usually used for
     * selection criteria.
     * 
     * @param value
     */
    public void setContactIdList(List<Integer> value) {
        this.contactIdList = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#setContactType(java.lang.String)
     */
    @Override
    public void setContactType(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getContactType()
     */
    @Override
    public String getContactType() {
        return null;
    }

    /**
     * Get the list of contact id's for this instance which is usually used for
     * selection criteria.
     * 
     * @return
     */
    public List<Integer> getContactIdList() {
        return this.contactIdList;
    }

    /**
     * Set customer id
     */
    @Override
    public void setEntityId(int value) {
        return;
    }

    /**
     * Get customer id
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
     * @see org.dto.SubsidiaryContactInfoDto#setCategoryId(int)
     */
    @Override
    public void setCategoryId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryContactInfoDto#getCategoryId()
     */
    @Override
    public int getCategoryId() {
        return 0;
    }

    // /**
    // * Stub method
    // */
    // @Override
    // public void setContactName(String value) {
    // b.setName(value);
    // }
    //
    // /**
    // * Always return null.
    // */
    // @Override
    // public String getContactName() {
    // return b.getName();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.CustomerDto#setBusinessId(int)
    // */
    // @Override
    // public void setContactId(int value) {
    // this.b.setBusinessId(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.CustomerDto#getBusinessId()
    // */
    // @Override
    // public int getContactId() {
    // return this.b.getBusinessId();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setShortName(java.lang.String)
    // */
    // @Override
    // public void setShortName(String value) {
    // b.setShortname(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getShortName()
    // */
    // @Override
    // public String getShortName() {
    // return b.getShortname();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setContactEmail(java.lang.String)
    // */
    // @Override
    // public void setContactEmail(String value) {
    // b.setContactEmail(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getContactEmail()
    // */
    // @Override
    // public String getContactEmail() {
    // return b.getContactEmail();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setEntityTypeId(int)
    // */
    // @Override
    // public void setEntityTypeId(int value) {
    // b.setBusType(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getEntityTypeId()
    // */
    // @Override
    // public int getEntityTypeId() {
    // return b.getBusType();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setServTypeId(int)
    // */
    // @Override
    // public void setServTypeId(int value) {
    // b.setServType(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getServTypeId()
    // */
    // @Override
    // public int getServTypeId() {
    // return b.getServType();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see
    // * org.dto.SubsidiaryContactInfoDto#setContactFirstname(java.lang.String)
    // */
    // @Override
    // public void setContactFirstname(String value) {
    // b.setContactFirstname(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getContactFirstname()
    // */
    // @Override
    // public String getContactFirstname() {
    // return b.getContactFirstname();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see
    // * org.dto.SubsidiaryContactInfoDto#setContactLastname(java.lang.String)
    // */
    // @Override
    // public void setContactLastname(String value) {
    // b.setContactLastname(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getContactLastname()
    // */
    // @Override
    // public String getContactLastname() {
    // return b.getContactLastname();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setContactPhone(java.lang.String)
    // */
    // @Override
    // public void setContactPhone(String value) {
    // b.setContactPhone(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getContactPhone()
    // */
    // @Override
    // public String getContactPhone() {
    // return b.getContactPhone();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setContactExt(java.lang.String)
    // */
    // @Override
    // public void setContactExt(String value) {
    // b.setContactExt(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getContactExt()
    // */
    // @Override
    // public String getContactExt() {
    // return b.getContactExt();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setTaxId(java.lang.String)
    // */
    // @Override
    // public void setTaxId(String value) {
    // b.setTaxId(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getTaxId()
    // */
    // @Override
    // public String getTaxId() {
    // return b.getTaxId();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setWebsite(java.lang.String)
    // */
    // @Override
    // public void setWebsite(String value) {
    // b.setWebsite(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getWebsite()
    // */
    // @Override
    // public String getWebsite() {
    // return b.getWebsite();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setAddrId(int)
    // */
    // @Override
    // public void setAddrId(int value) {
    // b.setAddrId(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getAddrId()
    // */
    // @Override
    // public int getAddrId() {
    // return b.getAddrId();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setAddr1(java.lang.String)
    // */
    // @Override
    // public void setAddr1(String value) {
    // b.setAddr1(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getAddr1()
    // */
    // @Override
    // public String getAddr1() {
    // return b.getAddr1();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setAddr2(java.lang.String)
    // */
    // @Override
    // public void setAddr2(String value) {
    // b.setAddr2(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getAddr2()
    // */
    // @Override
    // public String getAddr2() {
    // return b.getAddr2();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setAddr3(java.lang.String)
    // */
    // @Override
    // public void setAddr3(String value) {
    // b.setAddr3(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getAddr3()
    // */
    // @Override
    // public String getAddr3() {
    // return b.getAddr3();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setAddr4(java.lang.String)
    // */
    // @Override
    // public void setAddr4(String value) {
    // b.setAddr4(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getAddr4()
    // */
    // @Override
    // public String getAddr4() {
    // return b.getAddr4();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setZip(int)
    // */
    // @Override
    // public void setZip(int value) {
    // b.setAddrZip(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getZip()
    // */
    // @Override
    // public int getZip() {
    // return b.getAddrZip();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setZipext(int)
    // */
    // @Override
    // public void setZipext(int value) {
    // b.setAddrZipext(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getZipext()
    // */
    // @Override
    // public int getZipext() {
    // return b.getAddrZipext();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setCity(java.lang.String)
    // */
    // @Override
    // public void setCity(String value) {
    // b.setZipCity(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getCity()
    // */
    // @Override
    // public String getCity() {
    // return b.getZipCity();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setState(java.lang.String)
    // */
    // @Override
    // public void setState(String value) {
    // b.setZipState(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getState()
    // */
    // @Override
    // public String getState() {
    // return b.getZipState();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setPhoneHome(java.lang.String)
    // */
    // @Override
    // public void setPhoneHome(String value) {
    // b.setAddrPhoneHome(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getPhoneHome()
    // */
    // @Override
    // public String getPhoneHome() {
    // return b.getAddrPhoneHome();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setPhoneWork(java.lang.String)
    // */
    // @Override
    // public void setPhoneWork(String value) {
    // b.setAddrPhoneWork(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getPhoneWork()
    // */
    // @Override
    // public String getPhoneWork() {
    // return b.getAddrPhoneWork();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setPhoneExt(java.lang.String)
    // */
    // @Override
    // public void setPhoneExt(String value) {
    // b.setAddrPhoneExt(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getPhoneExt()
    // */
    // @Override
    // public String getPhoneExt() {
    // return b.getAddrPhoneExt();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setPhoneCompany(java.lang.String)
    // */
    // @Override
    // public void setPhoneCompany(String value) {
    // b.setAddrPhoneMain(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getPhoneCompany()
    // */
    // @Override
    // public String getPhoneCompany() {
    // return b.getAddrPhoneMain();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setPhoneCell(java.lang.String)
    // */
    // @Override
    // public void setPhoneCell(String value) {
    // b.setAddrPhoneCell(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getPhoneCell()
    // */
    // @Override
    // public String getPhoneCell() {
    // return b.getAddrPhoneCell();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setPhoneFax(java.lang.String)
    // */
    // @Override
    // public void setPhoneFax(String value) {
    // b.setAddrPhoneFax(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getPhoneFax()
    // */
    // @Override
    // public String getPhoneFax() {
    // return b.getAddrPhoneFax();
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#setPhonePager(java.lang.String)
    // */
    // @Override
    // public void setPhonePager(String value) {
    // b.setAddrPhonePager(value);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.dto.SubsidiaryContactInfoDto#getPhonePager()
    // */
    // @Override
    // public String getPhonePager() {
    // return b.getAddrPhonePager();
    // }
}
