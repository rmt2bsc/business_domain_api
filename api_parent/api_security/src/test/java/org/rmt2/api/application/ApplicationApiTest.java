package org.rmt2.api.application;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.Application;
import org.dto.ApplicationDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.SecurityConstants;
import org.modules.application.AppApi;
import org.modules.application.AppApiException;
import org.modules.application.AppApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Application module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class ApplicationApiTest extends SecurityMockData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(any(Application.class)))
             .thenReturn(this.mockApplicationData);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    @Test
    public void testSuccess_Fetch() {
//        try {
//            when(this.mockPersistenceClient.retrieveList(any(Application.class)))
//                            .thenReturn(this.mockApplicationData);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail("Fetch Application test case setup failed");
//        }

        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        ApplicationDto criteria = Rmt2OrmDtoFactory.getAppDtoInstance(null);
        List<ApplicationDto> results = null;
        try {
            results = api.get(criteria);
        } catch (AppApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        
    }

 
}