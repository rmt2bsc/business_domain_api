package org.dto.adapter.jaxb;

import org.dto.AddressDto;
import org.dto.BusinessContactDto;
import org.rmt2.jaxb.AddressType;
import org.rmt2.jaxb.BusinessContactCriteria;
import org.rmt2.jaxb.BusinessType;

import com.RMT2Base;

/**
 * A factory containing several adapter methods for transforming JAXB objects to
 * DTO's.
 * 
 * @author Roy Terrell.
 * 
 */
public class JaxbAddressBookFactory extends RMT2Base {

    /**
     * Creates an instance of <i>BusinessContactDto</i> using a valid
     * <i>BusinessContactCriteria</i> object.
     * 
     * @param criteria
     *            an instance of {@link BusinessContactCriteria}
     * @return an instance of {@link BusinessContactDto}
     */
    public static final BusinessContactDto createBusinessContactDtoInstance(
            BusinessContactCriteria criteria) {
        BusinessContactDto dto = new BusinessContactCriteriaJaxbAdapter(
                criteria);
        return dto;
    }

    /**
     * Creates an instance of <i>BusinessContactDto</i> using a valid
     * <i>BusinessType</i> object.
     * 
     * @param bus
     *            an instance of {@link BusinessType}
     * @return an instance of {@link BusinessContactDto}
     */
    public static final BusinessContactDto createBusinessContactDtoInstance(
            BusinessType bus) {
        BusinessContactDto dto = new BusinessContactJaxbAdapter(bus);
        return dto;
    }

    /**
     * Creates an instance of <i>AddressDto</i> using a valid <i>AddressType</i>
     * object.
     * 
     * @param addr
     *            an instance of {@link AddressType}
     * @return an instance of {@link AddressDto}
     */
    public static final AddressDto createAddressDtoInstance(AddressType addr) {
        AddressDto dto = new AddressJaxbAdapter(addr);
        return dto;
    }
}
