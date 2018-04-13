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
    public static List<Integer> TEST_NULL_CLIENT_ID_LIST = Arrays.asList(null, 1111, 1112, 1113);
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
            this.mockTimesheetHoursMap.put(InvoicingMockData.TEST_TIMESHEET_ID[ndx],
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

}