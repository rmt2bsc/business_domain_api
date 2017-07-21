package org.rmt2.api.subsidiary.creditor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.CreditorType;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCreditorXactHist;
import org.dto.CreditorDto;
import org.dto.CreditorTypeDto;
import org.dto.CreditorXactHistoryDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.CommonAccountingConst;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.CreditorApiException;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.subsidiary.SubsidiaryApiTest;

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
public class CreditorApiTest extends SubsidiaryApiTest {

    
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
    }

    @Test
    public void testFetchSingleNoContactData() {
        Creditor mockCriteria = new Creditor();
        mockCriteria.setCreditorId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        criteria.setCreditorId(200);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }
    
    @Test
    public void testFetchSingleNotFound() {
        Creditor mockCriteria = new Creditor();
        mockCriteria.setCreditorId(999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockCreditorNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        criteria.setCreditorId(999);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchWithNullCriteriaObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.get(null);
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
}