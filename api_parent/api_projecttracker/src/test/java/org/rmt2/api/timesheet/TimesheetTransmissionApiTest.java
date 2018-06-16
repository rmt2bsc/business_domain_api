package org.rmt2.api.timesheet;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetDto;
import org.dto.TimesheetHistDto;
import org.dto.adapter.orm.EmployeeObjectFactory;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.dto.adapter.orm.TimesheetObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.timesheet.TimesheetApiFactory;
import org.modules.timesheet.TimesheetTransmissionApi;
import org.modules.timesheet.TimesheetTransmissionException;
import org.modules.timesheet.TimesheetTransmissionValidationException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.SystemException;
import com.api.messaging.MessageException;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2Date;

/**
 * Test the timesheet transmission functionality of the Timesheet module of the
 * Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Rmt2OrmClientFactory.class, SmtpFactory.class, RMT2Date.class})
public class TimesheetTransmissionApiTest extends TimesheetMockData {

    private  ClientDto client;
    private  EmployeeDto employee;
    private  EmployeeDto manager;
    private TimesheetDto timesheet;
    private TimesheetDto timesheetExt;
    private TimesheetHistDto currentStatus;
    private Map<ProjectTaskDto, List<EventDto>> hours;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.createInputData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private void createInputData() {
        this.client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchMultiple.get(0));
        this.mockExtEmployeeFetchSingle.get(0).setFirstname("John");
        this.mockExtEmployeeFetchSingle.get(0).setLastname("Smith");
        this.employee = EmployeeObjectFactory.createEmployeeExtendedDtoInstance(this.mockExtEmployeeFetchSingle.get(0));
        this.manager = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockManagerFetchSingle.get(0));
        this.timesheet = TimesheetObjectFactory.createTimesheetDtoInstance(this.mockProjTimesheetSingle.get(0));
        this.timesheetExt = TimesheetObjectFactory.createTimesheetExtendedDtoInstance(this.mockVwTimesheetSingle.get(0));
        this.currentStatus = TimesheetObjectFactory.createTimesheetHistoryDtoInstance(this.mockCurrentProjTimesheetHist.get(0));
        this.hours = this.buildTimesheetHoursDtoMap();
    }
    
    private Map<ProjectTaskDto, List<EventDto>> buildTimesheetHoursDtoMap() {
        Map<ProjectTaskDto, List<EventDto>> hours = new HashMap<>(); 
        for (VwTimesheetProjectTask pt : this.mockVwTimesheetProjectTaskFetchMultiple) {
            ProjectTaskDto ptDto = ProjectObjectFactory.createProjectTaskExtendedDtoInstance(pt);
            List<EventDto> eventsDto = this.buildTimesheetEventDtoList(pt.getProjectTaskId());
            hours.put(ptDto, eventsDto);
        }
        return hours;
    }
    
    private List<EventDto> buildTimesheetEventDtoList(int projectTaskId) {
        List<ProjEvent> projEvents = createMockMultiple_Day_Task_Events(projectTaskId);
        List<EventDto> eventsDto = new ArrayList<>();
        for (ProjEvent evt : projEvents) {
            EventDto evtDto = ProjectObjectFactory.createEventDtoInstance(evt);
            eventsDto.add(evtDto);
        }
        return eventsDto;
    }
    

    @Test
    public void testSuccess_CreateApprovalDeclineEmailMessage() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        EmailMessageBean results = null;
        try {
            results = api.createSubmitMessage(timesheet, employee, manager, client, hours);
        }
        catch (TimesheetTransmissionException e) {
            e.printStackTrace();
            Assert.fail("Test case failed due to unexpected error");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("first_name_1.last_name_1@gte.net", results.getFromAddress().getAddress());
        Assert.assertEquals("mgr_first_name_1.mgr_last_name_1@gte.net", results.getRecipients());
        String subject = "Time Sheet Submission for " + " " + "John" + " " + "Smith" + " for period ending  " + "01-07-2018";
        Assert.assertEquals(subject, results.getSubject());
        try {
            Object content = results.getBody().getContent();
            Assert.assertTrue(content.toString().contains("<h3><strong>Timesheet Submital for Manager Approval</strong></h3>"));
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to error obtaining email body content");
        }
    }

    @Test
    public void testError_CreateApprovalDecline_InputStream_Failure() {
        PowerMockito.mockStatic(RMT2Date.class);
        try {
            when(RMT2Date.formatDate(isA(Date.class), isA(String.class))).thenThrow(SystemException.class);
        }
        catch (Exception e) {
            Assert.fail("Failed to setup stub for RMT2Date.formatDate method");
        }
        
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            api.createSubmitMessage(timesheet, employee, manager, client, hours);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof TimesheetTransmissionException);
            Assert.assertEquals("Unable to convert timesheet end period date string to Date object", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_CreateApprovalDecline_Null_Timesheet_Object() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            api.createSubmitMessage(null, employee, manager, client, hours);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof TimesheetTransmissionValidationException);
            Assert.assertEquals("Timesheet data object is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_CreateApprovalDecline_Null_Timesheet_EndPeriod() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            this.timesheet.setEndPeriod(null);
            api.createSubmitMessage(timesheet, employee, manager, client, hours);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof TimesheetTransmissionValidationException);
            Assert.assertEquals("Timesheet end period is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_CreateApprovalDecline_Null_Hours_Object() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            api.createSubmitMessage(timesheet, employee, manager, client, null);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof TimesheetTransmissionValidationException);
            Assert.assertEquals("Timesheet project-task hours data object is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_CreateApprovalDecline_Empty_Hours() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            hours = new HashMap<>();
            api.createSubmitMessage(timesheet, employee, manager, client, hours);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof TimesheetTransmissionValidationException);
            Assert.assertEquals("There are no project-task hours to process for timesheet, 0000000111", e.getMessage());
        }
    }

    @Test
    public void testSuccess_CreateConfirmationEmailMessage() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        EmailMessageBean results = null;
        try {
            results = api.createConfirmationMessage(timesheetExt, employee, currentStatus);
        }
        catch (TimesheetTransmissionException e) {
            e.printStackTrace();
            Assert.fail("Test case failed due to unexpected error");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("rmt2bsc@gmail.com", results.getFromAddress().getAddress());
        Assert.assertEquals("first_name_1.last_name_1@gte.net", results.getRecipients());
        String subject = "Time Sheet Approved (Period ending 01-07-2018)";
        Assert.assertEquals(subject, results.getSubject());
        try {
            Object content = results.getBody().getContent();
            Assert.assertTrue(content.toString().contains("Your time sheet, 0000000111, for the period ending on 01-07-2018<br>has been Approved at"));
            Assert.assertTrue(content.toString().contains("for ClientName1110 - 40.0 hours"));
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to error obtaining email body content");
        }
    }
    
    @Test
    public void testError_CreateConfirmation_InputStream_Failure() {
        PowerMockito.mockStatic(RMT2Date.class);
        try {
            when(RMT2Date.formatDate(isA(Date.class), isA(String.class))).thenThrow(SystemException.class);
        }
        catch (Exception e) {
            Assert.fail("Failed to setup stub for RMT2Date.formatDate method");
        }
        
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            api.createConfirmationMessage(timesheetExt, employee, currentStatus);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof TimesheetTransmissionException);
            Assert.assertEquals("Unable to convert timesheet end period date to String", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_CreateConfirmation_Null_Timesheet_Object() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            api.createConfirmationMessage(null, employee, currentStatus);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof TimesheetTransmissionValidationException);
            Assert.assertEquals("Timesheet data object is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_CreateConfirmation_Null_Timesheet_EndPeriod() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            this.timesheetExt.setEndPeriod(null);
            api.createConfirmationMessage(timesheetExt, employee, currentStatus);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof TimesheetTransmissionValidationException);
            Assert.assertEquals("Timesheet end period is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_CreateConfirmation_Null_Employee_Object() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            api.createConfirmationMessage(timesheetExt, null, currentStatus);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof TimesheetTransmissionValidationException);
            Assert.assertEquals("Employee data object is required", e.getMessage());
        }
    }
    
    @Test
    public void testValidation_CreateConfirmation_Null_CurrentStatus_Object() {
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            api.createConfirmationMessage(timesheetExt, employee, null);
            Assert.fail("Test failed due to unexcpected exception thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e instanceof TimesheetTransmissionValidationException);
            Assert.assertEquals("Timesheet current status object is required", e.getMessage());
        }
    }
    
    
    @Test
    public void testSuccess_Send() {
        SmtpApi mockSmtpApi = Mockito.mock(SmtpApi.class);
        PowerMockito.mockStatic(SmtpFactory.class);
        try {
            when(SmtpFactory.getSmtpInstance()).thenReturn(mockSmtpApi);
        }
        catch (Exception e) {
            Assert.fail("Failed to stub SmtpFactory.getSmtpInstance method");
        }
        try {
            when(mockSmtpApi.sendMessage(isA(EmailMessageBean.class))).thenReturn(1);  
            doNothing().when(mockSmtpApi).close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("The mocking of TimesheetTransmissionApi's send method failed");
        }
        
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        Object results = null;
        try {
            EmailMessageBean mockEmailMessageBean = Mockito.mock(EmailMessageBean.class);
            results = api.send(mockEmailMessageBean);
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case failed due to unexpected error");
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results instanceof Integer);
        Assert.assertEquals(1, results);
    }

    @Test
    public void testError_Send_General_Error() {
        SmtpApi mockSmtpApi = Mockito.mock(SmtpApi.class);
        PowerMockito.mockStatic(SmtpFactory.class);
        try {
            when(SmtpFactory.getSmtpInstance()).thenReturn(mockSmtpApi);
        }
        catch (Exception e) {
            Assert.fail("Failed to stub SmtpFactory.getSmtpInstance method");
        }
        try {
            when(mockSmtpApi.sendMessage(isA(EmailMessageBean.class))).thenThrow(MessageException.class); 
            doNothing().when(mockSmtpApi).close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("The mocking of TimesheetTransmissionApi's send method failed");
        }
        
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            EmailMessageBean emailBean = api.createSubmitMessage(timesheet, employee, manager, client, hours);;
            api.send(emailBean);
            Assert.fail("Expected an exception to be thrownr");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof TimesheetTransmissionException);
            Assert.assertEquals("Error occurred sending email message to mgr_first_name_1.mgr_last_name_1@gte.net",
                    e.getMessage());
        }
    }
    
}