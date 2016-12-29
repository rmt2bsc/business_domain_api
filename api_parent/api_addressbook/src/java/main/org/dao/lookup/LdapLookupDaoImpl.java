package org.dao.lookup;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.ldap.LdapLookup;
import org.dao.postal.PostalDaoException;
import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;
import org.dto.adapter.ldap.LdapAddressBookDtoFactory;

import com.RMT2Constants;
import com.api.ldap.AbstractLdapDaoClient;
import com.api.ldap.operation.LdapSearchOperation;

/**
 * An JNDI implementation of the {@link LookupDao} interface which accesses the
 * lookup data from a LDAP server for querying purposes only.
 * <p>
 * The base DN targeted for this implementation is
 * <i>ou=General,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>
 * 
 * @author Roy Terrell
 * 
 */
class LdapLookupDaoImpl extends AbstractLdapDaoClient implements LookupDao {

    private static final Logger logger = Logger
            .getLogger(LdapLookupDaoImpl.class);

    private static final String MAPPER_CLASS = "org.dao.mapping.orm.ldap.LdapLookup";

    /**
     * Base DN for country LDAP node
     */
    protected static final String BASE_DN = "ou=General,ou=LookupCodes";

    /**
     * Construct a LdapLookupDaoImpl object that establishes a connection to the
     * LDAP server.
     */
    public LdapLookupDaoImpl() {
        super();
    }

