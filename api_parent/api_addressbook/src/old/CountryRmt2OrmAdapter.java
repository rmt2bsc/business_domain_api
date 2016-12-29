package org.dto;

import org.dao.mapping.orm.rmt2.Country;

import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts a RMT2 ORM <i>Country</i> object to an <i>CountryDto</i>.
 * 
 * @author rterrell
 *
 */
class CountryRmt2OrmAdapter extends TransactionDtoImpl implements CountryDto {

    private Country country;
    
    /**
     * Create a CountryRmt2OrmAdapter using an instance of <i>Country</i>.
     * 
     * @param country
     *          an instance of {@link Country} or null for the purpose of creating a new 
     *          Country object
     */
    protected CountryRmt2OrmAdapter(Country country) {
	super();
	if (country == null) {
	    country = new Country();
	}
	this.country = country;
	this.updateUserId = "N/A";
    }

    /* (non-Javadoc)
     * @see org.dto.CountryDto#setCountryId(int)
     */
    @Override
    public void setCountryId(int value) {
	this.country.setCountryId(value);
    }

    /* (non-Javadoc)
     * @see org.dto.CountryDto#getCountryId()
     */
    @Override
    public int getCountryId() {
	return this.country.getCountryId();
    }

    /* (non-Javadoc)
     * @see org.dto.CountryDto#setCountryName(java.lang.String)
     */
    @Override
    public void setCountryName(String value) {
	this.country.setName(value);
    }

    /* (non-Javadoc)
     * @see org.dto.CountryDto#getCountryName()
     */
    @Override
    public String getCountryName() {
	return this.country.getName();
    }

    /* (non-Javadoc)
     * @see org.dto.CountryDto#setCountryPermcol(java.lang.String)
     */
    @Override
    public void setCountryPermcol(String value) {
	this.country.setCntryVoidInd(value);
    }

    /* (non-Javadoc)
     * @see org.dto.CountryDto#getCountryPermcol()
     */
    @Override
    public String getCountryPermcol() {
	return this.country.getCntryVoidInd();
    }

    /* (non-Javadoc)
     * @see org.dto.CountryDto#setCountryCode(java.lang.String)
     */
    @Override
    public void setCountryCode(String value) {
	this.country.setCode(value);
    }

    /* (non-Javadoc)
     * @see org.dto.CountryDto#getCountryCode()
     */
    @Override
    public String getCountryCode() {
	return this.country.getCode();
    }

}
