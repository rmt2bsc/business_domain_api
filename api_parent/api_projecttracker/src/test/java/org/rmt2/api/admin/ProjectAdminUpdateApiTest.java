package org.rmt2.api.admin;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.sql.ResultSet;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjProject;
import org.dao.mapping.orm.rmt2.ProjTask;
import org.dto.ClientDto;
import org.dto.ProjectDto;
import org.dto.TaskDto;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.admin.ProjectAdminApi;
import org.modules.admin.ProjectAdminApiException;
import org.modules.admin.ProjectAdminApiFactory;
import org.modules.admin.ProjectAdminApiImpl;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectAdminApiTestData;
import org.rmt2.constants.ApiTransactionCodes;
import org.rmt2.jaxb.AddressBookRequest;
import org.rmt2.jaxb.AddressBookResponse;
import org.rmt2.jaxb.AddressType;
import org.rmt2.jaxb.BusinessType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.ReplyStatusType;
import org.rmt2.jaxb.ZipcodeType;
import org.rmt2.util.ReplyStatusTypeBuilder;

import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the Administration module of the Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class, ProjectAdminApiImpl.class })
public class ProjectAdminUpdateApiTest extends ProjectAdminApiTestData {
    
    private static final int TEST_CLIENT_ID = 1000;
    private static final int TEST_NEW_CLIENT_ID = 11223344;
    private static final int TEST_PROJ_ID = 2220;
    private static final int TEST_NEW_PROJ_ID = 22334455;
    private static final int TEST_TASK_ID = 1112220;
    private static final int TEST_NEW_TASK_ID = 33445566;
    private static final int TEST_EVENT_ID = 123401;
    private static final int TEST_PROJECT_TASK_ID = 444441;
    private static final int TEST_TIMESHEET_ID = 848484840;
    private static final String TEST_PROJECT_NAME = "Project 2220";
    private static final String TEST_TASK_NAMES[] = new String[]{"Design and Analysis", 
            "Development", "Meetings", "Testing", "Holiday"};

    private MessageRouterHelper mockMessageRouterHelper;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockMessageRouterHelper = Mockito.mock(MessageRouterHelper.class);
        PowerMockito.whenNew(MessageRouterHelper.class).withNoArguments().thenReturn(this.mockMessageRouterHelper);
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
    public void testModify_Client_Success() {
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjClient.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Modify single client case setup failed");
        }
        
