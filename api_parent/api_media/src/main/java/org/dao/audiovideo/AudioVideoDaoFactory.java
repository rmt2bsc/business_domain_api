package org.dao.audiovideo;

import java.io.File;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dto.ArtistDto;
import org.dto.ProjectDto;
import org.dto.TracksDto;

import com.NotFoundException;
import com.RMT2Base;

/**
 * A factory for creating audio video DAO objects.
 * 
 * @author Roy Terrell
 * 
 */
public class AudioVideoDaoFactory extends RMT2Base {

    private static String ERROR_MP3_API = "MP3 Api Instantiation Error: ";

    /**
     * Default contructor
     */
    public AudioVideoDaoFactory() {
        return;
    }

    /**
     * Creates an instance of the {@link AudioVideoDao} using the RMT2 ORM basic
     * DAO implementation.
     * 
     * @return an instance of {@link AudioVideoDao}
     */
    public AudioVideoDao createRmt2OrmDaoInstance() {
        AudioVideoDao dao = new BasicRmt2OrmAudioVideoDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of the {@link AudioVideoDao} using the RMT2 ORM basic
     * DAO implementation.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link AudioVideoDao}
     */
    public AudioVideoDao createRmt2OrmDaoInstance(String appName) {
        AudioVideoDao dao = new BasicRmt2OrmAudioVideoDaoImpl(appName);
        return dao;
    }

    /**
     * Create and instance of ArtistDto.
     * 
     * @param artist
     *            an instance of {@link ArtistDto}
     * @return an instance of {@link AvArtist}
     */
    public static final AvArtist createArtistInstance(ArtistDto artist) {
        AvArtist a = new AvArtist();
        a.setArtistId(artist.getId());
        a.setName(artist.getName());
        return a;
    }

    /**
     * Create an instance of AvProject by adapting a ProjectDto object.
     * 
     * @param proj
     *            an instance of {@link ProjectDto}
     * @return an instance of {@link AvProject}
     */
    public static final AvProject createProjectInstance(ProjectDto proj) {
        AvProject p = new AvProject();
        p.setProjectId(proj.getProjectId());
        p.setArtistId(proj.getArtistId());
        p.setProjectTypeId(proj.getProjectTypeId());
        p.setGenreId(proj.getGenreId());
        p.setMediaTypeId(proj.getMediaTypeId());
        p.setTitle(proj.getTitle());
        p.setYear(proj.getYear());
        p.setMasterDupId(proj.getMasterDupId());
        p.setRipped(proj.getRippedInd());
        p.setCost(proj.getCost());
        p.setContentId(proj.getContentId());
        p.setDateCreated(proj.getDateCreated());
        return p;
    }

    /**
     * Create an instance of AvTracks by adapting a TracksDto object.
     * 
     * @param track
     *            an instance of {@link TracksDto}
     * @return an instance of {@link AvTracks}
     */
    public static final AvTracks createTrackInstance(TracksDto track) {
        AvTracks t = new AvTracks();
        t.setTrackId(track.getTrackId());
        t.setProjectId(track.getProjectId());
        t.setTrackNumber(track.getTrackNumber());
        t.setTrackTitle(track.getTrackTitle());
        t.setTrackHours(track.getTrackHours());
        t.setTrackMinutes(track.getTrackMinutes());
        t.setTrackSeconds(track.getTrackSeconds());
        t.setTrackDisc(track.getTrackDisc());
        t.setTrackProducer(track.getTrackProducer());
        t.setTrackComposer(track.getTrackComposer());
        t.setTrackLyricist(track.getTrackLyricist());
        t.setLocFilename(track.getLocFilename());
        t.setLocPath(track.getLocPath());
        t.setLocRootPath(track.getLocRootPath());
        t.setLocServername(track.getLocServername());
        t.setLocSharename(track.getLocSharename());
        t.setComments(track.getComments());
        t.setDateCreated(track.getDateCreated());
        return t;

    }

    /**
     * Create an instance of MP3Reader from a MyId3 API implementation.
     * 
     * @param mp3Source
     * @return an instance of {@link MP3Reader}
     * @throws MP3ApiInstantiationException
     */
    public static MP3Reader createMyId3Api(File mp3Source) {
        String msg = ERROR_MP3_API;
        MP3Reader api = null;
        try {
            api = new MyMp3LibImpl(mp3Source);
            return api;
        } catch (Exception e) {
            msg += "Unable to create MyId3 MP3Reader implementation for media resource, " + mp3Source.getAbsolutePath();
            throw new MP3ApiInstantiationException(msg, e);
        }
    }

    /**
     * Create an instance of MP3Reader from a JID3 API implementation.
     * 
     * @param mp3Source
     * @return an instance of {@link MP3Reader}
     * @throws MP3ApiInstantiationException
     */
    public static MP3Reader createJID3Mp3Api(File mp3Source) {
        String msg = ERROR_MP3_API;
        MP3Reader api = null;
        try {
            api = new JID3Mp3LibImpl(mp3Source);
            return api;
        } catch (NotFoundException e) {
            return null;
        } catch (Exception e) {
            msg += "Unable to create JID3 MP3Reader implementation for media resource, " + mp3Source.getAbsolutePath();
            throw new MP3ApiInstantiationException(msg, e);
        }
    }

    /**
     * Create an instance of MP3Reader from an Entagged ID3 API implementation.
     * 
     * @param mp3Source
     *            A {@link File} instance pointing to the actual media file
     * @return an instance of {@link MP3Reader}
     * @throws MP3ApiInstantiationException
     */
    public static MP3Reader createEntaggedId3Api(File mp3Source) {
        String msg = ERROR_MP3_API;
        MP3Reader api = null;
        try {
            api = new EntaggedMp3LibImpl(mp3Source);
            return api;
        } catch (Throwable e) {
            msg += "Unable to create EntaggedId3 MP3Reader implementation for media resource, " + mp3Source.getAbsolutePath();
            throw new MP3ApiInstantiationException(msg, e);
        }
    }

    /**
     * Create an instance of MP3Reader from a Id3Mp3Wmv API implementation.
     * 
     * @param mp3Source
     * @return an instacne of {@link MP3Reader}
     * @throws MP3ApiInstantiationException
     */
    public static MP3Reader createId3mp3WmvApi(File mp3Source) {
        String msg = ERROR_MP3_API;
        MP3Reader api = null;
        try {
            api = new Id3Mp3WmvLibImpl(mp3Source);
            return api;
        } catch (Throwable e) {
            msg += "Unable to create Id3Mp3Wmv MP3Reader implementation for media resource, " + mp3Source.getAbsolutePath();
            throw new MP3ApiInstantiationException(msg, e);
        }
    }
}
