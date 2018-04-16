package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvMediaType;
import org.dao.mapping.orm.rmt2.AvProject;
import org.dao.mapping.orm.rmt2.AvProjectType;
import org.dao.mapping.orm.rmt2.AvTracks;
import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.dao.mapping.orm.rmt2.PhotoAlbum;
import org.dao.mapping.orm.rmt2.PhotoEvent;
import org.dao.mapping.orm.rmt2.PhotoImage;
import org.dao.mapping.orm.rmt2.VwPhoto;
import org.dto.ArtistDto;
import org.dto.ContentDto;
import org.dto.GenreDto;
import org.dto.MediaTypeDto;
import org.dto.MimeTypeDto;
import org.dto.PhotoAlbumDto;
import org.dto.PhotoEventDto;
import org.dto.PhotoImageDto;
import org.dto.ProjectDto;
import org.dto.ProjectTypeDto;
import org.dto.TracksDto;
import org.dto.VwPhotoDto;

import com.RMT2Base;
import com.util.RMT2File;

/**
 * A factory for creating Multi Media module DTO instances using various adapter
 * imoplementations.
 * 
 * @author Roy Terrell.
 * 
 */
public class Rmt2MediaDtoFactory extends RMT2Base {

    /**
     * Creates an instance of class <i>ArtistDto</i> based on <i>AvArtist</i>
     * instance.
     * 
     * @return an instance of {@link ArtistDto}. Set to null for the purpose of
     *         instantiating a new AvArtist object.
     */
    public static final ArtistDto getArtistInstance(AvArtist artist) {
        ArtistDto dto = new MediaLookupInfoRmt2OrmDtoAdapter(artist);
        return dto;
    }

    /**
     * Creates an instance of class <i>GenreDto</i> based on <i>AvGenre</i>
     * instance.
     * 
     * @return an instance of {@link GenreDto}. Set to null for the purpose of
     *         instantiating a new AvGenre object.
     */
    public static final GenreDto getGenreInstance(AvGenre genre) {
        GenreDto dto = new MediaLookupInfoRmt2OrmDtoAdapter(genre);
        return dto;
    }

    /**
     * Creates an instance of class <i>MediaTypeDto</i> based on
     * <i>AvMediaType</i> instance.
     * 
     * @return an instance of {@link MediaTypeDto}. Set to null for the purpose
     *         of instantiating a new AvMediaType object.
     */
    public static final MediaTypeDto getMediaTypeInstance(AvMediaType mediaType) {
        MediaTypeDto dto = new MediaLookupInfoRmt2OrmDtoAdapter(mediaType);
        return dto;
    }

    /**
     * Creates an instance of class <i>ProjectTypeDto</i> based on
     * <i>AvProjectType</i> instance.
     * 
     * @return an instance of {@link ProjectTypeDto}. Set to null for the
     *         purpose of instantiating a new AvProjectType object.
     */
    public static final ProjectTypeDto getProjectTypeInstance(
            AvProjectType projectType) {
        ProjectTypeDto dto = new MediaLookupInfoRmt2OrmDtoAdapter(projectType);
        return dto;
    }

    /**
     * Creates an instance of class <i>ProjectDto</i> based on <i>AvProject</i>
     * instance.
     * 
     * @return an instance of {@link ProjectDto}. Set to null for the purpose of
     *         instantiating a new AvProject object.
     */
    public static final ProjectDto getProjectInstance(AvProject project) {
        ProjectDto dto = new ProjectRmt2OrmDtoAdapter(project);
        return dto;
    }

    /**
     * Creates an instance of class <i>TracksDto</i> based on <i>AvTracks</i>
     * instance.
     * 
     * @return an instance of {@link TracksDto}. Set to null for the purpose of
     *         instantiating a new AvTracks object.
     */
    public static final TracksDto getTrackInstance(AvTracks track) {
        TracksDto dto = new TracksRmt2OrmDtoAdapter(track);
        return dto;
    }

