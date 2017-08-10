package org.rmt2.api.subsidiary.customer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.AccountingConst;
import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCustomerXactHist;
import org.dto.CustomerDto;
import org.dto.CustomerXactHistoryDto;
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
import org.modules.subsidiary.CustomerApi;
import org.modules.subsidiary.CustomerApiException;
import org.modules.subsidiary.CustomerNotFoundException;
import org.modules.subsidiary.NewCustomerSetupFailureException;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.subsidiary.SubsidiaryException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.subsidiary.SubsidiaryApiTestData;
import org.rmt2.dao.AccountingMockDataUtility;
import org.rmt2.jaxb.BusinessType;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Customer Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class CustomerApiTest extends SubsidiaryApiTestData {

    

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
                    .retrieveList(any(Customer.class)))
                            .thenReturn(this.mockCustomerFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all customers test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
        List<CustomerDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }

    @Test
    public void testFetchSingleNoContactData() {
        Customer mockCriteria = new Customer();
        mockCriteria.setCustomerId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockCustomerFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
        List<CustomerDto> results = null;
        criteria.setCustomerId(200);
        try {
            results = api.get(criteria);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }
    
    @Test
    public void testFetchSingleNotFound() {
        Customer mockCriteria = new Customer();
        mockCriteria.setCustomerId(999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockCustomerNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
        criteria.setCustomerId(999);
        List<CustomerDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchWithNullCriteriaObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setAccountNo("C1234589");
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        List<CustomerDto> results = null;
        try {
            results = api.getByAcctNo("C1234589");
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }
  
    @Test
    public void testFetchByAccountNumberNotFound() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setAccountNo("XXXXXXX");
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        List<CustomerDto> results = null;
        try {
            results = api.getByAcctNo("XXXXXXX");
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByAccountNumberWithNullValue() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setBusinessId(1351);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto results = null;
        try {
            results = api.getByBusinessId(1351);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("C1234589", results.getAccountNo());
    }
    
    @Test
    public void testFetchByBusinessIdNotFound() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setBusinessId(9999);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto results = null;
        try {
            results = api.getByBusinessId(9999);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByBusinessIdTooManyReturned() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setBusinessId(1351);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByBusinessId(1351);
            Assert.fail("Expected exception due to too many customer records returned");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNullBusinessId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByBusinessId(0);
            Assert.fail("Expected exception due to negative business id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByCustomerId() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setCustomerId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto results = null;
        try {
            results = api.getByCustomerId(200);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("C1234589", results.getAccountNo());
    }
    
    @Test
    public void testFetchByCustomerIdNotFound() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setCustomerId(999);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto results = null;
        try {
            results = api.getByCustomerId(999);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByCustomerIdTooManyReturned() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setCustomerId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCustomerId(200);
            Assert.fail("Expected exception due to too many customer records returned");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNullCustomerId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCustomerId(null);
            Assert.fail("Expected exception due to null customer id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByZeroCustomerId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCustomerId(0);
            Assert.fail("Expected exception due to customer id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNegativeCustomerId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByCustomerId(0);
            Assert.fail("Expected exception due to negative customer id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByUID() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setCustomerId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto results = null;
        try {
            results = api.getByUid(200);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("C1234589", results.getAccountNo());
    }
    
    @Test
    public void testFetchByUIDNotFound() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setCustomerId(999);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto results = null;
        try {
            results = api.getByUid(999);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchByUIDNotFoundTooManyReturned() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setCustomerId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByUid(200);
            Assert.fail("Expected exception due to too many customer records returned");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByNullUID() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByUid(-100);
            Assert.fail("Expected exception due to negative UID");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testGetCustomerBalance() {
        ResultSet mockResulstSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(any(String.class)))
                            .thenReturn(mockResulstSet);
            when(mockResulstSet.next()).thenReturn(true);
            when(mockResulstSet.getDouble("balance")).thenReturn(7777777.77);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch customer balance test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
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
    public void testGetCustomerBalanceNoDataFound() {
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
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
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
    public void testGetBalanceWithNullCustomerId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getBalance(null);
            Assert.fail("Expected exception due to negative creditor id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetBalanceWithZeroCustomerId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getBalance(0);
            Assert.fail("Expected exception due to creditor id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetBalanceWithNegativeCustomerId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getBalance(-1350);
            Assert.fail("Expected exception due to creditor id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    
    @Test
    public void testFetchTransactionHistory() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCustomerXactHist.class))).thenReturn(
                            this.mockCustomerXactHistoryResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer transaction history fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        List<CustomerXactHistoryDto> results = null;
        try {
            results = api.getTransactionHistory(100);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        Assert.assertEquals(3000.00, results.get(3).getXactAmount(), 0);
    }
    
    @Test
    public void testFetchTransactionHistoryNotFound() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCustomerXactHist.class))).thenReturn(
                            null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer transaction history fetch test case setup failed");
        }
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        List<CustomerXactHistoryDto> results = null;
        try {
            results = api.getTransactionHistory(100);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchTransactionHistoryWithNullCustomerId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getTransactionHistory(null);
            Assert.fail("Expected exception due to customer id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchTransactionHistoryWithZeroCustomerId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getTransactionHistory(0);
            Assert.fail("Expected exception due to customer id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchTransactionHistoryWithNegativeCustomerId() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.getTransactionHistory(-123);
            Assert.fail("Expected exception due to creditor id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateNewCustomer() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTRCV);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(
                    this.mockSingleCustomerGLAccountFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch test case setup failed");
        }

        Customer cust = AccountingMockDataUtility.createMockOrmCustomer(0, 1351, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(1351, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        int newCustomerId = 1350;
        try {
            when(this.mockPersistenceClient.insertRow(any(Customer.class), eq(true))).thenReturn(newCustomerId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer insertRow test case setup failed");
        }

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.update(criteria);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(newCustomerId, rc);
    }

    @Test
    public void testCreateNewCustomerWithGLAccountFetchException() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTRCV);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenThrow(
                    new GeneralLedgerApiException("Database error occurred fetching GL Account data"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Customer cust = AccountingMockDataUtility.createMockOrmCustomer(0, 1351, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(1351, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to general database error occurre while fetching GL Account information");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NewCustomerSetupFailureException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCustomerWithGLAccountNotFound() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTRCV);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Customer cust = AccountingMockDataUtility.createMockOrmCustomer(0, 1351, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(1351, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to fetching GL Account information was not found");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NewCustomerSetupFailureException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCustomerWithFetchedGLAccountTypeIdInvalid() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTRCV);
        
        int invalidAcctTypeId = 5;
        List<GlAccounts> mockGLAccountListResults = new ArrayList<GlAccounts>();
        GlAccounts p = AccountingMockDataUtility.createMockOrmGlAccounts(1234, invalidAcctTypeId, 300, 1, "GL_200", "ACCT_PAY", "234",
                "Accounts Receivable", 2);
        mockGLAccountListResults.add(p);
        
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(
                    mockGLAccountListResults);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch test case setup failed");
        }

        Customer cust = AccountingMockDataUtility.createMockOrmCustomer(0, 1351, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(1351, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to fetched GL Account contains an invalid account type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NewCustomerSetupFailureException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCustomerWithInvalidBusinessId() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTRCV);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(
                    this.mockSingleCustomerGLAccountFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch test case setup failed");
        }

        int invalidBusinessId = 0;
        Customer cust = AccountingMockDataUtility.createMockOrmCustomer(0, invalidBusinessId, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataUtility.createMockJaxbBusiness(invalidBusinessId, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to business id is invalid");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCreditorWithNullCreditorObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(null);
            Assert.fail("Expected exception due to customer input object is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            e.printStackTrace();
        }
    }
    
 
    @Test
    public void testUpdateExistingCustomer() {
        Customer mockCustomerSubsidiaryCriteria = new Customer();
        mockCustomerSubsidiaryCriteria.setCustomerId(200);
        VwBusinessAddress mockBusAddrSubsidiaryCriteria = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockBusAddrSubsidiaryCriteria, mockCustomerSubsidiaryCriteria);

        try {
            when(this.mockPersistenceClient.updateRow(any(Customer.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer updateRow test case setup failed");
        }

        Customer updateCustomer = AccountingMockDataUtility.createMockOrmCustomer(200, 1351, 0,
                333, "C1234589", "Customer 1");
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(updateCustomer, null);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.update(criteria);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testUpdateExistingCreditorWhereCustomerNotFound() {
        Customer mockCustomerSubsidiaryCriteria = new Customer();
        mockCustomerSubsidiaryCriteria.setCustomerId(200);
        VwBusinessAddress mockBusAddrSubsidiaryCriteria = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockBusAddrSubsidiaryCriteria, mockCustomerSubsidiaryCriteria);

        Customer updateCustomer = AccountingMockDataUtility.createMockOrmCustomer(200, 1351, 0,
                333, "C1234589", "Customer 1");
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(updateCustomer, null);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to customer ws not found");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerNotFoundException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateExistingCustomerWithInvalidCreditorObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(null);
            Assert.fail("Expected exception due to input customer object is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDeleteCustomer() {
        Customer deleteCustomerCriteria = new Customer();
        deleteCustomerCriteria.setCustomerId(1350);

        try {
            when(this.mockPersistenceClient.deleteRow(eq(deleteCustomerCriteria))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer deleteRow test case setup failed");
        }

        CustomerDto deleteCustomer = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
        deleteCustomer.setCustomerId(1350);
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.delete(deleteCustomer);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }
    
    @Test
    public void testDeleteCustomerWithNullCustomerObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.delete(null);
            Assert.fail("Expected exception due to input customer object is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            e.printStackTrace();
        }
    }
}