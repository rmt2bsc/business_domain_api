package org.modules.subsidiary;

import java.util.List;

import org.AccountingConst.SubsidiaryType;
import org.dto.SubsidiaryDto;

import com.api.foundation.TransactionApi;

/**
 * An contract for executing transactions related to the common aspects of a
 * subsidiary account.
 * 
 * @author Roy Terrell
 * 
 */
public interface SubsidiaryApi extends TransactionApi {

    /**
     * Retrieve a single subsidiary account without contact info using the
     * subsidiary id.
     * 
     * @param subsidiaryId
     *            the unique identifier of the target subsidiary
     * @return an instnace of {@link SubsidiaryDto}
     * @throws SubsidiaryException
     */
    SubsidiaryDto getDomainBySubsidiaryId(int subsidiaryId)
            throws SubsidiaryException;

    /**
     * Builds an account number for the subsidirary.
     * 
     * @param businessId
     *            the business id of the subsidiary.
     * @param type
     *            an enum type of
     *            {@link org.modules.subsidiary.AccountingConst.SubsidiaryType}
     * @return String as the account number
     */
    String buildAccountNo(int businessId, SubsidiaryType type);

    /**
     * Obtain the subsidiary's account balance.
     * 
     * @param subsidiaryId
     *            The subsiary's unique id.
     * @return double as the subsidiary's balance.
     * @throws SubsidiaryException
     *             General database errors.
     */
    double getBalance(int subsidiaryId) throws SubsidiaryException;

    /**
     * Get transacton history for a particular subsidiary account.
     * 
     * @param subsidiaryId
     *            the unique id of the subsidiary account
     * @return a List of unknown ojects representing the transaction history.
     * @throws SubsidiaryException
     */
    <T> List<T> getTransactionHistory(int subsidiaryId)
            throws SubsidiaryException;

}
