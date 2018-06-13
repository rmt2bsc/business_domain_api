package org.rmt2.api.roles;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.management.relation.Role;

import org.dao.mapping.orm.rmt2.Roles;
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
import org.modules.roles.RoleApi;
import org.modules.roles.RoleApiException;
import org.modules.roles.RoleSecurityApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Role module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class RolesApiTest extends SecurityMockData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(any(Role.class)))
             .thenReturn(this.mockRolesData);
        when(this.mockPersistenceClient.retrieveObject(any(Role.class)))
        .thenReturn(this.mockRolesData.get(0));
        when(this.mockPersistenceClient.insertRow(any(Role.class), eq(true)))
             .thenReturn(SecurityMockDataFactory.TEST_ROLE_ID);
        when(this.mockPersistenceClient.updateRow(any(Role.class)))
             .thenReturn(1);
        when(this.mockPersistenceClient.deleteRow(any(Role.class)))
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
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getRoleDtoInstance(null);
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
            int currentUid = SecurityMockDataFactory.TEST_ROLE_ID + ndx;
            Assert.assertEquals(currentUid, item.getRoleId());
            Assert.assertEquals("RoleName_" + currentUid, item.getRoleName());
            Assert.assertEquals("RoleDescription_" + currentUid, item.getRoleDescription());
        }
    }

    
    @Test
    public void testSuccess_Fetch_NotFound() {
        when(this.mockPersistenceClient.retrieveList(any(Role.class)))
              .thenReturn(null);
        
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getRoleDtoInstance(null);
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
        when(this.mockPersistenceClient.retrieveList(any(Role.class)))
            .thenThrow(new DatabaseException("Error fetching role data"));
        
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getRoleDtoInstance(null);
        try {
            api.get(criteria);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SecurityModuleException);
            Assert.assertTrue(e instanceof RoleApiException);
            Assert.assertTrue(e.getCause() instanceof RoleDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Fetch_Null_Criteria_Parameter() {
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        try {
            api.get(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Role Category criteria object is required", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Create() {
        when(this.mockPersistenceClient.retrieveObject(any(Role.class))).thenReturn(null);
        
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        Roles obj = SecurityMockDataFactory.createOrmRoles(0);
        CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(obj);
        int results = 0;
        try {
            results = api.update(dto);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(SecurityMockDataFactory.TEST_ROLE_ID, results);
    }

    @Test
    public void testError_Create_DB_Access_Fault() {
        when(this.mockPersistenceClient.insertRow(any(Role.class), eq(true)))
             .thenThrow(new DatabaseException("Error inserting Role data"));
        
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        Roles obj = SecurityMockDataFactory.createOrmRoles(0);
        CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SecurityModuleException);
            Assert.assertTrue(e instanceof RoleApiException);
            Assert.assertTrue(e.getCause() instanceof RoleDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testSuccess_Modify() {
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        Roles obj = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(obj);
        
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
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        Roles obj = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(obj);
        
        when(this.mockPersistenceClient.updateRow(any(Role.class)))
               .thenThrow(new DatabaseException("Error updating role data"));
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SecurityModuleException);
            Assert.assertTrue(e instanceof RoleApiException);
            Assert.assertTrue(e.getCause() instanceof RoleDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Update_Data_Object_Null() {
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        try {
            api.update(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_RoleName_Null() {
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        Roles obj = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setName(null);
        CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_RoleName_Empty() {
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        Roles obj = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setName("");
        CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_RoleDescription_Null() {
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        Roles obj = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setDescription(null);
        CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_RoleDescription_Empty() {
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        Roles obj = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        obj.setDescription("");
        CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(obj);
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
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        int results = 0;
        try {
            results = api.delete(SecurityMockDataFactory.TEST_ROLE_ID);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Delete_DB_Access_Fault() {
        when(this.mockPersistenceClient.deleteRow(any(Role.class)))
             .thenThrow(new DatabaseException("Error deleting role data"));
        
        RoleApi api = RoleSecurityApiFactory.createRoleApi(SecurityConstants.APP_NAME);
        try {
            api.delete(SecurityMockDataFactory.TEST_ROLE_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SecurityModuleException);
            Assert.assertTrue(e instanceof RoleApiException);
            Assert.assertTrue(e.getCause() instanceof RoleDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
}