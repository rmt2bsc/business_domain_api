package old.tests.resources;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.dto.ResourceDto;
import org.dto.WebServiceDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.resource.ResourceRegistryApi;
import org.modules.resource.ResourceRegistryApiException;
import org.modules.resource.ResourceRegistryApiFactory;

/**
 * @author rterrell
 * 
 */
public class Rmt2OrmWebServiceResourceApiTest {

    private ResourceRegistryApiFactory f;

    private ResourceRegistryApi api;

    private List<ResourceDto> rDtoList = new ArrayList<ResourceDto>();

    private List<ResourceDto> rtDtoList = new ArrayList<ResourceDto>();

    private List<ResourceDto> rstDtoList = new ArrayList<ResourceDto>();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new ResourceRegistryApiFactory();
        this.api = this.f.createWebServiceRegistryApi();
        this.api.setApiUser("testuser");

        // Create data
        ResourceDto rtDto = this.createResourceTypeData("TestResourceType1");
        Assert.assertNotNull(rtDto);
        ResourceDto rstDto = this.createResourceSubTypeData(rtDto.getTypeId(),
                "SubType1", "This is a JUnit Test");
        Assert.assertNotNull(rstDto);
        ResourceDto dto = this.createResourceData("TEST_resource_1",
                rtDto.getTypeId(), rstDto.getSubTypeId());
        Assert.assertNotNull(dto);
        rtDtoList.add(rtDto);
        rstDtoList.add(rstDto);
        rDtoList.add(dto);

        rtDto = this.createResourceTypeData("TestResourceType2");
        Assert.assertNotNull(rtDto);
        rstDto = this.createResourceSubTypeData(rtDto.getTypeId(), "SubType2",
                "This is a JUnit Test");
        Assert.assertNotNull(rstDto);
        dto = this.createResourceData("TEST_resource_2", rtDto.getTypeId(),
                rstDto.getSubTypeId());
        Assert.assertNotNull(dto);
        rtDtoList.add(rtDto);
        rstDtoList.add(rstDto);
        rDtoList.add(dto);

        rtDto = this.createResourceTypeData("TestResourceType3");
        Assert.assertNotNull(rtDto);
        rstDto = this.createResourceSubTypeData(rtDto.getTypeId(), "SubType3",
                "This is a JUnit Test");
        Assert.assertNotNull(rstDto);
        dto = this.createResourceData("TEST_resource_3", rtDto.getTypeId(),
                rstDto.getSubTypeId());
        Assert.assertNotNull(dto);
        rtDtoList.add(rtDto);
        rstDtoList.add(rstDto);
        rDtoList.add(dto);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        // Delete data
        int rc = 0;
        rc = this.deleteResourceData(rDtoList.get(0).getUid());
        Assert.assertEquals(1, rc);
        rc = this.deleteResourceData(rDtoList.get(1).getUid());
        Assert.assertEquals(1, rc);
        rc = this.deleteResourceData(rDtoList.get(2).getUid());
        Assert.assertEquals(1, rc);

        rc = this.deleteResourceSubTypeData(rstDtoList.get(0).getSubTypeId());
        Assert.assertEquals(1, rc);
        rc = this.deleteResourceSubTypeData(rstDtoList.get(1).getSubTypeId());
        Assert.assertEquals(1, rc);
        rc = this.deleteResourceSubTypeData(rstDtoList.get(2).getSubTypeId());
        Assert.assertEquals(1, rc);

        rc = this.deleteResourceTypeData(rtDtoList.get(0).getTypeId());
        Assert.assertEquals(1, rc);
        rc = this.deleteResourceTypeData(rtDtoList.get(1).getTypeId());
        Assert.assertEquals(1, rc);
        rc = this.deleteResourceTypeData(rtDtoList.get(2).getTypeId());
        Assert.assertEquals(1, rc);

