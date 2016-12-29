package org.modules.timesheet.invoice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Caputres the results of the timesheet invoicing process
 * 
 * @author Roy Terrell
 */
class InvoiceResultsBean {

    private Map<Integer, List<String>> processedClientTimesheets;

    private List<Integer> clientTransactions;

    /**
     * Default constructor.
     */
    public InvoiceResultsBean() {
        super();
        this.processedClientTimesheets = new LinkedHashMap<Integer, List<String>>();
        this.clientTransactions = new ArrayList<Integer>();
        return;
    }

    /**
     * @return the processedClientTimesheets
     */
    public Map<Integer, List<String>> getProcessedClientTimesheets() {
        return processedClientTimesheets;
    }

    /**
     * @param processedClientTimesheets
     *            the processedClientTimesheets to set
     */
    public void setProcessedClientTimesheets(
            Map<Integer, List<String>> processedClientTimesheets) {
        this.processedClientTimesheets = processedClientTimesheets;
    }

    public void setProcessedClientTimesheets(int clientId,
            List<String> timesheets) {
        this.processedClientTimesheets.put(clientId, timesheets);
    }

    /**
     * @return the clientTransactions
     */
    public List<Integer> getClientTransactions() {
        return clientTransactions;
    }

    /**
     * @param clientTransactions
     *            the clientTransactions to set
     */
    public void setClientTransactions(List<Integer> clientTransactions) {
        this.clientTransactions = clientTransactions;
    }

}