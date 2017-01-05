package org.dao.inventory;

import com.RMT2Exception;

/**
 * An exception class for handling inventory data access errors.
 * 
 * @author roy terrell
 * 
 */
public class InventoryDaoException extends RMT2Exception {

    private static final long serialVersionUID = 8834182615584291890L;

    /**
     * Create InventoryDaoException object
     */
    public InventoryDaoException() {
        super();
    }

    public InventoryDaoException(String msg) {
        super(msg);
    }

    public InventoryDaoException(Exception e) {
        super(e);
    }

    public InventoryDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
