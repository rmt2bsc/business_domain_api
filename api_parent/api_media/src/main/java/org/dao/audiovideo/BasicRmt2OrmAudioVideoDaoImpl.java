package org.dao.audiovideo;

import java.io.File;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.MediaDaoImpl;
import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvProjectType;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dto.ArtistDto;
import org.dto.GenreDto;
import org.dto.ProjectDto;
import org.dto.ProjectTypeDto;
import org.dto.TracksDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;

import com.api.persistence.DatabaseException;
import com.api.persistence.db.DatabaseConnectionBean;
import com.api.persistence.db.DynamicSqlApi;
import com.api.persistence.db.DynamicSqlFactory;
import com.api.persistence.db.orm.OrmBean;
import com.util.RMT2Date;
import com.util.RMT2File;
import com.util.UserTimestamp;

/**
 * An RMT2 ORM implemetation of {@link AudioVideoDao} interface which the data
 * source is the MIME database.
 * 
 * @author Roy Terrell
 * 
 */
class BasicRmt2OrmAudioVideoDaoImpl extends MediaDaoImpl implements
        AudioVideoDao {

    private static Logger logger = Logger
            .getLogger(BasicRmt2OrmAudioVideoDaoImpl.class);

    /**
     * Defaul constructor that opens a connection to the MIME database.
     */
    protected BasicRmt2OrmAudioVideoDaoImpl() {
        super();
        return;
    }

    /**
     * Defaul constructor that opens a connection to the MIME database.
     */
    protected BasicRmt2OrmAudioVideoDaoImpl(String appName) {
        super(appName);
        return;
    }

    /**
     * Fetches artist information from the <i>av_artist</i> table based on
     * selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link ArtistDto} containing values for
     *            selection criteria.
     * @return a List of {@link ArtistDto} objects or null if no data was found.
     * @throws AudioVideoDaoException
     */
    @Override
    public List<ArtistDto> fetchArtist(ArtistDto criteria)
            throws AudioVideoDaoException {
        // Setup criteria
        AvArtist queryObj = new AvArtist();
        if (criteria != null) {
            if (criteria.getUid() > 0) {
                queryObj.addCriteria(AvArtist.PROP_ARTISTID, criteria.getUid());
            }
            if (criteria.getDescritpion() != null) {
                queryObj.addLikeClause(AvArtist.PROP_NAME,
                        criteria.getDescritpion());
            }
        }

        // Query data
        List<AvArtist> results = null;
        try {
            results = this.client.retrieveList(queryObj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new AudioVideoDaoException(e);
        }

        // Package results
        List<ArtistDto> list = new ArrayList<ArtistDto>();
        for (AvArtist item : results) {
            ArtistDto dto = Rmt2MediaDtoFactory.getArtistInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches project information from the <i>av_project</i> table based on the
     * selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link ProjectDto} containing values for
     *            selection criteria.
     * @return a List of {@link ProjectDto} objects or null if no data was
     *         found.
     * @throws AudioVideoDaoException
     */
    @Override
    public List<ProjectDto> fetchProject(ProjectDto criteria)
            throws AudioVideoDaoException {
        // Setup criteria
        AvProject queryObj = new AvProject();
        if (criteria != null) {
            if (criteria.getProjectId() > 0) {
                queryObj.addCriteria(AvProject.PROP_PROJECTID,
                        criteria.getProjectId());
            }
            if (criteria.getArtistId() > 0) {
                queryObj.addCriteria(AvProject.PROP_ARTISTID,
                        criteria.getArtistId());
            }
            if (criteria.getProjectTypeId() > 0) {
                queryObj.addCriteria(AvProject.PROP_PROJECTTYPEID,
                        criteria.getProjectTypeId());
            }
            if (criteria.getGenreId() > 0) {
                queryObj.addCriteria(AvProject.PROP_GENREID,
                        criteria.getGenreId());
            }
            if (criteria.getMediaTypeId() > 0) {
                queryObj.addCriteria(AvProject.PROP_MEDIATYPEID,
                        criteria.getMediaTypeId());
            }
            if (criteria.getTitle() != null) {
                queryObj.addLikeClause(AvProject.PROP_TITLE,
                        criteria.getTitle());
            }
            if (criteria.getYear() > 0) {
                queryObj.addCriteria(AvProject.PROP_YEAR, criteria.getYear());
            }
            if (criteria.getRippedInd() > 0) {
                queryObj.addCriteria(AvProject.PROP_RIPPED,
                        criteria.getRippedInd());
            }
            if (criteria.getCost() > 0) {
                queryObj.addCriteria(AvProject.PROP_COST, criteria.getCost());
            }
        }

        // Query data
        List<AvProject> results = null;
        try {
            results = this.client.retrieveList(queryObj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new AudioVideoDaoException(e);
        }

        // Package results
        List<ProjectDto> list = new ArrayList<ProjectDto>();
        for (AvProject item : results) {
            ProjectDto dto = Rmt2MediaDtoFactory.getProjectInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches track information from the <i>av_tracks</i> table based on the
     * selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link TracksDto} containing values for
     *            selection criteria.
     * @return a List of {@link TracksDto} objects or null if no data was found.
     * @throws AudioVideoDaoException
     */
    @Override
    public List<TracksDto> fetchTrack(TracksDto criteria)
            throws AudioVideoDaoException {
        // Setup criteria
        AvTracks queryObj = new AvTracks();
        if (criteria != null) {
            if (criteria.getTrackId() > 0) {
                queryObj.addCriteria(AvTracks.PROP_TRACKID,
                        criteria.getTrackId());
            }
            if (criteria.getProjectId() > 0) {
                queryObj.addCriteria(AvTracks.PROP_PROJECTID,
                        criteria.getProjectId());
            }
            if (criteria.getTrackTitle() != null) {
                queryObj.addLikeClause(AvTracks.PROP_TRACKTITLE,
                        criteria.getTrackTitle());
            }
            if (criteria.getComments() != null) {
                queryObj.addLikeClause(AvTracks.PROP_COMMENTS,
                        criteria.getComments(), OrmBean.LIKE_CONTAINS);
            }
        }

        // Query data
        List<AvTracks> results = null;
        try {
            results = this.client.retrieveList(queryObj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new AudioVideoDaoException(e);
        }

        // Package results
        List<TracksDto> list = new ArrayList<TracksDto>();
        for (AvTracks item : results) {
            TracksDto dto = Rmt2MediaDtoFactory.getTrackInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches genre information from the <i>av_genre</i> table based on the
     * selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link GenreDto} containing values for
     *            selection criteria.
     * @return a List of {@link GenreDto} objects or null if no data was found.
     * @throws AudioVideoDaoException
     */
    @Override
    public List<GenreDto> fetchGenre(GenreDto criteria)
            throws AudioVideoDaoException {
        // Setup criteria
        AvGenre queryObj = new AvGenre();
        if (criteria != null) {
            if (criteria.getUid() > 0) {
                queryObj.addCriteria(AvGenre.PROP_GENREID, criteria.getUid());
            }
            if (criteria.getDescritpion() != null) {
                queryObj.addCriteria(AvGenre.PROP_DESCRIPTION,
                        criteria.getDescritpion());
            }
        }

        // Query data
        List<AvGenre> results = null;
        try {
            results = this.client.retrieveList(queryObj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new AudioVideoDaoException(e);
        }

        // Package results
        List<GenreDto> list = new ArrayList<GenreDto>();
        for (AvGenre item : results) {
            GenreDto dto = Rmt2MediaDtoFactory.getGenreInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches project type information from the <i>av_project_type</i> table
     * based on the selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link ProjectTypeDto} containing values for
     *            selection criteria.
     * @return a List of {@link ProjectTypeDto} objects or null if no data was
     *         found.
     * @throws AudioVideoDaoException
     */
    @Override
    public List<ProjectTypeDto> fetchProjectType(ProjectTypeDto criteria)
            throws AudioVideoDaoException {
        // Setup criteria
        AvProjectType queryObj = new AvProjectType();
        if (criteria != null) {
            if (criteria.getUid() > 0) {
                queryObj.addCriteria(AvProjectType.PROP_PROJECTTYPEID,
                        criteria.getUid());
            }
            if (criteria.getDescritpion() != null) {
                queryObj.addLikeClause(AvProjectType.PROP_DESCRIPTION,
                        criteria.getDescritpion());
            }
        }

        // Query data
        List<AvProjectType> results = null;
        try {
            results = this.client.retrieveList(queryObj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new AudioVideoDaoException(e);
        }

        // Package results
        List<ProjectTypeDto> list = new ArrayList<ProjectTypeDto>();
        for (AvProjectType item : results) {
            ProjectTypeDto dto = Rmt2MediaDtoFactory
                    .getProjectTypeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a new or modifies an existing artist record in the
     * <i>av_artist</i> table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param artist
     *            an instance of {@link ArtistDto}
     * @return an int value representing either the unique identifier of the
     *         artist inserted, or the total number of rows effected by the
     *         artist update operation.
     * @throws AudioVideoDaoException
     */
    @Override
    public int maintainArtist(ArtistDto artist) throws AudioVideoDaoException {
        this.validateArtist(artist);

        AvArtist a = AudioVideoDaoFactory.createArtistInstance(artist);

        int rc = 0;
        this.client.beginTrans();
        try {
            if (a.getArtistId() == 0) {
                rc = this.insertArtist(a);
                artist.setUid(rc);
            }
            if (a.getArtistId() > 0) {
                rc = this.updateArtist(a);
            }
            this.client.commitTrans();
            return rc;
        } catch (AudioVideoDaoException e) {
            this.client.rollbackTrans();
            throw e;
        }
    }

    private int insertArtist(AvArtist artist) throws AudioVideoDaoException {
        int rc = 0;
        try {
            rc = this.client.insertRow(artist, true);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Error inserting artist record";
            throw new AudioVideoDaoException(this.msg);
        }
    }

    private int updateArtist(AvArtist artist) throws AudioVideoDaoException {
        int rc = 0;
        try {
            rc = this.client.updateRow(artist);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Error updating artist record";
            throw new AudioVideoDaoException(this.msg);
        }
    }

    /**
     * Verifies if <i>artist</i> is valid.
     * <p>
     * The artist object cannot be null and the artist's name is required to
     * have a value.
     * 
     * @param artist
     *            an instance of {@link ArtistDto}
     * @throws AvProjectDataValidationException
     *             a validation rule fails
     */
    protected void validateArtist(ArtistDto artist)
            throws AvProjectDataValidationException {
        if (artist == null) {
            this.msg = "Artist object is invalid or null";
            throw new AvProjectDataValidationException(this.msg);
        }
        if (artist.getDescritpion() == null) {
            throw new AvProjectDataValidationException(
                    "Artist name is required");
        }
        return;
    }

    /**
     * Creates a new or modifies an existing project record in the
     * <i>av_project</i> table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param proj
     *            an instance of {@link ProjectDto}
     * @return an int value representing either the unique identifier of the
     *         project inserted, or the total number of rows effected by the
     *         project update operation.
     * @throws AudioVideoDaoException
     */
    @Override
    public int maintainProject(ProjectDto proj) throws AudioVideoDaoException {
        this.validateProject(proj);

        AvProject p = AudioVideoDaoFactory.createProjectInstance(proj);

        int rc = 0;
        this.client.beginTrans();
        try {
            if (p.getProjectId() == 0) {
                rc = this.insertProject(p);
                proj.setProjectId(rc);
            }
            if (p.getProjectId() > 0) {
                rc = this.updateProject(p);
            }
            this.client.commitTrans();
            return rc;
        } catch (AudioVideoDaoException e) {
            this.client.rollbackTrans();
            throw e;
        }
    }

    private int insertProject(AvProject proj) throws AudioVideoDaoException {
        int rc = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            proj.setDateCreated(ut.getDateCreated());
            proj.setDateUpdated(ut.getDateCreated());
            proj.setUserId(ut.getLoginId());
            if (proj.getContentId() == 0) {
                proj.setNull(AvProject.PROP_CONTENTID);
            }
            rc = this.client.insertRow(proj, true);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Error inserting project record";
            throw new AudioVideoDaoException(this.msg, e);
        }
    }

    private int updateProject(AvProject proj) throws AudioVideoDaoException {
        int rc = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            proj.setDateUpdated(ut.getDateCreated());
            proj.setUserId(ut.getLoginId());
            if (proj.getContentId() == 0) {
                proj.setNull(AvProject.PROP_CONTENTID);
            }
            rc = this.client.updateRow(proj);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Error updating project record";
            throw new AudioVideoDaoException(this.msg);
        }
    }

    /**
     * Verifies that a project object is valid.
     * <p>
     * <i>proj</i> must be a valid instance, and the following properties are
     * required to have values: artist id, project type id, and title.
     * 
     * @param proj
     *            an instance of {@link ProjectDto}
     * @throws AvProjectDataValidationException
     *             a validation rule fails
     */
    protected void validateProject(ProjectDto proj)
            throws AvProjectDataValidationException {
        if (proj == null) {
            this.msg = "Project object is invalid or null";
            throw new AvProjectDataValidationException(this.msg);
        }
        if (proj.getArtistId() <= 0) {
            this.msg = "Artist id is required";
            throw new AvProjectDataValidationException(this.msg);
        }
        if (proj.getProjectTypeId() < 1 || proj.getProjectTypeId() > 2) {
            this.msg = "Project Type id is required";
            throw new AvProjectDataValidationException(this.msg);
        }
        if (proj.getTitle() == null || proj.getTitle().length() <= 0) {
            this.msg = "Title id is required";
            throw new AvProjectDataValidationException(this.msg);
        }
    }

    /**
     * Creates a new or modifies an existing track record in the
     * <i>av_tracks</i> table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param track
     *            an instance of {@link TracksDto}
     * @return an int value representing either the unique identifier of the
     *         track inserted, or the total number of rows effected by the track
     *         update operation.
     * @throws AudioVideoDaoException
     */
    @Override
    public int maintainTrack(TracksDto track) throws AudioVideoDaoException {
        this.validateTarck(track);
        AvTracks t = AudioVideoDaoFactory.createTrackInstance(track);

        int rc = 0;
        this.client.beginTrans();
        try {
            if (t.getTrackId() == 0) {
                rc = this.insertTrack(t);
                track.setTrackId(rc);
            }
            if (t.getTrackId() > 0) {
                rc = this.updateTrack(t);
            }
            this.client.commitTrans();
            return rc;
        } catch (AudioVideoDaoException e) {
            this.client.rollbackTrans();
            throw e;
        }
    }

    private int insertTrack(AvTracks track) throws AudioVideoDaoException {
        int rc = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            track.setDateCreated(ut.getDateCreated());
            track.setDateUpdated(ut.getDateCreated());
            track.setUserId(ut.getLoginId());
            rc = this.client.insertRow(track, true);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Error inserting audio/video track record";
            throw new AudioVideoDaoException(this.msg);
        }
    }

    private int updateTrack(AvTracks track) throws AudioVideoDaoException {
        int rc = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            track.setDateUpdated(ut.getDateCreated());
            track.setUserId(ut.getLoginId());
            rc = this.client.updateRow(track);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Error updating audio/video record";
            throw new AudioVideoDaoException(this.msg);
        }
    }

    /**
     * Verifies that the tracks object is valid for database updates.
     * <p>
     * A track object is considered valid for database updates when it is not
     * null, track title has a value, and track number is greater than zero.
     * 
     * @param track
     *            an instance of {@link TracksDto}
     * @throws AvProjectDataValidationException
     *             a validation rule fails
     */
    protected void validateTarck(TracksDto track)
            throws AvProjectDataValidationException {
        if (track == null) {
            this.msg = "Track object is invalid or null";
            throw new AvProjectDataValidationException(this.msg);
        }
        if (track.getTrackTitle() == null) {
            this.msg = "Track title is required";
            throw new AvProjectDataValidationException(this.msg);
        }
        if (track.getTrackNumber() <= 0) {
            this.msg = "Track number must be a numeric greater than zero";
            throw new AvProjectDataValidationException(this.msg);
        }
        return;
    }

    /**
     * Combines the efforts of adding artist, project, and all project tracks to
     * the tables, <i>av_artist</i>, <i>av_project</i>, and <i>av_tracks</i>,
     * respectively, under a single transaction.
     * <p>
     * The update sequence mandates that the <i>av_artist</i>,
     * <i>av_project</i>, and <i>av_tracks</i> tables are inserted into the
     * order stipulated. For eachtable encounterd, a SQL insert is performed
     * when the tables's primary key is equal to zero. When the primary key id
     * is greater than zero, an SQL update is applied.
     * 
     * 
     * @param avProj
     *            an instance of {@link AvCombinedProjectBean}
     * @return The total number of tracks added for the artist's project.
     * @throws AudioVideoDaoException
     */
    @Override
    public int addAudioVideoFileData(AvCombinedProjectBean avProj)
            throws AudioVideoDaoException {
        AvArtist artist = avProj.getAva();
        AvProject project = avProj.getAv();
        AvTracks track = avProj.getAvt();

        int artistId = this.insertArtistFromFile(artist);
        project.setArtistId(artistId);
        int projectId = this.insertProjectFromFile(project, avProj.getGenre());
        track.setProjectId(projectId);
        this.insertTrackFromFile(track);
        return projectId;
    }

    private int insertArtistFromFile(AvArtist artist) throws DatabaseException {
        ArtistDto aDto = Rmt2MediaDtoFactory.getArtistInstance(artist);
        this.validateArtist(aDto);
        int artistId = 0;
        if (artist.getArtistId() == 0) {
            // Check if artist alread exists
            ArtistDto artistCriteria = Rmt2MediaDtoFactory
                    .getArtistInstance(null);
            artistCriteria.setDescription(aDto.getDescritpion());
            List<ArtistDto> a = this.fetchArtist(artistCriteria);
            if (a != null && a.size() == 1) {
                artistId = a.get(0).getUid();
            }
            else {
                artistId = this.insertArtist(artist);
            }
            artist.setArtistId(artistId);
            aDto.setUid(artistId);
        }
        else {
            this.updateArtist(artist);
            artistId = artist.getArtistId();
        }
        return artistId;
    }

    private int insertProjectFromFile(AvProject project, String genreName)
            throws DatabaseException {
        int artistId = project.getArtistId();
        ProjectDto pDto = Rmt2MediaDtoFactory.getProjectInstance(project);
        int projectId = 0;
        project.setArtistId(artistId);
        pDto.setArtistId(artistId);
        this.validateProject(pDto);

        GenreDto genreCriteria = Rmt2MediaDtoFactory.getGenreInstance(null);
        genreCriteria.setDescription(genreName);
        List<GenreDto> g = this.fetchGenre(genreCriteria);
        int genreId = AudioVideoConstants.UNKNOWN_GENRE;
        if (g != null && g.size() == 1) {
            genreId = g.get(0).getUid();
        }
        project.setGenreId(genreId);
        pDto.setGenreId(genreId);
        if (project.getProjectId() == 0) {
            ProjectDto projCriteria = Rmt2MediaDtoFactory
                    .getProjectInstance(null);
            projCriteria.setTitle(pDto.getTitle());
            List<ProjectDto> p = this.fetchProject(projCriteria);
            if (p != null && p.size() == 1) {
                projectId = p.get(0).getProjectId();
            }
            else {
                projectId = this.insertProject(project);
            }
            project.setProjectId(projectId);
            pDto.setProjectId(projectId);
        }
        else {
            this.updateProject(project);
            projectId = project.getProjectId();
        }
        return projectId;
    }

    private int insertTrackFromFile(AvTracks track) throws DatabaseException {
        TracksDto tDto = Rmt2MediaDtoFactory.getTrackInstance(track);
        int trackId = 0;
        tDto.setProjectId(track.getProjectId());
        this.validateTarck(tDto);
        if (track.getTrackId() == 0) {
            trackId = this.insertTrack(track);
            track.setTrackId(trackId);
            tDto.setTrackId(trackId);
        }
        else {
            this.updateTrack(track);
            trackId = track.getTrackId();
        }
        return trackId;
    }

    /**
     * Reads the tag data from the media file, <i>sourceFile</i>, and packages
     * the data in an instance of AvCombinedProjectBean.
     * <p>
     * Afterwards, initiates the process of updating the database with tag data.
     * 
     * @param sourceFile
     *            the audio/video file to extract data from.
     * @return an instance of {@link AvCombinedProjectBean}
     * @throws AudioVideoDaoException
     */
    @Override
    public AvCombinedProjectBean extractFileMetaData(File sourceFile)
            throws AudioVideoDaoException {
        if (sourceFile == null) {
            this.msg = "Unable to extract meta data from audio/video file - The source file is invalid or null";
            throw new AvInvalidSourceFileException(this.msg);
        }

        AvCombinedProjectBean avb = null;
        AvArtist ava = null;
        AvProject av = null;
        AvTracks avt = null;

        try {
            avb = new AvCombinedProjectBean();
            ava = avb.getAva();
            av = avb.getAv();
            avt = avb.getAvt();
        } catch (com.SystemException e) {
            return null;
        }

        // Get file name with complet path
        String mediaPath = sourceFile.getPath();
        this.msg = "Extracting meta data from: " + mediaPath;
        logger.info(this.msg);
        System.out.println(this.msg);

        // Get the appropriate MP3Reader implementation
        // MP3Reader mp3 = AudioVideoDaoFactory.createJid3mp3WmvApi(sourceFile);
        // MP3Reader mp3 = AudioVideoDaoFactory.createJID3Mp3Api(sourceFile);
        // MP3Reader mp3 = AudioVideoDaoFactory.createMyId3Api(sourceFile);
        MP3Reader mp3 = AudioVideoDaoFactory.createEntaggedId3Api(sourceFile);
        if (mp3 == null) {
            return null;
        }

        String fileExt = RMT2File.getFileExt(mediaPath);
        if (fileExt.equalsIgnoreCase(".wmv")) {
            av.setProjectTypeId(AudioVideoConstants.PROJ_TYPE_ID_VIDEO);
            av.setMediaTypeId(AudioVideoConstants.MEDIA_TYPE_DVD);
        }
        else {
            av.setProjectTypeId(AudioVideoConstants.PROJ_TYPE_ID_AUDIO);
            av.setMediaTypeId(AudioVideoConstants.MEDIA_TYPE_CD);
        }

        try {
            // Get Artist
            ava.setName(mp3.getArtist());

            // Get Album
            av.setTitle(mp3.getAlbum());

            // Get Track Number
            avt.setTrackNumber(mp3.getTrack());

            // Get Track Title
            avt.setTrackTitle(mp3.getTrackTitle());

            // Get comments.
            String comments = mp3.getComment();
            avt.setComments(comments);
            // Make data adjustments in the event we are dealing with a Various
            // Artists type album.
            if (comments.contains(AudioVideoConstants.VARIOUS_ARTIST_TOKEN)) {
                avt.setTrackArtist(ava.getName());
                ava.setName(AudioVideoConstants.VARIOUS_ARTIST_NAME);
            }

            // Get Genre
            avb.setGenre(mp3.getGenre());

            // Get Year Released
            av.setYear(mp3.getYear());

            // Get Recording Time
            List<Integer> list = mp3.getDuration();
            if (list != null && list.size() > 0) {
                avt.setTrackHours(list.get(0));
                avt.setTrackMinutes(list.get(1));
                avt.setTrackSeconds(list.get(2));
            }

            // Get Disc Number
            int discNo = mp3.getDiscNumber();
            avt.setTrackDisc(String.valueOf(discNo));

            // Capture the media file location data
            String pathOnly = RMT2File.getFilePathInfo(mediaPath);
            avt.setLocPath(pathOnly);

            // Set File name
            String fileName = sourceFile.getName();
            avt.setLocFilename(fileName);

            // Initialized Ripped flag
            av.setRipped(mediaPath
                    .indexOf(AudioVideoConstants.DIRNAME_NON_RIPPED) > -1 ? 0
                    : 1);
            return avb;
        } catch (Exception e) {
            this.msg = "Audio/Video file extraction error for " + mediaPath;
            System.out.println(this.msg);
            e.printStackTrace();
            throw new AvFileExtractionException(this.msg, e);
        }
    }

    /**
     * Removes all entries from the audio_video and audio_video_tracks tables
     * where streaming files exist on the server.
     * 
     * @param projectTypeId
     *            the proejct type id
     * @return the total number of rows deleted.
     * @throws AudioVideoException
     */
    public int purge(int projectTypeId) {
        logger.info("Begin Audio/Video Purge process");
        int rows = 0;
        int tot = 0;

        DynamicSqlApi spApi = null;
        this.client.beginTrans();
        try {
            StringBuffer sql = new StringBuffer();
            // sql.append("delete from av_tracks where project_id in (select b.project_id from av_project b where b.project_type_id = ");
            // sql.append(projectTypeId);
            // sql.append(")");
            sql.append("delete from av_tracks");
            rows = this.client.executeUpdate(sql.toString());
            tot += rows;
            logger.info("Total rows purged from av_tracks: " + rows);

            sql = new StringBuffer();
            // sql.append("delete from av_project where project_type_id = ");
            // sql.append(projectTypeId);
            sql.append("delete from av_project");
            rows = this.client.executeUpdate(sql.toString());
            tot += rows;

            DatabaseConnectionBean con = (DatabaseConnectionBean) this.client
                    .getConnectionWrapper();
            spApi = DynamicSqlFactory.create(con);
            spApi.clearParms();
            spApi.addParm("tbl_name", Types.VARCHAR, "av_project", false);
            spApi.addParm("owner_name", Types.VARCHAR, "dba", false);
            spApi.addParm("new_identity", Types.VARCHAR, 0, false);
            spApi.execute("exec sa_reset_identity ???");

            spApi.clearParms();
            spApi.addParm("tbl_name", Types.VARCHAR, "av_tracks", false);
            spApi.addParm("owner_name", Types.VARCHAR, "dba", false);
            spApi.addParm("new_identity", Types.VARCHAR, 0, false);
            spApi.execute("exec sa_reset_identity ???");

            // Comment so not to purge artist table.
            sql = new StringBuffer();
            sql.append("delete from av_artist");
            rows = this.client.executeUpdate(sql.toString());
            tot += rows;
            logger.info("Total rows purged from av_project: " + rows);
            spApi.clearParms();
            spApi.addParm("tbl_name", Types.VARCHAR, "av_artist", false);
            spApi.addParm("owner_name", Types.VARCHAR, "dba", false);
            spApi.addParm("new_identity", Types.VARCHAR, 0, false);
            spApi.execute("exec sa_reset_identity ???");

            this.client.commitTrans();
            return tot;
        } catch (Exception e) {
            this.client.rollbackTrans();
            return -1;
        } finally {
            if (spApi != null) {
                spApi.close();
                spApi = null;
            }
        }
    }

    /**
     * Counts the total number of files of the directory, <i>filePath</i>, and
     * its sub-directories.
     * 
     * @param filePath
     * @return int
     */
    public int computeTotalFileCount(String filePath) {
        File dir = new File(filePath);
        return this.computeTotalFileCount(dir);
    }

    /**
     * Counts the total number of files of the directory, <i>file</i>, and its
     * sub-directories.
     * 
     * @param file
     *            an instance of File which must represent a directory in the
     *            file system.
     * @return int the file count.
     */
    public int computeTotalFileCount(File file) {
        File mediaList[];
        int itemCount = 0;
        int total = 0;

        mediaList = file.listFiles();
        itemCount = mediaList.length;
        for (int ndx = 0; ndx < itemCount; ndx++) {
            if (mediaList[ndx].isDirectory()) {
                // Make recursive call to process next level
                total += this.computeTotalFileCount(mediaList[ndx]);
            }
            if (mediaList[ndx].isFile()) {
                total += 1;
            }
        }
        return total;
    }
}
