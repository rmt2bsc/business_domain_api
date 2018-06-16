package org.dto;

/**
 * Contract for managing lookup information for the audio/video system where the
 * following properties common to all structures: unique id and description.
 * 
 * @author rterrell
 * 
 */
public interface MediaLookupInfoDto {
    /**
     * Returns the internal unique id of the media lookup entity.
     * 
     * @return an int value representing the internal unique identifier
     */
    int getUid();

    /**
     * Sets the internal unique identifier of the media lookup entity.
     * 
     * @param uid
     *            the internal unique identifier to set.
     */
    void setUid(int uid);

    /**
     * Returns the name or description of the media lookup entity.
     * 
     * @return String
     */
    String getDescritpion();

    /**
     * Sets the name or description of the media lookup entity.
     * 
     * @param descr
     *            String
     */
    void setDescription(String descr);
}
