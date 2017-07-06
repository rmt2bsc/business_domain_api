package org.dto.adapter.orm;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwCommonContact;
import org.dto.ContactDto;

/**
 * Adapts an RMT2 ORM <i>VwCommonContact</i> and an <i>Address</i> object to an
 * <i>ContactDto</i>.
 * 
 * @author rterrell
 * 
 */
public class CombinedContactRmt2OrmAdapter extends AddressRmt2OrmAdapter implements ContactDto {

    private VwCommonContact src; 
    private List<Integer> businessIdList;

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
        this.businessIdList = new ArrayList<Integer>();
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
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContactDto#getEmail()
     */
    @Override
    public String getContactEmail() {
        return null;
    }

    @Override
    public void setContactIdList(List<Integer> value) {
        this.businessIdList = value;
    }

    @Override
    public List<Integer> getContactIdList() {
        return this.businessIdList;
    }
    }
