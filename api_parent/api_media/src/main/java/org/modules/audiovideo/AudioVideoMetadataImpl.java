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
        return 0;
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
        return null;
    }

    /* (non-Javadoc)
     * @see org.modules.audiovideo.AudioVideoApi#updateProject(org.dto.ProjectDto)
     */
    @Override
    public int updateProject(ProjectDto project) throws AudioVideoApiException {
        return 0;
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
        return null;
    }

    /* (non-Javadoc)
     * @see org.modules.audiovideo.AudioVideoApi#updateTrack(org.dto.TracksDto)
     */
    @Override
    public int updateTrack(TracksDto track) throws AudioVideoApiException {
        return 0;
    }

    /**
     * Not supported
     */
    @Override
    public int deleteTracks(TracksDto criteria) throws AudioVideoApiException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
}
