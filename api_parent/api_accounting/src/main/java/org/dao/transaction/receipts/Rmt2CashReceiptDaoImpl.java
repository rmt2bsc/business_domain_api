package org.dao.transaction.receipts;

import org.dao.transaction.Rmt2XactDaoImpl;

import com.api.persistence.PersistenceClient;

/**
 * An implementation of {@link CashReceiptDao}. It provides functionality that
 * creates, updates, deletes, and queries cash receipts related data.
 * 
 * @author Roy Terrell
 * 
 */
public class Rmt2CashReceiptDaoImpl extends Rmt2XactDaoImpl implements
        CashReceiptDao {

    /**
     * Creates a Rmt2CashReceiptDaoImpl object with its own persistent client.
     */
    Rmt2CashReceiptDaoImpl() {
        super();
    }

    /**
     * Creates a Rmt2CashReceiptDaoImpl object with its own persistent client.
     * 
     * @param appName
     */
    Rmt2CashReceiptDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Creates a Rmt2CashReceiptDaoImpl object with a shared persistent client.
     * 
     * @param client
     */
    Rmt2CashReceiptDaoImpl(PersistenceClient client) {
        super(client);
    }
}
