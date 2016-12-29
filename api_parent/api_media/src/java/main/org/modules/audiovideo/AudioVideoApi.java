package org.modules.audiovideo;

import java.util.List;

import org.dto.ArtistDto;

import com.api.foundation.TransactionApi;

/**
 * A contract for managing audio/video information.
 * 
 * @author rterrell
 * 
 */
public interface AudioVideoApi extends TransactionApi {

    ArtistDto getArtist(int artistId) throws AudioVideoApiException;

    List<ArtistDto> getArtist(ArtistDto criteria) throws AudioVideoApiException;

}
