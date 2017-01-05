package inventory;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.AccountingConst;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dto.CreditorDto;
import org.dto.ItemMasterDto;
import org.dto.VendorItemDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.dto.adapter.orm.inventory.Rmt2InventoryDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.inventory.InventoryApi;
import org.modules.inventory.InventoryApiFactory;
import org.modules.inventory.InventoryConst;
import org.modules.inventory.InventoryException;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.subsidiary.SubsidiaryException;

import com.CommonAccountingTest;

/**
 * @author Roy Terrell
 * 
 */
public class VendorItemTest extends CommonAccountingTest {

    private SubsidiaryApiFactory credFactory;
    private CreditorApi credApi;
    private InventoryApiFactory f;
    private InventoryApi api;
    private List<Integer> itemKey;
    private int vendorKey;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.credFactory = new SubsidiaryApiFactory();
        this.credApi = this.credFactory.createCreditorApi();
        this.credApi.setApiUser("TestUser");

        this.f = new InventoryApiFactory();
        this.api = this.f.createApi();
        this.api.setApiUser("TestUser");
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
        this.f = null;
        this.credApi.close();
        this.api.close();
        this.api = null;
        this.credFactory = null;
        this.credApi = null;
        super.tearDown();
    }

    protected void createData() {
        int key;
        this.itemKey = new ArrayList<Integer>();

        // Create vendor
        int businessId = 1451;
        CreditorDto cred = Rmt2SubsidiaryDtoFactory.createCreditorInstance(
                null, null);
        cred.setContactId(businessId);
        cred.setActive(AccountingConst.ACCT_ACTIVE);
        cred.setCreditLimit(1000.00);
        cred.setCreditorTypeId(AccountingConst.CREDITORTYPE_VENDOR);
        this.credApi.beginTrans();
        try {
            key = this.credApi.update(cred);
        } catch (SubsidiaryException e) {
            this.credApi.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(key > 0);
        this.credApi.commitTrans();

        this.vendorKey = key;

        // Create Item #1
        ItemMaster nullItem = null;
        ItemMasterDto item1 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setItemName("Test Item #1");
        item1.setVendorItemNo("ITM 11111111");
        item1.setItemSerialNo("AAAAAA");
        item1.setQtyOnHand(4);
        item1.setUnitCost(1.55);
        item1.setMarkup(2.5);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(0);
        item1.setActive(AccountingConst.ACCT_ACTIVE);

        this.api.beginTrans();
        try {
            key = this.api.updateItemMaster(item1);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(key > 0);
        this.itemKey.add(key);

        // Create Item #2
        item1 = Rmt2InventoryDtoFactory.createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setItemName("Test Item #2");
        item1.setVendorItemNo("ITM 2222222");
        item1.setItemSerialNo("BBBBBB");
        item1.setQtyOnHand(15);
        item1.setUnitCost(21.55);
        item1.setMarkup(2.5);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(0);
        item1.setActive(AccountingConst.ACCT_ACTIVE);
        try {
            key = this.api.updateItemMaster(item1);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(key > 0);
        this.itemKey.add(key);

        // Create Item #3
        item1 = Rmt2InventoryDtoFactory.createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setItemName("Test Item #3");
        item1.setVendorItemNo("ITM 3333333");
        item1.setItemSerialNo("CCCCCC");
        item1.setQtyOnHand(100);
        item1.setUnitCost(5.34);
        item1.setMarkup(2.5);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(0);
        item1.setActive(AccountingConst.ACCT_ACTIVE);
        try {
            key = this.api.updateItemMaster(item1);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(key > 0);
        this.itemKey.add(key);
        this.credApi.rollbackTrans();
    }

    protected void deleteData() {
        int rc = 0;

        // Delete items
        this.api.beginTrans();
        for (int itemId : this.itemKey) {
            try {
                rc = this.api.deleteItemMaster(itemId);
            } catch (Exception e) {
                this.api.rollbackTrans();
                Assert.fail(e.getMessage());
                return;
            }
            Assert.assertEquals(1, rc);
        }

        // Delete creditor
        this.credApi.beginTrans();
        try {
            CreditorDto credDto = Rmt2SubsidiaryDtoFactory
                    .createCreditorInstance(null, null);
            credDto.setCreditorId(vendorKey);
            rc = this.credApi.delete(credDto);
        } catch (Exception e) {
            this.credApi.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }

        this.credApi.commitTrans();
        this.api.commitTrans();
    }

    @Test
    public void testAssociation() {
        // Create assoications
        int itemKeyArray[] = new int[this.itemKey.size()];
        for (int ndx = 0; ndx < this.itemKey.size(); ndx++) {
            itemKeyArray[ndx] = this.itemKey.get(ndx).intValue();
        }
        int count = 0;
        this.api.beginTrans();
        try {
            count = this.api.assignVendorItems(this.vendorKey, itemKeyArray);
        } catch (InventoryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(this.itemKey.size(), count);

        // verify assoications
        List<VendorItemDto> list = null;
        try {
            list = this.api.getVendorAssignItems(this.vendorKey);
        } catch (InventoryException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), count);

        // Delete associations
        try {
            count = this.api.removeVendorItems(vendorKey, itemKeyArray);
        } catch (InventoryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(this.itemKey.size(), count);

        // verify assoications were deleted
        try {
            list = this.api.getVendorAssignItems(this.vendorKey);
        } catch (InventoryException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNull(list);
        this.api.commitTrans();
    }

    @Test
    public void testItemOverrideChange() {
        // Change override to true
        int itemKeyArray[] = new int[this.itemKey.size()];
        for (int ndx = 0; ndx < this.itemKey.size(); ndx++) {
            itemKeyArray[ndx] = this.itemKey.get(ndx).intValue();
        }
        int count = 0;
        this.api.beginTrans();
        try {
            count = this.api.addInventoryOverride(this.vendorKey, itemKeyArray);
        } catch (InventoryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(this.itemKey.size(), count);

        // verify override is turned on
        ItemMasterDto check = null;
        try {
            check = this.api.getItemById(itemKeyArray[0]);
        } catch (InventoryException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(check);
        Assert.assertEquals(InventoryConst.ITEM_OVERRIDE_YES,
                check.getOverrideRetail());

        // Turn off override
        try {
            count = this.api.removeInventoryOverride(vendorKey, itemKeyArray);
        } catch (InventoryException e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(this.itemKey.size(), count);

        // verify override is turned off
        try {
            check = this.api.getItemById(itemKeyArray[1]);
        } catch (InventoryException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(InventoryConst.ITEM_OVERRIDE_NO,
                check.getOverrideRetail());
        this.api.commitTrans();
    }
}
