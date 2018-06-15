package org.rmt2.api.authentication;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dao.mapping.orm.rmt2.AppRole;
import org.dao.mapping.orm.rmt2.VwAppRoles;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.SecurityConstants;
import org.modules.SecurityModuleException;
import org.modules.roles.AppRoleApi;
import org.modules.roles.RoleSecurityApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;
import org.rmt2.api.SecurityMockDataFactory;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests AppRole module of the Security API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class AuthenticationApiTest extends SecurityMockData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.retrieveList(isA(VwAppRoles.class)))
             .thenReturn(this.mockVwAppRolesData);
        when(this.mockPersistenceClient.retrieveObject(any(AppRole.class)))
             .thenReturn(this.mockAppRoleData.get(0));
        when(this.mockPersistenceClient.insertRow(any(AppRole.class), eq(true)))
             .thenReturn(SecurityMockDataFactory.TEST_APP_ROLE_ID);
        when(this.mockPersistenceClient.updateRow(any(AppRole.class)))
             .thenReturn(1);
        when(this.mockPersistenceClient.deleteRow(any(AppRole.class)))
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
        AppRoleApi api = RoleSecurityApiFactory.createAppRoleApi(SecurityConstants.APP_NAME);
        CategoryDto criteria = Rmt2OrmDtoFactory.getAppRoleDtoInstance(null, "test_user");
        List<CategoryDto> results = null;
        try {
            results = api.get(criteria);
        } catch (SecurityModuleException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            CategoryDto item = results.get(ndx);
            int currentUid = SecurityMockDataFactory.TEST_APP_ROLE_ID + ndx;
            int currrentRoleId = SecurityMockDataFactory.TEST_ROLE_ID + ndx;
            Assert.assertEquals(currentUid, item.getAppRoleId());
            Assert.assertEquals(SecurityMockDataFactory.TEST_APP_ID, item.getApplicationId());
            Assert.assertEquals(currrentRoleId, item.getRoleId());
            Assert.assertEquals("AppRoleCode_" + currentUid, item.getAppRoleCode());
            Assert.assertEquals("AppRoleName_" + currentUid, item.getAppRoleName());
            Assert.assertEquals("RoleName_" + currrentRoleId, item.getRoleName());
            Assert.assertEquals("AppName_" + SecurityMockDataFactory.TEST_APP_ID, item.getAppName());
            Assert.assertEquals("AppRoleDescription_" + currentUid, item.getAppRoleDescription());
        }
    }

    
}