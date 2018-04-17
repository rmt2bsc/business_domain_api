package org.rmt2.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.modules.AddressBookConfigurator;
import org.powermock.api.mockito.PowerMockito;

import com.api.persistence.PersistenceClient;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Common functionality for mocking DAO classes
 * 
 * @author Roy Terrell
 *
 */

public class BaseAddressBookDaoTest {
    protected PersistenceClient mockPersistenceClient;

    /**
     * 
     */
    public BaseAddressBookDaoTest() {
        return;
    }

    /**
     * Initialize contact factory and contact api mock objects.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        AddressBookConfigurator configurator = new AddressBookConfigurator();
        configurator.start();
        
        PowerMockito.mockStatic(Rmt2OrmClientFactory.class);
        this.mockPersistenceClient = Mockito.mock(PersistenceClient.class);
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
