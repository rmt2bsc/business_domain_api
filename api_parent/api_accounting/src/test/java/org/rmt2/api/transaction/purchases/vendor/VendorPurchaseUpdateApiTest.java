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
import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.adapter.orm.transaction.purchaseorder.Rmt2PurchaseOrderDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.purchases.vendor.VendorPurchasesApi;
import org.modules.transaction.purchases.vendor.VendorPurchasesApiException;
import org.modules.transaction.purchases.vendor.VendorPurchasesApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.persistence.AbstractDaoClientImpl;
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
    
    private void makePurchaseOrderNew() {
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

    @Test
    public void testCreate_PurchaseOrder_Success() {
        // Modify mock data to appear as a new purchase order     
        this.makePurchaseOrderNew();
        
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
        mockCriteria.setCreditorId(TEST_CREDITOR_ID);
        try {
            this.mockCreditorFetchSingleResponse.get(0).setCreditorId(TEST_CREDITOR_ID);
            this.mockCreditorFetchSingleResponse.get(0).setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
            when(this.mockPersistenceClient.retrieveObject(isA(Creditor.class)))
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

}