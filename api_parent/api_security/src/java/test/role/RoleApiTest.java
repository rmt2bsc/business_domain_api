package role;

import java.util.List;

import junit.framework.Assert;

import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.SecurityConstants;
import org.modules.SecurityModuleException;
import org.modules.roles.RoleApi;
import org.modules.roles.RoleSecurityApiFactory;

/**
 * JUnint test case for testing the {@link RoleApi} implementation.
 * 
 * @author rterrell
 * 
 */
public class RoleApiTest {

    private RoleSecurityApiFactory f;

    private String userId;

    /**
     * Creates the Role API factory
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new RoleSecurityApiFactory();
        this.userId = "testuser";
    }

    /**
     * Releases the Role API Factory.
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.f = null;
    }

    /**
     * Test api to fetch all roles
     */
    @Test
    public void fetchAllRoles() {
        RoleApi api = this.f.createRoleApi();
        List<CategoryDto> list;
        try {
            list = api.get();
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    /**
     * Test api to fetch a single role.
     */
    @Test
    public void fetchSingleRole() {
        RoleApi api = this.f.createRoleApi();
        CategoryDto dto = null;
        String roleName = "manager";
        try {
            dto = api.get(SecurityConstants.ROLE_ID_MANAGER);
        } catch (UnsupportedOperationException e) {
            try {
                dto = api.get(roleName);
            } catch (SecurityModuleException e1) {
                e1.printStackTrace();
                Assert.fail(e1.getMessage());
            }
        } catch (SecurityModuleException e) {
            try {
                dto = api.get(roleName);
            } catch (SecurityModuleException ee) {
                e.printStackTrace();
                throw new RuntimeException(ee);
            }
        }

        Assert.assertNotNull(dto);
        Assert.assertEquals(roleName, dto.getRoleName());
    }

    /**
     * Test adding a role to the system.
     */
    @Test
    public void createRole() {
        // Create record
        CategoryDto dto = Rmt2OrmDtoFactory.getNewRoleCategoryInstance();
        dto.setRoleId(0);
        dto.setRoleName("TestRole");
        dto.setRoleDescription("This is a JUnit test");
        dto.setUpdateUserId("testuser");
        int newId = 0;
        RoleApi api = this.f.createRoleApi();
        api.setApiUser(this.userId);
        try {
            newId = api.update(dto);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Assert.assertNotNull(dto);
        Assert.assertTrue(newId > 0);
        Assert.assertEquals("TestRole", dto.getRoleName());

        // Delete reoord
        int rc = 0;
        try {
            rc = api.delete(newId);
        } catch (UnsupportedOperationException e) {
            try {
                rc = api.delete("TestRole");
            } catch (SecurityModuleException e1) {
                e1.printStackTrace();
                Assert.fail(e1.getMessage());
            }
        } catch (SecurityModuleException e) {
            try {
                rc = api.delete("TestRole");
            } catch (SecurityModuleException ee) {
                ee.printStackTrace();
                throw new RuntimeException(ee);
            }

        }
        Assert.assertTrue(rc == 1);
    }

    /**
     * Test modification of an existing role.
     */
    @Test
    public void updateRole() {
        // Create record
        String roleName = "TestRole";
        CategoryDto dto = Rmt2OrmDtoFactory.getNewRoleCategoryInstance();
        dto.setRoleId(0);
        dto.setRoleName(roleName);
        dto.setRoleDescription("This is a JUnit test");
        dto.setUpdateUserId("testuser");
        int newId = 0;
        RoleApi api = this.f.createRoleApi();
        api.setApiUser(this.userId);
        try {
            newId = api.update(dto);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Assert.assertNotNull(dto);
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(roleName, dto.getRoleName());

        // Modify Role
        int rows = 0;
        String modDescription = "Modidfied Description";
        try {
            dto.setRoleDescription(modDescription);
            rows = api.update(dto);
            Assert.assertEquals(1, rows);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Verify that record was modified
        CategoryDto dto2 = null;
        try {
            dto2 = api.get(newId);
        } catch (UnsupportedOperationException e) {
            try {
                dto2 = api.get(roleName);
            } catch (SecurityModuleException e1) {
                e1.printStackTrace();
                Assert.fail(e1.getMessage());
            }
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(modDescription, dto2.getRoleDescription());

        // Delete reoord
        int rc = 0;
        try {
            rc = api.delete(newId);
        } catch (UnsupportedOperationException e) {
            try {
                rc = api.delete(roleName);
            } catch (SecurityModuleException e1) {
                e1.printStackTrace();
                Assert.fail(e1.getMessage());
            }
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertTrue(rc == 1);
    }
}
