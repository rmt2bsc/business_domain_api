package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataUtility;

import com.util.RMT2Date;

public class ItemMasterStatusHistOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ItemMasterStatusHist o = AccountingMockDataUtility
                .createMockOrmItemMasterStatusHistory(10, 100, 1000, 12.50, 3,
                        "2107-01-01", "2017-02-15",
                        "Item Status History Description 1");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ItemMasterStatusHist o1 = new ItemMasterStatusHist();
        ItemMasterStatusHist o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataUtility.createMockOrmItemMasterStatusHistory(10,
                100, 1000, 12.50, 3, "2107-01-01", "2017-02-15",
                "Item Status History Description 1");
        o2 = new ItemMasterStatusHist();
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setItemStatusHistId(10);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setItemId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setItemStatusId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setUnitCost(12.50);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setMarkup(3);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setEffectiveDate(RMT2Date.stringToDate("2107-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setEndDate(RMT2Date.stringToDate("2017-02-15"));
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setReason("Item Status History Description 1");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ItemMasterStatusHist o1 = AccountingMockDataUtility
                .createMockOrmItemMasterStatusHistory(10, 100, 1000, 12.50, 3,
                        "2107-01-01", "2017-02-15",
                        "Item Status History Description 1");

        ItemMasterStatusHist o2 = AccountingMockDataUtility
                .createMockOrmItemMasterStatusHistory(10, 100, 1000, 12.50, 3,
                        "2107-01-01", "2017-02-15",
                        "Item Status History Description 1");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
