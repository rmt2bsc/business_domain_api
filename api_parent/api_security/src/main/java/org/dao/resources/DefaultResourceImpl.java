package org.dao.resources;

import java.util.List;

import org.dao.SecurityDaoImpl;
import org.dto.ResourceDto;

/**
 * This is a basic implementation of the {@link ResourceDao} interface where
 * most of the method bodies are sutbbed.
 * 
 * @author Roy Terrell
 * 
 */
class DefaultResourceImpl extends SecurityDaoImpl implements ResourceDao {

    /**
     * Default constructor
     */
    public DefaultResourceImpl() {
        return;
    }

    /**
     * Stubbed
     */
    @Override
    public void close() {
        return;
    }

    /**
     * Always return null
     */
    @Override
    public List<ResourceDto> fetchResource() throws ResourceDaoException {
        return null;
    }

    /**
     * Always return null
     */
    @Override
    public List<ResourceDto> fetchResource(ResourceDto criteria)
            throws ResourceDaoException {
        return null;
    }

    /**
     * Always return null
     */
    @Override
    public ResourceDto fetchResource(int resourceId)
            throws ResourceDaoException {
        return null;
    }

    /**
     * Always return null
     */
    @Override
    public List<ResourceDto> fetchResourceType() throws ResourceDaoException {
        return null;
    }

    /**
     * Always return null
     */
    @Override
    public ResourceDto fetchResourceType(int resourceTypeId)
            throws ResourceDaoException {
        return null;
    }

    /**
     * Always return null
     */
    @Override
    public List<ResourceDto> fetchResourceSubType() throws ResourceDaoException {
        return null;
    }

    /**
     * Always return null
     */
    @Override
    public ResourceDto fetchResourceSubType(int resourceSubTypeId)
            throws ResourceDaoException {
        return null;
    }

    /**
     * Always return null
     */
    @Override
    public ResourceDto fetchResourceSubType(String resourceSubTypeName)
            throws ResourceDaoException {
        return null;
    }

    /**
     * Always return null
     */
    @Override
    public List<ResourceDto> fetchExtResource(ResourceDto criteria)
            throws ResourceDaoException {
        return null;
    }

    /**
     * Always return zero
     */
    @Override
    public int maintainResource(ResourceDto rsrc) throws ResourceDaoException {
        return 0;
    }

    /**
     * Always return zero
     */
    @Override
    public int maintainResourceType(ResourceDto rsrcType)
            throws ResourceDaoException {
        return 1;
    }

    /**
     * Always return zero
     */
    @Override
    public int maintainResourceSubType(ResourceDto rsrcSubType)
            throws ResourceDaoException {
        return 0;
    }

    /**
     * Always return zero
     */
    @Override
    public int deleteResource(int resourceId) throws ResourceDaoException {
        return 0;
    }

    /**
     * Always return zero
     */
    @Override
    public int deleteResourceType(int resourceTypeId)
            throws ResourceDaoException {
        return 0;
    }

    /**
     * Always return zero
     */
    @Override
    public int deleteResourceSubType(int resourceSubTypeId)
            throws ResourceDaoException {
        return 0;
    }

    /**
     * Always return zero
     */
    @Override
    public int deleteResourceSubType(String subTypeName)
            throws ResourceDaoException {
        return 0;
    }

    /**
     * Always return zero
     */
    @Override
    public int deleteResource(ResourceDto rsrc) throws ResourceDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

}
