package org.modules.services.directory;

import com.RMT2Base;

/**
 * A bean representing the application's module configuration.
 * 
 * @author appdev
 * 
 */
public class ApplicationModuleBean extends RMT2Base {

    private int moduleCode;

    private String dbUrl;

    private String dbDriver;

    private String table;

    private String primaryKey;

    private String foreignKey;

    private String filePattern;

    private String dbUserId;

    private String dbPassword;

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
    protected ApplicationModuleBean(int moduleCode) {
        this.moduleCode = moduleCode;
        return;
    }

    /**
     * @return the table
     */
    public String getTable() {
        return table;
    }

    /**
     * @param table
     *            the table to set
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * @return the primaryKey
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param primaryKey
     *            the primaryKey to set
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return the foreignKey
     */
    public String getForeignKey() {
        return foreignKey;
    }

    /**
     * @param foreignKey
     *            the foreignKey to set
     */
    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
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

    /**
     * @return the moduleCode
     */
    public int getModuleCode() {
        return moduleCode;
    }

    /**
     * @param moduleCode
     *            the moduleCode to set
     */
    public void setModuleCode(int moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * @return the dbUrl
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * @param dbUrl
     *            the dbUrl to set
     */
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    /**
     * @return the dbUserId
     */
    public String getDbUserId() {
        return dbUserId;
    }

    /**
     * @param dbUserId
     *            the dbUserId to set
     */
    public void setDbUserId(String dbUserId) {
        this.dbUserId = dbUserId;
    }

    /**
     * @return the dbPassword
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * @param dbPassword
     *            the dbPassword to set
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * @return the dbDriver
     */
    public String getDbDriver() {
        return dbDriver;
    }

    /**
     * @param dbDriver
     *            the dbDriver to set
     */
    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

}
