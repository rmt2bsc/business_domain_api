package org.rmt2.api.transaction.purchases.creditor;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwXactCreditChargeList;
import org.dao.subsidiary.SubsidiaryDaoConst;
import org.dao.transaction.purchases.creditor.CreditorPurchasesDaoException;
import org.dto.XactCreditChargeDto;
import org.dto.adapter.orm.transaction.purchases.creditor.Rmt2CreditChargeDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.XactConst;
import org.modules.transaction.purchases.creditor.CreditorPurchasesApi;
import org.modules.transaction.purchases.creditor.CreditorPurchasesApiException;
import org.modules.transaction.purchases.creditor.CreditorPurchasesApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2Date;

/**
 * Tests creditor purchases transaction query Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class CreditorPurchaseQueryApiTest extends CreditPurchaseApiTestData {

    private static final String TEST_ACCOUNT_NO = "1111";
    private static final int TEST_CREDITOR_ID = 1111111;
    private VwXactCreditChargeList mockCriteria; 
    
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        this.mockCriteria = new VwXactCreditChargeList();
        this.mockCriteria.setCreditorTypeId(SubsidiaryDaoConst.CREDITOR_TYPE_CREDITOR);
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
    public void testFetchAll_WithNullCustomSqlCriteria() {
        // Mock method call to get creditor purchase transactions 
        this.mockCriteria.setAccountNo(TEST_ACCOUNT_NO);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(this.mockCriteria)))
                    .thenReturn(this.mockCreditPurchaseAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All creditor purchases transactions fetch test case setup failed");
        }

        // mock method to return contact info from the Contacts Api
        Creditor mockCredCriteria = new Creditor();
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        // Perform test
        CreditorPurchasesApiFactory f = new CreditorPurchasesApiFactory();
        CreditorPurchasesApi api = f.createApi(mockDaoClient);
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory.createCreditChargeInstance();
        List<XactCreditChargeDto> results = null;
        try {
            criteria.setAccountNumber(TEST_ACCOUNT_NO);
            results = api.get(criteria);
        } catch (CreditorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());

        double amountSeed = 20.00;
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactCreditChargeDto item = results.get(ndx);
            Assert.assertEquals("reason for transaction id " + item.getXactId(),
                    item.getXactReason());
            Assert.assertEquals(XactConst.XACT_TYPE_CREDITOR_PURCHASE,
                    item.getXactTypeId());
            Assert.assertEquals(XactConst.XACT_SUBTYPE_NOT_ASSIGNED,
                    item.getXactSubtypeId());
            Assert.assertEquals("2017-01-01",
                    RMT2Date.formatDate(item.getXactDate(), "yyyy-MM-dd"));

            // Calcuate acutal transaction amount
            double dollarAmt = (amountSeed + ndx);
            Assert.assertEquals(dollarAmt, item.getXactAmount(), 0);

            Assert.assertEquals(XactConst.TENDER_CREDITCARD, item.getXactTenderId());
            String prefix = String.valueOf(((ndx + 1) * 1111));
            Assert.assertEquals(prefix + "-0000-0000-0000", item.getXactNegInstrNo());
            Assert.assertEquals(item.getXactDate(), item.getXactPostedDate());
            Assert.assertEquals(String.valueOf(item.getXactDate().getTime()),
                    item.getXactConfirmNo());
            Assert.assertEquals(item.getXactId() + item.getXactTenderId(),
                    item.getDocumentId());
            
            // Contact info should be unavailable
            Assert.assertEquals("Company" + (ndx + 1), item.getCreditorName());
            Assert.assertEquals("3188889873", item.getPhone());
        }
    }

    @Test
    public void testFetchSingle_WithNullCustomSqlCriteria() {
        // Mock method call to get creditor purchase transactions
        this.mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(this.mockCriteria)))
                    .thenReturn(this.mockCreditPurchaseSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single creditor purchases transactions fetch test case setup failed");
        }

        // mock method to return contact info from the Contacts Api
        Creditor mockCredCriteria = new Creditor();
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetch(mockContactCritereia,
                mockCredCriteria);

        // Perform test
        CreditorPurchasesApiFactory f = new CreditorPurchasesApiFactory();
        CreditorPurchasesApi api = f.createApi(mockDaoClient);
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory
                .createCreditChargeInstance();
        List<XactCreditChargeDto> results = null;
        try {
            criteria.setCreditorId(TEST_CREDITOR_ID);
            results = api.get(criteria);
        } catch (CreditorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());

        double amountSeed = 20.00;
        XactCreditChargeDto item = results.get(0);
        Assert.assertEquals("reason for transaction id " + item.getXactId(), item.getXactReason());
        Assert.assertEquals(XactConst.XACT_TYPE_CREDITOR_PURCHASE, item.getXactTypeId());
        Assert.assertEquals(XactConst.XACT_SUBTYPE_NOT_ASSIGNED, item.getXactSubtypeId());
        Assert.assertEquals("2017-01-01", RMT2Date.formatDate(item.getXactDate(), "yyyy-MM-dd"));

        // Calculate acutal transaction amount
        Assert.assertEquals(amountSeed, item.getXactAmount(), 0);

        Assert.assertEquals(XactConst.TENDER_CREDITCARD, item.getXactTenderId());
        Assert.assertEquals("1111-0000-0000-0000", item.getXactNegInstrNo());
        Assert.assertEquals(item.getXactDate(), item.getXactPostedDate());
        Assert.assertEquals(String.valueOf(item.getXactDate().getTime()), item.getXactConfirmNo());
        Assert.assertEquals(item.getXactId() + item.getXactTenderId(), item.getDocumentId());
        
        // Contact info should be unavailable
        Assert.assertEquals("Company1", item.getCreditorName());
        Assert.assertEquals("3188889873", item.getPhone());
    }

    @Test
    public void testFetch_NotFound() {
        // Mock method call to get creditor purchase transactions
        this.mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(this.mockCriteria)))
                    .thenReturn(this.mockCreditPurchaseNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single creditor purchases transactions fetch test case setup failed");
        }

        // Perform test
        CreditorPurchasesApiFactory f = new CreditorPurchasesApiFactory();
        CreditorPurchasesApi api = f.createApi(mockDaoClient);
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory
                .createCreditChargeInstance();
        List<XactCreditChargeDto> results = null;
        try {
            criteria.setCreditorId(TEST_CREDITOR_ID);
            results = api.get(criteria);
        } catch (CreditorPurchasesApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchWithNullTransactionInput() {
        CreditorPurchasesApiFactory f = new CreditorPurchasesApiFactory();
        CreditorPurchasesApi api = f.createApi(mockDaoClient);
        XactCreditChargeDto criteria = null;
        try {
            api.get(criteria);
            Assert.fail("Expected exception due to input value is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetch_WithTransactionFetchException() {
        // Mock method call to get creditor purchase transactions
        this.mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(this.mockCriteria)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor purchases transactions fetch test case setup failed");
        }

        // Perform test
        CreditorPurchasesApiFactory f = new CreditorPurchasesApiFactory();
        CreditorPurchasesApi api = f.createApi(mockDaoClient);
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory
                .createCreditChargeInstance();
        try {
            criteria.setCreditorId(TEST_CREDITOR_ID);
            api.get(criteria);
            Assert.fail("Expected a database exception to be thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof CreditorPurchasesDaoException);
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testFetch_WithContactsFetchDbException() {
        // Mock method call to get creditor purchase transactions 
        this.mockCriteria.setAccountNo(TEST_ACCOUNT_NO);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(this.mockCriteria)))
                    .thenReturn(this.mockCreditPurchaseAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All creditor purchases transactions fetch test case setup failed");
        }

        // mock method to return contact info from the Contacts Api
        Creditor mockCredCriteria = new Creditor();
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetchDbException(mockContactCritereia, mockCredCriteria);
        
        // Perform test
        CreditorPurchasesApiFactory f = new CreditorPurchasesApiFactory();
        CreditorPurchasesApi api = f.createApi(mockDaoClient);
        XactCreditChargeDto criteria = Rmt2CreditChargeDtoFactory.createCreditChargeInstance();
        List<XactCreditChargeDto> results = null;
        try {
            criteria.setAccountNumber(TEST_ACCOUNT_NO);
            results = api.get(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());

        double amountSeed = 20.00;
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactCreditChargeDto item = results.get(ndx);
            Assert.assertEquals("reason for transaction id " + item.getXactId(), item.getXactReason());
            Assert.assertEquals(XactConst.XACT_TYPE_CREDITOR_PURCHASE, item.getXactTypeId());
            Assert.assertEquals(XactConst.XACT_SUBTYPE_NOT_ASSIGNED, item.getXactSubtypeId());
            Assert.assertEquals("2017-01-01", RMT2Date.formatDate(item.getXactDate(), "yyyy-MM-dd"));

            // Calculate acutal transaction amount
            double dollarAmt = (amountSeed + ndx);
            Assert.assertEquals(dollarAmt, item.getXactAmount(), 0);

            Assert.assertEquals(XactConst.TENDER_CREDITCARD, item.getXactTenderId());
            String prefix = String.valueOf(((ndx + 1) * 1111));
            Assert.assertEquals(prefix + "-0000-0000-0000", item.getXactNegInstrNo());
            Assert.assertEquals(item.getXactDate(), item.getXactPostedDate());
            Assert.assertEquals(String.valueOf(item.getXactDate().getTime()), item.getXactConfirmNo());
            Assert.assertEquals(item.getXactId() + item.getXactTenderId(), item.getDocumentId());
            
            // Contact info should be unavailable
            Assert.assertEquals("Unavailable", item.getCreditorName());
            Assert.assertEquals("Unavailable", item.getPhone());
        }
    }
}