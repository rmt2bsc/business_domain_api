package org.rmt2.api.transaction.disbursements.cash;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.disbursements.DisbursementsApi;
import org.modules.transaction.disbursements.DisbursementsApiException;
import org.modules.transaction.disbursements.DisbursementsApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.transaction.TransactionApiTestData;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2Date;

/**
 * Tests cash disbursement transaction type item activity query Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class CashDisbursementsItemQueryApiTest extends TransactionApiTestData {

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
    public void testFetchAllByTransactionId() {
        VwXactTypeItemActivity mockCriteria = new VwXactTypeItemActivity();
        mockCriteria.setXactId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockVwXactTypeItemActivityFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all transaction type item activity extension test case setup failed");
        }

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactTypeItemActivityDto criteria = Rmt2XactDtoFactory.createXactTypeItemActivityExtInstance((VwXactTypeItemActivity) null);
        List<XactTypeItemActivityDto> results = null;
        criteria.setXactId(111111);
        try {
            results = api.getItems(criteria, null);
        } catch (DisbursementsApiException e) {
            Assert.fail("An unexpected exception occurred");
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        double totalXactAmount = 0;
        double totalActivityAmount = 0;
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactTypeItemActivityDto item = results.get(ndx);
            Assert.assertEquals(7000 + ndx, item.getXactTypeItemActvId());
            Assert.assertEquals(1111111, item.getXactId());
            Assert.assertEquals(20.00 + ndx, item.getXactAmount(), 0);
            Assert.assertEquals(19.00 + ndx, item.getActivityAmount(), 0);
            Assert.assertEquals(RMT2Date.stringToDate("2017-01-01"), item.getXactDate());
            Assert.assertEquals(100 + ndx, item.getXactTypeId());
            Assert.assertEquals(1000 + ndx, item.getXactItemId());
            Assert.assertEquals("XT-" + ndx, item.getXactTypeCode());
            Assert.assertEquals(200 + ndx, item.getXactCatgId());
            Assert.assertEquals("XC-" + ndx, item.getXactCatgCode());
            
            // Test derived values set internally by factory
            Assert.assertEquals("ItemName for " + (7000 + ndx), item.getXactTypeItemActvName());
            Assert.assertEquals("XactReason" + item.getXactId(), item.getXactReason());
            Assert.assertEquals("ConfirmationNo" + item.getXactId(), item.getXactConfirmNo());
            Assert.assertEquals(Integer.parseInt(String.valueOf(item.getXactTypeItemActvId()) + String.valueOf(item.getXactCatgId())), item.getDocumentId());
            Assert.assertEquals("XactTypeItemName" + item.getXactTypeId(), item.getXactItemName());
            Assert.assertEquals("XactTypeDescription" + item.getXactTypeId(), item.getXactTypeDescription());
            Assert.assertEquals("XactCategoryDescription" + item.getXactCatgId(), item.getXactCatgDescription());
            totalXactAmount += item.getXactAmount();
            totalActivityAmount += item.getActivityAmount();
        }
        Assert.assertEquals(110.00, totalXactAmount, 0);
        Assert.assertEquals(105.00, totalActivityAmount, 0);
    }
    
    @Test
    public void testFetchDataXactIdNotFound() {
        VwXactTypeItemActivity mockCriteria = new VwXactTypeItemActivity();
        mockCriteria.setXactId(999999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockVwXactTypeItemActivityNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all transaction type item activity extension test case setup failed");
        }

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactTypeItemActivityDto criteria = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        List<XactTypeItemActivityDto> results = null;
        criteria.setXactId(999999);
        try {
            results = api.getItems(criteria, null);
        } catch (DisbursementsApiException e) {
            Assert.fail("An unexpected exception occurred");
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchSingleWithNullInput() {
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.getItems(null, null);
            Assert.fail("Expected exception due to input value is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleWithZeroInputValue() {
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactTypeItemActivityDto criteria = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        try {
            api.getItems(criteria, null);
            Assert.fail("Expected exception due to input value is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
   
 
    @Test
    public void testFetchWithException() {
        VwXactTypeItemActivity mockCriteria = new VwXactTypeItemActivity();
        mockCriteria.setId(1111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenThrow(CannotRetrieveException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch with exception test case setup failed");
        }

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactTypeItemActivityDto criteria = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        try {
            criteria.setXactId(1111111);
            api.getItems(criteria, null);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
}