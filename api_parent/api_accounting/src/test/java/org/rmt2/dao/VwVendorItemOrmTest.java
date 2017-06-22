package org.rmt2.dao;

import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VwVendorItemOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwVendorItems o =  AccountingMockDataUtility.createMockOrmVwVendorItems(
                100, "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwVendorItems o1 = new VwVendorItems();
        VwVendorItems o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataUtility.createMockOrmVwVendorItems(
                100, "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23);
        o2 = new VwVendorItems();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setItemId(100);
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
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwVendorItems o1 = AccountingMockDataUtility.createMockOrmVwVendorItems(
                100, "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23);

        VwVendorItems o2 = AccountingMockDataUtility.createMockOrmVwVendorItems(
                100, "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
