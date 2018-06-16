package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.AppRole;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

/**
 * Test AppRole adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class AppRoleAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrm() {
        AppRole o1 = SecurityMockDataFactory.createOrmAppRole(
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(o1);
        
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID, dto.getAppRoleId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, dto.getApplicationId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_ROLE_ID, dto.getRoleId());
        Assert.assertEquals("Code_" + dto.getAppRoleId(), dto.getAppRoleCode());
        Assert.assertEquals("Name_" + dto.getAppRoleId(), dto.getAppRoleName());
        Assert.assertEquals("Description_" + dto.getAppRoleId(), dto.getAppRoleDescription());
        
        try {
            AppRole nullParm = null;
            dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(nullParm);
            dto.setAppRoleId(SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setApplicationId(SecurityMockDataFactory.TEST_APP_ID);
            dto.setRoleId(SecurityMockDataFactory.TEST_ROLE_ID);
            dto.setAppRoleCode("Code_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setAppRoleName("Name_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setAppRoleDescription("Description_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);

            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID, dto.getAppRoleId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, dto.getApplicationId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_ROLE_ID, dto.getRoleId());
            Assert.assertEquals("Code_" + dto.getAppRoleId(), dto.getAppRoleCode());
            Assert.assertEquals("Name_" + dto.getAppRoleId(), dto.getAppRoleName());
            Assert.assertEquals("Description_" + dto.getAppRoleId(), dto.getAppRoleDescription());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for AppRole Adapater");
        }
    }
}
