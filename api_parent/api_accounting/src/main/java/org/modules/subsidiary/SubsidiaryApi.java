package org.modules.subsidiary;

import java.util.Map;

import org.AccountingConst.SubsidiaryType;
import org.dto.SubsidiaryContactInfoDto;

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
     * Retireve common contact data for a list of subsidiary accounts. 
     * <p>
     * The results of this method will typically be merged with subsidiary 
     * accounts that are related by <i>business id</i>.
     * 
     * @param criteria
     * @return
     * @throws SubsidiaryException
     */
    Map<Integer, SubsidiaryContactInfoDto> getContactInfo(SubsidiaryContactInfoDto criteria) 
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
}
