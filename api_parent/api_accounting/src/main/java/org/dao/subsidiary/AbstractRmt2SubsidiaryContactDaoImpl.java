package org.dao.subsidiary;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dao.AccountingDaoImpl;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.SubsidiaryXactHistoryDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.rmt2.jaxb.BusinessContactCriteria;
import org.rmt2.jaxb.BusinessType;
import org.rmt2.jaxb.HeaderType;
import org.rmt2.jaxb.ObjectFactory;
import org.rmt2.jaxb.RQBusinessContactSearch;
import org.rmt2.jaxb.RSBusinessContactSearch;
import org.rmt2.util.JaxbPayloadFactory;

import com.api.messaging.webservice.router.MessageRoutingException;
import com.api.messaging.webservice.WebServiceConstants;
import com.api.messaging.webservice.router.MessageRouterHelper;
import com.api.persistence.PersistenceClient;

//import com.services.contacts.ContactDataFactory;

/**
 * Queries, updates, deletes, and creates general contact information for a
 * subsidiary.
 * <p>
 * Implements the interface, {@link SubsidiaryDao}, in order to serve requests
 * for common business contact data as it pertains to any given subsidiary via
 * web services. <i>RQ_business_contact_search</i> is the web services utilized.
 * 
 * @author Roy Terrell
 * 
 */
