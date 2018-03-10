package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataFactory;

public class SalesOrderItemsOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        SalesOrderItems o = AccountingMockDataFactory.createMockOrmSalesOrderItem(88880, 33330, 1000, 1, 20.00);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        SalesOrderItems o1 = new SalesOrderItems();
        SalesOrderItems o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataFactory.createMockOrmSalesOrderItem(88880, 33330, 1000, 1, 20.00);
        o2 = new SalesOrderItems();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSoItemId(88880);
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
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        SalesOrderItems o1 = AccountingMockDataFactory.createMockOrmSalesOrderItem(88880, 33330, 1000, 1, 20.00);
        SalesOrderItems o2 = AccountingMockDataFactory.createMockOrmSalesOrderItem(88880, 33330, 1000, 1, 20.00);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
