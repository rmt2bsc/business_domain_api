package org.rmt2.api.inventory;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dao.mapping.orm.rmt2.VendorItems;
import org.dto.ItemMasterDto;
import org.dto.adapter.orm.inventory.Rmt2ItemMasterDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        // this.mockFetchAllResponse = this.createMockFetchAllResponse();
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

    private List<ItemMasterStatus> createMockSingleItemStatusFetchResponse(
            int statusId) {
        List<ItemMasterStatus> list = new ArrayList<ItemMasterStatus>();
        ItemMasterStatus p = AccountingMockDataUtility
                .createMockOrmItemMasterStatus(statusId,
                        "Item Status " + statusId);
        list.add(p);
        return list;
    }

    private List<ItemMasterStatusHist> createMockSingleItemStatusHistFetchResponse(
            int statusId) {
        List<ItemMasterStatusHist> list = new ArrayList<ItemMasterStatusHist>();
        ItemMasterStatusHist p = AccountingMockDataUtility
                .createMockOrmItemMasterStatusHistory(10, 100, statusId, 12.50,
                        3, "2107-01-01", null,
                        "Item Status History Description " + statusId);
        list.add(p);
        return list;
    }

    @Test
    public void testUpdateExistingWithAvailability() {
        try {
            ItemMaster im = new ItemMaster();
            im.setItemId(100);
            when(this.mockPersistenceClient.retrieveList(eq(im)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Fetch original Item Master for update test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(ItemMaster.class)))
                    .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Item master update row test case setup failed");
        }

        try {
            ItemMasterStatus ims = new ItemMasterStatus();
            ims.setItemStatusId(InventoryConst.ITEM_STATUS_INSRVC);
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_INSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(ims)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master update test case setup failed");
        }

        try {
            ItemMasterStatusHist mockItemMasterStatusHist = this
                    .createMockSingleItemStatusHistFetchResponse(100).get(0);
            when(this.mockPersistenceClient
                    .retrieveObject(any(ItemMasterStatusHist.class)))
                            .thenReturn(mockItemMasterStatusHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Item status history fetch test case setup failed");
        }
        try {
            Boolean insertInd = true;
            when(this.mockPersistenceClient
                    .updateRow(any(ItemMasterStatusHist.class))).thenReturn(1);
            when(this.mockPersistenceClient
                    .insertRow(any(ItemMasterStatusHist.class), eq(insertInd)))
                            .thenReturn(222);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Item master status history update/insert row test case setup failed");
        }

        ItemMaster im = AccountingMockDataUtility.createMockOrmItemMaster(100,
                1, "111-111-111", "11111111", 1234, "Modified Item #1", 5, 1.23,
                true);
        ItemMasterDto dto = Rmt2ItemMasterDtoFactory
                .createItemMasterInstance(im);

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateItemMaster(dto);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
        Assert.assertEquals("Modified Item #1", dto.getItemName());
        Assert.assertEquals("Modified Item #1", dto.getEntityName());
    }

    @Test
    public void testUpdateNewWithAvailability() {
        try {
            ItemMasterStatus ims = new ItemMasterStatus();
            ims.setItemStatusId(InventoryConst.ITEM_STATUS_INSRVC);
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_INSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(ims)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master update test case setup failed");
        }

        try {
            ItemMasterStatusHist mockItemMasterStatusHist = this
                    .createMockSingleItemStatusHistFetchResponse(100).get(0);
            when(this.mockPersistenceClient
                    .retrieveObject(any(ItemMasterStatusHist.class)))
                            .thenReturn(null);
            when(this.mockPersistenceClient
                    .retrieveObject(any(ItemMasterStatusHist.class)))
                            .thenReturn(mockItemMasterStatusHist);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Item status history fetch test case setup failed");
        }
        try {
            Boolean insertInd = true;
            when(this.mockPersistenceClient
                    .updateRow(any(ItemMasterStatusHist.class))).thenReturn(1);
            when(this.mockPersistenceClient
                    .insertRow(any(ItemMasterStatusHist.class), any(Boolean.class)))
                            .thenReturn(333);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Item master status history update/insert row test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(any(ItemMaster.class),
                    any(Boolean.class))).thenReturn(555);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Item master insert row test case setup failed");
        }

        ItemMaster im = AccountingMockDataUtility.createMockOrmItemMaster(0, 1,
                "111-111-111", "11111111", 1234, "Item #1", 5, 1.23, true);
        ItemMasterDto dto = Rmt2ItemMasterDtoFactory
                .createItemMasterInstance(im);

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateItemMaster(dto);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(555, rc);
        Assert.assertEquals("Item #1", dto.getItemName());
        Assert.assertEquals("Item #1", dto.getEntityName());
    }
    
    @Test
    public void testActivateItemMaster() {
        ItemMaster im = new ItemMaster();
        im.setItemId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(im)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Fetch original Item Master for activation test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(eq(im)))
                    .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Item master update row test case setup failed");
        }

        try {
            ItemMasterStatus ims = new ItemMasterStatus();
            ims.setItemStatusId(InventoryConst.ITEM_STATUS_INSRVC);
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_INSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(ims)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master activation test case setup failed");
        }
        
        ItemMasterStatusHist mockItemMasterStatusHist = this
                .createMockSingleItemStatusHistFetchResponse(100).get(0);
        try {
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_INSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(mockItemMasterStatusHist)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master activation test case setup failed");
        }
        try {
            Boolean insertInd = true;
            when(this.mockPersistenceClient
                    .updateRow(any(ItemMasterStatusHist.class))).thenReturn(1);
            when(this.mockPersistenceClient
                    .insertRow(any(ItemMasterStatusHist.class), eq(insertInd)))
                            .thenReturn(222);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Item master status history update/insert row test case setup failed");
        }
        ItemMasterDto dto = Rmt2ItemMasterDtoFactory
                .createItemMasterInstance(im);

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.activateItemMaster(100);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }
    
    @Test
    public void testActivateItemMasterWithNullItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.activateItemMaster(null);
            Assert.fail("Expected exception to be thrown due to null item id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testActivateItemMasterWithZeroValueItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.activateItemMaster(0);
            Assert.fail("Expected exception to be thrown due to item master id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testActivateItemMasterWithNegativeItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.activateItemMaster(0);
            Assert.fail("Expected exception to be thrown due to item master id is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testActivateItemMasterWithInvalidItemMasterId() {
        ItemMaster im = new ItemMaster();
        im.setItemId(222);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(im)))
                    .thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Fetch original Item Master for activation test case setup failed");
        }
    
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.activateItemMaster(222);
            Assert.fail("Expected exception to be thrown due to item master is not found");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InventoryApiException);
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testAddInventoryOverrideSIngleItem() {
        ItemMaster im = new ItemMaster();
        im.setItemId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(im)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Fetch original Item Master for add inventory override test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(eq(im)))
                    .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Item master update row test case setup failed");
        }

        try {
            ItemMasterStatus ims = new ItemMasterStatus();
            ims.setItemStatusId(InventoryConst.ITEM_STATUS_INSRVC);
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_INSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(ims)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master activation test case setup failed");
        }
        
        ItemMasterStatusHist mockItemMasterStatusHist = this
                .createMockSingleItemStatusHistFetchResponse(100).get(0);
        try {
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_INSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(mockItemMasterStatusHist)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master activation test case setup failed");
        }
        try {
            Boolean insertInd = true;
            when(this.mockPersistenceClient
                    .updateRow(any(ItemMasterStatusHist.class))).thenReturn(1);
            when(this.mockPersistenceClient
                    .insertRow(any(ItemMasterStatusHist.class), eq(insertInd)))
                            .thenReturn(222);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Item master status history update/insert row test case setup failed");
        }
        ItemMasterDto dto = Rmt2ItemMasterDtoFactory
                .createItemMasterInstance(im);

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        int rc = 0;
        Integer items[] = {100};
        try {
            rc = api.addInventoryOverride(555, items);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }
    
    
    @Test
    public void testAddInventoryOverrideMultipleItems() {
        ItemMaster item2 = AccountingMockDataUtility.createMockOrmItemMaster(200, 1,
                "222-111-111", "2222222", 1234, "Item # 2", 5, 1.23, true);
        List<ItemMaster> mockSingleFetchResponse2 = new ArrayList<ItemMaster>();
        mockSingleFetchResponse2.add(item2);
        ItemMaster item3 = AccountingMockDataUtility.createMockOrmItemMaster(300, 1,
                "333-111-111", "3333333", 1234, "Item # 3", 5, 1.23, true);
        List<ItemMaster> mockSingleFetchResponse3 = new ArrayList<ItemMaster>();
        mockSingleFetchResponse3.add(item3);
        ItemMaster im = new ItemMaster();
        im.setItemId(100);
        ItemMaster im2 = new ItemMaster();
        im2.setItemId(200);
        ItemMaster im3 = new ItemMaster();
        im3.setItemId(300);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(im)))
                    .thenReturn(this.mockSingleFetchResponse);
            when(this.mockPersistenceClient.retrieveList(eq(im2)))
            .thenReturn(mockSingleFetchResponse2);
            when(this.mockPersistenceClient.retrieveList(eq(im3)))
            .thenReturn(mockSingleFetchResponse3);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Fetch original Item Master for add inventory override test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(eq(im)))
                    .thenReturn(1);
            when(this.mockPersistenceClient.updateRow(eq(im2)))
            .thenReturn(1);
            when(this.mockPersistenceClient.updateRow(eq(im3)))
            .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Item master update row test case setup failed");
        }

        try {
            ItemMasterStatus ims = new ItemMasterStatus();
            ims.setItemStatusId(InventoryConst.ITEM_STATUS_INSRVC);
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_INSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(ims)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master activation test case setup failed");
        }
        
        ItemMasterStatusHist mockItemMasterStatusHist = this
                .createMockSingleItemStatusHistFetchResponse(100).get(0);
        try {
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_INSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(mockItemMasterStatusHist)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master activation test case setup failed");
        }
        try {
            Boolean insertInd = true;
            when(this.mockPersistenceClient
                    .updateRow(any(ItemMasterStatusHist.class))).thenReturn(1);
            when(this.mockPersistenceClient
                    .insertRow(any(ItemMasterStatusHist.class), eq(insertInd)))
                            .thenReturn(222);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Item master status history update/insert row test case setup failed");
        }
        ItemMasterDto dto = Rmt2ItemMasterDtoFactory
                .createItemMasterInstance(im);

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        int rc = 0;
        Integer items[] = {100, 200, 300};
        try {
            rc = api.addInventoryOverride(555, items);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(3, rc);
    }
    
    @Test
    public void testAddInventoryOverrideWithNullVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, 200, 300};
        try {
            api.addInventoryOverride(null, items);
            Assert.fail("Expected exception due to vendor id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAddInventoryOverrideWithZeroValueVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, 200, 300};
        try {
            api.addInventoryOverride(0, items);
            Assert.fail("Expected exception due to vendor id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAddInventoryOverrideWithNegativeVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, 200, 300};
        try {
            api.addInventoryOverride(-1, items);
            Assert.fail("Expected exception due to vendor id is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAddInventoryOverrideWithNullItemList() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.addInventoryOverride(0, null);
            Assert.fail("Expected exception due to item list is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAddInventoryOverrideWithItemListElementIsNull() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, null, 300};
        try {
            api.addInventoryOverride(0, items);
            Assert.fail("Expected exception due to item list contains a null item id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAddInventoryOverrideWithItemListElementIsZero() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, 200, 0};
        try {
            api.addInventoryOverride(0, items);
            Assert.fail("Expected exception due to item list contains an item id with value of zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAddInventoryOverrideWithItemListElementIsNegative() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {-100, 200, 300};
        try {
            api.addInventoryOverride(0, items);
            Assert.fail("Expected exception due to item list contains an item id with a negative value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAssignVendorItems() {
        ItemMaster item2 = AccountingMockDataUtility.createMockOrmItemMaster(200, 1,
                "222-111-111", "2222222", 1234, "Item # 2", 5, 1.23, true);
        List<ItemMaster> mockSingleFetchResponse2 = new ArrayList<ItemMaster>();
        mockSingleFetchResponse2.add(item2);
        ItemMaster item3 = AccountingMockDataUtility.createMockOrmItemMaster(300, 1,
                "333-111-111", "3333333", 1234, "Item # 3", 5, 1.23, true);
        List<ItemMaster> mockSingleFetchResponse3 = new ArrayList<ItemMaster>();
        mockSingleFetchResponse3.add(item3);
        ItemMaster im = new ItemMaster();
        im.setItemId(100);
        ItemMaster im2 = new ItemMaster();
        im2.setItemId(200);
        ItemMaster im3 = new ItemMaster();
        im3.setItemId(300);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(im)))
                    .thenReturn(this.mockSingleFetchResponse);
            when(this.mockPersistenceClient.retrieveList(eq(im2)))
            .thenReturn(mockSingleFetchResponse2);
            when(this.mockPersistenceClient.retrieveList(eq(im3)))
            .thenReturn(mockSingleFetchResponse3);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Fetch original Item Master for assign vendor items test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(VendorItems.class)))
                    .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Vendor Item update row test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        int rc = 0;
        Integer items[] = {100, 200, 300};
        try {
            rc = api.assignVendorItems(555, items);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(3, rc);
    }
    
    
    
    
    
    
    @Test
    public void testAssignVendorItemsWithNullVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, 200, 300};
        try {
            api.assignVendorItems(null, items);
            Assert.fail("Expected exception due to vendor id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAssignVendorItemsWithZeroValueVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, 200, 300};
        try {
            api.assignVendorItems(0, items);
            Assert.fail("Expected exception due to vendor id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAssignVendorItemsWithNegativeVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, 200, 300};
        try {
            api.assignVendorItems(-1, items);
            Assert.fail("Expected exception due to vendor id is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAssignVendorItemsWithNullItemList() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.assignVendorItems(0, null);
            Assert.fail("Expected exception due to item list is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAssignVendorItemsWithItemListElementIsNull() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, null, 300};
        try {
            api.assignVendorItems(0, items);
            Assert.fail("Expected exception due to item list contains a null item id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAssignVendorItemsWithItemListElementIsZero() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {100, 200, 0};
        try {
            api.assignVendorItems(0, items);
            Assert.fail("Expected exception due to item list contains an item id with value of zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAssignVendorItemsWithItemListElementIsNegative() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        Integer items[] = {-100, 200, 300};
        try {
            api.assignVendorItems(0, items);
            Assert.fail("Expected exception due to item list contains an item id with a negative value");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDeactivateItemMaster() {
        ItemMaster im = new ItemMaster();
        im.setItemId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(im)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Fetch original Item Master for activation test case setup failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(eq(im)))
                    .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Item master update row test case setup failed");
        }

        try {
            ItemMasterStatus ims = new ItemMasterStatus();
            ims.setItemStatusId(InventoryConst.ITEM_STATUS_OUTSRVC);
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_OUTSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(ims)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master activation test case setup failed");
        }
        
        ItemMasterStatusHist mockItemMasterStatusHist = this
                .createMockSingleItemStatusHistFetchResponse(100).get(0);
        try {
            List<ItemMasterStatus> mockItemStatusHistResp = this
                    .createMockSingleItemStatusFetchResponse(
                            InventoryConst.ITEM_STATUS_INSRVC);
            when(this.mockPersistenceClient.retrieveList(eq(mockItemMasterStatusHist)))
                    .thenReturn(mockItemStatusHistResp);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Verify item status history fetch for Item Master activation test case setup failed");
        }
        try {
            Boolean insertInd = true;
            when(this.mockPersistenceClient
                    .updateRow(any(ItemMasterStatusHist.class))).thenReturn(1);
            when(this.mockPersistenceClient
                    .insertRow(any(ItemMasterStatusHist.class), eq(insertInd)))
                            .thenReturn(222);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Item master status history update/insert row test case setup failed");
        }
        ItemMasterDto dto = Rmt2ItemMasterDtoFactory
                .createItemMasterInstance(im);

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.deactivateItemMaster(100);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }
    
    @Test
    public void testDeactivateItemMasterWithNullItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.deactivateItemMaster(null);
            Assert.fail("Expected exception to be thrown due to null item id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testDeactivateItemMasterWithZeroValueItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.deactivateItemMaster(0);
            Assert.fail("Expected exception to be thrown due to item master id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDeactivateItemMasterWithNegativeItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.deactivateItemMaster(0);
            Assert.fail("Expected exception to be thrown due to item master id is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDeactivateItemMasterWithInvalidItemMasterId() {
        ItemMaster im = new ItemMaster();
        im.setItemId(222);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(im)))
                    .thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Fetch original Item Master for activation test case setup failed");
        }
    
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        try {
            api.deactivateItemMaster(222);
            Assert.fail("Expected exception to be thrown due to item master is not found");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InventoryApiException);
            e.printStackTrace();
        }
    }
}