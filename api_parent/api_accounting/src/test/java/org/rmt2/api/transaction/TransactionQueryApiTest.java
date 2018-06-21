package org.rmt2.api.transaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.Xact;
import org.dto.XactDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.XactApi;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactApiFactory;
import org.modules.transaction.XactConst;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2Date;

/**
 * Tests general transaction query Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TransactionQueryApiTest extends TransactionApiTestData {

    

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
    public void testFetchSingleData() {
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        XactDto results = null;
        criteria.setXactId(111111);
        try {
            results = api.getXactById(111111);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(111111, results.getXactId());
        Assert.assertEquals("reason for transaction id " + results.getXactId(), results.getXactReason());
        Assert.assertEquals(XactConst.XACT_TYPE_CREDITOR_PURCHASE, results.getXactTypeId());
        Assert.assertEquals(XactConst.XACT_SUBTYPE_NOT_ASSIGNED, results.getXactSubtypeId());
        Assert.assertEquals("2017-01-13", RMT2Date.formatDate(results.getXactDate(), "yyyy-MM-dd"));
        Assert.assertEquals(111.11, results.getXactAmount(), 0);
        Assert.assertEquals(200, results.getXactTenderId());
        Assert.assertNotNull(results.getXactNegInstrNo());
        Assert.assertEquals(results.getXactDate(), results.getXactPostedDate());
        Assert.assertEquals(String.valueOf(results.getXactDate().getTime()), results.getXactConfirmNo());
        Assert.assertEquals(results.getXactId() + results.getXactTenderId(), results.getDocumentId());
    }
    
    @Test
    public void testFetchSingleReturnTooManyRows() {
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        criteria.setXactId(111111);
        try {
            api.getXactById(111111);
            Assert.fail("Expected exception due to too many rows returned");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchSingleNotFound() {
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact not found test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        XactDto results = null;
        criteria.setXactId(111111);
        try {
            results = api.getXactById(111111);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchSingleWithNullInput() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.getXactById(null);
            Assert.fail("Expected exception due to input value is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleWithZeroInputValue() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.getXactById(0);
            Assert.fail("Expected exception due to input value is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
   
    @Test
    public void testFetchSingleWithNegativeInputValue() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.getXactById(-1234);
            Assert.fail("Expected exception due to input value is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithException() {
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenThrow(CannotRetrieveException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact with exception test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        XactDto results = null;
        criteria.setXactId(111111);
        try {
            results = api.getXactById(111111);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
}