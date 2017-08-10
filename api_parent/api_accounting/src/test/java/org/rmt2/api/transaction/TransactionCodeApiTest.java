package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.XactCodes;
import org.dto.XactCodeDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.XactApi;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests transaction codes of the transaction Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TransactionCodeApiTest extends TransactionApiTestData {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    @Test
    public void testFetchAllData() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCodes.class)))
                            .thenReturn(this.mockCodeFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all transaction codes test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        List<XactCodeDto> results = null;
        try {
            results = api.getAllCode();
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactCodeDto code = results.get(ndx);
            Assert.assertEquals(code.getEntityId(), (200 + ndx));
            Assert.assertEquals(code.getEntityName(), "Transaction code " + (ndx + 1));
            Assert.assertEquals(code.getGrpId(), 10);
        }
    }

    @Test
    public void testFetchSingleData() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCodes.class)))
                            .thenReturn(this.mockCodeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single transaction codes test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        XactCodeDto results = null;
        try {
            results = api.getCode(201);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getEntityName(), "Transaction code 1");
        Assert.assertEquals(results.getGrpId(), 10);
    }
    
    @Test
    public void testFetchSingleNotFound() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCodes.class)))
                            .thenReturn(this.mockCodeNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single transaction codes test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        XactCodeDto results = null;
        try {
            results = api.getCode(9999);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchWithNullGroupId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            api.getCode(null);
            Assert.fail("Expected exception due to null input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchWithZeroGroupId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            api.getCode(0);
            Assert.fail("Expected exception due to zero input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithNegativeGroupId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            api.getCode(-100);
            Assert.fail("Expected exception due to negative input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByGroupId() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCodes.class)))
                            .thenReturn(this.mockCodeFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch transaction codes by group id test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        List<XactCodeDto> results = null;
        try {
            results = api.getCodeByGroupId(10);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactCodeDto code = results.get(ndx);
            Assert.assertEquals(code.getEntityId(), (200 + ndx));
            Assert.assertEquals(code.getEntityName(), "Transaction code " + (ndx + 1));
            Assert.assertEquals(code.getGrpId(), 10);
        }
    }
    
    @Test
    public void testFetchByGroupIdNotFound() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCodes.class)))
                            .thenReturn(this.mockCodeNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch transaction codes by group id test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        List<XactCodeDto> results = null;
        try {
            results = api.getCodeByGroupId(999);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByGroupIdWithNullGroupId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            api.getCodeByGroupId(null);
            Assert.fail("Expected exception due to null input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchByGroupIdWithZeroGroupId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            api.getCodeByGroupId(0);
            Assert.fail("Expected exception due to zero input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByGroupIdWithNegativeGroupId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            api.getCodeByGroupId(-100);
            Assert.fail("Expected exception due to negative input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
}