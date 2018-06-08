package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.UserResourceSubtype;
import org.dto.ResourceDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

/**
 * Test UserResourceSubtype adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class UserResourceSubTypeAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrm() {
        UserResourceSubtype o1 = SecurityMockDataFactory
                .createOrmUserResourceSubtype(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                        SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(o1);
        
        Assert.assertEquals(0, dto.getUid());
        Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, dto.getSubTypeId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, dto.getTypeId());
        Assert.assertEquals("ResourceSubtypeName_" + SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, dto.getSubTypeName());
        Assert.assertEquals("ResourceSubtypeDescription_" + SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, dto.getSubTypeDescription());
        Assert.assertNull(dto.getDescription());
        
        try {
            UserResourceSubtype nullParm = null;
            dto = Rmt2OrmDtoFactory.getResourceDtoInstance(nullParm);
            dto.setSubTypeId(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
            dto.setTypeId(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
            dto.setSubTypeName("ResourceSubtypeName_" + SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
            dto.setSubTypeDescription("ResourceSubtypeDescription_" + SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
            
            Assert.assertEquals(0, dto.getUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, dto.getSubTypeId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, dto.getTypeId());
            Assert.assertEquals("ResourceSubtypeName_" + SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, dto.getSubTypeName());
            Assert.assertEquals("ResourceSubtypeDescription_" + SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, dto.getSubTypeDescription());
            Assert.assertNull(dto.getDescription());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for UserResourceSubtype Adapater");
        }
    }
}
