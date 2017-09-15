package org.dao.transaction;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.AccountingDaoImpl;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactCategory;
import org.dao.mapping.orm.rmt2.XactCodeGroup;
import org.dao.mapping.orm.rmt2.XactCodes;
import org.dao.mapping.orm.rmt2.XactType;
import org.dao.mapping.orm.rmt2.XactTypeItem;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dto.XactCategoryDto;
import org.dto.XactCodeDto;
import org.dto.XactCodeGroupDto;
import org.dto.XactDto;
import org.dto.XactTypeDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.XactTypeItemDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;

import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
import com.api.persistence.PersistenceClient;
import com.util.RMT2Date;
import com.util.UserTimestamp;

/**
 * An basic implementation of {@link XactDao} which accesses and manipulates
 * data from database tables <i>xact</i>, <i>xact_type_item_activity</i>,
 * <i>xact_type</i>, <i>xact_type_item</i>, <i>xact_category</i>,
 * <i>xact_code_group</i>, and <i>xact_codes</i>.
 * <p>
 * This implementation is the basis for adding an entry into the <i>xact</i>
 * table, which is the root of all transaction activity in the system.
 * 
 * @author Roy Terrell
 * 
 */
public class Rmt2XactDaoImpl extends AccountingDaoImpl implements XactDao {

    private static Logger logger = Logger.getLogger(Rmt2XactDaoImpl.class);

    /**
     * Default constructor for creating a Rmt2XactDaoImpl object
     */
    public Rmt2XactDaoImpl() {
        super();
        return;
    }

    /**
     * Create a Rmt2XactDaoImpl object with a known valid link to some data
     * source
     * 
     * @param appName
     *            application name
     */
    public Rmt2XactDaoImpl(String appName) {
        super(appName);
        return;
    }

    /**
     * Create a Rmt2XactDaoImpl object with a known valid link to some data
     * source
     * 
     * @param client
     *            an instance of {@link PersistenceClient}
     */
    public Rmt2XactDaoImpl(PersistenceClient client) {
        super(client);
        return;
    }

