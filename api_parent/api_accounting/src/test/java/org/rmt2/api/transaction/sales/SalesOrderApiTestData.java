package org.rmt2.api.transaction.sales;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.dao.mapping.orm.rmt2.VwSalesOrderInvoice;
import org.junit.Before;
import org.modules.transaction.sales.SalesApiConst;
import org.rmt2.api.AccountingMockDataUtility;
import org.rmt2.api.transaction.TransactionApiTestData;

public class SalesOrderApiTestData extends TransactionApiTestData {

    protected List<SalesOrder> mockSalesOrderNotFoundResponse;
    protected List<SalesOrder> mockSalesOrderAllResponse;
    protected List<SalesOrder> mockSalesOrderSingleResponse;
    protected List<SalesInvoice> mockSalesInvoiceNotFoundResponse;
    protected List<SalesInvoice> mockSalesInvoiceAllResponse;
    protected List<SalesInvoice> mockSalesInvoiceSingleResponse;
    protected List<SalesOrderItems> mockSalesOrderItemsNotFoundResponse;
    protected List<SalesOrderItems> mockSalesOrderItemsAllResponse;
    protected List<VwSalesOrderInvoice> mockVwSalesOrderInvoiceNotFoundResponse;
    protected List<VwSalesOrderInvoice> mockVwSalesOrderInvoiceAllResponse;
    protected List<VwSalesOrderInvoice> mockVwSalesOrderInvoiceSingleResponse;
    
    protected List<SalesOrderStatusHist> mockStatusHistoryNotFoundResponse;
    protected List<SalesOrderStatusHist> mockStatusHistoryAllResponse;
    protected List<SalesOrderStatus> mockStatusNotFoundResponse;
    protected List<SalesOrderStatus> mockStatusAllResponse;
    
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockSalesOrderNotFoundResponse = this.createMockSalesOrderNotFoundResponse();
        this.mockSalesOrderAllResponse = this.createMockSalesOrderAllsponse();
        this.mockSalesOrderSingleResponse = this.createMockSalesOrderSingleResponse();
        
        this.mockSalesInvoiceNotFoundResponse = this.createMockSalesInvoiceNotFoundResponse();
        this.mockSalesInvoiceAllResponse = this.createMockSalesInvoiceAllsponse();
        this.mockSalesInvoiceSingleResponse = this.createMockSalesInvoiceSingleResponse();

        this.mockSalesOrderItemsNotFoundResponse = this.createMockSalesOrderItemsNotFoundResponse();
        this.mockSalesOrderItemsAllResponse = this.createMockSalesOrderItemsAllsponse();
        
        this.mockVwSalesOrderInvoiceNotFoundResponse = this.createMockVwSalesOrderInvoiceNotFoundResponse();
        this.mockVwSalesOrderInvoiceAllResponse = this.createMockVwSalesOrderInvoiceAllsponse();
        this.mockVwSalesOrderInvoiceSingleResponse = this.createMockVwSalesOrderInvoiceSingleResponse();

        this.mockStatusNotFoundResponse = this.createMockSalesOrderStatusNotFoundResponse();
        this.mockStatusAllResponse = this.createMockSalesOrderStatusAllResponse();
        
