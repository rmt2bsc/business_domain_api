package org.dto.adapter.orm.transaction;

import org.dao.mapping.orm.rmt2.XactCodeGroup;
import org.dao.mapping.orm.rmt2.XactCodes;
import org.dto.XactCodeDto;
import org.dto.XactCodeGroupDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * tables <i>xact_codes</i> and <i>xact_code_group</i>.
 * 
 * @author Roy Terrell
 * 
 */
class XactCodesRmt2OrmAdapter extends TransactionDtoImpl implements
        XactCodeGroupDto, XactCodeDto {

    private XactCodes c;

    private XactCodeGroup g;

    /**
     * Create a XactCodesRmt2OrmAdapter without performing any data adaptations
     */
    protected XactCodesRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a XactCodesRmt2OrmAdapter that adapts data coming from the
     * xact_code_group table
     * 
     * @param grp
     *            an instance of {@link XactCodeGroup} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected XactCodesRmt2OrmAdapter(XactCodeGroup grp) {
        if (grp == null) {
            grp = new XactCodeGroup();
        }
        this.g = grp;
        this.dateCreated = grp.getDateCreated();
        this.dateUpdated = grp.getDateUpdated();
        this.updateUserId = grp.getUserId();
        this.c = null;
        return;
    }

    /**
     * Create a XactCodesRmt2OrmAdapter that adapts data coming from the
     * xact_codes table
     * 
     * @param code
     *            an instance of {@link XactCodes} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected XactCodesRmt2OrmAdapter(XactCodes code) {
        if (code == null) {
            code = new XactCodes();
        }
        this.c = code;
        this.dateCreated = code.getDateCreated();
        this.dateUpdated = code.getDateUpdated();
        this.updateUserId = code.getUserId();
        this.g = null;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#setEntityId(int)
     */
    @Override
    public void setEntityId(int value) {
        if (this.g != null) {
            this.g.setXactCodeGrpId(value);
        }
        if (this.c != null) {
            this.c.setXactCodeGrpId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#getEntityId()
     */
    @Override
    public int getEntityId() {
        if (this.g != null) {
            return this.g.getXactCodeGrpId();
        }
        if (this.c != null) {
            return this.c.getXactCodeId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#setEntityName(java.lang.String)
     */
    @Override
    public void setEntityName(String value) {
        if (this.g != null) {
            this.g.setDescription(value);
        }
        if (this.c != null) {
            this.c.setDescription(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#getEntityName()
     */
    @Override
    public String getEntityName() {
        if (this.g != null) {
            return this.g.getDescription();
        }
        if (this.c != null) {
            return this.c.getDescription();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCodeDto#setGrpId(int)
     */
    @Override
    public void setGrpId(int value) {
        this.c.setXactCodeGrpId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCodeDto#getGrpId()
     */
    @Override
    public int getGrpId() {
        return this.c.getXactCodeGrpId();
    }
}
