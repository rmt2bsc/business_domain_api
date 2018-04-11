package org.rmt2.api.timesheet;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dao.mapping.orm.rmt2.VwTimesheetHours;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.timesheet.TimesheetConst;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.modules.timesheet.invoice.InvoiceTimesheetApi;
import org.modules.timesheet.invoice.InvoiceTimesheetApiFactory;
import org.modules.timesheet.invoice.InvoiceTimesheetApiImpl;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectTrackerMockDataFactory;
import org.rmt2.api.invoicing.InvoicingMockData;
import org.rmt2.jaxb.AccountingTransactionRequest;
import org.rmt2.jaxb.AccountingTransactionResponse;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.ReplyStatusType;
import org.rmt2.util.ReplyStatusTypeBuilder;

import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Test the invoicing functionality of the Timesheet module of the Project Tracker
 * Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class, InvoiceTimesheetApiImpl.class, TimesheetApiFactory.class })
public class TimesheetInvoicingApiTest extends InvoicingMockData {

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

//    private TimesheetDto buildTimesheetDto(boolean newTimesheet) {
//        ProjTimesheet ormTs = this.mockProjTimesheetSingle.get(0);
//        if (newTimesheet) {
//            ormTs.setTimesheetId(0);
//            ormTs.setProjId(0);
//            ormTs.setDisplayValue(null);
//        }
//        TimesheetDto ts = TimesheetObjectFactory.createTimesheetDtoInstance(ormTs);
//        return ts;
//    }
//    
//    private Map<ProjectTaskDto, List<EventDto>> buildTimesheetHoursDtoMap(boolean newTimesheet) {
//        Map<ProjectTaskDto, List<EventDto>> hours = new HashMap<>(); 
//        for (ProjProjectTask pt : this.mockProjProjectTaskMultiple) {
//            if (newTimesheet) {
//                pt.setTimesheetId(0);
//                pt.setProjectTaskId(0);
//            }
//            ProjectTaskDto ptDto = ProjectObjectFactory.createProjectTaskDtoInstance(pt);
//            List<EventDto> eventsDto = this.buildTimesheetEventDtoList(pt.getProjectTaskId(), newTimesheet);
//            hours.put(ptDto, eventsDto);
//        }
//        return hours;
//    }
//    
//    private List<EventDto> buildTimesheetEventDtoList(int projectTaskId, boolean newTimesheet) {
//        List<ProjEvent> projEvents = createMockMultiple_Day_Task_Events(projectTaskId);
//        List<EventDto> eventsDto = new ArrayList<>();
//        for (ProjEvent evt : projEvents) {
//            if (newTimesheet) {
//                evt.setEventId(0);
//            }
//            EventDto evtDto = ProjectObjectFactory.createEventDtoInstance(evt);
//            eventsDto.add(evtDto);
//        }
//        return eventsDto;
//    }
    
    private void setupClientWebServiceInvoiceStub() {
        ObjectFactory jaxbObjFactory = new ObjectFactory();
        AccountingTransactionResponse mockResponse = jaxbObjFactory.createAccountingTransactionResponse();

        // Mock reply status type so to receive the sales order invoice id
        ReplyStatusType mockReplyStatusType = ReplyStatusTypeBuilder.Builder.create().withStatus(true)
                .withReturnCode(ProjectTrackerMockDataFactory.TEST_INVOICE_ID).withMessage("SUCCESS").withDetailMessage("Timesheet Invoice operation completed successfully").build();

        mockResponse.setReplyStatus(mockReplyStatusType);
        when(this.mockMessageRouterHelper.routeXmlMessage(isA(String.class), 
                isA(AccountingTransactionRequest.class))).thenReturn(mockResponse);
    }
    
    @Test
    public void testSuccess_Invoice_Single_Timesheet() {
        // Stub Timesheet Fetch
        ProjTimesheet mockTsFetchCriteria = new ProjTimesheet();
        mockTsFetchCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTsFetchCriteria))).thenReturn(this.mockProjTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        // Stub Client Fetch
        ProjClient mockClientCriteria = new ProjClient();
        mockClientCriteria.setClientId(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockClientCriteria))).thenReturn(this.mockClientFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        // Stub Employee Fetch
        ProjEmployee mockEmpCriteria = new ProjEmployee();
        mockEmpCriteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockEmpCriteria))).thenReturn(this.mockEmployeeFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee case setup failed");
        }
        
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Stub Project/Employee Fetch
        try {
            when(this.mockPersistenceClient.retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Stub Approved Timehseet Current Status Fetch
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to APPROVED.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet approved current history case setup failed");
        }
        
        // Stub timesheet update
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheet.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet case setup failed");
        }
        
        // Stub timesheet changing of status to INVOICE
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        // Setup web service stup
        this.setupClientWebServiceInvoiceStub();
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        int results = 0;
        try {
            results = api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_INVOICE_ID, results);
    }
    

    @Test
    public void testSuccess_Invoice_Multiple_Clients_Timesheet() {
        // Stub Timesheet Fetch
//        VwTimesheetList mockTsFetchCriteria = new VwTimesheetList();
//        mockTsFetchCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwTimesheetList.class))).thenReturn(
                            this.mockClientTimesheetMap
                                    .get(InvoicingMockData.TEST_CLIENT_ID[0]),
                            this.mockClientTimesheetMap
                                    .get(InvoicingMockData.TEST_CLIENT_ID[1]),
                            this.mockClientTimesheetMap
                                    .get(InvoicingMockData.TEST_CLIENT_ID[2]),
                            this.mockClientTimesheetMap
                                    .get(InvoicingMockData.TEST_CLIENT_ID[3]));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        // Stub Client Fetch
        ProjClient mockClientCriteria = new ProjClient();
        mockClientCriteria.setClientId(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockClientCriteria))).thenReturn(this.mockClientFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        // Stub Employee Fetch
        ProjEmployee mockEmpCriteria = new ProjEmployee();
        mockEmpCriteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockEmpCriteria))).thenReturn(this.mockEmployeeFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee case setup failed");
        }
        
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Stub Project/Employee Fetch
        try {
            when(this.mockPersistenceClient.retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Stub Approved Timehseet Current Status Fetch
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to APPROVED.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet approved current history case setup failed");
        }
        
        // Stub timesheet update
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheet.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet case setup failed");
        }
        
        // Stub timesheet changing of status to INVOICE
        try {
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet current history case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(ProjTimesheetHist.class), eq(true))).thenReturn(
                            ProjectTrackerMockDataFactory.TEST_NEW_TIMESHEET_STATUS_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert timesheet current history case setup failed");
        }
        
        // Setup web service stup
        this.setupClientWebServiceInvoiceStub();
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        List<Integer> results = null;
        try {
//            List clientList =  Arrays.asList(InvoicingMockData.TEST_CLIENT_ID); //Arrays.stream(InvoicingMockData.TEST_CLIENT_ID).boxed().collect(Collectors.toList());
            results = api.invoice(InvoicingMockData.TEST_CLIENT_ID_LIST);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
//        Assert.assertNotNull(results);
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_INVOICE_ID, results);
    }
    
}