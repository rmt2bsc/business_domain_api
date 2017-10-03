package org.rmt2.api.transaction.cashdisbursements;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.transaction.disbursements.DisbursementsDaoException;
import org.dto.XactDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.XactConst;
import org.modules.transaction.disbursements.DisbursementsApi;
import org.modules.transaction.disbursements.DisbursementsApiException;
import org.modules.transaction.disbursements.DisbursementsApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.transaction.TransactionApiTestData;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2Date;

/**
 * Tests cash disbursements transaction query Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class CashDisbursementsQueryApiTest extends TransactionApiTestData {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        // Set transction types to cash disbursements for expenses
        // and set xact sub type id to "not assigned"

        // fetch all mock response.
        for (VwXactList item : this.mockXactFetchAllResponse) {
            item.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
            item.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
        }

        // Handle single mock response
        VwXactList item = this.mockXactFetchSingleResponse.get(0);
        item.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
        item.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
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
    public void testFetchAllWithNullCustomSqlCriteria() {
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockXactFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All cash disbursement fetch test case setup failed");
        }

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        List<XactDto> results = null;
        try {
            criteria.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
            results = api.getByTransaction(criteria, null);
        } catch (DisbursementsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());

        int monthSeed = 1;
        int daySeed = 13;
        int amountSeed = 111;
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactDto item = results.get(ndx);
            Assert.assertEquals("reason for transaction id " + item.getXactId(),
                    item.getXactReason());
            Assert.assertEquals(XactConst.XACT_TYPE_CASHDISBEXP,
                    item.getXactTypeId());
            Assert.assertEquals(XactConst.XACT_SUBTYPE_NOT_ASSIGNED,
                    item.getXactSubtypeId());

            // Calcuate acutal transaction date
            String month = "0" + (ndx + monthSeed);
            String day = String.valueOf(ndx + daySeed);
            String dt = "2017-" + month + "-" + day;
            Assert.assertEquals(dt,
                    RMT2Date.formatDate(item.getXactDate(), "yyyy-MM-dd"));

            // Calcuate acutal transaction amount
            double dollarAmt = (amountSeed * (ndx + 1)) + 0.11;
            Assert.assertEquals(dollarAmt, item.getXactAmount(), 0);

            Assert.assertEquals(200, item.getXactTenderId());
            Assert.assertNotNull(item.getXactNegInstrNo());
            Assert.assertEquals(item.getXactDate(), item.getXactPostedDate());
            Assert.assertEquals(String.valueOf(item.getXactDate().getTime()),
                    item.getXactConfirmNo());
            Assert.assertEquals(item.getXactId() + item.getXactTenderId(),
                    item.getDocumentId());
        }
    }

    @Test
    public void testFetchSingleWithNullCustomSqlCriteria() {
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockXactFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("single cash disbursement fetch test case setup failed");
        }

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        List<XactDto> results = null;
        criteria.setXactId(111111);
        try {
            results = api.getByTransaction(criteria, null);
        } catch (DisbursementsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());

        XactDto item = results.get(0);
        Assert.assertEquals("reason for transaction id " + item.getXactId(),
                item.getXactReason());
        Assert.assertEquals(XactConst.XACT_TYPE_CASHDISBEXP,
                item.getXactTypeId());
        Assert.assertEquals(XactConst.XACT_SUBTYPE_NOT_ASSIGNED,
                item.getXactSubtypeId());
        Assert.assertEquals("2017-01-13",
                RMT2Date.formatDate(item.getXactDate(), "yyyy-MM-dd"));
        Assert.assertEquals(111.11, item.getXactAmount(), 0);
        Assert.assertEquals(200, item.getXactTenderId());
        Assert.assertEquals("1111-1111-1111-1111", item.getXactNegInstrNo());
        Assert.assertEquals(item.getXactDate(), item.getXactPostedDate());
        Assert.assertEquals(String.valueOf(item.getXactDate().getTime()),
                item.getXactConfirmNo());
        Assert.assertEquals(item.getXactId() + item.getXactTenderId(),
                item.getDocumentId());
    }

    @Test
    public void testFetchSingleNotFound() {
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(999999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockXactNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("single cash disbursement fetch test case setup failed");
        }

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        List<XactDto> results = null;
        criteria.setXactId(999999);
        try {
            results = api.getByTransaction(criteria, null);
        } catch (DisbursementsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchWithNullTransactionInput() {
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.getByTransaction(null, null);
            Assert.fail("Expected exception due to input value is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchWithNoTransactionPropertiesSet() {
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        try {
            api.getByTransaction(criteria, null);
            Assert.fail("Expected exception due to input value is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithUnexpectedTransactionPropertySet() {
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        criteria.setXactReason("Reason is not a valid selection criteria property");
        try {
            api.getByTransaction(criteria, null);
            Assert.fail("Expected exception due to an unexpected property was set as selection criteria");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
 
    @Test
    public void testFetchWithException() {
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(999999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch xact with exception test case setup failed");
        }

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        criteria.setXactId(999999);
        try {
            api.getByTransaction(criteria, null);
            Assert.fail("Expected a database exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof DisbursementsDaoException);
            e.printStackTrace();
        }
    }
}