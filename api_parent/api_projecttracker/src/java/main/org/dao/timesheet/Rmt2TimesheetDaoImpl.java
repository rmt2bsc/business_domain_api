package org.dao.timesheet;

import java.util.ArrayList;
import java.util.List;

import org.dao.AbstractProjecttrackerDaoImpl;
import org.dao.admin.ProjectAdminDaoFactory;
import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.mapping.orm.rmt2.ProjTimesheetStatus;
import org.dao.mapping.orm.rmt2.VwClientTimesheetSummary;
import org.dao.mapping.orm.rmt2.VwProjectClient;
import org.dao.mapping.orm.rmt2.VwTimesheetEventList;
import org.dao.mapping.orm.rmt2.VwTimesheetHours;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
import org.dao.mapping.orm.rmt2.VwTimesheetSummary;
import org.dto.EventDto;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetClientDto;
import org.dto.TimesheetDto;
import org.dto.TimesheetHistDto;
import org.dto.TimesheetHoursDto;
import org.dto.TimesheetHoursSummaryDto;
import org.dto.TimesheetStatusDto;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.dto.adapter.orm.TimesheetObjectFactory;

import com.api.persistence.PersistenceClient;
import com.api.persistence.db.orm.OrmBean;
import com.util.RMT2Date;
import com.util.UserTimestamp;

