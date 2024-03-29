package org.rmt2.api.generalledger;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.generalledger.GeneralLedgerDaoException;
import org.dao.mapping.orm.rmt2.GlAccountTypes;
import org.dto.AccountTypeDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.AddressBookConstants;
import org.modules.generalledger.GeneralLedgerApiException;
import org.modules.generalledger.GeneralLedgerApiFactory;
import org.modules.generalledger.GlAccountApi;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.AccountingMockDataFactory;
import org.rmt2.api.BaseAccountingDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the Account Type entity belonging to the GlAccountApi within the 
 * general ledger API library.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class AccountTypeApiTest extends BaseAccountingDaoTest {
    private List<GlAccountTypes> mockSingleFetchResponse;
    private List<GlAccountTypes> mockCriteriaFetchResponse;
    private List<GlAccountTypes> mockFetchAllResponse;
    private List<GlAccountTypes> mockNotFoundFetchResponse;
    private List<GlAccountTypes> mockMultiResultsReturnedErrorResponse;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockSingleFetchResponse = this.createMockSingleFetchResponse();
        this.mockCriteriaFetchResponse = this.createMockFetchUsingCriteriaResponse();
        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this.createMockNotFoundSearchResultsResponse();
        this.mockMultiResultsReturnedErrorResponse = this.createMockFetchUidMultiResultsReturnedResponse();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<GlAccountTypes> createMockNotFoundSearchResultsResponse() {
        List<GlAccountTypes> list = null;
        return list;
    }

    private List<GlAccountTypes> createMockSingleFetchResponse() {
        List<GlAccountTypes> list = new ArrayList<GlAccountTypes>();
        GlAccountTypes p = AccountingMockDataFactory.createMockOrmGlAccountTypes(100, 1, "Asset");
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<GlAccountTypes> createMockFetchUsingCriteriaResponse() {
        List<GlAccountTypes> list = new ArrayList<GlAccountTypes>();
        GlAccountTypes p = AccountingMockDataFactory.createMockOrmGlAccountTypes(100, 1, "Asset");
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmGlAccountTypes(104, 1, "Expense");
        list.add(p);

        return list;
    }

    private List<GlAccountTypes> createMockFetchAllResponse() {
        List<GlAccountTypes> list = new ArrayList<GlAccountTypes>();
        GlAccountTypes p = AccountingMockDataFactory.createMockOrmGlAccountTypes(100, 1, "AccountType1");
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmGlAccountTypes(101, 1, "AccountType2");
        list.add(p);
        
        p = AccountingMockDataFactory.createMockOrmGlAccountTypes(102, 2, "AccountType3");
        list.add(p);
        
        p = AccountingMockDataFactory.createMockOrmGlAccountTypes(103, 2, "AccountType4");
        list.add(p);
        
        p = AccountingMockDataFactory.createMockOrmGlAccountTypes(104, 2, "AccountType5");
        list.add(p);
        return list;
    }

    private List<GlAccountTypes> createMockFetchUidMultiResultsReturnedResponse() {
        List<GlAccountTypes> list = new ArrayList<GlAccountTypes>();
        GlAccountTypes p = AccountingMockDataFactory.createMockOrmGlAccountTypes(100, 1, "Asset");
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmGlAccountTypes(400, 1, "Expense");
        list.add(p);
        return list;
    }
    
    
    @Test
    public void testFetchAll() {
        AccountTypeDto criteria = Rmt2AccountDtoFactory.createAccountTypeInstance(null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountTypes.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount Type fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountTypeDto> results = null;
        try {
            results = api.getAccountType(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            AccountTypeDto obj = results.get(ndx);
            Assert.assertEquals(obj.getAcctTypeId(), (100 + ndx));
            Assert.assertEquals(obj.getAcctTypeDescription(), "AccountType" + (ndx + 1));
            try {
                Assert.assertEquals(0, obj.getEntityId());
                Assert.fail("Expected exception since method is not supported");
            }
            catch (Exception e) {
                Assert.assertTrue(e instanceof UnsupportedOperationException);
            }
            try {
                Assert.assertNotNull(obj.getEntityName());
                Assert.fail("Expected exception since method is not supported");
            }
            catch (Exception e) {
                Assert.assertTrue(e instanceof UnsupportedOperationException);
            }
        }
    }

    @Test
    public void testFetchWithNullCriteriaObject() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountTypes.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount Type fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountTypeDto> results = null;
        try {
            results = api.getAccountType(null);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }

    @Test
    public void testFetchSingle() {
        AccountTypeDto criteria = Rmt2AccountDtoFactory.createAccountTypeInstance(null);
        criteria.setAcctTypeId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountTypes.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount Type fetch using criteria test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountTypeDto> results = null;
        try {
            results = api.getAccountType(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        AccountTypeDto dto = results.get(0);
        Assert.assertEquals(100, dto.getAcctTypeId());
        Assert.assertEquals(1, dto.getBalanceTypeId());
        Assert.assertEquals("Asset", dto.getAcctTypeDescription());
    }

   
    @Test
    public void testFetchByUid() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountTypes.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount Type fetch by UID test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        AccountTypeDto dto = null;
        try {
            dto = api.getAccountType(100);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(100, dto.getAcctTypeId());
        Assert.assertEquals(1, dto.getBalanceTypeId());
        Assert.assertEquals("Asset", dto.getAcctTypeDescription());
    }
    
    @Test
    public void testFetchByUidMultipleItemsReturned() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountTypes.class)))
                    .thenReturn(this.mockMultiResultsReturnedErrorResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount Type fetch by UID test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getAccountType(100);
            Assert.fail("Expected exception to be thrown due multiple items returned");
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchUsingCriteria() {
        AccountTypeDto criteria = Rmt2AccountDtoFactory.createAccountTypeInstance(null);
        criteria.setBalanceTypeId(1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountTypes.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount fetch using criteria test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountTypeDto> results = null;
        try {
            results = api.getAccountType(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (AccountTypeDto dto : results) {
            Assert.assertTrue(dto.getAcctTypeId() >= 100 && dto.getAcctTypeId() <= 105);
            Assert.assertEquals(1, dto.getBalanceTypeId());
        }
    }

    @Test
    public void testNotFoundlFetch() {
        AccountTypeDto criteria = Rmt2AccountDtoFactory.createAccountTypeInstance(null);
        criteria.setAcctTypeId(999);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountTypes.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount Type not found fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountTypeDto> results = null;
        try {
            results = api.getAccountType(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchAllWithException() {
        AccountTypeDto criteria = Rmt2AccountDtoFactory.createAccountTypeInstance(null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountTypes.class)))
                   .thenThrow(DatabaseException.class);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount Type fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountTypeDto> results = null;
        try {
            results = api.getAccountType(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof GeneralLedgerApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
}