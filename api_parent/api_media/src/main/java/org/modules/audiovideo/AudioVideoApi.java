package org.modules.audiovideo;

import java.util.List;

import org.dto.ArtistDto;
import org.dto.ProjectDto;
import org.dto.TracksDto;

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
