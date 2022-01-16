package org.rmt2.api.inventory;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dao.mapping.orm.rmt2.VwItemStatusHistory;
import org.dto.ItemMasterStatusHistDto;
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
 * Tests Item Status History entity query use cases belonging to the Inventory
 * Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class ItemStatusHistoryApiQueryTest extends BaseAccountingDaoTest {
    private List<VwItemStatusHistory> mockSingleFetchResponse;
    private List<VwItemStatusHistory> mockCriteriaFetchResponse;
    private List<VwItemStatusHistory> mockFetchAllResponse;
    private List<ItemMasterStatusHist> mockNotFoundFetchResponse;

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
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<ItemMasterStatusHist> createMockNotFoundSearchResultsResponse() {
        List<ItemMasterStatusHist> list = null;
        return list;
    }

    private List<VwItemStatusHistory> createMockSingleFetchResponse() {
        List<VwItemStatusHistory> list = new ArrayList<>();
        VwItemStatusHistory p = AccountingMockDataFactory
                .createMockOrmItemMasterStatusHistory(10, 100, 1000, 12.50, 3,
                        "2107-01-01", "2017-02-15",
                        "Item Status History Description 1", "Status Name");
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<VwItemStatusHistory> createMockFetchUsingCriteriaResponse() {
        List<VwItemStatusHistory> list = new ArrayList<>();
        VwItemStatusHistory p = AccountingMockDataFactory
                .createMockOrmItemMasterStatusHistory(10, 100, 1000, 12.50, 3,
                        "2107-01-01", "2017-02-15",
                        "Item Status History Description 1", "Status Name 1");
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMasterStatusHistory(11,
                        100, 1001, 13.50, 3, "2107-01-16", "2017-03-15",
                "Item Status History Description 2", "Status Name 2");
        list.add(p);

        return list;
    }

    private List<VwItemStatusHistory> createMockFetchAllResponse() {
        List<VwItemStatusHistory> list = new ArrayList<>();
        VwItemStatusHistory p = AccountingMockDataFactory
                .createMockOrmItemMasterStatusHistory(10, 100, 1000, 12.50, 3,
                        "2017-01-01", "2017-02-15",
                        "Item Status History Description 1", "Status Name 1");
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMasterStatusHistory(11,
                101, 1001, 12.50, 3, "2017-02-16", "2017-03-15",
                "Item Status History Description 2", "Status Name 2");
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMasterStatusHistory(12,
                102, 1000, 3.50, 3, "2017-01-01", "2017-02-15",
                "Item Status History Description 3", "Status Name 3");
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMasterStatusHistory(13,
                103, 1001, 3.50, 3, "2017-02-15", "2017-03-15",
                "Item Status History Description 4", "Status Name 4");
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmItemMasterStatusHistory(14,
                104, 1003, 93.50, 3, "2017-02-15", "2017-03-15",
                "Item Status History Description 5", "Status Name 5");
        list.add(p);
        return list;
    }

    @Test
    public void testFetchAll() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMasterStatusHist.class)))
                            .thenReturn(this.mockFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "All Inventory Item Master status history fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusHistDto> results = null;
        ItemMasterStatusHist im = null;
        ItemMasterStatusHistDto criteria = Rmt2ItemMasterDtoFactory
                .createItemStatusHistoryInstance(im);
        try {
            results = api.getItemStatusHist(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ItemMasterStatusHistDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEntityId(), (10 + ndx));
            Assert.assertEquals(obj.getReason(), "Item Status History Description " + (ndx + 1));
            Assert.assertNull(obj.getEntityName());
        }
    }

    @Test
    public void testFetchAllNotFound() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMasterStatusHist.class)))
                            .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "All Inventory Item Master status history not found fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusHistDto> results = null;
        ItemMasterStatusHist im = null;
        ItemMasterStatusHistDto criteria = Rmt2ItemMasterDtoFactory
                .createItemStatusHistoryInstance(im);
        criteria.setItemId(9999);
        try {
            results = api.getItemStatusHist(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchSingle() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMasterStatusHist.class)))
                            .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master status history fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusHistDto> results = null;
        ItemMasterStatusHist im = null;
        ItemMasterStatusHistDto criteria = Rmt2ItemMasterDtoFactory
                .createItemStatusHistoryInstance(im);
        criteria.setEntityId(100);
        try {
            results = api.getItemStatusHist(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ItemMasterStatusHistDto dto = results.get(0);
        Assert.assertEquals(10, dto.getEntityId());
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("Item Status History Description 1", dto.getReason());
    }
    
    

    @Test
    public void testFetchSingleNotFound() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMasterStatusHist.class)))
                            .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master status history fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusHistDto> results = null;
        ItemMasterStatusHist im = null;
        ItemMasterStatusHistDto criteria = Rmt2ItemMasterDtoFactory
                .createItemStatusHistoryInstance(im);
        criteria.setEntityId(9999);
        try {
            results = api.getItemStatusHist(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchCurrentItemStatus() {
        try {
            when(this.mockPersistenceClient
                    .retrieveObject(any(Integer.class))).thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master status history fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        ItemMasterStatusHistDto dto = null;
        try {
            dto = api.getCurrentItemStatusHist(100);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(10, dto.getEntityId());
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("Item Status History Description 1", dto.getReason());
    }
    
    @Test
    public void testFetchCurrentItemStatusByInvalidItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getCurrentItemStatusHist(null);
            Assert.fail(
                    "Expected exception to be thrown due item id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        try {
            api.getCurrentItemStatusHist(0);
            Assert.fail(
                    "Expected exception to be thrown due item id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }

        try {
            api.getCurrentItemStatusHist(-100);
            Assert.fail(
                    "Expected exception to be thrown due item id is less than zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchItemStatusHistory() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMasterStatusHist.class)))
                            .thenReturn(this.mockCriteriaFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master status history fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusHistDto> results = null;
        try {
            results = api.getItemStatusHistByItemId(100);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        ItemMasterStatusHistDto dto = results.get(0);
        Assert.assertEquals(10, dto.getEntityId());
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals("Item Status History Description 1", dto.getReason());
    }

    @Test
    public void testFetchNullCriteria() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusHistDto> results = null;
       
        ItemMasterStatusHistDto criteria = null;
        try {
            api.getItemStatusHist(criteria);
            Assert.fail(
                    "Expected exception to be thrown due to null criteria object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchItemStatusHistoryByInvalidItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getItemStatusHistByItemId(null);
            Assert.fail(
                    "Expected exception to be thrown due item id is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        try {
            api.getItemStatusHistByItemId(0);
            Assert.fail(
                    "Expected exception to be thrown due item id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }

        try {
            api.getItemStatusHistByItemId(-100);
            Assert.fail(
                    "Expected exception to be thrown due item id is less than zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchAllWithException() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(ItemMasterStatusHist.class)))
                        .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "All Inventory Item Master status history fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusHistDto> results = null;
        ItemMasterStatusHist im = null;
        ItemMasterStatusHistDto criteria = Rmt2ItemMasterDtoFactory
                .createItemStatusHistoryInstance(im);
        try {
            results = api.getItemStatusHist(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InventoryApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }
}