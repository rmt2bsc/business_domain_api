package org.rmt2.api.generalledger;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dto.AccountDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.generalledger.GeneralLedgerApiException;
import org.modules.generalledger.GeneralLedgerApiFactory;
import org.modules.generalledger.GlAccountApi;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseAccountingDaoTest;
import org.rmt2.dao.AccountingMockDataUtility;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the general ledger API library.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class AccountApiTest extends BaseAccountingDaoTest {
    private List<GlAccounts> mockSingleFetchResponse;
    private List<GlAccounts> mockCriteriaFetchResponse;
    private List<GlAccounts> mockFetchAllResponse;
    private List<GlAccounts> mockNotFoundFetchResponse;
    private List<GlAccounts> mockMultiResultsReturnedErrorResponse;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        APP_NAME = "accounting";
        super.setUp();
        this.mockSingleFetchResponse = this.createMockSingleFetchResponse();
        this.mockCriteriaFetchResponse = this
                .createMockFetchUsingCriteriaResponse();
        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this
                .createMockNotFoundSearchResultsResponse();
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

    private List<GlAccounts> createMockNotFoundSearchResultsResponse() {
        List<GlAccounts> list = null;
        return list;
    }

    private List<GlAccounts> createMockSingleFetchResponse() {
        List<GlAccounts> list = new ArrayList<GlAccounts>();
        GlAccounts p = AccountingMockDataUtility.createMockOrm(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<GlAccounts> createMockFetchUsingCriteriaResponse() {
        List<GlAccounts> list = new ArrayList<GlAccounts>();
        GlAccounts p = AccountingMockDataUtility.createMockOrm(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrm(101, 200, 300, 1, "GL_101", "CASH", "230",
                "CASH", 1);
        list.add(p);

        return list;
    }

    private List<GlAccounts> createMockFetchAllResponse() {
        List<GlAccounts> list = new ArrayList<GlAccounts>();
        GlAccounts p = AccountingMockDataUtility.createMockOrm(100, 1, 120, 1, "GL_100", "ACCT_RECV",
                "134", "Accounts Receivable", 1);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrm(200, 2, 150, 1, "GL_201", "ACCT_PAY", "230",
                "Accounts Payable", 2);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrm(300, 3, 160, 1, "GL_301", "OWNER_CAP", "330",
                "Owner's Capital", 2);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrm(400, 4, 170, 1, "GL_401", "CRED_PURCH", "430",
                "Creditor Purchases", 1);
        list.add(p);
        return list;
    }

    private List<GlAccounts> createMockFetchUidMultiResultsReturnedResponse() {
        List<GlAccounts> list = new ArrayList<GlAccounts>();
        GlAccounts p = AccountingMockDataUtility.createMockOrm(100, 1, 120, 1, "GL_100", "ACCT_RECV",
                "134", "Accounts Receivable", 1);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrm(100, 1, 150, 1, "GL_101", "CASH", "130",
                "Cash", 1);
        list.add(p);
        return list;
    }
    
    @Test
    public void testFetchAll() {
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount fetch test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        List<AccountDto> results = null;
        try {
            results = api.getAccount(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
    }

    @Test
    public void testFetchWithNullCriteriaObject() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount fetch test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        List<AccountDto> results = null;
        try {
            results = api.getAccount(null);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
    }

    @Test
    public void testFetchSingle() {
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        criteria.setAcctId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount fetch using criteria test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        List<AccountDto> results = null;
        try {
            results = api.getAccount(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        AccountDto dto = results.get(0);
        Assert.assertEquals(100, dto.getAcctId());
        Assert.assertEquals(200, dto.getAcctTypeId());
        Assert.assertEquals("GL_100", dto.getAcctNo());
    }

   
    @Test
    public void testFetchByUid() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount fetch by UID test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        AccountDto dto = null;
        try {
            dto = api.getAccount(100);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(100, dto.getAcctId());
        Assert.assertEquals(200, dto.getAcctTypeId());
        Assert.assertEquals("GL_100", dto.getAcctNo());
    }
    
    @Test
    public void testFetchByUidMultipleItemsReturned() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockMultiResultsReturnedErrorResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount fetch by UID test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        try {
            api.getAccount(100);
            Assert.fail("Expected exception to be thrown due multiple items returned");
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchUsingCriteria() {
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        criteria.setAcctTypeId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount fetch using criteria test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        List<AccountDto> results = null;
        try {
            results = api.getAccount(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (AccountDto dto : results) {
            Assert.assertTrue(dto.getAcctId() >= 100 && dto.getAcctId() <= 105);
            Assert.assertEquals(200, dto.getAcctTypeId());
        }
    }

    @Test
    public void testNotFoundlFetch() {
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        criteria.setAcctTypeId(999);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount not found fetch test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        List<AccountDto> results = null;
        try {
            results = api.getAccount(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testUpdate() {
        GlAccounts p = AccountingMockDataUtility.createMockOrm(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        AccountDto dto = AccountingMockDataUtility.createMockDto(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        dto.setAcctTypeId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account: Single GL Acccount fetch using criteria mock failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveObject(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account: Single GL Acccount fetch mock failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(GlAccounts.class)))
                    .thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount update row test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateAccount(dto);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
        Assert.assertEquals("Accounts Receivable modified", dto.getAcctDescription());
    }

    @Test
    public void testInsert() {
        AccountDto dto = AccountingMockDataUtility.createMockDto(0, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        dto.setAcctTypeId(200);
     
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(any(String.class)))
                    .thenReturn(mockResultSet);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Acccount insert row test case failed");
        }

//        try {
//            when(mockResultSet.next()).thenReturn(true);
//            when(mockResultSet.getInt("next_seq")).thenReturn(554);    
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            when(this.mockPersistenceClient.insertRow(any(GlAccounts.class), any(Boolean.class)))
                    .thenReturn(555);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount insert row test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateAccount(dto);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(555, rc);
        Assert.assertEquals(555, dto.getAcctId());
    }

    @Test
    public void testDelete() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testDeleteWithZeroUid() {
        Assert.fail("Test Failed");
    }
}