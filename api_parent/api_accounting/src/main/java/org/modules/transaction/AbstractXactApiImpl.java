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
import com.NotFoundException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;
import com.util.RMT2Money;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

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
public abstract class AbstractXactApiImpl extends AbstractTransactionApiImpl implements XactApi {

    private static final Logger logger = Logger.getLogger(AbstractXactApiImpl.class);

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
    public XactDto getXactById(Integer xactId) throws XactApiException {
        try {
            Verifier.verifyNotNull(xactId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction id is required", e);
        }
        try {
            Verifier.verifyPositive(xactId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction id must be greater than zero", e);
        }

        XactDao dao = this.getXactDao();
        List<XactDto> results = null;
        try {
            XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
            criteria.setXactId(xactId);
            results = dao.fetchXact(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve common transaction object by xact id: " + xactId;
            throw new XactApiException(this.msg, e);
        }

        if (results == null || results.size() <= 0) {
            return null;
        }
        if (results.size() > 1) {
            this.msg = "Common transaction query should of returned only 1 object based on xact id: " + xactId
                    + ".  Instead " + results.size() + " objects were returned";
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
            XactCategoryDto criteria = Rmt2XactDtoFactory.createXactCategoryInstance(null);
            results = dao.fetchCategory(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve all transaction categories";
            throw new XactApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getCategoryById(int)
     */
    @Override
    public XactCategoryDto getCategoryById(Integer catgId) throws XactApiException {
        try {
            Verifier.verifyNotNull(catgId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction category id is required", e);
        }
        try {
            Verifier.verifyPositive(catgId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction category id must be greater than zero", e);
        }

        XactDao dao = this.getXactDao();
        List<XactCategoryDto> results = null;
        try {
            XactCategoryDto criteria = Rmt2XactDtoFactory.createXactCategoryInstance(null);
            criteria.setXactCatgId(catgId);
            results = dao.fetchCategory(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction category by category id: " + catgId;
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
                    + catgId + ".  Instead " + results.size() + " objects were returned";
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
        XactCodeGroupDto criteria = Rmt2XactDtoFactory.createXactCodeGroupInstance(null);
        try {
            results = dao.fetchGroup(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve all transaction groups";
            throw new XactApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getGroup(int)
     */
    @Override
    public XactCodeGroupDto getGroup(Integer groupId) throws XactApiException {
        try {
            Verifier.verifyNotNull(groupId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction group id is required", e);
        }
        try {
            Verifier.verifyPositive(groupId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction group id must be greater than zero", e);
        }

        XactDao dao = this.getXactDao();
        List<XactCodeGroupDto> results = null;
        try {
            XactCodeGroupDto criteria = Rmt2XactDtoFactory.createXactCodeGroupInstance(null);
            criteria.setEntityId(groupId);
            results = dao.fetchGroup(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction group by group id: " + groupId;
            throw new XactApiException(this.msg, e);
        }

        if (results == null || results.size() <= 0) {
            return null;
        }
        if (results.size() > 1) {
            this.msg = "Common group transaction query should of returned only 1 object based on xact group id: "
                    + groupId + ".  Instead " + results.size() + " objects were returned";
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
    public XactCodeDto getCode(Integer codeId) throws XactApiException {
        try {
            Verifier.verifyNotNull(codeId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction code id is required", e);
        }
        try {
            Verifier.verifyPositive(codeId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction code id must be greater than zero", e);
        }
        XactDao dao = this.getXactDao();
        List<XactCodeDto> results = null;
        try {
            XactCodeDto criteria = Rmt2XactDtoFactory.createXactCodeInstance(null);
            criteria.setEntityId(codeId);
            results = dao.fetchCode(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction code by code id: " + codeId;
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
                    + codeId + ".  Instead " + results.size() + " objects were returned";
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
    public List<XactCodeDto> getCodeByGroupId(Integer groupId) throws XactApiException {
        try {
            Verifier.verifyNotNull(groupId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction group id is required", e);
        }
        try {
            Verifier.verifyPositive(groupId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction group id must be greater than zero", e);
        }

        XactDao dao = this.getXactDao();
        List<XactCodeDto> results = null;
        try {
            XactCodeDto criteria = Rmt2XactDtoFactory.createXactCodeInstance(null);
            criteria.setGrpId(groupId);
            results = dao.fetchCode(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve code transaction objects by group id: " + groupId;
            throw new XactApiException(this.msg, e);
        }
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactType(int)
     */
    @Override
    public XactTypeDto getXactType(Integer xactTypeId) throws XactApiException {
        try {
            Verifier.verifyNotNull(xactTypeId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction type id is required", e);
        }
        try {
            Verifier.verifyPositive(xactTypeId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction type id must be greater than zero", e);
        }
        XactDao dao = this.getXactDao();
        List<XactTypeDto> results = null;
        try {
            XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
            criteria.setXactTypeId(xactTypeId);
            results = dao.fetchType(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction type objects by xact type id: " + xactTypeId;
            throw new XactApiException(this.msg, e);
        }

        if (results == null || results.size() <= 0) {
            return null;
        }
        if (results.size() > 1) {
            this.msg = "Common transaction type query should of returned only 1 object based on xact type id: "
                    + xactTypeId + ".  Instead " + results.size() + " objects were returned";
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
    public List<XactTypeDto> getXactTypeByCatgId(Integer catgId) throws XactApiException {
        try {
            Verifier.verifyNotNull(catgId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction category id is required", e);
        }
        try {
            Verifier.verifyPositive(catgId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction category id must be greater than zero", e);
        }
        XactDao dao = this.getXactDao();
        List<XactTypeDto> results = null;
        try {
            XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
            criteria.setXactCatgId(catgId);
            results = dao.fetchType(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction type objects by xact category id: " + catgId;
            throw new XactApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactTypeItemActivity(int)
     */
    @Override
    public List<XactTypeItemActivityDto> getXactTypeItemActivity(Integer xactId) throws XactApiException {
        try {
            Verifier.verifyNotNull(xactId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction id is required", e);
        }
        try {
            Verifier.verifyPositive(xactId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction id must be greater than zero", e);
        }
        XactDao dao = this.getXactDao();
        List<XactTypeItemActivityDto> results = null;
        try {
            XactTypeItemActivityDto criteria = Rmt2XactDtoFactory
                    .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
            criteria.setXactId(xactId);
            results = dao.fetchXactTypeItemActivity(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction item type activity objects by xact id: " + xactId;
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
    public List<XactTypeItemActivityDto> getXactTypeItemActivityExt(Integer xactId) throws XactApiException {
        try {
            Verifier.verifyNotNull(xactId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction id is required", e);
        }
        try {
            Verifier.verifyPositive(xactId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction id must be greater than zero", e);
        }
        XactDao dao = this.getXactDao();
        List<XactTypeItemActivityDto> results = null;
        try {
            XactTypeItemActivityDto criteria = Rmt2XactDtoFactory
                    .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
            criteria.setXactId(xactId);
            results = dao.fetchXactTypeItemActivityExt(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction item type activity objects by xact id: " + xactId;
            throw new XactApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXactTypeItemsByXactTypeId(int)
     */
    @Override
    public List<XactTypeItemDto> getXactTypeItemsByXactTypeId(Integer xactTypeId) throws XactApiException {
        try {
            Verifier.verifyNotNull(xactTypeId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction type id is required", e);
        }
        try {
            Verifier.verifyPositive(xactTypeId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction type id must be greater than zero", e);
        }
        XactDao dao = this.getXactDao();
        List<XactTypeItemDto> results = null;
        try {
            XactTypeItemDto criteria = Rmt2XactDtoFactory.createXactTypeItemInstance(null);
            criteria.setXactTypeId(xactTypeId);
            results = dao.fetchXactTypeItem(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction type item objects by xact type id: " + xactTypeId;
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
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#setXactItems(java.util.List)
     */
    @Override
    public void setXactItems(List<XactTypeItemActivityDto> value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#update(org.dto.XactDto,
     * java.util.List)
     */
    @Override
    public int update(XactDto xact, List<XactTypeItemActivityDto> xactItems) throws XactApiException {

        try {
            this.validate(xact, xactItems);
        } catch (Exception e) {
            this.msg = "Common transaction update failed validation checks";
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
            this.msg = "Base transaction update failed";
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
        if (xact.getXactDate() == null) {
            java.util.Date today = new java.util.Date();
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
     * @throws InvalidDataException
     *             When <i>xact</i> does not meet basic validation requirements,
     *             one or more elements in <i>xactItems</i> do not meet basic
     *             validation requirements.
     * @throws TransactionAmountsUnbalancedException the sum of the transaction item
     *             amount of each element of <i>xactItems</i> does not equal the
     *             transaction amount found in <i>xact</i>.
     */
    protected void validate(XactDto xact, List<XactTypeItemActivityDto> xactItems) {
        // Validate base transaction
        try {
            this.validate(xact);    
        }
        catch (Exception e) {
            throw new InvalidDataException("base transaction failed validation", e);
        }

        // Validate transaction items.
        try {
            this.validate(xactItems);
        } catch (TransactionItemsUnavailableException e) {
            // At this level, it is okay to save a transaction without
            // transaction items. Some transactions (accounts with
            // subsidiaries), are not required to have any transaction detail
            // items. If a transaction requires detail items, then make
            // provisions to validate that use case at the descendent level.
            logger.warn("There are no transaction items associated with the base transaction", e);
            return;
        } catch (Exception e) {
            throw new InvalidDataException("List of base transaction detail items failed validation", e);
        }
        // At this point, we have items. Make sure the reversal of the
        // transaction base and transaction items balance.
        this.verifyTransactionAmountsBalance(xact, xactItems);
    }

    /**
     * Validates the base of the transaction that already exist for updating.
     * <p>
     * The following validations must be satified:
     * <ul>
     * <li>Base transaction object is valid</li>
     * <li>Base transaction type id is greater than zero</li>
     * </ul>
     * 
     * @throws InvalidDataException
     */
    private void validate(XactDto xact) {
        // Execute custom pre validations
        this.preValidate(xact);

        try {
            // Transaction id can be greater than or equal to zero
            Verifier.verifyNotNegative(xact.getXactId());
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction id cannot be negative", e);
        }
        try {
            Verifier.verifyPositive(xact.getXactTypeId());
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction type id cannot be negative", e);
        }

        // validate money expression
        RMT2Money.validateMoney(xact.getXactAmount());

        // Execute custom post validations
        this.postValidate(xact);
    }

    /**
     * Verifies that the said transaction is a proper candidate for
     * finailization.
     * <p>
     * Aplplicable sub transaction types are:
     * <ul>
     * <li>cancellations</li>
     * <li>reversals</li>
     * </ul>
     * 
     * @param xact
     * @throws XactApiException
     */
    private void validateFinalization(XactDto xact) throws XactApiException {
        if (xact.getXactSubtypeId() == XactConst.XACT_SUBTYPE_REVERSE
                || xact.getXactSubtypeId() == XactConst.XACT_SUBTYPE_CANCEL) {
            // This is valid
        }
        else {
            throw new InvalidFinalizationAttemptException("Transaction sub type must be reversed or cancelled");
        }
    }

    /**
     * Override this method to execute custom logic before base transaction
     * validations.
     * 
     * @param xact
     *            The transaction object to be validated
     * @throws InvalidDataException
     *             xact is null
     */
    protected void preValidate(XactDto xact) {
        try {
            Verifier.verifyNotNull(xact);
        } catch (VerifyException e) {
            this.msg = "Base transaction is required and cannot be null";
            throw new InvalidDataException(this.msg, e);
        }
        return;
    }

    /**
     * Override this method to execute custom logic after base transaction
     * validations.
     * 
     * @param xact
     *            The transaction object to be validated.
     * @throws InvalidDataException
     *             Validation error occurred.
     */
    protected void postValidate(XactDto xact)  {
        return;
    }

    private void validate(List<XactTypeItemActivityDto> items) {
        // Pre validate the list of items as a whole
        this.preValidate(items);

        // validate each transaction item
        for (XactTypeItemActivityDto item : items) {
            // Validate each item
            this.validate(item);
        }
        // Post validate the list of items as a whole
        this.postValidate(items);
    }

    /**
     * Verify that list of transaction items is not null.
     * <p>
     * It is okay not to have any items.
     * <p>
     * Override this method to execute custom logic before base transaction
     * Items list validations.
     * 
     * @param items
     * @throws TransactionItemsUnavailableException
     *             when transaction items is null or empty
     */
    protected void preValidate(List<XactTypeItemActivityDto> items) {
        // It is okay not to have any items.
        try {
            Verifier.verifyNotEmpty(items);
        } catch (VerifyException e) {
            throw new TransactionItemsUnavailableException("Transaction detail items not available", e);
        }
    }

    /**
     * Override this method to execute custom logic after base transaction Items
     * list validations.
     * 
     * @param items
     * @throws InvalidDataException
     */
    protected void postValidate(List<XactTypeItemActivityDto> items) {
        return;
    }

    /**
     * Validates a transaction detail item by ensuring that a transaction type
     * item id and item description is provided. Validates the base of the
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
     * @throws InvalidDataException
     *             When <i>xtia</i> is null, its id property is less than or
     *             equal to zero, or its description property is null.
     */
    private void validate(XactTypeItemActivityDto item) {
        // Execute custom pre validation logic.
        this.preValidateXactItem(item);

        try {
            Verifier.verifyPositive(item.getXactItemId());
        } catch (VerifyException e) {
            this.msg = "Transaction type item id property is required to have a value";
            logger.error(this.msg);
            throw new InvalidDataException(this.msg, e);
        }

        try {
            Verifier.verifyNotBlank(item.getXactTypeItemActvName());
        } catch (VerifyException e) {
            this.msg = "Transaction type item description property is required to have a value";
            logger.error(this.msg);
            throw new InvalidDataException(this.msg, e);
        }

        // Execute custom post validation logic.
        this.postValidateXactItem(item);

        return;
    }

    /**
     * Check if <i>item</i> is not null.
     * <p>
     * Override this method to execute custom logic before base transaction Item
     * Activity validations.
     * 
     * @param item
     * @throws InvalidDataException
     */
    protected void preValidateXactItem(XactTypeItemActivityDto item) {
        try {
            Verifier.verifyNotNull(item);
        } catch (VerifyException e) {
            this.msg = "Transaction type item activity object is invalid or null";
            throw new InvalidDataException(this.msg, e);
        }
    }

    /**
     * Override this method to execute custom logic after base transaction Item
     * Activity validations.
     * 
     * @param item
     * @throws InvalidDataException
     */
    protected void postValidateXactItem(XactTypeItemActivityDto item) {
        return;
    }

    /**
     * Validates that the base transaction amount is equal to the sum of
     * transaction item amounts.
     * <p>
     * For cash disbursements and creditor disbursements,
     * 
     * @param xact
     *            instance of {@link XactDto}
     * @param items
     *            a List of {@link XactTypeItemActivityDto}
     * @throws TransactionAmountsUnbalancedException
     */
    private void verifyTransactionAmountsBalance(XactDto xact, List<XactTypeItemActivityDto> items)
            throws TransactionAmountsUnbalancedException {
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
            this.msg = "The base transaction amount [" + Math.abs(xact.getXactAmount())
                    + "] must equal the sum of all transaction item amounts [" + totalItemAmount + "]";
            logger.error(this.msg);
            throw new TransactionAmountsUnbalancedException(this.msg);
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
    public int reverse(XactDto xact, List<XactTypeItemActivityDto> xactItems) throws XactApiException {
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
        // Do not try to access xact if null. Defer proper validation and
        // notification of xact to the update api method.
        if (xact == null) {
            return;
        }
        xact.setXactSubtypeId(XactConst.XACT_SUBTYPE_REVERSE);
        xact.setXactAmount(xact.getXactAmount() * XactConst.REVERSE_MULTIPLIER);
        String reason1 = "Reversed Transaction " + xact.getXactId();
        String reason2 = (xact.getXactReason() == null ? "" : " " + xact.getXactReason());
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
     *         reversed or null when <i>xactItems</i> is null or empty.
     */
    private List<XactTypeItemActivityDto> reverse(List<XactTypeItemActivityDto> xactItems) {
        try {
            Verifier.verifyNotEmpty(xactItems);
        } catch (VerifyException e) {
            logger.warn("There are no transaction item to process.  This is okay!");
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
        double amount = xtia.getActivityAmount() * XactConst.REVERSE_MULTIPLIER;
        xtia.setActivityAmount(amount);

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
    protected void preReverse(XactDto xact, List<XactTypeItemActivityDto> xactItems) {
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
    protected void postReverse(XactDto xact, List<XactTypeItemActivityDto> xactItems) {
        return;
    }

    /**
     * Creates transaction history activity for either a customer or a creditor
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
    public int createSubsidiaryActivity(Integer subsidiaryId, Integer xactId, Double amount) throws XactApiException {
        try {
            Verifier.verifyNotNull(subsidiaryId);
        } catch (VerifyException e) {
            this.msg = "Subsidiary id is required";
            logger.error(this.msg);
            throw new InvalidDataException(this.msg, e);
        }
        try {
            Verifier.verifyPositive(subsidiaryId);
        } catch (VerifyException e) {
            this.msg = "Subsidiary id must be a value greater than zero";
            logger.error(this.msg);
            throw new InvalidDataException(this.msg, e);
        }
        try {
            Verifier.verifyNotNull(xactId);
        } catch (VerifyException e) {
            this.msg = "Transaction id is required";
            logger.error(this.msg);
            throw new InvalidDataException(this.msg, e);
        }
        try {
            Verifier.verifyPositive(xactId);
        } catch (VerifyException e) {
            this.msg = "Transaction id must be a value greater than zero";
            logger.error(this.msg);
            throw new InvalidDataException(this.msg, e);
        }
        try {
            Verifier.verifyNotNull(amount);
        } catch (VerifyException e) {
            this.msg = "Transaction amount is required";
            logger.error(this.msg);
            throw new InvalidDataException(this.msg, e);
        }

        int rc = 0;
        // Determine the type of subsidiary we are dealing with
        SubsidiaryType subType = this.evaluateSubsidiaryType(subsidiaryId);

        // Verify that we were able to identify the subsidiary type
        try {
            Verifier.verifyNotNull(subType);
        } catch (VerifyException e) {
            this.msg = "Subsidiary id passed is invalid.  Must be SubsidiaryType.CREDITOR or SubsidiaryType.CUSTOMER";
            logger.error(this.msg);
            throw new NotFoundException(this.msg, e);
        }
        // validate transaction id
        XactDto xactDto = this.getXactById(xactId);
        try {
            Verifier.verifyNotNull(xactDto);
        } catch (VerifyException e) {
            this.msg = "Unable to create subidiary transaction activity for subsidiary, " + subsidiaryId
                    + ", due to transaction id is not valid and/or does not exist: " + xactId;
            logger.error(this.msg);
            throw new NotFoundException(this.msg, e);
        }

        XactDao xactDao = this.getXactDao();
        // Create transaction for creditor
        if (subType == SubsidiaryType.CREDITOR) {
            rc = this.createTransactionHistoryForCreditor(xactDao, subsidiaryId, xactId, amount);
        }
        // Create transaction for customer
        if (subType == SubsidiaryType.CUSTOMER) {
            rc = this.createTransactionHistoryForCustomer(xactDao, xactDto, subsidiaryId, xactId, amount);
        }
        return rc;
    }

    private int createTransactionHistoryForCustomer(XactDao xactDao, XactDto xactDto, Integer subsidiaryId,
            Integer xactId, Double amount) throws XactApiException {
        int rc = 0;
        SubsidiaryDaoFactory subsidiaryFactory = new SubsidiaryDaoFactory();
        CustomerDao custDao = subsidiaryFactory.createRmt2OrmCustomerDao(xactDao);
        custDao.setDaoUser(this.getApiUser());
        CustomerXactHistoryDto xactHist = Rmt2SubsidiaryDtoFactory.createCustomerTransactionInstance(null);
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
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to create subidiary transaction activity for customer, " + subsidiaryId;
            logger.error(this.msg);
            throw new XactApiException(this.msg, e);
        }
    }

    private int createTransactionHistoryForCreditor(XactDao xactDao, Integer subsidiaryId, Integer xactId, Double amount)
            throws XactApiException {
        SubsidiaryDaoFactory subsidiaryFactory = new SubsidiaryDaoFactory();
        CreditorDao credDao = subsidiaryFactory.createRmt2OrmCreditorDao(xactDao);
        credDao.setDaoUser(this.getApiUser());
        CreditorXactHistoryDto xactHist = Rmt2SubsidiaryDtoFactory.createCreditorTransactionInstance(null);
        xactHist.setCreditorId(subsidiaryId);
        xactHist.setXactId(xactId);
        xactHist.setActivityAmount(amount);
        try {
            // Create creditor transaction history entry
            return credDao.maintain(xactHist);
        } catch (Exception e) {
            this.msg = "Unable to create subidiary transaction activity for creditor, " + subsidiaryId;
            logger.error(this.msg);
            throw new XactApiException(this.msg, e);
        }
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
     * @return {@link SubsidiaryType} when the creditor or customer is found.
     *         Otherwise, null is returned.
     * @throws XactApiException
     */
    public SubsidiaryType evaluateSubsidiaryType(Integer subsidiaryId) throws XactApiException {
        SubsidiaryDto result = null;
        try {
            result = this.getCreditor(subsidiaryId);
            if (result != null) {
                return SubsidiaryType.CREDITOR;
            }
        } catch (Exception e) {
            throw new XactApiException("Error evaluating creditor [creditor id=" + subsidiaryId + "]", e);
        }

        try {
            result = this.getCustomer(subsidiaryId);
            if (result != null) {
                return SubsidiaryType.CUSTOMER;
            }
        } catch (Exception e) {
            throw new XactApiException("Error evaluating customer [customer id=" + subsidiaryId + "]", e);
        }
        return null;
    }

    private CreditorDto getCreditor(int creditorId) throws SubsidiaryException {
        XactDao xactDao;
        try {
            xactDao = this.getXactDao();
        } catch (XactApiException e) {
            throw new SubsidiaryException("Unable to retrieve creditor profile due to bad XactDao", e);
        }
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(xactDao);
        CreditorDto dto = api.get(creditorId);
        return dto;
    }

    private CustomerDto getCustomer(int customerId) throws SubsidiaryException {
        XactDao xactDao;
        try {
            xactDao = this.getXactDao();
        } catch (XactApiException e) {
            throw new SubsidiaryException("Unable to retrieve customer profile due to bad XactDao", e);
        }
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CustomerApi api = f.createCustomerApi(xactDao);
        CustomerDto dto = api.get(customerId);
        return dto;
    }

    /**
     * Determines if <i>xact</i> can be modified or adjusted.
     * <p>
     * Typical transaction sub types which cannot be modified are
     * <i>reversals</i>, <i>cancellations</i>, <i>returns</i>, and
     * <i>finalized</i>.
     * 
     * @param xact
     *            The transaction that is to be managed
     * @return true indicating that the transaction is eligible to be changed,
     *         and false indicating change is not allowd.
     * @throws InvalidDataException
     *             when xact is invalid or null.
     */
    @Override
    public boolean isModifiable(XactDto xact) {
        this.preValidate(xact);
        return xact.getXactSubtypeId() == XactConst.XACT_SUBTYPE_NOT_ASSIGNED;
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
        // Do basic transaction validations
        try {
            this.validate(xact);
        } catch (Exception e) {
            this.msg = "Finalization of transaction failed due to common validation error";
            logger.error(this.msg, e);
            throw new XactApiException(this.msg, e);
        }

        // Check if okay to finailze.
        try {
            this.validateFinalization(xact);
        } catch (Exception e) {
            this.msg = "Finalization of transaction failed due to transaction sub type incompatibilities";
            logger.error(this.msg, e);
            throw new XactApiException(this.msg, e);
        }

        xact.setXactSubtypeId(XactConst.XACT_SUBTYPE_FINAL);

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
            this.msg = "Error occurred finalizing transaction: " + xact.getXactId();
            logger.error(this.msg, e);
            throw new XactApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

}
