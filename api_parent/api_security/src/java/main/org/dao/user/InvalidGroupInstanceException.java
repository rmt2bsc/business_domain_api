package org.dao.user;

/**
 * Used for the handling validation errors regarding the Group.
 * 
 * @author RTerrell
 * 
 */
public class InvalidGroupInstanceException extends UserDaoException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public InvalidGroupInstanceException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public InvalidGroupInstanceException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public InvalidGroupInstanceException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public InvalidGroupInstanceException(String msg, Throwable e) {
        super(msg, e);
    }
}
