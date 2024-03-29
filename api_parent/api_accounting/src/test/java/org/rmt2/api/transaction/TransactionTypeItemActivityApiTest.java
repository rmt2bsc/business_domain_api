package org.rmt2.api.transaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dto.XactTypeItemActivityDto;
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
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests transaction type item activity Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TransactionTypeItemActivityApiTest extends TransactionApiTestData {

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
    public void testFetchDataXactId() {
        XactTypeItemActivity mockCriteria = new XactTypeItemActivity();
        mockCriteria.setXactId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactTypeItemActivityFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all transaction activity item types test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        XactTypeItemActivityDto criteria = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        List<XactTypeItemActivityDto> results = null;
        criteria.setXactId(111111);
        try {
            results = api.getXactTypeItemActivity(criteria.getXactId());
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        double totalAmount = 0;
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactTypeItemActivityDto item = results.get(ndx);
            Assert.assertEquals(7000 + (ndx + 1), item.getXactTypeItemActvId());
            Assert.assertEquals(111111, item.getXactId());
            Assert.assertEquals(600 + (ndx + 1), item.getXactItemId());
            Assert.assertEquals("Item" + (ndx + 1), item.getXactTypeItemActvName());
            totalAmount += item.getActivityAmount();
        }
        Assert.assertEquals(111.11, totalAmount, 0);
    }
    
    @Test
    public void testFetchDataXactIdNotFound() {
        XactTypeItemActivity mockCriteria = new XactTypeItemActivity();
        mockCriteria.setXactId(999999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockXactTypeItemActivityNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all transaction activity item types test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        XactTypeItemActivityDto criteria = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        List<XactTypeItemActivityDto> results = null;
        criteria.setXactId(999999);
        try {
            results = api.getXactTypeItemActivity(criteria.getXactId());
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
            api.getXactTypeItemActivity(null);
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
            api.getXactTypeItemActivity(0);
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
            api.getXactTypeItemActivity(-1234);
            Assert.fail("Expected exception due to input value is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithException() {
        XactTypeItemActivity mockCriteria = new XactTypeItemActivity();
        mockCriteria.setXactTypeItemActvId(1111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenThrow(CannotRetrieveException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact with exception test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.getXactTypeItemActivity(1111111);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
}