package org.modules.timesheet.invoice;

import java.io.Serializable;
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
import org.dto.adapter.orm.ProjectObjectFactory;
import org.modules.ProjectTrackerApiConst;
import org.modules.admin.ProjectAdminApi;
import org.modules.admin.ProjectAdminApiException;
import org.modules.admin.ProjectAdminApiFactory;
import org.modules.employee.EmployeeApi;
import org.modules.employee.EmployeeApiException;
import org.modules.employee.EmployeeApiFactory;
import org.modules.timesheet.TimesheetApi;
import org.modules.timesheet.TimesheetApiException;
import org.modules.timesheet.TimesheetApiFactory;
import org.rmt2.constants.ApiTransactionCodes;
import org.rmt2.jaxb.AccountingTransactionRequest;
import org.rmt2.jaxb.AccountingTransactionResponse;
import org.rmt2.jaxb.HeaderType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.SalesOrderType;
import org.rmt2.jaxb.TransactionDetailGroup;
import org.rmt2.util.JaxbPayloadFactory;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.foundation.TransactionApiException;
import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.messaging.webservice.router.MessageRoutingException;
import com.api.persistence.DaoClient;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * Implementation of InvoiceTimesheetApi that manages the invoicing of an
 * employee's timesheet activities.
 * 
 * @author Roy Terrell
 * 
 */
public class InvoiceTimesheetApiImpl extends AbstractTransactionApiImpl implements InvoiceTimesheetApi {

    private static final Logger logger = Logger.getLogger(InvoiceTimesheetApiImpl.class);

    private TimesheetApi tsApi;

    private ProjectAdminApi projApi;

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
        this(ProjectTrackerApiConst.DEFAULT_CONTEXT_NAME);
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
        super(ProjectTrackerApiConst.DEFAULT_CONTEXT_NAME, dao);
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
        ProjectAdminApiFactory pf = new ProjectAdminApiFactory();
        EmployeeApiFactory ef = new EmployeeApiFactory();
        this.tsApi = tf.createApi(connection);
        this.projApi = pf.createApi(connection);
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
    public List<Integer> invoice(List<Integer> clientIdList) throws InvoiceTimesheetApiException {
        try {
            Verifier.verifyNotEmpty(clientIdList);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Client Id list is required");
        }
        InvoiceResultsBean results = new InvoiceResultsBean();
        List<Integer> invoiceIdList = new ArrayList<Integer>();
        for (Integer clientId : clientIdList) {
            try {
                int invoiceId = this.startBilling(clientId);
                results.setProcessedClientTimesheets(clientId, this.timesheetIdList);
                invoiceIdList.add(invoiceId);
            } catch (Exception e) {
                throw new InvoiceTimesheetApiException(e);
            }
        }
        // Complete packaging the results with transaction id's
        results.setClientTransactions(invoiceIdList);
        return invoiceIdList;
    }

    /**
     * Invoices a single timesheet.
     * 
     * @param timesheetId
     *            The unique identifier of the timesheet to invoice.
     * @return int the sales order invoice id
     * @throws InvoiceTimesheetApiException
     * @throws NotFoundException Time sheet or Client cannot be found
     */
    @Override
    public int invoice(Integer timesheetId) throws InvoiceTimesheetApiException {
        this.validateTimesheetId(timesheetId);
        
        // Verify timesheet
        TimesheetDto ts;
        try {
            ts = this.tsApi.get(timesheetId);
            if (ts == null) {
                this.msg = "Timesheet, " + timesheetId + ", cannot be found";
                throw new NotFoundException(this.msg);
            }
        } catch (TimesheetApiException e) {
            this.msg = "Problem fetching timesheet by timesheet id, " + timesheetId;
            throw new InvoiceTimesheetApiException(this.msg);
        }
        // Verify client
        List<ClientDto> client;
        try {
            ClientDto criteria = ProjectObjectFactory.createClientDtoInstance(null);
            criteria.setClientId(ts.getClientId());
            client = this.projApi.getClient(criteria);
            if (client == null) {
                this.msg = "Timesheet's client cannot be found [client id=" + ts.getClientId() + "]";
                throw new NotFoundException(this.msg);
            }
        } catch (ProjectAdminApiException e) {
            this.msg = "Problem fetching timesheet's client [client id=" + ts.getClientId() + "]";
            throw new InvoiceTimesheetApiException(this.msg);
        }

        // Invoice the timesheet
        List<TimesheetDto> tsList = new ArrayList<TimesheetDto>();
        tsList.add(ts);
        int invoiceId = this.startBilling(client.get(0), tsList);
        return invoiceId;
    }

