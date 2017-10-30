package org.rmt2.api.subsidiary.creditor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.AccountingConst;
import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.CreditorType;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCreditorXactHist;
import org.dao.subsidiary.CreditorDaoException;
import org.dto.CreditorDto;
import org.dto.CreditorTypeDto;
import org.dto.CreditorXactHistoryDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.CommonAccountingConst;
import org.modules.generalledger.GeneralLedgerApiException;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.CreditorApiException;
import org.modules.subsidiary.NewCreditorSetupFailureException;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.subsidiary.SubsidiaryException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.AccountingMockDataUtility;
import org.rmt2.api.subsidiary.SubsidiaryApiTestData;
import org.rmt2.jaxb.BusinessType;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Creditor Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class CreditorApiTest extends SubsidiaryApiTestData {

    

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
    public void testFetchAllNoContactData() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(Creditor.class)))
                            .thenReturn(this.mockCreditorFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all creditors test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            CreditorDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEntityId(), (200 + ndx));
            Assert.assertEquals(obj.getCreditorId(), (200 + ndx));
            Assert.assertEquals(obj.getContactId(), (1351 + ndx));
            Assert.assertEquals(obj.getAccountNo(), "C123458" + ndx);
            Assert.assertEquals(obj.getExtAccountNumber(), "7437437JDJD848" + ndx);
            Assert.assertNull(obj.getEntityName());
            Assert.assertNull(obj.getContactName());
        }
    }

    @Test
    public void testFetchSingleNoContactData() {
        Creditor mockCriteria = new Creditor();
        mockCriteria.setCreditorId(200);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto results = null;
        try {
            results = api.get(200);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getEntityId(), (200));
        Assert.assertEquals(results.getCreditorId(), (200));
        Assert.assertEquals(results.getContactId(), (1351));
        Assert.assertEquals(results.getAccountNo(), "C1234589");
        Assert.assertEquals(results.getExtAccountNumber(), "123-456-789");
        Assert.assertNull(results.getEntityName());
        Assert.assertNull(results.getContactName());
    }
    
    @Test
    public void testFetchSingleNotFound() {
        Creditor mockCriteria = new Creditor();
        mockCriteria.setCreditorId(999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto results = null;
        try {
            results = api.get(999);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchSingleWithNullCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.get((Integer) null);
            Assert.fail("Expected exception due to null selection criteria object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleWithZeroCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.get(0);
            Assert.fail("Expected exception due to input argument is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleWithNegativeCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.get(-100);
            Assert.fail("Expected exception due to input argument is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithNullCriteriaObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.get((CreditorDto) null);
            Assert.fail("Expected exception due to null selection criteria object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchByAccountNumber() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setAccountNumber("C1234589");
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        List<CreditorDto> results = null;
        try {
            results = api.getByAcctNo("C1234589");
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }
  
    @Test
    public void testFetchByAccountNumberNotFound() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setAccountNumber("C1234589");
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        List<CreditorDto> results = null;
        try {
            results = api.getByAcctNo("C1234589");
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByAccountNumberWithNullValue() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByAcctNo(null);
            Assert.fail("Expected exception due to null account number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByBusinessId() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setBusinessId(1351);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto results = null;
        try {
            results = api.getByBusinessId(1351);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("C1234589", results.getAccountNo());
    }
    
    @Test
    public void testFetchByBusinessIdNotFound() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setBusinessId(1351);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto results = null;
        try {
            results = api.getByBusinessId(1351);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByBusinessIdTooManyReturned() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setBusinessId(1351);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByBusinessId(1351);
            Assert.fail("Expected exception due to too many creditor records returned");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNullBusinessId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByBusinessId(null);
            Assert.fail("Expected exception due to null business id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByZeroBusinessId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByBusinessId(0);
            Assert.fail("Expected exception due to business id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNegativeBusinessId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByBusinessId(0);
            Assert.fail("Expected exception due to negative business id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByCreditorId() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setCreditorId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto results = null;
        try {
            results = api.getByCreditorId(200);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("C1234589", results.getAccountNo());
    }
    
    @Test
    public void testFetchByCreditorIdNotFound() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setCreditorId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto results = null;
        try {
            results = api.getByCreditorId(200);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByCreditorIdTooManyReturned() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setCreditorId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCreditorId(200);
            Assert.fail("Expected exception due to too many creditor records returned");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNullCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCreditorId(null);
            Assert.fail("Expected exception due to null creditor id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByZeroCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCreditorId(0);
            Assert.fail("Expected exception due to creditor id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNegativeCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCreditorId(0);
            Assert.fail("Expected exception due to negative creditor id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByUID() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setCreditorId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto results = null;
        try {
            results = api.getByUid(200);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("C1234589", results.getAccountNo());
    }
    
    @Test
    public void testFetchByUIDNotFound() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setCreditorId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto results = null;
        try {
            results = api.getByUid(200);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByUIDNotFoundTooManyReturned() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setCreditorId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByUid(200);
            Assert.fail("Expected exception due to too many creditor records returned");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNullUID() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByUid(null);
            Assert.fail("Expected exception due to null UID");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByZeroUID() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByUid(0);
            Assert.fail("Expected exception due to UID equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNegativeUID() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByUid(0);
            Assert.fail("Expected exception due to negative UID");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByCreditorType() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setCreditorTypeId(22);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        List<CreditorDto> results = null;
        try {
            results = api.getByCreditorType(22);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("C1234589", results.get(0).getAccountNo());
    }
    
    @Test
    public void testFetchByCreditorTypeNotFound() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setCreditorTypeId(22);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        List<CreditorDto> results = null;
        try {
            results = api.getByCreditorType(22);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByNullCreditorType() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCreditorType(null);
            Assert.fail("Expected exception due to null creditor type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByZeroCreditorType() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCreditorType(0);
            Assert.fail("Expected exception due to creditor type id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNegativeCreditorType() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCreditorType(0);
            Assert.fail("Expected exception due to negative creditor type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetCreditorBalance() {
        ResultSet mockResulstSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(any(String.class)))
                            .thenReturn(mockResulstSet);
            when(mockResulstSet.next()).thenReturn(true);
            when(mockResulstSet.getDouble("balance")).thenReturn(7777777.77);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch creditor balance test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
       double results = 0;
        try {
            results = api.getBalance(1350);
        } catch (SubsidiaryException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(7777777.77, results, 0);
    }

    @Test
    public void testGetCreditorBalanceNoDataFound() {
        ResultSet mockResulstSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(any(String.class)))
                            .thenReturn(mockResulstSet);
            when(mockResulstSet.next()).thenReturn(false);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch creditor balance test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
       double results = 0;
        try {
            results = api.getBalance(1350);
        } catch (SubsidiaryException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(0, results, 0);
    }
    
    @Test
    public void testGetCreditorBalanceWithNullCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getBalance(null);
            Assert.fail("Expected exception due to negative creditor id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetCreditorBalanceWithZeroCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getBalance(0);
            Assert.fail("Expected exception due to creditor id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetCreditorBalanceWithNegativeCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getBalance(-1350);
            Assert.fail("Expected exception due to creditor id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchAllCreditorTypes() {
        CreditorType mockCredCriteria = new CreditorType();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCredCriteria))).thenReturn(
                            this.mockCreditorTypeFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All creditor type fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        List<CreditorTypeDto> results = null;
        try {
            results = api.getCreditorType();
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        Assert.assertEquals("Creditor Type 3", results.get(2).getEntityName());
    }
    
    @Test
    public void testFetchSingleCreditorType() {
        CreditorType mockCredCriteria = new CreditorType();
        mockCredCriteria.setCreditorTypeId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCredCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorTypeDto results = null;
        try {
            results = api.getCreditorType(100);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("Creditor Type 1", results.getEntityName());
    }
    
    @Test
    public void testFetchSingleCreditorTypeNotFound() {
        CreditorType mockCredCriteria = new CreditorType();
        mockCredCriteria.setCreditorTypeId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCredCriteria))).thenReturn(
                            this.mockCreditorTypeNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorTypeDto results = null;
        try {
            results = api.getCreditorType(100);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchSingleCreditorTypeTooManyReturned() {
        CreditorType mockCredCriteria = new CreditorType();
        mockCredCriteria.setCreditorTypeId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCredCriteria))).thenReturn(
                            this.mockCreditorTypeFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Too many returned creditor type fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getCreditorType(100);
            Assert.fail("Expected exception due to too many creditor type records returned");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleCreditorTypeWithNullId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getCreditorType(null);
            Assert.fail("Expected exception due to creditor type id argument is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleCreditorTypeWithZeroId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getCreditorType(0);
            Assert.fail("Expected exception due to creditor type id argument is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleCreditorTypeWithNegativeId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getCreditorType(-444);
            Assert.fail("Expected exception due to creditor type id argument is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchTransactionHistory() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCreditorXactHist.class))).thenReturn(
                            this.mockCreditorXactHistoryResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Creditor transaction history fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        List<CreditorXactHistoryDto> results = null;
        try {
            results = api.getTransactionHistory(100);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        Assert.assertEquals(25.67, results.get(3).getXactAmount(), 0);
    }
    
    @Test
    public void testFetchTransactionHistoryNotFound() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCreditorXactHist.class))).thenReturn(
                            null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Creditor transaction history fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        List<CreditorXactHistoryDto> results = null;
        try {
            results = api.getTransactionHistory(100);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchTransactionHistoryWithNullCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getTransactionHistory(null);
            Assert.fail("Expected exception due to creditor id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchTransactionHistoryWithZeroCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getTransactionHistory(0);
            Assert.fail("Expected exception due to creditor id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchTransactionHistoryWithNegativeCreditorId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getTransactionHistory(-123);
            Assert.fail("Expected exception due to creditor id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateNewCreditor() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTPAY);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(
                    this.mockSingleCreditorGLAccountFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch test case setup failed");
        }

        Creditor cred = AccountingMockDataUtility.createMockOrmCreditor(0, 4000, 1234, "GL_200", "932-392-339", 1);
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(4000, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        int newCreditorId = 1350;
        try {
            when(this.mockPersistenceClient.insertRow(any(Creditor.class), eq(true))).thenReturn(newCreditorId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Creditor insertRow test case setup failed");
        }

        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(cred, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.update(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(newCreditorId, rc);
    }

    @Test
    public void testCreateNewCreditorWithGLAccountFetchException() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTPAY);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenThrow(
                    new GeneralLedgerApiException("Database error occurred fetching GL Account data"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Creditor cred = AccountingMockDataUtility.createMockOrmCreditor(0, 4000, 1234, "GL_200", "932-392-339", 1);
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(4000, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(cred, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to general database error occurre while fetching GL Account information");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NewCreditorSetupFailureException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCreditorWithGLAccountNotFound() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTPAY);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Creditor cred = AccountingMockDataUtility.createMockOrmCreditor(0, 4000, 1234, "GL_200", "932-392-339", 1);
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(4000, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(cred, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to fetching GL Account information was not found");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NewCreditorSetupFailureException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCreditorWithFetchedGLAccountTypeIdInvalid() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTPAY);
        
        int invalidAcctTypeId = 5;
        List<GlAccounts> mockGLAccountListResults = new ArrayList<GlAccounts>();
        GlAccounts p = AccountingMockDataUtility.createMockOrmGlAccounts(1234, invalidAcctTypeId, 300, 1, "GL_200", "ACCT_PAY", "234",
                "Accounts Payable", 1);
        mockGLAccountListResults.add(p);
        
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(
                    mockGLAccountListResults);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch test case setup failed");
        }

        Creditor cred = AccountingMockDataUtility.createMockOrmCreditor(0, 4000, 1234, "GL_200", "932-392-339", 1);
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(4000, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(cred, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to fetched GL Account contains an invalid account type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NewCreditorSetupFailureException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCreditorWithInvalidBusinessId() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTPAY);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(
                    this.mockSingleCreditorGLAccountFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch test case setup failed");
        }

        int invalidBusinessId = 0;
        Creditor cred = AccountingMockDataUtility.createMockOrmCreditor(0, invalidBusinessId, 1234, "GL_200", "932-392-339", 1);
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(invalidBusinessId, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(cred, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to business id is invalid");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCreditorWithInvalidCreditorTypeId() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTPAY);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(
                    this.mockSingleCreditorGLAccountFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch test case setup failed");
        }

        int invalidCreditorTypeId = 0;
        Creditor cred = AccountingMockDataUtility.createMockOrmCreditor(0, 4000, 0, "GL_200", "932-392-339", invalidCreditorTypeId);
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(4000, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(cred, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to creditor type id is invalid");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCreditorWithNullCreditorObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(null);
            Assert.fail("Expected exception due to creditor input object is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            e.printStackTrace();
        }
    }
    
 
    @Test
    public void testUpdateExistingCreditor() {
        Creditor mockCreditorSubsidiaryCriteria = new Creditor();
        mockCreditorSubsidiaryCriteria.setCreditorId(1350);
        VwBusinessAddress mockBusAddrSubsidiaryCriteria = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockBusAddrSubsidiaryCriteria, mockCreditorSubsidiaryCriteria);

        try {
            when(this.mockPersistenceClient.updateRow(any(Creditor.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Creditor updateRow test case setup failed");
        }

        Creditor updateCreditor = AccountingMockDataUtility.createMockOrmCreditor(1350, 4000, 1234, "GL_200",
                "932-392-339", 1);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(updateCreditor, null);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.update(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testUpdateExistingCreditorWhereCreditorNotFound() {
        Creditor mockCreditorSubsidiaryCriteria = new Creditor();
        mockCreditorSubsidiaryCriteria.setCreditorId(1350);
        VwBusinessAddress mockBusAddrSubsidiaryCriteria = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockBusAddrSubsidiaryCriteria, mockCreditorSubsidiaryCriteria);

        Creditor updateCreditor = AccountingMockDataUtility.createMockOrmCreditor(1350, 4000, 1234, "GL_200",
                "932-392-339", 1);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(updateCreditor, null);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to creditor ws not found");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateExistingCreditorInvalidCreditorTypeId() {
        Creditor mockCreditorSubsidiaryCriteria = new Creditor();
        mockCreditorSubsidiaryCriteria.setCreditorId(1350);
        VwBusinessAddress mockBusAddrSubsidiaryCriteria = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockBusAddrSubsidiaryCriteria, mockCreditorSubsidiaryCriteria);

        int invalidCreditorTypeId = 0;
        Creditor updateCreditor = AccountingMockDataUtility.createMockOrmCreditor(1350, 4000, 1234, "GL_200",
                "932-392-339", invalidCreditorTypeId);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(updateCreditor, null);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to creditor type id is required");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateExistingCreditorWithInvalidCreditorObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(null);
            Assert.fail("Expected exception due to input creditor object is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDeleteCreditor() {
        Creditor deleteCreditorCriteria = new Creditor();
        deleteCreditorCriteria.setCreditorId(1350);

        try {
            when(this.mockPersistenceClient.deleteRow(eq(deleteCreditorCriteria))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Creditor deleteRow test case setup failed");
        }

        CreditorDto deleteCreditor = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        deleteCreditor.setCreditorId(1350);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.delete(deleteCreditor);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }
    
    @Test
    public void testDeleteCreditorWithNullCreditorObject() {
        Creditor deleteCreditorCriteria = new Creditor();
        deleteCreditorCriteria.setCreditorId(1350);

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.delete(null);
            Assert.fail("Expected exception due to input creditor object is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchAllNoContactDataWithException() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(Creditor.class)))
                            .thenThrow(CreditorDaoException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all creditors test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            Assert.assertTrue(e.getCause() instanceof CreditorDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateExistingCreditorWithException() {
        Creditor mockCreditorSubsidiaryCriteria = new Creditor();
        mockCreditorSubsidiaryCriteria.setCreditorId(1350);
        VwBusinessAddress mockBusAddrSubsidiaryCriteria = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockBusAddrSubsidiaryCriteria, mockCreditorSubsidiaryCriteria);

        try {
            when(this.mockPersistenceClient.updateRow(any(Creditor.class)))
            .thenThrow(CreditorDaoException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Creditor updateRow with excetpion test case setup failed");
        }

        Creditor updateCreditor = AccountingMockDataUtility.createMockOrmCreditor(1350, 4000, 1234, "GL_200",
                "932-392-339", 1);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(updateCreditor, null);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.update(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            Assert.assertTrue(e.getCause() instanceof CreditorDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCreditorWithException() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTPAY);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(
                    this.mockSingleCreditorGLAccountFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch test case setup failed");
        }

        Creditor cred = AccountingMockDataUtility.createMockOrmCreditor(0, 4000, 1234, "GL_200", "932-392-339", 1);
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(4000, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        int newCreditorId = 1350;
        try {
            when(this.mockPersistenceClient.insertRow(any(Creditor.class), eq(true)))
            .thenThrow(CreditorDaoException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Creditor insertRow test case setup failed");
        }

        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(cred, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.update(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            Assert.assertTrue(e.getCause() instanceof CreditorDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleCreditorTypeWithException() {
        CreditorType mockCredCriteria = new CreditorType();
        mockCredCriteria.setCreditorTypeId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCredCriteria)))
            .thenThrow(CreditorDaoException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorTypeDto results = null;
        try {
            results = api.getCreditorType(100);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            Assert.assertTrue(e.getCause() instanceof CreditorDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchTransactionHistoryWithException() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCreditorXactHist.class)))
            .thenThrow(CreditorDaoException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Creditor transaction history fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        List<CreditorXactHistoryDto> results = null;
        try {
            results = api.getTransactionHistory(100);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CreditorApiException);
            Assert.assertTrue(e.getCause() instanceof CreditorDaoException);
            e.printStackTrace();
        }
    }
}