package org.modules.lookup;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.lookup.LookupDao;
import org.dao.lookup.LookupDaoException;
import org.dao.lookup.LookupDaoFactory;
import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;

import com.api.foundation.AbstractTransactionApiImpl;

/**
 * An implementation of {@link LookupDataApi} for managing Lookup Code
 * transactions.
 * 
 * @author rterrell
 * 
 */
class LookupDataApiImpl extends AbstractTransactionApiImpl implements LookupDataApi {
    private static final Logger logger = Logger.getLogger(LookupDataApiImpl.class);

    private LookupDaoFactory factory;
    private LookupDao dao;
    private String appName;

    /**
     * Creates a LookupDataApiImpl object in which the configuration is
     * identified by the name of a given application.
     * 
     * @param appName
     */
    protected LookupDataApiImpl(String appName) {
        super();
        this.factory = new LookupDaoFactory();
        dao = this.factory.createRmt2OrmDao(appName);
        this.setSharedDao(dao);
        dao.setDaoUser(this.apiUser);
        this.appName = appName;
        logger.info("Code API is initialized...");
    }

    /**
     * Retrieves a general lookup by its unique identifier using the RMT ORM
     * implementaion of Code DAO.
     * 
     * @param codeId
     *            The unique identifier of the lookup.
     * @return An instance of {@link LookupCodeDto}
     * @throws LookupDataApiException
     *             DAO errors.
     */
    @Override
    public LookupCodeDto getCode(int codeId) throws LookupDataApiException {
        try {
            LookupCodeDto dto = dao.fetchCode(codeId);
            return dto;
        } catch (LookupDaoException e) {
            this.msg = "Unable to fetch Code by lookup id, " + codeId;
            throw new LookupDataApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Retrieves a list of lookup objects based on selection criteria included
     * in <i>criteria</i>.
     * <p>
     * This method implementation uses the RMT ORM Code DAO implementaion to
     * access data.
     * 
     * @param criteria
     *            an instance of {@link LookupCodeDto} containing properties
     *            used to build the query's selection criteria. The following
     *            properties recognized are <i>lookup id</i>, <i>group id</i>,
     *            <i>short name</i>, and <i>long description</i>.
     * @return a List of {@link LookupCodeDto}
     * @throws LookupDataApiException
     *             DAO errors.
     */
    @Override
    public List<LookupCodeDto> getCode(LookupCodeDto criteria) throws LookupDataApiException {
        try {
            List<LookupCodeDto> list = dao.fetchCode(criteria);
            return list;
        } catch (LookupDaoException e) {
            this.msg = "Unable to fetch List of Codes using selection criteria contained in DTO";
            throw new LookupDataApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Retrieves a lookup group by its unique identifier using the RMT ORM
     * implementaion of Code DAO.
     * 
     * @param grpId
     *            The unique identifier of the lookup.
     * @return an instance of {@link LookupGroupDto}
     * @throws LookupDataApiException
     *             DAO errors.
     */
    @Override
    public LookupGroupDto getGroup(int grpId) throws LookupDataApiException {
        try {
            LookupGroupDto dto = dao.fetchGroup(grpId);
            return dto;
        } catch (LookupDaoException e) {
            this.msg = "Unable to fetch Code Group by group id, " + grpId;
            throw new LookupDataApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Retrieves a list of lookup group objects based on selection criteria
     * included in <i>criteria</i>.
     * <p>
     * This method implementation uses the RMT ORM Code DAO implementaion to
     * access data.
     * 
     * @param criteria
     *            an instance of {@link LookupGroupDto} containing properties
     *            used to build the query's selection criteria. The following
     *            properties are recognized when building the selection
     *            criteria: <i>group id</i> and <i>group description</i>.
     * @return a List of {@link LookupGroupDto}
     * @throws LookupDataApiException
     */
    @Override
    public List<LookupGroupDto> getGroup(LookupGroupDto criteria) throws LookupDataApiException {
        try {
            List<LookupGroupDto> list = dao.fetchGroup(criteria);
            return list;
        } catch (LookupDaoException e) {
            this.msg = "Unable to fetch List of Code Groups using selection criteria contained in DTO";
            throw new LookupDataApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Retrieves a list of lookup extension objects containg both lookup group
     * and lookup detail data based on selection criteria included in
     * <i>criteria</i>.
     * <p>
     * This method implementation uses the RMT ORM Code DAO implementaion to
     * access data.
     * 
     * @param criteria
     *            an instance of {@link LookupExtDto} containing properties used
     *            to build the query's selection criteria. The following
     *            properties recognized are <i>lookup id</i>, <i>group id</i>,
     *            <i>group description</i>, <i>lookup short description</i>, and
     *            <i>lookup long description</i>.
     * @return a List of {@link LookupExtDto}
     * @throws LookupDataApiException
     *             DAO errors.
     */
    @Override
    public List<LookupExtDto> getCodeExt(LookupExtDto criteria) throws LookupDataApiException {
        try {
            List<LookupExtDto> list = dao.fetchCodeExt(criteria);
            return list;
        } catch (LookupDaoException e) {
            this.msg = "Unable to fetch List of Code Extention objects using selection criteria contained in DTO";
            throw new LookupDataApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Creates a new or modifies an existing lookup group object in the system
     * using the RMT ORM implementaion of Code DAO.
     * 
     * @param group
     *            an instance of {@link LookupGroupDto}
     * @return The lookup id for new records or the total number of records
     *         effected by the update.
     * @throws LookupDataApiException
     *             <i>group</i> produces validation errors or DAO errors.
     */
    @Override
    public int updateGroup(LookupGroupDto group) throws LookupDataApiException {
        this.validateGroup(group);
        try {
            int rc = dao.maintainGroup(group);
            return rc;
        } catch (LookupDaoException e) {
            this.msg = "Unable to update Code Group object";
            throw new LookupDataApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Removes a lookup group from the system using the RMT ORM implementaion of
     * Code DAO.
     * 
     * @param groupId
     *            the internal unique id of the lookup group.
     * @return the total number of records effected.
     * @throws LookupDataApiException
     *             DAO errors.
     */
    @Override
    public int deleteGroup(int groupId) throws LookupDataApiException {
        if (groupId <= 0) {
            this.msg = "Group id is required and must greater than zero";
            throw new LookupDataApiException(this.msg);
        }
        try {
            int rc = dao.deleteGroup(groupId);
            return rc;
        } catch (LookupDaoException e) {
            this.msg = "Unable to delete Code Group: " + groupId;
            throw new LookupDataApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Creates a new or modifies an existing lookup object in the system using
     * the RMT ORM implementaion of Code DAO.
     * 
     * @param code
     *            an instance of {@link LookupCodeDto}
     * @return The lookup id for new records or the total number of records
     *         effected by the update.
     * @throws LookupDataApiException <i>code</i> produces validation errors or DAO errors.
     */
    @Override
    public int updateCode(LookupCodeDto code) throws LookupDataApiException {
        this.validateCode(code);
        try {
            int rc = dao.maintainCode(code);
            return rc;
        } catch (LookupDaoException e) {
            this.msg = "Unable to update Code bject";
            throw new LookupDataApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Removes a lookup object from the system using the RMT ORM implementaion
     * of Code DAO.
     * 
     * @param codeId
     *            the internal unique id of the lookup.
     * @return the total number of records effected.
     * @throws LookupDataApiException
     *             DAO errors.
     */
    @Override
    public int deleteCode(int codeId) throws LookupDataApiException {
        if (codeId <= 0) {
            this.msg = "Code id is required and must greater than zero";
            throw new LookupDataApiException(this.msg);
        }
        try {
            int rc = dao.deleteCode(codeId);
            return rc;
        } catch (LookupDaoException e) {
            this.msg = "Unable to delete Code: " + codeId;
            throw new LookupDataApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Validates an incoming general lookup group data.
     * 
     * @param group
     *            an instance of {@link LookupCodeDto}
     * @throws InvalidLookupDataDaoException
     *             <ol>
     *             <li>The general lookup group object is null</li>
     *             <li>The description is null</li>
     *             </ol>
     */
    protected void validateGroup(LookupGroupDto group) throws LookupDataApiException {
        if (group == null) {
            throw new LookupDataApiException("General codes group object cannot be null");
        }
        if (group.getGrpDescr() == null || group.getGrpDescr().length() <= 0) {
            throw new LookupDataApiException("Group description is required");
        }
    }
    
    /**
     * Validates an incoming general lookup data.
     * 
     * @param code
     *            an instance of {@link LookupCodeDto}
     * @throws InvalidLookupDataDaoException
     *             <ol>
     *             <li>The general lookup object is null</li>
     *             <li>Code group id is not greater than zero</li>
     *             <li>The short description and long description are absent. At
     *             least one must exists.</li>
     *             </ol>
     */
    protected void validateCode(LookupCodeDto code) throws LookupDataApiException {
        if (code == null) {
            throw new LookupDataApiException("General lookup object is invalid");
        }
        if (code.getGrpId() <= 0) {
            throw new LookupDataApiException(
                    "lookup group id is required and must be a value greater than zero");
        }
        if ((code.getCodeShortName() == null || code.getCodeShortName().length() <= 0)
                && (code.getCodeLongName() == null || code.getCodeLongName().length() <= 0)) {
            throw new LookupDataApiException(
                    "General lookup object must have either a short or long descripton");
        }
    }
}
