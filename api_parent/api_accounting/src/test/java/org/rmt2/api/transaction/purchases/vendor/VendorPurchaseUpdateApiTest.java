package org.rmt2.api.transaction.purchases.vendor;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.AccountingConst;
import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.CreditorType;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.PurchaseOrderItems;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatusHist;
import org.dao.transaction.purchases.vendor.VendorPurchasesConst;
import org.dao.transaction.purchases.vendor.VendorPurchasesDaoException;
import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.adapter.orm.transaction.purchaseorder.Rmt2PurchaseOrderDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.inventory.InventoryConst;
import org.modules.transaction.purchases.vendor.PurchaseOrderItemValidationException;
import org.modules.transaction.purchases.vendor.PurchaseOrderValidationException;
import org.modules.transaction.purchases.vendor.VendorPurchasesApi;
import org.modules.transaction.purchases.vendor.VendorPurchasesApiException;
import org.modules.transaction.purchases.vendor.VendorPurchasesApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.AccountingMockDataUtility;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests creditor purchases transaction query Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class VendorPurchaseUpdateApiTest extends VendorPurchaseApiTestData {
    private static final int TEST_PO_ID = 330;
    private static final int TEST_PO_ID_NEW = 1234567;
    private static final int TEST_PO_ITEM_ID = 8880;
    private static final int TEST_XACT_ID = 7000;
    private static final int TEST_PO_STATUS_HIST_ID_NEW = 7654321;
    private static final int TEST_CREDITOR_ID = 1111111;
    private static final String TEST_VENDOR_ITEM_NO = "111-111";
    
    private PurchaseOrderDto poDto;
    private List<PurchaseOrderItemDto> poItemsDto;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }
    
    private void setupNewPurchaseOrderDto() {
        this.poDto = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderInstance(this.mockPurchaseOrder.get(0));
        this.poDto.setPoId(0);
        
        this.poItemsDto = new ArrayList<>();
        for (PurchaseOrderItems item : this.mockPurchaseOrderItems) {
            PurchaseOrderItemDto dto = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(item);
            dto.setPoId(0);
            dto.setPoItemId(0);
            this.poItemsDto.add(dto);
        }
    }
    
    private void setupExistingPurchaseOrderDto() {
        this.poDto = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderInstance(this.mockPurchaseOrder.get(0));
        
        this.poItemsDto = new ArrayList<>();
        for (PurchaseOrderItems item : this.mockPurchaseOrderItems) {
            PurchaseOrderItemDto dto = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(item);
            this.poItemsDto.add(dto);
        }
    }

    @Test
    public void testCreate_PurchaseOrder_Success() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrderItems.class), eq(true)))
              .thenReturn(8880, 8881, 8882, 8883, 8884);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID_NEW);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(PurchaseOrder.class)))
                    .thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor purchase orders fetch test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class)))
                            .thenReturn(this.mockItemMaster);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master fetch using criteria test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMasterType.class)))
                    .thenReturn(this.mockItemMasterType);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master Type fetch using criteria test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        PurchaseOrderStatusHist mockPoStatusCriteria = new PurchaseOrderStatusHist();
        Set<String> customSql = new HashSet<>();
        customSql.add("\"end_date is null\"");
        mockPoStatusCriteria.setCustomCriteria(customSql);
        mockPoStatusCriteria.setPoId(TEST_PO_ID_NEW);
        try {
            this.mockPurchaseOrderCurrentStatusHistory.get(0).setPoStatusId(VendorPurchasesConst.PURCH_STATUS_NEW);
            when(this.mockPersistenceClient.retrieveList(eq(mockPoStatusCriteria)))
                    .thenReturn(this.mockPurchaseOrderCurrentStatusHistory);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order current status fetch test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.updateRow(isA(PurchaseOrderStatusHist.class))).thenReturn(1);
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrderStatusHist.class), eq(true)))
                .thenReturn(TEST_PO_STATUS_HIST_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order status insert/update test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        int results = 0;
        try {
            results = api.updatePurchaseOrder(this.poDto, this.poItemsDto);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals(TEST_PO_ID_NEW, results);
    }

    @Test
    public void testValidation_Create_PurchaseOrder_Null_PO() {
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.updatePurchaseOrder(null, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PurchaseOrderValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_CreditorId_Zero() {
        // Perform test
        this.setupNewPurchaseOrderDto();
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            this.poDto.setCreditorId(0);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PurchaseOrderValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_CreditorId_Negative() {
        // Perform test
        this.setupNewPurchaseOrderDto();
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            this.poDto.setCreditorId(-1234);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PurchaseOrderValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Creditor_Notfound() {
        // Perform test
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        this.setupNewPurchaseOrderDto();
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PurchaseOrderValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_CreditorType_Notfound() {
        // Perform test
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        this.setupNewPurchaseOrderDto();
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            this.poDto.setCreditorId(-1234);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PurchaseOrderValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_CreditorType_Incorrect() {
        // Perform test
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_CREDITOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        this.setupNewPurchaseOrderDto();
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            this.poDto.setCreditorId(-1234);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PurchaseOrderValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Items_Null() {
        // Perform test
        this.setupNewPurchaseOrderDto();
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.updatePurchaseOrder(this.poDto, null);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PurchaseOrderValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Items_Empty() {
        // Perform test
        this.setupNewPurchaseOrderDto();
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            List<PurchaseOrderItemDto> items = new ArrayList<>();
            api.updatePurchaseOrder(this.poDto, items);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof PurchaseOrderValidationException);
        }
    }
    
    @Test
    public void testError_Create_PurchaseOrder_DB_Error() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenThrow(DatabaseException.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        int results = 0;
        try {
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof VendorPurchasesDaoException);
            Assert.assertTrue(e.getCause().getCause() instanceof DatabaseException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Null_PO_Item() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            PurchaseOrderItemDto o = null;
            this.poItemsDto.add(o);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof PurchaseOrderItemValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Item_POId_Zero() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            PurchaseOrderItems item = AccountingMockDataUtility.createPurchaseOrderItem(8880, 0, 100, 100.00, 11, 4, 0);
            PurchaseOrderItemDto o = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(item);
            this.poItemsDto.add(o);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof PurchaseOrderItemValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Item_POId_Notfound() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID_NEW);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(PurchaseOrder.class))).thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor purchase orders fetch test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof PurchaseOrderItemValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Item_ItemId_NotPositive() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID_NEW);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(PurchaseOrder.class))).thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor purchase orders fetch test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            PurchaseOrderItems item = AccountingMockDataUtility.createPurchaseOrderItem(8880, 330, 0, 100.00, 11, 4, 0);
            PurchaseOrderItemDto o = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(item);
            this.poItemsDto.add(o);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof PurchaseOrderItemValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Item_ItemMaster_Notfound() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID_NEW);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(PurchaseOrder.class))).thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor purchase orders fetch test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master fetch using criteria test case setup failed");
        }

        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof PurchaseOrderItemValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Item_ItemMasterType_Notfound() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }

        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID_NEW);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(PurchaseOrder.class))).thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor purchase orders fetch test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class))).thenReturn(this.mockItemMaster);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master fetch using criteria test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMasterType.class))).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master Type fetch using criteria test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof PurchaseOrderItemValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Item_ItemMasterType_NotMatcing() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID_NEW);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(PurchaseOrder.class))).thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor purchase orders fetch test case setup failed");
        }
        
        try {
            this.mockItemMaster.get(0).setItemTypeId(InventoryConst.ITEM_TYPE_SRVC);
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class))).thenReturn(this.mockItemMaster);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master fetch using criteria test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMasterType.class)))
                    .thenReturn(this.mockItemMasterType);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master Type fetch using criteria test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
      PurchaseOrderStatusHist mockPoStatusCriteria = new PurchaseOrderStatusHist();
      Set<String> customSql = new HashSet<>();
      customSql.add("\"end_date is null\"");
      mockPoStatusCriteria.setCustomCriteria(customSql);
      mockPoStatusCriteria.setPoId(TEST_PO_ID_NEW);
      try {
          this.mockPurchaseOrderCurrentStatusHistory.get(0).setPoStatusId(VendorPurchasesConst.PURCH_STATUS_NEW);
          when(this.mockPersistenceClient.retrieveList(eq(mockPoStatusCriteria)))
                  .thenReturn(this.mockPurchaseOrderCurrentStatusHistory);
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail("All vendor purchase order current status fetch test case setup failed");
      }
        
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
//            PurchaseOrderItems item = 
//                    AccountingMockDataUtility.createPurchaseOrderItem(8880, 330, InventoryConst.ITEM_TYPE_SRVC, 100.00, 11, 4, 0);
//            PurchaseOrderItemDto o = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(item);
//            this.poItemsDto.add(o);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof PurchaseOrderItemValidationException);
            Assert.assertTrue(e.getCause().getCause() instanceof PurchaseOrderItemValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Item_QtyOrdered_Zero() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
//        try {
//            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrderItems.class), eq(true)))
//              .thenReturn(8880, 8881, 8882, 8883, 8884);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail("Create vendor purchase order test case setup failed");
//        }
//        
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID_NEW);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(PurchaseOrder.class))).thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor purchase orders fetch test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class))).thenReturn(this.mockItemMaster);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master fetch using criteria test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMasterType.class)))
                    .thenReturn(this.mockItemMasterType);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master Type fetch using criteria test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
