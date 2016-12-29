package org.dao.transaction.purchases.creditor;

import java.util.List;

import org.dao.transaction.XactDao;
import org.dto.XactCreditChargeDto;
import org.dto.XactTypeItemActivityDto;

/**
 * DAO interface for managing creditor purchases transactions.
 * 
 * @author Roy Terrell
 * 
 */
public interface CreditorPurchasesDao extends XactDao {

    /**
     * Fetch base creditor purchases transactions using custom criteria.
     * 
     * @param criteria
     *            an instance of {@link XactCreditChargeDto}
     * @return An arbitrary object
     * @throws CreditorPurchasesException
     */
    List<XactCreditChargeDto> fetch(XactCreditChargeDto criteria)
            throws CreditorPurchasesDaoException;

    /**
     * Fetch detail items of a credit purchase transaction.
     * 
     * @param xactId
     *            the id of the transaction to obtain related items.
     * @return
     * @throws CreditorPurchasesDaoException
     */
    List<XactTypeItemActivityDto> fetch(int xactId)
            throws CreditorPurchasesDaoException;

}