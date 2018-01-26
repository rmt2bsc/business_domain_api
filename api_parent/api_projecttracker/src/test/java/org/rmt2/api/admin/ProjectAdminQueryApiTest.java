package org.rmt2.api.admin;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dto.ClientDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.admin.ProjectAdminApi;
import org.modules.admin.ProjectAdminApiException;
import org.modules.admin.ProjectAdminApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectAdminApiTestData;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2String;

/**
 * Tests the Account entity belonging to the GlAccountApi within the 
 * general ledger API library.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class ProjectAdminQueryApiTest extends ProjectAdminApiTestData {
    
    private static final int TEST_CLIENT_ID = 1000;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
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
    public void testFetch_AllClients_Success() {
        // Stub all clients fetch.
        ProjClient mockCriteria = new ProjClient();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockClientFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project clients case setup failed");
        }
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        List<ClientDto> results = null;
        try {
            results = api.getAllClients();
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ClientDto obj = results.get(ndx);
            Assert.assertEquals(obj.getClientId(), (TEST_CLIENT_ID + ndx));
            Assert.assertEquals(obj.getBusinessId(), (1350 + ndx));
            Assert.assertEquals(obj.getClientName(), (TEST_CLIENT_ID + ndx) + " Company");
            Assert.assertEquals(obj.getClientBillRate(), (70.00 + (ndx * 10)), 0 );
            Assert.assertEquals(obj.getClientOtBillRate(), (80.00 + (ndx * 10)), 0 );
            Assert.assertEquals(obj.getClientContactFirstname(), "firstname" + ndx);
            Assert.assertEquals(obj.getClientContactLastname(), "lastname" + ndx);
            Assert.assertEquals(obj.getClientContactPhone(), RMT2String.dupChar(String.valueOf(ndx).charAt(0), 10));
            Assert.assertEquals(obj.getClientContactEmail(), "firstname" + ndx + "lastname" + ndx + "@gte.net");
        }
    }

    @Test
    public void testFetch_SingleClient_Success() {
        // Stub all clients fetch.
        ProjClient mockCriteria = new ProjClient();
        mockCriteria.setClientId(TEST_CLIENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockClientFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project clients case setup failed");
        }
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto results = null;
        try {
            results = api.getClient(TEST_CLIENT_ID);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getClientId(), TEST_CLIENT_ID);
        Assert.assertEquals(results.getBusinessId(), 1350);
        Assert.assertEquals(results.getClientName(), "1000 Company");
        Assert.assertEquals(results.getClientBillRate(), 70.00, 0);
        Assert.assertEquals(results.getClientOtBillRate(), 80.00, 0);
        Assert.assertEquals(results.getClientContactFirstname(), "steve");
        Assert.assertEquals(results.getClientContactLastname(), "gadd");
        Assert.assertEquals(results.getClientContactPhone(), "0000000000");
        Assert.assertEquals(results.getClientContactEmail(), "stevegadd@gte.net");
    }
    
}