package org.rmt2.api.inventory;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.inventory.InventoryDaoException;
import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.VendorItems;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.VendorItemDto;
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
 * Tests Vendor Items entity query use cases belonging to the Inventory Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class VendorItemApiQueryUpdateTest extends BaseAccountingDaoTest {
    private List<VwVendorItems> mockSingleFetchResponse;
    private List<VwVendorItems> mockCriteriaFetchResponse;
    private List<VwVendorItems> mockFetchAllResponse;
    private List<VwVendorItems> mockNotFoundFetchResponse;
    private List<Creditor> mockCreditorFetchResponse;
    private List<VwBusinessAddress> mockBusinessContactFetchResponse;

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
        this.mockCreditorFetchResponse = this
                .createMockSingleCreditorFetchResponse();
        this.mockBusinessContactFetchResponse = this
                .createMockSingleContactFetchResponse();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<VwVendorItems> createMockNotFoundSearchResultsResponse() {
        List<VwVendorItems> list = null;
        return list;
    }

    private List<VwVendorItems> createMockSingleFetchResponse() {
        List<VwVendorItems> list = new ArrayList<VwVendorItems>();
        VwVendorItems p = AccountingMockDataFactory.createMockOrmVwVendorItems(
                100, "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23);
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<VwVendorItems> createMockFetchUsingCriteriaResponse() {
        List<VwVendorItems> list = new ArrayList<VwVendorItems>();
        VwVendorItems p = AccountingMockDataFactory.createMockOrmVwVendorItems(
                100, "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmVwVendorItems(200,
                "222-222-222", "22222222", 1234, "Item # 2", 15, 0.99);
        list.add(p);

        return list;
    }

    private List<VwVendorItems> createMockFetchAllResponse() {
        List<VwVendorItems> list = new ArrayList<VwVendorItems>();
        VwVendorItems p = AccountingMockDataFactory.createMockOrmVwVendorItems(
                100, "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmVwVendorItems(200,
                "222-222-222", "22222222", 1234, "Item # 2", 15, 0.99);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmVwVendorItems(300,
                "333-333-333", "3333333", 1234, "Item # 3", 15, 4.55);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmVwVendorItems(400,
                "444-444-444", "4444444", 1234, "Item # 4", 100, 10.99);
        list.add(p);

        p = AccountingMockDataFactory.createMockOrmVwVendorItems(500,
                "555-555-555", "5555555", 1234, "Item # 5", 55, 32.99);
        list.add(p);
        return list;
    }

    private List<Creditor> createMockSingleCreditorFetchResponse() {
        List<Creditor> list = new ArrayList<Creditor>();
        Creditor o = AccountingMockDataFactory.createMockOrmCreditor(200, 1351,
                333, "C1234589", "123-456-789", 22);
        list.add(o);
        return list;
    }

    private List<VwBusinessAddress> createMockSingleContactFetchResponse() {
        List<VwBusinessAddress> list = new ArrayList<VwBusinessAddress>();
        VwBusinessAddress p = AccountingMockDataFactory
                .createMockOrmBusinessContact(1351, "ABC Company", 2222,
                        "94393 Hall Ave.", "Building 123", "Suite 300",
                        "Room 45", "Dallas", "TX", 75232);
        list.add(p);
        return list;
    }

    @Test
    public void testFetchSingle() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(VwVendorItems.class)))
                            .thenReturn(this.mockSingleFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Vendor Item fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        VendorItemDto dto = null;
        try {
            dto = api.getVendorItem(1234, 100);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals(100, dto.getEntityId());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
        Assert.assertEquals("Item # 1", dto.getEntityName());
    }

    @Test
    public void testFetchSingleNotFound() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(VwVendorItems.class)))
                            .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Vendor Item Not Found fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        VendorItemDto dto = null;
        try {
            dto = api.getVendorItem(9999, 99);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(dto);
    }

    @Test
    public void testFetchSingleWithNullVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getVendorItem(null, 100);
            Assert.fail(
                    "Expected exception to be thrown due to null vendor id");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }

    @Test
    public void testFetchSingleWithNullItemId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getVendorItem(1234, null);
            Assert.fail("Expected exception to be thrown due to null item id");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }

    @Test
    public void testFetchAssignedItems() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(VwVendorItems.class)))
                            .thenReturn(this.mockFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Vendor Assigned Item fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<VendorItemDto> results = null;
        try {
            results = api.getVendorAssignItems(1234);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        VendorItemDto dto = results.get(0);
        Assert.assertEquals(100, dto.getItemId());
        Assert.assertEquals(100, dto.getEntityId());
        Assert.assertEquals(1234, dto.getVendorId());
        Assert.assertEquals("Item # 1", dto.getItemName());
        Assert.assertEquals("Item # 1", dto.getEntityName());
    }

    @Test
    public void testFetchAssignedItemsWithNullVendorId() {
        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getVendorAssignItems(null);
            Assert.fail(
                    "Expected exception to be thrown due to null vendor id");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
        }
    }

    @Test
    public void testFetchAssignedItemsNotFound() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(VwVendorItems.class)))
                            .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Vendor Assigned Item Not Found fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<VendorItemDto> results = null;
        try {
            results = api.getVendorAssignItems(9999);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testUpdateVendorItem() {
        VwVendorItems updateVendorItem = AccountingMockDataFactory
                .createMockOrmVwVendorItems(100, "111-111-111", "11111111",
                        1351, "Updated Item #1", 50, 1.55);

        VendorItemDto updateDto = Rmt2ItemMasterDtoFactory
                .createVendorItemInstance(updateVendorItem);

        VwBusinessAddress busCriteria = new VwBusinessAddress();
        try {
            when(this.mockPersistenceClient
                    .retrieveList(eq(busCriteria)))
                            .thenReturn(this.mockBusinessContactFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Business Contact fetch test case setup failed");
        }

        Creditor creditorCriteria = new Creditor();
        creditorCriteria.setCreditorId(1351);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(creditorCriteria)))
                    .thenReturn(this.mockCreditorFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Creditor fetch test case setup failed");
        }

        try {
            when(this.mockPersistenceClient.updateRow(any(VendorItems.class)))
                    .thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Vendor Item update test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateVendorItem(updateDto);
        } catch (InventoryApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }
    
    @Test
    public void testFetchAssignedItemsWithException() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(VwVendorItems.class)))
            .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Vendor Assigned Item fetch using criteria test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<VendorItemDto> results = null;
        try {
            results = api.getVendorAssignItems(1234);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InventoryApiException);
            Assert.assertTrue(e.getCause() instanceof CannotRetrieveException);
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateVendorItemWithException() {
        VwVendorItems updateVendorItem = AccountingMockDataFactory
                .createMockOrmVwVendorItems(100, "111-111-111", "11111111",
                        1351, "Updated Item #1", 50, 1.55);

        VendorItemDto updateDto = Rmt2ItemMasterDtoFactory
                .createVendorItemInstance(updateVendorItem);

        VwBusinessAddress busCriteria = new VwBusinessAddress();
        try {
            when(this.mockPersistenceClient
                    .retrieveList(eq(busCriteria)))
                            .thenReturn(this.mockBusinessContactFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Business Contact fetch test case setup failed");
        }

        Creditor creditorCriteria = new Creditor();
        creditorCriteria.setCreditorId(1351);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(creditorCriteria)))
                    .thenReturn(this.mockCreditorFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Creditor fetch test case setup failed");
        }

        try {
            when(this.mockPersistenceClient.updateRow(any(VendorItems.class)))
            .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Vendor Item update test case setup failed");
        }

        InventoryApiFactory f = new InventoryApiFactory();
        InventoryApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateVendorItem(updateDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InventoryApiException);
            Assert.assertTrue(e.getCause() instanceof InventoryDaoException);
            e.printStackTrace();
        }
    }
}