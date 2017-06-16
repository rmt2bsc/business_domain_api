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
import org.modules.inventory.InventoryApi;
import org.modules.inventory.InventoryApiException;
import org.modules.inventory.InventoryApiFactory;
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
        APP_NAME = "accounting";
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
                "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23, true);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmItemMaster(102, 1,
                "102-111-111", "10211111", 1234, "Item # 2", 15, 5, true);
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

    @Test
    public void testFetchAll() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMaster.class)))
                            .thenReturn(this.mockFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All Inventory Item Master fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(APP_NAME);
        List<ItemMasterDto> results = null;
        String criteria = null;
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        
        criteria = "item_id = 100";
        try {
            results = api.getItem(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        
        ItemMasterDto criteriaObj = null;
        try {
            results = api.getItem(criteriaObj);
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
            api.getItemByType(0);
            Assert.fail("Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        try {
            api.getItemByType(-100);
            Assert.fail("Expected exception to be thrown due to null serial number");
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
            api.getItemByVendorId(0);
            Assert.fail("Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        try {
            api.getItemByVendorId(-100);
            Assert.fail("Expected exception to be thrown due to null serial number");
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
}