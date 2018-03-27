package org.rmt2.api.timesheet;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dto.TimesheetDto;
import org.dto.adapter.orm.TimesheetObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.timesheet.TimesheetApi;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.ProjectTrackerMockData;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2Date;

/**
 * Test the query functionality of the Timesheet module of the Project Tracker Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TimesheetQueryApiTest extends ProjectTrackerMockData {
    
    private static final int TEST_TIMESHEET_ID = 111;
    private static final int TEST_CLIENT_ID = 1110;
    private static final int TEST_EMPLOYEE_ID = 2220;
    private static final int TEST_BUSINESS_ID = 1350;
    
    private static final int TEST_EMPLOYEE_TITLE_ID = 101;
    private static final int TEST_MANAGER_ID = 3333;
    
    private static final int TEST_PROJ_ID = 2220;
    private static final int TEST_EMP_PROJ_ID = 55551;
    private static final String TEST_COMPANY_NAME = "ABC Company";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
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
        mockCriteria.setTimesheetId(TEST_TIMESHEET_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTimesheetSingle);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single timesheet case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        TimesheetDto results = null;
        try {
            results = api.get(TEST_TIMESHEET_ID);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getTimesheetId(), TEST_TIMESHEET_ID);
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
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), results.getDateCreated());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), results.getDateUpdated());
        Assert.assertEquals("testuser", results.getUpdateUserId());
        Assert.assertEquals("1.2.3.4", results.getIpCreated());
        Assert.assertEquals("1.2.3.4", results.getIpUpdated());
    }
    
    @Test
    public void testSuccess_Fetch_Multiple_Timesheet() {
        ProjTimesheet mockCriteria = new ProjTimesheet();
        mockCriteria.setEmpId(2220);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockProjTimesheetMultiple);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch multiple timesheet case setup failed");
        }
        
        TimesheetApiFactory f = new TimesheetApiFactory();
        TimesheetApi api = f.createApi(this.mockDaoClient);
        TimesheetDto criteria = TimesheetObjectFactory.createTimesheetDtoInstance(null);
        List<TimesheetDto> results = null;
        try {
            criteria.setEmpId(TEST_EMPLOYEE_ID);
            results = api.get(criteria);
        } catch (TimesheetApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TimesheetDto item = results.get(ndx);
            Assert.assertEquals((TEST_TIMESHEET_ID + ndx), item.getTimesheetId());
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
    
    
   }