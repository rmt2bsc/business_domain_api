package role;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.ldap.LdapUser;
import org.dao.user.UserDao;
import org.dao.user.UserDaoFactory;
import org.dto.CategoryDto;
import org.dto.UserDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.SecurityModuleException;
import org.modules.roles.RoleSecurityApiFactory;
import org.modules.roles.UserAppRoleApi;

import com.RMT2RuntimeException;
import com.util.RMT2Date;

import junit.framework.Assert;

/**
 * @author rterrell
 * 
 */
public class UserAppRoleApiTest {

    private RoleSecurityApiFactory f;

    private UserAppRoleApi api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new RoleSecurityApiFactory();
        this.api = this.f.createUserAppRoleApi();
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.api = null;
        this.f = null;
//        this.deleteData();
    }

    private void createData() {
        UserDaoFactory f = new UserDaoFactory();
        UserDao dao = null;
        UserDto dto = null;
        List<String> roles = null;

        // Created User in DB
        dto = Rmt2OrmDtoFactory.getNewUserInstance();
        dto.setLoginUid(0);
        dto.setGroupId(1);
        dto.setUsername("testuser1");
        dto.setFirstname("Halle");
        dto.setLastname("Berry");
        dto.setBirthDate(RMT2Date.stringToDate("12/14/1979"));
        dto.setStartDate(RMT2Date.stringToDate("03/01/2002"));
        dto.setSsn("123-22-4958");
        dto.setUserDescription("This is test user #1");
        dto.setHomePhone("(214) 546-8374");
        dto.setMobilePhone("(318) 344-4948");
        dto.setTitleName("Mr");
        dto.setPassword("test1234");
        dto.setUpdateUserId("testuser");

        // Add roles to the DTO despite the fact that user roles are ignored in
        // the call to maintainUser() method of the RMT2 ORm Implementation.
        roles = new ArrayList<String>();
        roles.add("authadmin");
        roles.add("rmt2admin");
        dto.setRoles(roles);

        dao = f.createRmt2OrmDao();
        dao.setDaoUser("testuser");
        try {
            dao.maintainUser(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RMT2RuntimeException(e);
        } finally {
            dao.close();
            dao = null;
        }

        // create user in LDAP repository
        LdapUser dummy = null;
        dto = LdapDtoFactory.getUserDtoInstance(dummy);
        dto.setUsername("testuser1");
        dto.setFirstname("Halle");
        dto.setLastname("Berry");
        dto.setBirthDate(RMT2Date.stringToDate("12/14/1979"));
        dto.setStartDate(RMT2Date.stringToDate("03/01/2002"));
        dto.setGrp("Internal");
        dto.setSsn("123-22-4958");
        dto.setJobTitleDescription("Computer Analyst");
        dto.setAddress("9432 Live Oak Blvd.");
        dto.setCity("Dallas");
        dto.setState("TX");
        dto.setZip("75232");
        dto.setCountry("USA");
        dto.setEmail("danfouts@gte.net");
        dto.setUserDescription("This is test user #1");
        dto.setHomePhone("(214) 546-8374");
        dto.setMobilePhone("(318) 344-4948");
        dto.setTitleName("Mr");
        dto.setPassword("test1234");

        // Add roles to the DTO. The LDAP implementation recognizes user roles
        // when method, maintainUser(), is invoked.
        roles = new ArrayList<String>();
        roles.add("authadmin");
        roles.add("rmt2admin");
        dto.setRoles(roles);
        dao = f.createLdapDao();
        try {
            dao.maintainUser(dto);
        } catch (Exception e) {
            throw new RMT2RuntimeException(e);
        } finally {
            dao.close();
            dao = null;
        }
    }

//    private void deleteData() {
//        UserDaoFactory f = new UserDaoFactory();
//        UserDao dao = f.createLdapDao();
//
//        // Delete user from DB
//        dao = f.createRmt2OrmDao();
//        try {
//            dao.deleteUser("testuser1");
//        } catch (Exception e) {
//            throw new RMT2RuntimeException(e);
//        } finally {
//            dao.close();
//            dao = null;
//        }
//        // Delete user from LDAP
//        dao = f.createLdapDao();
//        try {
//            dao.deleteUser("testuser1");
//        } catch (Exception e) {
//            throw new RMT2RuntimeException(e);
//        } finally {
//            dao.close();
//            dao = null;
//        }
//
//    }

    /**
     * Test fetch all application roles belonging to a particular user.
     */
    @Test
    public void getAllAppRolesByUser() {
        List<CategoryDto> list = null;
        try {
            list = api.get("admin");
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void getAppRolesAssignedToUser() {
        List<CategoryDto> list = null;
        try {
            CategoryDto criteria = Rmt2OrmDtoFactory
                    .getNewExtUserAppRoleCategoryInstance(null);
            criteria.setUsername("admin");
            list = api.getAssignedRoles(criteria);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    /**
     * Test fetching user assigned roles based on a particular application.
     */
    @Test
    public void getAppRolesAssignedToUserByApplication() {
        List<CategoryDto> list = null;
        try {
            CategoryDto criteria = Rmt2OrmDtoFactory
                    .getNewExtUserAppRoleCategoryInstance(null);
            criteria.setUsername("admin");
            // criteria.setAppName("rmt2"); // RMT2 Application
            criteria.setAppName("projecttracker"); // Project Tracker
            list = api.getAssignedRoles(criteria);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    /**
     * Test missing user name for fetching user assigned roles based on a
     * particular application.
     */
    @Test
    public void validateRoleAssignmentMissingUserCriteria() {
        boolean error = false;
        try {
            CategoryDto criteria = Rmt2OrmDtoFactory
                    .getNewExtUserAppRoleCategoryInstance(null);
            criteria.setApplicationId(10); // RMT2 Application
            api.getAssignedRoles(criteria);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            error = true;
        }
        Assert.assertTrue(error);
    }

    /**
     * Test fetching user revoked roles based on a given application.
     */
    @Test
    public void getAppRolesRevokedFromUser() {
        List<CategoryDto> list = null;
        try {
            CategoryDto criteria = Rmt2OrmDtoFactory
                    .getNewExtUserAppRoleCategoryInstance(null);
            // Set values for RMT2 ORM implementaion
            criteria.setLoginUid(1);
            criteria.setApplicationId(10); // RMT2 Application

            // Set values for LDAP implementation
            criteria.setUsername("admin");
            criteria.setAppName("rmt2");
            list = api.getRevokedRoles(criteria);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    /**
     * Test missing user name for fetching user assigned roles based on a
     * particular application.
     */
    @Test
    public void validateRoleRevocationMissingUserCriteria() {
        boolean error = false;
        try {
            CategoryDto criteria = Rmt2OrmDtoFactory
                    .getNewExtUserAppRoleCategoryInstance(null);
            criteria.setApplicationId(10); // RMT2 Application
            api.getRevokedRoles(criteria);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            error = true;
        }
        Assert.assertTrue(error);
    }

    @Test
    public void assignNewRoleSetToUser() {
        String userName = "testuser1";
        int appId = 10; // RMT2 Application
        String appName = "rmt2";
        List<String> roles = new ArrayList<String>();
        // roles.add("15"); // rmt2quest
        // roles.add("16"); // rmt2client
        roles.add("rmt2guest");
        roles.add("rmt2client");
        roles.add("rmt2admin");

        int rc = 0;

        try {
            CategoryDto criteria = Rmt2OrmDtoFactory
                    .getNewExtUserAppRoleCategoryInstance(null);
            criteria.setUsername(userName);
            criteria.setApplicationId(appId);
            criteria.setAppName(appName);
            criteria.setUpdateUserId("rterrell");
            rc = api.update(criteria, roles);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertTrue(rc > 0);

        try {
            rc = api.delete(userName, roles);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertTrue(rc > 0);
    }
}
