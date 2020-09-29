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

    private AvTracks adaptee;

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
        this.adaptee = trackInfo;
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
        this.adaptee.setTrackId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackId()
     */
    @Override
    public int getTrackId() {
        return this.adaptee.getTrackId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setProjectId(int)
     */
    @Override
    public void setProjectId(int value) {
        this.adaptee.setProjectId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getProjectId()
     */
    @Override
    public int getProjectId() {
        return this.adaptee.getProjectId();
    }

    @Override
    public void setGenreId(int value) {
        this.adaptee.setGenreId(value);

    }

    @Override
    public int getGenreId() {
        return this.adaptee.getGenreId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackNumber(int)
     */
    @Override
    public void setTrackNumber(int value) {
        this.adaptee.setTrackNumber(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackNumber()
     */
    @Override
    public int getTrackNumber() {
        return this.adaptee.getTrackNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackTitle(java.lang.String)
     */
    @Override
    public void setTrackTitle(String value) {
        this.adaptee.setTrackTitle(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackTitle()
     */
    @Override
    public String getTrackTitle() {
        return this.adaptee.getTrackTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackHours(int)
     */
    @Override
    public void setTrackHours(int value) {
        this.adaptee.setTrackHours(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackHours()
     */
    @Override
    public int getTrackHours() {
        return this.adaptee.getTrackHours();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackMinutes(int)
     */
    @Override
    public void setTrackMinutes(int value) {
        this.adaptee.setTrackMinutes(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackMinutes()
     */
    @Override
    public int getTrackMinutes() {
        return this.adaptee.getTrackMinutes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackSeconds(int)
     */
    @Override
    public void setTrackSeconds(int value) {
        this.adaptee.setTrackSeconds(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackSeconds()
     */
    @Override
    public int getTrackSeconds() {
        return this.adaptee.getTrackSeconds();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackDisc(java.lang.String)
     */
    @Override
    public void setTrackDisc(String value) {
        this.adaptee.setTrackDisc(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackDisc()
     */
    @Override
    public String getTrackDisc() {
        return this.adaptee.getTrackDisc();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackProducer(java.lang.String)
     */
    @Override
    public void setTrackProducer(String value) {
        this.adaptee.setTrackProducer(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackProducer()
     */
    @Override
    public String getTrackProducer() {
        return this.adaptee.getTrackProducer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackComposer(java.lang.String)
     */
    @Override
    public void setTrackComposer(String value) {
        this.adaptee.setTrackComposer(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackComposer()
     */
    @Override
    public String getTrackComposer() {
        return this.adaptee.getTrackComposer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setTrackLyricist(java.lang.String)
     */
    @Override
    public void setTrackLyricist(String value) {
        this.adaptee.setTrackLyricist(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getTrackLyricist()
     */
    @Override
    public String getTrackLyricist() {
        return this.adaptee.getTrackLyricist();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocServername(java.lang.String)
     */
    @Override
    public void setLocServername(String value) {
        this.adaptee.setLocServername(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocServername()
     */
    @Override
    public String getLocServername() {
        return this.adaptee.getLocServername();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocSharename(java.lang.String)
     */
    @Override
    public void setLocSharename(String value) {
        this.adaptee.setLocSharename(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocSharename()
     */
    @Override
    public String getLocSharename() {
        return this.adaptee.getLocSharename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocRootPath(java.lang.String)
     */
    @Override
    public void setLocRootPath(String value) {
        this.adaptee.setLocRootPath(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocRootPath()
     */
    @Override
    public String getLocRootPath() {
        return this.adaptee.getLocRootPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocPath(java.lang.String)
     */
    @Override
    public void setLocPath(String value) {
        this.adaptee.setLocPath(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocPath()
     */
    @Override
    public String getLocPath() {
        return this.adaptee.getLocPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setLocFilename(java.lang.String)
     */
    @Override
    public void setLocFilename(String value) {
        this.adaptee.setLocFilename(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getLocFilename()
     */
    @Override
    public String getLocFilename() {
        return this.adaptee.getLocFilename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#setComments(java.lang.String)
     */
    @Override
    public void setComments(String value) {
        this.adaptee.setComments(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.TracksDto#getComments()
     */
    @Override
    public String getComments() {
        return this.adaptee.getComments();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return this.getTrackId() == ((TracksDto) obj).getTrackId()
                && this.getTrackTitle().equalsIgnoreCase(((TracksDto) obj).getTrackTitle());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.adaptee.hashCode();
    }
}
