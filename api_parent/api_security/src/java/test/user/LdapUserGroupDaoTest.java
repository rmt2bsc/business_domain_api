package user;

import java.util.List;

import junit.framework.Assert;

import org.dao.mapping.orm.ldap.LdapUserGroup;
import org.dao.user.UserDao;
import org.dao.user.UserDaoFactory;
import org.dto.UserDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the user group operations of an LDAP implementation of the
 * {@link UserDao}
 * 
 * @author Roy Terrell
 * 
 */
public class LdapUserGroupDaoTest {

    private UserDaoFactory f;

    private UserDao dao;

    private static final String PROP_GRP_DESC_MOD = "I modified the description of this user group.  Oh Yeah, this is a blast!";

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
        LdapUserGroup dummy = null;

        // create 1st user group
        UserDto dto = LdapDtoFactory.getUserDtoInstance(dummy);
        dto.setGrp("TestGroup1");
        dto.setGrpDescription("Test Group Description #1");
        this.dao.maintainGroup(dto);

        // craete 2nd user group
        dto = LdapDtoFactory.getUserDtoInstance(dummy);
        dto.setGrp("TestGroup2");
        dto.setGrpDescription("Test Group Description #2");
        this.dao.maintainGroup(dto);

        // craete 3rd user
        dto = LdapDtoFactory.getUserDtoInstance(dummy);
        dto.setGrp("TestGroup3");
        dto.setGrpDescription("Test Group Description #3");
        this.dao.maintainGroup(dto);
    }

    private void deleteData() {
        this.dao.deleteGroup("TestGroup1");
        this.dao.deleteGroup("TestGroup2");
        this.dao.deleteGroup("TestGroup3");
    }

    @Test
    public void fetchAll() throws Exception {
        List<UserDto> list = null;
        try {
            list = this.dao.fetchGroup();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        UserDto grp = list.get(0);
        Assert.assertNotNull(grp);
    }

    @Test
    public void fetchByName() throws Exception {
        UserDto grp = null;
        try {
            grp = this.dao.fetchGroup("TestGroup2");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(grp);
        Assert.assertEquals("TestGroup2", grp.getGrp());
        Assert.assertEquals("Test Group Description #2",
                grp.getGrpDescription());
    }

    @Test
    public void update() {
        UserDto grp = null;
        String grpName = "TestGroup3";

        // Retrieve user's profile
        try {
            grp = this.dao.fetchGroup(grpName);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(grp);
        Assert.assertEquals(grpName, grp.getGrp());
        Assert.assertEquals("Test Group Description #3",
                grp.getGrpDescription());

        // Apply profile updates
        grp.setGrpDescription(LdapUserGroupDaoTest.PROP_GRP_DESC_MOD);
        try {
            this.dao.maintainGroup(grp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // Verify change
        try {
            grp = this.dao.fetchGroup(grpName);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(grp);
        Assert.assertEquals(grpName, grp.getGrp());
        Assert.assertEquals(LdapUserGroupDaoTest.PROP_GRP_DESC_MOD,
                grp.getGrpDescription());

        return;
    }
}
