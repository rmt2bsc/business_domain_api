package org.rmt2.api.invoicing;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dao.mapping.orm.rmt2.VwTimesheetHours;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.modules.timesheet.invoice.InvoiceTimesheetApi;
import org.modules.timesheet.invoice.InvoiceTimesheetApiFactory;
import org.modules.timesheet.invoice.InvoiceTimesheetApiImpl;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Test the invoice calculation functionality of the Timesheet module of the
 * Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class, InvoiceTimesheetApiImpl.class, TimesheetApiFactory.class })
public class InvoiceCalculatingApiTest extends InvoicingMockData {
    
    private List<VwTimesheetHours> mockTimesheetMixedHours;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockTimesheetMixedHours = this.createMockTimesheetHoursMixed();
        
        // Stub Project/Employee Fetch
        try {
            // For the sake of simplicity when calculating timesheet hours, using mock data from ancestor
            when(this.mockPersistenceClient
                    .retrieveList(isA(VwEmployeeProjects.class))).thenReturn(this.mockVwEmployeeProjectsFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single employee projects case setup failed");
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<VwTimesheetHours> createMockTimesheetHoursMixed() {
        List<VwTimesheetHours> list = new ArrayList<VwTimesheetHours>();
        VwTimesheetHours o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-01", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-02", 8, false);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-03", 8, true);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-04", 8, false);
        list.add(o);
        
        o = ProjectTrackerMockDataFactory.createMockOrmVwTimesheetHours(111, 1110, 4440, 2220,
                1112220, 123401, 444441, "2018-01-05", 8, true);
        list.add(o);
        return list;
    }
    
    @Test
    public void testSuccess_Invoice_Calculation_With_All_Billable_Hours() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateInvoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2000.00, results, 0);
    }

    @Test
    public void testSuccess_Invoice_Calculation_With_Billable_and_NonBillable_Hours() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetMixedHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateInvoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1200.00, results, 0);
    }
    
    @Test
    public void testSuccess_Overtime_Invoice_Calculation() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockTimesheetHours.get(0).setHours(10);
            this.mockTimesheetHours.get(3).setHours(15);
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateInvoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2495.00, results, 0);
    }
    
    /**
     * Test successful path with a combination of billable, non-billable, and over time hours
     * when the last event's hours is partially made up of reg hours and over time hours 
     */
    @Test
    public void testSuccess_Overtime_Invoice_Calculation_With_Billable_and_NonBiliable_Hours_1() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockTimesheetMixedHours.get(0).setHours(10);
            this.mockTimesheetMixedHours.get(2).setHours(13);
            this.mockTimesheetMixedHours.get(3).setHours(15);
            this.mockTimesheetMixedHours.get(3).setBillable(1);
            this.mockTimesheetMixedHours.get(4).setHours(16);
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetMixedHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateInvoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2770.00, results, 0);
    }
    
    /**
     * Test successful path with all billable type hours and the over time hours
     * far exceed the regular pay by at least 2 event hours
     */
    @Test
    public void testSuccess_Overtime_Invoice_Calculation_With_Billable_Hours_2() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockTimesheetHours.get(0).setHours(15);
            this.mockTimesheetHours.get(1).setHours(15);
            this.mockTimesheetHours.get(2).setHours(10);
            this.mockTimesheetHours.get(3).setHours(10);
            this.mockTimesheetHours.get(4).setHours(10);
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateInvoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3100.00, results, 0);
    }
    
    /**
     * Test successful path with all billable type hours and the over time hours
     * exceed the regular pay by 2 hours with the last event hours item.
     */
    @Test
    public void testSuccess_Overtime_Invoice_Calculation_With_Billable_Hours_3() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockTimesheetHours.get(4).setHours(10);
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateInvoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2110.00, results, 0);
    }
    
    /**
     * Test successful path with all billable type hours and the over time hours
     * exceed the regular and the last event item is all over time hours.
     */
    @Test
    public void testSuccess_Overtime_Invoice_Calculation_With_Billable_Hours_4() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            this.mockTimesheetHours.get(0).setHours(10);
            this.mockTimesheetHours.get(1).setHours(13);
            this.mockTimesheetHours.get(2).setHours(10);
            this.mockTimesheetHours.get(3).setHours(10);
            this.mockTimesheetHours.get(4).setHours(2);
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateInvoice(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2275.00, results, 0);
    }
    
    /**
     * Test successful path calculating billable hours where time sheet
     * possesses all billable hours.
     */
    @Test
    public void testSuccess_Calculate_Billable_Hours_1() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateBillableHours(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(40, results, 0);
    }
    
    /**
     * Test successful path calculating billable hours where time sheet
     * possesses a mixture of billable and non-billable hours.
     */
    @Test
    public void testSuccess_Calculate_Billable_Hours_2() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetMixedHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateBillableHours(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(24, results, 0);
    }
    
    /**
     * Test successful path calculating billable hours. 
     */
    @Test
    public void testSuccess_Calculate_NonBillable_Hours() {
        // Stub Timesheet Hours Fetch
        VwTimesheetHours mockHoursCriteria = new VwTimesheetHours();
        mockHoursCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockHoursCriteria))).thenReturn(this.mockTimesheetMixedHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        // Perform test
        InvoiceTimesheetApiFactory f = new InvoiceTimesheetApiFactory();
        InvoiceTimesheetApi api = f.createApi(this.mockDaoClient);
        double results = 0;
        try {
            results = api.calculateNonBillableHours(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(16, results, 0);
    }
}