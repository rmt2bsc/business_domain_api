package org.rmt2.api.application;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dao.application.AppDaoException;
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
import org.rmt2.api.SecurityMockDataFactory;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Application module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class ApplicationApiTest extends SecurityMockData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(any(Application.class)))
             .thenReturn(this.mockApplicationData);
        when(this.mockPersistenceClient.insertRow(any(Application.class), eq(true)))
             .thenReturn(SecurityMockDataFactory.TEST_APP_ID);
        when(this.mockPersistenceClient.updateRow(any(Application.class)))
             .thenReturn(1);
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
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ApplicationDto item = results.get(ndx);
            int currentAppId = SecurityMockDataFactory.TEST_APP_ID + ndx;
            Assert.assertEquals(currentAppId, item.getApplicationId());
            Assert.assertEquals("ApplicationName_" + currentAppId, item.getAppName());
            Assert.assertEquals("ApplicationDescription_" + currentAppId, item.getAppDescription());
        }
    }

    
    @Test
    public void testSuccess_Fetch_NotFound() {
        when(this.mockPersistenceClient.retrieveList(any(Application.class)))
              .thenReturn(null);
        
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        ApplicationDto criteria = Rmt2OrmDtoFactory.getAppDtoInstance(null);
        List<ApplicationDto> results = null;
        try {
            results = api.get(criteria);
        } catch (AppApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
 
    @Test
    public void testError_Fetch_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveList(any(Application.class)))
            .thenThrow(new DatabaseException("Error fetching application data"));
        
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        ApplicationDto criteria = Rmt2OrmDtoFactory.getAppDtoInstance(null);
        try {
            api.get(criteria);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AppApiException);
            Assert.assertTrue(e.getCause() instanceof AppDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Fetch_Null_Criteria_Parameter() {
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        try {
            api.get(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Application crtieria object is required", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Create() {
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        Application obj = SecurityMockDataFactory.createOrmApplication(0);
        ApplicationDto dto = Rmt2OrmDtoFactory.getAppDtoInstance(obj);
        int results = 0;
        try {
            results = api.update(dto);
        } catch (AppApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, results);
    }

    @Test
    public void testError_Create_DB_Access_Fault() {
        when(this.mockPersistenceClient.insertRow(any(Application.class), eq(true)))
             .thenThrow(new DatabaseException("Error inserting application data"));
        
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        Application obj = SecurityMockDataFactory.createOrmApplication(0);
        ApplicationDto dto = Rmt2OrmDtoFactory.getAppDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AppApiException);
            Assert.assertTrue(e.getCause() instanceof AppDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testSuccess_Modify() {
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        Application obj = SecurityMockDataFactory.createOrmApplication(1350);
        ApplicationDto dto = Rmt2OrmDtoFactory.getAppDtoInstance(obj);
        int results = 0;
        try {
            results = api.update(dto);
        } catch (AppApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Modify_DB_Access_Fault() {
        when(this.mockPersistenceClient.updateRow(any(Application.class)))
             .thenThrow(new DatabaseException("Error updating application data"));
        
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        Application obj = SecurityMockDataFactory.createOrmApplication(1350);
        ApplicationDto dto = Rmt2OrmDtoFactory.getAppDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof AppApiException);
            Assert.assertTrue(e.getCause() instanceof AppDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Update_Data_Object_Null() {
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        Application obj = SecurityMockDataFactory.createOrmApplication(1350);
        try {
            api.update(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_Application_Name_Null() {
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        Application obj = SecurityMockDataFactory.createOrmApplication(1350);
        obj.setName(null);
        ApplicationDto dto = Rmt2OrmDtoFactory.getAppDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_Application_Name_Empty() {
        AppApi api = AppApiFactory.createApiInstance(SecurityConstants.APP_NAME);
        Application obj = SecurityMockDataFactory.createOrmApplication(1350);
        obj.setName("");
        ApplicationDto dto = Rmt2OrmDtoFactory.getAppDtoInstance(obj);
        try {
            api.update(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
}