package org.rmt2.api;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.dao.mapping.orm.rmt2.ProjProject;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.junit.After;
import org.junit.Before;

/**
 * Project Tracker Aministration testing facility that is mainly responsible for
 * setting up mock data.
 * <p>
 * All derived project tracker related Api unit tests should inherit this class
 * to prevent duplicating common functionality.
 * 
 * @author rterrell
 * 
 */
public class ProjectAdminApiTestData extends BaseProjectTrackerDaoTest {
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
        ProjClient o = ProjectTrackerMockDataUtility.createMockOrmProjClient(
                1000, 1350, "1000 Company", 70.00, 80.00, "000-111", "steve",
                "gadd", "0000000000", "stevegadd@gte.net");
        list.add(o);
        return list;
    }

    private List<ProjClient> createMockMultipleClient() {
        List<ProjClient> list = new ArrayList<ProjClient>();
        ProjClient o = ProjectTrackerMockDataUtility.createMockOrmProjClient(
                1000, 1350, "1000 Company", 70.00, 80.00, "000-111", "steve",
                "gadd", "0000000000", "stevegadd@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjClient(1001, 1351,
                "1001 Company", 80.00, 90.00, "111-111", "billy", "cobham",
                "1111111111", "billycobham@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjClient(1002, 1352,
                "1002 Company", 90.00, 100.00, "222-111", "dennis", "chambers",
                "2222222222", "dennischamabers@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjClient(1003, 1353,
                "1003 Company", 100.00, 110.00, "333-111", "harvey", "mason",
                "3333333333", "harveymason@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjClient(1004, 1354,
                "1004 Company", 110.00, 120.00, "444-111", "raford", "griffin",
                "4444444444", "rayfordgriffin@gte.net");
        list.add(o);
        return list;
    }

    private List<ProjProject> createMockSingleProject() {
        List<ProjProject> list = new ArrayList<ProjProject>();
        ProjProject o = ProjectTrackerMockDataUtility.createMockOrmProjProject(
                2220, 1000, "Project 2220", "2018-01-01", "2018-02-01");
        list.add(o);
        return list;
    }

    private List<ProjProject> createMockMultipleProject() {
        List<ProjProject> list = new ArrayList<ProjProject>();
        ProjProject o = ProjectTrackerMockDataUtility.createMockOrmProjProject(
                2220, 1000, "Project 2220", "2018-01-01", "2018-02-01");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjProject(2221, 1000,
                "Project 2221", "2018-02-01", "2018-03-01");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjProject(2222, 1000,
                "Project 2222", "2018-03-01", "2018-04-01");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjProject(2223, 1000,
                "Project 2223", "2018-04-01", "2018-05-01");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjProject(2224, 1000,
                "Project 2224", "2018-05-01", "2018-06-01");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployeeTitle> createMockSingleEmployeeTitle() {
        List<ProjEmployeeTitle> list = new ArrayList<ProjEmployeeTitle>();
        ProjEmployeeTitle o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployeeTitle> createMockMultipleEmployeeTitle() {
        List<ProjEmployeeTitle> list = new ArrayList<ProjEmployeeTitle>();
        ProjEmployeeTitle o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeTitle(102, "Employee Title 2");
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeTitle(103, "Employee Title 3");
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeTitle(104, "Employee Title 4");
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeTitle(105, "Employee Title 5");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployeeType> createMockSingleEmployeeType() {
        List<ProjEmployeeType> list = new ArrayList<ProjEmployeeType>();
        ProjEmployeeType o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeType(201, "Full Time");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployeeType> createMockMultipleEmployeeType() {
        List<ProjEmployeeType> list = new ArrayList<ProjEmployeeType>();
        ProjEmployeeType o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeType(201, "Employee Type 1");
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeType(202, "Part Time");
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployeeType(203, "Contractor");
        list.add(o);
        
        return list;
    }
    
    private List<ProjEmployee> createMockSingleEmployee() {
        List<ProjEmployee> list = new ArrayList<ProjEmployee>();
        ProjEmployee o = ProjectTrackerMockDataUtility.createMockOrmProjEmployee(5000, 201, 0, 3333, 101, 999991,
                "2010-01-01", null, "ffirst", "fulltime", "first",
                "111-11-5000", "ABC Company");
        list.add(o);
        return list;
    }
    
    private List<ProjEmployee> createMockMultipleEmployee() {
        List<ProjEmployee> list = new ArrayList<ProjEmployee>();
        ProjEmployee o = ProjectTrackerMockDataUtility.createMockOrmProjEmployee(5000, 201, 0, 3333, 101, 999991,
                "2010-01-01", null, "ffirst", "fulltime", "first",
                "111-11-5000", "ABC Company");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployee(5001, 201, 0, 3333, 102, 999992,
                "2011-01-01", null, "fsecond", "fulltime", "second",
                "111-11-5001", "ABC Company");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployee(5002, 201, 0, 3333, 103, 999993,
                "2012-01-01", null, "fthird", "fulltime", "third",
                "111-11-5002", "ABC Company");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployee(5003, 202, 0, 3333, 104, 999994,
                "2013-01-01", "2017-01-01", "pfirst", "parttime", "first",
                "111-11-5003", "ABC Company");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjEmployee(5004, 203, 1, 3334, 105, 999995,
                "2014-01-01", "2018-01-01", "cfirst", "contractor", "first",
                "111-11-5004", "ABC Company");
        list.add(o);
        return list;
    }
    
    private List<VwEmployeeProjects> createMockSingleVwEmployeeProjects() {
        List<VwEmployeeProjects> list = new ArrayList<VwEmployeeProjects>();
        VwEmployeeProjects o = ProjectTrackerMockDataUtility.createMockOrmVwEmployeeProjects(55551, 2220, "Project 2220",
                        1000, "1000 Company", 1350, "000-111", 5000,
                        "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                        50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        return list;
    }
    
    private List<VwEmployeeProjects> createMockMultipleVwEmployeeProjects() {
        List<VwEmployeeProjects> list = new ArrayList<VwEmployeeProjects>();
        VwEmployeeProjects o = ProjectTrackerMockDataUtility.createMockOrmVwEmployeeProjects(55551, 2220, "Project 2220",
                        1000, "1000 Company", 1350, "000-111", 5000,
                        "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                        50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmVwEmployeeProjects(55552,
                2220, "Project 2220", 1000, "1000 Company", 1350, "000-111",
                5001, "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmVwEmployeeProjects(55553,
                2220, "Project 2220", 1000, "1000 Company", 1350, "000-111",
                5002, "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmVwEmployeeProjects(55554,
                2220, "Project 2220", 1000, "1000 Company", 1350, "000-111",
                5003, "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        o = ProjectTrackerMockDataUtility.createMockOrmVwEmployeeProjects(55555,
                2220, "Project 2220", 1000, "1000 Company", 1350, "000-111",
                5004, "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        list.add(o);
        
        return list;
    }
}