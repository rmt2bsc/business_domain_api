package org.dto.adapter.orm.transaction.sales;

import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.dao.mapping.orm.rmt2.VwSalesOrderInvoice;
import org.dao.mapping.orm.rmt2.VwSalesorderItemsBySalesorder;
import org.dto.SalesInvoiceDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.SalesOrderStatusDto;
import org.dto.SalesOrderStatusHistDto;

import com.RMT2Base;

/**
 * A factory containing several adapters which function to create sales
 * transaction related entities.
 * 
 * @author Roy Terrell.
 * 
 */
public class Rmt2SalesOrderDtoFactory extends RMT2Base {

    /**
     * Create an instance of <i>SalesOrderStatusDto</i>.
     * <p>
     * A brand new instance of SalesOrderStatusDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of SalesOrderStatusDto.
     * 
     * @param ormBean
     *            an instance of {@link SalesOrderStatus}
     * @return an instance of {@link SalesOrderStatusDto}.
     */
    public static final SalesOrderStatusDto createSalesOrderStatusInstance(
            SalesOrderStatus ormBean) {
        return new SalesOrderStatusRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>SalesOrderStatusHistDto</i>.
     * <p>
     * A brand new instance of SalesOrderStatusHistDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of SalesOrderStatusHistDto.
     * 
     * @param ormBean
     *            an instance of {@link SalesOrderStatus}
     * @return an instance of {@link SalesOrderStatusHistDto}.
     */
    public static final SalesOrderStatusHistDto createSalesOrderStatusHistoryInstance(
            SalesOrderStatusHist ormBean) {
        return new SalesOrderStatusHistoryRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>SalesOrderDto</i>.
     * <p>
     * A brand new instance of SalesOrderDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * SalesOrderDto.
     * 
     * @param ormBean
     *            an instance of {@link SalesOrder}
     * @return an instance of {@link SalesOrderDto}.
     */
    public static final SalesOrderDto createSalesOrderInstance(
            SalesOrder ormBean) {
        return new SalesOrderRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>SalesOrderItemDto</i>.
     * <p>
     * A brand new instance of SalesOrderItemDto is created when <i>ormBean</i>
     * is null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * SalesOrderItemDto.
     * 
     * @param ormBean
     *            an instance of {@link SalesOrderItems}
     * @return an instance of {@link SalesOrderItemDto}.
     */
    public static final SalesOrderItemDto createSalesOrderItemInstance(
            SalesOrderItems ormBean) {
        return new SalesOrderItemRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>SalesOrderItemDto</i>.
     * <p>
     * A brand new instance of SalesOrderItemDto is created when <i>ormBean</i>
     * is null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * SalesOrderItemDto.
     * 
     * @param ormBean
     *            an instance of {@link VwSalesorderItemsBySalesorder}
     * @return an instance of {@link SalesOrderItemDto}.
     */
    public static final SalesOrderItemDto createSalesOrderItemInstance(
            VwSalesorderItemsBySalesorder ormBean) {
        return new SalesOrderItemRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>SalesInvoiceDto</i>.
     * <p>
     * A brand new instance of SalesInvoiceDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * SalesInvoiceDto.
     * 
     * @param ormBean
     *            an instance of {@link SalesInvoice}
     * @return an instance of {@link SalesInvoiceDto}.
     */
    public static final SalesInvoiceDto createSalesIvoiceInstance(
            SalesInvoice ormBean) {
        return new SalesInvoiceRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>SalesInvoiceDto</i>.
     * <p>
     * A brand new instance of SalesInvoiceDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * SalesInvoiceDto.
     * 
     * @param ormBean
     *            an instance of {@link VwSalesOrderInvoice}
     * @return an instance of {@link SalesInvoiceDto}.
     */
    public static final SalesInvoiceDto createSalesIvoiceInstance(VwSalesOrderInvoice ormBean) {
        return new SalesInvoiceRmt2OrmAdapter(ormBean);
    }
}
