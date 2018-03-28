package org.dto.adapter.orm;

import java.util.Date;
import java.util.List;

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

import com.api.foundation.TransactionDtoImpl;

/**
 * A DTO implementation that adapts data pertaining to the RMT2 ORM objects
 * {@link ProjTimesheet}, {@link ProjTimesheetStatus}, {@link ProjTimesheetHist}
 * , {@link VwTimesheetList}, {@link VwTimesheetHours},
 * {@link VwTimesheetSummary}, and {@link VwClientTimesheetSummary}.
 * 
 * @author Roy Terrell
 * 
 */
class TimesheetRmt2OrmAdapter extends TransactionDtoImpl implements
        TimesheetDto, TimesheetStatusDto, TimesheetHistDto, TimesheetHoursDto,
        TimesheetClientDto, TimesheetHoursSummaryDto {

    // private ProjTimesheet ts;
    private ProjTimesheetStatus tss;
    private ProjTimesheetHist tsh;
    private VwTimesheetList ts;
    private VwTimesheetHours vtsh;
    private VwTimesheetSummary vts;
    private VwClientTimesheetSummary vcts;
    private List<Integer> timesheetIdList;
    private boolean currentHistory;

    /**
     * Create a TimesheetRmt2OrmAdapter object by initializing its data members.
     */
    TimesheetRmt2OrmAdapter() {
        this.ts = null;
        this.tss = null;
        this.tsh = null;
        this.vcts = null;
        this.vtsh = null;
        this.vts = null;
        return;
    }

    /**
     * Creates an instance of TimesheetRmt2OrmAdapter which adapts a
     * {@link ProjTimesheet} object.
     * 
     * @param obj
     *            an instance of {@link ProjTimesheet}
     */
    protected TimesheetRmt2OrmAdapter(ProjTimesheet obj) {
        if (obj == null) {
            obj = new ProjTimesheet();
        }
        this.ts = TimesheetObjectFactory.createOrmTimesheet(obj);
        this.setDateCreated(obj.getDateCreated());
        this.setDateUpdated(obj.getDateUpdated());
        this.setUpdateUserId(obj.getUserId());
        this.setIpCreated(obj.getIpCreated());
        this.setIpUpdated(obj.getIpUpdated());
    }

    /**
     * Creates an instance of TimesheetRmt2OrmAdapter which adapts a
     * {@link VwTimesheetList} object.
     * 
     * @param obj
     *            an instance of {@link VwTimesheetList}
     */
    protected TimesheetRmt2OrmAdapter(VwTimesheetList obj) {
        if (obj == null) {
            obj = new VwTimesheetList();
        }
        this.ts = obj;
    }

    /**
     * Creates an instance of TimesheetRmt2OrmAdapter which adapts a
     * {@link ProjTimesheetStatus} object.
     * 
     * @param obj
     *            an instance of {@link ProjTimesheetStatus}
     */
    protected TimesheetRmt2OrmAdapter(ProjTimesheetStatus obj) {
        if (obj == null) {
            obj = new ProjTimesheetStatus();
        }
        this.tss = obj;
    }

    /**
     * Creates an instance of TimesheetRmt2OrmAdapter which adapts a
     * {@link ProjTimesheetHist} object.
     * 
     * @param obj
     *            an instance of {@link ProjTimesheetHist}
     */
    protected TimesheetRmt2OrmAdapter(ProjTimesheetHist obj) {
        if (obj == null) {
            obj = new ProjTimesheetHist();
        }
        this.tsh = obj;
        this.currentHistory = false;
        this.setUpdateUserId(obj.getUserId());
        this.setIpCreated(obj.getIpCreated());
        this.setIpUpdated(obj.getIpUpdated());
    }

    /**
     * Creates an instance of TimesheetRmt2OrmAdapter which adapts a
     * {@link VwTimesheetHours} object.
     * 
     * @param obj
     *            an instance of {@link VwTimesheetHours}
     */
    protected TimesheetRmt2OrmAdapter(VwTimesheetHours obj) {
        if (obj == null) {
            obj = new VwTimesheetHours();
        }
        this.vtsh = obj;
        this.setDateCreated(obj.getDateCreated());
    }

    /**
     * Creates an instance of TimesheetRmt2OrmAdapter which adapts a
     * {@link VwTimesheetSummary} object.
     * 
     * @param obj
     *            an instance of {@link VwTimesheetSummary}
     */
    protected TimesheetRmt2OrmAdapter(VwTimesheetSummary obj) {
        if (obj == null) {
            obj = new VwTimesheetSummary();
        }
        this.vts = obj;
    }

    /**
     * Creates an instance of TimesheetRmt2OrmAdapter which adapts a
     * {@link VwClientTimesheetSummary} object.
     * 
     * @param obj
     *            an instance of {@link VwClientTimesheetSummary}
     */
    protected TimesheetRmt2OrmAdapter(VwClientTimesheetSummary obj) {
        if (obj == null) {
            obj = new VwClientTimesheetSummary();
        }
        this.vcts = obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setTimesheetId(int)
     */
    @Override
    public void setTimesheetId(int value) {
        if (this.ts != null) {
            this.ts.setTimesheetId(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setTimesheetId(value);
        }
        if (this.tsh != null) {
            this.tsh.setTimesheetId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getTimesheetId()
     */
    @Override
    public int getTimesheetId() {
        if (this.ts != null) {
            return this.ts.getTimesheetId();
        }
        if (this.vtsh != null) {
            return this.vtsh.getTimesheetId();
        }
        if (this.tsh != null) {
            return this.tsh.getTimesheetId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setEmpId(int)
     */
    @Override
    public void setEmpId(int value) {
        if (this.ts != null) {
            this.ts.setEmpId(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setEmployeeId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getEmpId()
     */
    @Override
    public int getEmpId() {
        if (this.ts != null) {
            return this.ts.getEmpId();
        }
        if (this.vtsh != null) {
            return this.vtsh.getEmployeeId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setDisplayValue(java.lang.String)
     */
    @Override
    public void setDisplayValue(String value) {
        if (this.ts != null) {
            this.ts.setDisplayValue(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setDisplayValue(value);
        }
        if (this.vts != null) {
            this.vts.setDisplayValue(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getDisplayValue()
     */
    @Override
    public String getDisplayValue() {
        if (this.ts != null) {
            return this.ts.getDisplayValue();
        }
        if (this.vtsh != null) {
            return this.vtsh.getDisplayValue();
        }
        if (this.vts != null) {
            return this.vts.getDisplayValue();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setBeginPeriod(java.util.Date)
     */
    @Override
    public void setBeginPeriod(Date value) {
        if (this.ts != null) {
            this.ts.setBeginPeriod(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setTimesheetBeginPeriod(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getBeginPeriod()
     */
    @Override
    public Date getBeginPeriod() {
        if (this.ts != null) {
            return this.ts.getBeginPeriod();
        }
        if (this.vtsh != null) {
            return this.vtsh.getTimesheetBeginPeriod();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setEndPeriod(java.util.Date)
     */
    @Override
    public void setEndPeriod(Date value) {
        if (this.ts != null) {
            this.ts.setEndPeriod(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setTimesheetEndPeriod(value);
        }
        if (this.vts != null) {
            this.vts.setEndPeriod(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getEndPeriod()
     */
    @Override
    public Date getEndPeriod() {
        if (this.ts != null) {
            return this.ts.getEndPeriod();
        }
        if (this.vtsh != null) {
            return this.vtsh.getTimesheetEndPeriod();
        }
        if (this.vts != null) {
            return this.vts.getEndPeriod();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setInvoiceRefNo(java.lang.String)
     */
    @Override
    public void setInvoiceRefNo(String value) {
        if (this.ts != null) {
            this.ts.setInvoiceRefNo(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setInvoiceRefNo(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getInvoiceRefNo()
     */
    @Override
    public String getInvoiceRefNo() {
        if (this.ts != null) {
            return this.ts.getInvoiceRefNo();
        }
        if (this.vtsh != null) {
            return this.vtsh.getInvoiceRefNo();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setExtRef(java.lang.String)
     */
    @Override
    public void setExtRef(String value) {
        if (this.ts != null) {
            this.ts.setExtRef(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setExtRef(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getExtRef()
     */
    @Override
    public String getExtRef() {
        if (this.ts != null) {
            return this.ts.getExtRef();
        }
        if (this.vtsh != null) {
            return this.vtsh.getExtRef();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setProjId(int)
     */
    @Override
    public void setProjId(int value) {
        if (this.ts != null) {
            this.ts.setProjId(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setProjectId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getProjId()
     */
    @Override
    public int getProjId() {
        if (this.ts != null) {
            return this.ts.getProjId();
        }
        if (this.vtsh != null) {
            return this.vtsh.getProjectId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setComments(java.lang.String)
     */
    @Override
    public void setComments(String value) {
        if (this.ts != null) {
            this.ts.setComments(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getComments()
     */
    @Override
    public String getComments() {
        if (this.ts != null) {
            return this.ts.getComments();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setDocumentId(int)
     */
    @Override
    public void setDocumentId(int value) {
        if (this.ts != null) {
            this.ts.setDocumentId(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setDocumentId(value);
        }
        if (this.vts != null) {
            this.vts.setDocumentId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getDocumentId()
     */
    @Override
    public int getDocumentId() {
        if (this.ts != null) {
            return this.ts.getDocumentId();
        }
        if (this.vtsh != null) {
            return this.vtsh.getDocumentId();
        }
        if (this.vts != null) {
            return this.vts.getDocumentId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setStatusHistId(int)
     */
    @Override
    public void setStatusHistId(int value) {
        if (this.ts != null) {
            this.ts.setProjTimesheetHistId(value);
        }
        if (this.tsh != null) {
            this.tsh.setTimesheetHistId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getStatusHistId()
     */
    @Override
    public int getStatusHistId() {
        if (this.ts != null) {
            return this.ts.getProjTimesheetHistId();
        }
        if (this.tsh != null) {
            return this.tsh.getTimesheetHistId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setStatusEffectiveDate(java.util.Date)
     */
    @Override
    public void setStatusEffectiveDate(Date value) {
        if (this.ts != null) {
            this.ts.setStatusEffectiveDate(value);
        }
        if (this.tsh != null) {
            this.tsh.setEffectiveDate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getStatusEffectiveDate()
     */
    @Override
    public Date getStatusEffectiveDate() {
        if (this.ts != null) {
            return this.ts.getStatusEffectiveDate();
        }
        if (this.tsh != null) {
            return this.tsh.getEffectiveDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setStatusEndDate(java.util.Date)
     */
    @Override
    public void setStatusEndDate(Date value) {
        if (this.ts != null) {
            this.ts.setStatusEndDate(value);
        }
        if (this.tsh != null) {
            this.tsh.setEndDate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getStatusEndDate()
     */
    @Override
    public Date getStatusEndDate() {
        if (this.ts != null) {
            return this.ts.getStatusEndDate();
        }
        if (this.tsh != null) {
            return this.tsh.getEndDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setEmployeeTypeId(int)
     */
    @Override
    public void setEmployeeTypeId(int value) {
        if (this.ts != null) {
            this.ts.setTypeId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getEmployeeTypeId()
     */
    @Override
    public int getEmployeeTypeId() {
        if (this.ts != null) {
            return this.ts.getTypeId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setEmployeeFirstname(java.lang.String)
     */
    @Override
    public void setEmployeeFirstname(String value) {
        if (this.ts != null) {
            this.ts.setFirstname(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getEmployeeFirstname()
     */
    @Override
    public String getEmployeeFirstname() {
        if (this.ts != null) {
            return this.ts.getFirstname();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setEmployeeLastname(java.lang.String)
     */
    @Override
    public void setEmployeeLastname(String value) {
        if (this.ts != null) {
            this.ts.setLastname(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getEmployeeLastname()
     */
    @Override
    public String getEmployeeLastname() {
        if (this.ts != null) {
            return this.ts.getLastname();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setEmployeeManagerId(int)
     */
    @Override
    public void setEmployeeManagerId(int value) {
        if (this.ts != null) {
            this.ts.setManagerId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getEmployeeManagerId()
     */
    @Override
    public int getEmployeeManagerId() {
        if (this.ts != null) {
            return this.ts.getManagerId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setEmployeeHourlyOverRate(double)
     */
    @Override
    public void setEmployeeHourlyOverRate(double value) {
        if (this.ts != null) {
            this.ts.setHourlyOverRate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getEmployeeHourlyOverRate()
     */
    @Override
    public double getEmployeeHourlyOverRate() {
        if (this.ts != null) {
            return this.ts.getHourlyOverRate();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setEmployeeHourlyRate(double)
     */
    @Override
    public void setEmployeeHourlyRate(double value) {
        if (this.ts != null) {
            this.ts.setHourlyRate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getEmployeeHourlyRate()
     */
    @Override
    public double getEmployeeHourlyRate() {
        if (this.ts != null) {
            return this.ts.getHourlyRate();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setEmployeeFullName(java.lang.String)
     */
    @Override
    public void setEmployeeFullName(String value) {
        if (this.ts != null) {
            this.ts.setLastFirstName(value);
        }
        if (this.vts != null) {
            this.vts.setShortname(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getEmployeeFullName()
     */
    @Override
    public String getEmployeeFullName() {
        if (this.ts != null) {
            return this.ts.getLastFirstName();
        }
        if (this.vts != null) {
            return this.vts.getShortname();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setBillHrs(double)
     */
    @Override
    public void setBillHrs(double value) {
        if (this.ts != null) {
            this.ts.setBillHrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getBillHrs()
     */
    @Override
    public double getBillHrs() {
        if (this.ts != null) {
            return this.ts.getBillHrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setNonBillHrs(double)
     */
    @Override
    public void setNonBillHrs(double value) {
        if (this.ts != null) {
            this.ts.setNonBillHrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getNonBillHrs()
     */
    @Override
    public double getNonBillHrs() {
        if (this.ts != null) {
            return this.ts.getNonBillHrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#setStatusId(int)
     */
    @Override
    public void setStatusId(int value) {
        if (this.ts != null) {
            this.ts.setTimesheetStatusId(value);
        }
        if (this.tsh != null) {
            this.tsh.setTimesheetStatusId(value);
        }
        if (this.tss != null) {
            this.tss.setTimesheetStatusId(value);
        }
        if (this.vcts != null) {
            this.vcts.setStatusId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#getStatusId()
     */
    @Override
    public int getStatusId() {
        if (this.ts != null) {
            return this.ts.getTimesheetStatusId();
        }
        if (this.tsh != null) {
            return this.tsh.getTimesheetStatusId();
        }
        if (this.tss != null) {
            return this.tss.getTimesheetStatusId();
        }
        if (this.vcts != null) {
            this.vcts.getStatusId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#setStatusName(java.lang.String)
     */
    @Override
    public void setStatusName(String value) {
        if (this.ts != null) {
            this.ts.setStatusName(value);
        }
        if (this.tss != null) {
            this.tss.setName(value);
        }
        if (this.vcts != null) {
            this.vcts.setStatusName(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#getStatusName()
     */
    @Override
    public String getStatusName() {
        if (this.ts != null) {
            return this.ts.getStatusName();
        }
        if (this.tss != null) {
            return this.tss.getName();
        }
        if (this.vcts != null) {
            return this.vcts.getStatusName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#setStatusDescription(java.lang.String)
     */
    @Override
    public void setStatusDescription(String value) {
        if (this.ts != null) {
            this.ts.setStatusDescription(value);
        }
        if (this.tss != null) {
            this.tss.setDescription(value);
        }
        if (this.vcts != null) {
            this.vcts.setStatusDescr(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#getStatusDescription()
     */
    @Override
    public String getStatusDescription() {
        if (this.ts != null) {
            return this.ts.getStatusDescription();
        }
        if (this.tss != null) {
            return this.tss.getDescription();
        }
        if (this.vcts != null) {
            return this.vcts.getStatusDescr();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#setClientId(int)
     */
    @Override
    public void setClientId(int value) {
        if (this.ts != null) {
            this.ts.setClientId(value);
        }
        if (this.vtsh != null) {
            this.vtsh.setClientId(value);
        }
        if (this.vcts != null) {
            this.vcts.setClientId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#getClientId()
     */
    @Override
    public int getClientId() {
        if (this.ts != null) {
            return this.ts.getClientId();
        }
        if (this.vtsh != null) {
            return this.vtsh.getClientId();
        }
        if (this.vcts != null) {
            return this.vcts.getClientId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#setClientName(java.lang.String)
     */
    @Override
    public void setClientName(String value) {
        if (this.ts != null) {
            this.ts.setClientName(value);
        }
        if (this.vcts != null) {
            this.vcts.setName(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#getClientName()
     */
    @Override
    public String getClientName() {
        if (this.ts != null) {
            return this.ts.getClientName();
        }
        if (this.vcts != null) {
            return this.vcts.getName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#setClientAccountNo(java.lang.String)
     */
    @Override
    public void setClientAccountNo(String value) {
        if (this.ts != null) {
            this.ts.setAccountNo(value);
        }
        if (this.vcts != null) {
            this.vcts.setAccountNo(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#getClientAccountNo()
     */
    @Override
    public String getClientAccountNo() {
        if (this.ts != null) {
            return this.ts.getAccountNo();
        }
        if (this.vcts != null) {
            return this.vcts.getAccountNo();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setProjectDescription(java.lang.String)
     */
    @Override
    public void setProjectDescription(String value) {
        if (this.vtsh != null) {
            this.vtsh.setProjectName(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getProjectDescription()
     */
    @Override
    public String getProjectDescription() {
        if (this.vtsh != null) {
            return this.vtsh.getProjectName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setProjectEffectiveDate(java.util.Date)
     */
    @Override
    public void setProjectEffectiveDate(Date value) {
        if (this.vtsh != null) {
            this.vtsh.setEffectiveDate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getProjectEffectiveDate()
     */
    @Override
    public Date getProjectEffectiveDate() {
        if (this.vtsh != null) {
            return this.vtsh.getEffectiveDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setProjectEndDate(java.util.Date)
     */
    @Override
    public void setProjectEndDate(Date value) {
        if (this.vtsh != null) {
            this.vtsh.setEndDate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getProjectEndDate()
     */
    @Override
    public Date getProjectEndDate() {
        if (this.vtsh != null) {
            return this.vtsh.getEndDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setTaskId(int)
     */
    @Override
    public void setTaskId(int value) {
        if (this.vtsh != null) {
            this.vtsh.setTaskId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getTaskId()
     */
    @Override
    public int getTaskId() {
        if (this.vtsh != null) {
            return this.vtsh.getTaskId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setTaskDescription(java.lang.String)
     */
    @Override
    public void setTaskDescription(String value) {
        if (this.vtsh != null) {
            this.vtsh.setTaskName(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getTaskDescription()
     */
    @Override
    public String getTaskDescription() {
        if (this.vtsh != null) {
            return this.vtsh.getTaskName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setTaskBillable(int)
     */
    @Override
    public void setTaskBillable(int value) {
        if (this.vtsh != null) {
            this.vtsh.setBillable(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getTaskBillable()
     */
    @Override
    public int getTaskBillable() {
        if (this.vtsh != null) {
            return this.vtsh.getBillable();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setProjectTaskId(int)
     */
    @Override
    public void setProjectTaskId(int value) {
        if (this.vtsh != null) {
            this.vtsh.setProjTaskId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getProjectTaskId()
     */
    @Override
    public int getProjectTaskId() {
        if (this.vtsh != null) {
            return this.vtsh.getProjTaskId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setEventId(int)
     */
    @Override
    public void setEventId(int value) {
        if (this.vtsh != null) {
            this.vtsh.setEventId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getEventId()
     */
    @Override
    public int getEventId() {
        if (this.vtsh != null) {
            return this.vtsh.getEventId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setEventDate(java.util.Date)
     */
    @Override
    public void setEventDate(Date value) {
        if (this.vtsh != null) {
            this.vtsh.setEventDate(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getEventDate()
     */
    @Override
    public Date getEventDate() {
        if (this.vtsh != null) {
            return this.vtsh.getEventDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setEventHours(double)
     */
    @Override
    public void setEventHours(double value) {
        if (this.vtsh != null) {
            this.vtsh.setHours(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getEventHours()
     */
    @Override
    public double getEventHours() {
        if (this.vtsh != null) {
            return this.vtsh.getHours();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#setEventDateCreated(java.util.Date)
     */
    @Override
    public void setEventDateCreated(Date value) {
        if (this.vtsh != null) {
            this.vtsh.setDateCreated(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursDto#getEventDateCreated()
     */
    @Override
    public Date getEventDateCreated() {
        if (this.vtsh != null) {
            return this.vtsh.getDateCreated();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#setBusinessId(int)
     */
    @Override
    public void setBusinessId(int value) {
        if (this.vcts != null) {
            this.vcts.setBusinessId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#getBusinessId()
     */
    @Override
    public int getBusinessId() {
        if (this.vcts != null) {
            this.vcts.getBusinessId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#setTimesheetCount(int)
     */
    @Override
    public void setTimesheetCount(int value) {
        if (this.vcts != null) {
            this.vcts.setTimesheetCount(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetClientDto#getTimesheetCount()
     */
    @Override
    public int getTimesheetCount() {
        if (this.vcts != null) {
            return this.vcts.getTimesheetCount();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#setHours1(double)
     */
    @Override
    public void setHours1(double value) {
        if (this.vts != null) {
            this.vts.setDay1Hrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#getHours1()
     */
    @Override
    public double getHours1() {
        if (this.vts != null) {
            this.vts.getDay1Hrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#setHours2(double)
     */
    @Override
    public void setHours2(double value) {
        if (this.vts != null) {
            this.vts.setDay2Hrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#getHours2()
     */
    @Override
    public double getHours2() {
        if (this.vts != null) {
            this.vts.getDay2Hrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#setHours3(double)
     */
    @Override
    public void setHours3(double value) {
        if (this.vts != null) {
            this.vts.setDay3Hrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#getHours3()
     */
    @Override
    public double getHours3() {
        if (this.vts != null) {
            this.vts.getDay3Hrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#setHours4(double)
     */
    @Override
    public void setHours4(double value) {
        if (this.vts != null) {
            this.vts.setDay4Hrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#getHours4()
     */
    @Override
    public double getHours4() {
        if (this.vts != null) {
            this.vts.getDay4Hrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#setHours5(double)
     */
    @Override
    public void setHours5(double value) {
        if (this.vts != null) {
            this.vts.setDay5Hrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#getHours5()
     */
    @Override
    public double getHours5() {
        if (this.vts != null) {
            this.vts.getDay5Hrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#setHours6(double)
     */
    @Override
    public void setHours6(double value) {
        if (this.vts != null) {
            this.vts.setDay6Hrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#getHours6()
     */
    @Override
    public double getHours6() {
        if (this.vts != null) {
            this.vts.getDay6Hrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#setHours7(double)
     */
    @Override
    public void setHours7(double value) {
        if (this.vts != null) {
            this.vts.setDay7Hrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#getHours7()
     */
    @Override
    public double getHours7() {
        if (this.vts != null) {
            this.vts.getDay7Hrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#setTotalHours(double)
     */
    @Override
    public void setTotalHours(double value) {
        if (this.vts != null) {
            this.vts.setTotalHrs(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHoursSummaryDto#getTotalHours()
     */
    @Override
    public double getTotalHours() {
        if (this.vts != null) {
            this.vts.getTotalHrs();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#setTimesheetIdList(java.util.List)
     */
    @Override
    public void setTimesheetIdList(List<Integer> value) {
        this.timesheetIdList = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetDto#getTimesheetIdList()
     */
    @Override
    public List<Integer> getTimesheetIdList() {
        return this.timesheetIdList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHistDto#setCurrentStatusFlag(boolean)
     */
    @Override
    public void setCurrentStatusFlag(boolean flag) {
        this.currentHistory = flag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TimesheetHistDto#isCurrentStatusFlag()
     */
    @Override
    public boolean isCurrentStatusFlag() {
        return this.currentHistory;
    }

}
