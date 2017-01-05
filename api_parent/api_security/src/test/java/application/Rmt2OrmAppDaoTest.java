package application;

import java.util.List;

import junit.framework.Assert;

import org.dao.application.AppDao;
import org.dao.application.AppDaoException;
import org.dao.application.AppDaoFactory;
import org.dto.ApplicationDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author rterrell
 * 
 */
public class Rmt2OrmAppDaoTest {
    private AppDaoFactory f;

    private AppDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new AppDaoFactory();
        this.dao = f.createRmt2OrmDao();
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
        return;
    }

    @Test
    public void getAllApplications() {
        Object results = null;
        try {
            results = this.dao.fetchApp();
        } catch (AppDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results instanceof List);
        Assert.assertTrue(((List<Object>) results).size() > 0);
    }

    @Test
    public void getSingleAppById() {
        Object results = null;
        try {
            results = this.dao.fetchApp(1);
        } catch (AppDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
    }

    @Test
    public void handleSingleAppByIdNotFound() {
        Object results = null;
        try {
            results = this.dao.fetchApp(-1);
        } catch (AppDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNull(results);
    }

    @Test
    public void getSingleAppByName() {
        Object results = null;
        try {
            results = this.dao.fetchApp("authentication");
        } catch (AppDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
    }

    @Test
    public void handleSingleAppByNameNotFound() {
        Object results = null;
        try {
            results = this.dao.fetchApp("authentication@@@");
        } catch (AppDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNull(results);
    }

    /**
     * Test adding a new applcation record to a data source.
     */
    @Test
    public void createApplication() {
        // Create record
        ApplicationDto dto = Rmt2OrmDtoFactory.getNewAppCategoryInstance();
        dto.setApplicationId(0);
        dto.setAppName("JUnitTestAppName");
        dto.setAppDescription("This is a test entry orginiating from JUnit");
        dto.setUpdateUserId("testuser");
        dto.setActive("0");

        int newId;
        try {
            newId = this.dao.maintainApp(dto);
            Assert.assertTrue(newId > 0);
        } catch (AppDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Delete record
        int rows = 0;
        try {
            rows = this.dao.deleteApp(newId);
            Assert.assertEquals(1, rows);
        } catch (AppDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Test modifyig an existing applcation record to a data source.
     */
    @Test
    public void modifyApplication() {
        // Create record
        ApplicationDto dto = Rmt2OrmDtoFactory.getNewAppCategoryInstance();
        dto.setApplicationId(0);
        dto.setAppName("JUnitTestAppName");
        dto.setAppDescription("This is a test entry orginiating from JUnit");
        dto.setUpdateUserId("testuser");
        dto.setActive("0");

        int newId;
        try {
            newId = this.dao.maintainApp(dto);
            Assert.assertTrue(newId > 0);
        } catch (AppDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Update record
        int rows = 0;
        String modAppName = "ModifiedAppName2";
        try {
            dto.setAppName(modAppName);
            dto.setAppDescription("This record has been modified from JUnit");
            rows = this.dao.maintainApp(dto);
            Assert.assertTrue(rows > 0);
        } catch (AppDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Verify that record was modified
        ApplicationDto dto2 = null;
        try {
            dto2 = this.dao.fetchApp(newId);
        } catch (AppDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(modAppName, dto2.getAppName());

        // Delete record
        try {
            rows = this.dao.deleteApp(newId);
            Assert.assertEquals(1, rows);
        } catch (AppDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
