package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.UserAppRole;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

/**
 * Test UserAppRole adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class UserAppRoleAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrm() {
        UserAppRole o1 = SecurityMockDataFactory.createOrmUserAppRole(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(o1);
        
        Assert.assertEquals(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID, dto.getUserAppRoleId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID, dto.getLoginUid());
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID, dto.getAppRoleId());
        
        try {
            UserAppRole nullParm = null;
            dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(nullParm);
            dto.setUserAppRoleId(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID);
            dto.setLoginUid(SecurityMockDataFactory.TEST_USER_ID);
            dto.setAppRoleId(SecurityMockDataFactory.TEST_APP_ROLE_ID);

            Assert.assertEquals(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID, dto.getUserAppRoleId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID, dto.getLoginUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID, dto.getAppRoleId());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for UserAppRole Adapater");
        }
    }
}