        this.api = null;
        this.f = null;
    }

    private ResourceDto createResourceData(String name, int typeId,
            int subTypeId) {
        WebServiceDto dto = Rmt2OrmDtoFactory.getNewResourceInstance();
        dto.setName(name);
        dto.setTypeId(typeId);
        dto.setSubTypeId(subTypeId);
        dto.setRequestUrl("Test/Url/Junit");
        dto.setDescription("This is a test");
        dto.setSecured(1);
        dto.setHost("RMTDALSYS01");
        dto.setRouterType("RMI");
        dto.setUpdateUserId("testuser");

        // Insert data
        int newId = 0;
        try {
            newId = this.api.updateResource(dto);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
            Assert.fail("Resource insert failed: " + e.getMessage());
        }

        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, dto.getUid());
        return dto;
    }

    private ResourceDto createResourceTypeData(String description) {
        ResourceDto dto = Rmt2OrmDtoFactory.getNewResourceTypeInstance();
        dto.setTypeDescription(description);
        dto.setUpdateUserId("testuser");

        // Insert data
        int newId = 0;
        try {
            newId = this.api.updateResourceType(dto);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
            Assert.fail("Resource Type insert failed: " + e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        // Assert.assertEquals(newId, dto.getTypeId());
        return dto;
    }

    private ResourceDto createResourceSubTypeData(int typeId, String name,
            String description) {
        ResourceDto dto = Rmt2OrmDtoFactory.getNewResourceSubTypeInstance();
        dto.setTypeId(typeId);
        dto.setSubTypeName(name);
        dto.setSubTypeDescription(description);
        dto.setUpdateUserId("testuser");

        // Insert data
        int newId = 0;
        try {
            newId = this.api.updateResourceSubType(dto);
        } catch (ResourceRegistryApiException e) {
            e.printStackTrace();
            Assert.fail("Resource Sub Type insert failed: " + e.getMessage());
        }

        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, dto.getSubTypeId());
        return dto;
    }

    private int deleteResourceData(int resourceId) {
        int rc = 0;
        try {
            // rc = this.api.deleteResource(resourceId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Resource delete failed: " + e.getMessage());
        }
        return rc;
    }

    private int deleteResourceTypeData(int resourceTypeId) {
        int rc = 0;
        try {
            // rc = this.api.deleteResourceType(resourceTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Resource Type delete failed: " + e.getMessage());
        }
        return rc;
    }

    private int deleteResourceSubTypeData(int resourceSubTypeId) {
        int rc = 0;
        try {
            // rc = this.api.deleteResourceSubType(resourceSubTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Resource Sub Type delete failed: " + e.getMessage());
        }
        return rc;
    }

    @Test
    public void queryResourceAll() {
        List<ResourceDto> results = null;
        try {
            results = this.api.getResource();
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Resource master list fetch failed: " + e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void queryResourceTypeAll() {
        List<ResourceDto> results = null;
        try {
            results = this.api.getResourceType();
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Resource Type master list fetch failed: "
                    + e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void queryResourceSubTypeAll() {
        List<ResourceDto> results = null;
        try {
            results = this.api.getResourceSubType();
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Resource sub type master list fetch failed: "
                    + e.getMessage());
        }

        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void queryResourceType() {
        ResourceDto dto = null;
        int id = this.rtDtoList.get(0).getTypeId();
        try {
            dto = this.api.getResourceType(id);
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Resource Type fetch by id failed: " + e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(id, dto.getTypeId());
    }

    @Test
    public void queryResourceSubType() {
        ResourceDto dto = null;
        int id = this.rstDtoList.get(1).getSubTypeId();
        try {
            dto = this.api.getResourceSubType(id);
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Resource Sub Type fetch by id failed: "
                    + e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(id, dto.getSubTypeId());
    }

    @Test
    public void queryResource() {
        ResourceDto dto = null;
        int id = this.rDtoList.get(0).getUid();
        try {
            dto = this.api.getResource(id);
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Resource fetch by id failed: " + e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(id, dto.getUid());
    }

    @Test
    public void queryExtendedResource() {
        // Test actual query
        List<ResourceDto> results = null;
        try {
            ResourceDto criteria = Rmt2OrmDtoFactory
                    .getNewExtResourceInstance();
            criteria.setTypeDescription("TestResourceType");
            results = this.api.getResource(criteria);
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Resource Type query targeting Resource Extension fetch failed: "
                    + e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
        Assert.assertEquals(rtDtoList.size(), results.size());

        try {
            ResourceDto criteria = Rmt2OrmDtoFactory
                    .getNewExtResourceInstance();
            criteria.setName("TEST_resource_");
            results = this.api.getResource(criteria);
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Resource Name query targeting Resource Extension fetch failed: "
                    + e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
        Assert.assertEquals(rtDtoList.size(), results.size());
    }

    @Test
    public void updateAllLevels() {
        int rc = 0;
        ResourceDto origRDto = null;
        ResourceDto origRtDto = null;
        ResourceDto origRstDto = null;

        String modRsrcName = "Modified_Service2";
        String modRsrcTypeDesc = "Modified_Type2";
        String modRsrcSubTypeName = "ModName2";
        try {
            // Fetch originals from the database
            origRDto = this.api.getResource(this.rDtoList.get(2).getUid());
            origRtDto = this.api.getResourceType(this.rtDtoList.get(2)
                    .getTypeId());
            origRstDto = this.api.getResourceSubType(this.rstDtoList.get(2)
                    .getSubTypeId());

            // Perform actual updates
            origRDto.setName(modRsrcName);
            rc = this.api.updateResource(origRDto);
            Assert.assertEquals(1, rc);

            origRtDto.setTypeDescription(modRsrcTypeDesc);
            rc = this.api.updateResourceType(origRtDto);
            Assert.assertEquals(1, rc);

            origRstDto.setSubTypeName(modRsrcSubTypeName);
            rc = this.api.updateResourceSubType(origRstDto);
            Assert.assertEquals(1, rc);
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Mass Resource update failed: " + e.getMessage());
        }

        // Verify updates
        List<ResourceDto> results = null;
        try {
            ResourceDto criteria = Rmt2OrmDtoFactory
                    .getNewExtResourceInstance();
            criteria.setUid(origRDto.getUid());
            results = this.api.getResource(criteria);
        } catch (ResourceRegistryApiException e) {
            Assert.fail("Mass Resource update verification failed: "
                    + e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(modRsrcName, results.get(0).getName());
        Assert.assertEquals(modRsrcTypeDesc, results.get(0)
                .getTypeDescription());
        Assert.assertEquals(modRsrcSubTypeName, results.get(0).getSubTypeName());
    }
}
