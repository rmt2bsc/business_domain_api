package org.dao.document;

import java.util.List;

import org.dto.ContentDto;
import org.dto.MimeTypeDto;

import com.api.persistence.DaoClient;

/**
 * An interface designed to manage the persistence and retrieval of Multipurpose
 * Internet Mail Extensions (MIME) data to and from some external data source.
 * 
 * @author appdev
 * 
 */
public interface ContentDao extends DaoClient {

    /**
     * Adds multi media document to some external datasource.
     * 
     * @param content
     *            {@link ContentDto}
     * @return int the new mime type id just added.
     * @throws ContentDaoException
     */
    int addContent(ContentDto content) throws ContentDaoException;

    /**
     * Retrieves multi media document by its unique id.
     * 
     * @param contentId
     *            a integer value representing the unique id or primary key of
     *            the multi media document that is to be fetched.
     * @return An instance of {@link ContentDto}
     * @throws ContentDaoException
     */
    ContentDto fetchContent(int contentId) throws ContentDaoException;

    /**
     * Retrieves multi media document where the image document is represented as
     * an instance of {@link java.io.File}
     * 
     * @param contentId
     *            a integer value representing the unique id or primary key of
     *            the multi media document that is to be fetched.
     * @return An instance of {@link ContentDto}
     * @throws ContentDaoException
     */
    ContentDto fetchContentAsFile(int contentId) throws ContentDaoException;

    /**
     * Delete multi media document from some external data source.
     * 
     * @param contentId
     *            a integer value representing the unique id or primary key of
     *            the multi media document that is to be deleted.
     * @return an instance of {@link ContentDto} as a copy of the record just
     *         deleted.
     * @throws ContentDaoException
     */
    ContentDto deleteContent(int contentId) throws ContentDaoException;

    /**
     * Retrieve a specific mime type record based on its unique id.
     * 
     * @param mimeTypeId
     *            the id of the mime type record that is to be fetched.
     * @return Object an arbitrary object representing the mime type record
     *         related to the uid, <i>mimeTypeid</i>
     * @throws ContentDaoException
     */
    MimeTypeDto fetchMimeType(int mimeTypeId) throws ContentDaoException;

    /**
     * Retrieve all mime type records applicable to a specific file extension.
     * 
     * @param criteria
     *            An instance of {@link MimeTypeDto} containing the values that
     *            are to be used as selection criteria for the query.
     * @return Object a List of {@link MimeTypeDto} or null if no data is found.
     * @throws ContentDaoException
     */
    List<MimeTypeDto> fetchMimeType(MimeTypeDto criteria)
            throws ContentDaoException;

}
