package org.dto.adapter.orm.transaction.purchaseorder;

import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.PurchaseOrderItemReturn;
import org.dao.mapping.orm.rmt2.PurchaseOrderItems;
import org.dao.mapping.orm.rmt2.PurchaseOrderReturn;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatus;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatusHist;
import org.dao.mapping.orm.rmt2.VendorItems;
import org.dao.mapping.orm.rmt2.VwPurchaseOrderList;
import org.dao.mapping.orm.rmt2.VwVendorItemPurchaseOrderItem;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.PurchaseOrderReturnDto;
import org.dto.PurchaseOrderReturnItemDto;
import org.dto.PurchaseOrderStatusDto;
import org.dto.PurchaseOrderStatusHistDto;
import org.dto.VendorItemDto;
import org.dto.VwVendorItemDto;

import com.RMT2Base;

/**
 * A factory containing several adapters which function to create purchase order
 * related entities.
 * 
 * @author Roy Terrell.
 * 
 */
public class Rmt2PurchaseOrderDtoFactory extends RMT2Base {

    /**
     * Create an instance of <i>PurchaseOrderDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderDto is created when <i>ormBean</i>
     * is null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * PurchaseOrderDto.
     * 
     * @param ormBean
     *            an instance of {@link PurchaseOrder}
     * @return an instance of {@link PurchaseOrderDto}.
     */
    public static final PurchaseOrderDto createPurchaseOrderInstance(
            PurchaseOrder ormBean) {
        return new PurchaseOrderRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>PurchaseOrderDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderDto is created when <i>ormBean</i>
     * is null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * PurchaseOrderDto.
     * 
     * @param ormBean
     *            an instance of {@link VwPurchaseOrderList}
     * @return an instance of {@link PurchaseOrderDto}.
     */
    public static final PurchaseOrderDto createPurchaseOrderExtInstance(
            VwPurchaseOrderList ormBean) {
        return new PurchaseOrderRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>PurchaseOrderItemDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderItemDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of PurchaseOrderItemDto.
     * 
     * @param ormBean
     *            an instance of {@link PurchaseOrderItems}
     * @return an instance of {@link PurchaseOrderItemDto}.
     */
    public static final PurchaseOrderItemDto createPurchaseOrderItemInstance(PurchaseOrderItems ormBean) {
        PurchaseOrder po = null;
        if (ormBean != null) {
            po = new PurchaseOrder();
            po.setPoId(ormBean.getPoId());    
        }
        return new PurchaseOrderItemRmt2OrmAdapter(ormBean, po);
    }

    /**
     * Create an instance of <i>PurchaseOrderItemDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderItemDto is created when
     * <i>poItem</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of PurchaseOrderItemDto.
     * 
     * @param poItem
     *            an instance of {@link PurchaseOrderItems}
     * @param po
     *            an instance of {@link PurchaseOrder}
     * @return an instance of {@link PurchaseOrderItemDto}.
     */
    public static final PurchaseOrderItemDto createPurchaseOrderItemInstance(
            PurchaseOrderItems poItem, PurchaseOrder po) {
        return new PurchaseOrderItemRmt2OrmAdapter(poItem, po);
    }

    /**
     * Create an instance of <i>PurchaseOrderItemDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderItemDto is created when
     * <i>poItem</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of PurchaseOrderItemDto.
     * 
     * @param poItem
     *            an instance of {@link PurchaseOrderItems}
     * @param vendorItem
     *            an instance of {@link VwVendorItemPurchaseOrderItem}
     * @param po
     *            an instance of {@link PurchaseOrder}
     * @return an instance of {@link PurchaseOrderItemDto}.
     */
    public static final PurchaseOrderItemDto createPurchaseOrderItemInstance(
            PurchaseOrderItems poItem,
            VwVendorItemPurchaseOrderItem vendorItem, PurchaseOrder po) {
        return new PurchaseOrderItemRmt2OrmAdapter(poItem, po);
    }
    
    /**
     * Create an instance of <i>PurchaseOrderItemDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderItemDto is created when
     * <i>vendorItem</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of PurchaseOrderItemDto.
     * 
     * @param vendorItem
     *            an instance of {@link VwVendorItemPurchaseOrderItem}
     * @return an instance of {@link PurchaseOrderItemDto}.
     */
    public static final PurchaseOrderItemDto createPurchaseOrderExternalItemInstance(VwVendorItemPurchaseOrderItem vendorItem) {
        return new PurchaseOrderItemRmt2OrmAdapter(vendorItem);
    }
    
    

    /**
     * Create an instance of <i>PurchaseOrderReturnDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderReturnDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of PurchaseOrderReturnDto.
     * 
     * @param ormBean
     *            an instance of {@link VwPurchaseOrderList}
     * @return an instance of {@link PurchaseOrderReturnDto}.
     */
    public static final PurchaseOrderReturnDto createPurchaseOrderReturnInstance(
            PurchaseOrderReturn ormBean) {
        return new PurchaseOrderReturnRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>PurchaseOrderReturnItemDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderReturnItemDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of PurchaseOrderReturnItemDto.
     * 
     * @param ormBean
     *            an instance of {@link PurchaseOrderItemReturn}
     * @return an instance of {@link PurchaseOrderReturnItemDto}.
     */
    public static final PurchaseOrderReturnItemDto createPurchaseOrderReturnItemInstance(
            PurchaseOrderItemReturn ormBean) {
        return new PurchaseOrderReturnItemRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>PurchaseOrderStatusDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderStatusDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of PurchaseOrderStatusDto.
     * 
     * @param ormBean
     *            an instance of {@link PurchaseOrderStatus}
     * @return an instance of {@link PurchaseOrderStatusDto}.
     */
    public static final PurchaseOrderStatusDto createPurchaseOrderStatusInstance(
            PurchaseOrderStatus ormBean) {
        return new PurchaseOrderStatusRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>PurchaseOrderStatusHistDto</i>.
     * <p>
     * A brand new instance of PurchaseOrderStatusHistDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of PurchaseOrderStatusHistDto.
     * 
     * @param ormBean
     *            an instance of {@link PurchaseOrderStatusHist}
     * @return an instance of {@link PurchaseOrderStatusHistDto}.
     */
    public static final PurchaseOrderStatusHistDto createPurchaseOrderStatusHistoryInstance(
            PurchaseOrderStatusHist ormBean) {
        PurchaseOrderStatus status = null;
        if (ormBean != null) {
            status = new PurchaseOrderStatus();
            status.setPoStatusId(ormBean.getPoStatusId());
        }
        return new PurchaseOrderStatusHistoryRmt2OrmAdapter(ormBean, status);
    }

    
    /**
     * Create an instance of <i>VendorItemDto</i>.
     * <p>
     * A brand new instance of VendorItemDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of VendorItemDto.
     * 
     * @param ormBean
     *            an instance of {@link VendorItems}
     * @return an instance of {@link VendorItemDto}.
     */
    public static final VendorItemDto createVendorItemInstance(
            VendorItems ormBean) {
        return new VendorItemsRmt2OrmAdapter(ormBean);
    }
    
    /**
     * Create an instance of <i>VwVendorItemDto</i>.
     * <p>
     * A brand new instance of VwVendorItemDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of VwVendorItemDto.
     * 
     * @param ormBean
     *            an instance of {@link VwVendorItems}
     * @return an instance of {@link VwVendorItemDto}.
     */
    public static final VwVendorItemDto createVendorItemExtInstance(
            VwVendorItems ormBean) {
        return new VwVendorItemsRmt2OrmAdapter(ormBean);
    }
}
