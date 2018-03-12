package org.rmt2.api.admin;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dto.ClientDto;
import org.dto.ProjectEmployeeDto;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.employee.EmployeeApi;
import org.modules.employee.EmployeeApiException;
import org.modules.employee.EmployeeApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectAdminApiTestData;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the Administration module of the Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class EmplooyeeQueryApiTest extends ProjectAdminApiTestData {
    
    private static final int TEST_CLIENT_ID = 1000;
    private static final int TEST_PROJ_ID = 2220;
    private static final int TEST_TASK_ID = 1112220;
    private static final int TEST_EVENT_ID = 123401;
    private static final int TEST_EMPLOYEE_ID = 5000;
    private static final int TEST_TIMESHEET_ID = 848484840;
    private static final String TEST_PROJECT_NAME = "Project 2220";
    private static final String TEST_TASK_NAMES[] = new String[]{"Design and Analysis", 
            "Development", "Meetings", "Testing", "Holiday"};

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
    public void testFetch_Client_All_Success() {
        // Stub all clients fetch.
        VwEmployeeProjects mockCriteria = new VwEmployeeProjects();
        mockCriteria.setEmpId(TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwEmployeeProjectsFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee projects case setup failed");
        }
        
        EmployeeApiFactory f = new EmployeeApiFactory();
        EmployeeApi api = f.createApi(this.mockDaoClient);
        ProjectEmployeeDto criteria = ProjectObjectFactory.createEmployeeProjectDtoInstance(null);
        criteria.setEmpId(TEST_EMPLOYEE_ID);
        List<ClientDto> results = null;
        try {
            results = api.getClients(TEST_EMPLOYEE_ID);
        } catch (EmployeeApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ClientDto obj = results.get(ndx);
            Assert.assertEquals(obj.getClientId(), (TEST_CLIENT_ID + ndx));
            Assert.assertEquals(obj.getClientName(), (TEST_CLIENT_ID + ndx) + " Company");
        }
    }

   }