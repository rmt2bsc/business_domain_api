package resources;

import java.util.List;

import junit.framework.Assert;

import org.dao.resources.ResourceDao;
import org.dao.resources.ResourcesDaoFactory;
import org.dto.ResourceDto;
import org.dto.WebServiceDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Resource RMT2 ORM DAO implementation.
 * 
 * @author rterrell
 * 
 */
public class Rmt2OrmWebServiceResourceDaoTest {

    private ResourcesDaoFactory f;

    private ResourceDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new ResourcesDaoFactory();
        this.dao = this.f.createRmt2OrmDao();
        this.dao.setDaoUser("testuser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    @Test
    public void getAllResources() {
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchResource();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getSingleResource() {
        int resourceId = 1;
        String resourceName = "authremoteuser";
        ResourceDto results = null;
        try {
            results = this.dao.fetchResource(resourceId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(resourceName, results.getName());
    }

    @Test
    public void getSingleResourceNotExists() {
        int resourceId = 9999999;
        ResourceDto results = null;
        try {
            results = this.dao.fetchResource(resourceId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNull(results);
    }

    @Test
    public void getAllResourceTypes() {
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchResourceType();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getSingleResourceType() {
        int resourceTypeId = 3;
        String description = "Web Services";
        ResourceDto results = null;
        try {
            results = this.dao.fetchResourceType(resourceTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(description, results.getTypeDescription());
    }

    @Test
    public void getSingleResourceTypeNotExists() {
        int resourceTypeId = 99999;
        ResourceDto results = null;
        try {
            results = this.dao.fetchResourceType(resourceTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNull(results);
    }

    @Test
    public void getAllResourceSubTypes() {
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchResourceSubType();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getSingleResourceSubType() {
        int resourceSubTypeId = 1;
        String name = "HTML";
        ResourceDto results = null;
        try {
            results = this.dao.fetchResourceSubType(resourceSubTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(name, results.getSubTypeName());
    }

    @Test
    public void getAllExtendedResourcesWithInitializedDTO() {
        ResourceDto criteria = Rmt2OrmDtoFactory.getNewExtResourceInstance();
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchExtResource(criteria);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getAllExtendedResourcesWithNullDTO() {
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchExtResource(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getSecuredResources() {
        WebServiceDto criteria = Rmt2OrmDtoFactory.getNewExtResourceInstance();
        criteria.setQuerySecureFlag(true);
        criteria.setSecured(1);
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchExtResource(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getInsecuredResources() {
        WebServiceDto criteria = Rmt2OrmDtoFactory.getNewExtResourceInstance();
        criteria.setQuerySecureFlag(true);
        criteria.setSecured(0);
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchExtResource(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getAllResourcesByNameCriteria() {
        ResourceDto criteria = Rmt2OrmDtoFactory.getNewExtResourceInstance();
        criteria.setName("RQ_");
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchExtResource(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    /**
     * Queries the same data using two different selection criteria and expects
     * the result set of both queries to be equivalent.
     */
    @Test
    public void performParallelSubTypeQuery() {
        int recCount1 = 0;
        int recCount2 = 0;
        int subTypeId = 6;
        String subTypeName = "XML";

        // First Query
        ResourceDto criteria = Rmt2OrmDtoFactory.getNewExtResourceInstance();
        criteria.setSubTypeId(subTypeId);
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchExtResource(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
        recCount1 = results.size();

        // Second Query
        criteria = Rmt2OrmDtoFactory.getNewExtResourceInstance();
        criteria.setSubTypeName(subTypeName);
        results = null;
        try {
            results = this.dao.fetchExtResource(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
        recCount2 = results.size();

        Assert.assertEquals(recCount1, recCount2);
    }

    public static final ResourceDto createValidTestResourceDtoInstance() {
        WebServiceDto dto = Rmt2OrmDtoFactory.getNewResourceInstance();
        dto.setName("testResource");
        dto.setTypeId(3); // Web services
        dto.setSubTypeId(4); // DISH
        dto.setRequestUrl("Test/Url/Junit");
        dto.setDescription("This is a test");
        dto.setSecured(1);
        dto.setHost("RMTDALSYS01");
        dto.setRouterType("RMI");
        dto.setUpdateUserId("testuser");
        return dto;
    }

    private int deleteResourceTest(int resourceId) {
        int rc = 0;
        try {
            rc = this.dao.deleteResource(resourceId);
        } catch (Exception e) {
            rc = -1;
        }
        return rc;
    }

    @Test
    public void addResource() {
        ResourceDto dto = Rmt2OrmWebServiceResourceDaoTest
                .createValidTestResourceDtoInstance();
        int rc = 0;
        int newId = 0;
        try {
            newId = this.dao.maintainResource(dto);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, dto.getUid());

        // Delete record
        rc = this.deleteResourceTest(newId);
        Assert.assertTrue(rc == 1);
    }

    @Test
    public void updateResource() {
        ResourceDto dto = Rmt2OrmWebServiceResourceDaoTest
                .createValidTestResourceDtoInstance();
        int rc = 0;
        int newId = 0;
        try {
            newId = this.dao.maintainResource(dto);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, dto.getUid());

        // Get original record so to preserver date created.
        try {
            dto = this.dao.fetchResource(newId);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        // Modify record
        String nameMod = "JUnit_modified_service";
        dto.setName(nameMod);
        try {
            rc = this.dao.maintainResource(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);

        // Verify change
        ResourceDto dto2 = null;
        try {
            dto2 = this.dao.fetchResource(newId);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(newId, dto2.getUid());
        Assert.assertEquals(nameMod, dto2.getName());

        // Delete record
        rc = this.deleteResourceTest(newId);
        Assert.assertTrue(rc == 1);
    }

    public static final ResourceDto createValidTestResourceTypeDtoInstance() {
        ResourceDto dto = Rmt2OrmDtoFactory.getNewResourceTypeInstance();
        dto.setTypeDescription("Test Component TYpe");
        dto.setUpdateUserId("testuser");
        return dto;
    }

    private int deleteResourceTypeTest(int resourceTypeId) {
        int rc = 0;
        try {
            rc = this.dao.deleteResourceType(resourceTypeId);
        } catch (Exception e) {
            rc = -1;
        }
        return rc;
    }

    @Test
    public void addResourceType() {
        ResourceDto dto = Rmt2OrmWebServiceResourceDaoTest
                .createValidTestResourceTypeDtoInstance();
        int rc = 0;
        int newId = 0;
        try {
            newId = this.dao.maintainResourceType(dto);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, dto.getTypeId());

        // Delete record
        rc = this.deleteResourceTypeTest(newId);
        Assert.assertTrue(rc == 1);
    }

    @Test
    public void updateResourceType() {
        ResourceDto dto = Rmt2OrmWebServiceResourceDaoTest
                .createValidTestResourceTypeDtoInstance();
        int rc = 0;
        int newId = 0;
        try {
            newId = this.dao.maintainResourceType(dto);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, dto.getTypeId());

        // Get Orginial record so to preserver date created column value
        try {
            dto = this.dao.fetchResourceType(newId);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        // Modify record
        String descriptionMod = "JUnit_modified_Type";
        dto.setTypeDescription(descriptionMod);
        try {
            rc = this.dao.maintainResourceType(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);

        // Verify change
        ResourceDto dto2 = null;
        try {
            dto2 = this.dao.fetchResourceType(newId);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(newId, dto2.getTypeId());
        Assert.assertEquals(descriptionMod, dto2.getTypeDescription());

        // Delete record
        rc = this.deleteResourceTypeTest(newId);
        Assert.assertTrue(rc == 1);
    }

    public static final ResourceDto createValidTestResourceSubTypeDtoInstance() {
        ResourceDto dto = Rmt2OrmDtoFactory.getNewResourceSubTypeInstance();
        dto.setTypeId(6);
        dto.setSubTypeName("TestSubT");
        dto.setSubTypeDescription("Test subt type description");
        dto.setUpdateUserId("testuser");
        return dto;
    }

    private int deleteResourceSubTypeTest(int resourceSubTypeId) {
        int rc = 0;
        try {
            rc = this.dao.deleteResourceSubType(resourceSubTypeId);
        } catch (Exception e) {
            rc = -1;
        }
        return rc;
    }

    @Test
    public void addResourceSubType() {
        ResourceDto dto = Rmt2OrmWebServiceResourceDaoTest
                .createValidTestResourceSubTypeDtoInstance();
        int rc = 0;
        int newId = 0;
        try {
            newId = this.dao.maintainResourceSubType(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, dto.getSubTypeId());

        // Delete record
        rc = this.deleteResourceSubTypeTest(newId);
        Assert.assertTrue(rc == 1);
    }

    @Test
    public void updateResourceSubType() {
        ResourceDto dto = Rmt2OrmWebServiceResourceDaoTest
                .createValidTestResourceSubTypeDtoInstance();
        int rc = 0;
        int newId = 0;
        try {
            newId = this.dao.maintainResourceSubType(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, dto.getSubTypeId());

        // Get Orginial record so to preserver date created column value
        try {
            dto = this.dao.fetchResourceSubType(newId);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        // Modify record
        String descriptionMod = "JUnit modified this description";
        dto.setSubTypeDescription(descriptionMod);
        try {
            rc = this.dao.maintainResourceSubType(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc == 1);

        // Verify change
        ResourceDto dto2 = null;
        try {
            dto2 = this.dao.fetchResourceSubType(newId);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(newId, dto2.getSubTypeId());
        Assert.assertEquals(descriptionMod, dto2.getSubTypeDescription());

        // Delete record
        rc = this.deleteResourceSubTypeTest(newId);
        Assert.assertTrue(rc == 1);
    }

}
