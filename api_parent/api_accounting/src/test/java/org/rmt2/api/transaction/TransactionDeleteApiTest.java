package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dao.transaction.XactDaoException;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.TransactionAmountsUnbalancedException;
import org.modules.transaction.XactApi;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests general transaction delete Api functionality.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class TransactionDeleteApiTest extends TransactionApiTestData {

    private String lineItemErrorMsg;
    private String xactErrorMsg;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        StringBuilder xactIdList = new StringBuilder();
		for (Integer xactId : this.mockXactIdList) {
			if (xactIdList.length() > 0) {
				xactIdList.append(", ");
			}
			xactIdList.append(xactId);
		}
		
		this.lineItemErrorMsg = "An error occurred attempting to delete line item entries targeting transaction id's [" + xactIdList + "]";
		this.xactErrorMsg = "An error occurred attempting to delete base transaction entries targeting transaction id's [" + xactIdList + "]";
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
        try {
            when(this.mockPersistenceClient.deleteRow(any(Xact.class))).thenReturn(this.mockXactIdList.size());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up delete transaction mock case failed");
        }

        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        int results = 0;
        try {
            results = api.deleteXact(this.mockXactIdList);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(3, results);
    }
    
    @Test
    public void testApiError_DeleteXactLineItemOperation() {
    	String apiErrorMsg = "Test Database error occurred deleting transaction line items";
        try {
            when(this.mockPersistenceClient.deleteRow(any(XactTypeItemActivity.class)))
                 .thenThrow(new DatabaseException(apiErrorMsg));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up delete transaction line item mock case failed");
        }

        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        try {
            api.deleteXact(this.mockXactIdList);
            Assert.fail("testApiError_DeleteXactLineItemOperation test case failed...expected an exception to occur");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertEquals(e.getMessage(), this.lineItemErrorMsg);
            Assert.assertTrue(e.getCause() instanceof XactDaoException);
            Assert.assertEquals(e.getCause().getMessage(), this.lineItemErrorMsg);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(e.getCause().getCause().getMessage(), apiErrorMsg);
        }
    }
    
    @Test
    public void testApiError_DeleteBaseXactOperation() {
    	String apiErrorMsg = "Test Database error occurred deleting base transaction";
    	
    	 try {
             when(this.mockPersistenceClient.deleteRow(isA(XactTypeItemActivity.class)))
                  .thenReturn(this.mockXactIdList.size());
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail("Setting up delete transaction line item mock case failed");
         }
    	 
        try {
            when(this.mockPersistenceClient.deleteRow(isA(Xact.class)))
                 .thenThrow(new DatabaseException(apiErrorMsg));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up delete base transaction mock case failed");
        }

        XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        try {
            api.deleteXact(this.mockXactIdList);
            Assert.fail("testApiError_DeleteBaseXactOperation test case failed...expected an exception to occur");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertEquals(e.getMessage(), this.xactErrorMsg);
            Assert.assertTrue(e.getCause() instanceof XactDaoException);
            Assert.assertEquals(e.getCause().getMessage(), this.xactErrorMsg);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
            Assert.assertEquals(e.getCause().getCause().getMessage(), apiErrorMsg);
        }
    }

    @Test
    public void testValidateion_NullXactIdList() {
    	XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        try {
            api.deleteXact(null);
            Assert.fail("testError_NullXactIdList test case failed...expected an exception to occur");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
    @Test
    public void testValidateion_EmptyXactIdList() {
    	XactApi api = XactApiFactory.createDefaultXactApi(mockDaoClient);
        try {
            api.deleteXact(new ArrayList<Integer>());
            Assert.fail("testError_NullXactIdList test case failed...expected an exception to occur");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }
    
 
}