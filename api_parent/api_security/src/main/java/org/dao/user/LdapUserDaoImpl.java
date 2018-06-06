package org.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.dao.SecurityDaoException;
import org.dao.mapping.orm.ldap.LdapAppRoles;
import org.dao.mapping.orm.ldap.LdapUser;
import org.dao.mapping.orm.ldap.LdapUserGroup;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.dto.UserDto;
import org.dto.adapter.ldap.LdapDtoFactory;

import com.RMT2Constants;
import com.api.ldap.AbstractLdapDaoClient;
import com.api.ldap.LdapClient;
import com.api.ldap.operation.LdapAddOperation;
import com.api.ldap.operation.LdapDeleteOperation;
import com.api.ldap.operation.LdapModifyOperation;
import com.api.ldap.operation.LdapSearchOperation;
import com.api.persistence.DatabaseException;
import com.util.RMT2Date;

/**
 * A JNDI implementation for the {@link UserDao} interface which accesses and
 * manipulates user and group related data from a LDAP server.
 * <p>
 * The base DN in which this implementation operates from is
 * <i><i>ou=People,o=RMT2BSC,dc=rmt2,dc=net</i></i>.
 * 
 * @author Roy Terrell
 * 
 */
class LdapUserDaoImpl extends AbstractLdapDaoClient implements UserDao {

    /**
     * Construct a LdapUserDaoImpl that establishes a LDAP client and attemtps
     * to gain a connection to the LDAP server using the default LDAP
     * application user profile.
     */
    protected LdapUserDaoImpl() {
        super();
    }

