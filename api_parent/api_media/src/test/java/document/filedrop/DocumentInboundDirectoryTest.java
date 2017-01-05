package document.filedrop;

import org.apache.log4j.Logger;
import org.dao.document.ContentDao;
import org.dao.document.ContentDaoFactory;
import org.dao.document.file.FileDropProcessingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.services.DocumentInboundDirectoryListener;

import com.util.RMT2File;

/**
 * @author rterrell
 * 
 */
public class DocumentInboundDirectoryTest {

    private ContentDaoFactory f;

    private ContentDao dao;

    private Logger logger;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        RMT2File.loadSystemProperties("config.MultimediaTestSystemParms");

        // // Setup Logging environment
        // String logPath = "config.log4j";
        // Properties props = RMT2File.loadPropertiesFromClasspath(logPath);
        // PropertyConfigurator.configure(props);
        // logger = Logger.getLogger(StandAloneCoreSysConfigurator.class);
        // logger.info("Begin Application Configuration...");

        this.f = new ContentDaoFactory();
        this.dao = this.f.createEmbeddedMediaDaoInstance();
        this.dao.setDaoUser("TestUser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    @Test
    public void testBatchMediaFileProcessor() {
        DocumentInboundDirectoryListener l = new DocumentInboundDirectoryListener();
        try {
            l.processMultiMediaFiles();
        } catch (FileDropProcessingException e) {
            e.printStackTrace();
        }
        ;
    }

}
