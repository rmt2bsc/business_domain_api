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
import com.api.web.security.RMT2SecurityToken;

/**
 * Tests user authentication functioanality for single sign on login in the Security
 * API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class SingleSignOnAuthenticationApiTest extends SecurityMockData {

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
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results, firstTimeToken);
        Assert.assertEquals(2, results.getAppCount());
        Assert.assertEquals(6, results.getTotalLogons());
        Assert.assertEquals(5, results.getRoles().size());
        
    }

    @Test
    public void testError_SingleSignOn_Authenticate_Incorrect_Username() {
        RMT2SecurityToken firstTimeToken = this.performIntialLogin(TEST_UID, TEST_PASSWORD);
        
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
}