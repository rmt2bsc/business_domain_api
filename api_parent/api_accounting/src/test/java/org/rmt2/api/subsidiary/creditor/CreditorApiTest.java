package org.rmt2.api.subsidiary.creditor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dto.CreditorDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.CommonAccountingConst;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.CreditorApiException;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.subsidiary.SubsidiaryApiTest;
import org.rmt2.dao.AccountingMockDataUtility;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Creditor Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class CreditorApiTest extends SubsidiaryApiTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockCreditorFetchAllResponse = this.createMockFetchAllResponse();
        this.mockCreditorNotFoundFetchResponse = this.createMockNotFoundSearchResultsResponse();
        this.mockCreditorFetchSingleResponse = this.createMockSingleCreditorFetchResponse();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<Creditor> createMockNotFoundSearchResultsResponse() {
        List<Creditor> list = null;
        return list;
    }


    private List<Creditor> createMockFetchAllResponse() {
        List<Creditor> list = new ArrayList<Creditor>();
        Creditor o = AccountingMockDataUtility.createMockOrmCreditor(200, 1351,
                333, "C1234589", "123-456-789", 22);
        list.add(o);
        
        o = AccountingMockDataUtility.createMockOrmCreditor(201, 1400,
                444, "C1400444", "7437437JDJD8484", 22);
        list.add(o);
        
        o = AccountingMockDataUtility.createMockOrmCreditor(202, 1500,
                555, "C1500555", "ABC123", 22);
        list.add(o);
        
        o = AccountingMockDataUtility.createMockOrmCreditor(203, 1600,
                666, "C1600666", "XYZ321", 22);
        list.add(o);
        
        o = AccountingMockDataUtility.createMockOrmCreditor(204, 1700,
                777, "C1700777", "7654312", 22);
        list.add(o);
        return list;
    }

    
    private List<Creditor> createMockSingleCreditorFetchResponse() {
        List<Creditor> list = new ArrayList<Creditor>();
        Creditor o = AccountingMockDataUtility.createMockOrmCreditor(200, 1351,
                333, "C1234589", "123-456-789", 22);
        list.add(o);
        return list;
    }


    @Test
    public void testFetchAllNoContactData() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(Creditor.class)))
                            .thenReturn(this.mockCreditorFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all creditors test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }

    @Test
    public void testFetchSingleNoContactData() {
        Creditor mockCriteria = new Creditor();
        mockCriteria.setCreditorId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        criteria.setCreditorId(200);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }
    
    @Test
    public void testFetchSingleNotFound() {
        Creditor mockCriteria = new Creditor();
        mockCriteria.setCreditorId(999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockCreditorNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        criteria.setCreditorId(999);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchWithNullCriteriaObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.get(null);
            Assert.fail("Expected exception due to null selection criteria object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchByAccountNumber() {
        Creditor mockCredCriteria = new Creditor();
        mockCredCriteria.setAccountNumber("C1234589");
        VwBusinessAddress mockContactCritereia = new VwBusinessAddress();
        this.setupSingleSubsidiaryContactInfoFetch(mockContactCritereia, mockCredCriteria);
        
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        List<CreditorDto> results = null;
        try {
            results = api.getByAcctNo("C1234589");
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }
  

    @Test
    public void testFetchByAccountNumberWithNullValue() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(CommonAccountingConst.APP_NAME);
        try {
            api.getByAcctNo(null);
            Assert.fail("Expected exception due to null account number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
}