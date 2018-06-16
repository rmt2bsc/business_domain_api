package org.dto.adapter.orm.transaction.sales;

import java.util.Date;

import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.dto.SalesOrderStatusHistDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * object <i>sales_order_status_hist</i> table.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesOrderStatusHistoryRmt2OrmAdapter extends SalesOrderStatusRmt2OrmAdapter
        implements SalesOrderStatusHistDto {

    private SalesOrderStatusHist hist;

    /**
     * Create a SalesOrderStatusHistoryRmt2OrmAdapter without performing any
     * data adaptations
     */
    protected SalesOrderStatusHistoryRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a SalesOrderStatusHistoryRmt2OrmAdapter that adapts data coming
     * from the <i>sales_order_status_hist</i>.
     * 
     * @param soStatHist
     *            an instance of {@link SalesOrderStatusHist} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected SalesOrderStatusHistoryRmt2OrmAdapter(SalesOrderStatusHist soStatHist) {
        SalesOrderStatusHist stat = null;
        if (soStatHist == null) {
            stat = new SalesOrderStatusHist();
        }
        else if (soStatHist instanceof SalesOrderStatusHist) {
            stat = (SalesOrderStatusHist) soStatHist;
        }
        else {
            return;
        }
        this.hist = stat;

        // Initialize sales order status object
        SalesOrderStatus s = new SalesOrderStatus();
        s.setSoStatusId(stat.getSoStatusId());
        this.updateObjHeirarchy(s);
        
        this.dateCreated = stat.getDateCreated();
        this.updateUserId = stat.getUserId();
        this.ipCreated = stat.getIpCreated();
        this.ipUpdated = stat.getIpUpdated();

        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#setSoStatusHistId(int)
     */
    @Override
    public void setSoStatusHistId(int value) {
        this.hist.setSoStatusHistId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#getSoStatusHistId()
     */
    @Override
    public int getSoStatusHistId() {
        return this.hist.getSoStatusHistId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#setSoId(int)
     */
    @Override
    public void setSoId(int value) {
        this.hist.setSoId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#getSoId()
     */
    @Override
    public int getSoId() {
        return this.hist.getSoId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#setEffectiveDate(java.util.Date)
     */
    @Override
    public void setEffectiveDate(Date value) {
        this.hist.setEffectiveDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#getEffectiveDate()
     */
    @Override
    public Date getEffectiveDate() {
        return this.hist.getEffectiveDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#setEndDate(java.util.Date)
     */
    @Override
    public void setEndDate(Date value) {
        this.hist.setEndDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#getEndDate()
     */
    @Override
    public Date getEndDate() {
        return this.hist.getEndDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#setReason(java.lang.String)
     */
    @Override
    public void setReason(String value) {
        this.hist.setReason(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SalesOrderStatusHistDto#getReason()
     */
    @Override
    public String getReason() {
        return this.hist.getReason();
    }
}
