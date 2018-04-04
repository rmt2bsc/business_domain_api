package org.rmt2.api.timesheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetDto;
import org.dto.adapter.orm.EmployeeObjectFactory;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.dto.adapter.orm.TimesheetObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.timesheet.TimesheetApiFactory;
import org.modules.timesheet.TimesheetTransmissionApi;
import org.modules.timesheet.TimesheetTransmissionException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.messaging.email.smtp.SmtpFactory;

/**
 * Test the timesheet transmission functionality of the Timesheet module of the
 * Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ SmtpFactory.class })
public class TimesheetTransmissionApiTest extends TimesheetMockData {

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

    
    private Map<ProjectTaskDto, List<EventDto>> buildTimesheetHoursDtoMap() {
        Map<ProjectTaskDto, List<EventDto>> hours = new HashMap<>(); 
        for (ProjProjectTask pt : this.mockProjProjectTaskMultiple) {
            ProjectTaskDto ptDto = ProjectObjectFactory.createProjectTaskDtoInstance(pt);
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
    public void testSuccess_CreateConfirmation() {
        // TODO: Please look at the success scenario of "submit" timesheet in
        // TimesheetUpdateApiTest.java for mocking examples.

        ClientDto client = ProjectObjectFactory.createClientDtoInstance(this.mockClientFetchMultiple.get(0));
        EmployeeDto employee = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockEmployeeFetchSingle.get(0));
        EmployeeDto manager = EmployeeObjectFactory.createEmployeeDtoInstance(this.mockManagerFetchSingle.get(0));
        TimesheetDto timesheet = TimesheetObjectFactory.createTimesheetDtoInstance(this.mockProjTimesheetSingle.get(0));
        Map<ProjectTaskDto, List<EventDto>> hours = this.buildTimesheetHoursDtoMap();
        
        TimesheetTransmissionApi api = TimesheetApiFactory.createTransmissionApi();
        try {
            api.createConfirmationMessage(timesheet, employee, manager, client, hours);
        }
        catch (TimesheetTransmissionException e) {
            
        }
    }

    @Test
    public void testSuccess_Send() {
        Assert.fail("Implement test case");
    }

}