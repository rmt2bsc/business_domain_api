/**
 * 
 */
package org.modules.subsidiary;

import org.AccountingConst.SubsidiaryType;

import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;

/**
 * Abstract class providing common functionality to manage subsidiaries from the
 * level of the API module.
 * 
 * @author Roy Terrell
 * 
 */
abstract class AbstractSubsidiaryApiImpl extends AbstractTransactionApiImpl
        implements SubsidiaryApi {

    /**
     * Default constructor
     */
    public AbstractSubsidiaryApiImpl() {
        return;
    }

    protected AbstractSubsidiaryApiImpl(DaoClient connection) {
        super(connection);
    }

    /**
     * Creates an account number for a given subsidiary.
     * 
     * @param businessId
     *            The business id of the subsidiary
     * @param subType
     *            an enum type of
     *            {@link org.modules.subsidiary.AccountingConst.SubsidiaryType}
     *            which identifies the type of subsidiary.
     * @return The account number.
     */
    @Override
    public String buildAccountNo(int businessId, SubsidiaryType subType) {
        SubsidiaryHelper helper = new SubsidiaryHelper();
        return helper.buildAccountNo(businessId, subType);
    }

    // /**
    // * Calculate and return the customer's balance.
    // *
    // * @param businessId
    // * customer's internal unique id known as the busines id.
    // * @return double as the customer's balance.
    // * @throws SubsidiaryException
    // * General database SQL errors.
    // */
    // @Override
    // public double getBalance(int businessId) throws SubsidiaryException {
    // String sql =
    // "select sum(amount) balance from customer_activity where customer_id = "
    // + businessId;
    // double bal;
    // try {
    // ResultSet rs = this.client.executeSql(sql);
    // if (rs.next()) {
    // bal = rs.getDouble("balance");
    // }
    // else {
    // bal = 0;
    // }
    // return bal;
    // } catch (Exception e) {
    // throw new CustomerDaoException(e);
    // }
    // }

}
