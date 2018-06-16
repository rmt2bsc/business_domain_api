package org.dto;

/**
 * Data Transfer Object (DTO) contract for managing information pertaining to
 * Application or Web server computer resources.
 * 
 * @author Roy Terrell
 * 
 */
public interface ComputerAppServerDto extends ComputerDto {

    /**
     * Get the remote services web application context
     * 
     * @return String
     */
    String getRemoteSrvcApp();

    /**
     * Set the remote services web application context
     * 
     * @param app
     *            String
     */
    void setRemoteSrvcApp(String app);

    /**
     * Get the name of the servlet used for remote services.
     * 
     * @return String
     */
    String getRemoteSrvcServlet();

    /**
     * Set the name of the servlet used for remote services.
     * 
     * @param servlet
     *            String
     */
    void setRemoteSrvcServlet(String servlet);

    /**
     * Get the load services host name
     * 
     * @return the load services host
     */
    String getLoadServicesHost();

    /**
     * Set the load services host name
     * 
     * @param host
     *            the loadservices_host to set
     */
    void setLoadServicesHost(String host);

    /**
     * Get the services module name
     * 
     * @return the load services module name
     */
    String getLoadServicesModule();

    /**
     * Set the services module name
     * 
     * @param module
     *            the load services module to set
     */
    void setLoadServicesModule(String module);

    /**
     * Get the id of the load services.
     * 
     * @return the load services id
     */
    String getLoadServicesId();

    /**
     * Set the id of the load services
     * 
     * @param id
     *            the load services id to set
     */
    void setLoadServicesId(String id);

    /**
     * Get the default number of database connections to add to the connection
     * pool
     * 
     * @return the total number of default connections
     */
    String getDefaultconnections();

    /**
     * Set the default number of database connections to add to the connection
     * pool
     * 
     * @param defaultconnections
     *            the total number of default connections
     */
    void setDefaultconnections(String defaultconnections);

    /**
     * Get the maximum number of database connections to add to the connection
     * pool
     * 
     * @return the maxconnections the maximum number of default connections
     */
    String getMaxconnections();

    /**
     * Set the maximum number of database connections to add to the connection
     * pool
     * 
     * @param maxconnections
     *            the maximum number of default connections
     */
    void setMaxconnections(String maxconnections);

    /**
     * Get the time out interval in seconds which the user session should
     * terminate
     * 
     * @return String
     */
    String getTimeoutInterval();

    /**
     * Set the time out interval in seconds which the user session should
     * terminate
     * 
     * @param timeoutInterval
     *            String
     */
    void setTimeoutInterval(String timeoutInterval);

    /**
     * Get the letter of the hard drive where object serialization should occur
     * 
     * @return the serial drive
     */
    String getSerialDrive();

    /**
     * Set the letter of the hard drive where object serialization should occur
     * 
     * @param serialDrive
     *            the serial_drive to set
     */
    void setSerialDrive(String serialDrive);

    /**
     * Get file serialization directory path
     * 
     * @return the serial path
     */
    String getSerialPath();

    /**
     * Set file serialization directory path
     * 
     * @param serialPath
     *            the serial path to set
     */
    void setSerialPath(String serialPath);

    /**
     * Get the extention of the file that will containt the results of object
     * serialization
     * 
     * @return the serial ext
     */
    String getSerial_ext();

    /**
     * Set the extention of the file that will containt the results of object
     * serialization
     * 
     * @param serialExt
     *            the serial_ext to set
     */
    void setSerial_ext(String serialExt);

    /**
     * Get the fully qualified class name of the XML SAX driver
     * 
     * @return the SAX Driver
     */
    String getSaxDriver();

    /**
     * Set the fully qualified class name of the XML SAX driver
     * 
     * @param saxDriver
     *            the SAX Driver to set
     */
    void setSaxDriver(String saxDriver);

    /**
     * Get the URL resource for the \"Wait Please...\" JSP page used with
     * polling service
     * 
     * @return the polling page
     */
    String getPollingPage();

    /**
     * Set the URL resource for the \"Wait Please...\" JSP page used with
     * polling service
     * 
     * @param pollingPage
     *            the polling page to set
     */
    void setPollingPage(String pollingPage);

    /**
     * Get the file name of the application command mappings properties file
     * 
     * @return the web app mapping
     */
    String getWebAppMapping();

    /**
     * Set the file name of the application command mappings properties file
     * 
     * @param webAppMapping
     *            the web app mapping to set
     */
    void setWebAppMapping(String webAppMapping);

    /**
     * Get the path where to find the XSLT files for reporting
     * 
     * @return the rpt_xslt_path
     */
    String getRptXsltPath();

    /**
     * Set the path where to find the XSLT files for reporting
     * 
     * @param path
     *            the rpt_xslt_path to set
     */
    void setRptXsltPath(String path);

