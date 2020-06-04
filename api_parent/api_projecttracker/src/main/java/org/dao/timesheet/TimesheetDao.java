package org.dao.timesheet;

import java.util.List;

import org.dto.EventDto;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetClientDto;
import org.dto.TimesheetDto;
import org.dto.TimesheetHistDto;
import org.dto.TimesheetHoursDto;
import org.dto.TimesheetHoursSummaryDto;
import org.dto.TimesheetStatusDto;

import com.api.persistence.DaoClient;

/**
 * Contract for creating, accessing and managing timesheet data. This includes
 * data for entities such as timesheets, timesshet statuses, timesheet status
 * history, and events.
 * 
 * @author Roy Tererll
 * 
 */
public interface TimesheetDao extends DaoClient {

    /**
     * Fetches one or more timesheets using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheets, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link TimesheetDto} to filter results or null
     *            to fetch all timesheets.
     * @return A List of {@link TimesheetDto} or null if no data is found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<TimesheetDto> fetch(TimesheetDto criteria)
            throws TimesheetDaoException;

    /**
     * Fetches one or more timesheets containing employee, status, history, and
     * client information using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheets, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link TimesheetDto} to filter results or null
     *            to fetch all timesheets.
     * @return A List of {@link TimesheetDto} or null if no data is found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<TimesheetDto> fetchExt(TimesheetDto criteria)
            throws TimesheetDaoException;

    /**
     * Fetches one or more timesheet statuses using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheet statuses, pass <i>criteria</i>
     * as a null value.
     * 
     * @param criteria
     *            an instance of {@link TimesheetStatusDto} to filter results or
     *            null to fetch all timesheet statuses.
     * @return A List of {@link TimesheetStatusDto} or null if no data is found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<TimesheetStatusDto> fetchStatus(TimesheetStatusDto criteria)
            throws TimesheetDaoException;

    /**
     * Fetches one or more timesheet status history using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheet status history, pass
     * <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link TimesheetHistDto} to filter results or
     *            null to fetch all timesheet status history.
     * @return A List of {@link TimesheetHistDto} or null if no data is found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<TimesheetHistDto> fetchStatusHistory(TimesheetHistDto criteria)
            throws TimesheetDaoException;

    /**
     * Fetches one or more timesheet client using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheet client, pass <i>criteria</i> as
     * a null value.
     * 
     * @param criteria
     *            an instance of {@link TimesheetClientDto} to filter results or
     *            null to fetch all timesheet client.
     * @return A List of {@link TimesheetClientDto} or null if no data is found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<TimesheetClientDto> fetchClient(TimesheetClientDto criteria)
            throws TimesheetDaoException;

    /**
     * Fetches one or more timesheet hours using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheet hours, pass <i>criteria</i> as
     * a null value.
     * 
     * @param criteria
     *            an instance of {@link TimesheetHoursDto} to filter results or
     *            null to fetch all timesheet hours.
     * @return A List of {@link TimesheetHoursDto} or null if no data is found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<TimesheetHoursDto> fetchHours(TimesheetHoursDto criteria)
            throws TimesheetDaoException;

    /**
     * Fetches one or more timesheet hour summary using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheet hour summary, pass
     * <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link TimesheetHoursSummaryDto} to filter
     *            results or null to fetch all timesheet hour summary.
     * @return A List of {@link TimesheetHoursSummaryDto} or null if no data is
     *         found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<TimesheetHoursSummaryDto> fetchHourSummary(
            TimesheetHoursSummaryDto criteria) throws TimesheetDaoException;

    /**
     * Fetches one or more project/timesheet event using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all project/timesheet event, pass
     * <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link ProjectEventDto} to filter results or
     *            null to fetch all project/timesheet event.
     * @return A List of {@link ProjectEventDto} or null if no data is found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<ProjectEventDto> fetchEvent(ProjectEventDto criteria)
            throws TimesheetDaoException;

    /**
     * Fetches one or more project/tasks using selection criteria as related to
     * timesheets.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all project/tasks, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link ProjectTaskDto} to filter results or
     *            null to fetch all project/tasks.
     * @return A List of {@link ProjectTaskDto} or null if no data is found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<ProjectTaskDto> fetchProjectTask(ProjectTaskDto criteria)
            throws TimesheetDaoException;

    /**
     * Fetches one or more project/tasks, which includes extended data, using
     * selection criteria as related to timesheets.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all project/tasks, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link ProjectTaskDto} to filter results or
     *            null to fetch all project/tasks.
     * @return A List of {@link ProjectTaskDto} or null if no data is found.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    List<ProjectTaskDto> fetchProjectTaskExt(ProjectTaskDto criteria)
            throws TimesheetDaoException;

    /**
     * Creates a new or updates an existing timesheet.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable.
     * 
     * @param obj
     *            an instance of {@link TimesheetDto} representing the timesheet
     *            to be updated.
     * @return total number of timesheet updated or the id of the new timesheet
     *         created.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    int maintainTimesheet(TimesheetDto obj) throws TimesheetDaoException;

    /**
     * Creates a new project/task for a timesheet.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. Modifications to an existing Project/Task record is not
     * allowed.
     * 
     * @param obj
     *            an instance of {@link ProjectTaskDto} representing the
     *            project/task to be updated.
     * @return The id of the project/task created
     * @throws TimesheetDaoException
     */
    int maintainProjectTask(ProjectTaskDto obj) throws TimesheetDaoException;

