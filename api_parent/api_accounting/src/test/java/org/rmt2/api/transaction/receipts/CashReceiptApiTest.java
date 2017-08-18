package org.rmt2.api.transaction.receipts;

import java.util.List;

import org.dto.BusinessContactDto;
import org.dto.CustomerDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.modules.transaction.receipts.CashReceiptApi;
import org.modules.transaction.receipts.CashReceiptApiFactory;
import org.rmt2.api.CommonAccountingTest;

/**
 * @author Roy Terrell
 * 
 */
public class CashReceiptApiTest extends CommonAccountingTest {

    private CashReceiptApiFactory apiFactory;

    private CashReceiptApi api;

    private CustomerDto cust;

    private BusinessContactDto contact;

    private SalesOrderDto so;

    private List<SalesOrderItemDto> items;

    private int item1Id;

    private int item2Id;

//    /**
//     * @throws java.lang.Exception
//     */
//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//        this.apiFactory = new CashReceiptApiFactory();
//        this.api = this.apiFactory.createApi();
//        this.api.setApiUser("AcctTester");
//        this.createData();
//    }
//
//    /**
//     * @throws java.lang.Exception
//     */
//    @After
//    public void tearDown() throws Exception {
//        this.deleteData();
//        this.api.close();
//        super.tearDown();
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.CommonAccountingTest#createData()
//     */
//    @Override
//    protected void createData() {
//        super.createData();
//        this.items = null;
//        this.so = null;
//        this.cust = null;
//        this.contact = null;
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.CommonAccountingTest#deleteData()
//     */
//    @Override
//    protected void deleteData() {
//        super.deleteData();
//
//        InventoryApiFactory invFact = new InventoryApiFactory();
//        InventoryApi invApi = invFact.createApi();
//        SalesApiFactory soFact = new SalesApiFactory();
//        SalesApi soApi = soFact.createApi();
//        SubsidiaryApiFactory custFact = new SubsidiaryApiFactory();
//        CustomerApi custApi = custFact.createCustomerApi();
//        ContactsApiFactory contactFact = new ContactsApiFactory();
//        ContactsApi contactApi = contactFact.createApi();
//
//        if (this.so != null) {
//            try {
//                soApi.deleteSalesOrder(this.so.getSalesOrderId());
//            } catch (SalesApiException e) {
//                e.printStackTrace();
//                soApi.rollbackTrans();
//            }
//        }
//
//        if (this.item1Id > 0 && this.item2Id > 0) {
//            try {
//                invApi.deleteItemMaster(this.item1Id);
//                invApi.deleteItemMaster(this.item2Id);
//            } catch (InventoryApiException e) {
//                e.printStackTrace();
//                invApi.rollbackTrans();
//            }
//        }
//
//        if (this.cust != null) {
//            try {
//                custApi.delete(cust);
//            } catch (CustomerApiException e) {
//                e.printStackTrace();
//                custApi.rollbackTrans();
//            }
//        }
//
//        if (this.contact != null) {
//            try {
//                contactApi.deleteContact(this.contact);
//            } catch (Exception e) {
//                e.printStackTrace();
//                contactApi.rollbackTrans();
//            }
//        }
//
//        contactApi.close();
//        custApi.close();
//        invApi.close();
//        soApi.close();
//    }
//
//    @Test
//    public void testCustomerPayment() {
//        this.cust = this.createCustomer();
//        this.so = this.createSalesOrder(cust);
//        try {
//            this.api.applyCustomerPayment(so, 500.00);
//        } catch (CashReceiptApiException e) {
//            e.printStackTrace();
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testPaymentConfirmationEmail() {
//        try {
//            this.api.emailPaymentConfirmation(737, 17929);
//        } catch (CashReceiptApiException e) {
//            e.printStackTrace();
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    private CustomerDto createCustomer() {
//        // Create contact
//        ContactsApiFactory contactFact = new ContactsApiFactory();
//        ContactsApi contactApi = contactFact.createApi();
//        int businessId = 0;
//        this.contact = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
//        this.contact.setContactName("JUnit Corporation");
//        this.contact.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);
//        this.contact.setContactEmail("junit_test@hotmail.com");
//        this.contact.setUpdateUserId(this.api.getApiUser());
//        // contactApi.beginTrans();
//        try {
//            businessId = contactApi.updateContact(this.contact);
//            Assert.assertTrue(businessId > 0);
//            Assert.assertEquals(businessId, this.contact.getContactId());
//            contactApi.commitTrans();
//        } catch (Exception e) {
//            e.printStackTrace();
//            contactApi.rollbackTrans();
//            Assert.fail(e.getMessage());
//        } finally {
//            contactApi.close();
//        }
//
//        // Create customer
//        SubsidiaryApiFactory custFact = new SubsidiaryApiFactory();
//        CustomerApi custApi = custFact.createCustomerApi();
//
//        CustomerDto cust = Rmt2SubsidiaryDtoFactory.createCustomerInstance(
//                null, null);
//        cust.setContactId(businessId);
//        cust.setActive(AccountingConst.ACCT_ACTIVE);
//        cust.setCreditLimit(5000.00);
//        int key;
//
//        custApi.beginTrans();
//        try {
//            key = custApi.update(cust);
//            cust.setCustomerId(key);
//            Assert.assertTrue(key > 0);
//            custApi.commitTrans();
//        } catch (SubsidiaryException e) {
//            custApi.rollbackTrans();
//            Assert.fail(e.getMessage());
//        }
//
//        return cust;
//    }
//
//    private SalesOrderDto createSalesOrder(CustomerDto cust) {
//        // Create Inventory items
//        InventoryApiFactory invFact = new InventoryApiFactory();
//        InventoryApi invApi = invFact.createApi();
//        invApi.setApiUser(this.api.getApiUser());
//        ItemMaster nullItem = null;
//        ItemMasterDto item1 = Rmt2InventoryDtoFactory
//                .createItemMasterInstance(nullItem);
//        item1.setItemTypeId(2);
//        item1.setVendorId(8);
//        item1.setItemName("Promark 717 Drumsticks");
//        item1.setVendorItemNo("AFRD-3838-33");
//        item1.setItemSerialNo("94329393-39393939");
//        item1.setQtyOnHand(44);
//        item1.setUnitCost(3.55);
//        item1.setMarkup(2.5);
//        item1.setRetailPrice(0);
//        item1.setOverrideRetail(0);
//        item1.setActive(AccountingConst.ACCT_ACTIVE);
//
//        invApi.beginTrans();
//        try {
//            this.item1Id = invApi.updateItemMaster(item1);
//        } catch (Exception e) {
//            invApi.rollbackTrans();
//            Assert.fail(e.getMessage());
//        }
//        Assert.assertTrue(this.item1Id > 0);
//
//        ItemMasterDto item2 = Rmt2InventoryDtoFactory
//                .createItemMasterInstance(nullItem);
//        item2.setItemTypeId(2);
//        item2.setVendorId(8);
//        item2.setItemName("Yamaha Stage Custom 16 inch Floor Tom");
//        item2.setVendorItemNo("Y8434-KDK-382");
//        item2.setItemSerialNo("95435-484848");
//        item2.setQtyOnHand(44);
//        item2.setUnitCost(250.0);
//        item2.setMarkup(2.5);
//        item2.setRetailPrice(0);
//        item2.setOverrideRetail(0);
//        item2.setActive(AccountingConst.ACCT_ACTIVE);
//        try {
//            this.item2Id = invApi.updateItemMaster(item2);
//        } catch (Exception e) {
//            invApi.rollbackTrans();
//            Assert.fail(e.getMessage());
//        }
//        Assert.assertTrue(this.item2Id > 0);
//        invApi.commitTrans();
//        invApi.close();
//
//        // Create Sales order
//        SalesOrderDto so = Rmt2SalesOrderDtoFactory
//                .createSalesOrderInstance(null);
//        so.setOrderTotal(1963.75);
//
//        // Create sales order items
//        this.items = new ArrayList<SalesOrderItemDto>();
//        SalesOrderItemDto i = Rmt2SalesOrderDtoFactory
//                .createSalesOrderItemInstance((SalesOrderItems) null);
//        i.setItemId(this.item1Id);
//        i.setOrderQty(10);
//        items.add(i);
//
//        i = Rmt2SalesOrderDtoFactory
//                .createSalesOrderItemInstance((SalesOrderItems) null);
//        i.setItemId(this.item2Id);
//        i.setOrderQty(3);
//        items.add(i);
//
//        // Cerate sales order
//        SalesApiFactory soFact = new SalesApiFactory();
//        SalesApi soApi = soFact.createApi();
//        int rc = 0;
//        this.api.beginTrans();
//        try {
//            rc = soApi.updateSalesOrder(so, cust.getCustomerId(), items);
//            Assert.assertTrue(rc > 1);
//            // Invoice Sales order
//            rc = soApi.invoiceSalesOrder(so, items, false);
//            Assert.assertTrue(rc > 1);
//            soApi.commitTrans();
//        } catch (SalesApiException e) {
//            soApi.rollbackTrans();
//            Assert.fail(e.getMessage());
//        } finally {
//            soApi.close();
//        }
//
//        return so;
//    }

}