    /**
     * Retrieves a list of common transaction items from the vw_xact_list
     * database view.
     * <p>
     * The selection criteria is built from <i>criteria</i> which the following
     * properties are recognized: xactId, xactTypeId, xactCatgId, xactDate,
     * confirmNo, and tenderId.
     * <p>
     * The result set is ordered by transaction date and transaction id in
     * descending order.
     * 
     * @param criteria
     *            The selection criteria to apply to the query of data source.
     *            When null, all rows are fetched.
     * @returnA List of {@link XactDto} objects.
     * @throws XactDaoException
     *             general data access errors
     */
    @Override
    public List<XactDto> fetchXact(XactDto criteria) throws XactDaoException {
        VwXactList ormCriteria = XactDaoFactory.createCriteria(criteria);
        ormCriteria.addOrderBy(VwXactList.PROP_XACTDATE,
                VwXactList.ORDERBY_DESCENDING);
        ormCriteria.addOrderBy(VwXactList.PROP_ID,
                VwXactList.ORDERBY_DESCENDING);

        // Retrieve Data
        List<VwXactList> results = null;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<XactDto> list = new ArrayList<XactDto>();
        for (VwXactList item : results) {
            XactDto dto = Rmt2XactDtoFactory.createXactInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.transaction.XactDao#fetchCategory(org.dto.XactCategoryDto)
     */
    @Override
    public List<XactCategoryDto> fetchCategory(XactCategoryDto criteria) throws XactDaoException {
        XactCategory ormCriteria = XactDaoFactory.createCriteria(criteria);
        ormCriteria.addOrderBy(XactCategory.PROP_DESCRIPTION, ItemMaster.ORDERBY_ASCENDING);

        // Retrieve Data
        List<XactCategory> results = null;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<XactCategoryDto> list = new ArrayList<XactCategoryDto>();
        for (XactCategory item : results) {
            XactCategoryDto dto = Rmt2XactDtoFactory.createXactCategoryInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.transaction.XactDao#fetchGroup(org.dto.XactCodeGroupDto)
     */
    @Override
    public List<XactCodeGroupDto> fetchGroup(XactCodeGroupDto criteria) throws XactDaoException {
        XactCodeGroup ormCriteria = XactDaoFactory.createCriteria(criteria);
        ormCriteria.addOrderBy(XactCodeGroup.PROP_DESCRIPTION, ItemMaster.ORDERBY_ASCENDING);

        // Retrieve Data
        List<XactCodeGroup> results = null;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<XactCodeGroupDto> list = new ArrayList<XactCodeGroupDto>();
        for (XactCodeGroup item : results) {
            XactCodeGroupDto dto = Rmt2XactDtoFactory.createXactCodeGroupInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.transaction.XactDao#fetchCode(org.dto.XactCodeDto)
     */
    @Override
    public List<XactCodeDto> fetchCode(XactCodeDto criteria)
            throws XactDaoException {
        XactCodes ormCriteria = XactDaoFactory.createCriteria(criteria);
        ormCriteria.addOrderBy(XactCodes.PROP_DESCRIPTION, ItemMaster.ORDERBY_ASCENDING);

        // Retrieve Data
        List<XactCodes> results = null;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<XactCodeDto> list = new ArrayList<XactCodeDto>();
        for (XactCodes item : results) {
            XactCodeDto dto = Rmt2XactDtoFactory.createXactCodeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.transaction.XactDao#fetchType(org.dto.XactTypeDto)
     */
    @Override
    public List<XactTypeDto> fetchType(XactTypeDto criteria)
            throws XactDaoException {
        XactType ormCriteria = XactDaoFactory.createCriteria(criteria);
        ormCriteria.addOrderBy(XactCodes.PROP_DESCRIPTION,
                ItemMaster.ORDERBY_ASCENDING);

        // Retrieve Data
        List<XactType> results = null;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<XactTypeDto> list = new ArrayList<XactTypeDto>();
        for (XactType item : results) {
            XactTypeDto dto = Rmt2XactDtoFactory.createXactTypeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.transaction.XactDao#fetchXactTypeItem(org.dto.XactTypeItemDto)
     */
    @Override
    public List<XactTypeItemDto> fetchXactTypeItem(XactTypeItemDto criteria)
            throws XactDaoException {
        XactTypeItem ormCriteria = XactDaoFactory.createCriteria(criteria);
        ormCriteria.addOrderBy(XactCodes.PROP_DESCRIPTION,
                ItemMaster.ORDERBY_ASCENDING);

        // Retrieve Data
        List<XactTypeItem> results = null;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<XactTypeItemDto> list = new ArrayList<XactTypeItemDto>();
        for (XactTypeItem item : results) {
            XactTypeItemDto dto = Rmt2XactDtoFactory
                    .createXactTypeItemInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.transaction.XactDao#fetchXactTypeItemActivity(org.dto.
     * XactTypeItemActivityDto)
     */
    @Override
    public List<XactTypeItemActivityDto> fetchXactTypeItemActivity(XactTypeItemActivityDto criteria) 
            throws XactDaoException {
        XactTypeItemActivity ormCriteria = XactDaoFactory.createCriteria(criteria);
        ormCriteria.addOrderBy(XactCodes.PROP_DESCRIPTION, ItemMaster.ORDERBY_ASCENDING);

        // Retrieve Data
        List<XactTypeItemActivity> results = null;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<XactTypeItemActivityDto> list = new ArrayList<XactTypeItemActivityDto>();
        for (XactTypeItemActivity item : results) {
            XactTypeItemActivityDto dto = Rmt2XactDtoFactory
                    .createXactTypeItemActivityInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Retrieves one or more extended transaction type item activity objects
     * related to xactId from the vw_xact_type_item_activity view.
     * 
     * @param criteria
     *            An instance of {@link XactTypeItemActivityDto} representing
     *            the selection criteria to limit result set
     * @return A List of {@link XactTypeItemActivityDto} objects or null if no
     *         data is found
     * @throws XactDaoException
     */
    @Override
    public List<XactTypeItemActivityDto> fetchXactTypeItemActivityExt(
            XactTypeItemActivityDto criteria) throws XactDaoException {
        VwXactTypeItemActivity ormCriteria = XactDaoFactory
                .createCriteriaExt(criteria);
        ormCriteria.addOrderBy(VwXactTypeItemActivity.PROP_XACTDATE,
                VwXactTypeItemActivity.ORDERBY_DESCENDING);
        ormCriteria.addOrderBy(VwXactTypeItemActivity.PROP_XACTTYPEITEMNAME,
                VwXactTypeItemActivity.ORDERBY_ASCENDING);
        ormCriteria.addOrderBy(VwXactTypeItemActivity.PROP_ITEMNAME,
                VwXactTypeItemActivity.ORDERBY_ASCENDING);

        // Retrieve Data
        List<VwXactTypeItemActivity> results = null;
        try {
            results = this.client.retrieveList(ormCriteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<XactTypeItemActivityDto> list = new ArrayList<XactTypeItemActivityDto>();
        for (VwXactTypeItemActivity item : results) {
            XactTypeItemActivityDto dto = Rmt2XactDtoFactory
                    .createXactTypeItemActivityExtInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Adds the base transaction and it items to the xact and
     * xact_type_item_activity tables, respectively.
     * 
     * @param xact
     *            An instance of {@link XactDto} representing the transaction
     *            object to be managed.
     * @param items
     *            A List of {@link XactTypeItemActivityDto} instances
     *            representing the transaction's items.
     * @return The new transacton id.
     * @throws XactDaoException
     */
    @Override
    public int maintain(XactDto xact, List<XactTypeItemActivityDto> items)
            throws XactDaoException {
        Xact ormXact = XactDaoFactory.createXact(xact);
        List<XactTypeItemActivity> ormItems = XactDaoFactory.createXactItemActivity(items);
        // Handle base transaction
        int xactId = 0;
        try {
            xactId = this.insert(ormXact);
            xact.setXactId(xactId);
            logger.info("Transaction, " + xactId
                    + ", was created for the amount of $"
                    + xact.getXactAmount());
        } catch (Exception e) {
            this.msg = "A database error prevented the creation of base transaction";
            logger.error(this.msg, e);
            throw new XactDaoException(this.msg, e);
        }
        // Handle transaction items
        int itemCount = 0;
        try {
            itemCount = this.insert(ormXact, ormItems);
            logger.info("Base Transaction, " + xactId + ", created " + itemCount
                    + " activitiy items");
        } catch (Exception e) {
            this.msg = "Error occurred creating a transaction detail item for base transaction id, "
                    + xactId;
            logger.error(this.msg, e);
            throw new XactDaoException(this.msg, e);
        }
        return xactId;
    }

    /**
     * Updates an existing transaction entry.
     * 
     * @param xact
     *            The target transaction.
     * @return total number of rows effected.
     * @throws XactDaoException
     */
    @Override
    public int maintain(XactDto xact) throws XactDaoException {
        Xact ormXact = XactDaoFactory.createXact(xact);
        // Handle base transaction
        int xactId = 0;
        try {
            xactId = this.update(ormXact);
            logger.info("Transaction, " + xactId + ", was updated");
        } catch (Exception e) {
            this.msg = "Unable to persist base transaction";
            logger.error(this.msg, e);
            throw new XactDaoException(this.msg, e);
        }
        return xactId;
    }

    /**
     * Inserts an entry to the xact table.
     * 
     * @param xact
     *            The target transaction.
     * @return New transaction id.
     * @throws DatabaseException
     */
    private int insert(Xact xact)  {
        int newXactId = 0;
        if (xact.getXactSubtypeId() <= 0) {
            xact.setNull("xactSubtypeId");
        }
        if (xact.getTenderId() == 0) {
            xact.setNull("tenderId");
        }
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            xact.setDateCreated(ut.getDateCreated());
            xact.setDateUpdated(ut.getDateCreated());
            xact.setUserId(ut.getLoginId());
            xact.setIpCreated(ut.getIpAddr());
            xact.setIpUpdated(ut.getIpAddr());
            newXactId = this.client.insertRow(xact, true);

            // Set transaction id with the auto-generated key.
            xact.setXactId(newXactId);
            return newXactId;
        } catch (Exception e) {
            this.msg = "Unable to persist base transaction";
            throw new DatabaseException(this.msg, e);
        }
    }

    /**
     * Updates a transaction entry in the xact table.
     * 
     * @param xact
     *            The target transaction.
     * @return total number of rows effected.
     * @throws XactDaoException
     */
    private int update(Xact xact) {
        int rc = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            if (xact.getTenderId() == 0) {
                xact.setNull("tenderId");
            }
            if (xact.getXactSubtypeId() == 0) {
                xact.setNull("xactSubtypeId");
            }
            xact.setDateUpdated(ut.getDateCreated());
            xact.setUserId(ut.getLoginId());
            xact.setIpUpdated(ut.getIpAddr());
            xact.addCriteria(Xact.PROP_XACTID, xact.getXactId());
            rc = this.client.updateRow(xact);
            return rc;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    /**
     * Inserts one or more entries into the xact_type_item_activity table.
     * <p>
     * Drives the process of creating entries into the xact_type_item_activity
     * table by cycling through all elements of the transaction type item array.
     * 
     * @param xact
     *            The base transaction that is to be associated with each
     *            transaction _xactItems element.
     * @param xactItems
     *            The list of transaction items to apply to the database whch is
     *            expected to be of type {@link XactTypeItemActivity}
     * @return 0 when either _xactItems is null or has no items available. > 0
     *         to indicate the number of items successfully processed.
     * @throws DatabaseException
     */
    private int insert(Xact xact, List<XactTypeItemActivity> xactItems) {
        int totalItems = 0;
        int succesCount = 0;
        double totalItemAmount = 0;
        XactTypeItemActivity xtia = null;

        // Return to caller if transaction type item array has not been
        // initialized.
        if (xactItems == null) {
            return 0;
        }
        else {
            totalItems = xactItems.size();
        }

        // Begin to add each transaction type item to the database.
        for (int ndx = 0; ndx < totalItems; ndx++) {
            xtia = (XactTypeItemActivity) xactItems.get(ndx);
            xtia.setXactId(xact.getXactId());
            this.insert(xtia);
            totalItemAmount = totalItemAmount + xtia.getAmount();
            succesCount++;
        }

        // Successfully return the total number of items added.
        return succesCount;
    }

    /**
     * Creates a entry in the xact_type_item_activity table.
     * 
     * @param _xtia
     * @return int - id of new transaction type item activity object.
     * @throws DatabaseException
     */
    private int insert(XactTypeItemActivity xtia) {
        int xactItemActivityId = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            xtia.setDateCreated(ut.getDateCreated());
            xtia.setDateUpdated(ut.getDateCreated());
            xtia.setUserId(ut.getLoginId());
            xtia.setIpCreated(ut.getIpAddr());
            xtia.setIpUpdated(ut.getIpAddr());
            xactItemActivityId = this.client.insertRow(xtia, true);

            // Set transaction id with the auto-generated key.
            xtia.setXactTypeItemActvId(xactItemActivityId);
            return xactItemActivityId;
        } catch (Exception e) {
            this.msg = "Unable to persist base transaction detail item";
            throw new DatabaseException(this.msg, e);
        }
    }

}