    /**
     * Fetch all users that reside at DN,
     * <i>ou=People,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @return a List of {@link UserDto} objects containing the user data.
     * 
     * @throws UserDaoException
     */
    public List<UserDto> fetchUser() throws UserDaoException {
        List<LdapUser> userList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=People");
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapUser");
            Object results[] = this.ldap.retrieve(op);
            userList = this.ldap.extractLdapResults(results);
            if (userList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }

        List<UserDto> list = new ArrayList<UserDto>();
        for (LdapUser item : userList) {
            UserDto dto = LdapDtoFactory.getUserDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetch user profile by user name starting at DN,
     * <i>loginid=xxx,ou=People,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param userName
     *            the user's login id.
     * @return an instance of {@link UserDto}
     * @throws UserDaoException
     */
    public UserDto fetchUser(String userName) throws UserDaoException {
        LdapUser ldapUser = null;
        List<LdapUser> userList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=People");
            op.getMatchAttributes().put("loginid", userName);
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapUser");
            Object results[] = this.ldap.retrieve(op);
            userList = this.ldap.extractLdapResults(results);
            if (userList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }

        if (userList.size() > 1) {
            this.msg = "Too many users were returned from LDAP server for a single user query";
            throw new UserDaoException();
        }
        else {
            ldapUser = userList.get(0);
        }
        UserDto dto = LdapDtoFactory.getUserDtoInstance(ldapUser);
        return dto;
    }

    /**
     * Fetch user profile using custom criteria starting at the base DN,
     * <i>ou=People,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param criteria
     *            an instance of {@link UserDto} containing property values used
     *            to build selection criteria.
     * @return a List of {@link UserDto} objects or null when no data is found.
     * @throws UserDaoException
     */
    @Override
    public List<UserDto> fetchUser(UserDto criteria) throws UserDaoException {
        List<LdapUser> userList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=People");

            if (criteria != null) {
                if (criteria.getUsername() != null) {
                    op.getMatchAttributes().put("loginid",
                            criteria.getUsername());
                }
                if (criteria.getGrp() != null) {
                    op.getMatchAttributes().put("grp", criteria.getGrp());
                }
                if (criteria.getFirstname() != null) {
                    op.getMatchAttributes().put("fn", criteria.getFirstname());
                }
                if (criteria.getLastname() != null) {
                    op.getMatchAttributes().put("sn", criteria.getLastname());
                }
                if (criteria.getEmail() != null) {
                    op.getMatchAttributes().put("email", criteria.getEmail());
                }
                if (criteria.getBirthDate() != null) {
                    op.getMatchAttributes().put(
                            "dob",
                            RMT2Date.formatDate(criteria.getBirthDate(),
                                    "MM/dd/yyyy"));
                }
                if (criteria.getSsn() != null) {
                    op.getMatchAttributes().put("ssn", criteria.getSsn());
                }
                if (criteria.getStartDate() != null) {
                    op.getMatchAttributes().put(
                            "startDate",
                            RMT2Date.formatDate(criteria.getStartDate(),
                                    "MM/dd/yyyy"));
                }
                if (criteria.getTerminationDate() != null) {
                    op.getMatchAttributes().put(
                            "termDate",
                            RMT2Date.formatDate(criteria.getTerminationDate(),
                                    "MM/dd/yyyy"));
                }
                if (criteria.isQueryLoggedInFlag()) {
                    op.getMatchAttributes().put("loggedIn",
                            String.valueOf(criteria.getLoggedIn()));
                }
            }

            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapUser");
            Object results[] = this.ldap.retrieve(op);
            userList = this.ldap.extractLdapResults(results);
            if (userList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }

        // Package results
        List<UserDto> list = new ArrayList<UserDto>();
        for (LdapUser item : userList) {
            UserDto dto = LdapDtoFactory.getUserDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetch all user groups starting at DN,
     * <i>ou=UserGroups,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @return a List of {@link UserDto} objects containing the user groups or
     *         null if no data is found.
     * 
     * @throws UserDaoException
     */
    public List<UserDto> fetchGroup() throws UserDaoException {
        List<LdapUserGroup> userList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=UserGroups,ou=LookupCodes");
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapUserGroup");
            Object results[] = this.ldap.retrieve(op);
            userList = this.ldap.extractLdapResults(results);
            if (userList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }

        List<UserDto> list = new ArrayList<UserDto>();
        for (LdapUserGroup item : userList) {
            UserDto dto = LdapDtoFactory.getUserDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetch user group by name starting at DN,
     * <i>cn=xxx,ou=UserGroups,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param grpName
     *            The group's name
     * @return An instance of {@link UserDto} containing the user group data of
     *         null if no data is found.
     * @throws UserDaoException
     */
    public UserDto fetchGroup(String grpName) throws UserDaoException {
        List<LdapUserGroup> userList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=UserGroups,ou=LookupCodes");
            op.getMatchAttributes().put("cn", grpName);
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapUserGroup");
            Object results[] = this.ldap.retrieve(op);
            userList = this.ldap.extractLdapResults(results);
            if (userList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }

        LdapUserGroup ldapUserGrp = null;
        if (userList.size() > 1) {
            this.msg = "Too many user groups were returned from LDAP server for a single user group query";
            throw new UserDaoException();
        }
        else {
            ldapUserGrp = userList.get(0);
        }
        UserDto dto = LdapDtoFactory.getUserDtoInstance(ldapUserGrp);
        return dto;
    }

    /**
     * Create or modify a user profile where the changes will be persisted to a
     * LDAP server.
     * <p>
     * Changes are persisted at DN,
     * <i>loginid=admin,ou=People,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param user
     *            an instance of {@link UserDto}
     * @return int always return <i>1</i>.
     * @throws UserDaoException
     */
    @Override
    public int maintainUser(UserDto user) throws UserDaoException {
        this.validateUser(user);

        // Determine if this is a new or existing user
        boolean newUser = (this.fetchUser(user.getUsername()) == null);
        if (newUser) {
            this.validateNewUser(user);
            this.insertUser(user);
        }
        else {
            this.updateUser(user);
        }
        return 1;
    }

    /**
     * Inserts a user profile at DN,
     * <i>loginid=xxx,ou=People,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * The <i>loginCount</i> and <i>loggedIn</i> attributes are both defaulted
     * to zero.
     * 
     * @param usr
     *            The user profile as a DTO
     * @throws DatabaseException
     */
    private void insertUser(UserDto usr) throws DatabaseException {
        LdapAddOperation op = new LdapAddOperation();
        // Handle required fields
        String dn = "loginid=" + usr.getUsername() + ",ou=People";
        String fullName = usr.getFirstname() + " " + usr.getLastname();
        String dob = RMT2Date.formatDate(usr.getBirthDate(), "MM/dd/yyyy");
        String startDate = RMT2Date
                .formatDate(usr.getStartDate(), "MM/dd/yyyy");
        op.setDn(dn);
        op.addListAttribute("objectClass", "person");
        op.addListAttribute("objectClass", "RMT2Person");
        op.addListAttribute("objectClass", "RMT2User");
        op.addListAttribute("objectClass", "top");
        op.addAttribute("fn", usr.getFirstname());
        op.addAttribute("sn", usr.getLastname());
        op.addAttribute("cn", fullName);
        op.addAttribute("loginid", usr.getUsername());
        op.addAttribute("grp", usr.getGrp());
        op.addAttribute("userPassword", usr.getPassword());
        op.addAttribute("dob", dob);
        op.addAttribute("jt", usr.getJobTitleDescription());
        op.addAttribute("ssn", usr.getSsn());
        op.addAttribute("startDate", startDate);

        // Handle optional data
        if (usr.getAddress() != null) {
            op.addAttribute("addr", usr.getAddress());
        }
        if (usr.getCity() != null) {
            op.addAttribute("city", usr.getCity());
        }
        if (usr.getCountry() != null) {
            op.addAttribute("country", usr.getCountry());
        }
        if (usr.getUserDescription() != null) {
            op.addAttribute("description", usr.getUserDescription());
        }
        if (usr.getEmail() != null) {
            op.addAttribute("email", usr.getEmail());
        }
        if (usr.getCountry() != null) {
            op.addAttribute("country", usr.getCountry());
        }
        if (usr.getFax() != null) {
            op.addAttribute("phoneFax", usr.getFax());
        }
        if (usr.getHomePhone() != null) {
            op.addAttribute("phoneHome", usr.getHomePhone());
        }
        if (usr.getMobilePhone() != null) {
            op.addAttribute("phoneMobile", usr.getMobilePhone());
        }
        if (usr.getOfficePhone() != null) {
            op.addAttribute("phoneOffice", usr.getOfficePhone());
        }
        if (usr.getOtherPhone() != null) {
            op.addAttribute("phoneOther", usr.getOtherPhone());
        }
        if (usr.getPager() != null) {
            op.addAttribute("phonePager", usr.getPager());
        }
        if (usr.getCountry() != null) {
            op.addAttribute("country", usr.getCountry());
        }
        if (usr.getTitleName() != null) {
            op.addAttribute("pt", usr.getTitleName());
        }
        if (usr.getState() != null) {
            op.addAttribute("state", usr.getState());
        }
        if (usr.getZip() != null) {
            op.addAttribute("zip", usr.getZip());
        }
        op.addAttribute("lc", "0");
        op.addAttribute("loggedIn", "0");

        // Handle roles, if any.
        if (usr.getRoles() != null) {
            for (String role : usr.getRoles()) {
                op.addListAttribute("ar", role, LdapClient.MOD_OPERATION_ADD);
            }
        }

        // Add user profile to LDAP server
        this.ldap.insertRow(op, false);
        return;
    }

    /**
     * Updates a user profile at DN,
     * <i>loginid=xxx,ou=People,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * A special note should be made regarding the user's roles. Each update
     * will replace all replace role entries belonging to the attribute,
     * <i>ar</i>, with the current state of <i>usr<i/>'s property, roles.
     * 
     * @param usr
     *            The user profile as a DTO
     * @throws DatabaseException
     */
    private void updateUser(UserDto usr) throws DatabaseException {
        LdapModifyOperation op = new LdapModifyOperation();
        // Handle required fields
        String dn = "loginid=" + usr.getUsername() + ",ou=People";
        String fullName = usr.getFirstname() + " " + usr.getLastname();
        String dob = RMT2Date.formatDate(usr.getBirthDate(), "MM/dd/yyyy");
        String startDate = RMT2Date
                .formatDate(usr.getStartDate(), "MM/dd/yyyy");
        op.setDn(dn);
        op.addAttribute("fn", usr.getFirstname(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("sn", usr.getLastname(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("cn", fullName, LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("loginid", usr.getUsername(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("grp", usr.getGrp(), LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("dob", dob, LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("jt", usr.getJobTitleDescription(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("ssn", usr.getSsn(), LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("startDate", startDate,
                LdapClient.MOD_OPERATION_REPLACE);

        // Only update password when available
        if (usr.getPassword() != null) {
            op.addAttribute("userPassword", usr.getPassword(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }

        // Handle optional data
        if (usr.getAddress() != null) {
            op.addAttribute("addr", usr.getAddress(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getCity() != null) {
            op.addAttribute("city", usr.getCity(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getCountry() != null) {
            op.addAttribute("country", usr.getCountry(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getUserDescription() != null) {
            op.addAttribute("description", usr.getUserDescription(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getEmail() != null) {
            op.addAttribute("email", usr.getEmail(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getCountry() != null) {
            op.addAttribute("country", usr.getCountry(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getFax() != null) {
            op.addAttribute("phoneFax", usr.getFax(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getHomePhone() != null) {
            op.addAttribute("phoneHome", usr.getHomePhone(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getMobilePhone() != null) {
            op.addAttribute("phoneMobile", usr.getMobilePhone(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getOfficePhone() != null) {
            op.addAttribute("phoneOffice", usr.getOfficePhone(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getOtherPhone() != null) {
            op.addAttribute("phoneOther", usr.getOtherPhone(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getPager() != null) {
            op.addAttribute("phonePager", usr.getPager(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getCountry() != null) {
            op.addAttribute("country", usr.getCountry(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getTitleName() != null) {
            op.addAttribute("pt", usr.getTitleName(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getState() != null) {
            op.addAttribute("state", usr.getState(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (usr.getZip() != null) {
            op.addAttribute("zip", usr.getZip(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        op.addAttribute("lc", String.valueOf(usr.getTotalLogons()),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("loggedIn", String.valueOf(usr.getLoggedIn()),
                LdapClient.MOD_OPERATION_REPLACE);

        // Handle roles, if any.
        if (usr.getRoles() != null) {
            for (String role : usr.getRoles()) {
                op.addListAttribute("ar", role,
                        LdapClient.MOD_OPERATION_REPLACE);
            }
        }

        // Apply updates to the LDAP server
        this.ldap.updateRow(op);
        return;
    }

    /**
     * Validates a user profile reprsented as <i>user</i>.
     * 
     * @param user
     *            the user instance to validate
     * @throws InvalidUserInstanceException
     *             <ol>
     *             <li>When <i>user</i> is null</li>
     *             <li>The login id is null</li>
     *             <li>The first name is null</li>
     *             <li>The last name is null</li>
     *             <li>The group name is less than or equal to zero</li>
     *             <li>Birth date is null</li>
     *             <li>Job title is null</li>
     *             <li>Birth social security number is null</li>
     *             <li>Start date is null</li>
     *             </ol>
     */
    protected void validateUser(UserDto user)
            throws InvalidUserInstanceException {
        if (user == null) {
            throw new InvalidUserInstanceException(
                    "User DTO object is required");
        }
        if (user.getUsername() == null) {
            throw new InvalidUserInstanceException("Username is required");
        }
        if (user.getFirstname() == null) {
            throw new InvalidUserInstanceException(
                    "User first name is required");
        }
        if (user.getLastname() == null) {
            throw new InvalidUserInstanceException("User last name is required");
        }
        if (user.getGrp() == null) {
            throw new InvalidUserInstanceException("Group name is required");
        }
        if (user.getBirthDate() == null) {
            throw new InvalidUserInstanceException("Birth date is required");
        }
        if (user.getJobTitleDescription() == null) {
            throw new InvalidUserInstanceException("Job title is required");
        }
        if (user.getSsn() == null) {
            throw new InvalidUserInstanceException(
                    "Social security number is required");
        }
        if (user.getStartDate() == null) {
            throw new InvalidUserInstanceException("Start date is required");
        }

        // Validate roles, if exist.
        if (user.getRoles() != null) {
            try {
                RoleDaoFactory rf = new RoleDaoFactory();
                RoleDao rDao = rf.createLdapDao();
                LdapAppRoles dummy = null;
                CategoryDto criteria = LdapDtoFactory
                        .getApplicationRoleDtoInstance(dummy);
                List<CategoryDto> results = null;
                for (String role : user.getRoles()) {
                    criteria.setAppRoleCode(role);
                    results = rDao.fetchAppRole(criteria);
                    if (results == null) {
                        this.msg = "Application role is invalid: " + role;
                        throw new InvalidUserInstanceException(this.msg);
                    }
                }
            } catch (SecurityDaoException e) {
                throw new UserDaoException(e);
            }
        }
        return;
    }

    /**
     * Verifies that the new user's profile data is valid.
     * 
     * @param user
     *            an instance of {@link UserDto}
     * @throws InvalidUserInstanceException
     *             <ol>
     *             <li>When <i>user</i> is null</li>
     *             <li>The password is null</li>
     *             <li></li>
     *             </ol>
     */
    protected void validateNewUser(UserDto user)
            throws InvalidUserInstanceException {
        if (user == null) {
            throw new InvalidUserInstanceException(
                    "User DTO object is required for new user");
        }
        if (user.getPassword() == null) {
            throw new InvalidUserInstanceException("User password is required");
        }
        return;
    }

    /**
     * Delete a user using user name at DN,
     * <i>loginid=xxx,ou=People,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param userName
     *            the user's login id.
     * @return the total number of rows effected.
     * @throws UserDaoException
     */
    public int deleteUser(String userName) throws UserDaoException {
        if (userName == null) {
            this.msg = "User's login id (user name) is required for delete operation";
            throw new UserDaoException(this.msg);
        }
        String dn = "loginid=" + userName + ",ou=People";
        LdapDeleteOperation op = new LdapDeleteOperation();
        op.setDn(dn);
        return this.ldap.deleteRow(op);
    }

    /**
     * Create or modify a user group.
     * <p>
     * Changes are persisted at DN,
     * <i>cn=xxx,ou=UserGroups,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param grp
     *            an instance of {@link UserDto} containing the group data.
     * @return int Always return <i>1</i>.
     * @throws UserDaoException
     */
    @Override
    public int maintainGroup(UserDto grp) throws UserDaoException {
        this.validateGroup(grp);

        // Determine if this is a new or existing user
        boolean newGroup = (this.fetchGroup(grp.getGrp()) == null);
        if (newGroup) {
            this.insertGroup(grp);
        }
        else {
            this.updateGroup(grp);
        }
        return 1;
    }

    /**
     * Insert a user group at DN,
     * <i>cn=xxx,ou=UserGroups,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param grp
     *            The user group profile as a DTO
     * @throws DatabaseException
     */
    private void insertGroup(UserDto grp) throws DatabaseException {
        LdapAddOperation op = new LdapAddOperation();
        // Handle required fields
        String dn = "cn=" + grp.getGrp() + ",ou=UserGroups,ou=LookupCodes";
        op.setDn(dn);
        op.addListAttribute("objectClass", "RMT2UserGroups");
        op.addListAttribute("objectClass", "top");
        op.addAttribute("cn", grp.getGrp());
        op.addAttribute("description", grp.getGrpDescription());

        // Add user group profile to LDAP server
        this.ldap.insertRow(op, false);
        return;
    }

    /**
     * Update an existing user group at DN,
     * <i>cn=xxx,ou=UserGroups,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param grp
     *            The user group profile as a DTO
     * @throws DatabaseException
     */
    private void updateGroup(UserDto grp) throws DatabaseException {
        LdapModifyOperation op = new LdapModifyOperation();
        // Handle required fields
        String dn = "cn=" + grp.getGrp() + ",ou=UserGroups,ou=LookupCodes";
        op.setDn(dn);
        op.addAttribute("cn", grp.getGrp(), LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("description", grp.getGrpDescription(),
                LdapClient.MOD_OPERATION_REPLACE);

        // Apply updates to the LDAP server
        this.ldap.updateRow(op);
        return;
    }

    /**
     * Deletes a user group at DN,
     * <i>cn=xxx,ou=UserGroups,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param grpName
     *            The group name
     * @return an int value acting as the total number of targets affected.
     * @throws UserDaoException
     */
    public int deleteGroup(String grpName) throws UserDaoException {
        if (grpName == null) {
            this.msg = "User gropup's name is required for delete operation";
            throw new UserDaoException(this.msg);
        }
        String dn = "cn=" + grpName + ",ou=UserGroups,ou=LookupCodes";
        LdapDeleteOperation op = new LdapDeleteOperation();
        op.setDn(dn);
        return this.ldap.deleteRow(op);
    }

    /**
     * Validates a user profile reprsented as <i>user</i>.
     * 
     * @param grp
     *            the user group instance to validate
     * @throws InvalidUserInstanceException
     *             <ol>
     *             <li>When <i>grp</i> is null</li>
     *             <li>The group name is null</li>
     *             <li>The group description is null</li>
     *             </ol>
     */
    protected void validateGroup(UserDto grp)
            throws InvalidUserInstanceException {
        if (grp == null) {
            throw new InvalidUserInstanceException(
                    "User Group DTO object is required to be valid");
        }
        if (grp.getGrp() == null) {
            throw new InvalidUserInstanceException(
                    "User group name is required");
        }
        if (grp.getGrpDescription() == null) {
            throw new InvalidUserInstanceException(
                    "User group description is required");
        }
        return;
    }

    /**
     * Not supported.
     */

    public UserDto fetchUser(int uid) throws UserDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    @Override
    public int deleteUser(int uid) throws UserDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    @Override
    public int activateUser(String userName) throws UserDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    @Override
    public int inActivateUser(String userName) throws UserDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    public UserDto fetchGroup(int grpId) throws UserDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    @Override
    public int deleteGroup(int grpId) throws UserDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public List<UserDto> fetchGroup(UserDto group) throws UserDaoException {
        // TODO Auto-generated method stub
        return null;
    }
}
