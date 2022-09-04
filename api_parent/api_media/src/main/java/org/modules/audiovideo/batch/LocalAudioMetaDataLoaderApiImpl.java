package org.modules.audiovideo.batch;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dao.audiovideo.AudioVideoDao;
import org.dao.audiovideo.AudioVideoDaoConstants;
import org.dao.audiovideo.AudioVideoDaoException;
import org.dao.audiovideo.AudioVideoDaoFactory;
import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dto.ArtistDto;
import org.dto.GenreDto;
import org.dto.ProjectDto;
import org.dto.TracksDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.modules.MediaConstants;
import org.modules.audiovideo.AudioVideoApiException;
import org.modules.audiovideo.AudioVideoFactory;
import org.modules.audiovideo.AvCombinedProjectBean;
import org.modules.audiovideo.AvProjectDataValidationException;
import org.modules.audiovideo.AvTrackDataValidationException;
import org.modules.audiovideo.MP3ApiInstantiationException;
import org.modules.audiovideo.MP3Reader;
import org.modules.audiovideo.Mp3ReaderIdentityNotConfiguredException;

import com.RMT2Constants;
import com.SystemException;
import com.api.BatchFileException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DatabaseException;
import com.api.util.RMT2Date;
import com.api.util.RMT2File;
import com.api.util.RMT2Money;
import com.api.util.RMT2String;

/**
 * An file loader implementation of {@link AudioVideoBatchFileProcessorApi}
 * which extracts metadata from audio files stored locally on the server in
 * which it is executed and imports the data to various tables in the database.
 * <p>
 * The tables targeted for the data import are <i>av_artist</i>,
 * <i>av_project</i>, and <i>av_trackes</i>.
 * 
 * @author Roy Terrell
 * 
 */
class LocalAudioMetaDataLoaderApiImpl extends AbstractTransactionApiImpl implements AvBatchFileProcessorApi {

    private static Logger logger = Logger.getLogger(LocalAudioMetaDataLoaderApiImpl.class);
    
    private static Integer MP3_READER_IMPL_TO_USE;

    private static boolean REQUEST_REFRESH;

    private File directoryPath;

    private List<String> fileErrorMsg;

    private Date startTime;

    private Date endTime;

    protected int successCnt;

    protected int errorCnt;

    protected int nonAvFileCnt;

    protected int totCnt;

    private int expectedFileCount;
    
    private AudioVideoDao avDao;

    private AvBatchImportParameters parms;

    /**
     * Creates a MetaDataFileLoaderApiImpl that does no point to a source
     * directory for batch.
     * 
     * @throws BatchFileProcessException
     */
    protected LocalAudioMetaDataLoaderApiImpl() throws BatchFileProcessException {
        super();
        return;
    }

    /**
     * Creates a MetaDataFileLoaderApiImpl pointing to the source directory for
     * batch processing audio/video files.
     * <p>
     * User is responsible for providing the directory where batch processing
     * starts.
     * 
     * @param parms
     *            an instance of {@link AvBatchImportParameters}
     * @throws BatchFileProcessException
     */
    protected LocalAudioMetaDataLoaderApiImpl(AvBatchImportParameters parms) throws BatchFileProcessException {
        super(MediaConstants.APP_NAME);
        this.initConnection(parms);
        REQUEST_REFRESH = false;
        logger.info("Audio/Video batch processor is initialized.");
        logger.info("Audio/Video batch processing witll begin at this location: " + this.directoryPath.getAbsolutePath());
    }

