package org.rmt2.api.user;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dao.mapping.orm.rmt2.UserGroup;
import org.dao.user.UserDaoException;
import org.dto.UserDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.SecurityConstants;
import org.modules.users.UserApi;
import org.modules.users.UserApiException;
import org.modules.users.UserApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the User Group functionality of the Security API's User module.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class UserGroupApiTest extends SecurityMockData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(any(UserGroup.class)))
             .thenReturn(this.mockUserGroupData);
        when(this.mockPersistenceClient.insertRow(any(UserGroup.class), eq(true)))
             .thenReturn(SecurityMockDataFactory.TEST_GROUP_ID);
        when(this.mockPersistenceClient.updateRow(any(UserGroup.class)))
             .thenReturn(1);
        when(this.mockPersistenceClient.deleteRow(any(UserGroup.class)))
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
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserDto criteria = Rmt2OrmDtoFactory.getNewUserInstance();
        List<UserDto> results = null;
        try {
            results = api.getGroup(criteria);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            UserDto item = results.get(ndx);
            int currentGroupId = SecurityMockDataFactory.TEST_GROUP_ID + ndx;
            Assert.assertEquals(0, item.getLoginUid());
            Assert.assertEquals(currentGroupId, item.getGroupId());
            Assert.assertEquals("UserGroupDescription_" + currentGroupId, item.getGrp());
            Assert.assertEquals("UserGroupDescription_" + currentGroupId, item.getGrpDescription());
            Assert.assertNull(item.getUsername());
        }
    }

    
    @Test
    public void testSuccess_Fetch_NotFound() {
        when(this.mockPersistenceClient.retrieveList(any(UserGroup.class)))
              .thenReturn(null);
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserDto criteria = Rmt2OrmDtoFactory.getNewUserInstance();
        List<UserDto> results = null;
        try {
            results = api.getGroup(criteria);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
 
    @Test
    public void testError_Fetch_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveList(any(UserGroup.class)))
            .thenThrow(new DatabaseException("Error fetching user data"));
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserDto criteria = Rmt2OrmDtoFactory.getNewUserInstance();
        try {
            api.getGroup(criteria);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UserApiException);
            Assert.assertTrue(e.getCause() instanceof UserDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Fetch_Null_Criteria_Parameter() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        try {
            api.getGroup(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("User Group crtieria object is required", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Create() {
        when(this.mockPersistenceClient.retrieveList(any(UserGroup.class)))
             .thenReturn(null);
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserGroup obj = SecurityMockDataFactory.createOrmUserGroup(0);
        UserDto dto = Rmt2OrmDtoFactory.getGroupDtoInstance(obj);
        int results = 0;
        try {
            results = api.updateGroup(dto);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(SecurityMockDataFactory.TEST_GROUP_ID, results);
    }

    @Test
    public void testError_Create_DB_Access_Fault() {
        when(this.mockPersistenceClient.insertRow(any(UserGroup.class), eq(true)))
             .thenThrow(new DatabaseException("Error inserting User Group data"));
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserGroup obj = SecurityMockDataFactory.createOrmUserGroup(0);
        UserDto dto = Rmt2OrmDtoFactory.getGroupDtoInstance(obj);
        try {
            api.updateGroup(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UserApiException);
            Assert.assertTrue(e.getCause() instanceof UserDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testSuccess_Modify() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserGroup obj = SecurityMockDataFactory.createOrmUserGroup(1350);
        UserDto dto = Rmt2OrmDtoFactory.getGroupDtoInstance(obj);
        
        when(this.mockPersistenceClient.retrieveObject(any(UserGroup.class))).thenReturn(obj);
        
        int results = 0;
        try {
            results = api.updateGroup(dto);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Modify_DB_Access_Fault() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserGroup obj = SecurityMockDataFactory.createOrmUserGroup(1350);
        UserDto dto = Rmt2OrmDtoFactory.getGroupDtoInstance(obj);
        
        when(this.mockPersistenceClient.retrieveObject(any(UserGroup.class))).thenReturn(obj);
        when(this.mockPersistenceClient.updateRow(any(UserGroup.class)))
               .thenThrow(new DatabaseException("Error updating user data"));
        try {
            api.updateGroup(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UserApiException);
            Assert.assertTrue(e.getCause() instanceof UserDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Update_Data_Object_Null() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        try {
            api.updateGroup(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_Decription_Null() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserGroup obj = SecurityMockDataFactory.createOrmUserGroup(1350);
        obj.setDescription(null);
        UserDto dto = Rmt2OrmDtoFactory.getGroupDtoInstance(obj);
        try {
            api.updateGroup(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_Decription_Empty() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserGroup obj = SecurityMockDataFactory.createOrmUserGroup(1350);
        obj.setDescription("");
        UserDto dto = Rmt2OrmDtoFactory.getGroupDtoInstance(obj);
        try {
            api.updateGroup(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }

    @Test
    public void testSuccess_Delete() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        int results = 0;
        try {
            results = api.deleteGroup(1350);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Delete_DB_Access_Fault() {
        when(this.mockPersistenceClient.deleteRow(any(UserGroup.class)))
             .thenThrow(new DatabaseException("Error deleting user group data"));
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        try {
            api.deleteGroup(1350);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UserApiException);
            Assert.assertTrue(e.getCause() instanceof UserDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
}