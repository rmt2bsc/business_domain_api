package org.rmt2.api.transaction.sales;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.CustomerActivity;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.subsidiary.CustomerDaoException;
import org.dao.transaction.XactDaoException;
import org.dao.transaction.sales.SalesInvoiceDaoException;
import org.dao.transaction.sales.SalesOrderDaoException;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.XactDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.inventory.InventoryApiException;
import org.modules.inventory.InventoryConst;
import org.modules.subsidiary.CustomerApiException;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;
import org.modules.transaction.receipts.CashReceiptApi;
import org.modules.transaction.sales.OutOfSyncSalesOrderStatusesException;
import org.modules.transaction.sales.SalesApi;
import org.modules.transaction.sales.SalesApiConst;
import org.modules.transaction.sales.SalesApiException;
import org.modules.transaction.sales.SalesApiFactory;
import org.modules.transaction.sales.SalesOrderCustomerIdInvalidException;
import org.modules.transaction.sales.SalesOrderStatusInvalidException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.AccountingMockDataFactory;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.messaging.MessageException;
import com.api.messaging.email.EmailMessageBean;
import com.api.messaging.email.smtp.SmtpApi;
import com.api.messaging.email.smtp.SmtpFactory;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2Date;

/**
 * Tests sales order / sales invoice transaction query Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class, SmtpFactory.class })
public class SalesApiUpdateTest extends SalesApiTestData {
    private static final int TEST_NEW_SALES_ORDER_ID = 7654321;
    private static final int TEST_SALES_ORDER_ID = 1000;
    private static final int TEST_CUSTOMER_ID = 2000;
    private static final int TEST_NEW_SALES_ORDER_STAT_HIST_ID = 9999999;
    private static final int TEST_NEW_XACT_ID = 1234567890;
    private static final int TEST_EXISTING_XACT_ID = 54321;
    private static final int TEST_NEW_INVOICE_ID = 700000;
    private static final int TEST_BUSINESS_ID = 1351;

    private SalesOrder existingSalesOrderOrm;
    private SalesOrderDto existingSalesOrderDto;
    private SalesOrder newSalesOrderOrm;
    private SalesOrderDto newSalesOrderDto;
    private List<SalesOrderItemDto> newLineItemListDto;
    private List<SalesOrderItemDto> newSingleLineItemListDto;
    private List<SalesOrderItemDto> existingLineItemListDto;
    private List<VwXactList> mockSingleXact;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.modifyMockData();
        this.setupBasicMockStubs();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private void modifyMockData() {
        // Setup new mock sales order and sales order line item DTO's
        this.newSalesOrderOrm = SalesApiTestData.createMockSalesOrderSingleResponse().get(0);
        this.newSalesOrderDto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(this.newSalesOrderOrm);
        this.newSalesOrderDto.setSalesOrderId(0);
        this.newLineItemListDto = new ArrayList<>();
        List<SalesOrderItems> items = SalesApiTestData.createMockSalesOrderItemsAllResponse();
        for (SalesOrderItems item : items) {
            item.setSoId(0);
            SalesOrderItemDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderItemInstance(item);
            this.newLineItemListDto.add(dto);
        }
        this.newSingleLineItemListDto = new ArrayList<>();
        for (SalesOrderItems item : this.mockSalesOrderItemsSingleResponse) {
            item.setSoId(0);
            SalesOrderItemDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderItemInstance(item);
            this.newSingleLineItemListDto.add(dto);
        }

        // Setup exisitng mock sales order and sales order line item DTO's
        this.existingSalesOrderOrm = SalesApiTestData.createMockSalesOrderSingleResponse().get(0);
        this.existingSalesOrderDto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(this.existingSalesOrderOrm);
        this.existingLineItemListDto = new ArrayList<>();
        items = SalesApiTestData.createMockSalesOrderItemsAllResponse();
        for (SalesOrderItems item : items) {
            SalesOrderItemDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderItemInstance(item);
            this.existingLineItemListDto.add(dto);
        }

        // Create Single Xact response data
        this.mockSingleXact = this.createMockSingleXactData();

    }

    private List<VwXactList> createMockSingleXactData() {
        List<VwXactList> list = new ArrayList<VwXactList>();
        VwXactList o = AccountingMockDataFactory.createMockOrmXact(TEST_EXISTING_XACT_ID, XactConst.XACT_TYPE_SALESONACCTOUNT,
                XactConst.XACT_SUBTYPE_NOT_ASSIGNED, RMT2Date.stringToDate("2017-01-13"), 300.00, 200, "1111-1111-1111-1111");
        list.add(o);
        return list;
    }

    private void setupBasicMockStubs() {
        // Setup mock for validating line items
        try {
            // Use "isA" matcher instead of "any" to prevent the "any" matcher
            // from overriding the "eq" matcher when both matchers are used
            // interchangeably as arguments for the same stub method that is
            // called multiple times within the same use case.
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class))).thenReturn(this.mockSingleItemMasterFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master fetch using criteria test case setup failed");
        }

        // Setup mock for validating sales order object
        SalesOrder so = new SalesOrder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenReturn(this.mockSalesOrderSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order fetch test case setup failed");
        }

        // Setup mock to validate customer id
        Customer mockCustomerCriteria = new Customer();
        mockCustomerCriteria.setCustomerId(TEST_CUSTOMER_ID);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCustomerCriteria))).thenReturn(this.mockCustomerFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }

        // Setup mock for updating existing base sales order
        try {
            when(this.mockPersistenceClient.updateRow(eq(this.existingSalesOrderOrm))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order line item update case failed");
        }

        // Setup mock for adding base sales order
        try {
            when(this.mockPersistenceClient.insertRow(eq(this.newSalesOrderOrm), eq(true))).thenReturn(TEST_NEW_SALES_ORDER_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order line item update case failed");
        }

        // Setup mock for adding sales order line items
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesOrderItems.class), any(Boolean.class))).thenReturn(88880, 88881, 88882, 88883, 88884);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order line item update case failed");
        }

        // Setup mock for new sales order status verification
        SalesOrderStatus mockNextSalesOrderStatusFetchCriteria = new SalesOrderStatus();
        mockNextSalesOrderStatusFetchCriteria.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
        List<SalesOrderStatus> mockNextSalesOrderStatusFetchResponse = SalesApiTestData.createMockSingleSalesOrderStatus(
                SalesApiConst.STATUS_CODE_QUOTE, "Quote");
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockNextSalesOrderStatusFetchCriteria)))
                    .thenReturn(mockNextSalesOrderStatusFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }

        // Setup mock to get the current status of the sales order
        SalesOrderStatusHist mockCurrentSalesOrderStatus = new SalesOrderStatusHist();
        mockCurrentSalesOrderStatus.setSoId(TEST_NEW_SALES_ORDER_ID);
        try {
            // Ensure that current status returned is "New"
            when(this.mockPersistenceClient.retrieveObject(eq(mockCurrentSalesOrderStatus))).thenReturn(this.mockStatusHistoryNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Setup mock for updating existing sales order status history
        try {
            when(this.mockPersistenceClient.updateRow(isA(SalesOrderStatusHist.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order status history update case failed");
        }

        // Setup mock for adding sales order status history
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesOrderStatusHist.class), eq(true))).thenReturn(TEST_NEW_SALES_ORDER_STAT_HIST_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order status history insert case failed");
        }
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

    @Test
    public void test_Create_SalesOrder_Success() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        int results = 0;
        try {
            this.newSalesOrderDto.setOrderTotal(630.00);
            results = api.updateSalesOrder(this.newSalesOrderDto, this.newLineItemListDto);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals(TEST_NEW_SALES_ORDER_ID, results);
    }

    @Test
    public void test_Create_SalesOrder_With_BackOrder_Success() {
        // Setup backorder data
        SalesOrderItemDto lineItem = this.newSingleLineItemListDto.get(0);
        lineItem.setBackOrderQty(0);
        lineItem.setOrderQty(5);

        this.newSalesOrderDto.setOrderTotal(300.00);

        this.mockSingleItemMasterFetchResponse.get(0).setItemTypeId(InventoryConst.ITEM_TYPE_MERCH);

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        int results = 0;
        try {
            results = api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals(TEST_NEW_SALES_ORDER_ID, results);
        Assert.assertEquals(3, lineItem.getBackOrderQty(), 0);
        Assert.assertEquals(2, lineItem.getOrderQty(), 0);

    }

    @Test
    public void test_Create_SalesOrder_Using_ItemMaster_Calcuations_Success() {
        // Force app to use unit cost and markup from item master.
        SalesOrderItemDto lineItem = this.newSingleLineItemListDto.get(0);
        lineItem.setBackOrderQty(0);
        lineItem.setInitMarkup(0);
        lineItem.setInitUnitCost(0);

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        int results = 0;
        try {
            results = api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals(TEST_NEW_SALES_ORDER_ID, results);
        Assert.assertEquals(0, lineItem.getBackOrderQty(), 0);
        Assert.assertEquals(5, lineItem.getInitMarkup(), 0);
        Assert.assertEquals(1.23, lineItem.getInitUnitCost(), 0);
    }

    @Test
    public void test_Update_SalesOrder_Success() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        int results = 0;
        try {
            this.existingSalesOrderDto.setOrderTotal(630.00);
            results = api.updateSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals(1, results);
    }

    @Test
    public void test_SalesOrder_Validation_Db_Exception() {
        // Setup mock for throwing exception when validating sales order object
        SalesOrder so = new SalesOrder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Sales order validation DB Exception test case setup failed");
        }
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
        }
    }

    @Test
    public void test_SalesOrder_Customer_Validation_Db_Exception() {
        // Setup mock to validate customer id
        Customer mockCustomerCriteria = new Customer();
        mockCustomerCriteria.setCustomerId(TEST_CUSTOMER_ID);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCustomerCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Sales order validation DB Exception test case setup failed");
        }
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof CustomerApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof CustomerDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void test_SalesOrderLineItem_Validation_Db_Exception() {
        // Setup mock for validating line items
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up Inventory Item Master validation DB Exception case failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof InventoryApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof InventoryApiException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof CannotRetrieveException);
            Assert.assertTrue(e.getCause().getCause().getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void test_SalesOrder_Update_Db_Exception() {
        // Setup mock for updating existing base sales order
        try {
            when(this.mockPersistenceClient.updateRow(eq(this.existingSalesOrderOrm))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order update case failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
        }
    }

    @Test
    public void test_SalesOrder_Insert_Db_Exception() {
        // Setup mock for updating existing base sales order
        try {
            when(this.mockPersistenceClient.insertRow(eq(this.newSalesOrderOrm), eq(true))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order insert case failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
        }
    }

    @Test
    public void test_SalesOrderLineItems_Delete_Db_Exception() {
        // Setup mock for deleting all line items from existing base sales order
        try {
            when(this.mockPersistenceClient.deleteRow(isA(SalesOrderItems.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order line item delete case failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
        }
    }

    @Test
    public void test_SalesOrderLineItems_Insert_Db_Exception() {
        // Setup mock for adding sales order line items
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesOrderItems.class), any(Boolean.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order line item insert db exception case failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void test_Validate_NewSalesOrderStatus_Db_Exception() {
        // Setup mock for new sales order status verification
        SalesOrderStatus mockNextSalesOrderStatusFetchCriteria = new SalesOrderStatus();
        mockNextSalesOrderStatusFetchCriteria.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockNextSalesOrderStatusFetchCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderStatusInvalidException);
        }
    }

    @Test
    public void test_GetCurrentSalesOrderStatus_Db_Exception() {
        // Setup mock to get the current status of the sales order
        SalesOrderStatusHist mockCurrentSalesOrderStatus = new SalesOrderStatusHist();
        mockCurrentSalesOrderStatus.setSoId(TEST_NEW_SALES_ORDER_ID);
        try {
            // Ensure that current status returned is "New"
            when(this.mockPersistenceClient.retrieveObject(eq(mockCurrentSalesOrderStatus))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch DB Exception test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void test_Create_Quote_SalesOrderStatus_Db_Exception() {
        // Setup mock for adding sales order status history
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesOrderStatusHist.class), eq(true))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up sales order status history insert case failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void test_Validation_Null_SalesOrder_Object() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(null, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_NotFound() {
        // Setup mock for validating sales order object
        SalesOrder so = new SalesOrder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenReturn(this.mockSalesOrderNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order fetch test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_CustomerId_Negative() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.newSalesOrderDto.setCustomerId(-111);
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_CustomerId_Zero() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.newSalesOrderDto.setCustomerId(0);
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderCustomerIdInvalidException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_Customer_NotFound() {
        // Setup mock to validate customer id
        Customer mockCustomerCriteria = new Customer();
        mockCustomerCriteria.setCustomerId(TEST_CUSTOMER_ID);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCustomerCriteria))).thenReturn(this.mockCustomerNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_LineItemList_Null() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, null);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_LineItemList_Empty() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.newSingleLineItemListDto.clear();
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_LineItemList_Negative_ItemId() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.newSingleLineItemListDto.get(0).setItemId(-100);
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_LineItemList_Zero_ItemId() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.newSingleLineItemListDto.get(0).setItemId(0);
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_LineItemList_ItemId_NotFound() {
        // Setup mock for validating line items
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master fetch using criteria test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_Destination_Status_NotFound() {
        // Setup mock for new sales order status verification
        try {
            when(this.mockPersistenceClient.retrieveList(isA(SalesOrderStatus.class))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order status fetch test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderStatusInvalidException);
            Assert.assertTrue(e.getCause().getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void test_Validation_SalesOrder_Destination_Status_OutOfSync() {
        // Setup mock to get the current status of the sales order
        try {
            // Ensure that current status returned is "Invoice"
            when(this.mockPersistenceClient.retrieveObject(isA(SalesOrderStatusHist.class))).thenReturn(this.mockStatusHistoryAllResponse.get(2));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.updateSalesOrder(this.newSalesOrderDto, this.newSingleLineItemListDto);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof OutOfSyncSalesOrderStatusesException);
        }
    }

    @Test
    public void test_Invoice_NoImmediatePayment_SalesOrder_Success() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true))).thenReturn(TEST_NEW_XACT_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
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

        // Mock create customer transaction activity stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(CustomerActivity.class), eq(true))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert customer activity case setup failed");
        }

        // Mock sales invoice transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesInvoice.class), eq(true))).thenReturn(TEST_NEW_INVOICE_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Setup mock for invoice sales order status verification
        SalesOrderStatus mockNextSalesOrderStatusFetchCriteria = new SalesOrderStatus();
        mockNextSalesOrderStatusFetchCriteria.setSoStatusId(SalesApiConst.STATUS_CODE_INVOICED);
        List<SalesOrderStatus> mockNextSalesOrderStatusFetchResponse = SalesApiTestData.createMockSingleSalesOrderStatus(
                SalesApiConst.STATUS_CODE_INVOICED, "Invoice");
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockNextSalesOrderStatusFetchCriteria)))
                    .thenReturn(mockNextSalesOrderStatusFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }

        // Setup mock to get the current sales order status as "QUOTE"
        SalesOrderStatusHist mockCurrentSalesOrderStatus = new SalesOrderStatusHist();
        mockCurrentSalesOrderStatus.setSoId(TEST_SALES_ORDER_ID);
        try {
            // Ensure that current status returned is "Quote"
            when(this.mockPersistenceClient.retrieveObject(eq(mockCurrentSalesOrderStatus))).thenReturn(this.mockStatusHistoryAllResponse.get(1));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Setup mock to get all line items of a sales order stub
        SalesOrderItems so = new SalesOrderItems();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenReturn(this.mockSalesOrderItemsAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order items fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        int results = 0;
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            results = api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, false);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals(TEST_NEW_XACT_ID, results);
        Assert.assertEquals(true, this.existingSalesOrderDto.isInvoiced());
    }

    @Test
    public void test_Invoice_ImmediatePayment_SalesOrder_Success() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true))).thenReturn(TEST_NEW_XACT_ID, (TEST_NEW_XACT_ID + 111111111));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Mock base transaction query verification stub.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(VwXactList.class))).thenReturn(this.mockSingleXact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }

        // Mock create customer transaction activity stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(CustomerActivity.class), eq(true))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert customer activity case setup failed");
        }

        // Mock sales invoice transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesInvoice.class), eq(true))).thenReturn(TEST_NEW_INVOICE_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Setup mock for getting sales order current status calls.
        List<SalesOrderStatus> mockCurrentStatusResponse = SalesApiTestData.createMockSingleSalesOrderStatus(SalesApiConst.STATUS_CODE_INVOICED,
                "Invoice");
        List<SalesOrderStatus> mockCurrentStatusResponse2 = SalesApiTestData.createMockSingleSalesOrderStatus(SalesApiConst.STATUS_CODE_CLOSED,
                "Closed");
        try {
            when(this.mockPersistenceClient.retrieveList(isA(SalesOrderStatus.class))).thenReturn(mockCurrentStatusResponse,
                    mockCurrentStatusResponse2);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order closed status fetch test case setup failed");
        }

        // Setup mock to get the current sales order status as "QUOTE"
        SalesOrderStatusHist mockCurrentSalesOrderStatus = new SalesOrderStatusHist();
        mockCurrentSalesOrderStatus.setSoId(TEST_SALES_ORDER_ID);
        try {
            // Ensure that current status returned is "Quote"
            when(this.mockPersistenceClient.retrieveObject(eq(mockCurrentSalesOrderStatus))).thenReturn(this.mockStatusHistoryAllResponse.get(1),
                    this.mockStatusHistoryAllResponse.get(2));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Setup mock to get all line items of a sales order stub
        SalesOrderItems so = new SalesOrderItems();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenReturn(this.mockSalesOrderItemsAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order items fetch test case setup failed");
        }

        // Mock Customer query stub.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(Customer.class))).thenReturn(this.mockCustomerFetchSingleResponse);
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

        // Setup mocks for email confirmation.
        this.setupMocksForEmailConfirmation();

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        int results = 0;
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            results = api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, true);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals((TEST_NEW_XACT_ID + 111111111), results);
        Assert.assertEquals(true, this.existingSalesOrderDto.isInvoiced());
    }

    @Test
    public void test_Validation_Invoicing_SalesOrder_Null() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.invoiceSalesOrder(null, this.existingLineItemListDto, true);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }

    @Test
    public void test_Validation_Invoicing_SalesOrderItemList_Null() {
        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            api.invoiceSalesOrder(this.existingSalesOrderDto, null, true);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }

    @Test
    public void test_Validation_Invoicing_SalesOrder_NotFound() {
        // Setup mock for not found sales order validation error
        SalesOrder so = new SalesOrder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order fetch test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, true);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void test_Validation_Invoicing_Customer_NotFound() {
        // Setup mock for not found customer validation error
        Customer mockCustomerCriteria = new Customer();
        mockCustomerCriteria.setCustomerId(TEST_CUSTOMER_ID);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCustomerCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, true);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void test_Invoicing_Db_Exception_Xact_Verification_Error() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true))).thenReturn(TEST_NEW_XACT_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Mock base transaction query verification stub.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(VwXactList.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, true);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof XactApiException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof XactApiException);
            Assert.assertTrue(e.getCause().getCause().getCause().getCause() instanceof CannotRetrieveException);
            Assert.assertTrue(e.getCause().getCause().getCause().getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void test_Invoicing_Db_Exception_Invoice_Creation_Error() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, true);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof XactApiException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof XactDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause().getCause() instanceof XactDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause().getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void test_Invoicing_Db_Exception_Setting_SalesOrder_Invoiced_Flag() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesInvoice.class), eq(true))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true))).thenReturn(TEST_NEW_XACT_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Mock base transaction query verification stub.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(VwXactList.class))).thenReturn(this.mockSingleXact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, true);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof SalesInvoiceDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof DatabaseException);
        }
    }

    @Test
    public void test_Invoicing_Db_Exception_Status_OutOfSync() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true))).thenReturn(TEST_NEW_XACT_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
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

        // Mock create customer transaction activity stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(CustomerActivity.class), eq(true))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert customer activity case setup failed");
        }

        // Mock sales invoice transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesInvoice.class), eq(true))).thenReturn(TEST_NEW_INVOICE_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Setup mock for invoice sales order status verification
        SalesOrderStatus mockNextSalesOrderStatusFetchCriteria = new SalesOrderStatus();
        mockNextSalesOrderStatusFetchCriteria.setSoStatusId(SalesApiConst.STATUS_CODE_INVOICED);
        List<SalesOrderStatus> mockNextSalesOrderStatusFetchResponse = SalesApiTestData.createMockSingleSalesOrderStatus(
                SalesApiConst.STATUS_CODE_INVOICED, "Invoice");
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockNextSalesOrderStatusFetchCriteria)))
                    .thenReturn(mockNextSalesOrderStatusFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }

        // Setup mock to get the current sales order status as "CLOSE"
        SalesOrderStatusHist mockCurrentSalesOrderStatus = new SalesOrderStatusHist();
        mockCurrentSalesOrderStatus.setSoId(TEST_SALES_ORDER_ID);
        try {
            // Ensure that current status returned is "Quote"
            when(this.mockPersistenceClient.retrieveObject(eq(mockCurrentSalesOrderStatus))).thenReturn(this.mockStatusHistoryAllResponse.get(5));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Setup mock to get all line items of a sales order stub
        SalesOrderItems so = new SalesOrderItems();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenReturn(this.mockSalesOrderItemsAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order items fetch test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, false);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof OutOfSyncSalesOrderStatusesException);
        }
    }

    @Test
    public void test_Invoicing_Db_Exception_CashReceipt_Payment() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true))).thenReturn(TEST_NEW_XACT_ID).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
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

        // Mock create customer transaction activity stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(CustomerActivity.class), eq(true))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert customer activity case setup failed");
        }

        // Mock sales invoice transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesInvoice.class), eq(true))).thenReturn(TEST_NEW_INVOICE_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Setup mock for invoice sales order status verification
        SalesOrderStatus mockNextSalesOrderStatusFetchCriteria = new SalesOrderStatus();
        mockNextSalesOrderStatusFetchCriteria.setSoStatusId(SalesApiConst.STATUS_CODE_INVOICED);
        List<SalesOrderStatus> mockNextSalesOrderStatusFetchResponse = SalesApiTestData.createMockSingleSalesOrderStatus(
                SalesApiConst.STATUS_CODE_INVOICED, "Invoice");
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockNextSalesOrderStatusFetchCriteria)))
                    .thenReturn(mockNextSalesOrderStatusFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }

        // Setup mock to get the current sales order status as "Quote"
        SalesOrderStatusHist mockCurrentSalesOrderStatus = new SalesOrderStatusHist();
        mockCurrentSalesOrderStatus.setSoId(TEST_SALES_ORDER_ID);
        try {
            // Ensure that current status returned is "Quote"
            when(this.mockPersistenceClient.retrieveObject(eq(mockCurrentSalesOrderStatus))).thenReturn(this.mockStatusHistoryAllResponse.get(1));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Setup mock to get all line items of a sales order stub
        SalesOrderItems so = new SalesOrderItems();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenReturn(this.mockSalesOrderItemsAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order items fetch test case setup failed");
        }

        // Perform test
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, true);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
        }
    }

    @Test
    public void test_Invoicing_Db_Exception_CashReceipt_Email_Confirmation() {
        // Mock base transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(Xact.class), eq(true))).thenReturn(TEST_NEW_XACT_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
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

        // Mock create customer transaction activity stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(CustomerActivity.class), eq(true))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert customer activity case setup failed");
        }

        // Mock sales invoice transaction creation stub.
        try {
            when(this.mockPersistenceClient.insertRow(isA(SalesInvoice.class), eq(true))).thenReturn(TEST_NEW_INVOICE_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }

        // Setup mock for invoice sales order status verification
        SalesOrderStatus mockNextSalesOrderStatusFetchCriteria = new SalesOrderStatus();
        mockNextSalesOrderStatusFetchCriteria.setSoStatusId(SalesApiConst.STATUS_CODE_INVOICED);
        List<SalesOrderStatus> mockNextSalesOrderStatusFetchResponse = SalesApiTestData.createMockSingleSalesOrderStatus(
                SalesApiConst.STATUS_CODE_INVOICED, "Invoice");
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockNextSalesOrderStatusFetchCriteria)))
                    .thenReturn(mockNextSalesOrderStatusFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }

        // Setup mock to get the current sales order status as "Quote"
        SalesOrderStatusHist mockCurrentSalesOrderStatus = new SalesOrderStatusHist();
        mockCurrentSalesOrderStatus.setSoId(TEST_SALES_ORDER_ID);
        try {
            // Ensure that current status returned is "Quote"
            when(this.mockPersistenceClient.retrieveObject(eq(mockCurrentSalesOrderStatus))).thenReturn(this.mockStatusHistoryAllResponse.get(1));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Setup mock to get all line items of a sales order stub
        SalesOrderItems so = new SalesOrderItems();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenReturn(this.mockSalesOrderItemsAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order items fetch test case setup failed");
        }

        // Mock Customer query stub.
        try {
            when(this.mockPersistenceClient.retrieveList(isA(Customer.class))).thenReturn(this.mockCustomerFetchSingleResponse);
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
        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            this.existingSalesOrderDto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            api.invoiceSalesOrder(this.existingSalesOrderDto, this.existingLineItemListDto, true);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesApiException);
        }
    }

    @Test
    public void testSuccess_Close_SalesOrder_For_Payment_Single() {
        // Setup new mock sales order
        List<SalesOrder> soList = SalesApiTestData.createMockSalesOrderSingleResponse();
        List<SalesOrderDto> soDtoList = new ArrayList<>();

        // Five sales orders
        for (SalesOrder item : soList) {
            item.setOrderTotal(100.00);
            item.setInvoiced(1);
            SalesOrderDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(item);
            soDtoList.add(dto);
        }

        // Setup mock for validating "Closed" sales order status verification
        SalesOrderStatus mockNextSalesOrderStatusFetchCriteria = new SalesOrderStatus();
        mockNextSalesOrderStatusFetchCriteria.setSoStatusId(SalesApiConst.STATUS_CODE_CLOSED);
        List<SalesOrderStatus> mockNextSalesOrderStatusFetchResponse = SalesApiTestData.createMockSingleSalesOrderStatus(
                SalesApiConst.STATUS_CODE_CLOSED, "Closed");
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockNextSalesOrderStatusFetchCriteria)))
                    .thenReturn(mockNextSalesOrderStatusFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }
        
        // Setup mock to get the current status of the sales order
        SalesOrderStatusHist mockStatusHistoryInvoiced = createMockSalesOrderStatusHistoryInvoicedResponse();
        SalesOrderStatusHist mockCurrentSalesOrderStatus = new SalesOrderStatusHist();
        mockCurrentSalesOrderStatus.setSoId(1000);
        try {
            // Ensure that current status returned is "Invoiced"
            when(this.mockPersistenceClient.retrieveObject(eq(mockCurrentSalesOrderStatus))).thenReturn(mockStatusHistoryInvoiced);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Setup mock transaction
        List<VwXactList> xactList = this.createMockXactSingleFetchResponse();
        VwXactList vwXact = xactList.get(0);
        vwXact.setXactAmount(100.00);
        XactDto xactDto = Rmt2XactDtoFactory.createXactInstance(vwXact);

        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        int results = 0;
        try {
            results = api.closeSalesOrderForPayment(soDtoList, xactDto);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals(1, results);

    }

    @Test
    public void testValidation_Close_SalesOrder_For_Payment_Null_Order() {
        // Setup new mock sales order
        List<SalesOrderDto> soDtoList = new ArrayList<>();

        SalesOrderDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(null);
        soDtoList.add(dto);

        // Setup mock transaction
        List<VwXactList> xactList = this.createMockXactSingleFetchResponse();
        VwXactList vwXact = xactList.get(0);
        vwXact.setXactAmount(100.00);
        XactDto xactDto = Rmt2XactDtoFactory.createXactInstance(vwXact);

        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.closeSalesOrderForPayment(null, xactDto);
            Assert.fail("Test failed: expected an exception to be thrown as a result of a validation error");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals("A list of sales orders is required", e.getMessage());
        }

    }

    @Test
    public void testValidation_Close_SalesOrder_For_Payment_Empty_Order_List() {
        // Setup new mock sales order
        List<SalesOrderDto> soDtoList = new ArrayList<>();

        // Setup mock transaction
        List<VwXactList> xactList = this.createMockXactSingleFetchResponse();
        VwXactList vwXact = xactList.get(0);
        vwXact.setXactAmount(100.00);
        XactDto xactDto = Rmt2XactDtoFactory.createXactInstance(vwXact);

        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.closeSalesOrderForPayment(soDtoList, xactDto);
            Assert.fail("Test failed: expected an exception to be thrown as a result of a validation error");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals("There are no sales orders to close out", e.getMessage());
        }

    }

    @Test
    public void testValidation_Close_SalesOrder_For_Payment_Transaction_Null() {
        // Setup new mock sales order
        List<SalesOrder> soList = SalesApiTestData.createMockSalesOrderSingleResponse();
        List<SalesOrderDto> soDtoList = new ArrayList<>();

        // Five sales orders
        for (SalesOrder item : soList) {
            item.setOrderTotal(100.00);
            item.setInvoiced(1);
            SalesOrderDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(item);
            soDtoList.add(dto);
        }

        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.closeSalesOrderForPayment(soDtoList, null);
            Assert.fail("Test failed: expected an exception to be thrown as a result of a validation error");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals("Payment transaction is required", e.getMessage());
        }

    }

    @Test
    public void testValidation_Close_SalesOrder_For_Payment_All_SalesOrders_Not_invoiced() {
        // Setup new mock sales order
        List<SalesOrder> soList = SalesApiTestData.createMockSalesOrderSingleResponse();
        List<SalesOrderDto> soDtoList = new ArrayList<>();

        // Five sales orders
        for (SalesOrder item : soList) {
            item.setOrderTotal(100.00);
            item.setInvoiced(0);
            SalesOrderDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(item);
            soDtoList.add(dto);
        }

        // Setup mock transaction
        List<VwXactList> xactList = this.createMockXactSingleFetchResponse();
        VwXactList vwXact = xactList.get(0);
        vwXact.setXactAmount(100.00);
        XactDto xactDto = Rmt2XactDtoFactory.createXactInstance(vwXact);

        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.closeSalesOrderForPayment(soDtoList, xactDto);
            Assert.fail("Test failed: expected an exception to be thrown as a result of a validation error");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals(
                    "Close of sales order for payment operation aborted due sales order is not invoiced: [sales order id=1000]",
                    e.getMessage());
        }

    }

    @Test
    public void testValidation_Close_SalesOrder_For_Payment_Order_Total_Not_Equal_Sum_of_Items() {
        // Setup new mock sales order
        List<SalesOrder> soList = SalesApiTestData.createMockSalesOrderSingleResponse();
        List<SalesOrderDto> soDtoList = new ArrayList<>();

        // Five sales orders
        for (SalesOrder item : soList) {
            item.setOrderTotal(999.00);
            item.setInvoiced(1);
            SalesOrderDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(item);
            soDtoList.add(dto);
        }

        // Setup mock transaction
        List<VwXactList> xactList = this.createMockXactSingleFetchResponse();
        VwXactList vwXact = xactList.get(0);
        vwXact.setXactAmount(100.00);
        XactDto xactDto = Rmt2XactDtoFactory.createXactInstance(vwXact);

        SalesApi api = SalesApiFactory.createApi(mockDaoClient);
        try {
            api.closeSalesOrderForPayment(soDtoList, xactDto);
            Assert.fail("Test failed: expected an exception to be thrown as a result of a validation error");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals(
                    "The total dollar amount of selected invoices must be equal to the payment amount received",
                    e.getMessage());
        }

    }
}