package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.transaction.XactDaoException;
import org.dto.XactDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.XactApi;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactApiFactory;
import org.modules.transaction.XactConst;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests transaction finalization.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class FinalizeTransactionTest extends TransactionApiTestData {

    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        try {
            when(this.mockPersistenceClient.updateRow(any(Xact.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }
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
    public void testReverseFinalizationSuccess() {
        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        
        List<VwXactList> xactList = new ArrayList<>();
        VwXactList obj = new VwXactList();
        obj.setXactSubtypeId(XactConst.XACT_SUBTYPE_REVERSE);
        xactList.add(obj);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwXactList.class))).thenReturn(xactList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("REverse transaction test case setup failed");
        }
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_REVERSE);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        try {
            api.finalizeXact(mockXact);
        } catch (XactApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected here...");
        }
    }

    /**
     * This tests java way of handling whole dollar amounts when converting 
     * from double to String.  This ususally will results in 100.00 translating 
     * to 100.0.
     */
    @Test
    public void testReverseFinalizationSuccess_WithEvenXactAmount() {
        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        
        List<VwXactList> xactList = new ArrayList<>();
        VwXactList obj = new VwXactList();
        obj.setXactSubtypeId(XactConst.XACT_SUBTYPE_REVERSE);
        xactList.add(obj);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwXactList.class))).thenReturn(xactList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Reverse transaction test case setup failed");
        }
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_REVERSE);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        mockXact.setXactAmount(100.00);
        try {
            api.finalizeXact(mockXact);
        } catch (XactApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected here...");
        }
    }
    
    @Test
    public void testCancellationFinalizationSuccess() {
        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        
        List<VwXactList> xactList = new ArrayList<>();
        VwXactList obj = new VwXactList();
        obj.setXactSubtypeId(XactConst.XACT_SUBTYPE_CANCEL);
        xactList.add(obj);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwXactList.class))).thenReturn(xactList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Cancel transaction test case setup failed");
        }
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactTypeId(XactConst.XACT_TYPE_SALESONACCTOUNT);
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_CANCEL);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        try {
            api.finalizeXact(mockXact);
        } catch (XactApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected here...");
        }
    }
    
    @Test
    public void testDaoFailure() {
    	List<VwXactList> xactList = new ArrayList<>();
        VwXactList obj = new VwXactList();
        obj.setXactSubtypeId(XactConst.XACT_SUBTYPE_CANCEL);
        xactList.add(obj);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwXactList.class))).thenReturn(xactList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Cancel transaction test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.updateRow(any(Xact.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact DAO exception test case setup failed");
        }
        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactTypeId(XactConst.XACT_TYPE_SALESONACCTOUNT);
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_CANCEL);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        try {
            api.finalizeXact(mockXact);
            Assert.fail("Expected excpetion due to Xact DAO update method failed");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof XactDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testNullTransactionObjectInput() {
        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        
        try {
            api.finalizeXact(null);
            Assert.fail("Expected excpetion due to null Xact input");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            e.printStackTrace();
        }
    }
}