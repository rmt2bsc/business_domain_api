package ldap;

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
public class RMT2MultiRowResultSetNavigationTest {
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

        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=People");
            op.getMatchAttributes().put("sn", "Terrell");
            op.setMappingBeanName("com.api.ldap.beans.LdapUser");
            api.retrieve(op);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
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
    public void navigateForward() throws Exception {
        while (this.api.nextRow()) {
            this.displayValues();
        }
    }

    @Test
    public void navigateLast() throws Exception {
        if (this.api.lastRow()) {
            this.displayValues();
        }
    }

    @Test
    public void navigateFirst() throws Exception {
        this.api.lastRow();
        if (this.api.firstRow()) {
            this.displayValues();
        }
    }

    @Test
    public void navigatePrev() throws Exception {
        this.api.nextRow();
        this.api.nextRow();
        while (this.api.previousRow()) {
            this.displayValues();
        }
    }

    private void displayValues() {
        String fn = this.api.getColumnValue("fn");
        String ln = this.api.getColumnValue("sn");
        String loginId = this.api.getColumnValue("loginid");
        String desc = this.api.getColumnValue("description");

        System.out.println("Frist Name: " + fn);
        System.out.println("Last Name: " + ln);
        System.out.println("Login Id: " + loginId);
        System.out.println("Description: " + desc);
    }
}
