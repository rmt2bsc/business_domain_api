package org.dao.transaction;

import com.RMT2Exception;

/**
 * Handles common transacton errors.
 * 
 * @author Roy Terrell
 * 
 */
public class XactDaoException extends RMT2Exception {
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

    public XactDaoException(String msg, Exception e) {
        super(msg, e);
    }
}
