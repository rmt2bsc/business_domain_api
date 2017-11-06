package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modules.transaction.sales.SalesApiConst;
import org.rmt2.api.AccountingMockDataUtility;

import com.util.RMT2Date;

public class SalesOrderStatusHistoryOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        SalesOrderStatusHist o = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(70, 1000,
                SalesApiConst.STATUS_CODE_NEW, "2017-01-10", "2017-01-31");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        SalesOrderStatusHist o1 = new SalesOrderStatusHist();
        SalesOrderStatusHist o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(70, 1000, SalesApiConst.STATUS_CODE_NEW,
                "2017-01-10", "2017-01-31");
        o2 = new SalesOrderStatusHist();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSoId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSoStatusHistId(70);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSoStatusId(SalesApiConst.STATUS_CODE_NEW);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEffectiveDate(RMT2Date.stringToDate("2017-01-10"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setReason("SalesOrderStatusHistoryReason" + o2.getSoStatusId());
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setEndDate(RMT2Date.stringToDate("2017-01-31"));
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        SalesOrderStatusHist o1 = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(70, 1000,
                SalesApiConst.STATUS_CODE_NEW, "2017-01-10", "2017-01-31");
        SalesOrderStatusHist o2 = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(70, 1000,
                SalesApiConst.STATUS_CODE_NEW, "2017-01-10", "2017-01-31");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
