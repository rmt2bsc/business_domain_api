package org.rmt2.api.inventory;

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
import org.rmt2.api.CommonAccountingTest;

/**
 * @author Roy Terrell
 * 
 */
public class ItemMasterInvenotryAdjustmentTest extends CommonAccountingTest {

    private InventoryApiFactory f;
    private InventoryApi api;
    private int key;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.f = new InventoryApiFactory();
        this.api = this.f.createApi();
        this.api.setApiUser("TestUser");
        this.key = 0;
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.cleanup(key);
        this.f = null;
        this.api.close();
        this.api = null;
        super.tearDown();
    }

    @Test
    public void testInventoryPull() {
        // Create item
        int creditorId = 8;
        String itemName = "Gateway 2000 Computer Speakers";
        ItemMaster nullItem = null;
        ItemMasterDto item1 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setVendorId(creditorId);
        item1.setItemName(itemName);
        item1.setVendorItemNo("FMW 02292197");
        item1.setItemSerialNo("63639");
        item1.setQtyOnHand(144);
        item1.setUnitCost(6.55);
        item1.setMarkup(2.5);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(0);
        item1.setActive(AccountingConst.ACCT_ACTIVE);

        this.api.beginTrans();
        try {
            this.key = this.api.updateItemMaster(item1);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(this.key > 0);

        // Reduce item's inventory
        double invValue;
        try {
            invValue = this.api.pullInventory(this.key, 75);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(451.95, invValue);
        this.api.commitTrans();
    }

    @Test
    public void testInventoryPush() {
        // Create item
        int creditorId = 8;
        String itemName = "Dell Precision Workstation T3500";
        ItemMaster nullItem = null;
        ItemMasterDto item1 = Rmt2InventoryDtoFactory
                .createItemMasterInstance(nullItem);
        item1.setItemTypeId(2);
        item1.setVendorId(creditorId);
        item1.setItemName(itemName);
        item1.setVendorItemNo("TYE-19493");
        item1.setItemSerialNo("838238388888");
        item1.setQtyOnHand(100);
        item1.setUnitCost(350.55);
        item1.setMarkup(3.5);
        item1.setRetailPrice(0);
        item1.setOverrideRetail(0);
        item1.setActive(AccountingConst.ACCT_ACTIVE);

        this.api.beginTrans();
        try {
            this.key = this.api.updateItemMaster(item1);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(this.key > 0);

        // Increase item's inventory
        double invValue;
        try {
            invValue = this.api.pushInventory(this.key, 75);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(61346.25, invValue);
        this.api.commitTrans();
    }

    private void cleanup(int itemId) {
        int rc = 0;
        try {
            rc = this.api.deleteItemMaster(itemId);
        } catch (Exception e) {
            this.api.rollbackTrans();
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertEquals(1, rc);
        this.api.commitTrans();

        // Verify that Customer was deleted
        try {
            Object obj = this.api.getItemById(itemId);
            Assert.assertNull(obj);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return;
        }

    }
}