    /**
     * Get the file extention of the XSLT files for reporting
     * 
     * @return the rpt_file_ext
     */
    String getRptFileExt();

    /**
     * Set the file extention of the XSLT files for reporting
     * 
     * @param ext
     *            the rpt_file_ext to set
     */
    void setRptFileExt(String ext);

    /**
     * Get the path where image files can be recognized by the XSLT files used
     * for reporting
     * 
     * @return the image_dir
     */
    String getImageDir();

    /**
     * Set the path where image files can be recognized by the XSLT files used
     * for reporting
     * 
     * @param imageDir
     *            the image_dir to set
     */
    void setImageDir(String imageDir);

    /**
     * Get The number of cycles used to encrypt passwords
     * 
     * @return the encrypt cycle number
     */
    String getEncryptCycle();

    /**
     * Set The number of cycles used to encrypt passwords
     * 
     * @param encryptCycle
     *            the encrypt cycle number
     */
    void setEncryptCycle(String encryptCycle);

    /**
     * Get the server name of the SMTP host for the mail server
     * 
     * @return the mail_host_smtp
     */
    String getMailHostSmtp();

    /**
     * Set the server name of the SMTP host for the mail server
     * 
     * @param host
     *            the mail_host_smtp to set
     */
    void setMailHostSmtp(String host);

    /**
     * Get The server name of the POP3 host for the mail server
     * 
     * @return the mail_host_pop3
     */
    String getMailHostPop3();

    /**
     * Set the server name of the POP3 host for the mail server
     * 
     * @param host
     *            the mail_host_pop3 to set
     */
    void setMailHostPop3(String host);

    /**
     * Get the flag that indicates if the user should be authenticated when
     * connecting to the mail server.
     * 
     * @return the mail authentication
     */
    String getMailAuthentication();

    /**
     * Set the flag that indicates if the user should be authenticated when
     * connecting to the mail server.
     * <p>
     * Set to "true" when authentication is required. Otherwise, set to "false".
     * 
     * @param authentication
     *            the mail authentication to set
     */
    void setMailAuthentication(String authentication);

    /**
     * Get The login id of the user connecting to the mail server
     * 
     * @return the mail userId
     */
    String getMailUserId();

    /**
     * Set The login id of the user connecting to the mail server
     * 
     * @param userId
     *            the mail userId to set
     */
    void setMailUserId(String userId);

    /**
     * Get the password of the user connecting to the mail server
     * 
     * @return the mail password
     */
    String getMailPassword();

    /**
     * Set the password of the user connecting to the mail server
     * 
     * @param password
     *            the mail password to set
     */
    void setMailPassword(String password);

    /**
     * Get he default content type of the incoming/outgoing mail messages
     * 
     * @return the mail defaultcontent
     */
    String getMailDefaultcontent();

    /**
     * Set he default content type of the incoming/outgoing mail messages
     * 
     * @param defaultcontent
     *            the mail defaultcontent to set
     */
    void setMailDefaultcontent(String defaultcontent);

    /**
     * Get the path where mail templates can be found
     * 
     * @return the mail template path
     */
    String getMailTemplatepath();

    /**
     * Set the path where mail templates can be found
     * 
     * @param path
     *            the mail template path to set
     */
    void setMailTemplatepath(String path);

    /**
     * Get the domain name of the mail server
     * 
     * @return the mail internal smtp domain
     */
    String getMailIinternalSmtpDomain();

    /**
     * Set the domain name of the mail server
     * 
     * @param domain
     *            the mail internal smtp domain to set
     */
    void setMailIinternalSmtpDomain(String domain);

    /**
     * Get the mail resource type.
     * 
     * @return the mail resource type
     */
    String getMailResourcetype();

    /**
     * Set the mail resource type.
     * 
     * @param resourcetype
     *            the mail resource type to set
     */
    void setMailResourcetype(String resourcetype);

    /**
     * Get the JMS JNDI repository resource type
     * 
     * @return the jms jndi source
     */
    String getJmsJndisource();

    /**
     * Set the JMS JNDI repository resource type
     * 
     * @param jndisource
     *            the jms jndi source to set
     */
    void setJmsJndisource(String jndisource);

    /**
     * Get the fully qualified name of the JMS factory class
     * 
     * @return the jms contextclass
     */
    String getJmsContextclass();

    /**
     * Set the fully qualified name of the JMS factory class
     * 
     * @param contextclass
     *            the jms contextclass to set
     */
    void setJmsContextclass(String contextclass);

    /**
     * Get the URL of the JMS Server
     * 
     * @return the jms provider url
     */
    String getJmsProviderurl();

