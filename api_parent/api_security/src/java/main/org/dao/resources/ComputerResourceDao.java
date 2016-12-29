package org.dao.resources;

import java.util.List;

import org.dto.ComputerAppServerDto;

import com.api.persistence.DaoClient;

/**
 * Contract for accessing and managing data regarding Computer related
 * resources.
 * 
 * @author Roy Tererll
 * 
 */
public interface ComputerResourceDao extends DaoClient {

    /**
     * Fetch computer information that relates to application/web servers.
     * 
     * @param criteria
     *            an instance of {@link ComputerAppServerDto} containing various
     *            data values to be used to build selection criteria.
     * @return A List of {@link ComputerAppServerDto} or null when no data is
     *         found.
     * @throws ResourceDaoException
     *             General access errors.
     */
    List<ComputerAppServerDto> fetchApplicationServerInfo(
            ComputerAppServerDto criteria) throws ResourceDaoException;
}
