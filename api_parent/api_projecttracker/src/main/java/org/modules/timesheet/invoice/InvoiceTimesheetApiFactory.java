package org.modules.timesheet.invoice;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.SalesOrderItemType;
import org.rmt2.jaxb.SalesOrderType;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the invoicing of timesheets
 * within the Project Tracker API module.
 * 
 * @author Roy Terrell
 * 
 */
public class InvoiceTimesheetApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public InvoiceTimesheetApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link InvoiceTimesheetApi} using the transaction
     * api implementation.
     * 
     * @return an instance of {@link InvoiceTimesheetApi}
     */
    public InvoiceTimesheetApi createApi() {
        // InvoiceTimesheetApiImpl api = new InvoiceTimesheetApiImpl();
        // return api;
        return this.createApi();
    }

    /**
     * Creates an instance of {@link InvoiceTimesheetApi} using the transaction
     * api implementation.
     * 
     * @param appName
     *            the application name
     * @return an instance of {@link InvoiceTimesheetApi}
     */
    public InvoiceTimesheetApi createApi(String appName) {
        InvoiceTimesheetApiImpl api = new InvoiceTimesheetApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link InvoiceTimesheetApi} using the transaction
     * api implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link InvoiceTimesheetApi} or null when
     *         <i>connection</i> is null.
     */
    public InvoiceTimesheetApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        InvoiceTimesheetApiImpl api = new InvoiceTimesheetApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

    /**
     * Creates a JAXB SalesOrderType object.
     * 
     * @param so
     *            an instance of {@link InvoiceBean}
     * @return {@link SalesOrderType}
     */
    public static SalesOrderType createJaxbSalesOrderInstance(InvoiceBean so) {
        ObjectFactory f = new ObjectFactory();
        SalesOrderType sot = f.createSalesOrderType();
        sot.getCustomer().setAccountNo(so.getAccountNo());
        sot.setSalesOrderId(BigInteger.valueOf(so.getSalesOrderId()));
        sot.getCustomer().setCustomerId(BigInteger.valueOf(so.getCustomerId()));
        sot.setInvoiced(so.getInvoiced() == 1);
        sot.setOrderTotal(BigDecimal.valueOf(so.getOrderTotal()));
        sot.setSalesOrderReason(so.getReason());
        if (so.getItems() != null) {
            for (InvoiceDetailsBean item : so.getItems()) {
                SalesOrderItemType soit = InvoiceTimesheetApiFactory
                        .createJaxbSalesOrderItemInstance(item);
                sot.getItems().add(soit);
            }
        }
        return sot;
    }

    /**
     * Creates a JAXB SalesOrderItemType object.
     * 
     * @param soi
     *            an instance of {@link InvoiceDetailsBean}
     * @return {@link SalesOrderItemType}
     */
    public static SalesOrderItemType createJaxbSalesOrderItemInstance(
            InvoiceDetailsBean soi) {
        ObjectFactory f = new ObjectFactory();
        SalesOrderItemType soit = f.createSalesOrderItemType();
        soit.setsetItemId(BigInteger.valueOf(soi.getItemId()));
        soit.setSalesOrderItemId(BigInteger.valueOf(soi.getSoItemId()));
        soit.setSalesOrderId(BigInteger.valueOf(soi.getSoId()));
        soit.setItemName(soi.getItemNameOverride());
        soit.setOrderQty(BigInteger.valueOf(Double.valueOf(soi.getOrderQty())
                .longValue()));
        soit.setBackOrderQty(BigDecimal.valueOf(soi.getBackOrderQty()));
        soit.setUnitCost(BigDecimal.valueOf(soi.getInitUnitCost()));
        soit.setMarkup(BigDecimal.valueOf(soi.getInitMarkup()));
        return soit;
    }

}
