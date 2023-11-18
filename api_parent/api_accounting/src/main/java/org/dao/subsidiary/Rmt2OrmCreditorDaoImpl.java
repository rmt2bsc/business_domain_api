package org.dao.subsidiary;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.AccountingSqlConst;
import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.CreditorActivity;
import org.dao.mapping.orm.rmt2.CreditorType;
import org.dao.mapping.orm.rmt2.VwCreditorBalance;
import org.dao.mapping.orm.rmt2.VwCreditorXactHist;
import org.dto.CreditorDto;
import org.dto.CreditorTypeDto;
import org.dto.CreditorXactHistoryDto;
import org.dto.SubsidiaryXactHistoryDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;

import com.api.persistence.DatabaseException;
import com.api.persistence.PersistenceClient;
import com.api.util.RMT2Date;
import com.api.util.RMT2String;
import com.api.util.UserTimestamp;

/**
 * An implementation of {@link CreditorDao}. It provides functionality that
 * creates, updates, deletes, and queries creditor subsidiary account data.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmCreditorDaoImpl extends AbstractRmt2SubsidiaryContactDaoImpl implements CreditorDao {

    /**
     * Construce a Rmt2OrmCreditorDaoImpl object initialized with a connection
     * to the database
     */
    public Rmt2OrmCreditorDaoImpl() {
        super();
    }

    public Rmt2OrmCreditorDaoImpl(String appName) {
        super(appName);
        return;
    }

    public Rmt2OrmCreditorDaoImpl(PersistenceClient client) {
        super(client);
        return;
    }

    /**
     * Calculate and return the creditor's balance.
     * 
     * @param creditorId
     *            the unique id of the creditor.
     * @return the balance as a double
     * @throws CreditorDaoException
     */
    @Override
    public double calculateBalance(int creditorId) throws SubsidiaryDaoException {
        String sql = RMT2String.replace(AccountingSqlConst.SQL_CREDITOR_BALANCE,
                String.valueOf(creditorId), "$1");
        double bal = 0;
        try {
            ResultSet rs = this.client.executeSql(sql);
            if (rs.next()) {
                bal = rs.getDouble("balance");
            }
            return bal;
        } catch (Exception e) {
            throw new CreditorDaoException(e);
        }
    }

    @Override
    public CreditorDto fetch(int creditorId) throws CustomerDaoException {
        // Retrieve Creditor data
        Creditor criteria = new Creditor();
        criteria.addCriteria(Creditor.PROP_CREDITORID, creditorId);
        try {
            Creditor results = (Creditor) this.client.retrieveObject(criteria);
            if (results == null) {
                return null;
            }
            // Package data
            CreditorDto dto = Rmt2SubsidiaryDtoFactory.createCreditorInstance(results, null);
            return dto;
        } catch (DatabaseException e) {
            throw new CustomerDaoException(e);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.CreditorDao#fetch(org.dto.CreditorTypeDto)
     */
    @Override
    public List<CreditorTypeDto> fetch(CreditorTypeDto criteria) throws CreditorDaoException {
        // Gather creditor type criteria
        CreditorType ormCred = null;
        if (criteria != null) {
            ormCred = new CreditorType();
            if (criteria.getEntityId() > 0) {
                ormCred.addCriteria(CreditorType.PROP_CREDITORTYPEID, criteria.getEntityId());
            }
            if (criteria.getEntityName() != null) {
                ormCred.addCriteria(CreditorType.PROP_DESCRIPTION, criteria.getEntityName());
            }
        }
        // Retrieve creditor data from the database
        List<CreditorType> results = this.fetch(ormCred);
        if (results == null) {
            return null;
        }
        
        List<CreditorTypeDto> list = new ArrayList<CreditorTypeDto>();
        for (CreditorType item : results) {
            CreditorTypeDto dto = Rmt2SubsidiaryDtoFactory.createCreditorTypeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches the creditor's transaction history from the
     * <i>creditor_activitiy</i> table.
     * 
     * @param creditorId
     *            An integer representing the creditor id.
     * @return a list of {@link CreditorXactHistoryDto} objects or null when the
     *         query returns an empty result set.
     * @throws CreditorDaoException
     */
    @Override
    public List<CreditorXactHistoryDto> fetchTransactionHistory(int creditorId)
            throws CreditorDaoException {
        VwCreditorXactHist obj = new VwCreditorXactHist();
        obj.addCriteria(VwCreditorXactHist.PROP_CREDITORID, creditorId);
        obj.addOrderBy(VwCreditorXactHist.PROP_XACTDATE,
                VwCreditorXactHist.ORDERBY_DESCENDING);
        obj.addOrderBy(VwCreditorXactHist.PROP_XACTID,
                VwCreditorXactHist.ORDERBY_DESCENDING);

        List<VwCreditorXactHist> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CreditorDaoException(e);
        }

        List<CreditorXactHistoryDto> list = new ArrayList<CreditorXactHistoryDto>();
        for (VwCreditorXactHist item : results) {
            CreditorXactHistoryDto dto = Rmt2SubsidiaryDtoFactory
                    .createCreditorTransactionInstance(item);
            list.add(dto);
        }
        return list;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.CreditorDao#fetch(org.dto.CreditorDto)
     */
    @Override
    public List<CreditorDto> fetch(CreditorDto criteria) throws CreditorDaoException {
        // Gather creditor criteria
        Creditor ormCred = null;
        if (criteria != null) {
            ormCred = new Creditor();
            if (criteria.getContactIdList() != null && criteria.getContactIdList().size() > 0) {
                Integer[] intArray = criteria.getContactIdList().toArray(new Integer[criteria.getContactIdList().size()]);
                ormCred.addInClause(Creditor.PROP_BUSINESSID, intArray);
            }
            if (criteria.getCreditorId() > 0) {
                ormCred.addCriteria(Creditor.PROP_CREDITORID, criteria.getCreditorId());
            }
            if (criteria.getContactId() > 0) {
                ormCred.addCriteria(Creditor.PROP_BUSINESSID, criteria.getContactId());
            }
            if (criteria.getAcctId() > 0) {
                ormCred.addCriteria(Creditor.PROP_ACCTID, criteria.getAcctId());
            }
            if (criteria.getAccountNo() != null) {
                ormCred.addLikeClause(Creditor.PROP_ACCOUNTNUMBER, criteria.getAccountNo());
            }
            if (criteria.getExtAccountNumber() != null) {
                ormCred.addLikeClause(Creditor.PROP_EXTACCOUNTNUMBER, criteria.getExtAccountNumber());
            }
            if (criteria.getCreditorTypeId() > 0) {
                ormCred.addCriteria(Creditor.PROP_CREDITORTYPEID, criteria.getCreditorTypeId());
            }
        }
        // Retrieve creditor data from the database
        List<Creditor> results = this.fetch(ormCred);
        if (results == null || results.isEmpty()) {
            return null;
        }
        
        List<CreditorDto> list = new ArrayList<CreditorDto>();
        for (Creditor item : results) {
            CreditorDto dto = Rmt2SubsidiaryDtoFactory.createCreditorInstance(item, null);
            list.add(dto);
        }
        return list;
    }

    private List<Creditor> fetch(Creditor criteria) throws CreditorDaoException {
        // Retrieve creditor data from the database
        try {
            return this.client.retrieveList(criteria);
        } catch (DatabaseException e) {
            throw new CreditorDaoException(e);
        }
    }
    
    private List<CreditorType> fetch(CreditorType criteria) throws CreditorDaoException {
        // Retrieve creditor type data from the database
        try {
            return this.client.retrieveList(criteria);
        } catch (DatabaseException e) {
            throw new CreditorDaoException(e);
        }
    }


    /**
     * Associate a transaction history item to a creditor subsidiary account.
     * 
     * @param creditorXact
     *            an instnace of {@link SubsidiaryXactHistoryDto}
     * @return the total number of items associated with the subsidiary account.
     * @throws SubsidiaryDaoException
     */
    @Override
    public int maintain(SubsidiaryXactHistoryDto creditorXact)
            throws SubsidiaryDaoException {
        if (creditorXact == null) {
            throw new CreditorDaoException(
                    "Input creditor subsidiary transaction item object is invalid or null");
        }
        CreditorActivity ca = SubsidiaryDaoFactory.createCreditorActivity(
                creditorXact.getSubsidiaryId(), creditorXact.getXactId(),
                creditorXact.getActivityAmount());
        UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
        ca.setDateCreated(ut.getDateCreated());
        ca.setDateUpdated(ut.getDateCreated());
        ca.setUserId(ut.getLoginId());
        ca.setIpCreated(ut.getIpAddr());
        ca.setIpUpdated(ut.getIpAddr());

        try {
            int rc = this.client.insertRow(ca, true);
            return rc;
        } catch (DatabaseException e) {
            throw new CreditorDaoException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.CreditorDao#maintain(org.dto.CreditorDto)
     */
    @Override
    public int maintain(CreditorDto cred) throws CreditorDaoException {
        Creditor orm = SubsidiaryDaoFactory.createRmt2OrmCreditorBean(cred);
        int rc = 0;
        if (orm.getCreditorId() <= 0) {
            rc = this.insert(orm);
        }
        else {
            rc = this.update(orm);
        }
        return rc;
    }

    private int insert(Creditor cred) throws CreditorDaoException {
        // Handle user update timestamps
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            cred.setDateCreated(ut.getDateCreated());
            cred.setDateUpdated(ut.getDateCreated());
            cred.setUserId(ut.getLoginId());
            cred.setIpCreated(ut.getIpAddr());
            cred.setIpUpdated(ut.getIpAddr());
        } catch (Exception e) {
            throw new CreditorDaoException(e);
        }

        // Perform the actual insert of creditor.
        try {
            int newKey = this.client.insertRow(cred, true);
            cred.setCreditorId(newKey);
            return newKey;
        } catch (DatabaseException e) {
            throw new CreditorDaoException(e);
        }
    }

    private int update(Creditor cred) throws CreditorDaoException {
        // Handle user update timestamps
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            cred.setDateUpdated(ut.getDateCreated());
            cred.setUserId(ut.getLoginId());
            cred.setIpUpdated(ut.getIpAddr());
        } catch (Exception e) {
            throw new CreditorDaoException(e);
        }

        // Perform the actual update of creditor.
        try {
            cred.addCriteria(Creditor.PROP_CREDITORID, cred.getCreditorId());
            int rc = this.client.updateRow(cred);
            return rc;
        } catch (DatabaseException e) {
            throw new CreditorDaoException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.CreditorDao#delete(int)
     */
    @Override
    public int delete(int credId) throws CreditorDaoException {
        Creditor criteria = new Creditor();
        try {
            criteria.addCriteria(Creditor.PROP_CREDITORID, credId);
            int rc = this.client.deleteRow(criteria);
            return rc;
        } catch (DatabaseException e) {
            throw new CreditorDaoException(e);
        }
    }

    // UI-28: Added for fetching balances for all creditors
    @Override
    public Map<Integer, Double> getBalances() throws SubsidiaryDaoException {
        // Retrieve balances for all creditors
        VwCreditorBalance criteria = new VwCreditorBalance();
        List<VwCreditorBalance> results = this.client.retrieveList(criteria);
        if (results == null || results.isEmpty()) {
            return null;
        }

        Map<Integer, Double> map = new HashMap<>();
        for (VwCreditorBalance item : results) {
            map.put(item.getBusinessId(), item.getBalance());
        }
        return map;
    }
}
