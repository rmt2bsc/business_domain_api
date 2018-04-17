package org.rmt2.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.modules.MediaConfigurator;
import org.powermock.api.mockito.PowerMockito;

import com.api.persistence.DaoClient;
import com.api.persistence.PersistenceClient;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Common functionality for mocking DAO classes
 * 
 * @author Roy Terrell
 *
 */

public class BaseMediaDaoTest {
    protected PersistenceClient mockPersistenceClient;
    protected DaoClient mockDaoClient;

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
        MediaConfigurator configurator = new MediaConfigurator();
        configurator.start();
        
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
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        return;
    }

}
