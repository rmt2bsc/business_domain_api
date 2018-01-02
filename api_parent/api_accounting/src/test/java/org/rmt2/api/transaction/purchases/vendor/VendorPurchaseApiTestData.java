package org.rmt2.api.transaction.purchases.vendor;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.PurchaseOrderItems;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatus;
import org.dao.mapping.orm.rmt2.VendorItems;
import org.dao.mapping.orm.rmt2.VwVendorItemPurchaseOrderItem;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dao.transaction.purchases.vendor.VendorPurchasesConst;
import org.junit.Before;
import org.rmt2.api.AccountingMockDataUtility;
import org.rmt2.api.transaction.TransactionApiTestData;

/**
 * Mock test data for purchase order testing
 * 
 * @author roy.terrell
 *
 */
public class VendorPurchaseApiTestData extends TransactionApiTestData {
    protected List<PurchaseOrder> mockPurchaseOrders;
    protected List<PurchaseOrderItems> mockPurchaseOrderItems;
    protected List<VendorItems> mockVendorItems;
    protected List<VwVendorItemPurchaseOrderItem> mockVwVendorItemPurchaseOrderItems;
    protected List<VwVendorItems> mockVwVendorItems;
    protected List<PurchaseOrderStatus> mockPurchaseOrderStatuses;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockPurchaseOrders = this.createMockPurchaseOrders();
        this.mockPurchaseOrderItems = this.createMockPurchaseOrderItems();
        this.mockVendorItems = this.createMockVendorItems();
        this.mockVwVendorItemPurchaseOrderItems = this.createMockVwVendorItemPurchaseOrderItems();
        this.mockVwVendorItems = this.createMockVwVendorItems();
        this.mockPurchaseOrderStatuses = this.createMockPurchaseOrderStatuses();
        return;
    }

    private List<PurchaseOrder> createMockPurchaseOrders() {
        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        PurchaseOrder o = AccountingMockDataUtility.createPurchaseOrder(330, 7000, 1111111, 100.00, "330-0000-0000-0000");
        list.add(o);

        o =  AccountingMockDataUtility.createPurchaseOrder(331, 7000, 1111111, 200.00, "331-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrder(332, 7000, 1111111, 300.00, "332-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrder(333, 7000, 1111111, 400.00, "333-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrder(334, 7000, 1111111, 500.00, "334-0000-0000-0000");
        list.add(o);
        return list;
    }

    private List<PurchaseOrderItems> createMockPurchaseOrderItems() {
        List<PurchaseOrderItems> list = new ArrayList<PurchaseOrderItems>();
        PurchaseOrderItems o = AccountingMockDataUtility.createPurchaseOrderItem(330, 7000, 100, 100.00, 11, 4, 0);
        list.add(o);
        
        o =  AccountingMockDataUtility.createPurchaseOrderItem(331, 7000, 101, 200.00, 10, 3, 0);
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderItem(332, 7000, 102, 300.00, 9, 2, 0);
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderItem(333, 7000, 103, 400.00, 8, 1, 0);
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderItem(334, 7000, 104, 500.00, 7, 0, 5);
        list.add(o);
        return list;
    }
    
    private List<VendorItems> createMockVendorItems() {
        List<VendorItems> list = new ArrayList<VendorItems>();
        VendorItems o = AccountingMockDataUtility.createVendorItems(330, 1111111, "111-111", "111-ABC", 90.00);
        list.add(o);
        
        o =  AccountingMockDataUtility.createVendorItems(330, 1111111, "111-222", "222-ABC", 80.00);
        list.add(o);

        o = AccountingMockDataUtility.createVendorItems(330, 1111111, "111-333", "333-ABC", 70.00);
        list.add(o);

        o = AccountingMockDataUtility.createVendorItems(330, 1111111, "111-444", "444-ABC", 60.00);
        list.add(o);

        o = AccountingMockDataUtility.createVendorItems(330, 1111111, "111-555", "555-ABC", 50.00);
        list.add(o);
        return list;
    }
    
    private List<VwVendorItemPurchaseOrderItem> createMockVwVendorItemPurchaseOrderItems() {
        List<VwVendorItemPurchaseOrderItem> list = new ArrayList<VwVendorItemPurchaseOrderItem>();
        VwVendorItemPurchaseOrderItem o = AccountingMockDataUtility
                .createVwVendorItemPurchaseOrderItem(7000, 330, 1111111, 90.00,
                        10, 100, 0, 0, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItemPurchaseOrderItem(7000,
                331, 1111111, 80.00, 10, 80, 0, 0, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItemPurchaseOrderItem(7000,
                332, 1111111, 70.00, 10, 60, 0, 0, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItemPurchaseOrderItem(7000,
                333, 1111111, 60.00, 10, 40, 0, 0, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItemPurchaseOrderItem(7000,
                334, 1111111, 50.00, 10, 20, 0, 0, 0);
        list.add(o);
        return list;
    }
    
    private List<VwVendorItems> createMockVwVendorItems() {
        List<VwVendorItems> list = new ArrayList<VwVendorItems>();
        VwVendorItems o = AccountingMockDataUtility.createVwVendorItems(330,
                1111111, 90.00, 100, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItems(331, 1111111, 80.00,
                80, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItems(332, 1111111, 70.00,
                60, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItems(333, 1111111, 60.00,
                40, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItems(334, 1111111, 50.00,
                20, 0);
        list.add(o);
        return list;
    }
    
    private List<PurchaseOrderStatus> createMockPurchaseOrderStatuses() {
        List<PurchaseOrderStatus> list = new ArrayList<PurchaseOrderStatus>();
        PurchaseOrderStatus o = AccountingMockDataUtility
                .createPurchaseOrderStatus(
                        VendorPurchasesConst.PURCH_STATUS_QUOTE, "Quote");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderStatus(
                VendorPurchasesConst.PURCH_STATUS_FINALIZE, "Finalize");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderStatus(
                VendorPurchasesConst.PURCH_STATUS_PARTRET, "Partial Return");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderStatus(
                VendorPurchasesConst.PURCH_STATUS_CANCEL, "Cancel");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderStatus(
                VendorPurchasesConst.PURCH_STATUS_RECEIVED, "Received");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderStatus(
                VendorPurchasesConst.PURCH_STATUS_RETURN, "Return");
        list.add(o);

        return list;
    }
}
