package org.rmt2.api.profiles;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.dao.contacts.ContactsDao;
import org.dao.contacts.ContactsDaoFactory;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;

/**
 * @author Roy Terrell
 *
 */

public class BaseProfileTest {
    private static final String APP_NAME = "addressbook";
    private ContactsDaoFactory mockContactsDaoFactory;
    protected ContactsDao mockDao;



    /**
     * 
     */
    public BaseProfileTest() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Initialize contact factory and contact api mock objects.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.mockContactsDaoFactory = Mockito.mock(ContactsDaoFactory.class);
        this.mockDao = Mockito.mock(ContactsDao.class);
        try {
            whenNew(ContactsDaoFactory.class).withNoArguments().thenReturn(this.mockContactsDaoFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        when(this.mockContactsDaoFactory.createRmt2OrmDao(APP_NAME)).thenReturn(this.mockDao);
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
