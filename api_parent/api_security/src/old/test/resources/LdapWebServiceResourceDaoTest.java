package old.tests.resources;

import java.util.List;

import junit.framework.Assert;

import org.dao.mapping.orm.ldap.LdapResource;
import org.dao.mapping.orm.ldap.LdapWebService;
import org.dao.resources.ResourceDao;
import org.dao.resources.ResourcesDaoFactory;
import org.dto.ResourceDto;
import org.dto.WebServiceDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Resource LDAP DAO implementation.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapWebServiceResourceDaoTest {

    private ResourcesDaoFactory f;

    private ResourceDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new ResourcesDaoFactory();
        this.dao = this.f.createLdapDao();
        this.dao.setDaoUser("testuser");
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
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
        rc = this.dao.maintainResourceSubType(dto);
        Assert.assertEquals(1, rc);

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST2");
        dto.setSubTypeDescription("This is a description for resource sub type #2");
        rc = this.dao.maintainResourceSubType(dto);
        Assert.assertEquals(1, rc);

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST1");
        dto.setName("Test_service_1");
        dto.setDescription("This is a description for the test web service #1");
        dto.setSecured(1);
        dto.setRequestUrl("rmt:/tetserver/test1");
        dto.setReplyMsgId("Test_service_1_Reply");
        rc = this.dao.maintainResource(dto);
        Assert.assertEquals(1, rc);

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST2");
        dto.setName("Test_service_2");
        dto.setDescription("This is a description for the test web service #2");
        dto.setSecured(1);
        dto.setRequestUrl("rmt:/tetserver/test2");
        dto.setReplyMsgId("Test_service_2_Reply");
        rc = this.dao.maintainResource(dto);
        Assert.assertEquals(1, rc);

    }

    private void deleteData() {
        int rc = 0;
        LdapResource dummy = null;
        ResourceDto dto = LdapDtoFactory.getResourceDtoInstance(dummy);

        dto.setName("Test_service_1");
        dto.setSubTypeName("TEST1");
        rc = this.dao.deleteResource(dto);
        Assert.assertEquals(1, rc);

        dto.setName("Test_service_2");
        dto.setSubTypeName("TEST2");
        rc = this.dao.deleteResource(dto);
        Assert.assertEquals(1, rc);

        rc = this.dao.deleteResourceSubType("TEST1");
        Assert.assertEquals(1, rc);
        rc = this.dao.deleteResourceSubType("TEST2");
        Assert.assertEquals(1, rc);
    }

    @Test
    public void getAllWebServiceSubTypes() {
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchResourceSubType();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getSingleWebServiceSubType() {
        ResourceDto results = null;
        try {
            results = this.dao.fetchResourceSubType("RMI");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("RMI", results.getSubTypeName());
    }

    @Test
    public void getAllWebServiceEntriesUsingNullCriteria() {
        List<ResourceDto> results = null;
        try {
            results = this.dao.fetchResource(null);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getAllWebServiceEntriesUsingValidCriteria() {
        List<ResourceDto> results = null;
        try {
            LdapWebService dummy = null;
            WebServiceDto criteria = LdapDtoFactory
                    .getWebServiceResourceDtoInstance(dummy);
            results = this.dao.fetchResource(criteria);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getWebServiceEntriesBySubTypeName() {
        List<ResourceDto> results = null;
        try {
            LdapWebService dummy = null;
            WebServiceDto criteria = LdapDtoFactory
                    .getWebServiceResourceDtoInstance(dummy);
            criteria.setSubTypeName("SOAP");
            results = this.dao.fetchResource(criteria);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getWebServiceEntryByNameUsingFilter() {
        List<ResourceDto> results = null;
        try {
            LdapWebService dummy = null;
            WebServiceDto criteria = LdapDtoFactory
                    .getWebServiceResourceDtoInstance(dummy);
            criteria.setName("RQ_accounting_invoice_sales_order");
            results = this.dao.fetchResource(criteria);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void getWebServiceEntryUsingMultipleAttributeFilter() {
        List<ResourceDto> results = null;
        try {
            LdapWebService dummy = null;
            WebServiceDto criteria = LdapDtoFactory
                    .getWebServiceResourceDtoInstance(dummy);
            criteria.setName("RQ_accounting_invoice_sales_order");
            criteria.setSecured(1);
            criteria.setQuerySecureFlag(true);
            results = this.dao.fetchResource(criteria);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void getAllSecuredSoapWebServiceEntriesUsingFilter() {
        List<ResourceDto> results = null;
        try {
            LdapWebService dummy = null;
            WebServiceDto criteria = LdapDtoFactory
                    .getWebServiceResourceDtoInstance(dummy);
            criteria.setSubTypeName("SOAP");
            criteria.setSecured(1);
            criteria.setQuerySecureFlag(true);
            results = this.dao.fetchResource(criteria);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getAllSecuredWebServiceEntriesUsingFilter() {
        List<ResourceDto> results = null;
        try {
            LdapWebService dummy = null;
            WebServiceDto criteria = LdapDtoFactory
                    .getWebServiceResourceDtoInstance(dummy);
            criteria.setSecured(1);
            criteria.setQuerySecureFlag(true);
            results = this.dao.fetchResource(criteria);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void createWebServiceSubTypeWithInvalidData() {
        LdapResource dummy = null;
        ResourceDto dto = LdapDtoFactory.getResourceDtoInstance(dummy);
        dto.setSubTypeDescription("This is a description");
        try {
            this.dao.maintainResourceSubType(dto);
            Assert.fail("Test failed.  Should have produced an exception regarding web service sub type name was absent");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dto = LdapDtoFactory.getResourceDtoInstance(dummy);
        dto.setSubTypeName("InvalidTest");
        try {
            this.dao.maintainResourceSubType(dto);
            Assert.fail("Test failed.  Should have produced an exception regarding web service sub type description was absent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createWebServiceWithInvalidData() {
        LdapWebService dummy = null;
        WebServiceDto dto = LdapDtoFactory
                .getWebServiceResourceDtoInstance(dummy);

        dto.setName("Test_service_3");
        dto.setDescription("This is a description for the test web service #3");
        dto.setSecured(1);
        dto.setRequestUrl("rmt:/tetserver/test1");
        dto.setReplyMsgId("Test_service_3_Reply");
        try {
            this.dao.maintainResource(dto);
            Assert.fail("Test failed.  Should have produced an exception regarding the abscence of web service attribute, sub type name");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST2");
        dto.setDescription("This is a description for the test web service #1");
        dto.setSecured(1);
        dto.setRequestUrl("rmt:/tetserver/test1");
        dto.setReplyMsgId("Test_service_1_Reply");
        try {
            this.dao.maintainResource(dto);
            Assert.fail("Test failed.  Should have produced an exception regarding the abscence of web service attribute, name");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST2");
        dto.setName("Test_service_3");
        dto.setSecured(1);
        dto.setRequestUrl("rmt:/tetserver/test1");
        dto.setReplyMsgId("Test_service_1_Reply");
        try {
            this.dao.maintainResource(dto);
            Assert.fail("Test failed.  Should have produced an exception regarding the abscence of web service attribute, description");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST2");
        dto.setName("Test_service_3");
        dto.setDescription("This is a description for the test web service #1");
        dto.setSecured(4);
        dto.setRequestUrl("rmt:/tetserver/test1");
        dto.setReplyMsgId("Test_service_1_Reply");
        try {
            this.dao.maintainResource(dto);
            Assert.fail("Test failed.  Should have produced an exception regarding an invalid value for web service attribute, isSecured");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dto = LdapDtoFactory.getWebServiceResourceDtoInstance(dummy);
        dto.setSubTypeName("TEST2");
        dto.setName("Test_service_3");
        dto.setDescription("This is a description for the test web service #1");
        dto.setSecured(1);
        dto.setReplyMsgId("Test_service_1_Reply");
        try {
            this.dao.maintainResource(dto);
            Assert.fail("Test failed.  Should have produced an exception regarding the abscence of web service attribute, request URL");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
