/**
 * 
 */
package org.dto;

import org.dao.mapping.orm.rmt2.Country;
import org.dao.mapping.orm.rmt2.State;

import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts a RMT2 ORM <i>State</i> object to an <i>StateDto</i>.
 * 
 * @author rterrell
 *
 */
class StateProvinceRmt2OrmAdapter extends TransactionDtoImpl implements StateDto {

    private State state;
    
    private Country country;
    
    /**
     * Create a StateProvinceRmt2OrmAdapter using an instance of <i>State</i>.
     * 
     * @param state
     *          an instance of {@link State} or null for the purpose of creating a new 
     *          State object
     * @param country
     *          an instance of {@link Country} or null for the purpose of creating a new 
     *          Country object 
     */
    public StateProvinceRmt2OrmAdapter(State state, Country country) {
	super();
	if (state == null) {
	    state = new State();
	}
	if (country == null) {
	    country = new Country();
	}
	this.state = state;
	this.country = country;
	this.updateUserId = "N/A";
    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#setStateId(int)
     */
    @Override
    public void setStateId(int value) {
	this.state.setStateId(value);

    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#getStateId()
     */
    @Override
    public int getStateId() {
	return this.state.getStateId();
    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#setCode(java.lang.String)
     */
    @Override
    public void setStateCode(String value) {
	this.state.setAbbrCode(value);
    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#getCode()
     */
    @Override
    public String getStateCode() {
	return this.state.getAbbrCode();
    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#setCountryId(int)
     */
    @Override
    public void setCountryId(int value) {
	this.state.setCountryId(value);
    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#getCountryId()
     */
    @Override
    public int getCountryId() {
	return this.state.getCountryId();
    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#setName(java.lang.String)
     */
    @Override
    public void setStateName(String value) {
	this.state.setStateName(value);
    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#getName()
     */
    @Override
    public String getStateName() {
	return this.state.getStateName();
    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#setPermcol(java.lang.String)
     */
    @Override
    public void setStatePermcol(String value) {
	this.state.setSttVoidInd(value);
    }

    /* (non-Javadoc)
     * @see org.dto.StateDto#getPermcol()
     */
    @Override
    public String getStatePermcol() {
	return this.state.getSttVoidInd();
    }

}
