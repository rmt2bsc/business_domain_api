package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.UserResource;
import org.dto.DefaultResourceAdapter;
import org.dto.WebServiceDto;

/**
 * Adapts an RMT2 ORM <i>UserResource</i> object to an <i>WebServiceDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
class ResourceRmt2OrmAdapter extends DefaultResourceAdapter implements WebServiceDto {

    private UserResource r;

    /**
     * Create a ResourceRmt2OrmAdapter using an instance of <i>UserResource</i>.
     * 
     * @param rsrc
     *            an instance of {@link UserResource}
     */
    protected ResourceRmt2OrmAdapter(UserResource rsrc) {
        super();
        this.r = rsrc;
        this.dateCreated = rsrc.getDateCreated();
        this.dateUpdated = rsrc.getDateUpdated();
        this.updateUserId = rsrc.getUserId();
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
    public void setSecured(Boolean value) {
        this.r.setSecured(value ? 1 : 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSecured()
     */
    public Boolean isSecured() {
        return (this.r.getSecured() == 1 ? true : false);
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
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getRouterType()
     */
    public String getRouterType() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setRouterType(java.lang.String)
     */
    public void setRouterType(String routerType) {
        return;
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
}
