package ldap;

import java.util.List;

import javax.naming.directory.SearchControls;

import junit.framework.Assert;

import org.dao.mapping.orm.ldap.LdapComputerDatabase;
import org.dao.mapping.orm.ldap.LdapComputerWeb;
import org.dao.mapping.orm.ldap.LdapUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.api.ldap.LdapClient;
import com.api.ldap.LdapFactory;
import com.api.ldap.operation.LdapSearchOperation;

/**
 * @author rterrell
 * 
 */
public class RMT2LdapApiSearchTest {
    private LdapClient api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        LdapFactory f = new LdapFactory();
        api = f.createAttributeClient();
        api.connect("loginid=admin", "drum7777",
                LdapClient.DEFAULT_CONFIG_RESOURCE);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        api.close();
        api = null;
    }

    @Test
    public void testSingleRecordSearch() throws Exception {
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=People");
            op.getMatchAttributes().put("loginid", "admin");
            op.setMappingBeanName("com.api.ldap.beans.LdapUser");
            Object data = api.retrieve(op);
            Assert.assertNotNull(data);
            Assert.assertTrue(data instanceof LdapUser);
            LdapUser u = (LdapUser) data;
            Assert.assertEquals(u.getSn(), "Terrell");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
            return;
        }
    }

    @Test
    public void testSingleRecordSearchUsingFilterArgs() throws Exception {
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=People");
            op.setScope(SearchControls.SUBTREE_SCOPE);
            // op.setSearchFilter("loginid={0}");
            // op.getSearchFilterArgs().add("admin");

            op.getSearchFilterArgs().put("loginid", "{0}");
            op.getSearchFilterPlaceholders().add("admin");
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName("com.api.ldap.beans.LdapUser");
            Object data[] = api.retrieve(op);
            List list = (List) data[0];
            System.out.println("Total Person objects found: " + list.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMultiRecordSearch() throws Exception {
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=People");
            op.getMatchAttributes().put("sn", "Terrell");
            op.setMappingBeanName("com.api.ldap.beans.LdapUser");
            Object data[] = api.retrieve(op);
            List list = (List) data[0];
            System.out.println("Total Person objects found: " + list.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void searchDatabaseServerAndMap() throws Exception {
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=development,ou=Accounting-DB,ou=Database,ou=Servers,ou=Computers,ou=Resources");
            // op.setSearchFilter("serverConnectString={0}");
            // op.getSearchFilterArgs().add("jdbc:sybase:Tds:localhost?ServiceName=accounting");

            op.getSearchFilterArgs().put("serverConnectString", "{0}");
            op.getSearchFilterPlaceholders().add(
                    "jdbc:sybase:Tds:localhost?ServiceName=accounting");
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName("com.api.ldap.beans.LdapComputerDatabase");
            Object data[] = api.retrieve(op);
            List<LdapComputerDatabase> list = (List<LdapComputerDatabase>) data[0];
            LdapComputerDatabase rc = list.get(0);
            System.out.println("Database server description: "
                    + rc.getDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void searchWebServerAndMap() throws Exception {
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=development,ou=RMT2-Internet,ou=Web,ou=Servers,ou=Computers,ou=Resources");
            // op.setSearchFilter("server={0}");
            // op.getSearchFilterArgs().add("http://localhost:8080");

            op.getSearchFilterArgs().put("server", "{0}");
            op.getSearchFilterPlaceholders().add("http://localhost:8080");
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName("com.api.ldap.beans.LdapComputerWeb");
            Object data[] = api.retrieve(op);
            List<LdapComputerWeb> list = (List<LdapComputerWeb>) data[0];
            LdapComputerWeb rc = list.get(0);
            System.out
                    .println("Web server description: " + rc.getDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void searchAdminUsingFilterArgs() throws Exception {
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=People");
            op.setScope(SearchControls.SUBTREE_SCOPE);
            // op.setSearchFilter("loginid={0}");
            // op.getSearchFilterArgs().add("admin");

            op.getSearchFilterArgs().put("loginid", "{0}");
            op.getSearchFilterPlaceholders().add("admin");
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName("com.api.ldap.beans.LdapUser");
            Object data[] = api.retrieve(op);
            List list = (List) data[0];
            System.out.println("Total Person objects found: " + list.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
