package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.CustomerActivity;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.XactApi;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the creation of subsidiary transactions.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class SubsidiaryTransactionCreationTest extends TransactionApiTestData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    @Test
    public void testCreateCustomerTransaction() {
        // Setup stub to return mock customer data
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setCustomerId(200);
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
//        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCustCriteria);
        try {
            when(this.mockPersistenceClient
                    .retrieveList(eq(mockContactCritereia))).thenReturn(
                            this.mockBusinessContactFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Business Contact for customer fetch test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCustCriteria)))
                    .thenReturn(this.mockCustomerFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Customer fetch test case setup failed");
        }

        
        // Setup stub to return not found creditor mock data.
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setCreditorId(200);
//        this.setupNotFoundSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCredCriteria)))
                    .thenReturn(this.mockCreditorNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Creditor fetch test case setup failed");
        }

        // Setup stub to retrieve mock transaction data.
        VwXactList mockXactCriteria = new VwXactList();
        mockXactCriteria.setId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockXactCriteria)))
                            .thenReturn(this.mockXactFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }
        
        // Setup stub to insert customer activity mock data.
        CustomerActivity mockActivityCriteria = SubsidiaryDaoFactory.createCustomerActivity();
        mockActivityCriteria.setCustomerId(200);
        mockActivityCriteria.setXactId(111111);
        mockActivityCriteria.setAmount(111.11);
        try {
            when(this.mockPersistenceClient.insertRow(eq(mockActivityCriteria), any(Boolean.class)))
                            .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert customer activity case setup failed");
        }
        
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        int rc = 0;
        try {
            rc = api.createSubsidiaryTransaction(200, 111111, 111.11);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

}