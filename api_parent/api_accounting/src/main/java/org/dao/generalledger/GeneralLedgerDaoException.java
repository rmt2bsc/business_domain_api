package org.dao.generalledger;

import org.dao.AccountingDaoException;

/**
 * Exceptoin class for general ledger related DAO errors.
 * 
 * @author rterrell
 * 
 */
public class GeneralLedgerDaoException extends AccountingDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 1596161016938944938L;

    /**
     * 
     */
    public GeneralLedgerDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public GeneralLedgerDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public GeneralLedgerDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public GeneralLedgerDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