    /**
     * Set the URL of the JMS Server
     * 
     * @param providerurl
     *            the jms provider url to set
     */
    void setJmsProviderurl(String providerurl);

    /**
     * Get the lookup name for the ConnectionFactory when using the queue
     * manager as a JNDI repository
     * 
     * @return the jms connection factory
     */
    String getJmsConnectionfactory();

    /**
     * Set the lookup name for the ConnectionFactory when using the queue
     * manager as a JNDI repository
     * 
     * @param connectionfactory
     *            the jms connection factory to set
     */
    void setJmsConnectionfactory(String connectionfactory);

    /**
     * Get the default location where JMS bind and package XML related messages
     * via JAXB
     * 
     * @return the jms jaxb default package
     */
    String getJmsJaxbDefaultpackage();

    /**
     * Set the default location where JMS bind and package XML related messages
     * via JAXB
     * 
     * @param defaultpackage
     *            the jms jaxb default package to set
     */
    void setJmsJaxbDefaultpackage(String defaultpackage);

    /**
     * Get SOAP host
     * 
     * @return the soap host
     */
    String getSoaphost();

    /**
     * Set the SOAP host
     * 
     * @param soaphost
     *            the soap host to set
     */
    void setSoaphost(String soaphost);

    /**
     * Get SOAP name space aware flag
     * 
     * @return "true" when namespace aware and "false" when not namespace aware
     */
    String getSoapNameSpaceAware();

    /**
     * Set SOAP name space aware flag
     * 
     * @param flag
     *            the soap namespace aware flag value to set. Set to "true" for
     *            namespace aware and "false" for not namespace aware
     */
    void setSoapNameSpaceAware(String flag);

    /**
     * Get the pagination property value for governing how many rows are
     * displayed per page
     * 
     * @return the pagination page size
     */
    String getPaginationPageSize();

    /**
     * Set the pagination property value for governing how many rows are
     * displayed per page
     * 
     * @param pageSize
     *            the pagination page size to set
     */
    void setPaginationPageSize(String pageSize);

    /**
     * Get the pagination proerty value for governing how many page numbers are
     * displayed in pagination rule of the display
     * 
     * @return the page link max
     */
    String getPageLinkMax();

    /**
     * Set the pagination proerty value for governing how many page numbers are
     * displayed in pagination rule of the display
     * 
     * @param pageLinkMax
     *            the page link max to set
     */
    void setPageLinkMax(String pageLinkMax);

    /**
     * Get the identity of the server including its port number if applicable.
     * 
     * @return the server name and possible the port number
     */
    String getServer();

    /**
     * Set the identity of the server including its port number if applicable.
     * 
     * @param server
     *            the server name and possible the port number
     */
    void setServer(String server);

    /**
     * Get A numeric value representing the the manufacture of the RDBMS used by
     * the application.
     * 
     * @return the DBMS Vendor
     */
    String getDbmsVendor();

    /**
     * Set A numeric value representing the the manufacture of the RDBMS used by
     * the application.
     * 
     * @param dbmsVendor
     *            The DBMS vendor to set. Valid values are: 1 = Sybase Sql
     *            Anywhere, Adaptive Server Anywhere, 2 = Sybase Adaptive Server
     *            Enterprise, 3 = Oracle, 4 = MS SQL Server, and 5 = DB2
     */
    void setDbmsVendor(String dbmsVendor);

    /**
     * Get the drive letter where the web application is installed.
     * <p>
     * This should include the colon suffix
     * 
     * @return the web apps drive
     */
    String getWebAppsDrive();

    /**
     * Set the drive letter where the web application is installed.
     * 
     * @param drive
     *            the web apps drive to set which should include the colon
     *            suffix
     */
    void setWebAppsDrive(String drive);

    /**
     * Get the fully qualified file system directory path to the web application
     * context
     * 
     * @return the web apps dir
     */
    String getWebAppsDir();

    /**
     * Set the fully qualified file system directory path to the web application
     * context
     * 
     * @param dir
     *            the web apps dir to set
     */
    void setWebAppsDir(String dir);

    /**
     * @return the defaultWebAppCtxRootDirName
     */
    String getDefaultWebAppCtxRootDirName();

    /**
     * @param defaultWebAppCtxRootDirName
     *            the defaultWebAppCtxRootDirName to set
     */
    void setDefaultWebAppCtxRootDirName(String defaultWebAppCtxRootDirName);

    /**
     * @return the systemConfig
     */
    String getSystemConfig();

    /**
     * @param systemConfig
     *            the systemConfig to set
     */
    void setSystemConfig(String systemConfig);

    /**
     * @return the appConfigPath
     */
    String getAppConfigPath();

    /**
     * @param appConfigPath
     *            the appConfigPath to set
     */
    void setAppConfigPath(String appConfigPath);

}
