package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.VwCommonContact;
import org.dto.ContactDto;

/**
 * Adapts an RMT2 ORM <i>VwCommonContact</i> and an <i>Address</i> object to an
 * <i>ContactDto</i>.
 * 
 * @author rterrell
 * 
 */
public class CombinedContactRmt2OrmAdapter extends AddressRmt2OrmAdapter
        implements ContactDto {

    private VwCommonContact src;

    /**
     * Create a CombinedContactRmt2OrmAdapter using an instance of
     * <i>VwCommonContact</i> and <i>Address</i>.
     * 
     * @param contact
     *            an instance of {@link VwCommonContact} or null for the purpose
     *            of creating a new VwCommonContact object
     */
    protected CombinedContactRmt2OrmAdapter(VwCommonContact contact) {
        super(contact);
        if (contact == null) {
            contact = new VwCommonContact();
        }
        this.src = contact;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContactDto#setContactId(int)
     */
    @Override
    public void setContactId(int value) {
        this.src.setContactId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContactDto#getContactId()
     */
    @Override
    public int getContactId() {
        return this.src.getContactId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContactDto#setContactName(java.lang.String)
     */
    @Override
    public void setContactName(String value) {
        this.src.setContactName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContactDto#getContactName()
     */
    @Override
    public String getContactName() {
        return this.src.getContactName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContactDto#setEmail(java.lang.String)
     */
    @Override
    public void setContactEmail(String value) {
        this.src.setEmail(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContactDto#getEmail()
     */
    @Override
    public String getContactEmail() {
        return this.src.getEmail();
    }

    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setAddrId(int)
    // */
    // @Override
    // public void setAddrId(int value) {
    // this.src.setAddrId(value);
    //
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getAddrId()
    // */
    // @Override
    // public int getAddrId() {
    // return this.src.getAddrId();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setAddr1(java.lang.String)
    // */
    // @Override
    // public void setAddr1(String value) {
    // this.src.setAddr1(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getAddr1()
    // */
    // @Override
    // public String getAddr1() {
    // return this.src.getAddr1();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setAddr2(java.lang.String)
    // */
    // @Override
    // public void setAddr2(String value) {
    // this.src.setAddr2(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getAddr2()
    // */
    // @Override
    // public String getAddr2() {
    // return this.src.getAddr2();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setAddr3(java.lang.String)
    // */
    // @Override
    // public void setAddr3(String value) {
    // this.src.setAddr3(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getAddr3()
    // */
    // @Override
    // public String getAddr3() {
    // return this.src.getAddr3();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setAddr4(java.lang.String)
    // */
    // @Override
    // public void setAddr4(String value) {
    // this.src.setAddr4(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getAddr4()
    // */
    // @Override
    // public String getAddr4() {
    // return this.src.getAddr4();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setZip(int)
    // */
    // @Override
    // public void setZip(int value) {
    // this.src.setAddrZip(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getZip()
    // */
    // @Override
    // public int getZip() {
    // return this.src.getAddrZip();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setZipext(int)
    // */
    // @Override
    // public void setZipext(int value) {
    // this.src.setAddrZipext(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getZipext()
    // */
    // @Override
    // public int getZipext() {
    // return this.src.getAddrZipext();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setPhoneHome(java.lang.String)
    // */
    // @Override
    // public void setPhoneHome(String value) {
    // this.src.setAddrPhoneHome(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getPhoneHome()
    // */
    // @Override
    // public String getPhoneHome() {
    // return this.src.getAddrPhoneHome();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setPhoneWork(java.lang.String)
    // */
    // @Override
    // public void setPhoneWork(String value) {
    // this.src.setAddrPhoneWork(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getPhoneWork()
    // */
    // @Override
    // public String getPhoneWork() {
    // return this.src.getAddrPhoneWork();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setPhoneExt(java.lang.String)
    // */
    // @Override
    // public void setPhoneExt(String value) {
    // this.src.setAddrPhoneExt(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getPhoneExt()
    // */
    // @Override
    // public String getPhoneExt() {
    // return this.src.getAddrPhoneExt();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setPhoneCompany(java.lang.String)
    // */
    // @Override
    // public void setPhoneCompany(String value) {
    // this.src.setAddrPhoneMain(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getPhoneCompany()
    // */
    // @Override
    // public String getPhoneCompany() {
    // return this.src.getAddrPhoneMain();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setPhoneCell(java.lang.String)
    // */
    // @Override
    // public void setPhoneCell(String value) {
    // this.src.setAddrPhoneCell(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getPhoneCell()
    // */
    // @Override
    // public String getPhoneCell() {
    // return this.src.getAddrPhoneCell();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setPhoneFax(java.lang.String)
    // */
    // @Override
    // public void setPhoneFax(String value) {
    // this.src.setAddrPhoneFax(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getPhoneFax()
    // */
    // @Override
    // public String getPhoneFax() {
    // return this.src.getAddrPhoneFax();
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#setPhonePager(java.lang.String)
    // */
    // @Override
    // public void setPhonePager(String value) {
    // this.src.setAddrPhonePager(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.AddressDto#getPhonePager()
    // */
    // @Override
    // public String getPhonePager() {
    // return this.src.getAddrPhonePager();
    // }
}
