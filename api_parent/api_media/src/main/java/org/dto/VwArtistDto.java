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
     * Get genre id.
     * 
     * @return int
     */
    int getGenreId();

    /**
     * Set genre id.
     * 
     * @param genreId
     *            int
     */
    void setGenreId(int genreId);

    /**
     * Get media type id.
     * 
     * @return int
     */
    int getMediaTypeId();

    /**
     * Set media type id
     * 
     * @param mediaTypeId
     *            int
     */
    void setMediaTypeId(int mediaTypeId);

    /**
     * Get project year
     * 
     * @return int
     */
    int getYear();

    /**
     * Set project year
     * 
     * @param year
     *            int
     */
    void setYear(int year);

    /**
     * Get master duplication id.
     * 
     * @return int
     */
    int getMasterDupId();

    /**
     * Set Master duplication id
     * 
     * @param value
     *            int
     */
    void setMasterDupId(int value);

    /**
     * Get ripped indicator
     * 
     * @return int
     */
    int getRippedInd();

    /**
     * Set ripped indicator
     * 
     * @param flag
     *            int
     */
    void setRippedInd(int flag);

    /**
     * Get project cost
     * 
     * @return double
     */
    double getCost();

    /**
     * Set project cost
     * 
     * @param cost
     *            double
     */
    void setCost(double cost);

    /**
     * Get document details id.
     * 
     * @return int
     */
    int getContentId();

    /**
     * Set document details id.
     * 
     * @param contentId
     *            int
     */
    void setContentId(int contentId);

    /**
     * get content path
     * 
     * @return
     */
    String getContentPath();

    /**
     * set content path
     * 
     * @param contentPath
     */
    void setContentPath(String contentPath);

    /**
     * get art work image path
     * 
     * @return
     */
    String getArtWorkPath();

    /**
     * Set art work image path
     * 
     * @param artWorkPath
     */
    void setArtWorkPath(String artWorkPath);

    /**
     * Get art work image filename
     * 
     * @return
     */
    String getArtWorkFilename();

    /**
     * Set art work image filename
     * 
     * @param artWorkFilename
     */
    void setArtWorkFilename(String artWorkFilename);

    /**
     * Get content filename
     * 
     * @return
     */
    String getContentFilename();

    /**
     * Set the filename of content
     * 
     * @param contentPath
     */
    void setContentFilename(String contentFilename);

    /**
     * Get total time in minutes.
     * 
     * @return int
     */
    int getTotalTime();

    /**
     * Set total time in minutes.
     * 
     * @param contentId
     *            int
     */
    void setTotalTime(int totalTime);

    /**
     * Get producer
     * 
     * @return
     */
    String getProducer();

    /**
     * Set producer
     * 
     * @param producer
     */
    void setProducer(String producer);

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
     * Get track disc number
     * 
     * @param value
     */
    String getTrackDiscNumber();

    /**
     * Set track disc number
     * 
     * @param value
     */
    void setTrackDiscNumber(String value);

    /**
     * Get track number
     * 
     * @param value
     */
    int getTrackNumber();

    /**
     * Set track number
     * 
     * @param value
     */
    void setTrackNumber(int value);

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

    /**
     * Get track hours
     * 
     * @param value
     */
    int getTrackHours();

    /**
     * Set track hours
     * 
     * @param value
     */
    void setTrackHours(int value);

    /**
     * Get track minutes
     * 
     * @param value
     */
    int getTrackMinutes();

    /**
     * Set track minutes
     * 
     * @param value
     */
    void setTrackMinutes(int value);

    /**
     * Get track seconds
     * 
     * @param value
     */
    int getTrackSeconds();

    /**
     * Set track seconds
     * 
     * @param value
     */
    void setTrackSeconds(int value);
}
