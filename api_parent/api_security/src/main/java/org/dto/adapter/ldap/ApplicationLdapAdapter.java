package org.dto.adapter.ldap;

import java.util.ArrayList;

import org.dao.mapping.orm.ldap.LdapApplication;

import org.dto.DefaultApplicationAdpater;

/**
 * Adapts an LDAP instance of <i>LdapApplication</i> object to an
 * <i>ApplicationDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
class ApplicationLdapAdapter extends DefaultApplicationAdpater {

    private LdapApplication a;

    /**
     * Default constructor
     */
    protected ApplicationLdapAdapter() {
        super();
        return;
    }

    /**
     * Create a ApplicationLdapAdapter using an instance of
     * <i>LdapApplication</i>.
     * 
     * @param app
     *            an instance of {@link LdapApplication}
     */
    protected ApplicationLdapAdapter(LdapApplication app) {
        this();
        if (app == null) {
            app = new LdapApplication();
        }
        this.a = app;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setAppName(java.lang.String)
     */
    @Override
    public void setAppName(String value) {
        if (this.a.getCn() == null) {
            this.a.setCn(new ArrayList<String>());
        }
        this.a.getCn().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getAppName()
     */
    @Override
    public String getAppName() {
        if (this.a.getCn() != null && this.a.getCn().size() > 0) {
            return this.a.getCn().get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultApplicationAdpater#setAppDescription(java.lang.String)
     */
    @Override
    public void setAppDescription(String value) {
        if (this.a.getDescription() == null) {
            this.a.setDescription(new ArrayList<String>());
        }
        this.a.getDescription().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getAppDescription()
     */
    @Override
    public String getAppDescription() {
        if (this.a.getDescription() != null
                && this.a.getDescription().size() > 0) {
            return this.a.getDescription().get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getActive()
     */
    @Override
    public String getActive() {
        return this.a.getActive();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setActive(java.lang.String)
     */
    @Override
    public void setActive(String value) {
        this.a.setActive(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setAppCode(java.lang.String)
     */
    @Override
    public void setAppCode(String value) {
        this.a.setAppCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getAppCode()
     */
    @Override
    public String getAppCode() {
        return this.a.getAppCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setAppTitle(java.lang.String)
     */
    @Override
    public void setAppTitle(String value) {
        this.a.setAppTitle(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getAppTitle()
     */
    @Override
    public String getAppTitle() {
        return this.a.getAppTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setWebContext(java.lang.String)
     */
    @Override
    public void setWebContext(String value) {
        this.a.setAppWebContext(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getWebContext()
     */
    @Override
    public String getWebContext() {
        return this.a.getAppWebContext();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setAppDir(java.lang.String)
     */
    @Override
    public void setAppDir(String value) {
        this.a.setAppDir(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getAppDir()
     */
    @Override
    public String getAppDir() {
        return this.a.getAppDir();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setLogConfig(java.lang.String)
     */
    @Override
    public void setLogConfig(String value) {
        this.a.setAppLogConfig(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getLogConfig()
     */
    @Override
    public String getLogConfig() {
        return this.a.getAppLogConfig();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultApplicationAdpater#setNavigationRules(java.lang.String)
     */
    @Override
    public void setNavigationRules(String value) {
        this.a.setAppNavRulesPath(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getNavigationRules()
     */
    @Override
    public String getNavigationRules() {
        return this.a.getAppNavRulesPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setAuthenticator(java.lang.String)
     */
    @Override
    public void setAuthenticator(String value) {
        this.a.setAuthenticator(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getAuthenticator()
     */
    @Override
    public String getAuthenticator() {
        return this.a.getAuthenticator();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setOutputDir(java.lang.String)
     */
    @Override
    public void setOutputDir(String value) {
        this.a.setAppOutPath(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getOutputDir()
     */
    @Override
    public String getOutputDir() {
        return this.a.getAppOutPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setDbConfigName(java.lang.String)
     */
    @Override
    public void setDbConfigName(String value) {
        this.a.setDbConfigName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getDbConfigName()
     */
    @Override
    public String getDbConfigName() {
        return this.a.getDbConfigName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultApplicationAdpater#setDbConnectionFactory(java.lang.String
     * )
     */
    @Override
    public void setDbConnectionFactory(String value) {
        this.a.setDbConnectionFactoryClass(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getDbConnectionFactory()
     */
    @Override
    public String getDbConnectionFactory() {
        return this.a.getDbConnectionFactoryClass();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setDbOwner(java.lang.String)
     */
    @Override
    public void setDbOwner(String value) {
        this.a.setDbOwner(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getDbOwner()
     */
    @Override
    public String getDbOwner() {
        return this.a.getDbOwner();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultApplicationAdpater#setOrmBeanPackage(java.lang.String)
     */
    @Override
    public void setOrmBeanPackage(String value) {
        this.a.setOrmBeanPkg(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getOrmBeanPackage()
     */
    @Override
    public String getOrmBeanPackage() {
        return this.a.getOrmBeanPkg();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultApplicationAdpater#setOrmGeneratedOutputDir(java.lang.
     * String)
     */
    @Override
    public void setOrmGeneratedOutputDir(String value) {
        this.a.setOrmGenOutput(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getOrmGeneratedOutputDir()
     */
    @Override
    public String getOrmGeneratedOutputDir() {
        return this.a.getOrmGenOutput();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#setOrmXmlDir(java.lang.String)
     */
    @Override
    public void setOrmXmlDir(String value) {
        this.a.setOrmXmlDir(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultApplicationAdpater#getOrmXmlDir()
     */
    @Override
    public String getOrmXmlDir() {
        return this.a.getOrmXmlDir();
    }

}
