package old.tests.authentication;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modules.authentication.AuthenticationException;
import org.modules.authentication.Authenticator;
import org.modules.authentication.AuthenticatorFactory;
import org.modules.authentication.SecurityTokenAccessException;

import com.api.security.User;
import com.api.security.authentication.web.LogoutException;
import com.api.web.security.RMT2SecurityToken;

/**
 * Tests the the authentication api module.
 * 
 * @author rterrell
 * 
 */
public class AuthenticationApiTest {
    private AuthenticatorFactory f;

    private Authenticator a;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new AuthenticatorFactory();
        this.a = this.f.createAuthenticator();
        this.a.setApiUser("testuser");

        // try {
        // RMT2File.loadSystemProperties(TestConstants.SYSTEM_PROPS_FILE);
        // }
        // catch (Exception e) {
        // e.printStackTrace();
        // }

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.a = null;
        this.f = null;
    }

    @Test
    public void authenticate() {
        String userName = "admin";
        String pw = "drum7777";
        User u = null;
        try {
            RMT2SecurityToken token = (RMT2SecurityToken) this.a.authenticate(
                    userName, pw);
            if (token.getUser() instanceof User) {
                u = (User) token.getUser();
                System.out.println(u.getFirstname());
                System.out.println(u.getLastname());
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(userName, u.getLoginId());
    }

    @Test
    public void authenticateSingleSignOn() {
        String userName = "admin";
        String pw = "drum7777";
        RMT2SecurityToken token = null;
        int count = 0;
        try {
            token = (RMT2SecurityToken) this.a.authenticate(userName, pw);
            count++;
            token = (RMT2SecurityToken) this.a.authenticate(userName);
            count++;
            token = (RMT2SecurityToken) this.a.authenticate(userName);
            count++;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(token);
        User u = token.getUser();
        Assert.assertEquals(userName, u.getLoginId());
        Assert.assertEquals(count, u.getAppCount());
    }

    @Test
    public void logout() {
        RMT2SecurityToken token = null;
        // First, login user
        try {
            token = (RMT2SecurityToken) this.a
                    .authenticate("admin", "drum7777");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(token);

        // lastly, log out user
        try {
            int rc = a.logout(token.getLoginId());
            System.out
                    .println("Total number of applications the user logged out of: "
                            + rc);
        } catch (LogoutException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getSecurityTokenForInvalid() {
        try {
            this.a.getSecurityToken(null);
        } catch (SecurityTokenAccessException e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Fetch security token with null user failed");
    }

    @Test
    public void getSecurityTokenWhenEmpty() {
        Object token = null;
        try {
            token = this.a.getSecurityToken("admin");
        } catch (SecurityTokenAccessException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNull(token);
    }

    @Test
    public void getSecurityTokenForLoggedInUser() {
        String userName = "admin";
        String pw = "drum7777";
        RMT2SecurityToken token = null;
        try {
            token = this.a.authenticate(userName, pw);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(token);

        token = null;
        try {
            token = this.a.getSecurityToken(userName);
        } catch (SecurityTokenAccessException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(token);
        User u = token.getUser();
        Assert.assertNotNull(u);
        Assert.assertEquals(userName, u.getLoginId());
    }

    @Test
    public void getSecurityTokenForUserNotLoggedIn() {
        String userName = "admin";
        String pw = "drum7777";
        RMT2SecurityToken token = null;
        try {
            token = this.a.authenticate(userName, pw);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(token);

        token = null;
        try {
            token = this.a.getSecurityToken("@@@@@");
        } catch (SecurityTokenAccessException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNull(token);
    }

    @Test
    public void resetUserApplicationLogonCount() {
        String userName = "admin";
        String pw = "drum7777";
        RMT2SecurityToken token = null;
        int count = 0;
        try {
            token = (RMT2SecurityToken) this.a.authenticate(userName, pw);
            count++;
            token = (RMT2SecurityToken) this.a.authenticate(userName);
            count++;
            token = (RMT2SecurityToken) this.a.authenticate(userName);
            count++;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(token);
        User u = token.getUser();
        Assert.assertEquals(userName, u.getLoginId());
        Assert.assertEquals(count, u.getAppCount());

        boolean rc = false;
        try {
            rc = this.a.resetLoginCount(userName);
        } catch (SecurityTokenAccessException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc);
        Assert.assertEquals(0, u.getAppCount());
    }

    @Test
    public void verifyAuthenticationOfUserLoggedIn() {
        String userName = "admin";
        String pw = "drum7777";
        RMT2SecurityToken token = null;
        try {
            token = (RMT2SecurityToken) this.a.authenticate(userName, pw);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(token);
        User u = token.getUser();
        Assert.assertEquals(userName, u.getLoginId());

        boolean rc = this.a.isAuthenticated(userName);
        Assert.assertTrue(rc);
    }

    @Test
    public void verifyAuthenticationOfUserNotLoggedIn() {
        String userName = "admin";
        String pw = "drum7777";
        RMT2SecurityToken token = null;
        try {
            token = (RMT2SecurityToken) this.a.authenticate(userName, pw);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(token);
        User u = token.getUser();
        Assert.assertEquals(userName, u.getLoginId());

        boolean rc = this.a.isAuthenticated("@@@@@@");
        Assert.assertFalse(rc);
    }

    @Test
    public void performSuccessfulUseAuthorization() {
        String userName = "admin";
        List<String> requiredAppRoles = new ArrayList<String>();
        requiredAppRoles.add("rmt2admin");
        requiredAppRoles.add("acctadmin");
        try {
            this.a.authorize(userName, requiredAppRoles);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return;
    }

    @Test
    public void performFailedUseAuthorization() {
        String userName = "admin";
        List<String> requiredAppRoles = new ArrayList<String>();
        requiredAppRoles.add("@@@@@@");
        try {
            this.a.authorize(userName, requiredAppRoles);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("User authorization test failed...expected user to not be authorized based on required roles");
        return;
    }

    @Test
    public void authorizeInvalidUser() {
        String userName = "@@@admin";
        List<String> requiredAppRoles = new ArrayList<String>();
        requiredAppRoles.add("rmt2admin");
        try {
            this.a.authorize(userName, requiredAppRoles);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("User authorization test failed...expected user to not be authorized based on required roles");
        return;
    }

    @Test
    public void authorizeWithEmptyRequiredRoles() {
        String userName = "admin";
        List<String> requiredAppRoles = new ArrayList<String>();
        try {
            this.a.authorize(userName, requiredAppRoles);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("User authorization test failed...expected user to not be authorized based on required roles");
        return;
    }

    @Test
    public void authorizeWithNullRequiredRoles() {
        String userName = "admin";
        List<String> requiredAppRoles = new ArrayList<String>();
        try {
            this.a.authorize(userName, requiredAppRoles);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("User authorization test failed...expected user to not be authorized based on required roles");
        return;
    }

}