        return;
    }

    private List<SalesOrder> createMockSalesOrderNotFoundResponse() {
        List<SalesOrder> list = null;
        return list;
    }

    private List<SalesOrder> createMockSalesOrderAllsponse() {
        List<SalesOrder> list = new ArrayList<SalesOrder>();
        SalesOrder o = AccountingMockDataUtility.createMockOrmSalesOrder(1000,
                2000, 0, 300.00, "2017-01-01");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrder(1001, 2000, 0,
                600.00, "2017-01-01");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrder(1002, 2000, 0,
                900.00, "2017-02-01");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrder(1003, 2000, 0,
                1200.00, "2017-03-01");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrder(1004, 2000, 0,
                1500.00, "2017-04-01");
        list.add(o);
        return list;
    }

    private List<SalesOrder> createMockSalesOrderSingleResponse() {
        List<SalesOrder> list = new ArrayList<SalesOrder>();
        SalesOrder o = AccountingMockDataUtility.createMockOrmSalesOrder(1000,
                2000, 0, 100.00, "2017-01-01");
        list.add(o);
        return list;
    }

    private List<SalesInvoice> createMockSalesInvoiceNotFoundResponse() {
        List<SalesInvoice> list = null;
        return list;
    }

    private List<SalesInvoice> createMockSalesInvoiceAllsponse() {
        List<SalesInvoice> list = new ArrayList<SalesInvoice>();
        SalesInvoice o = AccountingMockDataUtility
                .createMockOrmSalesInvoice(7000, 1000, 5000, "80000");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesInvoice(7001, 1001,
                5001, "80001");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesInvoice(7002, 1002,
                5002, "80002");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesInvoice(7003, 1003,
                5003, "80003");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesInvoice(7004, 1004,
                5004, "80004");
        list.add(o);
        return list;
    }

    private List<SalesInvoice> createMockSalesInvoiceSingleResponse() {
        List<SalesInvoice> list = new ArrayList<SalesInvoice>();
        SalesInvoice o = AccountingMockDataUtility
                .createMockOrmSalesInvoice(7000, 1000, 5000, "80000");
        list.add(o);
        return list;
    }

    private List<SalesOrderItems> createMockSalesOrderItemsNotFoundResponse() {
        List<SalesOrderItems> list = null;
        return list;
    }

    private List<SalesOrderItems> createMockSalesOrderItemsAllsponse() {
        List<SalesOrderItems> list = new ArrayList<SalesOrderItems>();
        SalesOrderItems o = AccountingMockDataUtility
                .createMockOrmSalesOrderItem(88880, 33330, 1000, 1, 20.00);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderItem(88881, 33331,
                1000, 2, 5.00);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderItem(88882, 33332,
                1000, 10, 2.00);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderItem(88883, 33333,
                1000, 2, 15.00);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderItem(88884, 33334,
                1000, 20, 1.00);
        list.add(o);
        return list;
    }

    private List<VwSalesOrderInvoice> createMockVwSalesOrderInvoiceNotFoundResponse() {
        List<VwSalesOrderInvoice> list = null;
        return list;
    }

    private List<VwSalesOrderInvoice> createMockVwSalesOrderInvoiceAllsponse() {
        List<VwSalesOrderInvoice> list = new ArrayList<VwSalesOrderInvoice>();
        VwSalesOrderInvoice o = AccountingMockDataUtility
                .createMockOrmVwSalesOrderInvoice(7000, 1000, "2017-01-01",
                        300.00, SalesApiConst.STATUS_CODE_INVOICED, "80000", 1,
                        "2017-01-10", 444440, 2000, 1234, "111-111");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmVwSalesOrderInvoice(7001,
                1001, "2017-02-01", 600.00, SalesApiConst.STATUS_CODE_INVOICED,
                "80001", 1, "2017-02-10", 444440, 2000, 1234, "111-111");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmVwSalesOrderInvoice(7002,
                1002, "2017-03-01", 900.00, SalesApiConst.STATUS_CODE_INVOICED,
                "80002", 1, "2017-03-10", 444440, 2000, 1234, "111-111");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmVwSalesOrderInvoice(7003,
                1003, "2017-04-01", 1200.00, SalesApiConst.STATUS_CODE_INVOICED,
                "80003", 1, "2017-04-10", 444440, 2000, 1234, "111-111");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmVwSalesOrderInvoice(7004,
                1004, "2017-05-01", 1500.00, SalesApiConst.STATUS_CODE_INVOICED,
                "80004", 1, "2017-05-10", 444440, 2000, 1234, "111-111");
        list.add(o);
        return list;
    }

    private List<VwSalesOrderInvoice> createMockVwSalesOrderInvoiceSingleResponse() {
        List<VwSalesOrderInvoice> list = new ArrayList<VwSalesOrderInvoice>();
        VwSalesOrderInvoice o = AccountingMockDataUtility
                .createMockOrmVwSalesOrderInvoice(7000, 1000, "2017-01-01",
                        300.00, SalesApiConst.STATUS_CODE_INVOICED, "80000", 1,
                        "2017-01-10", 444440, 2000, 1234, "111-111");
        list.add(o);
        return list;
    }
    
    
    
    private List<SalesOrderStatus> createMockSalesOrderStatusNotFoundResponse() {
        List<SalesOrderStatus> list = null;
        return list;
    }

    private List<SalesOrderStatus> createMockSalesOrderStatusAllResponse() {
        List<SalesOrderStatus> list = new ArrayList<SalesOrderStatus>();
        SalesOrderStatus o = AccountingMockDataUtility
                .createMockOrmSalesOrderStatus(SalesApiConst.STATUS_CODE_NEW, "New");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_QUOTE, "Quote");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_INVOICED, "Invoiced");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_CANCELLED, "Canclelled");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_REFUNDED, "Refunded");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatus(
                SalesApiConst.STATUS_CODE_CLOSED, "Closed");
        list.add(o);
        return list;
    }
    
    private List<SalesOrderStatusHist> createMockSalesOrderStatusHistoryAllResponse() {
        List<SalesOrderStatusHist> list = new ArrayList<SalesOrderStatusHist>();
        SalesOrderStatusHist o = AccountingMockDataUtility
                .createMockOrmSalesOrderStatusHist(70, 1000,
                        SalesApiConst.STATUS_CODE_NEW, "2017-01-10",
                        "2017-01-31");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(71,
                1000, SalesApiConst.STATUS_CODE_QUOTE, "2017-02-10",
                "2017-02-28");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(72,
                1000, SalesApiConst.STATUS_CODE_INVOICED, "2017-03-10",
                "2017-03-31");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(73,
                1000, SalesApiConst.STATUS_CODE_CANCELLED, "2017-04-10",
                "2017-04-30");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(74,
                1000, SalesApiConst.STATUS_CODE_REFUNDED, "2017-05-10",
                "2017-05-31");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmSalesOrderStatusHist(75,
                1000, SalesApiConst.STATUS_CODE_CLOSED, "2017-06-10",
                "2017-06-30");
        list.add(o);
        return list;
    }
}
