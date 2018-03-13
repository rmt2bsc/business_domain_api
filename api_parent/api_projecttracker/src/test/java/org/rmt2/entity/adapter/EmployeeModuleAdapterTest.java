package org.rmt2.entity.adapter;

import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dto.ProjectEmployeeDto;
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
