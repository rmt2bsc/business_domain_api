package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.VwAudioVideoArtists;
import org.dto.VwArtistDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A RMT2 ORM implementation of the {@link VwArtistDto} interface.
 * 
 * @author rterrell
 * 
 */
class VwArtistRmt2OrmDtoAdapter extends TransactionDtoImpl implements VwArtistDto {

    private VwAudioVideoArtists adaptee;

    /**
     * Creates a ArtistRmt2OrmDtoAdapter without initializing its adaptee
     */
    private VwArtistRmt2OrmDtoAdapter() {
        super();
    }

    /**
     * Creates a VwArtistRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>info</i>
     * 
     * @param info
     *            an instance of {@link VwAudioVideoArtists}. Set to null for
     *            the purpose of intantiating a new adaptee.
     */
    protected VwArtistRmt2OrmDtoAdapter(VwAudioVideoArtists info) {
        this();
        if (info == null) {
            info = new VwAudioVideoArtists();
        }
        this.adaptee = info;
    }

    @Override
    public boolean isPrimaryArtist() {
        return (this.adaptee.getPrimaryArtist() == 1);
    }

    @Override
    public void setPrimaryArtist(int value) {
        this.adaptee.setArtistId(value);
    }

    @Override
    public int getProjectTypeId() {
        return this.adaptee.getProjectTypeId();
    }

    @Override
    public void setProjectTypeId(int value) {
        this.adaptee.setProjectTypeId(value);
    }

    @Override
    public String getProjectTypeName() {
        return this.adaptee.getProjectTypeName();
    }

    @Override
    public void setProjectTypeName(String value) {
        this.adaptee.setProjectTypeName(value);
    }

    @Override
    public int getArtistId() {
        return this.adaptee.getArtistId();
    }

    @Override
    public void setArtistId(int value) {
        this.adaptee.setArtistId(value);
    }

    @Override
    public String getArtistName() {
        return this.adaptee.getArtist();
    }

    @Override
    public void setArtistName(String value) {
        this.adaptee.setArtist(value);
    }

    @Override
    public int getProjectId() {
        return this.adaptee.getProjectId();
    }

    @Override
    public void setProjectId(int value) {
        this.adaptee.setProjectId(value);
    }

    @Override
    public String getProjectName() {
        return this.adaptee.getProjectTitle();
    }

    @Override
    public void setProjectName(String value) {
        this.adaptee.setProjectTitle(value);
    }

    @Override
    public String getProjectComments() {
        return this.adaptee.getProjectComments();
    }

    @Override
    public void setProjectComments(String value) {
        this.adaptee.setProjectComments(value);
    }

    @Override
    public int getTrackId() {
        return this.adaptee.getTrackId();
    }

    @Override
    public void setTrackId(int value) {
        this.adaptee.setTrackId(value);
    }

    @Override
    public String getTrackDiscNumber() {
        return this.adaptee.getTrackDiscNumber();
    }

    @Override
    public void setTrackDiscNumber(String value) {
        this.adaptee.setTrackDiscNumber(value);
    }

    @Override
    public int getTrackNumber() {
        return this.adaptee.getTrackNumber();
    }

    @Override
    public void setTrackNumber(int value) {
        this.adaptee.setTrackNumber(value);
    }

    @Override
    public String getTrackName() {
        return this.adaptee.getTrackTitle();
    }

    @Override
    public void setTrackName(String value) {
        this.adaptee.setTrackTitle(value);
    }

    @Override
    public String getTrackComments() {
        return this.adaptee.getTrackComments();
    }

