package ldap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.api.ldap.LdapClient;
import com.api.ldap.LdapFactory;
import com.api.ldap.operation.LdapAddOperation;
import com.api.ldap.operation.LdapDeleteOperation;
import com.api.ldap.operation.LdapModifyOperation;

/**
 * @author rterrell
 * 
 */
public class RMT2LdapApiModifyTest {
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

        // Add an entry to be modified later
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
        op.addAttribute("email", "royroy@gte.net");
        op.addAttribute("city", "Shreveport");
        op.addListAttribute("ar", "acctadmin");
        op.addListAttribute("ar", "authadmin");
        op.addListAttribute("ar", "rmt2admin");
        this.api.insertRow(op, false);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        // Delete test LDAP entry
        LdapDeleteOperation op = new LdapDeleteOperation();
        op.setDn("loginid=dchambers,ou=People");
        this.api.deleteRow(op);

        // Clean up!
        api.close();
        api = null;
    }

    @Test
    public void testGeneral() {
        LdapModifyOperation op = new LdapModifyOperation();
        op.setDn("loginid=dchambers,ou=People");
        op.addAttribute("dob", "1960-11-26", LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("grp", "External", LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("jt", "Independent Drummer for Pearl Drums",
                LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("ssn", "123-45-6789", LdapClient.MOD_OPERATION_REPLACE);
        op.addAttribute("description",
                "Added this attribute as a modify operation",
                LdapClient.MOD_OPERATION_ADD);
        op.addAttribute("email", null, LdapClient.MOD_OPERATION_REMOVE);
        op.addAttribute("city", null, LdapClient.MOD_OPERATION_REMOVE);
        this.api.updateRow(op);
    }

    @Test
    public void testAddToMultiValueAttribute() {
        LdapModifyOperation op = new LdapModifyOperation();
        op.setDn("loginid=dchambers,ou=People");
        op.addAttribute("ar", "rmt2client", LdapClient.MOD_OPERATION_ADD);
        this.api.updateRow(op);
    }

    @Test
    public void testReplaceMultiValueAttribute() {
        LdapModifyOperation op = new LdapModifyOperation();
        op.setDn("loginid=dchambers,ou=People");
        op.addAttribute("ar", "replacement_role",
                LdapClient.MOD_OPERATION_REPLACE);
        this.api.updateRow(op);
    }

    @Test
    public void testRemoveSingleValueFromMultiValueAttribute() {
        LdapModifyOperation op = new LdapModifyOperation();
        op.setDn("loginid=dchambers,ou=People");

        // Removing value, "authadmin", from "ar" attribute by simply recreating
        // the list
        // without including the value targeted for removal.
        op.addListAttribute("ar", "acctadmin", LdapClient.MOD_OPERATION_REPLACE);
        op.addListAttribute("ar", "authadmin", LdapClient.MOD_OPERATION_REPLACE);
        this.api.updateRow(op);
    }

    // @Test
    // public void addToMultiValueAttributeToAdminUser() {
    // LdapModifyOperation op = new LdapModifyOperation();
    // op.setDn("loginid=admin,ou=People");
    // op.addListAttribute("ar", "acctadmin", LdapClient.MOD_OPERATION_ADD);
    // op.addListAttribute("ar", "authadmin", LdapClient.MOD_OPERATION_ADD);
    // op.addListAttribute("ar", "comadmin", LdapClient.MOD_OPERATION_ADD);
    // op.addListAttribute("ar", "conadmin", LdapClient.MOD_OPERATION_ADD);
    // op.addListAttribute("ar", "ProjAdm", LdapClient.MOD_OPERATION_ADD);
    // // op.addListAttribute("ar", "rmt2admin", LdapClient.MOD_OPERATION_ADD);
    // this.api.updateRow(op);
    // }
    //
    // @Test
    // public void addToMultiValueAttributeToTimemgrUser() {
    // LdapModifyOperation op = new LdapModifyOperation();
    // op.setDn("loginid=timemgr,ou=People");
    // op.addAttribute("ar", "ProjAdm", LdapClient.MOD_OPERATION_REPLACE);
    // this.api.updateRow(op);
    // }
}
