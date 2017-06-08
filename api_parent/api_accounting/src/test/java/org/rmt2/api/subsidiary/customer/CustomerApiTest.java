package org.rmt2.api.subsidiary.customer;

import junit.framework.Assert;

import org.AccountingConst;
import org.dto.CustomerDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.subsidiary.CustomerApi;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.subsidiary.SubsidiaryException;
import org.rmt2.api.CommonAccountingTest;

/**
 * @author Roy Terrell
 * 
 */
public class CustomerApiTest extends CommonAccountingTest {

    private SubsidiaryApiFactory f;
    private CustomerApi api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.f = new SubsidiaryApiFactory();
        this.api = this.f.createCustomerApi();
        this.api.setApiUser("TestUser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.f = null;
        this.api.close();
        this.api = null;
        super.tearDown();
    }

    @Test
    public void testCreateQueryUpdateDelete() {
        // Create customer
        int businessId = 1465;
        CustomerDto cust = Rmt2SubsidiaryDtoFactory.createCustomerInstance(
                null, null);
        cust.setContactId(businessId);
        cust.setActive(AccountingConst.ACCT_ACTIVE);
        cust.setCreditLimit(1000.00);
        int key;

        this.api.beginTrans();
        try {
            key = this.api.update(cust);
        } catch (SubsidiaryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(key > 0);

        // Query Customer
        CustomerDto cust2;
        try {
            cust2 = this.api.getByCustomerId(key);
        } catch (SubsidiaryException e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(businessId, cust2.getContactId());

        // Update customer
        double creditLimitUpdate = 99.99;
        cust2.setCreditLimit(creditLimitUpdate);
        int rc;
        try {
            rc = this.api.update(cust2);
        } catch (SubsidiaryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(rc, 1);

        // Query Customer to verify update
        CustomerDto cust3;
        try {
            cust3 = this.api.getByCustomerId(key);
        } catch (SubsidiaryException e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(creditLimitUpdate, cust3.getCreditLimit());

        // Delete Customer to verify update
        try {
            rc = this.api.delete(cust3);
        } catch (SubsidiaryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(1, rc);
        this.api.commitTrans();

        // Verify that Customer was deleted
        try {
            cust3 = this.api.getByCustomerId(key);
        } catch (SubsidiaryException e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertNull(cust3);
    }

    @Test
    public void testBalance() {
        try {
            double results = this.api.getBalance(19);
            Assert.assertTrue(results > 0);
        } catch (SubsidiaryException e) {
            e.printStackTrace();
        } // business id = 1462
    }

}
