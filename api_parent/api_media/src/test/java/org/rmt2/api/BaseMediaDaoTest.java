package org.rmt2.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.modules.MediaConfigurator;
import org.modules.services.document.directory.file.BatchMediaFileProcessorImpl;
import org.modules.services.document.directory.file.MediaFileFactory;
import org.powermock.api.mockito.PowerMockito;

import com.api.config.ConfigConstants;
import com.api.persistence.DaoClient;
import com.api.persistence.PersistenceClient;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2SystemUtility;

/**
 * Common functionality for mocking DAO classes
 * 
 * @author Roy Terrell
 *
 */

public class BaseMediaDaoTest {
    protected PersistenceClient mockPersistenceClient;
    protected DaoClient mockDaoClient;
    protected String appServerContextPath;

    /**
     * 
     */
    public BaseMediaDaoTest() {
        return;
    }

    /**
     * Initialize contact factory and contact api mock objects.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        System.setProperty(ConfigConstants.PROPNAME_ENV, ConfigConstants.ENVTYPE_TEST);
        
        MediaConfigurator configurator = new MediaConfigurator();
        configurator.start();
        
        // IS-70: Determine the context path for AppServer configuration based
        // on the operating system
        if (RMT2SystemUtility.isMacOSNewGenerationArchitecture()) {
            System.setProperty(ConfigConstants.PROPNAME_APPSERVER_CONFIG_CONTEXTPATH, "/System/Volumes/Data/");
        }
        else {
            System.setProperty(ConfigConstants.PROPNAME_APPSERVER_CONFIG_CONTEXTPATH, "/");
        }
        this.appServerContextPath = System.getProperty(ConfigConstants.PROPNAME_APPSERVER_CONFIG_CONTEXTPATH);

        // Mock database connection since the common transaction Api expects
        // derived Api modules to obtain and pass in an instance of DaoClient.
        PowerMockito.mockStatic(Rmt2OrmClientFactory.class);
        this.mockDaoClient = Mockito.mock(DaoClient.class);
        this.mockPersistenceClient = Mockito.mock(PersistenceClient.class);
        when(this.mockDaoClient.getClient()).thenReturn(this.mockPersistenceClient);
        when(Rmt2OrmClientFactory.createOrmClientInstance(any(String.class))).thenReturn(this.mockPersistenceClient);
        return;
    }

    /**
     * Mock Batch File processing tasks for Inbound Directory Listener
     * 
     * @throws Exception
     */
    protected void mockListenerBatchProcess() throws Exception {
        // 
        PowerMockito.mockStatic(MediaFileFactory.class);
        BatchMediaFileProcessorImpl mockBatchMediaFileProcessorDaoImpl = Mockito.mock(BatchMediaFileProcessorImpl.class);
        when(MediaFileFactory.createBatchFileProcessor()).thenReturn(mockBatchMediaFileProcessorDaoImpl);
        doNothing().when(mockBatchMediaFileProcessorDaoImpl).initConnection();
        when(mockBatchMediaFileProcessorDaoImpl.processBatch()).thenReturn(0); 
    }
    
    /**
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        return;
    }

}
