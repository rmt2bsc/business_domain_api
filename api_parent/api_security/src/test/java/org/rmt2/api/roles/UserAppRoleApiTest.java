package org.rmt2.api.roles;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dao.mapping.orm.rmt2.AppRole;
import org.dao.mapping.orm.rmt2.UserAppRole;
import org.dao.mapping.orm.rmt2.VwUser;
import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.dao.roles.RoleDaoException;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.SecurityConstants;
import org.modules.SecurityModuleException;
import org.modules.roles.AppRoleApiException;
import org.modules.roles.RoleSecurityApiFactory;
import org.modules.roles.UserAppRoleApi;
import org.modules.roles.UserAppRoleApiException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2Date;

/**
 * Tests UserAppRole module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class UserAppRoleApiTest extends SecurityMockData {

    private static final String[] APP_ROLE_CODES = { "Code_6000", "Code_6001",
            "Code_6002", "Code_6003", "Code_6004" };

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(isA(VwUser.class)))
             .thenReturn(this.mockVwUserSingleData);
        when(this.mockPersistenceClient.retrieveList(isA(VwUserAppRoles.class)))
             .thenReturn(this.mockVwUserAppRolesData);
        when(this.mockPersistenceClient.retrieveList(isA(AppRole.class)))
             .thenReturn(this.mockAppRoleData);
//        when(this.mockPersistenceClient.retrieveObject(any(AppRole.class)))
//             .thenReturn(this.mockAppRoleData.get(0));
        when(this.mockPersistenceClient.insertRow(isA(UserAppRole.class), eq(true)))
             .thenReturn(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID);
//        when(this.mockPersistenceClient.updateRow(any(AppRole.class)))
//             .thenReturn(1);
        when(this.mockPersistenceClient.deleteRow(isA(UserAppRole.class)))
             .thenReturn(5);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    @Test
    public void testSuccess_Fetch() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(null, "test_user");
        List<CategoryDto> results = null;
        try {
            results = api.get(criteria);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            CategoryDto dto = results.get(ndx);
            Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID, dto.getLoginUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID + ndx, dto.getAppRoleId());
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
    }

    
    @Test
    public void testSuccess_Fetch_NotFound() {
        when(this.mockPersistenceClient.retrieveList(any(VwUserAppRoles.class))).thenReturn(null);
        
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(null, "test_user");
        List<CategoryDto> results = null;
        try {
            results = api.get(criteria);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
 
    @Test
    public void testError_Fetch_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveList(any(VwUserAppRoles.class)))
            .thenThrow(new DatabaseException("Error fetching VwUserAppRoles data"));
        
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(null, "test_user");
        try {
            api.get(criteria);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SecurityModuleException);
            Assert.assertTrue(e instanceof AppRoleApiException);
            Assert.assertTrue(e.getCause() instanceof RoleDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Fetch_Null_Criteria_Parameter() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        try {
            api.get(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("UserAppRole Category criteria object is required", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Create() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        VwUserAppRoles obj = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, 
                "user_name",
                "2018-01-01");
        CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(obj, null);
        int results = 0;
        try {
            List<String> appRoleCodeList = Arrays.asList(APP_ROLE_CODES);
            results = api.update(dto, appRoleCodeList);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results);
    }
    
    @Test
    public void testSuccess_Create_AppCodeList_Null() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        VwUserAppRoles obj = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, 
                "user_name",
                "2018-01-01");
        CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(obj, null);
        int results = 0;
        try {
            results = api.update(dto, null);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(0, results);
    }

    @Test
    public void testSuccess_Create_AppCodeList_Empty() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        VwUserAppRoles obj = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, 
                "user_name",
                "2018-01-01");
        CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(obj, null);
        int results = 0;
        try {
            List<String> appRoleCodeList = new ArrayList<>();
            results = api.update(dto, appRoleCodeList);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(0, results);
    }
    
    
    @Test
    public void testSuccess_Create_UnsupportedOperation_Update() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        UserAppRole obj = SecurityMockDataFactory.createOrmUserAppRole(0,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    @Test
    public void testError_Create_DB_Access_Fault() {
        when(this.mockPersistenceClient.insertRow(isA(UserAppRole.class), eq(true)))
             .thenThrow(new DatabaseException("Error inserting UserAppRole data"));
        
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        VwUserAppRoles obj = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, 
                "user_name",
                "2018-01-01");
        CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(obj, null);
        try {
            List<String> appRoleCodeList = Arrays.asList(APP_ROLE_CODES);
            api.update(dto, appRoleCodeList);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SecurityModuleException);
            Assert.assertTrue(e instanceof UserAppRoleApiException);
            Assert.assertTrue(e.getCause() instanceof RoleDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Create_User_Data_Object_Null() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        try {
            List<String> appRoleCodeList = Arrays.asList(APP_ROLE_CODES);
            api.update(null, appRoleCodeList);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
  
    @Test
    public void testSuccess_Delete_Selected_AppRoleCodes() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        int results = 0;
        try {
            List<String> appRoleCodeList = Arrays.asList(APP_ROLE_CODES);
            results = api.delete("user_name", appRoleCodeList);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results);
    }
    
    @Test
    public void testSuccess_Delete_AppRoleCodes_Null() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        int results = 0;
        try {
            results = api.delete("user_name", null);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results);
    }
    
    @Test
    public void testSuccess_Delete_AppRoleCodes_Empty() {
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        int results = 0;
        try {
            List<String> appRoleCodeList = new ArrayList<>();
            results = api.delete("user_name", null);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results);
    }
    
    @Test
    public void testError_Delete_DB_Access_Fault() {
        when(this.mockPersistenceClient.deleteRow(any(UserAppRole.class)))
             .thenThrow(new DatabaseException("Error deleting UserAppRole data"));
        
        UserAppRoleApi api = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
        try {
            List<String> appRoleCodeList = Arrays.asList(APP_ROLE_CODES);
            api.delete("user_name", appRoleCodeList);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SecurityModuleException);
            Assert.assertTrue(e instanceof UserAppRoleApiException);
            Assert.assertTrue(e.getCause() instanceof RoleDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
}