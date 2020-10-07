package org.dto;

import com.api.foundation.TransactionDto;

/**
 * DTO Contract for keeping project track information as it relates to audio
 * video media.
 * 
 * @author rterrell
 * 
 */
public interface TracksDto extends TransactionDto {

    /**
     * Sets the track Id
     */
    void setTrackId(int value);

    /**
     * Gets the track Id
     */
    int getTrackId();

    /**
     * Sets the project Id
     */
    void setProjectId(int value);

    /**
     * Gets the project Id
     */
    int getProjectId();

    /**
     * Sets the genre Id
     */
    void setGenreId(int value);

    /**
     * Gets the genre Id
     */
    int getGenreId();

    /**
     * Sets the track Number
     */
    void setTrackNumber(int value);

    /**
     * Gets the track Number
     */
    int getTrackNumber();

    /**
     * Sets the track Title
     */
    void setTrackTitle(String value);

    /**
     * Gets the track Title
     */
    String getTrackTitle();

    /**
     * Sets the track Hours
     */
    void setTrackHours(int value);

    /**
     * Gets the track Hours
     */
    int getTrackHours();

    /**
     * Sets the track Minutes
     */
    void setTrackMinutes(int value);

    /**
     * Gets the track Minutes
     */
    int getTrackMinutes();

    /**
     * Sets the track Seconds
     */
    void setTrackSeconds(int value);

    /**
     * Gets the track Seconds
     */
    int getTrackSeconds();

    /**
     * Sets the track Disc
     */
    void setTrackDisc(String value);

    /**
     * Gets the track Disc
     */
    String getTrackDisc();

    /**
     * Sets the track Artist
     */
    void setTrackArtist(String value);

    /**
     * Gets the track Artist
     */
    String getTrackArtist();

    /**
     * Sets the track Producer
     */
    void setTrackProducer(String value);

    /**
     * Gets the track Producer
     */
    String getTrackProducer();

    /**
     * Sets the track Composer
     */
    void setTrackComposer(String value);

    /**
     * Gets the track Composer
     */
    String getTrackComposer();

    /**
     * Sets the track Lyricist
     */
    void setTrackLyricist(String value);

    /**
     * Gets the track Lyricist
     */
    String getTrackLyricist();

    /**
     * Sets the Server name
     */
    void setLocServername(String value);

    /**
     * Gets the Server name
     */
    String getLocServername();

    /**
     * Sets the Share name
     */
    void setLocSharename(String value);

    /**
     * Gets the Share name
     */
    String getLocSharename();

    /**
     * Sets the Root Path
     */
    void setLocRootPath(String value);

    /**
     * Gets the Root Path
     */
    String getLocRootPath();

    /**
     * Sets the Path
     */
    void setLocPath(String value);

    /**
     * Gets the Path
     */
    String getLocPath();

    /**
     * Sets the File name
     */
    void setLocFilename(String value);

    /**
     * Gets the File name
     */
    String getLocFilename();

    /**
     * Sets the comments
     */
    void setComments(String value);

    /**
     * Gets the comments
     */
    String getComments();
}
