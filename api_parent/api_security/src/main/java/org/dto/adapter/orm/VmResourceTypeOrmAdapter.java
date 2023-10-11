package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.VwResourceType;
import org.dto.DefaultResourceAdapter;
import org.dto.ResourceDto;

/**
 * Adapts an RMT2 ORM <i>VwResourceType</i> object to an <i>ResourceDto</i>.
 * 
 * @author rterrell
 * 
 */
class VmResourceTypeOrmAdapter extends DefaultResourceAdapter implements ResourceDto {

    private VwResourceType r;


    /**
     * Create a ResourceExtRmt2OrmAdapter using an instance of
     * <i>VwResourceType</i>.
     * 
     * @param rsrc
     *            an instance of {@link VwResourceType}
     */
    protected VmResourceTypeOrmAdapter(VwResourceType rsrc) {
        super();
        this.r = rsrc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setTypeId(int)
     */
    @Override
    public void setTypeId(int value) {
        this.r.setResrcTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getTypeId()
     */
    @Override
    public int getTypeId() {
        return this.r.getResrcTypeId();
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
        this.r.setResrcTypeName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getTypeDescription()
     */
    @Override
    public String getTypeDescription() {
        return this.r.getResrcTypeName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setSubTypeId(int)
     */
    @Override
    public void setSubTypeId(int value) {
        this.r.setResrcSubtypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeId()
     */
    @Override
    public int getSubTypeId() {
        return this.r.getResrcSubtypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultResourceRmt2OrmAdapter#setSubTypeName(java.lang.String)
     */
    @Override
    public void setSubTypeName(String value) {
        this.r.setResrcSubtypeName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeName()
     */
    @Override
    public String getSubTypeName() {
        return this.r.getResrcSubtypeName();
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
        this.r.setResrcSubtypeDesc(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getSubTypeDescription()
     */
    @Override
    public String getSubTypeDescription() {
        return this.r.getResrcSubtypeDesc();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setUrl(java.lang.String)
     */
    public void setRequestUrl(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getUrl()
     */
    public String getRequestUrl() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#getHost()
     */
    public String getHost() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultResourceRmt2OrmAdapter#setHost(java.lang.String)
     */
    public void setHost(String hostName) {
        return;
    }

}
