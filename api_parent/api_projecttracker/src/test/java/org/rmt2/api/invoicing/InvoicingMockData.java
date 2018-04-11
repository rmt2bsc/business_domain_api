package org.rmt2.api.invoicing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.VwTimesheetHours;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.junit.After;
import org.junit.Before;
import org.rmt2.api.ProjectTrackerMockDataFactory;
import org.rmt2.api.timesheet.TimesheetMockData;

/**
 * Timesheet Invoicing API testing facility that is mainly responsible for setting up mock
 * data.
 * <p>
 * All Timesheet API unit tests should inherit this class to prevent duplicating
 * common functionality.
 * 
 * @author rterrell
 * 
 */
public class InvoicingMockData extends TimesheetMockData {
    public static final int TEST_EMPLOYEE_ID[] = {2220, 2221, 2222, 2223};
    public static final int TEST_CLIENT_ID[] = {1110, 1111, 1112, 1113};
    public static List<Integer> TEST_CLIENT_ID_LIST = Arrays.asList(1110, 1111, 1112, 1113);
    public static final int TEST_CLIENT_BUSINESS_ID[] = {1350, 1351, 1352, 1353};
    public static final int TEST_TIMESHEET_ID[] = {111, 112, 113, 114};
    public static final int TEST_PROJ_ID[] = {4440, 4441, 4442, 4443};
    
    protected Map<Integer, List<ProjClient>>  mockClientMap;
    protected Map<Integer, List<ProjEmployee>>  mockEmployeeMap;
    protected Map<Integer, List<VwTimesheetList>>  mockClientTimesheetMap;
    protected Map<Integer, List<VwTimesheetHours>>  mockTimesheetHoursMap;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.buildMockClientData();
        this.buildMockEmployeeData();
        this.buildMockClientTimesheetData();
        this.buildMockTimesheetHoursData();
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

    private void buildMockClientData() {
        this.mockClientMap = new HashMap<>();
        for (int ndx = 0; ndx < InvoicingMockData.TEST_CLIENT_ID.length; ndx++) {
            this.mockClientMap.put(InvoicingMockData.TEST_CLIENT_ID[ndx],
                    this.createMockDynamicClient(InvoicingMockData.TEST_CLIENT_ID[ndx],
                            InvoicingMockData.TEST_CLIENT_BUSINESS_ID[ndx]));
        }
    }
    
    private void buildMockEmployeeData() {
        this.mockEmployeeMap = new HashMap<>();
        for (int ndx = 0; ndx < InvoicingMockData.TEST_EMPLOYEE_ID.length; ndx++) {
            this.mockEmployeeMap.put(InvoicingMockData.TEST_EMPLOYEE_ID[ndx],
                    this.createMockDynamicEmployee(InvoicingMockData.TEST_EMPLOYEE_ID[ndx]));
        }
    }
    
    private void buildMockClientTimesheetData() {
        this.mockClientTimesheetMap = new HashMap<>();
        for (int ndx = 0; ndx < InvoicingMockData.TEST_CLIENT_ID.length; ndx++) {
            this.mockClientTimesheetMap.put(InvoicingMockData.TEST_CLIENT_ID[ndx],
                    this.createMockDynamicClientTimesheets(InvoicingMockData.TEST_TIMESHEET_ID[ndx],
                            InvoicingMockData.TEST_CLIENT_ID[ndx],
                            InvoicingMockData.TEST_PROJ_ID[ndx],
                            InvoicingMockData.TEST_EMPLOYEE_ID[ndx]));
        }
    }
    
    private void buildMockTimesheetHoursData() {
        this.mockTimesheetHoursMap = new HashMap<>();
        for (int ndx = 0; ndx < InvoicingMockData.TEST_TIMESHEET_ID.length; ndx++) {
            this.mockTimesheetHoursMap.put(
                    InvoicingMockData.TEST_TIMESHEET_ID[ndx],
                    this.createMockDynamicTimesheetHours(InvoicingMockData.TEST_TIMESHEET_ID[ndx],
                            InvoicingMockData.TEST_CLIENT_ID[ndx],
                            InvoicingMockData.TEST_PROJ_ID[ndx],
                            InvoicingMockData.TEST_EMPLOYEE_ID[ndx]));
        }
    }
    
