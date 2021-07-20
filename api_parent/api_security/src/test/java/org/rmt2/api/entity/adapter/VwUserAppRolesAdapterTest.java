package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

import com.api.util.RMT2Date;

/**
 * Test VwUserAppRoles adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class VwUserAppRolesAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrm() {
        VwUserAppRoles o1 = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, 
                "user_name",
                "2018-01-01");
        CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(o1, "test_user");
        
        Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID + SecurityMockDataFactory.TEST_APP_ID
                + SecurityMockDataFactory.TEST_ROLE_ID, dto.getUserAppRoleId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID, dto.getLoginUid());
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID, dto.getAppRoleId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, dto.getApplicationId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_ROLE_ID, dto.getRoleId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_GROUP_ID, dto.getGroupId());
        Assert.assertEquals("AppRoleCode_" + dto.getAppRoleId(), dto.getAppRoleCode());
        Assert.assertEquals("AppRoleName_" + dto.getAppRoleId(), dto.getAppRoleName());
        Assert.assertEquals("RoleName_" + dto.getRoleId(), dto.getRoleName());
        Assert.assertEquals("AppName_" + dto.getApplicationId(), dto.getAppName());
        Assert.assertEquals("AppRoleDescription_" + dto.getAppRoleId(), dto.getAppRoleDescription());
        Assert.assertEquals("GroupDescription_" + dto.getGroupId(), dto.getGrpDescription());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getStartDate());
        Assert.assertEquals(RMT2Date.stringToDate("1966-01-01"), dto.getBirthDate());
        Assert.assertEquals("user_name", dto.getUsername());
        Assert.assertEquals("firstname_" + dto.getLoginUid(), dto.getFirstname());
        Assert.assertEquals("lastname_" + dto.getLoginUid(), dto.getLastname());
        Assert.assertEquals(dto.getFirstname() + "." + dto.getLastname() + "@gte.net", dto.getEmail());
        Assert.assertEquals("UserDescription_" + dto.getLoginUid(), dto.getUserDescription());
        Assert.assertEquals(1, dto.getActive());
        Assert.assertEquals("111-11-1111", dto.getSsn());
        
        try {
            VwUserAppRoles nullParm = null;
            dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(nullParm, null);
            dto.setUserAppRoleId(SecurityMockDataFactory.TEST_USER_ID + SecurityMockDataFactory.TEST_APP_ID
                    + SecurityMockDataFactory.TEST_ROLE_ID);
            dto.setLoginUid(SecurityMockDataFactory.TEST_USER_ID);
            dto.setAppRoleId(SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setApplicationId(SecurityMockDataFactory.TEST_APP_ID);
            dto.setRoleId(SecurityMockDataFactory.TEST_ROLE_ID);
            dto.setGroupId(SecurityMockDataFactory.TEST_GROUP_ID);
            dto.setAppRoleCode("AppRoleCode_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setAppRoleName("AppRoleName_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setRoleName("RoleName_" + SecurityMockDataFactory.TEST_ROLE_ID);
            dto.setAppName("AppName_" + SecurityMockDataFactory.TEST_APP_ID);
            dto.setAppRoleDescription("AppRoleDescription_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
            dto.setGrpDescription("GroupDescription_" + dto.getGroupId());
            dto.setUsername("user_name");
            dto.setStartDate(RMT2Date.stringToDate("2018-01-01"));
            dto.setBirthDate(RMT2Date.stringToDate("1966-01-01"));
            dto.setFirstname("firstname_" + dto.getLoginUid());
            dto.setLastname("lastname_" + dto.getLoginUid());
            dto.setEmail(dto.getFirstname() + "." +dto.getLastname() + "@gte.net");
            dto.setUserDescription("UserDescription_" + dto.getLoginUid());
            dto.setActive(1);
            dto.setSsn("111-11-1111");

            Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID + SecurityMockDataFactory.TEST_APP_ID
                    + SecurityMockDataFactory.TEST_ROLE_ID, dto.getUserAppRoleId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID, dto.getLoginUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID, dto.getAppRoleId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, dto.getApplicationId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_ROLE_ID, dto.getRoleId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_GROUP_ID, dto.getGroupId());
            Assert.assertEquals("AppRoleCode_" + dto.getAppRoleId(), dto.getAppRoleCode());
            Assert.assertEquals("AppRoleName_" + dto.getAppRoleId(), dto.getAppRoleName());
            Assert.assertEquals("RoleName_" + dto.getRoleId(), dto.getRoleName());
            Assert.assertEquals("AppName_" + dto.getApplicationId(), dto.getAppName());
            Assert.assertEquals("AppRoleDescription_" + dto.getAppRoleId(), dto.getAppRoleDescription());
            Assert.assertEquals("GroupDescription_" + dto.getGroupId(), dto.getGrpDescription());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getStartDate());
            Assert.assertEquals(RMT2Date.stringToDate("1966-01-01"), dto.getBirthDate());
            Assert.assertEquals("user_name", dto.getUsername());
            Assert.assertEquals("firstname_" + dto.getLoginUid(), dto.getFirstname());
            Assert.assertEquals("lastname_" + dto.getLoginUid(), dto.getLastname());
            Assert.assertEquals(dto.getFirstname() + "." + dto.getLastname() + "@gte.net", dto.getEmail());
            Assert.assertEquals("UserDescription_" + dto.getLoginUid(), dto.getUserDescription());
            Assert.assertEquals(1, dto.getActive());
            Assert.assertEquals("111-11-1111", dto.getSsn());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for VwUserAppRoles Adapater");
        }
    }
}
