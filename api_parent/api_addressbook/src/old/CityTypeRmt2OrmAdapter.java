package org.dto;

import org.dao.mapping.orm.rmt2.CityType;

import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts a RMT2 ORM <i>CityType</i> object to an <i>CityTypeDto</i>.
 * 
 * @author rterrell
 *
 */
class CityTypeRmt2OrmAdapter extends TransactionDtoImpl implements CityTypeDto {

    private CityType ct;
    
    
    /**
     * Create a CityTypeRmt2OrmAdapter using an instance of <i>CityType</i>.
     * 
     * @param cityType
     *          an instance of {@link CityType} or null for the purpose of creating a new 
     *          CityType object
     */
    protected CityTypeRmt2OrmAdapter(CityType cityType) {
	super();
	if (cityType == null) {
	    cityType = new CityType();
	}
	this.ct = cityType;
    }

    /* (non-Javadoc)
     * @see org.dto.CityTypeDto#setCityTypeId(java.lang.String)
     */
    @Override
    public void setCityTypeId(String value) {
	this.ct.setCityTypeId(value);

    }

    /* (non-Javadoc)
     * @see org.dto.CityTypeDto#getCityTypeId()
     */
    @Override
    public String getCityTypeId() {
	return this.ct.getCityTypeId();
    }

    /* (non-Javadoc)
     * @see org.dto.CityTypeDto#setCityTypDescr(java.lang.String)
     */
    @Override
    public void setCityTypDescr(String value) {
	this.ct.setDescr(value);
    }

    /* (non-Javadoc)
     * @see org.dto.CityTypeDto#getCityTypDescr()
     */
    @Override
    public String getCityTypDescr() {
	return this.ct.getDescr();
    }

}
