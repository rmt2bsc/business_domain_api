package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.Content;
import org.dto.ContentDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A RMT2 ORM implementation of the {@link ContentDto} interface.
 * 
 * @author rterrell
 * 
 */
class ContentRmt2OrmDtoAdapter extends TransactionDtoImpl implements ContentDto {

    private Content c;

    /**
     * Creates a ContentRmt2OrmDtoAdapter without initializing its adaptee
     */
    private ContentRmt2OrmDtoAdapter() {
        super();
        this.c = null;
        return;
    }

    /**
     * Creates a ContentRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>document</i>
     * 
     * @param content
     *            an instance of {@link Content} or null in order to instantiate
     *            a new Content object to work with.
     */
    protected ContentRmt2OrmDtoAdapter(Content content) {
        this();
        if (content == null) {
            content = new Content();
        }
        this.c = content;
        this.init(content.getDateCreated(), null, content.getUserId());
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setContentId(int)
     */
    @Override
    public void setContentId(int value) {
        this.c.setContentId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getContentId()
     */
    @Override
    public int getContentId() {
        return this.c.getContentId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setProjectId(int)
     */
    @Override
    public void setProjectId(int value) {
        this.c.setProjectId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getProjectId()
     */
    @Override
    public int getProjectId() {
        return this.c.getProjectId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setMimeTypeId(int)
     */
    @Override
    public void setMimeTypeId(int value) {
        this.c.setMimeTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getMimeTypeId()
     */
    @Override
    public int getMimeTypeId() {
        return this.c.getMimeTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setImageData(java.lang.Object)
     */
    @Override
    public void setImageData(Object value) {
        this.c.setImageData(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getImageData()
     */
    @Override
    public Object getImageData() {
        return this.c.getImageData();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setTextData(java.lang.String)
     */
    @Override
    public void setTextData(String value) {
        this.c.setTextData(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getTextData()
     */
    @Override
    public String getTextData() {
        return this.c.getTextData();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setAppCode(java.lang.String)
     */
    @Override
    public void setAppCode(String value) {
        this.c.setAppCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getAppCode()
     */
    @Override
    public String getAppCode() {
        return this.c.getAppCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setModuleCode(java.lang.String)
     */
    @Override
    public void setModuleCode(String value) {
        this.c.setModuleCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getModuleCode()
     */
    @Override
    public String getModuleCode() {
        return this.c.getModuleCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setFilepath(java.lang.String)
     */
    @Override
    public void setFilepath(String value) {
        this.c.setFilepath(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getFilepath()
     */
    @Override
    public String getFilepath() {
        return this.c.getFilepath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setFilename(java.lang.String)
     */
    @Override
    public void setFilename(String value) {
        this.c.setFilename(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getFilename()
     */
    @Override
    public String getFilename() {
        return this.c.getFilename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#setSize(int)
     */
    @Override
    public void setSize(int value) {
        this.c.setSize(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ContentDto#getSize()
     */
    @Override
    public int getSize() {
        return this.c.getSize();
    }

}