    /**
     * Queries the LDAP server for a lookup detail record by its unique
     * identifier at base DN,
     * <i>ou=General,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * Since all values for the attribute, <i>uid</i>, are unique within the
     * base DN mentioned above, this method employs the search within the
     * subtree scope.
     * 
     * @param codeId
     *            The unique identifier used to target the lookup detail code
     *            record
     * @return An instance of {@link LookupCodeDto}
     * @throws LookupDaoException
     */
    @Override
    public LookupCodeDto fetchCode(int codeId) throws LookupDaoException {
        List<LdapLookup> list = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapLookupDaoImpl.BASE_DN);
            op.setScope(SearchControls.SUBTREE_SCOPE);
            op.getSearchFilterArgs().put("uid", String.valueOf(codeId));
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName(LdapLookupDaoImpl.MAPPER_CLASS);
            Object results[] = this.ldap.retrieve(op);
            if (results == null) {
                return null;
            }
            list = this.ldap.extractLdapResults(results);
        } catch (Exception e) {
            throw new LookupDaoException(e);
        }

        LdapLookup obj = null;
        if (list.size() > 1) {
            this.msg = "Too many lookup code objects were returned from LDAP server for a single lookup code query";
            throw new PostalDaoException();
        }
        else {
            obj = list.get(0);
        }
        LookupCodeDto dto = LdapAddressBookDtoFactory
                .getLookupCodeInstance(obj);
        return dto;
    }

    /**
     * Queries the LDAP server for one or more lookup detail records related to
     * a particular lookup group using a variety of selection criteria at base
     * DN, <i>ou=xxxxxx,ou=General,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>,
     * where <i>xxxxxx</i> is replaced with an actual lookup group name.
     * <p>
     * A valid non null instance of <i>LookupCodeDto</i> is required containing
     * at least the group id of the lookup record(s) sought after. Basically,
     * all records retrieved will be related to one group. Since all records are
     * to be found under a particular lookup group, this method employs the
     * search within the one level scope.
     * 
     * @param criteria
     *            an instance of {@link LookupCodeDto} containing properties
     *            used to build the query's selection criteria. The LDAP
     *            attributes, <i>cn</i>, <i>description</i>, are mapped to the
     *            properties set in this argument, <i>codeShortName</i>, and
     *            <i>codeLongName</i>, respectively.
     * @return a List of {@link LookupCodeDto}
     * @throws LookupDaoException
     *             <i>criteria</i> is null, group id property is not set in
     *             <i>criteria</i> or does not exist in the LDAP repository, or
     *             general LDAP access error.
     */
    @Override
    public List<LookupCodeDto> fetchCode(LookupCodeDto criteria)
            throws LookupDaoException {
        if (criteria == null) {
            throw new LookupDaoException(
                    "Selection criteria must exist as an instance of LookupCodeDto");
        }
        if (criteria.getGrpId() <= 0) {
            throw new LookupDaoException("Selection criteria requires Group Id");
        }

        LookupGroupDto grpDto = this.fetchGroup(criteria.getGrpId());
        if (grpDto == null) {
            throw new LookupDaoException(
                    "An invalid group was passed in the selection criteria object");
        }
        if (grpDto.getGrpDescr() == null) {
            throw new LookupDaoException(
                    "An invalid group description was found for lookup group, "
                            + grpDto.getGrpId());
        }

        // Build base DN for a particular group
        String dn = "ou=" + grpDto.getGrpDescr() + ","
                + LdapLookupDaoImpl.BASE_DN;

        // Build selection criteria
        LdapSearchOperation op = new LdapSearchOperation();
        if (criteria.getCodeShortName() != null) {
            op.getSearchFilterArgs().put("cn",
                    criteria.getCodeShortName() + "*");
        }
        if (criteria.getCodeLongName() != null) {
            op.getSearchFilterArgs().put("description",
                    criteria.getCodeLongName() + "*");
        }

        List<LdapLookup> lookupList;
        try {
            op.setDn(dn);
            op.setScope(SearchControls.ONELEVEL_SCOPE);
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName(LdapLookupDaoImpl.MAPPER_CLASS);
            logger.info("LDAP filter to be applied for lookup codes by group search operation: "
                    + op.getSearchFilter());
            Object results[] = this.ldap.retrieve(op);
            if (results == null) {
                return null;
            }
            lookupList = this.ldap.extractLdapResults(results);
        } catch (Exception e) {
            throw new LookupDaoException(e);
        }

        List<LookupCodeDto> list = new ArrayList<LookupCodeDto>();
        for (LdapLookup item : lookupList) {
            LookupCodeDto dto = LdapAddressBookDtoFactory
                    .getLookupCodeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Queries the LDAP server for a lookup group record by its unique
     * identifier at base DN,
     * <i>ou=General,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * Since all group lookup records exist just one level down from the base DN
     * mentioned above, this method employs the search within the one level
     * scope.
     * 
     * @param grpId
     *            The unique identifier used to target the lookup group code
     *            record
     * @return An instance of {@link LookupCodeDto}
     * @throws LookupDaoException
     */
    @Override
    public LookupGroupDto fetchGroup(int grpId) throws LookupDaoException {
        List<LdapLookup> list = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapLookupDaoImpl.BASE_DN);
            op.setScope(SearchControls.ONELEVEL_SCOPE);
            op.getSearchFilterArgs().put("uid", String.valueOf(grpId));
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName(LdapLookupDaoImpl.MAPPER_CLASS);
            Object results[] = this.ldap.retrieve(op);
            if (results == null) {
                return null;
            }
            list = this.ldap.extractLdapResults(results);
        } catch (Exception e) {
            throw new PostalDaoException(e);
        }

        LdapLookup obj = null;
        if (list.size() > 1) {
            this.msg = "Too many lookup code objects were returned from LDAP server for a single lookup code query";
            throw new PostalDaoException();
        }
        else {
            obj = list.get(0);
        }
        LookupGroupDto dto = LdapAddressBookDtoFactory
                .getLookupGroupInstance(obj);
        return dto;
    }

    /**
     * Queries the LDAP server for one or more lookup group records using a
     * variety of selection criteria at base DN,
     * <i>ou=General,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * If the desire exist to retrieve all group records, then set the input
     * parameter, <i>criteria</i>, to null. Otherwise, <i>criteria</i> should be
     * a valid instance of <i>LookupCodeDto</i> with the appropriate values set.
     * Since all group lookup records exist just one level down from the base DN
     * mentioned above, this method employs the search within the one level
     * scope.
     * 
     * @param criteria
     *            an instance of {@link LookupCodeDto} containing properties
     *            used to build the query's selection criteria. The LDAP
     *            attributes, <i>uid</i>, <i>ou</i>, are mapped to the
     *            properties set in this argument, <i>grpId</i>, and
     *            <i>grpDescr</i>, respectively.
     * @return a List of {@link LookupCodeDto}
     * @throws LookupDaoException
     *             general LDAP access error.
     */
    @Override
    public List<LookupGroupDto> fetchGroup(LookupGroupDto criteria)
            throws LookupDaoException {
        LdapSearchOperation op = new LdapSearchOperation();
        if (criteria != null) {
            if (criteria.getGrpId() > 0) {
                op.getSearchFilterArgs().put("uid",
                        String.valueOf(criteria.getGrpId()));
            }
            else {
                if (criteria.getGrpDescr() != null) {
                    op.getSearchFilterArgs().put("ou",
                            criteria.getGrpDescr() + "*");
                }
            }
        }

        List<LdapLookup> lookupList;
        try {
            op.setDn(LdapLookupDaoImpl.BASE_DN);
            op.setScope(SearchControls.ONELEVEL_SCOPE);
            op.setMappingBeanName(LdapLookupDaoImpl.MAPPER_CLASS);
            op.setUseSearchFilterExpression(true);
            logger.info("LDAP filter to be applied for lookup group search operation: "
                    + op.getSearchFilter());
            Object results[] = this.ldap.retrieve(op);
            if (results == null) {
                return null;
            }
            lookupList = this.ldap.extractLdapResults(results);
        } catch (Exception e) {
            throw new LookupDaoException(e);
        }

        List<LookupGroupDto> list = new ArrayList<LookupGroupDto>();
        for (LdapLookup item : lookupList) {
            LookupGroupDto dto = LdapAddressBookDtoFactory
                    .getLookupGroupInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Not supported.
     */
    @Override
    public List<LookupExtDto> fetchCodeExt(LookupExtDto criteria)
            throws LookupDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    @Override
    public int maintainGroup(LookupGroupDto group) throws LookupDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    @Override
    public int deleteGroup(int groupId) throws LookupDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    @Override
    public int maintainCode(LookupCodeDto lookup) throws LookupDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    @Override
    public int deleteCode(int codeId) throws LookupDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
}
