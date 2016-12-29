package org.dao.resources;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.dao.mapping.orm.ldap.LdapComputerApp;
import org.dao.user.UserDaoException;
import org.dto.ComputerAppServerDto;
import org.dto.adapter.ldap.LdapDtoFactory;

import com.api.ldap.LdapClient;
import com.api.ldap.LdapFactory;
import com.api.ldap.operation.LdapSearchOperation;
import com.api.persistence.AbstractDaoClientImpl;

/**
 * @author Roy Terrell
 * 
 */
class LdapComputerAppServerDaoImpl extends AbstractDaoClientImpl implements
        ComputerResourceDao {

    private LdapFactory factory;

    private LdapClient ldap;

    /**
     * Creates a LdapComputerAppServerDaoImpl object that will initialize the
     * LDAP connection.
     */
    protected LdapComputerAppServerDaoImpl() {
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.resources.ComputerResourceDao#getApplicationServerInfo(org.dto
     * .ComputerAppServerDto)
     */
    @Override
    public List<ComputerAppServerDto> fetchApplicationServerInfo(
            ComputerAppServerDto criteria) throws ResourceDaoException {
        String baseDn = "ou=App,ou=Servers,ou=Computers,ou=Resources";
        String mappingClass = "org.dao.mapping.orm.ldap.LdapComputerApp";

        List<LdapComputerApp> appList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(baseDn);
            op.setScope(SearchControls.SUBTREE_SCOPE);
            op.setUseSearchFilterExpression(true);
            op.getSearchFilterArgs().put("ou", criteria.getEnv());
            op.getSearchFilterArgs().put("cn", criteria.getName());
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName(mappingClass);
            Object results[] = this.ldap.retrieve(op);
            appList = this.ldap.extractLdapResults(results);
            if (appList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }

        List<ComputerAppServerDto> list = new ArrayList<ComputerAppServerDto>();
        for (LdapComputerApp item : appList) {
            ComputerAppServerDto dto = LdapDtoFactory
                    .getComputerAppServerDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

}
