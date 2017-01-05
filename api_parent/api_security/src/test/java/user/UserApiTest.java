package user;

import java.util.List;

import org.dao.mapping.orm.ldap.LdapUser;
import org.dao.mapping.orm.ldap.LdapUserGroup;
import org.dto.UserDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modules.users.UserApi;
import org.modules.users.UserApiException;
import org.modules.users.UserGroupMaintApiFactory;

import com.util.RMT2Date;

/**
 * Tests the {@link UserApi} interface.
 * 
 * @author rterrell
 * 
 */
public class UserApiTest {
    private UserGroupMaintApiFactory f;

    private UserApi api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new UserGroupMaintApiFactory();
        this.api = this.f.createApi();
        this.api.setApiUser("testuser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.api = null;
        this.f = null;
    }

    @Test
    public void fetchAllUsers() {
        List<UserDto> results = null;
        try {
            results = this.api.getUser();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void fetchUsersByUid() {
        UserDto results = null;
        try {
            results = this.api.getUser(1);
        } catch (UnsupportedOperationException e) {
            try {
                results = this.api.getUser("admin");
            } catch (Exception ee) {
                ee.printStackTrace();
                Assert.fail(ee.getMessage());
            }
        } catch (UserApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(results);
        Assert.assertEquals("admin", results.getUsername());
    }

    @Test
    public void fetchUsersByLoginId() {
        UserDto results = null;
        try {
            results = this.api.getUser("admin");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(results);
        Assert.assertEquals("admin", results.getUsername());
    }

    @Test
    public void fetchUsersByDto() {
        UserDto dto = Rmt2OrmDtoFactory.getNewExtUserInstance();
        dto.setLoginUid(1);
        String loginId = "admin";
        UserDto results = null;
        try {
            results = this.api.getUser(loginId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(results);
        Assert.assertEquals(loginId, results.getUsername());
    }

    @Test
    public void addUser() {
        // Setup new user
        LdapUser dummy = null;
        UserDto u = LdapDtoFactory.getUserDtoInstance(dummy);
        // UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(1);
        u.setGrp("Internal");
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setJobTitleDescription("Test Job Title");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        int rc = 0;
        try {
            rc = this.api.updateUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue(rc > 0);

        // Delete User
        try {
            rc = this.api.deleteUser(u.getLoginUid());
        } catch (UnsupportedOperationException e) {
            try {
                rc = this.api.deleteUser("fgood");
            } catch (Exception ee) {
                ee.printStackTrace();
                Assert.fail(ee.getMessage());
            }
        } catch (UserApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
    }

    @Test
    public void updateUser() {
        // Setup new user
        LdapUser dummy = null;
        UserDto u = LdapDtoFactory.getUserDtoInstance(dummy);
        // UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();

        u.setLoginUid(0);
        u.setGroupId(1);
        u.setGrp("Internal");
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setJobTitleDescription("Test Job Title");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        int rc = 0;
        int newId = 0;
        try {
            rc = this.api.updateUser(u);
            newId = rc;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);

        // Update user
        String newSsn = "555-55-5555";
        int newTotalLogons = 5;
        try {
            u.setSsn(newSsn);
            u.setTotalLogons(newTotalLogons);
            rc = this.api.updateUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);

        // Verify update
        UserDto u2 = null;
        try {
            u2 = this.api.getUser(newId);
        } catch (UnsupportedOperationException e) {
            try {
                u2 = this.api.getUser(u.getUsername());
            } catch (Exception ee) {
                ee.printStackTrace();
                Assert.fail(ee.getMessage());
            }
        } catch (UserApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newSsn, u2.getSsn());
        Assert.assertEquals(newTotalLogons, u.getTotalLogons());

        // Delete User
        try {
            rc = this.api.deleteUser(newId);
        } catch (UnsupportedOperationException e) {
            try {
                rc = this.api.deleteUser(u.getUsername());
            } catch (Exception ee) {
                ee.printStackTrace();
                Assert.fail(ee.getMessage());
            }
        } catch (UserApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
    }

    @Test
    public void fetchAllGroups() {
        List<UserDto> results = null;
        try {
            results = this.api.getGroup();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void fetchGroupById() {
        UserDto results = null;
        try {
            results = this.api.getGroup(1);
        } catch (UnsupportedOperationException e) {
            try {
                results = this.api.getGroup("Internal");
            } catch (Exception ee) {
                ee.printStackTrace();
                Assert.fail(ee.getMessage());
            }
        } catch (UserApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(results);
        Assert.assertEquals("Internal", results.getGrp());
    }

    @Test
    public void addGroup() {
        // Setup new group
        LdapUserGroup dummy = null;
        UserDto u = LdapDtoFactory.getUserDtoInstance(dummy);
        // UserDto u = Rmt2OrmDtoFactory.getNewGroupInstance();
        String grpName = "Test Name";
        u.setGrp(grpName);
        u.setGrpDescription("Test Description");
        u.setUpdateUserId("testuser");

        int rc = 0;
        try {
            rc = this.api.updateGroup(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue(rc > 0);

        // Delete User
        try {
            rc = this.api.deleteGroup(u.getGroupId());
        } catch (UnsupportedOperationException e) {
            try {
                rc = this.api.deleteGroup(grpName);
            } catch (Exception ee) {
                ee.printStackTrace();
                Assert.fail(ee.getMessage());
            }
        } catch (UserApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
    }

    @Test
    public void updateGroup() {
        // Setup new group
        LdapUserGroup dummy = null;
        UserDto u = LdapDtoFactory.getUserDtoInstance(dummy);
        // UserDto u = Rmt2OrmDtoFactory.getNewGroupInstance();
        String grpName = "Test Name";
        u.setGrp(grpName);
        u.setGrpDescription("Test Description");
        u.setUpdateUserId("testuser");

        int rc = 0;
        int newId = 0;
        try {
            rc = this.api.updateGroup(u);
            newId = rc;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue(rc > 0);

        // Update Group
        String desc = "Modified Description";
        try {
            u.setGrpDescription(desc);
            rc = this.api.updateGroup(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);

        // Verify update
        UserDto u2 = null;
        try {
            u2 = this.api.getGroup(newId);
        } catch (UnsupportedOperationException e) {
            try {
                u2 = this.api.getGroup(u.getGrp());
            } catch (Exception ee) {
                ee.printStackTrace();
                Assert.fail(ee.getMessage());
            }
        } catch (UserApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(desc, u2.getGrpDescription());

        // Delete User
        try {
            rc = this.api.deleteGroup(newId);
        } catch (UnsupportedOperationException e) {
            try {
                rc = this.api.deleteGroup(u.getGrp());
            } catch (Exception ee) {
                ee.printStackTrace();
                Assert.fail(ee.getMessage());
            }
        } catch (UserApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
    }

}
