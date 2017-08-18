package org.rmt2.api.transaction.sales;

/**
 * @author Roy Terrell
 * 
 */
public class ExistingSalesOrderQueryTest extends CommonSalesOrderTest {

    /**
     * 
     */
    public ExistingSalesOrderQueryTest() {
        super();
    }

//    @Test
//    public void testSalesOrderQuery() {
//        SalesOrderDto criteria = Rmt2SalesOrderDtoFactory
//                .createSalesOrderInstance(null);
//        criteria.setCustomerId(3);
//        criteria.setInvoiced(true);
//        try {
//            List<SalesOrderDto> results = this.dao.fetchSalesOrder(criteria);
//            Assert.assertNotNull(results);
//            Assert.assertTrue(results.size() > 0);
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testSalesInvoiceQueryByAccountNo() {
//        SalesInvoiceDto criteria = Rmt2SalesOrderDtoFactory
//                .createSalesIvoiceInstance((SalesInvoice) null);
//        criteria.setInvoiceNo("559-");
//        try {
//            List<SalesInvoiceDto> results = this.dao
//                    .fetchSalesInvoice(criteria);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(1, results.size());
//            Assert.assertEquals(17075, results.get(0).getXactId());
//            Assert.assertEquals("559-20121022", results.get(0).getInvoiceNo());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testExtSalesInvoiceQueryByInvoiceDate() {
//        SalesInvoiceDto criteria = Rmt2SalesOrderDtoFactory
//                .createSalesIvoiceInstance((VwSalesOrderInvoice) null);
//        criteria.setInvoiceDate(RMT2Date.stringToDate("2012-12-14"));
//        try {
//            List<SalesInvoiceDto> results = this.dao
//                    .fetchExtSalesOrder(criteria);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(3, results.size());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testExtSalesInvoiceQueryByInvoiceId() {
//        SalesInvoiceDto criteria = Rmt2SalesOrderDtoFactory
//                .createSalesIvoiceInstance((VwSalesOrderInvoice) null);
//        criteria.setInvoiceId(580);
//        try {
//            List<SalesInvoiceDto> results = this.dao
//                    .fetchExtSalesOrder(criteria);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(1, results.size());
//            Assert.assertEquals(17486, results.get(0).getXactId());
//            Assert.assertEquals(2400.00, results.get(0).getOrderTotal());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testSalesOrderItems() {
//        int salesOrderId = 583;
//        try {
//            List<SalesOrderItemDto> results = this.dao
//                    .fetchSalesOrderItem(salesOrderId);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(1, results.size());
//            Assert.assertEquals(776, results.get(0).getSoItemId());
//            Assert.assertEquals(2400.00, results.get(0).getInitUnitCost());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCreateNewExtSalesOrderItemDto() {
//        SalesOrderItemDto dto = Rmt2SalesOrderDtoFactory
//                .createSalesOrderItemInstance((VwSalesorderItemsBySalesorder) null);
//        int salesOrderId = 583;
//        dto.setSalesOrderId(salesOrderId);
//        try {
//            List<SalesOrderItemDto> results = this.dao
//                    .fetchExtSalesOrderItem(dto.getSalesOrderId());
//            Assert.assertNotNull(results);
//            Assert.assertEquals(1, results.size());
//            Assert.assertEquals(776, results.get(0).getSoItemId());
//            Assert.assertEquals(2400.00, results.get(0).getInitUnitCost());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testExtSalesOrderItems() {
//        int salesOrderId = 583;
//        try {
//            List<SalesOrderItemDto> results = this.dao
//                    .fetchExtSalesOrderItem(salesOrderId);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(1, results.size());
//            Assert.assertEquals(776, results.get(0).getSoItemId());
//            Assert.assertEquals(2400.00, results.get(0).getInitUnitCost());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testSalesOrderStatusQueryByDescription() {
//        SalesOrderStatusDto criteria = Rmt2SalesOrderDtoFactory
//                .createSalesOrderStatusInstance(null);
//        try {
//            criteria.setSoStatusDescription("c");
//            List<SalesOrderStatusDto> results = this.dao
//                    .fetchSalesOrderStatus(criteria);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(2, results.size());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testSalesOrderStatusQueryById() {
//        SalesOrderStatusDto criteria = Rmt2SalesOrderDtoFactory
//                .createSalesOrderStatusInstance(null);
//        try {
//            criteria.setSoStatusId(1);
//            List<SalesOrderStatusDto> results = this.dao
//                    .fetchSalesOrderStatus(criteria);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(1, results.size());
//            Assert.assertEquals("Quote", results.get(0)
//                    .getSoStatusDescription());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testSalesOrderStatusHistoryQueryById() {
//        SalesOrderStatusHistDto criteria = Rmt2SalesOrderDtoFactory
//                .createSalesOrderStatusHistoryInstance(null);
//        try {
//            criteria.setSoStatusHistId(1);
//            List<SalesOrderStatusHistDto> results = this.dao
//                    .fetchSalesOrderStatusHistory(criteria);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(1, results.size());
//            Assert.assertEquals(1, results.get(0).getSoId());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testSalesOrderStatusHistoryQueryBySalesOrderId() {
//        SalesOrderStatusHistDto criteria = Rmt2SalesOrderDtoFactory
//                .createSalesOrderStatusHistoryInstance(null);
//        try {
//            criteria.setSoId(1);
//            List<SalesOrderStatusHistDto> results = this.dao
//                    .fetchSalesOrderStatusHistory(criteria);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(2, results.size());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCurrentSalesOrderStatusHistoryQueryBySalesOrderId() {
//        int salesOrderId = 1;
//        try {
//            SalesOrderStatusHistDto results = this.dao
//                    .fetchCurrentSalesOrderStatus(salesOrderId);
//            Assert.assertNotNull(results);
//            Assert.assertEquals(2, results.getSoStatusHistId());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
}
