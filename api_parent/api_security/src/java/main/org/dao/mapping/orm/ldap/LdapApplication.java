package org.dao.mapping.orm.ldap;

import com.api.ldap.beans.LdapCommonEntity;

/**
 * ORM bean for mapping application related data coming from a LDAP server.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapApplication extends LdapCommonEntity {

    private String appCode;

    private String active;

    private String appDir;

    private String appTitle;

    private String appWebContext;

    private String appLogConfig;

    private String appNavRulesPath;

    private String appOutPath;

    private String dbConnectionFactoryClass;

    private String dbOwner;

    private String dbConfigName;

    private String ormBeanPkg;

    private String ormGenOutput;

    private String ormXmlDir;

    private String authenticator;

    /**
     * Creates a LdapApplication object withou intializing any of its
     * properties.
     */
    public LdapApplication() {
        super();
    }

    /**
     * Get the application code.
     * 
     * @return the appCode
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * Set the applicaton code
     * 
     * @param appCode
     *            the appCode
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * Get the active flag
     * 
     * @return String
     */
    public String getActive() {
        return active;
    }

    /**
     * Set the active flag
     * 
     * @param active
     *            the active to set
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     * Get the application directory path
     * 
     * @return String
     */
    public String getAppDir() {
        return appDir;
    }

    /**
     * Set the application directory path
     * 
     * @param appDir
     *            String
     */
    public void setAppDir(String appDir) {
        this.appDir = appDir;
    }

    /**
     * Get the application title
     * 
     * @return String
     */
    public String getAppTitle() {
        return appTitle;
    }

    /**
     * Set the application title
     * 
     * @param appTitle
     *            String
     */
    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    /**
     * Get application logging properties file name which is fully qualified by
     * its package name
     * 
     * @return String
     */
    public String getAppLogConfig() {
        return appLogConfig;
    }

    /**
     * Set application logging properties file name which is fully qualified by
     * its package name
     * 
     * @param appLogConfig
     *            String
     */
    public void setAppLogConfig(String appLogConfig) {
        this.appLogConfig = appLogConfig;
    }

    /**
     * Get application navigation rule properties file name which is fully
     * qualified by its package name
     * 
     * @return String
     */
    public String getAppNavRulesPath() {
        return appNavRulesPath;
    }

    /**
     * Set application navigation rule properties file name which is fully
     * qualified by its package name
     * 
     * @param appNavRulesPath
     *            String
     */
    public void setAppNavRulesPath(String appNavRulesPath) {
        this.appNavRulesPath = appNavRulesPath;
    }

    /**
     * Get the directory path where application data is output.
     * 
     * @return String
     */
    public String getAppOutPath() {
        return appOutPath;
    }

    /**
     * Set the directory path where application data is output.
     * 
     * @param appOutPath
     *            String
     */
    public void setAppOutPath(String appOutPath) {
        this.appOutPath = appOutPath;
    }

    /**
     * Get the database owner id
     * 
     * @return String
     */
    public String getDbOwner() {
        return dbOwner;
    }

    /**
     * Set the database owner id.
     * 
     * @param dbOwner
     *            String
     */
    public void setDbOwner(String dbOwner) {
        this.dbOwner = dbOwner;
    }

    /**
     * Return the package name where the database orm beans can be found.
     * 
     * @return String
     */
    public String getOrmBeanPkg() {
        return ormBeanPkg;
    }

    /**
     * Set the package name where the database orm beans can be found.
     * 
     * @param ormBeanPkg
     *            String
     */
    public void setOrmBeanPkg(String ormBeanPkg) {
        this.ormBeanPkg = ormBeanPkg;
    }

    /**
     * Get the directory path where generated ORM classes are output.
     * 
     * @return String
     */
    public String getOrmGenOutput() {
        return ormGenOutput;
    }

    /**
     * Set the directory path where generated ORM java classes are output.
     * 
     * @param ormGenOutput
     *            String
     */
    public void setOrmGenOutput(String ormGenOutput) {
        this.ormGenOutput = ormGenOutput;
    }

    /**
     * Get the directory path where generated ORM data source (XML) files are
     * output.
     * 
     * @return String
     */
    public String getOrmXmlDir() {
        return ormXmlDir;
    }

    /**
     * Set the directory path where generated ORM data source (XML) files are
     * output.
     * 
     * @param ormXmlDir
     *            String
     */
    public void setOrmXmlDir(String ormXmlDir) {
        this.ormXmlDir = ormXmlDir;
    }

    /**
     * Get the class name of the application's authenticator which is fully
     * qualified by its package name.
     * 
     * @return String
     */
    public String getAuthenticator() {
        return authenticator;
    }

    /**
     * Set the class name of the application's authenticator which is fully
     * qualified by its package name.
     * 
     * @param authenticator
     *            String
     */
    public void setAuthenticator(String authenticator) {
        this.authenticator = authenticator;
    }

    /**
     * Get the LDAP entry name that represents this application's database
     * configuration.
     * 
     * @return String
     */
    public String getDbConfigName() {
        return dbConfigName;
    }

    /**
     * Set the LDAP entry name that represents this application's database
     * configuration.
     * 
     * @param dbConfigName
     *            String
     */
    public void setDbConfigName(String dbConfigName) {
        this.dbConfigName = dbConfigName;
    }

    /**
     * Get the application's web context path info
     * 
     * @return the application's web context path
     */
    public String getAppWebContext() {
        return this.appWebContext;
    }

    /**
     * Set the application's web context path info
     * 
     * @param webAppContext
     *            the application's web context path
     */
    public void setAppWebContext(String webAppContext) {
        this.appWebContext = webAppContext;
    }

    /**
     * Get the class name of the database connection factory.
     * 
     * @return String
     */
    public String getDbConnectionFactoryClass() {
        return dbConnectionFactoryClass;
    }

    /**
     * Set the class name of the database connection factory.
     * 
     * @param dbConnectionFactoryClass
     *            Stirng
     */
    public void setDbConnectionFactoryClass(String dbConnectionFactoryClass) {
        this.dbConnectionFactoryClass = dbConnectionFactoryClass;
    }

}
