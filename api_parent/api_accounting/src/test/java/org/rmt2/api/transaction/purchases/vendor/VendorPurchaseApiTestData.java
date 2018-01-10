package org.rmt2.api.transaction.purchases.vendor;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.PurchaseOrderItems;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatus;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatusHist;
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
    protected List<PurchaseOrder> mockPurchaseOrder;
    protected List<PurchaseOrder> mockPurchaseOrders;
    protected List<PurchaseOrderItems> mockPurchaseOrderItems;
    protected List<PurchaseOrderItems> mockPurchaseOrderItem;
    protected List<VendorItems> mockVendorItems;
    protected List<VendorItems> mockVendorItem;
    protected List<VwVendorItemPurchaseOrderItem> mockVwVendorItemPurchaseOrderItems;
    protected List<VwVendorItems> mockVwVendorItems;
    protected List<VwVendorItems> mockVwVendorItem;
    protected List<PurchaseOrderStatus> mockPurchaseOrderStatuses;
    protected List<PurchaseOrderStatus> mockPurchaseOrderStatus;
    protected List<PurchaseOrderStatusHist> mockPurchaseOrderStatusHistory;
    protected List<PurchaseOrderStatusHist> mockPurchaseOrderCurrentStatusHistory;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockPurchaseOrder = this.createMockSinglePurchaseOrder();
        this.mockPurchaseOrders = this.createMockPurchaseOrders();
        this.mockPurchaseOrderItem = this.createMockPurchaseOrderItem();
        this.mockPurchaseOrderItems = this.createMockPurchaseOrderItems();
        this.mockVendorItems = this.createMockVendorItems();
        this.mockVendorItem = this.createMockVendorItem();
        this.mockVwVendorItemPurchaseOrderItems = this.createMockVwVendorItemPurchaseOrderItems();
        this.mockVwVendorItems = this.createMockVwVendorItems();
        this.mockVwVendorItem = this.createMockSingleVwVendorItems();
        this.mockPurchaseOrderStatuses = this.createMockPurchaseOrderStatuses();
        this.mockPurchaseOrderStatus = this.createMockPurchaseOrderStatus();
        this.mockPurchaseOrderStatusHistory = this.createMockPurchaseOrderStatusHistory();
        this.mockPurchaseOrderCurrentStatusHistory = this.createMockPurchaseOrderCurrentStatusHistory();
        return;
    }

    private List<PurchaseOrder> createMockSinglePurchaseOrder() {
        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        PurchaseOrder o = AccountingMockDataUtility.createPurchaseOrder(330,
                7000, 1111111, 100.00, "330-0000-0000-0000");
        list.add(o);
        return list;
    }

    
    private List<PurchaseOrder> createMockPurchaseOrders() {
        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        PurchaseOrder o = AccountingMockDataUtility.createPurchaseOrder(330,
                7000, 1111111, 100.00, "330-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrder(331, 7000, 1111111,
                200.00, "331-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrder(332, 7000, 1111111,
                300.00, "332-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrder(333, 7000, 1111111,
                400.00, "333-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrder(334, 7000, 1111111,
                500.00, "334-0000-0000-0000");
        list.add(o);
        return list;
    }

    private List<PurchaseOrderItems> createMockPurchaseOrderItem() {
        List<PurchaseOrderItems> list = new ArrayList<PurchaseOrderItems>();
        PurchaseOrderItems o = AccountingMockDataUtility.createPurchaseOrderItem(8880, 330, 100, 100.00, 11, 4, 0);
        list.add(o);
        return list;
    }
    
    private List<PurchaseOrderItems> createMockPurchaseOrderItems() {
        List<PurchaseOrderItems> list = new ArrayList<PurchaseOrderItems>();
        PurchaseOrderItems o = AccountingMockDataUtility.createPurchaseOrderItem(8880, 330, 100, 100.00, 11, 4, 0);
        list.add(o);
        
        o =  AccountingMockDataUtility.createPurchaseOrderItem(8881, 330, 101, 200.00, 10, 3, 0);
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderItem(8882, 330, 102, 300.00, 9, 2, 0);
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderItem(8883, 330, 103, 400.00, 8, 1, 0);
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderItem(8884, 330, 104, 500.00, 7, 0, 5);
        list.add(o);
        return list;
    }
    
    private List<VendorItems> createMockVendorItem() {
        List<VendorItems> list = new ArrayList<VendorItems>();
        VendorItems o = AccountingMockDataUtility.createVendorItems(8880, 1111111, "111-111", "111-ABC", 90.00);
        list.add(o);
        return list;
    }
    
    private List<VendorItems> createMockVendorItems() {
        List<VendorItems> list = new ArrayList<VendorItems>();
        VendorItems o = AccountingMockDataUtility.createVendorItems(8880, 1111111, "111-111", "111-ABC", 90.00);
        list.add(o);
        
        o =  AccountingMockDataUtility.createVendorItems(8881, 1111111, "111-222", "222-ABC", 80.00);
        list.add(o);

        o = AccountingMockDataUtility.createVendorItems(8882, 1111111, "111-333", "333-ABC", 70.00);
        list.add(o);

        o = AccountingMockDataUtility.createVendorItems(8883, 1111111, "111-444", "444-ABC", 60.00);
        list.add(o);

        o = AccountingMockDataUtility.createVendorItems(8884, 1111111, "111-555", "555-ABC", 50.00);
        list.add(o);
        return list;
    }
    
    private List<VwVendorItemPurchaseOrderItem> createMockVwVendorItemPurchaseOrderItems() {
        List<VwVendorItemPurchaseOrderItem> list = new ArrayList<VwVendorItemPurchaseOrderItem>();
        VwVendorItemPurchaseOrderItem o = AccountingMockDataUtility
                .createVwVendorItemPurchaseOrderItem(330, 8880, 1111111, 100.00,
                        10, 100, 0, 0, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItemPurchaseOrderItem(330,
                8881, 1111111, 101.00, 11, 101, 0, 0, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItemPurchaseOrderItem(330,
                8882, 1111111, 102.00, 12, 102, 0, 0, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItemPurchaseOrderItem(330,
                8883, 1111111, 103.00, 13, 103, 0, 0, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItemPurchaseOrderItem(330,
                8884, 1111111, 104.00, 14, 104, 0, 0, 0);
        list.add(o);
        return list;
    }
    
    private List<VwVendorItems> createMockSingleVwVendorItems() {
        List<VwVendorItems> list = new ArrayList<VwVendorItems>();
        VwVendorItems o = AccountingMockDataUtility.createVwVendorItems(8880,
                1111111, 90.00, 100, 0);
        list.add(o);
        return list;
    }
    
    private List<VwVendorItems> createMockVwVendorItems() {
        List<VwVendorItems> list = new ArrayList<VwVendorItems>();
        VwVendorItems o = AccountingMockDataUtility.createVwVendorItems(8880,
                1111111, 90.00, 100, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItems(8881, 1111111, 91.00,
                101, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItems(8882, 1111111, 92.00,
                102, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItems(8883, 1111111, 93.00,
                103, 0);
        list.add(o);

        o = AccountingMockDataUtility.createVwVendorItems(8884, 1111111, 94.00,
                104, 0);
        list.add(o);
        return list;
    }
    
    private List<PurchaseOrderStatus> createMockPurchaseOrderStatus() {
        List<PurchaseOrderStatus> list = new ArrayList<PurchaseOrderStatus>();
        PurchaseOrderStatus o = AccountingMockDataUtility
                .createPurchaseOrderStatus(VendorPurchasesConst.PURCH_STATUS_QUOTE, "Quote");
        list.add(o);
        return list;
    }
    
    private List<PurchaseOrderStatus> createMockPurchaseOrderStatuses() {
        List<PurchaseOrderStatus> list = new ArrayList<PurchaseOrderStatus>();
        PurchaseOrderStatus o = AccountingMockDataUtility
                .createPurchaseOrderStatus(VendorPurchasesConst.PURCH_STATUS_QUOTE, "Quote");
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
    
    private List<PurchaseOrderStatusHist> createMockPurchaseOrderCurrentStatusHistory() {
        List<PurchaseOrderStatusHist> list = new ArrayList<PurchaseOrderStatusHist>();
        PurchaseOrderStatusHist o = AccountingMockDataUtility
                .createPurchaseOrderStatusHist(1,
                        VendorPurchasesConst.PURCH_STATUS_QUOTE, 330,
                        "2017-01-02", null);
        list.add(o);

        return list;
    }
    
    private List<PurchaseOrderStatusHist> createMockPurchaseOrderStatusHistory() {
        List<PurchaseOrderStatusHist> list = new ArrayList<PurchaseOrderStatusHist>();
        PurchaseOrderStatusHist o = AccountingMockDataUtility
                .createPurchaseOrderStatusHist(1,
                        VendorPurchasesConst.PURCH_STATUS_NEW, 330, "2017-01-01",
                        "2017-01-01");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderStatusHist(2,
                VendorPurchasesConst.PURCH_STATUS_QUOTE, 330, "2017-02-02",
                "2017-02-02");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderStatusHist(3,
                VendorPurchasesConst.PURCH_STATUS_RECEIVED, 330, "2017-03-03",
                "2017-03-03");
        list.add(o);

        o = AccountingMockDataUtility.createPurchaseOrderStatusHist(4,
                VendorPurchasesConst.PURCH_STATUS_FINALIZE, 330, "2017-04-04",
                null);
        list.add(o);

        return list;
    }
}
