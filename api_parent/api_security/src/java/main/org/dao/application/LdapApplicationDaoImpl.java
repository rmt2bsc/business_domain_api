package org.dao.application;

import java.util.ArrayList;
import java.util.List;

import org.dao.SecurityDaoException;
import org.dao.mapping.orm.ldap.LdapApplication;
import org.dto.ApplicationDto;
import org.dto.adapter.ldap.LdapDtoFactory;

import com.RMT2Constants;
import com.api.ldap.AbstractLdapDaoClient;
import com.api.ldap.LdapClient;
import com.api.ldap.operation.LdapAddOperation;
import com.api.ldap.operation.LdapDeleteOperation;
import com.api.ldap.operation.LdapModifyOperation;
import com.api.ldap.operation.LdapSearchOperation;

/**
 * An JNDI implementation of the {@link AppDao} interface which functions to
 * manage application data from a LDAP server.
 * <p>
 * The base DN in which this implementation operates from is
 * <i>ou=Applications,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
 * 
 * @author Roy Terrell
 * 
 */
class LdapApplicationDaoImpl extends AbstractLdapDaoClient implements AppDao {

    private static final String BASE_DN = "ou=Applications,ou=Resources";

    /**
     * Create a LdapApplicationDaoImpl that establishes a LDAP client and
     * attemtps to gain a connection to the LDAP server.
     */
    public LdapApplicationDaoImpl() {
        super();
    }

    /**
     * Fetch an application configuration record by application name at base DN,
     * <i>cn=xxxx,ou=Applications,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>
     * 
     * @param appName
     *            the name of the application
     * @return An instance of {@link ApplicationDto} conatining the desired
     *         application data or null if no data is found.
     * @throws SecurityDaoException
     */
    @Override
    public ApplicationDto fetchApp(String appName) throws SecurityDaoException {
        LdapApplication ldapApp = null;
        List<LdapApplication> appList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapApplicationDaoImpl.BASE_DN);
            op.getMatchAttributes().put("cn", appName);
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapApplication");
            Object results[] = this.ldap.retrieve(op);
            appList = this.ldap.extractLdapResults(results);
            if (appList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new AppDaoException(e);
        }

