package org.rmt2.api.user;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.dao.mapping.orm.rmt2.UserLogin;
import org.dao.mapping.orm.rmt2.VwUser;
import org.dao.user.UserDaoException;
import org.dto.UserDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.SecurityConstants;
import org.modules.authentication.CryptoUtils;
import org.modules.users.InvalidUserInstanceException;
import org.modules.users.UserApi;
import org.modules.users.UserApiException;
import org.modules.users.UserApiFactory;
import org.modules.users.UsernameAleadyExistsException;
import org.powermock.api.mockito.PowerMockito;
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
 * Tests User module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, CryptoUtils.class })
public class UserApiTest extends SecurityMockData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(any(VwUser.class)))
             .thenReturn(this.mockVwUserData);
        when(this.mockPersistenceClient.insertRow(any(UserLogin.class), eq(true)))
             .thenReturn(SecurityMockDataFactory.TEST_USER_ID);
        when(this.mockPersistenceClient.updateRow(any(UserLogin.class)))
             .thenReturn(1);
        when(this.mockPersistenceClient.deleteRow(any(UserLogin.class)))
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
            results = api.getUser(criteria);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            UserDto item = results.get(ndx);
            int currentAppId = SecurityMockDataFactory.TEST_USER_ID + ndx;
            Assert.assertEquals(currentAppId, item.getLoginUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_GROUP_ID, item.getGroupId());
            Assert.assertEquals("UserName_" + currentAppId, item.getUsername());
        }
    }

    
    @Test
    public void testSuccess_Fetch_NotFound() {
        when(this.mockPersistenceClient.retrieveList(any(VwUser.class)))
              .thenReturn(null);
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserDto criteria = Rmt2OrmDtoFactory.getNewUserInstance();
        List<UserDto> results = null;
        try {
            results = api.getUser(criteria);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
 
    @Test
    public void testError_Fetch_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveList(any(VwUser.class)))
            .thenThrow(new DatabaseException("Error fetching user data"));
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserDto criteria = Rmt2OrmDtoFactory.getNewUserInstance();
        try {
            api.getUser(criteria);
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
            api.getUser(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("User crtieria object is required", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Create() {
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class)))
             .thenReturn(null);
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(0, 500, "user_name", "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        int results = 0;
        try {
            results = api.updateUser(dto);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID, results);
    }

    @Test
    public void testError_Create_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class)))
             .thenReturn(null);
        when(this.mockPersistenceClient.insertRow(any(UserLogin.class), eq(true)))
             .thenThrow(new DatabaseException("Error inserting User data"));
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(0, 500, "user_name", "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);;
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
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name", "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class)))
               .thenReturn(obj);
        
        int results = 0;
        try {
            results = api.updateUser(dto);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Modify_DB_Access_Fault() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name", "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class)))
               .thenReturn(obj);
        when(this.mockPersistenceClient.updateRow(any(UserLogin.class)))
               .thenThrow(new DatabaseException("Error updating user data"));
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UserApiException);
            Assert.assertTrue(e.getCause() instanceof UserDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testError_Modify_NotFound_Fault() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name", "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class)))
               .thenReturn(null);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof NotFoundException);
            Assert.assertEquals("User Profile does not exists [user id=" + dto.getLoginUid() + "]", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Modify_Username_Already_Exists() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name_changed", "test1234", "2018-01-01");
        UserLogin origRec = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name", "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class))).thenReturn(origRec);
        
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UsernameAleadyExistsException);
            String msg =  "User Profile cannot be created or modified due to username, "
                    + dto.getUsername() + ",  already exists!";
            Assert.assertEquals(msg, e.getMessage());
        }
    }
    
    @Test
    public void testError_Modify_DB_Access_Fetch_Fault() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name", "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class)))
              .thenThrow(new DatabaseException("Error fetching user data"));
        try {
            api.updateUser(dto);
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
            api.updateUser(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_UserName_Null() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, null, "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_UserName_Empty() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "", "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_FirstName_Null() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name", "test1234", "2018-01-01");
        obj.setFirstname(null);
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_FirstName_Empty() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name", "test1234", "2018-01-01");
        obj.setFirstname("");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_LastName_Null() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name", "test1234", "2018-01-01");
        obj.setLastname(null);
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_LastName_Empty() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 500, "user_name", "test1234", "2018-01-01");
        obj.setLastname("");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_Password_Null() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(0, 500, "user_name", null, "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_Password_Empty() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(0, 500, "user_name", "", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_GroupId_Negative() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, -500, "user_name", "", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_GroupId_Zero() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        UserLogin obj = SecurityMockDataFactory.createOrmUserLogin(1350, 0, "user_name", "", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(obj);
        try {
            api.updateUser(dto);
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
            results = api.deleteUser(1350);
        } catch (UserApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Delete_DB_Access_Fault() {
        when(this.mockPersistenceClient.deleteRow(any(UserLogin.class)))
             .thenThrow(new DatabaseException("Error deleting user data"));
        
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        try {
            api.deleteUser(1350);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UserApiException);
            Assert.assertTrue(e.getCause() instanceof UserDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void testSuccess_ChangePassword() {
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class))).thenReturn(this.mockUserLoginData.get(0));
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        String username = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        String newPassword = "NewPassword";
        try {
            api.changePassword(username, newPassword);
            Mockito.verify(this.mockPersistenceClient).retrieveObject(isA(UserLogin.class));
        } catch (UserApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testError_ChangePassword_API_Error() {
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class)))
                .thenThrow(new DatabaseException("API Error changing user password"));

        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        String username = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        String newPassword = "NewPassword";
        try {
            api.changePassword(username, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UserApiException);
            Assert.assertTrue(e.getCause() instanceof UserDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void testError_ChangePassword_Encryption_Error() {
        String username = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        String newPassword = "NewPassword";
        
        when(this.mockPersistenceClient.retrieveObject(any(UserLogin.class))).thenReturn(this.mockUserLoginData.get(0));

        PowerMockito.mockStatic(CryptoUtils.class);
        try {
            when(CryptoUtils.computeHash(eq(username + newPassword))).thenThrow(
                    new NoSuchAlgorithmException("Password encryption error"));
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
            Assert.fail(e1.getMessage());
        }

        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        try {
            api.changePassword(username, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UserApiException);
            Assert.assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }

    @Test
    public void testValidation_ChangePassword_Username_Null() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        String newPassword = "NewPassword";
        try {
            api.changePassword(null, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidUserInstanceException);
        }
    }

    @Test
    public void testValidation_ChangePassword_Username_Blank() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        String newPassword = "NewPassword";
        try {
            api.changePassword("", newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidUserInstanceException);
        }
    }

    @Test
    public void testValidation_ChangePassword_NewPassword_Null() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        String username = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        try {
            api.changePassword(username, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidUserInstanceException);
        }
    }

    @Test
    public void testValidation_ChangePassword_NewPassword_Blank() {
        UserApi api = UserApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        String username = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        try {
            api.changePassword(username, "");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidUserInstanceException);
        }
    }
}