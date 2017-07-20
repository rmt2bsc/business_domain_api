package org.rmt2.api.inventory;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dto.ItemMasterTypeDto;
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
import org.rmt2.api.BaseAccountingDaoTest;
import org.rmt2.dao.AccountingMockDataUtility;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Item Type entity query use cases belonging to the Inventory Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class ItemMasterTypeApiQueryTest extends BaseAccountingDaoTest {
    private List<ItemMasterType> mockSingleFetchResponse;
    private List<ItemMasterType> mockCriteriaFetchResponse;
    private List<ItemMasterType> mockFetchAllResponse;
    private List<ItemMasterType> mockNotFoundFetchResponse;

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

    private List<ItemMasterType> createMockNotFoundSearchResultsResponse() {
        List<ItemMasterType> list = null;
        return list;
    }

    private List<ItemMasterType> createMockSingleFetchResponse() {
        List<ItemMasterType> list = new ArrayList<ItemMasterType>();
        ItemMasterType p = AccountingMockDataUtility
                .createMockOrmItemMasterType(100, "Item Type #1");
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<ItemMasterType> createMockFetchUsingCriteriaResponse() {
        List<ItemMasterType> list = new ArrayList<ItemMasterType>();
        ItemMasterType p = AccountingMockDataUtility
                .createMockOrmItemMasterType(100, "Item Type #1");
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmItemMasterType(200,
                "Item Type #2");
        list.add(p);

        return list;
    }

    private List<ItemMasterType> createMockFetchAllResponse() {
        List<ItemMasterType> list = new ArrayList<ItemMasterType>();
        ItemMasterType p = AccountingMockDataUtility
                .createMockOrmItemMasterType(100, "Item Type #1");
        list.add(p);

        p = AccountingMockDataUtility
                .createMockOrmItemMasterType(200, "Item Type #2");
        list.add(p);

        p = AccountingMockDataUtility
                .createMockOrmItemMasterType(300, "Item Type #3");
        list.add(p);

        p = AccountingMockDataUtility
                .createMockOrmItemMasterType(400, "Item Type #4");
        list.add(p);

        p = AccountingMockDataUtility
                .createMockOrmItemMasterType(500, "Item Type #5");
        list.add(p);
        return list;
    }

    @Test
    public void testFetchAll() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterType.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "All Inventory Item Master Type fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterTypeDto> results = null;
        ItemMasterTypeDto criteriaObj = null;
        try {
            results = api.getItemType(criteriaObj);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }

    @Test
    public void testFetchAllNotFound() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterType.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "All Inventory Item Master Type not found fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterTypeDto> results = null;
        ItemMasterTypeDto criteriaObj = null;
        try {
            results = api.getItemType(criteriaObj);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchSingle() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterType.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master Type fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        ItemMasterTypeDto dto = null;
        try {
            dto = api.getItemTypeById(100);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(100, dto.getItemTypeId());
        Assert.assertEquals("Item Type #1", dto.getItemTypeDescription());

        ItemMasterType im = null;
        ItemMasterTypeDto criteria = Rmt2ItemMasterDtoFactory
                .createItemTypeInstance(im);
        criteria.setItemTypeId(100);
        List<ItemMasterTypeDto> results = null;
        try {
            results = api.getItemType(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        dto = results.get(0);
        Assert.assertEquals(100, dto.getItemTypeId());
        Assert.assertEquals("Item Type #1", dto.getItemTypeDescription());
    }

    @Test
    public void testFetchSingleNotFound() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterType.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master Type not found fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        ItemMasterTypeDto dto = null;
        try {
            dto = api.getItemTypeById(570);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(dto);

        ItemMasterType im = null;
        ItemMasterTypeDto criteria = Rmt2ItemMasterDtoFactory
                .createItemTypeInstance(im);
        criteria.setItemTypeId(100);
        List<ItemMasterTypeDto> results = null;
        try {
            results = api.getItemType(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    
    @Test
    public void testFetchByDescription() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterType.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master Type fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterTypeDto> results = null;
        try {
            results = api.getItemTypes("Item");
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        ItemMasterTypeDto dto = results.get(0);
        Assert.assertTrue(dto.getItemTypeId() >= 100 && dto.getItemTypeId() <= 200);
        Assert.assertTrue(dto.getItemTypeDescription().contains("Item Type"));

        ItemMasterType im = null;
        ItemMasterTypeDto criteria = Rmt2ItemMasterDtoFactory
                .createItemTypeInstance(im);
        criteria.setItemTypeDescription("Item");
        results = null;
        try {
            results = api.getItemType(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        dto = results.get(0);
        Assert.assertTrue(dto.getItemTypeId() >= 100 && dto.getItemTypeId() <= 200);
        Assert.assertTrue(dto.getItemTypeDescription().contains("Item Type"));
    }

    @Test
    public void testFetchByDescriptionNotFound() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterType.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master Type not found fetch  test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterTypeDto> results = null;
        try {
            results = api.getItemTypes("Item Not Exists");
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
        
        ItemMasterType im = null;
        ItemMasterTypeDto criteria = Rmt2ItemMasterDtoFactory
                .createItemTypeInstance(im);
        criteria.setItemTypeDescription("Item Not Exists");
        results = null;
        try {
            results = api.getItemType(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    
    @Test
    public void testFetchWithInvalidDescription() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getItemTypes(null);
            Assert.fail(
                    "Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }

        try {
            api.getItemTypes("");
            Assert.fail(
                    "Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

  
    @Test
    public void testFetchByInvalidItemTypeId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        
        try {
            api.getItemTypeById(null);
            Assert.fail(
                    "Expected exception to be thrown due item type id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        
        try {
            api.getItemTypeById(0);
            Assert.fail(
                    "Expected exception to be thrown due item type id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }

        try {
            api.getItemTypeById(-100);
            Assert.fail(
                    "Expected exception to be thrown due item type id is less than zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

}