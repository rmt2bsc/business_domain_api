package org.dto.adapter.orm.transaction.sales;

import java.util.Date;

import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dao.mapping.orm.rmt2.VwSalesOrderInvoice;
import org.dto.SalesInvoiceDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * object <i>sales_invoice</i> table.
 * 
 * @author rterrell
 * 
 */
class SalesInvoiceRmt2OrmAdapter extends SalesOrderRmt2OrmAdapter implements
        SalesInvoiceDto {

    private SalesInvoice si;

    /**
     * Create a SalesInvoiceRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected SalesInvoiceRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a SalesInvoiceRmt2OrmAdapter that adapts data coming from the
     * <i>sales_invoice</i>.
     * 
     * @param si
     *            an instance of {@link SalesInvoice} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected SalesInvoiceRmt2OrmAdapter(SalesInvoice si) {
        // Initialize sales order invoice object
        this.updateObjHeirarchy(si);

        // Initialize sales order object.
        SalesOrder so = new SalesOrder();
        so.setSoId(this.si.getSoId());
        super.updateObjHeirarchy(so);
        return;
    }

    /**
     * Create a SalesInvoiceRmt2OrmAdapter that adapts data coming from the
     * <i>vw_sales_order_invoice</i>.
     * 
     * @param ext
     *            an instance of {@link VwSalesOrderInvoice} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected SalesInvoiceRmt2OrmAdapter(VwSalesOrderInvoice ext) {
        SalesInvoice si = null;
        SalesOrder so = null;
        SalesOrderStatus sos = null;
        Customer cust = null;

        if (ext != null) {
            // Initialize sales order invoice object
            si = new SalesInvoice();
            si.setInvoiceId(ext.getInvoiceId());
            si.setXactId(ext.getXactId());
            si.setInvoiceNo(ext.getInvoiceNo());
            si.setDateCreated(ext.getInvoiceDate());
            this.updateObjHeirarchy(si);

            // Initialize sales order object.
            so = new SalesOrder();
            so.setSoId(ext.getSalesOrderId());
            so.setCustomerId(ext.getCustomerId());
            so.setInvoiced(ext.getInvoiced());
            so.setDateCreated(ext.getSalesOrderDate());
            so.setOrderTotal(ext.getOrderTotal());
            so.setEffectiveDate(ext.getSalesOrderDate());
            this.updateObjHeirarchy(so);

            // Initialize sales order status object
            sos = new SalesOrderStatus();
            sos.setSoStatusId(ext.getOrderStatusId());
            sos.setDescription(ext.getOrderStatusDescr());
            this.updateObjHeirarchy(sos);

            // Initialize customer companion object
            cust = new Customer();
            cust.setCustomerId(ext.getCustomerId());
            cust.setAcctId(ext.getAcctId());
            cust.setAccountNo(ext.getAccountNo());
            cust.setCreditLimit(ext.getCreditLimit());
            cust.setDescription(ext.getDescription());
            this.initCustomer(cust);
        }
        else {
            this.updateObjHeirarchy(null);
        }

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
        SalesInvoice so = null;
        if (obj == null) {
            so = new SalesInvoice();
        }
        else if (obj instanceof SalesInvoice) {
            so = (SalesInvoice) obj;
        }
        else {
            return;
        }
        this.si = so;
        this.dateCreated = so.getDateCreated();
        this.dateUpdated = so.getDateUpdated();
        this.ipCreated = so.getIpCreated();
        this.ipUpdated = so.getIpUpdated();
        this.updateUserId = so.getUserId();
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesInvoiceDto#setInvoiceId(int)
     */
    @Override
    public void setInvoiceId(int value) {
        this.si.setInvoiceId(value);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesInvoiceDto#getInvoiceId()
     */
    @Override
    public int getInvoiceId() {
        return this.si.getInvoiceId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesInvoiceDto#setXactId(int)
     */
    @Override
    public void setXactId(int value) {
        this.si.setXactId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesInvoiceDto#getXactId()
     */
    @Override
    public int getXactId() {
        return this.si.getXactId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesInvoiceDto#setInvoiceNo(java.lang.String)
     */
    @Override
    public void setInvoiceNo(String value) {
        this.si.setInvoiceNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesInvoiceDto#getInvoiceNo()
     */
    @Override
    public String getInvoiceNo() {
        return this.si.getInvoiceNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesInvoiceDto#setInvoiceDate(java.util.Date)
     */
    @Override
    public void setInvoiceDate(Date value) {
        this.si.setDateCreated(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesInvoiceDto#getInvoiceDate()
     */
    @Override
    public Date getInvoiceDate() {
        return this.si.getDateCreated();
    }

}
