package org.rmt2.entity.adapter;

import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.timesheet.TimesheetConst;
import org.dto.TimesheetDto;
import org.dto.adapter.orm.TimesheetObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.util.RMT2Date;

/**
 * Test adapters pertaining to the Timesheet module.
 * 
 * @author roy.terrell
 *
 */
public class TimesheetModuleAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrmProjTimesheet() {
        ProjTimesheet o = ProjectTrackerMockDataFactory.createMockOrmProjTimesheet(111, 1110, 1234, 2220, "INVREF1230",
                        "2018-01-01", "2018-01-07", "ExtReNo1000");
        TimesheetDto dto = TimesheetObjectFactory.createTimesheetDtoInstance(o);
        
        Assert.assertEquals(111, dto.getTimesheetId());
        Assert.assertEquals(1110, dto.getClientId());
        Assert.assertEquals(1234, dto.getProjId());
        Assert.assertEquals(2220, dto.getEmpId());
        Assert.assertEquals("INVREF1230", dto.getInvoiceRefNo());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getBeginPeriod());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), dto.getEndPeriod());
        Assert.assertEquals("ExtReNo1000", dto.getExtRef());
        Assert.assertEquals("Comments" + dto.getTimesheetId(), dto.getComments());
        Assert.assertEquals("0000000111", dto.getDisplayValue());
        Assert.assertEquals(dto.getTimesheetId(), dto.getDocumentId());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getDateCreated());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), dto.getDateUpdated());
        Assert.assertEquals("testuser", dto.getUpdateUserId());
        Assert.assertEquals("1.2.3.4", dto.getIpCreated());
        Assert.assertEquals("1.2.3.4", dto.getIpUpdated());
    }
    
    @Test
    public void testOrmVwTimesheetList() {
        VwTimesheetList o = ProjectTrackerMockDataFactory
                .createMockOrmVwTimesheetList(111, 1110, 1234, 2220,
                        "INVREF1230", "2018-01-01", "2018-01-07", "ExtReNo1000",
                        3330, "DRAFT", "ACCT-111", 40, 0, 70.00, 80.00);
        TimesheetDto dto = TimesheetObjectFactory.createTimesheetExtendedDtoInstance(o);
        
        Assert.assertEquals(111, dto.getTimesheetId());
        Assert.assertEquals(1110, dto.getClientId());
        Assert.assertEquals(1234, dto.getProjId());
        Assert.assertEquals(2220, dto.getEmpId());
        Assert.assertEquals("INVREF1230", dto.getInvoiceRefNo());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getBeginPeriod());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), dto.getEndPeriod());
        Assert.assertEquals("ExtReNo1000", dto.getExtRef());
        Assert.assertEquals("Comments" + dto.getTimesheetId(), dto.getComments());
        Assert.assertEquals("0000000111", dto.getDisplayValue());
        Assert.assertEquals(dto.getTimesheetId(), dto.getDocumentId());
        Assert.assertNull(dto.getDateCreated());
        Assert.assertNull(dto.getDateUpdated());
        Assert.assertNull(dto.getUpdateUserId());
        Assert.assertNull(dto.getIpCreated());
        Assert.assertNull(dto.getIpUpdated());
        Assert.assertEquals(3330, dto.getEmployeeManagerId());
        Assert.assertEquals("DRAFT", dto.getStatusName());
        Assert.assertEquals("ACCT-111", dto.getClientAccountNo());
        Assert.assertEquals(40, dto.getBillHrs(), 0);
        Assert.assertEquals(0, dto.getNonBillHrs(), 0);
        Assert.assertEquals(70.00, dto.getEmployeeHourlyRate(), 0);
        Assert.assertEquals(80.00, dto.getEmployeeHourlyOverRate(), 0);
        Assert.assertEquals(TimesheetConst.STATUS_DRAFT, dto.getStatusId());
        Assert.assertEquals(dto.getStatusName() + "Description", dto.getStatusDescription());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getStatusEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-07"), dto.getStatusEndDate());
        Assert.assertEquals(222, dto.getEmployeeTypeId());
        Assert.assertEquals(5555, dto.getStatusHistId());
        Assert.assertEquals(dto.getEmployeeLastname() + ", " + dto.getEmployeeFirstname(), dto.getEmployeeFullName());
    }
}
