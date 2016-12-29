package org.dao.lookup;

import java.util.ArrayList;
import java.util.List;

import org.dao.AddressBookDaoImpl;
import org.dao.mapping.orm.rmt2.GeneralCodes;
import org.dao.mapping.orm.rmt2.GeneralCodesGroup;
import org.dao.mapping.orm.rmt2.VwCodes;
import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;

import com.SystemException;
import com.api.persistence.DatabaseException;
import com.util.RMT2Date;
import com.util.UserTimestamp;

/**
 * An RMT2 ORM implementation of the {@link LookupDao} interface which accesses
 * the contacts relational database to create, modify, and remove Lookup data.
 * 
 * @author Roy Terrell
 * 
 */
public class Rmt2OrmLookupDaoImpl extends AddressBookDaoImpl implements
        LookupDao {

    /**
     * Default constructor.
     */
    protected Rmt2OrmLookupDaoImpl() {
        super();
    }

    protected Rmt2OrmLookupDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Queries the general_codes table for a single record using its primary key
     * as selection criteira.
     * 
     * @param codeId
     *            The primary key lookup type object.
     * @return An instance of {@link LookupCodeDto}
     * @throws GeneralCodeException
     */
    @Override
    public LookupCodeDto fetchCode(int codeId) throws LookupDaoException {
        GeneralCodes c = new GeneralCodes();
        c.addCriteria(GeneralCodes.PROP_CODEID, codeId);
        GeneralCodes results = this.getRmt2Code(codeId);
        results = (GeneralCodes) this.client.retrieveObject(c);
        if (results == null) {
            return null;
        }
        return Rmt2AddressBookDtoFactory.getCodeInstance(results);

    }

    private GeneralCodes getRmt2Code(int codeId) throws LookupDaoException {
        GeneralCodes c = new GeneralCodes();
        c.addCriteria(GeneralCodes.PROP_CODEID, codeId);
        GeneralCodes results = null;
        try {
            results = (GeneralCodes) this.client.retrieveObject(c);
            return results;
        } catch (DatabaseException e) {
            throw new LookupDaoException(e);
        }
    }

    /**
     * Queries the general_codes table for one or more rows that matches the
     * properties set in <i>criteria</i>.
     * <p>
     * This method uses the contents of <i>criteria</i> to build the selection
     * criteria for the query.
     * 
     * @param criteria
     *            an instance of {@link LookupCodeDto} containing properties
     *            used to build the query's selection criteria. The following
     *            table columns mapped to the properties set in this paramater:
     *            <i>code_id</i>, <i>code_group_id</i>, <i>shortdesc</i>, and
     *            <i>longdescr</i>.
     * @return a List of {@link LookupCodeDto}
     * @throws LookupDaoException
     */
    @Override
    public List<LookupCodeDto> fetchCode(LookupCodeDto criteria)
            throws LookupDaoException {
        GeneralCodes c = new GeneralCodes();
        if (criteria != null) {
            if (criteria.getCodeId() > 0) {
                c.addCriteria(GeneralCodes.PROP_CODEID, criteria.getCodeId());
            }
            if (criteria.getGrpId() > 0) {
                c.addCriteria(GeneralCodes.PROP_CODEGRPID, criteria.getGrpId());
            }
            if (criteria.getCodeShortName() != null) {
                c.addLikeClause(GeneralCodes.PROP_SHORTDESC,
                        criteria.getCodeShortName());
            }
            if (criteria.getCodeLongName() != null) {
                c.addLikeClause(GeneralCodes.PROP_LONGDESC,
                        criteria.getCodeLongName());
            }
        }

        List<GeneralCodes> results = null;
        try {
            results = this.client.retrieveList(c);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new LookupDaoException(e);
        }

        List<LookupCodeDto> list = new ArrayList<LookupCodeDto>();
        for (GeneralCodes item : results) {
            LookupCodeDto dto = Rmt2AddressBookDtoFactory.getCodeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Queries the general_codes_group table for a single row using its primary
     * key.
     * 
     * @param grpId
     *            The primary key of the group to search.
     * @return an instance of {@link LookupGroupDto}
     * @throws LookupDaoException
     */
    @Override
    public LookupGroupDto fetchGroup(int grpId) throws LookupDaoException {
        GeneralCodesGroup c = new GeneralCodesGroup();
        c.addCriteria(GeneralCodes.PROP_CODEGRPID, grpId);
        GeneralCodesGroup results = this.getRmt2Group(grpId);
        results = (GeneralCodesGroup) this.client.retrieveObject(c);
        if (results == null) {
            return null;
        }
        return Rmt2AddressBookDtoFactory.getCodeInstance(results);
    }

    private GeneralCodesGroup getRmt2Group(int grpId) throws LookupDaoException {
        GeneralCodesGroup c = new GeneralCodesGroup();
        c.addCriteria(GeneralCodes.PROP_CODEGRPID, grpId);
        GeneralCodesGroup results = null;
        try {
            results = (GeneralCodesGroup) this.client.retrieveObject(c);
            return results;
        } catch (DatabaseException e) {
            throw new LookupDaoException(e);
        }
    }

    /**
     * Queries the general_codes_group table for one or more rows that matches
     * the properties set in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link LookupGroupDto} containing properties
     *            used to build the query's selection criteria. The following
     *            table columns mapped to the properties set in this paramater:
     *            <i>code_grp_id</i> and <i>description</i>.
     * @return a List of {@link LookupGroupDto}
     * @throws LookupDaoException
     */
    @Override
    public List<LookupGroupDto> fetchGroup(LookupGroupDto criteria)
            throws LookupDaoException {
        GeneralCodesGroup c = new GeneralCodesGroup();
        if (criteria != null) {
            if (criteria.getGrpId() > 0) {
                c.addCriteria(GeneralCodesGroup.PROP_CODEGRPID,
                        criteria.getGrpId());
            }
            if (criteria.getGrpDescr() != null) {
                c.addLikeClause(GeneralCodesGroup.PROP_DESCRIPTION,
                        criteria.getGrpDescr());
            }
        }

        List<GeneralCodesGroup> results = null;
        try {
            results = this.client.retrieveList(c);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new LookupDaoException(e);
        }

        List<LookupGroupDto> list = new ArrayList<LookupGroupDto>();
        for (GeneralCodesGroup item : results) {
            LookupGroupDto dto = Rmt2AddressBookDtoFactory
                    .getCodeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Queries the database view, vw_codes, for one or more rows matching the
     * properties set in <i>critera</i>.
     * 
     * @param criteria
     *            an instance of {@link LookupExtDto} containing properties used
     *            to build the query's selection criteria. The following table
     *            columns mapped to the properties set in this paramater:
     *            <i>group_id</i>, <i>group_description</i>, <i>code_id</i>,
     *            <i>code_ shortdesc</i>, and <i>code_longdesc</i>.
     * @return a List of {@link LookupExtDto}
     * @throws LookupDaoException
     */
    @Override
    public List<LookupExtDto> fetchCodeExt(LookupExtDto criteria)
            throws LookupDaoException {
        VwCodes c = new VwCodes();
        if (criteria != null) {
            if (criteria.getCodeId() > 0) {
                c.addCriteria(VwCodes.PROP_CODEID, criteria.getCodeId());
            }
            if (criteria.getCodeShortName() != null) {
                c.addLikeClause(VwCodes.PROP_CODESHORTDESC,
                        criteria.getCodeShortName());
            }
            if (criteria.getCodeLongName() != null) {
                c.addLikeClause(VwCodes.PROP_CODELONGDESC,
                        criteria.getCodeLongName());
            }
            if (criteria.getGrpId() > 0) {
                c.addCriteria(VwCodes.PROP_GROUPID, criteria.getGrpId());
            }
            if (criteria.getGrpDescr() != null) {
                c.addLikeClause(VwCodes.PROP_GROUPDESC, criteria.getGrpDescr());
            }
        }

        List<VwCodes> results = null;
        try {
            results = this.client.retrieveList(c);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new LookupDaoException(e);
        }

        List<LookupExtDto> list = new ArrayList<LookupExtDto>();
        for (VwCodes item : results) {
            LookupExtDto dto = Rmt2AddressBookDtoFactory.getCodeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a new or modifies an existing record in the general_codes_group
     * table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param group
     *            an instance of {@link LookupGroupDto} containting data that
     *            can be mapped to a {@link GeneralCodesGroup} object.
     * @return an int value representing either the unique identifier of the
     *         resource inserted, or the total number of rows effected by the
     *         update of the resource.
     * @throws LookupDaoException
     */
    @Override
    public int maintainGroup(LookupGroupDto group) throws LookupDaoException {
        this.validateGroup(group);

        GeneralCodesGroup g = new GeneralCodesGroup();
        g.setCodeGrpId(group.getGrpId());
        g.setDescription(group.getGrpDescr());
        g.setDateCreated(group.getDateCreated());
        g.setDateUpdated(group.getDateUpdated());
        g.setUserId(group.getUpdateUserId());

        int rc = 0;
        if (g.getCodeGrpId() == 0) {
            rc = this.insertGroup(g);
            group.setGrpId(rc);
            g.setCodeGrpId(rc);
        }
        else {
            rc = this.updateGroup(g);
        }
        return rc;
    }

    /**
     * Inserts a General Code Group object into the database.
     * 
     * @param obj
     *            The target {@link GeneralCodesGroup} object to add to the
     *            database.
     * @return The primary key of the table row added.
     * @throws LookupUpdateDaoException
     */
    private int insertGroup(GeneralCodesGroup obj)
            throws LookupUpdateDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateCreated(ut.getDateCreated());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            int rc = this.client.insertRow(obj, true);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record insert targeting general_codes_group table";
            throw new LookupUpdateDaoException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before inserting record in general_codes_group table";
            throw new LookupUpdateDaoException(this.msg, e);
        }
    }

    /**
     * Updates general lookup group and persist the changes to the database.
     * 
     * @param obj
     *            The {@link GeneralCodesGroup} object to update.
     * @return The total number of rows effected by the transaction.
     * @throws LookupUpdateDaoException
     */
    private int updateGroup(GeneralCodesGroup obj)
            throws LookupUpdateDaoException {
        try {
            GeneralCodesGroup origRec = this.getRmt2Group(obj.getCodeGrpId());
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.addCriteria(GeneralCodesGroup.PROP_CODEGRPID,
                    obj.getCodeGrpId());
            obj.setDateCreated(origRec.getDateCreated());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            int rows = this.client.updateRow(obj);
            return rows;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record update targeting general_codes_group table";
            throw new LookupUpdateDaoException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before updating record in general_codes_group table";
            throw new LookupUpdateDaoException(this.msg, e);
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
    protected void validateGroup(LookupGroupDto group)
            throws InvalidLookupDataDaoException {
        if (group == null) {
            throw new InvalidLookupDataDaoException(
                    "General codes group object cannot be null");
        }
        if (group.getGrpDescr() == null || group.getGrpDescr().length() <= 0) {
            throw new InvalidLookupDataDaoException(
                    "Group description is required");
        }
    }

    /**
     * Deletes a record from the general_codes_group table using the primary
     * key.
     * 
     * @param groupId
     *            the primary key of the record to delete
     * @return total number of rows effected.
     * @throws LookupDaoException
     *             <i>codeId</i> is less than or equal to zero.
     */
    @Override
    public int deleteGroup(int groupId) throws LookupDaoException {
        if (groupId <= 0) {
            this.msg = "Group id is required and must greater than zero";
            throw new LookupDaoException(this.msg);
        }
        try {
            GeneralCodesGroup g = new GeneralCodesGroup();
            int rows = 0;
            g.addCriteria(GeneralCodesGroup.PROP_CODEGRPID, groupId);
            rows = this.client.deleteRow(g);
            return rows;
        } catch (DatabaseException e) {
            throw new LookupDaoException(e);
        }
    }

    /**
     * Creates a new or modifies an existing record in the general_codes table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param lookup
     *            an instance of {@link LookupCodeDto} containting data that can
     *            be mapped to a {@link GeneralCodes} object.
     * @return an int value representing either the unique identifier of the
     *         resource inserted, or the total number of rows effected by the
     *         update of the resource.
     * @throws LookupDaoException
     */
    @Override
    public int maintainCode(LookupCodeDto lookup) throws LookupDaoException {
        // Validate DTO object
        this.validateCode(lookup);

        // Convert dto to GeneralCodes object
        GeneralCodes c = new GeneralCodes();
        c.setCodeId(lookup.getCodeId());
        c.setCodeGrpId(lookup.getGrpId());
        c.setShortdesc(lookup.getCodeShortName());
        c.setLongdesc(lookup.getCodeLongName());
        c.setDateCreated(lookup.getDateCreated());
        c.setDateUpdated(lookup.getDateUpdated());
        c.setUserId(lookup.getUpdateUserId());

        int rc = 0;
        if (c.getCodeId() == 0) {
            rc = this.insertCode(c);
            lookup.setCodeId(rc);
            c.setCodeId(rc);
        }
        else {
            rc = this.updateCode(c);
        }
        return rc;
    }

    /**
     * Inserts a General Code object into the database.
     * 
     * @param obj
     *            The target {@link GeneralCodes} object to add to the database.
     * @return The primary key of the table row added.
     * @throws LookupUpdateDaoException
     */
    private int insertCode(GeneralCodes obj) throws LookupUpdateDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateCreated(ut.getDateCreated());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            int rc = this.client.insertRow(obj, true);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record insert targeting general_code table";
            throw new LookupUpdateDaoException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before inserting record in general_code table";
            throw new LookupUpdateDaoException(this.msg, e);
        }
    }

    /**
     * Updates general lookup and persist the changes to the database.
     * 
     * @param obj
     *            The {@link GeneralCodes} object to update.
     * @return The total number of rows effected by the transaction.
     * @throws LookupUpdateDaoException
     */
    private int updateCode(GeneralCodes obj) throws LookupUpdateDaoException {
        try {
            GeneralCodes origRec = this.getRmt2Code(obj.getCodeId());
            obj.addCriteria(GeneralCodes.PROP_CODEID, obj.getCodeId());
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateCreated(origRec.getDateCreated());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            int rows = this.client.updateRow(obj);
            return rows;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record update targeting general_code table";
            throw new LookupUpdateDaoException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before updating record in general_code table";
            throw new LookupUpdateDaoException(this.msg, e);
        }
    }

    /**
     * Validates an incoming general lookup data.
     * 
     * @param lookup
     *            an instance of {@link LookupCodeDto}
     * @throws InvalidLookupDataDaoException
     *             <ol>
     *             <li>The general lookup object is null</li>
     *             <li>Code group id is not greater than zero</li>
     *             <li>The short description and long description are absent. At
     *             least one must exists.</li>
     *             </ol>
     */
    protected void validateCode(LookupCodeDto lookup)
            throws InvalidLookupDataDaoException {
        if (lookup == null) {
            throw new InvalidLookupDataDaoException(
                    "General lookup object is invalid");
        }
        if (lookup.getGrpId() <= 0) {
            throw new InvalidLookupDataDaoException(
                    "lookup group id is required and must be a value greater than zero");
        }
        if ((lookup.getCodeShortName() == null || lookup.getCodeShortName()
                .length() <= 0)
                && (lookup.getCodeLongName() == null || lookup
                        .getCodeLongName().length() <= 0)) {
            throw new InvalidLookupDataDaoException(
                    "General lookup object must have either a short or long descripton");
        }
    }

    /**
     * 
     * Deletes a record from the general_codes table using the primary key.
     * 
     * @param codeId
     *            the primary key of the record to delete
     * @return total number of rows effected.
     * @throws LookupDaoException
     *             <i>codeId</i> is less than or equal to zero.
     */
    @Override
    public int deleteCode(int codeId) throws LookupDaoException {
        if (codeId <= 0) {
            this.msg = "Code id is required and must greater than zero";
            throw new LookupDaoException(this.msg);
        }
        GeneralCodes obj = new GeneralCodes();
        try {
            obj.addCriteria(GeneralCodes.PROP_CODEID, codeId);
            int rows = this.client.deleteRow(obj);
            return rows;
        } catch (Exception e) {
            throw new LookupDaoException(e);
        }
    }

}
