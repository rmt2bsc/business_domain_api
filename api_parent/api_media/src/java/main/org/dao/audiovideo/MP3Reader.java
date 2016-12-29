package org.dao.audiovideo;

import java.util.List;

/**
 * @author appdev
 * 
 */
public interface MP3Reader {

    /**
     * Get Artist name
     * 
     * @return String
     */
    String getArtist();

    /**
     * Get Album name
     * 
     * @return String
     */
    String getAlbum();

    /**
     * Get Track title
     * 
     * @return String
     */
    String getTrackTitle();

    /**
     * Get track number
     * 
     * @return int
     */
    int getTrack();

    /**
     * Get Album year
     * 
     * @return int
     */
    int getYear();

    /**
     * Get track duration
     * 
     * @return List of {@link Integer} values where the first element is the
     *         hours, the second is the minutes, and the third is the seconds.
     */
    List<Integer> getDuration();

    /**
     * Get the album genre
     * 
     * @return String
     */
    String getGenre();

    /**
     * Get the comments
     * 
     * @return String
     */
    String getComment();

    /**
     * Get composer
     * 
     * @return String
     */
    String getComposer();

    /**
     * Get lyricist
     * 
     * @return String
     */
    String getLyricist();

    /**
     * Get Producer
     * 
     * @return String
     */
    String getProducer();

    /**
     * Get disc number
     * 
     * @return int
     */
    int getDiscNumber();

    /**
     * Get total number of tracks
     * 
     * @return int
     */
    int getTrackCount();

}
