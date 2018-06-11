package org.modules;

import org.dto.CategoryDto;

import com.api.foundation.TransactionApi;

/**
 * A common API contract for managing modules that provide basic data lookup
 * functionality for the security application.
 * 
 * @author rterrell
 * 
 */
public interface CategoryApiModule extends TransactionApi {

    /**
     * Persist changes to a single API entity to the database.
     * <p>
     * This method will handle SQL inserts and updates where applicable. When
     * the entity's unique id is equal to zero, an insert is performed. When the
     * unique id is greater than zero, an update is performed.
     * 
     * @param dto
     *            An instance of {@link CategoryDto} containing the application
     *            related data to be applied to the database.
     * @return the total number of rows effecting existing records, or the
     *         unique identifier for a new record.
     * @throws SecurityModuleException
     */
    int update(CategoryDto dto) throws SecurityModuleException;

    /**
     * Delete an API record from a data source by its unique identifier.
     * 
     * @param uid
     *            The primary key of the user.
     * @return the total number of records deleted.
     * @throws SecurityModuleException
     */
    int delete(int uid) throws SecurityModuleException;

}