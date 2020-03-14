package org.rmt2.api.transaction.purchases.vendor;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.PurchaseOrderItems;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatus;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatusHist;
import org.dao.mapping.orm.rmt2.VendorItems;
import org.dao.mapping.orm.rmt2.VwVendorItemPurchaseOrderItem;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dao.transaction.purchases.vendor.VendorPurchasesConst;
import org.dao.transaction.purchases.vendor.VendorPurchasesDaoException;
import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.PurchaseOrderStatusDto;
import org.dto.PurchaseOrderStatusHistDto;
import org.dto.VendorItemDto;
import org.dto.VwVendorItemDto;
import org.dto.adapter.orm.transaction.purchaseorder.Rmt2PurchaseOrderDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.purchases.vendor.CannotCalculatePurchaseOrderException;
import org.modules.transaction.purchases.vendor.VendorPurchasesApi;
import org.modules.transaction.purchases.vendor.VendorPurchasesApiException;
import org.modules.transaction.purchases.vendor.VendorPurchasesApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2Date;

/**
 * Tests creditor purchases transaction query Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class VendorPurchaseQueryApiTest extends VendorPurchaseApiTestData {
    private static final int TEST_PO_ID = 330;
    private static final int TEST_PO_ITEM_ID = 8880;
    private static final int TEST_XACT_ID = 7000;
    private static final int TEST_CREDITOR_ID = 1111111;
    private static final String TEST_VENDOR_ITEM_NO = "111-111";
    
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
    public void testFetchAll_PurchaseOrders() {
        // Mock method call to get vendor purchase orders 
        PurchaseOrder mockCriteria = new PurchaseOrder();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrders);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase orders fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        PurchaseOrderDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderInstance(null);
        List<PurchaseOrderDto> results = null;
        try {
            results = api.getPurchaseOrder(criteria);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }

    @Test
    public void testFetchSingle_PurchaseOrder() {
        // Mock method call to get vendor purchase orders 
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase orders fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        PurchaseOrderDto results = null;
        try {
            results = api.getPurchaseOrder(TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(TEST_PO_ID, results.getPoId());
        Assert.assertEquals(TEST_XACT_ID, results.getXactId());
        Assert.assertEquals(TEST_CREDITOR_ID, results.getCreditorId());
        Assert.assertEquals(12500.00, results.getPurchaseOrderTotal(), 0);
        Assert.assertEquals("330-0000-0000-0000", results.getRefNo());
    }

    @Test
    public void testError_PurchaseOrder_NotFound() {
        // Mock method call to get vendor purchase orders 
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase orders fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        PurchaseOrderDto results = null;
        try {
            results = api.getPurchaseOrder(TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }

    @Test
    public void testError_Single_PurchaseOrder_TooManyRowsRetunred() {
        // Mock method call to get vendor purchase orders 
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrders);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase orders fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrder(TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
        }
    }
    
    @Test
    public void testError_PurchaseOrder_DB_Error() {
        // Mock method call to get vendor purchase orders 
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase orders fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrder(TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_FetchPurchaseOrder_Null_CriteriaObject() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        PurchaseOrderDto criteria = null;
        try {
            api.getPurchaseOrder(criteria);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_FetchSinglePurchaseOrder_Null_PO_Id() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        Integer criteria = null;
        try {
            api.getPurchaseOrder(criteria);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_FetchSinglePurchaseOrder_Zero_PO_Id() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        Integer criteria = 0;
        try {
            api.getPurchaseOrder(criteria);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_PurchaseOrderItems() {
        // Mock method call to get vendor purchase order items 
        PurchaseOrderItems mockCriteria = new PurchaseOrderItems();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrderItems);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        List<PurchaseOrderItemDto> results = null;
        try {
            results = api.getPurchaseOrderItems(TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }
    
    @Test
    public void testValidation_FetchPurchaseOrderItems_Null_PO_Id() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderItems(null);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_FetchPurchaseOrderItems_Zero_PO_Id() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderItems(0);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testError_PurchaseOrderItems_NotFound() {
        // Mock method call to get vendor purchase order items 
        PurchaseOrderItems mockCriteria = new PurchaseOrderItems();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        List<PurchaseOrderItemDto> results = null;
        try {
            results = api.getPurchaseOrderItems(TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchSingle_PurchaseOrderItem() {
        // Mock method call to get vendor purchase order items 
        PurchaseOrderItems mockCriteria = new PurchaseOrderItems();
        mockCriteria.setPoItemId(TEST_PO_ITEM_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrderItem);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        PurchaseOrderItemDto results = null;
        try {
            results = api.getPurchaseOrderItem(TEST_PO_ITEM_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(TEST_PO_ITEM_ID, results.getPoItemId());
        Assert.assertEquals(TEST_PO_ID, results.getPoId());
        Assert.assertEquals(100, results.getItemId());
        Assert.assertEquals(100.00, results.getActualUnitCost(), 0);
        Assert.assertEquals(11, results.getQtyOrdered());
        Assert.assertEquals(4, results.getQtyRcvd());
        Assert.assertEquals(0, results.getQtyRtn());
    }
    
    @Test
    public void testError_Single_PurchaseOrderItem_NotFound() {
        // Mock method call to get vendor purchase order items 
        PurchaseOrderItems mockCriteria = new PurchaseOrderItems();
        mockCriteria.setPoItemId(TEST_PO_ITEM_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        PurchaseOrderItemDto results = null;
        try {
            results = api.getPurchaseOrderItem(TEST_PO_ITEM_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testError_Single_PurchaseOrderItem_TooManyRows() {
        // Mock method call to get vendor purchase order items
        PurchaseOrderItems mockCriteria = new PurchaseOrderItems();
        mockCriteria.setPoItemId(TEST_PO_ITEM_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrderItems);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderItem(TEST_PO_ITEM_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
        }
    }

    @Test
    public void testError_Single_PurchaseOrderItem_DB_Error() {
        // Mock method call to get vendor purchase order items
        PurchaseOrderItems mockCriteria = new PurchaseOrderItems();
        mockCriteria.setPoItemId(TEST_PO_ITEM_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderItem(TEST_PO_ITEM_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testFetch_PurchaseOrderAvailableItems() {
        // Mock method call to get purchase order available items 
        VwVendorItems mockCriteria = new VwVendorItems();
        Set<String> customSql = new HashSet<>();
        customSql.add("item_id not in ( select item_id from purchase_order_items where po_id = " + TEST_PO_ID + ")");
        mockCriteria.setCustomCriteria(customSql);
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVwVendorItems);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase orders fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        List<VwVendorItemDto> results = null;
        try {
            results = api.getPurchaseOrderAvailableItems(TEST_CREDITOR_ID, TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            VwVendorItemDto item = results.get(ndx);
            Assert.assertEquals(TEST_PO_ITEM_ID + ndx, item.getItemId());
            Assert.assertEquals(TEST_CREDITOR_ID, item.getVendorId());
            Assert.assertEquals(90.00 + ndx, item.getUnitCost(), 0);
            Assert.assertEquals(100.00 + ndx, item.getQtyOnHand(), 0);
            Assert.assertEquals(0, item.getOverrideRetail());
            Assert.assertEquals(3, item.getMarkup(), 0);
            Assert.assertEquals("VendorItemNo-" + item.getVendorId() + "-" + item.getItemId(), item.getVendorItemNo());
            Assert.assertEquals("ItemSerialNo-" + item.getVendorId() + "-" + item.getItemId(), item.getItemSerialNo());
            Assert.assertEquals("Description-" + item.getVendorId() + "-" + item.getItemId(), item.getItemName());
        }
    }
    
    @Test
    public void testError_PurchaseOrderAvailableItems_NotFound() {
        // Mock method call to get purchase order available items 
        VwVendorItems mockCriteria = new VwVendorItems();
        Set<String> customSql = new HashSet<>();
        customSql.add("item_id not in ( select item_id from purchase_order_items where po_id = " + TEST_PO_ID + ")");
        mockCriteria.setCustomCriteria(customSql);
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase orders fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        List<VwVendorItemDto> results = null;
        try {
            results = api.getPurchaseOrderAvailableItems(TEST_CREDITOR_ID, TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testError_PurchaseOrderAvailableItems_DB_Error() {
        // Mock method call to get purchase order available items
        VwVendorItems mockCriteria = new VwVendorItems();
        Set<String> customSql = new HashSet<>();
        customSql.add("item_id not in ( select item_id from purchase_order_items where po_id = " + TEST_PO_ID + ")");
        mockCriteria.setCustomCriteria(customSql);
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase orders fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderAvailableItems(TEST_CREDITOR_ID, TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_PurchaseOrderAvailableItems_Null_VendorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderAvailableItems(null, TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_PurchaseOrderAvailableItems_Null_POId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderAvailableItems(TEST_CREDITOR_ID, null);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_PurchaseOrderAvailableItems_Zero_VendorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderAvailableItems(0, TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_PurchaseOrderAvailableItems_Zero_POId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderAvailableItems(TEST_CREDITOR_ID, 0);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_PurchaseOrderHistoryAll() {
        // Mock method call to get purchase order history 
        PurchaseOrderStatusHist mockCriteria = new PurchaseOrderStatusHist();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrderStatusHistory);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order history fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        List<PurchaseOrderStatusHistDto> results = null;
        try {
            results = api.getPurchaseOrderHistory(TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            PurchaseOrderStatusHistDto item = results.get(ndx);
            Assert.assertEquals(1 + ndx, item.getPoStatusHistId());
            Assert.assertEquals(TEST_PO_ID, item.getPoId());
            if (ndx == 0) {
                Assert.assertTrue(item.getPoStatusId() < 0);
            }
            else {
                Assert.assertTrue(item.getPoStatusId() > 0);
            }
            Date dt = RMT2Date.stringToDate("2017-" + (ndx + 1) + "-" + (ndx +1));
            Assert.assertEquals(dt, item.getEffectiveDate());
            if (ndx < 3) {
                Assert.assertEquals(dt, item.getEndDate());
            }
            else {
                Assert.assertNull(item.getEndDate());
            }
        }
    }
    
    @Test
    public void testValidate_PurchaseOrderHistoryAll_Null_PO_Id() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderHistory(null);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);    
        }
    }
    
    @Test
    public void testValidate_PurchaseOrderHistoryAll_Negative_PO_Id() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderHistory(-1);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);    
        }
    }
    
    @Test
    public void testValidate_PurchaseOrderHistoryAll_Zero_PO_Id() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderHistory(0);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);    
        }
    }
    
    @Test
    public void testFetch_PurchaseOrderHistoryCurrentStatusOnly() {
        // Mock method call to get purchase order history 
        PurchaseOrderStatusHist mockCriteria = new PurchaseOrderStatusHist();
        Set<String> customSql = new HashSet<>();
        customSql.add("end_date is null");
        mockCriteria.setCustomCriteria(customSql);
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrderCurrentStatusHistory);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase orders fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        PurchaseOrderStatusHistDto results = null;
        try {
            results = api.getCurrentPurchaseOrderHistory(TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.getPoStatusHistId());
        Assert.assertEquals(TEST_PO_ID, results.getPoId());
        Assert.assertEquals(VendorPurchasesConst.PURCH_STATUS_QUOTE, results.getPoStatusId());
        Date dt = RMT2Date.stringToDate("2017-01-02");
        Assert.assertEquals(dt, results.getEffectiveDate());
        Assert.assertNull(results.getEndDate());
    }
    
    @Test
    public void testFetch_VendorItemInventoryData() {
        // Mock method call to get vendor item inventory data
        VwVendorItems mockCriteria = new VwVendorItems();
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        mockCriteria.setItemId(TEST_PO_ITEM_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVwVendorItem);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor item/item master fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        VwVendorItemDto results = null;
        try {
            results = api.getVendorInventoryItem(TEST_CREDITOR_ID, TEST_PO_ITEM_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(TEST_PO_ITEM_ID, results.getItemId());
        Assert.assertEquals(TEST_CREDITOR_ID, results.getVendorId());
        Assert.assertEquals(90.00, results.getUnitCost(), 0);
        Assert.assertEquals(100, results.getQtyOnHand());
        Assert.assertEquals(0, results.getOverrideRetail());
        Assert.assertEquals(3, results.getMarkup(), 0);
        Assert.assertEquals("VendorItemNo-" + results.getVendorId() + "-"
                + results.getItemId(), results.getVendorItemNo());
        Assert.assertEquals("ItemSerialNo-" + results.getVendorId() + "-"
                + results.getItemId(), results.getItemSerialNo());
        Assert.assertEquals("Description-" + results.getVendorId() + "-"
                + results.getItemId(), results.getItemName());
            
    }
    
    @Test
    public void testError_VendorItemInventoryData_NotFound() {
        // Mock method call to get vendor item inventory data
        VwVendorItems mockCriteria = new VwVendorItems();
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        mockCriteria.setItemId(TEST_PO_ITEM_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor item/item master not found fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        VwVendorItemDto results = null;
        try {
            results = api.getVendorInventoryItem(TEST_CREDITOR_ID, TEST_PO_ITEM_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }
    
    
    @Test
    public void testError_VendorItemInventoryData_TooManyRowsReturned() {
        // Mock method call to get vendor item inventory data
        VwVendorItems mockCriteria = new VwVendorItems();
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        mockCriteria.setItemId(TEST_PO_ITEM_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVwVendorItems);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor item/item master fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorInventoryItem(TEST_CREDITOR_ID, TEST_PO_ITEM_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
        }
    }
    
    @Test
    public void testError_VendorItemInventoryData_DB_Error() {
        // Mock method call to get vendor item inventory data
        VwVendorItems mockCriteria = new VwVendorItems();
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        mockCriteria.setItemId(TEST_PO_ITEM_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor item/item master fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorInventoryItem(TEST_CREDITOR_ID, TEST_PO_ITEM_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_VendorItemInventoryData_Null_CreditorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorInventoryItem(null, TEST_PO_ITEM_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_VendorItemInventoryData_Null_PoId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorInventoryItem(TEST_CREDITOR_ID, null);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_VendorItemInventoryData_Zero_CreditorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorInventoryItem(0, TEST_PO_ITEM_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_VendorItemInventoryData_Zero_PoId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorInventoryItem(TEST_CREDITOR_ID, 0);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_VendorItemInventoryData_Negative_CreditorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorInventoryItem(-123, TEST_PO_ITEM_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_VendorItemInventoryData_Negative_PoId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorInventoryItem(TEST_CREDITOR_ID, -123);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_PurchaseOrderVendorInventoryItems() {
        // Mock method call to get purchase order items that contains combined vendor/inventory info
        VwVendorItemPurchaseOrderItem mockCriteria = new VwVendorItemPurchaseOrderItem();
        mockCriteria.setVendorId(TEST_CREDITOR_ID);
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVwVendorItemPurchaseOrderItems);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All purchase order vendor/inventory items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        List<PurchaseOrderItemDto> results = null;
        try {
            results = api.getPurchaseOrderVendorInventoryItems(TEST_CREDITOR_ID, TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            PurchaseOrderItemDto item = results.get(ndx);
            Assert.assertEquals(TEST_PO_ID, item.getPoId());
            Assert.assertEquals(TEST_CREDITOR_ID, item.getCreditorId());
            Assert.assertEquals(100.00 + ndx, item.getVendorUnitCost(), 0);
            Assert.assertEquals(100 + ndx, item.getVendorQtyOnHand());
            Assert.assertEquals(10 + ndx, item.getQtyOrdered());
            Assert.assertEquals(0, item.getQtyRcvd());
            Assert.assertEquals(0, item.getQtyRtn());
            Assert.assertEquals(0, item.getVendorOverrideRetail());
            Assert.assertEquals(3, item.getVendorMarkup(), 0);
            Assert.assertEquals("VendorItemNo-" + item.getCreditorId() + "-"
                    + item.getPoId(), item.getVendorItemNo());
            Assert.assertEquals("ItemSerialNo-" + item.getCreditorId() + "-"
                    + item.getPoId(), item.getVendorItemSerialNo());
            Assert.assertEquals("Description-" + item.getCreditorId() + "-"
                    + item.getPoId(), item.getEntityName());            
        }
    }
    
    
    @Test
    public void testError_PurchaseOrderVendorInventoryItems_NotFound() {
        // Mock method call to get purchase order items that contains combined vendor/inventory info
        VwVendorItemPurchaseOrderItem mockCriteria = new VwVendorItemPurchaseOrderItem();
        mockCriteria.setVendorId(TEST_CREDITOR_ID);
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All purchase order vendor/inventory items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        List<PurchaseOrderItemDto> results = null;
        try {
            results = api.getPurchaseOrderVendorInventoryItems(TEST_CREDITOR_ID, TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
   }
    
    @Test
    public void testError_PurchaseOrderVendorInventoryItems_DB_Error() {
        // Mock method call to get purchase order items that contains combined vendor/inventory info
        VwVendorItemPurchaseOrderItem mockCriteria = new VwVendorItemPurchaseOrderItem();
        mockCriteria.setVendorId(TEST_CREDITOR_ID);
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All purchase order vendor/inventory items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderVendorInventoryItems(TEST_CREDITOR_ID, TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
   }
    
    @Test
    public void testValidation_PurchaseOrderVendorInventoryItems_Null_VendorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderVendorInventoryItems(null, TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
   }
    
    @Test
    public void testValidation_PurchaseOrderVendorInventoryItems_Null_PoId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderVendorInventoryItems(TEST_CREDITOR_ID, null);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
   }
    
    @Test
    public void testValidation_PurchaseOrderVendorInventoryItems_Negative_VendorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderVendorInventoryItems(-123, TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
   }
    
    @Test
    public void testValidation_PurchaseOrderVendorInventoryItems_Negative_PoId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderVendorInventoryItems(TEST_CREDITOR_ID, -123);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
   }
    
    @Test
    public void testValidation_PurchaseOrderVendorInventoryItems_Zero_VendorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderVendorInventoryItems(0, TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
   }
    
    @Test
    public void testValidation_PurchaseOrderVendorInventoryItems_Zero_PoId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderVendorInventoryItems(TEST_CREDITOR_ID, 0);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
   }
    
    @Test
    public void testFetch_SingleVendorItem() {
        // Mock method call to get a single vendor item.
        VendorItems mockCriteria = new VendorItems();
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        mockCriteria.setVendorItemNo(TEST_VENDOR_ITEM_NO);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVendorItem);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor item fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        VendorItemDto results = null;
        try {
            results = api.getVendorItem(TEST_CREDITOR_ID, TEST_VENDOR_ITEM_NO);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        
        Assert.assertEquals(TEST_PO_ITEM_ID, results.getItemId());
        Assert.assertEquals(TEST_CREDITOR_ID, results.getVendorId());
        Assert.assertEquals(TEST_VENDOR_ITEM_NO, results.getVendorItemNo());
        Assert.assertEquals("111-ABC", results.getVendorItemSerialNo());
        Assert.assertEquals(90.00, results.getVendorItemUnitCost(), 0);
   }
    
    @Test
    public void testError_SingleVendorItem_NotFound() {
        // Mock method call to get a single vendor item.
        VendorItems mockCriteria = new VendorItems();
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        mockCriteria.setVendorItemNo(TEST_VENDOR_ITEM_NO);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor item fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        VendorItemDto results = null;
        try {
            results = api.getVendorItem(TEST_CREDITOR_ID, TEST_VENDOR_ITEM_NO);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
   }
    
    @Test
    public void testError_SingleVendorItem_TooManyRows() {
        // Mock method call to get a single vendor item.
        VendorItems mockCriteria = new VendorItems();
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        mockCriteria.setVendorItemNo(TEST_VENDOR_ITEM_NO);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockVendorItems);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor item fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorItem(TEST_CREDITOR_ID, TEST_VENDOR_ITEM_NO);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
        }
   }
    
    @Test
    public void testError_SingleVendorItem_DB_Error() {
        // Mock method call to get a single vendor item.
        VendorItems mockCriteria = new VendorItems();
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        mockCriteria.setVendorItemNo(TEST_VENDOR_ITEM_NO);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor item fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorItem(TEST_CREDITOR_ID, TEST_VENDOR_ITEM_NO);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
   }
    
    @Test
    public void testValidation_SingleVendorItem_Null_VendorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorItem(null, TEST_VENDOR_ITEM_NO);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_SingleVendorItem_Null_VendorItemNo() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorItem(TEST_CREDITOR_ID, null);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_SingleVendorItem_Empty_VendorItemNo() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorItem(TEST_CREDITOR_ID, "");
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_SingleVendorItem_Negative_VendorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorItem(-1234, TEST_VENDOR_ITEM_NO);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_SingleVendorItem_Zero_VendorId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorItem(0, TEST_VENDOR_ITEM_NO);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_SinglePurchaseOrderStatus() {
        // Mock method call to get a single purchase order status.
        PurchaseOrderStatus mockCriteria = new PurchaseOrderStatus();
        mockCriteria.setPoStatusId(VendorPurchasesConst.PURCH_STATUS_QUOTE);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrderStatus);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("A purchase order status fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        PurchaseOrderStatusDto results = null;
        try {
            results = api.getPurchaseOrderStatus(VendorPurchasesConst.PURCH_STATUS_QUOTE);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(VendorPurchasesConst.PURCH_STATUS_QUOTE, results.getPoStatusId());
        Assert.assertEquals("Quote", results.getPoStatusDescription());
   }
    
    @Test
    public void testError_SinglePurchaseOrderStatus_NotFound() {
        // Mock method call to get a single purchase order status.
        PurchaseOrderStatus mockCriteria = new PurchaseOrderStatus();
        mockCriteria.setPoStatusId(VendorPurchasesConst.PURCH_STATUS_QUOTE);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("A purchase order status fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        PurchaseOrderStatusDto results = null;
        try {
            results = api.getPurchaseOrderStatus(VendorPurchasesConst.PURCH_STATUS_QUOTE);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
   }
    
    @Test
    public void testError_SinglePurchaseOrderStatus_TooManyRows() {
        // Mock method call to get a single purchase order status.
        PurchaseOrderStatus mockCriteria = new PurchaseOrderStatus();
        mockCriteria.setPoStatusId(VendorPurchasesConst.PURCH_STATUS_QUOTE);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrderStatuses);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("A purchase order status fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderStatus(VendorPurchasesConst.PURCH_STATUS_QUOTE);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
        }
    }
    
    @Test
    public void testError_SinglePurchaseOrderStatus_DB_Error() {
        // Mock method call to get a single purchase order status.
        PurchaseOrderStatus mockCriteria = new PurchaseOrderStatus();
        mockCriteria.setPoStatusId(VendorPurchasesConst.PURCH_STATUS_QUOTE);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("A purchase order status fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderStatus(VendorPurchasesConst.PURCH_STATUS_QUOTE);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_SinglePurchaseOrderStatus_Null_PoStatusId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderStatus(null);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_SinglePurchaseOrderStatus_Zero_PoStatusId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderStatus(0);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_SinglePurchaseOrderStatus_Negative_PoStatusId() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getPurchaseOrderStatus(-1230);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testCalculate_PurchaseOrderTotal() {
        // Mock method call to get vendor purchase order items 
        PurchaseOrderItems mockCriteria = new PurchaseOrderItems();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenReturn(this.mockPurchaseOrderItems);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        double results = 0;
        try {
            results = api.calcPurchaseOrderTotal(TEST_PO_ID);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertTrue(results > 0);
        Assert.assertEquals(12500.00, results, 0);
    }
    
    @Test
    public void testError_Calculate_PurchaseOrderTotal_PurchaseOrder_NotFound() {
        // Mock method call to get vendor purchase order items 
        PurchaseOrderItems mockCriteria = new PurchaseOrderItems();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.calcPurchaseOrderTotal(TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof CannotCalculatePurchaseOrderException);
        }
    }
    
    @Test
    public void testError_Calculate_PurchaseOrderTotal_PurchaseOrder_DB_Error() {
        // Mock method call to get vendor purchase order items 
        PurchaseOrderItems mockCriteria = new PurchaseOrderItems();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
            .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order items fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.calcPurchaseOrderTotal(TEST_PO_ID);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Calculate_PurchaseOrderTotal_PoId_Null() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.calcPurchaseOrderTotal(null);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Calculate_PurchaseOrderTotal_PoId_Zero() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.calcPurchaseOrderTotal(0);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidation_Calculate_PurchaseOrderTotal_PoId_Negative() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.calcPurchaseOrderTotal(-12340);
            Assert.fail("Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
}