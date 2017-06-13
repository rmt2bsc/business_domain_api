package org.rmt2.dao;

import org.dao.mapping.orm.rmt2.ItemMaster;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemMasterOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ItemMaster o =  AccountingMockDataUtility.createMockOrmItemMaster(100, 1,
                "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23, true);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ItemMaster o1 = new ItemMaster();
        ItemMaster o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataUtility.createMockOrmItemMaster(100, 1,
                "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23, true);
        o2 = new ItemMaster();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setItemId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setItemTypeId(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setItemSerialNo("111-111-111");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setVendorItemNo("11111111");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setCreditorId(1234);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("Item # 1");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setQtyOnHand(5);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setUnitCost(1.23);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setMarkup(3);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setRetailPrice((o2.getQtyOnHand() * o2.getUnitCost()) * o2.getMarkup());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setActive(1);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ItemMaster o1 = AccountingMockDataUtility.createMockOrmItemMaster(100, 1,
                "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23, true);

        ItemMaster o2 = AccountingMockDataUtility.createMockOrmItemMaster(100, 1,
                "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23, true);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
