package org.rmt2.api.generalledger;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.generalledger.GeneralLedgerDaoException;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dto.AccountDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modules.AddressBookConstants;
import org.modules.generalledger.GeneralLedgerApiException;
import org.modules.generalledger.GeneralLedgerApiFactory;
import org.modules.generalledger.GlAccountApi;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.AccountingMockDataFactory;
import org.rmt2.api.BaseAccountingDaoTest;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotPersistException;
import com.api.persistence.CannotProceedException;
import com.api.persistence.CannotRemoveException;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the Account entity belonging to the GlAccountApi within the 
 * general ledger API library.
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
        GlAccounts p = AccountingMockDataFactory.createMockOrmGlAccounts(100, 200, 300, 1, "GL_100",
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
        GlAccounts p = AccountingMockDataFactory.createMockOrmGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmGlAccounts(101, 200, 300, 1, "GL_101", "CASH", "230",
                "CASH", 1);
        list.add(p);

        return list;
    }

    private List<GlAccounts> createMockFetchAllResponse() {
        List<GlAccounts> list = new ArrayList<GlAccounts>();
        GlAccounts p = AccountingMockDataFactory.createMockOrmGlAccounts(100, 1, 120, 1, "AccountNo1", "AccountName1",
                "AccoutCode1", "AccountDescription1", 1);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmGlAccounts(101, 2, 121, 1, "AccountNo2", "AccountName2", "AccoutCode2",
                "AccountDescription2", 2);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmGlAccounts(102, 3, 122, 1, "AccountNo3", "AccountName3", "AccoutCode3",
                "AccountDescription3", 2);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmGlAccounts(103, 4, 123, 1, "AccountNo4", "AccountName4", "AccoutCode4",
                "AccountDescription4", 1);
        list.add(p);
        return list;
    }

    private List<GlAccounts> createMockFetchUidMultiResultsReturnedResponse() {
        List<GlAccounts> list = new ArrayList<GlAccounts>();
        GlAccounts p = AccountingMockDataFactory.createMockOrmGlAccounts(100, 1, 120, 1, "AccountNo1", "ACCT_RECV",
                "134", "Accounts Receivable", 1);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmGlAccounts(100, 1, 150, 1, "AccountNo2", "CASH", "130",
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
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountDto> results = null;
        try {
            results = api.getAccount(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            AccountDto obj = results.get(ndx);
            Assert.assertEquals(obj.getAcctId(), (100 + ndx));
            Assert.assertEquals(obj.getAcctTypeId(), (1 + ndx));
            Assert.assertEquals(obj.getAcctCatgId(), (120 + ndx));
            Assert.assertEquals(obj.getAcctNo(), "AccountNo" + (ndx + 1));
            Assert.assertEquals(obj.getAcctName(), "AccountName" + (ndx + 1));
            Assert.assertEquals(obj.getAcctCode(), "AccoutCode" + (ndx + 1));
            Assert.assertEquals(obj.getAcctDescription(), "AccountDescription" + (ndx + 1));
            
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
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountDto> results = null;
        try {
            results = api.getAccount((AccountDto) null);
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
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount fetch using criteria test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount fetch by UID test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount fetch by UID test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount fetch using criteria test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount not found fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        GlAccounts p = AccountingMockDataFactory.createMockOrmGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        dto.setAcctTypeId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account: Single GL Acccount fetch using criteria mock setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveObject(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account: Single GL Acccount fetch mock setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(GlAccounts.class)))
                    .thenReturn(1);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount update row test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 0, null,
                "ACCT_RECV", "234", "Accounts Receivable", 1);
     
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account mock setup failed");
        }
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(any(String.class)))
                    .thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getInt("next_seq")).thenReturn(554);   
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Acccount insert row test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(any(GlAccounts.class), any(Boolean.class)))
                    .thenReturn(555);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount insert row test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateAccount(dto);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(555, rc);
        Assert.assertEquals(555, dto.getAcctId());
        Assert.assertEquals("200-300-555", dto.getAcctNo());
    }

    @Test
    public void testDelete() {
        try {
            when(this.mockPersistenceClient.deleteRow(any(Integer.class)))
                    .thenReturn(1);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Delete account mock setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteAccount(100);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

    
    @Test
    public void testUpdateWithNullAccountDto() {
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateAccount(null);
            Assert.fail("Expected exception to be thrown due to null input DTO object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CannotProceedException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithAccountThatDoesNotExists() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account not exist: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due account does not exists");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CannotProceedException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateExistCheckReturnsTooManyAccounts() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account exist check return too many: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due account exist chech return too many accounts");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CannotProceedException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateAccountNumberMissing() {
        String nullAcctNo = null;
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, nullAcctNo,
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account missing account number: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due account number missing");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithNegativeSequenceNumber() {
        int invalidSeqNo = -1;
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, invalidSeqNo, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account with negative sequence number: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due sequence number is a negative number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithInvalidSequenceNumber() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account with zero sequence number: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        int invalidSeqNo = -1;
        dto.setAcctSeq(invalidSeqNo);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due sequence number is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        invalidSeqNo = 0;
        dto.setAcctSeq(invalidSeqNo);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due sequence number is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    // Common validations
    @Test
    public void testUpdateWithInvalidAccountTypeId() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account with invalid account type id: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        int invalidNumericArg = -1;
        dto.setAcctTypeId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due negative account type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        invalidNumericArg = 0;
        dto.setAcctTypeId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due account type id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testUpdateWithInvalidAccountCategoryId() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account with invalid account category id: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        int invalidNumericArg = -1;
        dto.setAcctCatgId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due negative account category id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        invalidNumericArg = 0;
        dto.setAcctCatgId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due account category id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithInvalidBalanceTypeId() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account with invalid balance type id: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        int invalidNumericArg = -1;
        dto.setBalanceTypeId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due negative balance type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        invalidNumericArg = 0;
        dto.setBalanceTypeId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due balance type id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithNullAccountName() {
        String invalidStringArg = null;
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        dto.setAcctName(invalidStringArg);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account with null account name: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due to account name is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithNullAccountCode() {
        String invalidStringArg = null;
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        dto.setAcctCode(invalidStringArg);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account with null account code: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due to account code is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithNullAccountDescription() {
        String invalidStringArg = null;
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        dto.setAcctDescription(invalidStringArg);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account with null account description: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due to account description is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    
    
    @Test
    public void testInsertWithInvalidAccountTypeId() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account with invalid account type id: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        int invalidNumericArg = -1;
        dto.setAcctTypeId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due negative account type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        invalidNumericArg = 0;
        dto.setAcctTypeId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due account type id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testInsertWithInvalidAccountCategoryId() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account with invalid account category id: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        int invalidNumericArg = -1;
        dto.setAcctCatgId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due negative account category id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        invalidNumericArg = 0;
        dto.setAcctCatgId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due account category id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertWithInvalidBalanceTypeId() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account with invalid balance type id: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        int invalidNumericArg = -1;
        dto.setBalanceTypeId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due negative balance type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        invalidNumericArg = 0;
        dto.setBalanceTypeId(invalidNumericArg);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due balance type id equals zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertWithNullAccountName() {
        String invalidStringArg = null;
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        dto.setAcctName(invalidStringArg);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account with null account name: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due to account name is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertWithNullAccountCode() {
        String invalidStringArg = null;
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        dto.setAcctCode(invalidStringArg);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account with null account code: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due to account code is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertWithNullAccountDescription() {
        String invalidStringArg = null;
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        dto.setAcctDescription(invalidStringArg);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account with null account description: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due to account description is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    
    // Insert specific tests
    @Test
    public void testInsertWithDuplicateAccountName() {
        GlAccounts nameCheckCriteria = AccountingMockDataFactory.createMockOrmGlAccounts(0, 0, 0, 0, null,
                "ACCT_RECV", null, null, 0);
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 1, "GL_122",
                "ACCT_RECV", "255", "Cash account", 1);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(nameCheckCriteria)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due to account name is duplicated");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CannotProceedException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertWithDuplicateAccountCode() {
        GlAccounts nameCheckCriteria = AccountingMockDataFactory.createMockOrmGlAccounts(0, 0, 0, 0, null,
                "ACCT_RECV", null, null, 0);
        GlAccounts codeCheckCriteria = AccountingMockDataFactory.createMockOrmGlAccounts(0, 0, 0, 0, null,
                null, "234", null, 0);
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 1, "GL_122",
                "ACCT_RECV", "234", "Cash account", 1);
        dto.setAcctTypeId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(nameCheckCriteria)))
                    .thenReturn(this.mockNotFoundFetchResponse);
            when(this.mockPersistenceClient.retrieveList(eq(codeCheckCriteria)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account: Single GL Acccount fetch using criteria mock setup failed");
        }
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateAccount(dto);
            Assert.fail("Expected exception to be thrown due to account name is duplicated");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CannotProceedException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchAllWithException() {
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
            .thenThrow(DatabaseException.class);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountDto> results = null;
        try {
            results = api.getAccount(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof GeneralLedgerApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithException() {
        GlAccounts p = AccountingMockDataFactory.createMockOrmGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable modified", 1);
        dto.setAcctTypeId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account: Single GL Acccount fetch using criteria mock setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveObject(any(GlAccounts.class)))
                    .thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update account: Single GL Acccount fetch mock setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(GlAccounts.class)))
                    .thenThrow(DatabaseException.class);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount update row test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateAccount(dto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof GeneralLedgerApiException);
            Assert.assertTrue(e.getCause() instanceof CannotPersistException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertWithException() {
        AccountDto dto = AccountingMockDataFactory.createMockDtoGlAccounts(0, 200, 300, 0, null,
                "ACCT_RECV", "234", "Accounts Receivable", 1);
     
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account mock setup failed");
        }
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(any(String.class)))
                    .thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getInt("next_seq")).thenReturn(554);   
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Acccount insert row test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(any(GlAccounts.class), any(Boolean.class)))
            .thenThrow(DatabaseException.class);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount insert row test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateAccount(dto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof GeneralLedgerApiException);
            Assert.assertTrue(e.getCause() instanceof CannotPersistException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDeleteWithException() {
        try {
            when(this.mockPersistenceClient.deleteRow(any(Integer.class)))
            .thenThrow(DatabaseException.class);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Delete account mock setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteAccount(100);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof GeneralLedgerApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRemoveException);
            e.printStackTrace();
        }
    }
}