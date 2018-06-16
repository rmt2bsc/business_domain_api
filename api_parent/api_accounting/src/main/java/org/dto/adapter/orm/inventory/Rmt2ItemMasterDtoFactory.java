package org.dto.adapter.orm.inventory;

import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.VwItemAssociations;
import org.dao.mapping.orm.rmt2.VwItemMaster;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.ItemAssociationDto;
import org.dto.ItemMasterDto;
import org.dto.ItemMasterStatusDto;
import org.dto.ItemMasterStatusHistDto;
import org.dto.ItemMasterTypeDto;
import org.dto.VendorItemDto;

import com.RMT2Base;

/**
 * A factory containing several adapters which function to create various item
 * master related objects.
 * 
 * @author Roy Terrell.
 * 
 */
public class Rmt2ItemMasterDtoFactory extends RMT2Base {

    /**
     * Create an instance of <i>ItemMasterDto</i>.
     * <p>
     * A brand new instance of ItemMasterDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * ItemMasterDto.
     * 
     * @param ormBean
     *            an instance of {@link ItemMaster}
     * @return an instance of {@link ItemMasterDto}.
     */
    public static final ItemMasterDto createItemMasterInstance(
            ItemMaster ormBean) {
        return new ItemMasterRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ItemMasterDto</i>.
     * <p>
     * A brand new instance of ItemMasterDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * ItemMasterDto.
     * 
     * @param ormBean
     *            an instance of {@link VwItemMaster}
     * @return an instance of {@link ItemMasterDto}.
     */
    public static final ItemMasterDto createItemMasterInstance(
            VwItemMaster ormBean) {
        return new ItemMasterRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ItemAssociationDto</i>.
     * <p>
     * A brand new instance of ItemAssociationDto is created when <i>ormBean</i>
     * is null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * ItemAssociationDto.
     * 
     * @param ormBean
     *            an instance of {@link VwItemAssociations}
     * @return an instance of {@link ItemAssociationDto}.
     */
    public static final ItemAssociationDto createItemAssociationInstance(
            VwItemAssociations ormBean) {
        return new ItemAssocRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ItemMasterStatusDto</i>.
     * <p>
     * A brand new instance of ItemMasterStatusDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of ItemMasterStatusDto.
     * 
     * @param ormBean
     *            an instance of {@link ItemMasterStatus}
     * @return an instance of {@link ItemMasterStatusDto}.
     */
    public static final ItemMasterStatusDto createItemStatusInstance(
            ItemMasterStatus ormBean) {
        return new ItemStatusRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ItemMasterStatusHistDto</i>.
     * <p>
     * A brand new instance of ItemMasterStatusHistDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of ItemMasterStatusHistDto.
     * 
     * @param ormBean
     *            an instance of {@link ItemMasterStatusHist}
     * @return an instance of {@link ItemMasterStatusHistDto}.
     */
    public static final ItemMasterStatusHistDto createItemStatusHistoryInstance(
            ItemMasterStatusHist ormBean) {
        return new ItemStatusHistRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>ItemMasterTypeDto</i>.
     * <p>
     * A brand new instance of ItemMasterTypeDto is created when <i>ormBean</i>
     * is null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * ItemMasterTypeDto.
     * 
     * @param ormBean
     *            an instance of {@link ItemMasterType}
     * @return an instance of {@link ItemMasterTypeDto}.
     */
    public static final ItemMasterTypeDto createItemTypeInstance(
            ItemMasterType ormBean) {
        return new ItemTypeRmt2OrmAdapter(ormBean);
    }

     /**
     * Create an instance of <i>VendorItemDto</i>.
     * <p>
     * A brand new instance of VendorItemDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * VendorItemDto.
     *
     * @param ormBean
     * an instance of {@link VwVendorItems}
     * @return an instance of {@link VendorItemDto}.
     */
     public static final VendorItemDto createVendorItemInstance(VwVendorItems ormBean) {
         return new VendorItemRmt2OrmAdapter(ormBean);
     }
}
