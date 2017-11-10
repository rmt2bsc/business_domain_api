package org.rmt2.api.transaction.sales;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.dao.mapping.orm.rmt2.VwSalesOrderInvoice;
import org.dao.mapping.orm.rmt2.VwSalesorderItemsBySalesorder;
import org.dao.transaction.sales.SalesInvoiceDaoException;
import org.dao.transaction.sales.SalesOrderDaoException;
import org.dto.SalesInvoiceDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.SalesOrderStatusDto;
import org.dto.SalesOrderStatusHistDto;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.inventory.InventoryConst;
import org.modules.transaction.sales.MissingCurrentStatusException;
import org.modules.transaction.sales.SalesApi;
import org.modules.transaction.sales.SalesApiConst;
import org.modules.transaction.sales.SalesApiException;
import org.modules.transaction.sales.SalesApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2Date;
import com.util.RMT2String;

/**
 * Tests sales order / sales invoice transaction query Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class SalesApiQueryTest extends SalesApiTestData {

    private static final int TEST_SALES_ORDER_ID = 1000;
    
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
    public void testFetchAll_SalesOrders() {
        // Mock method call to get multiple sales orders
        SalesOrder so = new SalesOrder();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenReturn(this.mockSalesOrderAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesOrderDto criteria = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(null);
        List<SalesOrderDto> results = null;
        try {
            results = api.getSalesOrder(criteria);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            SalesOrderDto item = results.get(ndx);
            Assert.assertEquals((1000 + ndx), item.getSalesOrderId());
            Assert.assertEquals(2000, item.getCustomerId());
            Assert.assertFalse(item.isInvoiced());
            Assert.assertEquals((300 * (ndx + 1)), item.getOrderTotal(), 0);
            String effDate = RMT2String.replace("2017-0%-01", String.valueOf((ndx + 1)), "%");
            Assert.assertEquals(RMT2Date.stringToDate(effDate), item.getSaleOrderDate());
        }
    }
    
    @Test
    public void testFetchAll_SalesOrders_NotFound() {
        // Mock method call to get multiple sales orders
        SalesOrder so = new SalesOrder();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenReturn(this.mockSalesOrderNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesOrderDto criteria = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(null);
        List<SalesOrderDto> results = null;
        try {
            results = api.getSalesOrder(criteria);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchAll_Null_Criteria() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesOrderDto criteria = null;
        try {
            api.getSalesOrder(criteria);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchAll_Db_Exception() {
        // Mock method call to force databae exception
        SalesOrder so = new SalesOrder();
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order fetch test case setup failed");
        }
        
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesOrderDto criteria = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(null);
        try {
            api.getSalesOrder(criteria);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testFetchSingle_SalesOrder() {
        SalesOrder so = new SalesOrder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
            .thenReturn(this.mockSalesOrderSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesOrderDto results = null;
        try {
            results = api.getSalesOrder(TEST_SALES_ORDER_ID);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1000, results.getSalesOrderId());
        Assert.assertEquals(2000, results.getCustomerId());
        Assert.assertFalse(results.isInvoiced());
        Assert.assertEquals(RMT2Date.stringToDate("2017-01-01"), results.getSaleOrderDate());
    }
    
    @Test
    public void testFetchSingle_SalesOrders_NotFound() {
        SalesOrder so = new SalesOrder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
            .thenReturn(this.mockSalesOrderNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesOrderDto results = null;
        try {
            results = api.getSalesOrder(TEST_SALES_ORDER_ID);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchSingle_Null_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        Integer criteria = null;
        try {
            api.getSalesOrder(criteria);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchSingle_Zero_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        Integer criteria = 0;
        try {
            api.getSalesOrder(criteria);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchSingle_Negative_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        Integer criteria = -1;
        try {
            api.getSalesOrder(criteria);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchSingle_Too_Many_Rows_Return() {
        SalesOrder so = new SalesOrder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
            .thenReturn(this.mockSalesOrderAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single slaes order fetch test case setup failed");
        }
        
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getSalesOrder(TEST_SALES_ORDER_ID);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
        }
    }
    
    @Test
    public void testFetchSingle_Db_Exception() {
        // Mock method call to force databae exception
        SalesOrder so = new SalesOrder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order fetch test case setup failed");
        }
        
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getSalesOrder(TEST_SALES_ORDER_ID);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof SalesOrderDaoException);
            Assert.assertTrue(e.getCause().getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testFetchAll_SalesOrderItems() {
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
        List<SalesOrderItemDto> results = null;
        try {
            results = api.getLineItems(TEST_SALES_ORDER_ID);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        double totalAmt = 0;
        for (int ndx = 0; ndx < results.size(); ndx++) {
            SalesOrderItemDto item = results.get(ndx);
            try {
                Assert.assertEquals(Integer.parseInt("8888" + ndx), item.getSoItemId());
            } catch(NumberFormatException e) {
                Assert.fail("Unable to convert calculated sales order item id String value to an integer for index " + ndx);
            }
            
            try {
                Assert.assertEquals(Integer.parseInt("3333" + ndx), item.getItemId());
            } catch(NumberFormatException e) {
                Assert.fail("Unable to convert calculated sales order inventory item id String value to an integer for index " + ndx);
            }
            Assert.assertEquals(1000, item.getSalesOrderId());
            totalAmt += item.getOrderQty() * (item.getInitUnitCost() * item.getInitMarkup());
        }
        Assert.assertEquals(300.00, totalAmt, 0);
    }
    
    @Test
    public void testFetchAll_SalesOrderItems_NotFound() {
        SalesOrderItems so = new SalesOrderItems();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                .thenReturn(this.mockSalesOrderItemsNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order items fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        List<SalesOrderItemDto> results = null;
        try {
            results = api.getLineItems(TEST_SALES_ORDER_ID);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchAll_SalesOrderItems_Null_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getLineItems(null);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchAll_SalesOrderItems_Zero_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getLineItems(0);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchAll_SalesOrderItems_Negative_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getLineItems(-1);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchAll_SalesOrderItems_Db_Exception() {
        SalesOrderItems so = new SalesOrderItems();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single sales order items fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getLineItems(TEST_SALES_ORDER_ID);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testFetchAll_ExtSalesOrderItems() {
        VwSalesorderItemsBySalesorder so = new VwSalesorderItemsBySalesorder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
               .thenReturn(this.mockVwSalesorderItemsBySalesorderAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Extended sales order items fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        List<SalesOrderItemDto> results = null;
        try {
            results = api.getLineItemsExt(TEST_SALES_ORDER_ID);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        double totalAmt = 0;
        for (int ndx = 0; ndx < results.size(); ndx++) {
            SalesOrderItemDto item = results.get(ndx);

            // Test constant values for all line items
            Assert.assertEquals(2000, item.getCustomerId());
            Assert.assertEquals(1351, item.getBusinessId());
            Assert.assertEquals(0, item.getPersonId());
            Assert.assertEquals(0, item.getAcctId());
            Assert.assertNull(item.getAccountNo());
            
            Assert.assertEquals(1000, item.getSalesOrderId());
            Assert.assertFalse(item.isInvoiced());
            Assert.assertEquals(0, item.getOrderTotal(), 0);
            Assert.assertNull(item.getSaleOrderDate());
            
            Assert.assertEquals(InventoryConst.ITEM_TYPE_MERCH, item.getImItemTypeId());
            Assert.assertEquals("ItemTypeMerchandise", item.getImItemTypeDescription());

            // Test variable values for all line items
            try {
                Assert.assertEquals(Integer.parseInt("8888" + ndx), item.getSoItemId());
            } catch(NumberFormatException e) {
                Assert.fail("Unable to convert calculated sales order item id String value to an integer for index " + ndx);
            }
            
            try {
                Assert.assertEquals(Integer.parseInt("3333" + ndx), item.getItemId());
            } catch(NumberFormatException e) {
                Assert.fail("Unable to convert calculated sales order inventory item id String value to an integer for index " + ndx);
            }
            Assert.assertEquals(1, item.getOrderQty(), 0);
            Assert.assertEquals(20, item.getInitUnitCost(), 0);
            Assert.assertEquals(3, item.getInitMarkup(), 0);
            totalAmt += item.getOrderQty() * (item.getInitUnitCost() * item.getInitMarkup());
            
            Assert.assertEquals("1111-111-11" + ndx, item.getImSerialNo());
            Assert.assertEquals("1111111" + ndx, item.getImVendorItemNo());
            Assert.assertEquals(77770, item.getImVendorId());
            Assert.assertEquals("Item" + (ndx + 1), item.getImName());
            Assert.assertEquals(10, item.getImQtyOnHand(), 0);
            Assert.assertEquals(20, item.getImUnitCost(), 0);
            Assert.assertEquals(5, item.getImMarkup(), 0);
        }
        Assert.assertEquals(300.00, totalAmt, 0);
    }
    
    @Test
    public void testFetchAll_ExtSalesOrderItems_NotFound() {
        VwSalesorderItemsBySalesorder so = new VwSalesorderItemsBySalesorder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Extended sales order items fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        List<SalesOrderItemDto> results = null;
        try {
            results = api.getLineItemsExt(TEST_SALES_ORDER_ID);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchAll_ExtSalesOrderItems_Null_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getLineItemsExt(null);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchAll_ExtSalesOrderItems_Zero_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getLineItemsExt(0);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchAll_ExtSalesOrderItems_Negative_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getLineItemsExt(-1);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchAll_ExtSalesOrderItems_Db_Exception() {
        VwSalesorderItemsBySalesorder so = new VwSalesorderItemsBySalesorder();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Extended sales order items fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getLineItemsExt(TEST_SALES_ORDER_ID);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testFetchSingle_SalesInvoice() {
        // Mock method call to get multiple sales orders
        VwSalesOrderInvoice so = new VwSalesOrderInvoice();
        so.setSalesOrderId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenReturn(this.mockVwSalesOrderInvoiceSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order invoice fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesInvoiceDto results = null;
        try {
            results = api.getInvoice(TEST_SALES_ORDER_ID);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(7000, results.getInvoiceId());
        Assert.assertEquals(1000, results.getSalesOrderId());
        Assert.assertEquals(444440, results.getXactId());
        Assert.assertEquals("80000", results.getInvoiceNo());
        
    }
    
    @Test
    public void testFetchSingle_SalesInvoice_NotFound() {
        // Mock method call to get multiple sales orders
        VwSalesOrderInvoice so = new VwSalesOrderInvoice();
        so.setSalesOrderId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenReturn(this.mockVwSalesOrderInvoiceNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order invoice fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesInvoiceDto results = null;
        try {
            results = api.getInvoice(TEST_SALES_ORDER_ID);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchSingle_SalesInvoice_Too_Many_Rows_Returned() {
        // Mock method call to get multiple sales orders
        VwSalesOrderInvoice so = new VwSalesOrderInvoice();
        so.setSalesOrderId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenReturn(this.mockVwSalesOrderInvoiceAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order invoice fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getInvoice(TEST_SALES_ORDER_ID);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
        }
    }
    
    @Test
    public void testFetchSingle_SalesInvoice_Null_SalesOrderId() {
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        Integer nullSalesOrderId = null;
        try {
            api.getInvoice(nullSalesOrderId);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchSingle_SalesInvoice_Zero_SalesOrderId() {
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        Integer salesOrderId = 0;
        try {
            api.getInvoice(salesOrderId);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchSingle_SalesInvoice_Negative_SalesOrderId() {
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        Integer salesOrderId = -1;
        try {
            api.getInvoice(salesOrderId);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetchSingle_SalesInvoice_Db_Exception() {
        // Mock method call to get multiple sales orders
        VwSalesOrderInvoice so = new VwSalesOrderInvoice();
        so.setSalesOrderId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order invoice fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getInvoice(TEST_SALES_ORDER_ID);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesInvoiceDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    
    @Test
    public void testFetchMultiple_SalesInvoice() {
        // Mock method call to get multiple sales orders
        VwSalesOrderInvoice so = new VwSalesOrderInvoice();
        so.setSalesOrderId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenReturn(this.mockVwSalesOrderInvoiceAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order invoice fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        List<SalesInvoiceDto> results = null;
        try {
            VwSalesOrderInvoice item = null;
            SalesInvoiceDto criteria = Rmt2SalesOrderDtoFactory.createSalesIvoiceInstance(item);
            criteria.setSalesOrderId(TEST_SALES_ORDER_ID);
            results = api.getInvoice(criteria);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            SalesInvoiceDto item = results.get(ndx);
            Assert.assertEquals(7000 + ndx, item.getInvoiceId());
            Assert.assertEquals(1000 + ndx, item.getSalesOrderId());
            Assert.assertEquals(444440, item.getXactId());
            Assert.assertEquals("8000" + ndx, item.getInvoiceNo());
            
            String salesOrderDate = RMT2String.replace("2017-0%-01", String.valueOf((ndx + 1)), "%");
            Assert.assertEquals(RMT2Date.stringToDate(salesOrderDate), item.getSaleOrderDate());
            
            Assert.assertEquals(300.00 * (ndx + 1), item.getOrderTotal(), 0);
            Assert.assertEquals(SalesApiConst.STATUS_CODE_INVOICED, item.getSoStatusId());
            Assert.assertTrue(item.isInvoiced());
            
            String invoiceDate = RMT2String.replace("2017-0%-10", String.valueOf((ndx + 1)), "%");
            Assert.assertEquals(RMT2Date.stringToDate(invoiceDate), item.getInvoiceDate());
            Assert.assertEquals(2000, item.getCustomerId());
            Assert.assertEquals(1234, item.getAcctId());
            Assert.assertEquals("111-111", item.getAccountNo());
        }
    }
    
    @Test
    public void testFetchMultiple_SalesInvoice_NotFound() {
        // Mock method call to get multiple sales orders
        VwSalesOrderInvoice so = new VwSalesOrderInvoice();
        so.setSalesOrderId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenReturn(this.mockVwSalesOrderInvoiceNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order invoice fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        List<SalesInvoiceDto> results = null;
        try {
            VwSalesOrderInvoice item = null;
            SalesInvoiceDto criteria = Rmt2SalesOrderDtoFactory.createSalesIvoiceInstance(item);
            criteria.setSalesOrderId(TEST_SALES_ORDER_ID);
            results = api.getInvoice(criteria);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNull(results);
    }
    
    
    @Test
    public void testFetchMultiple_SalesInvoice_Null_Crtieria_Object() {
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            SalesInvoiceDto criteria = null;
            api.getInvoice(criteria);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    

    @Test
    public void testFetchMultiple_SalesInvoice_Db_Exception() {
        // Mock method call to get multiple sales orders
        VwSalesOrderInvoice so = new VwSalesOrderInvoice();
        so.setSalesOrderId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order invoice fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            VwSalesOrderInvoice item = null;
            SalesInvoiceDto criteria = Rmt2SalesOrderDtoFactory.createSalesIvoiceInstance(item);
            criteria.setSalesOrderId(TEST_SALES_ORDER_ID);
            api.getInvoice(criteria);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesInvoiceDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testFetch_CurrentStatus() {
        // Mock method call to get current status of sales order.
        SalesOrderStatusHist so = new SalesOrderStatusHist();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(so)))
                    .thenReturn(this.mockStatusHistoryAllResponse.get(1));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesOrderStatusHistDto results = null;
        try {
            results = api.getCurrentStatus(TEST_SALES_ORDER_ID);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(71, results.getSoStatusHistId());
        Assert.assertEquals(1000, results.getSoId());
        Assert.assertEquals(SalesApiConst.STATUS_CODE_QUOTE, results.getSoStatusId());
        Assert.assertEquals(RMT2Date.stringToDate("2017-02-10"), results.getEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2017-02-28"), results.getEndDate());
    }

    @Test
    public void testFetch_CurrentStatus_NotFound() {
        // Mock method call to get current status of sales order.
        SalesOrderStatusHist so = new SalesOrderStatusHist();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(so))).thenReturn(this.mockStatusHistoryNotFoundResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getCurrentStatus(TEST_SALES_ORDER_ID);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof MissingCurrentStatusException);
        }
    }
    
    @Test
    public void testFetch_CurrentStatus_Null_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getCurrentStatus(null);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_CurrentStatus_Zero_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getCurrentStatus(0);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_CurrentStatus_Negative_SalesOrderId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getCurrentStatus(-1);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_CurrentStatus_Db_Exception() {
        // Mock method call to get current status of sales order.
        SalesOrderStatusHist so = new SalesOrderStatusHist();
        so.setSoId(TEST_SALES_ORDER_ID);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(so))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order current status fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getCurrentStatus(TEST_SALES_ORDER_ID);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testFetch_SalesOrderStatus() {
        // Mock method call to get current status of sales order.
        SalesOrderStatus so = new SalesOrderStatus();
        so.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenReturn(this.mockStatusSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order status fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        SalesOrderStatusDto results = null;
        try {
            results = api.getStatus(SalesApiConst.STATUS_CODE_QUOTE);
        } catch (SalesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(SalesApiConst.STATUS_CODE_QUOTE, results.getSoStatusId());
        Assert.assertEquals("Quote", results.getSoStatusDescription());
    }
    
    @Test
    public void testFetch_SalesOrderStatus_Null_StatusId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getStatus(null);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_SalesOrderStatus_Zero_StatusId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getStatus(0);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_SalesOrderStatus_Negative_StatusId() {
        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getStatus(-1);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testFetch_SalesOrderStatus_Too_Many_Rows_Return() {
        // Mock method call to get current status of sales order.
        SalesOrderStatus so = new SalesOrderStatus();
        so.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenReturn(this.mockStatusAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order status fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getStatus(SalesApiConst.STATUS_CODE_QUOTE);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
        }
    }
    
    @Test
    public void testFetch_SalesOrderStatus_Db_Exception() {
        // Mock method call to get current status of sales order.
        SalesOrderStatus so = new SalesOrderStatus();
        so.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(so)))
                    .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Sales order status fetch test case setup failed");
        }

        // Perform test
        SalesApiFactory f = new SalesApiFactory();
        SalesApi api = f.createApi(mockDaoClient);
        try {
            api.getStatus(SalesApiConst.STATUS_CODE_QUOTE);
            Assert.fail("Test failed due to exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof SalesApiException);
            Assert.assertTrue(e.getCause() instanceof SalesOrderDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
}