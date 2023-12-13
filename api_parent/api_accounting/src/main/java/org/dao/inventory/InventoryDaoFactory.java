package org.dao.inventory;

import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.VendorItems;
import org.dao.mapping.orm.rmt2.VwItemAssociations;
import org.dao.mapping.orm.rmt2.VwItemMaster;
import org.dao.mapping.orm.rmt2.VwItemStatusHistory;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.ItemAssociationDto;
import org.dto.ItemMasterDto;
import org.dto.ItemMasterStatusDto;
import org.dto.ItemMasterStatusHistDto;
import org.dto.ItemMasterTypeDto;
import org.dto.VendorItemDto;

import com.RMT2Base;
import com.api.constants.GeneralConst;
import com.api.persistence.DaoClient;
import com.api.util.RMT2String2;

/**
 * Factory class for creating inventory DAO related objects.
 * 
 * @author Roy Terrell
 * 
 */
public class InventoryDaoFactory extends RMT2Base {

    /**
     * Default constructory
     */
    public InventoryDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>InventoryDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @return an instance of {@link InventoryDao}
     */
    public InventoryDao createRmt2OrmDao() {
        InventoryDao dao = new Rmt2InventoryDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>InventoryDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param appName
     * @return an instance of {@link InventoryDao}
     */
    public InventoryDao createRmt2OrmDao(String appName) {
        InventoryDao dao = new Rmt2InventoryDaoImpl(appName);
        return dao;
    }

    /**
     * Creates an instance of <i>InventoryDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link DaoClient}
     * 
     * @return an instance of {@link InventoryDao}
     */
    public InventoryDao createRmt2OrmDao(DaoClient dao) {
        InventoryDao d = new Rmt2InventoryDaoImpl(dao.getClient());
        return d;
    }

    /**
     * Creates a ItemMaster object from an ItemMasterDto.
     * 
     * @param item
     *            an instance of {@link ItemMasterDto}
     * @return an instance of {@link ItemMaster} or null if <i>item</i> is null.
     */
    public static final ItemMaster createItemMasterRmt2Orm(ItemMasterDto item) {
        if (item == null) {
            return null;
        }
        ItemMaster obj = new ItemMaster();
        obj.setItemId(item.getItemId());
        obj.setItemTypeId(item.getItemTypeId());
        obj.setCreditorId(item.getVendorId());
        obj.setDescription(item.getItemName());
        obj.setVendorItemNo(item.getVendorItemNo());
        obj.setItemSerialNo(item.getItemSerialNo());
        obj.setQtyOnHand(item.getQtyOnHand());
        obj.setUnitCost(item.getUnitCost());
        obj.setMarkup(item.getMarkup());
        obj.setRetailPrice(item.getRetailPrice());
        obj.setOverrideRetail(item.getOverrideRetail());
        obj.setActive(item.getActive());
        obj.setDateCreated(item.getDateCreated());
        obj.setDateUpdated(item.getDateUpdated());
        obj.setIpCreated(item.getIpCreated());
        obj.setIpUpdated(item.getIpUpdated());
        obj.setUserId(item.getUpdateUserId());
        return obj;
    }

    /**
     * Creates a VendorItems object from an VendorItemDto.
     * 
     * @param item
     *            an instance of {@link VendorItemDto}
     * @return an instance of {@link VendorItems} or null if <i>item</i> is
     *         null.
     */
    public static final VendorItems createVendorItemRmt2Orm(VendorItemDto item) {
        if (item == null) {
            return null;
        }
        VendorItems vi = new VendorItems();
        vi.setItemId(item.getItemId());
        vi.setCreditorId(item.getVendorId());
        vi.setVendorItemNo(item.getVendorItemNo());
        vi.setItemSerialNo(item.getItemSerialNo());
        vi.setUnitCost(item.getUnitCost());
        return vi;
    }

    /**
     * Creates a ItemMasterStatus object from an ItemMasterStatusDto.
     * 
     * @param status
     *            an instance of {@link ItemMasterStatusDto}
     * @return an instance of {@link ItemMasterStatus} or null if <i>status</i>
     *         is null.
     */
    public static final ItemMasterStatus createItemStatusRmt2Orm(
            ItemMasterStatusDto status) {
        if (status == null) {
            return null;
        }
        ItemMasterStatus obj = new ItemMasterStatus();
        obj.setItemStatusId(status.getEntityId());
        obj.setDescription(status.getEntityName());
        return obj;
    }

    /**
     * Creates a ItemMasterStatusHist object from an ItemMasterStatusHistDto.
     * 
     * @param status
     *            an instance of {@link ItemMasterStatusHistDto}
     * @return an instance of {@link ItemMasterStatusHist} or null if
     *         <i>hist</i> is null.
     */
    public static final ItemMasterStatusHist createItemStatusHistoryRmt2Orm(
            ItemMasterStatusHistDto hist) {
        if (hist == null) {
            return null;
        }
        ItemMasterStatusHist obj = new ItemMasterStatusHist();
        obj.setItemStatusHistId(hist.getEntityId());
        obj.setItemId(hist.getItemId());
        obj.setItemStatusId(hist.getItemStatusId());
        obj.setUnitCost(hist.getUnitCost());
        obj.setMarkup(hist.getMarkup());
        obj.setEffectiveDate(hist.getEffectiveDate());
        obj.setDateCreated(hist.getDateCreated());
        obj.setUserId(hist.getUpdateUserId());
        return obj;
    }

    /**
     * Creates a ItemMasterType object from an ItemMasterTypeDto.
     * 
     * @param itemType
     *            an instance of {@link ItemMasterTypeDto}
     * @return an instance of {@link ItemMasterType} or null if <i>itemType</i>
     *         is null.
     */
    public static final ItemMasterType createItemTypeRmt2Orm(
            ItemMasterTypeDto itemType) {
        if (itemType == null) {
            return null;
        }
        ItemMasterType obj = new ItemMasterType();
        obj.setItemTypeId(itemType.getItemTypeId());
        obj.setDescription(itemType.getItemTypeDescription());
        return obj;
    }

    /**
     * Create an instance of VendorItems which the vendor, item, item serial
     * number, vendor item number, and item unit cost are known.
     * 
     * @param vendorId
     * @param item
     *            instance of {@link ItemMasterDto}
     * @return
     */
    public static VendorItems createVendorItem(int vendorId, ItemMasterDto item) {
        VendorItems obj = new VendorItems();
        obj.setCreditorId(vendorId);
        obj.setItemId(item.getItemId());
        obj.setItemSerialNo(item.getItemSerialNo());
        obj.setVendorItemNo(item.getVendorItemNo());
        obj.setUnitCost(item.getUnitCost());
        return obj;
    }

    /**
     * Creates and returns an <i>ItemMaster</i> object containing selection
     * criteria obtained from an instance of <i>ItemMasterDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>itemId</li>
     *            <li>itemSerialNo</li>
     *            <li>itemTypeId</li>
     *            <li>creditorId</li>
     *            <li>vendorItemNo</li>
     *            <li>description</li>
     *            </ul>
     * @return an instance of {@link ItemMaster}
     */
    public static final ItemMaster createCriteria(ItemMasterDto criteria) {
        ItemMaster obj = new ItemMaster();
        if (criteria != null) {
            if (criteria.getItemId() > 0) {
                obj.addCriteria(ItemMaster.PROP_ITEMID, criteria.getItemId());
                obj.setItemId(criteria.getItemId());
            }
            if (criteria.getItemSerialNo() != null) {
                obj.addLikeClause(ItemMaster.PROP_ITEMSERIALNO,
                        criteria.getItemSerialNo());
                obj.setItemSerialNo(criteria.getItemSerialNo());
            }
            if (criteria.getItemTypeId() > 0) {
                obj.addCriteria(ItemMaster.PROP_ITEMTYPEID,
                        criteria.getItemTypeId());
                obj.setItemTypeId(criteria.getItemTypeId());
            }
            if (criteria.getVendorId() > 0) {
                obj.addCriteria(ItemMaster.PROP_CREDITORID,
                        criteria.getVendorId());
                obj.setCreditorId(criteria.getVendorId());
            }
            if (criteria.getVendorItemNo() != null) {
                obj.addLikeClause(ItemMaster.PROP_VENDORITEMNO,
                        criteria.getVendorItemNo());
                obj.setVendorItemNo(criteria.getVendorItemNo());
            }
            if (criteria.getItemName() != null) {
                obj.addLikeClause(ItemMaster.PROP_DESCRIPTION,
                        criteria.getItemName());
                obj.setDescription(criteria.getItemName());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwItemMaster</i> object containing selection
     * criteria obtained from an instance of <i>ItemMasterDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>itemId</li>
     *            <li>itemSerialNo</li>
     *            <li>itemTypeId</li>
     *            <li>creditorId</li>
     *            <li>vendorItemNo</li>
     *            <li>description</li>
     *            </ul>
     * @return an instance of {@link ItemMaster}
     */
    public static final VwItemMaster createCriteriaExt(ItemMasterDto criteria) {
        VwItemMaster obj = new VwItemMaster();
        if (criteria != null) {
            if (criteria.getItemId() > 0) {
                obj.addCriteria(VwItemMaster.PROP_ID, criteria.getItemId());
                obj.setId(criteria.getItemId());
            }
            if (criteria.getItemSerialNo() != null) {
                obj.addLikeClause(VwItemMaster.PROP_ITEMSERIALNO, criteria.getItemSerialNo());
                obj.setItemSerialNo(criteria.getItemSerialNo());
            }
            if (criteria.getItemTypeId() > 0) {
                obj.addCriteria(VwItemMaster.PROP_ITEMTYPEID, criteria.getItemTypeId());
                obj.setItemTypeId(criteria.getItemTypeId());
            }
            if (criteria.getVendorId() > 0) {
                obj.addCriteria(VwItemMaster.PROP_VENDORID, criteria.getVendorId());
                obj.setVendorId(criteria.getVendorId());
            }
            if (criteria.getVendorItemNo() != null) {
                obj.addLikeClause(VwItemMaster.PROP_VENDORITEMNO, criteria.getVendorItemNo());
                obj.setVendorItemNo(criteria.getVendorItemNo());
            }
            if (criteria.getItemName() != null) {
                obj.addLikeClause(VwItemMaster.PROP_DESCRIPTION, criteria.getItemName());
                obj.setDescription(criteria.getItemName());
            }

            // UI-30: Added unit cost and its predicate
            if (criteria.getUnitCost() > 0) {
                if (RMT2String2.isNotEmpty(criteria.getUnitCostPredicate())
                        && !criteria.getUnitCostPredicate().equals(GeneralConst.COND_OPS_EQUALS)) {
                    StringBuilder sql = new StringBuilder();
                    sql.append(" unit_cost ");
                    sql.append(GeneralConst.translateCondOps(criteria.getUnitCostPredicate()));
                    sql.append(" ");
                    sql.append(criteria.getUnitCost());
                    obj.addCustomCriteria(sql.toString());
                }
                else {
                    obj.addCriteria(VwItemMaster.PROP_UNITCOST, criteria.getUnitCost());
                }
            }

            // UI-30: Added quantity on hand and its predicate
            if (criteria.getQtyOnHand() > 0) {
                if (RMT2String2.isNotEmpty(criteria.getQtyOnHandPredicate())
                        && !criteria.getQtyOnHandPredicate().equals(GeneralConst.COND_OPS_EQUALS)) {
                    StringBuilder sql = new StringBuilder();
                    sql.append(" qty_on_hand ");
                    sql.append(GeneralConst.translateCondOps(criteria.getQtyOnHandPredicate()));
                    sql.append(" ");
                    sql.append(criteria.getQtyOnHand());
                    obj.addCustomCriteria(sql.toString());
                }
                else {
                    obj.addCriteria(VwItemMaster.PROP_QTYONHAND, criteria.getQtyOnHand());
                }
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwVendorItems</i> object containing selection
     * criteria obtained from an instance of <i>VendorItemDto</i>.
     * 
     * @param criteria
     *            an instance of {@link VendorItemDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>itemId</li>
     *            <li>itemSerialNo</li>
     *            <li>creditorId</li>
     *            <li>vendorItemNo</li>
     *            </ul>
     * @return an instance of {@link VwVendorItems}
     */
    public static final VwVendorItems createCriteria(VendorItemDto criteria) {
        VwVendorItems obj = new VwVendorItems();
        if (criteria != null) {
            if (criteria.getItemId() > 0) {
                obj.addCriteria(VendorItems.PROP_ITEMID, criteria.getItemId());
                obj.setItemId(criteria.getItemId());
            }
            if (criteria.getItemSerialNo() != null) {
                obj.addLikeClause(VendorItems.PROP_ITEMSERIALNO,
                        criteria.getItemSerialNo());
                obj.setItemSerialNo(criteria.getItemSerialNo());
            }
            if (criteria.getVendorId() > 0) {
                obj.addCriteria(VendorItems.PROP_CREDITORID,
                        criteria.getVendorId());
                obj.setCreditorId(criteria.getVendorId());
            }
            if (criteria.getVendorItemNo() != null) {
                obj.addLikeClause(VendorItems.PROP_VENDORITEMNO,
                        criteria.getVendorItemNo());
                obj.setVendorItemNo(criteria.getVendorItemNo());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ItemMasterType</i> object containing selection
     * criteria obtained from an instance of <i>ItemMasterTypeDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterTypeDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>itemTypeId</li>
     *            <li>description</li>
     *            </ul>
     * @return an instance of {@link ItemMasterType}
     */
    public static final ItemMasterType createCriteria(ItemMasterTypeDto criteria) {
        ItemMasterType obj = new ItemMasterType();
        if (criteria != null) {
            if (criteria.getItemTypeId() > 0) {
                obj.addCriteria(ItemMasterType.PROP_ITEMTYPEID,
                        criteria.getItemTypeId());
                obj.setItemTypeId(criteria.getItemTypeId());
            }
            if (criteria.getItemTypeDescription() != null) {
                obj.addLikeClause(ItemMasterType.PROP_DESCRIPTION,
                        criteria.getItemTypeDescription());
                obj.setDescription(criteria.getItemTypeDescription());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ItemMasterStatus</i> object containing
     * selection criteria obtained from an instance of
     * <i>ItemMasterStatusDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>itemStatusId</li>
     *            <li>description</li>
     *            </ul>
     * @return an instance of {@link ItemMasterStatus}
     */
    public static final ItemMasterStatus createCriteria(
            ItemMasterStatusDto criteria) {
        ItemMasterStatus obj = new ItemMasterStatus();
        if (criteria != null) {
            if (criteria.getEntityId() > 0) {
                obj.addCriteria(ItemMasterStatus.PROP_ITEMSTATUSID,
                        criteria.getEntityId());
                obj.setItemStatusId(criteria.getEntityId());
            }
            if (criteria.getEntityName() != null) {
                obj.addLikeClause(ItemMasterStatus.PROP_DESCRIPTION,
                        criteria.getEntityName());
                obj.setDescription(criteria.getEntityName());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>ItemMasterStatusHist</i> object containing
     * selection criteria obtained from an instance of
     * <i>ItemMasterStatusHistDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusHist} which the
     *            following properties are recognized:
     *            <ul>
     *            <li>itemStatusHistId</li>
     *            <li>itemId</li>
     *            <li>itemStatusId</li>
     *            </ul>
     * @return an instance of {@link ItemMasterStatusHist}
     */
    public static final ItemMasterStatusHist createCriteria(ItemMasterStatusHistDto criteria) {
        ItemMasterStatusHist obj = new ItemMasterStatusHist();
        if (criteria != null) {
            if (criteria.getEntityId() > 0) {
                obj.addCriteria(ItemMasterStatusHist.PROP_ITEMSTATUSHISTID, criteria.getEntityId());
                obj.setItemStatusHistId(criteria.getEntityId());
            }
            if (criteria.getItemId() > 0) {
                obj.addCriteria(ItemMasterStatusHist.PROP_ITEMID, criteria.getItemId());
                obj.setItemId(criteria.getItemId());
            }
            if (criteria.getItemStatusId() > 0) {
                obj.addCriteria(ItemMasterStatusHist.PROP_ITEMSTATUSID, criteria.getItemStatusId());
                obj.setItemStatusId(criteria.getItemStatusId());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwItemStatusHistory</i> object containing
     * selection criteria obtained from an instance of
     * <i>ItemMasterStatusHistDto</i>.
     * 
     * @param criteria
     *            an instance of {@link VwItemStatusHistory} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>itemStatusHistId</li>
     *            <li>itemId</li>
     *            <li>itemStatusId</li>
     *            </ul>
     * @return an instance of {@link ItemMasterStatusHist}
     */
    public static final VwItemStatusHistory createVwItemHistCriteria(ItemMasterStatusHistDto criteria) {
        VwItemStatusHistory obj = new VwItemStatusHistory();
        if (criteria != null) {
            if (criteria.getEntityId() > 0) {
                obj.addCriteria(VwItemStatusHistory.PROP_ITEMSTATUSHISTID, criteria.getEntityId());
                obj.setItemStatusHistId(criteria.getEntityId());
            }
            if (criteria.getItemId() > 0) {
                obj.addCriteria(VwItemStatusHistory.PROP_ITEMID, criteria.getItemId());
                obj.setItemId(criteria.getItemId());
            }
            if (criteria.getItemStatusId() > 0) {
                obj.addCriteria(VwItemStatusHistory.PROP_ITEMSTATUSID, criteria.getItemStatusId());
                obj.setItemStatusId(criteria.getItemStatusId());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwItemAssociations</i> object containing
     * selection criteria obtained from an instance of
     * <i>ItemAssociationDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ItemAssociationDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>itemId</li>
     *            <li>assocId</li>
     *            <li>assocItemId</li>
     *            <li>assocType</li>
     *            </ul>
     * @return an instance of {@link VwItemAssociations}
     */
    public static final VwItemAssociations createCriteria(
            ItemAssociationDto criteria) {
        VwItemAssociations obj = new VwItemAssociations();
        if (criteria != null) {
            if (criteria.getAssociationId() > 0) {
                obj.addCriteria(VwItemAssociations.PROP_ASSOCID,
                        criteria.getAssociationId());
            }
            if (criteria.getAssociationItemId() > 0) {
                obj.addCriteria(VwItemAssociations.PROP_ASSOCITEMID,
                        criteria.getAssociationItemId());
            }
            if (criteria.getItemId() > 0) {
                obj.addCriteria(VwItemAssociations.PROP_ITEMID,
                        criteria.getItemId());
            }
            if (criteria.getAssociationType() != null) {
                obj.addCriteria(VwItemAssociations.PROP_ASSOCTYPE,
                        criteria.getAssociationType());
            }
        }
        return obj;
    }
}
