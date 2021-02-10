package org.dao.transaction.disbursements;

import java.util.List;

import org.dao.transaction.Rmt2XactDaoImpl;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;

import com.api.persistence.PersistenceClient;

/**
 * An implementation of {@link DisbursementsDao}. It provides functionality that
 * creates, updates, deletes, and queries disbursement related data.
 * 
 * @author Roy Terrell
 * 
 */
public class Rmt2DisbursementsDaoImpl extends Rmt2XactDaoImpl implements DisbursementsDao {

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
    public List<XactDto> fetchDisbursmentByXact(XactDto criteria) throws DisbursementsDaoException {
        try {
            return super.fetchXact(criteria);
        } catch (Exception e) {
            throw new DisbursementsDaoException("Error obtaining disbursment(s) by transaction", e);
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
    public List<XactTypeItemActivityDto> fetchDisbursmentByXactItem(XactTypeItemActivityDto criteria) 
            throws DisbursementsDaoException {
        try {
            return super.fetchXactTypeItemActivityExt(criteria);
        } catch (Exception e) {
            this.msg = "Error obtaining disbursment(s) by transaction type item activity";
            throw new DisbursementsDaoException(this.msg, e);
        }
    }
}
