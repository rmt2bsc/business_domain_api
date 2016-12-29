package org.dao.subsidiary;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dao.AccountingDaoImpl;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;

import com.api.messaging.webservice.router.MessageRouterHelper;
import com.bindings.xml.jaxb.BusinessContactCriteria;
import com.bindings.xml.jaxb.BusinessType;
import com.bindings.xml.jaxb.HeaderType;
import com.bindings.xml.jaxb.ObjectFactory;
import com.bindings.xml.jaxb.RQBusinessContactSearch;
import com.bindings.xml.jaxb.RSBusinessContactSearch;
import com.services.DataServiceException;
import com.services.JaxbPayloadFactory;
import com.services.contacts.ContactDataFactory;

/**
 * Queries, updates, deletes, and creates general contact information for a
 * subsidiary.
 * <p>
 * Implements the interface, {@link SubsidiaryDao}, in order to serve requests
 * for common business contact data as it pertains to any given subsidiary via
 * web services.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2SubsidiaryContactDaoImpl extends AccountingDaoImpl implements
        SubsidiaryDao {

    private static Logger logger = Logger
            .getLogger(Rmt2SubsidiaryContactDaoImpl.class);

    /**
     * Default constructor for creating a Rmt2SubsidiaryContactDaoImpl object
     */
    public Rmt2SubsidiaryContactDaoImpl() {
        super();
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.subsidiary.SubsidiaryDao#fetch(java.lang.String)
     */
    @Override
    public List fetch(String criteria) throws SubsidiaryDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.subsidiary.SubsidiaryDao#fetch(org.dto.SubsidiaryContactInfoDto)
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
        ContactDataFactory cdf = new ContactDataFactory();
        List<BusinessType> busTypelist = null;
        List<SubsidiaryContactInfoDto> dtoList = null;
        try {
            // Send message to broker
            busTypelist = this.getBusinessContactData(wsCriteria,
                    this.getDaoUser());
        } catch (Exception e) {
            msg = "Problem occurred requesting common contact data via SOAP service";
            logger.error(msg);
            throw new SubsidiaryDaoException(msg, e);
        }
        // Decode the results of the service call.
        dtoList = Rmt2SubsidiaryDtoFactory.createBusinessContact(busTypelist);
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
            BusinessContactCriteria criteria, String loginId)
            throws DataServiceException {
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
     * @throws MessageException
     */
    private RSBusinessContactSearch getContactData(
            BusinessContactCriteria criteria, String loginId)
            throws DataServiceException {
        String messageId = "RQ_business_contact_search";
        ObjectFactory f = new ObjectFactory();
        // Create Payload instance
        RQBusinessContactSearch ws = f.createRQBusinessContactSearch();
        HeaderType header = JaxbPayloadFactory.createHeader(messageId, "SYNC",
                "REQUEST", loginId);
        ws.setHeader(header);
        ws.setCriteriaData(criteria);

        // Send message directly via JMS
        MessageRouterHelper helper = new MessageRouterHelper();
        Object results = null;
        try {
            results = helper.routeSerialMessage(messageId, ws);
        } catch (Exception e) {
            throw new DataServiceException(e);
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

}
