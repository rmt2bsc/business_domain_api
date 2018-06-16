package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.Roles;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

/**
 * Test Roles adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class RolesAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrm() {
        Roles o1 = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(o1);
        
        Assert.assertEquals(SecurityMockDataFactory.TEST_ROLE_ID, dto.getRoleId());
        Assert.assertEquals("RoleName_" + dto.getRoleId(), dto.getRoleName());
        Assert.assertEquals("RoleDescription_" + dto.getRoleId(), dto.getRoleDescription());
        
        try {
            Roles nullParm = null;
            dto = Rmt2OrmDtoFactory.getRoleDtoInstance(nullParm);
            dto.setRoleId(SecurityMockDataFactory.TEST_ROLE_ID);
            dto.setRoleName("RoleName_" + SecurityMockDataFactory.TEST_ROLE_ID);
            dto.setRoleDescription("RoleDescription_" + SecurityMockDataFactory.TEST_ROLE_ID);

            Assert.assertEquals(SecurityMockDataFactory.TEST_ROLE_ID, dto.getRoleId());
            Assert.assertEquals("RoleName_" + dto.getRoleId(), dto.getRoleName());
            Assert.assertEquals("RoleDescription_" + dto.getRoleId(), dto.getRoleDescription());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for Roles Adapater");
        }
    }
}
