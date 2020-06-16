package org.rmt2.api.timesheet;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.mapping.orm.rmt2.VwTimesheetEventList;
import org.dao.mapping.orm.rmt2.VwTimesheetHours;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
import org.dao.mapping.orm.rmt2.VwTimesheetSummary;
import org.dao.timesheet.TimesheetConst;
import org.junit.After;
import org.junit.Before;
import org.rmt2.api.ProjectTrackerMockData;
import org.rmt2.api.ProjectTrackerMockDataFactory;

/**
 * Timesheet API testing facility that is mainly responsible for setting up mock
 * data.
 * <p>
 * All Timesheet API unit tests should inherit this class to prevent duplicating
 * common functionality.
 * 
 * @author rterrell
 * 
 */
public class TimesheetMockData extends ProjectTrackerMockData {
    protected List<ProjProjectTask> mockProjProjectTaskSingle;
    protected List<ProjProjectTask> mockProjProjectTaskMultiple;
    protected List<ProjTimesheet> mockProjTimesheetSingle;
    protected List<ProjTimesheet> mockProjTimesheetMultiple;
    protected List<VwTimesheetList> mockVwTimesheetSingle;
    protected List<VwTimesheetList> mockVwTimesheetMultiple;   
    protected List<ProjTimesheetHist> mockProjTimesheetHistMultiple;
    protected List<ProjTimesheetHist> mockCurrentProjTimesheetHist;
    protected List<VwTimesheetEventList> mockVwTimesheetEventListFetchMultiple;
    protected List<VwTimesheetEventList> mockVwTimesheetEventListFetchSingle;
    protected List<VwTimesheetProjectTask> mockVwTimesheetProjectTaskFetchMultiple;
    protected List<VwTimesheetProjectTask> mockVwTimesheetProjectTaskFetchSingle;
    protected List<VwTimesheetHours> mockTimesheetHours;
    protected List<VwTimesheetSummary> mockTimesheetSummary;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockProjProjectTaskSingle = this.createMockSingleProjProjectTask();
        this.mockProjProjectTaskMultiple = this.createMockMultipleProjProjectTask();
        this.mockVwTimesheetEventListFetchMultiple = this.createMockMultipleVwTimesheetEventList();
        this.mockVwTimesheetEventListFetchSingle = this.createMockSingleVwTimesheetEventList();
        this.mockVwTimesheetProjectTaskFetchMultiple = this.createMockMultipleVwTimesheetProjectTask();
        this.mockVwTimesheetProjectTaskFetchSingle = this.createMockSingleVwTimesheetProjectTask();
        this.mockProjTimesheetSingle = this.createMockSingleTimesheetList();
        this.mockProjTimesheetMultiple = this.createMockMultipleTimesheetSameClientList();
        this.mockVwTimesheetSingle = this.createMockSingleExtTimesheetList();
        this.mockVwTimesheetMultiple = this.createMockMultipleExtTimesheetList();
        this.mockProjTimesheetHistMultiple = this.createMockTimesheetStatusHistory();
        this.mockCurrentProjTimesheetHist = this.createMockTimesheetCurrentStatus();
        this.mockTimesheetHours = this.createMockTimesheetHours();
        this.mockTimesheetSummary = this.createMockTimesheetSummaryList();
        
