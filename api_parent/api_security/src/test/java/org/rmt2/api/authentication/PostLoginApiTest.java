package org.rmt2.api.authentication;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.UserLogin;
import org.dao.mapping.orm.rmt2.VwUser;
import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.SecurityConstants;
import org.modules.SecurityModuleException;
import org.modules.authentication.AuthenticationException;
import org.modules.authentication.Authenticator;
import org.modules.authentication.AuthenticatorFactory;
import org.modules.authentication.UsernameInvalidException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.security.authentication.web.LogoutException;
import com.api.web.security.RMT2SecurityToken;

/**
 * Tests various user authentication functioanality that occurs post login in the Security API.
 * <p>
 * Test single sign on, authentication checks, and logging out of system. 
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class PostLoginApiTest extends SecurityMockData {

    private static final String TEST_UID = "john.smith";
    private static final String TEST_PASSWORD = "test1234";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<VwUser> buildExtUserProfile(String userName, String password) {
        List<VwUser> list = new ArrayList<>();
        VwUser o = SecurityMockDataFactory.createOrmVwUser(
                SecurityMockDataFactory.TEST_USER_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, userName, password,
                "2018-01-01",
                "ShortName_" + SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        return list;

    }
    
    
    private RMT2SecurityToken performIntialLogin(String userName, String password) {
        List<VwUser> userList = this.buildExtUserProfile(userName, password);
        when(this.mockPersistenceClient.retrieveList(isA(VwUser.class)))
               .thenReturn(userList);
        when(this.mockPersistenceClient.retrieveList(isA(VwUserAppRoles.class)))
               .thenReturn(this.mockVwUserAppRolesData);
        when(this.mockPersistenceClient.updateRow(isA(UserLogin.class)))
               .thenReturn(1);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        RMT2SecurityToken results = null;
        try {
            results = api.authenticate(userName, password);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected to be thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(userName, results.getLoginId());
        Assert.assertEquals(6, results.getTotalLogons());
        Assert.assertEquals(5, results.getRoles().size());
        Assert.assertEquals(1, results.getAppCount());
        return results;
    }
    
    
    @Test
    public void testSuccess_SingleSignOn_Authenticate() {
        RMT2SecurityToken firstTimeToken = this.performIntialLogin(TEST_UID, TEST_PASSWORD);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        RMT2SecurityToken results = null;
        try {
            results = api.authenticate(TEST_UID);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected to be thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results, firstTimeToken);
        Assert.assertEquals(2, results.getAppCount());
        Assert.assertEquals(6, results.getTotalLogons());
        Assert.assertEquals(5, results.getRoles().size());
        
    }

    @Test
    public void testError_SingleSignOn_Authenticate_Incorrect_Username() {
        this.performIntialLogin(TEST_UID, TEST_PASSWORD);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "bad_username";
        try {
            api.authenticate(userName);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthenticationException);
            Assert.assertEquals("Single sign on authentication failed for user, " + userName, e.getMessage());
        }
    }
    
    
    @Test
    public void testValidation_FirstTime_Authenticate_Username_Null() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = null;
        try {
            api.authenticate(userName);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UsernameInvalidException);
            Assert.assertEquals("Username is required for Single Sign On authentication", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_FirstTime_Authenticate_Username_Empty() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "";
        try {
            api.authenticate(userName);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UsernameInvalidException);
            Assert.assertEquals("Username is required for Single Sign On authentication", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_User_Authentication_Check() {
        this.performIntialLogin(TEST_UID, TEST_PASSWORD);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        boolean results = false;
        results = api.isAuthenticated(TEST_UID);
        Assert.assertNotNull(results);
        Assert.assertTrue(results);
    }
    
    @Test
    public void testError_User_Authentication_Check() {
        this.performIntialLogin(TEST_UID, TEST_PASSWORD);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        boolean results = false;
        results = api.isAuthenticated("bad_username");
        Assert.assertNotNull(results);
        Assert.assertFalse(results);
    }
    
    @Test
    public void testValidation_User_Authentication_Check_Null_UserName() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        try {
            api.isAuthenticated(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UsernameInvalidException);
            Assert.assertEquals("Username is required for authentication check", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_User_Authentication_Check_Empty_UserName() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        try {
            api.isAuthenticated("");
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UsernameInvalidException);
            Assert.assertEquals("Username is required for authentication check", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Logout() {
        RMT2SecurityToken firstTimeToken = this.performIntialLogin(TEST_UID, TEST_PASSWORD);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        int results = 0;
        try {
            results = api.logout(TEST_UID);
        } catch (LogoutException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected to be thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(0, results);
        Assert.assertEquals(0, firstTimeToken.getAppCount());
        boolean isLoggedIn = api.isAuthenticated(TEST_UID);
        Assert.assertFalse(isLoggedIn);
    }
    
    @Test
    public void testSuccess_Logout_Multiple_Times() {
        RMT2SecurityToken firstTimeToken = this.performIntialLogin(TEST_UID, TEST_PASSWORD);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        int results = 0;
        try {
            api.authenticate(TEST_UID);
            api.authenticate(TEST_UID);
            api.authenticate(TEST_UID);
           
            results = api.logout(TEST_UID);
            Assert.assertNotNull(results);
            Assert.assertEquals(3, results);
            
            results = api.logout(TEST_UID);
            Assert.assertNotNull(results);
            Assert.assertEquals(2, results);
            
            results = api.logout(TEST_UID);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results);
            
            results = api.logout(TEST_UID);
            Assert.assertNotNull(results);
            Assert.assertEquals(0, results);
            
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected to be thrown");
        }
        
        Assert.assertEquals(0, firstTimeToken.getAppCount());
        boolean isLoggedIn = api.isAuthenticated(TEST_UID);
        Assert.assertFalse(isLoggedIn);
    }
    
    @Test
    public void testSuccess_Logout_Multiple_Times_When_Multiple_Users_Exists() {
        // add random users
        this.performIntialLogin("user1", "test1234");
        this.performIntialLogin("user2", "test1234");
        this.performIntialLogin("user3", "test1234");
        
        // add the user that is target for this test.
        RMT2SecurityToken firstTimeToken = this.performIntialLogin(TEST_UID, TEST_PASSWORD);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        int results = 0;
        try {
            api.authenticate(TEST_UID);
            api.authenticate(TEST_UID);
            api.authenticate(TEST_UID);
           
            results = api.logout(TEST_UID);
            Assert.assertNotNull(results);
            Assert.assertEquals(3, results);
            
            results = api.logout(TEST_UID);
            Assert.assertNotNull(results);
            Assert.assertEquals(2, results);
            
            results = api.logout(TEST_UID);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results);
            
            results = api.logout(TEST_UID);
            Assert.assertNotNull(results);
            Assert.assertEquals(0, results);
            
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected to be thrown");
        }
        
        Assert.assertEquals(0, firstTimeToken.getAppCount());
        boolean isLoggedIn = api.isAuthenticated(TEST_UID);
        Assert.assertFalse(isLoggedIn);
        
        // Check to see if random user exists.  Should be there
        isLoggedIn = api.isAuthenticated("user1");
        Assert.assertTrue(isLoggedIn);
        isLoggedIn = api.isAuthenticated("user2");
        Assert.assertTrue(isLoggedIn);
        isLoggedIn = api.isAuthenticated("user3");
        Assert.assertTrue(isLoggedIn);
        
        try {
            results = api.logout("user1");
            Assert.assertNotNull(results);
            Assert.assertEquals(0, results);
            
            results = api.logout("user2");
            Assert.assertNotNull(results);
            Assert.assertEquals(0, results);
            
            results = api.logout("user3");
            Assert.assertNotNull(results);
            Assert.assertEquals(0, results);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected to be thrown");
        }
        
        // Check to see if random user were logged out.
        isLoggedIn = api.isAuthenticated("user1");
        Assert.assertFalse(isLoggedIn);
        isLoggedIn = api.isAuthenticated("user2");
        Assert.assertFalse(isLoggedIn);
        isLoggedIn = api.isAuthenticated("user3");
        Assert.assertFalse(isLoggedIn);
    }
    
    @Test
    public void testValidation_Logout_UserName_Null() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        try {
            api.logout(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UsernameInvalidException);
            Assert.assertEquals("Username is required for logout operation", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Logout_UserName_Empty() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        try {
            api.logout("");
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UsernameInvalidException);
            Assert.assertEquals("Username is required for logout operation", e.getMessage());
        }
    }
}