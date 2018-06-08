package org.rmt2.api.resources;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dao.mapping.orm.rmt2.UserResource;
import org.dao.resources.ResourceDaoException;
import org.dto.ResourceDto;
import org.dto.WebServiceDto;
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
 * Tests for User Resource module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class UserResourceApiTest extends SecurityMockData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(any(UserResource.class)))
             .thenReturn(this.mockUserResourceData);
        when(this.mockPersistenceClient.insertRow(any(UserResource.class), eq(true)))
             .thenReturn(SecurityMockDataFactory.TEST_RESOURCE_ID);
        when(this.mockPersistenceClient.updateRow(any(UserResource.class)))
             .thenReturn(1);
        when(this.mockPersistenceClient.deleteRow(any(UserResource.class)))
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
        ResourceDto criteria = Rmt2OrmDtoFactory.getNewResourceInstance();
        List<ResourceDto> results = null;
        try {
            results = api.getResource(criteria);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ResourceDto item = results.get(ndx);
            int currentUid = SecurityMockDataFactory.TEST_RESOURCE_ID + ndx;
            Assert.assertEquals(currentUid, item.getUid());
            Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, item.getTypeId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, item.getSubTypeId());
            Assert.assertEquals("URL_" + item.getUid(), ((WebServiceDto) item).getRequestUrl());
            Assert.assertEquals("description_" + item.getUid(), item.getDescription());
        }
    }

    
    @Test
    public void testSuccess_Fetch_NotFound() {
        when(this.mockPersistenceClient.retrieveList(any(UserResource.class)))
              .thenReturn(null);
        
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        ResourceDto criteria = Rmt2OrmDtoFactory.getNewResourceInstance();
        List<ResourceDto> results = null;
        try {
            results = api.getResource(criteria);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
 
    @Test
    public void testError_Fetch_DB_Access_Fault() {
        when(this.mockPersistenceClient.retrieveList(any(UserResource.class)))
            .thenThrow(new DatabaseException("Error fetching user resource data"));
        
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        ResourceDto criteria = Rmt2OrmDtoFactory.getNewResourceInstance();
        try {
            api.getResource(criteria);
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
            api.getResource(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Selection criteria object is required for UserResourece Query", e.getMessage());
        }
    }
    
    @Test
    public void testSuccess_Create() {
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(0,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        int results = 0;
        try {
            results = api.updateResource(dto);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(SecurityMockDataFactory.TEST_RESOURCE_ID, results);
    }

    @Test
    public void testError_Create_DB_Access_Fault() {
        when(this.mockPersistenceClient.insertRow(any(UserResource.class), eq(true)))
             .thenThrow(new DatabaseException("Error inserting User Resource data"));
        
        ResourceRegistryApi api = 
                ResourceRegistryApiFactory.createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(0,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        try {
            api.updateResource(dto);;
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
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        int results = 0;
        try {
            results = api.updateResource(dto);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Modify_DB_Access_Fault() {
        when(this.mockPersistenceClient.updateRow(any(UserResource.class)))
                .thenThrow(new DatabaseException("Error modifying User Resource data"));

        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        try {
            api.updateResource(dto);
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
            api.updateResource(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Update_Name_Null() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        orm.setName(null);
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
    public void testValidation_Update_Name_Empty() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        orm.setName("");
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
    public void testValidation_Update_Description_Null() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        orm.setDescription(null);
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
    public void testValidation_Update_Description_Empty() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
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
    public void testValidation_Update_URL_Null() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, null, true);
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
    public void testValidation_Update_URL_Empty() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "", true);
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
    public void testValidation_Update_TypeId_Negative() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                0,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "", true);
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
    public void testValidation_Update_TypeId_Zero() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                -1234,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "", true);
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
    public void testValidation_Update_SubTypeId_Negative() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, -1234, "URL", true);
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
    public void testValidation_Update_SubTypeId_Zero() {
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID, 0, "URL", true);
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
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(
                SecurityMockDataFactory.TEST_RESOURCE_ID, 0, 0, null, null);
        ResourceDto criteria = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        int results = 0;
        try {
            results = api.deleteResource(criteria);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results);
    }
    
    @Test
    public void testError_Delete_DB_Access_Fault() {
        when(this.mockPersistenceClient.deleteRow(any(UserResource.class)))
             .thenThrow(new DatabaseException("Error deleting user resource data"));
        
        ResourceRegistryApi api = ResourceRegistryApiFactory
                .createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        UserResource orm = SecurityMockDataFactory.createOrmUserResource(
                SecurityMockDataFactory.TEST_RESOURCE_ID, 0, 0, null, null);
        ResourceDto criteria = Rmt2OrmDtoFactory.getResourceDtoInstance(orm);
        try {
            api.deleteResource(criteria);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ResourceRegistryApiException);
            Assert.assertTrue(e.getCause() instanceof ResourceDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
}