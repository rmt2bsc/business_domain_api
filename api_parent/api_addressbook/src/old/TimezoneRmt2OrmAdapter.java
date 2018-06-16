package org.dto;

import org.dao.mapping.orm.rmt2.TimeZone;

import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts a RMT2 ORM <i>State</i> object to an <i>StateDto</i>.
 * 
 * @author rterrell
 *
 */
class TimezoneRmt2OrmAdapter extends TransactionDtoImpl implements TimeZoneDto {

    private TimeZone tz;
    
    
    /**
     * Create a TimezoneRmt2OrmAdapter using an instance of <i>TimeZone</i>.
     * 
     * @param timezone
     *          an instance of {@link TimeZone} or null for the purpose of creating a new 
     *          TimeZone object
     */
    public TimezoneRmt2OrmAdapter(TimeZone timezone) {
	super();
	if (timezone == null) {
	    timezone = new TimeZone();
	}
	this.tz = timezone;
	this.updateUserId = "N/A";
    }

    /* (non-Javadoc)
     * @see org.dto.TimeZoneDto#setTimeZoneId(int)
     */
    @Override
    public void setTimeZoneId(int value) {
	this.tz.setTimeZoneId(value);
    }

    /* (non-Javadoc)
     * @see org.dto.TimeZoneDto#getTimeZoneId()
     */
    @Override
    public int getTimeZoneId() {
	return this.tz.getTimeZoneId();
    }

    /* (non-Javadoc)
     * @see org.dto.TimeZoneDto#setTimeZoneDescr(java.lang.String)
     */
    @Override
    public void setTimeZoneDescr(String value) {
	this.tz.setDescr(value);
    }

    /* (non-Javadoc)
     * @see org.dto.TimeZoneDto#getTimeZoneDescr()
     */
    @Override
    public String getTimeZoneDescr() {
	return this.tz.getDescr();
    }

}
