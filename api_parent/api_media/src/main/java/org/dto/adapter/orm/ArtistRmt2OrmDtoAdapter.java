package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.AvArtist;
import org.dto.ArtistDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A RMT2 ORM implementation of the {@link ArtistDto} interface.
 * 
 * @author rterrell
 * 
 */
class ArtistRmt2OrmDtoAdapter extends TransactionDtoImpl implements ArtistDto {

    private AvArtist adaptee;

    /**
     * Creates a ArtistRmt2OrmDtoAdapter without initializing its adaptee
     */
    private ArtistRmt2OrmDtoAdapter() {
        super();
    }

    /**
     * Creates a ArtistRmt2OrmDtoAdapter by initializing its adaptee to
     * <i>info</i>
     * 
     * @param info
     *            an instance of {@link AvArtist}. Set to null for the purpose
     *            of intantiating a new adaptee.
     */
    protected ArtistRmt2OrmDtoAdapter(AvArtist info) {
        this();
        if (info == null) {
            info = new AvArtist();
        }
        this.adaptee = info;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ArtistDto#setId(int)
     */
    @Override
    public void setId(int value) {
        this.adaptee.setArtistId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ArtistDto#getId()
     */
    @Override
    public int getId() {
        return this.adaptee.getArtistId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ArtistDto#setName(java.lang.String)
     */
    @Override
    public void setName(String value) {
        this.adaptee.setName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ArtistDto#getName()
     */
    @Override
    public String getName() {
        return this.adaptee.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return this.getId() == ((ArtistDto) obj).getId();
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
