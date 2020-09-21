package org.dao.entity;

import com.RMT2Base;
import com.SystemException;

/**
 * Common media information
 *
 * @author roy.terrell
 */
public class CommonMediaDto extends RMT2Base {

    private int resultType;
    private String resultTypeDescription;
    private int artistId;
    private String artistName;
    private int projectId;
    private String projectTitle;
    private String trackName;
    private int trackNumber;
    private int trackHours;
    private int trackMinutes;
    private int trackSeconds;

    /**
     * Default constructor.
     */
    public CommonMediaDto() throws SystemException {
        super();
    }

    /**
     * @return the resultType
     */
    public int getResultType() {
        return resultType;
    }

    /**
     * @param resultType
     *            the resultType to set
     */
    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    /**
     * @return the resultTypeDescription
     */
    public String getResultTypeDescription() {
        return resultTypeDescription;
    }

    /**
     * @param resultTypeDescription
     *            the resultTypeDescription to set
     */
    public void setResultTypeDescription(String resultTypeDescription) {
        this.resultTypeDescription = resultTypeDescription;
    }

    /**
     * @return the artistId
     */
    public int getArtistId() {
        return artistId;
    }

    /**
     * @param artistId
     *            the artistId to set
     */
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    /**
     * @return the artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artistName
     *            the artistName to set
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * @return the projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     *            the projectId to set
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the projectTitle
     */
    public String getProjectTitle() {
        return projectTitle;
    }

    /**
     * @param projectTitle
     *            the projectTitle to set
     */
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    /**
     * @return the trackName
     */
    public String getTrackName() {
        return trackName;
    }

    /**
     * @param trackName
     *            the trackName to set
     */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     * @return the trackNumber
     */
    public int getTrackNumber() {
        return trackNumber;
    }

    /**
     * @param trackNumber
     *            the trackNumber to set
     */
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * @return the trackHours
     */
    public int getTrackHours() {
        return trackHours;
    }

    /**
     * @param trackHours
     *            the trackHours to set
     */
    public void setTrackHours(int trackHours) {
        this.trackHours = trackHours;
    }

    /**
     * @return the trackMinutes
     */
    public int getTrackMinutes() {
        return trackMinutes;
    }

    /**
     * @param trackMinutes
     *            the trackMinutes to set
     */
    public void setTrackMinutes(int trackMinutes) {
        this.trackMinutes = trackMinutes;
    }

    /**
     * @return the trackSeconds
     */
    public int getTrackSeconds() {
        return trackSeconds;
    }

    /**
     * @param trackSeconds
     *            the trackSeconds to set
     */
    public void setTrackSeconds(int trackSeconds) {
        this.trackSeconds = trackSeconds;
    }

}