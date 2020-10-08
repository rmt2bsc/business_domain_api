package org.dto;

import com.api.foundation.TransactionDto;

/**
 * DTO Contract for maintaining primary, non-primary, and video artist
 * information as it relates to audio/video media.
 * 
 * @author rterrell
 * 
 */
public interface VwArtistDto extends TransactionDto {

    /**
     * Indicates whether are not this is a primary artist
     * 
     * @return boolean
     */
    boolean isPrimaryArtist();

    /**
     * Set the indicator for primary artist
     * 
     * @param value
     *            1=primary artist, 0=non-primary artist
     */
    void setPrimaryArtist(int value);

    /**
     * Get project type id
     * 
     * @return int
     */
    int getProjectTypeId();

    /**
     * Set project type id
     * 
     * @param value
     *            int
     */
    void setProjectTypeId(int value);

    /**
     * Get project type name
     * 
     * @return String
     */
    String getProjectTypeName();

    /**
     * Set project type name
     * 
     * @param value
     *            String
     */
    void setProjectTypeName(String value);

    /**
     * Return Artist Id
     * 
     * @return
     */
    int getArtistId();

    /**
     * Set Artist Id
     * 
     * @param value
     */
    void setArtistId(int value);

    /**
     * Rturn Artist name
     * 
     * @return
     */
    String getArtistName();

    /**
     * Set artitst name
     * 
     * @param value
     */
    void setArtistName(String value);

    /**
     * Return project Id
     * 
     * @return
     */
    int getProjectId();

    /**
     * Set project Id
     * 
     * @param value
     */
    void setProjectId(int value);

    /**
     * Rturn project name
     * 
     * @return
     */
    String getProjectName();

    /**
     * Set project name
     * 
     * @param value
     */
    void setProjectName(String value);

    /**
     * Rturn project comments
     * 
     * @return
     */
    String getProjectComments();

    /**
     * Set project comments
     * 
     * @param value
     */
    void setProjectComments(String value);

    /**
     * Return track Id
     * 
     * @return
     */
    int getTrackId();

    /**
     * Set track Id
     * 
     * @param value
     */
    void setTrackId(int value);

    /**
     * Rturn track name
     * 
     * @return
     */
    String getTrackName();

    /**
     * Set track name
     * 
     * @param value
     */
    void setTrackName(String value);

    /**
     * Rturn track comments
     * 
     * @return
     */
    String getTrackComments();

    /**
     * Set track comments
     * 
     * @param value
     */
    void setTrackComments(String value);
}
