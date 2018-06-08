package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.UserResource;
import org.dto.WebServiceDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

/**
 * Test UserResource adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class UserResourceAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrm() {
        UserResource o1 = SecurityMockDataFactory.createOrmUserResource(
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        WebServiceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(o1);
        
        Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_ID, dto.getUid());
        Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, dto.getTypeId());
        Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, dto.getSubTypeId());
        Assert.assertEquals("URL", dto.getRequestUrl());
        Assert.assertEquals("name_" + dto.getUid(), dto.getName());
        Assert.assertEquals("description_" + dto.getUid(), dto.getDescription());
        
        try {
            UserResource nullParm = null;
            dto = Rmt2OrmDtoFactory.getResourceDtoInstance(nullParm);
            dto.setUid(SecurityMockDataFactory.TEST_RESOURCE_ID);
            dto.setTypeId(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
            dto.setSubTypeId(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
            dto.setName("name_" + SecurityMockDataFactory.TEST_RESOURCE_ID);
            dto.setDescription("description_" + SecurityMockDataFactory.TEST_RESOURCE_ID);
            dto.setRequestUrl("URL");
            
            Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_ID, dto.getUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, dto.getTypeId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, dto.getSubTypeId());
            Assert.assertEquals("URL", dto.getRequestUrl());
            Assert.assertEquals("name_" + dto.getUid(), dto.getName());
            Assert.assertEquals("description_" + dto.getUid(), dto.getDescription());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for UserResource Adapater");
        }
    }
}
