package org.dao.audiovideo;

import java.util.List;

import org.dao.entity.CommonMediaDto;
import org.dto.ArtistDto;
import org.dto.GenreDto;
import org.dto.MediaTypeDto;
import org.dto.ProjectDto;
import org.dto.ProjectTypeDto;
import org.dto.TracksDto;

import com.api.persistence.DaoClient;

/**
 * DAO contract for accessing an audio/video external datastore which functions
 * to create, update, delete, and query detailed audio and video information.
 * 
 * @author Roy Terrell
 * 
 */
public interface AudioVideoDao extends DaoClient {

    /**
     * Fetches common media information (artisit, project, and/or track data)
     * using a simple String value.
     * 
     * @param criteria
     *            String that could represent either the artist, project, or
     *            track.
     * @return List of {@link CommonMediaDto} objects
     * @throws AudioVideoDaoException
     */
    List<CommonMediaDto> fetchCommonMedia(String criteria) throws AudioVideoDaoException;

    /**
     * Fetches artist information based on the selection criteria data elements
     * containted in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link ArtistDto} containing values for
     *            selection criteria.
     * @return a List of {@link ArtistDto} objects or null if no data was found.
     * @throws AudioVideoDaoException
     */
    List<ArtistDto> fetchArtist(ArtistDto criteria) throws AudioVideoDaoException;

    /**
     * Fetches project information based on the selection criteria data elements
     * containted in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link ProjectDto} containing values for
     *            selection criteria.
     * @return a List of {@link ProjectDto} objects or null if no data was
     *         found.
     * @throws AudioVideoDaoException
     */
    List<ProjectDto> fetchProject(ProjectDto criteria) throws AudioVideoDaoException;

    /**
     * Fetches track information based on the selection criteria data elements
     * containted in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link TracksDto} containing values for
     *            selection criteria.
     * @return a List of {@link TracksDto} objects or null if no data was found.
     * @throws AudioVideoDaoException
     */
    List<TracksDto> fetchTrack(TracksDto criteria) throws AudioVideoDaoException;

    /**
     * Fetches genre information based on the selection criteria data elements
     * containted in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link GenreDto} containing values for
     *            selection criteria.
     * @return a List of {@link GenreDto} objects or null if no data was found.
     * @throws AudioVideoDaoException
     */
    List<GenreDto> fetchGenre(GenreDto criteria) throws AudioVideoDaoException;

    /**
     * Fetches project type information based on the selection criteria data
     * elements containted in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link ProjectTypeDto} containing values for
     *            selection criteria.
     * @return a List of {@link ProjectTypeDto} objects or null if no data was
     *         found.
     * @throws AudioVideoDaoException
     */
    List<ProjectTypeDto> fetchProjectType(ProjectTypeDto criteria) throws AudioVideoDaoException;

    /**
     * Fetches media type information based on the selection criteria data
     * elements containted in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link MediaTypeDto} containing values for
     *            selection criteria.
     * @return a List of {@link MediaTypeDto} objects or null if no data was
     *         found.
     * @throws AudioVideoDaoException
     */
    List<MediaTypeDto> fetchMediaType(MediaTypeDto criteria) throws AudioVideoDaoException;

    /**
     * Creates a new or modifies an existing artist object.
     * 
     * @param artist
     *            an instance of {@link ArtistDto}
     * @return an int value representing either the unique identifier of the
     *         artist inserted, or the total number of rows effected by the
     *         artist update operation.
     * @throws AudioVideoDaoException
     */
    int maintainArtist(ArtistDto artist) throws AudioVideoDaoException;

    /**
     * Creates a new or modifies an existing project object.
     * 
     * @param proj
     *            an instance of {@link ProjectDto}
     * @return an int value representing either the unique identifier of the
     *         project inserted, or the total number of rows effected by the
     *         project update operation.
     * @throws AudioVideoDaoException
     */
    int maintainProject(ProjectDto proj) throws AudioVideoDaoException;

    /**
     * Creates a new or modifies an existing track object.
     * 
     * @param track
     *            an instance of {@link TracksDto}
     * @return an int value representing either the unique identifier of the
     *         track inserted, or the total number of rows effected by the track
     *         update operation.
     * @throws AudioVideoDaoException
     */
    int maintainTrack(TracksDto track) throws AudioVideoDaoException;

    /**
     * Removes all entries from the audio_video and audio_video_tracks tables
     * where streaming files exist on the server.
     * 
     * @param projectTypeId
     * @return int
     * @throws AudioVideoDaoException
     */
    int purge(int projectTypeId) throws AudioVideoDaoException;
}
