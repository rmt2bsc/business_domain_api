package org.dto.adapter.jaxb;

import org.dto.SubsidiaryContactInfoDto;

import com.RMT2Base;
import com.services.contacts.BusinessContactBean;

/**
 * A factory containing several adapters which function to create various
 * subsidiary related objects from JAXB objects.
 * <p>
 * An example of a subsidiary type would be creditors and customers.
 * 
 * @author Roy Terrell.
 * 
 */
public class JaxbSubsidiaryDtoFactory extends RMT2Base {

    /**
     * Create an instance of <i>SubsidiaryContactInfoDto</i>.
     * <p>
     * A brand new instance of SubsidiaryContactInfoDto is created when
     * <i>jaxbObj</i> is null. Otherwise, <i>jaxbObj</i> is adapted to an
     * instance of SubsidiaryContactInfoDto.
     * 
     * @param jaxbObj
     *            an instance of {@link BusinessContactBean}
     * @return an instance of {@link SubsidiaryContactInfoDto}.
     */
    public static final SubsidiaryContactInfoDto createCreditorInstance(
            BusinessContactBean jaxbObj) {
        return new BusinessContactJaxbAdapter(jaxbObj);
    }

}
