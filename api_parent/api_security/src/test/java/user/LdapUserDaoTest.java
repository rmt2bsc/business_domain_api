package user;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.ldap.LdapUser;
import org.dao.user.UserDao;
import org.dao.user.UserDaoFactory;
import org.dto.UserDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.util.RMT2Date;

import junit.framework.Assert;

/**
 * Test the user operations of an LDAP implementation of the {@link UserDao}
 * 
 * @author Roy Terrell
 * 
 */
public class LdapUserDaoTest {

    private UserDaoFactory f;

    private UserDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new UserDaoFactory();
        this.dao = this.f.createLdapDao();
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    private void createData() {
        LdapUser dummy = null;
        UserDto dto = LdapDtoFactory.getUserDtoInstance(dummy);
        dto.setUsername("testuser1");
        dto.setFirstname("Halle");
        dto.setLastname("Berry");
        dto.setBirthDate(RMT2Date.stringToDate("12/14/1979"));
        dto.setStartDate(RMT2Date.stringToDate("03/01/2002"));
        dto.setGrp("Internal");
        dto.setSsn("123-22-4958");
        dto.setJobTitleDescription("Computer Analyst");
        dto.setAddress("9432 Live Oak Blvd.");
        dto.setCity("Dallas");
        dto.setState("TX");
        dto.setZip("75232");
        dto.setCountry("USA");
        dto.setEmail("danfouts@gte.net");
        dto.setUserDescription("This is test user #1");
        dto.setHomePhone("(214) 546-8374");
        dto.setMobilePhone("(318) 344-4948");
        dto.setTitleName("Mr");
        dto.setPassword("test1234");
        List<String> roles = new ArrayList<String>();
        roles.add("authadmin");
        roles.add("rmt2admin");
        dto.setRoles(roles);
        this.dao.maintainUser(dto);

        // craete 2nd user
        dto = LdapDtoFactory.getUserDtoInstance(dummy);
        dto.setUsername("testuser2");
        dto.setFirstname("Janet");
        dto.setLastname("Jackson");
        dto.setBirthDate(RMT2Date.stringToDate("05/16/1966"));
        dto.setStartDate(RMT2Date.stringToDate("10/03/2005"));
        dto.setGrp("External");
        dto.setSsn("436-42-6854");
        dto.setJobTitleDescription("Consumer Relations");
        dto.setAddress("73239 San Juan Ave.");
        dto.setCity("Lewisville");
        dto.setState("TX");
        dto.setZip("75067");
        dto.setCountry("USA");
        dto.setEmail("janetjackson@att.net");
        dto.setUserDescription("This is test user #2");
        dto.setHomePhone("9724958372");
        dto.setMobilePhone("2145559382");
        dto.setTitleName("Ms");
        dto.setPassword("test1234");
        roles = new ArrayList<String>();
        roles.add("rmt2admin");
        dto.setRoles(roles);
        this.dao.maintainUser(dto);

        // craete 3rd user
        dto = LdapDtoFactory.getUserDtoInstance(dummy);
        dto.setUsername("testuser3");
        dto.setFirstname("Kate");
        dto.setLastname("Beckensale");
        dto.setBirthDate(RMT2Date.stringToDate("10/26/1973"));
        dto.setStartDate(RMT2Date.stringToDate("11/11/2011"));
        dto.setGrp("Temporary");
        dto.setSsn("745-67-1234");
        dto.setJobTitleDescription("Software Engineer II");
        dto.setAddress("239 North Shore Dr.");
        dto.setCity("Carrollton");
        dto.setState("TX");
        dto.setZip("75143");
        dto.setCountry("USA");
        dto.setEmail("kbeckensale@msn.net");
        dto.setUserDescription("This is test user #3");
        dto.setHomePhone("9406947382");
        dto.setMobilePhone("2149478473");
        dto.setTitleName("Mrs");
        dto.setPassword("test1234");
        roles = new ArrayList<String>();
        roles.add("rmt2admin");
        roles.add("acctadmin");
        roles.add("authadmin");
        roles.add("comadmin");
        roles.add("ProjAdm");
        dto.setRoles(roles);
        this.dao.maintainUser(dto);
    }

    private void deleteData() {
        this.dao.deleteUser("testuser1");
        this.dao.deleteUser("testuser2");
        this.dao.deleteUser("testuser3");
    }

