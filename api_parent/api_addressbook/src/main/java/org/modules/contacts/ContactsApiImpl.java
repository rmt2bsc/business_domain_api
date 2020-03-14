package org.modules.contacts;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.contacts.ContactDaoException;
import org.dao.contacts.ContactsDao;
import org.dao.contacts.ContactsDaoFactory;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;

import com.InvalidDataException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.util.RMT2Money;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * An implementation of {@link ContactsApi} for managing Contacts module
 * transactions.
 * 
 * @author rterrell
 * 
 */
class ContactsApiImpl extends AbstractTransactionApiImpl implements ContactsApi {

    private static final Logger logger = Logger.getLogger(ContactsApiImpl.class);

    private ContactsDaoFactory factory;
    private ContactsDao dao;

    private String appName;

    /**
     * Creates a ContactsApiImpl object in which the configuration is identified
     * by the name of a given application.
     * 
     * @param appName
     */
    protected ContactsApiImpl(String appName) {
        super();
        this.factory = new ContactsDaoFactory();
        this.appName = appName;
        dao = this.factory.createRmt2OrmDao(appName);
        this.setSharedDao(dao);
        dao.setDaoUser(this.apiUser);
        logger.info("logger initialized");
    }

    /**
     * Obtains a list of all contact objects common to both personal and
     * business types.
     * <p>
     * The list of contacts returned can be a mixture of both personal and
     * business types. When the results are received, evaluate the property,
     * contact_type, of each element of the list in order to verify the type of
     * contact being handled.
     * 
     * @return a List of {@link ContactDto} objects or null if no data is found.
     * @throws ContactsApiException
     *             general data access errors
     */
    @Override
    public List<ContactDto> getContact() throws ContactsApiException {
        try {
            List<ContactDto> results = dao.fetchContact();
            this.msg = "Successfully retrieved " + (results == null ? 0 : results.size()) + " generic contacts";
            logger.info(this.msg);
            return results;
        } catch (ContactDaoException e) {
            this.msg = "Error retrieving list of generic contacts";
            logger.error(this.msg, e);
            throw new ContactsApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Obtains a contact object which could be of type personal or business
     * based on its internal unique id.
     * <p>
     * The contact contact could be personal or business. When the results are
     * received, evaluate the property, contact_type, of the results in order to
     * verify the type of contact being handled.
     * 
     * @param contactId
     *            an int value that uniquely identifies the contact to retrieve.
     * @return an instance of {@link ContactDto}
     * @throws ContactsApiException
     *             general data access error
     */
    @Override
    public ContactDto getontact(int contactId) throws ContactsApiException {
        try {
            ContactDto results = dao.fetchContact(contactId);
            this.msg = "Successfully retrieved contact details for contact, " + contactId;
            logger.info(this.msg);
            return results;
        } catch (ContactDaoException e) {
            this.msg = "Error retrieving generic contact by contact id, " + contactId;
            logger.error(this.msg, e);
            throw new ContactsApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Obtains one or more business contact objects using a List of business
     * id's as selection criteria.
     * <p>
     * As a requirement, <i>busIdList</i> cannot be null and every element in
     * the list must be numeric.
     * 
     * @param busIdList
     *            a List of String objects which each element is a business
     *            id's.
     * @return a List of {@link BusinessContactDto} objects or null when no data
     *         is found.
     * @throws ContactsApiException
     *             <i>busIdList</i> is null or is empty; one or more business
     *             id's in <i>busIdList</i> are non numeric
     */
    @Override
    public List<BusinessContactDto> getBusinessContact(List<String> busIdList) throws ContactsApiException {
        if (busIdList == null || busIdList.size() <= 0) {
            this.msg = "A list of business id's are required";
            logger.error(this.msg);
            throw new ContactsApiException(this.msg);
        }

        // verify that all id's are numeric. If requirement is not met, abort
        // the method
        for (String item : busIdList) {
            if (!RMT2Money.isNumeric(item)) {
                this.msg = "Error obtaining generic contacts due to non-numeric business id, " + item;
                logger.error(this.msg);
                throw new ContactsApiException(this.msg);
            }
        }

        try {
            List<BusinessContactDto> results = dao.fetchBusinessContact(busIdList);
            this.msg = "Successfully retrieved list of contacts by one or more business id's";
            logger.info(this.msg);
            return results;
        } catch (ContactDaoException e) {
            this.msg = "Error retrieving list of business contacts";
            logger.error(this.msg, e);
            throw new ContactsApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Obtains one or more contact objects based on the selection criteria
     * contained in <i>criteria</i>.
     * <p>
     * This implementation takes evaluates the runtime type of the
     * <i>criteria</i> before executing the query. If the runtime type of
     * <i>criteria</i> is <i>BusinessContactDto</i> or
     * <i>PersonalContactDto</i>, then the selection criteria constructed and
     * the query executed should be appropriate to that runtime type. Otherwise,
     * the criteria and query will conform the common contact approach.
     * 
     * @param criteria
     *            an instance of {@link ContactDto} or one of its descendents.
     * @return a List of {@link ContactDto} objects or null if no data is found.
     * @throws ContactsApiException
     *             general data access error
     */
    @Override
    public List<ContactDto> getContact(ContactDto criteria) throws ContactsApiException {
        try {
            List<ContactDto> results = dao.fetchContact(criteria);
            this.msg = "Successfully retrieved list of contacts using DTO as criteria";
            logger.info(this.msg);
            return results;
        } catch (Exception e) {
            this.msg = "Error retrieving list of contacts using DTO as criteria";
            logger.error(this.msg, e);
            throw new ContactsApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Creates a new or modifies an existing contact object.
     * <p>
     * This implementation evaluates the runtime type of the <i>contact</i>
     * before executing the update. When the runtime type of <i>contact</i> is
     * <i>BusinessContactDto</i> or <i>PersonalContactDto</i>, the update logic
     * should be appropriate to that runtime type.
     * 
     * @param contact
     *            an instance of {@link ContactDto}
     * @return an int value representing either the unique identifier of the
     *         contact inserted, or the total number of rows effected by the
     *         contact update operation.
     * @throws ContactsApiException
     *             general data access error
     */
    @Override
    public int updateContact(ContactDto contact) throws ContactsApiException {
        try {
            Verifier.verifyNotNull(contact);
        }
        catch (VerifyException e) {
            this.msg = "A Contact data object is required as an input parameter for update operation";
            throw new InvalidDataException(this.msg);
        }
        try {
            int rc = dao.maintainContact(contact);
            this.msg = "Update was successfully performed for contact, " + contact.getContactId();
            logger.info(this.msg);
            return rc;
        } catch (ContactDaoException e) {
            this.msg = "Error occurred updating contact, " + contact.getContactName();
            logger.error(this.msg, e);
            throw new ContactsApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Deletes contact object.
     * <p>
     * This implmentation evaluates the runtime type of the <i>contacte</i>
     * before executing the delete operation. When the runtime type of
     * <i>contact</i> is* <i>BusinessContactDto</i> or
     * <i>PersonalContactDto</i>, the delete logic should be appropriate to that
     * runtime type.
     * 
     * @param contact
     *            an instance of {@link ContactDto}
     * @return The total number of entities effected by the delete operation
     *         which is the contact and its associated addresses. For instance,
     *         when the total rows effected equals 3, this means that one
     *         contact and two contact addresses were deleted.
     * 
     * @throws ContactsApiException
     *             general data access error
     * @throws InvalidDataException <i>contact</i> is null or <i>contact.contactId</i> is not greater than zero
     */
    @Override
    public int deleteContact(ContactDto contact) throws ContactsApiException {
        try {
            Verifier.verifyNotNull(contact);
        }
        catch (VerifyException e) {
            this.msg = "A Contact crtieria instance is required as an input parameter when deleting a contact";
            throw new InvalidDataException(this.msg);
        }
        
        try {
           Verifier.verifyPositive(contact.getContactId());
        }
        catch (VerifyException e) {
            this.msg = "A valid Contact Id is required when deleting a contact from the database";
            throw new InvalidDataException(this.msg);
        }
        
        try {
            int rc = dao.deleteContact(contact);
            this.msg = "Delete was successfully performed for contact, " + contact.getContactId();
            logger.info(this.msg);
            return rc;
        } catch (ContactDaoException e) {
            this.msg = "Error occurred deleting contact, " + contact.getContactName();
            logger.error(this.msg, e);
            throw new ContactsApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }
}
