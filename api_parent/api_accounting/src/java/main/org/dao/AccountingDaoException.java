package org.dao;

import com.api.persistence.DatabaseException;

/**
 * The base exception for all DAO components of the Accounting API.
 * 
 * @author Roy Terrell
 * 
 */
public class AccountingDaoException extends DatabaseException {

    private static final long serialVersionUID = 2969536074770899864L;

    public AccountingDaoException() {
        super();
    }

    public AccountingDaoException(String msg) {
        super(msg);
    }

    public AccountingDaoException(Exception e) {
        super(e);
    }

    public AccountingDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
