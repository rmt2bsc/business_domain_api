package org.dto.adapter.orm.transaction;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.XactCategory;
import org.dto.XactCategoryDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table, <i>xact_category</i>.
 * 
 * @author rterrell
 * 
 */
class XactCategoryRmt2OrmAdapter extends TransactionDtoImpl implements
        XactCategoryDto {

    private XactCategory xc;

    /**
     * Create a XactCategoryRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected XactCategoryRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a XactCategoryRmt2OrmAdapter that adapts data coming from the
     * xact_category table
     * 
     * @param xactCatg
     *            an instance of {@link XactCategory} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected XactCategoryRmt2OrmAdapter(XactCategory xactCatg) {
        this.updateObjHeirarchy(xactCatg);
        return;
    }

    /**
     * Tries to cast <i>obj</i> as a <i>XactCategory</i> object.
     * <p>
     * If <i>obj</i> is not of the expected data type, then the process of
     * assigning the data object is ignored.
     * 
     * @param obj
     *            is expected to be an instance of {@link XactCategory} or null
     *            if the desire is to deal with a newly instantiated data
     *            object.
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        super.updateObjHeirarchy(obj);
        if (obj == null) {
            this.xc = new XactCategory();
        }
        else if (obj instanceof XactCategory) {
            this.xc = (XactCategory) obj;
        }
        else if (obj instanceof VwXactList) {
            VwXactList in = (VwXactList) obj;
            this.xc = new XactCategory();
            this.xc.setXactCatgId(in.getXactCatgId());
        }
        else if (obj instanceof VwXactTypeItemActivity) {
            VwXactTypeItemActivity in = (VwXactTypeItemActivity) obj;
            this.xc = new XactCategory();
            this.xc.setDescription(in.getXactCategoryDescription());
            this.xc.setCode(in.getXactCategoryCode());
            this.xc.setXactCatgId(in.getXactCategoryId());
        }
        else {
            // Leave XactType object as is...
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCategoryDto#setId(int)
     */
    @Override
    public void setXactCatgId(int value) {
        this.xc.setXactCatgId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCategoryDto#getId()
     */
    @Override
    public int getXactCatgId() {
        return this.xc.getXactCatgId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCategoryDto#setDescription(java.lang.String)
     */
    @Override
    public void setXactCatgDescription(String value) {
        this.xc.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCategoryDto#getDescription()
     */
    @Override
    public String getXactCatgDescription() {
        return this.xc.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCategoryDto#setCode(java.lang.String)
     */
    @Override
    public void setXactCatgCode(String value) {
        this.xc.setCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCategoryDto#getCode()
     */
    @Override
    public String getXactCatgCode() {
        return this.xc.getCode();
    }

    /**
     * Not Implemented
     */
    @Override
    public void setEntityId(int value) {
        this.xc.setXactCatgId(value);
    }

    /**
     * Always return zero
     */
    @Override
    public int getEntityId() {
        return this.xc.getXactCatgId();
    }

    /**
     * Not Implemented
     */
    @Override
    public void setEntityName(String value) {
        this.xc.setDescription(value);
    }

    /**
     * Always return null.
     */
    @Override
    public String getEntityName() {
        return this.xc.getDescription();
    }

}
