package org.rmt2.api.transaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.AccountingConst.SubsidiaryType;
import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.VwXactList;
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

import com.InvalidDataException;
import com.NotFoundException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the common validations pertaining to the creation of subsidiary 
 * transaction activity.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class SubsidiaryActivityValidationTest extends TransactionApiTestData {

    

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
    public void testTransactionWithNullSubsidiaryId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(null, SubsidiaryType.CUSTOMER, 111111, 111.11);
            Assert.fail("Expected an exception due to subsidiary id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testTransactionWithNullXactId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(200, SubsidiaryType.CUSTOMER, null, 111.11);
            Assert.fail("Expected an exception due to xact id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testTransactionWithNullAmount() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(200, SubsidiaryType.CUSTOMER, 111111, null);
            Assert.fail("Expected an exception due to amount id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testTransactionWithZeroSubsidiaryId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(0, SubsidiaryType.CUSTOMER, 111111, 111.11);
            Assert.fail("Expected an exception due to subsidiary id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testTransactionWithZeroXactId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(200, SubsidiaryType.CUSTOMER, 0, 111.11);
            Assert.fail("Expected an exception due to xact id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testTransactionWithNegativeSubsidiaryId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(-100, SubsidiaryType.CUSTOMER, 111111, 111.11);
            Assert.fail("Expected an exception due to subsidiary id is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testTransactionWithZNegativeXactId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(200, SubsidiaryType.CUSTOMER, -111111, 111.11);
            Assert.fail("Expected an exception due to xact id is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInvalidSubsidiaryId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            // This will work without stubbing any of the DAO fetch methods 
            // used to verfiy the existence of the customer or creditor.
            api.createSubsidiaryActivity(200, SubsidiaryType.CUSTOMER, 111111, 111.11);
            Assert.fail("Expected an exception due to subsidiary type could not be determined");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NotFoundException);
            e.printStackTrace();
        }
    }

    @Test
    public void testUnableToIdentifySubsidiaryType() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            // This will work without stubbing any of the DAO fetch methods
            // used to verfiy the existence of the customer or creditor.
            api.createSubsidiaryActivity(200, null, 111111, 111.11);
            Assert.fail("Expected an exception due to subsidiary type could not be determined");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testXactDaoFetchFailure() {
        // Setup stub to return mock customer data
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setCustomerId(200);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCustCriteria)))
                            .thenReturn(this.mockCustomerFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }

        // Setup stub to retrieve mock transaction data.
        VwXactList mockXactCriteria = new VwXactList();
        mockXactCriteria.setId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockXactCriteria)))
                            .thenThrow(XactApiException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }
        
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(200, SubsidiaryType.CUSTOMER, 111111, 111.11);
            Assert.fail("Expected an exception due to Xact DAO fetch method errored");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            e.printStackTrace();
        }
    }

    @Test
    public void testXactRecordNotFound() {
        // Setup stub to return mock customer data
        Customer mockCustCriteria = new Customer();
        mockCustCriteria.setCustomerId(200);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCustCriteria)))
                            .thenReturn(this.mockCustomerFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single customer test case setup failed");
        }

        // Setup stub to retrieve mock transaction data.
        VwXactList mockXactCriteria = new VwXactList();
        mockXactCriteria.setId(111111);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockXactCriteria)))
                            .thenReturn(mockXactNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single xact test case setup failed");
        }
        
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(200, SubsidiaryType.CUSTOMER, 222222, 111.11);
            Assert.fail("Expected an exception due to Xact does not exists");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NotFoundException);
            e.printStackTrace();
        }
    }
}