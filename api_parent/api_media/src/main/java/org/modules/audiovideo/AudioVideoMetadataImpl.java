package org.modules.audiovideo;

import java.util.List;

import org.dao.audiovideo.AudioVideoDao;
import org.dao.audiovideo.AudioVideoDaoException;
import org.dao.audiovideo.AudioVideoDaoFactory;
import org.dto.ArtistDto;
import org.dto.ProjectDto;
import org.dto.TracksDto;
import org.modules.MediaConstants;

import com.InvalidDataException;
import com.RMT2Constants;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * API for managing the meta data of audio/video media.
 * <p>
 * Artist, project/album, and track information can be created, maintained and
 * removed
 * 
 * @author roy.terrell
 *
 */
public class AudioVideoMetadataImpl extends AbstractTransactionApiImpl implements AudioVideoApi {
    
    private AudioVideoDaoFactory daoFact;
    private AudioVideoDao dao;

    /**
     * Create a AudioVideoMetadataImpl using the default application name,
     * {@link MediaConstants#APP_NAME}
     */
    AudioVideoMetadataImpl() {
        super(MediaConstants.APP_NAME);
        this.dao = this.daoFact.createRmt2OrmDaoInstance(MediaConstants.APP_NAME);
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Create a AudioVideoMetadataImpl using the specified application name.
     * 
     * @param appName
     *            the application name
     */
    AudioVideoMetadataImpl(String appName) {
        super(appName);
        this.dao = this.daoFact.createRmt2OrmDaoInstance(appName);
        this.setSharedDao(this.dao);
    }

    /**
     * Create a AudioVideoMetadataImpl using DaoClient instance that is intended
     * to be shared by another related application.
     * 
     * @param dao
     *            instance of {@link DaoClient}
     */
    AudioVideoMetadataImpl(DaoClient dao) {
        super(dao);
        this.dao = this.daoFact.createRmt2OrmDaoInstance(this.getSharedDao());
    }

    @Override
    public void init() {
        super.init();
        this.daoFact = new AudioVideoDaoFactory();
    }

    
    /* (non-Javadoc)
     * @see org.modules.audiovideo.AudioVideoApi#getArtist(org.dto.ArtistDto)
     */
    @Override
    public List<ArtistDto> getArtist(ArtistDto criteria) throws AudioVideoApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Artist criteria object is required", e);
        }
        
        List<ArtistDto> results = null;
        try {
            results = this.dao.fetchArtist(criteria);
            return results;
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Audio/Video DAO error: Unable to retrieve artist(s)", e);
        }
    }

    /* (non-Javadoc)
     * @see org.modules.audiovideo.AudioVideoApi#updateArtist(org.dto.ArtistDto)
     */
    @Override
    public int updateArtist(ArtistDto artist) throws AudioVideoApiException {
        this.validate(artist);
        try {
            int rc = this.dao.maintainArtist(artist);
            return rc;    
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Audio/Video DAO error: Unable to create/modify artist", e);
        }
    }

    /**
     * Not supported
     */
    @Override
    public int deleteArtist(ArtistDto criteria) throws AudioVideoApiException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /* (non-Javadoc)
     * @see org.modules.audiovideo.AudioVideoApi#getProject(org.dto.ProjectDto)
     */
    @Override
    public List<ProjectDto> getProject(ProjectDto criteria) throws AudioVideoApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project/Album criteria object is required", e);
        }
        
        List<ProjectDto> results = null;
        try {
            results = this.dao.fetchProject(criteria);
            return results;
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Audio/Video DAO error: Unable to retrieve project/album(s)", e);
        }
    }

    /* (non-Javadoc)
     * @see org.modules.audiovideo.AudioVideoApi#updateProject(org.dto.ProjectDto)
     */
    @Override
    public int updateProject(ProjectDto project) throws AudioVideoApiException {
        this.validate(project);
        try {
            int rc = this.dao.maintainProject(project);
            return rc;    
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Audio/Video DAO error: Unable to create/modify project/album", e);
        }
    }

    /**
     * Not supported
     */
    @Override
    public int deleteProject(ProjectDto criteria) throws AudioVideoApiException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /* (non-Javadoc)
     * @see org.modules.audiovideo.AudioVideoApi#getTracks(org.dto.TracksDto)
     */
    @Override
    public List<TracksDto> getTracks(TracksDto criteria) throws AudioVideoApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Track criteria object is required", e);
        }
        
        List<TracksDto> results = null;
        try {
            results = this.dao.fetchTrack(criteria);
            return results;
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Audio/Video DAO error: Unable to retrieve track(s)", e);
        }
    }

    /* (non-Javadoc)
     * @see org.modules.audiovideo.AudioVideoApi#updateTrack(org.dto.TracksDto)
     */
    @Override
    public int updateTrack(TracksDto track) throws AudioVideoApiException {
        this.validate(track);
        try {
            int rc = this.dao.maintainTrack(track);
            return rc;    
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Audio/Video DAO error: Unable to create/modify track", e);
        }
    }

    /**
     * Not supported
     */
    @Override
    public int deleteTracks(TracksDto criteria) throws AudioVideoApiException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
    
    private void validate(ArtistDto artist) {
        try {
            Verifier.verifyNotNull(artist);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Artist criteria object is required", e);
        }
        
        try {
            Verifier.verifyNotEmpty(artist.getName());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Artist name is required", e);
        }
    }
    
    private void validate(ProjectDto project) {
        try {
            Verifier.verifyNotNull(project);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project/Album criteria object is required", e);
        }
        
        try {
            Verifier.verifyNotEmpty(project.getTitle());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project/Album title is required", e);
        }
        
        try {
            Verifier.verifyPositive(project.getArtistId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project/Album artist id is required", e);
        }
        
        try {
            Verifier.verifyPositive(project.getGenreId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project/Album genre id is required", e);
        }
        
        try {
            Verifier.verifyPositive(project.getMediaTypeId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project/Album media type id is required", e);
        }
        
        try {
            Verifier.verifyPositive(project.getProjectTypeId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Project/Album project type id is required", e);
        }
    }
    
    private void validate(TracksDto project) {
        try {
            Verifier.verifyNotNull(project);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Track criteria object is required", e);
        }
        
        try {
            Verifier.verifyNotEmpty(project.getTrackTitle());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Track title is required", e);
        }
        
        try {
            Verifier.verifyPositive(project.getProjectId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Track project id is required", e);
        }
        
        try {
            Verifier.verifyPositive(project.getTrackNumber());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Track number is required", e);
        }
    }
    
}
