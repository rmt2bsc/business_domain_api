package org.rmt2.api.inventory;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.generalledger.GeneralLedgerDaoException;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dto.AccountDto;
import org.dto.ItemMasterDto;
import org.dto.adapter.orm.inventory.Rmt2ItemMasterDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.generalledger.GeneralLedgerApiException;
import org.modules.generalledger.GeneralLedgerApiFactory;
import org.modules.generalledger.GlAccountApi;
import org.modules.inventory.InventoryApi;
import org.modules.inventory.InventoryApiException;
import org.modules.inventory.InventoryApiFactory;
import org.modules.inventory.InventoryConst;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseAccountingDaoTest;
import org.rmt2.dao.AccountingMockDataUtility;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Item Master entity query use cases belonging to the Inventory Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class ItemMasterApiUpdateTest extends BaseAccountingDaoTest {
    private List<ItemMaster> mockSingleFetchResponse;
    private List<ItemMaster> mockCriteriaFetchResponse;
    List<ItemMasterStatusHist> mockFetchItemStatusAllResponse;
    private List<ItemMaster> mockNotFoundFetchResponse;

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
//        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this
                .createMockNotFoundSearchResultsResponse();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<ItemMaster> createMockNotFoundSearchResultsResponse() {
        List<ItemMaster> list = null;
        return list;
    }

    private List<ItemMaster> createMockSingleFetchResponse() {
        List<ItemMaster> list = new ArrayList<ItemMaster>();
        ItemMaster p = AccountingMockDataUtility.createMockOrmItemMaster(100, 1,
                "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23, true);
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<ItemMaster> createMockFetchUsingCriteriaResponse() {
        List<ItemMaster> list = new ArrayList<ItemMaster>();
        ItemMaster p = AccountingMockDataUtility.createMockOrmItemMaster(100, 1,
                "444-111-111", "1234567", 4567, "Item # 10", 5, 1.23, true);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmItemMaster(102, 1,
                "555-111-111", "3232333", 5432, "Item # 12", 15, 5, true);
        list.add(p);

        return list;
    }

    private List<ItemMaster> createMockFetchAllResponse() {
        List<ItemMaster> list = new ArrayList<ItemMaster>();
        ItemMaster p = AccountingMockDataUtility.createMockOrmItemMaster(100, 1,
                "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23, true);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmItemMaster(102, 1,
                "102-111-111", "10211111", 4321, "Item # 2", 15, 5, true);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmItemMaster(102, 1,
                "200-111-111", "20011111", 5555, "Item # 3", 115, 35.80, true);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmItemMaster(102, 1,
                "300-111-111", "30011111", 1234, "Item # 4", 300, 0.99, true);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmItemMaster(102, 1,
                "400-111-111", "40011111", 6543, "Item # 5", 1000, 21.99, true);
        list.add(p);
        return list;
    }

    private List<ItemMasterStatus> createMockSingleItemStatusFetchResponse(int statusId) {
        List<ItemMasterStatus> list = new ArrayList<ItemMasterStatus>();
        ItemMasterStatus p = AccountingMockDataUtility
                .createMockOrmItemMasterStatus(statusId, "Item Status " + statusId);
        list.add(p);
        return list;
    }
    
    private List<ItemMasterStatusHist> createMockSingleItemStatusHistFetchResponse(int statusId) {
        List<ItemMasterStatusHist> list = new ArrayList<ItemMasterStatusHist>();
        ItemMasterStatusHist p = AccountingMockDataUtility
                .createMockOrmItemMasterStatusHistory(10, 100, statusId, 12.50, 3,
                        "2107-01-01", "2017-02-15",
                        "Item Status History Description " + statusId);
        list.add(p);
        return list;
    }
    
    
//    @Test
//    public void testFetchAll() {
//        try {
//            when(this.mockPersistenceClient
//                    .retrieveList(any(ItemMaster.class)))
//                            .thenReturn(this.mockFetchAllResponse);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail("All Inventory Item Master fetch test case setup failed");
//        }
//
//        InventoryApiFactory f = new InventoryApiFactory();
//        InventoryApi api = f.createApi(APP_NAME);
//        List<ItemMasterDto> results = null;
//        String criteria = null;
//        try {
//            results = api.getItem(criteria);
//        } catch (InventoryApiException e) {
//            e.printStackTrace();
//        }
//        Assert.assertNotNull(results);
//        Assert.assertEquals(5, results.size());
//        
//        criteria = "item_id = 100";
//        try {
//            results = api.getItem(criteria);
//        } catch (InventoryApiException e) {
//            e.printStackTrace();
//        }
//        Assert.assertNotNull(results);
//        Assert.assertEquals(5, results.size());
//        
//        ItemMasterDto criteriaObj = null;
//        try {
//            results = api.getItem(criteriaObj);
//        } catch (InventoryApiException e) {
//            e.printStackTrace();
//        }
//        Assert.assertNotNull(results);
//        Assert.assertEquals(5, results.size());
//    }

    @Test
    public void testFetchSingle() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        ItemMasterDto dto = null;
        try {
            dto = api.getItemById(100);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
        
        ItemMaster im = null;
        ItemMasterDto criteria = Rmt2ItemMasterDtoFactory.createItemMasterInstance(im);
        criteria.setItemId(100);
        List <ItemMasterDto> results = null;
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1,  results.size());
        dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
    }

    @Test
    public void testFetchSingleWithInvalidItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.getItemById(null);
            Assert.fail("Expected exception to be thrown due to null item id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        try {
            api.getItemById(0);
            Assert.fail("Expected exception to be thrown due to item id equal zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        try {
            api.getItemById(-100);
            Assert.fail("Expected exception to be thrown due to item id cannot be less than or equal to zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchBySerialNo() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        List<ItemMasterDto> results = null;
        try {
            results = api.getItemBySerialNo("111-111-111");
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ItemMasterDto dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
        
        ItemMaster im = null;
        ItemMasterDto criteria = Rmt2ItemMasterDtoFactory.createItemMasterInstance(im);
        criteria.setItemSerialNo("111-111-111");
        results = null;
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1,  results.size());
        dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
    }

    @Test
    public void testFetchByInvalidSerialNo() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.getItemBySerialNo(null);
            Assert.fail("Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        try {
            api.getItemBySerialNo("");
            Assert.fail("Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchByType() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        List<ItemMasterDto> results = null;
        try {
            results = api.getItemByType(1);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ItemMasterDto dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
        
        ItemMaster im = null;
        ItemMasterDto criteria = Rmt2ItemMasterDtoFactory.createItemMasterInstance(im);
        criteria.setItemTypeId(1);
        results = null;
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1,  results.size());
        dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
    }

    @Test
    public void testFetchByInvalidType() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.getItemByType(null);
            Assert.fail("Expected exception to be thrown due to null item type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        try {
            api.getItemByType(0);
            Assert.fail("Expected exception to be thrown due to null item type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        try {
            api.getItemByType(-100);
            Assert.fail("Expected exception to be thrown due to null item type id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchByVendorId() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        List<ItemMasterDto> results = null;
        try {
            results = api.getItemByVendorId(1234);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ItemMasterDto dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
        
        ItemMaster im = null;
        ItemMasterDto criteria = Rmt2ItemMasterDtoFactory.createItemMasterInstance(im);
        criteria.setVendorId(1234);
        results = null;
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1,  results.size());
        dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
    }

    @Test
    public void testFetchByInvalidVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.getItemByVendorId(null);
            Assert.fail("Expected exception to be thrown due to null vendor id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        try {
            api.getItemByVendorId(0);
            Assert.fail("Expected exception to be thrown due to null vendor id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        try {
            api.getItemByVendorId(-100);
            Assert.fail("Expected exception to be thrown due to null vendor id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testFetchByVendorItemNo() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        List<ItemMasterDto> results = null;
        try {
            results = api.getItemByVendorItemNo("11111111");
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ItemMasterDto dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
        
        ItemMaster im = null;
        ItemMasterDto criteria = Rmt2ItemMasterDtoFactory.createItemMasterInstance(im);
        criteria.setVendorItemNo("11111111");
        results = null;
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1,  results.size());
        dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("111-111-111", dto.getItemSerialNo());
        Assert.assertEquals("11111111", dto.getVendorItemNo());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
    }

    @Test
    public void testFetchByInvalidVendorItemNo() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.getItemByVendorItemNo(null);
            Assert.fail("Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        try {
            api.getItemByVendorItemNo("");
            Assert.fail("Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchVendorUnassignedItems() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockCriteriaFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Vendor Unassinged Items fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        List<ItemMasterDto> results = null;
        try {
            results = api.getVendorUnassignItems(1234);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (ItemMasterDto dto : results) {
            Assert.assertNotEquals(1234, dto.getVendorId());
        }
   }
    
    @Test
    public void testFetchVendorUnassignedItemsNotFound() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Vendor unassinged items not found fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        List<ItemMasterDto> results = null;
        try {
            results = api.getVendorUnassignItems(1234);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
   }
    
    @Test
    public void testFetchVendorUnassignedItemsWithNullVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.getVendorUnassignItems(null);
            Assert.fail("Expected exception to be thrown due to null vendor id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
   }
    
    @Test
    public void testUpdate() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch original Item Master for update test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(ItemMaster.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Item master update row test case setup failed");
        }
        
        ItemMasterStatus im = new ItemMasterStatus();
        im.setItemStatusId(InventoryConst.ITEM_STATUS_INSRVC);
        List<ItemMasterStatus> mockItemStatusHistResp = this.createMockSingleItemStatusFetchResponse(InventoryConst.ITEM_STATUS_INSRVC);
        
        try {
            when(this.mockPersistenceClient.retrieveList(eq(im)))
                            .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Verify item status fetch for Item Master update test case setup failed");
        }
        
        GlAccounts p = AccountingMockDataUtility.createMockOrmGlAccounts(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        AccountDto dto = AccountingMockDataUtility.createMockDtoGlAccounts(100, 200, 300, 1, "GL_100",
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
    
}