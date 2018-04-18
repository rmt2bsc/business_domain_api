package org.dto;

import com.api.foundation.TransactionDto;

/**
 * DTO Contract for keeping document information as it relates to audio, video
 * and imaging media.
 * 
 * @author rterrell
 * 
 */
public interface ContentDto extends TransactionDto {

    /**
     * Sets the document Id
     */
    void setContentId(int value);

    /**
     * Gets the document Id
     */
    int getContentId();

    /**
     * Sets the project Id
     */
    void setProjectId(int value);

    /**
     * Gets the project Id
     */
    int getProjectId();

    /**
     * Sets the mime Type Id
     */
    void setMimeTypeId(int value);

    /**
     * Gets the mime Type Id
     */
    int getMimeTypeId();

    /**
     * Sets the image Data
     */
    void setImageData(byte[] value);

    /**
     * Gets the image Data
     */
    byte[] getImageData();

    /**
     * Sets the text Data
     */
    void setTextData(String value);

    /**
     * Gets the text Data
     */
    String getTextData();

    /**
     * Sets the application Code
     */
    void setAppCode(String value);

    /**
     * Gets the application Code
     */
    String getAppCode();

    /**
     * Sets the module Code
     */
    void setModuleCode(String value);

    /**
     * Gets the module Code
     */
    String getModuleCode();

    /**
     * Sets the file path
     */
    void setFilepath(String value);

    /**
     * Gets the file path
     */
    String getFilepath();

    /**
     * Sets the file name
     */
    void setFilename(String value);

    /**
     * Gets the file name
     */
    String getFilename();

    /**
     * Sets the size
     */
    void setSize(int value);

    /**
     * Gets the size
     */
    int getSize();

}
