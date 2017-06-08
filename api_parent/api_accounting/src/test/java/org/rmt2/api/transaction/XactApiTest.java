package org.rmt2.api.transaction;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dao.subsidiary.CreditorDao;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.transaction.XactApi;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactApiFactory;
import org.modules.transaction.XactConst;
import org.rmt2.api.CommonAccountingTest;

/**
 * @author Roy Terrell
 * 
 */
public class XactApiTest extends CommonAccountingTest {
    private XactApiFactory f;
    private SubsidiaryDaoFactory subFactory;
    private XactApi api;
    private CreditorDao sharedDao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        // Create DAO to share
        this.subFactory = new SubsidiaryDaoFactory();
        this.sharedDao = subFactory.createRmt2OrmCreditorDao();

        // Setup Xact API with shared DAO
        this.f = new XactApiFactory();
        this.api = this.f.createDefaultXactApi(sharedDao);
        this.api.setApiUser("TestUser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.subFactory = null;
        this.f = null;
        this.api.close();
        this.api = null;
        this.sharedDao.close();
        super.tearDown();
    }

    // @Test
    // public void testCreateInvalidCashDisbursementWithoutTenderCode() {
    // XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
    // xact.setXactAmount(123.99);
    // xact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
    // xact.setXactReason("This is a test");
    //
    // List<XactTypeItemActivityDto> items = new
    // ArrayList<XactTypeItemActivityDto>();
    // XactTypeItemActivityDto item = Rmt2XactDtoFactory
    // .createXactTypeItemActivityInstance(null);
    // item.setActivityAmount(123.00);
    // item.setXactItemId(6);
    // item.setXactTypeItemActvName("Food Item");
    // items.add(item);
    //
    // item = Rmt2XactDtoFactory.createXactTypeItemActivityInstance(null);
    // item.setActivityAmount(0.99);
    // item.setXactItemId(36);
    // item.setXactTypeItemActvName("Gas Item");
    // items.add(item);
    //
    // int results = 0;
    // this.api.beginTrans();
    // try {
    // results = this.api.update(xact, items);
    // } catch (XactApiException e) {
    // this.api.rollbackTrans();
    // return;
    // }
    // Assert.fail("Transaction was expected to fail since tender code id is absent");
    // }

    @Test
    public void testCreateInvalidCashDisbursementWithoutItemNames() {
        XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        xact.setXactAmount(123.99);
        xact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
        xact.setXactReason("This is a test");
        xact.setXactTenderId(11);

        List<XactTypeItemActivityDto> items = new ArrayList<XactTypeItemActivityDto>();
        XactTypeItemActivityDto item = Rmt2XactDtoFactory
                .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setActivityAmount(123.00);
        item.setXactItemId(6);
        items.add(item);

        item = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setActivityAmount(0.99);
        item.setXactItemId(36);
        items.add(item);

        int results = 0;
        this.api.beginTrans();
        try {
            results = this.api.update(xact, items);
        } catch (XactApiException e) {
            this.api.rollbackTrans();
            return;
        }
        Assert.fail("Transaction was expected to fail since transaction items did not have item names");
    }

    @Test
    public void testCreateInvalidCashDisbursementWithoutItems() {
        XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        xact.setXactAmount(123.99);
        xact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
        xact.setXactReason("This is a test");
        xact.setXactTenderId(11);

        // At the common transaction level, you should be able to save a
        // transaction without details.
        int results = 0;
        this.api.beginTrans();
        try {
            List<XactTypeItemActivityDto> items = new ArrayList<XactTypeItemActivityDto>();
            results = this.api.update(xact, items);
        } catch (XactApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(results > 0);
        this.api.commitTrans();
    }

    @Test
    public void testCreateTransaction() {
        XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        xact.setXactAmount(123.99);
        xact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
        xact.setXactReason("This is a test");
        xact.setXactTenderId(11);
        xact.setXactConfirmNo("XXXXXXXXXX");

        List<XactTypeItemActivityDto> items = new ArrayList<XactTypeItemActivityDto>();
        XactTypeItemActivityDto item = Rmt2XactDtoFactory
                .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setActivityAmount(123.00);
        item.setXactItemId(6);
        item.setXactTypeItemActvName("Food Item");
        items.add(item);

        item = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setActivityAmount(0.99);
        item.setXactItemId(36);
        item.setXactTypeItemActvName("Gas Item");
        items.add(item);

        int results = 0;
        this.api.beginTrans();
        try {
            results = this.api.update(xact, items);
        } catch (XactApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(results > 0);
        this.api.commitTrans();
    }

    @Test
    public void testReverseTransaction() {
        // Create the cash disbursement tranasaction
        XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        xact.setXactAmount(123.99);
        xact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
        xact.setXactReason("This is a test");
        xact.setXactTenderId(11);
        xact.setXactConfirmNo("XXXXXXXXXX");

        List<XactTypeItemActivityDto> items = new ArrayList<XactTypeItemActivityDto>();
        XactTypeItemActivityDto item = Rmt2XactDtoFactory
                .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setActivityAmount(123.00);
        item.setXactItemId(6);
        item.setXactTypeItemActvName("Food Item");
        items.add(item);

        item = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setActivityAmount(0.99);
        item.setXactItemId(36);
        item.setXactTypeItemActvName("Gas Item");
        items.add(item);

        int results = 0;
        this.api.beginTrans();
        try {
            results = this.api.update(xact, items);
        } catch (XactApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(results > 0);

        // Reverse the transaction
        try {
            results = this.api.reverse(xact, items);
        } catch (XactApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(results > 100);
        this.api.commitTrans();
    }

    @Test
    public void testFinalizeTransaction() {
        // Create the cash disbursement tranasaction
        XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        xact.setXactAmount(123.99);
        xact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
        xact.setXactReason("This is a test");
        xact.setXactTenderId(11);
        xact.setXactConfirmNo("XXXXXXXXXX");

        List<XactTypeItemActivityDto> items = new ArrayList<XactTypeItemActivityDto>();
        XactTypeItemActivityDto item = Rmt2XactDtoFactory
                .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setActivityAmount(123.00);
        item.setXactItemId(6);
        item.setXactTypeItemActvName("Food Item");
        items.add(item);

        item = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setActivityAmount(0.99);
        item.setXactItemId(36);
        item.setXactTypeItemActvName("Gas Item");
        items.add(item);

        int results = 0;
        this.api.beginTrans();
        try {
            results = this.api.update(xact, items);
        } catch (XactApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(results > 0);

        // Finalize the transaction
        try {
            this.api.finalizeXact(xact);
        } catch (XactApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(XactConst.XACT_TYPE_FINAL, xact.getXactSubtypeId());

        // Reverse the transaction
        try {
            results = this.api.reverse(xact, items);
        } catch (XactApiException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        this.api.commitTrans();
    }
}
