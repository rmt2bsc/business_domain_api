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
    private static final int TEST_SALES_ORDER_ID = 1000;
    private static final int TEST_NEW_XACT_ID = 1234567890;
    private static final int TEST_EXISTING_XACT_ID = 54321;
    private static final String CUSTOMER_CONFIRMATION_DATA = "<Customer>Customer confirnatio</Customer>";
    
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
        try {
            when(SmtpFactory.getSmtpInstance()).thenReturn(mockSmtpApi);
            when(mockSmtpApi.sendMessage(isA(EmailMessageBean.class))).thenReturn(Boolean.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up mock for SMTP Api instance");
        }

        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        boolean rc = false;
        try {
            CashReceiptApi apiSpy = Mockito.spy(api);
            Mockito.doReturn(CUSTOMER_CONFIRMATION_DATA).when(apiSpy).buildPaymentConfirmation(TEST_SALES_ORDER_ID, TEST_NEW_XACT_ID);
            rc = apiSpy.emailPaymentConfirmation(TEST_SALES_ORDER_ID, TEST_NEW_XACT_ID);
        } catch (Exception e) {
            Assert.fail("An unexcpected exception was thrown");
            e.printStackTrace();
        }
        Assert.assertTrue(rc);
    }
    
    @Test
    public void test_Validation_Email_Confirmation_Null_SalesOrderId() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
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

        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        
        try {
            CashReceiptApi apiSpy = Mockito.spy(api);
            Mockito.doReturn(CUSTOMER_CONFIRMATION_DATA).when(apiSpy).buildPaymentConfirmation(TEST_SALES_ORDER_ID, TEST_NEW_XACT_ID);
            apiSpy.emailPaymentConfirmation(TEST_SALES_ORDER_ID, TEST_NEW_XACT_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof CashReceiptApiException);
            Assert.assertTrue(e.getCause() instanceof MessageException);
        }
    }
    
    @Test
    public void test_Apply_Payment_To_Invoice_Success() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
        boolean rc = false;
        try {
            CashReceiptApi apiSpy = Mockito.spy(api);
            Mockito.doReturn(TEST_NEW_XACT_ID).when(apiSpy).receivePayment(isA(XactDto.class), isA(Integer.class));
            Mockito.doReturn(true).when(apiSpy).emailPaymentConfirmation(isA(Integer.class), isA(Integer.class));
            rc = apiSpy.applyPaymentToInvoice(this.salesOrderDto, 300.00);
        } catch (Exception e) {
            Assert.fail("An unexcpected exception was thrown");
            e.printStackTrace();
        }
        Assert.assertTrue(rc);
    }
    
    @Test
    public void test_Validation_Apply_Payment_To_Invoice_Null_SalesOrder() {
        // Perform test
        CashReceiptApiFactory f = new CashReceiptApiFactory();
        CashReceiptApi api = f.createApi(mockDaoClient);
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