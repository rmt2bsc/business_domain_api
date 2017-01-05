package ldap;

import java.util.List;

import javax.naming.directory.SearchControls;

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
public class RMT2LdapAnonymousiSearchTest {
    private LdapClient api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        LdapFactory f = new LdapFactory();
        api = f.createAttributeClient();
        api.connect(null, null, "config.LdapAnonymousConfig");
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
            op.setDn("ou=People,o=RMT2BSC,dc=rmt2,dc=net");
            op.getMatchAttributes().put("loginid", "admin");
            op.setMappingBeanName("testcases.ldap.LdapUser");
            Object data = api.retrieve(op);
            System.out.println("Person object was successfully mapped.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSingleRecordSearchUsingFilterArgs() throws Exception {
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=People,o=RMT2BSC,dc=rmt2,dc=net");
            op.setScope(SearchControls.SUBTREE_SCOPE);
            // op.setSearchFilter("loginid={0}");
            // op.getSearchFilterArgs().add("admin");
            op.getSearchFilterArgs().put("loginid", "{0}");
            op.getSearchFilterPlaceholders().add("admin");
            op.setMappingBeanName("testcases.ldap.LdapUser");
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
            op.setDn("ou=People,o=RMT2BSC,dc=rmt2,dc=net");
            op.getMatchAttributes().put("sn", "Terrell");
            op.setMappingBeanName("testcases.ldap.LdapUser");
            Object data[] = api.retrieve(op);
            List list = (List) data[0];
            System.out.println("Total Person objects found: " + list.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
