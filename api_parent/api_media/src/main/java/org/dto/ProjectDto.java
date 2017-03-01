package org.dto;

import com.api.foundation.TransactionDto;

/**
 * DTO Contract for keeping project information as it relates to audio video
 * media.
 * 
 * @author rterrell
 * 
 */
public interface ProjectDto extends TransactionDto {

    /**
     * Get project id.
     * 
     * @return int
     */
    int getProjectId();

    /**
     * Set Project id.
     * 
     * @param projId
     *            int
     */
    void setProjectId(int projId);

    /**
     * Get artist id.
     * 
     * @return int
     */
    int getArtistId();

    /**
     * Set artist id.
     * 
     * @param artistId
     *            int
     */
    void setArtistId(int artistId);

    /**
     * Get project type id
     * 
     * @return int
     */
    int getProjectTypeId();

    /**
     * Set project type id.
     * 
     * @param projTypeId
     *            int
     */
    void setProjectTypeId(int projTypeId);

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
     * Get the genre name
     * 
     * @return String
     */
    String getGenreName();

    /**
     * Set genre name
     * 
     * @param name
     *            String
     */
    void setGenreName(String name);

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
     * Get project title
     * 
     * @return String
     */
    String getTitle();

    /**
     * Set project title.
     * 
     * @param title
     *            String
     */
    void setTitle(String title);

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
     * Get comments
     * 
     * @return
     */
    String getComments();

    /**
     * Set comments
     * 
     * @param artWorkPath
     */
    void setComments(String comments);

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
}
