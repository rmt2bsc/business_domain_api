package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.GlAccountTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataUtility;

public class GLAccountTypesOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        GlAccountTypes o1 = AccountingMockDataUtility.createMockOrmGlAccountTypes(100, 1, "Asset");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        GlAccountTypes o1 = new GlAccountTypes();
        GlAccountTypes o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = AccountingMockDataUtility.createMockOrmGlAccountTypes(100, 1, "Asset");
        o2 = new GlAccountTypes();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAcctTypeId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAcctBaltypeId(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("Asset");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        GlAccountTypes o1 = AccountingMockDataUtility.createMockOrmGlAccountTypes(100, 1, "Asset");
        GlAccountTypes o2 = AccountingMockDataUtility.createMockOrmGlAccountTypes(100, 1, "Asset");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
