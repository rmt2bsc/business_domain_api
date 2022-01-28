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
import org.dao.transaction.XactDaoException;
import org.dto.CommonXactDto;
import org.dto.CreditorDto;
import org.dto.CreditorXactHistoryDto;
import org.dto.CustomerDto;
import org.dto.CustomerXactHistoryDto;
import org.dto.XactCategoryDto;
import org.dto.XactCodeDto;
import org.dto.XactCodeGroupDto;
import org.dto.XactCustomCriteriaDto;
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
import com.api.util.RMT2Money;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * The base implementation of XactApi interface containing common transaction
 * functionality such as transaction creation, subsidiary transaction history
 * creation, transaction reversal, and transaction finalization.
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
    
    // IS-71:  Added common accounting transaction DAO 
    protected XactDao xactDao;

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
	 * @throws XactApiException the shared DAO instance is null, the shared DAO is
	 *                          valid but is not associated with a valid connection.
	 * 
	 * @deprecated IS-71: Do not use this method due to code that deals with shared DAO
	 *             object to get XactDao instance has been removed.
	 */
    protected XactDao getXactDao() throws XactApiException {
    	// IS-71:  Simply return the member variable that contains the reference to common transaction instance.
    	return this.xactDao;
    }

    /**
     * Interprets the incoming criteria, which can be in any format, and creates
     * meaningful selection criteria that is usable by the target DAO
     * implementation.
     * 
     * @param criteria
     *            An instance of {@link XactCustomCriteriaDto}
     * @return null
     */
    protected String parseCriteria(XactCustomCriteriaDto criteria) {
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXact(org.dto.CommonXactDto)
     */
    @Override
    public List<CommonXactDto> getXact(CommonXactDto criteria) throws XactApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Common transaction criteria object is required", e);
        }

        List<CommonXactDto> results = null;
        try {
        	// IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchXact(criteria);
        } catch (Exception e) {
            this.msg = "DAO error retreiving list of common transaction objects";
            throw new XactApiException(this.msg, e);
        }
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.XactApi#getXact(org.dto.XactDto)
     */
    @Override
    public List<XactDto> getXact(XactDto criteria) throws XactApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Transaction criteria object is required", e);
        }
        List<XactDto> results = null;
        try {
        	// IS-71:  Use new common accounting transaction member variable to fetch Xact object
        	results = this.xactDao.fetchXact(criteria);
        } catch (Exception e) {
            this.msg = "DAO error retreiving list of transaction objects";
            throw new XactApiException(this.msg, e);
        }
        return results;
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

        List<XactDto> results = null;
        try {
            XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
            criteria.setXactId(xactId);
            results = this.getXact(criteria);
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
    public List<XactCategoryDto> getCategory(XactCategoryDto criteria) throws XactApiException {
        List<XactCategoryDto> results = null;
        try {
        	// IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchCategory(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction categories";
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

        List<XactCategoryDto> results = null;
        try {
            XactCategoryDto criteria = Rmt2XactDtoFactory.createXactCategoryInstance(null);
            criteria.setXactCatgId(catgId);
            // IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchCategory(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction category by category id: " + catgId;
            throw new XactApiException(this.msg, e);
        }

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
    public List<XactCodeGroupDto> getGroup(XactCodeGroupDto criteria) throws XactApiException {
    	// IS-71: Use the new xactDao member variable to access DAO methods;
        List<XactCodeGroupDto> results = null;
        try {
            results = this.xactDao.fetchGroup(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction groups";
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

        // IS-71: Use the new xactDao member variable to access DAO methods
        List<XactCodeGroupDto> results = null;
        try {
            XactCodeGroupDto criteria = Rmt2XactDtoFactory.createXactCodeGroupInstance(null);
            criteria.setEntityId(groupId);
            results = this.xactDao.fetchGroup(criteria);
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
    public List<XactCodeDto> getCodes(XactCodeDto criteria) throws XactApiException {
    	// IS-71: Use the new xactDao member variable to access DAO methods
        List<XactCodeDto> results = null;
        try {
            results = this.xactDao.fetchCode(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction codes";
            throw new XactApiException(this.msg, e);
        }
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
        
        List<XactCodeDto> results = null;
        try {
            XactCodeDto criteria = Rmt2XactDtoFactory.createXactCodeInstance(null);
            criteria.setEntityId(codeId);
            // IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchCode(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction code by code id: " + codeId;
            throw new XactApiException(this.msg, e);
        }

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

        List<XactCodeDto> results = null;
        try {
            XactCodeDto criteria = Rmt2XactDtoFactory.createXactCodeInstance(null);
            criteria.setGrpId(groupId);
            // IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchCode(criteria);
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
    public List<XactTypeDto> getXactTypes(XactTypeDto criteria) throws XactApiException {
        List<XactTypeDto> results = null;
        try {
        	// IS-71:  Use DAO member variable to access database.
            results = this.xactDao.fetchType(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve  transaction type objects";
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

        List<XactTypeDto> results = null;
        try {
            XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
            criteria.setXactTypeId(xactTypeId);
            // IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchType(criteria);
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

        List<XactTypeDto> results = null;
        try {
            XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
            criteria.setXactCatgId(catgId);
            // IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchType(criteria);
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

        List<XactTypeItemActivityDto> results = null;
        try {
            XactTypeItemActivityDto criteria = Rmt2XactDtoFactory
                    .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
            criteria.setXactId(xactId);
            // IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchXactTypeItemActivity(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction item type activity objects by xact id: " + xactId;
            throw new XactApiException(this.msg, e);
        }
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
        
        List<XactTypeItemActivityDto> results = null;
        try {
            XactTypeItemActivityDto criteria = Rmt2XactDtoFactory
                    .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
            criteria.setXactId(xactId);
            // IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchXactTypeItemActivityExt(criteria);
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
        
        List<XactTypeItemDto> results = null;
        try {
            XactTypeItemDto criteria = Rmt2XactDtoFactory.createXactTypeItemInstance(null);
            criteria.setXactTypeId(xactTypeId);
            // IS-71: Use the new xactDao member variable to access DAO methods
            results = this.xactDao.fetchXactTypeItem(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to retrieve transaction type item objects by xact type id: " + xactTypeId;
            throw new XactApiException(this.msg, e);
        }
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

    /**
     * Updates the base transaction.
     * <p>
     * This applies only for existing transactions, hence the functionality does
     * not create new transactions.
     * 
     * @param xact
     *            The transaction object to be managed.
     * @return The total number of transactions effected.
     * @throws XactApiException
     */
    @Override
    public int update(XactDto xact) throws XactApiException {
        this.validate(xact);

        // Fetch original transaction
        XactDto obj = this.getXactById(xact.getXactId());
        if (obj == null) {
            return 0;
        }

        // Apply updates to the original record
        obj.setXactAmount(xact.getXactAmount());
        obj.setXactBankTransInd(xact.getXactBankTransInd());
        obj.setXactTypeId(xact.getXactTypeId());
        obj.setXactSubtypeId(xact.getXactSubtypeId());
        obj.setXactDate(xact.getXactDate());
        obj.setXactTenderId(xact.getXactTenderId());
        obj.setXactNegInstrNo(xact.getXactNegInstrNo());
        obj.setXactConfirmNo(xact.getXactConfirmNo());
        obj.setXactEntityRefNo(xact.getXactEntityRefNo());
        obj.setXactPostedDate(xact.getXactPostedDate());
        obj.setXactReason(xact.getXactReason());
        obj.setDocumentId(xact.getDocumentId());

        int rc = 0;
        // Apply transaction changes
        try {
        	// IS-71: Use the new xactDao member variable to access DAO methods
            rc = this.xactDao.maintain(xact);
            return rc;
        } catch (Exception e) {
            this.msg = "Base transaction update failed";
            logger.error(this.msg, e);
            throw new XactApiException(this.msg, e);
        }
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
            throw new XactApiException(e);
        }

        int rc = 0;
        // Apply transaction changes
        try {
            this.preCreateXact(xact);
            // IS-71: Use the new xactDao member variable to access DAO methods
            rc = this.xactDao.maintain(xact, xactItems);
            this.postCreateXact(xact);
            return rc;
        } catch (Exception e) {
            this.msg = "Base transaction update failed";
            logger.error(this.msg, e);
            throw new XactApiException(this.msg, e);
        }
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
     * method does not recognize the transaction details when the transaction 
     * (sales orders and/or purchases)is linked to a subsidiary account 
     * (Creditor or Customer).
     * <p>
     * Override this method to add specific validations pertaining to the
     * business requirement of the descendant.
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
            throw new InvalidDataException(e);
        }

        // Validate transaction items.
        try {
            this.validate(xactItems);
        } catch (TransactionItemsUnavailableException e) {
            // At this level, it is okay to save a transaction without
            // transaction line items. Some Subsidiary accounts (customers and
            // creditors) have dedicated entities (sales orders and purchases)
            // to maintain their own transaction line items and are not required
            // to have any transaction detail items. If a transaction requires
            // detail items, then make provisions to validate that use case at
            // the descendant level.
            logger.warn("There are no transaction items associated with the base transaction");
            return;
        } catch (Exception e) {
            throw new InvalidDataException(e);
        }
        // At this point, we have items. Make sure the reversal of the
        // transaction base and transaction items balance.
        this.verifyTransactionAmountsBalance(xact, xactItems);
    }

    /**
     * Validates the base of the transaction that already exist for updating.
     * <p>
     * The following validations must be satisfied:
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
     * finalization.
     * <p>
     * Applicable sub transaction type values are:
     * <ul>
     * <li>{@link XactConst.XACT_SUBTYPE_REVERSE}</li>
     * <li>{@link XactConst.XACT_SUBTYPE_CANCEL}</li>
     * <li>{@link XactConst.XACT_SUBTYPE_NOT_ASSIGNED}</li>
     * </ul>
     * 
     * @param xact
     * @throws XactApiException
     */
    private void validateFinalization(XactDto xact) throws XactApiException {
    	XactDto orig = this.getXactById(xact.getXactId());
    	if (orig == null) {
    		throw new InvalidFinalizationAttemptException(
                    "Unable to finalize target transaction due to transction, " + xact.getXactId() + ", does not exists");
    	}
        switch (orig.getXactSubtypeId()) {
            case XactConst.XACT_SUBTYPE_REVERSE:
            case XactConst.XACT_SUBTYPE_CANCEL:
            case XactConst.XACT_SUBTYPE_NOT_ASSIGNED:
                // This is valid
                break;

            default:
                throw new InvalidFinalizationAttemptException(
                        "Unable to finalize target transaction.  The Sub type must be unassigned, reversed or cancelled");
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
     * The process of reversing a transaction is to multiply the transaction
     * amount by -1 and providing a calculated reason. As a result of this
     * operation, the original transaction amount is permanently changed by
     * off setting the previous transaction.
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
        // Finalize Transaction
        this.finalizeXact(xact);

        int rc = 0;
        this.preReverse(xact, xactItems);
        this.reverse(xact);
        this.reverse(xactItems);
   
        // It's not uncommon for some transactions to submit a null transaction object here...
        if (xact != null) {
            xact.setXactId(0);    
        }
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
     * Reverses all items of an existing transaction without applying changes to
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
    public int createSubsidiaryActivity(Integer subsidiaryId, SubsidiaryType subsidiaryType, Integer xactId, Double amount) throws XactApiException {
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
            Verifier.verifyNotNull(subsidiaryType);
        } catch (VerifyException e) {
            this.msg = "Subsidiary Type is required.  Must be SubsidiaryType.CREDITOR or SubsidiaryType.CUSTOMER";
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

        // validate transaction id
        int rc = 0;
        XactDto xactDto = this.getXactById(xactId);
        try {
            Verifier.verifyNotNull(xactDto);
        } catch (VerifyException e) {
            this.msg = "Unable to create subidiary transaction activity for subsidiary, " + subsidiaryId
                    + ", due to transaction id is not valid and/or does not exist: " + xactId;
            logger.error(this.msg);
            throw new NotFoundException(this.msg, e);
        }

        // Create transaction for creditor
        // IS-71:  Use transaction DAO member variable to reference common Xact DAO
        switch (subsidiaryType) {
        case CREDITOR:
            rc = this.createTransactionHistoryForCreditor(subsidiaryId, xactId, amount);
            break;
        case CUSTOMER:
            rc = this.createTransactionHistoryForCustomer(xactDto, subsidiaryId, xactId, amount);
            break;
    }
        return rc;
    }


    
    /**
     * Create transaction history for customers.
     * <p>
     * <b><u>Change log</u></b><p>
     * IS-71:  Changed method signature by removing DAO parameter now that the xact DAO is made available at the class scope.
     * <p>
     * @param xactDto
     * @param customerId
     * @param xactId
     * @param amount
     * @return
     * @throws XactApiException
     */
    protected int createTransactionHistoryForCustomer(XactDto xactDto, Integer customerId, Integer xactId, Double amount) 
    		throws XactApiException {
        int rc = 0;
        SubsidiaryDaoFactory subsidiaryFactory = new SubsidiaryDaoFactory();
        CustomerDao custDao = subsidiaryFactory.createRmt2OrmCustomerDao(this.xactDao);
        custDao.setDaoUser(this.getApiUser());
        CustomerXactHistoryDto xactHist = Rmt2SubsidiaryDtoFactory.createCustomerTransactionInstance(null);
        xactHist.setCustomerId(customerId);
        xactHist.setXactId(xactId);
        xactHist.setActivityAmount(amount);
        try {
            // Create customer transaction history entry
            rc = custDao.maintain(xactHist);
            // Update transaction with confirmation number
            switch (xactDto.getXactTypeId()) {
                case XactConst.XACT_TYPE_CASHRECEIPT:
                case XactConst.XACT_TYPE_CASHSALES:
                    xactDto.setXactConfirmNo(this.createGenericConfirmNo());
                    xactDao.maintain(xactDto);
                    break;
            } // end switch
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to create subidiary transaction activity for customer, " + customerId;
            logger.error(this.msg);
            throw new XactApiException(this.msg, e);
        }
    }

    /**
     * Create transaction history for creditors.
     * <p>
     * <b><u>Change log</u></b><p>
     * IS-71:  Changed method signature by removing DAO parameter now that the xact DAO is made available at the class scope.
     * <p>
     * @param creditorId
     * @param xactId
     * @param amount
     * @return
     * @throws XactApiException
     */
    protected int createTransactionHistoryForCreditor(Integer creditorId, Integer xactId, Double amount)
            throws XactApiException {
        SubsidiaryDaoFactory subsidiaryFactory = new SubsidiaryDaoFactory();
        CreditorDao credDao = subsidiaryFactory.createRmt2OrmCreditorDao(this.xactDao);
        credDao.setDaoUser(this.getApiUser());
        CreditorXactHistoryDto xactHist = Rmt2SubsidiaryDtoFactory.createCreditorTransactionInstance(null);
        xactHist.setCreditorId(creditorId);
        xactHist.setXactId(xactId);
        xactHist.setActivityAmount(amount);
        try {
            // Create creditor transaction history entry
            return credDao.maintain(xactHist);
        } catch (Exception e) {
            this.msg = "Unable to create subidiary transaction activity for creditor, " + creditorId;
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
     * Verifies the existence of the creditor
     * 
     * @param creditorId
     * @return {@link CreditorDto} if found. Otherwise null is retunred.
     * @throws SubsidiaryException
     */
    protected CreditorDto verifyCreditor(int creditorId) throws SubsidiaryException {
    	// IS-71: Use the new xactDao member variable to access DAO methods
        CreditorApi api = SubsidiaryApiFactory.createCreditorApi(this.xactDao);
        CreditorDto dto = api.get(creditorId);
        return dto;
    }

    /**
     * Verifies the existence of the customer
     * 
     * @param customerId
     * @return {@link CustomerDto} if found. Otherwise null is retunred.
     * @throws SubsidiaryException
     */
    protected CustomerDto verifyCustomer(int customerId) throws SubsidiaryException {
    	// IS-71: Use the new xactDao member variable to access DAO methods
        CustomerApi api = SubsidiaryApiFactory.createCustomerApi(this.xactDao);
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
     *         and false indicating change is not allowed.
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
     * We assuming that the transaction has undergone basic validations. If not,
     * then this.validate(XactDto) will need to be called.
     * <p>
     * Once the transaction is finalized, the transaction cannot be changed
     * anymore. Transactions that involve finalization are: sales order
     * cancellation, cash disbursement reversal, credit charge reversal, and
     * customer payment reversal.
     * 
     * @param xact
     *            Transaction object that is to finalized.
     * @throws XactApiException
     *             If transaction id or transaction type id are invalid, or when
     *             a database error occurs.
     */
    @Override
    public void finalizeXact(XactDto xact) throws XactApiException {
        // Check if okay to finalize.
        try {
            this.validateFinalization(xact);
        } catch (Exception e) {
            this.msg = "Finalization of transaction failed due to transaction sub type incompatibilities";
            logger.error(this.msg, e);
            throw new XactApiException(e);
        }

        xact.setXactSubtypeId(XactConst.XACT_SUBTYPE_FINAL);

        // Apply transaction changes
        try {
        	// IS-71:  Use new common accounting transaction DAO object to finalize transaction
            this.xactDao.maintain(xact);
            return;
        } catch (Exception e) {
            this.msg = "Error occurred finalizing transaction: " + xact.getXactId();
            logger.error(this.msg, e);
            throw new XactApiException(e);
        }
    }
    
    /**
     * Deletes one or more transaction objects.
     * 
     * @param xactIdList
     *            A List of Integer.
     * @return total number of objects effected.
     * @throws XactApiException
     */
    @Override
	public int deleteXact(List<Integer> xactIdList) throws XactApiException {
		try {
			Verifier.verifyNotEmpty(xactIdList);
		} catch (VerifyException e) {
			this.msg = "The transaction id list criteria object is cannot be null or empty for the delete transaction operation";
			throw new InvalidDataException(this.msg, e);
		}
		int rc = 0;
		try {
			rc = this.xactDao.delete(xactIdList);
		} catch (XactDaoException e) {
			throw new XactApiException(e);
		}
		return rc;
	}
}
