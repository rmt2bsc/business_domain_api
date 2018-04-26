package org.modules.services.document.directory;

import com.RMT2Base;

/**
 * A bean representing the application's module configuration.
 * 
 * @author appdev
 * 
 */
public class ApplicationModuleBean extends RMT2Base {
    private String projectName;
    
    private String moduleCode;
    
    private String moduleName;

    private String entityUid;

    private String filePattern;

    /**
     * Creates an empty AppModuleConfig object
     */
    protected ApplicationModuleBean() {
        return;
    }

    /**
     * Creates a AppModuleConfig object containing the module code
     * 
     * @param moduleCode
     *            The module code
     */
    protected ApplicationModuleBean(String moduleCode) {
        this.moduleCode = moduleCode;
        return;
    }

    /**
     * @return the filePattern
     */
    public String getFilePattern() {
        return filePattern;
    }

    /**
     * @param filePattern
     *            the filePattern to set
     */
    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getEntityUid() {
        return entityUid;
    }

    public void setEntityUid(String entityUid) {
        this.entityUid = entityUid;
    }
}
