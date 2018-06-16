package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.Application;
import org.dto.ApplicationDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

/**
 * Test Application adapter as it pertains to the Security API module.
 * 
 * @author roy.terrell
 *
 */
public class ApplicationAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrm() {
        Application o1 = SecurityMockDataFactory.createOrmApplication(SecurityMockDataFactory.TEST_APP_ID);
        ApplicationDto dto = Rmt2OrmDtoFactory.getAppDtoInstance(o1);
        
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, dto.getApplicationId());
        Assert.assertEquals("ApplicationName_" + dto.getApplicationId(), dto.getAppName());
        Assert.assertEquals("ApplicationDescription_" + dto.getApplicationId(), dto.getAppDescription());
        
        try {
            Application nullParm = null;
            dto = Rmt2OrmDtoFactory.getAppDtoInstance(nullParm);
            dto.setApplicationId(SecurityMockDataFactory.TEST_APP_ID);
            dto.setAppName("ApplicationName_" + SecurityMockDataFactory.TEST_APP_ID);
            dto.setAppDescription("ApplicationDescription_" + SecurityMockDataFactory.TEST_APP_ID);

            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, dto.getApplicationId());
            Assert.assertEquals("ApplicationName_" + dto.getApplicationId(), dto.getAppName());
            Assert.assertEquals("ApplicationDescription_" + dto.getApplicationId(), dto.getAppDescription());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for Application Adapater");
        }
    }
}
