package inventory;

import static org.junit.Assert.fail;

import java.util.List;

import org.dao.inventory.InventoryDao;
import org.dao.inventory.InventoryDaoException;
import org.dao.inventory.InventoryDaoFactory;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dto.ItemMasterDto;
import org.dto.ItemMasterStatusDto;
import org.dto.ItemMasterStatusHistDto;
import org.dto.ItemMasterTypeDto;
import org.dto.adapter.orm.inventory.Rmt2InventoryDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.CommonAccountingTest;

/**
 * @author royterrell
 * 
 */
public class ExistingItemMasterQueryTest extends CommonAccountingTest {
    private InventoryDaoFactory f;
    InventoryDao dao;
    private int itemId;
    private int itemTypeId;
    private int itemStatusId;
    private int itemStatusHistId;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.f = new InventoryDaoFactory();
        this.dao = f.createRmt2OrmDao();
        if (this.dao == null) {
            fail("Unable to create Inventory DAO object");
        }
        this.itemId = 157;
        this.itemTypeId = 1;
        this.itemStatusId = 27;
        this.itemStatusHistId = 1;
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.f = null;
        this.dao.close();
        this.dao = null;
        super.tearDown();
    }

    @Test
    public void testInventoryItemTypeQuery() {
        ItemMasterType imt = new ItemMasterType();
        imt.setItemTypeId(this.itemTypeId);
        ItemMasterTypeDto dto = Rmt2InventoryDtoFactory
                .createItemTypeInstance(imt);
        List<ItemMasterTypeDto> result = null;
        try {
            result = dao.fetch(dto);
        } catch (InventoryDaoException e) {
            fail(e.getMessage());
        }

        if (result == null || result.size() == 0) {
            fail("An empty result set was found");
        }
        return;
    }

    @Test
    public void testInventoryItemQuery() {
        ItemMaster im = new ItemMaster();
        im.setItemId(this.itemId);
        ItemMasterDto dto = Rmt2InventoryDtoFactory
                .createItemMasterInstance(im);
        List<ItemMasterDto> result = null;
        try {
            result = dao.fetch(dto);
        } catch (InventoryDaoException e) {
            fail(e.getMessage());
        }

        if (result == null || result.size() == 0) {
            fail("An empty result set was found");
        }
        return;
    }

    @Test
    public void testInventoryItemStatusQuery() {
        ItemMasterStatus ims = new ItemMasterStatus();
        ims.setItemStatusId(this.itemStatusId);
        ItemMasterStatusDto dto = Rmt2InventoryDtoFactory
                .createItemStatusInstance(ims);
        List<ItemMasterStatusDto> result = null;
        try {
            result = dao.fetch(dto);
        } catch (InventoryDaoException e) {
            fail(e.getMessage());
        }

        if (result == null || result.size() == 0) {
            fail("An empty result set was found");
        }
        return;
    }

    @Test
    public void testInventoryItemStatusHistoryQuery() {
        ItemMasterStatusHist imsh = new ItemMasterStatusHist();
        imsh.setItemStatusHistId(this.itemStatusHistId);
        ItemMasterStatusHistDto dto = Rmt2InventoryDtoFactory
                .createItemStatusHistoryInstance(imsh);
        List<ItemMasterStatusHistDto> result = null;
        try {
            result = dao.fetch(dto);
        } catch (InventoryDaoException e) {
            fail(e.getMessage());
        }

        if (result == null || result.size() == 0) {
            fail("An empty result set was found");
        }
        return;
    }

}
