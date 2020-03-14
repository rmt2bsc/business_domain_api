package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.UserLogin;
import org.dto.UserDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

import com.api.util.RMT2Date;

/**
 * Test UserLogin adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class UserAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUserLoginOrm() {
        UserLogin o1 = SecurityMockDataFactory.createOrmUserLogin(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name", "test1234", "2018-01-01");
        UserDto dto = Rmt2OrmDtoFactory.getUserDtoInstance(o1);
        
        Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID, dto.getLoginUid());
        Assert.assertEquals(SecurityMockDataFactory.TEST_GROUP_ID, dto.getGroupId());
        Assert.assertEquals("user_name", dto.getUsername());
        Assert.assertEquals("test1234", dto.getPassword());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getStartDate());
        Assert.assertEquals("firstname_" + SecurityMockDataFactory.TEST_USER_ID, dto.getFirstname());
        Assert.assertEquals("lastname_" + SecurityMockDataFactory.TEST_USER_ID, dto.getLastname());
        Assert.assertEquals(dto.getFirstname() + "." + dto.getLastname() + "@gte.net", dto.getEmail());
        Assert.assertEquals(1, dto.getActive());
        Assert.assertEquals(5, dto.getTotalLogons());
        Assert.assertEquals("111-11-1111", dto.getSsn());
        
        try {
            UserLogin nullParm = null;
            dto = Rmt2OrmDtoFactory.getUserDtoInstance(nullParm);
            dto.setLoginUid(SecurityMockDataFactory.TEST_USER_ID);
            dto.setGroupId(SecurityMockDataFactory.TEST_GROUP_ID);
            dto.setUsername("user_name");
            dto.setPassword("test1234");
            dto.setStartDate(RMT2Date.stringToDate("2018-01-01"));
            dto.setFirstname("firstname_" + SecurityMockDataFactory.TEST_USER_ID);
            dto.setLastname("lastname_" + SecurityMockDataFactory.TEST_USER_ID);
            dto.setEmail(dto.getFirstname() + "." + dto.getLastname() + "@gte.net");
            dto.setGrpDescription("description_" + SecurityMockDataFactory.TEST_USER_ID);
            dto.setActive(1);
            dto.setTotalLogons(5);
            dto.setSsn("111-11-1111");
            

            Assert.assertEquals(SecurityMockDataFactory.TEST_USER_ID, dto.getLoginUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_GROUP_ID, dto.getGroupId());
            Assert.assertEquals("user_name", dto.getUsername());
            Assert.assertEquals("test1234", dto.getPassword());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getStartDate());
            Assert.assertEquals("firstname_" + SecurityMockDataFactory.TEST_USER_ID, dto.getFirstname());
            Assert.assertEquals("lastname_" + SecurityMockDataFactory.TEST_USER_ID, dto.getLastname());
            Assert.assertEquals(dto.getFirstname() + "." + dto.getLastname() + "@gte.net", dto.getEmail());
            Assert.assertEquals(1, dto.getActive());
            Assert.assertEquals(5, dto.getTotalLogons());
            Assert.assertEquals("111-11-1111", dto.getSsn());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for UserLogin Adapater");
        }
    }
}
