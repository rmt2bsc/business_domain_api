package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.dao.mapping.orm.rmt2.VwEmployeeExt;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dto.EmployeeDto;
import org.dto.EmployeeTitleDto;
import org.dto.EmployeeTypeDto;
import org.dto.ProjectEmployeeDto;
import org.dto.adapter.orm.EmployeeObjectFactory;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.api.util.RMT2Date;

/**
 * Test adapters pertaining to the Employee module.
 * 
 * @author roy.terrell
 *
 */
public class EmployeeModuleAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrmProjEmployeeTypes() {
        ProjEmployeeType o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeType(201, "Employee Type 1");
        EmployeeTypeDto dto = EmployeeObjectFactory.createEmployeeTypeDtoInstance(o);
        
        Assert.assertEquals(201, dto.getEmployeeTypeId());
        Assert.assertEquals("Employee Type 1", dto.getEmployeeTypeDescription());
        
        try {
            dto = EmployeeObjectFactory.createEmployeeTypeDtoInstance(null);
            dto.setEmployeeTypeId(201);
            dto.setEmployeeTypeDescription("Employee Type 1");
            
            Assert.assertEquals(201, dto.getEmployeeTypeId());
            Assert.assertEquals("Employee Type 1", dto.getEmployeeTypeDescription());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ProjEmployeeTypes Adapater");
        }
    }
    
    @Test
    public void testOrmProjEmployeeTitle() {
        ProjEmployeeTitle o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        EmployeeTitleDto dto = EmployeeObjectFactory.createEmployeeTitleDtoInstance(o);
        
        Assert.assertEquals(101, dto.getEmployeeTitleId());
        Assert.assertEquals("Employee Title 1", dto.getEmployeeTitleDescription());
        
        try {
            dto = EmployeeObjectFactory.createEmployeeTitleDtoInstance(null);
            dto.setEmployeeTitleId(101);
            dto.setEmployeeTitleDescription("Employee Title 1");
            
            Assert.assertEquals(101, dto.getEmployeeTitleId());
            Assert.assertEquals("Employee Title 1", dto.getEmployeeTitleDescription());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ProjEmployeeTitle Adapater");
        }
    }
    
    @Test
    public void testOrmProjEmployee() {
        ProjEmployee o1 = ProjectTrackerMockDataFactory.createMockOrmProjEmployee(5000, 201, 1, 3333, 101, 999991,
                "2010-01-01", "2018-01-01", "login_name_1", "first_name_1", "last_name_1",
                "111-11-5000", "ABC Company");
        EmployeeDto dto = EmployeeObjectFactory.createEmployeeDtoInstance(o1);
        
        Assert.assertEquals(dto.getEmployeeId(), 5000);
        Assert.assertEquals(dto.getManagerId(), 3333);
        Assert.assertEquals(dto.getEmployeeTitleId(), 101);
        Assert.assertEquals(dto.getLoginId(), 999991);
        Assert.assertEquals(RMT2Date.stringToDate("2010-01-01"), dto.getStartDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getTerminationDate());
        Assert.assertEquals(dto.getEmployeeTypeId(), 201);
        Assert.assertEquals(dto.getIsManager(), 1);
        Assert.assertEquals(dto.getEmployeeFirstname(), "first_name_1");
        Assert.assertEquals(dto.getEmployeeLastname(), "last_name_1");
        Assert.assertEquals("111-11-5000", dto.getSsn());
        Assert.assertEquals("ABC Company", dto.getEmployeeCompanyName());
        
        try {
            dto = EmployeeObjectFactory.createEmployeeDtoInstance(null);
            dto.setEmployeeId(5000);
            dto.setManagerId(3333);
            dto.setEmployeeTitleId(101);
            dto.setLoginId(999991);
            dto.setStartDate(RMT2Date.stringToDate("2010-01-01"));
            dto.setTerminationDate(RMT2Date.stringToDate("2018-01-01"));
            dto.setEmployeeTypeId(201);
            dto.setIsManager(1);
            dto.setEmployeeFirstname("first_name_1");
            dto.setEmployeeLastname("last_name_1");
            dto.setSsn("111-11-5000");
            dto.setEmployeeCompanyName("ABC Company");
            
            Assert.assertEquals(dto.getEmployeeId(), 5000);
            Assert.assertEquals(dto.getManagerId(), 3333);
            Assert.assertEquals(dto.getEmployeeTitleId(), 101);
            Assert.assertEquals(dto.getLoginId(), 999991);
            Assert.assertEquals(RMT2Date.stringToDate("2010-01-01"), dto.getStartDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getTerminationDate());
            Assert.assertEquals(dto.getEmployeeTypeId(), 201);
            Assert.assertEquals(dto.getIsManager(), 1);
            Assert.assertEquals(dto.getEmployeeFirstname(), "first_name_1");
            Assert.assertEquals(dto.getEmployeeLastname(), "last_name_1");
            Assert.assertEquals("111-11-5000", dto.getSsn());
            Assert.assertEquals("ABC Company", dto.getEmployeeCompanyName());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ProjEmployee Adapater");
        }
    }
    
    @Test
    public void testOrmVMEmployeeExt() {
        VwEmployeeExt o1 = ProjectTrackerMockDataFactory.createMockOrmExtEmployee(5000, "EmployeeType", 1, 3333, "EmployeeTitle", 999991,
                "2010-01-01", "2018-01-01", "login_name_1", "first_name_1", "last_name_1",
                "111-11-5000", "ABC Company");
        EmployeeDto dto = EmployeeObjectFactory.createEmployeeExtendedDtoInstance(o1);
        
        Assert.assertEquals(dto.getEmployeeId(), 5000);
        Assert.assertEquals(dto.getManagerId(), 3333);
        Assert.assertEquals(dto.getEmployeeTitle(), "EmployeeTitle");
        Assert.assertEquals(dto.getLoginId(), 999991);
        Assert.assertEquals(RMT2Date.stringToDate("2010-01-01"), dto.getStartDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getTerminationDate());
        Assert.assertEquals(dto.getEmployeeType(), "EmployeeType");
        Assert.assertEquals(dto.getIsManager(), 1);
        Assert.assertEquals(dto.getEmployeeFirstname(), "first_name_1");
        Assert.assertEquals(dto.getEmployeeLastname(), "last_name_1");
        Assert.assertEquals("111-11-5000", dto.getSsn());
        Assert.assertEquals("ABC Company", dto.getEmployeeCompanyName());
        
        try {
            dto = EmployeeObjectFactory.createEmployeeExtendedDtoInstance(null);
            dto.setEmployeeId(5000);
            dto.setManagerId(3333);
            dto.setEmployeeTitle("EmployeeTitle");
            dto.setLoginId(999991);
            dto.setStartDate(RMT2Date.stringToDate("2010-01-01"));
            dto.setTerminationDate(RMT2Date.stringToDate("2018-01-01"));
            dto.setEmployeeType("EmployeeType");
            dto.setIsManager(1);
            dto.setEmployeeFirstname("first_name_1");
            dto.setEmployeeLastname("last_name_1");
            dto.setSsn("111-11-5000");
            dto.setEmployeeCompanyName("ABC Company");
            
            Assert.assertEquals(dto.getEmployeeId(), 5000);
            Assert.assertEquals(dto.getManagerId(), 3333);
            Assert.assertEquals(dto.getEmployeeTitle(), "EmployeeTitle");
            Assert.assertEquals(dto.getLoginId(), 999991);
            Assert.assertEquals(RMT2Date.stringToDate("2010-01-01"), dto.getStartDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getTerminationDate());
            Assert.assertEquals(dto.getEmployeeType(), "EmployeeType");
            Assert.assertEquals(dto.getIsManager(), 1);
            Assert.assertEquals(dto.getEmployeeFirstname(), "first_name_1");
            Assert.assertEquals(dto.getEmployeeLastname(), "last_name_1");
            Assert.assertEquals("111-11-5000", dto.getSsn());
            Assert.assertEquals("ABC Company", dto.getEmployeeCompanyName());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ProjEmployee Adapater");
        }
    }
    
    @Test
    public void testOrmVwEmployeeProjects() {
        VwEmployeeProjects o1 = ProjectTrackerMockDataFactory.createMockOrmVwEmployeeProjects(55551, 2220, "Project 2220",
                1000, "1000 Company", 1350, "000-111", 5000,
                "2018-01-01", "2018-02-01", "2018-01-01", "2018-02-01",
                50.00, 55.00, 0.00, 70.00, 80.00);
        ProjectEmployeeDto dto = ProjectObjectFactory.createEmployeeProjectDtoInstance(o1);
        
        Assert.assertEquals(55551, dto.getEmpProjId());
        Assert.assertEquals(5000, dto.getEmpId());
        Assert.assertEquals(2220, dto.getProjId());
        Assert.assertEquals(1000, dto.getClientId());
        Assert.assertEquals(1350, dto.getBusinessId());
        Assert.assertEquals("000-111", dto.getAccountNo());
        Assert.assertEquals("1000 Company", dto.getClientName());
        Assert.assertEquals("Project 2220", dto.getProjectDescription());
        Assert.assertEquals(70.00, dto.getClientBillRate(), 0);
        Assert.assertEquals(80.00, dto.getClientOtBillRate(), 0);
        Assert.assertEquals(50.00, dto.getHourlyRate(), 0);
        Assert.assertEquals(55.00, dto.getHourlyOverRate(), 0);
        Assert.assertEquals(0.00, dto.getFlatRate(), 0);
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getProjectEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-02-01"), dto.getProjectEndDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getProjEmpEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-02-01"), dto.getProjEmpEndDate());
        
        try {
            dto = ProjectObjectFactory.createEmployeeProjectDtoInstance(null);
            dto.setEmpProjId(55551);
            dto.setEmpId(5000);
            dto.setProjId(2220);
            dto.setClientId(1000);
            dto.setBusinessId(1350);
            dto.setAccountNo("000-111");
            dto.setClientName("1000 Company");
            dto.setProjectDescription("Project 2220");
            dto.setClientBillRate(70.00);
            dto.setClientOtBillRate(80.00);
            dto.setHourlyRate(50.00);
            dto.setHourlyOverRate(55.00);
            dto.setFlatRate(0.00);
            dto.setProjectEffectiveDate(RMT2Date.stringToDate("2018-01-01"));
            dto.setProjectEndDate(RMT2Date.stringToDate("2018-02-01"));
            dto.setProjEmpEffectiveDate(RMT2Date.stringToDate("2018-01-01"));
            dto.setProjEmpEndDate(RMT2Date.stringToDate("2018-02-01"));
            
            Assert.assertEquals(55551, dto.getEmpProjId());
            Assert.assertEquals(5000, dto.getEmpId());
            Assert.assertEquals(2220, dto.getProjId());
            Assert.assertEquals(1000, dto.getClientId());
            Assert.assertEquals(1350, dto.getBusinessId());
            Assert.assertEquals("000-111", dto.getAccountNo());
            Assert.assertEquals("1000 Company", dto.getClientName());
            Assert.assertEquals("Project 2220", dto.getProjectDescription());
            Assert.assertEquals(70.00, dto.getClientBillRate(), 0);
            Assert.assertEquals(80.00, dto.getClientOtBillRate(), 0);
            Assert.assertEquals(50.00, dto.getHourlyRate(), 0);
            Assert.assertEquals(55.00, dto.getHourlyOverRate(), 0);
            Assert.assertEquals(0.00, dto.getFlatRate(), 0);
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getProjectEffectiveDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-02-01"), dto.getProjectEndDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getProjEmpEffectiveDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-02-01"), dto.getProjEmpEndDate());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ProjectEmployee Adapater");
        }
    }
    
 
}
