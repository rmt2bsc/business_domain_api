package org.rmt2.api;

import org.dao.mapping.orm.rmt2.MimeTypes;

public class MediaMockDataFactory {
    public static final int TEST_CONTENT_ID = 999991;

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
}
