package org.rmt2.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import com.api.persistence.PersistenceClient;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * @author Roy Terrell
 *
 */

public class BaseDaoTest {
    protected static String APP_NAME;
    protected PersistenceClient mockPersistenceClient;
    // private Rmt2OrmClientFactory mockRmt2OrmClientFactory;

    /**
     * 
     */
    public BaseDaoTest() {
return;
    }

    /**
     * Initialize contact factory and contact api mock objects.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // this.mockRmt2OrmClientFactory =
        // Mockito.mock(Rmt2OrmClientFactory.class);
        PowerMockito.mockStatic(Rmt2OrmClientFactory.class);
        this.mockPersistenceClient = Mockito.mock(PersistenceClient.class);
        // try {
        // whenNew(Rmt2OrmClientFactory.class).withNoArguments().thenReturn(this.mockRmt2OrmClientFactory);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
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
