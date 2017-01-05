package org.dao.admin;

import org.dao.ProjecttrackerDaoException;

/**
 * The exception class for handleing errors pertaining to the Project Admin DAO
 * component.
 * 
 * @author Roy Terrell
 * 
 */
public class ProjectAdminDaoException extends ProjecttrackerDaoException {

    private static final long serialVersionUID = -9121106275070847124L;

    /**
     * 
     */
    public ProjectAdminDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public ProjectAdminDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public ProjectAdminDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public ProjectAdminDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
