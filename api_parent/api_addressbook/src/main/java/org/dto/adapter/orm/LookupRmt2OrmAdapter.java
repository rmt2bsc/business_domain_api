package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.GeneralCodes;
import org.dao.mapping.orm.rmt2.GeneralCodesGroup;
import org.dao.mapping.orm.rmt2.VwCodes;
import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts RMT2 ORM <i>GeneralCodesGroup</i> and/or a <i>GeneralCodes</i> objects
 * to an appropriate DTO.
 * <p>
 * This class implements <i>LookupExtDto</i>, <i>LookupCodeDto</i>, and
 * <i>LookupGroupDto</i>.
 * 
 * @author rterrell
 * 
 */
class LookupRmt2OrmAdapter extends TransactionDtoImpl implements LookupExtDto, LookupCodeDto, LookupGroupDto {

    private GeneralCodesGroup grp;

    private GeneralCodes code;

    private VwCodes vwCode;

    /**
     * Default constructor
     */
    private LookupRmt2OrmAdapter() {
        super();
    }

    /**
     * Create a LookupRmt2OrmAdapter using an instance of
     * <i>GeneralCodesGroup</i>.
     * 
     * @param grp
     *            an instance of {@link GeneralCodesGroup} or null for the
     *            purpose of creating a new GeneralCodesGroup object
     */
    protected LookupRmt2OrmAdapter(GeneralCodesGroup grp) {
        this();
        if (grp == null) {
            grp = new GeneralCodesGroup();
        }
        this.grp = grp;
    }

    /**
     * Create a LookupRmt2OrmAdapter using an instance of <i>GeneralCodes</i>.
     * 
     * @param lookup
     *            an instance of {@link GeneralCodes} or null for the purpose of
     *            creating a new GeneralCodes object
     */
    protected LookupRmt2OrmAdapter(GeneralCodes lookup) {
        this();
        if (lookup == null) {
            lookup = new GeneralCodes();
        }
        this.code = lookup;
    }

    /**
     * Create a LookupRmt2OrmAdapter using an instance of <i>VwCodes</i>.
     * 
     * @param lookup
     *            an instanceo of {@link VwCodes} or null for the purpose of
     *            creating a new VwCodes object
     */
    protected LookupRmt2OrmAdapter(VwCodes lookup) {
        this();
        if (lookup == null) {
            lookup = new VwCodes();
        }
        this.vwCode = lookup;
        this.grp = null;
        this.code = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#setGrpId(int)
     */
    @Override
    public void setGrpId(int value) {
        if (this.grp != null) {
            this.grp.setCodeGrpId(value);
        }
        if (this.code != null) {
            this.code.setCodeGrpId(value);
        }
        if (this.vwCode != null) {
            this.vwCode.setGroupId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#getGrpId()
     */
    @Override
    public int getGrpId() {
        if (this.grp != null) {
            return this.grp.getCodeGrpId();
        }
        if (this.code != null) {
            return this.code.getCodeGrpId();
        }
        if (this.vwCode != null) {
            return this.vwCode.getGroupId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#setGrpDescr(java.lang.String)
     */
    @Override
    public void setGrpDescr(String value) {
        if (this.grp != null) {
            this.grp.setDescription(value);
        }
        if (this.vwCode != null) {
            this.vwCode.setGroupDesc(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#getGrpDescr()
     */
    @Override
    public String getGrpDescr() {
        if (this.grp != null) {
            return this.grp.getDescription();
        }
        if (this.vwCode != null) {
            return this.vwCode.getGroupDesc();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#setGrpPermcol(java.lang.String)
     */
    @Override
    public void setGrpPermcol(String value) {
        this.grp.setPermcol(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#getGrpPermcol()
     */
    @Override
    public String getGrpPermcol() {
        return this.grp.getPermcol();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#setCodeId(int)
     */
    @Override
    public void setCodeId(int value) {
        if (this.code != null) {
            this.code.setCodeId(value);
        }
        if (this.vwCode != null) {
            this.vwCode.setCodeId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#getCodeId()
     */
    @Override
    public int getCodeId() {
        if (this.code != null) {
            return this.code.getCodeId();
        }
        if (this.vwCode != null) {
            return this.vwCode.getCodeId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#setCodeShortDesc(java.lang.String)
     */
    @Override
    public void setCodeShortDesc(String value) {
        if (this.code != null) {
            this.code.setShortdesc(value);
        }
        if (this.vwCode != null) {
            this.vwCode.setCodeShortdesc(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#getCodeShortName()
     */
    @Override
    public String getCodeShortName() {
        if (this.code != null) {
            return this.code.getShortdesc();
        }
        if (this.vwCode != null) {
            return this.vwCode.getCodeShortdesc();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#setCodeLongName(java.lang.String)
     */
    @Override
    public void setCodeLongName(String value) {
        if (this.code != null) {
            this.code.setLongdesc(value);
        }
        if (this.vwCode != null) {
            this.vwCode.setCodeLongdesc(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#getCodeLongName()
     */
    @Override
    public String getCodeLongName() {
        if (this.code != null) {
            return this.code.getLongdesc();
        }
        if (this.vwCode != null) {
            return this.vwCode.getCodeLongdesc();
        }
        return null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#setCodeGenIndValue(java.lang.String)
     */
    @Override
    public void setCodeGenIndValue(String value) {
        this.code.setGenIndValue(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#getCodeGenIndValue()
     */
    @Override
    public String getCodeGenIndValue() {
        return this.code.getGenIndValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#setCodePermcol(java.lang.String)
     */
    @Override
    public void setCodePermcol(String value) {
        this.code.setPermcol(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.LookupExtDto#getCodePermcol()
     */
    @Override
    public String getCodePermcol() {
        return this.code.getPermcol();
    }

}
