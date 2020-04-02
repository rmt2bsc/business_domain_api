package org.rmt2.api;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import org.modules.AccountingConfigurator;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import com.api.config.AppPropertyPool;
import com.api.config.ConfigConstants;
import com.api.persistence.DaoClient;
import com.api.persistence.PersistenceClient;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Common functionality for mocking DAO classes
 * 
 * @author Roy Terrell
 *
 */

@PrepareForTest({ Rmt2OrmClientFactory.class, AppPropertyPool.class })
public class BaseAccountingDaoTest {
    protected PersistenceClient mockPersistenceClient;
    protected DaoClient mockDaoClient;

    /**
     * 
     */
    public BaseAccountingDaoTest() {
        return;
    }

    /**
     * Initialize contact factory and contact api mock objects.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        AccountingConfigurator configurator = new AccountingConfigurator();
        configurator.start();
        
        PowerMockito.mockStatic(AppPropertyPool.class);
        try {
            when(AppPropertyPool.getProperty(eq("dbmsVendor"))).thenReturn(ConfigConstants.DBMSTYPE_ASA);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Mocking dbmsVendor value fetch setup failed");
        }
        
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
