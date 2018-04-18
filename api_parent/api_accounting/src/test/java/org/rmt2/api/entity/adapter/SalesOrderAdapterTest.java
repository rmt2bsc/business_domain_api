package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.dao.mapping.orm.rmt2.VwSalesOrderInvoice;
import org.dao.mapping.orm.rmt2.VwSalesorderItemsBySalesorder;
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
import org.modules.inventory.InventoryConst;
import org.modules.transaction.sales.SalesApiConst;
import org.rmt2.api.AccountingMockDataFactory;

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
        SalesOrder o = AccountingMockDataFactory.createMockOrmSalesOrder(1000, 2000, 0, 300.00, "2017-01-01");
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
        
        
        try {
            dto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(null);
            dto.setSalesOrderId(1000);
            dto.setCustomerId(2000);
            dto.setOrderTotal(300.00);
            dto.setInvoiced(false);
            dto.setSaleOrderDate(RMT2Date.stringToDate("2017-01-01"));
            
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
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for SalesOrderDto Adapater");
        }
    }
 
    @Test
    public void testSalesLineItemAdapter() {
        SalesOrderItems o = AccountingMockDataFactory.createMockOrmSalesOrderItem(88880, 33330, 1000, 1, 20.00);
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
        
        
        try {
            SalesOrderItems nullObj = null;
            dto = Rmt2SalesOrderDtoFactory.createSalesOrderItemInstance(nullObj);
            dto.setSoItemId(88880);
            dto.setSalesOrderId(1000);
            dto.setItemId(33330);
            dto.setOrderQty(1);
            dto.setInitUnitCost(20.00);
            dto.setInitMarkup(3);
            dto.setItemNameOverride("ItemNameOverride" + dto.getItemId());
            dto.setBackOrderQty(100);
            
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
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for SalesOrderItemDto Adapater");
        }
    }
    
    @Test
    public void testSalesLineItemExtAdapter() {
        Customer cust = AccountingMockDataFactory.createMockOrmCustomer(2000, 1351, 0, 333, "C1234580", "Customer 1");
        SalesOrder so = AccountingMockDataFactory.createMockOrmSalesOrder(1000, 2000, 0, 300.00, "2017-01-01");
        ItemMasterType imt = AccountingMockDataFactory.createMockOrmItemMasterType(InventoryConst.ITEM_TYPE_MERCH, 
                "ItemTypeMerchandise");
        SalesOrderItems soi = AccountingMockDataFactory.createMockOrmSalesOrderItem(88880, 33330, 1000, 1, 20.00);
        ItemMaster im = AccountingMockDataFactory.createMockOrmItemMaster(123450, InventoryConst.ITEM_TYPE_MERCH, 
                "1111-111-110", "11111110", 1351, "Item1", 10, 20.00, true);

        VwSalesorderItemsBySalesorder o = AccountingMockDataFactory
                .createMockOrmVwSalesorderItemsBySalesorder(soi, so, cust, im, imt);

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
        Assert.assertEquals(5, dto.getImMarkup(), 0);
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
        
        // Test setters
        try {
            VwSalesorderItemsBySalesorder nullObj = null;
            dto = Rmt2SalesOrderDtoFactory.createSalesOrderItemInstance(nullObj);
            // sales order item info
            dto.setSoItemId(88880);
            dto.setSalesOrderId(1000);
            dto.setItemId(33330);
            dto.setOrderQty(1);
            dto.setInitUnitCost(20.00);
            dto.setInitMarkup(3);
            dto.setItemNameOverride("ItemNameOverride" + dto.getItemId());
            dto.setBackOrderQty(100);
            
            // Customer info
            dto.setCustomerId(2000);
            dto.setBusinessId(1351);
            dto.setPersonId(0);
            
            // Sales order info
            dto.setSalesOrderId(1000);
            
            // Item master type info
            dto.setImItemTypeId(InventoryConst.ITEM_TYPE_MERCH);
            dto.setImItemTypeDescription("ItemTypeMerchandise");
            
            // Item master info
            dto.setImVendorId(1351);
            dto.setImName("Item1");
            dto.setImVendorItemNo("11111110");
            dto.setImSerialNo("1111-111-110");
            dto.setImQtyOnHand(10);
            dto.setImUnitCost(20.00);
            dto.setImMarkup(5);
            double retailPrice = ((dto.getImQtyOnHand() * dto.getImUnitCost()) * dto.getImMarkup());
            dto.setImRetailPrice(retailPrice);
            
            // Assert values
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
            Assert.assertEquals(5, dto.getImMarkup(), 0);
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
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for SalesOrderItemDto extended Adapater");
        }
    }
    
    @Test
    public void testVwSalesOrderInvoiceAdapter() {
        VwSalesOrderInvoice o =  AccountingMockDataFactory.createMockOrmVwSalesOrderInvoice(7000, 1000, "2017-01-01",
                        300.00, SalesApiConst.STATUS_CODE_INVOICED, "80000", 1, "2017-01-10", 444440, 2000, 1234, 
                        "111-111");
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
        
        // Test setters
        try {
            VwSalesOrderInvoice nullObj =  null;
            dto = Rmt2SalesOrderDtoFactory.createSalesIvoiceInstance(nullObj);
            dto.setInvoiceId(7000);
            dto.setSalesOrderId(1000);
            dto.setSaleOrderDate(RMT2Date.stringToDate("2017-01-01"));
            dto.setOrderTotal(300.00);
            dto.setSoStatusId(SalesApiConst.STATUS_CODE_INVOICED);
            dto.setInvoiceNo("80000");
            dto.setInvoiced(true);
            dto.setInvoiceDate(RMT2Date.stringToDate("2017-01-10"));
            dto.setXactId(444440);
            dto.setCustomerId(2000);
            dto.setAcctId(1234);
            dto.setAccountNo("111-111");
            dto.setSoStatusDescription("SalesOrderStatus" + dto.getSoStatusId());
            
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
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for SalesInvoiceDto extended Adapater");
        }
    }
    
    @Test
    public void testSalesInvoiceAdapter() {
        SalesInvoice o = AccountingMockDataFactory.createMockOrmSalesInvoice(7000, 1000, 5000, "80000");
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
        
        // Test Setters
        try {
            SalesInvoice nullObj =  null;
            dto = Rmt2SalesOrderDtoFactory.createSalesIvoiceInstance(nullObj);
            dto.setInvoiceId(7000);
            dto.setSalesOrderId(1000);
            dto.setXactId(5000);
            dto.setInvoiceNo("80000");
            
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
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for SalesInvoiceDto Adapater");
        }
    }

    @Test
    public void testSalesOrderStatusHistoryAdapter() {
        SalesOrderStatusHist o = AccountingMockDataFactory.createMockOrmSalesOrderStatusHist(70, 1000,
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
        
        // TEst setters
        try {
            dto = Rmt2SalesOrderDtoFactory.createSalesOrderStatusHistoryInstance(null);
            dto.setSoStatusHistId(70);
            dto.setSoId(1000);
            dto.setSoStatusId(SalesApiConst.STATUS_CODE_NEW);
            dto.setEffectiveDate(RMT2Date.stringToDate("2017-01-10"));
            dto.setEndDate(RMT2Date.stringToDate("2017-01-31"));
            dto.setReason("SalesOrderStatusHistoryReason" + dto.getSoStatusId());
            dto.setDateCreated(dto.getEffectiveDate());
            dto.setUpdateUserId("testuser");
            dto.setIpCreated("111.222.101.100");
            dto.setIpUpdated("111.222.101.100");
            
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
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for SalesOrderStatusHistDto Adapater");
        }
    }
    
    @Test
    public void testSalesOrderStatusAdapter() {
        SalesOrderStatus o = AccountingMockDataFactory.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_QUOTE, "Quote");
        SalesOrderStatusDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderStatusInstance(o);
        
        Assert.assertEquals(SalesApiConst.STATUS_CODE_QUOTE, dto.getSoStatusId());
        Assert.assertEquals("Quote", dto.getSoStatusDescription());
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
        
        // Test setters
        try {
            dto = Rmt2SalesOrderDtoFactory.createSalesOrderStatusInstance(null);
            dto.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
            dto.setSoStatusDescription("Quote");
            
            Assert.assertEquals(SalesApiConst.STATUS_CODE_QUOTE, dto.getSoStatusId());
            Assert.assertEquals("Quote", dto.getSoStatusDescription());
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
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for SalesOrderStatusHistDto Adapater");
        }
    }
}
