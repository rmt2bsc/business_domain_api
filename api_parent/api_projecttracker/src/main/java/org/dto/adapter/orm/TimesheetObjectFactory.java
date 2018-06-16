package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.ProjTimesheet;
import org.dao.mapping.orm.rmt2.ProjTimesheetHist;
import org.dao.mapping.orm.rmt2.ProjTimesheetStatus;
import org.dao.mapping.orm.rmt2.VwClientTimesheetSummary;
import org.dao.mapping.orm.rmt2.VwTimesheetHours;
import org.dao.mapping.orm.rmt2.VwTimesheetList;
import org.dao.mapping.orm.rmt2.VwTimesheetSummary;
import org.dto.TimesheetClientDto;
import org.dto.TimesheetDto;
import org.dto.TimesheetHistDto;
import org.dto.TimesheetHoursDto;
import org.dto.TimesheetHoursSummaryDto;
import org.dto.TimesheetStatusDto;

import com.RMT2Base;

/**
 * A factory containing several methods to create adapters and converters for
 * obtaining timesheet reltated DTO's.
 * 
 * @author Roy Terrell.
 * 
 */
public class TimesheetObjectFactory extends RMT2Base {

    /**
     * Create an instance of <i>TimesheetDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjTimesheet}
     * 
     * @return an instance of {@link TimesheetDto}.
     */
    public static final TimesheetDto createTimesheetDtoInstance(ProjTimesheet ormBean) {
        return new TimesheetRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>TimesheetDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link VwTimesheetList}
     * 
     * @return an instance of {@link TimesheetDto}.
     */
    public static final TimesheetDto createTimesheetExtendedDtoInstance(VwTimesheetList ormBean) {
        return new TimesheetRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>TimesheetStatusDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjTimesheetStatus}
     * 
     * @return an instance of {@link TimesheetStatusDto}.
     */
    public static final TimesheetStatusDto createTimesheetStatusDtoInstance(ProjTimesheetStatus ormBean) {
        return new TimesheetRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>TimesheetHistDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjTimesheetHist}
     * 
     * @return an instance of {@link TimesheetHistDto}.
     */
    public static final TimesheetHistDto createTimesheetHistoryDtoInstance(ProjTimesheetHist ormBean) {
        return new TimesheetRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>TimesheetHoursDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link VwTimesheetHours}
     * 
     * @return an instance of {@link TimesheetHoursDto}.
     */
    public static final TimesheetHoursDto createTimesheetHoursDtoInstance(VwTimesheetHours ormBean) {
        return new TimesheetRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>TimesheetHoursSummaryDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link VwTimesheetSummary}
     * 
     * @return an instance of {@link TimesheetHoursSummaryDto}.
     */
    public static final TimesheetHoursSummaryDto createTimesheetSummaryDtoInstance(VwTimesheetSummary ormBean) {
        return new TimesheetRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>TimesheetClientDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link VwClientTimesheetSummary}
     * 
     * @return an instance of {@link TimesheetClientDto}.
     */
    public static final TimesheetClientDto createTimesheetClientDtoInstance(VwClientTimesheetSummary ormBean) {
        return new TimesheetRmt2OrmAdapter(ormBean);
    }

    /**
     * Create a <i>VwTimesheetList</i> object from a <i>ProjTimesheet</i>
     * object.
     * 
     * @param src
     *            an instnace of {@link ProjTimesheet}
     * @return
     */
    public static VwTimesheetList createOrmTimesheet(ProjTimesheet src) {
        VwTimesheetList ts = new VwTimesheetList();
        ts.setTimesheetId(src.getTimesheetId());
        ts.setClientId(src.getClientId());
        ts.setEmpId(src.getEmpId());
        ts.setDisplayValue(src.getDisplayValue());
        ts.setBeginPeriod(src.getBeginPeriod());
        ts.setEndPeriod(src.getEndPeriod());
        ts.setInvoiceRefNo(src.getInvoiceRefNo());
        ts.setExtRef(src.getExtRef());
        ts.setProjId(src.getProjId());
        ts.setComments(src.getComments());
        ts.setDocumentId(src.getDocumentId());
        return ts;
    }
}
