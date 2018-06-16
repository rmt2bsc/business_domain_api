package org.dao.subsidiary;

import java.util.List;

import org.dto.SubsidiaryXactHistoryDto;

import com.api.persistence.PersistenceClient;

/**
 * @author Roy Terrell
 * 
 */
public class DefaultRmt2SubsidiaryContactDaoImpl extends
        AbstractRmt2SubsidiaryContactDaoImpl {

    /**
     * 
     */
    public DefaultRmt2SubsidiaryContactDaoImpl() {
        return;
    }

    public DefaultRmt2SubsidiaryContactDaoImpl(String appName) {
        super(appName);
    }

    /**
     * @param client
     */
    public DefaultRmt2SubsidiaryContactDaoImpl(PersistenceClient client) {
        super(client);
    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see org.dao.subsidiary.SubsidiaryDao#fetchDomain(org.dto.SubsidiaryDto)
//     */
//    @Override
//    public List<SubsidiaryDto> fetchDomain(SubsidiaryDto criteria)
//            throws SubsidiaryDaoException {
//        return null;
//    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.SubsidiaryDao#fetchTransactionHistory(int)
     */
    @Override
    public List<SubsidiaryXactHistoryDto> fetchTransactionHistory(
            int subsidiaryId) throws SubsidiaryDaoException {
        // TODO Auto-generated method stub
        return null;
    }

}
