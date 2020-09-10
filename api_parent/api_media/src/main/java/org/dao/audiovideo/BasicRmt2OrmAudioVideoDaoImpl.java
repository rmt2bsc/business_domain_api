package org.dao.audiovideo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.MediaDaoImpl;
import org.dao.entity.CommonMediaDto;
import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvMediaType;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvProjectType;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dto.ArtistDto;
import org.dto.GenreDto;
import org.dto.MediaTypeDto;
import org.dto.ProjectDto;
import org.dto.ProjectTypeDto;
import org.dto.TracksDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.modules.audiovideo.AudioVideoFactory;

import com.api.persistence.DatabaseException;
import com.api.persistence.PersistenceClient;
import com.api.persistence.PersistenceConst;
import com.api.persistence.db.DatabaseConnectionBean;
import com.api.persistence.db.DynamicSqlApi;
import com.api.persistence.db.DynamicSqlFactory;
import com.api.persistence.db.orm.OrmBean;
import com.api.util.RMT2Date;
import com.api.util.RMT2File;
import com.api.util.RMT2String;
import com.api.util.UserTimestamp;

/**
 * An RMT2 ORM implemetation of {@link AudioVideoDao} interface which the data
 * source is the MIME database.
 * 
 * @author Roy Terrell
 * 
 */
class BasicRmt2OrmAudioVideoDaoImpl extends MediaDaoImpl implements AudioVideoDao {

    private static Logger logger = Logger.getLogger(BasicRmt2OrmAudioVideoDaoImpl.class);

    /**
     * Defaul constructor that opens a connection to the MIME database.
     */
    BasicRmt2OrmAudioVideoDaoImpl() {
        super();
        return;
    }

    /**
     * Defaul constructor that opens a connection to the MIME database.
     */
    BasicRmt2OrmAudioVideoDaoImpl(String appName) {
        super(appName);
        return;
    }

    /**
     * Creates a BasicRmt2OrmAudioVideoDaoImpl object with a shared persistent client.
     * 
     * @param client
     */
    BasicRmt2OrmAudioVideoDaoImpl(PersistenceClient client) {
        super(client);
    }
    
