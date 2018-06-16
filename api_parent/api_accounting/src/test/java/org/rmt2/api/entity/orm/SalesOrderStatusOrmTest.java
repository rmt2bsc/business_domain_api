package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modules.transaction.sales.SalesApiConst;
import org.rmt2.api.AccountingMockDataFactory;

public class SalesOrderStatusOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        SalesOrderStatus o = AccountingMockDataFactory.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_QUOTE, "Quote");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        SalesOrderStatus o1 = new SalesOrderStatus();
        SalesOrderStatus o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataFactory.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_QUOTE, "Quote");
        o2 = new SalesOrderStatus();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("Quote");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        SalesOrderStatus o1 = AccountingMockDataFactory.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_QUOTE, "Quote");

        SalesOrderStatus o2 = AccountingMockDataFactory.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_QUOTE, "Quote");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
