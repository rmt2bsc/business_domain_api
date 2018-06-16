package org.modules.transaction.sales;

import com.InvalidDataException;

/**
 * Handles the invalid customer associated with a sale order.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesOrderCustomerIdInvalidException extends InvalidDataException {
    private static final long serialVersionUID = -1884703323759924257L;

    public SalesOrderCustomerIdInvalidException() {
        super();
    }

    public SalesOrderCustomerIdInvalidException(String msg) {
        super(msg);
    }

    public SalesOrderCustomerIdInvalidException(Exception e) {
        super(e);
    }

    public SalesOrderCustomerIdInvalidException(String msg, Exception e) {
        super(msg, e);
    }
}
