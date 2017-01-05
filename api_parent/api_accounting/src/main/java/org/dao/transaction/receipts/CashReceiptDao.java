package org.dao.transaction.receipts;

import org.dao.transaction.XactDao;

/**
 * Interface for accessing and managing data pertaining to cash receipt
 * transactions.
 * 
 * @author Roy Terrell
 * 
 */
public interface CashReceiptDao extends XactDao {

    /**
     * 
     * @param salesOrderId
     * @param xactId
     * @return
     * @throws CashReceiptDaoException
     */
    String buildPaymentConfirmation(int salesOrderId, int xactId)
            throws CashReceiptDaoException;

}
