package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwPhoto;
import org.dto.VwPhotoDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A RMT2 ORM implementation of the {@link VwPhotoDto} interface.
 * 
 * @author rterrell
 * 
 */
class VwPhotoRmt2OrmDtoAdapter extends TransactionDtoImpl implements VwPhotoDto {

    private VwPhoto p;

    /**
     * Creates a VwPhotoRmt2OrmDtoAdapter without initializing its adaptee
     */
    private VwPhotoRmt2OrmDtoAdapter() {
        super();
        this.p = null;
    }

    /**
     * Creates a VwPhotoRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>img</i>
     * 
     * @param img
     *            an instance of {@link VwPhoto}. Set to null for the purpose of
     *            instantiating a new adaptee.
     */
    protected VwPhotoRmt2OrmDtoAdapter(VwPhoto img) {
        this();
        if (img == null) {
            img = new VwPhoto();
        }
        this.p = img;
        this.init(null, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#setImageId(int)
     */
    @Override
    public void setImageId(int value) {
        this.p.setImageId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#getImageId()
     */
    @Override
    public int getImageId() {
        return this.p.getImageId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#setEventId(int)
     */
    @Override
    public void setEventId(int value) {
        this.p.setEventId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#getEventId()
     */
    @Override
    public int getEventId() {
        return this.p.getEventId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#setMimeTypeId(int)
     */
    @Override
    public void setMimeTypeId(int value) {
        this.p.setMimeTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#getMimeTypeId()
     */
    @Override
    public int getMimeTypeId() {
        return this.p.getMimeTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#setDirPath(java.lang.String)
     */
    @Override
    public void setDirPath(String value) {
        this.p.setDirPath(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#getDirPath()
     */
    @Override
    public String getDirPath() {
        return this.p.getDirPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#setFileName(java.lang.String)
     */
    @Override
    public void setFileName(String value) {
        this.p.setFileName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#getFileName()
     */
    @Override
    public String getFileName() {
        return this.p.getFileName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#setFileSize(int)
     */
    @Override
    public void setFileSize(int value) {
        this.p.setFileSize(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#getFileSize()
     */
    @Override
    public int getFileSize() {
        return this.p.getFileSize();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#setFileExt(java.lang.String)
     */
    @Override
    public void setFileExt(String value) {
        this.p.setFileExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#getFileExt()
     */
    @Override
    public String getFileExt() {
        return this.p.getFileExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#setAlbumId(int)
     */
    @Override
    public void setAlbumId(int value) {
        this.p.setAlbumId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#getAlbumId()
     */
    @Override
    public int getAlbumId() {
        return this.p.getAlbumId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#setAlbumName(java.lang.String)
     */
    @Override
    public void setAlbumName(String value) {
        this.p.setAlbumName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#getAlbumName()
     */
    @Override
    public String getAlbumName() {
        return this.p.getAlbumName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#setAlbumDate(java.util.Date)
     */
    @Override
    public void setAlbumDate(Date value) {
        this.p.setAlbumDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#getAlbumDate()
     */
    @Override
    public Date getAlbumDate() {
        return this.p.getAlbumDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#setEventName(java.lang.String)
     */
    @Override
    public void setEventName(String value) {
        this.p.setEventName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#getEventName()
     */
    @Override
    public String getEventName() {
        return this.p.getEventName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#setMimeTypeFileExt(java.lang.String)
     */
    @Override
    public void setMimeTypeFileExt(String value) {
        this.p.setMimeTypeFileExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#getMimeTypeFileExt()
     */
    @Override
    public String getMimeTypeFileExt() {
        return this.p.getMimeTypeFileExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#setMediaType(java.lang.String)
     */
    @Override
    public void setMediaType(String value) {
        this.p.setMediaType(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwPhotoDto#getMediaType()
     */
    @Override
    public String getMediaType() {
        return this.p.getMediaType();
    }

}