/**
 * An implementation of {@link TimesheetDao}. It provides functionality that
 * inserts, updates, deletes, and queries timesheet related data.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2TimesheetDaoImpl extends AbstractProjecttrackerDaoImpl implements
        TimesheetDao {

    /**
     * Creates a Rmt2ProjectAdminDaoImpl object with its own persistent client.
     */
    protected Rmt2TimesheetDaoImpl() {
        super();
    }

    /**
     * @param appName
     */
    protected Rmt2TimesheetDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Creates a Rmt2TimesheetDaoImpl object with a shared persistent client.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     */
    protected Rmt2TimesheetDaoImpl(PersistenceClient client) {
        super(client);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.TimesheetDao#fetchTimesheet(org.dto.TimesheetDto)
     */
    @Override
    public List<TimesheetDto> fetch(TimesheetDto criteria)
            throws TimesheetDaoException {
        ProjTimesheet obj = TimesheetDaoFactory.createCriteria(criteria);
        List<ProjTimesheet> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
        List<TimesheetDto> list = new ArrayList<TimesheetDto>();
        for (ProjTimesheet item : results) {
            TimesheetDto dto = TimesheetObjectFactory
                    .createTimesheetDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.timesheet.TimesheetDao#fetchTimesheetExt(org.dto.TimesheetDto)
     */
    @Override
    public List<TimesheetDto> fetchExt(TimesheetDto criteria)
            throws TimesheetDaoException {
        VwTimesheetList obj = TimesheetDaoFactory.createCriteriaExt(criteria);
        obj.addOrderBy(VwTimesheetList.PROP_CLIENTNAME,
                VwTimesheetList.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetList.PROP_LASTNAME,
                VwTimesheetList.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetList.PROP_FIRSTNAME,
                VwTimesheetList.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetList.PROP_TIMESHEETID,
                VwTimesheetList.ORDERBY_ASCENDING);
        List<VwTimesheetList> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
        List<TimesheetDto> list = new ArrayList<TimesheetDto>();
        for (VwTimesheetList item : results) {
            TimesheetDto dto = TimesheetObjectFactory
                    .createTimesheetExtendedDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.timesheet.TimesheetDao#fetchProjectTask(org.dto.ProjectTaskDto)
     */
    @Override
    public List<ProjectTaskDto> fetchProjectTask(ProjectTaskDto criteria)
            throws TimesheetDaoException {
        ProjProjectTask obj = TimesheetDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ProjProjectTask.PROP_PROJID,
                ProjProjectTask.ORDERBY_ASCENDING);
        obj.addOrderBy(ProjProjectTask.PROP_TIMESHEETID,
                ProjProjectTask.ORDERBY_ASCENDING);
        obj.addOrderBy(ProjProjectTask.PROP_PROJECTTASKID,
                ProjProjectTask.ORDERBY_ASCENDING);
        List<ProjProjectTask> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
        List<ProjectTaskDto> list = new ArrayList<ProjectTaskDto>();
        for (ProjProjectTask item : results) {
            ProjectTaskDto dto = ProjectObjectFactory
                    .createProjectTaskDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.timesheet.TimesheetDao#fetchProjectTaskExt(org.dto.ProjectTaskDto
     * )
     */
    @Override
    public List<ProjectTaskDto> fetchProjectTaskExt(ProjectTaskDto criteria)
            throws TimesheetDaoException {
        VwTimesheetProjectTask obj = TimesheetDaoFactory
                .createCriteriaExt(criteria);
        obj.addOrderBy(VwTimesheetProjectTask.PROP_PROJECTNAME,
                VwTimesheetProjectTask.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetProjectTask.PROP_TASKNAME,
                VwTimesheetProjectTask.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetProjectTask.PROP_TIMESHEETID,
                VwTimesheetProjectTask.ORDERBY_ASCENDING);
        List<VwTimesheetProjectTask> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
        List<ProjectTaskDto> list = new ArrayList<ProjectTaskDto>();
        for (VwTimesheetProjectTask item : results) {
            ProjectTaskDto dto = ProjectObjectFactory
                    .createProjectTaskExtendedDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.TimesheetDao#fetchTimesheetStatus(org.dto.TimesheetStatusDto)
     */
    @Override
    public List<TimesheetStatusDto> fetchStatus(TimesheetStatusDto criteria)
            throws TimesheetDaoException {
        ProjTimesheetStatus obj = TimesheetDaoFactory.createCriteria(criteria);
        List<ProjTimesheetStatus> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
        List<TimesheetStatusDto> list = new ArrayList<TimesheetStatusDto>();
        for (ProjTimesheetStatus item : results) {
            TimesheetStatusDto dto = TimesheetObjectFactory
                    .createTimesheetStatusDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.TimesheetDao#fetchTimesheetStatusHistory(org.dto.TimesheetHistDto
     * )
     */
    @Override
    public List<TimesheetHistDto> fetchStatusHistory(TimesheetHistDto criteria)
            throws TimesheetDaoException {
        ProjTimesheetHist obj = TimesheetDaoFactory.createCriteria(criteria);
        List<ProjTimesheetHist> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
        List<TimesheetHistDto> list = new ArrayList<TimesheetHistDto>();
        for (ProjTimesheetHist item : results) {
            TimesheetHistDto dto = TimesheetObjectFactory
                    .createTimesheetHistoryDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.TimesheetDao#fetchTimesheetClient(org.dto.TimesheetClientDto)
     */
    @Override
    public List<TimesheetClientDto> fetchClient(TimesheetClientDto criteria)
            throws TimesheetDaoException {
        VwClientTimesheetSummary obj = TimesheetDaoFactory
                .createCriteria(criteria);
        List<VwClientTimesheetSummary> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
        List<TimesheetClientDto> list = new ArrayList<TimesheetClientDto>();
        for (VwClientTimesheetSummary item : results) {
            TimesheetClientDto dto = TimesheetObjectFactory
                    .createTimesheetClientDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.TimesheetDao#fetchTimesheetHours(org.dto.TimesheetHoursDto)
     */
    @Override
    public List<TimesheetHoursDto> fetchHours(TimesheetHoursDto criteria)
            throws TimesheetDaoException {
        VwTimesheetHours obj = TimesheetDaoFactory.createCriteria(criteria);
        List<VwTimesheetHours> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
        List<TimesheetHoursDto> list = new ArrayList<TimesheetHoursDto>();
        for (VwTimesheetHours item : results) {
            TimesheetHoursDto dto = TimesheetObjectFactory
                    .createTimesheetHoursDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.TimesheetDao#fetchTimesheetHourSummary(org.dto.
     * TimesheetHoursSummaryDto)
     */
    @Override
    public List<TimesheetHoursSummaryDto> fetchHourSummary(
            TimesheetHoursSummaryDto criteria) throws TimesheetDaoException {
        VwTimesheetSummary obj = TimesheetDaoFactory.createCriteria(criteria);
        List<VwTimesheetSummary> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
        List<TimesheetHoursSummaryDto> list = new ArrayList<TimesheetHoursSummaryDto>();
        for (VwTimesheetSummary item : results) {
            TimesheetHoursSummaryDto dto = TimesheetObjectFactory
                    .createTimesheetSummaryDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.TimesheetDao#fetchProjectEvent(org.dto.ProjectEventDto)
     */
    @Override
    public List<ProjectEventDto> fetchEvent(ProjectEventDto criteria)
            throws TimesheetDaoException {
        VwTimesheetEventList obj = ProjectAdminDaoFactory
                .createCriteria(criteria);
        obj.addOrderBy(VwTimesheetEventList.PROP_PROJECTNAME,
                VwProjectClient.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetEventList.PROP_TASKNAME,
                VwProjectClient.ORDERBY_ASCENDING);
        obj.addOrderBy(VwTimesheetEventList.PROP_EFFECTIVEDATE,
                VwProjectClient.ORDERBY_DESCENDING);

        List<VwTimesheetEventList> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }

        List<ProjectEventDto> list = new ArrayList<ProjectEventDto>();
        for (VwTimesheetEventList item : results) {
            ProjectEventDto dto = ProjectObjectFactory
                    .createProjectEventDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.TimesheetDao#maintainTimesheet(org.dto.TimesheetDto)
     */
    @Override
    public int maintainTimesheet(TimesheetDto obj) throws TimesheetDaoException {
        int rc = 0;

        if (obj == null) {
            this.msg = "Timesheet object is invalid and could not be processed";
            throw new TimesheetDaoException(this.msg);
        }

        ProjTimesheet timesheet = TimesheetDaoFactory.createOrm(obj);
        if (timesheet.getTimesheetId() == 0) {
            rc = this.createTimesheet(timesheet);
            obj.setTimesheetId(rc);
        }
        else {
            rc = this.updateTimesheet(timesheet);
        }

        // Refresh DTO
        obj.setDateCreated(timesheet.getDateCreated());
        obj.setDateUpdated(timesheet.getDateCreated());
        obj.setUpdateUserId(timesheet.getUserId());
        obj.setIpCreated(timesheet.getIpCreated());
        obj.setIpUpdated(timesheet.getIpUpdated());
        obj.setTimesheetId(timesheet.getTimesheetId());
        return rc;
    }

    /**
     * Creates a timesheet and saves changes to the database.
     * 
     * @param ts
     *            The timesheet data.
     * @return New timesheet id.
     * @throws TimesheetDaoException
     *             if client id is not provided, or if employee id is not
     *             provided, or a database access error.
     */
    private int createTimesheet(ProjTimesheet ts) throws TimesheetDaoException {
        int rc = 0;
        UserTimestamp ut = null;
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            // Add base timesheet
            if (ts.getProjId() == 0) {
                ts.setNull(ProjTimesheet.PROP_PROJID);
            }
            ts.setDateCreated(ut.getDateCreated());
            ts.setDateUpdated(ut.getDateCreated());
            ts.setUserId(ut.getLoginId());
            ts.setIpCreated(ut.getIpAddr());
            ts.setIpUpdated(ut.getIpAddr());
            rc = this.client.insertRow(ts, true);
            ts.setTimesheetId(rc);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to create timesheet due to database errors";
            throw new TimesheetDaoException(this.msg, e);
        }
    }

    /**
     * Applies the modifications of an existing timesheet to the database.
     * 
     * @param ts
     *            The timesheet data.
     * @return the total number of rows effected
     * @throws TimesheetDaoException
     *             a database access error.
     */
    private int updateTimesheet(ProjTimesheet ts) throws TimesheetDaoException {
        int rc = 0;
        UserTimestamp ut = null;
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            // Add base timesheet
            if (ts.getProjId() == 0) {
                ts.setNull(ProjTimesheet.PROP_PROJID);
            }
            else {
                ts.removeNull(ProjTimesheet.PROP_PROJID);
            }
            ts.setDateUpdated(ut.getDateCreated());
            ts.setUserId(ut.getLoginId());
            ts.setIpUpdated(ut.getIpAddr());
            ts.addCriteria(ProjTimesheet.PROP_TIMESHEETID, ts.getTimesheetId());
            rc = this.client.updateRow(ts);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to modify timesheet due to database errors";
            throw new TimesheetDaoException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.timesheet.TimesheetDao#maintainStatusHistory(org.dto.TimesheetHistDto
     * )
     */
    @Override
    public int maintainStatusHistory(TimesheetHistDto obj)
            throws TimesheetDaoException {
        if (obj == null) {
            this.msg = "Timesheet status history cannot be null";
            throw new TimesheetDaoException(this.msg);
        }
        ProjTimesheetHist pth = TimesheetDaoFactory.createCriteria(obj);
        int rc = 0;
        if (pth.getTimesheetHistId() == 0) {
            rc = this.createStatusHistory(pth);
        }
        else {
            rc = this.updateStatusHistory(pth);
        }
        return rc;
    }

    /**
     * 
     * @param hist
     * @return
     * @throws TimesheetDaoException
     */
    private int createStatusHistory(ProjTimesheetHist hist)
            throws TimesheetDaoException {
        UserTimestamp ut = null;
        int rc = 0;
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            hist.setEffectiveDate(ut.getDateCreated());
            hist.setNull("endDate");
            hist.setUserId(ut.getLoginId());
            hist.setIpCreated(ut.getIpAddr());
            hist.setIpUpdated(ut.getIpAddr());
            rc = this.client.insertRow(hist, true);
            hist.setTimesheetHistId(rc);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to create timesheet status history in database";
            throw new TimesheetDaoException(this.msg, e);
        }
    }

    /**
     * 
     * @param hist
     * @return
     * @throws TimesheetDaoException
     */
    private int updateStatusHistory(ProjTimesheetHist hist)
            throws TimesheetDaoException {
        UserTimestamp ut = null;
        int rc = 0;
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            hist.setEndDate(ut.getDateCreated());
            hist.setUserId(ut.getLoginId());
            hist.setIpUpdated(ut.getIpAddr());
            hist.addCriteria(ProjTimesheetHist.PROP_TIMESHEETHISTID,
                    hist.getTimesheetHistId());
            rc = this.client.updateRow(hist);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to update existing timesheet status history record.  Primary Key: "
                    + hist.getTimesheetHistId();
            throw new TimesheetDaoException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.timesheet.TimesheetDao#maintainProjectTask(org.dto.ProjectTaskDto
     * )
     */
    @Override
    public int maintainProjectTask(ProjectTaskDto obj)
            throws TimesheetDaoException {
        int rc = 0;

        if (obj == null) {
            this.msg = "Project/Task object is invalid and could not be processed";
            throw new TimesheetDaoException(this.msg);
        }

        ProjProjectTask projectTask = TimesheetDaoFactory.createOrm(obj);
        if (projectTask.getProjectTaskId() == 0) {
            rc = this.createProjectTask(projectTask);
            projectTask.setProjectTaskId(rc);
            obj.setProjectTaskId(rc);
        }
        return rc;
    }

    /**
     * Creates a project/task record and saves changes to the database.
     * 
     * @param pt
     *            Project Task data
     * @return The id of the project task.
     * @throws TimesheetDaoException
     *             if a validation error occurs.
     */
    private int createProjectTask(ProjProjectTask pt)
            throws TimesheetDaoException {
        try {
            int rc = this.client.insertRow(pt, true);
            pt.setProjectTaskId(rc);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to create timesheet project/task due to database errors";
            throw new TimesheetDaoException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.timesheet.TimesheetDao#maintainEvent(org.dto.EventDto)
     */
    @Override
    public int maintainEvent(EventDto obj) throws TimesheetDaoException {
        if (obj == null) {
            this.msg = "Project event object is invalid and could not be processed";
            throw new TimesheetDaoException(this.msg);
        }
        ProjEvent evt = ProjectAdminDaoFactory.createOrm(obj);
        int rc = 0;
        if (evt.getEventId() == 0) {
            rc = this.createEvent(evt);
        }
        else {
            rc = this.updateEvent(evt);
        }
        return rc;
    }

    /**
     * Creates a project event and saves changes to the database.
     * 
     * @param evt
     *            The project event data.
     * @return New project event id.
     * @throws TimesheetDaoException
     *             a database access error.
     */
    private int createEvent(ProjEvent evt) throws TimesheetDaoException {
        int rc = 0;
        UserTimestamp ut = null;
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            if (evt.getHours() <= 0) {
                evt.setNull("hours");
            }
            evt.setDateCreated(ut.getDateCreated());
            evt.setDateUpdated(ut.getDateCreated());
            evt.setUserId(ut.getLoginId());
            rc = this.client.insertRow(evt, true);
            evt.setEventId(rc);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to create project event due to database errors";
            throw new TimesheetDaoException(this.msg, e);
        }
    }

    /**
     * Modifies a project event and saves changes to the database.
     * 
     * @param evt
     *            The project event data.
     * @return total number of rows effected
     * @throws TimesheetDaoException
     *             a database access error.
     */
    private int updateEvent(ProjEvent evt) throws TimesheetDaoException {
        int rc = 0;
        UserTimestamp ut = null;
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            if (evt.getHours() <= 0) {
                evt.setNull("hours");
            }
            evt.setDateUpdated(ut.getDateCreated());
            evt.setUserId(ut.getLoginId());
            evt.addCriteria(ProjEvent.PROP_EVENTID, evt.getEventId());
            rc = this.client.updateRow(evt);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to update project event due to database errors";
            throw new TimesheetDaoException(this.msg, e);
        }
    }

    /**
     * 
     * @param obj
     * @return
     */
    private int deleteObject(OrmBean obj) {
        int rows = 0;
        try {
            rows = this.client.deleteRow(obj);
            return rows;
        } catch (Exception e) {
            throw new TimesheetDaoException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.TimesheetDao#deleteTimesheet(org.dto.TimesheetDto)
     */
    @Override
    public int deleteTimesheet(TimesheetDto criteria)
            throws TimesheetDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.timesheet.TimesheetDao#deleteTimesheetStatus(org.dto.
     * TimesheetStatusDto)
     */
    @Override
    public int deleteTimesheetStatus(TimesheetStatusDto criteria)
            throws TimesheetDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.timesheet.TimesheetDao#deleteProjectTask(org.dto.ProjectTaskDto)
     */
    @Override
    public int deleteProjectTask(ProjectTaskDto criteria)
            throws TimesheetDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.timesheet.TimesheetDao#deleteEvent(org.dto.EventDto)
     */
    @Override
    public int deleteEvent(EventDto criteria) throws TimesheetDaoException {
        ProjEvent obj = ProjectAdminDaoFactory.createCriteria(criteria);
        return this.deleteObject(obj);
    }

}
