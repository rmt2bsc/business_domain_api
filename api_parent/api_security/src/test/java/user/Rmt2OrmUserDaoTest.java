package user;

import java.util.List;

import junit.framework.Assert;

import org.dao.user.UserDao;
import org.dao.user.UserDaoException;
import org.dao.user.UserDaoFactory;
import org.dto.UserDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.util.RMT2Date;

/**
 * Tests the User DAO
 * 
 * @author rterrell
 * 
 */
public class Rmt2OrmUserDaoTest {

    private UserDaoFactory f;

    private UserDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new UserDaoFactory();
        this.dao = this.f.createRmt2OrmDao();
        this.dao.setDaoUser("testuser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    @Test
    public void getAllUserProfiles() {
        List<UserDto> list = null;
        try {
            list = this.dao.fetchUser();
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void getUserProfileByUsername() {
        String loginId = "admin";
        UserDto dto = null;
        try {
            dto = this.dao.fetchUser(loginId);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(loginId, dto.getUsername());
    }

    @Test
    public void getUserProfileByInvalidUsername() {
        String loginId = "billbates";
        UserDto dto = null;
        try {
            dto = this.dao.fetchUser(loginId);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNull(dto);
    }

    @Test
    public void getUserProfileByInternalId() {
        int uid = 1;
        String loginId = "admin";
        UserDto dto = null;
        try {
            dto = this.dao.fetchUser(uid);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(loginId, dto.getUsername());
    }

    @Test
    public void getUserProfileByInternalIdNotExists() {
        int uid = 999999;
        UserDto dto = null;
        try {
            dto = this.dao.fetchUser(uid);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNull(dto);
    }

    @Test
    public void getUserProfileByInvalidInternalId() {
        int loginId = 0;
        try {
            this.dao.fetchUser(loginId);
        } catch (UserDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed...expecting an exception");
    }

    @Test
    public void getUserProfileUsingDtoByInternalId() {
        UserDto dto = Rmt2OrmDtoFactory.getNewExtUserInstance();
        dto.setLoginUid(1);
        String loginId = "admin";
        List<UserDto> list = null;
        try {
            list = this.dao.fetchUser(dto);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
        Assert.assertEquals(loginId, list.get(0).getUsername());
    }

    @Test
    public void getUserProfileUsingDtoByUsername() {
        String userName = "admin";
        int uid = 1;
        UserDto dto = Rmt2OrmDtoFactory.getNewExtUserInstance();
        dto.setUsername(userName);
        List<UserDto> list = null;
        try {
            list = this.dao.fetchUser(dto);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
        Assert.assertEquals(uid, list.get(0).getLoginUid());
    }

    @Test
    public void getUserProfileUsingDtoByGroupId() {
        UserDto dto = Rmt2OrmDtoFactory.getNewExtUserInstance();
        dto.setGroupId(1);
        List<UserDto> list = null;
        try {
            list = this.dao.fetchUser(dto);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 1);
    }

    @Test
    public void getUserProfileUsingDtoByStartDate() {
        UserDto dto = Rmt2OrmDtoFactory.getNewExtUserInstance();
        dto.setStartDate(RMT2Date.stringToDate("2007-08-30"));
        List<UserDto> list = null;
        try {
            list = this.dao.fetchUser(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void getUserProfileUsingDtoByFirstname() {
        UserDto dto = Rmt2OrmDtoFactory.getNewExtUserInstance();
        dto.setFirstname("fn");
        List<UserDto> list = null;
        try {
            list = this.dao.fetchUser(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 1);
    }

    @Test
    public void validateUserInstance() {
        int rc = 0;
        try {
            rc = this.dao.maintainUser(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(rc == 0);
            return;
        }

        Assert.fail("Validation test of user instance failed");
        return;
    }

    @Test
    public void validateInternalUniqueId() {
        int rc = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(-1);
        u.setGroupId(1);
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(rc == 0);
            return;
        }

        Assert.fail("Validation test of user login id failed");
        return;
    }

    @Test
    public void validateGroupId() {
        int rc = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(0);
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(rc == 0);
            return;
        }

        Assert.fail("Validation test of user login id failed");
        return;
    }

    @Test
    public void validateUserName() {
        int rc = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(1);
        u.setUsername(null);
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(rc == 0);
            return;
        }

        Assert.fail("Validation test of user login id failed");
        return;
    }

    @Test
    public void validateFirstName() {
        int rc = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(1);
        u.setUsername("fgood");
        u.setFirstname(null);
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(rc == 0);
            return;
        }

        Assert.fail("Validation test of user login id failed");
        return;
    }

    @Test
    public void validateLastName() {
        int rc = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(1);
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname(null);
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(rc == 0);
            return;
        }

        Assert.fail("Validation test of user login id failed");
        return;
    }

    @Test
    public void validatePassword() {
        int rc = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(1);
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword(null);

        try {
            rc = this.dao.maintainUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(rc == 0);
            return;
        }

        Assert.fail("Validation test of user login id failed");
        return;
    }

    @Test
    public void addUser() {
        int rc = 0;
        int newId = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(1);
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(u);
            newId = rc;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(newId, u.getLoginUid());

        // Delete user
        try {
            rc = this.dao.deleteUser(newId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        return;
    }

    @Test
    public void addDuplicateUser() {
        int rc = 0;
        int newId = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(1);
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(u);
            newId = rc;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(newId, u.getLoginUid());

        // Perform duplication insertion
        rc = 0;
        try {
            u.setLoginUid(0);
            rc = this.dao.maintainUser(u);
        } catch (Exception e) {
            Assert.assertEquals(0, rc);
            return;
        } finally {
            // Delete user
            try {
                rc = this.dao.deleteUser(newId);
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail(e.getMessage());
            }
            Assert.assertTrue(rc > 0);
        }
        Assert.fail("Duplciate user test failed...user of the same name was added unexpectedly");
    }

    @Test
    public void updateUser() {
        int rc = 0;
        int newId = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(1);
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(u);
            newId = rc;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(newId, u.getLoginUid());

        // Update user
        String newSsn = "555-55-5555";
        int newTotalLogons = 5;
        try {
            u.setSsn(newSsn);
            u.setTotalLogons(newTotalLogons);
            rc = this.dao.maintainUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);

        // Verify update
        UserDto u2 = null;
        try {
            u2 = this.dao.fetchUser(newId);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newSsn, u2.getSsn());
        Assert.assertEquals(newTotalLogons, u.getTotalLogons());

        // Delete user
        try {
            rc = this.dao.deleteUser(newId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        return;
    }

    @Test
    public void updateUserPassword() {
        int rc = 0;
        int newId = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewUserInstance();
        u.setLoginUid(0);
        u.setGroupId(1);
        u.setUsername("fgood");
        u.setFirstname("Frank");
        u.setLastname("Good");
        u.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        u.setSsn("444-44-4444");
        u.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        u.setTerminationDate(null);
        u.setUserDescription("This is a test user for JUnit");
        u.setTotalLogons(0);
        u.setEmail("fgood@verizon.net");
        u.setActive(1);
        u.setUpdateUserId("testuser");
        u.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(u);
            newId = rc;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(newId, u.getLoginUid());

        // Update user
        String newPassword = "drum7777";
        try {
            u.setPassword(newPassword);
            rc = this.dao.maintainUser(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);

        // Verify update
        UserDto u2 = null;
        try {
            u2 = this.dao.fetchUser(newId);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newPassword, u2.getPassword());

        // Delete user
        try {
            rc = this.dao.deleteUser(newId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        return;
    }

    @Test
    public void getAllGroups() {
        List<UserDto> list = null;
        try {
            list = this.dao.fetchGroup();
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void getGroupByGroupId() {
        int grpId = 1;
        String desc = "Internal";
        UserDto dto = null;
        try {
            dto = this.dao.fetchGroup(grpId);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(desc, dto.getGrp());
    }

    @Test
    public void addGroup() {
        int rc = 0;
        int newId = 0;

        // Setup new group
        UserDto u = Rmt2OrmDtoFactory.getNewGroupInstance();
        u.setGroupId(0);
        u.setGrp("Test Description");
        u.setUpdateUserId("testuser");

        try {
            rc = this.dao.maintainGroup(u);
            newId = rc;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(newId, u.getGroupId());

        // Delete user
        try {
            rc = this.dao.deleteGroup(newId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);
        return;
    }

    @Test
    public void updateGroup() {
        int rc = 0;
        int newId = 0;

        // Setup new user
        UserDto u = Rmt2OrmDtoFactory.getNewGroupInstance();
        u.setGroupId(0);
        u.setGrp("Test Description");
        u.setUpdateUserId("testuser");

        try {
            rc = this.dao.maintainGroup(u);
            newId = rc;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(newId, u.getGroupId());

        // Update Group
        String desc = "Modified Description";
        try {
            u.setGrp(desc);
            rc = this.dao.maintainGroup(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);

        // Verify update
        UserDto u2 = null;
        try {
            u2 = this.dao.fetchGroup(newId);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(desc, u2.getGrp());

        // Delete user
        try {
            rc = this.dao.deleteGroup(newId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);
        return;
    }

    @Test
    public void getUserProfileUsingDtoByLoggedInFlag() {
        int rc = 0;
        int newId = 0;

        // Setup new user
        UserDto criteria = Rmt2OrmDtoFactory.getNewUserInstance();
        criteria.setLoginUid(0);
        criteria.setGroupId(1);
        criteria.setUsername("fgood");
        criteria.setFirstname("Frank");
        criteria.setLastname("Good");
        criteria.setBirthDate(RMT2Date.stringToDate("04/12/1974"));
        criteria.setSsn("444-44-4444");
        criteria.setStartDate(RMT2Date.stringToDate("03/12/2005"));
        criteria.setTerminationDate(null);
        criteria.setUserDescription("This is a test user for JUnit");
        criteria.setTotalLogons(0);
        criteria.setEmail("fgood@verizon.net");
        criteria.setActive(1);
        criteria.setUpdateUserId("testuser");
        criteria.setPassword("Test1");

        try {
            rc = this.dao.maintainUser(criteria);
            newId = rc;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(newId, criteria.getLoginUid());

        // Update user's logged in status
        try {
            criteria.setLoggedIn(1);
            criteria.setTotalLogons(1);
            rc = this.dao.maintainUser(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);

        // Verify update by querying for the only record with logged in flag set
        // to "1".
        criteria = Rmt2OrmDtoFactory.getNewUserInstance();
        List<UserDto> u2 = null;
        try {
            criteria.setQueryLoggedInFlag(true);
            criteria.setLoggedIn(1);
            u2 = this.dao.fetchUser(criteria);
        } catch (UserDaoException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(u2);
        Assert.assertTrue(u2.size() > 0);
        Assert.assertEquals("fgood", u2.get(0).getUsername());

        // Delete user
        try {
            rc = this.dao.deleteUser(newId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        return;

    }
}
