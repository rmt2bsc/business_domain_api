package org.dto;

import java.util.Date;

/**
 * Data Transfer Object (DTO) contract that represents a sales invoice entity.
 * 
 * @author Roy Terrell
 * 
 */
public interface SalesInvoiceDto extends SalesOrderDto {
    /**
     * Sets the value of member variable invoiceId
     */
    public void setInvoiceId(int value);

    /**
     * Gets the value of member variable invoiceId
     */
    public int getInvoiceId();

    /**
     * Sets the value of member variable xactId
     */
    public void setXactId(int value);

    /**
     * Gets the value of member variable xactId
     */
    public int getXactId();

    /**
     * Sets the value of member variable invoiceNo
     */
    public void setInvoiceNo(String value);

    /**
     * Gets the value of member variable invoiceNo
     */
    public String getInvoiceNo();

    /**
     * Set sales invoice date
     */
    void setInvoiceDate(Date value);

    /**
     * Get sales invoice date
     */
    Date getInvoiceDate();
}
