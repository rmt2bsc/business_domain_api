package org.rmt2.api.transaction.sales;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.AccountingConst;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dto.ItemMasterDto;
import org.dto.SalesInvoiceDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.SalesOrderStatusDto;
import org.dto.SalesOrderStatusHistDto;
import org.dto.XactDto;
import org.dto.adapter.orm.inventory.Rmt2InventoryDtoFactory;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.inventory.InventoryApi;
import org.modules.inventory.InventoryApiFactory;
import org.modules.transaction.XactApiException;
import org.modules.transaction.sales.SalesApiException;

/**
 * @author appdev
 * 
 */
public class SalesOrderApiTest extends CommonSalesOrderTest {

    /*
     * (non-Javadoc)
     * 
     * @see transaction.sales.CommonSalesOrderTest#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see transaction.sales.CommonSalesOrderTest#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testQueryExistingSalesOrderById() {
        SalesOrderDto results = null;
        try {
            results = this.api.getSalesOrderById(25);
        } catch (SalesApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.getCustomerId());
        Assert.assertEquals(8800.00, results.getOrderTotal());
    }

    @Test
    public void testQueryExistingCurrentSalesOrderStatus() {
        SalesOrderStatusHistDto results = null;
        try {
            results = this.api.getCurrentSalesOrderStatus(25);
        } catch (SalesApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.getSoStatusId());
    }

    @Test
    public void testQueryExistingSalesOrderStatus() {
        SalesOrderStatusDto results = null;
        try {
            results = this.api.getSalesOrderStatusById(2);
        } catch (SalesApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("Invoiced", results.getSoStatusDescription());
    }

    @Test
    public void testCreateSalesOrder() {
        // Create Inventory items
        int item1Id;
        int item2Id;
        InventoryApiFactory invFact = new InventoryApiFactory();
        InventoryApi invApi = invFact.createApi();
        invApi.setApiUser(this.api.getApiUser());
        ItemMaster nullItem = null;
        ItemMasterDto item1 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setVendorId(8);
        item1.setItemName("Bic Pen");
        item1.setVendorItemNo("83823-3838-33");
        item1.setItemSerialNo("JFDK8383DJD");
        item1.setQtyOnHand(44);
        item1.setUnitCost(3.55);
        item1.setMarkup(2.5);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(0);
        item1.setActive(AccountingConst.ACCT_ACTIVE);

        this.api.beginTrans();
        try {
            item1Id = invApi.updateItemMaster(item1);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(item1Id > 0);

        ItemMasterDto item2 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item2.setItemTypeId(2);
        item2.setVendorId(8);
        item2.setItemName("Samsung Galaxy Note 2");
        item2.setVendorItemNo("743-KDK-382");
        item2.setItemSerialNo("ABC8484848");
        item2.setQtyOnHand(44);
        item2.setUnitCost(250.0);
        item2.setMarkup(2.5);
        item2.setRetailPrice(0);
        item2.setOverrideRetail(0);
        item2.setActive(AccountingConst.ACCT_ACTIVE);
        try {
            item2Id = invApi.updateItemMaster(item2);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(item2Id > 0);

        // Create Sales order
        SalesOrderDto so = Rmt2SalesOrderDtoFactory
                .createSalesOrderInstance(null);
        so.setOrderTotal(1963.75);

        // Create sales order items
        List<SalesOrderItemDto> items = new ArrayList<SalesOrderItemDto>();
        SalesOrderItemDto i = Rmt2SalesOrderDtoFactory
                .createSalesOrderItemInstance((SalesOrderItems) null);
        i.setItemId(item1Id);
        // i.setOrderTotal(88.75);
        i.setOrderQty(10);
        items.add(i);

        i = Rmt2SalesOrderDtoFactory
                .createSalesOrderItemInstance((SalesOrderItems) null);
        i.setItemId(item2Id);
        // i.setOrderTotal(88.75);
        i.setOrderQty(3);
        items.add(i);

        // Setup customer
        int customerId = 1;

        int rc = 0;
        try {
            rc = this.api.updateSalesOrder(so, customerId, items);
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            e.printStackTrace();
        }
        Assert.assertTrue(rc > 1);

        // Delete Sales Order
        try {
            rc = this.api.deleteSalesOrder(so.getSalesOrderId());
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
        this.api.commitTrans();
    }

    @Test
    public void testInvoiceSalesOrder() {
        // Create Inventory items
        int item1Id;
        int item2Id;
        InventoryApiFactory invFact = new InventoryApiFactory();
        InventoryApi invApi = invFact.createApi();
        invApi.setApiUser(this.api.getApiUser());
        ItemMaster nullItem = null;
        ItemMasterDto item1 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setVendorId(8);
        item1.setItemName("Bic Pen");
        item1.setVendorItemNo("83823-3838-33");
        item1.setItemSerialNo("JFDK8383DJD");
        item1.setQtyOnHand(44);
        item1.setUnitCost(3.55);
        item1.setMarkup(2.5);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(0);
        item1.setActive(AccountingConst.ACCT_ACTIVE);

        invApi.beginTrans();
        try {
            item1Id = invApi.updateItemMaster(item1);
        } catch (Exception e) {
            invApi.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(item1Id > 0);

        ItemMasterDto item2 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item2.setItemTypeId(2);
        item2.setVendorId(8);
        item2.setItemName("Samsung Galaxy Note 2");
        item2.setVendorItemNo("743-KDK-382");
        item2.setItemSerialNo("ABC8484848");
        item2.setQtyOnHand(44);
        item2.setUnitCost(250.0);
        item2.setMarkup(2.5);
        item2.setRetailPrice(0);
        item2.setOverrideRetail(0);
        item2.setActive(AccountingConst.ACCT_ACTIVE);
        try {
            item2Id = invApi.updateItemMaster(item2);
        } catch (Exception e) {
            invApi.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(item2Id > 0);
        invApi.commitTrans();

        // Create Sales order
        SalesOrderDto so = Rmt2SalesOrderDtoFactory
                .createSalesOrderInstance(null);
        so.setOrderTotal(1963.75);

        // Create sales order items
        List<SalesOrderItemDto> items = new ArrayList<SalesOrderItemDto>();
        SalesOrderItemDto i = Rmt2SalesOrderDtoFactory
                .createSalesOrderItemInstance((SalesOrderItems) null);
        i.setItemId(item1Id);
        // i.setOrderTotal(88.75);
        i.setOrderQty(10);
        items.add(i);

        i = Rmt2SalesOrderDtoFactory
                .createSalesOrderItemInstance((SalesOrderItems) null);
        i.setItemId(item2Id);
        // i.setOrderTotal(88.75);
        i.setOrderQty(3);
        items.add(i);

        // Setup customer
        int customerId = 1;

        int rc = 0;
        this.api.beginTrans();
        try {
            rc = this.api.updateSalesOrder(so, customerId, items);
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 1);

        // Invoice Sales order
        try {
            rc = this.api.invoiceSalesOrder(so, items, false);
        } catch (SalesApiException e) {
            try {
                rc = this.api.deleteSalesOrder(so.getSalesOrderId());
            } catch (SalesApiException e1) {
                e1.printStackTrace();
            }
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 1);

        // Delete Sales Order
        try {
            rc = this.api.deleteSalesOrder(so.getSalesOrderId());
            Assert.fail("Test should not have deleted sales order since it is in invoice status");
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            e.printStackTrace();
        }
        this.api.commitTrans();
    }

    @Test
    public void testRefundSalesOrder() {
        // Create Inventory items
        int item1Id;
        int item2Id;
        InventoryApiFactory invFact = new InventoryApiFactory();
        InventoryApi invApi = invFact.createApi();
        invApi.setApiUser(this.api.getApiUser());
        ItemMaster nullItem = null;
        ItemMasterDto item1 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setVendorId(8);
        item1.setItemName("Bic Pen");
        item1.setVendorItemNo("83823-3838-33");
        item1.setItemSerialNo("JFDK8383DJD");
        item1.setQtyOnHand(44);
        item1.setUnitCost(3.55);
        item1.setMarkup(2.5);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(0);
        item1.setActive(AccountingConst.ACCT_ACTIVE);

        invApi.beginTrans();
        try {
            item1Id = invApi.updateItemMaster(item1);
        } catch (Exception e) {
            invApi.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(item1Id > 0);

        ItemMasterDto item2 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item2.setItemTypeId(2);
        item2.setVendorId(8);
        item2.setItemName("Samsung Galaxy Note 2");
        item2.setVendorItemNo("743-KDK-382");
        item2.setItemSerialNo("ABC8484848");
        item2.setQtyOnHand(44);
        item2.setUnitCost(250.0);
        item2.setMarkup(2.5);
        item2.setRetailPrice(0);
        item2.setOverrideRetail(0);
        item2.setActive(AccountingConst.ACCT_ACTIVE);
        try {
            item2Id = invApi.updateItemMaster(item2);
        } catch (Exception e) {
            invApi.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(item2Id > 0);
        invApi.commitTrans();

        // Create Sales order
        SalesOrderDto so = Rmt2SalesOrderDtoFactory
                .createSalesOrderInstance(null);
        so.setOrderTotal(1963.75);

        // Create sales order items
        List<SalesOrderItemDto> items = new ArrayList<SalesOrderItemDto>();
        SalesOrderItemDto i = Rmt2SalesOrderDtoFactory
                .createSalesOrderItemInstance((SalesOrderItems) null);
        i.setItemId(item1Id);
        // i.setOrderTotal(88.75);
        i.setOrderQty(10);
        items.add(i);

        i = Rmt2SalesOrderDtoFactory
                .createSalesOrderItemInstance((SalesOrderItems) null);
        i.setItemId(item2Id);
        // i.setOrderTotal(88.75);
        i.setOrderQty(3);
        items.add(i);

        // Setup customer
        int customerId = 1;

        int rc = 0;
        this.api.beginTrans();
        try {
            rc = this.api.updateSalesOrder(so, customerId, items);
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 1);

        // Invoice Sales order with full cash payment
        try {
            rc = this.api.invoiceSalesOrder(so, items, true);
        } catch (SalesApiException e) {
            try {
                rc = this.api.deleteSalesOrder(so.getSalesOrderId());
            } catch (SalesApiException e1) {
                e1.printStackTrace();
            }
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 1);

        SalesInvoiceDto si = null;
        try {
            si = this.api
                    .getSalesOrderInvoiceBySalesOrder(so.getSalesOrderId());
        } catch (SalesApiException e1) {
            e1.printStackTrace();
        }

        // this.api.beginTrans();
        // Cancel Salse order
        try {
            rc = this.api.refundSalesOrder(so.getSalesOrderId());
            Assert.assertTrue(rc > 0);
            this.api.commitTrans();
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            e.printStackTrace();
        }

        XactDto xact = null;
        try {
            xact = this.api.getXactById(rc);
        } catch (XactApiException e1) {
            e1.printStackTrace();
        }

        // Verify that refund was actually applied.
        Assert.assertEquals(0.0, si.getOrderTotal() + xact.getXactAmount());

        // Delete Sales Order
        try {
            rc = this.api.deleteSalesOrder(so.getSalesOrderId());
            Assert.fail("Test should not have deleted sales order since it is in closed status");
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            e.printStackTrace();
        }
        this.api.commitTrans();
    }

    @Test
    public void testCancelSalesOrder() {
        // Create Inventory items
        int item1Id;
        int item2Id;
        InventoryApiFactory invFact = new InventoryApiFactory();
        InventoryApi invApi = invFact.createApi();
        invApi.setApiUser(this.api.getApiUser());
        ItemMaster nullItem = null;
        ItemMasterDto item1 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setVendorId(8);
        item1.setItemName("Bic Pen");
        item1.setVendorItemNo("83823-3838-33");
        item1.setItemSerialNo("JFDK8383DJD");
        item1.setQtyOnHand(44);
        item1.setUnitCost(3.55);
        item1.setMarkup(2.5);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(0);
        item1.setActive(AccountingConst.ACCT_ACTIVE);

        invApi.beginTrans();
        try {
            item1Id = invApi.updateItemMaster(item1);
        } catch (Exception e) {
            invApi.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(item1Id > 0);

        ItemMasterDto item2 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item2.setItemTypeId(2);
        item2.setVendorId(8);
        item2.setItemName("Samsung Galaxy Note 2");
        item2.setVendorItemNo("743-KDK-382");
        item2.setItemSerialNo("ABC8484848");
        item2.setQtyOnHand(44);
        item2.setUnitCost(250.0);
        item2.setMarkup(2.5);
        item2.setRetailPrice(0);
        item2.setOverrideRetail(0);
        item2.setActive(AccountingConst.ACCT_ACTIVE);
        try {
            item2Id = invApi.updateItemMaster(item2);
        } catch (Exception e) {
            invApi.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(item2Id > 0);
        invApi.commitTrans();
        invApi.close();

        // Create Sales order
        SalesOrderDto so = Rmt2SalesOrderDtoFactory
                .createSalesOrderInstance(null);
        so.setOrderTotal(1963.75);

        // Create sales order items
        List<SalesOrderItemDto> items = new ArrayList<SalesOrderItemDto>();
        SalesOrderItemDto i = Rmt2SalesOrderDtoFactory
                .createSalesOrderItemInstance((SalesOrderItems) null);
        i.setItemId(item1Id);
        // i.setOrderTotal(88.75);
        i.setOrderQty(10);
        items.add(i);

        i = Rmt2SalesOrderDtoFactory
                .createSalesOrderItemInstance((SalesOrderItems) null);
        i.setItemId(item2Id);
        // i.setOrderTotal(88.75);
        i.setOrderQty(3);
        items.add(i);

        // Setup customer
        int customerId = 1;

        int rc = 0;
        this.api.beginTrans();
        try {
            rc = this.api.updateSalesOrder(so, customerId, items);
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 1);

        // Invoice Sales order
        try {
            rc = this.api.invoiceSalesOrder(so, items, false);
            Assert.assertTrue(rc > 1);
            // this.api.commitTrans();
        } catch (SalesApiException e) {
            try {
                rc = this.api.deleteSalesOrder(so.getSalesOrderId());
            } catch (SalesApiException e1) {
                e1.printStackTrace();
            }
            Assert.fail(e.getMessage());
        }

        SalesInvoiceDto si = null;
        try {
            si = this.api
                    .getSalesOrderInvoiceBySalesOrder(so.getSalesOrderId());
        } catch (SalesApiException e1) {
            e1.printStackTrace();
        }

        // this.api.beginTrans();
        // Cancel Salse order
        try {
            rc = this.api.cancelSalesOrder(so.getSalesOrderId());
            Assert.assertTrue(rc > 0);
            this.api.commitTrans();
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            e.printStackTrace();
        }

        XactDto xact = null;
        try {
            xact = this.api.getXactById(rc);
        } catch (XactApiException e1) {
            e1.printStackTrace();
        }

        // Verify that cancellation was actually applied.
        Assert.assertEquals(0.0, si.getOrderTotal() + xact.getXactAmount());

        this.api.beginTrans();
        // Delete Sales Order
        try {
            rc = this.api.deleteSalesOrder(so.getSalesOrderId());
            Assert.fail("Test should not have deleted sales order since it is in invoice status");
        } catch (SalesApiException e) {
            this.api.rollbackTrans();
            e.printStackTrace();
        }
        // this.api.commitTrans();
    }

}
