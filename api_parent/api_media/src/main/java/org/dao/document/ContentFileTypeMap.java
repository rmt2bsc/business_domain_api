package org.dao.document;

import java.io.File;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;

import javax.activation.FileTypeMap;

import com.api.persistence.PersistenceClient;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2File;

/**
 * Provides an interface for data typing files by their file extension.
 * <p>
 * MIME type definitions are loaded from the MIME_TYPE table of the MIME
 * database into a Map collection.
 * 
 * @author Roy Terrell
 * 
 */
public class ContentFileTypeMap extends FileTypeMap {
    private static Map<String, String> MIMEMAP;

    /**
     * Creates a ContentFileTypeMap object with a Map of MIME type descriptions
     * keyed by file extension.
     */
    public ContentFileTypeMap() {
        // Load Map of MIME types
        if (ContentFileTypeMap.MIMEMAP == null) {
            this.loadMimeTypes();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.activation.FileTypeMap#getContentType(java.io.File)
     */
    @Override
    public String getContentType(File file) {
        String fileName = file.getAbsolutePath();
        return this.getContentType(fileName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.activation.FileTypeMap#getContentType(java.lang.String)
     */
    @Override
    public String getContentType(String fileName) {
        String fileExt = RMT2File.getFileExt(fileName);
        String contentType = ContentFileTypeMap.MIMEMAP.get(fileExt);
        return contentType;
    }

    /**
     * Load a Map collection with MIME type definitions where the key will
     * represent the file extension prefixed with a "." character. The value
     * will represent the file document type description.
     */
    private void loadMimeTypes() {
        PersistenceClient db = Rmt2OrmClientFactory.createOrmClientInstance();
        Connection con = (Connection) db.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("select file_ext, media_type from mime_types order by file_ext");
            while (rs.next()) {
                if (ContentFileTypeMap.MIMEMAP == null) {
                    ContentFileTypeMap.MIMEMAP = new HashMap<String, String>();
                }
                String key = rs.getString("file_ext");
                String value = rs.getString("media_type");
                ContentFileTypeMap.MIMEMAP.put(key, value);
            }
        } catch (Exception e) {
            ContentFileTypeMap.MIMEMAP = null;
        }
    }

    // private void loadMimeTypes() {
    // String configFile = MediaAppConfig.configFile + "_" + MediaAppConfig.ENV;
    // String dbUrl = RMT2File.getPropertyValue(configFile, "mime.dbURL");
    // DatabaseConnectionBean con = JdbcFactory.getConnection(dbUrl,
    // configFile);
    // ContentDao api =
    // ContentDaoFactory.getMimeContentApiInstance("com.api.db.DefaultFileSystemContentImpl");
    // api.initApi(con);
    // try {
    // Statement stmt = con.getDbConn().createStatement();
    // ResultSet rs =
    // stmt.executeQuery("select file_ext, media_type from mime_types order by file_ext");
    // while (rs.next()) {
    // if (ContentFileTypeMap.MIMEMAP == null) {
    // ContentFileTypeMap.MIMEMAP = new HashMap<String, String>();
    // }
    // String key = rs.getString("file_ext");
    // String value = rs.getString("media_type");
    // ContentFileTypeMap.MIMEMAP.put(key, value);
    // }
    // }
    // catch (Exception e) {
    // ContentFileTypeMap.MIMEMAP = null;
    // }
    // }

}
