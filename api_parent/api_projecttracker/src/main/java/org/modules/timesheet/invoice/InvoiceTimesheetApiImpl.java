package org.modules.timesheet.invoice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.VwEmployeeProjects;
import org.dao.timesheet.TimesheetConst;
import org.dao.timesheet.TimesheetDao;
import org.dao.timesheet.TimesheetDaoException;
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
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

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
     * Creates a InvoiceTimesheetApiImpl object in which the configuration is
     * identified by the name of a given application.
     * 
     * @param appName
     */
    protected InvoiceTimesheetApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        this.setApiUser(this.apiUser);
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

    /**
     * Invoices all timesheets related to multiple clients.
     * <p>
     * An invoice id shall be created per client that is procesed. This process
     * executes on an all or none basis. For example, if one client is incapable
     * of being processed successfully, then the entire batch of clients are
     * aborted.
     * 
     * @param clientIdList
     *            A List of client unique identifiers.
     * @return A List of invoice id's where each invoice id represent one of the
     *         clients that were processed.
     * @throws InvoiceTimesheetApiException
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
            // Validate Client Id
            try {
                Verifier.verifyNotNull(clientId);
            }
            catch (VerifyException e) {
                throw new InvalidDataException("All elements in the list client id's are required to not be null");
            }
            
            // Get client profile
            try {
                ClientDto client = this.getClient(clientId);
                int invoiceId = this.startBilling(client);
                results.setProcessedClientTimesheets(clientId, this.timesheetIdList);
                invoiceIdList.add(invoiceId);    
            }
            catch (NotFoundException e) {
                this.msg = "Skip invoicing time sheets for client, " + clientId + ", due to client does not exists";
                throw new InvoiceTimesheetApiException(this.msg, e);
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
        ClientDto client = this.getClient(ts.getClientId());

        // Invoice the timesheet
        List<TimesheetDto> tsList = new ArrayList<TimesheetDto>();
        tsList.add(ts);
        int invoiceId = this.startBilling(client, tsList);
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
     * @throws NotFoundException
     *             when there are not approved time sheets for client to
     *             process.
     */
    private int startBilling(ClientDto client) throws InvoiceTimesheetApiException {
        List<TimesheetDto> ts = null;
        try {
            // Get client approved timesheets
            ts = this.tsApi.getClientApproved(client.getClientId());
            if (ts == null) {
                throw new NotFoundException("Unable to find approved time sheets to invoice for client, "
                                + client.getClientId());
            }
            // Begin to bill the client's timesheets
            int invoiceId = this.startBilling(client, ts);
            return invoiceId;
        } catch (TimesheetApiException e) {
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
            throw new InvoiceTimesheetApiException("Detected a problem with time sheet invoicing."
                            + "  An invalid sales order invoice id was returned from Accounting Sales Order service");
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
        details.setSalesOrders(f.createSalesOrderListType());
        SalesOrderType sot = InvoiceTimesheetApiFactory.createJaxbSalesOrderInstance(invBean);
        details.getSalesOrders().getSalesOrder().add(sot);
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
            this.msg = "A web service problem occurred sending time sheet(s) to accounting for the purpose of creating a sales order: timesheet id's "
                    + this.timesheetIdList;
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
            InvoiceDetailsBean soi = null; 
            try {
                soi = this.prepareBilling(ts);
            }
            catch (InvalidStateForBillingException e) {
                this.msg = "Time sheet billing preparation failed. The processing of all time sheets is aborted for client, "
                        + client.getClientId() + ".";
                throw new InvoiceTimesheetApiException(this.msg, e);
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
   
        // Calculate timesheet invoice amount
        double timesheetAmt = this.calculateInvoice(timesheetId);
        double hours = this.calculateBillableHours(timesheetId);
        try {
            TimesheetHistDto status = this.tsApi.getCurrentStatus(timesheetId);
            if (status == null) {
                this.msg = "Unable to identify timesheet's current status [time sheet=" + timesheetId + "]";
                throw new InvalidStateForBillingException(this.msg);
            }
            if (status.getStatusId() != TimesheetConst.STATUS_APPROVED) {
                this.msg = "Timsheet's current status is invalid for billing/invoicing process [time sheet=" + timesheetId + "]";
                throw new InvalidStateForBillingException(this.msg);
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
        } catch (EmployeeApiException | TimesheetApiException e) {
            this.msg = "Unable to prepare billing for time sheet, " + ts.getDisplayValue();
            throw new InvoiceTimesheetApiException(this.msg, e);
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
        } catch (TimesheetDaoException e) {
            this.msg = "DAO error occurred attempting to post time sheet invoicing";
            throw new InvoiceTimesheetApiException(this.msg, e);
        }
        catch (TimesheetApiException e) {
            this.msg = "An error occurred attempting to change the status of a time sheet during invoice posting";
            throw new InvoiceTimesheetApiException(this.msg, e);
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
            this.msg = "Error occurred fetching invoice hours for time sheet, " + timesheetId;
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
                    VwEmployeeProjects vep = new VwEmployeeProjects();
                    ProjectEmployeeDto criteria = ProjectObjectFactory.createEmployeeProjectDtoInstance(vep);
                    criteria.setEmpId(hoursEvent.getEmpId());
                    criteria.setProjId(hoursEvent.getProjId());
                    List<ProjectEmployeeDto> peps = this.empApi.getProjectEmployee(criteria);
                    if (peps != null) {
                        pep = peps.get(0);
                    }
                    else {
                        this.msg = "Unable to process time sheet hours due to project/employee profile is unavailable [project id="
                                + hoursEvent.getProjId() + ", employee id=" + hoursEvent.getEmpId() + "]";
                        throw new InvoiceTimesheetApiException(this.msg);
                    }
                } catch (EmployeeApiException e) {
                    this.msg = "Error occurred fetching project/employee profile while processing time sheet hours";
                    throw new InvoiceTimesheetApiException(this.msg, e);
                }
            }

            // Calculate only billable hours
            if (hoursEvent.getTaskBillable() == TimesheetConst.HOUR_TYPE_NONBILLABLE) {
                continue;
            }

            totHrs += hoursEvent.getEventHours();
            // Calculate invoice pay
            if (totHrs <= TimesheetConst.REG_PAY_HOURS) {
                // Calculate regular hours
                invoiceAmt += hoursEvent.getEventHours() * pep.getHourlyRate();
            }
            else {
                // Calculate overtime hours
                double eventHrs = hoursEvent.getEventHours();
                double overtimeHrs = totHrs - TimesheetConst.REG_PAY_HOURS;
                if (eventHrs > overtimeHrs) {
                    // Calculate remaining regular hours
                    invoiceAmt += (eventHrs - overtimeHrs) * pep.getHourlyRate();
                    // Apply overtime to a portion of the hours
                    invoiceAmt += (overtimeHrs * pep.getHourlyOverRate());
                }
                else {
                    invoiceAmt += (eventHrs * pep.getHourlyOverRate());
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
            this.msg = "Error occcured fetching time sheet billable hours for timesheet, " + timesheetId;
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
    
    
    private ClientDto getClient(int clientId) throws InvoiceTimesheetApiException, NotFoundException {
        List<ClientDto> clients = null;
        ClientDto criteria = ProjectObjectFactory.createClientDtoInstance(null);
        criteria.setClientId(clientId);
        try {
            clients = this.projApi.getClient(criteria);
        }
        catch (ProjectAdminApiException e) {
            this.msg = "Error fetching client profile";
            throw new InvoiceTimesheetApiException(this.msg, e);
        }
        
        try {
            Verifier.verifyNotEmpty(clients);
            return clients.get(0);
        }
        catch (VerifyException e) {
            throw new NotFoundException("Client profile was not found [client id=" + clientId + "]");
        }
    }
    
}
