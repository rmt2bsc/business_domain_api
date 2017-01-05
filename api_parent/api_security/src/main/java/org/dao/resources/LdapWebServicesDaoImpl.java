package org.dao.resources;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.ldap.LdapWebService;
import org.dao.roles.RoleDaoException;
import org.dao.user.UserDaoException;
import org.dto.ResourceDto;
import org.dto.WebServiceDto;
import org.dto.adapter.ldap.LdapDtoFactory;

import com.api.ldap.LdapClient;
import com.api.ldap.LdapFactory;
import com.api.ldap.operation.LdapAddOperation;
import com.api.ldap.operation.LdapDeleteOperation;
import com.api.ldap.operation.LdapModifyOperation;
import com.api.ldap.operation.LdapSearchOperation;

/**
 * A JNDI implementation of the {@link ResourceDao} interface which functions to
 * manage web service configurations as a Resource entity.
 * <p>
 * All operations belonging to this implementtaion will be anchored at the base
 * DN, <i>ou=Web Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
 * 
 * @author Roy Terrell
 * 
 */
class LdapWebServicesDaoImpl extends DefaultResourceImpl {

    private static final Logger logger = Logger
            .getLogger(LdapWebServicesDaoImpl.class);

    private static final String BASE_DN = "ou=Web Services,ou=Resources";

    private LdapFactory factory;

    private LdapClient ldap;

    private static final String MAPPING_CLASS = "org.dao.mapping.orm.ldap.LdapWebService";

    /**
     * Creates a LdapWebServicesDaoImpl object that will initialize the LDAP
     * connection.
     */
    protected LdapWebServicesDaoImpl() {
        this.factory = new LdapFactory();
        this.ldap = this.factory.createAttributeClient();
        // this.api.connect(null, null,
        // AuthenticationConst.LDAP_ANONYONUS_DATASOURCE);
        this.ldap.connect(LdapClient.DEFAULT_CONFIG_RESOURCE);
    }

    /**
     * Release any LDAP resources used for this implementation.
     */
    @Override
    public void close() {
        this.ldap.close();
        this.ldap = null;
        this.factory = null;
    }

    /**
     * Retrieve all web services sub type configuration records at DN, <i>ou=Web
     * Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @return List of {@link ResourceDto} objects or null if no data is found
     * @throws ResourceDaoException
     *             Database access error
     */
    @Override
    public List<ResourceDto> fetchResourceSubType() throws ResourceDaoException {
        List<LdapWebService> subTypeList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapWebServicesDaoImpl.BASE_DN); // ou=Web
                                                      // Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net
            op.setMappingBeanName(MAPPING_CLASS);
            Object results[] = this.ldap.retrieve(op);
            subTypeList = this.ldap.extractLdapResults(results);
            if (subTypeList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }

        List<ResourceDto> list = new ArrayList<ResourceDto>();
        for (LdapWebService item : subTypeList) {
            ResourceDto dto = LdapDtoFactory.getResourceDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetch a single web service sub type configuration record by its name.
     * <p>
     * The value of <i>subTypeName</i> will be used to match the target
     * <i>ou</i> attribute of the node located one level below the base DN,
     * <i>ou=Web Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param subTypeName
     *            the name of the web service sub type
     * @return An instance of {@link ResourceDto} objects containting resource
     *         sub type data.
     * @throws ResourceDaoException
     */
    @Override
    public ResourceDto fetchResourceSubType(String subTypeName)
            throws ResourceDaoException {
        if (subTypeName == null) {
            throw new ResourceDaoException(
                    "Resource sub type name argument is required");
        }
        List<LdapWebService> subTypeList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapWebServicesDaoImpl.BASE_DN);
            op.getMatchAttributes().put("ou", subTypeName);
            op.setMappingBeanName(MAPPING_CLASS);
            Object results[] = this.ldap.retrieve(op);
            subTypeList = this.ldap.extractLdapResults(results);
            if (subTypeList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }

        LdapWebService obj = null;
        if (subTypeList.size() > 1) {
            this.msg = "Too many resource sub type objects were returned from LDAP server for a single resource sub type query";
            throw new RoleDaoException();
        }
        else {
            obj = subTypeList.get(0);
        }
        WebServiceDto dto = LdapDtoFactory
                .getWebServiceResourceDtoInstance(obj);
        return dto;
    }

