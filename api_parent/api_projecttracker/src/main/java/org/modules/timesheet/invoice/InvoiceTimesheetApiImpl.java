package org.modules.timesheet.invoice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.timesheet.TimesheetConst;
import org.dao.timesheet.TimesheetDao;
import org.dao.timesheet.TimesheetDaoFactory;
import org.dto.ClientDto;
import org.dto.EmployeeDto;
import org.dto.ProjectEmployeeDto;
import org.dto.TimesheetDto;
import org.dto.TimesheetHistDto;
import org.dto.TimesheetHoursDto;
import org.modules.admin.ProjectApi;
import org.modules.admin.ProjectApiException;
import org.modules.admin.ProjectApiFactory;
import org.modules.employee.EmployeeApi;
import org.modules.employee.EmployeeApiException;
import org.modules.employee.EmployeeApiFactory;
import org.modules.timesheet.TimesheetApi;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.rmt2.jaxb.HeaderType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.RQAccountingInvoiceSalesOrder;
import org.rmt2.jaxb.RSAccountingInvoiceSalesOrder;
import org.rmt2.jaxb.SalesOrderType;
import org.rmt2.util.JaxbPayloadFactory;

import com.api.foundation.AbstractTransactionApiImpl;
import com.api.messaging.webservice.ServiceReturnCode;
import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.messaging.webservice.router.MessageRoutingException;
import com.api.persistence.DaoClient;
import com.util.RMT2File;

/**
 * Implementation of InvoiceTimesheetApi that manages the invoicing of an
 * employee's timesheet activities.
 * 
 * @author Roy Terrell
 * 
 */
