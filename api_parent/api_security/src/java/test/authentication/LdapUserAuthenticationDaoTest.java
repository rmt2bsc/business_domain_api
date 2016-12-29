package authentication;

import junit.framework.Assert;

import org.dao.authentication.AuthenticationDao;
import org.dao.authentication.AuthenticationDaoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.api.security.User;
import com.util.RMT2Date;

/**
 * Test the LDAP implementation of the {@link AuthenticationDao}
 * 
 * @author Roy Terrell
 * 
 */
public class LdapUserAuthenticationDaoTest {

    private AuthenticationDaoFactory f;
    private AuthenticationDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new AuthenticationDaoFactory();
        this.dao = this.f.createLdapDao();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    @Test
    public void loginLogoutStockUser() throws Exception {
        User user = null;
        try {
            user = this.dao.loginUser("admin", "drum7777");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(user);
        Assert.assertEquals("Roy Terrell", user.getShortname());
        Assert.assertEquals(RMT2Date.stringToDate("5/17/1966"),
                user.getBirthDate());
        Assert.assertEquals("Roy", user.getFirstname());
        Assert.assertEquals("Terrell", user.getLastname());
        Assert.assertEquals("Internal", user.getGrp());
        Assert.assertEquals("Built-In System Administrator",
                user.getJobTitleDescription());
        Assert.assertEquals("admin", user.getLoginId());
        Assert.assertEquals("xxx-xx-xxxx", user.getSsn());
        Assert.assertEquals(RMT2Date.stringToDate("08/14/1997"),
                user.getStartDate());
        Assert.assertNull(user.getTerminationDate());
        Assert.assertEquals(
                "This is the administrator of the system with GOD like privileges.",
                user.getUserDescription());
        Assert.assertEquals("rmt2bsc2@verizon.net", user.getEmail());
        Assert.assertEquals("Flower Mound", user.getCity());
        Assert.assertEquals("TX", user.getState());
        Assert.assertEquals("75028", user.getZip());
        Assert.assertEquals("972.691.8810", user.getHomePhone());
        Assert.assertEquals("972.335.7125", user.getOfficePhone());
        Assert.assertEquals("972.335.7125", user.getFax());
        Assert.assertEquals("Mr", user.getTitleName());
        Assert.assertTrue(user.getTotalLogons() > 0);
        Assert.assertEquals(0, user.getLoggedIn());
        Assert.assertEquals(6, user.getRoles().size());
        // Assert.assertEquals("xxxxxx", user.getShortname());
        // Assert.assertEquals("xxxxxx", user.getShortname());

    }

    @Test
    public void loginLogoutMultiStockUser() throws Exception {
        User user = null;
        try {
            this.dao.loginUser("admin", "drum7777");
            user = this.dao.loginUser("admin", "drum7777");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getTotalLogons() > 0);
    }

    @Test
    public void bindWithZeroParameters() throws Exception {
        try {
            this.dao.loginUser(null, null);
        } catch (Exception e) {
            return;
        }
        Assert.fail("Test failed...expected authentication to failed since user id and password parameters were not supplied");
    }

    @Test
    public void bindInvalidUser() throws Exception {
        try {
            this.dao.loginUser("xxxxxx", "123456");
        } catch (Exception e) {
            return;
        }
        Assert.fail("Test failed...expected user not to be authenticated");
    }

    @Test
    public void bindInvalidPassword() throws Exception {
        try {
            this.dao.loginUser("admin", "123456");
        } catch (Exception e) {
            return;
        }
        Assert.fail("Test failed...expected user with incorrect password not to be authenticated");
    }

}
