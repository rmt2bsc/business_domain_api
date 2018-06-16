package org.modules.application;

import java.util.List;

import org.dto.ApplicationDto;

import com.api.foundation.TransactionApi;

/**
 * An API contract for for managing the application module.
 * 
 * @author rterrell
 * 
 */
public interface AppApi extends TransactionApi {

    /**
     * Retrieve all Application records.
     * 
     * @param criteria an instance of {@link ApplicationDto} containing the values needed for building selection criteria
     * @return A List of {@link ApplicationDto} objects or null if no data is
     *         found.
     * @throws AppApiException
     */
    List<ApplicationDto> get(ApplicationDto criteria) throws AppApiException;

    /**
     * Persist changes to a single Application entity to the database.
     * <p>
     * This method will handle SQL inserts and updates where applicable. When
     * the entity's unique id is equal to zero, an insert is performed. When the
     * unique id is greater than zero, an update is performed.
     * 
     * @param dto
     *            An instance of {@link ApplicationDto} containing the
     *            application related data to be applied to the database.
     * @return the total number of rows effecting existing records, or the
     *         unique identifier for a new record.
     * @throws AppApiException
     */
    int update(ApplicationDto dto) throws AppApiException;

    /**
     * Delete an application object from a data source by its unique identifier.
     * 
     * @param uid
     *            The primary key of the user.
     * @return the total number of records deleted.
     * @throws AppApiException
     */
    int delete(int uid) throws AppApiException;
}