package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.VwResource;
import org.dto.DefaultResourceAdapter;
import org.dto.WebServiceDto;

/**
 * Adapts an RMT2 ORM <i>VwResource</i> object to an <i>ResourceDto</i>.
 * 
 * @author rterrell
 * 
 */
class ResourceExtRmt2OrmAdapter extends DefaultResourceAdapter implements
        WebServiceDto {

    private VwResource r;

    /**
     * Create a ResourceExtRmt2OrmAdapter using an instance of
     * <i>VwResource</i>.
     * 
     * @param r
     *            an instance of {@link VwResource}
     */
    protected ResourceExtRmt2OrmAdapter(VwResource rsrc) {
        super();
        this.r = rsrc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setUid(int)
     */
    @Override
    public void setUid(int value) {
        this.r.setRsrcId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getUid()
     */
    @Override
    public int getUid() {
        return this.r.getRsrcId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setName(java.lang.String)
     */
    @Override
    public void setName(String value) {
        this.r.setName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getName()
     */
    @Override
    public String getName() {
        return this.r.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setUrl(java.lang.String)
     */
    public void setRequestUrl(String value) {
        this.r.setUrl(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getUrl()
     */
    public String getRequestUrl() {
        return this.r.getUrl();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setDescription(java.lang.String)
     */
    @Override
    public void setDescription(String value) {
        this.r.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getDescription()
     */
    @Override
    public String getDescription() {
        return this.r.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setSecured(boolean)
     */
    public void setSecured(int value) {
        this.r.setSecured(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSecured()
     */
    public int getSecured() {
        return (this.r.getSecured());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getHost()
     */
    public String getHost() {
        return this.r.getHost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setHost(java.lang.String)
     */
    public void setHost(String hostName) {
        this.r.setHost(hostName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setTypeId(int)
     */
    @Override
    public void setTypeId(int value) {
        this.r.setRsrcTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getTypeId()
     */
    @Override
    public int getTypeId() {
        return this.r.getRsrcTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setTypeDescription(java.lang.String
     * )
     */
    @Override
    public void setTypeDescription(String value) {
        this.r.setTypeDescr(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getTypeDescription()
     */
    @Override
    public String getTypeDescription() {
        return this.r.getTypeDescr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setSubTypeId(int)
     */
    @Override
    public void setSubTypeId(int value) {
        this.r.setRsrcSubtypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeId()
     */
    @Override
    public int getSubTypeId() {
        return this.r.getRsrcSubtypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setSubTypeName(java.lang.String)
     */
    @Override
    public void setSubTypeName(String value) {
        this.r.setSubtypeName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeName()
     */
    @Override
    public String getSubTypeName() {
        return this.r.getSubtypeName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setSubTypeDescription(java.lang
     * .String)
     */
    @Override
    public void setSubTypeDescription(String value) {
        this.r.setSubtypeDesc(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeDescription()
     */
    @Override
    public String getSubTypeDescription() {
        return this.r.getSubtypeDesc();
    }

}
