package org.modules.transaction;

import com.RMT2Exception;

/**
 * Handles common transacton errors.
 * 
 * @author Roy Terrell
 * 
 */
public class XactApiException extends RMT2Exception {
    private static final long serialVersionUID = -1884703323759924257L;

    public XactApiException() {
        super();
    }

    public XactApiException(String msg) {
        super(msg);
    }

    public XactApiException(Exception e) {
        super(e);
    }

    public XactApiException(String msg, Throwable e) {
        super(msg, e);
    }
}
