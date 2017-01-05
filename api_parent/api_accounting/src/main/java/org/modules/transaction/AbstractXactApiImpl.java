package org.modules.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.AccountingConst.SubsidiaryType;
import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dao.subsidiary.CreditorDao;
import org.dao.subsidiary.CustomerDao;
import org.dao.subsidiary.SubsidiaryDaoException;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dao.transaction.XactDao;
import org.dao.transaction.XactDaoFactory;
import org.dto.CreditorDto;
import org.dto.CreditorXactHistoryDto;
import org.dto.CustomerDto;
import org.dto.CustomerXactHistoryDto;
import org.dto.SubsidiaryDto;
import org.dto.XactCategoryDto;
import org.dto.XactCodeDto;
import org.dto.XactCodeGroupDto;
import org.dto.XactDto;
import org.dto.XactTypeDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.XactTypeItemDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.CustomerApi;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.subsidiary.SubsidiaryException;

import com.InvalidDataException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;
import com.util.RMT2Money;

/**
 * The base implementation of XactApi interface containing common transaction
 * functionality such as transaction creation, subsidiary transaction history
 * creation, transacction reversal, and transaction finalization.
 * <p>
 * This implementation forces the user to supply a valid DaoClient object via
 * the class constructor or setter method. This connection sharing will
 * facilitate transactions being executed under one unit of work.
 * 
 * @author Roy Terrell
 * 
 */
