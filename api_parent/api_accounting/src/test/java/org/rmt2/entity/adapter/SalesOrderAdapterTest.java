package org.rmt2.entity.adapter;

import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.dao.mapping.orm.rmt2.VwSalesOrderInvoice;
import org.dao.mapping.orm.rmt2.VwSalesorderItemsBySalesorder;
import org.dto.SalesInvoiceDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.SalesOrderStatusHistDto;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modules.inventory.InventoryConst;
import org.modules.transaction.sales.SalesApiConst;
import org.rmt2.api.AccountingMockDataUtility;

import com.util.RMT2Date;

/**
 * Test Sales Order adapters
 * 
 * @author roy.terrell
 *
 */
public class SalesOrderAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSalesOrderAdapter() {
        SalesOrder o = AccountingMockDataUtility.createMockOrmSalesOrder(1000,
                2000, 0, 300.00, "2017-01-01");
        SalesOrderDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(o);
        
        Assert.assertEquals(1000, dto.getSalesOrderId());
        Assert.assertEquals(2000, dto.getCustomerId());
        Assert.assertEquals(300.00, dto.getOrderTotal(), 0);
        Assert.assertFalse(dto.isInvoiced());
        Assert.assertEquals(RMT2Date.stringToDate("2017-01-01"), dto.getSaleOrderDate());
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
 
