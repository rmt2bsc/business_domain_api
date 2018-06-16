package org.rmt2.api.document;

import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;

public class DocumentMediaMockDataFactory {
    public static final int TEST_CONTENT_ID = 999991;
    public static final int TEST_MIME_TYPE_ID = 101;

    /**
     * 
     * @param id
     * @param fileExt
     * @param mimeType
     * @return
     */
    public static final MimeTypes createOrmMimeTypes(int id, String fileExt, String mimeType) {
        MimeTypes o = new MimeTypes();
        o.setFileExt(fileExt);
        o.setMediaType(mimeType);
        o.setMimeTypeId(id);
        return o;
    }
    
    /**
     * 
     * @param contentId
     * @param mimeTypeId
     * @param filepath
     * @param filename
     * @param size
     * @param projectId
     * @param appCode
     * @return
     */
    public static final Content createOrmContent(int contentId, int mimeTypeId,
            String filepath, String filename, int size, int projectId,
            String appCode) {

        Content o = new Content();
        o.setContentId(contentId);
        o.setMimeTypeId(mimeTypeId);
        o.setFilepath(filepath);
        o.setFilename(filename);
        o.setSize(size);
        o.setProjectId(projectId);
        o.setAppCode(appCode);
        o.setModuleCode("ModuleCode");
        o.setTextData("TextData");
        o.setImageData("ImageData".getBytes());
        return o;
    }
}
