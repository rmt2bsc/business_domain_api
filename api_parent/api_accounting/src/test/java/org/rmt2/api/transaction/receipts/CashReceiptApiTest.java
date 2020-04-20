package org.rmt2.api.transaction.receipts;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.CustomerActivity;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.transaction.XactDaoException;
import org.dto.SalesOrderDto;
import org.dto.XactDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;
import org.modules.transaction.receipts.CashReceiptApi;
import org.modules.transaction.receipts.CashReceiptApiException;
import org.modules.transaction.receipts.CashReceiptApiFactory;
import org.modules.transaction.receipts.PaymentEmailConfirmationExceptionOld;
import org.modules.transaction.sales.SalesApiException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.AccountingMockDataFactory;
import org.rmt2.api.transaction.sales.SalesApiTestData;

import com.InvalidDataException;
import com.api.messaging.MessageException;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2Date;

/**
 * Tests cash receipts transaction query Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class, SmtpFactory.class })
public class CashReceiptApiTest extends SalesApiTestData {

    private static final int SMTP_SUCCESS_RETURN_CODE = 221;
    private static final int TEST_CUSTOMER_ID = 200;
    private static final int TEST_BUSINESS_ID = 1351;
    private static final int TEST_SALES_ORDER_ID = 1000;
    private static final int TEST_NEW_XACT_ID = 1234567890;
    private static final int TEST_EXISTING_XACT_ID = 54321;
    private static final String CUSTOMER_CONFIRMATION_DATA = "<CustomerExt><beanClassName>org.dto.adapter.orm.account.subsidiary.CustomerExt</beanClassName><accountNo>C1234589</accountNo><active>1</active><addr1 /><addr2 /><addr3 /><addr4 /><addrBusinessId>0</addrBusinessId><addrId>0</addrId><addrPersonId>0</addrPersonId><addrPhoneCell /><addrPhoneExt /><addrPhoneFax /><addrPhoneHome /><addrPhoneMain /><addrPhonePager /><addrPhoneWork /><addrZip>0</addrZip><addrZipext>0</addrZipext><balance>300.0</balance><busType>0</busType><businessId>1351</businessId><contactEmail /><contactExt /><contactFirstname /><contactLastname /><contactPhone /><creditLimit>10000.0</creditLimit><customerId>200</customerId><dateCreated>Wed Mar 04 22:05:53 CST 2020</dateCreated><dateUpdated>Wed Mar 04 22:05:53 CST 2020</dateUpdated><description /><glAccountId>333</glAccountId><name /><servType>0</servType><shortname /><taxId /><userId>testuser</userId><website /><zipCity /><zipState /></CustomerExt>"
            +
            "<SalesOrder><beanClassName>org.dao.mapping.orm.rmt2.SalesOrder</beanClassName><criteriaAvailable>false</criteriaAvailable><customCriteriaAvailable>false</customCriteriaAvailable><customerId>2000</customerId><dataSourceClassName>org.dao.mapping.orm.rmt2.SalesOrder</dataSourceClassName><dataSourceName>SalesOrderView</dataSourceName><dataSourcePackage>org.dao.mapping.orm.rmt2</dataSourcePackage><dataSourceRoot>SalesOrder</dataSourceRoot><dateCreated /><dateUpdated /><effectiveDate>Sun Jan 01 00:00:00 CST 2017</effectiveDate><fileName /><inClauseAvailable>false</inClauseAvailable><invoiced>0</invoiced><ipCreated>111.222.101.100</ipCreated><ipUpdated>111.222.101.100</ipUpdated><null /><orderByAvailable>false</orderByAvailable><orderTotal>100.0</orderTotal><resultsetType>0</resultsetType><rowLimitClause /><serializeXml>false</serializeXml><soId>1000</soId><userId /></SalesOrder>"
            +
            "<Xact><beanClassName>org.dao.mapping.orm.rmt2.Xact</beanClassName><bankTransInd /><confirmNo>1484287200000</confirmNo><criteriaAvailable>false</criteriaAvailable><customCriteriaAvailable>false</customCriteriaAvailable><dataSourceClassName>org.dao.mapping.orm.rmt2.Xact</dataSourceClassName><dataSourceName>XactView</dataSourceName><dataSourcePackage>org.dao.mapping.orm.rmt2</dataSourcePackage><dataSourceRoot>Xact</dataSourceRoot><dateCreated /><dateUpdated /><documentId>54521</documentId><entityRefNo /><fileName /><inClauseAvailable>false</inClauseAvailable><ipCreated /><ipUpdated /><negInstrNo>1111-1111-1111-1111</negInstrNo><null /><orderByAvailable>false</orderByAvailable><postedDate>Fri Jan 13 00:00:00 CST 2017</postedDate><reason>reason for transaction id 54321</reason><resultsetType>0</resultsetType><rowLimitClause /><serializeXml>false</serializeXml><tenderId>200</tenderId><userId /><xactAmount>300.0</xactAmount><xactDate>Fri Jan 13 00:00:00 CST 2017</xactDate><xactId>54321</xactId><xactSubtypeId>0</xactSubtypeId><xactTypeId>10</xactTypeId></Xact>";
    
    private List<VwXactList> mockSingleXact;
    private SalesOrder salesOrderOrm;
    private SalesOrderDto salesOrderDto;
    
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockSingleXact = this.createMockSingleXactData();
        this.salesOrderOrm = SalesApiTestData.createMockSalesOrderSingleResponse().get(0);
        this.salesOrderDto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(this.salesOrderOrm);
        System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }
    
    private List<VwXactList> createMockSingleXactData() {
        List<VwXactList> list = new ArrayList<VwXactList>();
        VwXactList o = AccountingMockDataFactory.createMockOrmXact(TEST_EXISTING_XACT_ID,
                XactConst.XACT_TYPE_SALESONACCTOUNT,
                XactConst.XACT_SUBTYPE_NOT_ASSIGNED,
                RMT2Date.stringToDate("2017-01-13"), 300.00, 200, "1111-1111-1111-1111");
        list.add(o);
        return list;
    }
    
    private List<VwXactList> createMockSingleXactDataForPaymentEmail() {
        List<VwXactList> list = new ArrayList<VwXactList>();
        VwXactList o = AccountingMockDataFactory.createMockOrmXact(TEST_NEW_XACT_ID,
                XactConst.XACT_TYPE_CASHRECEIPT,
                XactConst.XACT_SUBTYPE_NOT_ASSIGNED,
                RMT2Date.stringToDate("2020-04-19"), 755.94, 11, "1111-1111-1111-1111");
        list.add(o);
        return list;
    }

    private List<Customer> createMockSingleCustomer() {
        List<Customer> list = new ArrayList<Customer>();
        Customer o = AccountingMockDataFactory.createMockOrmCustomer(TEST_CUSTOMER_ID, TEST_BUSINESS_ID, 0, 333, "C1234589",
                "Customer 1");
        list.add(o);
        return list;
    }

    private List<VwBusinessAddress> createMockSingleVwBusinessAddress() {
        List<VwBusinessAddress> list = new ArrayList<VwBusinessAddress>();
        VwBusinessAddress p = AccountingMockDataFactory.createMockOrmBusinessContact(TEST_BUSINESS_ID, "ABC Company", 2222,
                        "94393 Hall Ave.", "Building 123", "Suite 300",
                        "Room 45", "Dallas", "TX", 75232);
        p.setContactEmail("johndoe@testemail.com");
        list.add(p);
        return list;
    }

    private void setupMocksForEmailConfirmation() {

        // Transaction mocking
        VwXactList mockXactCriteria = new VwXactList();
        mockXactCriteria.setId(TEST_NEW_XACT_ID);
        try {
            List<VwXactList> mockXactList = createMockSingleXactDataForPaymentEmail();
            when(this.mockPersistenceClient.retrieveList(eq(mockXactCriteria))).thenReturn(mockXactList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }

        // Sales Order mocking
        SalesOrder mockSalesOrderCriteria = new SalesOrder();
        mockSalesOrderCriteria.setSoId(TEST_SALES_ORDER_ID);
        try {
            List<SalesOrder> mockSalesOrderList = createMockSalesOrderSingleResponse();
            mockSalesOrderList.get(0).setOrderTotal(755.94);
            mockSalesOrderList.get(0).setSoId(TEST_SALES_ORDER_ID);
            mockSalesOrderList.get(0).setCustomerId(TEST_CUSTOMER_ID);
            when(this.mockPersistenceClient.retrieveList(eq(mockSalesOrderCriteria))).thenReturn(mockSalesOrderList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single sales order test case setup failed");
        }

        // Custmer mocking
        Customer mockCustomerCriteria = new Customer();
        mockCustomerCriteria.setCustomerId(TEST_CUSTOMER_ID);
        try {
            List<Customer> mockCustomerList = this.createMockSingleCustomer();
            when(this.mockPersistenceClient.retrieveList(eq(mockCustomerCriteria))).thenReturn(mockCustomerList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Customer List fetch test case setup failed");
        }

        // Mock Customer balance SQL query stub in Cash Receipts API.
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(isA(String.class))).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getDouble("balance")).thenReturn(755.94);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch fetch custome balance test case setup failed");
        }

        // VwBusinessAddress mocking
        VwBusinessAddress mockBusinessContactcriteria = new VwBusinessAddress();
        mockBusinessContactcriteria.setBusinessId(TEST_BUSINESS_ID);
        try {
            List<VwBusinessAddress> mockVwBusinessAddressList = this.createMockSingleVwBusinessAddress();
            when(this.mockPersistenceClient.retrieveList(eq(mockBusinessContactcriteria))).thenReturn(mockVwBusinessAddressList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single VwBusinessAddress fetch test case setup failed");
        }
    }

    private void setupMocksForApplyPaymentToInvoice() {
        // Custmer mocking
        Customer mockCustomerCriteria = new Customer();
        mockCustomerCriteria.setCustomerId(2000);
        try {
            List<Customer> mockCustomerList = this.createMockSingleCustomer();
            when(this.mockPersistenceClient.retrieveObject(eq(mockCustomerCriteria))).thenReturn(mockCustomerList.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Customer fetch test case setup failed");
        }
    }

    @Test
    public void test_Receive_Payment_Success() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true)))
                    .thenReturn(TEST_NEW_XACT_ID, (TEST_NEW_XACT_ID + 111111111));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }
        
        // Mock create customer transaction activity stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(CustomerActivity.class), eq(true)))
                            .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert customer activity case setup failed");
        }
        
        // Mock Customer query stub.
        try {
            when(this.mockPersistenceClient.retrieveObject(isA(Customer.class)))
                            .thenReturn(this.mockCustomerFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }
        
        // Mock base transaction query verification stub.
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(TEST_NEW_XACT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockSingleXact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }
        
        // Mock Customer balance SQL query stub in Cash Receipts API.
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(isA(String.class)))
                            .thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getDouble("balance")).thenReturn(300.00);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch fetch custome balance test case setup failed");
        }
        
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        int results = 0;
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
        vwXact.setId(0);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        mockXact.setXactAmount(300.00);
        Integer customerId = 200;
        try {
            results = api.receivePayment(mockXact, customerId);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals(TEST_NEW_XACT_ID, results);
        Assert.assertEquals(TEST_NEW_XACT_ID, mockXact.getXactId());
    } 
    
    @Test
    public void test_Validation_Receive_Payment_Null_Xact() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        Integer customerId = 200;
        try {
            api.receivePayment(null, customerId);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    } 
    
    @Test
    public void test_Validation_Receive_Payment_Null_CustomerId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
        vwXact.setId(0);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        mockXact.setXactAmount(300.00);
        Integer customerId = null;
        try {
            api.receivePayment(mockXact, customerId);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    } 
    
    @Test
    public void test_Validation_Receive_Payment_Missing_Tender() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);

        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0);
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
        vwXact.setId(0);
        vwXact.setTenderId(0);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        mockXact.setXactAmount(300.00);
        Integer customerId = 1000;
        try {
            api.receivePayment(mockXact, customerId);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }

    @Test
    public void test_Validation_Receive_Payment_Negative_CustomerId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
        vwXact.setId(0);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        mockXact.setXactAmount(300.00);
        Integer customerId = -200;
        try {
            api.receivePayment(mockXact, customerId);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    } 
    
    @Test
    public void test_Validation_Receive_Payment_Zero_CustomerId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
        vwXact.setId(0);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        mockXact.setXactAmount(300.00);
        Integer customerId = 0;
        try {
            api.receivePayment(mockXact, customerId);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    } 
    
    @Test
    public void test_Receive_Payment_Db_Exception() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true)))
                 .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
        vwXact.setId(0);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        mockXact.setXactAmount(300.00);
        Integer customerId = 200;
        try {
            api.receivePayment(mockXact, customerId);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof CashReceiptApiException);
            Assert.assertTrue(e.getCause() instanceof XactApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof XactDaoException);
        }
    }
    
    @Test
    public void test_Build_Confirmation_Success() {
        // Mock base transaction query verification stub.
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(TEST_NEW_XACT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(this.mockSingleXact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }

        // Setup mock for validating sales order object
        SalesOrder so = new SalesOrder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
            .thenReturn(this.mockSalesOrderSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order fetch test case setup failed");
        }

        // Mock Customer query stub.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(Customer.class)))
                    .thenReturn(this.mockCustomerFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }

        // Mock Customer balance SQL query stub in Cash Receipts API.
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(isA(String.class))).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getDouble("balance")).thenReturn(300.00);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }

        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        String results = null;
        try {
            results = api.buildPaymentConfirmation(TEST_SALES_ORDER_ID, TEST_NEW_XACT_ID);
        } catch (Exception e) {
            Assert.fail("An unexcpected exception was thrown");
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.length() > 10);
    }
    
    @Test
    public void test_Validation_Build_Confirmation_Xact_NotFound() {
        // Mock base transaction query verification stub.
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(TEST_NEW_XACT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }
        
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.buildPaymentConfirmation(TEST_SALES_ORDER_ID, TEST_NEW_XACT_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof CashReceiptApiException);
        }
    }
    
    @Test
    public void test_Build_Confirmation_Db_Exception() {
        // Mock base transaction query verification stub.
        VwXactList mockCriteria = new VwXactList();
        mockCriteria.setId(TEST_NEW_XACT_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(XactDaoException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }
        
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.buildPaymentConfirmation(TEST_SALES_ORDER_ID, TEST_NEW_XACT_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof CashReceiptApiException);
            Assert.assertTrue(e.getCause() instanceof XactDaoException);
        }
    }
    
    @Test
    public void test_Validation_Build_Confirmation_Null_SalesOrderId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.buildPaymentConfirmation(null, TEST_NEW_XACT_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Build_Confirmation_Negative_SalesOrderId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.buildPaymentConfirmation(-111, TEST_NEW_XACT_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Build_Confirmation_Zero_SalesOrderId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.buildPaymentConfirmation(0, TEST_NEW_XACT_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Build_Confirmation_Null_XactId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.buildPaymentConfirmation(TEST_SALES_ORDER_ID, null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Build_Confirmation_Negative_XactId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.buildPaymentConfirmation(TEST_SALES_ORDER_ID, -111);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Build_Confirmation_Zero_XactId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.buildPaymentConfirmation(TEST_SALES_ORDER_ID, 0);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Email_Confirmation_Success() {
        // Setup mock for SMTP Api usage
        PowerMockito.mockStatic(SmtpFactory.class);
        SmtpApi mockSmtpApi = Mockito.mock(SmtpApi.class);
        CashReceiptApi mockCashReceiptApi = Mockito.mock(CashReceiptApi.class);
        try {
            when(SmtpFactory.getSmtpInstance()).thenReturn(mockSmtpApi);
            when(mockSmtpApi.sendMessage(isA(EmailMessageBean.class))).thenReturn(221);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up mock for SMTP Api instance");
        }

        try {
            when(mockCashReceiptApi.buildPaymentConfirmation(isA(Integer.class), isA(Integer.class))).thenReturn(
                    CUSTOMER_CONFIRMATION_DATA);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up mock for buildPaymentConfirmation method");
        }

        // Setup general mocks needed for building email confirmation
        this.setupMocksForEmailConfirmation();

        // Perform test
        CashReceiptApi api = CashReceiptApiFactory.createApi(mockDaoClient);
        int rc = 0;
        try {
            rc = api.emailPaymentConfirmation(TEST_SALES_ORDER_ID, TEST_NEW_XACT_ID);
        } catch (Exception e) {
            Assert.fail("An unexcpected exception was thrown");
            e.printStackTrace();
        }
        Assert.assertEquals(SMTP_SUCCESS_RETURN_CODE, rc);
    }
    
    @Test
    public void test_Validation_Email_Confirmation_Null_SalesOrderId() {
        // Perform test
        CashReceiptApi api = CashReceiptApiFactory.createApi(mockDaoClient);
        try {
            api.emailPaymentConfirmation(null, TEST_NEW_XACT_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Email_Confirmation_Negative_SalesOrderId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.emailPaymentConfirmation(-111, TEST_NEW_XACT_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Email_Confirmation_Zero_SalesOrderId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.emailPaymentConfirmation(0, TEST_NEW_XACT_ID);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Email_Confirmation_Null_XactId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.emailPaymentConfirmation(TEST_SALES_ORDER_ID, null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Email_Confirmation_Negative_XactId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.emailPaymentConfirmation(TEST_SALES_ORDER_ID, -111);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Email_Confirmation_Zero_XactId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.emailPaymentConfirmation(TEST_SALES_ORDER_ID, 0);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
        
    @Test
    public void test_Email_Confirmation_Message_Exception() {
        // Setup mock for SMTP Api usage
        PowerMockito.mockStatic(SmtpFactory.class);
        SmtpApi mockSmtpApi = Mockito.mock(SmtpApi.class);
        try {
            when(SmtpFactory.getSmtpInstance()).thenReturn(mockSmtpApi);
            when(mockSmtpApi.sendMessage(isA(EmailMessageBean.class))).thenThrow(MessageException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up mock for SMTP Api instance");
        }

        // Setup general mocks needed for building email confirmation
        this.setupMocksForEmailConfirmation();

        // Perform test
        CashReceiptApi api = CashReceiptApiFactory.createApi(mockDaoClient);
        
        try {
            api.emailPaymentConfirmation(TEST_SALES_ORDER_ID, TEST_NEW_XACT_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PaymentEmailConfirmationExceptionOld);
        }
    }
    
    @Test
    public void test_Apply_Payment_To_Invoice_Success() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true))).thenReturn(TEST_NEW_XACT_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert xact test case setup failed");
        }

        // Setup general mocks needed for applying payment to invoice
        this.setupMocksForApplyPaymentToInvoice();
        // Setup general mocks needed for building email confirmation
        this.setupMocksForEmailConfirmation();

        // Perform test
        CashReceiptApi api = CashReceiptApiFactory.createApi(mockDaoClient);
        int xactId = 0;
        try {
            xactId = api.applyPaymentToInvoice(this.salesOrderDto, 300.00);
        } catch (Exception e) {
            Assert.fail("An unexcpected exception was thrown");
            e.printStackTrace();
        }
        Assert.assertEquals(TEST_NEW_XACT_ID, xactId);
    }
    
    @Test
    public void test_Validation_Apply_Payment_To_Invoice_Null_SalesOrder() {
        // Perform test
        CashReceiptApi api = CashReceiptApiFactory.createApi(mockDaoClient);
        try {
            api.applyPaymentToInvoice(null, 300.00);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void test_Validation_Apply_Payment_To_Invoice_Null_Amount() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        try {
            api.applyPaymentToInvoice(this.salesOrderDto, null);
            Assert.fail("Expected an exception to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
}