package role;

import java.util.List;

import junit.framework.Assert;

import org.dao.mapping.orm.ldap.LdapAppRoles;
import org.dto.CategoryDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.SecurityModuleException;
import org.modules.roles.AppRoleApi;
import org.modules.roles.RoleSecurityApiFactory;

/**
 * JUnint test case for testing the {@link AppRoleApi} implementation.
 * 
 * @author rterrell
 * 
 */
public class AppRoleApiTest {

    private RoleSecurityApiFactory f;

    private String userId;

    /**
     * Creates the factory for Application Role api.
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
     * Test api to fetch all application roles
     */
    @Test
    public void fetchAllAppRoles() {
        AppRoleApi api = this.f.createAppRoleApi();
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
     * Test api to fetch a single application role.
     */
    @Test
    public void fetchSingleAppRole() {
        int appRoleId = 13; // Proj Admin
        AppRoleApi api = this.f.createAppRoleApi();
        CategoryDto dto = null;
        List<CategoryDto> list;
        String appRoleCode = "ProjAdm";
        try {
            dto = api.get(appRoleId);
        } catch (SecurityModuleException e) {

            try {
                LdapAppRoles dummy = null;
                CategoryDto criteria = api.create();
                criteria.setAppRoleCode(appRoleCode);
                list = api.get(criteria);
            } catch (SecurityModuleException ee) {
                e.printStackTrace();
                throw new RuntimeException(ee);
            }
            Assert.assertNotNull(list);
            Assert.assertTrue(list.size() > 0);
            dto = list.get(0);
        }

        Assert.assertNotNull(dto);
        Assert.assertEquals(appRoleCode, dto.getAppRoleCode());
    }

    // /**
    // * Test api to fetch all application roles related to a user.
    // */
    // @Test
    // public void fetchAppRoleByUsername() {
    // AppRoleApi api = this.f.createAppRoleApi();
    // List<CategoryDto> list;
    // try {
    // list = api.get("admin");
    // }
    // catch (SecurityModuleException e) {
    // e.printStackTrace();
    // throw new RuntimeException(e);
    // }
    // Assert.assertNotNull(list);
    // Assert.assertTrue(list.size() > 0);
    // }

    // /**
    // * Test api to fetch all application roles related to a user and an
    // application.
    // */
    // @Test
    // public void fetchAppRoleByUsernameAndAppname() {
    // AppRoleApi api = this.f.createAppRoleApi();
    // List<CategoryDto> list;
    // try {
    // list = api.get("admin", "authentication");
    // }
    // catch (SecurityModuleException e) {
    // e.printStackTrace();
    // throw new RuntimeException(e);
    // }
    // Assert.assertNotNull(list);
    // Assert.assertTrue(list.size() > 0);
    // }

    /**
     * Test api to fetch all application roles belonging to a given application.
     * <p>
     * The application id is specified in an instance of {@link CategoryDto}.
     */
    @Test
    public void fetchAppRoleByDTO() {
        AppRoleApi api = this.f.createAppRoleApi();
        List<CategoryDto> list;
        try {
            CategoryDto dto = Rmt2OrmDtoFactory
                    .getNewExtAppRoleCategoryInstance(null);
            dto.setApplicationId(10); // Authentication application id.
            list = api.get(dto);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    /**
     * Test creating a new instanceo of {@link CategoryDto} focused on
     * application role data.
     */
    @Test
    public void createNewAppRoleInstance() {
        AppRoleApi api = this.f.createAppRoleApi();
        CategoryDto dto = api.create();
        Assert.assertNotNull(dto);
    }

    /**
     * Test inserting a new application role into the database.
     */
    @Test
    public void insertAppRole() {
        AppRoleApi api = this.f.createAppRoleApi();
        api.setApiUser(this.userId);
        int newId = 0;

        // Create record
        CategoryDto dto = api.create();
        try {
            dto.setAppRoleId(0);
            dto.setApplicationId(8);
            dto.setRoleId(20);
        } catch (Exception e) {
            // do nothing...
        }
        dto.setAppRoleCode("testcode");
        dto.setAppRoleName("TestAppRole");
        dto.setAppRoleDescription("This is a JUnit test");
        dto.setAppName("app name");
        dto.setRoleName("role name");
        dto.setUpdateUserId("testuser");

        // Insert Record
        try {
            newId = api.update(dto);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(dto);
        Assert.assertTrue(newId > 0);
        Assert.assertEquals("testcode", dto.getAppRoleCode());

        // Delete reoord
        int rc = 0;
        try {
            rc = api.delete(newId);
        } catch (SecurityModuleException e) {
            try {
                rc = api.delete(dto.getAppRoleCode());
            } catch (SecurityModuleException ee) {
                e.printStackTrace();
                throw new RuntimeException(ee);
            }
        }
        Assert.assertTrue(rc == 1);
    }

    /**
     * Test applying modifications to an existing application role to the
     * database.
     */
    @Test
    public void updateAppRole() {
        String modDescr = "This is a modified JUnit test record";
        AppRoleApi api = this.f.createAppRoleApi();
        api.setApiUser(this.userId);
        int newId = 0;

        // Create record
        CategoryDto dto = api.create();
        try {
            dto.setAppRoleId(0);
            dto.setApplicationId(8);
            dto.setRoleId(20);
        } catch (Exception e) {
            // do nothing..
        }
        dto.setAppRoleCode("testcode");
        dto.setAppRoleName("TestAppRole");
        dto.setAppRoleDescription("This is a JUnit test");
        dto.setAppName("app name");
        dto.setRoleName("role name");
        dto.setUpdateUserId("testuser");
        dto.setUpdateUserId("testuser");

        // Insert Record
        try {
            newId = api.update(dto);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(dto);
        Assert.assertTrue(newId > 0);
        Assert.assertEquals("testcode", dto.getAppRoleCode());

        // Update record
        int rc = 0;
        String modAppRoleName = "TestAppRole2Mod";
        try {
            dto.setAppRoleName(modAppRoleName);
            dto.setAppRoleDescription(modDescr);
            rc = api.update(dto);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(dto);
        Assert.assertTrue(rc == 1);

        // Verify changes
        CategoryDto dto2;
        List<CategoryDto> list = null;
        try {
            dto2 = api.get(newId);
        } catch (SecurityModuleException e) {
            try {
                LdapAppRoles dummy = null;
                CategoryDto criteria = LdapDtoFactory
                        .getApplicationRoleDtoInstance(dummy);
                criteria.setAppRoleCode(dto.getAppRoleCode());
                list = api.get(criteria);
            } catch (SecurityModuleException ee) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            Assert.assertNotNull(list);
            Assert.assertEquals(1, list.size());
            dto2 = list.get(0);
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(modDescr, dto2.getAppRoleDescription());

        // Delete reoord
        try {
            rc = api.delete(newId);
        } catch (SecurityModuleException e) {
            try {
                rc = api.delete(dto2.getAppRoleCode());
            } catch (SecurityModuleException ee) {
                e.printStackTrace();
                throw new RuntimeException(ee);
            }
        }
        Assert.assertTrue(rc == 1);
    }

}