abstract class AbstractRmt2SubsidiaryContactDaoImpl extends AccountingDaoImpl
        implements SubsidiaryDao {

    private static Logger logger = Logger
            .getLogger(AbstractRmt2SubsidiaryContactDaoImpl.class);

    /**
     * Default constructor for creating a AbstractRmt2SubsidiaryContactDaoImpl
     * object
     */
    public AbstractRmt2SubsidiaryContactDaoImpl() {
        super();
        return;
    }

    public AbstractRmt2SubsidiaryContactDaoImpl(String appName) {
        super(appName);
        return;
    }

    public AbstractRmt2SubsidiaryContactDaoImpl(PersistenceClient client) {
        super(client);
        return;
    }

    /**
     * A web services implementation to fetch common subsidiary contact data
     * based on the selection criteria containted in <i>criteria</i>.
     * <p>
     * The properties, <i>contactIdList</i>, <i>contactId</i>,
     * <i>contactName</i>, <i>taxId</i>, and <i>phoneCompany</i> are used to
     * build the query predicate.
     * 
     * @param criteria
     *            an instance of {@link SubsidiaryContactInfoDto} which each
     *            property found to have a value will serve as selection
     *            criteria. The properties targeted in <i>crtieria</i> that are
     *            used to filter data can be:
     *            <ul>
     *            <li><i>contactIdList</i> - a list of business id's.</li> <li>
     *            <i>contactId</i> - a single business id.</li> <li>
     *            <i>contactName</i> - the contact's name.</li> <li><i>taxId</i>
     *            - the contact's tax id number.</li> <li><i>phoneCompany</i> -
     *            the contact's main phone number.</li>
     *            </ul>
     * @return Map <Integer, {@link SubsidiaryContactInfoDto}> in which the map
     *         is keyed by business id.
     * @throws SubsidiaryDaoException
     */
    @Override
    public Map<Integer, SubsidiaryContactInfoDto> fetch(
            SubsidiaryContactInfoDto criteria) throws SubsidiaryDaoException {

        if (criteria == null) {
            throw new SubsidiaryDaoException(
                    "Error fetching common subsidiary contact information.  The criteria object cannot be null");
        }
        ObjectFactory f = new ObjectFactory();
        BusinessContactCriteria wsCriteria = f.createBusinessContactCriteria();
        // Add list business id's to submit to remote service
        if (criteria.getContactIdList() != null
                && criteria.getContactIdList().size() > 0) {
            for (String item : criteria.getContactIdList()) {
                long busId = Long.parseLong(item);
                wsCriteria.getBusinessId().add(BigInteger.valueOf(busId));
            }
        }
        // Add single business id to submit to remote service
        if (criteria.getContactId() > 0) {
            wsCriteria.getBusinessId().add(
                    BigInteger.valueOf(criteria.getContactId()));
        }
        // Add contact name to submit to remote service
        if (criteria.getContactName() != null) {
            wsCriteria.setBusinessName(criteria.getContactName());
        }
        // Add tax id to submit to remote service
        if (criteria.getTaxId() != null) {
            wsCriteria.setTaxId(criteria.getTaxId());
        }
        // Add company phone to submit to remote service
        if (criteria.getPhoneCompany() != null) {
            wsCriteria.setMainPhone(criteria.getPhoneCompany());
        }

        // ContactDataService srvc = new ContactDataService();
        // ContactDataFactory cdf = new ContactDataFactory();
        List<BusinessType> busTypelist = null;
        // List<SubsidiaryContactInfoDto> dtoList = null;
        try {
            // Send request to message broker
            busTypelist = this.getBusinessContactData(wsCriteria,
                    this.getDaoUser());
        } catch (Exception e) {
            msg = "SOAP error: Problem occurred requesting common contact data via web service.";
            logger.error(msg);
            throw new SubsidiaryDaoException(msg, e);
        }
        // Decode the results of the service call.
        // dtoList =
        // Rmt2SubsidiaryDtoFactory.createBusinessContact(busTypelist);
        Map<Integer, SubsidiaryContactInfoDto> map = Rmt2SubsidiaryDtoFactory
                .createBusinessContactMap(busTypelist);
        return map;
    }

    /**
     * Obtains a list of business contact data pertaining to one or more
     * creditors based the selection criteria specified. Merges each business
     * contact record to an instance of CreditorBean. Each CreditorBean instance
     * is added to List collection and returned to the client.
     * 
     * @param criteria
     *            an instance of {@link BusinessContactCriteria}
     * @param loginId
     *            the id of the user requesting contact data
     * @return List <{@link com.gl.creditor.CreditorExt CreditorBean}>
     * @throws DataServiceException
     */
    private List<BusinessType> getBusinessContactData(
            BusinessContactCriteria criteria, String loginId) {
        RSBusinessContactSearch soapResp = this.getContactData(criteria,
                loginId);
        return soapResp.getBusinessList();
    }

    /**
     * Calls the web service, RQ_business_contact_search, to fetch business
     * contact data.
     * 
     * @param criteria
     *            an instance of
     *            {@link com.xml.schema.bindings.BusinessContactCriteria
     *            BusinessContactCriteria}
     * @param loginId
     *            the id of the user requesting the business contact fetch
     * @return {@link com.xml.schema.bindings.RSBusinessContactSearch
     *         RSBusinessContactSearch}
     * @throws MessageRoutingException
     */
    private RSBusinessContactSearch getContactData(
            BusinessContactCriteria criteria, String loginId) {
        String messageId = "RQ_business_contact_search";
        ObjectFactory f = new ObjectFactory();
        // Create Payload instance
        RQBusinessContactSearch ws = f.createRQBusinessContactSearch();
        HeaderType header = JaxbPayloadFactory
                .createHeader("routing", "app", "module", messageId,
                        WebServiceConstants.MSG_TRANSPORT_MODE_SYNC, "REQUEST",
                        loginId);
        ws.setHeader(header);
        ws.setCriteriaData(criteria);

        // Send message directly via JMS
        MessageRouterHelper helper = new MessageRouterHelper();
        Object results = null;
        try {
            results = helper.routeSerialMessage(messageId, ws);
        } catch (Exception e) {
            throw new MessageRoutingException(e);
        }
        RSBusinessContactSearch response = null;
        if (results instanceof RSBusinessContactSearch) {
            response = (RSBusinessContactSearch) results;
        }
        return response;

        // // Send SOAP message
        // String msg = MessagingResourceFactory.getJaxbMessageBinder()
        // .marshalMessage(ws);
        // SoapClientWrapper client = new SoapClientWrapper();
        // SOAPMessage resp;
        // try {
        // resp = client.callSoap(msg);
        // } catch (MessageException e) {
        // msg =
        // "Error contacting SOAP server for web service, RQ_business_contact_search";
        // logger.error(msg);
        // throw new DataServiceException(msg, e);
        // }
        // try {
        // if (client.isSoapError(resp)) {
        // String errMsg = client.getSoapErrorMessage(resp);
        // msg =
        // "SOAP results was found to be an error for web service, RQ_business_contact_search.  Error message: "
        // + errMsg;
        // logger.error(msg);
        // throw new MessageException(msg);
        // }
        // RSBusinessContactSearch soapResp = (RSBusinessContactSearch) client
        // .getSoapResponsePayload();
        // return soapResp;
        // } catch (MessageException e) {
        // throw new DataServiceException(e);
        // }
        // // End send SOAP message
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.subsidiary.SubsidiaryDao#maintain(org.dto.SubsidiaryContactInfoDto
     * )
     */
    @Override
    public int maintain(SubsidiaryContactInfoDto contact)
            throws SubsidiaryDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Stub method.
     * 
     * @param businessId
     *            the business id of the subsidiary
     * @return Always return zero
     * @throws SubsidiaryDaoException
     */
    @Override
    public double calculateBalance(int businessId)
            throws SubsidiaryDaoException {
        return 0;
    }

    /**
     * Stub method
     * 
     * @param subXact
     *            an instnace of {@link SubsidiaryXactHistoryDto}
     * @return Always return zero
     * @throws SubsidiaryDaoException
     */
    @Override
    public int maintain(SubsidiaryXactHistoryDto subXact)
            throws SubsidiaryDaoException {
        return 0;
    }

}
