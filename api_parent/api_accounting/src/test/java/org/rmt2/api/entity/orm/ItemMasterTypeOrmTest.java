package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataFactory;

public class ItemMasterTypeOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ItemMasterType o =  AccountingMockDataFactory.createMockOrmItemMasterType(100, "Description 1");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ItemMasterType o1 = new ItemMasterType();
        ItemMasterType o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataFactory.createMockOrmItemMasterType(100, "Description 1");
        o2 = new ItemMasterType();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setItemTypeId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);
   
        o2.setDescription("Description 1");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ItemMasterType o1 = AccountingMockDataFactory.createMockOrmItemMasterType(100, "Description 1");

        ItemMasterType o2 = AccountingMockDataFactory.createMockOrmItemMasterType(100, "Description 1");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