    /**
     * Bulk Invoices all apporoved time sheets pertaining to a single client
     * identified as <i>clientId</i>.
     * <p>
     * All of the client's invoices are bundled together as a single sales order
     * invoice transaction. In terms of the sales order, a sales order item is
     * created for each approved time sheet available.
     * <p>
     * The invoices are sent to the sales order subsystems for processing. This
     * request is submitted by an employee of manager status.
     * 
     * @param clientId
     *            The id of the client.
     * @return the sales order invoice id.
     * @throws InvoiceTimesheetApiException
     *             client validation errors, problem gathering timesheets, or
     *             sales order processing errors or database access errors.
     */
    private int startBilling(int clientId) throws InvoiceTimesheetApiException {
        List<ClientDto> client = null;
        try {
            // Get client object
            ClientDto criteria = ProjectObjectFactory.createClientDtoInstance(null);
            criteria.setClientId(clientId);
            client = this.projApi.getClient(criteria);
            // Get client approved timesheets
            List<TimesheetDto> ts = this.tsApi.getClientApproved(clientId);
            if (ts == null) {
                return 0;
            }
            // Begin to bill the client's timesheets
            int invoiceId = this.startBilling(client.get(0), ts);
            return invoiceId;
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
     * @return the sales order invoice id
     * @throws InvoiceTimesheetApiException
     */
    private int startBilling(ClientDto client, List<TimesheetDto> timesheets) throws InvoiceTimesheetApiException {
        int invoiceId;
        InvoiceBean invBean = this.prepareBilling(client, timesheets);
        invoiceId = this.submitBilling(invBean);
        if (invoiceId <= 0) {
            throw new InvoiceTimesheetApiException(
                    "An invalid sales order invoice id was returned from Accounting Sales Order service");
        }
        this.postInvoice(timesheets, invoiceId);
        return invoiceId;
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
    private int submitBilling(InvoiceBean invBean) throws InvoiceTimesheetApiException {
        ObjectFactory f = new ObjectFactory();
        AccountingTransactionRequest request = f.createAccountingTransactionRequest();
        HeaderType header = JaxbPayloadFactory.createHeader("routing", "app",
                "module", ApiTransactionCodes.ACCOUNTING_SALESORDER_CREATE, "SYNC", "REQUEST", this.getApiUser());
        request.setHeader(header);

        // Setup customer's sales order that will be invoiced
        TransactionDetailGroup details = f.createTransactionDetailGroup();
        SalesOrderType sot = InvoiceTimesheetApiFactory.createJaxbSalesOrderInstance(invBean);
        details.getSalesOrder().add(sot);
        request.setProfile(details);

        // Send time sheet deatils to Accounting systsem to create and invoice sales order
        try {
            Object response = this.sendMessage(ApiTransactionCodes.ACCOUNTING_SALESORDER_CREATE, request);
            if (response != null && response instanceof AccountingTransactionResponse) {
                AccountingTransactionResponse r = (AccountingTransactionResponse) response;
                return r.getReplyStatus().getReturnCode().intValue();
            }
            else {
                throw new InvoiceTimesheetApiException(
                        "An invalid response was returned from the Timesheet-to-sales order web service operation");
            }
        } catch (TransactionApiException e) {
            this.msg = "A web service problem occurred sending time sheet(s) to accounting for the purpose of creating a sales order: timesheet id's["
                    + this.timesheetIdList + "]";
            throw new InvoiceTimesheetApiException(this.msg, e);
        }
    }
    
    @Override
    public Object sendMessage(String messageId, Serializable payload) throws TransactionApiException {
        String msg = null;
        MessageRouterHelper helper = new MessageRouterHelper();
        try {
            return helper.routeXmlMessage(messageId, payload);
        } catch (MessageRoutingException e) {
            msg = "Error occurred routing Timesheet XML message to its designated API handler";
            throw new TransactionApiException(msg, e);
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
    private InvoiceBean prepareBilling(ClientDto client, List<TimesheetDto> timesheets) throws InvoiceTimesheetApiException {
        // Setup sales order object
        InvoiceBean invBean = new InvoiceBean();
        invBean.setCustomerId(client.getClientId());

        // Process each timesheet as a single sales order item
        List<InvoiceDetailsBean> items = new ArrayList<InvoiceDetailsBean>();
        StringBuffer tsDisplayIds = new StringBuffer();
        this.invoiceAmt = 0;
        this.timesheetIdList = new ArrayList<String>();
        for (TimesheetDto ts : timesheets) {
            InvoiceDetailsBean soi = this.prepareBilling(ts);
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
    private InvoiceDetailsBean prepareBilling(TimesheetDto ts) throws InvoiceTimesheetApiException {

        int timesheetId = ts.getTimesheetId();
        try {
            // Calculate timesheet invoice amount
            double timesheetAmt = this.calculateInvoice(timesheetId);
            double hours = this.calculateBillableHours(timesheetId);

            TimesheetHistDto status = this.tsApi.getCurrentStatus(timesheetId);
            if (status == null) {
                return null;
            }
            if (status.getStatusId() != TimesheetConst.STATUS_APPROVED) {
                return null;
            }

            // Create Sales Order Item
            InvoiceDetailsBean invDetailBean = new InvoiceDetailsBean();
            String temp = this.getConfig().getProperty("invoice_item_id");
            int invoiceMasterItemId = Integer.parseInt(temp);
            invDetailBean.setItemId(invoiceMasterItemId);

            // Get employee
            EmployeeDto employee = this.empApi.getEmployee(ts.getEmpId());
            
            // Build complex description line item.
            String descr = employee.getEmployeeFirstname() + " " + employee.getEmployeeLastname() + " for " + hours + " hours";
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
     *            The sales order invoice id that was created for the sales order.
     * @throws InvoiceTimesheetApiException
     */
    private void postInvoice(List<TimesheetDto> timesheets, int invoiceId) throws InvoiceTimesheetApiException {
        try {
            for (TimesheetDto ts : timesheets) {
                // Setup reference number.
                ts.setInvoiceRefNo(String.valueOf(invoiceId));
                this.dao.maintainTimesheet(ts);
                // Change the status of timesheet to invoice.
                this.tsApi.changeTimesheetStatus(ts.getTimesheetId(), TimesheetConst.STATUS_INVOICED);
                logger.info("Timesheet " + ts.getDisplayValue() + " was successfully invoiced.");
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
    public double calculateInvoice(Integer timesheetId) throws InvoiceTimesheetApiException {
        this.validateTimesheetId(timesheetId);
        
        List<TimesheetHoursDto> hours;
        try {
            hours = this.tsApi.getHours(timesheetId);
            if (hours == null) {
                return 0;
            }
        } catch (TimesheetApiException e) {
            this.msg = "Fetching timesheet invoice hours failed";
            throw new InvoiceTimesheetApiException(this.msg, e);
        }

        ProjectEmployeeDto pep = null;
        double invoiceAmt = 0;
        double totHrs = 0;
        int ndx = 0;

        for (TimesheetHoursDto hoursEvent : hours) {
            // TODO: may want to execute getting the ProjectEmployee object in
            // the event the employee is billing for multiple projects.
            if (ndx == 0) {
                try {
                    // Get employee bill rates for target project
                    ProjectEmployeeDto criteria = ProjectObjectFactory.createEmployeeProjectDtoInstance(null);
                    criteria.setEmpId(hoursEvent.getEmpId());
                    criteria.setProjId(hoursEvent.getProjId());
                    List<ProjectEmployeeDto> peps = this.empApi.getProjectEmployee(criteria);
                    if (peps != null) {
                        pep = peps.get(0);
                    }
                } catch (EmployeeApiException e) {
                    throw new InvoiceTimesheetApiException(e);
                }
            }

            totHrs += hoursEvent.getEventHours();
            // Calculate only billable hours
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
                    invoiceAmt += (overtimeHrs - (totHrs - TimesheetConst.REG_PAY_HOURS)) * pep.getHourlyRate();
                    // Apply overtime to a portion of the hours
                    invoiceAmt += ((totHrs - TimesheetConst.REG_PAY_HOURS) * pep.getHourlyOverRate());
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
    public double calculateBillableHours(Integer timesheetId) throws InvoiceTimesheetApiException {
        this.validateTimesheetId(timesheetId);
        
        List<TimesheetHoursDto> hours;
        try {
            hours = this.tsApi.getHours(timesheetId);
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
            if (hoursEvent.getTaskBillable() == TimesheetConst.HOUR_TYPE_BILLABLE) {
                totHrs += hoursEvent.getEventHours();
            }
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
    public double calculateNonBillableHours(Integer timesheetId) throws InvoiceTimesheetApiException {
        this.validateTimesheetId(timesheetId);
        
        List<TimesheetHoursDto> hours;
        try {
            hours = this.tsApi.getHours(timesheetId);
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
            if (hoursEvent.getTaskBillable() == TimesheetConst.HOUR_TYPE_NONBILLABLE) {
                totHrs += hoursEvent.getEventHours();
            }
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

    private void validateTimesheetId(Integer timesheetId) {
        try {
            Verifier.verifyNotNull(timesheetId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Time sheet Id is required");
        }
        try {
            Verifier.verifyPositive(timesheetId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Time sheet Id must be greater than zero");
        }
    }
    
    
}
