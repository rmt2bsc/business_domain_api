package org.rmt2.api.transaction.purchases.creditor;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwXactCreditChargeList;
import org.junit.Before;
import org.modules.transaction.XactConst;
import org.rmt2.api.transaction.TransactionApiTestData;
import org.rmt2.dao.AccountingMockDataUtility;

public class CreditPurchaseApiTestData extends TransactionApiTestData {

    protected List<VwXactCreditChargeList> mockCreditPurchaseNotFoundResponse;
    protected List<VwXactCreditChargeList> mockCreditPurchaseAllResponse;
    protected List<VwXactCreditChargeList> mockCreditPurchaseSingleResponse;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockCreditPurchaseNotFoundResponse = this.createMockCreditPurchaseNotFoundResponse();
        this.mockCreditPurchaseAllResponse = this.createMockCreditPurchaseAllsponse();
        this.mockCreditPurchaseSingleResponse = this.createMockCreditPurchaseSingleResponse();
        return;
    }

    private List<VwXactCreditChargeList> createMockCreditPurchaseNotFoundResponse() {
        List<VwXactCreditChargeList> list = null;
        return list;
    }

    private List<VwXactCreditChargeList> createMockCreditPurchaseAllsponse() {
        List<VwXactCreditChargeList> list = new ArrayList<VwXactCreditChargeList>();
        VwXactCreditChargeList o = AccountingMockDataUtility
                .createMockOrmXVwXactCreditChargeList(7000, 1111111, 1351,
                        XactConst.XACT_TYPE_CREDITOR_PURCHASE, "1111",
                        XactConst.XACT_SUBTYPE_NOT_ASSIGNED, 20.00,
                        "2017-01-01", XactConst.TENDER_CREDITCARD,
                        "1111-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXVwXactCreditChargeList(7001,
                2222222, 1352, XactConst.XACT_TYPE_CREDITOR_PURCHASE, "2222",
                XactConst.XACT_SUBTYPE_NOT_ASSIGNED, 21.00, "2017-01-01",
                XactConst.TENDER_CREDITCARD, "2222-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXVwXactCreditChargeList(7002,
                3333333, 1353, XactConst.XACT_TYPE_CREDITOR_PURCHASE, "3333",
                XactConst.XACT_SUBTYPE_NOT_ASSIGNED, 22.00, "2017-01-01",
                XactConst.TENDER_CREDITCARD, "3333-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXVwXactCreditChargeList(7003,
                4444444, 1354, XactConst.XACT_TYPE_CREDITOR_PURCHASE, "4444",
                XactConst.XACT_SUBTYPE_NOT_ASSIGNED, 23.00, "2017-01-01",
                XactConst.TENDER_CREDITCARD, "4444-0000-0000-0000");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXVwXactCreditChargeList(7004,
                5555555, 1355, XactConst.XACT_TYPE_CREDITOR_PURCHASE, "5555",
                XactConst.XACT_SUBTYPE_NOT_ASSIGNED, 24.00, "2017-01-01",
                XactConst.TENDER_CREDITCARD, "5555-0000-0000-0000");
        list.add(o);
        return list;
    }

    private List<VwXactCreditChargeList> createMockCreditPurchaseSingleResponse() {
        List<VwXactCreditChargeList> list = new ArrayList<VwXactCreditChargeList>();
        VwXactCreditChargeList o = AccountingMockDataUtility
.createMockOrmXVwXactCreditChargeList(7000, 111111, 1351,
                        XactConst.XACT_TYPE_CREDITOR_PURCHASE, "1111",
                        XactConst.XACT_SUBTYPE_NOT_ASSIGNED, 20.00,
                        "2017-01-01", XactConst.TENDER_CREDITCARD,
                        "1111-0000-0000-0000");
        list.add(o);
        return list;
    }
}
