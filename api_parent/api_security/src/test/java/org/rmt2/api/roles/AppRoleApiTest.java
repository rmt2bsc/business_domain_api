package org.rmt2.api.roles;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dao.mapping.orm.rmt2.AppRole;
import org.dao.mapping.orm.rmt2.VwAppRoles;
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
import org.modules.roles.AppRoleApi;
import org.modules.roles.AppRoleApiException;
import org.modules.roles.RoleSecurityApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests AppRole module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class AppRoleApiTest extends SecurityMockData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(any(VwAppRoles.class)))
             .thenReturn(this.mockVwAppRolesData);
        when(this.mockPersistenceClient.retrieveObject(any(AppRole.class)))
             .thenReturn(this.mockAppRoleData.get(0));
        when(this.mockPersistenceClient.insertRow(any(AppRole.class), eq(true)))
             .thenReturn(SecurityMockDataFactory.TEST_APP_ROLE_ID);
        when(this.mockPersistenceClient.updateRow(any(AppRole.class)))
             .thenReturn(1);
        when(this.mockPersistenceClient.deleteRow(any(AppRole.class)))
             .thenReturn(1);
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
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getAppRoleDtoInstance(null, "test_user");
        List<CategoryDto> results = null;
        try {
            results = api.get(criteria);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            CategoryDto item = results.get(ndx);
            int currentUid = SecurityMockDataFactory.TEST_APP_ROLE_ID + ndx;
            int currrentRoleId = SecurityMockDataFactory.TEST_ROLE_ID + ndx;
            Assert.assertEquals(currentUid, item.getAppRoleId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, item.getApplicationId());
            Assert.assertEquals(currrentRoleId, item.getRoleId());
            Assert.assertEquals("AppRoleCode_" + currentUid, item.getAppRoleCode());
            Assert.assertEquals("AppRoleName_" + currentUid, item.getAppRoleName());
            Assert.assertEquals("RoleName_" + currrentRoleId, item.getRoleName());
            Assert.assertEquals("AppName_" + SecurityMockDataFactory.TEST_APP_ID, item.getAppName());
            Assert.assertEquals("AppRoleDescription_" + currentUid, item.getAppRoleDescription());
        }
    }

    
    @Test
    public void testSuccess_Fetch_NotFound() {
        when(this.mockPersistenceClient.retrieveList(any(VwAppRoles.class)))
              .thenReturn(null);
        
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getAppRoleDtoInstance(null, "test_user");
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
        when(this.mockPersistenceClient.retrieveList(any(VwAppRoles.class)))
            .thenThrow(new DatabaseException("Error fetching VwAppRoles data"));
        
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getAppRoleDtoInstance(null, "test_user");
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
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        try {
            api.get(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("AppRole Category criteria object is required", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Create() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(0,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        int results = 0;
        try {
            results = api.update(dto);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ROLE_ID, results);
    }

    @Test
    public void testError_Create_DB_Access_Fault() {
        when(this.mockPersistenceClient.insertRow(any(AppRole.class), eq(true)))
             .thenThrow(new DatabaseException("Error inserting AppRole data"));
        
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(0,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
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
    public void testSuccess_Modify() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        
        int results = 0;
        try {
            results = api.update(dto);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Modify_DB_Access_Fault() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        
        when(this.mockPersistenceClient.updateRow(any(AppRole.class)))
               .thenThrow(new DatabaseException("Error updating AppRole data"));
        try {
            api.update(dto);
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
    public void testError_Modify_NotFound_Fault() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        
        when(this.mockPersistenceClient.retrieveObject(any(AppRole.class)))
               .thenReturn(null);
        
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof NotFoundException);
            Assert.assertEquals("AppRole does not exists [app role id=" + dto.getAppRoleId() + "]", e.getMessage());
        }
    }
    
    @Test
    public void testError_Modify_Fetch_Fault() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        
        when(this.mockPersistenceClient.retrieveObject(any(AppRole.class)))
               .thenThrow(new DatabaseException("Error fetching AppRole data"));
        try {
            api.update(dto);
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
    public void testValidation_Update_Data_Object_Null() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        try {
            api.update(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_ApplicationId_Negative() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                -1234,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_ApplicationId_Zero() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                0,
                SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_RoleId_Negative() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                -1234);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_RoleId_Zero() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                0);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_AppRoleName_Empty() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setName("");
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_AppRoleName_Null() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setName(null);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_AppRoleDescription_Empty() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setDescription("");
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_AppRoleDescription_Null() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setDescription(null);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_AppRoleCode_Empty() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setCode("");
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_AppRoleCode_Null() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        AppRole obj = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setCode(null);
        CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    
    @Test
    public void testSuccess_Delete() {
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        int results = 0;
        try {
            results = api.delete(SecurityMockDataFactory.TEST_APP_ROLE_ID);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Delete_DB_Access_Fault() {
        when(this.mockPersistenceClient.deleteRow(any(AppRole.class)))
             .thenThrow(new DatabaseException("Error deleting AppRole data"));
        
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        try {
            api.delete(SecurityMockDataFactory.TEST_APP_ROLE_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SecurityModuleException);
            Assert.assertTrue(e instanceof AppRoleApiException);
            Assert.assertTrue(e.getCause() instanceof RoleDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
}