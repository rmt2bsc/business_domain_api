package org.rmt2.api;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProject;
import org.dao.mapping.orm.rmt2.ProjTask;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.junit.After;
import org.junit.Before;

/**
 * Project Tracker Administration testing facility that is mainly responsible for
 * setting up mock data.
 * <p>
 * All derived project tracker related Api unit tests should inherit this class
 * to prevent duplicating common functionality.
 * 
 * @author rterrell
 * 
 */
public class ProjectTrackerMockData extends BaseProjectTrackerDaoTest {
    protected List<ProjClient> mockClientFetchMultiple;
    protected List<ProjClient> mockClientFetchSingle;
    protected List<ProjProject> mockProjectFetchSingle;
    protected List<ProjProject> mockProjectFetchMultiple;
    protected List<ProjEmployee> mockEmployeeFetchMultiple;
    protected List<ProjEmployee> mockEmployeeFetchSingle;
    protected List<ProjEmployeeType> mockEmployeeTypeFetchMultiple;
    protected List<ProjEmployeeType> mockEmployeeTypeFetchSingle;
    protected List<ProjEmployeeTitle> mockEmployeeTitleFetchMultiple;
    protected List<ProjEmployeeTitle> mockEmployeeTitleFetchSingle;
    protected List<VwEmployeeProjects> mockVwEmployeeProjectsFetchMultiple;
    protected List<VwEmployeeProjects> mockVwEmployeeProjectsFetchSingle;
    protected List<ProjEvent> mockProjEventFetchMultiple;
    protected List<ProjEvent> mockProjEventFetchSingle;
    protected List<ProjTask> mockProjTaskFetchMultiple;
    protected List<ProjTask> mockProjTaskFetchSingle;
    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockClientFetchMultiple = this.createMockMultipleClient();
        this.mockClientFetchSingle = this.createMockSingleClient();
        this.mockProjectFetchMultiple = this.createMockMultipleProject();
        this.mockProjectFetchSingle = this.createMockSingleProject();
        this.mockEmployeeFetchMultiple = this.createMockMultipleEmployee();
        this.mockEmployeeFetchSingle = this.createMockSingleEmployee();
        this.mockEmployeeTitleFetchMultiple = this.createMockMultipleEmployeeTitle();
        this.mockEmployeeTitleFetchSingle = this.createMockSingleEmployeeTitle();
        this.mockEmployeeTypeFetchMultiple = this.createMockMultipleEmployeeType();
        this.mockEmployeeTypeFetchSingle = this.createMockSingleEmployeeType();
        this.mockVwEmployeeProjectsFetchMultiple = this.createMockMultipleVwEmployeeProjects();
        this.mockVwEmployeeProjectsFetchSingle = this.createMockSingleVwEmployeeProjects();
        this.mockProjEventFetchMultiple = this.createMockMultipleEvent();
        this.mockProjEventFetchSingle = this.createMockSingleEvent();
        this.mockProjTaskFetchMultiple = this.createMockMultipleTask();
        this.mockProjTaskFetchSingle = this.createMockSingleTask();
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

    private List<ProjClient> createMockSingleClient() {
        List<ProjClient> list = new ArrayList<ProjClient>();
        ProjClient o = ProjectTrackerMockDataFactory.createMockOrmProjClient(
                1000, 1350, "1000 Company", 70.00, 80.00, "000-111", "steve",
                "gadd", "0000000000", "stevegadd@gte.net");
        list.add(o);
        return list;
    }

    private List<ProjClient> createMockMultipleClient() {
        List<ProjClient> list = new ArrayList<ProjClient>();
        ProjClient o = ProjectTrackerMockDataFactory.createMockOrmProjClient(
                1000, 1350, "1000 Company", 70.00, 80.00, "000-111", "firstname0",
                "lastname0", "0000000000", "firstname0lastname0@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjClient(1001, 1351,
                "1001 Company", 80.00, 90.00, "111-111", "firstname1", "lastname1",
                "1111111111", "firstname1lastname1@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjClient(1002, 1352,
                "1002 Company", 90.00, 100.00, "222-111", "firstname2", "lastname2",
                "2222222222", "firstname2lastname2@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjClient(1003, 1353,
                "1003 Company", 100.00, 110.00, "333-111", "firstname3", "lastname3",
                "3333333333", "firstname3lastname3@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjClient(1004, 1354,
                "1004 Company", 110.00, 120.00, "444-111", "firstname4", "lastname4",
                "4444444444", "firstname4lastname4@gte.net");
        list.add(o);
        return list;
    }

    private List<ProjProject> createMockSingleProject() {
        List<ProjProject> list = new ArrayList<ProjProject>();
        ProjProject o = ProjectTrackerMockDataFactory.createMockOrmProjProject(
                2220, 1000, "Project 2220", "2018-01-01", "2018-02-01");
        list.add(o);
        return list;
    }