        ProjClient mockCriteria = new ProjClient();
        mockCriteria.setClientId(TEST_CLIENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockClientFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        ObjectFactory jaxbObjFactory = new ObjectFactory();
        
        // Mock business type web service update response data
        ReplyStatusType mockReplyStatusType = ReplyStatusTypeBuilder.Builder
                .create().withStatus(true).withReturnCode(1)
                .withMessage("SUCCESS")
                .withDetailMessage("Business Type update complete").build();
        
        // Mock business type fetch response data
        ZipcodeType zip = jaxbObjFactory.createZipcodeType();
        AddressType address = jaxbObjFactory.createAddressType();
        address.setZip(zip);
        BusinessType profile = jaxbObjFactory.createBusinessType();
        profile.setAddress(address);
        AddressBookResponse mockAddressBookResponse = jaxbObjFactory
                .createAddressBookResponse();
        mockAddressBookResponse
                .setProfile(jaxbObjFactory.createContactDetailGroup());
        mockAddressBookResponse.getProfile().getBusinessContacts().add(profile);
        profile.setBusinessId(BigInteger.valueOf(123456789));
        profile.setContactEmail("john.smith@gte.net");
        profile.setContactExt("4444");
        profile.setContactFirstname("john");
        profile.setContactLastname("smith");
        profile.setContactPhone("9999999999");
        profile.setLongName("ABC Company");
        profile.setTaxId("77-7777777");
        profile.getAddress().setAddr1("AddressLine1");
        profile.getAddress().setAddr2("AddressLine2");
        profile.getAddress().setAddr3("AddressLine3");
        profile.getAddress().setAddr4("AddressLine4");
        profile.getAddress().setAddrId(BigInteger.valueOf(22222));
        profile.getAddress().setBusinessId(BigInteger.valueOf(123456789));
        profile.getAddress().setPhoneMain("8888888888");
        profile.getAddress().getZip().setZipId(BigInteger.valueOf(75232));
        profile.getAddress().getZip().setCity("Dallas");
        profile.getAddress().getZip().setState("TX");
        profile.getAddress().getZip().setZipcode(BigInteger.valueOf(75232));
        profile.getAddress().getZip().setAreaCode("214");
        profile.getAddress().getZip().setCountyName("Dallas");
        when(this.mockMessageRouterHelper.routeXmlMessage(
                isA(String.class), isA(AddressBookRequest.class))).thenReturn(mockAddressBookResponse, mockReplyStatusType);
        
        when(this.mockMessageRouterHelper.routeXmlMessage(
                eq(ApiTransactionCodes.CONTACTS_BUSINESS_UPDATE),
                isA(AddressBookRequest.class))).thenReturn(mockReplyStatusType);
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchSingle.get(0));
        int results = 0;
        try {
            results = api.updateClient(client);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results);
   }
    
    @Test
    public void testInsert_Client_Success() {
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjClient.class), eq(true))).thenReturn(TEST_NEW_CLIENT_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert single client case setup failed");
        }
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchSingle.get(0));
        client.setClientId(0);
        int results = 0;
        try {
            results = api.updateClient(client);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(TEST_NEW_CLIENT_ID, results);
   }
    
    @Test
    public void testDelete_Client_Success() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjClient.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete single client case setup failed");
        }
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchSingle.get(0));
        int results = 0;
        try {
            results = api.deleteClient(client);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results);
   }
    
    @Test
    public void testModify_Project_Success() {
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjProject.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Modify single project case setup failed");
        }
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto project = ProjectObjectFactory.createProjectDtoInstance(this.mockProjectFetchSingle.get(0));
        int results = 0;
        try {
            results = api.updateProject(project);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results);
   }
    
    @Test
    public void testInsert_Project_Success() {
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjProject.class), eq(true))).thenReturn(TEST_NEW_PROJ_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert single project case setup failed");
        }
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto project = ProjectObjectFactory.createProjectDtoInstance(this.mockProjectFetchSingle.get(0));
        project.setProjId(0);
        int results = 0;
        try {
            results = api.updateProject(project);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(TEST_NEW_PROJ_ID, results);
   }
    
    @Test
    public void testDelete_Project_Success() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjProject.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete single project case setup failed");
        }
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto project = ProjectObjectFactory.createProjectDtoInstance(this.mockProjectFetchSingle.get(0));
        int results = 0;
        try {
            results = api.deleteProject(project);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results);
   }
    
    @Test
    public void testModify_Task_Success() {
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTask.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Modify single task case setup failed");
        }
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        TaskDto task = ProjectObjectFactory.createTaskDtoInstance(this.mockProjTaskFetchSingle.get(0));
        int results = 0;
        try {
            results = api.updateTask(task);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results);
   }
    
    @Test
    public void testInsert_Task_Success() {
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTask.class), eq(true))).thenReturn(TEST_NEW_TASK_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert single task case setup failed");
        }
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        TaskDto task = ProjectObjectFactory.createTaskDtoInstance(this.mockProjTaskFetchSingle.get(0));
        task.setTaskId(0);
        int results = 0;
        try {
            results = api.updateTask(task);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(TEST_NEW_TASK_ID, results);
   }
    
    @Test
    public void testDelete_Task_Success() {
        try {
            when(this.mockPersistenceClient.deleteRow(isA(ProjTask.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Delete single task case setup failed");
        }
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        TaskDto task = ProjectObjectFactory.createTaskDtoInstance(this.mockProjTaskFetchSingle.get(0));
        int results = 0;
        try {
            results = api.deleteTask(task);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results);
   }
}