//        PurchaseOrderStatusHist mockPoStatusCriteria = new PurchaseOrderStatusHist();
//        Set<String> customSql = new HashSet<>();
//        customSql.add("\"end_date is null\"");
//        mockPoStatusCriteria.setCustomCriteria(customSql);
//        mockPoStatusCriteria.setPoId(TEST_PO_ID_NEW);
//        try {
//            this.mockPurchaseOrderCurrentStatusHistory.get(0).setPoStatusId(VendorPurchasesConst.PURCH_STATUS_NEW);
//            when(this.mockPersistenceClient.retrieveList(eq(mockPoStatusCriteria)))
//                    .thenReturn(this.mockPurchaseOrderCurrentStatusHistory);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail("All vendor purchase order current status fetch test case setup failed");
//        }
//        
//        try {
//            when(this.mockPersistenceClient.updateRow(isA(PurchaseOrderStatusHist.class))).thenReturn(1);
//            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrderStatusHist.class), eq(true)))
//                .thenReturn(TEST_PO_STATUS_HIST_ID_NEW);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail("All vendor purchase order status insert/update test case setup failed");
//        }

        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            PurchaseOrderItems item = 
                    AccountingMockDataUtility.createPurchaseOrderItem(8880, 330, InventoryConst.ITEM_TYPE_MERCH, 100.00, 0, 4, 0);
            PurchaseOrderItemDto o = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(item);
            this.poItemsDto.add(o);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof PurchaseOrderItemValidationException);
        }
    }
    
    @Test
    public void testValidation_Create_PurchaseOrder_Item_QtyOrdered_LessThan_QtyReceived() {
        // Modify mock data to appear as a new purchase order     
        this.setupNewPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrder.class), eq(true)))
              .thenReturn(TEST_PO_ID_NEW);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID_NEW);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(PurchaseOrder.class))).thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor purchase orders fetch test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class))).thenReturn(this.mockItemMaster);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master fetch using criteria test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMasterType.class)))
                    .thenReturn(this.mockItemMasterType);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master Type fetch using criteria test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        try {
            PurchaseOrderItems item = 
                    AccountingMockDataUtility.createPurchaseOrderItem(8880, 330, InventoryConst.ITEM_TYPE_MERCH, 100.00, 10, 40, 0);
            PurchaseOrderItemDto o = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(item);
            this.poItemsDto.add(o);
            api.updatePurchaseOrder(this.poDto, this.poItemsDto);
            Assert.fail("Test failed due to an exception was expected");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof VendorPurchasesApiException);
            Assert.assertTrue(e.getCause() instanceof PurchaseOrderItemValidationException);
        }
    }
    
    @Test
    public void testModify_PurchaseOrder_Refresh_Success() {
        this.setupExistingPurchaseOrderDto();
        
        // Mock method call to create vendor purchase order 
        try {
            when(this.mockPersistenceClient.updateRow(isA(PurchaseOrder.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("UPdate vendor purchase order test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.insertRow(isA(PurchaseOrderItems.class), eq(true)))
              .thenReturn(8880, 8881, 8882, 8883, 8884);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Create vendor purchase order test case setup failed");
        }
        
        PurchaseOrder mockCriteria = new PurchaseOrder();
        mockCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(isA(PurchaseOrder.class)))
                    .thenReturn(this.mockPurchaseOrder);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single vendor purchase orders fetch test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMaster.class)))
                            .thenReturn(this.mockItemMaster);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master fetch using criteria test case setup failed");
        }
        
        try {
            when(this.mockPersistenceClient.retrieveList(isA(ItemMasterType.class)))
                    .thenReturn(this.mockItemMasterType);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Inventory Item Master Type fetch using criteria test case setup failed");
        }
        
        Creditor mockCreditorCriteria = new Creditor();
        mockCreditorCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(eq(mockCreditorCriteria)))
                            .thenReturn(this.mockCreditorFetchSingleResponse.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }
        
        CreditorType mockCredTypeCriteria = new CreditorType();
        mockCredTypeCriteria.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        try {
            this.mockCreditorTypeFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveList(eq(mockCredTypeCriteria))).thenReturn(
                            this.mockCreditorTypeFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single creditor type fetch test case setup failed");
        }
        
        PurchaseOrderStatusHist mockPoStatusCriteria = new PurchaseOrderStatusHist();
        Set<String> customSql = new HashSet<>();
        customSql.add("\"end_date is null\"");
        mockPoStatusCriteria.setCustomCriteria(customSql);
        mockPoStatusCriteria.setPoId(TEST_PO_ID);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockPoStatusCriteria)))
                    .thenReturn(this.mockPurchaseOrderCurrentStatusHistory);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("All vendor purchase order current status fetch test case setup failed");
        }
        
        // Perform test
        VendorPurchasesApiFactory f = new VendorPurchasesApiFactory();
        VendorPurchasesApi api = f.createApi(mockDaoClient);
        int results = 0;
        try {
            results = api.updatePurchaseOrder(this.poDto, this.poItemsDto);
        } catch (VendorPurchasesApiException e) {
            e.printStackTrace();
            Assert.fail("Test failed due to unexpected exception thrown");
        }
        Assert.assertEquals(VendorPurchasesConst.PO_UPDATE_SUCCESSFUL, results);
    }
}