    /**
     * Creates an instance of class <i>ContentDto</i> based on <i>Content</i>
     * instance.
     * 
     * @return an instance of {@link ContentDto}. Set to null for the purpose of
     *         instantiating a new Content object.
     */
    public static final ContentDto getContentInstance(Content content) {
        ContentDto dto = new ContentRmt2OrmDtoAdapter(content);
        return dto;
    }
    
    /**
     * Creates a <i>ContentDto</i> object using the name of
     * <i>mediaFileName</i>.
     * <p>
     * The <i>ContentDto</i> properties, <i>filename</i> and <i>filepath</i>,
     * are set based on the value of <i>mediaFileName</i>. If
     * <i>mediaFileName</i> contains the file name and file extension, but the
     * path sequence is not present, then <i>filepath</i> will equal
     * <i>filename</i>. Otherwise, <i>filepath</i> and <i>filename</i> will be
     * assigned the path sequence and file name, respectively.
     * 
     * @param mediaFileName
     *            the name of the file to build Content object.
     * @return {@link ContentDto}
     */
    public static final ContentDto getContentInstance(String mediaFileName) {
        Content nullContent = null;
        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(nullContent);
        String fn = RMT2File.getFileName(mediaFileName);
        String path = RMT2File.getFilePathInfo(mediaFileName);
        dto.setFilename(fn);
        dto.setFilepath(path);
        return dto;
    }

    /**
     * Creates an instance of class <i>PhotoAlbumDto</i> based on
     * <i>PhotoAlbum</i> instance.
     * 
     * @return an instance of {@link PhotoAlbumDto}. Set to null for the purpose
     *         of instantiating a new PhotoAlbum object.
     */
    public static final PhotoAlbumDto getPhotoAlbumInstance(PhotoAlbum album) {
        PhotoAlbumDto dto = new PhotoAlbumnRmt2OrmDtoAdapter(album);
        return dto;
    }

    /**
     * Creates an instance of class <i>PhotoEventDto</i> based on
     * <i>PhotoEvent</i> instance.
     * 
     * @return an instance of {@link PhotoEventDto}. Set to null for the purpose
     *         of instantiating a new PhotoEvent object.
     */
    public static final PhotoEventDto getPhotoAlbumInstance(PhotoEvent evt) {
        PhotoEventDto dto = new PhotoEventRmt2OrmDtoAdapter(evt);
        return dto;
    }

    /**
     * Creates an instance of class <i>PhotoImageDto</i> based on
     * <i>PhotoEvent</i> instance.
     * 
     * @return an instance of {@link PhotoImageDto}. Set to null for the purpose
     *         of instantiating a new PhotoEvent object.
     */
    public static final PhotoImageDto getPhotoAlbumInstance(PhotoImage img) {
        PhotoImageDto dto = new PhotoImageRmt2OrmDtoAdapter(img);
        return dto;
    }

    /**
     * Creates an instance of class <i>VwPhotoDto</i> based on <i>VwPhoto</i>
     * instance.
     * 
     * @return an instance of {@link VwPhotoDto}. Set to null for the purpose of
     *         instantiating a new PhotoEvent object.
     */
    public static final VwPhotoDto getVwPhotoInstance(VwPhoto img) {
        VwPhotoDto dto = new VwPhotoRmt2OrmDtoAdapter(img);
        return dto;
    }

    /**
     * Creates an instance of class <i>MimeTypeDto</i> based on <i>MimeTypes</i>
     * instance.
     * 
     * @return an instance of {@link MimeTypes}. Set to null for the purpose of
     *         instantiating a new MimeTypes object.
     */
    public static final MimeTypeDto getMimeTypeInstance(MimeTypes mt) {
        MimeTypeDto dto = new MimeTypeRmt2OrmDtoAdapter(mt);
        return dto;
    }
}
