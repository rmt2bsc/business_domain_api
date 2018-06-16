package org.modules.subsidiary;

import java.util.List;
import java.util.Map;

import org.AccountingConst.SubsidiaryType;
import org.dao.subsidiary.SubsidiaryDaoException;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.modules.AddressBookConstants;
import org.modules.contacts.ContactsApi;
import org.modules.contacts.ContactsApiException;
import org.modules.contacts.ContactsApiFactory;

import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;

/**
 * Abstract class providing common functionality to manage subsidiaries from the
 * level of the API module.
 * 
 * @author Roy Terrell
 * 
 */
abstract class AbstractSubsidiaryApiImpl<E> extends AbstractTransactionApiImpl
        implements SubsidiaryApi {

    /**
     * Default constructor
     */
    public AbstractSubsidiaryApiImpl() {
        return;
    }

    protected AbstractSubsidiaryApiImpl(DaoClient connection) {
        super(connection);
    }

    /**
     * Fetches subsidiary accounts.
     * <p>
     * Combines common contact data with the subsidiary account information.
     * 
     * @param criteria
     *            an instance of {@link E} containing the selection
     *            criteria that is used by both the local and remote queries.
     * @return List of {@link E} or null when no data is found
     * @throws CreditorDaoException
     */
    protected abstract List<E> getSubsidiaryInfo(E criteria) throws SubsidiaryException;
    
    /**
     * Combines the a list of subsidiary data with a list of common contact data.
     * 
     * @param subsidiary
     * @param contact
     * @return a List<E>
     */
    protected abstract List<E> mergeContactInfo(List<E> subsidiary, Map<Integer, SubsidiaryContactInfoDto> contact);
    
    /**
     * Creates an account number for a given subsidiary.
     * 
     * @param businessId
     *            The business id of the subsidiary
     * @param subType
     *            an enum type of
     *            {@link org.modules.subsidiary.AccountingConst.SubsidiaryType}
     *            which identifies the type of subsidiary.
     * @return The account number.
     */
    @Override
    public String buildAccountNo(int businessId, SubsidiaryType subType) {
        SubsidiaryHelper helper = new SubsidiaryHelper();
        return helper.buildAccountNo(businessId, subType);
    }

    /**
     * Fetch common subsidiary contact data based on the selection criteria 
     * containted in <i>criteria</i>.
     * <p>
     * The properties, <i>contactIdList</i>, <i>contactId</i>, <i>contactName</i>, 
     * <i>taxId</i>, and <i>phoneCompany</i> are used to build the query predicate.
     * 
     * @param criteria
     *            an instance of {@link SubsidiaryContactInfoDto} which each
     *            property found to have a value will serve as selection
     *            criteria. The properties targeted in <i>crtieria</i> that are
     *            used to filter data can be:
     *            <ul>
     *               <li><i>contactIdList</i> - a list of business id's.</li> 
     *               <li><i>contactId</i> - a single business id.</li> 
     *               <li><i>contactName</i> - the contact's name.</li> 
     *               <li><i>taxId</i> - the contact's tax id number.</li> 
     *               <li><i>phoneCompany</i> - the contact's main phone number.</li>
     *            </ul>
     * @return Map <Integer, {@link SubsidiaryContactInfoDto}> in which the map
     *         is keyed by business id.
     * @throws SubsidiaryDaoException
     */
    public Map<Integer, SubsidiaryContactInfoDto> getContactInfo(SubsidiaryContactInfoDto criteria) 
            throws SubsidiaryException {
        if (criteria == null) {
            throw new SubsidiaryDaoException("Subsidiary contact criteria object cannot be null");
        }
        BusinessContactDto contactDto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();

        // Add multiple business id's to submit to remote service
        if (criteria.getContactIdList() != null && criteria.getContactIdList().size() > 0) {
            contactDto.setContactIdList(criteria.getContactIdList());
        }
        // Add single business id to submit to remote service
        if (criteria.getContactId() > 0) {
            contactDto.setContactId(criteria.getContactId());
        }
        // Add contact name to submit to remote service
        if (criteria.getContactName() != null) {
            contactDto.setContactName(criteria.getContactName());
        }
        // Add tax id to submit to remote service
        if (criteria.getTaxId() != null) {
            contactDto.setTaxId(criteria.getTaxId());
        }
        // Add company phone to submit to remote service
        if (criteria.getPhoneCompany() != null) {
            contactDto.setPhoneCompany(criteria.getPhoneCompany());
        }
        
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(contactDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        
        Map<Integer, SubsidiaryContactInfoDto> map = Rmt2SubsidiaryDtoFactory
                .createContactMap(results);
        return map;
    }
}
