package org.rmt2.entity.adapter;

import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dto.SalesOrderDto;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataUtility;

import com.util.RMT2Date;

/**
 * Test Sales Order adapters
 * 
 * @author roy.terrell
 *
 */
public class SalesOrderAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAccountAdapter() {
        SalesOrder o = AccountingMockDataUtility.createMockOrmSalesOrder(1000,
                2000, 0, 300.00, "2017-01-01");
        SalesOrderDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(o);
        
        Assert.assertEquals(1000, dto.getSalesOrderId());
        Assert.assertEquals(2000, dto.getCustomerId());
        Assert.assertEquals(300.00, dto.getOrderTotal(), 0);
        Assert.assertFalse(dto.isInvoiced());
        Assert.assertEquals(RMT2Date.stringToDate("2017-01-01"), dto.getSaleOrderDate());
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
 
}