        return;
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }
    
    private List<ProjTimesheetHist> createMockTimesheetCurrentStatus() {
        List<ProjTimesheetHist> list = new ArrayList<ProjTimesheetHist>();
        ProjTimesheetHist o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                TimesheetConst.STATUS_APPROVED, "2018-01-09", null);
        list.add(o);
        
        return list;
    }
    
    private List<ProjTimesheetHist> createMockTimesheetStatusHistory() {
        List<ProjTimesheetHist> list = new ArrayList<ProjTimesheetHist>();
        ProjTimesheetHist o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                TimesheetConst.STATUS_NEW, "2018-01-01", "2018-01-02");    
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                TimesheetConst.STATUS_DRAFT, "2018-01-03", "2018-01-04");
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                TimesheetConst.STATUS_SUBMITTED, "2018-01-05", "2018-01-06");
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                TimesheetConst.STATUS_RECVD, "2018-01-07", "2018-01-08");
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheetHist(
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID,
                ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID,
                TimesheetConst.STATUS_APPROVED, "2018-01-09", null);
        list.add(o);
        
        return list;
    }
    
    private List<ProjProjectTask> createMockSingleProjProjectTask() {
        List<ProjProjectTask> list = new ArrayList<ProjProjectTask>();
        ProjProjectTask o = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(444441, 1112220, 111, 4440);
        list.add(o);
        return list;
    }
    
    private List<ProjProjectTask> createMockMultipleProjProjectTask() {
        List<ProjProjectTask> list = new ArrayList<ProjProjectTask>();
        ProjProjectTask o = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(444441, 1112220, 111, 4440);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(444442, 1112221, 111, 4440);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(444443, 1112222, 111, 4440);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(444444, 1112223, 111, 4440);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjProjectTask(444445, 1112224, 111, 4440);
        list.add(o);
        
        return list;
    }
    
    
    
    
    private List<VwTimesheetProjectTask> createMockSingleVwTimesheetProjectTask() {
        List<VwTimesheetProjectTask> list = new ArrayList<VwTimesheetProjectTask>();
        VwTimesheetProjectTask o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444441, 111, 4440,
                        1112220, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Design and Analysis", true);
        list.add(o);
        return list;
    }
    
    private List<VwTimesheetProjectTask> createMockMultipleVwTimesheetProjectTask() {
        List<VwTimesheetProjectTask> list = new ArrayList<VwTimesheetProjectTask>();
        VwTimesheetProjectTask o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444441, 111, 4440,
                        1112220, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Design and Analysis", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444442, 111, 4440,
                        1112221, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Development", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444443, 111, 4440,
                        1112222, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Meetings", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444444, 111, 4440,
                        1112223, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Testing", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444445, 111, 4440,
                        1112224, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Holiday", false);
        list.add(o);
        
        return list;
    }
    
    private List<VwTimesheetEventList> createMockSingleVwTimesheetEventList() {
        List<VwTimesheetEventList> list = new ArrayList<VwTimesheetEventList>();
        VwTimesheetEventList o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123401, "2018-01-01", 8,
                        444441, 111, 4440, "Project 2220", 1112220,
                        "Design and Analysis", 1110, "2018-01-01", "2018-01-07", true);
        list.add(o);
        return list;
    }
    
    private List<VwTimesheetEventList> createMockMultipleVwTimesheetEventList() {
        List<VwTimesheetEventList> list = new ArrayList<VwTimesheetEventList>();
        VwTimesheetEventList o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123401, "2018-01-01", 8,
                        444441, 111, 4440, "Project 2220", 1112220,
                        "Design and Analysis", 1110, "2018-01-01", "2018-01-07",
                        true);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123402, "2018-01-02", 8, 444442, 111, 4440,
                "Project 2220", 1112221, "Development", 1110, "2018-01-01",
                "2018-01-07", true);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123403, "2018-01-03", 8, 444443, 111, 4440,
                "Project 2220", 1112222, "Meetings", 1110, "2018-01-01",
                "2018-01-07", true);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123404, "2018-01-04", 8, 444444, 111, 4440,
                "Project 2220", 1112223, "Testing", 1110, "2018-01-01",
                "2018-01-07", true);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123405, "2018-01-05", 8, 444445, 111, 4440,
                "Project 2220", 1112224, "Holiday", 1110, "2018-01-01",
                "2018-01-07", false);
        list.add(o);
        return list;
    }
    
    private List<ProjTimesheet> createMockSingleTimesheetList() {
        List<ProjTimesheet> list = new ArrayList<ProjTimesheet>();
        ProjTimesheet o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 4440, 2220, "INVREF1230",
                "2018-01-01", "2018-01-07", "ExtReNo1000");
        list.add(o);
        return list;
    }
    
    private List<ProjTimesheet> createMockMultipleTimesheetSameClientList() {
        List<ProjTimesheet> list = new ArrayList<ProjTimesheet>();
        ProjTimesheet o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 4440, 2220, "INVREF1230",
                "2018-01-01", "2018-01-07", "ExtReNo1000");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(112, 1110, 4440, 2220, "INVREF1231",
                "2018-01-08", "2018-01-14", "ExtReNo1001");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(113, 1110, 4440, 2220, "INVREF1232",
                "2018-01-15", "2018-01-21", "ExtReNo1002");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(114, 1110, 4440, 2220, "INVREF1233",
                "2018-01-22", "2018-01-28", "ExtReNo1003");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(115, 1110, 4440, 2220, "INVREF1234",
                "2018-01-29", "2018-02-04", "ExtReNo1004");
        list.add(o);
        return list;
    }
    
    private List<VwTimesheetList> createMockSingleExtTimesheetList() {
        List<VwTimesheetList> list = new ArrayList<VwTimesheetList>();
        VwTimesheetList o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(111, 1110, 1234, 2220,
                        "INVREF1230", "2018-01-01", "2018-01-07", "ExtReNo1000",
                        3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        list.add(o);
        return list;
    }
    
    private List<VwTimesheetList> createMockMultipleExtTimesheetList() {
        List<VwTimesheetList> list = new ArrayList<VwTimesheetList>();
        VwTimesheetList o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(111, 1110, 1234, 2220,
                "INVREF1230", "2018-01-01", "2018-01-07", "ExtReNo1000",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(112, 1110, 1234, 2220,
                "INVREF1231", "2018-01-08", "2018-01-14", "ExtReNo1001",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(113, 1110, 1234, 2220,
                "INVREF1232", "2018-01-15", "2018-01-21", "ExtReNo1002",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(114, 1110, 1234, 2220,
                "INVREF1233", "2018-01-22", "2018-01-28", "ExtReNo1003",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(115, 1110, 1234, 2220,
                "INVREF1234", "2018-01-29", "2018-02-04", "ExtReNo1004",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        list.add(o);
        return list;
    }
    
    private List<VwTimesheetHours> createMockTimesheetHours() {
        List<VwTimesheetHours> list = new ArrayList<VwTimesheetHours>();
        VwTimesheetHours o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-01", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-02", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-03", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-04", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-05", 8, true);
        list.add(o);
        return list;
    }
    
    /**
     * 
     * @return
     */
    private List<VwTimesheetSummary> createMockTimesheetSummaryList() {
        List<VwTimesheetSummary> list = new ArrayList<>();
        VwTimesheetSummary o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetSummary(111, "john", "doe", "2020-5-15", 8);
        list.add(o);
        return list;
    }

    /**
     * 
     * @param day
     * @return
     */
    public static final List<ProjEvent> createMockEventDataSingle() {
        List<ProjEvent> list = new ArrayList<ProjEvent>();
        ProjEvent o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(ProjectTrackerMockDataFactory.TEST_EVENT_ID, 1234,
                "2018-01-01", 0);
        list.add(o);
        return list;
    }

    /**
     * 
     * @param day
     * @return
     */
    public static final List<ProjEvent> createMockMultiple_Day_Task_Events(int projectTaskId) {
        List<ProjEvent> list = new ArrayList<ProjEvent>();
        int eventId = ProjectTrackerMockDataFactory.TEST_EVENT_ID;
        // Day 1
        ProjEvent o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(eventId, projectTaskId, "2018-01-01", 0);
        list.add(o);
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(++eventId, projectTaskId, "2018-01-02", 2);
        list.add(o);
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(++eventId, projectTaskId, "2018-01-03", 2);
        list.add(o);
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(++eventId, projectTaskId, "2018-01-04", 2);
        list.add(o);
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(++eventId, projectTaskId, "2018-01-05", 1);
        list.add(o);
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(++eventId, projectTaskId, "2018-01-06", 1);
        list.add(o);
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(++eventId, projectTaskId, "2018-01-07", 0);
        list.add(o);
        
       
        return list;
    }
}