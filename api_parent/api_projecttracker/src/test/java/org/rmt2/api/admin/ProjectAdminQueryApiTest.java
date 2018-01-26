package org.rmt2.api.admin;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProject;
import org.dao.mapping.orm.rmt2.ProjTask;
import org.dto.ClientDto;
import org.dto.EventDto;
import org.dto.ProjectDto;
import org.dto.TaskDto;
import org.dto.adapter.orm.ProjectObjectFactory;
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
import com.util.RMT2Date;
import com.util.RMT2String;

/**
 * Tests the Administration module of the Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class ProjectAdminQueryApiTest extends ProjectAdminApiTestData {
    
    private static final int TEST_CLIENT_ID = 1000;
    private static final int TEST_PROJ_ID = 2220;
    private static final int TEST_TASK_ID = 1112220;
    private static final int TEST_EVENT_ID = 123401;
    private static final int TEST_PROJECT_TASK_ID = 444441;

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
    public void testFetch_ClientAll_Success() {
        // Stub all clients fetch.
        ProjClient mockCriteria = new ProjClient();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockClientFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all clients case setup failed");
        }
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto criteria = ProjectObjectFactory.createClientDtoInstance(null);
        List<ClientDto> results = null;
        try {
            results = api.getClient(criteria);
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
    public void testFetch_ClientSingle_Success() {
        // Stub all clients fetch.
        ProjClient mockCriteria = new ProjClient();
        mockCriteria.setClientId(TEST_CLIENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockClientFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto criteria = ProjectObjectFactory.createClientDtoInstance(null);
        criteria.setClientId(TEST_CLIENT_ID);
        List<ClientDto> results = null;
        try {
            results = api.getClient(criteria);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ClientDto obj = results.get(0);
        Assert.assertEquals(obj.getClientId(), TEST_CLIENT_ID);
        Assert.assertEquals(obj.getBusinessId(), 1350);
        Assert.assertEquals(obj.getClientName(), "1000 Company");
        Assert.assertEquals(obj.getClientBillRate(), 70.00, 0);
        Assert.assertEquals(obj.getClientOtBillRate(), 80.00, 0);
        Assert.assertEquals(obj.getClientContactFirstname(), "steve");
        Assert.assertEquals(obj.getClientContactLastname(), "gadd");
        Assert.assertEquals(obj.getClientContactPhone(), "0000000000");
        Assert.assertEquals(obj.getClientContactEmail(), "stevegadd@gte.net");
    }
    
    @Test
    public void testFetch_ProjectAll_Success() {
        // Stub all clients fetch.
        ProjProject mockCriteria = new ProjProject();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjectFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all projects case setup failed");
        }

        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto criteria = ProjectObjectFactory.createProjectDtoInstance(null);
        List<ProjectDto> results = null;
        try {
            results = api.getProject(criteria);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectDto obj = results.get(ndx);
            Assert.assertEquals(obj.getProjId(), (2220 + ndx));
            Assert.assertEquals(obj.getClientId(), TEST_CLIENT_ID);
            Assert.assertEquals(obj.getProjectDescription(), ("Project 222" + ndx));
            Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-0" + (ndx + 1) + "-01"));
            Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-0" + (ndx + 2) + "-01"));
        }
    }

    @Test
    public void testFetch_ProjectSingle_Success() {
        // Stub all clients fetch.
        ProjProject mockCriteria = new ProjProject();
        mockCriteria.setProjId(TEST_PROJ_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjectFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all projects case setup failed");
        }

        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto criteria = ProjectObjectFactory.createProjectDtoInstance(null);
        criteria.setProjId(TEST_PROJ_ID);
        List<ProjectDto> results = null;
        try {
            results = api.getProject(criteria);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ProjectDto obj = results.get(0);
        Assert.assertEquals(obj.getProjId(), 2220);
        Assert.assertEquals(obj.getClientId(), TEST_CLIENT_ID);
        Assert.assertEquals(obj.getProjectDescription(), "Project 2220");
        Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-01-01"));
        Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-02-01"));
    }

    @Test
    public void testFetch_TaskAll_Success() {
        // Stub all clients fetch.
        ProjTask mockCriteria = new ProjTask();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all tasks case setup failed");
        }

        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        TaskDto criteria = ProjectObjectFactory.createTaskDtoInstance(null);
        List<TaskDto> results = null;
        try {
            results = api.getTask(criteria);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TaskDto obj = results.get(ndx);
            Assert.assertEquals(obj.getTaskId(), (TEST_TASK_ID + ndx));
            Assert.assertNotNull(obj.getTaskDescription());
        }
    }

    @Test
    public void testFetch_TaskSingle_Success() {
        // Stub all clients fetch.
        ProjTask mockCriteria = new ProjTask();
        mockCriteria.setTaskId(TEST_TASK_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTaskFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single task case setup failed");
        }

        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        TaskDto criteria = ProjectObjectFactory.createTaskDtoInstance(null);
        criteria.setTaskId(TEST_TASK_ID);
        List<TaskDto> results = null;
        try {
            results = api.getTask(criteria);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        TaskDto obj = results.get(0);
        Assert.assertEquals(obj.getTaskId(), TEST_TASK_ID);
        Assert.assertEquals(obj.getTaskBillable(), 1);
        Assert.assertEquals(obj.getTaskDescription(), "Design and Analysis");
    }
    
    
    @Test
    public void testFetch_EventAll_Success() {
        // Stub all clients fetch.
        ProjEvent mockCriteria = new ProjEvent();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjEventFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all events case setup failed");
        }

        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        EventDto criteria = ProjectObjectFactory.createEventDtoInstance(null);
        List<EventDto> results = null;
        try {
            results = api.getEvent(criteria, null, null);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            EventDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEventId(), (TEST_EVENT_ID + ndx));
            Assert.assertEquals(obj.getProjectTaskId(), (TEST_PROJECT_TASK_ID + ndx));
            Assert.assertEquals(obj.getEventDate(), RMT2Date.stringToDate("2018-01-0" + (ndx + 1)));
            Assert.assertEquals(obj.getEventHours(), 8, 0);
        }
    }
    
    @Test
    public void testFetch_EventSingle_Success() {
        // Stub all clients fetch.
        ProjEvent mockCriteria = new ProjEvent();
        mockCriteria.setEventId(TEST_EVENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjEventFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single event case setup failed");
        }

        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        EventDto criteria = ProjectObjectFactory.createEventDtoInstance(null);
        criteria.setEventId(TEST_EVENT_ID);
        List<EventDto> results = null;
        try {
            results = api.getEvent(criteria, null, null);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        EventDto obj = results.get(0);
        Assert.assertEquals(obj.getEventId(), TEST_EVENT_ID);
        Assert.assertEquals(obj.getProjectTaskId(), TEST_PROJECT_TASK_ID);
        Assert.assertEquals(obj.getEventDate(), RMT2Date.stringToDate("2018-01-01"));
        Assert.assertEquals(obj.getEventHours(), 8, 0);
    }
}