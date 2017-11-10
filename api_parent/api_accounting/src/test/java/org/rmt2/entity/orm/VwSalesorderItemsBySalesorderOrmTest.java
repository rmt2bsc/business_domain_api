package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.VwSalesorderItemsBySalesorder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modules.inventory.InventoryConst;
import org.rmt2.api.AccountingMockDataUtility;

public class VwSalesorderItemsBySalesorderOrmTest {

    private VwSalesorderItemsBySalesorder orm;

    @Before
    public void setUp() throws Exception {
        this.orm = this.buildItem();
    }

    private VwSalesorderItemsBySalesorder buildItem() {
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
                123450, InventoryConst.ITEM_TYPE_MERCH, "111-111-110",
                "11111110", 1351, "Item1", 10, 20.00, true);

        VwSalesorderItemsBySalesorder orm = AccountingMockDataUtility
                .createMockOrmVwSalesorderItemsBySalesorder(soi, so, cust, im,
                        imt);
        return orm;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        String val = this.orm.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwSalesorderItemsBySalesorder o1 = new VwSalesorderItemsBySalesorder();
        VwSalesorderItemsBySalesorder o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = this.orm;
        o2 = new VwSalesorderItemsBySalesorder();
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setSalesOrderItemId(88880);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setSoId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setItemId(33330);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setInitUnitCost(20.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setOrderQty(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setInitMarkup(3);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setBackOrderQty(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setItemNameOverride("ItemNameOverride" + o2.getItemId());
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setItemTypeId(InventoryConst.ITEM_TYPE_MERCH);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setItemTypeDescr("ItemTypeMerchandise");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setVendorItemNo("11111110");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setItemSerialNo("111-111-110");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setQtyOnHand(10);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setUnitCost(20.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setMarkup(5);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setCreditorId(1351);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setCustomerId(2000);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setBusinessId(1351);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setPersonId(0);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setInvoiced(0);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setItemName("Item1");
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setRetailPrice(((o2.getQtyOnHand() * o2.getUnitCost()) * o2.getMarkup()));
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwSalesorderItemsBySalesorder o1 = this.buildItem();
        VwSalesorderItemsBySalesorder o2 = this.buildItem();
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
