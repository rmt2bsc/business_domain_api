package org.dto.adapter.orm.transaction.sales;

import java.util.Date;

import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dto.SalesOrderDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * object <i>sales_order</i> table.
 * 
 * @author rterrell
 * 
 */
class SalesOrderRmt2OrmAdapter extends SalesOrderStatusRmt2OrmAdapter implements SalesOrderDto {

    private SalesOrder so;

    private Customer cust;

    /**
     * Create a SalesOrderRmt2OrmAdapter without performing any data adaptations
     */
    protected SalesOrderRmt2OrmAdapter() {
        this.cust = new Customer();
        return;
    }

    /**
     * Create a SalesOrderRmt2OrmAdapter that adapts data coming from the
     * <i>sales_order</i>.
     * 
     * @param so
     *            an instance of {@link SalesOrder} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected SalesOrderRmt2OrmAdapter(SalesOrder so) {
        this();

        // Initialize sales order status object
        SalesOrderStatus sos = new SalesOrderStatus();
        super.updateObjHeirarchy(sos);

        // Initialize sales order object
        this.updateObjHeirarchy(so);
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
        SalesOrder so = null;
        if (obj == null) {
            so = new SalesOrder();
            so.setInvoiced(-1);
        }
        else if (obj instanceof SalesOrder) {
            so = (SalesOrder) obj;
        }
        else {
            return;
        }
        this.so = so;
        this.dateCreated = so.getDateCreated();
        this.dateUpdated = so.getDateUpdated();
        this.ipCreated = so.getIpCreated();
        this.ipUpdated = so.getIpUpdated();
        this.updateUserId = so.getUserId();
        return;
    }

    /**
     * Initializes the customer member variable with properties set in
     * <i>customer</i>.
     * 
     * @param customer
     *            An instance of {@link Customer}
     */
    protected void initCustomer(Customer customer) {
        if (customer != null) {
            if (this.cust == null) {
                this.cust = new Customer();
            }
            this.cust.setAcctId(customer.getAcctId());
            this.cust.setAccountNo(customer.getAccountNo());
            this.cust.setPersonId(customer.getPersonId());
            this.cust.setBusinessId(customer.getBusinessId());
            this.cust.setCreditLimit(customer.getCreditLimit());
            this.cust.setActive(customer.getActive());
            this.cust.setDescription(customer.getDescription());
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setSalesOrderId(int)
     */
    @Override
    public void setSalesOrderId(int value) {
        this.so.setSoId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getSalesOrderId()
     */
    @Override
    public int getSalesOrderId() {
        return this.so.getSoId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setCustomerId(int)
     */
    @Override
    public void setCustomerId(int value) {
        this.so.setCustomerId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getCustomerId()
     */
    @Override
    public int getCustomerId() {
        return this.so.getCustomerId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setInvoiced(int)
     */
    @Override
    public void setInvoiced(Boolean value) {
        if (value == null) {
            this.so.setInvoiced(-1);
        }
        else {
            this.so.setInvoiced(value ? 1 : 0);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getInvoiced()
     */
    @Override
    public Boolean isInvoiced() {
        if (this.so.getInvoiced() == -1) {
            return null;
        }
        return (this.so.getInvoiced() == 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setOrderTotal(double)
     */
    @Override
    public void setOrderTotal(double value) {
        this.so.setOrderTotal(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getOrderTotal()
     */
    @Override
    public double getOrderTotal() {
        return this.so.getOrderTotal();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setCustomerName(java.lang.String)
     */
    @Override
    public void setCustomerName(String value) {
        // throw new
        // UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getCustomerName()
     */
    @Override
    public String getCustomerName() {
        // throw new
        // UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setAcctId(int)
     */
    @Override
    public void setAcctId(int value) {
        this.cust.setAcctId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getAcctId()
     */
    @Override
    public int getAcctId() {
        return this.cust.getAcctId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setAccountNo(java.lang.String)
     */
    @Override
    public void setAccountNo(String value) {
        this.cust.setAccountNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getAccountNo()
     */
    @Override
    public String getAccountNo() {
        return this.cust.getAccountNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setCreditLimit(double)
     */
    @Override
    public void setCreditLimit(double value) {
        this.cust.setCreditLimit(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getCreditLimit()
     */
    @Override
    public double getCreditLimit() {
        return this.cust.getCreditLimit();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setPersonId(int)
     */
    @Override
    public void setPersonId(int value) {
        this.cust.setPersonId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getPersonId()
     */
    @Override
    public int getPersonId() {
        return this.cust.getPersonId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setBusinessId(int)
     */
    @Override
    public void setBusinessId(int value) {
        this.cust.setBusinessId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getBusinessId()
     */
    @Override
    public int getBusinessId() {
        return this.cust.getBusinessId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#setSaleOrderDate(java.util.Date)
     */
    @Override
    public void setSaleOrderDate(Date value) {
        this.so.setEffectiveDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderDto#getSaleOrderDate()
     */
    @Override
    public Date getSaleOrderDate() {
        return this.so.getEffectiveDate();
    }

    @Override
    public Date getEffectiveDate() {
        return this.so.getEffectiveDate();
    }

    @Override
    public void setEffectiveDate(Date value) {
        this.so.setEffectiveDate(value);
    }

}
