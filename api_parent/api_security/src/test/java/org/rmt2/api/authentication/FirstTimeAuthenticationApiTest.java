package org.rmt2.api.authentication;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import org.dao.SecurityDaoException;
import org.dao.mapping.orm.rmt2.UserLogin;
import org.dao.mapping.orm.rmt2.VwUser;
import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.dao.user.UserDaoException;
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
import org.modules.authentication.LoginFailedException;
import org.modules.authentication.PasswordInvalidException;
import org.modules.authentication.UsernameInvalidException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.NotFoundException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.web.security.RMT2SecurityToken;

/**
 * Tests user authentication functioanality for first time login in the Security
 * API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class FirstTimeAuthenticationApiTest extends SecurityMockData {

    

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
        when(this.mockPersistenceClient.updateRow(isA(UserLogin.class)))
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
    public void testSuccess_FirstTime_Authenticate() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        RMT2SecurityToken results = null;
        try {
            String userName = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
            String password = "test1234" + SecurityMockDataFactory.TEST_USER_ID;
            results = api.authenticate(userName, password);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("UserName_" + SecurityMockDataFactory.TEST_USER_ID, results.getLoginId());
        Assert.assertEquals(6, results.getTotalLogons());
        Assert.assertEquals(5, results.getRoles().size());
        
    }

    @Test
    public void testError_FirstTime_Authenticate_Incorrect_Username() {
        when(this.mockPersistenceClient.retrieveList(isA(VwUser.class)))
               .thenReturn(null);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "bad_username";
        String password = "test1234" + SecurityMockDataFactory.TEST_USER_ID;
        try {
            api.authenticate(userName, password);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthenticationException);
            Assert.assertEquals("User authentication failed due to invalid user id and/or password", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof NotFoundException);
            Assert.assertEquals("User profile does not exist for user, " + userName, e.getCause().getMessage());
        }
    }
    
    @Test
    public void testError_FirstTime_Authenticate_Incorrect_Password() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        String password = "bad_password";
        try {
            api.authenticate(userName, password);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthenticationException);
            Assert.assertEquals("User authentication failed due to invalid user id and/or password", e.getMessage());
            Assert.assertTrue(e.getCause() instanceof LoginFailedException);
            Assert.assertEquals("Password is incorrect", e.getCause().getMessage());
        }
    }
    
    @Test
    public void testError_FirstTime_Authenticate_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveList(isA(VwUser.class)))
               .thenThrow(DatabaseException.class);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        String password = "test1234" + SecurityMockDataFactory.TEST_USER_ID;
        try {
            api.authenticate(userName, password);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthenticationException);
            Assert.assertTrue(e.getCause() instanceof UserDaoException);
        }
    }
    
    @Test
    public void testError_FirstTime_Authenticate_DB_Access_Fault_During_DoPostLogin1() {
        when(this.mockPersistenceClient.retrieveList(isA(VwUserAppRoles.class)))
               .thenThrow(DatabaseException.class);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        String password = "test1234" + SecurityMockDataFactory.TEST_USER_ID;
        try {
            api.authenticate(userName, password);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthenticationException);
            Assert.assertTrue(e.getCause() instanceof SecurityDaoException);
        }
    }
    
    @Test
    public void testError_FirstTime_Authenticate_DB_Access_Fault_During_DoPostLogin2() {
        when(this.mockPersistenceClient.updateRow(isA(UserLogin.class)))
               .thenThrow(DatabaseException.class);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        String password = "test1234" + SecurityMockDataFactory.TEST_USER_ID;
        try {
            api.authenticate(userName, password);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthenticationException);
            Assert.assertTrue(e.getCause() instanceof UserDaoException);
        }
    }
    
    @Test
    public void testValidation_FirstTime_Authenticate_Username_Null() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = null;
        String password = "test1234" + SecurityMockDataFactory.TEST_USER_ID;
        try {
            api.authenticate(userName, password);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UsernameInvalidException);
            Assert.assertEquals("Username is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_FirstTime_Authenticate_Username_Empty() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "";
        String password = "test1234" + SecurityMockDataFactory.TEST_USER_ID;
        try {
            api.authenticate(userName, password);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof UsernameInvalidException);
            Assert.assertEquals("Username is required", e.getMessage());
        }
    }
    
    @Test
    public void testError_FirstTime_Authenticate_Password_Null() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        String password = null;
        try {
            api.authenticate(userName, password);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PasswordInvalidException);
            Assert.assertEquals("Password is required", e.getMessage());
        }
    }
    
    @Test
    public void testError_FirstTime_Authenticate_Password_Empty() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "UserName_" + SecurityMockDataFactory.TEST_USER_ID;
        String password = null;
        try {
            api.authenticate(userName, password);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PasswordInvalidException);
            Assert.assertEquals("Password is required", e.getMessage());
        }
    }
}