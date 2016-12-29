package org.dto.adapter.orm.transaction.sales;

import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dto.SalesOrderStatusDto;

import com.RMT2Constants;
import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * object <i>sales_order_status</i> table.
 * 
 * @author rterrell
 * 
 */
class SalesOrderStatusRmt2OrmAdapter extends TransactionDtoImpl implements
        SalesOrderStatusDto {

    private SalesOrderStatus s;

    /**
     * Create a SalesOrderStatusRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected SalesOrderStatusRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a SalesOrderStatusRmt2OrmAdapter that adapts data coming from the
     * <i>sales_order_status</i>.
     * 
     * @param stat
     *            an instance of {@link SalesOrderStatus} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected SalesOrderStatusRmt2OrmAdapter(SalesOrderStatus stat) {
        this.updateObjHeirarchy(stat);
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.api.foundation.TransactionDtoImpl#initDataObject(java.lang.Object)
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        super.updateObjHeirarchy(obj);
        SalesOrderStatus stat = null;
        if (obj == null) {
            stat = new SalesOrderStatus();
        }
        else if (obj instanceof SalesOrderStatus) {
            stat = (SalesOrderStatus) obj;
        }
        else {
            return;
        }
        this.s = stat;
        this.dateCreated = stat.getDateCreated();
        this.dateUpdated = stat.getDateUpdated();
        this.updateUserId = stat.getUserId();
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusDto#setSoStatusId(int)
     */
    @Override
    public void setSoStatusId(int value) {
        this.s.setSoStatusId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusDto#getSoStatusId()
     */
    @Override
    public int getSoStatusId() {
        return this.s.getSoStatusId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusDto#setSoStatusDescription(java.lang.String)
     */
    @Override
    public void setSoStatusDescription(String value) {
        this.s.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusDto#getSoStatusDescription()
     */
    @Override
    public String getSoStatusDescription() {
        return this.s.getDescription();
    }

    /**
     * Not Implemented
     */
    @Override
    public void setEntityId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);

    }

    /**
     * Not Implemented
     */
    @Override
    public int getEntityId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not Implemented
     */
    @Override
    public void setEntityName(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not Implemented
     */
    @Override
    public String getEntityName() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
