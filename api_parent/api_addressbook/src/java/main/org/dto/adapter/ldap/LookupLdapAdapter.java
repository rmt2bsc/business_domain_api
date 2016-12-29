package org.dto.adapter.ldap;

import java.util.ArrayList;

import org.dao.mapping.orm.ldap.LdapLookup;
import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;

import com.RMT2Constants;
import com.RMT2RuntimeException;

/**
 * Adapts a JNDI/LDAP <i>LdapLookup</i> object to an <i>LookupExtDto</i>,
 * <i>LookupCodeDto</i>, or <i>LookupGroupDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
public class LookupLdapAdapter extends AbstractAddressBookLdapAdapter implements
        LookupExtDto, LookupCodeDto, LookupGroupDto {

    private LdapLookup c;

    /**
     * Constructs a LookupLdapAdapter object initialized with an instance of
     * <i>LdapLookup</i>
     * 
     * @param lookup
     *            an instacne of {@link LdapLookup}
     */
    protected LookupLdapAdapter(LdapLookup lookup) {
        if (lookup == null) {
            lookup = new LdapLookup();
        }
        this.c = lookup;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#setCodeId(int)
     */
    @Override
    public void setCodeId(int value) {
        if (this.c.getUid() == null) {
            this.c.setUid(new ArrayList<String>());
        }
        this.c.getUid().add(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#getCodeId()
     */
    @Override
    public int getCodeId() {
        if (this.c.getUid() == null) {
            return 0;
        }
        try {
            return Integer.parseInt(this.c.getUid().get(0));
        } catch (NumberFormatException e) {
            throw new RMT2RuntimeException("Lookup code id must be numeric");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#setCodeShortDesc(java.lang.String)
     */
    @Override
    public void setCodeShortDesc(String value) {
        if (this.c.getCn() == null) {
            this.c.setCn(new ArrayList<String>());
        }
        this.c.getCn().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#getCodeShortName()
     */
    @Override
    public String getCodeShortName() {
        if (this.c.getCn() == null) {
            return null;
        }
        return this.c.getCn().get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#setCodeLongName(java.lang.String)
     */
    @Override
    public void setCodeLongName(String value) {
        if (this.c.getDescription() == null) {
            this.c.setDescription(new ArrayList<String>());
        }
        this.c.getDescription().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#getCodeLongName()
     */
    @Override
    public String getCodeLongName() {
        if (this.c.getDescription() == null) {
            return null;
        }
        return this.c.getDescription().get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupGroupDto#setGrpId(int)
     */
    @Override
    public void setGrpId(int value) {
        if (this.c.getUid() == null) {
            this.c.setUid(new ArrayList<String>());
        }
        this.c.getUid().add(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupGroupDto#getGrpId()
     */
    @Override
    public int getGrpId() {
        if (this.c.getUid() == null) {
            return 0;
        }
        try {
            return Integer.parseInt(this.c.getUid().get(0));
        } catch (NumberFormatException e) {
            throw new RMT2RuntimeException(
                    "Lookup code group id must be numeric");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupGroupDto#setGrpDescr(java.lang.String)
     */
    @Override
    public void setGrpDescr(String value) {
        if (this.c.getOu() == null) {
            this.c.setOu(new ArrayList<String>());
        }
        this.c.getOu().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupGroupDto#getGrpDescr()
     */
    @Override
    public String getGrpDescr() {
        if (this.c.getOu() == null) {
            return null;
        }
        return this.c.getOu().get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupGroupDto#setGrpPermcol(java.lang.String)
     */
    @Override
    public void setGrpPermcol(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupGroupDto#getGrpPermcol()
     */
    @Override
    public String getGrpPermcol() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#setCodeGenIndValue(java.lang.String)
     */
    @Override
    public void setCodeGenIndValue(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#getCodeGenIndValue()
     */
    @Override
    public String getCodeGenIndValue() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#setCodePermcol(java.lang.String)
     */
    @Override
    public void setCodePermcol(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupCodeDto#getCodePermcol()
     */
    @Override
    public String getCodePermcol() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
