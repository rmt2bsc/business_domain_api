package org.rmt2.api.authentication;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.SecurityConstants;
import org.modules.SecurityModuleException;
import org.modules.authentication.Authenticator;
import org.modules.authentication.AuthenticatorFactory;
import org.modules.authentication.AuthorizationException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests user authorization functioanality in the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class AuthorizationApiTest extends SecurityMockData {

    private static final String[] USER_APP_ROLE_CODES_SOMEMATCH = {
            "AppRoleCode_" + SecurityMockDataFactory.TEST_APP_ROLE_ID,
            "AppRoleCode_" + (SecurityMockDataFactory.TEST_APP_ROLE_ID + 2),
            "AppRoleCode_" + (SecurityMockDataFactory.TEST_APP_ROLE_ID + 3),
            "AppRoleCode_" + SecurityMockDataFactory.TEST_APP_ID,
            "AppRoleCode_" + SecurityMockDataFactory.TEST_RESOURCE_ID };
    
    private static final String[] USER_APP_ROLE_CODES_NOMATCH = {
            "AppRoleCode1", "AppRoleCode2", "AppRoleCode3", "AppRoleCode4",
            "AppRoleCode5" };

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(isA(VwUserAppRoles.class)))
             .thenReturn(this.mockVwUserAppRolesData);
        
        
//        when(this.mockPersistenceClient.retrieveList(isA(VwUser.class)))
//             .thenReturn(this.mockVwUserSingleData);
//        when(this.mockPersistenceClient.updateRow(isA(UserLogin.class)))
//             .thenReturn(1);
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
    public void testSuccess_Authorize() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        boolean results = false;
        try {
            String userName = "user_name";
            List<String> requiredRoles = Arrays.asList(USER_APP_ROLE_CODES_SOMEMATCH);
            results = api.authorize(userName, requiredRoles);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected to be thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results);
    }
    
    @Test
    public void testSuccess_Not_Authorize() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        boolean results = false;
        try {
            String userName = "user_name";
            List<String> requiredRoles = Arrays.asList(USER_APP_ROLE_CODES_NOMATCH);
            results = api.authorize(userName, requiredRoles);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected to be thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertFalse(results);
    }
    
    @Test
    public void testError_Authorize_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveList(isA(VwUserAppRoles.class)))
               .thenThrow(DatabaseException.class);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "user_name";
        List<String> requiredRoles = Arrays.asList(USER_APP_ROLE_CODES_NOMATCH);
        try {
            api.authorize(userName, requiredRoles);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthorizationException);
            Assert.assertEquals("Unable to authorize user, " + userName
                            + ", due to user application roles are unavailable",
                    e.getMessage());
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            Assert.assertEquals("Unable to retrieve user application roles for " + userName,
                    e.getCause().getMessage());
        }
    }
    
   
    @Test
    public void testValidation_Authorize_Username_Null() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = null;
        List<String> requiredRoles = Arrays.asList(USER_APP_ROLE_CODES_NOMATCH);
        try {
            api.authorize(userName, requiredRoles);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthorizationException);
            Assert.assertEquals("Username is required for authorziation", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Authorize_Username_Empty() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "";
        List<String> requiredRoles = Arrays.asList(USER_APP_ROLE_CODES_NOMATCH);
        try {
            api.authorize(userName, requiredRoles);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthorizationException);
            Assert.assertEquals("Username is required for authorziation", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Authorize_RequiredRoles_Null() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "user_name";
        List<String> requiredRoles = null;
        try {
            api.authorize(userName, requiredRoles);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthorizationException);
            Assert.assertEquals("A list of required application roles are required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Authorize_RequiredRoles_Empty() {
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "user_name";
        List<String> requiredRoles = new ArrayList<>();
        try {
            api.authorize(userName, requiredRoles);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthorizationException);
            Assert.assertEquals("A list of required application roles are required", e.getMessage());
        }
    }
    
    @Test
    public void testError_Authorize_UserAppRoles_Unavailable() {
        when(this.mockPersistenceClient.retrieveList(isA(VwUserAppRoles.class)))
               .thenReturn(null);
        
        Authenticator api = AuthenticatorFactory.createApi(SecurityConstants.APP_NAME);
        String userName = "user_name";
        List<String> requiredRoles = Arrays.asList(USER_APP_ROLE_CODES_NOMATCH);
        try {
            api.authorize(userName, requiredRoles);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AuthorizationException);
            Assert.assertEquals("Unable to locate any User Application Roles for user, " + userName, e.getMessage());
        }
    }
}