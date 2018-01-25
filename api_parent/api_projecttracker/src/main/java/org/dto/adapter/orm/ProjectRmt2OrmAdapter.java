package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.dao.mapping.orm.rmt2.ProjEmployeeProject;
import org.dao.mapping.orm.rmt2.ProjEvent;
import org.dao.mapping.orm.rmt2.ProjProject;
import org.dao.mapping.orm.rmt2.ProjProjectTask;
import org.dao.mapping.orm.rmt2.ProjTask;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dao.mapping.orm.rmt2.VwProjectClient;
import org.dao.mapping.orm.rmt2.VwTimesheetEventList;
import org.dao.mapping.orm.rmt2.VwTimesheetProjectTask;
import org.dto.ClientDto;
import org.dto.EventDto;
import org.dto.ProjectClientDto;
import org.dto.ProjectDto;
import org.dto.ProjectEmployeeDto;
import org.dto.ProjectEventDto;
import org.dto.ProjectTaskDto;
import org.dto.TaskDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A DTO implementation that adapts data pertaining to the RMT2 ORM objects
 * {@link ProjClient}, {@link ProjEmployeeProject}, {@link ProjProject},
 * {@link ProjProjectTask}, {@link ProjTask}, {@link VwEmployeeProjects},
 * {@link VwProjectClient}, {@link VwTimesheetProjectTask}, and
 * {@link VwTimesheetEventList} .
 * 
 * @author Roy Terrell
 * 
 */
