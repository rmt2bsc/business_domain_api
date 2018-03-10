package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataFactory;

public class SalesInvoiceOrmTest {
    
    private SalesInvoice obj;

    @Before
    public void setUp() throws Exception {
        this.obj = AccountingMockDataFactory.createMockOrmSalesInvoice(7000, 1000, 5000, "80000");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        SalesInvoice o = this.obj;
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        SalesInvoice o1 = new SalesInvoice();
        SalesInvoice o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = this.obj;
        o2 = new SalesInvoice();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setInvoiceId(7000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSoId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setXactId(5000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setInvoiceNo("80000");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        SalesInvoice o1 = this.obj;
        SalesInvoice o2 = this.obj;
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
