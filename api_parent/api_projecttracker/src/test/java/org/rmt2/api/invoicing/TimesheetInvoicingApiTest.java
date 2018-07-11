package org.rmt2.api.invoicing;

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
import org.modules.employee.EmployeeApiException;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.modules.timesheet.invoice.InvalidStateForBillingException;
import org.modules.timesheet.invoice.InvoiceTimesheetApi;
import org.modules.timesheet.invoice.InvoiceTimesheetApiException;
import org.modules.timesheet.invoice.InvoiceTimesheetApiFactory;
import org.modules.timesheet.invoice.InvoiceTimesheetApiImpl;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectTrackerMockDataFactory;
import org.rmt2.jaxb.AccountingTransactionRequest;
import org.rmt2.jaxb.AccountingTransactionResponse;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.ReplyStatusType;
import org.rmt2.util.ReplyStatusTypeBuilder;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.foundation.TransactionApiException;
import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.messaging.webservice.router.MessageRoutingException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
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

    private void setupClientWebServiceInvoiceStub() {
        // Mock sequence of responses based on the number of clients that we are processing
        when(this.mockMessageRouterHelper.routeXmlMessage(isA(String.class),
                isA(AccountingTransactionRequest.class)))
                        .thenReturn(this.createResponse(ProjectTrackerMockDataFactory.TEST_INVOICE_ID),
                                this.createResponse(ProjectTrackerMockDataFactory.TEST_INVOICE_ID + 1),
                                this.createResponse(ProjectTrackerMockDataFactory.TEST_INVOICE_ID + 2),
                                this.createResponse(ProjectTrackerMockDataFactory.TEST_INVOICE_ID + 3));
    }
    
    private AccountingTransactionResponse createResponse(int invoiceId) {
        ObjectFactory jaxbObjFactory = new ObjectFactory();
        AccountingTransactionResponse mockResponse = jaxbObjFactory.createAccountingTransactionResponse();
        ReplyStatusType mockReplyStatusType = ReplyStatusTypeBuilder.Builder.create().withStatus("200")
                .withReturnCode(invoiceId)
                .withMessage("SUCCESS")
                .withDetailMessage("Timesheet Invoice operation completed successfully " + invoiceId)
                .build();
        mockResponse.setReplyStatus(mockReplyStatusType);
        return mockResponse;
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
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
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
        try {
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwTimesheetList.class))).thenReturn(
                            this.mockClientTimesheetMap.get(InvoicingMockData.TEST_CLIENT_ID[0]),
                            this.mockClientTimesheetMap.get(InvoicingMockData.TEST_CLIENT_ID[1]),
                            this.mockClientTimesheetMap.get(InvoicingMockData.TEST_CLIENT_ID[2]),
                            this.mockClientTimesheetMap.get(InvoicingMockData.TEST_CLIENT_ID[3]));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        // Stub Client Fetch
        ProjClient mockClientCriteria = new ProjClient();
        mockClientCriteria.setClientId(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjClient.class)))
                    .thenReturn(
                            this.mockClientMap.get(InvoicingMockData.TEST_CLIENT_ID[0]),
                            this.mockClientMap.get(InvoicingMockData.TEST_CLIENT_ID[1]),
                            this.mockClientMap.get(InvoicingMockData.TEST_CLIENT_ID[2]),
                            this.mockClientMap.get(InvoicingMockData.TEST_CLIENT_ID[3]));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        // Stub Employee Fetch
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEmployee.class)))
                    .thenReturn(
                            this.mockEmployeeMap.get(InvoicingMockData.TEST_EMPLOYEE_ID[0]),
                            this.mockEmployeeMap.get(InvoicingMockData.TEST_EMPLOYEE_ID[1]),
                            this.mockEmployeeMap.get(InvoicingMockData.TEST_EMPLOYEE_ID[2]),
                            this.mockEmployeeMap.get(InvoicingMockData.TEST_EMPLOYEE_ID[3]));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all employee case setup failed");
        }
        
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(VwTimesheetHours.class))).thenReturn(
                            this.mockTimesheetHoursMap.get(InvoicingMockData.TEST_TIMESHEET_ID[0]),
                            this.mockTimesheetHoursMap.get(InvoicingMockData.TEST_TIMESHEET_ID[1]),
                            this.mockTimesheetHoursMap.get(InvoicingMockData.TEST_TIMESHEET_ID[2]),
                            this.mockTimesheetHoursMap.get(InvoicingMockData.TEST_TIMESHEET_ID[3]));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Stub Project/Employee Fetch
        try {
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Stub Approved Timehseet Current Status Fetch
        try {
            // Change status id of mock data to APPROVED.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
            when(this.mockPersistenceClient.retrieveList(isA(ProjTimesheetHist.class))).thenReturn(this.mockCurrentProjTimesheetHist);
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
            results = api.invoice(InvoicingMockData.TEST_CLIENT_ID_LIST);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            Assert.assertEquals((ProjectTrackerMockDataFactory.TEST_INVOICE_ID + ndx), results.get(ndx), 0);    
        }
    }
    
    
    @Test
    public void testError_Update_Timesheet_Status_DB_Access_Fault() {
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
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
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
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheetHist.class))).thenThrow(new DatabaseException(
                            "Database error occurred updating timesheet status"));
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
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("An error occurred attempting to change the status of a time sheet during invoice posting",
                    e.getMessage());
        }
    }
    
    @Test
    public void testError_Update_Timesheet_DB_Access_Fault() {
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
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
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
            when(this.mockPersistenceClient.updateRow(isA(ProjTimesheet.class)))
                    .thenThrow(new DatabaseException("Error occurred updating timesheet invoice reference number"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update timesheet case setup failed");
        }
        
        // Setup web service stup
        this.setupClientWebServiceInvoiceStub();
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("DAO error occurred attempting to post time sheet invoicing", e.getMessage());
        }
    }
    
    @Test
    public void testError_Submit_Timesheet_WebService_Return_Zero_InvoiceId() {
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
        
        // Stub Approved Timehseet Current Status Fetch
        try {
            // Change status id of mock data to APPROVED.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
            when(this.mockPersistenceClient.retrieveList(isA(ProjTimesheetHist.class))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet approved current history case setup failed");
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
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Mock web service response to contain an invoiceId equaling zero.
        when(this.mockMessageRouterHelper.routeXmlMessage(isA(String.class),
                isA(AccountingTransactionRequest.class))).thenReturn(this.createResponse(0));
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals(
                    "Detected a problem with time sheet invoicing.  An invalid sales order invoice id was returned from Accounting Sales Order service",
                    e.getMessage());
        }
    }
    
    @Test
    public void testError_Submit_Timesheet_WebService_Reply_Is_Null() {
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
        
        // Stub Approved Timehseet Current Status Fetch
        try {
            // Change status id of mock data to APPROVED.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
            when(this.mockPersistenceClient.retrieveList(isA(ProjTimesheetHist.class))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet approved current history case setup failed");
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
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Mock sequence of responses based on the number of clients that we are processing
        when(this.mockMessageRouterHelper.routeXmlMessage(isA(String.class),
                isA(AccountingTransactionRequest.class))).thenReturn(null);
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("An invalid response was returned from the Timesheet-to-sales order web service operation",
                    e.getMessage());
        }
    }
    
    @Test
    public void testError_Submit_Timesheet_WebService_Communication_Fault() {
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
        
        // Stub Approved Timehseet Current Status Fetch
        try {
            // Change status id of mock data to APPROVED.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
            when(this.mockPersistenceClient.retrieveList(isA(ProjTimesheetHist.class))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet approved current history case setup failed");
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
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Mock sequence of responses based on the number of clients that we are processing
        when(this.mockMessageRouterHelper.routeXmlMessage(isA(String.class),
                isA(AccountingTransactionRequest.class))).thenThrow(new MessageRoutingException(
                        "Message Router error submiting timesheet billing"));
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("A web service problem occurred sending time sheet(s) to accounting for the purpose of creating a sales order: timesheet id's [111]",
                    e.getMessage());
            Assert.assertTrue(e.getCause() instanceof TransactionApiException);
            Assert.assertEquals("Error occurred routing Timesheet XML message to its designated API handler", e.getCause().getMessage());
            Assert.assertTrue(e.getCause().getCause() instanceof MessageRoutingException);
            Assert.assertEquals("Message Router error submiting timesheet billing", e.getCause().getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Fetch_Timesheet_Current_Status_DB_Access_Fault() {
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
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Stub Approved Timehseet Current Status Fetch
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to APPROVED.
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(new DatabaseException("Database access error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet approved current history case setup failed");
        }
        
        // Setup web service stup
        this.setupClientWebServiceInvoiceStub();
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("Unable to prepare billing for time sheet, 0000000111", e.getMessage());
        }
    }
    
    @Test
    public void testError_Timesheet_Current_Status_Incorrect() {
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
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Stub Approved Timehseet Current Status Fetch
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to APPROVED.
            this.mockCurrentProjTimesheetHist.get(0).setTimesheetStatusId(TimesheetConst.STATUS_SUBMITTED);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet approved current history case setup failed");
        }
        
        // Setup web service stup
        this.setupClientWebServiceInvoiceStub();
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("Time sheet billing preparation failed. The processing of all time sheets is aborted for client, 1110.",
                    e.getMessage());
            Assert.assertTrue(e.getCause() instanceof InvalidStateForBillingException);
            Assert.assertEquals("Timsheet's current status is invalid for billing/invoicing process [time sheet=111]",
                    e.getCause().getMessage());
        }
    }
    
    @Test
    public void testError_Timesheet_Current_Status_Unavailable() {
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
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Stub Approved Timehseet Current Status Fetch
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            // Change status id of mock data to APPROVED.
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet approved current history case setup failed");
        }
        
        // Setup web service stup
        this.setupClientWebServiceInvoiceStub();
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("Time sheet billing preparation failed. The processing of all time sheets is aborted for client, 1110.",
                    e.getMessage());
            Assert.assertTrue(e.getCause() instanceof InvalidStateForBillingException);
            Assert.assertEquals("Unable to identify timesheet's current status [time sheet=111]",
                    e.getCause().getMessage());
        }
    }
    
    
    
    @Test
    public void testError_Fetch_Timesheet_Hours_ProjectEmployee_Unavailable() {
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
        
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Stub Project/Employee fetch
        try {
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals(
                    "Unable to process time sheet hours due to project/employee profile is unavailable [project id=4440, employee id=2220]",
                    e.getMessage());
        }
    }
    
    @Test
    public void testError_Fetch_Timesheet_Hours_ProjectEmployee_DB_Access_Fault() {
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
        
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Stub Project/Employee fetch
        try {
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient.retrieveList(isA(
                    VwEmployeeProjects.class))).thenThrow(new DatabaseException(
                            "Database error fetching Project/Employee profile"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("Error occurred fetching project/employee profile while processing time sheet hours",
                    e.getMessage());
            Assert.assertTrue(e.getCause() instanceof EmployeeApiException);
        }
    }
    
    @Test
    public void testError_Fetch_Timesheet_Hours_DB_Access_Fault() {
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
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria)))
                    .thenThrow(new DatabaseException("Database error fetching time sheet hours"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("Error occurred fetching invoice hours for time sheet, 111", e.getMessage());
        }
    }
    
    @Test
    public void testError_Invoice_Multiple_Clients_Approved_Timesheet_Notfound() {
        // Stub Timesheet Fetch
        try {
            when(this.mockPersistenceClient.retrieveList(isA(VwTimesheetList.class))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        // Stub Client Fetch
        ProjClient mockClientCriteria = new ProjClient();
        mockClientCriteria.setClientId(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjClient.class)))
                    .thenReturn(
                            this.mockClientMap.get(InvoicingMockData.TEST_CLIENT_ID[0]),
                            this.mockClientMap.get(InvoicingMockData.TEST_CLIENT_ID[1]),
                            this.mockClientMap.get(InvoicingMockData.TEST_CLIENT_ID[2]),
                            this.mockClientMap.get(InvoicingMockData.TEST_CLIENT_ID[3]));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(InvoicingMockData.TEST_CLIENT_ID_LIST);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("Skip invoicing time sheets for client, 1110, due to client does not exists", e.getMessage());
        }
    }
   
    @Test
    public void testError_Invoice_Timesheet_Client_NotFound() {
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
            when(this.mockPersistenceClient.retrieveList(eq(mockClientCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof NotFoundException);
            Assert.assertEquals("Client profile was not found [client id=1110]", e.getMessage());
        }
    }
    
    @Test
    public void testError_Invoice_Timesheet_Client_DB_Access_Fault() {
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
            when(this.mockPersistenceClient.retrieveList(eq(mockClientCriteria)))
                  .thenThrow(new DatabaseException("Database Error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single client case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("Error fetching client profile", e.getMessage());
        }
    }
    
    @Test
    public void testError_Invoice_Timesheet_Fetch_DB_Access_Fault() {
        // Stub Timesheet Fetch
        ProjTimesheet mockTsFetchCriteria = new ProjTimesheet();
        mockTsFetchCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTsFetchCriteria)))
                            .thenThrow(new DatabaseException("Database excepiton occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvoiceTimesheetApiException);
            Assert.assertEquals("Problem fetching timesheet by timesheet id, 111", e.getMessage());
        }
    }
    
    @Test
    public void testError_Invoice_Timesheet_Fetch_NotFound() {
        // Stub Timesheet Fetch
        ProjTimesheet mockTsFetchCriteria = new ProjTimesheet();
        mockTsFetchCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTsFetchCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof NotFoundException);
            Assert.assertEquals("Timesheet, 111, cannot be found", e.getMessage());
        }
    }
    
    
    @Test
    public void testValidation_Invoice_Multiple_Null_ClientId_Element() {
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            api.invoice(InvoicingMockData.TEST_NULL_CLIENT_ID_LIST);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("All elements in the list client id's are required to not be null", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Invoice_Multiple_ClientId_List() {
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            List<Integer> nullList = null;
            api.invoice(nullList);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Client Id list is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Invoice_Timesheet_TimesheetId_Null() {
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            Integer nullParm = null;
            api.invoice(nullParm);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Time sheet Id is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Invoice_Timesheet_TimesheetId_Negative() {
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            Integer parm = -1234;
            api.invoice(parm);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Time sheet Id must be greater than zero", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_Invoice_Timesheet_TimesheetId_Zero() {
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        try {
            Integer parm = 0;
            api.invoice(parm);
            Assert.fail("Expected an exception to be thrown for this test");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("Time sheet Id must be greater than zero", e.getMessage());
        }
    }
}