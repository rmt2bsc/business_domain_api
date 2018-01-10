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
import org.dao.mapping.orm.rmt2.PurchaseOrderStatusHist;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dao.transaction.purchases.vendor.VendorPurchasesConst;
import org.dao.transaction.purchases.vendor.VendorPurchasesDaoException;
import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.PurchaseOrderStatusHistDto;
import org.dto.VwVendorItemDto;
import org.dto.adapter.orm.transaction.purchaseorder.Rmt2PurchaseOrderDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.purchases.vendor.VendorPurchasesApi;
import org.modules.transaction.purchases.vendor.VendorPurchasesApiException;
import org.modules.transaction.purchases.vendor.VendorPurchasesApiFactory;
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
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class VendorPurchaseQueryApiTest extends VendorPurchaseApiTestData {
    private static final int TEST_PO_ID = 330;
    private static final int TEST_PO_ITEM_ID = 8880;
    private static final int TEST_XACT_ID = 7000;
    private static final int TEST_CREDITOR_ID = 1111111;
    
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
        Assert.assertEquals(100.00, results.getPurchaseOrderTotal(), 0);
        Assert.assertEquals("330-0000-0000-0000", results.getRefNo());
    }

    @Test
    public void testFetch_PurchaseOrder_NotFound() {
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
    public void testFetchSingle_PurchaseOrder_TooManyRowsRetunred() {
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
    public void testFetch_PurchaseOrder_DB_Error() {
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
    public void testFetch_PurchaseOrderItems_NotFound() {
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
    public void testFetchSingle_PurchaseOrderItem_NotFound() {
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
    public void testFetchSingle_PurchaseOrderItem_TooManyRows() {
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
    public void testFetchSingle_PurchaseOrderItem_DB_Error() {
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
    public void testFetch_PurchaseOrderAvailableItems_NotFound() {
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
    public void testFetch_PurchaseOrderAvailableItems_DB_Error() {
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
    public void testFetch_VendorItemInventoryData_NotFound() {
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
    public void testFetch_VendorItemInventoryData_TooManyRowsReturned() {
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
    public void testFetch_VendorItemInventoryData_DB_Error() {
        // Mock method call to get vendor item inventory data
        VwVendorItems mockCriteria = new VwVendorItems();
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        mockCriteria.setItemId(TEST_PO_ITEM_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single vendor item/item master fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.getVendorInventoryItem(TEST_CREDITOR_ID, TEST_PO_ITEM_ID);
            Assert.fail(
                    "Test failed due to exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(
                    e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(
                    e.getCause().getCause() instanceof DatabaseException);
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
}