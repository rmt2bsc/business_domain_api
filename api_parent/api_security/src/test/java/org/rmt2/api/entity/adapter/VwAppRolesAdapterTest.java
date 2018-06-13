package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.VwAppRoles;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

/**
 * Test VwAppRoles adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class VwAppRolesAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrm() {
        VwAppRoles o1 = SecurityMockDataFactory.createOrmVwAppRoles(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(o1, "test_user");
        
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID, dto.getAppRoleId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, dto.getApplicationId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_ROLE_ID, dto.getRoleId());
        Assert.assertEquals("AppRoleCode_" + dto.getAppRoleId(), dto.getAppRoleCode());
        Assert.assertEquals("AppRoleName_" + dto.getAppRoleId(), dto.getAppRoleName());
        Assert.assertEquals("RoleName_" + dto.getRoleId(), dto.getRoleName());
        Assert.assertEquals("AppName_" + dto.getApplicationId(), dto.getAppName());
        Assert.assertEquals("AppRoleDescription_" + dto.getAppRoleId(), dto.getAppRoleDescription());
        
        try {
            VwAppRoles nullParm = null;
            dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(nullParm, null);
            dto.setAppRoleId(SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setApplicationId(SecurityMockDataFactory.TEST_APP_ID);
            dto.setRoleId(SecurityMockDataFactory.TEST_ROLE_ID);
            dto.setAppRoleCode("AppRoleCode_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setAppRoleName("AppRoleName_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setRoleName("RoleName_" + SecurityMockDataFactory.TEST_ROLE_ID);
            dto.setAppName("AppName_" + SecurityMockDataFactory.TEST_APP_ID);
            dto.setAppRoleDescription("AppRoleDescription_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);

            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID, dto.getAppRoleId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, dto.getApplicationId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_ROLE_ID, dto.getRoleId());
            Assert.assertEquals("AppRoleCode_" + dto.getAppRoleId(), dto.getAppRoleCode());
            Assert.assertEquals("AppRoleName_" + dto.getAppRoleId(), dto.getAppRoleName());
            Assert.assertEquals("RoleName_" + dto.getRoleId(), dto.getRoleName());
            Assert.assertEquals("AppName_" + dto.getApplicationId(), dto.getAppName());
            Assert.assertEquals("AppRoleDescription_" + dto.getAppRoleId(), dto.getAppRoleDescription());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for VwAppRoles Adapater");
        }
    }
}
