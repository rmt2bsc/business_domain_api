package org.rmt2.api.admin;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.dao.admin.ProjectAdminDaoException;
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
import org.rmt2.api.ProjectTrackerMockData;
import org.rmt2.constants.ApiTransactionCodes;
import org.rmt2.jaxb.AddressBookRequest;
import org.rmt2.jaxb.AddressBookResponse;
import org.rmt2.jaxb.AddressType;
import org.rmt2.jaxb.BusinessType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.ReplyStatusType;
import org.rmt2.jaxb.ZipcodeType;
import org.rmt2.util.ReplyStatusTypeBuilder;
import org.rmt2.util.addressbook.AddressTypeBuilder;
import org.rmt2.util.addressbook.BusinessTypeBuilder;
import org.rmt2.util.addressbook.ZipcodeTypeBuilder;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.foundation.TransactionApiException;
import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.messaging.webservice.router.MessageRoutingException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the Administration module of the Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class, ProjectAdminApiImpl.class })
public class ProjectAdminUpdateApiTest extends ProjectTrackerMockData {
    
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
        
        // Setup stubs for mocking client update web service calls
        this.setupClientWebServiceUpdateStub();

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
    public void testValidation_Modify_Client_Null_Client_Object() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        try {
            api.updateClient(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Modify_Client_Negative_ClientId() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchSingle.get(0));
        client.setClientId(-1234);
        try {
            api.updateClient(client);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testValidation_Modify_Client_Null_ClientName() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchSingle.get(0));
        client.setClientName(null);
        try {
            api.updateClient(client);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Modify_Client_Database_Access_Failure() {
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjClient.class)))
                    .thenThrow(new DatabaseException("A database access error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Modify single client case setup failed");
        }
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchSingle.get(0));
        try {
            api.updateClient(client);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof ProjectAdminDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Modify_Client_Web_Service_Call_Failure() {
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
        
        // Setup stubs for mocking client update web service calls
        this.setupClientWebServiceUpdateStub();
        
        when(this.mockMessageRouterHelper.routeXmlMessage(isA(String.class),
                isA(AddressBookRequest.class)))
                        .thenThrow(new MessageRoutingException("A web service call error occurred"));
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchSingle.get(0));
        try {
            api.updateClient(client);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof TransactionApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof MessageRoutingException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Modify_Client_Profile_NotFound_In_AddressBook_App() {
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
        
        // Setup stubs for mocking client update web service calls
        this.setupClientWebServiceUpdateStub();
        
        when(this.mockMessageRouterHelper.routeXmlMessage(isA(String.class),
                isA(AddressBookRequest.class))).thenReturn(null);
        
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ClientDto client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchSingle.get(0));
        try {
            api.updateClient(client);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof NotFoundException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsert_Client_Success() {
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjClient.class), eq(true))).thenReturn(TEST_NEW_CLIENT_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert single client case setup failed");
        }

        // Setup stubs for mocking client update web service calls
        this.setupClientWebServiceUpdateStub();

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

    private void setupClientWebServiceUpdateStub() {
        ObjectFactory jaxbObjFactory = new ObjectFactory();
        
        // Mock business type fetch response data
        ZipcodeType zip = ZipcodeTypeBuilder.Builder.create().withAreaCode("214").withZipcode(75232).withZipId(75232)
                .withCity("Dallas").withState("TX").withCountyName("Dallas").build();

        AddressType address = AddressTypeBuilder.Builder.create().withAddressLine1("AddressLine1")
                .withAddressLine2("AddressLine2").withAddressLine3("AddressLine3").withAddressLine4("AddressLine4")
                .withBusinessId(123456789).withAddrId(22222).withPhoneMain("8888888888").withZipcode(zip).build();

        BusinessType profile = BusinessTypeBuilder.Builder.create().withBusinessId(123456789)
                .withContactEmail("john.smith@gte.net").withContactPhoneExt("4444").withContactFirstname("john")
                .withContactLastname("terrell").withContactPhone("9999999999").withLongname("ABC Company")
                .withTaxId("77-7777777").withAddress(address).build();

        AddressBookResponse mockAddressBookResponse = jaxbObjFactory.createAddressBookResponse();
        mockAddressBookResponse.setProfile(jaxbObjFactory.createContactDetailGroup());
        mockAddressBookResponse.getProfile().getBusinessContacts().add(profile);

        // Mock business type web service update response data
        ReplyStatusType mockReplyStatusType = ReplyStatusTypeBuilder.Builder.create().withStatus(true)
                .withReturnCode(1).withMessage("SUCCESS").withDetailMessage("Business Type update complete").build();

        when(this.mockMessageRouterHelper.routeXmlMessage(
                isA(String.class), isA(AddressBookRequest.class))).thenReturn(mockAddressBookResponse, mockReplyStatusType);
        
        when(this.mockMessageRouterHelper.routeXmlMessage(
                eq(ApiTransactionCodes.CONTACTS_BUSINESS_UPDATE),
                isA(AddressBookRequest.class))).thenReturn(mockReplyStatusType);
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
    public void testError_Modify_Project_Database_Access_Failure() {
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjProject.class)))
                    .thenThrow(new DatabaseException("Database error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Modify single project case setup failed");
        }
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto project = ProjectObjectFactory.createProjectDtoInstance(this.mockProjectFetchSingle.get(0));
        try {
            api.updateProject(project);
            Assert.fail("Expected exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof ProjectAdminDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
   }
    
    @Test
    public void testValidation_Modify_Project_Null_Project_Object() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        try {
            api.updateProject(null);
            Assert.fail("Expected exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            Assert.assertEquals("Project data is required", e.getCause().getMessage());
            e.printStackTrace();
        }
   }
    
    @Test
    public void testValidation_Modify_Project_Negative_ProjectId() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto project = ProjectObjectFactory.createProjectDtoInstance(this.mockProjectFetchSingle.get(0));
        project.setProjId(-12345);
        try {
            api.updateProject(project);
            Assert.fail("Expected exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            Assert.assertEquals("The project id must be greater than or equal zero", e.getCause().getMessage());
            e.printStackTrace();
        }
   }
    
    @Test
    public void testValidation_Modify_Project_Null_Description() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto project = ProjectObjectFactory.createProjectDtoInstance(this.mockProjectFetchSingle.get(0));
        project.setProjectDescription(null);
        try {
            api.updateProject(project);
            Assert.fail("Expected exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            Assert.assertEquals("Project description is required", e.getCause().getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Modify_Project_Empty_Description() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto project = ProjectObjectFactory.createProjectDtoInstance(this.mockProjectFetchSingle.get(0));
        project.setProjectDescription("");
        try {
            api.updateProject(project);
            Assert.fail("Expected exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            Assert.assertEquals("Project description is required", e.getCause().getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Modify_Project_Null_EffectiveDate() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        ProjectDto project = ProjectObjectFactory.createProjectDtoInstance(this.mockProjectFetchSingle.get(0));
        project.setProjectEffectiveDate(null);
        try {
            api.updateProject(project);
            Assert.fail("Expected exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            Assert.assertEquals("Project effective date is required", e.getCause().getMessage());
            e.printStackTrace();
        }
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
    public void testError_Modify_Task_Database_Access_Failure() {
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTask.class)))
                    .thenThrow(new DatabaseException("A database error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Modify single task case setup failed");
        }
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        TaskDto task = ProjectObjectFactory.createTaskDtoInstance(this.mockProjTaskFetchSingle.get(0));
        try {
            api.updateTask(task);
            Assert.fail("Expected an exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof ProjectAdminDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
   }
    
    @Test
    public void testValidation_Modify_Task_Null_Task() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        try {
            api.updateTask(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            Assert.assertEquals("Task data is required", e.getCause().getMessage());
            e.printStackTrace();
        }
   }
    
    @Test
    public void testValidation_Modify_Task_Negative_TaskId() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        TaskDto task = ProjectObjectFactory.createTaskDtoInstance(this.mockProjTaskFetchSingle.get(0));
        task.setTaskId(-1234);
        try {
            api.updateTask(task);
            Assert.fail("Expected an exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            Assert.assertEquals("The task id must be greater than or equal zero", e.getCause().getMessage());
            e.printStackTrace();
        }
   }
    
    @Test
    public void testValidation_Modify_Task_Null_Description() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        TaskDto task = ProjectObjectFactory.createTaskDtoInstance(this.mockProjTaskFetchSingle.get(0));
        task.setTaskDescription(null);
        try {
            api.updateTask(task);
            Assert.fail("Expected an exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            Assert.assertEquals("Task Description is required", e.getCause().getMessage());
            e.printStackTrace();
        }
   }
    
    @Test
    public void testValidation_Modify_Task_Empty_Description() {
        ProjectAdminApiFactory f = new ProjectAdminApiFactory();
        ProjectAdminApi api = f.createApi(this.mockDaoClient);
        TaskDto task = ProjectObjectFactory.createTaskDtoInstance(this.mockProjTaskFetchSingle.get(0));
        task.setTaskDescription("");
        try {
            api.updateTask(task);
            Assert.fail("Expected an exception to be thrown");
        } catch (ProjectAdminApiException e) {
            Assert.assertTrue(e instanceof ProjectAdminApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            Assert.assertEquals("Task Description is required", e.getCause().getMessage());
            e.printStackTrace();
        }
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