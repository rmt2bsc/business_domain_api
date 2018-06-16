package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.XactCategory;
import org.dto.XactCategoryDto;
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
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests general transaction Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TransactionCategoryApiTest extends TransactionApiTestData {

    

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
                    .retrieveList(any(XactCategory.class)))
                            .thenReturn(this.mockCategoryFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all transaction category test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        List<XactCategoryDto> results = null;
        try {
            results = api.getAllCategory();
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactCategoryDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEntityId(), (1000 + ndx));
            Assert.assertEquals(obj.getXactCatgId(), (1000 + ndx));
            Assert.assertEquals(obj.getEntityName(), "transaction category " + (ndx + 1));
            Assert.assertEquals(obj.getXactCatgDescription(), "transaction category " + (ndx + 1));
            Assert.assertEquals(obj.getXactCatgCode(), "catg code " + (ndx + 1));
        }
    }

    @Test
    public void testFetchSingleData() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCategory.class)))
                            .thenReturn(this.mockCategoryFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single transaction category test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        XactCategoryDto results = null;
        try {
            results = api.getCategoryById(1000);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getEntityId(), 1000);
        Assert.assertEquals(results.getXactCatgId(), 1000);
        Assert.assertEquals(results.getEntityName(), "transaction category 1");
        Assert.assertEquals(results.getXactCatgDescription(), "transaction category 1");
        Assert.assertEquals(results.getXactCatgCode(), "catg code 1");
    }
    
    @Test
    public void testFetchSingleNotFound() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCategory.class)))
                            .thenReturn(this.mockCategoryNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single transaction category test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        XactCategoryDto results = null;
        try {
            results = api.getCategoryById(1234);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchWithNullId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            api.getCategoryById(null);
            Assert.fail("Expected exception due to null input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchWithZeroId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            api.getCategoryById(0);
            Assert.fail("Expected exception due to zero input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithNegativeId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            api.getCategoryById(-1234);
            Assert.fail("Expected exception due to negative input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithException() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCategory.class)))
                            .thenThrow(CannotRetrieveException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all transaction category with exception test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        List<XactCategoryDto> results = null;
        try {
            results = api.getAllCategory();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }

}