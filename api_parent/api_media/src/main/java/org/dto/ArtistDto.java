package org.dto;

import com.api.foundation.TransactionDto;

/**
 * DTO Contract for keeping artist information as it relates to audio video
 * media.
 * 
 * @author rterrell
 * 
 */
public interface ArtistDto extends TransactionDto {

    /**
     * Return Artist Id
     * 
     * @return
     */
    int getId();

    /**
     * Set Artist Id
     * 
     * @param value
     */
    void setId(int value);

    /**
     * Rturn Artist name
     * 
     * @return
     */
    String getName();

    /**
     * Set artitst name
     * 
     * @param value
     */
    void setName(String value);

}
