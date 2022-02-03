package org.rmt2.api.subsidiary.customer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.AccountingConst;
import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCustomerBalance;
import org.dao.mapping.orm.rmt2.VwCustomerXactHist;
import org.dao.subsidiary.CustomerDaoException;
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
import org.rmt2.api.AccountingMockDataFactory;
import org.rmt2.api.subsidiary.SubsidiaryApiTestData;
import org.rmt2.jaxb.BusinessType;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotRetrieveException;
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
            when(this.mockPersistenceClient.retrieveList(any(Customer.class)))
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
        for (int ndx = 0; ndx < results.size(); ndx++) {
            CustomerDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEntityId(), (200 + ndx));
            Assert.assertEquals(obj.getCustomerId(), (200 + ndx));
            Assert.assertEquals(obj.getContactId(), (1351 + ndx));
            Assert.assertEquals(obj.getPersonId(), 0);
            Assert.assertEquals(obj.getAccountNo(), "C123458" + ndx);
            Assert.assertEquals(obj.getDescription(), "Customer " + (ndx + 1));
            Assert.assertEquals(obj.getEntityName(), "Customer " + (ndx + 1));
            Assert.assertNull(obj.getContactName());
        }
    }

    @Test
    public void testFetchAllWithContactInfo() {
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setAccountNo("C1234589");
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupMultipleSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
        criteria.setAccountNo("C1234589");
        List<CustomerDto> results = null;
        try {
            results = api.getExt(criteria);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            CustomerDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEntityId(), (200 + ndx));
            Assert.assertEquals(obj.getCustomerId(), (200 + ndx));
            Assert.assertEquals(obj.getContactId(), (1351 + ndx));
            Assert.assertEquals(obj.getPersonId(), 0);
            Assert.assertEquals(obj.getAccountNo(), "C123458" + ndx);
            Assert.assertEquals(obj.getDescription(), "Customer " + (ndx + 1));
            Assert.assertEquals(obj.getEntityName(), "Customer " + (ndx + 1));
            Assert.assertNotNull(obj.getContactName());
            Assert.assertEquals(obj.getContactName(), "Company" + (ndx + 1));
        }
    }
    
    @Test
    public void testFetchSingleNoContactData() {
        Customer mockCriteria = new Customer();
        mockCriteria.setCustomerId(200);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCriteria)))
                            .thenReturn(this.mockCustomerFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto results = null;
        try {
            results = api.get(200);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getEntityId(), 200);
        Assert.assertEquals(results.getCustomerId(), 200);
        Assert.assertEquals(results.getContactId(), 1351);
        Assert.assertEquals(results.getPersonId(), 0);
        Assert.assertEquals(results.getAccountNo(), "C1234589");
        Assert.assertEquals(results.getDescription(), "Customer 1");
        Assert.assertEquals(results.getEntityName(), "Customer 1");
        Assert.assertNull(results.getContactName());
    }
    
    
    @Test
    public void testFetchSingleNotFound() {
        Customer mockCriteria = new Customer();
        mockCriteria.setCustomerId(999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto results = null;
        try {
            results = api.get(999);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchSingleWithNullInput() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.get((Integer) null);
            Assert.fail("Expected exception due to null input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleWithZeroInput() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.get(0);
            Assert.fail("Expected exception due to zero input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleWithNegativeInput() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.get(-100);
            Assert.fail("Expected exception due to zero input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithNullCriteriaObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.get((CustomerDto) null);
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
        // mockCustCriteria.setBusinessId(1351);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        mockContactCritereia.setBusinessId(1351);
        // this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia,
        // mockCustCriteria);
        this.setupSingleSubsidiaryContactInfoExactFetch(mockContactCritereia, mockCustCriteria);
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CreditorApi api = SubsidiaryApiFactory.createCreditorApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        VwCustomerBalance mockResults = new VwCustomerBalance();
        mockResults.setCustomerId(1350);
        mockResults.setBalance(7777777.77);
        try {
            when(this.mockPersistenceClient.retrieveObject(isA(VwCustomerBalance.class)))
                    .thenReturn(mockResults);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch customer balance test case setup failed");
        }

        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
       double results = 0;
        try {
            results = api.getBalance(1350);
        } catch (SubsidiaryException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(7777777.77, results, 0);
    }
    
    
    private void testMock_JDBC_executeSql_SQLCalls_Example() {
        ResultSet mockResulstSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(any(String.class))).thenReturn(mockResulstSet);
            when(mockResulstSet.next()).thenReturn(true);
            when(mockResulstSet.getDouble("balance")).thenReturn(7777777.77);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch customer balance test case setup failed");
        }

        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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

        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
            when(this.mockPersistenceClient.retrieveList(any(VwCustomerXactHist.class)))
                  .thenReturn(this.mockCustomerXactHistoryResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer transaction history fetch test case setup failed");
        }
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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

        Customer cust = AccountingMockDataFactory.createMockOrmCustomer(0, 1351, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataFactory.createMockJaxbBusiness(1351, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        int newCustomerId = 1350;
        try {
            when(this.mockPersistenceClient.insertRow(any(Customer.class), eq(true))).thenReturn(newCustomerId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer insertRow test case setup failed");
        }

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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

        Customer cust = AccountingMockDataFactory.createMockOrmCustomer(0, 1351, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataFactory.createMockJaxbBusiness(1351, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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

        Customer cust = AccountingMockDataFactory.createMockOrmCustomer(0, 1351, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataFactory.createMockJaxbBusiness(1351, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        GlAccounts p = AccountingMockDataFactory.createMockOrmGlAccounts(1234, invalidAcctTypeId, 300, 1, "GL_200", "ACCT_PAY", "234",
                "Accounts Receivable", 2);
        mockGLAccountListResults.add(p);
        
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria))).thenReturn(
                    mockGLAccountListResults);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch test case setup failed");
        }

        Customer cust = AccountingMockDataFactory.createMockOrmCustomer(0, 1351, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataFactory.createMockJaxbBusiness(1351, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        Customer cust = AccountingMockDataFactory.createMockOrmCustomer(0, invalidBusinessId, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataFactory.createMockJaxbBusiness(invalidBusinessId, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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

        Customer updateCustomer = AccountingMockDataFactory.createMockOrmCustomer(200, 1351, 0,
                333, "C1234589", "Customer 1");
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(updateCustomer, null);
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.update(criteria);
        } catch (CustomerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testUpdateExistingCustomerWhereCustomerNotFound() {
        Customer mockCustomerSubsidiaryCriteria = new Customer();
        mockCustomerSubsidiaryCriteria.setCustomerId(200);
        VwBusinessAddress mockBusAddrSubsidiaryCriteria = new VwBusinessAddress();
        this.setupNotFoundSubsidiaryContactInfoFetch(mockBusAddrSubsidiaryCriteria, mockCustomerSubsidiaryCriteria);

        Customer updateCustomer = AccountingMockDataFactory.createMockOrmCustomer(200, 1351, 0,
                333, "C1234589", "Customer 1");
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(updateCustomer, null);
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.update(criteria);
            Assert.fail("Expected exception due to customer ws not found");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerNotFoundException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateExistingCustomerWithInvalidCustomerObject() {
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
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
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
        try {
            api.delete(null);
            Assert.fail("Expected exception due to input customer object is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithException() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(Customer.class)))
            .thenThrow(CannotRetrieveException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all customers with exception test case setup failed");
        }

        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null, null);
        List<CustomerDto> results = null;
        try {
            results = api.get(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            Assert.assertTrue(e.getCause() instanceof CustomerDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchTransactionHistoryWithException() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCustomerXactHist.class)))
            .thenThrow(CustomerDaoException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer transaction history fetch with exception test case setup failed");
        }
        
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
        List<CustomerXactHistoryDto> results = null;
        try {
            results = api.getTransactionHistory(100);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            Assert.assertTrue(e.getCause() instanceof CustomerDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateExistingCustomerWithException() {
        Customer mockCustomerSubsidiaryCriteria = new Customer();
        mockCustomerSubsidiaryCriteria.setCustomerId(200);
        VwBusinessAddress mockBusAddrSubsidiaryCriteria = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockBusAddrSubsidiaryCriteria, mockCustomerSubsidiaryCriteria);

        try {
            when(this.mockPersistenceClient.updateRow(any(Customer.class)))
            .thenThrow(CustomerDaoException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer updateRow with exception test case setup failed");
        }

        Customer updateCustomer = AccountingMockDataFactory.createMockOrmCustomer(200, 1351, 0,
                333, "C1234589", "Customer 1");
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(updateCustomer, null);
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.update(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            Assert.assertTrue(e.getCause() instanceof CustomerDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateNewCustomerWithException() {
        GlAccounts mockGLAcctCriteria = new GlAccounts();
        mockGLAcctCriteria.setName(AccountingConst.ACCT_NAME_ACCTRCV);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockGLAcctCriteria)))
            .thenReturn(this.mockSingleCustomerGLAccountFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Account fetch with exception test case setup failed");
        }

        Customer cust = AccountingMockDataFactory.createMockOrmCustomer(0, 1351, 0,
                333, "C1234589", "Customer 1");
        BusinessType bus = AccountingMockDataFactory.createMockJaxbBusiness(1351, "ABC Company", "roy", "terrell",
                "9723333333", "royroy@gte.net", "75-1234567", "ABCCompany.com");

        int newCustomerId = 1350;
        try {
            when(this.mockPersistenceClient.insertRow(any(Customer.class), eq(true)))
            .thenThrow(CustomerDaoException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer insertRow test case setup failed");
        }

        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(cust, bus);
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(CommonAccountingConst.APP_NAME);
        int rc = 0;
        try {
            rc = api.update(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CustomerApiException);
            Assert.assertTrue(e.getCause() instanceof CustomerDaoException);
            e.printStackTrace();
        }
    }
}