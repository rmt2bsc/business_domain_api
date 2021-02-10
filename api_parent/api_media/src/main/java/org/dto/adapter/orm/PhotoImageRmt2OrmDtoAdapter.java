package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.PhotoImage;
import org.dto.PhotoImageDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A RMT2 ORM implementation of the {@link PhotoImageDto} interface.
 * 
 * @author rterrell
 * 
 */
class PhotoImageRmt2OrmDtoAdapter extends TransactionDtoImpl implements
        PhotoImageDto {

    private PhotoImage p;

    /**
     * Creates a PhotoImageRmt2OrmDtoAdapter without initializing its adaptee
     */
    private PhotoImageRmt2OrmDtoAdapter() {
        super();
        this.p = null;
    }

    /**
     * Creates a PhotoImageRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>img</i>
     * 
     * @param img
     *            an instance of {@link PhotoImage}. Set to null for the purpose
     *            of instantiating a new adaptee.
     */
    protected PhotoImageRmt2OrmDtoAdapter(PhotoImage img) {
        this();
        if (img == null) {
            img = new PhotoImage();
        }
        this.p = img;
        this.init(img.getDateCreated(), img.getDateUpdated(), img.getUserId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#setImageId(int)
     */
    @Override
    public void setImageId(int value) {
        this.p.setImageId2(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#getImageId()
     */
    @Override
    public int getImageId() {
        return this.p.getImageId2();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#setEventId(int)
     */
    @Override
    public void setEventId(int value) {
        this.p.setEventId3(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoImageDto#getEventId()
     */
    @Override
    public int getEventId() {
        return this.p.getEventId3();
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

}
