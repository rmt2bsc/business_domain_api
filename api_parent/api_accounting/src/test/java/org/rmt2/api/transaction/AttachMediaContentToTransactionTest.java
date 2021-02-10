package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.Xact;
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
 * Tests adding media content attachment to transaction.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class AttachMediaContentToTransactionTest extends TransactionApiTestData {

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
            Assert.fail("Update xact mock failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveList(isA(VwXactList.class))).thenReturn(this.mockXactFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Retrieve single xact mock failed");
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
    public void test_Success() {
        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0);
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_REVERSE);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);

        int rc = 0;
        try {
            rc = api.update(mockXact);
        } catch (XactApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected here...");
        }
        Assert.assertEquals(1, rc);
        Assert.assertEquals(111111 + 200, mockXact.getXactId() + mockXact.getXactTenderId());
    }


    
    @Test
    public void test_API_Error() {
        try {
            when(this.mockPersistenceClient.retrieveList(isA(VwXactList.class))).thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact DAO exception test case setup failed");
        }
        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0);
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_REVERSE);
        XactDto mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);
        try {
            api.update(mockXact);
            Assert.fail("Expected excpetion due to Xact DAO update method failed");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            e.printStackTrace();
        }
    }

}