        if (appList.size() > 1) {
            this.msg = "Too many application records were returned from LDAP server for a single application query";
            throw new AppDaoException();
        }
        else {
            ldapApp = appList.get(0);
        }
        ApplicationDto dto = LdapDtoFactory.getApplicationInstance(ldapApp);
        return dto;
    }

    /**
     * Fetches all application configuration records under the base DN,
     * <i>ou=Applications,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @return A List of {@link ApplicationDto} objects conatining the desired
     *         application data or null if no data is found.
     * @throws SecurityDaoException
     */
    @Override
    public List<ApplicationDto> fetchApp() throws SecurityDaoException {
        List<LdapApplication> appList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapApplicationDaoImpl.BASE_DN);
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapApplication");
            Object results[] = this.ldap.retrieve(op);
            appList = this.ldap.extractLdapResults(results);
            if (appList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new AppDaoException(e);
        }

        List<ApplicationDto> list = new ArrayList<ApplicationDto>();
        for (LdapApplication item : appList) {
            ApplicationDto dto = LdapDtoFactory.getApplicationInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates or modifies an application oconfiguration entry at base DN,
     * <i>cn=xxxxxx,ou=Applications,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param app
     *            An instance of {@link ApplicationDto} containing the data
     *            needed to update an application record.
     * @return The application id for new records or the total number of records
     *         effected by the update.
     * @throws SecurityDaoException
     */
    @Override
    public int maintainApp(ApplicationDto app) throws SecurityDaoException {
        this.validateApp(app);

        // Determine if this is a new or existing user
        boolean newApp = (this.fetchApp(app.getAppName()) == null);
        if (newApp) {
            this.insertApp(app);
        }
        else {
            this.updateApp(app);
        }
        return 1;
    }

    private void insertApp(ApplicationDto app) throws AppDaoException {
        LdapAddOperation op = new LdapAddOperation();
        // Handle required fields
        String dn = "cn=" + app.getAppName() + ","
                + LdapApplicationDaoImpl.BASE_DN;
        op.setDn(dn);
        op.addListAttribute("objectClass", "RMT2ApplicationConfig");
        op.addListAttribute("objectClass", "RMT2Applications");
        op.addListAttribute("objectClass", "top");
        op.addAttribute("active", app.getActive());
        op.addAttribute("appCode", app.getAppCode());
        op.addAttribute("appTitle", app.getAppTitle());
        op.addAttribute("appWebContext", app.getWebContext());
        op.addAttribute("cn", app.getAppName());
        op.addAttribute("description", app.getAppDescription());

        // Handle optional data
        if (app.getAppDir() != null) {
            op.addAttribute("appDirName", app.getAppDir());
        }
        if (app.getLogConfig() != null) {
            op.addAttribute("appLogConfig", app.getLogConfig());
        }
        if (app.getNavigationRules() != null) {
            op.addAttribute("appNamRulesPath", app.getNavigationRules());
        }
        if (app.getOutputDir() != null) {
            op.addAttribute("appOutPath", app.getOutputDir());
        }
        if (app.getAuthenticator() != null) {
            op.addAttribute("authenticator", app.getAuthenticator());
        }
        if (app.getDbConfigName() != null) {
            op.addAttribute("dbConfigName", app.getDbConfigName());
        }
        if (app.getDbConnectionFactory() != null) {
            op.addAttribute("dbConnectionFactoryClass",
                    app.getDbConnectionFactory());
        }
        if (app.getDbOwner() != null) {
            op.addAttribute("dbOwner", app.getDbOwner());
        }
        if (app.getOrmBeanPackage() != null) {
            op.addAttribute("ormBeanPkg", app.getOrmBeanPackage());
        }
        if (app.getOrmGeneratedOutputDir() != null) {
            op.addAttribute("ormGenOutput", app.getOrmGeneratedOutputDir());
        }
        if (app.getOrmXmlDir() != null) {
            op.addAttribute("ormXmlDir", app.getOrmXmlDir());
        }

        // Add application configuration profile to LDAP server
        this.ldap.insertRow(op, false);
        return;
    }

    private void updateApp(ApplicationDto app) throws AppDaoException {
        LdapModifyOperation op = new LdapModifyOperation();
        // Handle required fields
        String dn = "cn=" + app.getAppName() + ","
                + LdapApplicationDaoImpl.BASE_DN;
        op.setDn(dn);
        op.addAttribute("active", app.getActive(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("appCode", app.getAppCode(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("appTitle", app.getAppTitle(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("appWebContext", app.getWebContext(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("description", app.getAppDescription(),
                LdapClient.MOD_OPERATION_REPLACE);

        // Handle optional data
        if (app.getAppDir() != null) {
            op.addAttribute("appDirName", app.getAppDir(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getLogConfig() != null) {
            op.addAttribute("appLogConfig", app.getLogConfig(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getNavigationRules() != null) {
            op.addAttribute("appNamRulesPath", app.getNavigationRules(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getOutputDir() != null) {
            op.addAttribute("appOutPath", app.getOutputDir(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getAuthenticator() != null) {
            op.addAttribute("authenticator", app.getAuthenticator(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getDbConfigName() != null) {
            op.addAttribute("dbConfigName", app.getDbConfigName(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getDbConnectionFactory() != null) {
            op.addAttribute("dbConnectionFactoryClass",
                    app.getDbConnectionFactory(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getDbOwner() != null) {
            op.addAttribute("dbOwner", app.getDbOwner(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getOrmBeanPackage() != null) {
            op.addAttribute("ormBeanPkg", app.getOrmBeanPackage(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getOrmGeneratedOutputDir() != null) {
            op.addAttribute("ormGenOutput", app.getOrmGeneratedOutputDir(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (app.getOrmXmlDir() != null) {
            op.addAttribute("ormXmlDir", app.getOrmXmlDir(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }

        // Modify application configuration profile to LDAP server
        this.ldap.updateRow(op);
        return;
    }

    /**
     * Validates an application configuration profile reprsented as <i>app</i>.
     * 
     * @param app
     *            an instance of {@link ApplicationDto}
     * @throws InvalidAppInstanceException
     *             <ol>
     *             <li><i>app</i> is null</li>
     *             <li>The application name is null</li>
     *             <li>The application code is null</li>
     *             <li>The application title is null</li>
     *             <li>The application web context path is null</li>
     *             <li>The application description is null</li>
     *             <li>The active flag is null, not numeric, or a value other
     *             than 1 or 0</li>
     *             </ol>
     */
    protected void validateApp(ApplicationDto app)
            throws InvalidAppInstanceException {
        if (app == null) {
            throw new InvalidAppInstanceException(
                    "Application DTO object cannot be null");
        }
        if (app.getAppName() == null) {
            throw new InvalidAppInstanceException(
                    "Application name is required");
        }
        if (app.getAppCode() == null) {
            throw new InvalidAppInstanceException(
                    "Application code is required");
        }
        if (app.getAppTitle() == null) {
            throw new InvalidAppInstanceException(
                    "Application title is required");
        }
        if (app.getWebContext() == null) {
            throw new InvalidAppInstanceException(
                    "Application web context path is required");
        }
        if (app.getAppDescription() == null) {
            throw new InvalidAppInstanceException(
                    "Application description is required");
        }
        if (app.getActive() == null) {
            throw new InvalidAppInstanceException(
                    "Application active flag is required");
        }
        try {
            int val = Integer.parseInt(app.getActive());
            if (val != 1 && val != 0) {
                throw new InvalidAppInstanceException(
                        "Application active flag value must be \"1\" or \"0\"");
            }
        } catch (NumberFormatException e) {
            throw new InvalidAppInstanceException(
                    "Application active flag must be numeric");
        }
        return;
    }

    /**
     * Delete an application configuration entry by name at base DN,
     * <i>cn=xxxxx,ou=Applications,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param appName
     *            The name of the application
     * @return Total number of application records deleted.
     * @throws SecurityDaoException
     */
    @Override
    public int deleteApp(String appName) throws AppDaoException {
        if (appName == null) {
            this.msg = "Application name is required for delete operation";
            throw new AppDaoException(this.msg);
        }
        String dn = "cn=" + appName + "," + LdapApplicationDaoImpl.BASE_DN;
        LdapDeleteOperation op = new LdapDeleteOperation();
        op.setDn(dn);
        return this.ldap.deleteRow(op);
    }

    /**
     * Not supported
     */
    @Override
    public int deleteApp(int appId) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public ApplicationDto fetchApp(int uid) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
