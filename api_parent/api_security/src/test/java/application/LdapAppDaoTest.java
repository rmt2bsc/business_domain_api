package application;

import java.util.List;

import junit.framework.Assert;

import org.dao.application.AppDao;
import org.dao.application.AppDaoException;
import org.dao.application.AppDaoFactory;
import org.dto.ApplicationDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author rterrell
 * 
 */
public class LdapAppDaoTest {
    private AppDaoFactory f;

    private AppDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new AppDaoFactory();
        this.dao = f.createLdapDao();
        this.dao.setDaoUser("testuser");
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
        this.dao.close();
        this.dao = null;
        this.f = null;
        return;
    }

    private void createData() {
        ApplicationDto dto = LdapDtoFactory.getApplicationInstance(null);
        dto.setAppName("TestApp1");
        dto.setAppDescription("This is a description for TestApp1");
        dto.setActive("1");
        dto.setAppCode("testapp1");
        dto.setAppTitle("TestApp1 title");
        dto.setWebContext("/testapp1");
        dto.setAuthenticator("com.api.security.authentication.web.UserAuthenticationAuthorization");
        dto.setDbConnectionFactory("com.api.db.JndiDatabaseConnectionImpl");
        dto.setDbConfigName("TestApp1-DB");
        this.dao.maintainApp(dto);

        return;
    }

    private void deleteData() {
        this.dao.deleteApp("TestApp1");

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
    public void getSingleAppByName() {
        ApplicationDto results = null;
        try {
            results = this.dao.fetchApp("TestApp1");
        } catch (AppDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("testapp1", results.getAppCode());
        Assert.assertEquals("TestApp1 title", results.getAppTitle());
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
     * Test modifyig an existing applcation record to a data source.
     */
    @Test
    public void modifyApplication() {
        // Fetch record
        ApplicationDto dto = null;
        try {
            dto = this.dao.fetchApp("TestApp1");
        } catch (AppDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals("testapp1", dto.getAppCode());
        Assert.assertEquals("TestApp1 title", dto.getAppTitle());

        // Update record
        String modAppCode = "Modified Appcode";
        try {
            dto.setAppCode(modAppCode);
            this.dao.maintainApp(dto);
        } catch (AppDaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Verify that record was modified
        ApplicationDto dto2 = null;
        try {
            dto2 = this.dao.fetchApp("TestApp1");
        } catch (AppDaoException e) {
            e.printStackTrace();
            return;
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(modAppCode, dto2.getAppCode());
        Assert.assertEquals("TestApp1", dto.getAppName());
    }
}
