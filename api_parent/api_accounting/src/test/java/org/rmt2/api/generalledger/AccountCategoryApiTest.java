package org.rmt2.api.generalledger;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.generalledger.GeneralLedgerDaoException;
import org.dao.mapping.orm.rmt2.GlAccountCategory;
import org.dto.AccountCategoryDto;
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
import org.rmt2.api.AccountingMockDataUtility;
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
 * Tests the Account Type entity belonging to the GlAccountApi within the 
 * general ledger API library.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class AccountCategoryApiTest extends BaseAccountingDaoTest {
    private List<GlAccountCategory> mockSingleFetchResponse;
    private List<GlAccountCategory> mockCriteriaFetchResponse;
    private List<GlAccountCategory> mockFetchAllResponse;
    private List<GlAccountCategory> mockNotFoundFetchResponse;
    private List<GlAccountCategory> mockMultiResultsReturnedErrorResponse;

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

    private List<GlAccountCategory> createMockNotFoundSearchResultsResponse() {
        List<GlAccountCategory> list = null;
        return list;
    }

    private List<GlAccountCategory> createMockSingleFetchResponse() {
        List<GlAccountCategory> list = new ArrayList<GlAccountCategory>();
        GlAccountCategory p = AccountingMockDataUtility.createMockOrmGlAccountCategory(100, 1, "Category1");
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<GlAccountCategory> createMockFetchUsingCriteriaResponse() {
        List<GlAccountCategory> list = new ArrayList<GlAccountCategory>();
        GlAccountCategory p = AccountingMockDataUtility.createMockOrmGlAccountCategory(100, 1, "Category1");
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmGlAccountCategory(104, 1, "Category2");
        list.add(p);

        return list;
    }

    private List<GlAccountCategory> createMockFetchAllResponse() {
        List<GlAccountCategory> list = new ArrayList<GlAccountCategory>();
        GlAccountCategory p = AccountingMockDataUtility.createMockOrmGlAccountCategory(100, 1, "Category1");
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmGlAccountCategory(101, 2, "Category2");
        list.add(p);
        
        p = AccountingMockDataUtility.createMockOrmGlAccountCategory(102, 3, "Category3");
        list.add(p);
        
        p = AccountingMockDataUtility.createMockOrmGlAccountCategory(103, 4, "Category4");
        list.add(p);
        
        p = AccountingMockDataUtility.createMockOrmGlAccountCategory(104, 5, "Category5");
        list.add(p);
        return list;
    }

    private List<GlAccountCategory> createMockFetchUidMultiResultsReturnedResponse() {
        List<GlAccountCategory> list = new ArrayList<GlAccountCategory>();
        GlAccountCategory p = AccountingMockDataUtility.createMockOrmGlAccountCategory(100, 1, "Category1");
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmGlAccountCategory(400, 1, "Category2");
        list.add(p);
        return list;
    }
    
    
    @Test
    public void testFetchAll() {
        AccountCategoryDto criteria = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount Type fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountCategoryDto> results = null;
        try {
            results = api.getAccountCategory(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            AccountCategoryDto obj = results.get(ndx);
            Assert.assertEquals(obj.getAcctCatgId(), (100 + ndx));
            Assert.assertEquals(obj.getAcctTypeId(), (1 + ndx));
            Assert.assertEquals(obj.getAcctCatgDescription(), "Category" + (ndx + 1));
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
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount Type fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountCategoryDto> results = null;
        try {
            results = api.getAccountCategory(null);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }

    @Test
    public void testFetchSingle() {
        AccountCategoryDto criteria = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        criteria.setAcctCatgId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount Type fetch using criteria test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountCategoryDto> results = null;
        try {
            results = api.getAccountCategory(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        AccountCategoryDto dto = results.get(0);
        Assert.assertEquals(100, dto.getAcctCatgId());
        Assert.assertEquals(1, dto.getAcctTypeId());
        Assert.assertEquals("Category1", dto.getAcctCatgDescription());
    }

   
    @Test
    public void testFetchByUid() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount Type fetch by UID test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        AccountCategoryDto dto = null;
        try {
            dto = api.getAccountCategory(100);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(100, dto.getAcctCatgId());
        Assert.assertEquals(1, dto.getAcctTypeId());
        Assert.assertEquals("Category1", dto.getAcctCatgDescription());
    }
    
    @Test
    public void testFetchByUidMultipleItemsReturned() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockMultiResultsReturnedErrorResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Single GL Acccount Type fetch by UID test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getAccountCategory(100);
            Assert.fail("Expected exception to be thrown due multiple items returned");
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchUsingCriteria() {
        AccountCategoryDto criteria = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        criteria.setAcctCatgDescription("Cat");
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount fetch using criteria test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountCategoryDto> results = null;
        try {
            results = api.getAccountCategory(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (AccountCategoryDto dto : results) {
            Assert.assertTrue(dto.getAcctCatgId() >= 100 && dto.getAcctCatgId() <= 500);
        }
    }

    @Test
    public void testNotFoundlFetch() {
        AccountCategoryDto criteria = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        criteria.setAcctTypeId(999);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount Type not found fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountCategoryDto> results = null;
        try {
            results = api.getAccountCategory(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testUpdate() {
        AccountCategoryDto dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctCatgId(100);
        dto.setAcctTypeId(200);
        dto.setAcctCatgDescription("Category Modified");
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update Category, validate category setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveObject(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update Category, retreive delta category setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(GlAccountCategory.class)))
                    .thenReturn(1);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update GL Category test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCategory(dto);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
        Assert.assertEquals("Category Modified", dto.getAcctCatgDescription());
    }
    
    @Test
    public void testInsert() {
        AccountCategoryDto dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctCatgId(0);
        dto.setAcctTypeId(200);
        dto.setAcctCatgDescription("Category Added");
     
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account category mock setup failed");
        }
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(any(String.class)))
                    .thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getInt("next_seq")).thenReturn(554);   
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Acccount Category insert row test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(any(GlAccountCategory.class), any(Boolean.class)))
                    .thenReturn(555);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount Category insert row test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCategory(dto);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(555, rc);
        Assert.assertEquals(555, dto.getAcctCatgId());
        Assert.assertEquals("Category Added", dto.getAcctCatgDescription());
    }
    
    @Test
    public void testDelete() {
        try {
            when(this.mockPersistenceClient.deleteRow(any(Integer.class)))
                    .thenReturn(1);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Delete account category mock setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteCategory(100);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }
    
    @Test
    public void testUpdateWithNullCategoryObject() {
        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateCategory(null);
            Assert.fail("Expected exception to be thrown due to null category object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CannotProceedException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateInvalidCategoryId() {
        AccountCategoryDto dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctCatgId(100);
        dto.setAcctTypeId(200);
        dto.setAcctCatgDescription("Category Modified");
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update Category, validate category setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateCategory(dto);
            Assert.fail("Expected exception to be thrown due to category id does not exists");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CannotProceedException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateInvalidAccountTypeId() {
        AccountCategoryDto dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctCatgId(100);
        dto.setAcctTypeId(0);
        dto.setAcctCatgDescription("Category Modified");
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update Category, validate category setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateCategory(dto);
            Assert.fail("Expected exception to be thrown due to account type id is absent");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateInvalidCategoryName() {
        AccountCategoryDto dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctCatgId(100);
        dto.setAcctTypeId(200);
        dto.setAcctCatgDescription(null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update Category, validate category setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateCategory(dto);
            Assert.fail("Expected exception to be thrown due to category name is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        dto.setAcctCatgDescription("");
        try {
            api.updateCategory(dto);
            Assert.fail("Expected exception to be thrown due to category name is blank is blank");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertInvalidAccountTypeId() {
        AccountCategoryDto dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctCatgId(0);
        dto.setAcctTypeId(0);
        dto.setAcctCatgDescription("Category Modified");
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update Category, validate category setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateCategory(dto);
            Assert.fail("Expected exception to be thrown due to account type id is absent");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertInvalidCategoryName() {
        AccountCategoryDto dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctCatgId(0);
        dto.setAcctTypeId(200);
        dto.setAcctCatgDescription(null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update Category, validate category setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateCategory(dto);
            Assert.fail("Expected exception to be thrown due to category name is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        dto.setAcctCatgDescription("");
        try {
            api.updateCategory(dto);
            Assert.fail("Expected exception to be thrown due to category name is blank is blank");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchAllWithException() {
        AccountCategoryDto criteria = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                        .thenThrow(DatabaseException.class);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount Type fetch test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<AccountCategoryDto> results = null;
        try {
            results = api.getAccountCategory(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof GeneralLedgerApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertWithException() {
        AccountCategoryDto dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctCatgId(0);
        dto.setAcctTypeId(200);
        dto.setAcctCatgDescription("Category Added");
     
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert account category mock setup failed");
        }
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        try {
            when(this.mockPersistenceClient.executeSql(any(String.class)))
                    .thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getInt("next_seq")).thenReturn(554);   
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GL Acccount Category insert row test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(any(GlAccountCategory.class), any(Boolean.class)))
            .thenThrow(DatabaseException.class);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("GL Acccount Category insert row test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCategory(dto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof GeneralLedgerApiException);
            Assert.assertTrue(e.getCause() instanceof CannotPersistException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithException() {
        AccountCategoryDto dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctCatgId(100);
        dto.setAcctTypeId(200);
        dto.setAcctCatgDescription("Category Modified");
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update Category, validate category setup failed");
        }
        try {
            when(this.mockPersistenceClient.retrieveObject(any(GlAccountCategory.class)))
                    .thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update Category, retreive delta category setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(GlAccountCategory.class)))
            .thenThrow(DatabaseException.class);
        } catch (GeneralLedgerDaoException e) {
            e.printStackTrace();
            Assert.fail("Update GL Category test case setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCategory(dto);
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
            Assert.fail("Delete account category mock setup failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteCategory(100);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof GeneralLedgerApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRemoveException);
            e.printStackTrace();
        }
    }
}