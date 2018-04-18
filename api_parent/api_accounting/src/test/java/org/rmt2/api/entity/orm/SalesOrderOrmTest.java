package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.SalesOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataFactory;

import com.util.RMT2Date;

public class SalesOrderOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        SalesOrder o = AccountingMockDataFactory.createMockOrmSalesOrder(1000,
                2000, 0, 300.00, "2017-01-01");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        SalesOrder o1 = new SalesOrder();
        SalesOrder o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataFactory.createMockOrmSalesOrder(1000,
                2000, 0, 300.00, "2017-01-01");
        o2 = new SalesOrder();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSoId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setCustomerId(2000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setOrderTotal(300.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setInvoiced(0);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEffectiveDate(RMT2Date.stringToDate("2017-01-01"));
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        SalesOrder o1 = AccountingMockDataFactory.createMockOrmSalesOrder(1000,
                2000, 0, 300.00, "2017-01-01");

        SalesOrder o2 = AccountingMockDataFactory.createMockOrmSalesOrder(1000,
                2000, 0, 300.00, "2017-01-01");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
