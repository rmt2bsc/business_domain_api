package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.UserResourceType;
import org.dto.ResourceDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

/**
 * Test UserResourceType adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class UserResourceTypeAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrm() {
        UserResourceType o1 = SecurityMockDataFactory.createOrmUserResourceType(
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(o1);
        
        Assert.assertEquals(0, dto.getUid());
        Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, dto.getTypeId());
        Assert.assertEquals(0, dto.getSubTypeId());
        Assert.assertEquals("ResourceTypeDescription_" + SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, dto.getTypeDescription());
        Assert.assertNull(dto.getDescription());
        
        try {
            UserResourceType nullParm = null;
            dto = Rmt2OrmDtoFactory.getResourceDtoInstance(nullParm);
            dto.setTypeId(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
            dto.setTypeDescription("ResourceTypeDescription_" + SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
            
            Assert.assertEquals(0, dto.getUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, dto.getTypeId());
            Assert.assertEquals(0, dto.getSubTypeId());
            Assert.assertEquals("ResourceTypeDescription_" + SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, dto.getTypeDescription());
            Assert.assertNull(dto.getDescription());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for UserResourceType Adapater");
        }
    }
}
