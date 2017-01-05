package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a personal contact.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell.
 */
public interface PersonalContactDto extends ContactDto {

    /**
     * Set the first name of the person.
     * 
     * @param value
     *            String
     */
    void setFirstname(String value);

    /**
     * Get the last name of the person.
     * 
     * @return String
     */
    String getFirstname();

    /**
     * Set the middle name of the person.
     * 
     * @param value
     *            String
     */
    void setMidname(String value);

    /**
     * Get the middle name of the person.
     * 
     * @return String
     */
    String getMidname();

    /**
     * Set the last name of the person.
     * 
     * @param value
     *            String
     */
    void setLastname(String value);

    /**
     * Get the last name of the person.
     * 
     * @return String
     */
    String getLastname();

    /**
     * Set the maiden name of the person.
     * 
     * @param value
     *            String
     */
    void setMaidenname(String value);

    /**
     * Get the maiden name of the person.
     * 
     * @return String
     */
    String getMaidenname();

    /**
     * Set generation.
     * 
     * @param value
     *            String
     */
    void setGeneration(String value);

    /**
     * Get generation.
     * 
     * @return String
     */
    String getGeneration();

    /**
     * Set the title of the person.
     * 
     * @param value
     *            int
     */
    void setTitle(int value);

    /**
     * Get the title of the person.
     * 
     * @return int
     */
    int getTitle();

    /**
     * Set the gender of the person.
     * 
     * @param value
     *            int
     */
    void setGenderId(int value);

    /**
     * Get the gender of the person.
     * 
     * @return int
     */
    int getGenderId();

    /**
     * Set the marital status of the person.
     * 
     * @param value
     *            int
     */
    void setMaritalStatusId(int value);

    /**
     * Get the marital status of the person.
     * 
     * @return int
     */
    int getMaritalStatusId();

    /**
     * Set the birth date of the person.
     * 
     * @param value
     *            {@link java.util.Date}
     */
    void setBirthDate(java.util.Date value);

    /**
     * Get the birth date of the person.
     * 
     * @return {@link java.util.Date}
     */
    java.util.Date getBirthDate();

    /**
     * Set the race of the person.
     * 
     * @param value
     *            int
     */
    void setRaceId(int value);

    /**
     * Get the race of the person.
     * 
     * @return int
     */
    int getRaceId();

    /**
     * Set the social security number.
     * 
     * @param value
     *            String
     */
    void setSsn(String value);

    /**
     * Get the social security number.
     * 
     * @return String
     */
    String getSsn();

    /**
     * Set the category id.
     * 
     * @param value
     *            int
     */
    void setCategoryId(int value);

    /**
     * Get the category id.
     * 
     * @return int
     */
    int getCategoryId();

}