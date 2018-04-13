package audiovideo.batch;

import junit.framework.Assert;

import org.dao.audiovideo.AudioVideoConstants;
import org.dao.audiovideo.AudioVideoDao;
import org.dao.audiovideo.AudioVideoDaoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.audiovideo.batch.AvBatchApiFactory;
import org.modules.audiovideo.batch.AvBatchFileProcessorApi;

import com.api.foundation.TransactionApi;
import com.util.RMT2File;

/**
 * @author appdev
 * 
 */
public class AudioBatchProcessTest {

    private AvBatchApiFactory f;

    private AvBatchFileProcessorApi api;

    private AudioVideoDaoFactory f2;

    private AudioVideoDao dao;

    private static String ROOT_DIR;

    private static String RIPPED_DIR;

    private static String NON_RIPPED_DIR;

    // This works when the computer is acting as a stand alone machine that is
    // not connected to a network.
    // private String allDir = "C:/data/music/test/";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new AvBatchApiFactory();
        this.f2 = new AudioVideoDaoFactory();
        this.dao = this.f2.createRmt2OrmDaoInstance();
        this.dao.setDaoUser("testuser");

        // Load values from config file
        ROOT_DIR = RMT2File.getPropertyValue("config.MimeConfig_DEV",
                "LOADER_ROOT_DIR");
        RIPPED_DIR = RMT2File.getPropertyValue("config.MimeConfig_DEV",
                "LOADER_RIPPED_DIR");
        NON_RIPPED_DIR = RMT2File.getPropertyValue("config.MimeConfig_DEV",
                "LOADER_NONRIPPED_DIR");
        String host = RMT2File.getPropertyValue("config.MimeConfig_DEV",
                "mail.host.smtp");
        String auth = RMT2File.getPropertyValue("config.MimeConfig_DEV",
                "mail.authentication");
        String uid = RMT2File.getPropertyValue("config.MimeConfig_DEV",
                "mail.userId");
        String pw = RMT2File.getPropertyValue("config.MimeConfig_DEV",
                "mail.password");

        // Set system properties from config file
        System.setProperty("mail.host.smtp", host);
        System.setProperty("mail.authentication", auth);
        System.setProperty("mail.userId", uid);
        System.setProperty("mail.password", pw);

        this.testPurge();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        if (this.dao != null) {
            this.dao.close();
            this.dao = null;
        }
        if (this.api != null) {
            this.api.close();
            this.api = null;
        }
    }

    // @Test
    // public void calculateFiles() {
    // int count =
    // this.dao.computeTotalFileCount(AudioBatchProcessTest.ROOT_DIR);
    // Assert.assertTrue(count >= 0);
    // }
    //
    //
    // @Test
    // public void testRipped() {
    // String dir = AudioBatchProcessTest.ROOT_DIR + RIPPED_DIR;
    // this.api = this.f.createRmt2OrmBatchLoaderDaoInstance(dir);
    // ((TransactionApi)
    // this.api).setApiUser(AudioVideoConstants.LOADER_USERID);
    // try {
    // this.api.processBatch();
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // }
    //
    // @Test
    // public void testNonRipped() {
    // String dir = AudioBatchProcessTest.ROOT_DIR + NON_RIPPED_DIR;
    // this.api = this.f.createRmt2OrmBatchLoaderDaoInstance(dir);
    // ((TransactionApi)
    // this.api).setApiUser(AudioVideoConstants.LOADER_USERID);
    // try {
    // this.api.processBatch();
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // }

    @Test
    public void testAll() {
        String dir = AudioBatchProcessTest.ROOT_DIR;
        this.api = this.f.createRmt2OrmBatchLoaderDaoInstance(dir);
        ((TransactionApi) this.api)
                .setApiUser(AudioVideoConstants.LOADER_USERID);
        try {
            this.api.processBatch();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    public void testPurge() {
        AudioVideoDaoFactory f = new AudioVideoDaoFactory();
        AudioVideoDao avDao = f.createRmt2OrmDaoInstance();
        avDao.setDaoUser(AudioVideoConstants.LOADER_USERID);
        int rows = 0;
        avDao = f.createRmt2OrmDaoInstance();
        try {
            rows = avDao.purge(AudioVideoConstants.PROJ_TYPE_ID_AUDIO);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } finally {
            avDao.close();
            avDao = null;
        }
        Assert.assertTrue(rows >= 0);
    }

}
