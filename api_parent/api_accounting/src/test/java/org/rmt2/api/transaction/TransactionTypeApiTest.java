package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.XactType;
import org.dto.XactTypeDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
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
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests transaction type Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TransactionTypeApiTest extends TransactionApiTestData {

    

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
        XactType mockCriteria = new XactType();
        mockCriteria.setXactTypeId(301);
        try {
            when(this.mockPersistenceClient.retrieveList(any(XactType.class)))
                            .thenReturn(this.mockXactTypeFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all xact type test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        List<XactTypeDto> results = null;
        try {
            results = api.getAllXactType();
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactTypeDto o = results.get(ndx);
            Assert.assertEquals((300 + ndx), o.getXactTypeId());
            Assert.assertEquals(1000, o.getXactCatgId());
            Assert.assertEquals("TransactionType" + (ndx + 1), o.getXactTypeDescription());
            Assert.assertEquals("TransactionTypeCode" + (ndx + 1), o.getXactTypeCode());
            Assert.assertEquals(1, o.getXactTypeToMultiplier());
            Assert.assertEquals(-1, o.getXactTypeFromMultiplier());
            Assert.assertEquals(400, o.getXactTypeToAcctTypeId());
            Assert.assertEquals(401, o.getXactTypeFromAcctTypeId());
            Assert.assertEquals(200, o.getXactTypeToAcctCatgId());
            Assert.assertEquals(222, o.getXactTypeFromAcctCatgId());
            Assert.assertEquals(1, o.getXactTypeHasSubsidiary());
        }
    }
    
    @Test
    public void testFetchAllNoDataReturned() {
        XactType mockCriteria = new XactType();
        mockCriteria.setXactTypeId(301);
        try {
            when(this.mockPersistenceClient.retrieveList(any(XactType.class)))
                            .thenReturn(this.mockXactTypeNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all xact type test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        List<XactTypeDto> results = null;
        try {
            results = api.getAllXactType();
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchSingleData() {
        XactType mockCriteria = new XactType();
        mockCriteria.setXactTypeId(301);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact type test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
        XactTypeDto results = null;
        criteria.setXactTypeId(301);
        try {
            results = api.getXactType(301);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(301, results.getXactTypeId());
        Assert.assertEquals(1000, results.getXactCatgId());
        Assert.assertEquals("Transaction Type 1", results.getXactTypeDescription());
        Assert.assertEquals("transaction type code 1", results.getXactTypeCode());
        Assert.assertEquals(1, results.getXactTypeToMultiplier());
        Assert.assertEquals(-1, results.getXactTypeFromMultiplier());
        Assert.assertEquals(400, results.getXactTypeToAcctTypeId());
        Assert.assertEquals(401, results.getXactTypeFromAcctTypeId());
        Assert.assertEquals(200, results.getXactTypeToAcctCatgId());
        Assert.assertEquals(222, results.getXactTypeFromAcctCatgId());
        Assert.assertEquals(1, results.getXactTypeHasSubsidiary());
    }
    
    @Test
    public void testFetchSingleReturnTooManyRows() {
        XactType mockCriteria = new XactType();
        mockCriteria.setXactTypeId(301);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactTypeFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all xact type too  many rows returned test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
        criteria.setXactTypeId(301);
        try {
            api.getXactType(301);
            Assert.fail("Expected exception due to too many rows returned");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchSingleNotFound() {
        XactType mockCriteria = new XactType();
        mockCriteria.setXactTypeId(301);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactTypeNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact type not found test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
        XactTypeDto results = null;
        criteria.setXactTypeId(301);
        try {
            results = api.getXactType(301);
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
            api.getXactType(null);
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
            api.getXactType(0);
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
            api.getXactType(-1234);
            Assert.fail("Expected exception due to input value is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByCategoryData() {
        XactType mockCriteria = new XactType();
        mockCriteria.setXactCatgId(1000);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactTypeFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all xact type by category id test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        List<XactTypeDto> results = null;
        XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
        criteria.setXactCatgId(1000);
        try {
            results = api.getXactTypeByCatgId(criteria.getXactCatgId());
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactTypeDto o = results.get(ndx);
            Assert.assertEquals((300 + ndx), o.getXactTypeId());
            Assert.assertEquals(1000, o.getXactCatgId());
            Assert.assertEquals("TransactionType" + (ndx + 1), o.getXactTypeDescription());
            Assert.assertEquals("TransactionTypeCode" + (ndx + 1), o.getXactTypeCode());
            Assert.assertEquals(1, o.getXactTypeToMultiplier());
            Assert.assertEquals(-1, o.getXactTypeFromMultiplier());
            Assert.assertEquals(400, o.getXactTypeToAcctTypeId());
            Assert.assertEquals(401, o.getXactTypeFromAcctTypeId());
            Assert.assertEquals(200, o.getXactTypeToAcctCatgId());
            Assert.assertEquals(222, o.getXactTypeFromAcctCatgId());
            Assert.assertEquals(1, o.getXactTypeHasSubsidiary());
        }
    }
    
    @Test
    public void testFetchByCategoryNotFound() {
        XactType mockCriteria = new XactType();
        mockCriteria.setXactCatgId(5555);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactTypeNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all xact type by category id not found test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        List<XactTypeDto> results = null;
        XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
        criteria.setXactCatgId(5555);
        try {
            results = api.getXactTypeByCatgId(criteria.getXactCatgId());
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
   }
    
    @Test
    public void testFetchByCategoryWithNullInput() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.getXactTypeByCatgId(null);
            Assert.fail("Expected exception due to input value is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByCategoryWithZeroInputValue() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.getXactTypeByCatgId(0);
            Assert.fail("Expected exception due to input value is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
   
    @Test
    public void testFetchByCategoryWithNegativeInputValue() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.getXactTypeByCatgId(-1234);
            Assert.fail("Expected exception due to input value is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchDaoException() {
        XactType mockCriteria = new XactType();
        mockCriteria.setXactTypeId(301);
        try {
            when(this.mockPersistenceClient.retrieveList(any(XactType.class)))
                            .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all xact type DAO exception test case setup failed");
        }
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.getXactTypeByCatgId(301);
            Assert.fail("Expected exception due to DAO exception");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
    
    
}