package org.dto.adapter.ldap;

import org.dao.mapping.orm.ldap.LdapComputerApp;
import org.dto.ComputerAppServerDto;

/**
 * Adapts a LDAP <i>LdapComputerApp</i> object to an
 * <i>ComputerAppServerDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
class LdapComputerAppServerAdapter extends LdapComputerAdapter implements
        ComputerAppServerDto {

    private LdapComputerApp a;

    /**
     * Constuct a LdapComputerAppServerAdapter that is initialized with a
     * computer application server resource object.
     * 
     * @param comp
     *            an instance of {@link LdapComputerApp}
     */
    protected LdapComputerAppServerAdapter(LdapComputerApp comp) {
        super(comp);
        if (comp == null) {
            comp = new LdapComputerApp();
        }
        this.a = comp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getRemoteSrvcApp()
     */
    @Override
    public String getRemoteSrvcApp() {
        return this.a.getRemoteSrvcApp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setRemoteSrvcApp(java.lang.String)
     */
    @Override
    public void setRemoteSrvcApp(String app) {
        this.a.setRemoteSrvcApp(app);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getRemoteSrvcServlet()
     */
    @Override
    public String getRemoteSrvcServlet() {
        return this.a.getRemoteSrvcServlet();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setRemoteSrvcServlet(java.lang.String)
     */
    @Override
    public void setRemoteSrvcServlet(String servlet) {
        this.a.setRemoteSrvcServlet(servlet);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getLoadServicesHost()
     */
    @Override
    public String getLoadServicesHost() {
        return this.a.getLoadServicesHost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setLoadServicesHost(java.lang.String)
     */
    @Override
    public void setLoadServicesHost(String host) {
        this.a.setLoadServicesHost(host);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getLoadServicesModule()
     */
    @Override
    public String getLoadServicesModule() {
        return this.a.getLoadServicesModule();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setLoadServicesModule(java.lang.String)
     */
    @Override
    public void setLoadServicesModule(String module) {
        this.a.setLoadServicesModule(module);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getLoadServicesId()
     */
    @Override
    public String getLoadServicesId() {
        return this.a.getLoadServicesId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setLoadServicesId(java.lang.String)
     */
    @Override
    public void setLoadServicesId(String id) {
        this.a.setLoadServicesId(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getDefaultconnections()
     */
    @Override
    public String getDefaultconnections() {
        return this.a.getDefaultconnections();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setDefaultconnections(java.lang.String)
     */
    @Override
    public void setDefaultconnections(String defaultconnections) {
        this.a.setDefaultconnections(defaultconnections);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMaxconnections()
     */
    @Override
    public String getMaxconnections() {
        return this.a.getMaxconnections();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setMaxconnections(java.lang.String)
     */
    @Override
    public void setMaxconnections(String maxconnections) {
        this.a.setMaxconnections(maxconnections);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getTimeoutInterval()
     */
    @Override
    public String getTimeoutInterval() {
        return this.a.getTimeoutInterval();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setTimeoutInterval(java.lang.String)
     */
    @Override
    public void setTimeoutInterval(String timeoutInterval) {
        this.a.setTimeoutInterval(timeoutInterval);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getSerialDrive()
     */
    @Override
    public String getSerialDrive() {
        return this.a.getSerialDrive();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setSerialDrive(java.lang.String)
     */
    @Override
    public void setSerialDrive(String serialDrive) {
        this.a.setSerialDrive(serialDrive);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getSerialPath()
     */
    @Override
    public String getSerialPath() {
        return this.a.getSerialPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setSerialPath(java.lang.String)
     */
    @Override
    public void setSerialPath(String serialPath) {
        this.a.setSerialPath(serialPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getSerial_ext()
     */
    @Override
    public String getSerial_ext() {
        return this.a.getSerialExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setSerial_ext(java.lang.String)
     */
    @Override
    public void setSerial_ext(String serialExt) {
        this.a.setSerialExt(serialExt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getSaxDriver()
     */
    @Override
    public String getSaxDriver() {
        return this.a.getSaxDriver();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setSaxDriver(java.lang.String)
     */
    @Override
    public void setSaxDriver(String saxDriver) {
        this.a.setSaxDriver(saxDriver);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getPollingPage()
     */
    @Override
    public String getPollingPage() {
        return this.a.getPollingPage();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setPollingPage(java.lang.String)
     */
    @Override
    public void setPollingPage(String pollingPage) {
        this.a.setPollingPage(pollingPage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getWeb_app_mapping()
     */
    @Override
    public String getWebAppMapping() {
        return this.a.getWebAppMapping();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setWeb_app_mapping(java.lang.String)
     */
    @Override
    public void setWebAppMapping(String webAppMapping) {
        this.a.setWebAppMapping(webAppMapping);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getRptXsltPath()
     */
    @Override
    public String getRptXsltPath() {
        return this.a.getRptXsltPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setRptXsltPath(java.lang.String)
     */
    @Override
    public void setRptXsltPath(String path) {
        this.a.setRptXsltPath(path);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getRptFileExt()
     */
    @Override
    public String getRptFileExt() {
        return this.a.getRptFileExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setRptFileExt(java.lang.String)
     */
    @Override
    public void setRptFileExt(String ext) {
        this.a.setRptFileExt(ext);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getImageDir()
     */
    @Override
    public String getImageDir() {
        return this.a.getImageDir();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setImageDir(java.lang.String)
     */
    @Override
    public void setImageDir(String imageDir) {
        this.a.setImageDir(imageDir);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getEncryptCycle()
     */
    @Override
    public String getEncryptCycle() {
        return this.a.getEncryptCycle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setEncryptCycle(java.lang.String)
     */
    @Override
    public void setEncryptCycle(String encryptCycle) {
        this.a.setEncryptCycle(encryptCycle);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMailHostSmtp()
     */
    @Override
    public String getMailHostSmtp() {
        return this.a.getMailHostSmtp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setMailHostSmtp(java.lang.String)
     */
    @Override
    public void setMailHostSmtp(String host) {
        this.a.setMailHostSmtp(host);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMailHostPop3()
     */
    @Override
    public String getMailHostPop3() {
        return this.a.getMailHostPop3();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setMailHostPop3(java.lang.String)
     */
    @Override
    public void setMailHostPop3(String host) {
        this.a.setMailHostPop3(host);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMailAuthentication()
     */
    @Override
    public String getMailAuthentication() {
        return this.a.getMailAuthentication();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setMailAuthentication(java.lang.String)
     */
    @Override
    public void setMailAuthentication(String authentication) {
        this.a.setMailAuthentication(authentication);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMailUserId()
     */
    @Override
    public String getMailUserId() {
        return this.a.getMailUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setMailUserId(java.lang.String)
     */
    @Override
    public void setMailUserId(String userId) {
        this.a.setMailUserId(userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMailPassword()
     */
    @Override
    public String getMailPassword() {
        return this.a.getMailPassword();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setMailPassword(java.lang.String)
     */
    @Override
    public void setMailPassword(String password) {
        this.a.setMailPassword(password);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMailDefaultcontent()
     */
    @Override
    public String getMailDefaultcontent() {
        return this.a.getMailDefaultcontent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setMailDefaultcontent(java.lang.String)
     */
    @Override
    public void setMailDefaultcontent(String defaultcontent) {
        this.a.setMailDefaultcontent(defaultcontent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMailTemplatepath()
     */
    @Override
    public String getMailTemplatepath() {
        return this.a.getMailTemplatepath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setMailTemplatepath(java.lang.String)
     */
    @Override
    public void setMailTemplatepath(String path) {
        this.a.setMailTemplatepath(path);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMailIinternalSmtpDomain()
     */
    @Override
    public String getMailIinternalSmtpDomain() {
        return this.a.getMailIinternalSmtpDomain();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.ComputerAppServerDto#setMailIinternalSmtpDomain(java.lang.String)
     */
    @Override
    public void setMailIinternalSmtpDomain(String domain) {
        this.a.setMailIinternalSmtpDomain(domain);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getMailResourcetype()
     */
    @Override
    public String getMailResourcetype() {
        return this.a.getMailResourcetype();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setMailResourcetype(java.lang.String)
     */
    @Override
    public void setMailResourcetype(String resourcetype) {
        this.a.setMailResourcetype(resourcetype);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getJmsJndisource()
     */
    @Override
    public String getJmsJndisource() {
        return this.a.getJmsJndisource();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setJmsJndisource(java.lang.String)
     */
    @Override
    public void setJmsJndisource(String jndisource) {
        this.a.setJmsJndisource(jndisource);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getJmsContextclass()
     */
    @Override
    public String getJmsContextclass() {
        return this.a.getJmsContextclass();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setJmsContextclass(java.lang.String)
     */
    @Override
    public void setJmsContextclass(String contextclass) {
        this.a.setJmsContextclass(contextclass);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getJmsProviderurl()
     */
    @Override
    public String getJmsProviderurl() {
        return this.a.getJmsProviderurl();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setJmsProviderurl(java.lang.String)
     */
    @Override
    public void setJmsProviderurl(String providerurl) {
        this.a.setJmsProviderurl(providerurl);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getJmsConnectionfactory()
     */
    @Override
    public String getJmsConnectionfactory() {
        return this.a.getJmsConnectionfactory();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.ComputerAppServerDto#setJmsConnectionfactory(java.lang.String)
     */
    @Override
    public void setJmsConnectionfactory(String connectionfactory) {
        this.a.setJmsConnectionfactory(connectionfactory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getJmsJaxbDefaultpackage()
     */
    @Override
    public String getJmsJaxbDefaultpackage() {
        return this.a.getJmsJaxbDefaultpackage();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.ComputerAppServerDto#setJmsJaxbDefaultpackage(java.lang.String)
     */
    @Override
    public void setJmsJaxbDefaultpackage(String defaultpackage) {
        this.a.setJmsJaxbDefaultpackage(defaultpackage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getSoaphost()
     */
    @Override
    public String getSoaphost() {
        return this.a.getSoaphost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setSoaphost(java.lang.String)
     */
    @Override
    public void setSoaphost(String soaphost) {
        this.a.setSoaphost(soaphost);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getSoapNameSpaceAware()
     */
    @Override
    public String getSoapNameSpaceAware() {
        return this.a.getSoapNameSpaceAware();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setSoapNameSpaceAware(java.lang.String)
     */
    @Override
    public void setSoapNameSpaceAware(String flag) {
        this.a.setSoapNameSpaceAware(flag);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getPaginationPageSize()
     */
    @Override
    public String getPaginationPageSize() {
        return this.a.getPaginationPageSize();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setPaginationPageSize(java.lang.String)
     */
    @Override
    public void setPaginationPageSize(String pageSize) {
        this.a.setPaginationPageSize(pageSize);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getPageLinkMax()
     */
    @Override
    public String getPageLinkMax() {
        return this.a.getPageLinkMax();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setPageLinkMax(java.lang.String)
     */
    @Override
    public void setPageLinkMax(String pageLinkMax) {
        this.a.setPageLinkMax(pageLinkMax);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getServer()
     */
    @Override
    public String getServer() {
        return this.a.getServer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setServer(java.lang.String)
     */
    @Override
    public void setServer(String server) {
        this.a.setServer(server);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getDBMSVendor()
     */
    @Override
    public String getDbmsVendor() {
        return this.a.getDbmsVendor();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setDBMSVendor(java.lang.String)
     */
    @Override
    public void setDbmsVendor(String dbmsVendor) {
        this.a.setDbmsVendor(dbmsVendor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getWebapps_drive()
     */
    @Override
    public String getWebAppsDrive() {
        return this.a.getWebAppsDrive();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setWebapps_drive(java.lang.String)
     */
    @Override
    public void setWebAppsDrive(String drive) {
        this.a.setWebAppsDrive(drive);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getWebapps_dir()
     */
    @Override
    public String getWebAppsDir() {
        return this.a.getWebAppsDir();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setWebapps_dir(java.lang.String)
     */
    @Override
    public void setWebAppsDir(String dir) {
        this.a.setWebAppsDir(dir);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getDefaultWebAppCtxRootDirName()
     */
    @Override
    public String getDefaultWebAppCtxRootDirName() {
        return this.a.getDefaultWebAppCtxRootDirName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.ComputerAppServerDto#setDefaultWebAppCtxRootDirName(java.lang
     * .String)
     */
    @Override
    public void setDefaultWebAppCtxRootDirName(
            String defaultWebAppCtxRootDirName) {
        this.a.setDefaultWebAppCtxRootDirName(defaultWebAppCtxRootDirName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getSystemConfig()
     */
    @Override
    public String getSystemConfig() {
        return this.a.getSystemConfig();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setSystemConfig(java.lang.String)
     */
    @Override
    public void setSystemConfig(String systemConfig) {
        this.a.setSystemConfig(systemConfig);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#getAppConfigPath()
     */
    @Override
    public String getAppConfigPath() {
        return this.a.getAppConfigPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerAppServerDto#setAppConfigPath(java.lang.String)
     */
    @Override
    public void setAppConfigPath(String appConfigPath) {
        this.a.setAppConfigPath(appConfigPath);
    }

}
