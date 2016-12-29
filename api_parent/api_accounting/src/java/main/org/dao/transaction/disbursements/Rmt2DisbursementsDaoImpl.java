package org.dao.transaction.disbursements;

import java.util.List;

import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.transaction.Rmt2XactDaoImpl;
import org.dao.transaction.XactDaoException;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;

import com.api.persistence.PersistenceClient;

/**
 * An implementation of {@link DisbursementsDao}. It provides functionality that
 * creates, updates, deletes, and queries disbursement related data.
 * 
 * @author Roy Terrell
 * 
 */
public class Rmt2DisbursementsDaoImpl extends Rmt2XactDaoImpl implements
        DisbursementsDao {

    /**
     * Creates a Rmt2DisbursementDaoImpl object with its own persistent client.
     */
    Rmt2DisbursementsDaoImpl() {
        super();
    }

    /**
     * Creates a Rmt2DisbursementDaoImpl object with a shared persistent client.
     * 
     * @param appName
     */
    Rmt2DisbursementsDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Creates a Rmt2DisbursementDaoImpl object with a shared persistent client.
     * 
     * @param client
     */
    Rmt2DisbursementsDaoImpl(PersistenceClient client) {
        super(client);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.transaction.disbursements.DisbursementsDao#fetchDisbursmentByXact
     * (java.lang.String)
     */
    @Override
    public List<XactDto> fetchDisbursmentByXact(String criteria)
            throws DisbursementsDaoException {
        XactDto dto = Rmt2XactDtoFactory.createXactInstance((Xact) null,
                criteria);
        try {
            return super.fetchXact(dto);
        } catch (XactDaoException e) {
            this.msg = "Error obtaining disbursment(s) by transaction";
            throw new DisbursementsDaoException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.transaction.disbursements.DisbursementsDao#fetchDisbursmentByXactItem
     * (java.lang.String)
     */
    @Override
    public List<XactTypeItemActivityDto> fetchDisbursmentByXactItem(
            String criteria) throws DisbursementsDaoException {
        XactTypeItemActivityDto dto = Rmt2XactDtoFactory
                .createXactTypeItemActivityExtInstance(
                        (VwXactTypeItemActivity) null, criteria);
        try {
            return super.fetchXactTypeItemActivityExt(dto);
        } catch (XactDaoException e) {
            this.msg = "Error obtaining disbursment(s) by transaction item";
            throw new DisbursementsDaoException(this.msg, e);
        }
    }

    // /**
    // * Creates a general cash disbursement transaction or reverses and
    // existing
    // * cash disbursement transaction.
    // * <p>
    // * If the transaction id that is encapsulated in xact is equal to 0, then
    // a new transaction is created.
    // * Otherwise, an existing transaction is reversed.
    // *
    // * @param xact
    // * The target transaction
    // * @param items
    // * The transaction items
    // * @returnint
    // * @throws DisbursementsDaoException
    // */
    // @Override
    // public int maintain(XactDto xact, List<XactTypeItemActivityDto> items)
    // throws DisbursementsDaoException {
    //
    // return 0;
    // }
    //
    // /**
    // * Creates a creditor related cash disbursement transaction or reverses
    // and
    // * existing one.
    // * <p>
    // * If the transaction id that is encapsulated in xact is equal to 0,
    // * then a new transaction is created. Otherwise, an existing transaction
    // is
    // * reversed.
    // *
    // * @param xact
    // * The target transaction
    // * @param items
    // * The transaction items
    // * @param creditorId
    // * The id of creditor assoicated with the transaction
    // * @return int
    // * @throws DisbursementsDaoException
    // */
    // @Override
    // public int maintain(XactDto xact, List<XactTypeItemActivityDto> items,
    // int creditorId)
    // throws DisbursementsDaoException {
    //
    // return 0;
    // }

}
