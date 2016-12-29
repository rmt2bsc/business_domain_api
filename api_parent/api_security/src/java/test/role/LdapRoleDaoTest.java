package role;

import java.util.List;

import junit.framework.Assert;

import org.dao.mapping.orm.ldap.LdapRoles;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the role operations of an LDAP implementation of the
 * {@link org.dao.roles.RoleDao}
 * 
 * @author Roy Terrell
 * 
 */
public class LdapRoleDaoTest {

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
        LdapRoles dummy = null;
        CategoryDto dto = LdapDtoFactory.getRoleDtoInstance(dummy);
        dto.setRoleName("TestRole1");
        dto.setRoleDescription("This test role #1");
        this.dao.maintainRole(dto);

        // craete 2nd user
        dto = LdapDtoFactory.getRoleDtoInstance(dummy);
        dto.setRoleName("TestRole2");
        dto.setRoleDescription("This test role #2");
        this.dao.maintainRole(dto);

        // craete 3rd user
        dto = LdapDtoFactory.getRoleDtoInstance(dummy);
        dto.setRoleName("TestRole3");
        dto.setRoleDescription("This test role #3");
        this.dao.maintainRole(dto);
    }

    private void deleteData() {
        this.dao.deleteRole("TestRole1");
        this.dao.deleteRole("TestRole2");
        this.dao.deleteRole("TestRole3");
    }

    @Test
    public void fetchAll() throws Exception {
        List<CategoryDto> list = null;
        try {
            list = this.dao.fetchRole();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
    }

    @Test
    public void fetchByName() throws Exception {
        CategoryDto dto = null;
        String name = "TestRole2";
        String descr = "This test role #2";
        try {
            dto = this.dao.fetchRole(name);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(dto);
        Assert.assertEquals(name, dto.getRoleName());
        Assert.assertEquals(descr, dto.getRoleDescription());
    }

    @Test
    public void fetchByInvalidName() throws Exception {
        CategoryDto dto = null;
        String name = "xxxxxx";
        try {
            dto = this.dao.fetchRole(name);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNull(dto);
    }

    @Test
    public void updateRole() throws Exception {
        String newDescr = "This is a modified description";
        CategoryDto dto = null;
        String name = "TestRole2";
        try {
            dto = this.dao.fetchRole(name);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(name, dto.getRoleName());

        // Update role
        dto.setRoleDescription(newDescr);
        try {
            this.dao.maintainRole(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // Verify change
        try {
            dto = this.dao.fetchRole(name);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(newDescr, dto.getRoleDescription());
    }

}
