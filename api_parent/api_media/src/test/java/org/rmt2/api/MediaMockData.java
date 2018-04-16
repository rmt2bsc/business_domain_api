package org.rmt2.api;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.MimeTypes;
import org.junit.After;
import org.junit.Before;

/**
 * Media testing facility that is mainly responsible for setting up mock data.
 * <p>
 * All derived media related Api unit tests should inherit this class
 * to prevent duplicating common functionality.
 * 
 * @author rterrell
 * 
 */
public class MediaMockData extends BaseMediaDaoTest {
    protected List<MimeTypes> mockClientFetchMimeTypeMultiple;
    protected MimeTypes mockClientFetchMimeTypeSingle;
    

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockClientFetchMimeTypeMultiple = this.createMockMimeTypeMultiple();
      
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

    private List<MimeTypes> createMockMimeTypeMultiple() {
        List<MimeTypes> list = new ArrayList<>();
        MimeTypes o = MediaMockDataFactory.createOrmMimeTypes(101, ".jpg", "image/jpg");
        list.add(o);
        
        o = MediaMockDataFactory.createOrmMimeTypes(102, ".pdf", "application/pdf");
        list.add(o);
        
        o = MediaMockDataFactory.createOrmMimeTypes(103, ".txt", "text/plain");
        list.add(o);
        
        o = MediaMockDataFactory.createOrmMimeTypes(104, ".doc", "application/msword");
        list.add(o);
        
        o = MediaMockDataFactory.createOrmMimeTypes(105, ".xlsx", "application/vnd.ms-excel");
        list.add(o);
        
        o = MediaMockDataFactory.createOrmMimeTypes(106, ".mp3", "audio/x-mpeg-3");
        list.add(o);
        
        o = MediaMockDataFactory.createOrmMimeTypes(10, ".wmv", "video/x-ms-wmv");
        list.add(o);
        
        return list;
    }
}