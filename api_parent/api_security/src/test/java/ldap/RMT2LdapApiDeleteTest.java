package ldap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.api.ldap.LdapClient;
import com.api.ldap.LdapFactory;
import com.api.ldap.operation.LdapAddOperation;
import com.api.ldap.operation.LdapDeleteOperation;

/**
 * @author rterrell
 * 
 */
public class RMT2LdapApiDeleteTest {
    private LdapClient api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        LdapFactory f = new LdapFactory();
        api = f.createAttributeClient();
        // api.connect("loginid=admin", "drum7777",
        // "testcases.ldap.TestLdapAuthConfig");
        api.connect(LdapClient.DEFAULT_CONFIG_RESOURCE);
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
    public void testDelete() {
        // try to add entry...may throw an exception if already exists.
        try {
            this.testAdd();
        } catch (Exception e) {
            // Do nothing...Entry probably exists
        }

        LdapDeleteOperation op = new LdapDeleteOperation();
        op.setDn("loginid=dchambers,ou=People");
        this.api.deleteRow(op);
    }

    private void testAdd() {
        LdapAddOperation op = new LdapAddOperation();
        op.setDn("loginid=dchambers,ou=People");
        op.addListAttribute("objectClass", "person");
        op.addListAttribute("objectClass", "RMT2Person");
        op.addListAttribute("objectClass", "RMT2User");
        op.addListAttribute("objectClass", "top");
        op.addAttribute("fn", "Dennis");
        op.addAttribute("sn", "Chambers");
        op.addAttribute("cn", "Dennis Chambers");
        op.addAttribute("loginid", "dchambers");
        op.addAttribute("dob", "1960-01-01");
        op.addAttribute("grp", "Internal");
        op.addAttribute("jt", "Independent Drummer");
        op.addAttribute("ssn", "999-99-9999");
        op.addAttribute("startDate", "2011-3-14");

        this.api.insertRow(op, false);
    }

}
