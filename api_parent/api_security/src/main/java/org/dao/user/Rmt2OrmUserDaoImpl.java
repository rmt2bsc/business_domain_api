package org.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.SecurityDaoImpl;
import org.dao.mapping.orm.rmt2.UserGroup;
import org.dao.mapping.orm.rmt2.UserLogin;
import org.dao.mapping.orm.rmt2.VwUser;
import org.dto.UserDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;

import com.api.persistence.DatabaseException;
import com.api.persistence.PersistenceClient;
import com.api.util.RMT2Date;
import com.api.util.UserTimestamp;

/**
 * RMT2 ORM implementation for the {@link UserDao} interface which accesses and
 * manipulates user and group related data.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmUserDaoImpl extends SecurityDaoImpl implements UserDao {

    private static final Logger logger = Logger
            .getLogger(Rmt2OrmUserDaoImpl.class);

    /**
     * Default constructor
     */
    protected Rmt2OrmUserDaoImpl() {
        super();
        return;
    }

    /**
     * @param appName
     *            application name
     */
    protected Rmt2OrmUserDaoImpl(String appName) {
        super(appName);
        return;
    }

    /**
     * Creates a Rmt2OrmUserDaoImpl object with a shared persistent client.
     * 
     * @param client
     */
    protected Rmt2OrmUserDaoImpl(PersistenceClient client) {
        super(client);
    }
    
    /**
     * Query the user_login table for one or more records using a combination of
     * user related property values contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link UserDto} containing a combination of
     *            optional property values used to build selection criteria.
     *            <ol>
     *            <li>user's internal unique id</li>
     *            <li>login id</li>
     *            <li>user name</li>
     *            <li>group id</li>
     *            <li>first name</li>
     *            <li>last name</li>
     *            <li>email</li>
     *            <li>birth date</li>
     *            <li>social security number</li>
     *            <li>start date</li>
     *            <li>termination date</li>
     *            <li>active</li>
     *            <li>logged in flag</li>
     *            </ol>
     * @return a List of {@link UserDto} objects or null when no data is found.
     * @throws UserDaoException
     *             database access error
     */
    @Override
    public List<UserDto> fetchUserProfile(UserDto criteria) throws UserDaoException {
        VwUser user = UserDaoFactory.buildUserCriteria(criteria);
        user.addOrderBy(VwUser.PROP_LASTNAME, VwUser.ORDERBY_ASCENDING);
        user.addOrderBy(VwUser.PROP_FIRSTNAME, VwUser.ORDERBY_ASCENDING);
        user.addOrderBy(VwUser.PROP_USERNAME, VwUser.ORDERBY_ASCENDING);

        List<VwUser> results = null;
        try {
            results = this.client.retrieveList(user);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new UserDaoException(e);
        }

        List<UserDto> users = new ArrayList<UserDto>();
        for (VwUser item : results) {
            UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(item);
            users.add(dto);
        }
        return users;
    }

    /**
     * Creates a new or modifies an existing record in the user_login table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param user
     *            an instance of {@link UserDto} containing data that will be
     *            mapped to an {@link UserLogin} instance targeted for a
     *            insert/update operation.
     * @return The internal unique id for an insert operation or the total
     *         number of rows effected for an update operation.
     * @throws UserDaoException
     *             <i>user</i> contains invalid data or database access errors.
     */
    @Override
    public int maintainUser(UserDto user) throws UserDaoException {
        int rc;

        // Convert input object
        UserLogin u = new UserLogin();
        u.setLoginId(user.getLoginUid());
        u.setGrpId(user.getGroupId());
        u.setUsername(user.getUsername());
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setBirthDate(user.getBirthDate());
        u.setSsn(user.getSsn());
        u.setStartDate(user.getStartDate());
        u.setTerminationDate(user.getTerminationDate());
        u.setDescription(user.getUserDescription());
        u.setTotalLogons(user.getTotalLogons());
        u.setLoggedIn(user.getLoggedIn());
        u.setEmail(user.getEmail());
        u.setActive(user.getActive());
        u.setDateCreated(user.getDateCreated());
        u.setDateUpdated(user.getDateUpdated());
        u.setUserId(user.getUpdateUserId());
        u.setPassword(user.getPassword());

        // start transaction
        this.client.beginTrans();
        try {
            if (u.getLoginId() == 0) {
                rc = this.createUser(u);
                u.setLoginId(rc);
                user.setLoginUid(rc);
                this.msg = "User Login record was created successfully for " + u.getUsername();
                logger.info(this.msg);
            }
            else {
                rc = this.updateUser(u);
                this.msg = "User Login record was updated successfully for "
                        + u.getUsername()
                        + ".  Total number of rows effected by operation: "
                        + rc;
                logger.info(this.msg);
            }
            this.client.commitTrans();
            return rc;
        } catch (Exception e) {
            this.client.rollbackTrans();
            this.msg = "User maintenance database transaction failed for " + user.getUsername();
            throw new UserDaoException(this.msg, e);
        }
    }

    /**
     * Creates an User Login.
     * 
     * @param usr
     *            The user login object to persist
     * @return int value as the new user id.
     * @throws DatabaseException
     */
    private int createUser(UserLogin usr) throws DatabaseException {
        // Add record to database
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            usr.setActive(1);
            usr.setDateCreated(ut.getDateCreated());
            usr.setDateUpdated(ut.getDateCreated());
            usr.setUserId(ut.getLoginId());
            int rc = this.client.insertRow(usr, true);
            usr.setLoginId(rc);
            return rc;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Updates user's profile
     * 
     * @param usr
     *            The user changes to persist
     * @return int value acting as the total number of rows effected
     * @throws DatabaseException
     */
    private int updateUser(UserLogin usr) throws DatabaseException {
        // Perform updates
        usr.addCriteria(UserLogin.PROP_LOGINID, usr.getLoginId());
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            usr.setDateUpdated(ut.getDateCreated());
            usr.setUserId(ut.getLoginId());
            int rows = this.client.updateRow(usr);
            return rows;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    /**
     * Obtains the user's profile using the unique identifier.
     * 
     * @param login
     *            the user login id
     * @return An instance of {@link UserDto}
     * @throws UserDaoException
     */
    @Override
    public UserDto fetchUserProfile(int uid) throws UserDaoException {
        UserLogin user = new UserLogin();
        user.addCriteria(UserLogin.PROP_LOGINID, uid);
        
        try {
            user = (UserLogin) this.client.retrieveObject(user);
            if (user == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new UserDaoException(e);
        }
        
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(user);
        return dto;
    }
    
    /**
     * Obtains the user's profile as an instance of UserLogin from the database
     * using user name.
     * 
     * @param login
     *            the user login id
     * @return An instance of {@link UserDto}
     * @throws UserDaoException
     */
    @Override
    public UserDto fetchUserProfile(String userName) throws UserDaoException {
        UserLogin user = new UserLogin();
        user.addCriteria(UserLogin.PROP_USERNAME, userName);
        
        try {
            user = (UserLogin) this.client.retrieveObject(user);
            if (user == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new UserDaoException(e);
        }
        
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(user);
        return dto;
    }

    /**
     * Delete a record from the user_login table using the internal unique
     * identifier.
     * 
     * @param uid
     *            the user's internal unique identifier.
     * @return the total number of rows effected.
     * @throws UserDaoException
     */
    @Override
    public int deleteUser(int uid) throws UserDaoException {
        UserLogin user = new UserLogin();
        this.client.beginTrans();
        try {
            user.addCriteria("LoginId", uid);
            int rows = this.client.deleteRow(user);
            this.client.commitTrans();
            return rows;
        } catch (Exception e) {
            this.client.rollbackTrans();
            throw new UserDaoException(e);
        }
    }

    /**
     * Query the user_group table for all records.
     * 
     * @param group
     *            an instance of {@link UserDto} containing the group data.
     * @return List of {@link UserDto} objects containing group data or null if
     *         no data is found
     * @throws UserDaoException
     *             database access errors.
     */
    @Override
    public List<UserDto> fetchUserGroup(UserDto group) throws UserDaoException {
        UserGroup grp = UserDaoFactory.buildGroupCriteria(group);
        grp.addOrderBy(UserGroup.PROP_DESCRIPTION, UserGroup.ORDERBY_ASCENDING);
        List<UserGroup> results = null;
        try {
            results = this.client.retrieveList(grp);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new UserDaoException(e);
        }

        List<UserDto> groups = new ArrayList<UserDto>();
        for (UserGroup item : results) {
            UserDto dto = Rmt2OrmDtoFactory.getGroupDtoInstance(item);
            groups.add(dto);
        }
        return groups;
    }

    /**
     * 
     * @param grpId
     * @return
     * @throws UserDaoException
     */
    @Override
    public UserDto fetchUserGroup(int grpId) throws UserDaoException {
        UserGroup grp = new UserGroup();
        grp.addCriteria(UserGroup.PROP_GRPID, grpId);
        UserGroup results = null;
        try {
            results = (UserGroup) this.client.retrieveObject(grp);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new UserDaoException(e);
        }
        
        UserDto dto = Rmt2OrmDtoFactory.getGroupDtoInstance(results);
        return dto;
    }

    /**
     * Creates a new or modifies an existing record in the user_group table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param grp
     *            an instance of {@link UserDto} containing data that will be
     *            mapped to an {@link UserGroup} instance targeted for a
     *            insert/update operation.
     * @return The group id for an insert operation or the total number of rows
     *         effected for an update operation.
     * 
     * @throws UserDaoException
     *             <i>grp</i> contains invalid data or database access error.
     */
    @Override
    public int maintainGroup(UserDto grp) throws UserDaoException {
              // Convert input object
        UserGroup u = new UserGroup();
        u.setGrpId(grp.getGroupId());
        u.setDescription(grp.getGrp());
        u.setDateCreated(grp.getDateCreated());
        u.setDateUpdated(grp.getDateUpdated());
        u.setUserId(grp.getUpdateUserId());

        this.client.beginTrans();
        int rc = 0;
        try {
            if (u.getGrpId() > 0) {
                rc = this.updateGroup(u);
                this.msg = "Security Group record was created successfully for " + u.getDescription();
            }
            if (u.getGrpId() == 0) {
                rc = this.createGroup(u);
                u.setGrpId(rc);
                grp.setGroupId(rc);
                this.msg = "Security Group record was created successfully for " + u.getDescription();
            }
            this.client.commitTrans();
            return rc;
        } catch (DatabaseException e) {
            this.client.rollbackTrans();
            this.msg = "Security Group maintenance database transaction failed for " + u.getDescription();
            throw new UserDaoException(this.msg, e);
        }
    }

    /**
     * Adds an User Group record to the database,.
     * 
     * @param grp
     *            An instance of {@link org.dao.bean.Application}
     * @throws DatabaseException
     *             For database and system errors.
     */
    private int createGroup(UserGroup grp) throws DatabaseException {
        UserTimestamp ut = null;
        ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
        grp.setDateCreated(ut.getDateCreated());
        grp.setDateUpdated(ut.getDateCreated());
        grp.setUserId(ut.getLoginId());
        int rc = this.client.insertRow(grp, true);
        return rc;
    }

    /**
     * Updates a single user group record to the database.
     * 
     * @param grp
     *            an instance of {@link org.dao.bean.Application}
     * @throws DatabaseException
     *             for database and system errors.
     */
    private int updateGroup(UserGroup grp) throws DatabaseException {
        UserDto orig = this.fetchUserGroup(grp.getGrpId());
        if (orig != null) {
            grp.setDateCreated(orig.getDateCreated());
        }
        UserTimestamp ut = null;
        ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
        grp.setDateUpdated(ut.getDateCreated());
        grp.setUserId(ut.getLoginId());
        grp.addCriteria(UserGroup.PROP_GRPID, grp.getGrpId());
        int rc = this.client.updateRow(grp);
        return rc;
    }

      /**
     * Deletes a record from the user_group table using the groups primary key,
     * <i>grpId</i>.
     * 
     * @param grpId
     *            The id of the group that is to be delete.
     * @return an int value acting as the total number of targets affected.
     * @throws UserDaoException
     *             <i>grpId</i> is less than or equal to zero or database access
     *             error.
     */
    @Override
    public int deleteGroup(int grpId) throws UserDaoException {
        if (grpId <= 0) {
            this.msg = "User Group id is required";
            throw new UserDaoException(this.msg);
        }
        this.client.beginTrans();
        try {
            UserGroup grp = new UserGroup();
            grp.addCriteria(UserGroup.PROP_GRPID, grpId);
            int rc = this.client.deleteRow(grp);
            this.client.commitTrans();
            return rc;
        } catch (DatabaseException e) {
            this.client.rollbackTrans();
            throw new UserDaoException("User Group delete opertaion failed", e);
        }

    }
}
