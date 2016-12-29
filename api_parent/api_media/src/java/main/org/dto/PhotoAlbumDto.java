package org.dto;

import com.api.foundation.TransactionDto;

/**
 * DTO Contract for keeping photo album information as it relates to imaging
 * media.
 * 
 * @author rterrell
 * 
 */
public interface PhotoAlbumDto extends TransactionDto {

    /**
     * Sets the album Id
     */
    void setAlbumId(int value);

    /**
     * Gets the album Id
     */
    int getAlbumId();

    /**
     * Sets the album Name
     */
    void setAlbumName(String value);

    /**
     * Gets the album Name
     */
    String getAlbumName();

    /**
     * Sets the album Date
     */
    void setAlbumDate(java.util.Date value);

    /**
     * Gets the album Date
     */
    java.util.Date getAlbumDate();

    /**
     * Sets the location
     */
    void setLocation(String value);

    /**
     * Gets the location
     */
    String getLocation();

    /**
     * Sets the server name
     */
    void setServername(String value);

    /**
     * Gets the server name
     */
    String getServername();

    /**
     * Sets the share name
     */
    void setSharename(String value);

    /**
     * Gets the share name
     */
    String getSharename();
}
