package org.rmt2.api.resources;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dao.mapping.orm.rmt2.UserResourceType;
import org.dao.resources.ResourceDaoException;
import org.dto.ResourceDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.SecurityConstants;
import org.modules.resource.ResourceRegistryApi;
import org.modules.resource.ResourceRegistryApiException;
import org.modules.resource.ResourceRegistryApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests for User Resource Type module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class UserResourceTypeApiTest extends SecurityMockData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(any(UserResourceType.class)))
             .thenReturn(this.mockUserResourceTypeData);
        when(this.mockPersistenceClient.insertRow(any(UserResourceType.class), eq(true)))
             .thenReturn(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        when(this.mockPersistenceClient.updateRow(any(UserResourceType.class)))
             .thenReturn(1);
        when(this.mockPersistenceClient.deleteRow(any(UserResourceType.class)))
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
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        ResourceDto criteria = Rmt2OrmDtoFactory.getNewResourceTypeInstance();
        List<ResourceDto> results = null;
        try {
            results = api.getResourceType(criteria);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
            Assert.fail("Expected not to throw an exception");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ResourceDto item = results.get(ndx);
            int currentUid = SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID + ndx;
            Assert.assertEquals(currentUid, item.getTypeId());
            Assert.assertEquals("ResourceTypeDescription_" + item.getTypeId(), item.getTypeDescription());
        }
    }

    
    @Test
    public void testSuccess_Fetch_NotFound() {
        when(this.mockPersistenceClient.retrieveList(any(UserResourceType.class)))
              .thenReturn(null);
        
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        ResourceDto criteria = Rmt2OrmDtoFactory.getNewResourceTypeInstance();
        List<ResourceDto> results = null;
        try {
            results = api.getResourceType(criteria);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
            Assert.fail("Expected not to throw an exception");
        }
        Assert.assertNull(results);
    }
 
    @Test
    public void testError_Fetch_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveList(any(UserResourceType.class)))
            .thenThrow(new DatabaseException("Error fetching user resource type data"));
        
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        ResourceDto criteria = Rmt2OrmDtoFactory.getNewResourceTypeInstance();
        try {
            api.getResourceType(criteria);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ResourceRegistryApiException);
            Assert.assertTrue(e.getCause() instanceof ResourceDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Fetch_Null_Criteria_Parameter() {
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        try {
            api.getResourceType(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Selection criteria object is required for UserResoureceType Query", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Create() {
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResourceType orm = SecurityMockDataFactory.createOrmUserResourceType(0);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        int results = 0;
        try {
            results = api.updateResourceType(dto);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
            Assert.fail("Expected not to throw an exception");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, results);
    }

    @Test
    public void testError_Create_DB_Access_Fault() {
        when(this.mockPersistenceClient.insertRow(any(UserResourceType.class), eq(true)))
             .thenThrow(new DatabaseException("Error inserting User Resource Type data"));
        
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResourceType orm = SecurityMockDataFactory.createOrmUserResourceType(0);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        try {
            api.updateResourceType(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ResourceRegistryApiException);
            Assert.assertTrue(e.getCause() instanceof ResourceDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testSuccess_Modify() {
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResourceType orm = 
                SecurityMockDataFactory.createOrmUserResourceType(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        int results = 0;
        try {
            results = api.updateResourceType(dto);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
            Assert.fail("Expected not to throw an exception");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Modify_DB_Access_Fault() {
        when(this.mockPersistenceClient.updateRow(any(UserResourceType.class)))
                .thenThrow(new DatabaseException("Error modifying User Resource Type data"));
   
   ResourceRegistryApi api = 
           ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResourceType orm = SecurityMockDataFactory
                .createOrmUserResourceType(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
   ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
   try {
       api.updateResourceType(dto);
       Assert.fail("Expected an exception to be thrown");
   } catch (Exception e) {
       e.printStackTrace();
       Assert.assertTrue(e instanceof ResourceRegistryApiException);
       Assert.assertTrue(e.getCause() instanceof ResourceDaoException);
       Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
   }
    }
    
    @Test
    public void testValidation_Update_Data_Object_Null() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        try {
            api.updateResourceType(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    
    @Test
    public void testValidation_Update_Description_Null() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResourceType orm = SecurityMockDataFactory
                .createOrmUserResourceType(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        orm.setDescription(null);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        try {
            api.updateResourceType(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_Description_Empty() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResourceType orm = SecurityMockDataFactory
                .createOrmUserResourceType(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        orm.setDescription("");
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        try {
            api.updateResource(dto);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
     @Test
    public void testSuccess_Delete() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResourceType orm = SecurityMockDataFactory
                .createOrmUserResourceType(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        orm.setDescription(null);
        ResourceDto criteria = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        int results = 0;
        try {
            results = api.deleteResourceType(criteria);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
            Assert.fail("Expected not to throw an exceptionn");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Delete_DB_Access_Fault() {
        when(this.mockPersistenceClient.deleteRow(any(UserResourceType.class)))
             .thenThrow(new DatabaseException("Error deleting user resource type data"));
        
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResourceType orm = SecurityMockDataFactory
                .createOrmUserResourceType(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        orm.setDescription(null);
        ResourceDto criteria = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        try {
            api.deleteResourceType(criteria);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ResourceRegistryApiException);
            Assert.assertTrue(e.getCause() instanceof ResourceDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
}