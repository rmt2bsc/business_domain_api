package org.rmt2.api.authentication;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

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
import org.modules.authentication.Authenticator;
import org.modules.authentication.AuthenticatorFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.web.security.RMT2SecurityToken;

/**
 * Tests AppRole module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class AuthenticationApiTest extends SecurityMockData {

    

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
        
//        when(this.mockPersistenceClient.retrieveList(isA(VwAppRoles.class)))
//             .thenReturn(this.mockVwAppRolesData);
//        when(this.mockPersistenceClient.retrieveObject(any(AppRole.class)))
//             .thenReturn(this.mockAppRoleData.get(0));
//        when(this.mockPersistenceClient.insertRow(any(AppRole.class), eq(true)))
//             .thenReturn(SecurityMockDataFactory.TEST_APP_ROLE_ID);
//        when(this.mockPersistenceClient.updateRow(any(AppRole.class)))
//             .thenReturn(1);
//        when(this.mockPersistenceClient.deleteRow(any(AppRole.class)))
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
        
    }

    
}