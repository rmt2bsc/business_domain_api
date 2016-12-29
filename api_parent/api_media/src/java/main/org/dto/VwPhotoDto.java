package org.dto;

/**
 * DTO Contract for keeping extended photo image information as it relates to
 * imaging media.
 * 
 * @author rterrell
 * 
 */
public interface VwPhotoDto extends PhotoImageDto {

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
     * Sets the event Name
     */
    void setEventName(String value);

    /**
     * Gets the event Name
     */
    String getEventName();

    /**
     * Sets the mime Type File Ext
     */
    void setMimeTypeFileExt(String value);

    /**
     * Gets the mime Type File Ext
     */
    String getMimeTypeFileExt();

    /**
     * Sets the media Type
     */
    void setMediaType(String value);

    /**
     * Gets the media Type
     */
    String getMediaType();

}
