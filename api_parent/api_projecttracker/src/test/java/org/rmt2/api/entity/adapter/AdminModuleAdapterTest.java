package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjProject;
import org.dao.mapping.orm.rmt2.ProjTask;
import org.dto.ClientDto;
import org.dto.Project2Dto;
import org.dto.TaskDto;
import org.dto.adapter.orm.ProjectObjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

import com.api.util.RMT2Date;

/**
 * Test adapters pertaining to the Project Administation module.
 * 
 * @author roy.terrell
 *
 */
public class AdminModuleAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrmProjClient() {
        ProjClient o1 = ProjectTrackerMockDataFactory.createMockOrmProjClient(
                1000, 1350, "CompanyName", 70.00, 80.00, "acctNo", "steve",
                "gadd", "0000000000", "stevegadd@gte.net");
        ClientDto dto = ProjectObjectFactory.createClientDtoInstance(o1);
        
        Assert.assertEquals(1000, dto.getClientId());
        Assert.assertEquals(1350, dto.getBusinessId());
        Assert.assertEquals("acctNo", dto.getAccountNo());
        Assert.assertEquals("CompanyName", dto.getClientName());
        Assert.assertEquals(70.00, dto.getClientBillRate(), 0);
        Assert.assertEquals(80.00, dto.getClientOtBillRate(), 0);
        Assert.assertEquals("steve", dto.getClientContactFirstname());
        Assert.assertEquals("gadd", dto.getClientContactLastname());
        Assert.assertEquals("0000000000", dto.getClientContactPhone());
        Assert.assertEquals("stevegadd@gte.net", dto.getClientContactEmail());
        
        try {
            dto = ProjectObjectFactory.createClientDtoInstance(null);
            dto.setClientId(1000);
            dto.setBusinessId(1350);
            dto.setAccountNo("acctNo");
            dto.setClientName("CompanyName");
            dto.setClientBillRate(70.00);
            dto.setClientOtBillRate(80.00);
            dto.setClientContactFirstname("steve");
            dto.setClientContactLastname("gadd");
            dto.setClientContactPhone("0000000000");
            dto.setClientContactEmail("stevegadd@gte.net");
            
            Assert.assertEquals(1000, dto.getClientId());
            Assert.assertEquals(1350, dto.getBusinessId());
            Assert.assertEquals("acctNo", dto.getAccountNo());
            Assert.assertEquals("CompanyName", dto.getClientName());
            Assert.assertEquals(70.00, dto.getClientBillRate(), 0);
            Assert.assertEquals(80.00, dto.getClientOtBillRate(), 0);
            Assert.assertEquals("steve", dto.getClientContactFirstname());
            Assert.assertEquals("gadd", dto.getClientContactLastname());
            Assert.assertEquals("0000000000", dto.getClientContactPhone());
            Assert.assertEquals("stevegadd@gte.net", dto.getClientContactEmail());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ProjClient Adapater");
        }
    }
    
    @Test
    public void testOrmProjProject() {
        ProjProject o1 = ProjectTrackerMockDataFactory.createMockOrmProjProject(1000, 2000, 
                "ProjectDescription", "2018-01-01", "2018-02-02");
        Project2Dto dto = ProjectObjectFactory.createProjectDtoInstance(o1);
        
        Assert.assertEquals(1000, dto.getProjId());
        Assert.assertEquals(2000, dto.getClientId());
        Assert.assertEquals("ProjectDescription", dto.getProjectDescription());
        Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getProjectEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2018-02-02"), dto.getProjectEndDate());
        
        try {
            dto = ProjectObjectFactory.createProjectDtoInstance(null);
            dto.setProjId(1000);
            dto.setClientId(2000);
            dto.setProjectDescription("ProjectDescription");
            dto.setProjectEffectiveDate(RMT2Date.stringToDate("2018-01-01"));
            dto.setProjectEndDate(RMT2Date.stringToDate("2018-02-02"));
            
            Assert.assertEquals(1000, dto.getProjId());
            Assert.assertEquals(2000, dto.getClientId());
            Assert.assertEquals("ProjectDescription", dto.getProjectDescription());
            Assert.assertEquals(RMT2Date.stringToDate("2018-01-01"), dto.getProjectEffectiveDate());
            Assert.assertEquals(RMT2Date.stringToDate("2018-02-02"), dto.getProjectEndDate());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ProjProject Adapater");
        }
    }
    
    @Test
    public void testOrmProjTask() {
        ProjTask o1 = ProjectTrackerMockDataFactory.createMockOrmProjTask(1000, "TaskDescription", true);
        TaskDto dto = ProjectObjectFactory.createTaskDtoInstance(o1);
        
        Assert.assertEquals(1000, dto.getTaskId());
        Assert.assertEquals("TaskDescription", dto.getTaskDescription());
        Assert.assertEquals(1, dto.getTaskBillable());
        
        try {
            dto = ProjectObjectFactory.createTaskDtoInstance(null);
            dto.setTaskId(1000);
            dto.setTaskDescription("TaskDescription");
            dto.setTaskBillable(1);
            
            Assert.assertEquals(1000, dto.getTaskId());
            Assert.assertEquals("TaskDescription", dto.getTaskDescription());
            Assert.assertEquals(1, dto.getTaskBillable());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ProjTask Adapater");
        }
    }
}
