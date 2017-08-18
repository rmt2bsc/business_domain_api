package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
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
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests transaction finalization.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TransactoionReversalTest extends TransactionApiTestData {

    private XactDto mockXact;
    private List<XactTypeItemActivityDto> mockXactItemList;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        try {
            when(this.mockPersistenceClient.insertRow(any(XactTypeItemActivity.class), any(Boolean.class)))
                .thenReturn(5);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact tyope item activity test case setup failed");
        }
        // Mock base transaction stub last since its return code will be asserted.
        // This is due to using back to back any matcher of r insertRow calls.
        try {
            when(this.mockPersistenceClient.insertRow(any(Xact.class), any(Boolean.class)))
               .thenReturn(777);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Update xact test case setup failed");
        }
        
        // Build mock transaction object to be updated
        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0); 
        vwXact.setXactSubtypeId(XactConst.XACT_SUBTYPE_NOT_ASSIGNED);
        this.mockXact = Rmt2XactDtoFactory.createXactInstance(vwXact);

        // Build list of mock transaction type item activity object to be updated
        this.mockXactItemList = new ArrayList<XactTypeItemActivityDto>();
        for (XactTypeItemActivity xtia : this.mockXactTypeItemActivityFetchAllResponse) {
            XactTypeItemActivityDto dto = Rmt2XactDtoFactory.createXactTypeItemActivityInstance(xtia);
            this.mockXactItemList.add(dto);
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
    public void testSuccess() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        
        int rc = 0;
        try {
            rc = api.reverse(this.mockXact, this.mockXactItemList);
        } catch (XactApiException e) {
            e.printStackTrace();
            Assert.fail("An exception was not expected here...");
        }
        Assert.assertEquals(777, rc);
        Assert.assertEquals(777, this.mockXact.getXactId());
    }
}