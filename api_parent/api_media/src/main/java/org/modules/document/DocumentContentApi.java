package org.modules.document;

import java.util.List;

import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.modules.MediaModuleException;

import com.api.foundation.TransactionApi;

/**
 * An contract for executing transactions related to the media document module.
 * 
 * @author Roy Terrell
 * 
 */
public interface DocumentContentApi extends TransactionApi {

    /**
     * Add media document.
     * 
     * @param document
     *            An instance of {@link ContentDto} which contains the document
     *            image and its metadata.
     * @return The internal unique identifier of the document object added.
     * @throws MediaModuleException
     */
    int add(ContentDto document) throws MediaModuleException;

    /**
     * Add media document.
     * 
     * @param filePath
     *            The full path of the media file to be added.
     * @return The internal unique identifier of the document object added.
     * @throws MediaModuleException
     */
    int add(String filePath) throws MediaModuleException;

    /**
     * Retrieve the media based on it internal unique identifier
     * 
     * @param contentId
     *            The internal unique identifier of the media document.
     * @return An instance of {@link ContentDto} representing the media
     *         document.
     * @throws MediaModuleException
     */
    ContentDto get(int contentId) throws MediaModuleException;

    /**
     * Remove media document.
     * 
     * @param contentId
     *            The internal unique identifier of the media document.
     * @return The total number of instances effected by the operation.
     * @throws MediaModuleException
     */
    int delete(int contentId) throws MediaModuleException;

    /**
     * Retrieve a mime type object using its internal unique identifier.
     * 
     * @param mimeTypeId
     *            The internal unique identifier of the MIME type.
     * @return an instance of {@link MimeTypeDto}
     * @throws MediaModuleException
     */
    MimeTypeDto getMimeType(int mimeTypeId) throws MediaModuleException;

    /**
     * Retrieve one or more mime type ojects uisng a mime type DTO as selection
     * criteria.
     * 
     * @param criteria
     *            an instance of {@link MimeTypeDto} containing selection
     *            criteria values.
     * @return a List of {@link MimeTypeDto} objects
     * @throws MediaModuleException
     */
    List<MimeTypeDto> getMimeType(MimeTypeDto criteria) throws MediaModuleException;

    /**
     * Start the thread responsible for listening for the existence of media
     * files in the media file inbound directory.
     * <p>
     * The inbound directory is configured in <i>MimeConfig_XXX.properties</i>
     * file.
     */
    void startMediaFileListener();

    /**
     * Terminate the thread responsible for listening for the existence of media
     * files in the media file inbound directory.
     * <p>
     * The inbound directory is configured in <i>MimeConfig_XXX.properties</i>
     * file.
     */
    void stopMediaFileListener();
}
