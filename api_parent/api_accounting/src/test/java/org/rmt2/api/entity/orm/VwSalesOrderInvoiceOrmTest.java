package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.VwSalesOrderInvoice;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modules.transaction.sales.SalesApiConst;
import org.rmt2.api.AccountingMockDataFactory;

import com.util.RMT2Date;

public class VwSalesOrderInvoiceOrmTest {
    
    private VwSalesOrderInvoice obj;

    @Before
    public void setUp() throws Exception {
        this.obj = AccountingMockDataFactory.createMockOrmVwSalesOrderInvoice(
                7000, 1000, "2017-01-01", 300.00,
                SalesApiConst.STATUS_CODE_INVOICED, "80000", 1, "2017-01-10",
                444440, 2000, 1234, "111-111");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwSalesOrderInvoice o = this.obj;
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwSalesOrderInvoice o1 = new VwSalesOrderInvoice();
        VwSalesOrderInvoice o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = this.obj;
        o2 = new VwSalesOrderInvoice();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setInvoiceId(7000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSalesOrderId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setCustomerId(2000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setOrderTotal(300.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setInvoiced(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setXactId(444440);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAcctId(1234);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setCreditLimit(1000.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAccountNo("111-111");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setInvoiceNo("80000");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setInvoiceDate(RMT2Date.stringToDate("2017-01-10"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setOrderStatusId(SalesApiConst.STATUS_CODE_INVOICED);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setOrderStatusDescr("SalesOrderStatus" + o2.getOrderStatusId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("Description" + o2.getAcctId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSalesOrderDate(RMT2Date.stringToDate("2017-01-01"));
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwSalesOrderInvoice o1 = this.obj;
        VwSalesOrderInvoice o2 = this.obj;
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
