package application;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.dto.ApplicationDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.SecurityModuleException;
import org.modules.application.AppApi;
import org.modules.application.AppApiException;
import org.modules.application.AppApiFactory;

/**
 * Test the application module.
 * 
 * @author rterrell
 * 
 */
public class ApplicationApiTest {
    private static final Logger logger = Logger
            .getLogger(ApplicationApiTest.class);

    private AppApiFactory f;

    private AppApi api;

    /**
     * Creates the API factory.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new AppApiFactory();
        this.api = this.f.createApi();
        this.api.setApiUser("testuser");
    }

    /**
     * Disposes of the application factory.
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.f = null;
    }

    /**
     * Test log initialization.
     */
    @Test
    public void checkLogInitialization() {
        api = this.f.createApi();
        api.hashCode();
        api = null;
    }

    /**
     * Tests obtaining a new {@link ApplicationDto} instance.
     * 
     */
    @Test
    public void getNewApplicationInstance() {
        ApplicationDto dto = api.create();
        Assert.assertNotNull(dto);
    }

    /**
     * Test fetching all application records from an data source.
     * 
     */
    @Test
    public void fetchAllApps() {
        List<ApplicationDto> dtoList;
        try {
            dtoList = api.get();
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(dtoList);
        Assert.assertTrue(dtoList.size() > 0);
    }

    /**
     * Test fetching a single application record from an data source.
     */
    @Test
    public void fetchUnsupportedSingleApp() {
        try {
            api.get(1);
            Assert.fail("Test failed since an exception was not thrown for using an unsupported method");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Test fetching an application record that does not exist.
     */
    @Test
    public void fetchSingleAppNotFound() {
        ApplicationDto dto;
        try {
            dto = api.get("dummApp");
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNull(dto);
    }

    /**
     * Test fetching an application record by application name from an data
     * source.
     */
    @Test
    public void fetchSingleAppByName() {
        ApplicationDto dto;
        try {
            dto = api.get("authentication");
        } catch (AppApiException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals("authentication", dto.getAppName());
    }

    /**
     * Test fetching an application record by application name from an data
     * source in which the record does not exist.
     */
    @Test
    public void fetchSingleAppByNameNotFound() {
        ApplicationDto dto;
        try {
            dto = api.get("InvalidAppName");
        } catch (AppApiException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNull(dto);
    }

    /**
     * Test adding a new applcation record to a data source.
     */
    @Test
    public void createApplication() {
        int rc = 0;

        // Create record
        ApplicationDto dto = api.create();
        dto.setApplicationId(0);
        dto.setAppName("JUnitTestAppName");
        dto.setAppDescription("This is a test entry orginiating from JUnit");
        dto.setUpdateUserId("testuser");
        dto.setActive("0");
        try {
            rc = api.update(dto);
        } catch (SecurityModuleException e) {
            logger.error("Application update Test failed", e);
            return;
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(rc, dto.getApplicationId());

        // Delete record
        try {
            rc = api.delete(dto.getApplicationId());
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(rc, 1);
    }

    /**
     * Test ability to recognize and handle database error when attempting to
     * add a record to a data source.
     */
    @Test
    public void createApplicationError() {
        boolean error = false;

        // Create record
        ApplicationDto dto = api.create();
        dto.setApplicationId(0);
        dto.setAppName("JUnitTestAppNameCreated");
        dto.setAppDescription("This is a test entry orginiating from JUnit");
        dto.setUpdateUserId("testuser");
        dto.setActive("0");
        try {
            api.update(dto);
        } catch (SecurityModuleException e) {
            logger.error("Application update Test failed", e);
            error = true;
        }
        Assert.assertTrue(error);
    }

    /**
     * Test modifying an existing applcation record.
     */
    @Test
    public void updateApplication() {
        int rc = 0;
        int newId = 0;

        // Create record
        ApplicationDto dto = api.create();
        dto.setApplicationId(0);
        dto.setAppName("NewAppName");
        dto.setAppDescription("This is a test entry orginiating from JUnit");
        dto.setUpdateUserId("testuser");
        dto.setActive("0");
        try {
            rc = api.update(dto);
            newId = rc;
        } catch (SecurityModuleException e) {
            logger.error("Application update Test failed", e);
            return;
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(rc, dto.getApplicationId());

        // Update reocrd
        String updateAppName = "ModifiedAppName";
        try {
            dto.setAppName(updateAppName);
            rc = api.update(dto);
        } catch (SecurityModuleException e) {
            logger.error("Application update Test failed", e);
            return;
        }
        // Verify Update
        try {
            dto = api.get(newId);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(updateAppName, dto.getAppName());

        // Delete record
        try {
            rc = api.delete(newId);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(rc, 1);
    }
}