    @Override
    public List<CommonMediaDto> fetchCommonMedia(String criteria) throws AudioVideoDaoException {
        String sql = RMT2File.getFileContentsAsString("sql/FetchMediaInfo.sql");
        sql = RMT2String.replaceAll(sql, criteria, PersistenceConst.SQL_PLACEHOLDER);
        ResultSet rs = null;
        try {
            rs = this.client.executeSql(sql);
        } catch (DatabaseException e) {
            this.msg = "Error fetching common media information record(s)";
            throw new AudioVideoDaoException(this.msg, e);
        }

        List<CommonMediaDto> list = new ArrayList<>();
        try {
            if (rs != null) {
                while (rs.next()) {
                    CommonMediaDto dto = new CommonMediaDto();
                    dto.setResultType(rs.getInt("result_type"));
                    dto.setResultTypeDescription(rs.getString("result_type_description"));
                    dto.setArtistId(rs.getInt("artist_id"));
                    dto.setArtistName(rs.getString("artist_name"));
                    dto.setProjectId(rs.getInt("project_id"));
                    dto.setProjectTitle(rs.getString("project_title"));
                    dto.setTrackNumber(rs.getInt("track_number"));
                    dto.setTrackName(rs.getString("track_name"));
                    dto.setTrackHours(rs.getInt("track_hours"));
                    dto.setTrackMinutes(rs.getInt("track_minutes"));
                    dto.setTrackSeconds(rs.getInt("track_seconds"));
                    list.add(dto);
                }
            }
            return list;
        } catch (SQLException e) {
            this.msg = "Error fetching common media information from SQL resultset";
            throw new AudioVideoDaoException(this.msg, e);
        }
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
    public List<ArtistDto> fetchArtist(ArtistDto criteria)  throws AudioVideoDaoException {
        // Setup criteria
        AvArtist queryObj = new AvArtist();
        if (criteria != null) {
            if (criteria.getId() > 0) {
                queryObj.addCriteria(AvArtist.PROP_ARTISTID, criteria.getId());
            }
            if (criteria.getName() != null) {
                queryObj.addLikeClause(AvArtist.PROP_NAME, criteria.getName());
            }
            if (criteria.getCriteria() != null) {
                queryObj.addCustomCriteria(criteria.getCriteria());
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
            ArtistDto dto = Rmt2MediaDtoFactory.getAvArtistInstance(item);
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
    public List<ProjectDto> fetchProject(ProjectDto criteria) throws AudioVideoDaoException {
        // Setup criteria
        AvProject queryObj = new AvProject();
        if (criteria != null) {
            if (criteria.getProjectId() > 0) {
                queryObj.addCriteria(AvProject.PROP_PROJECTID, criteria.getProjectId());
            }
            if (criteria.getArtistId() > 0) {
                queryObj.addCriteria(AvProject.PROP_ARTISTID, criteria.getArtistId());
            }
            if (criteria.getProjectTypeId() > 0) {
                queryObj.addCriteria(AvProject.PROP_PROJECTTYPEID, criteria.getProjectTypeId());
            }
            if (criteria.getGenreId() > 0) {
                queryObj.addCriteria(AvProject.PROP_GENREID, criteria.getGenreId());
            }
            if (criteria.getMediaTypeId() > 0) {
                queryObj.addCriteria(AvProject.PROP_MEDIATYPEID, criteria.getMediaTypeId());
            }
            if (criteria.getTitle() != null) {
                queryObj.addLikeClause(AvProject.PROP_TITLE, criteria.getTitle());
            }
            if (criteria.getYear() > 0) {
                queryObj.addCriteria(AvProject.PROP_YEAR, criteria.getYear());
            }
            if (criteria.getRippedInd() > 0) {
                queryObj.addCriteria(AvProject.PROP_RIPPED, criteria.getRippedInd());
            }
            if (criteria.getCost() > 0) {
                queryObj.addCriteria(AvProject.PROP_COST, criteria.getCost());
            }
            if (criteria.getContentPath() != null) {
                queryObj.addCriteria(AvProject.PROP_CONTENTPATH, criteria.getContentPath());
            }
            if (criteria.getProducer() != null) {
                queryObj.addCriteria(AvProject.PROP_PRODUCER, criteria.getProducer());
            }
            if (criteria.getCriteria() != null) {
                queryObj.addCustomCriteria(criteria.getCriteria());
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
            ProjectDto dto = Rmt2MediaDtoFactory.getAvProjectInstance(item);
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
    public List<TracksDto> fetchTrack(TracksDto criteria) throws AudioVideoDaoException {
        // Setup criteria
        AvTracks queryObj = new AvTracks();
        if (criteria != null) {
            if (criteria.getTrackId() > 0) {
                queryObj.addCriteria(AvTracks.PROP_TRACKID, criteria.getTrackId());
            }
            if (criteria.getProjectId() > 0) {
                queryObj.addCriteria(AvTracks.PROP_PROJECTID, criteria.getProjectId());
            }
            if (criteria.getGenreId() > 0) {
                queryObj.addCriteria(AvTracks.PROP_GENREID, criteria.getGenreId());
            }
            if (criteria.getTrackTitle() != null) {
                queryObj.addLikeClause(AvTracks.PROP_TRACKTITLE, criteria.getTrackTitle());
            }
            if (criteria.getComments() != null) {
                queryObj.addLikeClause(AvTracks.PROP_COMMENTS, criteria.getComments(), OrmBean.LIKE_CONTAINS);
            }
            if (criteria.getCriteria() != null) {
                queryObj.addCustomCriteria(criteria.getCriteria());
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
            TracksDto dto = Rmt2MediaDtoFactory.getAvTrackInstance(item);
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
    public List<GenreDto> fetchGenre(GenreDto criteria) throws AudioVideoDaoException {
        // Setup criteria
        AvGenre queryObj = new AvGenre();
        if (criteria != null) {
            if (criteria.getUid() > 0) {
                queryObj.addCriteria(AvGenre.PROP_GENREID, criteria.getUid());
            }
            if (criteria.getDescritpion() != null) {
                queryObj.addCriteria(AvGenre.PROP_DESCRIPTION, criteria.getDescritpion());
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
            GenreDto dto = Rmt2MediaDtoFactory.getAvGenreInstance(item);
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
    public List<ProjectTypeDto> fetchProjectType(ProjectTypeDto criteria) throws AudioVideoDaoException {
        // Setup criteria
        AvProjectType queryObj = new AvProjectType();
        if (criteria != null) {
            if (criteria.getUid() > 0) {
                queryObj.addCriteria(AvProjectType.PROP_PROJECTTYPEID, criteria.getUid());
            }
            if (criteria.getDescritpion() != null) {
                queryObj.addLikeClause(AvProjectType.PROP_DESCRIPTION, criteria.getDescritpion());
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
            ProjectTypeDto dto = Rmt2MediaDtoFactory.getAvProjectTypeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches media type information from the <i>av_media_type</i> table based
     * on the selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instnace of {@link MediaTypeDto} containing values for
     *            selection criteria.
     * @return a List of {@link MediaTypeDto} objects or null if no data was
     *         found.
     * @throws AudioVideoDaoException
     */
    @Override
    public List<MediaTypeDto> fetchMediaType(MediaTypeDto criteria) throws AudioVideoDaoException {
        // Setup criteria
        AvMediaType queryObj = new AvMediaType();
        if (criteria != null) {
            if (criteria.getUid() > 0) {
                queryObj.addCriteria(AvMediaType.PROP_MEDIATYPEID, criteria.getUid());
            }
            if (criteria.getDescritpion() != null) {
                queryObj.addCriteria(AvMediaType.PROP_DESCRIPTION, criteria.getDescritpion());
            }
        }

        // Query data
        List<AvMediaType> results = null;
        try {
            results = this.client.retrieveList(queryObj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new AudioVideoDaoException(e);
        }

        // Package results
        List<MediaTypeDto> list = new ArrayList<>();
        for (AvMediaType item : results) {
            MediaTypeDto dto = Rmt2MediaDtoFactory.getAvMediaTypeInstance(item);
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
        AvArtist a = AudioVideoFactory.createArtistInstance(artist);

        int rc = 0;
        try {
            if (a.getArtistId() == 0) {
                rc = this.insertArtist(a);
                artist.setId(rc);
            }
            if (a.getArtistId() > 0) {
                rc = this.updateArtist(a);
            }
            return rc;
        } catch (AudioVideoDaoException e) {
            this.msg = "Error adding/updating artist, " + artist.getName();
            throw new AudioVideoDaoException(this.msg, e);
        }
    }

    private int insertArtist(AvArtist artist) throws AudioVideoDaoException {
        int rc = 0;
        try {
            rc = this.client.insertRow(artist, true);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Error inserting artist record";
            throw new AudioVideoDaoException(this.msg,e );
        }
    }

    private int updateArtist(AvArtist artist) throws AudioVideoDaoException {
        int rc = 0;
        try {
            rc = this.client.updateRow(artist);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Error updating artist record";
            throw new AudioVideoDaoException(this.msg, e);
        }
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
        AvProject p = AudioVideoFactory.createProjectInstance(proj);
        int rc = 0;
        try {
            if (p.getProjectId() == 0) {
                rc = this.insertProject(p);
                proj.setProjectId(rc);
            }
            if (p.getProjectId() > 0) {
                rc = this.updateProject(p);
            }
            return rc;
        } catch (AudioVideoDaoException e) {
            this.msg = "Error adding/updating project/album, " + proj.getTitle();
            throw new AudioVideoDaoException(this.msg, e);
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
            throw new AudioVideoDaoException(this.msg, e);
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
        AvTracks t = AudioVideoFactory.createTrackInstance(track);
        int rc = 0;
        try {
            if (t.getTrackId() == 0) {
                rc = this.insertTrack(t);
                track.setTrackId(rc);
            }
            if (t.getTrackId() > 0) {
                rc = this.updateTrack(t);
            }
            return rc;
        } catch (AudioVideoDaoException e) {
            this.msg = "Error adding/updating track, " + track.getTrackTitle();
            throw new AudioVideoDaoException(this.msg, e);
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
            throw new AudioVideoDaoException(this.msg, e);
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
            throw new AudioVideoDaoException(this.msg, e);
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

            DatabaseConnectionBean con = (DatabaseConnectionBean) this.client.getConnectionWrapper();
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
}
