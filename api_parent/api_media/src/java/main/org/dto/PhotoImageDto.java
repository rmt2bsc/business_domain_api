package org.dto;

import com.api.foundation.TransactionDto;

/**
 * DTO Contract for keeping photo image information as it relates to imaging
 * media.
 * 
 * @author rterrell
 * 
 */
public interface PhotoImageDto extends TransactionDto {

    /**
     * Sets the image Id
     */
    void setImageId(int value);

    /**
     * Gets the image Id
     */
    int getImageId();

    /**
     * Sets the event Id
     */
    void setEventId(int value);

    /**
     * Gets the event Id
     */
    int getEventId();

    /**
     * Sets the mime Type Id
     */
    void setMimeTypeId(int value);

    /**
     * Gets the mime Type Id
     */
    int getMimeTypeId();

    /**
     * Sets the dir Path
     */
    void setDirPath(String value);

    /**
     * Gets the dir Path
     */
    String getDirPath();

    /**
     * Sets the file Name
     */
    void setFileName(String value);

    /**
     * Gets the file Name
     */
    String getFileName();

    /**
     * Sets the file Size
     */
    void setFileSize(int value);

    /**
     * Gets the file Size
     */
    int getFileSize();

    /**
     * Sets the file Ext
     */
    void setFileExt(String value);

    /**
     * Gets the file Ext
     */
    String getFileExt();
}
