package org.rmt2.api.timesheet;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.mapping.orm.rmt2.VwTimesheetEventList;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
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
    protected List<ProjTimesheet> mockProjTimesheetSingle;
    protected List<ProjTimesheet> mockProjTimesheetMultiple;
    protected List<VwTimesheetList> mockVwTimesheetSingle;
    protected List<VwTimesheetList> mockVwTimesheetMultiple;    
    protected List<VwTimesheetEventList> mockVwTimesheetEventListFetchMultiple;
    protected List<VwTimesheetEventList> mockVwTimesheetEventListFetchSingle;
    protected List<VwTimesheetProjectTask> mockVwTimesheetProjectTaskFetchMultiple;
    protected List<VwTimesheetProjectTask> mockVwTimesheetProjectTaskFetchSingle;
    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockVwTimesheetEventListFetchMultiple = this.createMockMultipleVwTimesheetEventList();
        this.mockVwTimesheetEventListFetchSingle = this.createMockSingleVwTimesheetEventList();
        this.mockVwTimesheetProjectTaskFetchMultiple = this.createMockMultipleVwTimesheetProjectTask();
        this.mockVwTimesheetProjectTaskFetchSingle = this.createMockSingleVwTimesheetProjectTask();
        this.mockProjTimesheetSingle = this.createMockSingleTimesheetList();
        this.mockProjTimesheetMultiple = this.createMockMultipleTimesheetList();
        this.mockVwTimesheetSingle = this.createMockSingleExtTimesheetList();
        this.mockVwTimesheetMultiple = this.createMockMultipleExtTimesheetList();
        
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
    
    private List<VwTimesheetProjectTask> createMockSingleVwTimesheetProjectTask() {
        List<VwTimesheetProjectTask> list = new ArrayList<VwTimesheetProjectTask>();
        VwTimesheetProjectTask o = ProjectTrackerMockDataFactory
                .createMockOrmVwTimesheetProjectTask(444441, 111, 2220,
                        1112220, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Design and Analysis", true);
        list.add(o);
        return list;
    }
    
    private List<VwTimesheetProjectTask> createMockMultipleVwTimesheetProjectTask() {
        List<VwTimesheetProjectTask> list = new ArrayList<VwTimesheetProjectTask>();
        VwTimesheetProjectTask o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444441, 111, 2220,
                        1112220, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Design and Analysis", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444442, 111, 2220,
                        1112221, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Development", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444443, 111, 2220,
                        1112222, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Meetings", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444444, 111, 2220,
                        1112223, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Testing", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetProjectTask(444445, 111, 2220,
                        1112224, 1110, "Project 2220", "2018-01-01",
                        "2018-01-07", "Holiday", false);
        list.add(o);
        
        return list;
    }
    
    private List<VwTimesheetEventList> createMockSingleVwTimesheetEventList() {
        List<VwTimesheetEventList> list = new ArrayList<VwTimesheetEventList>();
        VwTimesheetEventList o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123401, "2018-01-01", 8,
                        444441, 111, 2220, "Project 2220", 1112220,
                        "Design and Analysis", 1110, "2018-01-01", "2018-01-07", true);
        list.add(o);
        return list;
    }
    
    private List<VwTimesheetEventList> createMockMultipleVwTimesheetEventList() {
        List<VwTimesheetEventList> list = new ArrayList<VwTimesheetEventList>();
        VwTimesheetEventList o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123401, "2018-01-01", 8,
                        444441, 111, 2220, "Project 2220", 1112220,
                        "Design and Analysis", 1110, "2018-01-01", "2018-01-07",
                        true);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123402, "2018-01-02", 8, 444442, 111, 2220,
                "Project 2220", 1112221, "Development", 1110, "2018-01-01",
                "2018-01-07", true);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123403, "2018-01-03", 8, 444443, 111, 2220,
                "Project 2220", 1112222, "Meetings", 1110, "2018-01-01",
                "2018-01-07", true);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123404, "2018-01-04", 8, 444444, 111, 2220,
                "Project 2220", 1112223, "Testing", 1110, "2018-01-01",
                "2018-01-07", true);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetEventList(123405, "2018-01-05", 8, 444445, 111, 2220,
                "Project 2220", 1112224, "Holiday", 1110, "2018-01-01",
                "2018-01-07", false);
        list.add(o);
        return list;
    }
    
    private List<ProjTimesheet> createMockSingleTimesheetList() {
        List<ProjTimesheet> list = new ArrayList<ProjTimesheet>();
        ProjTimesheet o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 1234, 2220, "INVREF1230",
                "2018-01-01", "2018-01-07", "ExtReNo1000");
        list.add(o);
        return list;
    }
    
    private List<ProjTimesheet> createMockMultipleTimesheetList() {
        List<ProjTimesheet> list = new ArrayList<ProjTimesheet>();
        ProjTimesheet o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 1234, 2220, "INVREF1230",
                "2018-01-01", "2018-01-07", "ExtReNo1000");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(112, 1110, 1234, 2220, "INVREF1231",
                "2018-01-08", "2018-01-14", "ExtReNo1001");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(113, 1110, 1234, 2220, "INVREF1232",
                "2018-01-15", "2018-01-21", "ExtReNo1002");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(114, 1110, 1234, 2220, "INVREF1233",
                "2018-01-22", "2018-01-28", "ExtReNo1003");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(115, 1110, 1234, 2220, "INVREF1234",
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
}