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
     * @param media
     *            an instance of {@link ContentDto}
     * @param embedded
     *            Determines how the image data of the media document image data
     *            is to be persisted. Set to <i>true</i> when the media image
     *            data is to be saved in the same datastore as the media detail
     *            data. Set to <i>false</i> when the media document image data
     *            is to be saved as an external file.
     * @return The internal unique identifier of the document object added.
     * @throws MediaModuleException
     */
    int addMedia(ContentDto media, boolean embedded)
            throws MediaModuleException;

    /**
     * Retrieve the media based on it internal unique identifier
     * 
     * @param contentId
     *            The internal unique identifier of the media document. param
     *            embedded Determines how the image data of the media document
     *            detail record is to be retrieved. Set to <i>true</i> when the
     *            media image data exists as part of the document detail record.
     *            Set to <i>false</i> when the media document's image data is to
     *            be retrieved from an external file.
     * @return An instance of {@link ContentDto} representing the media
     *         document.
     * @throws MediaModuleException
     */
    ContentDto getMedia(int contentId, boolean embedded)
            throws MediaModuleException;

    /**
     * Remove media document.
     * 
     * @param contentId
     *            The internal unique identifier of the media document.
     * @return The total number of instances effected by the operation.
     * @throws MediaModuleException
     */
    int deleteMedia(int contentId) throws MediaModuleException;

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
    List<MimeTypeDto> getMimeType(MimeTypeDto criteria)
            throws MediaModuleException;

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
