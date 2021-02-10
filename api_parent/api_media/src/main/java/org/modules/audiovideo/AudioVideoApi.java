package org.modules.audiovideo;

import java.util.List;

import org.dao.entity.CommonMediaDto;
import org.dto.ArtistDto;
import org.dto.GenreDto;
import org.dto.MediaTypeDto;
import org.dto.ProjectDto;
import org.dto.ProjectTypeDto;
import org.dto.TracksDto;
import org.dto.VwArtistDto;

import com.api.foundation.TransactionApi;

/**
 * A contract for creating, modifying, deleting, and managing audio/video
 * information.
 * 
 * @author rterrell
 * 
 */
public interface AudioVideoApi extends TransactionApi {

    /**
     * Retrieve a list of genre types
     * 
     * @param criteria
     *            instance of {@link GenreDto}
     * @return List of {@link GenreDto} objects
     * @throws AudioVideoApiException
     */
    List<GenreDto> getGenre(GenreDto criteria) throws AudioVideoApiException;

    /**
     * Retrieve a list of media types
     * 
     * @param criteria
     *            instance of {@link MediaTypeDto}
     * @return List of {@link MediaTypeDto} objects
     * @throws AudioVideoApiException
     */
    List<MediaTypeDto> getMediaType(MediaTypeDto criteria) throws AudioVideoApiException;

    /**
     * Retrieve a list of project types
     * 
     * @param criteria
     *            instance of {@link ProjectTypeDto}
     * @return List of {@link ProjectTypeDto} objects
     * @throws AudioVideoApiException
     */
    List<ProjectTypeDto> getProjectType(ProjectTypeDto criteria) throws AudioVideoApiException;

    /**
     * Retrieve a list of common media data (artisit, project, and/or track
     * data) using a simple String value.
     * 
     * @param criteria
     *            String that could represent either the artist, project, or
     *            track.
     * @return List of {@link CommonMediaDto} objects
     * @throws AudioVideoApiException
     */
    List<CommonMediaDto> getCommonMedia(String criteria) throws AudioVideoApiException;

    /**
     * Retrieve list of artists based on selection criteria contained in
     * <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link ArtistDto} containing the selection
     *            criteria.
     * @return List of {@link ArtistDto} objects or null when no data is found.
     * @throws AudioVideoApiException
     */
    List<ArtistDto> getArtist(ArtistDto criteria) throws AudioVideoApiException;
    
    /**
     * Retrieve list of primary, non-primary, and video artists based on
     * selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link VwArtistDto} containing the selection
     *            criteria.
     * @return List of {@link VwArtistDto} objects or null when no data is
     *         found.
     * @throws AudioVideoApiException
     */
    List<VwArtistDto> getConsolidatedArtist(VwArtistDto criteria) throws AudioVideoApiException;

    /**
     * Apply modifications to an artists
     * 
     * @param artist
     *            an instance of {@link ArtistDto} containing the changes.
     * @return the number of rows effected by update.
     * @throws AudioVideoApiException
     */
    int updateArtist(ArtistDto artist) throws AudioVideoApiException;
    
    /**
     * Delete one or more artists.
     * 
     * @param criteria
     *            an instance of {@link ArtistDto} containing the selection
     *            criteria.
     * @return the number of rows effected by the delete operation.
     * @throws AudioVideoApiException
     */
    int deleteArtist(ArtistDto criteria) throws AudioVideoApiException;

    /**
     * Retrieve list of projects/albums based on selection criteria contained in
     * <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link ProjectDto} containing the selection
     *            criteria.
     * @return List of {@link ProjectDto} objects or null when no data is found.
     * @throws AudioVideoApiException
     */
    List<ProjectDto> getProject(ProjectDto criteria) throws AudioVideoApiException;
    
    /**
     * Apply modifications to an albumn or project
     * 
     * @param artist
     *            an instance of {@link ProjectDto} containing the changes.
     * @return the number of rows effected by update.
     * @throws AudioVideoApiException
     */
    int updateProject(ProjectDto project) throws AudioVideoApiException;
    
    /**
     * Delete one or more projects/albums.
     * 
     * @param criteria
     *            an instance of {@link ProjectDto} containing the selection
     *            criteria.
     * @return the number of rows effected by the delete operation.
     * @throws AudioVideoApiException
     */
    int deleteProject(ProjectDto criteria) throws AudioVideoApiException;
    
    /**
     * Retrieve list of tracks based on selection criteria contained in
     * <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link TracksDto} containing the selection
     *            criteria.
     * @return List of {@link TracksDto} objects or null when no data is found.
     * @throws AudioVideoApiException
     */
    List<TracksDto> getTracks(TracksDto criteria) throws AudioVideoApiException;
    
    /**
     * Apply modifications to a track.
     * 
     * @param artist
     *            an instance of {@link TracksDto} containing the changes.
     * @return the number of rows effected by update.
     * @throws AudioVideoApiException
     */
    int updateTrack(TracksDto track) throws AudioVideoApiException;
    
    /**
     * Delete one or more tracks.
     * 
     * @param criteria
     *            an instance of {@link TracksDto} containing the selection
     *            criteria.
     * @return the number of rows effected by the delete operation.
     * @throws AudioVideoApiException
     */
    int deleteTracks(TracksDto criteria) throws AudioVideoApiException;
}
