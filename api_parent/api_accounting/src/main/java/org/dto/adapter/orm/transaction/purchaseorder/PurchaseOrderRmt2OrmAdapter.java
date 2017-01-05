package org.dto.adapter.orm.transaction.purchaseorder;

import java.util.Date;

import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.VwPurchaseOrderList;
import org.dto.PurchaseOrderDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table <i>purchase_order</i>.
 * 
 * @author Roy Terrell
 * 
 */
class PurchaseOrderRmt2OrmAdapter extends TransactionDtoImpl implements
        PurchaseOrderDto {

    private PurchaseOrder po;

    private VwPurchaseOrderList pol;

    /**
     * Create a PurchaseOrderRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected PurchaseOrderRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a PurchaseOrderRmt2OrmAdapter that adapts data coming from the
     * item_master_status table
     * 
     * @param po
     *            an instance of {@link PurchaseOrder} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected PurchaseOrderRmt2OrmAdapter(PurchaseOrder po) {
        if (po == null) {
            po = new PurchaseOrder();
        }
        this.po = po;
        this.pol = null;
        this.dateCreated = po.getDateCreated();
        this.dateUpdated = po.getDateUpdated();
        this.updateUserId = po.getUserId();
    }

    /**
     * Create a PurchaseOrderRmt2OrmAdapter that adapts data coming from the
     * item_master_status table
     * 
     * @param po
     *            an instance of {@link VwPurchaseOrderList} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected PurchaseOrderRmt2OrmAdapter(VwPurchaseOrderList po) {
        if (po == null) {
            po = new VwPurchaseOrderList();
        }

        // Adapt purchase order entity.
        this.po = new PurchaseOrder();
        this.po.setPoId(po.getId());
        this.po.setXactId(0);
        this.po.setCreditorId(po.getCreditorId());
        this.po.setRefNo(po.getRefNo());
        this.po.setTotal(po.getTotal());

        this.updateUserId = po.getUserId();
        return;
    }

    /**
     * Sets the value of member variable poId
     */
    @Override
    public void setEntityId(int value) {
        this.po.setPoId(value);
    }

    /**
     * Gets the value of member variable poId
     */
    @Override
    public int getEntityId() {
        return this.po.getPoId();
    }

    /**
     * Not implemented
     */
    @Override
    public void setEntityName(String value) {
        return;
    }

    /**
     * Always return null. Not implemented.
     */
    @Override
    public String getEntityName() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setPoId(int)
     */
    @Override
    public void setPoId(int value) {
        this.po.setPoId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getPoId()
     */
    @Override
    public int getPoId() {
        return this.po.getPoId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setXactId(int)
     */
    @Override
    public void setXactId(int value) {
        this.po.setXactId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getXactId()
     */
    @Override
    public int getXactId() {
        return this.po.getXactId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setCreditorId(int)
     */
    @Override
    public void setCreditorId(int value) {
        this.po.setCreditorId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getCreditorId()
     */
    @Override
    public int getCreditorId() {
        return this.po.getCreditorId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setRefNo(java.lang.String)
     */
    @Override
    public void setRefNo(String value) {
        this.po.setRefNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getRefNo()
     */
    @Override
    public String getRefNo() {
        return this.po.getRefNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setTotal(double)
     */
    @Override
    public void setTotal(double value) {
        if (this.pol != null) {
            this.pol.setTotal(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getTotal()
     */
    @Override
    public double getTotal() {
        if (this.pol != null) {
            return this.pol.getTotal();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setStatusId(int)
     */
    @Override
    public void setStatusId(int value) {
        if (this.pol != null) {
            this.pol.setStatusId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getStatusId()
     */
    @Override
    public int getStatusId() {
        if (this.pol != null) {
            return this.pol.getStatusId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setStatusDescription(java.lang.String)
     */
    @Override
    public void setStatusDescription(String value) {
        if (this.pol != null) {
            this.pol.setStatusDescription(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getStatusDescription()
     */
    @Override
    public String getStatusDescription() {
        if (this.pol != null) {
            return this.pol.getStatusDescription();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setCreditorTypeId(int)
     */
    @Override
    public void setCreditorTypeId(int value) {
        if (this.pol != null) {
            this.pol.setCreditTypeId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getCreditorTypeId()
     */
    @Override
    public int getCreditorTypeId() {
        if (this.pol != null) {
            this.pol.getCreditTypeId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setBusinessId(int)
     */
    @Override
    public void setBusinessId(int value) {
        if (this.pol != null) {
            this.pol.setBusinessId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getBusinessId()
     */
    @Override
    public int getBusinessId() {
        if (this.pol != null) {
            return this.pol.getBusinessId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setAccountNumber(java.lang.String)
     */
    @Override
    public void setAccountNumber(String value) {
        if (this.pol != null) {
            this.pol.setAccountNumber(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getAccountNumber()
     */
    @Override
    public String getAccountNumber() {
        if (this.pol != null) {
            return this.pol.getAccountNumber();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.PurchaseOrderDto#setCreditorTypeDescription(java.lang.String)
     */
    @Override
    public void setCreditorTypeDescription(String value) {
        if (this.pol != null) {
            this.pol.setCreditorTypeDescr(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getCreditorTypeDescription()
     */
    @Override
    public String getCreditorTypeDescription() {
        if (this.pol != null) {
            return this.pol.getCreditorTypeDescr();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setCreditLimit(double)
     */
    @Override
    public void setCreditLimit(double value) {
        if (this.pol != null) {
            this.pol.setCreditLimit(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getCreditLimit()
     */
    @Override
    public double getCreditLimit() {
        if (this.pol != null) {
            this.pol.getCreditLimit();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setPurchaseOrderTotal(double)
     */
    @Override
    public void setPurchaseOrderTotal(double value) {
        this.po.setTotal(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getPurchaseOrderTotal()
     */
    @Override
    public double getPurchaseOrderTotal() {
        return this.po.getTotal();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setStatusHistId(int)
     */
    @Override
    public void setStatusHistId(int value) {
        if (this.pol != null) {
            this.pol.setStatusHistId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getStatusHistId()
     */
    @Override
    public int getStatusHistId() {
        if (this.pol != null) {
            this.pol.getStatusHistId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setEffectiveDate(java.util.Date)
     */
    @Override
    public void setEffectiveDate(Date value) {
        if (this.pol != null) {
            this.pol.setEffectiveDate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getEffectiveDate()
     */
    @Override
    public Date getEffectiveDate() {
        if (this.pol != null) {
            this.pol.getEffectiveDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#setEndDate(java.util.Date)
     */
    @Override
    public void setEndDate(Date value) {
        if (this.pol != null) {
            this.pol.setEndDate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderDto#getEndDate()
     */
    @Override
    public Date getEndDate() {
        if (this.pol != null) {
            this.pol.getEndDate();
        }
        return null;
    }

}