class ProjectRmt2OrmAdapter extends TransactionDtoImpl implements ClientDto,
        ProjectDto, TaskDto, EventDto, ProjectClientDto, ProjectEmployeeDto,
        ProjectTaskDto, ProjectEventDto {

    private ProjClient pc;
    private ProjProject pp;
    private ProjProjectTask ppt;
    private ProjTask pt;
    private VwTimesheetProjectTask vtpt;
    private VwEmployeeProjects vep;
    private VwProjectClient vpc;
    private ProjEvent pe;
    private VwTimesheetEventList vtel;
    private boolean deleteProjectTask;

    /**
     * Creates a ProjectRmt2OrmAdapter object in which all data members are
     * initialized to null.
     */
    ProjectRmt2OrmAdapter() {
        this.pc = null;
        this.pp = null;
        this.pt = null;
        this.ppt = null;
        this.vep = null;
        this.vpc = null;
        this.vtpt = null;
        this.pe = null;
        this.vtel = null;
        return;
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link ProjClient} object.
     * 
     * @param client
     *            an instance of {@link ProjClient}
     */
    protected ProjectRmt2OrmAdapter(ProjClient client) {
        this();
        if (client == null) {
            client = new ProjClient();
        }
        this.pc = client;
        this.setDateCreated(client.getDateCreated());
        this.setDateUpdated(client.getDateUpdated());
        this.setUpdateUserId(client.getUserId());
        return;
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link ProjEmployeeProject} object.
     * 
     * @param empProj
     *            an instance of {@link ProjEmployeeProject}
     */
    protected ProjectRmt2OrmAdapter(ProjEmployeeProject empProj) {
        this();
        if (empProj == null) {
            empProj = new ProjEmployeeProject();
        }
        this.vep = ProjectObjectFactory
                .createOrmExtendedEmployeeProject(empProj);
        // this.pep = empProj;
        this.setDateCreated(empProj.getDateCreated());
        this.setDateUpdated(empProj.getDateUpdated());
        this.setUpdateUserId(empProj.getUserId());
        this.setIpCreated(empProj.getIpCreated());
        this.setIpUpdated(empProj.getIpUpdated());
        return;
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link VwEmployeeProjects} object.
     * 
     * @param empProj
     *            an instance of {@link VwEmployeeProjects}
     */
    protected ProjectRmt2OrmAdapter(VwEmployeeProjects empProj) {
        this();
        if (empProj == null) {
            empProj = new VwEmployeeProjects();
        }
        this.vep = empProj;
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link ProjProject} object.
     * 
     * @param proj
     *            an instance of {@link ProjProject}
     */
    protected ProjectRmt2OrmAdapter(ProjProject proj) {
        this();
        if (proj == null) {
            proj = new ProjProject();
        }
        this.pp = proj;
        this.setDateCreated(proj.getDateCreated());
        this.setDateUpdated(proj.getDateUpdated());
        this.setUpdateUserId(proj.getUserId());
        return;
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link ProjProjectTask} object.
     * 
     * @param projProjectTask
     *            an instance of {@link ProjProjectTask}
     */
    protected ProjectRmt2OrmAdapter(ProjProjectTask projProjectTask) {
        this();
        if (projProjectTask == null) {
            projProjectTask = new ProjProjectTask();
        }
        this.ppt = projProjectTask;
        this.deleteProjectTask = false;
        return;
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link VwTimesheetProjectTask} object.
     * 
     * @param projTask
     *            an instance of {@link VwTimesheetProjectTask}
     */
    protected ProjectRmt2OrmAdapter(VwTimesheetProjectTask projTask) {
        this();
        if (projTask == null) {
            projTask = new VwTimesheetProjectTask();
        }
        this.vtpt = projTask;
        this.deleteProjectTask = false;
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link ProjTask} object.
     * 
     * @param task
     *            an instance of {@link ProjTask}
     */
    protected ProjectRmt2OrmAdapter(ProjTask task) {
        this();
        if (task == null) {
            task = new ProjTask();
        }
        this.pt = task;
        this.setDateCreated(task.getDateCreated());
        this.setDateUpdated(task.getDateUpdated());
        this.setUpdateUserId(task.getUserId());
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link VwProjectClient} object.
     * 
     * @param extProjClient
     *            an instance of {@link VwProjectClient}
     */
    protected ProjectRmt2OrmAdapter(VwProjectClient extProjClient) {
        this();
        if (extProjClient == null) {
            extProjClient = new VwProjectClient();
        }
        this.vpc = extProjClient;
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link ProjEvent} object.
     * 
     * @param event
     *            an instance of {@link ProjEvent}
     */
    protected ProjectRmt2OrmAdapter(ProjEvent event) {
        this();
        if (event == null) {
            event = new ProjEvent();
        }
        this.pe = event;
        this.setDateCreated(event.getDateCreated());
        this.setDateUpdated(event.getDateUpdated());
        this.setUpdateUserId(event.getUserId());
    }

    /**
     * Creates an instance of ProjectRmt2OrmAdapter which adapts a
     * {@link VwTimesheetEventList} object.
     * 
     * @param event
     *            an instance of {@link VwTimesheetEventList}
     */
    protected ProjectRmt2OrmAdapter(VwTimesheetEventList event) {
        this();
        if (event == null) {
            event = new VwTimesheetEventList();
        }
        this.vtel = event;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#setProjEmpEffectiveDate(java.util. Date)
     */
    @Override
    public void setProjEmpEffectiveDate(Date value) {
        this.vep.setProjempEffectiveDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#getProjEmpEffectiveDate()
     */
    @Override
    public Date getProjEmpEffectiveDate() {
        return this.vep.getProjempEffectiveDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#setProjEmpEndDate(java.util.Date)
     */
    @Override
    public void setProjEmpEndDate(Date value) {
        this.vep.setProjempEndDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#getProjEmpEndDate()
     */
    @Override
    public Date getProjEmpEndDate() {
        return this.vep.getProjempEndDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TaskDto#setTaskDescription(java.lang.String)
     */
    @Override
    public void setTaskDescription(String value) {
        if (this.pt != null) {
            this.pt.setDescription(value);
        }
        if (this.vtpt != null) {
            this.vtpt.setTaskName(value);
        }
        if (this.vtel != null) {
            this.vtel.setTaskName(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TaskDto#getTaskDescription()
     */
    @Override
    public String getTaskDescription() {
        if (this.pt != null) {
            return this.pt.getDescription();
        }
        if (this.vtpt != null) {
            return this.vtpt.getTaskName();
        }
        if (this.vtel != null) {
            return this.vtel.getTaskName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TaskDto#setTaskBillable(int)
     */
    @Override
    public void setTaskBillable(int value) {
        if (this.pt != null) {
            this.pt.setBillable(value);
        }
        if (this.vtpt != null) {
            this.vtpt.setBillable(value);
        }
        if (this.vtel != null) {
            this.vtel.setBillable(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TaskDto#getTaskBillable()
     */
    @Override
    public int getTaskBillable() {
        if (this.pt != null) {
            return this.pt.getBillable();
        }
        if (this.vtpt != null) {
            return this.vtpt.getBillable();
        }
        if (this.vtel != null) {
            return this.vtel.getBillable();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectTaskDto#setProjectTaskId(int)
     */
    @Override
    public void setProjectTaskId(int value) {
        if (this.vtpt != null) {
            this.vtpt.setProjectTaskId(value);
        }
        if (this.vtel != null) {
            this.vtel.setProjectTaskId(value);
        }
        if (this.ppt != null) {
            this.ppt.setProjectTaskId(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectTaskDto#getProjectTaskId()
     */
    @Override
    public int getProjectTaskId() {
        if (this.vtpt != null) {
            return this.vtpt.getProjectTaskId();
        }
        if (this.vtel != null) {
            return this.vtel.getProjectTaskId();
        }
        if (this.ppt != null) {
            return this.ppt.getProjectTaskId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectTaskDto#setTaskId(int)
     */
    @Override
    public void setTaskId(int value) {
        if (this.pt != null) {
            this.pt.setTaskId(value);
        }
        if (this.ppt != null) {
            this.ppt.setTaskId(value);
        }
        if (this.vtpt != null) {
            this.vtpt.setTaskId(value);
        }
        if (this.vtel != null) {
            this.vtel.setTaskId(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectTaskDto#getTaskId()
     */
    @Override
    public int getTaskId() {
        if (this.pt != null) {
            return this.pt.getTaskId();
        }
        if (this.ppt != null) {
            return this.ppt.getTaskId();
        }
        if (this.vtpt != null) {
            return this.vtpt.getTaskId();
        }
        if (this.vtel != null) {
            return this.vtel.getTaskId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectTaskDto#setTimesheetId(int)
     */
    @Override
    public void setTimesheetId(int value) {
        if (this.vtpt != null) {
            this.vtpt.setTimesheetId(value);
        }
        if (this.ppt != null) {
            this.ppt.setTimesheetId(value);
        }
        if (this.vtel != null) {
            this.vtel.setTimesheetId(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectTaskDto#getTimesheetId()
     */
    @Override
    public int getTimesheetId() {
        if (this.vtpt != null) {
            return this.vtpt.getTimesheetId();
        }
        if (this.ppt != null) {
            return this.ppt.getTimesheetId();
        }
        if (this.vtel != null) {
            return this.vtel.getTimesheetId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setProjectDescription(java.lang.String)
     */
    @Override
    public void setProjectDescription(String value) {
        if (this.pp != null) {
            this.pp.setDescription(value);
        }
        if (this.vpc != null) {
            this.vpc.setDescription(value);
        }
        if (this.vep != null) {
            this.vep.setProjectName(value);
        }
        if (this.vtpt != null) {
            this.vtpt.setProjectName(value);
        }
        if (this.vtpt != null) {
            this.vtel.setProjectName(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getProjectDescription()
     */
    @Override
    public String getProjectDescription() {
        if (this.pp != null) {
            return this.pp.getDescription();
        }
        if (this.vpc != null) {
            return this.vpc.getDescription();
        }
        if (this.vep != null) {
            return this.vep.getProjectName();
        }
        if (this.vtpt != null) {
            return this.vtpt.getProjectName();
        }
        if (this.vtpt != null) {
            return this.vtel.getProjectName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setProjectEffectiveDate(java.util.Date)
     */
    @Override
    public void setProjectEffectiveDate(Date value) {
        if (this.pp != null) {
            this.pp.setEffectiveDate(value);
        }
        if (this.vpc != null) {
            this.vpc.setEffectiveDate(value);
        }
        if (this.vep != null) {
            this.vep.setProjEffectiveDate(value);
        }
        if (this.vtpt != null) {
            this.vtpt.setEffectiveDate(value);
        }
        if (this.vtel != null) {
            this.vtel.setEffectiveDate(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getProjectEffectiveDate()
     */
    @Override
    public Date getProjectEffectiveDate() {
        if (this.pp != null) {
            return this.pp.getEffectiveDate();
        }
        if (this.vpc != null) {
            return this.vpc.getEffectiveDate();
        }
        if (this.vep != null) {
            return this.vep.getProjEffectiveDate();
        }
        if (this.vtpt != null) {
            return this.vtpt.getEffectiveDate();
        }
        if (this.vtel != null) {
            return this.vtel.getEffectiveDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#setProjectEndDate(java.util.Date)
     */
    @Override
    public void setProjectEndDate(Date value) {
        if (this.pp != null) {
            this.pp.setEndDate(value);
        }
        if (this.vpc != null) {
            this.vpc.setEndDate(value);
        }
        if (this.vep != null) {
            this.vep.setProjEndDate(value);
        }
        if (this.vtpt != null) {
            this.vtpt.setEndDate(value);
        }
        if (this.vtel != null) {
            this.vtel.setEndDate(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectDto#getProjectEndDate()
     */
    @Override
    public Date getProjectEndDate() {
        if (this.pp != null) {
            return this.pp.getEndDate();
        }
        if (this.vpc != null) {
            return this.vpc.getEndDate();
        }
        if (this.vep != null) {
            return this.vep.getProjEndDate();
        }
        if (this.vtpt != null) {
            return this.vtpt.getEndDate();
        }
        if (this.vtel != null) {
            return this.vtel.getEndDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#setEmpProjId(int)
     */
    @Override
    public void setEmpProjId(int value) {
        this.vep.setEmpProjId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#getEmpProjId()
     */
    @Override
    public int getEmpProjId() {
        return this.vep.getEmpProjId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#setEmpId(int)
     */
    @Override
    public void setEmpId(int value) {
        this.vep.setEmpId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#getEmpId()
     */
    @Override
    public int getEmpId() {
        return this.vep.getEmpId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#setProjId(int)
     */
    @Override
    public void setProjId(int value) {
        if (this.vpc != null) {
            this.vpc.setProjId(value);
        }
        if (this.pp != null) {
            this.pp.setProjId(value);
        }
        if (this.ppt != null) {
            this.ppt.setProjId(value);
        }
        if (this.vep != null) {
            this.vep.setProjId(value);
        }
        if (this.vtpt != null) {
            this.vtpt.setProjectId(value);
        }
        if (this.vtel != null) {
            this.vtel.setProjectId(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#getProjId()
     */
    @Override
    public int getProjId() {
        if (this.vpc != null) {
            return this.vpc.getProjId();
        }
        if (this.pp != null) {
            return this.pp.getProjId();
        }
        if (this.ppt != null) {
            return this.ppt.getProjId();
        }
        if (this.vep != null) {
            return this.vep.getProjId();
        }
        if (this.vtpt != null) {
            return this.vtpt.getProjectId();
        }
        if (this.vtel != null) {
            return this.vtel.getProjectId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#setHourlyRate(double)
     */
    @Override
    public void setHourlyRate(double value) {
        this.vep.setPayRate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#getHourlyRate()
     */
    @Override
    public double getHourlyRate() {
        return this.vep.getPayRate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#setHourlyOverRate(double)
     */
    @Override
    public void setHourlyOverRate(double value) {
        this.vep.setOtPayRate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#getHourlyOverRate()
     */
    @Override
    public double getHourlyOverRate() {
        return this.vep.getOtPayRate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#setFlatRate(double)
     */
    @Override
    public void setFlatRate(double value) {
        this.vep.setFlatRate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#getFlatRate()
     */
    @Override
    public double getFlatRate() {
        return this.vep.getFlatRate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#setComments(java.lang.String)
     */
    @Override
    public void setComments(String value) {
        this.vep.setComments(value);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectEmployeeDto#getComments()
     */
    @Override
    public String getComments() {
        return this.vep.getComments();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setClientId(int)
     */
    @Override
    public void setClientId(int value) {
        if (this.pc != null) {
            this.pc.setClientId(value);
        }
        if (this.pp != null) {
            this.pp.setClientId(value);
        }
        if (this.vpc != null) {
            this.vpc.setClientId(value);
        }
        if (this.vep != null) {
            this.vep.setClientId(value);
        }
        if (this.vtpt != null) {
            this.vtpt.setClientId(value);
        }
        if (this.vtel != null) {
            this.vtel.setClientId(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getClientId()
     */
    @Override
    public int getClientId() {
        if (this.pc != null) {
            return this.pc.getClientId();
        }
        if (this.pp != null) {
            return this.pp.getClientId();
        }
        if (this.vpc != null) {
            return this.vpc.getClientId();
        }
        if (this.vep != null) {
            return this.vep.getClientId();
        }
        if (this.vtpt != null) {
            return this.vtpt.getClientId();
        }
        if (this.vtel != null) {
            return this.vtel.getClientId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setBusinessId(int)
     */
    @Override
    public void setBusinessId(int value) {
        if (this.pc != null) {
            this.pc.setBusinessId(value);
        }
        if (this.vpc != null) {
            this.vpc.setBusinessId(value);
        }
        if (this.vep != null) {
            this.vep.setBusinessId(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getBusinessId()
     */
    @Override
    public int getBusinessId() {
        if (this.pc != null) {
            return this.pc.getBusinessId();
        }
        if (this.vpc != null) {
            return this.vpc.getBusinessId();
        }
        if (this.vep != null) {
            return this.vep.getBusinessId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setAccountNo(java.lang.String)
     */
    @Override
    public void setAccountNo(String value) {
        if (this.pc != null) {
            this.pc.setAccountNo(value);
        }
        if (this.vep != null) {
            this.vep.setAccountNo(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getAccountNo()
     */
    @Override
    public String getAccountNo() {
        if (this.pc != null) {
            return this.pc.getAccountNo();
        }
        if (this.vep != null) {
            return this.vep.getAccountNo();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setClientName(java.lang.String)
     */
    @Override
    public void setClientName(String value) {
        if (this.vep != null) {
            this.vep.setClientName(value);
        }
        if (this.vpc != null) {
            this.vpc.setName(value);
        }
        if (this.vep != null) {
            this.vep.setClientName(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getClientName()
     */
    @Override
    public String getClientName() {
        if (this.pc != null) {
            return this.pc.getName();
        }
        if (this.vep != null) {
            return this.vep.getClientName();
        }
        if (this.vpc != null) {
            return this.vpc.getName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setClientBillRate(double)
     */
    @Override
    public void setClientBillRate(double value) {
        if (this.pc != null) {
            this.pc.setBillRate(value);
        }
        if (this.vpc != null) {
            this.vpc.setBillRate(value);
        }
        if (this.vep != null) {
            this.vep.setClientBillRate(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getClientBillRate()
     */
    @Override
    public double getClientBillRate() {
        if (this.pc != null) {
            return this.pc.getBillRate();
        }
        if (this.vpc != null) {
            return this.vpc.getBillRate();
        }
        if (this.vep != null) {
            return this.vep.getClientBillRate();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setClientOtBillRate(double)
     */
    @Override
    public void setClientOtBillRate(double value) {
        if (this.pc != null) {
            this.pc.setOtBillRate(value);
        }
        if (this.vpc != null) {
            this.vpc.setOtBillRate(value);
        }
        if (this.vep != null) {
            this.vep.setClientOtBillRate(value);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getClientOtBillRate()
     */
    @Override
    public double getClientOtBillRate() {
        if (this.pc != null) {
            return this.pc.getOtBillRate();
        }
        if (this.vpc != null) {
            return this.vpc.getOtBillRate();
        }
        if (this.vep != null) {
            return this.vep.getClientOtBillRate();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setContactFirstname(java.lang.String)
     */
    @Override
    public void setClientContactFirstname(String value) {
        this.pc.setContactFirstname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getClientContactFirstname()
     */
    @Override
    public String getClientContactFirstname() {
        return this.pc.getContactFirstname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setClientContactLastname(java.lang.String)
     */
    @Override
    public void setClientContactLastname(String value) {
        this.pc.setContactLastname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getClientContactLastname()
     */
    @Override
    public String getClientContactLastname() {
        return this.pc.getContactLastname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setClientContactPhone(java.lang.String)
     */
    @Override
    public void setClientContactPhone(String value) {
        this.pc.setContactPhone(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getClientContactPhone()
     */
    @Override
    public String getClientContactPhone() {
        return this.pc.getContactPhone();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setClientContactExt(java.lang.String)
     */
    @Override
    public void setClientContactExt(String value) {
        this.pc.setContactExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getClientContactExt()
     */
    @Override
    public String getClientContactExt() {
        return this.pc.getContactExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#setClientContactEmail(java.lang.String)
     */
    @Override
    public void setClientContactEmail(String value) {
        this.pc.setContactEmail(value);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ClientDto#getClientContactEmail()
     */
    @Override
    public String getClientContactEmail() {
        return this.pc.getContactEmail();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EventDto#setEventDate(java.util.Date)
     */
    @Override
    public void setEventDate(Date value) {
        if (this.pe != null) {
            this.pe.setEventDate(value);
        }
        if (this.vtel != null) {
            this.vtel.setEventDate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EventDto#getEventDate()
     */
    @Override
    public Date getEventDate() {
        if (this.pe != null) {
            return this.pe.getEventDate();
        }
        if (this.vtel != null) {
            return this.vtel.getEventDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EventDto#setEventHours(double)
     */
    @Override
    public void setEventHours(double value) {
        if (this.pe != null) {
            this.pe.setHours(value);
        }
        if (this.vtel != null) {
            this.vtel.setHours(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EventDto#getEventHours()
     */
    @Override
    public double getEventHours() {
        if (this.pe != null) {
            return this.pe.getHours();
        }
        if (this.vtel != null) {
            return this.vtel.getHours();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EventDto#setEventId(int)
     */
    @Override
    public void setEventId(int value) {
        if (this.pe != null) {
            this.pe.setEventId(value);
        }
        if (this.vtel != null) {
            this.vtel.setEventId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EventDto#getEventId()
     */
    @Override
    public int getEventId() {
        if (this.pe != null) {
            return this.pe.getEventId();
        }
        if (this.vtel != null) {
            return this.vtel.getEventId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectTaskDto#setDeleteFlag(boolean)
     */
    @Override
    public void setDeleteFlag(boolean value) {
        this.deleteProjectTask = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ProjectTaskDto#isDeleteFlag()
     */
    @Override
    public boolean isDeleteFlag() {
        return this.deleteProjectTask;
    }
}
