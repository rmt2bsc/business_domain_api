package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.dao.mapping.orm.rmt2.XactCodeGroup;
import org.dto.XactCodeGroupDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
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
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests transaction groups of the transaction Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class TransactionGroupApiTest extends TransactionApiTestData {

    

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
    public void testFetchAllData() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCodeGroup.class)))
                            .thenReturn(this.mockCodeGroupFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all transaction groups test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        List<XactCodeGroupDto> results = null;
        XactCodeGroupDto criteria = null;
        try {
            results = api.getGroup(criteria);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            XactCodeGroupDto grp = results.get(ndx);
            Assert.assertEquals(grp.getEntityId(), (10 + ndx));
            Assert.assertEquals(grp.getEntityName(), "Code Group " + (ndx + 10));
        }
    }

    @Test
    public void testFetchSingleData() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCodeGroup.class)))
                            .thenReturn(this.mockCodeGroupFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single transaction groups test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        List<XactCodeGroupDto> results = null;
        XactCodeGroupDto criteria = Rmt2XactDtoFactory.createXactCodeGroupInstance(null);
        try {
            results = api.getGroup(criteria);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(results.get(0).getEntityName(), "Code Group 10");
    }
    
    @Test
    public void testFetchSingleNotFound() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCodeGroup.class)))
                            .thenReturn(this.mockCodeGroupNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single transaction groups test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        XactCodeGroupDto results = null;
        try {
            results = api.getGroup(999);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchWithNullGroupId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        Integer grpIdParm = null;
        try {
            api.getGroup(grpIdParm);
            Assert.fail("Expected exception due to null input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchWithZeroGroupId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            Integer grpIdParm = 0;
            api.getGroup(grpIdParm);
            Assert.fail("Expected exception due to zero input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithNegativeGroupId() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            Integer grpIdParm = -100;
            api.getGroup(grpIdParm);
            Assert.fail("Expected exception due to negative input value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithDaoException() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(XactCodeGroup.class)))
                            .thenThrow(CannotRetrieveException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all transaction groups with exception test case setup failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(this.mockDaoClient);
        try {
            XactCodeGroupDto criteria = Rmt2XactDtoFactory.createXactCodeGroupInstance(null);
            api.getGroup(criteria);
            Assert.fail("Expected exception due to DAO exception");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
}