public abstract class AbstractXactApiImpl extends AbstractTransactionApiImpl
        implements XactApi {

    private static final Logger logger = Logger
            .getLogger(AbstractXactApiImpl.class);

    /**
     * Default constructor
     */
    protected AbstractXactApiImpl() {
        super();
        return;
    }

    /**
     * Creates an AbstractXactApiImpl initialized with a DaoClient object.
     * 
     * @param connection
     *            an instance of {@link DaoClient}
     */
    public AbstractXactApiImpl(DaoClient connection) {
        super(connection);
        return;
    }

    /**
     * Obtains a <i>XactDao</i> object using the shared DAO connection.
     * 
     * @return an instance of {@link XactDao}
     * @throws XactApiException
     *             the shared DAO instance is null, the shared DAO is valid but
     *             is not associated with a valid connection.
     */
    protected XactDao getXactDao() throws XactApiException {
        if (this.getSharedDao() == null) {
            this.msg = "Internal DaoClient object is not initialized";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        if (this.getSharedDao().getClient() == null) {
            this.msg = "Internal DaoClient's persistence object is not initialized";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        XactDaoFactory f = new XactDaoFactory();
        XactDao dao = f.createRmt2OrmXactDao(this.getSharedDao());
        dao.setDaoUser(this.getApiUser());
        return dao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactById(int)
     */
    @Override
    public XactDto getXactById(int xactId) throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactDto> results = null;
        try {
            XactDto criteria = Rmt2XactDtoFactory
                    .createXactInstance((Xact) null);
            criteria.setXactId(xactId);
            results = dao.fetchXact(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve common transaction object by xact id: "
                    + xactId;
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }

        if (results == null || results.size() <= 0) {
            return null;
        }
        if (results.size() > 1) {
            this.msg = "Common transaction query should of returned only 1 object based on xact id: "
                    + xactId
                    + ".  Instead "
                    + results.size()
                    + " objects were returned";
            throw new XactApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getAllCategory()
     */
    @Override
    public List<XactCategoryDto> getAllCategory() throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactCategoryDto> results = null;
        try {
            results = dao.fetchCategory(null);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve all transaction categories";
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getCategoryById(int)
     */
    @Override
    public XactCategoryDto getCategoryById(int catgId) throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactCategoryDto> results = null;
        try {
            XactCategoryDto criteria = Rmt2XactDtoFactory
                    .createXactCategoryInstance(null);
            criteria.setXactCatgId(catgId);
            results = dao.fetchCategory(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction category by category id: "
                    + catgId;
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }

        if (results == null || results.size() <= 0) {
            return null;
        }
        if (results.size() > 1) {
            this.msg = "Common category transaction query should of returned only 1 object based on xact category id: "
                    + catgId
                    + ".  Instead "
                    + results.size()
                    + " objects were returned";
            throw new XactApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getAllGroups()
     */
    @Override
    public List<XactCodeGroupDto> getAllGroups() throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactCodeGroupDto> results = null;
        try {
            results = dao.fetchGroup(null);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve all transaction groups";
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getGroup(int)
     */
    @Override
    public XactCodeGroupDto getGroup(int groupId) throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactCodeGroupDto> results = null;
        try {
            XactCodeGroupDto criteria = Rmt2XactDtoFactory
                    .createXactCodeGroupInstance(null);
            criteria.setEntityId(groupId);
            results = dao.fetchGroup(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction group by group id: "
                    + groupId;
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }

        if (results == null || results.size() <= 0) {
            return null;
        }
        if (results.size() > 1) {
            this.msg = "Common group transaction query should of returned only 1 object based on xact group id: "
                    + groupId
                    + ".  Instead "
                    + results.size()
                    + " objects were returned";
            throw new XactApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getAllCode()
     */
    @Override
    public List<XactCodeDto> getAllCode() throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactCodeDto> results = null;
        try {
            results = dao.fetchCode(null);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve all transaction codes";
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getCode(int)
     */
    @Override
    public XactCodeDto getCode(int codeId) throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactCodeDto> results = null;
        try {
            XactCodeDto criteria = Rmt2XactDtoFactory
                    .createXactCodeInstance(null);
            criteria.setEntityId(codeId);
            results = dao.fetchCode(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction code by code id: "
                    + codeId;
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }

        if (results == null || results.size() <= 0) {
            return null;
        }
        if (results.size() > 1) {
            this.msg = "Common code transaction query should of returned only 1 object based on xact group id: "
                    + codeId
                    + ".  Instead "
                    + results.size()
                    + " objects were returned";
            throw new XactApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getCodeByGroupId(int)
     */
    @Override
    public List<XactCodeDto> getCodeByGroupId(int groupId)
            throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactCodeDto> results = null;
        try {
            XactCodeDto criteria = Rmt2XactDtoFactory
                    .createXactCodeInstance(null);
            criteria.setGrpId(groupId);
            results = dao.fetchCode(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve code transaction objects by group id: "
                    + groupId;
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getAllXactType()
     */
    @Override
    public List<XactTypeDto> getAllXactType() throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactTypeDto> results = null;
        try {
            results = dao.fetchType(null);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve all transaction type objects";
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactType(int)
     */
    @Override
    public XactTypeDto getXactType(int typeId) throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactTypeDto> results = null;
        try {
            XactTypeDto criteria = Rmt2XactDtoFactory
                    .createXactTypeInstance(null);
            criteria.setXactTypeId(typeId);
            results = dao.fetchType(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction type objects by xact type id: "
                    + typeId;
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }

        if (results == null || results.size() <= 0) {
            return null;
        }
        if (results.size() > 1) {
            this.msg = "Common transaction type query should of returned only 1 object based on xact type id: "
                    + typeId
                    + ".  Instead "
                    + results.size()
                    + " objects were returned";
            throw new XactApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactTypeByCatgId(int)
     */
    @Override
    public List<XactTypeDto> getXactTypeByCatgId(int catgId)
            throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactTypeDto> results = null;
        try {
            XactTypeDto criteria = Rmt2XactDtoFactory
                    .createXactTypeInstance(null);
            criteria.setXactCatgId(catgId);
            results = dao.fetchType(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction type objects by xact category id: "
                    + catgId;
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactTypeItemActivity(int)
     */
    @Override
    public List<XactTypeItemActivityDto> getXactTypeItemActivity(int xactId)
            throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactTypeItemActivityDto> results = null;
        try {
            XactTypeItemActivityDto criteria = Rmt2XactDtoFactory
                    .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
            criteria.setXactId(xactId);
            results = dao.fetchXactTypeItemActivity(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction item type activity objects by xact id: "
                    + xactId;
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactTypeItemActivityExt(int)
     */
    @Override
    public List<XactTypeItemActivityDto> getXactTypeItemActivityExt(int xactId)
            throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactTypeItemActivityDto> results = null;
        try {
            XactTypeItemActivityDto criteria = Rmt2XactDtoFactory
                    .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
            criteria.setXactId(xactId);
            results = dao.fetchXactTypeItemActivityExt(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction item type activity objects by xact id: "
                    + xactId;
            throw new XactApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactTypeItemsByXactTypeId(int)
     */
    @Override
    public List<XactTypeItemDto> getXactTypeItemsByXactTypeId(int xactTypeId)
            throws XactApiException {
        XactDao dao = this.getXactDao();
        List<XactTypeItemDto> results = null;
        try {
            XactTypeItemDto criteria = Rmt2XactDtoFactory
                    .createXactTypeItemInstance(null);
            criteria.setXactTypeId(xactTypeId);
            results = dao.fetchXactTypeItem(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction type item objects by xact type id: "
                    + xactTypeId;
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactItems()
     */
    @Override
    public List<XactTypeItemActivityDto> getXactItems() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#setXactItems(java.util.List)
     */
    @Override
    public void setXactItems(List<XactTypeItemActivityDto> value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#update(org.dto.XactDto,
     * java.util.List)
     */
    @Override
    public int update(XactDto xact, List<XactTypeItemActivityDto> xactItems)
            throws XactApiException {

        try {
            this.validate(xact, xactItems);
        } catch (Exception e) {
            this.msg = "Common transaction update failed validation checks";
            logger.error(this.msg, e);
            throw new XactApiException(this.msg, e);
        }

        int rc = 0;
        XactDao dao = this.getXactDao();
        dao.setDaoUser(this.getApiUser());

        // Apply transaction changes
        try {
            this.preCreateXact(xact);
            rc = dao.maintain(xact, xactItems);
            this.postCreateXact(xact);
            return rc;
        } catch (Exception e) {
            this.msg = "Error occurred persisting common transaction changes";
            logger.error(this.msg, e);
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /**
     * Ensures that transaction date has a value before applying the transaction
     * to the database. If transaction date is null or does not exist, default
     * transaction date to the current day. Override this method to execute
     * custom logic before base transaction is added to the database.
     * 
     * @param xact
     *            The target transaction
     */
    protected void preCreateXact(XactDto xact) {
        java.util.Date today = new java.util.Date();
        if (xact.getXactDate() == null) {
            xact.setXactDate(today);
        }
        return;
    }

    /**
     * Override this method to execute custom logic after base transaction is
     * added to the database.
     * 
     * @param xact
     *            The target transaction
     */
    protected void postCreateXact(XactDto _xact) {
        return;
    }

    /**
     * Performs basic validations on the incoming transaction instance and the
     * collection of transaction item instances. <i>xact</i> is required to be
     * not null. <i>xactItems</i> is not required and is checked only when it is
     * not null. When <i>xactItems</i> contains one or more items, the sum of
     * those items must equal the transaction amount found in <i>xact</i>. This
     * method does not recognize the transaction details when the transaction is
     * linked to a subsidiary account (Creditor or Customer).
     * <p>
     * Override this method to add specific validations pertaining to the
     * business requirement of the descendent.
     * 
     * @param xact
     *            An instance of {@link XactDto} serving as the base
     *            transaction.
     * @param xactItems
     *            A List of {@link XactTypeItemActivityDto} serving as the
     *            transaction items
     * @throws XactApiException
     *             When <i>xact</i> does not meet basic validation requirements,
     *             one or more elements in <i>xactItems</i> do not meet basic
     *             validation requirements, or the sum of the transaction item
     *             amount of each element of <i>xactItems</i> does not equal the
     *             transaction amount found in <i>xact</i>.
     */
    @Override
    public void validate(XactDto xact, List<XactTypeItemActivityDto> xactItems)
            throws XactApiException {
        // Validate base transaction
        this.validate(xact);

        // At this level, it is okay to save a transaction without transaction
        // items. For some transactions (accounts with subsidiaries), it is okay
        // to not have any transaction detail items
        if (xactItems != null) {
            this.validate(xactItems);
            if (xactItems.size() > 0) {
                this.validateTransactionAmounts(xact, xactItems);
            }
        }

    }

    /**
     * Validates the base of the transaction. The following validations must be
     * satified:
     * <ul>
     * <li>Base transaction object is valid</li>
     * <li>Base transaction type id is greater than zero</li>
     * </ul>
     * 
     * @throws XactException
     */
    private void validate(XactDto xact) throws XactApiException {
        // Execute custom pre validations
        this.preValidate(xact);

        // Validate the Transaction Type
        if (xact.getXactTypeId() <= 0) {
            this.msg = "Transaction Type code must be greater than zero";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }

        // validate money expression
        String temp = String.valueOf(xact.getXactAmount());
        try {
            RMT2Money.validateMoney(temp);
        } catch (InvalidDataException e) {
            throw new XactApiException(e);
        }

        // // Validate tender id is set
        // if (xact.getXactTenderId() <= 0) {
        // this.msg = "Transaction tender id must be greater than zero";
        // logger.error(this.msg);
        // throw new XactApiException(this.msg);
        // }

        // Execute custom post validations
        this.postValidate(xact);
    }

    /**
     * Override this method to execute custom logic before base transaction
     * validations.
     * 
     * @param xact
     *            The transaction object to be validated
     * @throws XactApiException
     *             Validation error occurred.
     */
    protected void preValidate(XactDto xact) throws XactApiException {
        if (xact == null) {
            this.msg = "Transaction Base could not be updated since base object is null";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        return;
    }

    /**
     * Override this method to execute custom logic after base transaction
     * validations.
     * 
     * @param xact
     *            The transaction object to be validated.
     * @throws XactApiException
     *             Validation error occurred.
     */
    protected void postValidate(XactDto xact) throws XactApiException {
        return;
    }

    private void validate(List<XactTypeItemActivityDto> items)
            throws XactApiException {
        this.preValidate(items);
        if (items == null) {
            return;
        }
        for (XactTypeItemActivityDto item : items) {
            this.validate(item);
        }
        this.postValidate(items);
    }

    /**
     * Override this method to execute custom logic before base transaction
     * Items list validations.
     * 
     * @param items
     * @throws XactApiException
     */
    protected void preValidate(List<XactTypeItemActivityDto> items)
            throws XactApiException {
        return;
    }

    /**
     * Override this method to execute custom logic after base transaction Items
     * list validations.
     * 
     * @param items
     * @throws XactApiException
     */
    protected void postValidate(List<XactTypeItemActivityDto> items)
            throws XactApiException {
        return;
    }

    /**
     * Validates a transaction type item object by ensuring that a transaction
     * type item id and item description is provided. Validates the base of the
     * transaction. The following validations must be satified:
     * <ul>
     * <li>Transaction Type Item Activity object must be valid</li>
     * <li>Transaction Type Item Id must valid (greater than zero)</li>
     * <li>Transaction Type Item Activity Description cannot be null</li>
     * </ul>
     * 
     * @param item
     *            {@link com.bean.XactTypeItemActivity XactTypeItemActivity}
     *            instance.
     * @throws XactApiException
     *             When <i>xtia</i> is null, its id property is less than or
     *             equal to zero, or its description property is null.
     */
    private void validate(XactTypeItemActivityDto item) throws XactApiException {
        // Execute custom pre validation logic.
        this.preValidateXactItem(item);

        if (item == null) {
            this.msg = "Transaction type item activity object is invalid or null";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        if (item.getXactItemId() <= 0) {
            this.msg = "Transaction type item id property is required to have a value";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        if (item.getXactTypeItemActvName() == null
                || item.getXactTypeItemActvName().length() <= 0) {
            this.msg = "Transaction type item description property is required to have a value";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }

        // Execute custom post validation logic.
        this.postValidateXactItem(item);

        return;
    }

    /**
     * Override this method to execute custom logic before base transaction Item
     * Activity validations.
     * 
     * @param item
     * @throws XactApiException
     */
    protected void preValidateXactItem(XactTypeItemActivityDto item)
            throws XactApiException {
        return;
    }

    /**
     * Override this method to execute custom logic after base transaction Item
     * Activity validations.
     * 
     * @param item
     * @throws XactApiException
     */
    protected void postValidateXactItem(XactTypeItemActivityDto item)
            throws XactApiException {
        return;
    }

    /**
     * Validates that the base transaction amount is equal to the sum of
     * transaction item amounts.
     * <p>
     * For general disbursements and creditor disbursements,
     * 
     * @param xact
     *            instance of {@link XactDto}
     * @param items
     *            a List of {@link XactTypeItemActivityDto}
     * @throws XactApiException
     */
    private void validateTransactionAmounts(XactDto xact,
            List<XactTypeItemActivityDto> items) throws XactApiException {
        // Return to caller if transaction type item array has not been
        // initialized.
        if (items == null) {
            this.msg = "The list of common transaction items are invalid or null";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        // Verify that at least one item exists.
        if (items.size() == 0) {
            this.msg = "A minimum of one transaction item must exist in order to process the common transaction";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        // Begin to sum each transaction type item amount.
        double totalItemAmount = 0;
        for (XactTypeItemActivityDto item : items) {
            totalItemAmount = totalItemAmount + item.getActivityAmount();
        }
        // Round summed item amount
        BigDecimal bd = new BigDecimal(totalItemAmount);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        totalItemAmount = bd.doubleValue();

        // Verify that transaction amount must equal the sum of all item
        // amounts.
        if (Math.abs(xact.getXactAmount()) != Math.abs(totalItemAmount)) {
            this.msg = "The base transaction amount ["
                    + Math.abs(xact.getXactAmount())
                    + "] must equal the sum of all transaction item amounts ["
                    + totalItemAmount + "]";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
    }

    /**
     * Reverses a transaction and its detail items.
     * <p>
     * The process of reversing a transaction is to multiply the transactin
     * amount by -1 and providing a calculated reason. As a result of this
     * operation, the original transaction amount is permanently changed by
     * offsetting the previous transaction.
     * 
     * @param xact
     *            The base transaction that is to be reversed.
     * @param xactItems
     *            Transaction items to be reversed.
     * @return New id of the reversed transaction.
     * @throws XactApiException
     */
    @Override
    public int reverse(XactDto xact, List<XactTypeItemActivityDto> xactItems)
            throws XactApiException {
        int rc = 0;
        this.preReverse(xact, xactItems);
        this.reverse(xact);
        this.reverse(xactItems);
        xact.setXactId(0);
        rc = this.update(xact, xactItems);
        xact.setXactId(rc);
        this.postReverse(xact, xactItems);
        return rc;
    }

    /**
     * Reverses the base transaction amount. As a result of this operation, the
     * internal transaction amount is permanently changed. The reversal process
     * simply multiplies a -1 to the base transaction amount which basically
     * will zero out the transaction.
     * <p>
     * <b>Note:</b><br>
     * The transaction type id of the original transaction is carried over to
     * the reverse transaction to maintain history. The reversal transction code
     * is identified as <i>xactSubType</i>.
     * 
     * @param The
     *            base transaction object to reverse which is an instance of
     *            {@link XactDto}.
     */
    private void reverse(XactDto xact) {
        xact.setXactSubtypeId(XactConst.XACT_TYPE_REVERSE);
        xact.setXactAmount(xact.getXactAmount() * XactConst.REVERSE_MULTIPLIER);
        String reason1 = "Reversed Transaction " + xact.getXactId();
        String reason2 = (xact.getXactReason() == null ? "" : " "
                + xact.getXactReason());
        xact.setXactReason(reason1 + reason2);
        return;
    }

    /**
     * Reverses all items of an existing transaction withou applying changes to
     * the database.
     * <p>
     * By default each item is expected to be of type
     * {@link XactTypeItemActivityDto}. See documentation on method, Object
     * reverseXactItems(Object, int), about overriding to utilize transaction
     * items of a different data type.
     * 
     * @param xactItems
     *            Transaction items to be reversed.
     * @return A List of {@link XactTypeItemActivityDto} objects that were
     *         reversed.
     */
    private List<XactTypeItemActivityDto> reverse(
            List<XactTypeItemActivityDto> xactItems) {
        if (xactItems == null) {
            return null;
        }
        List<XactTypeItemActivityDto> newList = new ArrayList<XactTypeItemActivityDto>();
        int total = xactItems.size();
        for (int ndx = 0; ndx < total; ndx++) {
            this.reverse(xactItems.get(ndx));
            newList.add(xactItems.get(ndx));
        }
        return newList;
    }

    /**
     * Reverses transaction item, <I>xtia</i>.
     * <p>
     * As a result of this operation, the internal item's transaction amount is
     * permanently changed. The reversal process simply multiplies a -1 to the
     * item's transaction amount which basically will zero out the item. By
     * default, the runtime type of each item is expected to be
     * {@link XactTypeItemActivityDto}.
     * 
     * @param xtia
     *            An instance of {@link XactTypeItemActivityDto} which is the
     *            transaction item object that is to be reversed.
     */
    protected void reverse(XactTypeItemActivityDto xtia) {
        double amount = xtia.getXactAmount() * XactConst.REVERSE_MULTIPLIER;
        xtia.setXactAmount(amount);

        // Reset id to make it appear that this is a new entry.
        xtia.setXactTypeItemActvId(0);
        return;
    }

    /**
     * Override this method to perform any transaction reversal logic before
     * <i>xact</i> is applied to the database.
     * <p>
     * This method is invoked from the reverseXact method just after the
     * modification of the transaction amount and reason.
     * 
     * @param xact
     *            The transaction that is being reversed.
     * @param xactItems
     *            Transaction items to be reversed.
     */
    protected void preReverse(XactDto xact,
            List<XactTypeItemActivityDto> xactItems) {
        return;
    }

    /**
     * Override this method to perform any transaction reversal logic after
     * <i>xact</i> is applied to the database.
     * <p>
     * This method is invoked from the reverseXact method
     * 
     * @param xact
     *            The transaction that is being reversed.
     * @param xactItems
     *            Transaction items to be reversed.
     */
    protected void postReverse(XactDto xact,
            List<XactTypeItemActivityDto> xactItems) {
        return;
    }

    /**
     * Creates transacction history activity for either a customer or a creditor
     * subsidiary account.
     * 
     * @param subsidiaryId
     *            The id of the customer or creditor subsidiary account
     * @param xactId
     *            The id of the transaction
     * @param amount
     *            The transaction amount
     * @return The unique identifier of the new transaction activity of the
     *         customer or creditor.
     * @throws XactApiException
     */
    @Override
    public int createSubsidiaryTransaction(int subsidiaryId, int xactId,
            double amount) throws XactApiException {
        int rc = 0;
        // Determine the type of subsidiary we are dealing with
        SubsidiaryType subType = this.evaluateSubsidiaryType(subsidiaryId);
        if (subType == null) {
            this.msg = "Subsidiary id passed is invalid.  Must be SubsidiaryType.CREDITOR or SubsidiaryType.CUSTOMER";
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }
        // validate transaction id
        XactDto xactDto = this.getXactById(xactId);
        if (xactDto == null) {
            this.msg = "Unable to create subidiary transaction activity for subsidiary, "
                    + subsidiaryId
                    + ", due to transaction id is not valid and/or does not exist: "
                    + xactId;
            logger.error(this.msg);
            throw new XactApiException(this.msg);
        }

        // Create transaction for creditor
        XactDao xactDao = this.getXactDao();
        SubsidiaryDaoFactory subsidiaryFactory = new SubsidiaryDaoFactory();

        if (subType == SubsidiaryType.CREDITOR) {
            CreditorDao credDao = subsidiaryFactory
                    .createRmt2OrmCreditorDao(xactDao);
            credDao.setDaoUser(this.getApiUser());
            CreditorXactHistoryDto xactHist = Rmt2SubsidiaryDtoFactory
                    .createCreditorTransactionInstance(null);
            xactHist.setCreditorId(subsidiaryId);
            xactHist.setXactId(xactId);
            xactHist.setActivityAmount(amount);
            try {
                rc = credDao.maintain(xactHist);
            } catch (Exception e) {
                this.msg = "Unable to create subidiary transaction activity for creditor, "
                        + subsidiaryId;
                logger.error(this.msg);
                throw new XactApiException(this.msg, e);
            } finally {
                credDao.close();
                credDao = null;
            }
        }
        // Create transaction for customer
        if (subType == SubsidiaryType.CUSTOMER) {
            CustomerDao custDao = subsidiaryFactory
                    .createRmt2OrmCustomerDao(xactDao);
            custDao.setDaoUser(this.getApiUser());
            CustomerXactHistoryDto xactHist = Rmt2SubsidiaryDtoFactory
                    .createCustomerTransactionInstance(null);
            xactHist.setCustomerId(subsidiaryId);
            xactHist.setXactId(xactId);
            xactHist.setActivityAmount(amount);
            try {
                // Create customer transaction history entry
                rc = custDao.maintain(xactHist);
                // Update transaction with confirmation number
                switch (xactDto.getXactTypeId()) {
                    case XactConst.XACT_TYPE_CASHPAY:
                    case XactConst.XACT_TYPE_CASHSALES:
                        xactDto.setXactConfirmNo(this.createGenericConfirmNo());
                        xactDao.maintain(xactDto);
                        break;
                } // end switch
            } catch (Exception e) {
                this.msg = "Unable to create subidiary transaction activity for customer, "
                        + subsidiaryId;
                logger.error(this.msg);
                throw new XactApiException(this.msg, e);
            } finally {
                xactDao.close();
                custDao.close();
                xactDao = null;
                custDao = null;
            }
        }
        return rc;
    }

    /**
     * Generates a generic confirmation number using the long number
     * representation of a java.util.Date object containing the current
     * timestamp at the time of invocation
     * 
     * @return confirmation number
     */
    private String createGenericConfirmNo() {
        java.util.Date today = new java.util.Date();
        String confirmNo = String.valueOf(today.getTime());
        return confirmNo;
    }

    /**
     * Determine if the subsidiary id represents a creditor or a customer.
     * 
     * @param subsidiaryId
     *            the id of the subsidiary
     * @return {@link SubsidiaryType}
     * @throws SubsidiaryDaoException
     */
    public SubsidiaryType evaluateSubsidiaryType(int subsidiaryId)
            throws SubsidiaryDaoException {
        SubsidiaryDto result = null;
        try {
            result = this.getCreditor(subsidiaryId);
            if (result != null) {
                return SubsidiaryType.CREDITOR;
            }
        } catch (Exception e) {
            throw new SubsidiaryDaoException(e);
        }

        try {
            result = this.getCustomer(subsidiaryId);
            if (result != null) {
                return SubsidiaryType.CUSTOMER;
            }
        } catch (Exception e) {
            throw new SubsidiaryDaoException(e);
        }
        return null;
    }

    private CreditorDto getCreditor(int creditorId) throws SubsidiaryException {
        XactDao xactDao;
        try {
            xactDao = this.getXactDao();
        } catch (XactApiException e) {
            throw new SubsidiaryException(
                    "Unable to retrieve creditor profile due to bad XactDao", e);
        }
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(xactDao);
        CreditorDto dto = (CreditorDto) api.getDomainBySubsidiaryId(creditorId);
        return dto;
    }

    private CustomerDto getCustomer(int customerId) throws SubsidiaryException {
        XactDao xactDao;
        try {
            xactDao = this.getXactDao();
        } catch (XactApiException e) {
            throw new SubsidiaryException(
                    "Unable to retrieve customer profile due to bad XactDao", e);
        }
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(xactDao);
        CustomerDto dto = (CustomerDto) api.getDomainBySubsidiaryId(customerId);
        return dto;
    }

    /**
     * Determines if <i>xact</i> can modified or adjusted.
     * <p>
     * Typical transaction typess would be reversals, cancellations, and
     * returns.
     * 
     * @param xact
     *            The transaction that is to be managed
     * @return true indicating that the transaction is eligible to be changed,
     *         and false indicating change is not allowd.
     * @throws XactException
     *             when xact is invalid or null.
     */
    @Override
    public boolean isTransModifiable(XactDto xact) throws XactApiException {
        if (xact == null) {
            throw new XactApiException(
                    "Transaction modifyable check failed to do transaction object is null");
        }
        return xact.getXactSubtypeId() == 0;
    }

    /**
     * This method flags the transaction, <i>xact</i>, as finalized by setting
     * the transaction sub type property to XactConst.XACT_TYPE_FINAL.
     * <p>
     * Once the transaction is finalized, the transaction cannot be changed
     * anymore. Transactions that involve finalization are: sales order
     * cancellation, cash disbursement reversal, credit charge reversal, and
     * customer payment reversal.
     * 
     * @param xact
     *            Transaction object that is to finalized.
     * @throws XactApiException
     *             If transactio id or transaction type id are invalid, or when
     *             a database error occurs.
     */
    @Override
    public void finalizeXact(XactDto xact) throws XactApiException {
        try {
            this.validate(xact);
        } catch (Exception e) {
            this.msg = "Common transaction update failed validation checks";
            logger.error(this.msg, e);
            throw new XactApiException(this.msg, e);
        }
        if (xact.getXactId() <= 0) {
            throw new XactApiException(
                    "The finalization of transaction failed due to invalid transaction id");
        }
        if (xact.getXactTypeId() <= 0) {
            throw new XactApiException(
                    "The finalization of transaction failed due to invalid transaction type id");
        }
        xact.setXactSubtypeId(XactConst.XACT_TYPE_FINAL);

        XactDao dao = this.getXactDao();
        dao.setDaoUser(this.getApiUser());

        // Apply transaction changes
        try {
            // dao.beginTrans();
            dao.maintain(xact);
            // dao.commitTrans();
            return;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Error occurred finalizing transaction: "
                    + xact.getXactId();
            logger.error(this.msg, e);
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

}
