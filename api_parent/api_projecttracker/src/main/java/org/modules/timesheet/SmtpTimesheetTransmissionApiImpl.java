package org.modules.timesheet;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.EventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetDto;

import com.RMT2Base;
import com.SystemException;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.util.RMT2Date;
import com.util.RMT2File;
import com.util.RMT2String;

/**
 * This is an implementation of the TimesheetTransmissionApi interface using the
 * SMTP protocol approach to send timesheets to some designated recipient.
 * 
 * @author Roy Terrell
 * 
 */
class SmtpTimesheetTransmissionApiImpl extends RMT2Base implements TimesheetTransmissionApi {

    private static final Logger logger = Logger.getLogger(SmtpTimesheetTransmissionApiImpl.class);

    private double totalHours;

    /**
     * 
     */
    protected SmtpTimesheetTransmissionApiImpl() {
        super();
    }

    /**
     * Emails a copy of an employee's timesheet to the employee's manager using
     * <i>timesheet</i>, <i>employee</i>, <i>client</i>, and <i>hours</i>.
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
     * @return the message that was sent.
     * @throws TimesheetApiException
     *             Validation errors
     * @throws TimesheetTransmissionException
     *             Error occurs sending timesheet data to its designated
     *             recipient via the SMTP protocol.
     */
    @Override
    public Object send(EmailMessageBean email) throws TimesheetApiException, TimesheetTransmissionException {

        if (email == null) {
            return null;
        }
        SmtpApi emailApi = null;
        try {
            // Send the timesheet over the SMTP protocol.
            emailApi = SmtpFactory.getSmtpInstance();
            Object rc = emailApi.sendMessage(email);
            emailApi.close();
            return rc;
        } catch (Exception e) {
            this.msg = "Error occurred sending email message to " + email.getRecipients();
            throw new TimesheetTransmissionException(this.msg, e);
        }
    }

    /**
     * Builds HTML containing content that represents the details of a given
     * timesheet.
     * <p>
     * Uses <i>timesheet</i>, <i>employee</i>, <i>client</i>, and <i>hours</i>
     * to devise the timesheet header, weekly hours for each project-task, and
     * the totals. Uses a timesheet header template file to build the HTML.
     * 
     * @param timesheet
     *            An instance of {@link TimesheetDto}
     * @param employee
     *            An instance of {@link EmployeeDto}
     * @param client
     *            An instance of {@link ClientDto}
     * @param hours
     *            A Map containing the hours for each project/task. The key is
     *            represented as {@link ProjectTaskDto} and the values is
     *            represented as a List of {@link EventDto} objects.
     * @return an instance of {@link EmailMessageBean}. Returns null when the
     *         total number billable hours equals zero.
     * @throws TimesheetTransmissionException
     *             User's session bean is unobtainable. Problem obtaining
     *             timesheet's project-task entries.
     */
    @Override
    public EmailMessageBean createConfirmationMessage(TimesheetDto timesheet, EmployeeDto employee, 
            EmployeeDto manager, ClientDto client, Map<ProjectTaskDto, List<EventDto>> hours)
            throws TimesheetTransmissionException {

        try {
            this.validate(timesheet, employee, manager, client, hours);
        } catch (TimesheetTransmissionValidationException e) {
            this.msg = "Timesheet SMTP transmission validation error occurred";
            throw new TimesheetTransmissionException(this.msg, e);
        } catch (ZeroTimesheetHoursException e) {
            logger.warn(e.getMessage());
            return null;
        }

        // Begin to build email content
        String periodEnd = null;
        try {
            periodEnd = RMT2Date.formatDate(timesheet.getEndPeriod(), "MM/dd/yyyy");
        } catch (SystemException e) {
            periodEnd = "N/A";
        }

        EmailMessageBean email = new EmailMessageBean();
        StringBuffer buf = new StringBuffer();
        // Setup basic email components
        email.setToAddress(manager.getEmployeeEmail());
        email.setFromAddress(employee.getEmployeeEmail());
        buf.append(TimesheetTransmissionApi.SUBJECT_PREFIX);
        buf.append(" ");
        buf.append(employee.getEmployeeFirstname());
        buf.append(" ");
        buf.append(employee.getEmployeeLastname());
        buf.append(" for period ending  ");
        buf.append(periodEnd);
        email.setSubject(buf.toString());

        // Build email body
        String root = RMT2File.getAppParmProperty("webapp_root");
        String uri = RMT2File.getAppParmProperty("email_submit_uri");
        String uriParms = RMT2File.getAppParmProperty("email_submit_uri_parms");
        String submitUri = root + uri + RMT2String.replace(uriParms, String.valueOf(timesheet.getTimesheetId()),
                        "$timesheet_id02$");

        // Get HTML Content
        String htmlContent = null;
        String formattedDate = null;
        String emailTemplateFile = RMT2File.getAppParmProperty("email_template");
        try {
            InputStream is = RMT2File.getFileInputStream(emailTemplateFile);
            htmlContent = RMT2File.getStreamStringData(is);
            formattedDate = RMT2Date.formatDate(timesheet.getEndPeriod(), "MM/dd/yyyy");
        } catch (SystemException e) {
            throw new TimesheetTransmissionException("Unalbe to get HTML content pertaining to timesheet: "
                            + timesheet.getDisplayValue(), e);
        }

        String deltaContent = null;
        deltaContent = RMT2String.replaceAll(htmlContent, root, "$root$");
        deltaContent = RMT2String.replace(deltaContent, employee.getEmployeeFirstname() + " "
                        + employee.getEmployeeLastname(), "$employeename$");
        deltaContent = RMT2String.replace(deltaContent, employee.getEmployeeTitle(), "$employeetitle$");
        deltaContent = RMT2String.replace(deltaContent, client.getClientName(), "$clientname$");
        deltaContent = RMT2String.replace(deltaContent, timesheet.getDisplayValue(), "$timesheetid$");
        deltaContent = RMT2String.replace(deltaContent, formattedDate, "$periodending$");

        // Get project/task hours
        String details = this.setupTimesheetEmailHours(hours);

        deltaContent = RMT2String.replace(deltaContent, details, "$timesheetdetails$");
        deltaContent = RMT2String.replace(deltaContent, String.valueOf(this.totalHours), "$totalhours$");
        deltaContent = RMT2String.replace(deltaContent, String.valueOf(timesheet.getTimesheetId()), "$timesheetid$");

        deltaContent = RMT2String.replace(deltaContent, submitUri + "approve", "$approveURI$");
        deltaContent = RMT2String.replace(deltaContent, submitUri + "decline", "$declineURI$");

        email.setBody(deltaContent, EmailMessageBean.HTML_CONTENT);
        return email;
    }

