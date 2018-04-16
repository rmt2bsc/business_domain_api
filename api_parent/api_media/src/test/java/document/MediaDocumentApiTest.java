package document;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.MediaConstants;
import org.modules.MediaModuleException;
import org.modules.document.DocumentContentApi;
import org.modules.document.DocumentContentApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.MediaMockData;
import org.rmt2.api.MediaMockDataFactory;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Test the document module functionality of the Media API.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, ResultSet.class })
public class MediaDocumentApiTest extends MediaMockData {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        when(this.mockPersistenceClient.insertRow(isA(Content.class), eq(true)))
                .thenReturn(MediaMockDataFactory.TEST_CONTENT_ID);

        when(this.mockPersistenceClient.retrieveList(isA(MimeTypes.class)))
                .thenReturn(this.mockClientFetchMimeTypeMultiple);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

   
    @Test
    public void testSuccess_Add_Image_As_File() {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        int contentId = 0;
        try {
            contentId = api.add("media/image.jpg");
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(MediaMockDataFactory.TEST_CONTENT_ID, contentId);
    }
    
    @Test
    public void testSuccess_Add_Audio_As_File() {
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        DocumentContentApi api = f.createMediaContentApi(MediaConstants.DEFAULT_CONTEXT_NAME, false);
        int contentId = 0;
        try {
            contentId = api.add("media/Audio.mp3");
        }
        catch (MediaModuleException e) {
            Assert.fail("An exception was not expected");
        }
        
        Assert.assertEquals(MediaMockDataFactory.TEST_CONTENT_ID, contentId);
    }
}