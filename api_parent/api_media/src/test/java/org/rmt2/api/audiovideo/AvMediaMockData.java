package org.rmt2.api.audiovideo;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvMediaType;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvProjectType;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.junit.After;
import org.junit.Before;
import org.rmt2.api.BaseMediaDaoTest;

/**
 * Audio/Video Media testing facility that is mainly responsible for setting up mock data.
 * <p>
 * All derived media related Api unit tests should inherit this class
 * to prevent duplicating common functionality.
 * 
 * @author rterrell
 * 
 */
public class AvMediaMockData extends BaseMediaDaoTest {
    protected List<AvGenre> mockAvGenreData;
    protected List<AvProjectType> mockAvProjectTypeData;
    protected List<AvMediaType> mockAvMediaTypeData;
    protected List<AvArtist> mockAvArtistData;
    protected List<AvProject> mockAvProjectData;
    protected List<AvTracks> mockAvTracksData;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        this.mockAvGenreData = this.createAvGenreMockData();
        this.mockAvProjectTypeData = this.createAvProjectTypeMockData();
        this.mockAvMediaTypeData = this.createAvMediaTypeMockData();
        this.mockAvArtistData = this.createAvArtistMockData();
        this.mockAvProjectData = this.createAvProjectMockData();
        this.mockAvTracksData = this.createAvTrackMockData();
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

    private List<AvGenre> createAvGenreMockData() {
        List<AvGenre> list = new ArrayList<>();
        int ndx = AvMediaMockDataFactory.TEST_GENRE_ID;
        AvGenre o = AvMediaMockDataFactory.createOrmAvGenre(ndx, "Genre" + ndx);
        list.add(o);
        
        o = AvMediaMockDataFactory.createOrmAvGenre(++ndx, "Genre" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvGenre(++ndx, "Genre" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvGenre(++ndx, "Genre" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvGenre(++ndx, "Genre" + ndx);
        list.add(o);
        
        return list;
    }
    
    private List<AvProjectType> createAvProjectTypeMockData() {
        List<AvProjectType> list = new ArrayList<>();
        int ndx = AvMediaMockDataFactory.TEST_PROJECTTYPE_ID;
        AvProjectType o = AvMediaMockDataFactory.createOrmAvProjectType(ndx, "ProjectType" + ndx);
        list.add(o);
        
        o = AvMediaMockDataFactory.createOrmAvProjectType(++ndx, "ProjectType" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvProjectType(++ndx, "ProjectType" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvProjectType(++ndx, "ProjectType" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvProjectType(++ndx, "ProjectType" + ndx);
        list.add(o);
        
        return list;
    }
    
    private List<AvMediaType> createAvMediaTypeMockData() {
        List<AvMediaType> list = new ArrayList<>();
        int ndx = AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID;
        AvMediaType o = AvMediaMockDataFactory.createOrmAvMediaType(ndx, "MediaType" + ndx);
        list.add(o);
        
        o = AvMediaMockDataFactory.createOrmAvMediaType(++ndx, "MediaType" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvMediaType(++ndx, "MediaType" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvMediaType(++ndx, "MediaType" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvMediaType(++ndx, "MediaType" + ndx);
        list.add(o);
        
        return list;
    }
    
    private List<AvArtist> createAvArtistMockData() {
        List<AvArtist> list = new ArrayList<>();
        int ndx = AvMediaMockDataFactory.TEST_ARTIST_ID;
        AvArtist o = AvMediaMockDataFactory.createOrmAvArtist(ndx, "Artist" + ndx);
        list.add(o);
        
        o = AvMediaMockDataFactory.createOrmAvArtist(++ndx, "Artist" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvArtist(++ndx, "Artist" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvArtist(++ndx, "Artist" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvArtist(++ndx, "Artist" + ndx);
        list.add(o);
        
        return list;
    }
    
    private List<AvProject> createAvProjectMockData() {
        List<AvProject> list = new ArrayList<>();
        int ndx = AvMediaMockDataFactory.TEST_PROJECT_ID;
        AvProject o = AvMediaMockDataFactory.createOrmAvProject(ndx,
                AvMediaMockDataFactory.TEST_ARTIST_ID,
                AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID,
                AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title" + ndx, 2018,
                "/FilePath/" + ndx, "ProjectFileName" + ndx);
        list.add(o);
        
        o = AvMediaMockDataFactory.createOrmAvProject(++ndx,
                AvMediaMockDataFactory.TEST_ARTIST_ID,
                AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID,
                AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title" + ndx, 2018,
                "/FilePath/" + ndx, "ProjectFileName" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvProject(++ndx,
                AvMediaMockDataFactory.TEST_ARTIST_ID,
                AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID,
                AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title" + ndx, 2018,
                "/FilePath/" + ndx, "ProjectFileName" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvProject(++ndx,
                AvMediaMockDataFactory.TEST_ARTIST_ID,
                AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID,
                AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title" + ndx, 2018,
                "/FilePath/" + ndx, "ProjectFileName" + ndx);
        list.add(o);
        o = AvMediaMockDataFactory.createOrmAvProject(++ndx,
                AvMediaMockDataFactory.TEST_ARTIST_ID,
                AvMediaMockDataFactory.TEST_PROJECTTYPE_ID,
                AvMediaMockDataFactory.TEST_GENRE_ID,
                AvMediaMockDataFactory.TEST_MEDIA_TYPE_ID, "Title" + ndx, 2018,
                "/FilePath/" + ndx, "ProjectFileName" + ndx);
        list.add(o);
        
        return list;
    }
    
    private List<AvTracks> createAvTrackMockData() {
        List<AvTracks> list = new ArrayList<>();
        int ndx = AvMediaMockDataFactory.TEST_TRACK_ID;
        int trackNo = 0;
        AvTracks o = AvMediaMockDataFactory.createOrmAvTracks(ndx,
                AvMediaMockDataFactory.TEST_PROJECT_ID, ++trackNo,
                "Track" + trackNo, 5, 30, 00, "1", "/FilePath/" + ndx,
                "ProjectFileName" + ndx);
        list.add(o);
        
        o = AvMediaMockDataFactory.createOrmAvTracks(++ndx,
                AvMediaMockDataFactory.TEST_PROJECT_ID, ++trackNo,
                "Track" + trackNo, 5, 30, 00, "1", "/FilePath/" + ndx,
                "ProjectFileName" + ndx);
        list.add(o);
        
        o = AvMediaMockDataFactory.createOrmAvTracks(++ndx,
                AvMediaMockDataFactory.TEST_PROJECT_ID, ++trackNo,
                "Track" + trackNo, 5, 30, 00, "1", "/FilePath/" + ndx,
                "ProjectFileName" + ndx);
        list.add(o);
        
        o = AvMediaMockDataFactory.createOrmAvTracks(++ndx,
                AvMediaMockDataFactory.TEST_PROJECT_ID, ++trackNo,
                "Track" + trackNo, 5, 30, 00, "1", "/FilePath/" + ndx,
                "ProjectFileName" + ndx);
        list.add(o);
        
        o = AvMediaMockDataFactory.createOrmAvTracks(++ndx,
                AvMediaMockDataFactory.TEST_PROJECT_ID, ++trackNo,
                "Track" + trackNo, 5, 30, 00, "1", "/FilePath/" + ndx,
                "ProjectFileName" + ndx);
        list.add(o);
        
        return list;
    }
}