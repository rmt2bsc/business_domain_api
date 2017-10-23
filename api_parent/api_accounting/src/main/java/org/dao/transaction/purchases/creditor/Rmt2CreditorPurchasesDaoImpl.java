package org.dao.transaction.purchases.creditor;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwXactCreditChargeList;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dao.transaction.Rmt2XactDaoImpl;
import org.dao.transaction.XactDaoException;
import org.dto.XactCreditChargeDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.dto.adapter.orm.transaction.purchases.creditor.Rmt2CreditChargeDtoFactory;

import com.api.persistence.PersistenceClient;

/**
 * An implementation of {@link CreditorPurchasesDao}. It provides functionality
 * that creates, updates, deletes, and queries creditor purchases related data.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2CreditorPurchasesDaoImpl extends Rmt2XactDaoImpl implements
        CreditorPurchasesDao {

    /**
     * Creates a Rmt2CreditorPurchasesDaoImpl object with its own persistent
     * client.
     */
    public Rmt2CreditorPurchasesDaoImpl() {
        super();
    }

    /**
     * Creates a Rmt2CreditorPurchasesDaoImpl object with its own persistent
     * client.
     * 
     * @param appName
     */
    public Rmt2CreditorPurchasesDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Creates a Rmt2CreditorPurchasesDaoImpl object with a shared persistent
     * client.
     * 
     * @param client
     */
    public Rmt2CreditorPurchasesDaoImpl(PersistenceClient client) {
        super(client);
    }

    /**
     * Uses the combination of the local database and web service to fetch
     * creditor purchases transactions.
     * <p>
     * The result set is ordered by transaction date in descending order.
     * 
     * @param criteria
     *            an instance of {@link XactCreditChargeDto}
     * @return A List of {@link XactCreditChargeDto} objects or null when no
     *         creditors are found locally.
     * @throws CreditorPurchasesException
     *             General data access errors.
     */
    @Override
    public List<XactCreditChargeDto> fetch(XactCreditChargeDto criteria)
            throws CreditorPurchasesDaoException {

        // Get creditor data from local database
        VwXactCreditChargeList dbCriteria = CreditorPurchasesDaoFactory.createCriteria(criteria);
        dbCriteria.addOrderBy(VwXactCreditChargeList.PROP_XACTDATE,
                VwXactCreditChargeList.ORDERBY_DESCENDING);
        List<VwXactCreditChargeList> results = null;
        try {
            results = this.client.retrieveList(dbCriteria);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new CreditorPurchasesDaoException(e);
        }

        // Combine the results of local and remote query results
        List<XactCreditChargeDto> list = new ArrayList<XactCreditChargeDto>();
        for (VwXactCreditChargeList item : results) {
            XactCreditChargeDto listItem = Rmt2CreditChargeDtoFactory
                    .createCreditChargeInstance(item, null);
            list.add(listItem);
        }
        return list;
    }

    /**
     * Fetch detail items of a credit purchase transaction.
     * 
     * @param xactId
     *            the id of the transaction to obtain related items.
     * @return List of {@link XactTypeItemActivityDto} or null when no data is
     *         retrieved.
     * @throws CreditorPurchasesDaoException
     *             General database error.
     */
    @Override
    public List<XactTypeItemActivityDto> fetch(int xactId)
            throws CreditorPurchasesDaoException {
        XactTypeItemActivityDto criteria = Rmt2XactDtoFactory
                .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        criteria.setXactId(xactId);
        try {
            return super.fetchXactTypeItemActivity(criteria);
        } catch (XactDaoException e) {
            msg = "DB error occurred fetching detail items for transaction, "
                    + xactId;
            throw new CreditorPurchasesDaoException(msg, e);
        }
    }

}
