package org.modules.timesheet;

import java.util.List;
import java.util.Map;

import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetDto;

import com.api.messaging.email.EmailMessageBean;

/**
 * Interface for transmitting a timesheet from one party to another as
 * confirmation that the timesheet was submitted for approval.
 * 
 * @author Roy Terrell
 * 
 */
public interface TimesheetTransmissionApi {

    final String SUBJECT_PREFIX = "Timesheet Submission for ";

    /**
     * Sends a copy of a given timesheet to a designated recipient.
     * 
     * @param message
     *            an instance of {@link EmailMessageBean} containing the data
     *            that is to be emailed.
     * @return Object
     * @throws TimesheetApiException
     *             <i>timesheet</i>, <i>employee</i>, <i>client</i>, or
     *             <i>hours</i> are null or invalid. <i>hours</i> does not
     *             contain any entries.
     * @throws TimesheetTransmissionException
     *             Error occurs sending timesheet data to its designated
     *             recipient.
     */
    Object send(EmailMessageBean message) throws TimesheetApiException, TimesheetTransmissionException;

    /**
     * Constructs the timesheet confirmation message.
     * 
     * @param timesheet
     *            An instance of {@link TimesheetDto}
     * @param employee
     *            An instance of {@link EmployeeDto}
     * @param manager
     *            An instance of {@link EmployeeDto} representing the employee's
     *            manager.
     * @param client
     *            An instance of {@link ClientDto}
     * @param hours
     *            A Map containing the hours for each project/task. The key is
     *            represented as {@link ProjectTaskDto} and the values is
     *            represented as a List of {@link EventDto} objects.
     * @return an instance of {@link EmailMessageBean} or null if total billable
     *         hours equal zero.
     * @throws TimesheetTransmissionException
     *             Error occurs sending timesheet data to its designated
     *             recipient.
     */
    EmailMessageBean createConfirmationMessage(TimesheetDto timesheet, EmployeeDto employee, EmployeeDto manager, 
            ClientDto client, Map<ProjectTaskDto, List<EventDto>> hours) throws TimesheetTransmissionException;

}
