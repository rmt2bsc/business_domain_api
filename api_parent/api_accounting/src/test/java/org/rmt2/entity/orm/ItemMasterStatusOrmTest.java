package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataUtility;

public class ItemMasterStatusOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ItemMasterStatus o =  AccountingMockDataUtility.createMockOrmItemMasterStatus(100, "Description 1");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ItemMasterStatus o1 = new ItemMasterStatus();
        ItemMasterStatus o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataUtility.createMockOrmItemMasterStatus(100, "Description 1");
        o2 = new ItemMasterStatus();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setItemStatusId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);
   
        o2.setDescription("Description 1");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ItemMasterStatus o1 = AccountingMockDataUtility.createMockOrmItemMasterStatus(100, "Description 1");

        ItemMasterStatus o2 = AccountingMockDataUtility.createMockOrmItemMasterStatus(100, "Description 1");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
