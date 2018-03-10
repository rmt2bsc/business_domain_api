package org.dto.adapter.orm;

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

import com.RMT2Base;

/**
 * A factory containing several methods to create adapters and converters for
 * obtaining client, project, and task reltated DTO's.
 * 
 * @author Roy Terrell.
 * 
 */
public class ProjectObjectFactory extends RMT2Base {

    /**
     * Create an instance of <i>ClientDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjClient}
     * 
     * @return an instance of {@link ClientDto}.
     */
    public static final ClientDto createClientDtoInstance(ProjClient ormBean) {
        return new ProjectRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ProjectDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjProject}
     * 
     * @return an instance of {@link ProjectDto}.
     */
    public static final ProjectDto createProjectDtoInstance(ProjProject ormBean) {
        return new ProjectRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>TaskDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjTask}
     * 
     * @return an instance of {@link TaskDto}.
     */
    public static final TaskDto createTaskDtoInstance(ProjTask ormBean) {
        return new ProjectRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ProjectClientDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link VwProjectClient}
     * 
     * @return an instance of {@link ProjectClientDto}.
     */
    public static final ProjectClientDto createProjectClientDtoInstance(
            VwProjectClient ormBean) {
        return new ProjectRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ProjectTaskDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjProjectTask}
     * 
     * @return an instance of {@link ProjectTaskDto}.
     */
    public static final ProjectTaskDto createProjectTaskDtoInstance(
            ProjProjectTask ormBean) {
        return new ProjectRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ProjectTaskDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link VwTimesheetProjectTask}
     * 
     * @return an instance of {@link ProjectTaskDto}.
     */
    public static final ProjectTaskDto createProjectTaskExtendedDtoInstance(
            VwTimesheetProjectTask ormBean) {
        return new ProjectRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ProjectEmployeeDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link VwEmployeeProjects}
     * 
     * @return an instance of {@link ProjectEmployeeDto}.
     */
    public static final ProjectEmployeeDto createEmployeeProjectDtoInstance(
            VwEmployeeProjects ormBean) {
        return new ProjectRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>EventDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjEvent}
     * 
     * @return an instance of {@link EventDto}.
     */
    public static final EventDto createEventDtoInstance(ProjEvent ormBean) {
        return new ProjectRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ProjectEventDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link VwTimesheetEventList}
     * 
     * @return an instance of {@link ProjectEventDto}.
     */
    public static final ProjectEventDto createProjectEventDtoInstance(VwTimesheetEventList ormBean) {
        return new ProjectRmt2OrmAdapter(ormBean);
    }

    /**
     * Create a <i>VwTimesheetProjectTask</i> object from a
     * <i>ProjProjectTask</i> object.
     * 
     * @param src
     *            an instnace of {@link ProjProjectTask}
     * @return an instance of {@link VwTimesheetProjectTask}
     */
    public static VwTimesheetProjectTask createOrmExtendedProjectTask(
            ProjProjectTask src) {
        VwTimesheetProjectTask pt = new VwTimesheetProjectTask();
        pt.setProjectTaskId(src.getProjectTaskId());
        pt.setTimesheetId(src.getTimesheetId());
        pt.setProjectId(src.getProjId());
        pt.setTaskId(src.getTaskId());
        return pt;
    }

    /**
     * Create a <i>VwEmployeeProjects</i> object from a
     * <i>ProjEmployeeProject</i> object.
     * 
     * @param src
     *            an instnace of {@link ProjEmployeeProject}
     * @return an instance of {@link VwEmployeeProjects}
     */
    public static VwEmployeeProjects createOrmExtendedEmployeeProject(
            ProjEmployeeProject src) {
        VwEmployeeProjects ep = new VwEmployeeProjects();
        ep.setEmpProjId(src.getEmpProjId());
        ep.setEmpId(src.getEmpId());
        ep.setProjId(src.getProjId());
        ep.setProjempEffectiveDate(src.getEffectiveDate());
        ep.setProjempEndDate(src.getEndDate());
        ep.setPayRate(src.getHourlyRate());
        ep.setOtPayRate(src.getHourlyOverRate());
        ep.setFlatRate(src.getFlatRate());
        ep.setComments(src.getComments());
        return ep;
    }

    /**
     * Create a <i>VwTimesheetEventList</i> object from a <i>ProjEvent</i>
     * object.
     * 
     * @param src
     *            an instnace of {@link ProjEvent}
     * @return an instance of {@link VwTimesheetEventList}
     */
    public static VwTimesheetEventList createOrmProjectEvent(ProjEvent src) {
        VwTimesheetEventList vtel = new VwTimesheetEventList();
        vtel.setProjectTaskId(src.getProjectTaskId());
        vtel.setEventId(src.getEventId());
        vtel.setHours(src.getHours());
        vtel.setEventDateCreated(src.getEventDate());
        return vtel;
    }

}