    private List<ProjProject> createMockMultipleProject() {
        List<ProjProject> list = new ArrayList<ProjProject>();
        ProjProject o = ProjectTrackerMockDataFactory.createMockOrmProjProject(
                2220, 1000, "Project 2220", "2018-01-01", "2018-02-01");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjProject(2221, 1000,
                "Project 2221", "2018-02-01", "2018-03-01");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjProject(2222, 1000,
                "Project 2222", "2018-03-01", "2018-04-01");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjProject(2223, 1000,
                "Project 2223", "2018-04-01", "2018-05-01");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjProject(2224, 1000,
                "Project 2224", "2018-05-01", "2018-06-01");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployeeTitle> createMockSingleEmployeeTitle() {
        List<ProjEmployeeTitle> list = new ArrayList<ProjEmployeeTitle>();
        ProjEmployeeTitle o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployeeTitle> createMockMultipleEmployeeTitle() {
        List<ProjEmployeeTitle> list = new ArrayList<ProjEmployeeTitle>();
        ProjEmployeeTitle o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(102, "Employee Title 2");
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(103, "Employee Title 3");
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(104, "Employee Title 4");
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(105, "Employee Title 5");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployeeType> createMockSingleEmployeeType() {
        List<ProjEmployeeType> list = new ArrayList<ProjEmployeeType>();
        ProjEmployeeType o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeType(201, "Employee Type 1");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployeeType> createMockMultipleEmployeeType() {
        List<ProjEmployeeType> list = new ArrayList<ProjEmployeeType>();
        ProjEmployeeType o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeType(201, "Employee Type 1");
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeType(202, "Employee Type 2");
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeType(203, "Employee Type 3");
        list.add(o);
        
        return list;
    }
    
    private List<ProjEmployee> createMockSingleEmployee() {
        List<ProjEmployee> list = new ArrayList<ProjEmployee>();
        ProjEmployee o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5000, 201, 1, 3333, 101, 999991,
                "2010-01-01", "2018-01-01", "login_name_1", "first_name_1", "last_name_1",
                "111-11-5000", "ABC Company");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployee> createMockMultipleEmployee() {
        List<ProjEmployee> list = new ArrayList<ProjEmployee>();
        ProjEmployee o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5000, 201, 0, 3333, 101, 999991,
                "2010-01-01", null, "login_name_1", "first_name_1", "last_name_1",
                "111-11-5000", "ABC Company");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5001, 201, 0, 3333, 102, 999992,
                "2011-01-01", null, "login_name_2", "first_name_2", "last_name_2",
                "111-11-5001", "ABC Company");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5002, 201, 0, 3333, 103, 999993,
                "2012-01-01", null, "login_name_3", "first_name_3", "last_name_3",
                "111-11-5002", "ABC Company");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5003, 202, 1, 3333, 104, 999994,
                "2013-01-01", "2018-01-01", "login_name_4", "first_name_4", "last_name_4",
                "111-11-5003", "ABC Company");
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5004, 202, 1, 3333, 105, 999995,
                "2014-01-01", "2018-01-01", "login_name_5", "first_name_5", "last_name_5",
                "111-11-5004", "ABC Company");
        list.add(o);
        return list;
    }
    
    private List<VwEmployeeProjects> createMockSingleVwEmployeeProjects() {
        List<VwEmployeeProjects> list = new ArrayList<VwEmployeeProjects>();
        VwEmployeeProjects o = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55551, 2220, "Project 2220",
                        1000, "1000 Company", 1350, "000-111", 5000,
                        "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                        50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        return list;
    }
    
    private List<VwEmployeeProjects> createMockMultipleVwEmployeeProjects() {
        List<VwEmployeeProjects> list = new ArrayList<VwEmployeeProjects>();
        VwEmployeeProjects o = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55551, 2220, "Project 2220",
                        1000, "1000 Company", 1350, "000-111", 5000,
                        "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                        50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55552,
                2221, "Project 2221", 1001, "1001 Company", 1350, "000-111",
                5000, "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55553,
                2222, "Project 2222", 1002, "1002 Company", 1350, "000-111",
                5000, "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55554,
                2223, "Project 2223", 1003, "1003 Company", 1350, "000-111",
                5000, "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55555,
                2224, "Project 2224", 1004, "1004 Company", 1350, "000-111",
                5000, "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        return list;
    }
    
    private List<ProjEvent> createMockSingleEvent() {
        List<ProjEvent> list = new ArrayList<ProjEvent>();
        ProjEvent o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(123401, 444441, "2018-01-01", 8);
        list.add(o);
        return list;
    }
    
    private List<ProjEvent> createMockMultipleEvent() {
        List<ProjEvent> list = new ArrayList<ProjEvent>();
        ProjEvent o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(123401, 444441, "2018-01-01", 8);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(123402, 444442, "2018-01-02", 8);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(123403, 444443, "2018-01-03", 8);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(123404, 444444, "2018-01-04", 8);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjEvent(123405, 444445, "2018-01-05", 8);
        list.add(o);
        return list;
    }
    
    private List<ProjTask> createMockSingleTask() {
        List<ProjTask> list = new ArrayList<ProjTask>();
        ProjTask o = ProjectTrackerMockDataFactory.createMockOrmProjTask(1112220, "Design and Analysis", true);
        list.add(o);
        return list;
    }
    
    private List<ProjTask> createMockMultipleTask() {
        List<ProjTask> list = new ArrayList<ProjTask>();
        ProjTask o = ProjectTrackerMockDataFactory.createMockOrmProjTask(1112220, "Design and Analysis", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjTask(1112221, "Development", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjTask(1112222, "Meetings", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjTask(1112223, "Testing", true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmProjTask(1112224, "Holiday", false);
        list.add(o);
        return list;
    }
}