    /**
     * Setup connection for an arbitrary external datasource which the
     * configuration is known at implementation.
     * 
     * @param parms
     *            a genreic object containing the parmeters needed to identify
     *            the resources to import.
     * 
     * @throws BatchFileProcessException
     */
    public void initConnection(Object parms) throws BatchFileProcessException {
        if (parms == null || !(parms instanceof AvBatchImportParameters)) {
            this.msg = "The audio/video batch import parameters are required";
            LocalAudioMetaDataLoaderApiImpl.logger.error(this.msg);
            throw new InvalidBatchRootDirectoryException(this.msg);
        }
        this.parms = (AvBatchImportParameters) parms;
        if (this.parms.getPath() == null) {
            this.msg = "Path/Location value is required as an audio/video batch import parameter";
            LocalAudioMetaDataLoaderApiImpl.logger.error(this.msg);
            throw new InvalidBatchRootDirectoryException(this.msg);
        }
        
        // Verify that the starting resource path is a directory
        File testFile = new File(this.parms.getAbsolutePath());
        if (RMT2File.verifyDirectory(testFile) != RMT2File.FILE_IO_EXIST) {
            this.msg = testFile + " is required to be a directory for Audio Video Batch process";
            throw new AvSourceNotADirectoryException(this.msg);
        }
        this.directoryPath = testFile;
        this.successCnt = 0;
        this.errorCnt = 0;
        this.totCnt = 0;
        this.nonAvFileCnt = 0;
        this.fileErrorMsg = new ArrayList<String>();
        
        // Setup DAO
        this.avDao = AudioVideoDaoFactory.createRmt2OrmDaoInstance();
        this.avDao.setDaoUser(this.getApiUser());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#close()
     */
    @Override
    public void close() {
        return;
    }

    /**
     * Creates an audoi/video library (audio_video and audio_video_tracks
     * tables) from the files stored locally or remotely. Returns the total
     * number of tracks successfull processed. Data from the tables audio_video
     * and audio_video_tracks are deleted before processing artist's
     * directories.
     * 
     * @return the total count of media resources processed.
     * @throws AudioVideoException
     * @throws AvBatchValidationException
     */
    public int processBatch() throws BatchFileProcessException {
        this.startTime = new Date();
        AudioVideoDao dao = AudioVideoDaoFactory.createRmt2OrmDaoInstance();
        dao.setDaoUser(this.getApiUser());

        // Begin process all files
        try {
            this.expectedFileCount = RMT2File.getDirectoryListingCount(this.directoryPath);
            this.msg = "Audio/Video Batch Update process started [" + this.expectedFileCount + " files discovered]...";
            this.processDirectory(this.directoryPath, null);
            return this.totCnt;
        } catch (BatchFileProcessException e) {
            this.msg = "A batch file error occurred";
            throw new BatchFileProcessException(this.msg, e);
        } catch (Mp3ReaderIdentityNotConfiguredException e) {
            this.msg = "An error occurred trying to identify the MP3Reader implemetation to use";
            throw new BatchFileProcessException(this.msg, e);
        } catch (Exception e) {
            this.msg = "An unknown error was discovered";
            throw new BatchFileProcessException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
            this.endTime = new Date();
            
            logger.info("Batch start time: " + startTime.toString());
            logger.info("Batch end time: " + endTime.toString());
            logger.info("Total Media Files Processed: " + this.totCnt);
            logger.info("Total Media Files Successfully Processed: " + this.successCnt);
            logger.info("Total Media Files Unsuccessfully Processed: " + this.errorCnt);
            logger.info("Total Non-Audio/Video Files encountered: " + this.nonAvFileCnt);
            logger.info("End Audio-Video Update");

            // Send batch report via SMTP
            try {
                this.sendAvBatchReport();
            } catch (AvBatchReportException e) {
                logger.error("Audio/Video Batch Report Failed...See Log for details");
            }
        }

    }

    /**
     * Process all the high level audio/video artist directories.
     * 
     * @param mediaResource
     *            an instance of {@link File} which is required to point to the
     *            directory holding the media files to be processed.
     * @return Always returns an {@link Integer} object which equals "1".
     * @throws BatchFileProcessException
     */
    @Override
    public Object processDirectory(File mediaResource, Object parent) throws BatchFileProcessException {
        File mediaList[];
        int itemCount = 0;
        File mediaFile = null;
        try {
            mediaList = mediaResource.listFiles();
            itemCount = mediaList.length;
            for (int ndx = 0; ndx < itemCount; ndx++) {
                if (mediaList[ndx].isDirectory()) {
                    // Make recursive call to process sub-directroy
                    this.processDirectory(mediaList[ndx], mediaList[ndx]);
                }
                if (mediaList[ndx].isFile()) {
                    mediaFile = mediaList[ndx];
                    this.processSingleFile(mediaFile, parent);
                }
            }
            return 1;
        } catch (SecurityException e) {
            throw new BatchFileProcessException("A problem with security was discovered while processing file, "
                            + mediaFile.getAbsolutePath(), e);
        } catch (MP3ApiInstantiationException e) {
            throw new BatchFileProcessException("A problem instantiating MP3 library was discovered while processing file, "
                            + mediaFile.getAbsolutePath(), e);
        }
    }

    /**
     * Initiates the media file data extraction process.
     * 
     * @param mediaFile
     *            An instance of {@link File} which points to the media file to
     *            be processed
     * @return Always returns a instance of {@link Integer} which equals "1".
     * @throws MP3ApiInstantiationException
     * @throws BatchFileProcessException
     */
    @Override
    public Object processSingleFile(File mediaFile, Object parent) throws BatchFileProcessException {
        String pathName;
        AvCombinedProjectBean avb = null;

        File parentDirectory = (File) parent;
        pathName = mediaFile.getPath();
        logger.log(Level.DEBUG, "Processing File: " + pathName);
        try {
            avb = this.extractFileMetaData(mediaFile);
            if (avb != null) {
                this.addAudioVideoFileData(avb, parentDirectory);
            }
            this.successCnt++;
        } catch (MP3ApiInstantiationException e) {
            if (mediaFile.getName().contains(".mp3")) {
                this.fileErrorMsg.add(this.buildFileWarningMessage(pathName, "Not a valid audio File", e.getMessage()));
            }
            this.nonAvFileCnt++;
        } catch (AvProjectDataValidationException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName, "Project Data Not Valid", e.getMessage()));
            this.errorCnt++;
        } catch (AvTrackDataValidationException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName, "Track Data Not Valid", e.getMessage()));
            this.errorCnt++;
        } catch (SystemException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName, "System Error", e.getMessage()));
            this.errorCnt++;
        } catch (AvFileExtractionException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName, "MetaData Extraction Error", e.getMessage()));
            this.errorCnt++;
        } catch (AudioVideoApiException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName, "General API Error", e.getMessage()));
            this.errorCnt++;
            e.printStackTrace();
        } catch (DatabaseException e) {
            this.fileErrorMsg.add(this.buildFileErrorMessage(pathName, "Database Access Error", e.getMessage()));
            this.errorCnt++;
        } finally {
            this.totCnt++;
        }
        return 1;
    }

    /**
     * Create an instance of MP3Reader based on the implementation selected in
     * configuration.
     * 
     * @param mp3Source
     * @return an instance of {@link MP3Reader}
     * @throws MP3ApiInstantiationException
     */
    protected MP3Reader getMp3ReaderInstance(File mp3Source) {
        // Determine the implementation to use for MP3Reader 
        if (LocalAudioMetaDataLoaderApiImpl.MP3_READER_IMPL_TO_USE == null) {
            String val = null;
            try {
                val = this.getConfig().getProperty(MediaConstants.MP3_READER_TO_USE_CONFIG_KEY);
                LocalAudioMetaDataLoaderApiImpl.MP3_READER_IMPL_TO_USE = Integer.valueOf(val);
            }
            catch (NumberFormatException e) {
                this.msg = "A non-numeric code was discovered to be configured for the MP3 reader implementation to use for this API [" + val + "]";
                throw new Mp3ReaderIdentityNotConfiguredException(this.msg, e);
            }
            catch (Exception e) {
                this.msg = "A general error occurred attempting to read configuration for MP3 reader implementation";
                throw new Mp3ReaderIdentityNotConfiguredException(this.msg, e);
            }
        }
        
        // Create MP3Reader based on selected implementation
        MP3Reader api = null;
        switch (LocalAudioMetaDataLoaderApiImpl.MP3_READER_IMPL_TO_USE) {
            case MediaConstants.MP3_READER_IMPL_MP3AGIC:
                api = AudioVideoFactory.createMp3agicInstance(mp3Source);
                break;
            default:
                this.msg = "An invalid MP3 reader implementation code was specified in configuration: " + LocalAudioMetaDataLoaderApiImpl.MP3_READER_IMPL_TO_USE;
                throw new Mp3ReaderIdentityNotConfiguredException(this.msg);
        }
        return api;
    }
    
    
    /**
     * Reads the tag data from the media file, <i>sourceFile</i>, and packages
     * the data in an instance of AvCombinedProjectBean.
     * <p>
     * Afterwards, initiates the process of updating the database with tag data.
     * 
     * @param mediaFile
     *            the audio/video file to extract data from.
     * @return an instance of {@link AvCombinedProjectBean}
     * @throws AudioVideoApiException
     * @throws Mp3ReaderIdentityNotConfiguredException
     */
    @Override
    public AvCombinedProjectBean extractFileMetaData(File mediaFile) throws AudioVideoApiException {
        if (mediaFile == null) {
            this.msg = "Unable to extract meta data from audio/video file - The source file is invalid or null";
            throw new AvInvalidSourceFileException(this.msg);
        }

        AvCombinedProjectBean avb = new AvCombinedProjectBean();
        AvArtist ava = avb.getAva();
        AvProject av = avb.getAv();
        AvTracks avt = avb.getAvt();

        // Get file name with complet path
        String mediaPath = mediaFile.getPath();
        this.msg = "Extracting meta data from: " + mediaPath;
        logger.info(this.msg);

        // Get the appropriate MP3Reader implementation
        // MP3Reader mp3 = AudioVideoDaoFactory.createJid3mp3WmvApi(sourceFile);
        // MP3Reader mp3 = AudioVideoDaoFactory.createJID3Mp3Api(sourceFile);
        // MP3Reader mp3 = AudioVideoDaoFactory.createMyId3Api(sourceFile);
//        MP3Reader mp3 = AudioVideoDaoFactory.createEntaggedId3Instance(sourceFile);
        
        MP3Reader mp3 = this.getMp3ReaderInstance(mediaFile);
        if (mp3 == null) {
            return null;
        }

        String fileExt = RMT2File.getFileExt(mediaPath);
        if (fileExt.equalsIgnoreCase(".wmv") 
                || fileExt.equalsIgnoreCase(".mp4")
                || fileExt.equalsIgnoreCase(".avi")
                || fileExt.equalsIgnoreCase(".mpg")) {
            av.setProjectTypeId(AudioVideoDaoConstants.PROJ_TYPE_ID_VIDEO);
            av.setMediaTypeId(AudioVideoDaoConstants.MEDIA_TYPE_DVD);
        }
        else {
            // We are assuming that this is an audio file...may need to eliminate dangerous assumption.
            av.setProjectTypeId(AudioVideoDaoConstants.PROJ_TYPE_ID_AUDIO);
            av.setMediaTypeId(AudioVideoDaoConstants.MEDIA_TYPE_CD);
        }

        try {
            // Get Artist
            if (!mp3.getArtist().equalsIgnoreCase(mp3.getAlbumArtist()) && mp3.getAlbumArtist() != null) {
                // This is for Various Artist type album/cd

                // Set artist in the av_artist table to the name of the various
                // artist project.
                ava.setName(mp3.getAlbumArtist());
                // Set track artist in the av_tracks table to actual artist of
                // the track on the various artist project
                avt.setTrackArtist(mp3.getArtist());
            }
            else {
                ava.setName(mp3.getArtist());
            }

            // Get Album
            av.setTitle(mp3.getAlbum());

            // Get Track Number
            avt.setTrackNumber(mp3.getTrack() == 0 ? 1 : mp3.getTrack());

            // Get Track Title
            avt.setTrackTitle(mp3.getTrackTitle());

            try {
                // Get Composer
                avt.setTrackComposer(mp3.getComposer());

                // Get producer
                avt.setTrackProducer(mp3.getProducer());

                // Get Lyricist
                avt.setTrackLyricist(mp3.getLyricist());
            } catch (Exception e) {
                // method may not be implemented..do nothing
            }

            // Get comments.
            avt.setComments(mp3.getComment());
            String comments = mp3.getComment();
            // Make data adjustments in the event we are dealing with a Various
            // Artists type album.
            if (comments != null && comments.contains(AudioVideoDaoConstants.VARIOUS_ARTIST_TOKEN)) {
                // avt.setComments(comments);
                ava.setName(AudioVideoDaoConstants.VARIOUS_ARTIST_NAME);
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
            avt.setTrackDisc(mp3.getDiscNumber() == 0 ? "1" : String.valueOf(mp3.getDiscNumber()));

            // Capture the media file location data
            avt.setLocServername(this.parms.getServerName());
            avt.setLocSharename(this.parms.getShareName());
            avt.setLocRootPath(this.parms.getRootPath());
            String pathOnly = RMT2File.getFilePathInfo(mediaPath);
            avt.setLocPath(pathOnly);
            av.setContentPath(pathOnly);
            av.setArtWorkPath(pathOnly);

            String image = this.getImageFile(pathOnly);
            av.setArtWorkFilename(image);

            // Set File name
            String fileName = mediaFile.getName();
            avt.setLocFilename(fileName);

            try {
                String unc[] = RMT2File.getUNCFilename(mediaFile.getCanonicalPath());
                if (unc != null && unc.length > 0) {
                    avt.setLocServername(unc[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Initialized Ripped flag
            av.setRipped(mediaPath.indexOf(AudioVideoDaoConstants.DIRNAME_NON_RIPPED) > -1 ? 0 : 1);
            return avb;
        } catch (Exception e) {
            this.msg = "Audio/Video file extraction error for " + mediaPath;
            logger.error(this.msg, e);
            throw new AvFileExtractionException(this.msg, e);
        }
    }
    
    private String getImageFile(String dir) {
        List<String> listing = RMT2File.getDirectoryListing(dir, "*.jpg");
        // Try to return folder.jpg, if it exists
        int ndx = 0;
        String firstAvailable = null;
        for (String item : listing) {
            if (ndx++ == 0) {
                firstAvailable = item;
            }
            if (item.equalsIgnoreCase("folder.jpg")) {
                return item;
            }
        }

        return firstAvailable;
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
     * @param parentDirectory
     *            the directory containing the information gathered for
     *            <i>avProj<i>.
     * @return The total number of tracks added for the artist's project.
     * @throws AudioVideoApiException
     */
    protected int addAudioVideoFileData(AvCombinedProjectBean avProj, File parentDirectory) throws AudioVideoApiException {
        // Process artist
        int artistId = this.insertArtistFromFile(avProj);
        avProj.getAv().setArtistId(artistId);
        
        // Process Project/Album
        int projectId = this.insertProjectFromFile(avProj, parentDirectory);
        avProj.getAvt().setProjectId(projectId);
        
        // Process track
        this.insertTrackFromFile(avProj);
        return projectId;
    }
    
    private int insertArtistFromFile(AvCombinedProjectBean avProj) throws AudioVideoApiException {
        AvArtist artist = avProj.getAva();
        ArtistDto artistDto = Rmt2MediaDtoFactory.getAvArtistInstance(artist);
        this.validateArtist(artistDto);
        int artistId = 0;
        
        // Check if artist exists
        try {
            if (artist.getArtistId() == 0) {
                ArtistDto artistCriteria = Rmt2MediaDtoFactory.getAvArtistInstance(null);
                String temp = RMT2String.replaceAll2(artist.getName(), "''", "'");
                String customSql = " name = '" + temp + "' ";
                artistCriteria.setCriteria(customSql);
                List<ArtistDto> a = this.avDao.fetchArtist(artistCriteria);
                if (a != null && a.size() == 1) {
                    artistId = a.get(0).getId();
                    artistDto.setId(artistId);
                    artistDto.setDateCreated(a.get(0).getDateCreated());
                    artist.setArtistId(artistId);
                }
            }    
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Unable to verify artist existence by artist name: " + artistDto.getName(), e); 
        }
        
        // Since artist does not exist, add it.
        try {
            if (artist.getArtistId() == 0) {
                artistId = this.avDao.maintainArtist(artistDto);
                logger.info("Added Artist: " + artistDto.getName());
            }
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Unable to create artist: " + artistDto.getName(), e); 
        }
        return artistId;
    }

    private int insertProjectFromFile(AvCombinedProjectBean avProj, File parentDirectory) throws AudioVideoApiException {
        AvProject project = avProj.getAv();
        ProjectDto projectDto = Rmt2MediaDtoFactory.getAvProjectInstance(project);
        int projectId = 0;
        this.validateProject(projectDto);

        // Genre value may have been obtained as a UID value in the format of
        // (n) or as its name.
        int genreId = this.getGenreId(avProj);

        // Continue processing the rest of "project"
        project.setGenreId(genreId);
        projectDto.setGenreId(genreId);
        
        // Verify if project already exists
        if (project.getProjectId() == 0) {
            ProjectDto projCriteria = Rmt2MediaDtoFactory.getAvProjectInstance(null);
            
            // Verify the existence of project by artist/project title
            String temp = RMT2String.replaceAll2(project.getTitle(), "''", "'");
            String customSql = " title = '" + temp + "' ";
            projCriteria.setCriteria(customSql);
            projCriteria.setArtistId(project.getArtistId());
            List<ProjectDto> p = null;
            try {
                p = this.avDao.fetchProject(projCriteria);    
            }
            catch (AudioVideoDaoException e) {
                throw new AudioVideoApiException(
                        "Unable to verify project/album existence by artist id and title ["
                                + project.getArtistId() + ", " + projectDto.getTitle() + "]", e);
            }
            if (p != null && p.size() == 1) {
                projectId = p.get(0).getProjectId();
                project.setProjectId(projectId);
                projectDto.setProjectId(projectId);
            }
            else {
                // Verify the existence of project by project title/content path
                // (This is typically for albums with multiple artists).
                projCriteria = Rmt2MediaDtoFactory.getAvProjectInstance(null);
                projCriteria.setCriteria(" title = '" + temp + "' ");
                projCriteria.setContentPath(project.getContentPath());
                try {
                    p = this.avDao.fetchProject(projCriteria);    
                }
                catch (AudioVideoDaoException e) {
                    throw new AudioVideoApiException(
                            "Unable to verify project/album existence by title and project content path ["
                                    + projectDto.getTitle() + ", "
                                    + projectDto.getContentPath() + "]", e);
                }
                if (p != null && p.size() == 1) {
                    projectId = p.get(0).getProjectId();
                    project.setProjectId(projectId);
                    projectDto.setProjectId(projectId);
                }
            }
        }
        
        try {
            if (projectId == 0) {
                // Create project/album
                projectId = this.avDao.maintainProject(projectDto);
                logger.info("Added Album: " + projectDto.getTitle());
            }
            else {
                if (REQUEST_REFRESH) {
                    this.avDao.maintainProject(projectDto);
                    logger.info("Updated Album: " + projectDto.getTitle());
                }
            }    
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Unable to create/modify project/album: " + project.getTitle(), e); 
        }
        return projectId;
    }

    private int insertTrackFromFile(AvCombinedProjectBean avProj) throws AudioVideoApiException {
        AvTracks track = avProj.getAvt();
        track.setGenreId(avProj.getAv().getGenreId());
        TracksDto trackDto = Rmt2MediaDtoFactory.getAvTrackInstance(track);
        int trackId = 0;
        this.validateTarck(trackDto);

        // Verify that track does not exists for this project
        try {
            if (track.getTrackId() == 0) {
                TracksDto criteria = Rmt2MediaDtoFactory.getAvTrackInstance(null);
                criteria.setProjectId(track.getProjectId());

                String escapedValue = RMT2String.replaceAll2(track.getTrackTitle(), "''", "'");
                String customSql = " track_title = '" + escapedValue + "' ";

                criteria.setCriteria(customSql);
                List<TracksDto> t = this.avDao.fetchTrack(criteria);
                if (t != null && t.size() == 1) {
                    trackId = t.get(0).getTrackId();
                }
            }
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Unable to verify track existence: " + track.getTrackTitle(), e); 
        }
       
        try {
            if (trackId == 0) {
                // Create new track
                trackId = this.avDao.maintainTrack(trackDto);
                track.setTrackId(trackId);
                trackDto.setTrackId(trackId);
                logger.info("Added Track: " + trackDto.getTrackTitle());
            }
            else {
                // Update existing track
                if (REQUEST_REFRESH) {
                    this.avDao.maintainTrack(trackDto);
                    logger.info("Updated Track: " + trackDto.getTrackTitle());
                }
            }    
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Unable to create/modify track: " + track.getTrackTitle(), e); 
        }
        return trackId;
    }
    
    private int getGenreId(AvCombinedProjectBean avProj) {
        GenreDto genreCriteria = Rmt2MediaDtoFactory.getAvGenreInstance(null);
        int genreId = AudioVideoDaoConstants.UNKNOWN_GENRE;
        String genreValue = avProj.getGenre();
        if (genreValue != null) {
            if (genreValue.contains("(") && genreValue.contains(")")) {
                String genreIdString = RMT2String.replace(RMT2String.replace(genreValue, "", "("), "", ")");
                if (RMT2Money.isNumeric(genreIdString)) {
                    int temp = Integer.valueOf(genreIdString);
                    genreCriteria.setUid(temp);
                }
                else {
                    genreCriteria.setDescription(genreValue);
                }
            }
            else {
                genreCriteria.setDescription(genreValue);
            }
            List<GenreDto> g = this.avDao.fetchGenre(genreCriteria);
            if (g != null && g.size() == 1) {
                genreId = g.get(0).getUid();
            }
        }
        return genreId;
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
    protected void validateArtist(ArtistDto artist) throws AvProjectDataValidationException {
        if (artist == null) {
            this.msg = "Artist object is invalid or null";
            throw new AvProjectDataValidationException(this.msg);
        }
        if (artist.getName() == null) {
            throw new AvProjectDataValidationException("Artist name is required");
        }
        return;
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
    protected void validateProject(ProjectDto proj) throws AvProjectDataValidationException {
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
    protected void validateTarck(TracksDto track) throws AvProjectDataValidationException {
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
     * 
     * @param fileName
     * @param msgCatg
     * @param msg
     * @return
     */
    private String buildFileErrorMessage(String fileName, String msgCatg, String msg) {
        StringBuffer errMsg = new StringBuffer();
        errMsg.append("File: ");
        errMsg.append(fileName);
        errMsg.append("\n");
        errMsg.append("Error Category: ");
        errMsg.append(msgCatg);
        errMsg.append("\n");
        errMsg.append("Cause: ");
        if (msg == null) {
            msg = "Unknown";
        }
        errMsg.append(msg);
        errMsg.append("\n");

        String m = errMsg.toString();
        System.out.println("Error ===> " + m);
        logger.log(Level.ERROR, "Error ===> " + m);
        return m;
    }

    /**
     * 
     * @param fileName
     * @param msgCatg
     * @param msg
     * @return
     */
    private String buildFileWarningMessage(String fileName, String msgCatg, String msg) {
        StringBuffer errMsg = new StringBuffer();
        errMsg.append("File: ");
        errMsg.append(fileName);
        errMsg.append("\n");
        errMsg.append("Waring Category: ");
        errMsg.append(msgCatg);
        errMsg.append("\n");
        errMsg.append("Cause: ");
        if (msg == null) {
            msg = "Unknown";
        }
        errMsg.append(msg);
        errMsg.append("\n");

        String m = errMsg.toString();
        System.out.println("Error ===> " + m);
        logger.log(Level.WARN, "Warning ===> " + m);
        return m;
    }

    /**
     * Creates a report detailing the success or failure of all the files
     * processed and transmits the report via SMTP to the user designated as the
     * application's email recipient.
     * 
     * @throws AvBatchReportException
     *             problem occurred creaing or sending file drop report via
     *             SMTP.
     */
    private void sendAvBatchReport() throws AvBatchReportException {
        StringBuffer body = new StringBuffer();

        // Attempt to obtain From email address from the application's property
        // pool which is loaded at server start up.
        String fromAddr = null;
        try {
            fromAddr = this.getConfig().getProperty(MediaConstants.BATCH_FILE_IMPORT_REPORT_EMAIL);
        } catch (Exception e) {
            this.msg = "Unable to obtain the recipient's email address from AppParms.properties needed to send batch report";
            throw new AvBatchReportException(this.msg, e);
        }
        if (fromAddr == null) {
            this.msg = "Unable to send batch report due to the recipient's email address is not available.   Check configuation.";
            throw new AvBatchReportException(this.msg);
        }
        String toAddr = fromAddr;
        String subject = this.getConfig().getProperty(MediaConstants.BATCH_FILE_IMPORT_REPORT_SUBJECT);

        body.append("This is a report of the results of the Audio/Video Batch File Import process\n");
        body.append("Start Time: ");
        body.append(RMT2Date.formatDate(this.startTime, "MM-dd-yyyy HH:mm:ss.S"));
        body.append("\n");
        body.append("End Time: ");
        body.append(RMT2Date.formatDate(this.endTime, "MM-dd-yyyy HH:mm:ss.S"));
        body.append("\n\n\n");

        body.append("Total Media Files Processed: " + this.totCnt);
        body.append("\n");
        body.append("Total Media Files Successfully Processed: " + this.successCnt);
        body.append("\n");
        body.append("Total Media Files Unsuccessfully Processed: " + this.errorCnt);
        body.append("\n");
        body.append("Total non-Media Files Encountered: " + this.nonAvFileCnt);
        body.append("\n\n\n");

        if (this.fileErrorMsg.size() > 0) {
            body.append("Detail report of files that failed during this batch process");
            body.append("\n");
            body.append("=============================================================");
            body.append("\n");
        }
        else {
            body.append("All audio/video files processed successfully!");
            body.append("\n");
        }
        int count = 0;
        // Add details about each file that was processed
        for (String msg : this.fileErrorMsg) {
            count++;
            body.append(count);
            body.append(".  ");
            body.append(msg);
            body.append("\n");
        }

        // IS-70: Added logic to dynamically determine the context path of the
        // AppServer configuration location.
        String errorFile = this.getAppServerContextPath() + "AppServer/log/media_import_error_report.txt";
        RMT2File.outputFile(body.toString(), errorFile);
        
        // // Setup bean that represents the email message.
        // EmailMessageBean bean = new EmailMessageBean();
        // bean.setFromAddress(fromAddr);
        //
        // // You can optionally enter multiple email addresses separated by
        // commas
        // bean.setToAddress(toAddr);
        // bean.setSubject(subject);
        // bean.setBody(body.toString(), EmailMessageBean.TEXT_CONTENT);
        //
        // // Declare and initialize SMTP api and allow the system to discover
        // SMTP
        // // host
        // SmtpApi api = SmtpFactory.getSmtpInstance();
        // // Send simple email to its intended destination
        // try {
        // api.sendMessage(bean);
        // // Close the service.
        // api.close();
        // } catch (Exception e) {
        // throw new AvBatchReportException(e);
        // }
        return;
    }
    
    @Override
    public int getErrorCount() {
        return this.errorCnt;
    }

    @Override
    public int getSuccessCount() {
        return this.successCnt;
    }

    @Override
    public List<String> getErrorMessages() {
        return this.fileErrorMsg;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#getFileListing()
     */
    public List<String> getFileListing() {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#initConnection()
     */
    public void initConnection() throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#initConnection(java.lang.String,
     * java.lang.String)
     */
    public void initConnection(String arg0, String arg1)
            throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#processFiles(java.util.List)
     */
    public Object processFiles(List<String> arg0, Object parent)
            throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#processSingleFile(java.lang.String)
     */
    public Object processSingleFile(String arg0, Object parent)
            throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @return the successCnt
     */
    public int getSuccessCnt() {
        return successCnt;
    }

    /**
     * @return the errorCnt
     */
    public int getErrorCnt() {
        return errorCnt;
    }

    /**
     * @return the nonAvFileCnt
     */
    public int getNonAvFileCnt() {
        return nonAvFileCnt;
    }

    /**
     * @return the totCnt
     */
    public int getTotCnt() {
        return totCnt;
    }

    /**
     * @return the fileErrorMsg
     */
    public List<String> getFileErrorMsg() {
        return fileErrorMsg;
    }
}
