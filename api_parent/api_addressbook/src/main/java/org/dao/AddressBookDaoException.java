package org.dao;

import com.api.persistence.DatabaseException;

/**
 * The base DAO exception for all modules pertaining to the Address Book API.
 * 
 * @author RTerrell
 * 
 */
public class AddressBookDaoException extends DatabaseException {
    private static final long serialVersionUID = 2969536074770899864L;

    public AddressBookDaoException() {
        super();
    }

    public AddressBookDaoException(String msg) {
        super(msg);
    }

    public AddressBookDaoException(Exception e) {
        super(e);
    }

    public AddressBookDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
