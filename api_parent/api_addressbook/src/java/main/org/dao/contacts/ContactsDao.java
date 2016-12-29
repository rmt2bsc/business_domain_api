package org.dao.contacts;

import java.util.List;

import org.dto.BusinessContactDto;
import org.dto.ContactDto;

import com.api.persistence.DaoClient;

/**
 * Contract for accessing and managing the contacts module of the Address Book
 * API.
 * <p>
 * A contact entity can be categorized as either <i>personal</i> or
 * <i>business</i>.
 * 
 * @author rterrell
 * 
 */
public interface ContactsDao extends DaoClient {

    /**
     * Retrieves all common contacts from an external data source.
     * <p>
     * The list of contacts returned can be a mixture of both personal and
     * business types. When the results are received, evaluate the property,
     * contact_type, of each element of the list in order to verify the type of
     * contact being handled.
     * 
     * @return a List of {@link ContactDto} objects or null if no data is found.
     * @throws ContactDaoException
     */
    List<ContactDto> fetchContact() throws ContactDaoException;

    /**
     * Retireves a common contact based on its internal unique id.
     * <p>
     * The contact contact could be personal or business. When the results are
     * received, evaluate the property, contact_type, of the results in order to
     * verify the type of contact being handled.
     * 
     * @param contactId
     *            an int value that uniquely identifies the contact to retrieve.
     * @return an instance of {@link ContactDto}
     * @throws ContactDaoException
     */
    ContactDto fetchContact(int contactId) throws ContactDaoException;

    /**
     * Retrieves one or more business contacts using a List of business id's as
     * selection criteria.
     * 
     * @param businessId
     *            a List of String objects which each element is a business
     *            id's.
     * @return a List of {@link BusinessContactDto} objects or null when no data
     *         is found.
     * @throws ContactDaoException
     */
    List<BusinessContactDto> fetchBusinessContact(List<String> businessId)
            throws ContactDaoException;

    /**
     * Rettieves a list of contacts based on the selection criteria contained in
     * <i>criteria</i>.
     * <p>
     * The implementation of the this method should take into account the
     * runtime type of the <i>criteria</i> before executing the query. If the
     * runtime type of <i>criteria</i> is <i>BusinessContactDto</i> or
     * <i>PersonalContactDto</i>, then the selection criteria constructed and
     * the query executed should be appropriate to that runtime type. Otherwise,
     * the criteria and query will conform the common contact approach.
     * 
     * @param criteria
     *            an instance of {@link ContactDto} or one of its descendents.
     * @return a List of {@link ContactDto} objects or null if no data is found.
     * @throws ContactDaoException
     */
    List<ContactDto> fetchContact(ContactDto criteria)
            throws ContactDaoException;

    /**
     * Creates a new or modifies an existing contact object.
     * <p>
     * The implementation of the this method should take into account the
     * runtime type of the <i>contact</i> before executing the update. When the
     * runtime type of <i>contact</i> is <i>BusinessContactDto</i> or
     * <i>PersonalContactDto</i>, the update logic should be appropriate to that
     * runtime type.
     * 
     * @param contact
     *            an instance of {@link ContactDto}
     * @return an int value representing either the unique identifier of the
     *         contact inserted, or the total number of rows effected by the
     *         contact update operation.
     * @throws ContactUpdateDaoException
     */
    int maintainContact(ContactDto contact) throws ContactUpdateDaoException;

    /**
     * Deletes contact object..
     * <p>
     * The implmentation of the this method should take into account the runtime
     * type of the <i>contacte</i> before executing the delete. When the runtime
     * type of <i>contact</i> is <i>BusinessContactDto</i> or
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
     * @throws ContactUpdateDaoException
     */
    int deleteContact(ContactDto contact) throws ContactUpdateDaoException;
}
