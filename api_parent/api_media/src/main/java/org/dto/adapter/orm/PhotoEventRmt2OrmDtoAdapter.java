package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.PhotoEvent;
import org.dto.PhotoEventDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A RMT2 ORM implementation of the {@link PhotoEventDto} interface.
 * 
 * @author rterrell
 * 
 */
class PhotoEventRmt2OrmDtoAdapter extends TransactionDtoImpl implements
        PhotoEventDto {

    private PhotoEvent p;

    /**
     * Creates a PhotoEventRmt2OrmDtoAdapter without initializing its adaptee
     */
    public PhotoEventRmt2OrmDtoAdapter() {
        super();
        this.p = null;
    }

    /**
     * Creates a PhotoEventRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>evt</i>
     * 
     * @param evt
     *            an instance of {@link PhotoEvent}. Set to null for the purpose
     *            of instantiating a new adaptee.
     */
    protected PhotoEventRmt2OrmDtoAdapter(PhotoEvent evt) {
        this();
        if (evt == null) {
            evt = new PhotoEvent();
        }
        this.p = evt;
        this.init(evt.getDateCreated(), evt.getDateUpdated(), evt.getUserId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoEventDto#setEventId(int)
     */
    @Override
    public void setEventId(int value) {
        this.p.setEventId3(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoEventDto#getEventId()
     */
    @Override
    public int getEventId() {
        return this.p.getEventId3();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoEventDto#setAlbumId(int)
     */
    @Override
    public void setAlbumId(int value) {
        this.p.setAlbumId2(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoEventDto#getAlbumId()
     */
    @Override
    public int getAlbumId() {
        return this.p.getAlbumId2();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoEventDto#setEventName(java.lang.String)
     */
    @Override
    public void setEventName(String value) {
        this.p.setEventName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoEventDto#getEventName()
     */
    @Override
    public String getEventName() {
        return this.p.getEventName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoEventDto#setLocation(java.lang.String)
     */
    @Override
    public void setLocation(String value) {
        this.p.setLocation(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PhotoEventDto#getLocation()
     */
    @Override
    public String getLocation() {
        return this.p.getLocation();
    }

}
