package org.rmt2.api.subsidiary.creditor;

import junit.framework.Assert;

import org.AccountingConst;
import org.dto.CreditorDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.subsidiary.SubsidiaryException;
import org.rmt2.api.CommonAccountingTest;

/**
 * @author Roy Terrell
 * 
 */
public class CreditorApiTest extends CommonAccountingTest {

    private SubsidiaryApiFactory f;
    private CreditorApi api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.f = new SubsidiaryApiFactory();
        this.api = this.f.createCreditorApi();
        this.api.setApiUser("TestUser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.f = null;
        api.close();
        this.api = null;
        super.tearDown();
    }

    @Test
    public void testCreateQueryUpdateDelete() {
        // Create creditor
        int businessId = 1451;
        CreditorDto cred = Rmt2SubsidiaryDtoFactory.createCreditorInstance(
                null, null);
        cred.setContactId(businessId);
        cred.setActive(AccountingConst.ACCT_ACTIVE);
        cred.setCreditLimit(1000.00);
        cred.setCreditorTypeId(AccountingConst.CREDITORTYPE_CREDITOR);
        int key;

        this.api.beginTrans();
        try {
            key = this.api.update(cred);
        } catch (SubsidiaryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(key > 0);

        // Query Creditor
        CreditorDto cred2;
        try {
            cred2 = this.api.getByCreditorId(key);
        } catch (SubsidiaryException e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(businessId, cred2.getContactId());

        // Update creditor
        double creditLimitUpdate = 99.99;
        cred2.setCreditLimit(creditLimitUpdate);
        int rc;
        try {
            rc = this.api.update(cred2);
        } catch (SubsidiaryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(rc, 1);

        // Query Creditor to verify update
        CreditorDto cred3;
        try {
            cred3 = this.api.getByCreditorId(key);
        } catch (SubsidiaryException e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(creditLimitUpdate, cred3.getCreditLimit());

        // Delete Creditor to verify update
        try {
            rc = this.api.delete(cred3);
        } catch (SubsidiaryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(1, rc);
        this.api.commitTrans();

        // Verify that Creditor was deleted
        try {
            cred3 = this.api.getByCreditorId(key);
        } catch (SubsidiaryException e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertNull(cred3);
    }

    @Test
    public void testBalance() {
        try {
            double results = this.api.getBalance(8);
            Assert.assertTrue(results > 0);
        } catch (SubsidiaryException e) {
            e.printStackTrace();
        } // business id = 1451
    }

}
