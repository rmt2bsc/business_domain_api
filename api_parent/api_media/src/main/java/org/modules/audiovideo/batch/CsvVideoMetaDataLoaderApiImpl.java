package org.modules.audiovideo.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dao.audiovideo.AudioVideoDao;
import org.dao.audiovideo.AudioVideoDaoConstants;
import org.dao.audiovideo.AudioVideoDaoException;
import org.dao.audiovideo.AudioVideoDaoFactory;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dto.GenreDto;
import org.dto.ProjectDto;
import org.dto.TracksDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.modules.MediaConstants;
import org.modules.audiovideo.AudioVideoApiException;
import org.modules.audiovideo.AvCombinedProjectBean;
import org.modules.audiovideo.AvProjectDataValidationException;

import com.RMT2Constants;
import com.api.BatchFileException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.util.RMT2Date;
import com.api.util.RMT2File;
import com.api.util.RMT2Money;
import com.api.util.RMT2String;

/**
 * A comma separated values implementation of
 * {@link AudioVideoBatchFileProcessorApi} for loading video meta data into
 * various tables in the database.
 * <p>
 * The tables targeted for the data import are <i>av_project</i>, and
 * <i>av_trackes</i>.
 * 
 * @author Roy Terrell
 * 
 */
class CsvVideoMetaDataLoaderApiImpl extends AbstractTransactionApiImpl implements AvBatchFileProcessorApi {

    private static Logger logger = Logger.getLogger(CsvVideoMetaDataLoaderApiImpl.class);
    

    private BufferedReader csvReader;
    private String videoPath;
    private List<String> fileErrorMsg;
    private Date startTime;
    private Date endTime;
    protected int successCnt;
    protected int errorCnt;
    protected int nonAvFileCnt;
    protected int totCnt;
    private AudioVideoDao avDao;

    /**
     * Creates a CsvVideoMetaDataLoaderApiImpl that does no point to a source
     * directory for batch.
     * 
     * @throws BatchFileProcessException
     */
    protected CsvVideoMetaDataLoaderApiImpl() throws BatchFileProcessException {
        super();
        return;
    }

    /**
     * Creates a CsvVideoMetaDataLoaderApiImpl pointing to the source directory
     * for batch processing audio/video files.
     * <p>
     * User is responsible for providing the directory where batch processing
     * starts.
     * 
     * @param dirPath
     *            the complete path where to start processing audio/video files
     * @throws BatchFileProcessException
     */
    protected CsvVideoMetaDataLoaderApiImpl(String dirPath) throws BatchFileProcessException {
        super(MediaConstants.APP_NAME);
        this.initConnection(dirPath);
        logger.info("Video batch processor is initialized.");
        logger.info("Video batch processing will begin at this location: " + this.videoPath);
    }

