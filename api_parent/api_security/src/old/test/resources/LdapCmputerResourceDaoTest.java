package old.tests.resources;

import java.util.List;

import junit.framework.Assert;

import org.dao.mapping.orm.ldap.LdapResource;
import org.dao.mapping.orm.ldap.LdapWebService;
import org.dao.resources.ComputerResourceDao;
import org.dao.resources.ResourceDao;
import org.dao.resources.ResourcesDaoFactory;
import org.dto.ComputerAppServerDto;
import org.dto.ResourceDto;
import org.dto.WebServiceDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Computer Resource LDAP DAO implementation.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapCmputerResourceDaoTest {

    private ResourcesDaoFactory f;

    private ComputerResourceDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new ResourcesDaoFactory();
        this.dao = this.f.createLdapComputerAppServerDao();
        this.dao.setDaoUser("testuser");
        // this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        // this.deleteData();
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    private void createData() {
        int rc = 0;
        LdapWebService dummy = null;
        WebServiceDto dto = LdapDtoFactory
                .getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST1");
        dto.setSubTypeDescription("This is a description for resource sub type #1");
        // rc = this.dao.maintainResourceSubType(dto);
        Assert.assertEquals(1, rc);

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST2");
        dto.setSubTypeDescription("This is a description for resource sub type #2");
        // rc = this.dao.maintainResourceSubType(dto);
        Assert.assertEquals(1, rc);

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST1");
        dto.setName("Test_service_1");
        dto.setDescription("This is a description for the test web service #1");
        dto.setSecured(1);
        dto.setRequestUrl("rmt:/tetserver/test1");
        dto.setReplyMsgId("Test_service_1_Reply");
        // rc = this.dao.maintainResource(dto);
        Assert.assertEquals(1, rc);

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST2");
        dto.setName("Test_service_2");
        dto.setDescription("This is a description for the test web service #2");
        dto.setSecured(1);
        dto.setRequestUrl("rmt:/tetserver/test2");
        dto.setReplyMsgId("Test_service_2_Reply");
        // rc = this.dao.maintainResource(dto);
        Assert.assertEquals(1, rc);

    }

    private void deleteData() {
        int rc = 0;
        LdapResource dummy = null;
        ResourceDto dto = LdapDtoFactory.getResourceDtoInstance(dummy);

        dto.setName("Test_service_1");
        dto.setSubTypeName("TEST1");
        // rc = this.dao.deleteResource(dto);
        Assert.assertEquals(1, rc);

        dto.setName("Test_service_2");
        dto.setSubTypeName("TEST2");
        // rc = this.dao.deleteResource(dto);
        Assert.assertEquals(1, rc);

        // rc = this.dao.deleteResourceSubType("TEST1");
        Assert.assertEquals(1, rc);
        // rc = this.dao.deleteResourceSubType("TEST2");
        Assert.assertEquals(1, rc);
    }

    @Test
    public void getSingleComputerAppServerConfig() {
        List<ComputerAppServerDto> results = null;
        ComputerAppServerDto criteria = LdapDtoFactory
                .getComputerAppServerDtoInstance(null);
        criteria.setEnv("dev");
        criteria.setName("RMT2-Internet-DEV");
        try {
            results = this.dao.fetchApplicationServerInfo(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }
}
