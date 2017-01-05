package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.AvTracks;
import org.dto.TracksDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A RMT2 ORM implementation of the {@link TracksDto} interface.
 * 
 * @author rterrell
 * 
 */
class TracksRmt2OrmDtoAdapter extends TransactionDtoImpl implements TracksDto {

    private AvTracks t;

    /**
     * Creates a TracksRmt2OrmDtoAdapter without initializing its adaptee
     */
    private TracksRmt2OrmDtoAdapter() {
        super();
    }

    /**
     * Creates a TracksRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>trackInfo</i>
     * 
     * @param trackInfo
     *            an instance of {@link AvTracks}. Set to null for the purpose
     *            of intantiating a new adaptee.
     */
    protected TracksRmt2OrmDtoAdapter(AvTracks trackInfo) {
        this();
        if (trackInfo == null) {
            trackInfo = new AvTracks();
        }
        this.t = trackInfo;
        this.init(trackInfo.getDateCreated(), trackInfo.getDateUpdated(),
                trackInfo.getUserId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackId(int)
     */
    @Override
    public void setTrackId(int value) {
        this.t.setTrackId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackId()
     */
    @Override
    public int getTrackId() {
        return this.t.getTrackId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setProjectId(int)
     */
    @Override
    public void setProjectId(int value) {
        this.t.setProjectId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getProjectId()
     */
    @Override
    public int getProjectId() {
        return this.t.getProjectId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackNumber(int)
     */
    @Override
    public void setTrackNumber(int value) {
        this.t.setTrackNumber(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackNumber()
     */
    @Override
    public int getTrackNumber() {
        return this.t.getTrackNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackTitle(java.lang.String)
     */
    @Override
    public void setTrackTitle(String value) {
        this.t.setTrackTitle(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackTitle()
     */
    @Override
    public String getTrackTitle() {
        return this.t.getTrackTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackHours(int)
     */
    @Override
    public void setTrackHours(int value) {
        this.t.setTrackHours(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackHours()
     */
    @Override
    public int getTrackHours() {
        return this.t.getTrackHours();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackMinutes(int)
     */
    @Override
    public void setTrackMinutes(int value) {
        this.t.setTrackMinutes(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackMinutes()
     */
    @Override
    public int getTrackMinutes() {
        return this.t.getTrackMinutes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackSeconds(int)
     */
    @Override
    public void setTrackSeconds(int value) {
        this.t.setTrackSeconds(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackSeconds()
     */
    @Override
    public int getTrackSeconds() {
        return this.t.getTrackSeconds();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackDisc(java.lang.String)
     */
    @Override
    public void setTrackDisc(String value) {
        this.t.setTrackDisc(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackDisc()
     */
    @Override
    public String getTrackDisc() {
        return this.t.getTrackDisc();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackProducer(java.lang.String)
     */
    @Override
    public void setTrackProducer(String value) {
        this.t.setTrackProducer(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackProducer()
     */
    @Override
    public String getTrackProducer() {
        return this.t.getTrackProducer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackComposer(java.lang.String)
     */
    @Override
    public void setTrackComposer(String value) {
        this.t.setTrackComposer(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackComposer()
     */
    @Override
    public String getTrackComposer() {
        return this.t.getTrackComposer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackLyricist(java.lang.String)
     */
    @Override
    public void setTrackLyricist(String value) {
        this.t.setTrackLyricist(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackLyricist()
     */
    @Override
    public String getTrackLyricist() {
        return this.t.getTrackLyricist();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocServername(java.lang.String)
     */
    @Override
    public void setLocServername(String value) {
        this.t.setLocServername(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocServername()
     */
    @Override
    public String getLocServername() {
        return this.t.getLocServername();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocSharename(java.lang.String)
     */
    @Override
    public void setLocSharename(String value) {
        this.t.setLocSharename(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocSharename()
     */
    @Override
    public String getLocSharename() {
        return this.t.getLocSharename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocRootPath(java.lang.String)
     */
    @Override
    public void setLocRootPath(String value) {
        this.t.setLocRootPath(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocRootPath()
     */
    @Override
    public String getLocRootPath() {
        return this.t.getLocRootPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocPath(java.lang.String)
     */
    @Override
    public void setLocPath(String value) {
        this.t.setLocPath(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocPath()
     */
    @Override
    public String getLocPath() {
        return this.t.getLocPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocFilename(java.lang.String)
     */
    @Override
    public void setLocFilename(String value) {
        this.t.setLocFilename(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocFilename()
     */
    @Override
    public String getLocFilename() {
        return this.t.getLocFilename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setComments(java.lang.String)
     */
    @Override
    public void setComments(String value) {
        this.t.setComments(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getComments()
     */
    @Override
    public String getComments() {
        return this.t.getComments();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackArtist(java.lang.String)
     */
    @Override
    public void setTrackArtist(String value) {
        this.t.setTrackArtist(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackArtist()
     */
    @Override
    public String getTrackArtist() {
        return this.t.getTrackArtist();
    }

}
