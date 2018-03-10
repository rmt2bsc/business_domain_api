package org.rmt2.api.inventory;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dto.ItemMasterDto;
import org.dto.adapter.orm.inventory.Rmt2ItemMasterDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.AddressBookConstants;
import org.modules.inventory.InventoryApi;
import org.modules.inventory.InventoryApiException;
import org.modules.inventory.InventoryApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.AccountingMockDataFactory;
import org.rmt2.api.BaseAccountingDaoTest;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
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
public class ItemMasterApiQueryTest extends BaseAccountingDaoTest {
    private List<ItemMaster> mockSingleFetchResponse;
    private List<ItemMaster> mockCriteriaFetchResponse;
    private List<ItemMaster> mockFetchAllResponse;
    private List<ItemMaster> mockNotFoundFetchResponse;

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
        ItemMaster p = AccountingMockDataFactory.createMockOrmItemMaster(100, 1,
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
        ItemMaster p = AccountingMockDataFactory.createMockOrmItemMaster(100, 1,
                "444-111-111", "1234567", 4567, "Item # 10", 5, 1.23, true);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMaster(102, 1,
                "555-111-111", "3232333", 5432, "Item # 12", 15, 5, true);
        list.add(p);

        return list;
    }

    private List<ItemMaster> createMockFetchAllResponse() {
        List<ItemMaster> list = new ArrayList<ItemMaster>();
        ItemMaster p = AccountingMockDataFactory.createMockOrmItemMaster(100, 1,
                "100-111-111", "11111110", 1351, "Item # 1", 1, 1.23, true);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMaster(101, 1,
                "101-111-111", "11111111", 1352, "Item # 2", 2, 1.23, true);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMaster(102, 1,
                "102-111-111", "11111112", 1353, "Item # 3", 3, 1.23, true);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMaster(103, 1,
                "103-111-111", "11111113", 1354, "Item # 4", 4, 1.23, true);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMaster(104, 1,
                "104-111-111", "11111114", 1355, "Item # 5", 5, 1.23, true);
        list.add(p);
        return list;
    }

    @Test
    public void testFetchAllWithCriteriaObject() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Inventory Item Master fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterDto> results = null;
        ItemMaster im = null;
        ItemMasterDto criteria = Rmt2ItemMasterDtoFactory.createItemMasterInstance(im);
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ItemMasterDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEntityId(), (100 + ndx));
            Assert.assertEquals(obj.getItemId(), (100 + ndx));
            Assert.assertEquals(obj.getVendorId(), (1351 + ndx));
            Assert.assertEquals(obj.getItemSerialNo(), (100 + ndx) + "-111-111");
            Assert.assertEquals(obj.getVendorItemNo(), "1111111" + ndx);
            Assert.assertEquals(obj.getItemName(), "Item # " + (ndx + 1));
            Assert.assertEquals(obj.getEntityName(), "Item # " + (ndx + 1));
            Assert.assertEquals(obj.getQtyOnHand(), (1 + ndx));
            Assert.assertEquals(obj.getRetailPrice(), (obj.getQtyOnHand() * obj.getUnitCost()) * obj.getMarkup(), 0);
        }
    }

    @Test
    public void testFetchAllWithStringCriteria() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Inventory Item Master fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterDto> results = null;
        String criteria = null;
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ItemMasterDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEntityId(), (100 + ndx));
            Assert.assertEquals(obj.getItemId(), (100 + ndx));
            Assert.assertEquals(obj.getVendorId(), (1351 + ndx));
            Assert.assertEquals(obj.getItemSerialNo(), (100 + ndx) + "-111-111");
            Assert.assertEquals(obj.getVendorItemNo(), "1111111" + ndx);
            Assert.assertEquals(obj.getItemName(), "Item # " + (ndx + 1));
            Assert.assertEquals(obj.getEntityName(), "Item # " + (ndx + 1));
            Assert.assertEquals(obj.getQtyOnHand(), (1 + ndx));
            Assert.assertEquals(obj.getRetailPrice(), (obj.getQtyOnHand() * obj.getUnitCost()) * obj.getMarkup(), 0);
        }
        
        criteria = "item_id = 100";
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }
    
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getVendorUnassignItems(null);
            Assert.fail("Expected exception to be thrown due to null vendor id");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
   }
    
    @Test
    public void testFetchAllCriteriaObjectWithException() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                         .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Inventory Item Master fetch with exception test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterDto> results = null;
        ItemMaster im = null;
        ItemMasterDto criteria = Rmt2ItemMasterDtoFactory.createItemMasterInstance(im);
        try {
            results = api.getItem(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InventoryApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchAllStringArgWithException() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                         .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Inventory Item Master fetch with exception test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterDto> results = null;
        String criteria = null;
        try {
            results = api.getItem(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InventoryApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
}