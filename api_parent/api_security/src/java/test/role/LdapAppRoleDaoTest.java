package role;

import java.util.List;

import junit.framework.Assert;

import org.dao.mapping.orm.ldap.LdapAppRoles;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the application role operations of an LDAP implementation of the
 * {@link org.dao.roles.RoleDao}
 * 
 * @author Roy Terrell
 * 
 */
public class LdapAppRoleDaoTest {

    private RoleDaoFactory f;

    private RoleDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new RoleDaoFactory();
        this.dao = this.f.createLdapDao();
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    private void createData() {
        LdapAppRoles dummy = null;
        CategoryDto dto = LdapDtoFactory.getApplicationRoleDtoInstance(dummy);
        dto.setAppRoleCode("testapprolecode1");
        dto.setRoleName("open");
        dto.setAppName("TestApplication1");
        dto.setAppRoleName("TestAppRoleName1");
        dto.setAppRoleDescription("This test app role description #1");
        this.dao.maintainAppRole(dto);

        // craete 2nd user
        dto = LdapDtoFactory.getApplicationRoleDtoInstance(dummy);
        dto.setAppRoleCode("testapprolecode2");
        dto.setRoleName("open");
        dto.setAppName("TestApplication2");
        dto.setAppRoleName("TestAppRoleName2");
        dto.setAppRoleDescription("This test app role description #2");
        this.dao.maintainAppRole(dto);

        // craete 3rd user
        dto = LdapDtoFactory.getApplicationRoleDtoInstance(dummy);
        dto.setAppRoleCode("testapprolecode3");
        dto.setRoleName("power");
        dto.setAppName("TestApplication3");
        dto.setAppRoleName("TestAppRoleName3");
        dto.setAppRoleDescription("This test app role description #3");
        this.dao.maintainAppRole(dto);
    }

    private void deleteData() {
        this.dao.deleteAppRole("testapprolecode1");
        this.dao.deleteAppRole("testapprolecode2");
        this.dao.deleteAppRole("testapprolecode3");
    }

    @Test
    public void fetchAll() throws Exception {
        List<CategoryDto> list = null;
        try {
            list = this.dao.fetchAppRole();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
    }

    @Test
    public void fetchByAppRoleCode() throws Exception {
        List<CategoryDto> list = null;
        LdapAppRoles dummy = null;
        CategoryDto criteria = LdapDtoFactory
                .getApplicationRoleDtoInstance(dummy);
        String appRoleName = "TestAppRoleName3";
        String code = "testapprolecode3";
        String descr = "This test app role description #3";
        String roleName = "power";
        String appName = "TestApplication3";
        criteria.setAppRoleCode(code);
        try {
            list = this.dao.fetchAppRole(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        CategoryDto dto = list.get(0);
        Assert.assertEquals(code, dto.getAppRoleCode());
        Assert.assertEquals(appRoleName, dto.getAppRoleName());
        Assert.assertEquals(descr, dto.getAppRoleDescription());
        Assert.assertEquals(roleName, dto.getRoleName());
        Assert.assertEquals(appName, dto.getAppName());
    }

    @Test
    public void fetchAllOpenRoles() throws Exception {
        List<CategoryDto> list = null;
        LdapAppRoles dummy = null;
        CategoryDto criteria = LdapDtoFactory
                .getApplicationRoleDtoInstance(dummy);
        String roleName = "open";
        criteria.setRoleName(roleName);
        try {
            list = this.dao.fetchAppRole(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void fetchAllPowerRoles() throws Exception {
        List<CategoryDto> list = null;
        LdapAppRoles dummy = null;
        CategoryDto criteria = LdapDtoFactory
                .getApplicationRoleDtoInstance(dummy);
        String roleName = "power";
        criteria.setRoleName(roleName);
        try {
            list = this.dao.fetchAppRole(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void fetchByInvalidCodeName() throws Exception {
        List<CategoryDto> list = null;
        LdapAppRoles dummy = null;
        CategoryDto criteria = LdapDtoFactory
                .getApplicationRoleDtoInstance(dummy);
        String code = "xxxxxxxx";
        criteria.setAppRoleCode(code);
        try {
            list = this.dao.fetchAppRole(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNull(list);
    }

    @Test
    public void updateRole() throws Exception {
        List<CategoryDto> list = null;
        LdapAppRoles dummy = null;
        CategoryDto criteria = LdapDtoFactory
                .getApplicationRoleDtoInstance(dummy);
        String appRoleName = "TestAppRoleName2";
        String code = "testapprolecode2";
        String modDescr = "This test app role description was modified";
        criteria.setAppRoleCode(code);
        try {
            list = this.dao.fetchAppRole(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        CategoryDto dto = list.get(0);
        Assert.assertEquals(appRoleName, dto.getAppRoleName());

        // Update role
        dto.setAppRoleDescription(modDescr);
        try {
            this.dao.maintainAppRole(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // Verify change
        try {
            list = this.dao.fetchAppRole(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        dto = list.get(0);
        Assert.assertNotNull(dto);
        Assert.assertEquals(appRoleName, dto.getAppRoleName());
        Assert.assertEquals(modDescr, dto.getAppRoleDescription());
    }

}
