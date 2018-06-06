package org.dao.user;

import org.dao.mapping.orm.rmt2.UserGroup;
import org.dao.mapping.orm.rmt2.VwUser;
import org.dto.UserDto;

import com.RMT2Base;

/**
 * A factory for DAO instances that manage user related entities.
 * 
 * @author rterrell
 * 
 */
public class UserDaoFactory extends RMT2Base {

    /**
     * Default Constructor
     */
    public UserDaoFactory() {
        return;
    }

    /**
     * Creates a database implementataion of UserDao interface which is capable
     * of accessing data from relational database management system (RDBMS).
     * 
     * @return an instance of {@link UserDao}
     */
    public UserDao createRmt2OrmDao() {
        UserDao dao = new Rmt2OrmUserDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of UserDao interface which is capable
     * of accessing data from relational database management system (RDBMS).
     * 
     * @param appName
     *            application name
     * @return an instance of {@link UserDao}
     */
    public UserDao createRmt2OrmDao(String appName) {
        UserDao dao = new Rmt2OrmUserDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a LDAP implementataion of UserDao interface which is capable of
     * accessing data from a directory server by using an anonymous connection.
     * 
     * @return an instance of {@link UserDao}
     */
    public UserDao createLdapDao() {
        UserDao dao = new LdapUserDaoImpl();
        return dao;
    }

    /**
     * Build Extended User selection criteria from a UserDto instance
     * 
     * @param criteria an instance of {@link UserDto}
     * @return an instance of [{@link VwUser}
     */
    public static final VwUser buildUserCriteria(UserDto criteria) {
        VwUser user = new VwUser();

        if (criteria != null) {
            if (criteria.getLoginUid() > 0) {
                user.addCriteria(VwUser.PROP_LOGINID, criteria.getLoginUid());
            }
            if (criteria.getGroupId() > 0) {
                user.addCriteria(VwUser.PROP_GRPID, criteria.getGroupId());
            }
            if (criteria.getUsername() != null) {
                user.addLikeClause(VwUser.PROP_USERNAME, criteria.getUsername());
            }
            if (criteria.getFirstname() != null) {
                user.addLikeClause(VwUser.PROP_FIRSTNAME, criteria.getFirstname());
            }
            if (criteria.getLastname() != null) {
                user.addLikeClause(VwUser.PROP_LASTNAME, criteria.getLastname());
            }
            if (criteria.getEmail() != null) {
                user.addCriteria(VwUser.PROP_EMAIL, criteria.getEmail());
            }
            if (criteria.getBirthDate() != null) {
                user.addCriteria(VwUser.PROP_BIRTHDATE, criteria.getBirthDate());
            }
            if (criteria.getSsn() != null) {
                user.addCriteria(VwUser.PROP_SSN, criteria.getSsn());
            }
            if (criteria.getStartDate() != null) {
                user.addCriteria(VwUser.PROP_STARTDATE, criteria.getStartDate());
            }
            if (criteria.getTerminationDate() != null) {
                user.addCriteria(VwUser.PROP_TERMINATIONDATE, criteria.getTerminationDate());
            }
            if (criteria.getActive() > 0) {
                user.addCriteria(VwUser.PROP_ACTIVE, criteria.getActive());
            }
            if (criteria.isQueryLoggedInFlag()) {
                user.addCriteria(VwUser.PROP_LOGGEDIN, criteria.getLoggedIn());
            } 
        }
        return user;
    }
    
    
    /**
     * Build User Group selection criteria from a UserDto instance
     * 
     * @param criteria an instance of {@link UserDto}
     * @return an instance of [{@link UserGroup}
     */
    public static final UserGroup buildGroupCriteria(UserDto criteria) {
        UserGroup grp = new UserGroup();

        if (criteria != null) {
            if (criteria.getGroupId() > 0) {
                grp.addCriteria(UserGroup.PROP_GRPID, criteria.getGroupId());
            }
            if (criteria.getGrpDescription() != null) {
                grp.addLikeClause(UserGroup.PROP_DESCRIPTION, criteria.getGrpDescription());
            }
        }
        return grp;
    }
}
