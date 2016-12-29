package org.dao.transaction.purchases.creditor;

import org.dao.mapping.orm.rmt2.VwXactCreditChargeList;
import org.dao.subsidiary.SubsidiaryDaoConst;
import org.dto.XactCreditChargeDto;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * Factory class for creating Creditor Purchases Transaction DAO related
 * objects.
 * 
 * @author Roy Terrell
 * 
 */
public class CreditorPurchasesDaoFactory extends RMT2Base {

    /**
     * Default constructor
     */
    public CreditorPurchasesDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>CreditorPurchasesDao</i> using the RMT2
     * transaction ORM implementation.
     * 
     * @return an instance of {@link CreditorPurchasesDao}
     */
    public CreditorPurchasesDao createRmt2OrmDao() {
        CreditorPurchasesDao dao = new Rmt2CreditorPurchasesDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>CreditorPurchasesDao</i> using the RMT2 ORM
     * basic maintenance implementation.
     * 
     * @param appName
     *            application name
     * 
     * @return an instance of {@link CreditorPurchasesDao}
     */
    public CreditorPurchasesDao createRmt2OrmDao(String appName) {
        CreditorPurchasesDao d = new Rmt2CreditorPurchasesDaoImpl(appName);
        return d;
    }

    /**
     * Creates an instance of <i>CreditorPurchasesDao</i> using the RMT2 ORM
     * basic maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     * 
     * @return an instance of {@link CreditorPurchasesDao}
     */
    public CreditorPurchasesDao createRmt2OrmDao(DaoClient dao) {
        CreditorPurchasesDao d = new Rmt2CreditorPurchasesDaoImpl(
                dao.getClient());
        d.setDaoUser(dao.getDaoUser());
        return d;
    }

    /**
     * Creates and returns an <i>VwXactCreditChargeList</i> object containing
     * selection criteria obtained from an instance of
     * <i>XactCreditChargeDto</i>.
     * 
     * @param criteria
     *            an instance of {@link XactCreditChargeDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>creditorTypeId - default criteria which will retrieve all
     *            creditors of creditor type</li>
     *            <li>creditorId</li>
     *            <li>businessId</li>
     *            <li>xactId</li>
     *            <li>accountNo</li>
     *            <li>confirmNo</li>
     *            <li>xactDate</li>
     *            <li>xactReason</li>
     *            <li>criteria - include custom selection criteriaif available</li>
     *            </ul>
     * @return an instance of {@link SalesOrder}
     */
    public static final VwXactCreditChargeList createCriteria(
            XactCreditChargeDto criteria) {
        VwXactCreditChargeList obj = new VwXactCreditChargeList();

        // Default predicate to fetch all creditors of "creditor" type
        obj.addCriteria(VwXactCreditChargeList.PROP_CREDITORTYPEID,
                SubsidiaryDaoConst.CREDITOR_TYPE_CREDITOR);

        if (criteria != null) {
            if (criteria.getCreditorId() > 0) {
                obj.addCriteria(VwXactCreditChargeList.PROP_CREDITORID,
                        criteria.getCreditorId());
            }
            if (criteria.getBusinessId() > 0) {
                obj.addCriteria(VwXactCreditChargeList.PROP_BUSINESSID,
                        criteria.getBusinessId());
            }
            if (criteria.getXactId() > 0) {
                obj.addCriteria(VwXactCreditChargeList.PROP_XACTID,
                        criteria.getXactId());
            }
            if (criteria.getAccountNumber() != null) {
                obj.addLikeClause(VwXactCreditChargeList.PROP_ACCOUNTNO,
                        criteria.getAccountNumber());
            }
            if (criteria.getXactConfirmNo() != null) {
                obj.addLikeClause(VwXactCreditChargeList.PROP_CONFIRMNO,
                        criteria.getXactConfirmNo());
            }
            if (criteria.getXactDate() != null) {
                obj.addCriteria(VwXactCreditChargeList.PROP_XACTDATE,
                        criteria.getXactDate());
            }
            if (criteria.getXactReason() != null) {
                obj.addLikeClause(VwXactCreditChargeList.PROP_REASON,
                        criteria.getXactReason());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

}
