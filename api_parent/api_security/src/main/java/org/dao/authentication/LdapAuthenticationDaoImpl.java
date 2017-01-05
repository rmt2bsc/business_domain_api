package org.dao.authentication;

import org.dao.SecurityDaoException;
import org.dao.user.UserDao;
import org.dao.user.UserDaoException;
import org.dao.user.UserDaoFactory;
import org.dto.UserDto;

import com.RMT2Constants;
import com.api.ldap.AbstractLdapDaoClient;
import com.api.ldap.LdapClient;
import com.api.security.User;

/**
 * An JNDI implementation of the {@link AuthenticationDao} interface which
 * functions to authenticate a user from a LDAP server.
 * <p>
 * The base DN in which this implementation operates from is
 * <i>loginid=xxxx,ou=People,o=RMT2BSC,dc=rmt2,dc=net</i>. This base DN is where
 * the user's profile is found. The <i>xxxx</i> place holder is generally
 * replaced with user's login id.
 * 
 * @author Roy Terrell
 * 
 */
class LdapAuthenticationDaoImpl extends AbstractLdapDaoClient implements
        AuthenticationDao {

    private String ldapConfigProfile;

    /**
     * Default LDAP configuration constructor
     */
    public LdapAuthenticationDaoImpl() {
        super();
        this.ldapConfigProfile = null;
    }

    /**
     * Create LdapAuthenticationDaoImpl object with a specific profile.
     * 
     * @param ldapProfile
     */
    protected LdapAuthenticationDaoImpl(String ldapProfile) {
        this();
        this.ldapConfigProfile = ldapProfile;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.authentication.AuthenticationDao#loginUser(java.lang.String,
     * java.lang.String)
     */
    @Override
    public User loginUser(String loginId, String password)
            throws SecurityDaoException {
        // Validate user input parameters
        if (loginId == null) {
            throw new SecurityDaoException("Login Id is required");
        }
        if (password == null) {
            throw new SecurityDaoException("Password is required");
        }
        // Authenticate user via LDAP
        try {
            if (this.ldapConfigProfile == null) {
                this.ldap.connect(loginId, password,
                        LdapClient.DEFAULT_CONFIG_RESOURCE);
            }
            else {
                this.ldap.connect(loginId, password, this.ldapConfigProfile);
            }
        } catch (Exception e) {
            throw new SecurityDaoException(e);
        }

        // Obtain user's profile and return to caller
        UserDaoFactory uf = new UserDaoFactory();
        UserDao dao = uf.createLdapDao();
        try {
            UserDto dto = dao.fetchUser(loginId);
            // update user login count
            int logons = dto.getTotalLogons();
            dto.setTotalLogons(++logons);
            dao.maintainUser(dto);
            return (User) dto;
        } catch (UserDaoException e) {
            this.msg = "Error retrieving/updating user's profile from the LDAP server during user authentication: "
                    + loginId;
            throw new SecurityDaoException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.authentication.AuthenticationDao#logoutUser(java.lang.String)
     */
    @Override
    public void logoutUser(String loginId) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * @return the ldapConfigProfile
     */
    public String getLdapConfigProfile() {
        return ldapConfigProfile;
    }

    /**
     * @param ldapConfigProfile
     *            the ldapConfigProfile to set
     */
    public void setLdapConfigProfile(String ldapConfigProfile) {
        this.ldapConfigProfile = ldapConfigProfile;
    }

}
