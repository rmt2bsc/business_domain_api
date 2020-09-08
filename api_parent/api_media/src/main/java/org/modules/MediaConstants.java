package org.modules;

/**
 * Media application constants
 * 
 * @author roy.terrell
 *
 */
public class MediaConstants {
    public static final String CONFIG_CLASSPATH = "config";
    public static final String DEFAULT_CONTEXT_NAME = "Media";
    public static final String APP_NAME = "Media";
    /**
     * The internal user id for timestamping database update activity in regards
     * to processing media files
     */
    public static final String UPDATE_USERID = "media_api_user";
    
    /**
     * Wildcard to identify accounting related image files
     */
    public static final String MODULE_FILEPATTERN_ACCOUNTING = "acct_*.*";
    
    /**
     * Wildcard to identify project tracker related image files
     */
    public static final String MODULE_FILEPATTERN_PROJECTTRACKER = "proj_ts*.*";

    /**
     * The key name used in the configuration to identify the MP3Reader to use.
     */
    public static final String MP3_READER_TO_USE_CONFIG_KEY = "mp3ReaderToUse";
    
    /**
     * Code for Entagged MP3 File reader.
     */
    public static final int MP3_READER_IMPL_ENTAGGED = 1000;
    
    /**
     * Code for Id3 MP3 WMV File reader.
     */
    public static final int MP3_READER_IMPL_ID3MP3WMV = 2000;
    
    /**
     * Code for JID3 MP3 File reader.
     */
    public static final int MP3_READER_IMPL_JID3 = 3000;
    
    /**
     * Code for MyId3 MP3 File reader.
     */
    public static final int MP3_READER_IMPL_MYID3 = 4000;
    
    /**
     * Audio Video batch file import subject line key
     */
    public static final String BATCH_FILE_IMPORT_REPORT_SUBJECT = "audioVideoBatchImportSubject";
    
    /**
     * Audio Video batch file import email key
     */
    public static final String BATCH_FILE_IMPORT_REPORT_EMAIL = "audioVideoBatchImportEmail";
    
    /**
     * Path to video csv import file
     */
    public static final String VIDEO_IMPORT_DATAFILE_PATH = "data/video_batch_import.txt";

    /**
     * Default constructor
     */
    public MediaConstants() {
        return;
    }

}
