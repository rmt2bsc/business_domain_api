package org.dao.subsidiary;

import org.apache.log4j.Logger;
import org.dao.AccountingDaoImpl;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.SubsidiaryXactHistoryDto;

import com.api.persistence.PersistenceClient;


/**
 * Queries, updates, deletes, and creates general contact information for a
 * subsidiary.
 * <p>
 * Implements the interface, {@link SubsidiaryDao}, in order to serve requests
 * for common business contact data as it pertains to any given subsidiary via
 * web services. <i>RQ_business_contact_search</i> is the web services utilized.
 * 
 * @author Roy Terrell
 * 
 */
abstract class AbstractRmt2SubsidiaryContactDaoImpl extends AccountingDaoImpl
        implements SubsidiaryDao {

    private static Logger logger = Logger
            .getLogger(AbstractRmt2SubsidiaryContactDaoImpl.class);

    /**
     * Default constructor for creating a AbstractRmt2SubsidiaryContactDaoImpl
     * object
     */
    public AbstractRmt2SubsidiaryContactDaoImpl() {
        super();
        return;
    }

    public AbstractRmt2SubsidiaryContactDaoImpl(String appName) {
        super(appName);
        return;
    }

    public AbstractRmt2SubsidiaryContactDaoImpl(PersistenceClient client) {
        super(client);
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.subsidiary.SubsidiaryDao#maintain(org.dto.SubsidiaryContactInfoDto
     * )
     */
    @Override
    public int maintain(SubsidiaryContactInfoDto contact) throws SubsidiaryDaoException {
        return 0;
    }

    /**
     * Stub method.
     * 
     * @param businessId
     *            the business id of the subsidiary
     * @return Always return zero
     * @throws SubsidiaryDaoException
     */
    @Override
    public double calculateBalance(int businessId)
            throws SubsidiaryDaoException {
        return 0;
    }

    /**
     * Stub method
     * 
     * @param subXact
     *            an instnace of {@link SubsidiaryXactHistoryDto}
     * @return Always return zero
     * @throws SubsidiaryDaoException
     */
    @Override
    public int maintain(SubsidiaryXactHistoryDto subXact)
            throws SubsidiaryDaoException {
        return 0;
    }
}
