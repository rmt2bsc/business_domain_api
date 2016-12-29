package org.dto;

/**
 * DTO Contract for keeping MIME type information as it relates to the various
 * muti media types: audio, video and imaging media.
 * 
 * @author rterrell
 * 
 */
public interface MimeTypeDto {

    /**
     * Sets the mime Type Id
     */
    void setMimeTypeId(int value);

    /**
     * Gets the mime Type Id
     */
    int getMimeTypeId();

    /**
     * Sets the file Extention
     */
    void setFileExt(String value);

    /**
     * Gets the file Extention
     */
    String getFileExt();

    /**
     * Sets the media Type
     */
    void setMediaType(String value);

    /**
     * Gets the media Type
     */
    String getMediaType();
}