    /**
     * Retrieve all web service entries at DN, <i>ou=xxxx,ou=Web
     * Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i> based on the selection
     * criteria containted in <i>criteria</i>.
     * <p>
     * Web service entries are matched and obtained based on a valid criteria
     * object using JNDI attribute matching or search filters. JNDI predicates
     * are built using <i>attribute matching</i> when the query is anchored from
     * a particular resource sub type node which requires the node to be
     * traversed one level deep. The search filter is utilized in situations
     * where the query is anchored from the web service node and the traversal
     * depth to locate the target data is unknown. Another reason to use the
     * search filter is to construct complex predicates. For example,
     * <blockquote>
     * <i>(&(cn=RQ_accounting_invoice_sales_order)(isSecured=1))</i>
     * </blockquote>
     * 
     * @param criteria
     *            a instance of {@link ResourceDto} containing the selection
     *            criteria.
     * @return List of {@link ResourceDto} objects or null if no data is found
     * @throws ResourceDaoException
     *             Database access error
     */
    @Override
    public List<ResourceDto> fetchResource(ResourceDto criteria)
            throws ResourceDaoException {
        List<LdapWebService> userList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            if (criteria == null) {
                // Get all web services for every resource sub type in the tree
                op.setDn(LdapWebServicesDaoImpl.BASE_DN);
                op.setScope(SearchControls.SUBTREE_SCOPE);
                op.getSearchFilterArgs().put("cn", "*");
                op.setUseSearchFilterExpression(true);
            }
            else {
                if (!(criteria instanceof WebServiceDto)) {
                    throw new InvalidResourceDaoException(
                            "Input parameter is required to be of type, WebServiceDto, at runtime for LDAP query");
                }
                // Obtain web service entries based on a valid criteria object
                // using attribute matching
                // or search filters. Attribute matching will be used when we
                // anchor from a particular
                // resource sub type only needing to traverse the node one level
                // deep. Otherwise, utilize
                // the search filter when anchored from the web service node not
                // knowing the depth we need
                // to traverse the tree to find matching data as well as to
                // build complex predicates.
                // For example,
                // (&(cn=RQ_accounting_invoice_sales_order)(isSecured=1))
                if (criteria.getSubTypeName() != null) {
                    // Change Base DN to start at the selected resource sub type
                    // of the web service DN
                    op.setDn("ou=" + criteria.getSubTypeName() + ", "
                            + LdapWebServicesDaoImpl.BASE_DN);
                    // Change scope to select items one level down from the base
                    // DN
                    op.setScope(SearchControls.ONELEVEL_SCOPE);

                    // If a web service name is supplied, then targe that entry.
                    if (criteria.getName() != null) {
                        op.getMatchAttributes().put("cn", criteria.getName());
                    }
                    if (((WebServiceDto) criteria).isQuerySecureFlag()) {
                        op.getMatchAttributes().put(
                                "isSecured",
                                String.valueOf(((WebServiceDto) criteria)
                                        .getSecured()));
                    }
                    op.setUseSearchFilterExpression(false);
                }
                else {
                    // Start search at the web service base DN.
                    op.setDn(LdapWebServicesDaoImpl.BASE_DN);
                    op.setScope(SearchControls.SUBTREE_SCOPE);

                    // Evaluate web service name argument
                    if (criteria.getName() != null) {
                        op.getSearchFilterArgs().put("cn", criteria.getName());
                    }
                    else {
                        // Ensure that we target all web services with a valid
                        // name
                        op.getSearchFilterArgs().put("cn", "*");
                    }

                    if (((WebServiceDto) criteria).isQuerySecureFlag()) {
                        op.getSearchFilterArgs().put(
                                "isSecured",
                                String.valueOf(((WebServiceDto) criteria)
                                        .getSecured()));
                    }
                    op.setUseSearchFilterExpression(true);
                    logger.info("LDAP filter to be applied: "
                            + op.getSearchFilter());
                    System.out.println("LDAP filter to be applied: "
                            + op.getSearchFilter());
                }
            }

            op.setMappingBeanName(MAPPING_CLASS);
            Object results[] = this.ldap.retrieve(op);
            userList = this.ldap.extractLdapResults(results);
            if (userList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }

        List<ResourceDto> list = new ArrayList<ResourceDto>();
        for (LdapWebService item : userList) {
            WebServiceDto dto = LdapDtoFactory
                    .getWebServiceResourceDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Adds a new or modifies an existing web service sub type node at a level
     * just below base DN, <i>ou=Web
     * Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param subType
     *            an instance of {@link ResourceDto} containting the resource
     *            sub type data.
     * @return Always return 1.
     * @throws ResourceDaoException
     */
    @Override
    public int maintainResourceSubType(ResourceDto subType)
            throws ResourceDaoException {
        this.validateResourceSubType(subType);

        // Perform fetch to determine if resource exists
        Object dto = this.fetchResourceSubType(subType.getSubTypeName());
        if (dto == null) {
            this.insertResourceSubType(subType);
        }
        else {
            this.updateResourceSubType(subType);
        }
        return 1;
    }

    /**
     * Inserts a new web service sub type configuration entry at DN, <i>ou=Web
     * Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param subType
     *            an instance of {@link ResourceDto} containing web service sub
     *            type configuration data.
     * @throws ResourceDaoException
     */
    private void insertResourceSubType(ResourceDto subType)
            throws ResourceDaoException {
        LdapAddOperation op = new LdapAddOperation();
        String dn = "ou=" + subType.getSubTypeName()
                + ",ou=Web Services,ou=Resources";
        op.setDn(dn);
        op.addListAttribute("objectClass", "organizationalUnit");
        op.addListAttribute("objectClass", "top");
        op.addAttribute("ou", subType.getSubTypeName());
        op.addAttribute("description", subType.getSubTypeDescription());
        this.ldap.insertRow(op, false);
    }

    /**
     * Applies modifications to an existing web service sub type configuration
     * entry at DN, <i>ou=Web
     * Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param subType
     *            an instance of {@link ResourceDto} containing web service sub
     *            type configuration data.
     * @throws ResourceDaoException
     */
    private void updateResourceSubType(ResourceDto subType)
            throws ResourceDaoException {
        LdapModifyOperation op = new LdapModifyOperation();
        String dn = "ou=" + subType.getSubTypeName()
                + ",ou=Web Services,ou=Resources";
        op.setDn(dn);
        op.addAttribute("description", subType.getSubTypeDescription(),
                LdapClient.MOD_OPERATION_REPLACE);
        this.ldap.updateRow(op);
    }

    /**
     * Deletes a web service sub type configuration record by name at base DN,
     * <i>ou=Web Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param subTypeName
     *            the name of the web service sub type
     * @return Always return 1
     * @throws ResourceDaoException
     */
    @Override
    public int deleteResourceSubType(String subTypeName)
            throws ResourceDaoException {
        LdapDeleteOperation op = new LdapDeleteOperation();
        String dn = "ou=" + subTypeName + ",ou=Web Services,ou=Resources";
        op.setDn(dn);
        return this.ldap.deleteRow(op);
    }

    /**
     * Verifies that web service sub type configuration object contains the
     * required data.
     * 
     * @param subType
     *            an instance of {@link ResourceDto} containing the required web
     *            service sub type configuration data.
     * @throws ResourceDaoException
     *             When <i>subType</i> is null or the properties, sub type name
     *             and sub type description are absent.
     */
    protected void validateResourceSubType(ResourceDto subType)
            throws ResourceDaoException {
        if (subType == null) {
            throw new ResourceDaoException(
                    "Web service resource sub type object is invalid");
        }
        if (subType.getSubTypeName() == null) {
            throw new ResourceDaoException(
                    "Web service resource sub type name is required");
        }
        if (subType.getSubTypeDescription() == null) {
            throw new ResourceDaoException(
                    "Web service resource sub type description is required");
        }
        return;
    }

    /**
     * Adds a new or modifies an existing web service configuration record at
     * DN, <i>ou=xxxx,ou=Web Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param rsrc
     *            an instance of {@link ResourceDto} containting the resource
     *            data.
     * @return Always return 1.
     * @throws ResourceDaoException
     */
    @Override
    public int maintainResource(ResourceDto rsrc) throws ResourceDaoException {
        this.validateResource(rsrc);

        LdapWebService dummy = null;
        WebServiceDto criteria = LdapDtoFactory
                .getWebServiceResourceDtoInstance(dummy);
        criteria.setSubTypeName(rsrc.getSubTypeName());
        criteria.setName(rsrc.getName());
        Object dto = this.fetchResource(criteria);

        if (dto == null) {
            this.insertResource(rsrc);
        }
        else {
            this.updateResource(rsrc);
        }
        return 1;
    }

    /**
     * Inserts a new web service configuration entry at DN, <i>ou=xxxx,ou=Web
     * Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param wsConfig
     *            an instance of {@link ResourceDto} containing web service
     *            configuration data.
     * @throws ResourceDaoException
     */
    private void insertResource(ResourceDto wsConfig)
            throws ResourceDaoException {
        LdapAddOperation op = new LdapAddOperation();
        String dn = "cn=" + wsConfig.getName() + ",ou="
                + wsConfig.getSubTypeName() + ",ou=Web Services,ou=Resources";
        op.setDn(dn);
        op.addListAttribute("objectClass", "RMT2WebService");
        op.addListAttribute("objectClass", "top");
        op.addAttribute("cn", wsConfig.getName());
        op.addAttribute("description", wsConfig.getDescription());
        op.addAttribute("isSecured",
                String.valueOf(((WebServiceDto) wsConfig).getSecured()));
        op.addAttribute("requestUrl",
                ((WebServiceDto) wsConfig).getRequestUrl());
        op.addAttribute("replyMsgId",
                ((WebServiceDto) wsConfig).getReplyMsgId());

        try {
            this.ldap.insertRow(op, false);
        } catch (Exception e) {
            this.msg = "Error inserting web service configuration LDAP entry: "
                    + dn;
            throw new ResourceDaoException(this.msg, e);
        }
    }

    /**
     * Applies modifications to an existing web service configuration entry at
     * DN, <i>ou=xxxx,ou=Web Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param wsConfig
     *            an instance of {@link ResourceDto} containing web service
     *            configuration data.
     * @throws ResourceDaoException
     */
    private void updateResource(ResourceDto wsConfig)
            throws ResourceDaoException {
        LdapModifyOperation op = new LdapModifyOperation();
        String dn = "cn=" + wsConfig.getName() + ",ou="
                + wsConfig.getSubTypeName() + ",ou=Web Services,ou=Resources";
        op.setDn(dn);
        op.addAttribute("description", wsConfig.getDescription(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("isSecured",
                String.valueOf(((WebServiceDto) wsConfig).getSecured()),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("requestUrl",
                ((WebServiceDto) wsConfig).getRequestUrl(),
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("replyMsgId",
                ((WebServiceDto) wsConfig).getReplyMsgId(),
                LdapClient.MOD_OPERATION_REMOVE);

        try {
            this.ldap.updateRow(op);
        } catch (Exception e) {
            this.msg = "Error updating web service configuration LDAP entry: "
                    + dn;
            throw new ResourceDaoException(this.msg, e);
        }
    }

    /**
     * Deletes a single web service configuration record at DN,
     * <i>ou=xxxx,ou=Web Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * In order to target the exact LDAP entry, the resource name and resource
     * sub type name must be suppled in <i>rsrc</i> so that the appropriate
     * distinguished name can be built for the query.
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} which the properties,
     *            resource name and resource sub type name, are required to be
     *            populated in order to target the web service configuration
     *            LDAP entry.
     * @return total number of records effected.
     * @throws ResourceDaoException
     */
    @Override
    public int deleteResource(ResourceDto criteria) throws ResourceDaoException {
        if (criteria == null) {
            throw new ResourceDaoException(
                    "LDAP resource delete failed...The ResourceDto input parameter is invalid or null");
        }
        String rsrcName = criteria.getName();
        String rsrcSubTypeName = criteria.getSubTypeName();

        LdapDeleteOperation op = new LdapDeleteOperation();
        String dn = "cn=" + rsrcName + ",ou=" + rsrcSubTypeName
                + ",ou=Web Services,ou=Resources";
        op.setDn(dn);
        return this.ldap.deleteRow(op);
    }

    /**
     * Verifies that web service configuration object contains the required
     * data.
     * 
     * @param rsrc
     *            an instance of {@link ResourceDto} containing the required web
     *            service configuration data.
     * @throws ResourceDaoException
     *             When <i>rsrc</i> is null, one of the properties, sub type
     *             name, name, description or request URL is absent, or the
     *             secured flag contains a valid other than 0 or 1.
     */
    protected void validateResource(ResourceDto rsrc)
            throws ResourceDaoException {
        if (rsrc == null) {
            throw new ResourceDaoException(
                    "Web service resource object is invalid");
        }
        if (!(rsrc instanceof WebServiceDto)) {
            throw new InvalidResourceDaoException(
                    "The target web service resource object is required to be of type, WebServiceDto, at runtime");
        }
        if (rsrc.getSubTypeName() == null) {
            throw new ResourceDaoException(
                    "Web service sub type name is required");
        }
        if (rsrc.getName() == null) {
            throw new ResourceDaoException(
                    "Web service resource name is required");
        }
        if (rsrc.getDescription() == null) {
            throw new ResourceDaoException(
                    "Web service resource description is required");
        }
        if (((WebServiceDto) rsrc).getRequestUrl() == null) {
            throw new ResourceDaoException(
                    "Web service resource request URL is required");
        }
        if (((WebServiceDto) rsrc).getSecured() != 0
                && ((WebServiceDto) rsrc).getSecured() != 1) {
            throw new ResourceDaoException(
                    "Web service resource security flag contains an invalid value: "
                            + ((WebServiceDto) rsrc).getSecured());
        }
        return;
    }

}
