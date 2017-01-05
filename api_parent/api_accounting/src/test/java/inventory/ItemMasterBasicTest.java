package inventory;

import junit.framework.Assert;

import org.AccountingConst;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dto.ItemMasterDto;
import org.dto.adapter.orm.inventory.Rmt2InventoryDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.inventory.InventoryApi;
import org.modules.inventory.InventoryApiFactory;

import com.CommonAccountingTest;

/**
 * @author Roy Terrell
 * 
 */
public class ItemMasterBasicTest extends CommonAccountingTest {

    private InventoryApiFactory f;
    private InventoryApi api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.f = new InventoryApiFactory();
        this.api = this.f.createApi();
        this.api.setApiUser("TestUser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.f = null;
        this.api.close();
        this.api = null;
        super.tearDown();
    }

    @Test
    public void testCreateQueryUpdateDeleteMerchandiseItemMaster() {
        // Create item
        int creditorId = 8;
        String itemName = "Logitech Mouse";
        int key = 0;
        ItemMaster nullItem = null;
        ItemMasterDto item1 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setVendorId(creditorId);
        item1.setItemName(itemName);
        item1.setVendorItemNo("83823-3838-33");
        item1.setItemSerialNo("JFDK8383DJD");
        item1.setQtyOnHand(44);
        item1.setUnitCost(3.55);
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

        // Query ITem
        ItemMasterDto item2;
        try {
            item2 = this.api.getItemById(key);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(itemName, item2.getItemName());

        // Update item
        double newUnitCost = 4.25;
        String newItemName = "Microsoft Mouse";
        item2.setUnitCost(newUnitCost);
        item2.setItemName(newItemName);
        int rc;
        try {
            rc = this.api.updateItemMaster(item2);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(rc, 1);

        // Query item to verify update
        ItemMasterDto item3;
        try {
            item3 = this.api.getItemById(key);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(newUnitCost, item3.getUnitCost());
        Assert.assertEquals(newItemName, item3.getItemName());

        // Delete Customer to verify update
        try {
            rc = this.api.deleteItemMaster(item3.getItemId());
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(1, rc);
        this.api.commitTrans();

        // Verify that Customer was deleted
        try {
            item3 = this.api.getItemById(key);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertNull(item3);
    }

    @Test
    public void testCreateQueryUpdateDeleteServiceItemMaster() {
        // Create item
        String itemName = "Apartment Locator";
        int key = 0;
        ItemMaster nullItem = null;
        ItemMasterDto item1 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item1.setItemTypeId(1);
        item1.setVendorId(0);
        item1.setItemName(itemName);
        item1.setVendorItemNo("37335452-44");
        item1.setItemSerialNo("D843-4848-43JD");
        item1.setQtyOnHand(1);
        item1.setUnitCost(0);
        item1.setMarkup(1);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(1);
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

        // Query ITem
        ItemMasterDto item2;
        try {
            item2 = this.api.getItemById(key);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(itemName, item2.getItemName());

        // Update item
        String newItemName = "Commercial Building Assistant";
        item2.setItemName(newItemName);
        int rc;
        try {
            rc = this.api.updateItemMaster(item2);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(rc, 1);

        // Query item to verify update
        ItemMasterDto item3;
        try {
            item3 = this.api.getItemById(key);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(newItemName, item3.getItemName());

        // Delete Customer to verify update
        try {
            rc = this.api.deleteItemMaster(item3.getItemId());
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(1, rc);
        this.api.commitTrans();

        // Verify that Customer was deleted
        try {
            item3 = this.api.getItemById(key);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertNull(item3);
    }

}
