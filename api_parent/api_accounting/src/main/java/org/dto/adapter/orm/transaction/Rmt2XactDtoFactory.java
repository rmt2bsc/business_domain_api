package org.dto.adapter.orm.transaction;

import org.dao.mapping.orm.rmt2.VwGenericXactList;
import org.dao.mapping.orm.rmt2.VwMultiDisburseTypeXact;
import org.dao.mapping.orm.rmt2.VwXactItemList;
import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactCategory;
import org.dao.mapping.orm.rmt2.XactCodeGroup;
import org.dao.mapping.orm.rmt2.XactCodes;
import org.dao.mapping.orm.rmt2.XactType;
import org.dao.mapping.orm.rmt2.XactTypeItem;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dto.CommonXactDto;
import org.dto.MultiDisburseTypeXactDto;
import org.dto.XactCategoryDto;
import org.dto.XactCodeDto;
import org.dto.XactCodeGroupDto;
import org.dto.XactDto;
import org.dto.XactTypeDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.XactTypeItemDto;

import com.RMT2Base;

/**
 * A factory containing several adapters which function to create common
 * accounting/financial related transactions.
 * 
 * @author Roy Terrell.
 * 
 */
public class Rmt2XactDtoFactory extends RMT2Base {

    /**
     * Create an instance of <i>XactCategoryDto</i>.
     * <p>
     * A brand new instance of XactCategoryDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * XactCategoryDto.
     * 
     * @param ormBean
     *            an instance of {@link XactCategory}
     * @return an instance of {@link XactCategoryDto}.
     */
    public static final XactCategoryDto createXactCategoryInstance(
            XactCategory ormBean) {
        return new XactCategoryRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactTypeDto</i>.
     * <p>
     * A brand new instance of XactTypeDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of XactTypeDto.
     * 
     * @param ormBean
     *            an instance of {@link XactType}
     * @return an instance of {@link XactTypeDto}.
     */
    public static final XactTypeDto createXactTypeInstance(XactType ormBean) {
        return new XactTypeRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactCodeGroupDto</i>.
     * <p>
     * A brand new instance of XactCodeGroupDto is created when <i>ormBean</i>
     * is null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * XactCodeGroupDto.
     * 
     * @param ormBean
     *            an instance of {@link XactCodeGroup}
     * @return an instance of {@link XactCodeGroupDto}.
     */
    public static final XactCodeGroupDto createXactCodeGroupInstance(
            XactCodeGroup ormBean) {
        return new XactCodesRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactCodeDto</i>.
     * <p>
     * A brand new instance of XactCodeDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of XactCodeDto.
     * 
     * @param ormBean
     *            an instance of {@link XactCodes}
     * @return an instance of {@link XactCodeDto}.
     */
    public static final XactCodeDto createXactCodeInstance(XactCodes ormBean) {
        return new XactCodesRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactDto</i>.
     * <p>
     * A brand new instance of XactDto is created when <i>ormBean</i> is null.
     * Otherwise, <i>ormBean</i> is adapted to an instance of XactDto.
     * 
     * @param ormBean
     *            an instance of {@link Xact}
     * @return an instance of {@link XactDto}.
     */
    public static final XactDto createXactBaseInstance(Xact ormBean) {
        return new XactRmt2OrmAdapter(ormBean);
    }
    
    /**
     * Create an instance of <i>XactDto</i>.
     * <p>
     * A brand new instance of XactDto is created when <i>ormBean</i> is null.
     * Otherwise, <i>ormBean</i> is adapted to an instance of XactDto.
     * 
     * @param ormBean
     *            an instance of {@link Xact}
     * @return an instance of {@link XactDto}.
     */
    public static final XactDto createXactInstance(Xact ormBean) {
        return new XactRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactDto</i>.
     * <p>
     * A brand new instance of XactDto is created when <i>ormBean</i> is null.
     * Otherwise, <i>ormBean</i> is adapted to an instance of XactDto.
     * 
     * @param ormBean
     *            an instance of {@link Xact}
     * @param criteria
     *            customer selection criteria
     * @return an instance of {@link XactDto}.
     */
    public static final XactDto createXactInstance(Xact ormBean, String criteria) {
        return new XactRmt2OrmAdapter(ormBean, criteria);
    }

    /**
     * Create an instance of <i>XactDto</i>.
     * <p>
     * A brand new instance of XactDto is created when <i>ormBean</i> is null.
     * Otherwise, <i>ormBean</i> is adapted to an instance of XactDto.
     * 
     * @param ormBean
     *            an instance of {@link VwXactList}
     * @return an instance of {@link XactDto}.
     */
    public static final XactDto createXactInstance(VwXactList ormBean) {
        return new XactRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactDto</i>.
     * <p>
     * A brand new instance of XactDto is created when <i>ormBean</i> is null.
     * Otherwise, <i>ormBean</i> is adapted to an instance of XactDto.
     * 
     * @param ormBean
     *            an instance of {@link VwXactList}
     * @param criteria
     *            customer selection criteria
     * @return an instance of {@link XactDto}.
     */
    public static final XactDto createXactInstance(VwXactList ormBean,
            String criteria) {
        return new XactRmt2OrmAdapter(ormBean, criteria);
    }

    /**
     * Create an instance of <i>XactTypeItemDto</i>.
     * <p>
     * A brand new instance of XactTypeItemDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * XactTypeItemDto.
     * 
     * @param ormBean
     *            an instance of {@link XactTypeItem}
     * @return an instance of {@link XactTypeItemDto}.
     */
    public static final XactTypeItemDto createXactTypeItemInstance(
            XactTypeItem ormBean) {
        return new XactTypeItemRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactTypeItemActivityDto</i>.
     * <p>
     * A brand new instance of XactTypeItemActivityDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of XactTypeIXactTypeItemActivityDtotemDto.
     * 
     * @param ormBean
     *            an instance of {@link XactTypeItemActivity}
     * @return an instance of {@link XactTypeItemActivityDto}.
     */
    public static final XactTypeItemActivityDto createXactTypeItemActivityInstance(
            XactTypeItemActivity ormBean) {
        return new XactTypeItemActivityRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactTypeItemActivityDto</i>.
     * <p>
     * A brand new instance of XactTypeItemActivityDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of XactTypeItemActivityDto.
     * 
     * @param ormBean
     *            an instance of {@link XactTypeItemActivity}
     * @param criteria
     *            custom selection criteria
     * @return an instance of {@link XactTypeItemActivityDto}.
     */
    public static final XactTypeItemActivityDto createXactTypeItemActivityInstance(
            XactTypeItemActivity ormBean, String criteria) {
        return new XactTypeItemActivityRmt2OrmAdapter(ormBean, criteria);
    }

    /**
     * Create an instance of <i>XactTypeItemActivityDto</i>.
     * <p>
     * A brand new instance of XactTypeItemActivityDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of XactTypeItemActivityDto.
     * 
     * @param ormBean
     *            an instance of {@link VwXactTypeItemActivity}
     * @return an instance of {@link XactTypeItemActivityDto}.
     */
    public static final XactTypeItemActivityDto createXactTypeItemActivityExtInstance(
            VwXactTypeItemActivity ormBean) {
        return new XactTypeItemActivityRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactTypeItemActivityDto</i>.
     * <p>
     * A brand new instance of XactTypeItemActivityDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of XactTypeItemActivityDto.
     * 
     * @param ormBean
     *            an instance of {@link VwXactTypeItemActivity}
     * @param criteria
     *            custom selection criteria
     * @return an instance of {@link XactTypeItemActivityDto}.
     */
    public static final XactTypeItemActivityDto createXactTypeItemActivityExtInstance(
            VwXactTypeItemActivity ormBean, String criteria) {
        return new XactTypeItemActivityRmt2OrmAdapter(ormBean, criteria);
    }

    /**
     * Create an instance of <i>XactTypeItemActivityDto</i>.
     * <p>
     * A brand new instance of XactTypeItemActivityDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of XactTypeItemActivityDto.
     * 
     * @param ormBean
     *            an instance of {@link VwXactItemList}
     * @return an instance of {@link XactTypeItemActivityDto}.
     */
    public static final XactTypeItemActivityDto createXactTypeItemActivityInstance(
            VwXactItemList ormBean) {
        return new VwXactItemListRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>XactTypeItemActivityDto</i>.
     * <p>
     * A brand new instance of XactTypeItemActivityDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of XactTypeItemActivityDto.
     * 
     * @param ormBean
     *            an instance of {@link VwXactItemList}
     * @param criteria
     *            custom selection criteria
     * @return an instance of {@link XactTypeItemActivityDto}.
     */
    public static final XactTypeItemActivityDto createXactTypeItemActivityInstance(
            VwXactItemList ormBean, String criteria) {
        return new VwXactItemListRmt2OrmAdapter(ormBean, criteria);
    }

    /**
     * Create an instance of <i>CommonXactDto</i>.
     * <p>
     * A brand new instance of CommonXactDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * CommonXactDto.
     * 
     * @param ormBean
     *            an instance of {@link VwGenericXactList}
     * @return an instance of {@link CommonXactDto}.
     */
    public static final CommonXactDto createGenericXactInstance(VwGenericXactList ormBean) {
        return new GenericXactRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>MultiDisburseTypeXactDto</i>.
     * <p>
     * A brand new instance of MultiDisburseTypeXactDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of MultiDisburseTypeXactDto.
     * 
     * @param ormBean
     *            an instance of {@link VwMultiDisburseTypeXact}
     * @return an instance of {@link MultiDisburseTypeXactDto}.
     */
    public static final MultiDisburseTypeXactDto createDisbursmentXactInstance(
            VwMultiDisburseTypeXact ormBean) {
        return new MultiDisburseTypeXactRmt2OrmAdapter(ormBean);
    }
}
