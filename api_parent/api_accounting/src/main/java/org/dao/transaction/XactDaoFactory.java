package org.dao.transaction;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.CreditorActivity;
import org.dao.mapping.orm.rmt2.CustomerActivity;
import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactCategory;
import org.dao.mapping.orm.rmt2.XactCodeGroup;
import org.dao.mapping.orm.rmt2.XactCodes;
import org.dao.mapping.orm.rmt2.XactType;
import org.dao.mapping.orm.rmt2.XactTypeItem;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dto.SubsidiaryActivityDto;
import org.dto.XactCategoryDto;
import org.dto.XactCodeDto;
import org.dto.XactCodeGroupDto;
import org.dto.XactDto;
import org.dto.XactTypeDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.XactTypeItemDto;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * Factory class for creating Transaction DAO related objects.
 * 
 * @author Roy Terrell
 * 
 */
public class XactDaoFactory extends RMT2Base {

    /**
     * Default constructory
     */
    public XactDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>XactDao</i> using the RMT2 transaction ORM
     * implementation.
     * 
     * @return an instance of {@link XactDao}
     */
    public XactDao createRmt2OrmXactDao() {
        XactDao dao = new Rmt2XactDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>XactDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param appName
     *            application name
     * 
     * @return an instance of {@link XactDao}
     */
    public XactDao createRmt2OrmXactDao(String appName) {
        XactDao d = new Rmt2XactDaoImpl(appName);
        return d;
    }

    /**
     * Creates an instance of <i>XactDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link DaoClient}
     * 
     * @return an instance of {@link XactDao}
     */
    public XactDao createRmt2OrmXactDao(DaoClient dao) {
        XactDao d = new Rmt2XactDaoImpl(dao.getClient());
        return d;
    }

    /**
     * Creates and returns an <i>VwXactList</i> object containing selection
     * criteria obtained from an instance of <i>XactDto</i>.
     * 
     * @param criteria
     *            an instance of {@link XactDto} which the following properties
     *            are recognized:
     *            <ul>
     *            <li>id</li>
     *            <li>xactDate</li>
     *            <li>xactTypeId</li>
     *            <li>xactCatgId</li>
     *            <li>confirmNo</li>
     *            <li>tenderId</li>
     *            <li>customCriteria</li>
     *            </ul>
     * @return an instance of {@link VwXactList}
     */
    public static final VwXactList createCriteria(XactDto criteria) {
        VwXactList obj = new VwXactList();
        if (criteria != null) {
            if (criteria.getXactId() > 0) {
                obj.addCriteria(VwXactList.PROP_ID, criteria.getXactId());
            }
            if (criteria.getXactDate() != null) {
                obj.addCriteria(VwXactList.PROP_XACTDATE,
                        criteria.getXactDate());
            }
            if (criteria.getXactTypeId() > 0) {
                obj.addCriteria(VwXactList.PROP_XACTTYPEID,
                        criteria.getXactTypeId());
            }
            if (criteria.getXactCatgId() > 0) {
                obj.addCriteria(VwXactList.PROP_XACTCATGID,
                        criteria.getXactCatgId());
            }
            if (criteria.getXactConfirmNo() != null) {
                obj.addLikeClause(VwXactList.PROP_CONFIRMNO,
                        criteria.getXactConfirmNo());
            }
            if (criteria.getXactTenderId() > 0) {
                obj.addCriteria(VwXactList.PROP_TENDERID,
                        criteria.getXactTenderId());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwXactList</i> object containing selection
     * criteria obtained from an instance of <i>XactDto</i>.
     * 
     * @param criteria
     *            an instance of {@link XactDto} which the following properties
     *            are recognized:
     *            <ul>
     *            <li>xactCatgId</li>
     *            <li>description</li>
     *            <li>code</li>
     *            </ul>
     * @return an instance of {@link VwXactList}
     */
    public static final XactCategory createCriteria(XactCategoryDto criteria) {
        XactCategory obj = new XactCategory();
        if (criteria != null) {
            if (criteria.getXactCatgId() > 0) {
                obj.addCriteria(XactCategory.PROP_XACTCATGID,
                        criteria.getXactCatgId());
            }
            if (criteria.getXactCatgDescription() != null) {
                obj.addLikeClause(XactCategory.PROP_DESCRIPTION,
                        criteria.getXactCatgDescription());
            }
            if (criteria.getXactCatgCode() != null) {
                obj.addCriteria(XactCategory.PROP_CODE,
                        criteria.getXactCatgCode());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>XactCodeGroup</i> object containing selection
     * criteria obtained from an instance of <i>XactCodeGroupDto</i>.
     * 
     * @param criteria
     *            an instance of {@link XactCodeGroupDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>entityId</li>
     *            <li>entiityName</li>
     *            </ul>
     * @return an instance of {@link XactCodeGroup}
     */
    public static final XactCodeGroup createCriteria(XactCodeGroupDto criteria) {
        XactCodeGroup obj = new XactCodeGroup();
        if (criteria != null) {
            if (criteria.getEntityId() > 0) {
                obj.addCriteria(XactCodeGroup.PROP_XACTCODEGRPID,
                        criteria.getEntityId());
            }
            if (criteria.getEntityName() != null) {
                obj.addLikeClause(XactCodeGroup.PROP_DESCRIPTION,
                        criteria.getEntityName());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>XactCodes</i> object containing selection
     * criteria obtained from an instance of <i>XactCodeDto</i>.
     * 
     * @param criteria
     *            an instance of {@link XactCodeDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>entityId</li>
     *            <li>entiityName</li>
     *            <li>grpId</li>
     *            </ul>
     * @return an instance of {@link XactCodes}
     */
    public static final XactCodes createCriteria(XactCodeDto criteria) {
        XactCodes obj = new XactCodes();
        if (criteria != null) {
            if (criteria.getEntityId() > 0) {
                obj.addCriteria(XactCodeGroup.PROP_XACTCODEGRPID,
                        criteria.getEntityId());
            }
            if (criteria.getEntityName() != null) {
                obj.addLikeClause(XactCodeGroup.PROP_DESCRIPTION,
                        criteria.getEntityName());
            }
            if (criteria.getGrpId() > 0) {
                obj.addCriteria(XactCodeGroup.PROP_XACTCODEGRPID,
                        criteria.getGrpId());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>XactType</i> object containing selection
     * criteria obtained from an instance of <i>XactTypeDto</i>.
     * 
     * @param criteria
     *            an instance of {@link XactTypeDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>xactTypeId</li>
     *            <li>description</li>
     *            <li>xactCatgId</li>
     *            <li>code</li>
     *            </ul>
     * @return an instance of {@link XactType}
     */
    public static final XactType createCriteria(XactTypeDto criteria) {
        XactType obj = new XactType();
        if (criteria != null) {
            if (criteria.getXactTypeId() > 0) {
                obj.addCriteria(XactType.PROP_XACTTYPEID,
                        criteria.getXactTypeId());
            }
            if (criteria.getXactTypeDescription() != null) {
                obj.addLikeClause(XactType.PROP_DESCRIPTION,
                        criteria.getXactTypeDescription());
            }
            if (criteria.getXactCatgId() > 0) {
                obj.addCriteria(XactType.PROP_XACTCATGID,
                        criteria.getXactCatgId());
            }
            if (criteria.getXactTypeCode() != null) {
                obj.addLikeClause(XactType.PROP_CODE,
                        criteria.getXactTypeCode());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>XactTypeItem</i> object containing selection
     * criteria obtained from an instance of <i>XactTypeItemDto</i>.
     * 
     * @param criteria
     *            an instance of {@link XactTypeItemDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>xactItemId</li>
     *            <li>xactTypeId</li>
     *            <li>name</li>
     *            <li>customCriteria</li>
     *            </ul>
     * @return an instance of {@link XactTypeItem}
     */
    public static final XactTypeItem createCriteria(XactTypeItemDto criteria) {
        XactTypeItem obj = new XactTypeItem();
        if (criteria != null) {
            if (criteria.getXactItemId() > 0) {
                obj.addCriteria(XactTypeItem.PROP_XACTITEMID,
                        criteria.getXactItemId());
            }
            if (criteria.getXactTypeId() > 0) {
                obj.addCriteria(XactTypeItem.PROP_XACTTYPEID,
                        criteria.getXactTypeId());
            }
            if (criteria.getXactItemName() != null) {
                obj.addLikeClause(XactTypeItem.PROP_NAME,
                        criteria.getXactItemName());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>XactTypeItemActivity</i> object containing
     * selection criteria obtained from an instance of
     * <i>XactTypeItemActivityDto</i>.
     * 
     * @param criteria
     *            an instance of {@link XactTypeItemActivityDto} which the
     *            following properties are recognized:
     *            <ul>
     *            <li>xactTypeItemActvId</li>
     *            <li>xactId</li>
     *            <li>description</li>
     *            <li>xactItemId</li>
     *            <li>amount</li>
     *            <li>customCriteria</li>
     *            </ul>
     * @return an instance of {@link XactTypeItemActivity}
     */
    public static final XactTypeItemActivity createCriteria(
            XactTypeItemActivityDto criteria) {
        XactTypeItemActivity obj = new XactTypeItemActivity();
        if (criteria != null) {
            if (criteria.getXactTypeItemActvId() > 0) {
                obj.addCriteria(XactTypeItemActivity.PROP_XACTTYPEITEMACTVID,
                        criteria.getXactTypeItemActvId());
            }
            if (criteria.getXactId() > 0) {
                obj.addCriteria(XactTypeItemActivity.PROP_XACTID,
                        criteria.getXactId());
            }
            if (criteria.getXactTypeItemActvName() != null) {
                obj.addLikeClause(XactTypeItemActivity.PROP_DESCRIPTION,
                        criteria.getXactTypeItemActvName());
            }
            if (criteria.getXactItemId() > 0) {
                obj.addCriteria(XactTypeItemActivity.PROP_XACTITEMID,
                        criteria.getXactItemId());
            }
            if (Math.abs(criteria.getActivityAmount()) > 0) {
                obj.addCriteria(XactTypeItemActivity.PROP_AMOUNT,
                        criteria.getActivityAmount());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwXactTypeItemActivity</i> object containing
     * selection criteria obtained from an instance of
     * <i>XactTypeItemActivityDto</i>.
     * 
     * @param criteria
     *            an instance of {@link XactTypeItemActivityDto} which the
     *            following properties are recognized:
     *            <ul>
     *            <li>id</li>
     *            <li>xactId</li>
     *            <li>itemName</li>
     *            <li>xactTypeItemId</li>
     *            <li>itemAmount</li>
     *            <li>customCriteria</li>
     *            </ul>
     * @return an instance of {@link VwXactTypeItemActivity}
     */
    public static final VwXactTypeItemActivity createCriteriaExt(
            XactTypeItemActivityDto criteria) {
        VwXactTypeItemActivity obj = new VwXactTypeItemActivity();
        if (criteria != null) {
            if (criteria.getXactTypeItemActvId() > 0) {
                obj.addCriteria(VwXactTypeItemActivity.PROP_ID,
                        criteria.getXactTypeItemActvId());
            }
            if (criteria.getXactId() > 0) {
                obj.addCriteria(VwXactTypeItemActivity.PROP_XACTID,
                        criteria.getXactId());
            }
            if (criteria.getXactTypeItemActvName() != null) {
                obj.addLikeClause(VwXactTypeItemActivity.PROP_ITEMNAME,
                        criteria.getXactTypeItemActvName());
            }
            if (criteria.getXactItemId() > 0) {
                obj.addCriteria(VwXactTypeItemActivity.PROP_XACTTYPEITEMID,
                        criteria.getXactItemId());
            }
            if (Math.abs(criteria.getActivityAmount()) > 0) {
                obj.addCriteria(VwXactTypeItemActivity.PROP_ITEMAMOUNT,
                        criteria.getActivityAmount());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Create <i>Xact</i> object from an instance of <i>XactDto</i>
     * 
     * @param obj
     *            an instnace of {@link XactDto}
     * @return an instnace of {@link Xact}
     */
    public static final Xact createXact(XactDto obj) {
        if (obj == null) {
            return null;
        }
        Xact x = new Xact();
        x.setXactId(obj.getXactId());
        x.setXactTypeId(obj.getXactTypeId());
        x.setXactSubtypeId(obj.getXactSubtypeId());
        x.setXactDate(obj.getXactDate());
        x.setXactAmount(obj.getXactAmount());
        x.setTenderId(obj.getXactTenderId());
        x.setNegInstrNo(obj.getXactNegInstrNo());
        x.setBankTransInd(obj.getXactBankTransInd());
        x.setConfirmNo(obj.getXactConfirmNo());
        x.setEntityRefNo(obj.getXactEntityRefNo());
        x.setPostedDate(obj.getXactPostedDate());
        x.setReason(obj.getXactReason());
        x.setDateCreated(obj.getDateCreated());
        x.setDateUpdated(obj.getDateUpdated());
        x.setUserId(obj.getUpdateUserId());
        x.setDocumentId(obj.getDocumentId());
        x.setIpCreated(obj.getIpCreated());
        x.setIpUpdated(obj.getIpUpdated());
        return x;
    }

    /**
     * Create a List of <i>XactTypeItemActivity</i> objects from a List of
     * <i>XactTypeItemActivityDto</i> instances.
     * 
     * @param actvList
     *            A List of {@link XactTypeItemActivityDto}
     * @return A List of {@link XactTypeItemActivity}
     */
    public static final List<XactTypeItemActivity> createXactItemActivity(
            List<XactTypeItemActivityDto> actvList) {
        if (actvList == null) {
            return null;
        }
        List<XactTypeItemActivity> list = new ArrayList<XactTypeItemActivity>();
        for (XactTypeItemActivityDto dto : actvList) {
            list.add(XactDaoFactory.createXactItemActivity(dto));
        }
        return list;
    }

    /**
     * Create <i>XactTypeItemActivity</i> object from an instance of
     * <i>XactTypeItemActivityDto</i>
     * 
     * @param obj
     *            an instnace of {@link XactTypeItemActivityDto}
     * @return an instnace of {@link XactTypeItemActivity}
     */
    public static final XactTypeItemActivity createXactItemActivity(
            XactTypeItemActivityDto obj) {
        if (obj == null) {
            return null;
        }
        XactTypeItemActivity x = new XactTypeItemActivity();
        x.setXactTypeItemActvId(obj.getXactTypeItemActvId());
        x.setXactId(obj.getXactId());
        x.setXactItemId(obj.getXactItemId());
        x.setAmount(obj.getActivityAmount());
        x.setDescription(obj.getXactTypeItemActvName());
        x.setDateCreated(obj.getDateCreated());
        x.setDateUpdated(obj.getDateUpdated());
        x.setUserId(obj.getUpdateUserId());
        x.setIpCreated(obj.getIpCreated());
        x.setIpUpdated(obj.getIpUpdated());
        return x;
    }

    public static final CustomerActivity createCustomerActivity(
            SubsidiaryActivityDto subsidiary) {
        if (subsidiary == null) {
            return null;
        }
        CustomerActivity obj = new CustomerActivity();
        obj.setCustomerActvId(subsidiary.getActivityId());
        obj.setCustomerId(subsidiary.getSubsidiaryId());
        obj.setXactId(subsidiary.getXactId());
        obj.setAmount(subsidiary.getActivityAmount());
        obj.setDateCreated(subsidiary.getDateCreated());
        obj.setDateUpdated(subsidiary.getDateUpdated());
        obj.setUserId(subsidiary.getUpdateUserId());
        obj.setIpCreated(subsidiary.getIpCreated());
        obj.setIpUpdated(subsidiary.getIpUpdated());
        return obj;
    }

    public static final CreditorActivity createCreditorActivity(
            SubsidiaryActivityDto subsidiary) {
        CreditorActivity obj = new CreditorActivity();
        if (subsidiary == null) {
            return null;
        }

        return obj;
    }
}
