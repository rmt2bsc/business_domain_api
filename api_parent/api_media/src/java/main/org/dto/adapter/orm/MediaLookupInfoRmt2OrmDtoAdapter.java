package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dao.mapping.orm.rmt2.AvGenre;
import org.dao.mapping.orm.rmt2.AvMediaType;
import org.dao.mapping.orm.rmt2.AvProjectType;
import org.dto.ArtistDto;
import org.dto.GenreDto;
import org.dto.MediaLookupInfoDto;
import org.dto.MediaTypeDto;
import org.dto.ProjectTypeDto;

/**
 * A common implementation that uses the adapter patttern to maintain data for
 * artist, genre, media type, and project type adaptees.
 * 
 * @author rterrell
 * 
 */
class MediaLookupInfoRmt2OrmDtoAdapter implements MediaLookupInfoDto,
        ArtistDto, GenreDto, MediaTypeDto, ProjectTypeDto {

    private AvArtist a;

    private AvGenre g;

    private AvMediaType mt;

    private AvProjectType pt;

    /**
     * Creates a MediaLookupInfoRmt2OrmDtoAdapter where any of the adaptees are
     * not initialize.
     */
    private MediaLookupInfoRmt2OrmDtoAdapter() {
        this.a = null;
        this.g = null;
        this.pt = null;
        this.mt = null;
    }

    /**
     * Creates a MediaLookupInfoRmt2OrmDtoAdapter that adapts an artist ORM
     * object.
     * 
     * @param src
     *            an instance of {@link AvArtist}. When null, a new AvArtist
     *            instance is created.
     */
    protected MediaLookupInfoRmt2OrmDtoAdapter(AvArtist src) {
        this();
        if (src == null) {
            src = new AvArtist();
        }
        this.a = src;
    }

    /**
     * Creates a MediaLookupInfoRmt2OrmDtoAdapter that adapts a genre ORM
     * object.
     * 
     * @param src
     *            an instance of {@link AvGenre}. When null, a new AvGenre
     *            instance is created.
     */
    protected MediaLookupInfoRmt2OrmDtoAdapter(AvGenre src) {
        this();
        if (src == null) {
            src = new AvGenre();
        }
        this.g = src;
    }

    /**
     * Creates a MediaLookupInfoRmt2OrmDtoAdapter that adapts a media type ORM
     * object.
     * 
     * @param src
     *            an instance of {@link AvMediaType}. When null, a new
     *            AvMediaType instance is created.
     */
    protected MediaLookupInfoRmt2OrmDtoAdapter(AvMediaType src) {
        this();
        if (src == null) {
            src = new AvMediaType();
        }
        this.mt = src;
    }

    /**
     * Creates a MediaLookupInfoRmt2OrmDtoAdapter that adapts a project type ORM
     * object.
     * 
     * @param src
     *            an instance of {@link AvProjectType}. When null, a new
     *            AvProjectType instance is created.
     */
    protected MediaLookupInfoRmt2OrmDtoAdapter(AvProjectType src) {
        this();
        if (src == null) {
            src = new AvProjectType();
        }
        this.pt = src;
    }

    /**
     * Returns the primary key value of the RMT2 ORM adaptee.
     * <p>
     * For instance, if the adaptee is an instance of AvArtist, then the artist
     * id is returned. Or if the adaptee is an instance of AvGenre, then the
     * genre's id is returned.
     * 
     * @return an int value representing the primary key of the adaptee.
     */
    @Override
    public int getUid() {
        if (this.a != null) {
            return this.a.getArtistId();
        }
        else if (this.g != null) {
            return this.g.getGenreId();
        }
        else if (this.mt != null) {
            return this.mt.getMediaTypeId();
        }
        else if (this.pt != null) {
            return this.pt.getProjectTypeId();
        }
        return 0;
    }

    /**
     * Sets the primary key of the current adaptee.
     * 
     * @param uid
     *            the primary key value to set.
     */
    @Override
    public void setUid(int uid) {
        if (this.a != null) {
            this.a.setArtistId(uid);
        }
        else if (this.g != null) {
            this.g.setGenreId(uid);
        }
        else if (this.mt != null) {
            this.mt.setMediaTypeId(uid);
        }
        else if (this.pt != null) {
            this.pt.setProjectTypeId(uid);
        }

    }

    /**
     * Returns the name or description of the RMT2 ORM adaptee.
     * <p>
     * For instance, if the adaptee is an instance of AvArtist, then the artist
     * name is returned. Or if the adaptee is an instance of AvGenre, then the
     * genre's description is returned.
     * 
     * @return an int value representing the name or description of the adaptee.
     */
    @Override
    public String getDescritpion() {
        if (this.a != null) {
            return this.a.getName();
        }
        else if (this.g != null) {
            return this.g.getDescription();
        }
        else if (this.mt != null) {
            return this.mt.getDescription();
        }
        else if (this.pt != null) {
            return this.pt.getDescription();
        }
        return null;
    }

    /**
     * Sets the name or description of the current adaptee.
     * 
     * @param descr
     *            the name or description to set.
     */
    @Override
    public void setDescription(String descr) {
        if (this.a != null) {
            this.a.setName(descr);
        }
        else if (this.g != null) {
            this.g.setDescription(descr);
        }
        else if (this.mt != null) {
            this.mt.setDescription(descr);
        }
        else if (this.pt != null) {
            this.pt.setDescription(descr);
        }
    }

}
