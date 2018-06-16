package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.UserGroup;
import org.dto.UserDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

/**
 * Test User Group adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class UserGroupAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUserGroupOrm() {
        UserGroup o1 = SecurityMockDataFactory.createOrmUserGroup(SecurityMockDataFactory.TEST_GROUP_ID);
        UserDto dto = Rmt2OrmDtoFactory.getGroupDtoInstance(o1);
        
        Assert.assertEquals(0, dto.getLoginUid());
        Assert.assertEquals(SecurityMockDataFactory.TEST_GROUP_ID, dto.getGroupId());
        Assert.assertEquals("UserGroupDescription_" + SecurityMockDataFactory.TEST_GROUP_ID, dto.getGrpDescription());
        Assert.assertEquals("UserGroupDescription_" + SecurityMockDataFactory.TEST_GROUP_ID, dto.getGrp());
        Assert.assertNull(dto.getUsername());
        Assert.assertNull(dto.getPassword());
        Assert.assertNull(dto.getStartDate());
        Assert.assertNull(dto.getFirstname());
        Assert.assertNull(dto.getLastname());
        Assert.assertNull(dto.getEmail());
        Assert.assertEquals(0, dto.getActive());
        Assert.assertEquals(0, dto.getTotalLogons());
        Assert.assertNull(dto.getSsn());
        
        try {
            UserGroup nullParm = null;
            dto = Rmt2OrmDtoFactory.getGroupDtoInstance(nullParm);
            dto.setGroupId(SecurityMockDataFactory.TEST_GROUP_ID);
            dto.setGrpDescription("UserGroupDescription_" + SecurityMockDataFactory.TEST_GROUP_ID);
            
            Assert.assertEquals(0, dto.getLoginUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_GROUP_ID, dto.getGroupId());
            Assert.assertEquals("UserGroupDescription_" + SecurityMockDataFactory.TEST_GROUP_ID, dto.getGrpDescription());
            Assert.assertEquals("UserGroupDescription_" + SecurityMockDataFactory.TEST_GROUP_ID, dto.getGrp());
            Assert.assertNull(dto.getUsername());
            Assert.assertNull(dto.getPassword());
            Assert.assertNull(dto.getStartDate());
            Assert.assertNull(dto.getFirstname());
            Assert.assertNull(dto.getLastname());
            Assert.assertNull(dto.getEmail());
            Assert.assertEquals(0, dto.getActive());
            Assert.assertEquals(0, dto.getTotalLogons());
            Assert.assertNull(dto.getSsn());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for UserGroup Adapater");
        }
    }
}
