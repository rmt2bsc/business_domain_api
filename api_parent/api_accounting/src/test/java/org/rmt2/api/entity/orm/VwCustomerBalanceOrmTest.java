package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.VwCustomerBalance;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataFactory;

public class VwCustomerBalanceOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwCustomerBalance o =  AccountingMockDataFactory.createVwCustomerBalance(100, 420.11);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwCustomerBalance o1 = new VwCustomerBalance();
        VwCustomerBalance o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataFactory.createVwCustomerBalance(100, 420.11);
        o2 = new VwCustomerBalance();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setCustomerId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBalance(420.11);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwCustomerBalance o1 = AccountingMockDataFactory.createVwCustomerBalance(100, 420.11);

        VwCustomerBalance o2 = AccountingMockDataFactory.createVwCustomerBalance(100, 420.11);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
