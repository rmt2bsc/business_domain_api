package org.modules.timesheet.invoice;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.modules.ProjectTrackerApiConst;
import org.rmt2.jaxb.InventoryItemType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.SalesInvoiceType;
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
        return this.createApi(ProjectTrackerApiConst.DEFAULT_CONTEXT_NAME);
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
        sot.setSalesOrderItems(f.createSalesOrderItemListType());
        SalesOrderType.SalesOrderId soIdObj = f.createSalesOrderTypeSalesOrderId();
        soIdObj.setValue(BigInteger.valueOf(so.getSalesOrderId()));
        soIdObj.setCustomerAccountNo(so.getAccountNo());
        soIdObj.setCustomerId(BigInteger.valueOf(so.getCustomerId()));
        soIdObj.setCustomerName(null);
        soIdObj.setInvoiced(so.getInvoiced() == 1);
        sot.setSalesOrderId(soIdObj);
        
        sot.setOrderTotal(BigDecimal.valueOf(so.getOrderTotal()));
        
        SalesInvoiceType sit = f.createSalesInvoiceType();
        sot.setInvoiceDetails(sit);
        sit.setInvoiceTotal(BigDecimal.valueOf(so.getOrderTotal()));
        
        // TODO:  might need to add reason to XML schema in the event it is used as part of the sales order
//        sot.set(so.getReason());
        if (so.getItems() != null) {
            int count = 0;
            for (InvoiceDetailsBean item : so.getItems()) {
                SalesOrderItemType soit = InvoiceTimesheetApiFactory.createJaxbSalesOrderItemInstance(item);
                sot.getSalesOrderItems().getSalesOrderItem().add(soit);
                count++;
            }
            sit.setItemCount(BigInteger.valueOf(count));
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
    public static SalesOrderItemType createJaxbSalesOrderItemInstance(InvoiceDetailsBean soi) {
        ObjectFactory f = new ObjectFactory();
        SalesOrderItemType soit = f.createSalesOrderItemType();
        InventoryItemType imt = f.createInventoryItemType();
        SalesOrderItemType.SalesOrderItemId soItemIdObj = f.createSalesOrderItemTypeSalesOrderItemId();
        soItemIdObj.setValue(BigInteger.valueOf(soi.getSoItemId()));
        soItemIdObj.setSalesOrderId(BigInteger.valueOf(soi.getSoId()));
        soit.setItem(imt);
        imt.setItemId(BigInteger.valueOf(soi.getItemId()));
        soit.setItemNameOverride(soi.getItemNameOverride());
        soit.setOrderQty(BigInteger.valueOf(Double.valueOf(soi.getOrderQty()).longValue()));
        soit.setBackOrderQty(BigDecimal.valueOf(soi.getBackOrderQty()));
        soit.setUnitCost(BigDecimal.valueOf(soi.getInitUnitCost()));
        soit.setMarkup(BigDecimal.valueOf(soi.getInitMarkup()));
        return soit;
    }

}
