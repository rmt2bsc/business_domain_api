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
        ReplyStatusType mockReplyStatusType = ReplyStatusTypeBuilder.Builder.create().withStatus(true)
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
            when(this.mockPersistenceClient.retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
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
//            List clientList =  Arrays.asList(InvoicingMockData.TEST_CLIENT_ID); //Arrays.stream(InvoicingMockData.TEST_CLIENT_ID).boxed().collect(Collectors.toList());
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
    
}