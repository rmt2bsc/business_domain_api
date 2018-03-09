package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.ProjectTrackerMockDataFactory;

public class ClientOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        ProjClient o1 = ProjectTrackerMockDataFactory.createMockOrmProjClient(
                1000, 1350, "1000 Company", 70.00, 80.00, "000-111", "steve",
                "gadd", "0000000000", "stevegadd@gte.net");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        ProjClient o1 = new ProjClient();
        ProjClient o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1 = ProjectTrackerMockDataFactory.createMockOrmProjClient(
                1000, 1350, "1000 Company", 70.00, 80.00, "000-111", "steve",
                "gadd", "0000000000", "stevegadd@gte.net");
        o2 = new ProjClient();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setClientId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBusinessId(1350);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setName("1000 Company");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBillRate(70.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setOtBillRate(80.00);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAccountNo("000-111");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setContactFirstname("steve");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setContactLastname("gadd");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setContactPhone("0000000000");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setContactEmail("stevegadd@gte.net");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        ProjClient o1 = ProjectTrackerMockDataFactory.createMockOrmProjClient(
                1000, 1350, "1000 Company", 70.00, 80.00, "000-111", "steve",
                "gadd", "0000000000", "stevegadd@gte.net");
        ProjClient o2 = ProjectTrackerMockDataFactory.createMockOrmProjClient(
                1000, 1350, "1000 Company", 70.00, 80.00, "000-111", "steve",
                "gadd", "0000000000", "stevegadd@gte.net");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
