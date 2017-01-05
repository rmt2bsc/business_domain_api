package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a common contact.
 * <p>
 * The contact information managed is generic in terms of whether the contact is
 * a person or a business.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell.
 */
public interface ContactDto extends AddressDto {

    /**
     * Sets the value of the contact id.
     * <p>
     * This can be either the unique id of a personal or business contact.
     * 
     * @param value
     *            the contact id which is either personal or business id.
     */
    void setContactId(int value);

    /**
     * Returns the value of the contact id.
     * <p>
     * This can be either the unique id of a personal or business contact.
     * 
     * @return the contact id which is either personal or business id.
     * 
     */
    int getContactId();

    /**
     * Sets the Contact name.
     * 
     * @param value
     *            The contact's name. For personal contacts, the name will be
     *            first name and last name separated by a space. For business
     *            contacts, the business' long name is used.
     */
    void setContactName(String value);

    /**
     * Returns the Contact name.
     * 
     * @return The contact's name. For personal contacts, the name will be first
     *         name and last name separated by a space. For business contacts,
     *         the business' long name is used.
     */
    String getContactName();

    /**
     * Sets the contact's email.
     * 
     * @param value
     *            email address.
     */
    void setContactEmail(String value);

    /**
     * Returns the contact's email address.
     * 
     * @return email address.
     */
    String getContactEmail();

    /**
     * Sets the contact's type.
     * 
     * @param value
     *            equals either <i>per</i> or <i>bus</i> for Personal or
     *            Business, respectively.
     */
    void setContactType(String value);

    /**
     * Returns the contact's type.
     * 
     * @return equals either <i>per</i> or <i>bus</i> for Personal or Business,
     *         respectively.
     */
    String getContactType();

}