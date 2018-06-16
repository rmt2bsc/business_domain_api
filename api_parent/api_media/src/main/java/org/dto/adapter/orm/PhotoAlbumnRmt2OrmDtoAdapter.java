package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.PhotoAlbum;
import org.dto.PhotoAlbumDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A RMT2 ORM implementation of the {@link PhotoAlbumDto} interface.
 * 
 * @author rterrell
 * 
 */
class PhotoAlbumnRmt2OrmDtoAdapter extends TransactionDtoImpl implements
        PhotoAlbumDto {

    private PhotoAlbum p;

    /**
     * Creates a PhotoAlbumnRmt2OrmDtoAdapter without initializing its adaptee
     */
    private PhotoAlbumnRmt2OrmDtoAdapter() {
        super();
        this.p = null;
    }

    /**
     * Creates a PhotoAlbumnRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>photoAlbum</i>
     * 
     * @param photoAlbum
     *            an instance of {@link PhotoAlbum}. Set to null for the purpose
     *            of instantiating a new adaptee.
     */
    protected PhotoAlbumnRmt2OrmDtoAdapter(PhotoAlbum photoAlbum) {
        this();
        if (photoAlbum == null) {
            photoAlbum = new PhotoAlbum();
        }
        this.p = photoAlbum;
        this.init(photoAlbum.getDateCreated(), photoAlbum.getDateUpdated(),
                photoAlbum.getUserId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#setAlbumId(int)
     */
    @Override
    public void setAlbumId(int value) {
        this.p.setAlbumId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#getAlbumId()
     */
    @Override
    public int getAlbumId() {
        return this.p.getAlbumId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#setAlbumName(java.lang.String)
     */
    @Override
    public void setAlbumName(String value) {
        this.p.setAlbumName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#getAlbumName()
     */
    @Override
    public String getAlbumName() {
        return this.p.getAlbumName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#setAlbumDate(java.util.Date)
     */
    @Override
    public void setAlbumDate(Date value) {
        this.p.setAlbumDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#getAlbumDate()
     */
    @Override
    public Date getAlbumDate() {
        return this.p.getAlbumDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#setLocation(java.lang.String)
     */
    @Override
    public void setLocation(String value) {
        this.p.setLocation(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#getLocation()
     */
    @Override
    public String getLocation() {
        return this.p.getLocation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#setServername(java.lang.String)
     */
    @Override
    public void setServername(String value) {
        this.p.setServername(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#getServername()
     */
    @Override
    public String getServername() {
        return this.p.getServername();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#setSharename(java.lang.String)
     */
    @Override
    public void setSharename(String value) {
        this.p.setSharename(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoAlbumDto#getSharename()
     */
    @Override
    public String getSharename() {
        return this.p.getSharename();
    }

}