    @Test
    public void testSalesLineItemAdapter() {
        SalesOrderItems o = AccountingMockDataUtility
                .createMockOrmSalesOrderItem(88880, 33330, 1000, 1, 20.00);
        SalesOrderItemDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderItemInstance(o);

        Assert.assertEquals(88880, dto.getSoItemId());
        Assert.assertEquals(1000, dto.getSalesOrderId());
        Assert.assertEquals(33330, dto.getItemId());
        Assert.assertEquals(1, dto.getOrderQty(), 0);
        Assert.assertEquals(20.00, dto.getInitUnitCost(), 0);
        Assert.assertEquals(3, dto.getInitMarkup(), 0);
        Assert.assertEquals("ItemNameOverride" + dto.getItemId(), dto.getItemNameOverride());
        Assert.assertEquals(100, dto.getBackOrderQty(), 0);
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
    
    @Test
    public void testSalesLineItemExtAdapter() {
        Customer cust = AccountingMockDataUtility.createMockOrmCustomer(2000,
                1351, 0, 333, "C1234580", "Customer 1");
        SalesOrder so = AccountingMockDataUtility.createMockOrmSalesOrder(1000,
                2000, 0, 300.00, "2017-01-01");
        ItemMasterType imt = AccountingMockDataUtility
                .createMockOrmItemMasterType(InventoryConst.ITEM_TYPE_MERCH,
                        "ItemTypeMerchandise");
        SalesOrderItems soi = AccountingMockDataUtility
                .createMockOrmSalesOrderItem(88880, 33330, 1000, 1, 20.00);
        ItemMaster im = AccountingMockDataUtility.createMockOrmItemMaster(
                123450, InventoryConst.ITEM_TYPE_MERCH, "1111-111-110",
                "11111110", 1351, "Item1", 10, 20.00, true);

        VwSalesorderItemsBySalesorder o = AccountingMockDataUtility
                .createMockOrmVwSalesorderItemsBySalesorder(soi, so, cust, im,
                        imt);

        // Properties that are expected to have values
        SalesOrderItemDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderItemInstance(o);    
        
        // Check data from sales order item
        Assert.assertEquals(88880, dto.getSoItemId());
        Assert.assertEquals(1000, dto.getSalesOrderId());
        Assert.assertEquals(33330, dto.getItemId());
        Assert.assertEquals(1, dto.getOrderQty(), 0);
        Assert.assertEquals(20.00, dto.getInitUnitCost(), 0);
        Assert.assertEquals(3, dto.getInitMarkup(), 0);
        Assert.assertEquals("ItemNameOverride" + dto.getItemId(), dto.getItemNameOverride());
        Assert.assertEquals(100, dto.getBackOrderQty(), 0);
        
        // Check data from customer
        Assert.assertEquals(2000, dto.getCustomerId());
        Assert.assertEquals(1351, dto.getBusinessId());
        Assert.assertEquals(0, dto.getPersonId());
        
        // Check data from sales order
        Assert.assertEquals(1000, dto.getSalesOrderId());
        
        // Check data from item master type
        Assert.assertEquals(InventoryConst.ITEM_TYPE_MERCH, dto.getImItemTypeId());
        Assert.assertEquals("ItemTypeMerchandise", dto.getImItemTypeDescription());
        
        // Check data from item master
        Assert.assertEquals(1351, dto.getImVendorId());
        Assert.assertEquals("Item1", dto.getImName());
        Assert.assertEquals("11111110", dto.getImVendorItemNo());
        Assert.assertEquals("1111-111-110", dto.getImSerialNo());
        Assert.assertEquals(10, dto.getImQtyOnHand());
        Assert.assertEquals(20.00, dto.getImUnitCost(), 0);
        Assert.assertEquals(3, dto.getImMarkup(), 0);
        Assert.assertEquals(((dto.getImQtyOnHand() * dto.getImUnitCost()) * dto.getImMarkup()), dto.getImRetailPrice(), 0);
        
        // Properties that are not expected to have values.
        Assert.assertEquals(0, dto.getOrderTotal(), 0);
        Assert.assertNull(dto.getSaleOrderDate());
        
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
    
    @Test
    public void testVwSalesOrderInvoiceAdapter() {
        VwSalesOrderInvoice o =  AccountingMockDataUtility
                .createMockOrmVwSalesOrderInvoice(7000, 1000, "2017-01-01",
                        300.00, SalesApiConst.STATUS_CODE_INVOICED, "80000", 1,
                        "2017-01-10", 444440, 2000, 1234, "111-111");
        SalesInvoiceDto dto = Rmt2SalesOrderDtoFactory.createSalesIvoiceInstance(o);

        Assert.assertEquals(7000, dto.getInvoiceId());
        Assert.assertEquals(1000, dto.getSalesOrderId());
        Assert.assertEquals(RMT2Date.stringToDate("2017-01-01"), dto.getSaleOrderDate());
        Assert.assertEquals(300.00, dto.getOrderTotal(), 0);
        Assert.assertEquals(SalesApiConst.STATUS_CODE_INVOICED, dto.getSoStatusId());
        Assert.assertEquals("80000", dto.getInvoiceNo());
        Assert.assertTrue(dto.isInvoiced());
        Assert.assertEquals(RMT2Date.stringToDate("2017-01-10"), dto.getInvoiceDate());
        Assert.assertEquals(444440, dto.getXactId());
        Assert.assertEquals(2000, dto.getCustomerId());
        Assert.assertEquals(1234, dto.getAcctId());
        Assert.assertEquals("111-111", dto.getAccountNo());
        Assert.assertEquals("SalesOrderStatus" + dto.getSoStatusId(), dto.getSoStatusDescription());
        

        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
    
    @Test
    public void testSalesInvoiceAdapter() {
        SalesInvoice o = AccountingMockDataUtility
                .createMockOrmSalesInvoice(7000, 1000, 5000, "80000");
        SalesInvoiceDto dto = Rmt2SalesOrderDtoFactory.createSalesIvoiceInstance(o);
        
        Assert.assertEquals(7000, dto.getInvoiceId());
        Assert.assertEquals(1000, dto.getSalesOrderId());
        Assert.assertEquals(5000, dto.getXactId());
        Assert.assertEquals("80000", dto.getInvoiceNo());
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }

    @Test
    public void testSalesOrderStatusHistoryAdapter() {
        SalesOrderStatusHist o = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(70, 1000,
                SalesApiConst.STATUS_CODE_NEW, "2017-01-10", "2017-01-31");
        SalesOrderStatusHistDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderStatusHistoryInstance(o);

        Assert.assertEquals(70, dto.getSoStatusHistId());
        Assert.assertEquals(1000, dto.getSoId());
        Assert.assertEquals(SalesApiConst.STATUS_CODE_NEW, dto.getSoStatusId());
        Assert.assertEquals(RMT2Date.stringToDate("2017-01-10"), dto.getEffectiveDate());
        Assert.assertEquals(RMT2Date.stringToDate("2017-01-31"), dto.getEndDate());
        Assert.assertEquals("SalesOrderStatusHistoryReason" + dto.getSoStatusId(), dto.getReason());
        Assert.assertEquals(dto.getEffectiveDate(), dto.getDateCreated());
        Assert.assertEquals("testuser", dto.getUpdateUserId());
        Assert.assertEquals("111.222.101.100", dto.getIpCreated());
        Assert.assertEquals("111.222.101.100", dto.getIpUpdated());
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
}
