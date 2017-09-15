package org.rmt2.api.transaction;

import java.sql.ResultSet;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dto.XactDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.XactApi;
import org.modules.transaction.XactApiFactory;
import org.modules.transaction.XactConst;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests transaction modifiable state.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class ModifiableStateTransactionTest extends TransactionApiTestData {

    

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
    public void testWithNullXactInput() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        
        try {
            api.isModifiable(null);
            Assert.fail("Expected excpetion due to Xact input is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUnassignedTransactionModifiableState() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactTypeId(XactConst.XACT_TYPE_SALESONACCTOUNT);
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        try {
            Assert.assertTrue(api.isModifiable(mockXact));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception occurred");
        }
    }
    
    @Test
    public void testFinalizedTransactionModifiableState() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactTypeId(XactConst.XACT_TYPE_SALESONACCTOUNT);
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_FINAL);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        try {
            Assert.assertFalse(api.isModifiable(mockXact));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception occurred");
        }
    }
    
    @Test
    public void testReverseTransactionModifiableState() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactTypeId(XactConst.XACT_TYPE_SALESONACCTOUNT);
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_REVERSE);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        try {
            Assert.assertFalse(api.isModifiable(mockXact));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception occurred");
        }
    }
    
    @Test
    public void testCancelledTransactionModifiableState() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactTypeId(XactConst.XACT_TYPE_SALESONACCTOUNT);
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_CANCEL);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        try {
            Assert.assertFalse(api.isModifiable(mockXact));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception occurred");
        }
    }
}