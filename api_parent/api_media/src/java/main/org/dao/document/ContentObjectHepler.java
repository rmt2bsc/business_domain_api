package org.dao.document;

import org.dto.ContentDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;

import com.util.RMT2File;

/**
 * Helper class containing methods for document miscellaneous routines.
 * 
 * @author rterrell
 * 
 */
public class ContentObjectHepler {

    /**
     * Creates a <i>ContentDto</i> object using the name of
     * <i>mediaFileName</i>.
     * <p>
     * The <i>ContentDto</i> properties, <i>filename</i> and <i>filepath</i>,
     * are set based on the value of <i>mediaFileName</i>. If
     * <i>mediaFileName</i> contains the file name and file extension, but the
     * path sequence is not present, then <i>filepath</i> will equal
     * <i>filename</i>. Otherwise, <i>filepath</i> and <i>filename</i> will be
     * assigned the path sequence and file name, respectively.
     * 
     * @param mediaFileName
     *            the name of the file to build Content object.
     * @return {@link ContentDto}
     */
    public static final ContentDto createContentInstance(String mediaFileName) {
        ContentDto mime = Rmt2MediaDtoFactory.getContentInstance(null);
        String fn = RMT2File.getFileName(mediaFileName);
        String path = RMT2File.getFilePathInfo(mediaFileName);
        mime.setFilename(fn);
        mime.setFilepath(path);
        return mime;
    }

}