    @Override
    public void setTrackComments(String value) {
        this.adaptee.setTrackComments(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getGenreId()
     */
    @Override
    public int getGenreId() {
        return this.adaptee.getGenreId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setGenreId(int)
     */
    @Override
    public void setGenreId(int genreId) {
        this.adaptee.setGenreId(genreId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getMediaTypeId()
     */
    @Override
    public int getMediaTypeId() {
        return this.adaptee.getMediaTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setMediaTypeId(int)
     */
    @Override
    public void setMediaTypeId(int mediaTypeId) {
        this.adaptee.setMediaTypeId(mediaTypeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getYear()
     */
    @Override
    public int getYear() {
        return this.adaptee.getYear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setYear(int)
     */
    @Override
    public void setYear(int year) {
        this.adaptee.setYear(year);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getMasterDupId()
     */
    @Override
    public int getMasterDupId() {
        return this.adaptee.getMasterDupId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setMasterDupId(int)
     */
    @Override
    public void setMasterDupId(int value) {
        this.adaptee.setMasterDupId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getRippedInd()
     */
    @Override
    public int getRippedInd() {
        return this.adaptee.getRipped();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setRippedInd(int)
     */
    @Override
    public void setRippedInd(int flag) {
        this.adaptee.setRipped(flag);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getCost()
     */
    @Override
    public double getCost() {
        return this.adaptee.getCost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setCost(double)
     */
    @Override
    public void setCost(double cost) {
        this.adaptee.setCost(cost);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getContentId()
     */
    @Override
    public int getContentId() {
        return this.adaptee.getContentId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setContentId(int)
     */
    @Override
    public void setContentId(int contentId) {
        this.adaptee.setContentId(contentId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getContentPath()
     */
    @Override
    public String getContentPath() {
        return this.adaptee.getContentPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setContentPath(java.lang.String)
     */
    @Override
    public void setContentPath(String contentPath) {
        this.adaptee.setContentPath(contentPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getArtWorkPath()
     */
    @Override
    public String getArtWorkPath() {
        return this.adaptee.getArtWorkPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setArtWorkPath(java.lang.String)
     */
    @Override
    public void setArtWorkPath(String artWorkPath) {
        this.adaptee.setArtWorkPath(artWorkPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getArtWorkFilename()
     */
    @Override
    public String getArtWorkFilename() {
        return this.adaptee.getArtWorkFilename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setArtWorkFilename(java.lang.String)
     */
    @Override
    public void setArtWorkFilename(String artWorkFilename) {
        this.adaptee.setArtWorkFilename(artWorkFilename);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getContentFilename()
     */
    @Override
    public String getContentFilename() {
        return this.adaptee.getContentFilename();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setContentFilename(java.lang.String)
     */
    @Override
    public void setContentFilename(String contentFilename) {
        this.adaptee.setContentFilename(contentFilename);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getTotalTime()
     */
    @Override
    public int getTotalTime() {
        return this.adaptee.getTotalTime();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setTotalTime(int)
     */
    @Override
    public void setTotalTime(int totalTime) {
        this.adaptee.setTotalTime(totalTime);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#getProducer()
     */
    @Override
    public String getProducer() {
        return this.adaptee.getProducer();
    }

    @Override
    public int getTrackHours() {
        return this.adaptee.getTrackHours();
    }

    @Override
    public void setTrackHours(int value) {
        this.adaptee.setTrackHours(value);
    }

    @Override
    public int getTrackMinutes() {
        return this.adaptee.getTrackMinutes();
    }

    @Override
    public void setTrackMinutes(int value) {
        this.adaptee.setTrackMinutes(value);
    }

    @Override
    public int getTrackSeconds() {
        return this.adaptee.getTrackSeconds();
    }

    @Override
    public void setTrackSeconds(int value) {
        this.adaptee.setTrackSeconds(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.VwArtistDto#setProducer(java.lang.String)
     */
    @Override
    public void setProducer(String producer) {
        this.adaptee.setProducer(producer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return (this.getArtistId() == ((VwArtistDto) obj).getArtistId()
                && this.getProjectId() == ((VwArtistDto) obj).getProjectId() && this.getTrackId() == ((VwArtistDto) obj)
                .getTrackId());
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
