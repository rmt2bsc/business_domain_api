package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.AccountingConst.SubsidiaryType;
import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.CreditorActivity;
import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.subsidiary.CreditorDaoException;
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
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the creation of creditor activity transactions.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class CreditorSubsidiaryActivityTest extends TransactionApiTestData {

    

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
    public void testCreateCreditorTransaction() {
        // Setup stub to return mock creditor data
        Creditor mockCustCriteria = new Creditor();
        mockCustCriteria.setCreditorId(200);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCustCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
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
        

        try {
            when(this.mockPersistenceClient.insertRow(any(CreditorActivity.class), any(Boolean.class)))
                            .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert creditor activity case setup failed");
        }
        
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        int rc = 0;
        try {
            rc = api.createSubsidiaryActivity(200, SubsidiaryType.CREDITOR, 111111, 111.11);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testActivityDaoInsertFailure() {
        // Setup stub to return mock creditor data
        Creditor mockCustCriteria = new Creditor();
        mockCustCriteria.setCreditorId(200);
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockCustCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
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
        

        try {
            when(this.mockPersistenceClient.insertRow(any(CreditorActivity.class), any(Boolean.class)))
                       .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Insert creditor activity case setup failed");
        }
        
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.createSubsidiaryActivity(200, SubsidiaryType.CREDITOR, 111111, 111.11);
            Assert.fail("Expected excpetion due to Creditor Activity DAO insert method failed");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof CreditorDaoException);
            e.printStackTrace();
        }
    }

}