    @Test
    public void fetchAll() throws Exception {
        List<UserDto> list = null;
        try {
            list = this.dao.fetchUser();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
    }

    @Test
    public void fetchByLoginId() throws Exception {
        UserDto user = null;
        try {
            user = this.dao.fetchUser("testuser3");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(user);
        Assert.assertEquals(RMT2Date.stringToDate("10/26/1973"),
                user.getBirthDate());
        Assert.assertEquals("Kate", user.getFirstname());
        Assert.assertEquals("Beckensale", user.getLastname());
        Assert.assertEquals("Temporary", user.getGrp());
        Assert.assertEquals("Software Engineer II",
                user.getJobTitleDescription());
        Assert.assertEquals("testuser3", user.getUsername());
        Assert.assertEquals("745-67-1234", user.getSsn());
        Assert.assertEquals(RMT2Date.stringToDate("11/11/2011"),
                user.getStartDate());
        Assert.assertNull(user.getTerminationDate());
        Assert.assertEquals("This is test user #3", user.getUserDescription());
        Assert.assertEquals("kbeckensale@msn.net", user.getEmail());
        Assert.assertEquals("Carrollton", user.getCity());
        Assert.assertEquals("TX", user.getState());
        Assert.assertEquals("75143", user.getZip());
        Assert.assertEquals("9406947382", user.getHomePhone());
        Assert.assertEquals("2149478473", user.getMobilePhone());
        Assert.assertEquals("Mrs", user.getTitleName());
        Assert.assertEquals(5, user.getRoles().size());
    }

    @Test
    public void fetchByFirstName() throws Exception {
        UserDto user = null;
        List<UserDto> users = null;
        ;
        try {
            UserDto criteria = Rmt2OrmDtoFactory.getNewUserInstance();
            criteria.setFirstname("Janet");
            users = this.dao.fetchUser(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());

        user = users.get(0);
        Assert.assertEquals(RMT2Date.stringToDate("5/16/1966"),
                user.getBirthDate());
        Assert.assertEquals("Janet", user.getFirstname());
        Assert.assertEquals("Jackson", user.getLastname());
        Assert.assertEquals("External", user.getGrp());
        Assert.assertEquals("Consumer Relations", user.getJobTitleDescription());
        Assert.assertEquals("testuser2", user.getUsername());
        Assert.assertEquals("436-42-6854", user.getSsn());
        Assert.assertEquals(RMT2Date.stringToDate("10/03/2005"),
                user.getStartDate());
        Assert.assertNull(user.getTerminationDate());
        Assert.assertEquals("This is test user #2", user.getUserDescription());
        Assert.assertEquals("janetjackson@att.net", user.getEmail());
        Assert.assertEquals("Lewisville", user.getCity());
        Assert.assertEquals("TX", user.getState());
        Assert.assertEquals("75067", user.getZip());
        Assert.assertEquals("9724958372", user.getHomePhone());
        Assert.assertEquals("2145559382", user.getMobilePhone());
        Assert.assertEquals("Ms", user.getTitleName());
        Assert.assertEquals(1, user.getRoles().size());
    }

    @Test
    public void fetchByFirstAndLastName() throws Exception {
        UserDto user = null;
        List<UserDto> users = null;
        ;
        try {
            UserDto criteria = Rmt2OrmDtoFactory.getNewUserInstance();
            criteria.setFirstname("Janet");
            criteria.setLastname("Jackson");
            users = this.dao.fetchUser(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());

        user = users.get(0);
        Assert.assertEquals(RMT2Date.stringToDate("5/16/1966"),
                user.getBirthDate());
        Assert.assertEquals("Janet", user.getFirstname());
        Assert.assertEquals("Jackson", user.getLastname());
        Assert.assertEquals("External", user.getGrp());
        Assert.assertEquals("Consumer Relations", user.getJobTitleDescription());
        Assert.assertEquals("testuser2", user.getUsername());
        Assert.assertEquals("436-42-6854", user.getSsn());
        Assert.assertEquals(RMT2Date.stringToDate("10/03/2005"),
                user.getStartDate());
        Assert.assertNull(user.getTerminationDate());
        Assert.assertEquals("This is test user #2", user.getUserDescription());
        Assert.assertEquals("janetjackson@att.net", user.getEmail());
        Assert.assertEquals("Lewisville", user.getCity());
        Assert.assertEquals("TX", user.getState());
        Assert.assertEquals("75067", user.getZip());
        Assert.assertEquals("9724958372", user.getHomePhone());
        Assert.assertEquals("2145559382", user.getMobilePhone());
        Assert.assertEquals("Ms", user.getTitleName());
        Assert.assertEquals(1, user.getRoles().size());
    }

    @Test
    public void fetchBySsn() throws Exception {
        UserDto user = null;
        List<UserDto> users = null;
        ;
        try {
            UserDto criteria = Rmt2OrmDtoFactory.getNewUserInstance();
            criteria.setSsn("436-42-6854");
            users = this.dao.fetchUser(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());

        user = users.get(0);
        Assert.assertEquals(RMT2Date.stringToDate("5/16/1966"),
                user.getBirthDate());
        Assert.assertEquals("Janet", user.getFirstname());
        Assert.assertEquals("Jackson", user.getLastname());
        Assert.assertEquals("External", user.getGrp());
        Assert.assertEquals("Consumer Relations", user.getJobTitleDescription());
        Assert.assertEquals("testuser2", user.getUsername());
        Assert.assertEquals("436-42-6854", user.getSsn());
        Assert.assertEquals(RMT2Date.stringToDate("10/03/2005"),
                user.getStartDate());
        Assert.assertNull(user.getTerminationDate());
        Assert.assertEquals("This is test user #2", user.getUserDescription());
        Assert.assertEquals("janetjackson@att.net", user.getEmail());
        Assert.assertEquals("Lewisville", user.getCity());
        Assert.assertEquals("TX", user.getState());
        Assert.assertEquals("75067", user.getZip());
        Assert.assertEquals("9724958372", user.getHomePhone());
        Assert.assertEquals("2145559382", user.getMobilePhone());
        Assert.assertEquals("Ms", user.getTitleName());
        Assert.assertEquals(1, user.getRoles().size());
    }

    @Test
    public void update() throws Exception {
        UserDto user = null;
        String userName = "testuser1";

        // Retrieve user's profile
        try {
            user = this.dao.fetchUser(userName);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(user);
        Assert.assertEquals("Halle", user.getFirstname());
        Assert.assertEquals("Berry", user.getLastname());

        // Apply profile updates
        user.getRoles().clear();
        user.getRoles().add("acctadmin");
        user.getRoles().add("comadmin");
        user.getRoles().add("ProjAdm");
        user.setHomePhone("8174441234");
        user.setMobilePhone("3183444948");
        user.setOfficePhone("2145555555");
        user.setEmail("halleberry@neimanmarcus.com");
        try {
            this.dao.maintainUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // Verify change
        try {
            user = this.dao.fetchUser(userName);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(user);
        Assert.assertEquals("Halle", user.getFirstname());
        Assert.assertEquals("Berry", user.getLastname());
        Assert.assertEquals("8174441234", user.getHomePhone());
        Assert.assertEquals("3183444948", user.getMobilePhone());
        Assert.assertEquals("2145555555", user.getOfficePhone());
        Assert.assertEquals("halleberry@neimanmarcus.com", user.getEmail());

        return;
    }

    @Test
    public void fetchInvalidUser() throws Exception {
        UserDto dto = null;
        try {
            dto = this.dao.fetchUser("xxxxxxxx");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNull(dto);
    }

    @Test
    public void createUserWithInvalidAppRole() {
        LdapUser dummy = null;
        UserDto dto = LdapDtoFactory.getUserDtoInstance(dummy);
        dto.setUsername("testuser4");
        dto.setFirstname("Barack");
        dto.setLastname("Obama");
        dto.setBirthDate(RMT2Date.stringToDate("1/14/1960"));
        dto.setStartDate(RMT2Date.stringToDate("03/01/2002"));
        dto.setGrp("Internal");
        dto.setSsn("55-44-4958");
        dto.setJobTitleDescription("QA Analyst");
        dto.setAddress("9393 Summerset.");
        dto.setCity("Dallas");
        dto.setState("TX");
        dto.setZip("75241");
        dto.setCountry("USA");
        dto.setEmail("barackobama@gte.net");
        dto.setUserDescription("This is test user #4");
        dto.setHomePhone("8888888888");
        dto.setMobilePhone("3333333333");
        dto.setTitleName("Mr");
        dto.setPassword("test1234");
        List<String> roles = new ArrayList<String>();
        roles.add("authadmin");
        roles.add("rmt2admin2");
        dto.setRoles(roles);

        try {
            this.dao.maintainUser(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Assert.fail("Test failed, because test case completed without catching an invalid application role that was assoicated with the user");
    }
}
