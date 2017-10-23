package org.rmt2.api.transaction.purchases.creditor;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwXactCreditChargeList;
import org.dao.subsidiary.SubsidiaryDaoConst;
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

import com.api.persistence.AbstractDaoClientImpl;
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
        }
    }

//    @Test
//    public void testFetchSingleWithNullCustomSqlCriteria() {
//        VwXactList mockCriteria = new VwXactList();
//        mockCriteria.setId(111111);
//        try {
//            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
//                    .thenReturn(this.mockXactFetchSingleResponse);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail("single cash disbursement fetch test case setup failed");
//        }
//
//        DisbursementsApiFactory f = new DisbursementsApiFactory();
//        DisbursementsApi api = f.createApi(mockDaoClient);
//        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
//        List<XactDto> results = null;
//        criteria.setXactId(111111);
//        try {
//            results = api.get(criteria, null);
//        } catch (DisbursementsApiException e) {
//            e.printStackTrace();
//        }
//        Assert.assertNotNull(results);
//        Assert.assertEquals(1, results.size());
//
//        XactDto item = results.get(0);
//        Assert.assertEquals("reason for transaction id " + item.getXactId(),
//                item.getXactReason());
//        Assert.assertEquals(XactConst.XACT_TYPE_CASH_DISBURSE,
//                item.getXactTypeId());
//        Assert.assertEquals(XactConst.XACT_SUBTYPE_NOT_ASSIGNED,
//                item.getXactSubtypeId());
//        Assert.assertEquals("2017-01-13",
//                RMT2Date.formatDate(item.getXactDate(), "yyyy-MM-dd"));
//        Assert.assertEquals(111.11, item.getXactAmount(), 0);
//        Assert.assertEquals(200, item.getXactTenderId());
//        Assert.assertEquals("1111-1111-1111-1111", item.getXactNegInstrNo());
//        Assert.assertEquals(item.getXactDate(), item.getXactPostedDate());
//        Assert.assertEquals(String.valueOf(item.getXactDate().getTime()),
//                item.getXactConfirmNo());
//        Assert.assertEquals(item.getXactId() + item.getXactTenderId(),
//                item.getDocumentId());
//    }
//
//    @Test
//    public void testFetchSingleNotFound() {
//        VwXactList mockCriteria = new VwXactList();
//        mockCriteria.setId(999999);
//        try {
//            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
//                    .thenReturn(this.mockXactNotFoundFetchResponse);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail("single cash disbursement fetch test case setup failed");
//        }
//
//        DisbursementsApiFactory f = new DisbursementsApiFactory();
//        DisbursementsApi api = f.createApi(mockDaoClient);
//        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
//        List<XactDto> results = null;
//        criteria.setXactId(999999);
//        try {
//            results = api.get(criteria, null);
//        } catch (DisbursementsApiException e) {
//            e.printStackTrace();
//        }
//        Assert.assertNull(results);
//    }
//
//    @Test
//    public void testFetchWithNullTransactionInput() {
//        DisbursementsApiFactory f = new DisbursementsApiFactory();
//        DisbursementsApi api = f.createApi(mockDaoClient);
//        try {
//            api.get(null, null);
//            Assert.fail("Expected exception due to input value is null");
//        } catch (Exception e) {
//            Assert.assertTrue(e instanceof InvalidDataException);
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testFetchWithNoTransactionPropertiesSet() {
//        DisbursementsApiFactory f = new DisbursementsApiFactory();
//        DisbursementsApi api = f.createApi(mockDaoClient);
//        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
//        try {
//            api.get(criteria, null);
//            Assert.fail("Expected exception due to input value is null");
//        } catch (Exception e) {
//            Assert.assertTrue(e instanceof InvalidDataException);
//            e.printStackTrace();
//        }
//    }
//    
//    @Test
//    public void testFetchWithUnexpectedTransactionPropertySet() {
//        DisbursementsApiFactory f = new DisbursementsApiFactory();
//        DisbursementsApi api = f.createApi(mockDaoClient);
//        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
//        criteria.setXactReason("Reason is not a valid selection criteria property");
//        try {
//            api.get(criteria, null);
//            Assert.fail("Expected exception due to an unexpected property was set as selection criteria");
//        } catch (Exception e) {
//            Assert.assertTrue(e instanceof InvalidDataException);
//            e.printStackTrace();
//        }
//    }
//    
// 
//    @Test
//    public void testFetchWithException() {
//        VwXactList mockCriteria = new VwXactList();
//        mockCriteria.setId(999999);
//        try {
//            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
//                    .thenThrow(DatabaseException.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail("Fetch xact with exception test case setup failed");
//        }
//
//        DisbursementsApiFactory f = new DisbursementsApiFactory();
//        DisbursementsApi api = f.createApi(mockDaoClient);
//        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
//        criteria.setXactId(999999);
//        try {
//            api.get(criteria, null);
//            Assert.fail("Expected a database exception to be thrown");
//        } catch (Exception e) {
//            Assert.assertTrue(e instanceof DisbursementsApiException);
//            Assert.assertTrue(e.getCause() instanceof DisbursementsDaoException);
//            e.printStackTrace();
//        }
//    }
}