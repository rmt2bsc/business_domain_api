package org.rmt2.dao;

import org.dao.mapping.orm.rmt2.GlAccounts;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GLAccountsOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        GlAccounts o =  AccountingMockDataUtility.createMockOrm(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        GlAccounts o1 = new GlAccounts();
        GlAccounts o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataUtility.createMockOrm(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        o2 = new GlAccounts();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAcctId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAcctTypeId(200);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAcctCatgId(300);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAcctSeq(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAcctNo("GL_100");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setName("ACCT_RECV");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setCode("234");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("Accounts Receivable");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAcctBaltypeId(1);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        GlAccounts o1 = AccountingMockDataUtility.createMockOrm(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);

        GlAccounts o2 = AccountingMockDataUtility.createMockOrm(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
