package org.modules.inventory;

import com.RMT2Exception;

public class InventoryException extends RMT2Exception {

    private static final long serialVersionUID = 8834182615584291890L;

    public InventoryException() {
        super();
    }

    public InventoryException(String msg) {
        super(msg);
    }

    public InventoryException(Exception e) {
        super(e);
    }

    public InventoryException(String msg, Throwable e) {
        super(msg, e);
    }
}
