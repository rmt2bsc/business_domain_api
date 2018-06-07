package old.tests.ldap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.api.ldap.LdapClient;
import com.api.ldap.LdapFactory;
import com.api.ldap.operation.LdapCompareOperation;

/**
 * @author rterrell
 * 
 */
public class RMT2LdapApiCompareTest {
    private LdapClient api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        LdapFactory f = new LdapFactory();
        api = f.createAttributeClient();
        api.connect("cn=directory manager", "drum7777",
                "config.LdapAnonymousConfig");

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
    public void testSingleRecordCompareUsingFilterArgs() throws Exception {
        try {
            LdapCompareOperation op = new LdapCompareOperation();
            op.setDn("ou=development,ou=Accounting-DB,ou=Database,ou=Servers,ou=Computers,ou=Resources");
            // op.setSearchFilter("serverConnectString={0}");
            // op.getSearchFilterArgs().add("jdbc:sybase:Tds:localhost?ServiceName=accounting");
            op.getSearchFilterArgs().put("serverConnectString", "{0}");
            op.getSearchFilterPlaceholders().add(
                    "jdbc:sybase:Tds:localhost?ServiceName=accounting");
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName("com.api.ldap.beans.LdapComputerDatabase");
            Object data[] = api.retrieve(op);
            Boolean rc = (Boolean) data[0];
            System.out.println("Did Compare operation succeed? " + rc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