    private List<ProjClient> createMockDynamicClient(int clientId, int businessId) {
        List<ProjClient> list = new ArrayList<ProjClient>();
        ProjClient o = ProjectTrackerMockDataFactory.createMockOrmProjClient(
                clientId, businessId, clientId + " Company", 70.00, 80.00, "000-111", "firstname" + clientId,
                "lastname" + clientId, "0000000000", "firstname" + clientId + "lastname" + clientId + "@gte.net");
        list.add(o);
        return list;
    }

    private List<ProjEmployee> createMockDynamicEmployee(int empId) {
        List<ProjEmployee> list = new ArrayList<ProjEmployee>();
        ProjEmployee o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(empId, 201, 0, 3330, 101, 999991,
                "2010-01-01", null, "login_name_1", "first_name_" + empId, "last_name_" + empId,
                "111-11-5000", "ABC Company");
        list.add(o);
        return list;
    }
    
    private List<VwTimesheetList> createMockDynamicClientTimesheets(int timesheetId, int clientId, int projId, int empId) {
        List<VwTimesheetList> list = new ArrayList<VwTimesheetList>();
        VwTimesheetList o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(timesheetId, clientId, projId, empId,
                "INVREF1230", "2018-01-01", "2018-01-07", "ExtReNo1000",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(timesheetId, clientId, projId, empId,
                "INVREF1231", "2018-01-08", "2018-01-14", "ExtReNo1001",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        list.add(o);

        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetList(timesheetId, clientId, projId, empId,
                "INVREF1232", "2018-01-15", "2018-01-21", "ExtReNo1002",
                3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        list.add(o);
        return list;
    }
    
    private List<VwTimesheetHours> createMockDynamicTimesheetHours(int timesheetId, int clientId, int projId, int empId) {
        List<VwTimesheetHours> list = new ArrayList<VwTimesheetHours>();
        VwTimesheetHours o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(timesheetId, clientId, projId, empId,
                1112220, 123401, 444441, "2018-01-01", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(timesheetId, clientId, projId, empId,
                1112220, 123401, 444441, "2018-01-02", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(timesheetId, clientId, projId, empId,
                1112220, 123401, 444441, "2018-01-03", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(timesheetId, clientId, projId, empId,
                1112220, 123401, 444441, "2018-01-04", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(timesheetId, clientId, projId, empId,
                1112220, 123401, 444441, "2018-01-05", 8, true);
        list.add(o);
        return list;
    }
    
    
    
//    private List<ProjClient> createMockClient_1110() {
//        List<ProjClient> list = new ArrayList<ProjClient>();
//        ProjClient o = ProjectTrackerMockDataFactory.createMockOrmProjClient(
//                1110, 1350, "1110 Company", 70.00, 80.00, "000-111", "firstname0",
//                "lastname0", "0000000000", "firstname0lastname0@gte.net");
//        list.add(o);
//        return list;
//    }
//    
//    private List<ProjClient> createMockClient_1111() {
//        List<ProjClient> list = new ArrayList<ProjClient>();
//        ProjClient o = ProjectTrackerMockDataFactory.createMockOrmProjClient(1111, 1351,
//                "1111 Company", 80.00, 90.00, "111-111", "firstname1", "lastname1",
//                "1111111111", "firstname1lastname1@gte.net");
//        list.add(o);
//        return list;
//    }
//    
//    private List<ProjClient> createMockClient_1112() {
//        List<ProjClient> list = new ArrayList<ProjClient>();
//        ProjClient o = ProjectTrackerMockDataFactory.createMockOrmProjClient(1112, 1352,
//                "1112 Company", 90.00, 100.00, "222-111", "firstname2", "lastname2",
//                "2222222222", "firstname2lastname2@gte.net");
//        list.add(o);
//        return list;
//    }
//    
//    private List<ProjClient> createMockClient_1113() {
//        List<ProjClient> list = new ArrayList<ProjClient>();
//        ProjClient o = ProjectTrackerMockDataFactory.createMockOrmProjClient(1113, 1353,
//                "1113 Company", 100.00, 110.00, "333-111", "firstname3", "lastname3",
//                "3333333333", "firstname3lastname3@gte.net");
//        list.add(o);
//        return list;
//    }
    
    
//    private List<ProjTimesheet> createMockClientTimesheets_1110() {
//        List<ProjTimesheet> list = new ArrayList<ProjTimesheet>();
//        ProjTimesheet o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 4440, 2220, "INVREF1230",
//                "2018-01-01", "2018-01-07", "ExtReNo1000");
//        list.add(o);
//
//        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(112, 1110, 4440, 2220, "INVREF1231",
//                "2018-01-08", "2018-01-14", "ExtReNo1001");
//        list.add(o);
//
//        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(113, 1110, 4440, 2220, "INVREF1232",
//                "2018-01-15", "2018-01-21", "ExtReNo1002");
//        list.add(o);
//
//        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(114, 1110, 4440, 2220, "INVREF1233",
//                "2018-01-22", "2018-01-28", "ExtReNo1003");
//        list.add(o);
//
//        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(115, 1110, 4440, 2220, "INVREF1234",
//                "2018-01-29", "2018-02-04", "ExtReNo1004");
//        list.add(o);
//        return list;
//    }
//    
//    private List<ProjTimesheet> createMockClientTimesheets_1111() {
//        List<ProjTimesheet> list = new ArrayList<ProjTimesheet>();
//        ProjTimesheet o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(116, 1111, 4441, 2221, "INVREF1235",
//                "2018-01-01", "2018-01-07", "ExtReNo1005");
//        list.add(o);
//
//        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(117, 1111, 4441, 2221, "INVREF1236",
//                "2018-01-08", "2018-01-14", "ExtReNo1006");
//        list.add(o);
//        return list;
//    }
//    
//    private List<ProjTimesheet> createMockClientTimesheets_1112() {
//        List<ProjTimesheet> list = new ArrayList<ProjTimesheet>();
//        ProjTimesheet o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(118, 1112, 4442, 2222, "INVREF1237",
//                "2018-01-01", "2018-01-07", "ExtReNo1007");
//        list.add(o);
//
//        o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(119, 1112, 4442, 2222, "INVREF1238",
//                "2018-01-08", "2018-01-14", "ExtReNo1008");
//        list.add(o);
//        return list;
//    }
//    
//    private List<ProjTimesheet> createMockClientTimesheets_1113() {
//        List<ProjTimesheet> list = new ArrayList<ProjTimesheet>();
//        ProjTimesheet o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(120, 1113, 4443, 2223, "INVREF1239",
//                "2018-01-01", "2018-01-07", "ExtReNo1009");
//        list.add(o);
//        return list;
//    }
    

    
//    private List<ProjEmployee> createMockEmployee_2220() {
//        List<ProjEmployee> list = new ArrayList<ProjEmployee>();
//        ProjEmployee o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(2220, 201, 0, 3330, 101, 999991,
//                "2010-01-01", null, "login_name_1", "first_name_1", "last_name_1",
//                "111-11-5000", "ABC Company");
//        list.add(o);
//        return list;
//    }
//    
//    private List<ProjEmployee> createMockEmployee_2221() {
//        List<ProjEmployee> list = new ArrayList<ProjEmployee>();
//        ProjEmployee o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(2221, 201, 0, 3330, 102, 999992,
//                "2011-01-01", null, "login_name_2", "first_name_2", "last_name_2",
//                "111-11-5001", "ABC Company");
//        list.add(o);
//        return list;
//    }
//    
//    private List<ProjEmployee> createMockEmployee_2222() {
//        List<ProjEmployee> list = new ArrayList<ProjEmployee>();
//        ProjEmployee o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(2222, 201, 0, 3330, 103, 999993,
//                "2012-01-01", null, "login_name_3", "first_name_3", "last_name_3",
//                "111-11-5002", "ABC Company");
//        list.add(o);
//        return list;
//    }
//    
//    private List<ProjEmployee> createMockEmployee_2223() {
//        List<ProjEmployee> list = new ArrayList<ProjEmployee>();
//        ProjEmployee o = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(2223, 202, 1, 3330, 104, 999994,
//                "2013-01-01", "2018-01-01", "login_name_4", "first_name_4", "last_name_4",
//                "111-11-5003", "ABC Company");
//        list.add(o);
//        return list;
//    }
}