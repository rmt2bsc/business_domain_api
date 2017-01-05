package org.dao.user;

import java.util.List;

import org.dao.SecurityDaoImpl;
import org.dto.UserDto;

/**
 * @author Roy Terrell
 * 
 */
public class DefaultUserDaoImpl extends SecurityDaoImpl implements UserDao {

    /**
     * 
     */
    public DefaultUserDaoImpl() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.persistence.DaoClient#close()
     */
    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.persistence.DaoClient#getDaoUser()
     */
    @Override
    public String getDaoUser() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.persistence.DaoClient#setDaoUser(java.lang.String)
     */
    @Override
    public void setDaoUser(String userName) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#fetchUser()
     */
    @Override
    public List<UserDto> fetchUser() throws UserDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#fetchUser(int)
     */
    @Override
    public UserDto fetchUser(int uid) throws UserDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#fetchUser(java.lang.String)
     */
    @Override
    public UserDto fetchUser(String userName) throws UserDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#fetchUser(org.dto.UserDto)
     */
    @Override
    public List<UserDto> fetchUser(UserDto user) throws UserDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#maintainUser(org.dto.UserDto)
     */
    @Override
    public int maintainUser(UserDto user) throws UserDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#deleteUser(int)
     */
    @Override
    public int deleteUser(int uid) throws UserDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#deleteUser(java.lang.String)
     */
    @Override
    public int deleteUser(String userName) throws UserDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#activateUser(java.lang.String)
     */
    @Override
    public int activateUser(String userName) throws UserDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#inActivateUser(java.lang.String)
     */
    @Override
    public int inActivateUser(String userName) throws UserDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#fetchGroup()
     */
    @Override
    public List<UserDto> fetchGroup() throws UserDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#fetchGroup(int)
     */
    @Override
    public UserDto fetchGroup(int grpId) throws UserDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#fetchGroup(java.lang.String)
     */
    @Override
    public UserDto fetchGroup(String grpName) throws UserDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#maintainGroup(org.dto.UserDto)
     */
    @Override
    public int maintainGroup(UserDto grp) throws UserDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#deleteGroup(int)
     */
    @Override
    public int deleteGroup(int grpId) throws UserDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#deleteGroup(java.lang.String)
     */
    @Override
    public int deleteGroup(String grpName) throws UserDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

}