    /**
     * Creates a new or updates an existing event.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable.
     * 
     * @param obj
     *            an instance of {@link EventDto} representing the event to be
     *            updated.
     * @return total number of events updated or the id of the new event
     *         created.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    int maintainEvent(EventDto obj) throws TimesheetDaoException;

    /**
     * Creates a new or modifies an existing status history entry for a
     * particular timesheet
     * 
     * @param obj
     *            an instance of {@link TimesheetHistDto}
     * @return the id of the new status history record or the total number of
     *         items effected.
     * @throws TimesheetDaoException
     */
    int maintainStatusHistory(TimesheetHistDto obj)
            throws TimesheetDaoException;

    /**
     * Deletes one or more timesheets using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheets, pass <i>criteria</i> as a
     * null value.
     * 
     * @param criteria
     *            an instance of {@link TimesheetDto} used as a filter to target
     *            a particular set of timesheets to delete.
     * @return total number of timesheets deleted.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    int deleteTimesheet(TimesheetDto criteria) throws TimesheetDaoException;

    /**
     * Deletes one or more timesheet statuses using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheet statuses, pass <i>criteria</i>
     * as a null value.
     * 
     * @param criteria
     *            an instance of {@link TimesheetHistDto} used as a filter to
     *            target a particular set of timesheet statuses to delete.
     * @return total number of timesheet statuses deleted.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    int deleteTimesheetStatus(TimesheetHistDto criteria) throws TimesheetDaoException;

    /**
     * Deletes one or more timesheet roject/tasks using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all timesheet project/tasks, pass
     * <i>criteria</i> as a null value.
     * 
     * @param criteria
     *            an instance of {@link ProjectTaskDto} used as a filter to
     *            target a particular set of timesheet project/tasks to delete.
     * @return total number of timesheet project/tasks deleted.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    int deleteProjectTask(ProjectTaskDto criteria) throws TimesheetDaoException;

    /**
     * Deletes one or more events using selection criteria.
     * <p>
     * This method will use property values as parameters for selection criteria
     * where applicable. To select all events, pass <i>criteria</i> as a null
     * value.
     * 
     * @param criteria
     *            an instance of {@link EventDto} used as a filter to target a
     *            particular set of events to delete.
     * @return total number of events deleted.
     * @throws TimesheetDaoException
     *             general data access errors
     */
    int deleteEvent(EventDto criteria) throws TimesheetDaoException;
}
