package org.rmt2.api.timesheet;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.dto.EventDto;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TimesheetDto;
import org.dto.TimesheetHistDto;
import org.dto.TimesheetHoursDto;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.dto.adapter.orm.TimesheetObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.ProjectTrackerApiConst;
import org.modules.admin.ProjectAdminApi;
import org.modules.admin.ProjectAdminApiException;
import org.modules.admin.ProjectAdminApiFactory;
import org.modules.timesheet.InvalidTimesheetException;
import org.modules.timesheet.TimesheetApi;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2Date;

/**
 * Test the query functionality of the Timesheet module of the Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TimesheetQueryApiTest extends TimesheetMockData {
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        VwTimesheetSummary mockCriteria = new VwTimesheetSummary();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockTimesheetSummary);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
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
    
    @Test
    public void testSuccess_Fetch_Single_Timesheet() {
        ProjTimesheet mockCriteria = new ProjTimesheet();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        TimesheetDto results = null;
        try {
            results = api.get(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        Assert.assertEquals(1110, results.getClientId());
        Assert.assertEquals(4440, results.getProjId());
        Assert.assertEquals(2220, results.getEmpId());
        Assert.assertEquals("INVREF1230", results.getInvoiceRefNo());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getBeginPeriod());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), results.getEndPeriod());
        Assert.assertEquals("ExtReNo1000", results.getExtRef());
        Assert.assertEquals("Comments" + results.getTimesheetId(), results.getComments());
        Assert.assertEquals("0000000111", results.getDisplayValue());
        Assert.assertEquals(results.getTimesheetId(), results.getDocumentId());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getDateCreated());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), results.getDateUpdated());
        Assert.assertEquals("testuser", results.getUpdateUserId());
        Assert.assertEquals("1.2.3.4", results.getIpCreated());
        Assert.assertEquals("1.2.3.4", results.getIpUpdated());
    }
    
    @Test
    public void testError_Fetch_Single_Timesheet_Too_Many_Returned() {
        ProjTimesheet mockCriteria = new ProjTimesheet();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTimesheetMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.get(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Method returned too many rows using timesheet id, "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Fetch_Single_Timesheet_DB_Access_Fault() {
        ProjTimesheet mockCriteria = new ProjTimesheet();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(new DatabaseException("A database error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.get(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Database error occurred retrieving single timesheet by id, "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Single_Timesheet_Null_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer nullParm = null;
        try {
            api.get(nullParm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Single_Timesheet_Negative_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = -1000;
        try {
            api.get(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative"));
            e.printStackTrace();
        }
    }
        
    @Test
    public void testValidation_Fetch_Single_Timesheet_Zero_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = 0;
        try {
            api.get(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " must be greater than zero"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Fetch_Multiple_Timesheet() {
        ProjTimesheet mockCriteria = new ProjTimesheet();
        mockCriteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTimesheetMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        TimesheetDto criteria = TimesheetObjectFactory.createTimesheetDtoInstance(null);
        List<TimesheetDto> results = null;
        try {
            criteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
            results = api.get(criteria);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TimesheetDto item = results.get(ndx);
            Assert.assertEquals((ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID + ndx), item.getTimesheetId());
            Assert.assertEquals(1110, item.getClientId());
            Assert.assertEquals(4440, item.getProjId());
            Assert.assertEquals(2220, item.getEmpId());
            Assert.assertEquals(("INVREF123" + ndx), item.getInvoiceRefNo());
            Assert.assertEquals(("ExtReNo100" + ndx), item.getExtRef());
            Assert.assertEquals(("Comments" + item.getTimesheetId()), item.getComments());
            Assert.assertEquals(("000000011" + (ndx + 1)), item.getDisplayValue());
            Assert.assertEquals(item.getTimesheetId(), item.getDocumentId());
            
            switch (ndx) {
                case 0:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getDateUpdated());
                    break;
                case 1:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-08"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-14"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-08"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-14"), item.getDateUpdated());
                    break;
                    
                case 2:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-15"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-21"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-15"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-21"), item.getDateUpdated());
                    break;
                case 3:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-22"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-28"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-22"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-28"), item.getDateUpdated());
                    break;
                case 4:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-29"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-02-04"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-29"), item.getDateCreated());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-02-04"), item.getDateUpdated());
                    break;
            }
            Assert.assertEquals("testuser", item.getUpdateUserId());
            Assert.assertEquals("1.2.3.4", item.getIpCreated());
            Assert.assertEquals("1.2.3.4", item.getIpUpdated());
        }
    }
    
    
    @Test
    public void testSuccess_Fetch_Single_Extended_Timesheet() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single extended timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        TimesheetDto results = null;
        try {
            results = api.getExt(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        Assert.assertEquals(1110, results.getClientId());
        Assert.assertEquals(1234, results.getProjId());
        Assert.assertEquals(2220, results.getEmpId());
        Assert.assertEquals("INVREF1230", results.getInvoiceRefNo());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getBeginPeriod());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), results.getEndPeriod());
        Assert.assertEquals("ExtReNo1000", results.getExtRef());
        Assert.assertEquals("Comments" + results.getTimesheetId(), results.getComments());
        Assert.assertEquals("0000000111", results.getDisplayValue());
        Assert.assertEquals(results.getTimesheetId(), results.getDocumentId());
        Assert.assertNull(results.getDateCreated());
        Assert.assertNull(results.getDateUpdated());
        Assert.assertNull(results.getUpdateUserId());
        Assert.assertNull(results.getIpCreated());
        Assert.assertNull(results.getIpUpdated());
        Assert.assertEquals(3330, results.getEmployeeManagerId());
        Assert.assertEquals("DRAFT", results.getStatusName());
        Assert.assertEquals("ACCT-111", results.getClientAccountNo());
        Assert.assertEquals(40, results.getBillHrs(), 0);
        Assert.assertEquals(0, results.getNonBillHrs(), 0);
        Assert.assertEquals(70.00, results.getEmployeeHourlyRate(), 0);
        Assert.assertEquals(80.00, results.getEmployeeHourlyOverRate(), 0);
        Assert.assertEquals(TimesheetConst.STATUS_DRAFT, results.getStatusId());
        Assert.assertEquals(results.getStatusName() + "Description", results.getStatusDescription());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getStatusEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), results.getStatusEndDate());
        Assert.assertEquals(222, results.getEmployeeTypeId());
        Assert.assertEquals(5555, results.getStatusHistId());
        Assert.assertEquals(results.getEmployeeLastname() + ", " + results.getEmployeeFirstname(), results.getEmployeeFullName());
    }
    
    @Test
    public void testError_Fetch_Single_Extended_Timesheet_Too_Many_Returned() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single extended timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getExt(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Method returned too many rows using timesheet id, "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Fetch_Single_Extended_Timesheet_DB_Access_Fault() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(new DatabaseException("A database error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getExt(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Database error occurred retrieving single extended timesheet by id, "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Single_Extended_Timesheet_Null_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer nullParm = null;
        try {
            api.getExt(nullParm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Single_Extended_Timesheet_Negative_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = -1000;
        try {
            api.getExt(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative"));
            e.printStackTrace();
        }
    }
        
    @Test
    public void testValidation_Fetch_Single_Extended_Timesheet_Zero_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = 0;
        try {
            api.getExt(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " must be greater than zero"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Fetch_Multiple_Extended_Timesheet() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        TimesheetDto criteria = TimesheetObjectFactory.createTimesheetDtoInstance(null);
        List<TimesheetDto> results = null;
        try {
            criteria.setEmpId(ProjectTrackerMockDataFactory.TEST_EMPLOYEE_ID);
            results = api.getExt(criteria);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TimesheetDto item = results.get(ndx);
            Assert.assertEquals((ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID + ndx), item.getTimesheetId());
            Assert.assertEquals(1110, item.getClientId());
            Assert.assertEquals(1234, item.getProjId());
            Assert.assertEquals(2220, item.getEmpId());
            Assert.assertEquals(("INVREF123" + ndx), item.getInvoiceRefNo());
            Assert.assertEquals(("ExtReNo100" + ndx), item.getExtRef());
            Assert.assertEquals(("Comments" + item.getTimesheetId()), item.getComments());
            Assert.assertEquals(("000000011" + (ndx + 1)), item.getDisplayValue());
            Assert.assertEquals(item.getTimesheetId(), item.getDocumentId());
            
            switch (ndx) {
                case 0:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getStatusEndDate());
                    break;
                case 1:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-08"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-14"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-08"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-14"), item.getStatusEndDate());
                    break;
                    
                case 2:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-15"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-21"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-15"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-21"), item.getStatusEndDate());
                    break;
                case 3:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-22"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-28"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-22"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-28"), item.getStatusEndDate());
                    break;
                case 4:
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-29"), item.getBeginPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-02-04"), item.getEndPeriod());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-01-29"), item.getStatusEffectiveDate());
                    Assert.assertEquals(RMT2Date.stringToDate("2018-02-04"), item.getStatusEndDate());
                    break;
            }
            // Test extended data
            Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_MANAGER_ID, item.getEmployeeManagerId());
            Assert.assertEquals("DRAFT", item.getStatusName());
            Assert.assertEquals("ACCT-111", item.getClientAccountNo());
            Assert.assertEquals(40, item.getBillHrs(), 0);
            Assert.assertEquals(0, item.getNonBillHrs(), 0);
            Assert.assertEquals(70.00, item.getEmployeeHourlyRate(), 0);
            Assert.assertEquals(80.00, item.getEmployeeHourlyOverRate(), 0);
            Assert.assertEquals(TimesheetConst.STATUS_DRAFT, item.getStatusId());
            Assert.assertEquals(item.getStatusName() + "Description", item.getStatusDescription());
            
            Assert.assertEquals(222, item.getEmployeeTypeId());
            Assert.assertEquals(5555, item.getStatusHistId());
            Assert.assertEquals(item.getEmployeeLastname() + ", " + item.getEmployeeFirstname(), item.getEmployeeFullName());
        }
    }
    
    @Test
    public void testError_Fetch_Multiple_Extended_Timesheet_DB_Access_Fault() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(new DatabaseException("A database error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        TimesheetDto criteria = TimesheetObjectFactory.createTimesheetDtoInstance(null);
        try {
            api.getExt(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains(
                    "Database error occurred retrieving extended timesheet(s) using selection criteria object"));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Multiple_Extended_Timesheet_Null_Selection_Criteria() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        TimesheetDto criteria = null;
        try {
            api.getExt(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidTimesheetException);
            Assert.assertEquals("Timesheet selection criteria is required", e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Fetch_Client_Approved() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setClientId(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        mockCriteria.setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
        try {
            // Changed mock data to represent an approved timesheet
            this.mockVwTimesheetSingle.get(0).setStatusName("APPROVED");
            this.mockVwTimesheetSingle.get(0).setStatusDescription("APPROVEDDescription");
            this.mockVwTimesheetSingle.get(0).setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single extended timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        List<TimesheetDto> results = null;
        try {
            results = api.getClientApproved(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TimesheetDto item = results.get(ndx);
            Assert.assertEquals((ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID + ndx), item.getTimesheetId());
            Assert.assertEquals(1110, item.getClientId());
            Assert.assertEquals(1234, item.getProjId());
            Assert.assertEquals(2220, item.getEmpId());
            Assert.assertEquals(("INVREF123" + ndx), item.getInvoiceRefNo());
            Assert.assertEquals(("ExtReNo100" + ndx), item.getExtRef());
            Assert.assertEquals(("Comments" + item.getTimesheetId()), item.getComments());
            Assert.assertEquals(("000000011" + (ndx + 1)), item.getDisplayValue());
            Assert.assertEquals(item.getTimesheetId(), item.getDocumentId());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getBeginPeriod());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getEndPeriod());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), item.getStatusEffectiveDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), item.getStatusEndDate());
            // Test extended data
            Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_MANAGER_ID, item.getEmployeeManagerId());
            Assert.assertEquals("APPROVED", item.getStatusName());
            Assert.assertEquals("ACCT-111", item.getClientAccountNo());
            Assert.assertEquals(40, item.getBillHrs(), 0);
            Assert.assertEquals(0, item.getNonBillHrs(), 0);
            Assert.assertEquals(70.00, item.getEmployeeHourlyRate(), 0);
            Assert.assertEquals(80.00, item.getEmployeeHourlyOverRate(), 0);
            Assert.assertEquals(TimesheetConst.STATUS_APPROVED, item.getStatusId());
            Assert.assertEquals(item.getStatusName() + "Description", item.getStatusDescription());
            
            Assert.assertEquals(222, item.getEmployeeTypeId());
            Assert.assertEquals(5555, item.getStatusHistId());
            Assert.assertEquals(item.getEmployeeLastname() + ", " + item.getEmployeeFirstname(), item.getEmployeeFullName());
        }
    }
    
    @Test
    public void testError_Fetch_Client_Approved_DB_Access_Fault() {
        VwTimesheetList mockCriteria = new VwTimesheetList();
        mockCriteria.setClientId(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        mockCriteria.setTimesheetStatusId(TimesheetConst.STATUS_APPROVED);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(new DatabaseException("DB error occurred"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single extended timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getClientApproved(ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains(
                    "Database error occurred retrieving approved extended timesheet(s) by client id: "
                            + ProjectTrackerMockDataFactory.TEST_CLIENT_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Client_Approved_Null_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getClientApproved(null);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(e.getMessage(), ProjectTrackerApiConst.PARM_NAME_CLIENT_ID + " is required");
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Client_Approved_Negative_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getClientApproved(-1234);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(e.getMessage(), ProjectTrackerApiConst.PARM_NAME_CLIENT_ID + " cannot be negative");
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Client_Approved_Zero_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getClientApproved(0);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(e.getMessage(), ProjectTrackerApiConst.PARM_NAME_CLIENT_ID + " must be greater than zero");
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testSuccess_Fetch_Simple_ProjectTask_By_TimesheetId() {
        ProjProjectTask mockCriteria = new ProjProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjProjectTaskMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project-task case setup failed");
        }

        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        
        List<ProjectTaskDto> results = null;
        try {
            results = api.getProjectTaskByTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectTaskDto obj = results.get(ndx);
            Assert.assertEquals(obj.getProjectTaskId(), (ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + ndx));
            Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
            Assert.assertEquals(obj.getTaskId(), (ProjectTrackerMockDataFactory.TEST_TASK_ID + ndx));
        }
    }
    
    @Test
    public void testError_Fetch_Simple_ProjectTask_By_TimesheetId_DB_Access_Fault() {
        ProjProjectTask mockCriteria = new ProjProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project-task case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getProjectTaskByTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Database error occurred retrieving timesheet project/task(s) by timesheet id: "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Simple_ProjectTask_By_TimesheetId_Null_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer nullParm = null;
        try {
            api.getProjectTaskByTimesheet(nullParm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID 
                    + " is required"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Simple_ProjectTask_By_TimesheetId_Negative_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = -1000;
        try {
            api.getProjectTaskByTimesheet(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID 
                    + " cannot be negative"));
            e.printStackTrace();
        }
    }
        
    @Test
    public void testValidation_Fetch_Simple_ProjectTask_By_TimesheetId_Zero_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = 0;
        try {
            api.getProjectTaskByTimesheet(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID
                            + " must be greater than zero"));
            e.printStackTrace();
        }
    }
    
 
    @Test
    public void testSuccess_Fetch_Extended_ProjectTask_By_TimesheetId() {
        // Stub all project-task fetch.
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project-task case setup failed");
        }

        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        List<ProjectTaskDto> results = null;
        try {
            results = api.getProjectTaskExtByTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectTaskDto obj = results.get(ndx);
            Assert.assertEquals(obj.getProjectTaskId(), (ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + ndx));
            Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
            Assert.assertEquals(obj.getTaskId(), (ProjectTrackerMockDataFactory.TEST_TASK_ID + ndx));
            Assert.assertEquals(obj.getClientId(), ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
            Assert.assertEquals(obj.getProjectDescription(), ProjectTrackerMockDataFactory.TEST_PROJECT_NAME);
            Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-01-01"));
            Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-01-07"));
            Assert.assertEquals(obj.getTaskDescription(), ProjectTrackerMockDataFactory.TEST_TASK_NAMES[ndx]);
            Assert.assertEquals(obj.getTaskBillable().intValue(), (ndx <= 3 ? 1 : 0));
        }
    }
  
    @Test
    public void testError_Fetch_Extended_ProjectTask_By_TimesheetId_DB_Access_Fault() {
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project-task case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getProjectTaskExtByTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains(
                    "Database error occurred retrieving extended timesheet project/task(s) by timesheet id: "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Extended_ProjectTask_By_TimesheetId_Null_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer nullParm = null;
        try {
            api.getProjectTaskExtByTimesheet(nullParm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID 
                    + " is required"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Extended_ProjectTask_By_TimesheetId_Negative_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = -1000;
        try {
            api.getProjectTaskExtByTimesheet(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID 
                    + " cannot be negative"));
            e.printStackTrace();
        }
    }
        
    @Test
    public void testValidation_Fetch_Extended_ProjectTask_By_TimesheetId_Zero_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = 0;
        try {
            api.getProjectTaskExtByTimesheet(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID
                            + " must be greater than zero"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Fetch_ProjectEvent_All() {
        // Stub all  project-event fetch.
        VwTimesheetEventList mockCriteria = new VwTimesheetEventList();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetEventListFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all project-event case setup failed");
        }

        ProjectAdminApi api = ProjectAdminApiFactory.createApi(this.mockDaoClient);
        ProjectEventDto criteria = ProjectObjectFactory.createProjectEventDtoInstance(null);
        List<ProjectEventDto> results = null;
        try {
            results = api.getProjectEvent(criteria);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectEventDto obj = results.get(ndx);
            Assert.assertEquals(obj.getProjectTaskId(), (ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + ndx));
            Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
            Assert.assertEquals(obj.getTaskId(), (ProjectTrackerMockDataFactory.TEST_TASK_ID + ndx));
            Assert.assertEquals(obj.getClientId(), ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
            Assert.assertEquals(obj.getProjectDescription(), ProjectTrackerMockDataFactory.TEST_PROJECT_NAME);
            Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-01-01"));
            Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-01-07"));
            Assert.assertEquals(obj.getTaskDescription(), ProjectTrackerMockDataFactory.TEST_TASK_NAMES[ndx]);
            Assert.assertEquals(obj.getTaskBillable().intValue(), (ndx <= 3 ? 1 : 0));
        }
    }
    
    @Test
    public void testSuccess_Fetch_ProjectEvent_Single() {
        // Stub single project-event fetch.
        VwTimesheetEventList mockCriteria = new VwTimesheetEventList();
        mockCriteria.setEventId(ProjectTrackerMockDataFactory.TEST_EVENT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockVwTimesheetEventListFetchSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single project-event case setup failed");
        }

        ProjectAdminApi api = ProjectAdminApiFactory.createApi(this.mockDaoClient);
        ProjectEventDto criteria = ProjectObjectFactory.createProjectEventDtoInstance(null);
        criteria.setEventId(ProjectTrackerMockDataFactory.TEST_EVENT_ID);
        List<ProjectEventDto> results = null;
        try {
            results = api.getProjectEvent(criteria);
        } catch (ProjectAdminApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ProjectEventDto obj = results.get(0);
        Assert.assertEquals(obj.getProjectTaskId(), ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);
        Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
        Assert.assertEquals(obj.getTaskId(), ProjectTrackerMockDataFactory.TEST_TASK_ID);
        Assert.assertEquals(obj.getClientId(), ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
        Assert.assertEquals(obj.getProjectDescription(), ProjectTrackerMockDataFactory.TEST_PROJECT_NAME);
        Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-01-01"));
        Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-01-07"));
        Assert.assertEquals(obj.getTaskDescription(), ProjectTrackerMockDataFactory.TEST_TASK_NAMES[0]);
        Assert.assertEquals(obj.getTaskBillable().intValue(), 1);
    }
    
    @Test
    public void testSuccess_Fetch_ProjectEvent_By_TimesheetId() {
        VwTimesheetEventList mockCriteria = new VwTimesheetEventList();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                      .thenReturn(this.mockVwTimesheetEventListFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet events case setup failed");
        }

        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        List<ProjectEventDto> results = null;
        try {
            results = api.getEventByTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ProjectEventDto obj = results.get(ndx);
            Assert.assertEquals(obj.getProjectTaskId(), (ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + ndx));
            Assert.assertEquals(obj.getTimesheetId(), ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
            Assert.assertEquals(obj.getProjId(), ProjectTrackerMockDataFactory.TEST_PROJ_ID);
            Assert.assertEquals(obj.getTaskId(), (ProjectTrackerMockDataFactory.TEST_TASK_ID + ndx));
            Assert.assertEquals(obj.getClientId(), ProjectTrackerMockDataFactory.TEST_CLIENT_ID);
            Assert.assertEquals(obj.getProjectDescription(), ProjectTrackerMockDataFactory.TEST_PROJECT_NAME);
            Assert.assertEquals(obj.getProjectEffectiveDate(), RMT2Date.stringToDate("2018-01-01"));
            Assert.assertEquals(obj.getProjectEndDate(), RMT2Date.stringToDate("2018-01-07"));
            Assert.assertEquals(obj.getTaskDescription(), ProjectTrackerMockDataFactory.TEST_TASK_NAMES[ndx]);
            Assert.assertEquals(obj.getTaskBillable().intValue(), (ndx <= 3 ? 1 : 0));
        }
    }
    
    @Test
    public void testError_Fetch_ProjectEvent_By_TimesheetId_DB_Access_Fault() {
        VwTimesheetEventList mockCriteria = new VwTimesheetEventList();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                      .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet events case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getEventByTimesheet(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Database error occurred retrieving timesheet event(s) by timesheet id: "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_ProjectEvent_By_TimesheetId_Null_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = null;
        try {
            api.getEventByTimesheet(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_FetchProjectEvent_By_TimesheetId_Negative_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = -1000;
        try {
            api.getEventByTimesheet(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative"));
            e.printStackTrace();
        }
    }
        
    @Test
    public void testValidation_Fetch_ProjectEvent_By_TimesheetId_Zero_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = 0;
        try {
            api.getEventByTimesheet(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " must be greater than zero"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Fetch_Current_Status() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }

        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        TimesheetHistDto results = null;
        try {
            results = api.getCurrentStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_TIMESHEET_HIST_ID, results.getStatusHistId());
        Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID, results.getTimesheetId());
        Assert.assertEquals(TimesheetConst.STATUS_APPROVED, results.getStatusId());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-09"), results.getStatusEffectiveDate());
        Assert.assertNull(results.getStatusEndDate());
        Assert.assertEquals("testuser", results.getUpdateUserId());
        Assert.assertEquals("1.2.3.4", results.getIpCreated());
        Assert.assertEquals("1.2.3.4", results.getIpUpdated());
    }
   
    @Test
    public void testError_Fetch_Current_Status_Too_Many_Returned() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTimesheetHistMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getCurrentStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Method returned too many rows using timesheet id, "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Fetch_Current_Status_DB_Access_Fault() {
        ProjTimesheetHist mockCriteria = new ProjTimesheetHist();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockCurrentProjTimesheetHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet current history case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getCurrentStatus(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Database error occurred retrieving single timesheet by id, "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Current_Status_Null_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer nullParm = null;
        try {
            api.getCurrentStatus(nullParm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " is required"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Current_Status_Negative_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = -1000;
        try {
            api.getCurrentStatus(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " cannot be negative"));
            e.printStackTrace();
        }
    }
        
    @Test
    public void testValidation_Fetch_Current_Status_Zero_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = 0;
        try {
            api.getCurrentStatus(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID + " must be greater than zero"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Fetch_Timesheet_Total_Hours() {
        VwTimesheetHours mockCriteria = new VwTimesheetHours();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockTimesheetHours);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }

        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        List<TimesheetHoursDto> results = null;
        try {
            results = api.getHours(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        double totalHours = 0;
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TimesheetHoursDto dto = results.get(ndx);
            Assert.assertEquals(111, dto.getTimesheetId());
            Assert.assertEquals(1110, dto.getClientId());
            Assert.assertEquals(4440, dto.getProjId());
            Assert.assertEquals(2220, dto.getEmpId());
            Assert.assertEquals(1112220, dto.getTaskId());
            Assert.assertEquals(123401, dto.getEventId());
            Assert.assertEquals(444441, dto.getProjectTaskId());
            Assert.assertEquals(8, dto.getEventHours(), 0);
            Assert.assertEquals(1, dto.getTaskBillable());
            
            // Accumulate hours worked
            totalHours += dto.getEventHours();
            
            // Verify derived properties
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-0" + (ndx + 1)), dto.getEventDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-0" + (ndx + 1)), dto.getBeginPeriod());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-0" + (ndx + 1)), dto.getProjectEffectiveDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-0" + (ndx + 1)), dto.getDateCreated());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-0" + (ndx + 2)), dto.getEndPeriod());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-0" + (ndx + 2)), dto.getProjectEndDate());
            Assert.assertEquals("InvoiceRefNo" + dto.getTimesheetId(), dto.getInvoiceRefNo());
            Assert.assertEquals("ExtRefNo" + dto.getTimesheetId(), dto.getExtRef());
            Assert.assertEquals(dto.getTimesheetId(), dto.getDocumentId());
            Assert.assertEquals("0000000111", dto.getDisplayValue());
            Assert.assertEquals("ProjectName" + dto.getProjId(), dto.getProjectDescription());
            Assert.assertEquals("TaskName" + dto.getTaskId(), dto.getTaskDescription());
        }

        Assert.assertEquals(40, totalHours, 0);
    }
    
    @Test
    public void testError_Fetch_Timesheet_Total_Hours_DB_Access_Fault() {
        VwTimesheetHours mockCriteria = new VwTimesheetHours();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch timesheet total hours case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.getHours(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Database error occurred retrieving timesheet hours by timesheet id: "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Timesheet_Total_Hours_Null_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer nullParm = null;
        try {
            api.getHours(nullParm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID 
                    + " is required"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Fetch_Timesheet_Total_Hours_Negative_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = -1000;
        try {
            api.getHours(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID 
                    + " cannot be negative"));
            e.printStackTrace();
        }
    }
        
    @Test
    public void testValidation_Fetch_Timesheet_Total_Hours_Zero_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = 0;
        try {
            api.getHours(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID
                            + " must be greater than zero"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSuccess_Load_Timesheet_Graph() {
        // Setup timesheet stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet case setup failed");
        }
        
        // Setup project/task stub
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task case setup failed");
        }
        
        // Setup event stub
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEvent.class)))
                    .thenReturn(createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 1),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 2),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 3),
                            createMockMultiple_Day_Task_Events(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + 4));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all events case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Map<ProjectTaskDto, List<EventDto>> results = null;
        try {
            results = api.load(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        
        List<ProjectTaskDto> ptList = new ArrayList<ProjectTaskDto>(results.keySet());
        int projectTaskNdx = 0;
        double timesheetHourTotal = 0;
        
        for (ProjectTaskDto ptDto : ptList) {
            List<EventDto> eventList = results.get(ptDto);
            Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + projectTaskNdx, ptDto.getProjectTaskId());
            Assert.assertNotNull(eventList);
            
            double eventHourTotal = 0;
            int daySeed = 1;
            for (int eventNdx = 0; eventNdx < eventList.size(); eventNdx++) {
                EventDto event = eventList.get(eventNdx);
                Assert.assertEquals(ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID + projectTaskNdx, event.getProjectTaskId());
                Assert.assertTrue(event.getEventId() > 1000);
                Assert.assertEquals(RMT2Date.stringToDate("2018-01-0" + daySeed), event.getEventDate());
                eventHourTotal += event.getEventHours();
                daySeed++;
            }
            Assert.assertEquals(8, eventHourTotal, 0);
            timesheetHourTotal += eventHourTotal;
            projectTaskNdx++;
        }
        
        Assert.assertEquals(40, timesheetHourTotal, 0);
    }
    
    @Test
    public void testError_Load_Timesheet_Graph_DB_Access_Fault_1() {
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                  .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.load(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Database error occurred retrieving single extended timesheet by id, "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Load_Timesheet_Graph_DB_Access_Fault_2() {
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet case setup failed");
        }
        
        // Setup project/task stub
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
               .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.load(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains(
                    "Database error occurred retrieving extended timesheet project/task(s) by timesheet id: "
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Load_Timesheet_Graph_DB_Access_Fault_3() {
        // Setup timesheet stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet case setup failed");
        }
        
        // Setup project/task stub
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task case setup failed");
        }
        
        // Setup event stub
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEvent.class)))
                .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all events case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.load(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertEquals(e.getMessage(),
                    "Timesheet API load operation failed.  Error occurred fetching events for project/task id, "
                            + ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID);

            Assert.assertEquals(e.getCause().getMessage(),
                    "Database error occurred retrieving event(s) by selection criteria");
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Load_Timesheet_Graph_Timesheet_Not_Found() {
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.load(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NotFoundException);
            Assert.assertTrue(e.getMessage().contains("Failed to build timesheet object graph due to Timesheet ["
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID + "] could not be found"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Load_Timesheet_Graph_ProjectTask_Not_Found() {
        // Setup timesheet stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet case setup failed");
        }
        
        // Setup project/task stub
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.load(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Failed to build timesheet object graph due to Timesheet ["
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID
                            + "] is not assoicated with any project/task items"));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testError_Load_Timesheet_Graph_Event_Not_Found() {
        // Setup timesheet stub
        VwTimesheetList mockTimesheetCriteria = new VwTimesheetList();
        mockTimesheetCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockTimesheetCriteria)))
                   .thenReturn(this.mockVwTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple extended timesheet case setup failed");
        }
        
        // Setup project/task stub
        VwTimesheetProjectTask mockCriteria = new VwTimesheetProjectTask();
        mockCriteria.setTimesheetId(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVwTimesheetProjectTaskFetchMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all timesheet project-task case setup failed");
        }
        
        // Setup event stub
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ProjEvent.class))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all events case setup failed");
        }
        
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        try {
            api.load(ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof TimesheetApiException);
            Assert.assertTrue(e.getMessage().contains("Failed to build timesheet object graph due to Timesheet ["
                            + ProjectTrackerMockDataFactory.TEST_TIMESHEET_ID
                            + "] and Project/Task ["
                            + ProjectTrackerMockDataFactory.TEST_PROJECT_TASK_ID
                            + "] is not assoicated with any event items"));
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Load_Timesheet_Graph_Null_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer nullParm = null;
        try {
            api.load(nullParm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID 
                    + " is required"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_Load_Timesheet_Graph_Negative_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = -1000;
        try {
            api.load(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID 
                    + " cannot be negative"));
            e.printStackTrace();
        }
    }
        
    @Test
    public void testValidation_Load_Timesheet_Graph_Zero_Parameter() {
        
        TimesheetApi api = TimesheetApiFactory.createApi(this.mockDaoClient);
        Integer parm = 0;
        try {
            api.load(parm);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertTrue(e.getMessage().contains(ProjectTrackerApiConst.PARM_NAME_TIMESHEET_ID
                            + " must be greater than zero"));
            e.printStackTrace();
        }
    }
 }