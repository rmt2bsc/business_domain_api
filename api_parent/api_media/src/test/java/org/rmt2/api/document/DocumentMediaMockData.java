package org.rmt2.api.document;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.junit.After;
import org.junit.Before;
import org.rmt2.api.BaseMediaDaoTest;

/**
 * Media testing facility that is mainly responsible for setting up mock data.
 * <p>
 * All derived media related Api unit tests should inherit this class
 * to prevent duplicating common functionality.
 * 
 * @author rterrell
 * 
 */
public class DocumentMediaMockData extends BaseMediaDaoTest {
    protected Content mockSingleContent;
    protected List<MimeTypes> mockMultipleMimeTypeList;
    protected List<MimeTypes> mockSingleMimeTypeList;
    protected MimeTypes mockSingleMimeTypes;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        this.mockSingleContent = this.createMockSingleContent();
        this.mockMultipleMimeTypeList = this.createMockMimeTypeMultiple();
        this.mockSingleMimeTypeList = new ArrayList<>();
        this.mockSingleMimeTypes = this.createMockSingleMimeTypes();
        return;
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private Content createMockSingleContent() {
        Content o = DocumentMediaMockDataFactory.createOrmContent(
                DocumentMediaMockDataFactory.TEST_CONTENT_ID,
                DocumentMediaMockDataFactory.TEST_MIME_TYPE_ID, "tmp/", "image.jpg",
                1024, 5555, "Media");
        return o;
    }
    
    private MimeTypes createMockSingleMimeTypes() {
        MimeTypes o = DocumentMediaMockDataFactory.createOrmMimeTypes(DocumentMediaMockDataFactory.TEST_MIME_TYPE_ID, ".jpg", "image/jpg");
        return o;
    }
    
    private List<MimeTypes> createMockMimeTypeMultiple() {
        int mimeTypeId = DocumentMediaMockDataFactory.TEST_MIME_TYPE_ID;
        List<MimeTypes> list = new ArrayList<>();
        MimeTypes o = DocumentMediaMockDataFactory.createOrmMimeTypes(mimeTypeId, ".jpg", "image/jpg");
        list.add(o);
        
        o = DocumentMediaMockDataFactory.createOrmMimeTypes(++mimeTypeId, ".pdf", "application/pdf");
        list.add(o);
        
        o = DocumentMediaMockDataFactory.createOrmMimeTypes(++mimeTypeId, ".txt", "text/plain");
        list.add(o);
        
        o = DocumentMediaMockDataFactory.createOrmMimeTypes(++mimeTypeId, ".doc", "application/msword");
        list.add(o);
        
        o = DocumentMediaMockDataFactory.createOrmMimeTypes(++mimeTypeId, ".xlsx", "application/vnd.ms-excel");
        list.add(o);
        
        o = DocumentMediaMockDataFactory.createOrmMimeTypes(++mimeTypeId, ".mp3", "audio/x-mpeg-3");
        list.add(o);
        
        o = DocumentMediaMockDataFactory.createOrmMimeTypes(++mimeTypeId, ".mp4", "video/mp4");
        list.add(o);
        
        return list;
    }
}