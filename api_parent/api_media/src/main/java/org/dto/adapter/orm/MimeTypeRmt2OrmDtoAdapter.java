package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.MimeTypes;

import org.dto.MimeTypeDto;

/**
 * A RMT2 ORM implementation of the {@link MimeTypeDto} interface.
 * 
 * @author rterrell
 * 
 */
class MimeTypeRmt2OrmDtoAdapter implements MimeTypeDto {

    private MimeTypes mt;

    /**
     * Creates a MimeTypeRmt2OrmDtoAdapter without initializing its adaptee
     */
    private MimeTypeRmt2OrmDtoAdapter() {
        super();
        this.mt = null;
    }

    /**
     * Creates a MimeTypeRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>mimeTypes</i>
     * 
     * @param mimeTypes
     *            an instance of {@link MimeTypes}. Set to null for the purpose
     *            of instantiating a new adaptee.
     */
    protected MimeTypeRmt2OrmDtoAdapter(MimeTypes mimeTypes) {
        this();
        if (mimeTypes == null) {
            mimeTypes = new MimeTypes();
        }
        this.mt = mimeTypes;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MimeTypeDto#setMimeTypeId(int)
     */
    @Override
    public void setMimeTypeId(int value) {
        this.mt.setMimeTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MimeTypeDto#getMimeTypeId()
     */
    @Override
    public int getMimeTypeId() {
        return this.mt.getMimeTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MimeTypeDto#setFileExt(java.lang.String)
     */
    @Override
    public void setFileExt(String value) {
        this.mt.setFileExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MimeTypeDto#getFileExt()
     */
    @Override
    public String getFileExt() {
        return this.mt.getFileExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MimeTypeDto#setMediaType(java.lang.String)
     */
    @Override
    public void setMediaType(String value) {
        this.mt.setMediaType(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MimeTypeDto#getMediaType()
     */
    @Override
    public String getMediaType() {
        return this.mt.getMediaType();
    }

}