    /**
     * Setup connection for an arbitrary external datasource which the
     * configuration is known at implementation.
     * 
     * @param dirPath
     *            a String representing the directory path process audio/video
     *            files
     * 
     * @throws BatchFileProcessException
     */
    public void initConnection(Object dirPath) throws BatchFileProcessException {
        if (dirPath != null) {
            this.videoPath = dirPath.toString();
        }
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
            this.msg = "Video Batch Update process started";
            logger.info(this.msg);
            this.processSingleFile(MediaConstants.VIDEO_IMPORT_DATAFILE_PATH, null);
            return this.totCnt;
        } catch (BatchFileProcessException e) {
            this.msg = "A batch file error occurred";
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
        try {
            this.extractFileMetaData(mediaFile);
        } catch (AudioVideoApiException e1) {
            e1.printStackTrace();
        }
        return 1;
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

        try {
            this.csvReader = new BufferedReader(new FileReader(mediaFile));
            String row = null;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                AvCombinedProjectBean avb = this.extractFileMetaData(data);
                try {
                    this.addAudioVideoFileData(avb);
                    this.successCnt++;
                } catch (AudioVideoApiException e) {
                    logger.error("Error adding meta data for video, " + data[0] + ", to database", e);
                    this.errorCnt++;
                } finally {
                    this.totCnt++;
                }
            }
            return null;
        } catch (FileNotFoundException e) {
            throw new AudioVideoApiException("Error setting up connection to video batch import file", e);
        } catch (IOException e) {
            throw new AudioVideoApiException("Error reading line in video batch import file", e);
        } finally {
            try {
                csvReader.close();
            } catch (IOException e) {
                throw new AudioVideoApiException("Error closing video batch import file", e);
            }
        }
    }

    private AvCombinedProjectBean extractFileMetaData(String[] d) {
        AvCombinedProjectBean avb = new AvCombinedProjectBean();
        AvProject avp = avb.getAv();
        AvTracks avt = avb.getAvt();
        avp.setProjectTypeId(AudioVideoDaoConstants.PROJ_TYPE_ID_VIDEO);

        // Get movie title
        avp.setTitle(d[0]);

        // Get movie producer/director
        avt.setTrackProducer(d[1]);

        // Get Genre
        avp.setGenreId(Integer.valueOf(d[2]));
        avt.setGenreId(Integer.valueOf(d[2]));

        // Media type
        avp.setMediaTypeId(Integer.valueOf(d[3]));

        // Get Year Released
        avp.setYear(Integer.valueOf(d[4]));

        // Get Recording Time
        int hours = 0;
        int mins = (d[6] != null && RMT2Money.isNumeric(d[6]) ? Integer.valueOf(d[6]) : 0);
        if (d[5] != null && RMT2Money.isNumeric(d[5])) {
            hours = Integer.valueOf(d[5]);
            mins += (hours * 60);
        }
        int secs = (d[7] != null && RMT2Money.isNumeric(d[7]) ? Integer.valueOf(d[7]) : 0);
        avt.setTrackHours(hours);
        avt.setTrackMinutes(mins);
        avt.setTrackSeconds(secs);

        // Get cost
        double cost = (d[8] != null && RMT2Money.isNumeric(d[8]) ? Integer.valueOf(d[8]) : 2.99);
        avp.setCost(cost);

        // Set track number to 1 by default
        avt.setTrackNumber(1);

        // Add cast memebers to project comments.
        avp.setProjectComments(d[9]);

        // Get Disc Number
        avt.setTrackDisc("1");

        // Initialized Ripped flag
        avp.setRipped((d[11] != null && RMT2Money.isNumeric(d[11]) ? Integer.valueOf(d[11]) : 0));

        // Set File name
        String fileName = d[12];
        avp.setContentFilename(fileName);

        // Set artwork filename
        if (fileName != null && !fileName.isEmpty()) {
            List<String> tokens = RMT2String.getTokens(fileName, ".");
            avp.setArtWorkFilename(tokens.get(0) + ".jpg");
        }

        // Set path for content file name and artwork filename
        avp.setContentPath(this.videoPath);
        avp.setArtWorkPath(this.videoPath);
        return avb;
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
     * @throws AudioVideoApiException
     */
    protected int addAudioVideoFileData(AvCombinedProjectBean avProj) throws AudioVideoApiException {
        // Process movie project
        int projectId = this.insertProjectFromFile(avProj);
        avProj.getAvt().setProjectId(projectId);
        
        // Process track
        this.insertTrackFromFile(avProj);
        return projectId;
    }
    

    private int insertProjectFromFile(AvCombinedProjectBean avProj) throws AudioVideoApiException {
        AvProject project = avProj.getAv();
        ProjectDto projectDto = Rmt2MediaDtoFactory.getAvProjectInstance(project);
        int projectId = 0;
        this.validateProject(projectDto);
        try {
            if (projectDto.getProjectId() == 0) {
                // Create project/album
                projectId = this.avDao.maintainProject(projectDto);
                logger.info("Added Video Project: " + projectDto.getTitle());
            }
        }
        catch (AudioVideoDaoException e) {
            throw new AudioVideoApiException("Unable to create video project: " + project.getTitle(), e);
        }
        return projectId;
    }

    private int insertTrackFromFile(AvCombinedProjectBean avProj) throws AudioVideoApiException {
        AvTracks track = avProj.getAvt();
        track.setGenreId(avProj.getAv().getGenreId());
        TracksDto trackDto = Rmt2MediaDtoFactory.getAvTrackInstance(track);
        int trackId = 0;
        this.validateTarck(trackDto);
       
        try {
            if (trackDto.getTrackId() == 0) {
                // Create new track
                trackId = this.avDao.maintainTrack(trackDto);
                track.setTrackId(trackId);
                trackDto.setTrackId(trackId);
                logger.info("Added Video Track information for: " + avProj.getAv().getTitle());
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

        RMT2File.outputFile(body.toString(), "c:/tmp/log/media_import_error_report.txt");
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
    public Object processSingleFile(String importFile, Object parent) throws BatchFileException {
        File csv = RMT2File.getFileInstanceFromClassPath(importFile);
        return this.processSingleFile(csv, null);
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

    @Override
    public Object processDirectory(File dir, Object parent) throws BatchFileException {
        return null;
    }
}
