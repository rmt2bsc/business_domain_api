package old.tests.role;

import java.util.List;

import junit.framework.Assert;

import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoException;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the database implementation of {@link RoleDao}
 * 
 * @author rterrell
 * 
 */
public class Rmt2OrmRoleDaoTest {

    private RoleDaoFactory f;

    private RoleDao dao;

    /**
     * Creates the Role DAO factory and creates an instance of {@link RoleDao}
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new RoleDaoFactory();
        this.dao = f.createRmt2OrmDao();
        this.dao.setDaoUser("testuser");
    }

    /**
     * Releases the DAO resources as needed.
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    /**
     * Test fetching all roles from the database.
     */
    @Test
    public void getAllRoles() {
        Object results = null;
        try {
            results = this.dao.fetchRole();
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results instanceof List);
        Assert.assertTrue(((List<Object>) results).size() > 0);
    }

    /**
     * Test fetching single role by primary key
     */
    @Test
    public void getSingleRoleById() {
        Object results = null;
        try {
            results = this.dao.fetchRole(1);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
    }

    /**
     * Test fetching single role by primary key that does not exist
     */
    @Test
    public void handleSingleRoleByIdNotFound() {
        Object results = null;
        try {
            results = this.dao.fetchRole(-1);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNull(results);
    }

    /**
     * Test fetching all application roles
     */
    @Test
    public void getAllAppRoles() {
        Object results = null;
        try {
            results = this.dao.fetchAppRole();
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results instanceof List);
        Assert.assertTrue(((List<Object>) results).size() > 0);
    }

    /**
     * Test fetching single application role by primary key
     */
    @Test
    public void getAppRoleById() {
        Object results = null;
        try {
            results = this.dao.fetchAppRole(1);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
    }

    // /**
    // * Test fetching a list of roles belonging to a given user
    // */
    // @Test
    // public void getAppRoleByUser() {
    // Object results = null;
    // try {
    // results = this.dao.fetchAppRole("admin");
    // }
    // catch (RoleDaoException e) {
    // e.printStackTrace();
    // return;
    // }
    // Assert.assertNotNull(results);
    // Assert.assertTrue(results instanceof List);
    // Assert.assertTrue(((List<Object>) results).size() > 0);
    // }
    //
    // /**
    // * Test fetching a list of roles by user and application name
    // */
    // @Test
    // public void getAppRoleByUserAndAppName() {
    // Object results = null;
    // try {
    // results = this.dao.fetchAppRole("admin", "authentication");
    // }
    // catch (RoleDaoException e) {
    // e.printStackTrace();
    // return;
    // }
    // Assert.assertNotNull(results);
    // Assert.assertTrue(results instanceof List);
    // Assert.assertTrue(((List<Object>) results).size() > 0);
    // }
    //
    // /**
    // * Test fetching a list of roles by user and an invalid application name.
    // */
    // @Test
    // public void handleAppRoleByUserAndAppNameNotFound() {
    // Object results = null;
    // try {
    // results = this.dao.fetchAppRole("admin", "invalid_app");
    // }
    // catch (RoleDaoException e) {
    // e.printStackTrace();
    // return;
    // }
    // Assert.assertNull(results);
    // }

    /**
     * Test fetching a list of application roles by application id.
     * <p>
     * The application id is required to be included in an instance of
     * {@link CategoryDto}
     */
    @Test
    public void getAppRoleByApplicationIdUsingDTO() {
        CategoryDto dto = Rmt2OrmDtoFactory
                .getNewExtAppRoleCategoryInstance(null);
        dto.setApplicationId(10);
        Object results = null;
        try {
            results = this.dao.fetchAppRole(dto);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results instanceof List);
        Assert.assertTrue(((List<Object>) results).size() > 0);
    }

    /**
     * Test fetching a list of application roles that are granted to a given
     * user
     */
    @Test
    public void getAssignedAppRolesForUser() {
        CategoryDto dto = Rmt2OrmDtoFactory
                .getNewExtUserAppRoleCategoryInstance(null);
        dto.setUsername("admin");
        Object results = null;
        try {
            results = this.dao.fetchUserAssignedRoles(dto);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results instanceof List);
        Assert.assertTrue(((List<Object>) results).size() > 0);
    }

    /**
     * Test fetching a list of revoked application roles for a given user
     */
    @Test
    public void getRevokedAppRolesForUser() {
        CategoryDto dto = Rmt2OrmDtoFactory
                .getNewExtAppRoleCategoryInstance(null);
        dto.setLoginUid(1); // Admin
        Object results = null;
        try {
            this.dao.setDaoUser(null);
            results = this.dao.fetchUserRevokedRoles(dto);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results instanceof List);
        Assert.assertTrue(((List<Object>) results).size() > 0);
    }

    /**
     * Test fetching a list of application roles belonging to a user.
     */
    @Test
    public void getUserApplications() {
        Object results = null;
        try {
            results = this.dao.fetchUserAppRole("admin");
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results instanceof List);
        Assert.assertTrue(((List<Object>) results).size() > 0);
    }

    /**
     * Test adding a new role to the database
     */
    @Test
    public void createRole() {
        // Create record
        CategoryDto dto = Rmt2OrmDtoFactory.getNewRoleCategoryInstance();
        dto.setRoleId(0);
        dto.setRoleName("TestRole");
        dto.setRoleDescription("This is a JUnit test");
        dto.setUpdateUserId("testuser");

        int newId;
        try {
            newId = this.dao.maintainRole(dto);
            Assert.assertTrue(newId > 0);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Delete record
        int rows = 0;
        try {
            rows = this.dao.deleteRole(newId);
            Assert.assertEquals(1, rows);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Test modifying an existing record in the database
     */
    @Test
    public void modifyRole() {
        // Create record
        CategoryDto dto = Rmt2OrmDtoFactory.getNewRoleCategoryInstance();
        dto.setRoleId(0);
        dto.setRoleName("TestRole");
        dto.setRoleDescription("This is a JUnit test");
        dto.setUpdateUserId("testuser");

        int newId;
        try {
            newId = this.dao.maintainRole(dto);
            Assert.assertTrue(newId > 0);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Modify Role
        int rows = 0;
        String modRoleName = "TestRole2";
        try {
            dto.setRoleName(modRoleName);
            dto.setRoleDescription("This is a modified JUnit Test");
            rows = this.dao.maintainRole(dto);
            Assert.assertEquals(1, rows);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Verify that record was modified
        CategoryDto dto2 = null;
        try {
            dto2 = this.dao.fetchRole(newId);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(modRoleName, dto2.getRoleName());

        // Delete record
        try {
            rows = this.dao.deleteRole(newId);
            Assert.assertEquals(1, rows);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createAppRole() {
        // Create record
        CategoryDto dto = Rmt2OrmDtoFactory.getNewAppRoleCategoryInstance();
        dto.setAppRoleId(0);
        dto.setApplicationId(8);
        dto.setRoleId(20);
        dto.setAppRoleCode("testcode");
        dto.setAppRoleName("TestAppRole");
        dto.setAppRoleDescription("This is a JUnit test");
        dto.setUpdateUserId("testuser");

        int newId;
        try {
            newId = this.dao.maintainAppRole(dto);
            Assert.assertTrue(newId > 0);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Delete record
        int rows = 0;
        try {
            rows = this.dao.deleteAppRole(newId);
            Assert.assertEquals(1, rows);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Test modifying an existing application role an persiting the changes to
     * the database.
     */
    @Test
    public void modifyAppRole() {
        // Create record
        CategoryDto dto = Rmt2OrmDtoFactory.getNewAppRoleCategoryInstance();
        dto.setAppRoleId(0);
        dto.setApplicationId(8);
        dto.setRoleId(20);
        dto.setAppRoleCode("testcode");
        dto.setAppRoleName("TestAppRole");
        dto.setAppRoleDescription("This is a JUnit test");
        dto.setUpdateUserId("testuser");

        int newId;
        try {
            newId = this.dao.maintainAppRole(dto);
            Assert.assertTrue(newId > 0);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Modify Role
        int rows = 0;
        String modAppRoleCode = "testcode2";
        String modAppRoleName = "TestAppRole";
        try {
            dto.setAppRoleCode(modAppRoleCode);
            dto.setAppRoleName(modAppRoleName);
            dto.setAppRoleDescription("This is a modified JUnit Test");
            rows = this.dao.maintainAppRole(dto);
            Assert.assertEquals(1, rows);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Verify that record was modified
        CategoryDto dto2 = null;
        try {
            dto2 = this.dao.fetchAppRole(newId);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(modAppRoleCode, dto2.getAppRoleCode());
        Assert.assertEquals(modAppRoleName, dto2.getAppRoleName());

        // Delete record
        try {
            rows = this.dao.deleteAppRole(newId);
            Assert.assertEquals(1, rows);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Test assigning a set of new application roles to a user
     */
    @Test
    public void assigneApplicationRolesToUser() {
        String userName = "user6";
        int appId = 10;
        String roles[] = { "15", "16" }; // rmt2quest, and rmt2client
        int rows = 0;

        CategoryDto dto = Rmt2OrmDtoFactory
                .getNewExtUserAppRoleCategoryInstance("admin");
        dto.setUsername(userName);
        dto.setApplicationId(appId);
        try {
            rows = this.dao.maintainUserAppRole(dto, roles, null);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Assert.assertTrue(rows > 0);

        // Delete entries just created
        try {
            rows = this.dao.deleteUserAppRoles(userName, roles);
        } catch (RoleDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertTrue(rows > 0);
    }

}