class InvoiceTimesheetApiImpl extends AbstractTransactionApiImpl implements
        InvoiceTimesheetApi {

    private static final Logger logger = Logger
            .getLogger(InvoiceTimesheetApiImpl.class);

    private TimesheetApi tsBaseApi;

    private ProjectApi projApi;

    private EmployeeApi empApi;

    private TimesheetDaoFactory daoFact;

    private TimesheetDao dao;

    private List<String> timesheetIdList;

    private double invoiceAmt;

    private InvoiceResultsBean results;

    /**
     * 
     */
    protected InvoiceTimesheetApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmDao();
        this.setSharedDao(this.dao);
        this.createOtherResources(this.dao);
        return;
    }

    /**
     * 
     * @param appName
     */
    protected InvoiceTimesheetApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        this.createOtherResources(this.dao);
        return;
    }

    /**
     * @param dao
     */
    protected InvoiceTimesheetApiImpl(DaoClient dao) {
        super(dao);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
        this.createOtherResources(this.dao);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.RMT2Base#init()
     */
    @Override
    public void init() {
        super.init();
        this.daoFact = new TimesheetDaoFactory();
        return;
    }

    /**
     * 
     * @param connection
     */
    private void createOtherResources(TimesheetDao connection) {
        TimesheetApiFactory tf = new TimesheetApiFactory();
        this.tsBaseApi = tf.createApi(connection);
        ProjectApiFactory pf = new ProjectApiFactory();
        this.projApi = pf.createApi(connection);
        EmployeeApiFactory ef = new EmployeeApiFactory();
        this.empApi = ef.createApi(connection);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.timesheet.invoice.InvoiceTimesheetApi#invoice(java.util.List
     * [])
     */
    @Override
    public int invoice(List<Integer> clientIdList)
            throws InvoiceTimesheetApiException {
        int count = 0;
        if (clientIdList == null) {
            return TimesheetConst.INVOICE_STATE_NO_DATA;
        }
        InvoiceResultsBean results = new InvoiceResultsBean();
        List<Integer> xactList = new ArrayList<Integer>();
        for (Integer clientId : clientIdList) {
            try {
                int xactId = this.startClientBilling(clientId);
                results.setProcessedClientTimesheets(clientId,
                        this.timesheetIdList);
                xactList.add(xactId);
                count++;
            } catch (Exception e) {
                throw new InvoiceTimesheetApiException(e);
            }
        }
        // Complete packaging the results with transaction id's
        results.setClientTransactions(xactList);
        return count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.timesheet.invoice.InvoiceTimesheetApi#invoice(int)
     */
    @Override
    public int invoice(int timesheetId) throws InvoiceTimesheetApiException {
        // Verify timesheet
        TimesheetDto ts;
        try {
            ts = this.tsBaseApi.get(timesheetId);
            if (ts == null) {
                this.msg = "Timesheet Invoice Error:  Timesheet, "
                        + timesheetId + ", does not exist";
                throw new InvoiceTimesheetApiException(this.msg);
            }
        } catch (TimesheetApiException e) {
            this.msg = "Timesheet Invoice Error:  Unable to fetch timesheet by timesheet id, "
                    + timesheetId;
            throw new InvoiceTimesheetApiException(this.msg);
        }
        // Verify client
        ClientDto client;
        try {
            client = this.projApi.getClient(ts.getClientId());
            if (client == null) {
                this.msg = "Timesheet Invoice Error:  Timesheet's client does not exist by client id, "
                        + ts.getClientId();
                throw new InvoiceTimesheetApiException(this.msg);
            }
        } catch (ProjectApiException e) {
            this.msg = "Timesheet Invoice Error:  Unable to fetch timesheet's client by client id, "
                    + ts.getClientId();
            throw new InvoiceTimesheetApiException(this.msg);
        }

        // Invoice the timesheet
        List<TimesheetDto> tsList = new ArrayList<TimesheetDto>();
        tsList.add(ts);
        int xactId = this.startClientBilling(client, tsList);

        // Package results
        InvoiceResultsBean results = new InvoiceResultsBean();
        results.setProcessedClientTimesheets(client.getClientId(),
                this.timesheetIdList);
        List<Integer> xactList = new ArrayList<Integer>();
        xactList.add(xactId);
        results.setClientTransactions(xactList);
        return xactId;
    }

    /**
     * Invoices all timesheets pertaining to a single client identified by
     * <i>clientId</i> when engaged from the bulk invoice feature. The invoices
     * are sent to the sales order subsystems for processing. This request is
     * submitted by an employee of manager status.
     * 
     * @param clientId
     *            The id of the client.
     * @return int as the new sales order id.
     * @throws InvoiceTimesheetApiException
     *             client validation errors, problem gathering timesheets, or
     *             sales order processing errors or database access errors.
     */
    private int startClientBilling(int clientId)
            throws InvoiceTimesheetApiException {
        ClientDto client = null;
        try {
            // Get client object
            client = this.projApi.getClient(clientId);
            // Get client approved timesheets
            List<TimesheetDto> ts = this.tsBaseApi.getClientApproved(clientId);
            if (ts == null) {
                return 0;
            }
            // Begin to bill the client's timesheets
            int xactId = this.startClientBilling(client, ts);
            return xactId;
        } catch (Exception e) {
            throw new InvoiceTimesheetApiException(e);
        }
    }

    /**
     * Start the process of billing the client's timesheets.
     * 
     * @param client
     *            a {@link ClientDto} object
     * @param timesheets
     *            a List of {@link TimesheetDto} objects
     * @return the invoice id
     * @throws InvoiceTimesheetApiException
     */
    private int startClientBilling(ClientDto client,
            List<TimesheetDto> timesheets) throws InvoiceTimesheetApiException {
        int invoiceId;
        try {
            InvoiceBean so = this.setupBilling(client, timesheets);
            ServiceReturnCode rc = this.sendClientBilling(so);
            if (rc.getCode() <= 0) {
                throw new InvoiceTimesheetApiException(rc.getMessage());
            }
            invoiceId = rc.getCode();
            this.postInvoice(timesheets, invoiceId);
            return invoiceId;
        } catch (Exception e) {
            throw new InvoiceTimesheetApiException(e);
        }
    }

    /**
     * Creates a sales order for one or more timesheets pertaining to the
     * client's project(s) and invoices each sales order.
     * 
     * @param invBean
     *            An instance of {@link InvoiceBean} representing the timesheet
     *            that is to be billed.
     * @return An instance of {@link ServiceReturnCode}
     * @throws InvoiceTimesheetApiException
     *             errors pertaining to sending billing data to the accounting
     *             system or general Systems errors.
     */
    private ServiceReturnCode sendClientBilling(InvoiceBean invBean)
            throws InvoiceTimesheetApiException {
        ObjectFactory f = new ObjectFactory();
        String serviceId = "RQ_accounting_invoice_sales_order";
        RQAccountingInvoiceSalesOrder ws = f
                .createRQAccountingInvoiceSalesOrder();
        HeaderType header = JaxbPayloadFactory.createHeader("routing", "app",
                "module", serviceId, "SYNC", "REQUEST", this.getApiUser());
        ws.setHeader(header);

        // Setup customer's sales order that will be invoiced
        SalesOrderType sot = InvoiceTimesheetApiFactory
                .createJaxbSalesOrderInstance(invBean);
        ws.setSalesOrder(sot);

        // Send message directly via JMS
        MessageRouterHelper helper = new MessageRouterHelper();
        Object results = null;
        try {
            results = helper.routeSerialMessage(serviceId, ws);
            RSAccountingInvoiceSalesOrder response = null;
            if (results instanceof RSAccountingInvoiceSalesOrder) {
                response = (RSAccountingInvoiceSalesOrder) results;
            }
            ServiceReturnCode rc = new ServiceReturnCode();
            rc.setCode(response.getInvoiceResult().getReturnCode().intValue());
            rc.setMessage(response.getInvoiceResult().getReturnMessage());
            return rc;
        } catch (MessageRoutingException e) {
            throw new InvoiceTimesheetApiException(e);
        }
    }

    /**
     * Set up the billing for alL timesheets belonging to a clinet.
     * 
     * @param client
     *            an instance of {@link ClientDto}
     * @param timesheets
     *            A List of {@link TimesheetDto}
     * @return a {@link InvoiceBean} object
     * @throws InvoiceTimesheetApiException
     */
    private InvoiceBean setupBilling(ClientDto client,
            List<TimesheetDto> timesheets) throws InvoiceTimesheetApiException {
        // Setup sales order object
        InvoiceBean invBean = new InvoiceBean();
        invBean.setCustomerId(client.getClientId());

        // Process each timesheet as a single sales order item
        List<InvoiceDetailsBean> items = new ArrayList<InvoiceDetailsBean>();
        StringBuffer tsDisplayIds = new StringBuffer();
        this.invoiceAmt = 0;
        this.timesheetIdList = new ArrayList<String>();
        for (TimesheetDto ts : timesheets) {
            InvoiceDetailsBean soi = this.setupBilling(ts);
            if (soi == null) {
                continue;
            }
            this.timesheetIdList.add(String.valueOf(ts.getTimesheetId()));
            this.invoiceAmt += soi.getInitUnitCost() * soi.getOrderQty();
            items.add(soi);

            if (tsDisplayIds.length() > 0) {
                tsDisplayIds.append(", ");
            }
            tsDisplayIds.append(ts.getDisplayValue());
        }

        String xactReason = null;
        if (tsDisplayIds.length() > 0) {
            xactReason = "Invoiced Time Sheet(s): " + tsDisplayIds.toString();
        }
        invBean.setItems(items);
        invBean.setOrderTotal(this.invoiceAmt);
        invBean.setReason(xactReason);
        invBean.setDateCreated(new Date());
        return invBean;
    }

    /**
     * Set up billing for a single timesheet.
     * 
     * @param ts
     *            a {@link TimesheetDto} object.
     * @return {@link InvoiceDetailsBean}
     * @throws InvoiceTimesheetApiException
     */
    private InvoiceDetailsBean setupBilling(TimesheetDto ts)
            throws InvoiceTimesheetApiException {

        int timesheetId = ts.getTimesheetId();
        try {
            // Get employee
            EmployeeDto employee = this.empApi.getEmployee(ts.getEmpId());

            // Calculate timesheet invoice amount
            double timesheetAmt = this.calculateInvoice(timesheetId);
            double hours = this.calculateBillableHours(timesheetId);

            TimesheetHistDto status = this.tsBaseApi
                    .getCurrentStatus(timesheetId);
            if (status == null) {
                return null;
            }
            if (status.getStatusId() != TimesheetConst.STATUS_APPROVED) {
                return null;
            }

            // Create Sales Order Item
            InvoiceDetailsBean invDetailBean = new InvoiceDetailsBean();
            String temp = RMT2File.getAppParmProperty("invoice_item_id");
            int invoiceMasterItemId = Integer.parseInt(temp);
            invDetailBean.setItemId(invoiceMasterItemId);

            // Build complex description line item.
            String descr = employee.getEmployeeFullname() + " for " + hours
                    + " hours";
            if (ts.getExtRef() != null && !ts.getExtRef().equals("")) {
                descr += ", " + ts.getExtRef();
            }
            invDetailBean.setItemNameOverride(descr);
            invDetailBean.setInitUnitCost(timesheetAmt);
            invDetailBean.setInitMarkup(1);
            invDetailBean.setOrderQty(1);
            return invDetailBean;
        } catch (Exception e) {
            throw new InvoiceTimesheetApiException(e);
        }
    }

    /**
     * Updates the timesheet's status and reference number after the timesheet
     * has been invoiced.
     * 
     * @param timesheets
     *            A List of {@link TimesheetDto} objects
     * @param invoiceId
     *            The invoice id that was created for the sales order.
     * @throws InvoiceTimesheetApiException
     */
    private void postInvoice(List<TimesheetDto> timesheets, int invoiceId)
            throws InvoiceTimesheetApiException {
        try {
            for (TimesheetDto ts : timesheets) {
                // Setup reference number.
                ts.setInvoiceRefNo(String.valueOf(invoiceId));
                this.dao.maintainTimesheet(ts);
                // Change the status of timesheet to invoice.
                this.tsBaseApi.changeTimesheetStatus(ts.getTimesheetId(),
                        TimesheetConst.STATUS_INVOICED);
                logger.info("Timesheet " + ts.getDisplayValue()
                        + " was successfully invoiced.");
            }
        } catch (TimesheetApiException e) {
            throw new InvoiceTimesheetApiException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.timesheet.invoice.InvoiceTimesheetApi#calculateInvoice(int)
     */
    @Override
    public double calculateInvoice(int timesheetId)
            throws InvoiceTimesheetApiException {
        List<TimesheetHoursDto> hours;
        try {
            hours = this.tsBaseApi.getHours(timesheetId);
            if (hours == null) {
                return 0;
            }
        } catch (TimesheetApiException e) {
            this.msg = "Timesheet Invoice Error:  Fetching timesheet invoice hours failed";
            throw new InvoiceTimesheetApiException(this.msg, e);
        }

        ProjectEmployeeDto pep = null;
        double invoiceAmt = 0;
        double totHrs = 0;
        int ndx = 0;

        for (TimesheetHoursDto hoursEvent : hours) {
            // Get employee bill rates for target project
            if (ndx == 0) {
                try {
                    pep = this.empApi.getProject(hoursEvent.getEmpId(),
                            hoursEvent.getProjId());
                } catch (EmployeeApiException e) {
                    throw new InvoiceTimesheetApiException(e);
                }
            }

            totHrs += hoursEvent.getEventHours();
            // Calcuate only billable hours
            if (hoursEvent.getTaskBillable() == TimesheetConst.HOUR_TYPE_NONBILLABLE) {
                continue;
            }

            // Calculate invoice pay
            if (totHrs <= TimesheetConst.REG_PAY_HOURS) {
                // Calculate regular hours
                invoiceAmt += hoursEvent.getEventHours() * pep.getHourlyRate();
            }
            else {
                // Calculate overtime hours
                double overtimeHrs = hoursEvent.getEventHours();
                if (overtimeHrs > (totHrs - TimesheetConst.REG_PAY_HOURS)) {
                    // Calculate remaining regular hours
                    invoiceAmt += (overtimeHrs - (totHrs - TimesheetConst.REG_PAY_HOURS))
                            * pep.getHourlyRate();
                    // Apply overtime to a portion of the hours
                    invoiceAmt += ((totHrs - TimesheetConst.REG_PAY_HOURS) * pep
                            .getHourlyOverRate());
                }
                else {
                    invoiceAmt += (overtimeHrs * pep.getHourlyOverRate());
                }
            } // end else
            ndx++;
        } // end for
        return invoiceAmt;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.timesheet.invoice.InvoiceTimesheetApi#calculateBillableHours
     * (int)
     */
    @Override
    public double calculateBillableHours(int timesheetId)
            throws InvoiceTimesheetApiException {
        List<TimesheetHoursDto> hours;
        try {
            hours = this.tsBaseApi.getHours(timesheetId);
            if (hours == null) {
                return 0;
            }
        } catch (TimesheetApiException e) {
            this.msg = "Timesheet Invoice Error:  Fetching timesheet billable hours failed";
            throw new InvoiceTimesheetApiException(this.msg, e);
        }

        double totHrs = 0;
        for (TimesheetHoursDto hoursEvent : hours) {
            // Calcuate only billable hours
            if (hoursEvent.getTaskBillable() == TimesheetConst.HOUR_TYPE_NONBILLABLE) {
                continue;
            }
            totHrs += hoursEvent.getEventHours();
        }
        return totHrs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.timesheet.invoice.InvoiceTimesheetApi#calculateNonBillableHours
     * (int)
     */
    @Override
    public double calculateNonBillableHours(int timesheetId)
            throws InvoiceTimesheetApiException {
        List<TimesheetHoursDto> hours;
        try {
            hours = this.tsBaseApi.getHours(timesheetId);
            if (hours == null) {
                return 0;
            }
        } catch (TimesheetApiException e) {
            this.msg = "Timesheet Invoice Error:  Fetching timesheet non-billable hours failed";
            throw new InvoiceTimesheetApiException(this.msg, e);
        }

        double totHrs = 0;
        for (TimesheetHoursDto hoursEvent : hours) {
            // Calcuate only non-billable hours
            if (hoursEvent.getTaskBillable() == TimesheetConst.HOUR_TYPE_BILLABLE) {
                continue;
            }
            totHrs += hoursEvent.getEventHours();
        }
        return totHrs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.timesheet.invoice.InvoiceTimesheetApi#getResults()
     */
    @Override
    public InvoiceResultsBean getResults() {
        return this.results;
    }

}
