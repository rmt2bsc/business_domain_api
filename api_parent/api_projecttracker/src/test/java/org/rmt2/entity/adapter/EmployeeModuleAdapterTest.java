package org.rmt2.entity.adapter;

import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dto.EmployeeDto;
import org.dto.EmployeeTitleDto;
import org.dto.ProjectEmployeeDto;
import org.dto.adapter.orm.EmployeeObjectFactory;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.util.RMT2Date;

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
    public void testOrmProjEmployeeTitle() {
        ProjEmployeeTitle o = ProjectTrackerMockDataFactory.createMockOrmProjEmployeeTitle(101, "Employee Title 1");
        EmployeeTitleDto dto = EmployeeObjectFactory.createEmployeeTitleDtoInstance(o);
        
        Assert.assertEquals(101, dto.getEmployeeTitleId());
        Assert.assertEquals("Employee Title 1", dto.getEmployeeTitleDescription());
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
    }
    
 
}
