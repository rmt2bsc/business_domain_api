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
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return this.getArtistId() == ((VwArtistDto) obj).getArtistId();
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
