package org.dao.transaction;

import com.RMT2RuntimeException;

/**
 * Handles common transacton errors.
 * 
 * @author Roy Terrell
 * 
 */
public class XactDaoException extends RMT2RuntimeException {
    private static final long serialVersionUID = -1884703323759924257L;

    public XactDaoException() {
        super();
    }

    public XactDaoException(String msg) {
        super(msg);
    }

    public XactDaoException(Exception e) {
        super(e);
    }

    public XactDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
