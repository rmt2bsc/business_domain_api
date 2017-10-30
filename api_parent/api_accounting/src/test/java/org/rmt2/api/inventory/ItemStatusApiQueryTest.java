package org.rmt2.api.inventory;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dto.ItemMasterStatusDto;
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
import org.rmt2.api.AccountingMockDataUtility;
import org.rmt2.api.BaseAccountingDaoTest;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Item Status entity query use cases belonging to the Inventory Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class ItemStatusApiQueryTest extends BaseAccountingDaoTest {
    private List<ItemMasterStatus> mockSingleFetchResponse;
    private List<ItemMasterStatus> mockCriteriaFetchResponse;
    private List<ItemMasterStatus> mockFetchAllResponse;
    private List<ItemMasterStatus> mockNotFoundFetchResponse;

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

    private List<ItemMasterStatus> createMockNotFoundSearchResultsResponse() {
        List<ItemMasterStatus> list = null;
        return list;
    }

    private List<ItemMasterStatus> createMockSingleFetchResponse() {
        List<ItemMasterStatus> list = new ArrayList<ItemMasterStatus>();
        ItemMasterStatus p = AccountingMockDataUtility
                .createMockOrmItemMasterStatus(100, "Item Status #1");
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<ItemMasterStatus> createMockFetchUsingCriteriaResponse() {
        List<ItemMasterStatus> list = new ArrayList<ItemMasterStatus>();
        ItemMasterStatus p = AccountingMockDataUtility
                .createMockOrmItemMasterStatus(100, "Item Status #1");
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmItemMasterStatus(200,
                "Item Status #2");
        list.add(p);

        return list;
    }

    private List<ItemMasterStatus> createMockFetchAllResponse() {
        List<ItemMasterStatus> list = new ArrayList<ItemMasterStatus>();
        ItemMasterStatus p = AccountingMockDataUtility
                .createMockOrmItemMasterStatus(100, "Item Status #1");
        list.add(p);

        p = AccountingMockDataUtility
                .createMockOrmItemMasterStatus(101, "Item Status #2");
        list.add(p);

        p = AccountingMockDataUtility
                .createMockOrmItemMasterStatus(102, "Item Status #3");
        list.add(p);

        p = AccountingMockDataUtility
                .createMockOrmItemMasterStatus(103, "Item Status #4");
        list.add(p);

        p = AccountingMockDataUtility
                .createMockOrmItemMasterStatus(104, "Item Status #5");
        list.add(p);
        return list;
    }

    @Test
    public void testFetchAll() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterStatus.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "All Inventory Item Master Status fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusDto> results = null;
        ItemMasterStatusDto criteriaObj = null;
        try {
            results = api.getItemStatus(criteriaObj);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        for (int ndx = 0; ndx < results.size(); ndx++) {
            ItemMasterStatusDto obj = results.get(ndx);
            Assert.assertEquals(obj.getEntityId(), (100 + ndx));
            Assert.assertEquals(obj.getEntityName(), "Item Status #" + (ndx + 1));
        }
    }

    @Test
    public void testFetchAllNotFound() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterStatus.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "All Inventory Item Master Status not found fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusDto> results = null;
        ItemMasterStatusDto criteriaObj = null;
        try {
            results = api.getItemStatus(criteriaObj);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchSingle() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterStatus.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master Status fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        ItemMasterStatusDto dto = null;
        try {
            dto = api.getItemStatusById(100);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(100, dto.getEntityId());
        Assert.assertEquals("Item Status #1", dto.getEntityName());

        ItemMasterStatus im = null;
        ItemMasterStatusDto criteria = Rmt2ItemMasterDtoFactory
                .createItemStatusInstance(im);
        criteria.setEntityId(100);
        List<ItemMasterStatusDto> results = null;
        try {
            results = api.getItemStatus(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        dto = results.get(0);
        Assert.assertEquals(100, dto.getEntityId());
        Assert.assertEquals("Item Status #1", dto.getEntityName());
    }

    @Test
    public void testFetchSingleNotFound() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterStatus.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master Status not found fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        ItemMasterStatusDto dto = null;
        try {
            dto = api.getItemStatusById(570);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(dto);

        ItemMasterStatus im = null;
        ItemMasterStatusDto criteria = Rmt2ItemMasterDtoFactory
                .createItemStatusInstance(im);
        criteria.setEntityId(100);
        List<ItemMasterStatusDto> results = null;
        try {
            results = api.getItemStatus(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    
    @Test
    public void testFetchByStatusName() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterStatus.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master Status fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusDto> results = null;
        try {
            results = api.getItemStatus("Item");
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        ItemMasterStatusDto dto = results.get(0);
        Assert.assertTrue(dto.getEntityId() >= 100 && dto.getEntityId() <= 200);
        Assert.assertTrue(dto.getEntityName().contains("Item Status"));

        ItemMasterStatus im = null;
        ItemMasterStatusDto criteria = Rmt2ItemMasterDtoFactory
                .createItemStatusInstance(im);
        criteria.setEntityName("Item");
        results = null;
        try {
            results = api.getItemStatus(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        dto = results.get(0);
        Assert.assertTrue(dto.getEntityId() >= 100 && dto.getEntityId() <= 200);
        Assert.assertTrue(dto.getEntityName().contains("Item Status"));
    }

    @Test
    public void testFetchByStatusNameNotFound() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterStatus.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Inventory Item Master Status not found fetch  test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusDto> results = null;
        try {
            results = api.getItemStatus("Item Not Exists");
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
        
        ItemMasterStatus im = null;
        ItemMasterStatusDto criteria = Rmt2ItemMasterDtoFactory
                .createItemStatusInstance(im);
        criteria.setEntityName("Item Not Exists");
        results = null;
        try {
            results = api.getItemStatus(criteria);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    
    @Test
    public void testFetchWithInvalidStatusName() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            String criteria = null;
            api.getItemStatus(criteria);
            Assert.fail(
                    "Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }

        try {
            api.getItemStatus("");
            Assert.fail(
                    "Expected exception to be thrown due to null serial number");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

  
    @Test
    public void testFetchByInvalidStatusId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getItemStatusById(null);
            Assert.fail(
                    "Expected exception to be thrown due item statusid is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
        try {
            api.getItemStatusById(0);
            Assert.fail(
                    "Expected exception to be thrown due item statusid is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }

        try {
            api.getItemStatusById(-100);
            Assert.fail(
                    "Expected exception to be thrown due item statusid is less than zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchAllWithException() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(ItemMasterStatus.class)))
            .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "All Inventory Item Master Status fetch test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ItemMasterStatusDto> results = null;
        ItemMasterStatusDto criteriaObj = null;
        try {
            results = api.getItemStatus(criteriaObj);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InventoryApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }

}