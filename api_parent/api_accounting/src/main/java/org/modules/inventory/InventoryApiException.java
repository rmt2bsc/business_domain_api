package org.modules.inventory;

import com.RMT2Exception;

public class InventoryApiException extends RMT2Exception {

    private static final long serialVersionUID = 8834182615584291890L;

    public InventoryApiException() {
        super();
    }

    public InventoryApiException(String msg) {
        super(msg);
    }

    public InventoryApiException(Exception e) {
        super(e);
    }

    public InventoryApiException(String msg, Throwable e) {
        super(msg, e);
    }
}