    /**
     * Builds HTML that is to present the hours of one or more project-task
     * instances.
     * <p>
     * Uses a timesheet details template file to build the HTML into rows an
     * columns for each project/task and its hours. Each project/task will
     * report each day's hours for a total of seven days.
     * 
     * @param hours
     *            A Map of project/task hours which the key exist as an instance
     *            of {@link ProjectTaskDto} and the value exist as a List of
     *            {@link EventDto}, respectively.
     * @return HTML table rows and columns hours for each project-task.
     * @throws TimesheetTransmissionException
     *             Unable to get the contents of the HTML template file used to
     *             build project/task details.
     */
    private String setupTimesheetEmailHours(Map<ProjectTaskDto, List<EventDto>> hours) throws TimesheetTransmissionException {
        String origHtmlContents = null;
        String htmlContents = null;
        String deltaContents = "";
        ProjectTaskDto vtpt = null;
        EventDto pe = null;

        String emailTemplateFile = RMT2File.getAppParmProperty("email_details_template");
        try {
            InputStream is = RMT2File.getFileInputStream(emailTemplateFile);
            origHtmlContents = RMT2File.getStreamStringData(is);
        } catch (SystemException e) {
            throw new TimesheetTransmissionException(e);
        }

        Iterator<ProjectTaskDto> keys = hours.keySet().iterator();
        while (keys.hasNext()) {
            htmlContents = origHtmlContents;
            vtpt = keys.next();
            htmlContents = RMT2String.replace(htmlContents, vtpt.getProjectDescription(), "$projectname$");
            htmlContents = RMT2String.replace(htmlContents, vtpt.getTaskDescription(), "$taskname$");

            List<EventDto> list = hours.get(vtpt);
            for (int ndx = 0; ndx < list.size(); ndx++) {
                pe = list.get(ndx);
                totalHours += pe.getEventHours();
                htmlContents = RMT2String.replace(htmlContents, String.valueOf(pe.getEventHours()), "$" + ndx + "hrs$");
            }
            deltaContents += htmlContents;
        }
        return deltaContents;

    }

    /**
     * Verifies that <i>timesheet</i>, <i>employee</i>, <i>client</i>, and
     * <i>hours</i> are valid instances.
     * 
     * @param timesheet
     *            An instance of {@link TimesheetDto}
     * @param employee
     *            An instance of {@link EmployeeDto}
     * @param manager
     *            An instance of {@link EmployeeDto}
     * @param client
     *            An instance of {@link ClientDto}
     * @param hours
     *            A Map containing the hours for each project/task. The key is
     *            represented as {@link ProjectTaskDto} and the values is
     *            represented as a List of {@link EventDto} objects.
     * @throws TimesheetTransmissionValidationException
     *             When either <i>timesheet</i>, <i>employee</i>, <i>client</i>,
     *             or <i>hours</i> are invalid. If <i>hours</i> does not contain
     *             any entries.
     */
    private void validate(TimesheetDto timesheet, EmployeeDto employee,  EmployeeDto manager, ClientDto client,
            Map<ProjectTaskDto, List<EventDto>> hours)
            throws TimesheetTransmissionValidationException, ZeroTimesheetHoursException {
        // Validate timesheet
        if (timesheet == null) {
            this.msg = "Timesheet instance is invalid when attempting to send timesheet to manager over SMTP protocol";
            throw new TimesheetTransmissionValidationException(this.msg);
        }

        // Validate employee
        if (employee == null) {
            this.msg = "Employee instance is invalid when attempting to send timesheet to manager over SMTP protocol";
            throw new TimesheetTransmissionValidationException(this.msg);
        }

        // validate employee's client
        if (client == null) {
            this.msg = "Client instance is invalid when attempting to send timesheet to manager over SMTP protocol";
            throw new TimesheetTransmissionValidationException(this.msg);
        }

        if (hours == null) {
            this.msg = "Timesheet hours instance is invalid when attempting to send timesheet to manager over SMTP protocol";
            throw new TimesheetTransmissionValidationException(this.msg);
        }

        if (manager == null) {
            this.msg = "Employee, "
                    + employee.getEmployeeId()
                    + ", does have a manager to send timesheet over SMTP protocol";
            logger.warn(this.msg);
        }

        if (hours.isEmpty()) {
            this.msg = "There are no project-task hours to process for timesheet, "
                    + timesheet.getTimesheetId()
                    + ", when attempting to send timesheet to manager over SMTP protocol";
            throw new ZeroTimesheetHoursException(this.msg);
        }
    }

}
