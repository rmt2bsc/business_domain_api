package org.dao.document;

import com.RMT2Base;
import com.SystemException;
import com.util.RMT2BeanUtility;

/**
 * A factory for creating multi media DAO related instances in regards to the
 * document category.
 * 
 * @author appdev
 * 
 */
public class ContentDaoFactory extends RMT2Base {

    /**
     * Default contructor
     */
    public ContentDaoFactory() {
        return;
    }

    /**
     * Creates an instance of the {@link ContentDao} from the implementation
     * specified in the home application's MIME configuration file.
     * <p>
     * In order for this to work, the application's MIME configufation
     * .properties file must include an entry which identifies the ContentDao's
     * implementation such as
     * <i>mime.handler=org.dao.document.Rmt2OrmSybaseEmbeddedMediaDaoImpl</i>.
     * 
     * @param className
     *            a fully qualified name of the class to instantiate
     *            dynamically.
     * @return an instance of {@link ContentDao}
     * @throws SystemException
     *             if <i>className</i> is discovered not to be an instance of
     *             ContentDao.
     */
    public ContentDao createDaoInstance(String className) {
        RMT2BeanUtility beanUtil = new RMT2BeanUtility();
        try {
            Object obj = beanUtil.createBean(className);
            if (obj instanceof ContentDao) {
                return (ContentDao) obj;
            }
        } catch (Exception e) {
            throw new SystemException(e);
        }

        String msg = "File Processor instance must be a derivative of com.api.db.MimeContentApi";
        throw new SystemException(msg);
    }

    /**
     * Creates an instance of the {@link ContentDao} using the RMT2 ORM embedded
     * media file document implementation.
     * 
     * @return an instance of {@link ContentDao}
     */
    public ContentDao createEmbeddedMediaDaoInstance() {
        return new Rmt2OrmEmbeddedMediaDaoImpl();
    }

    /**
     * Creates an instance of the {@link ContentDao} using the RMT2 ORM External
     * media file document implementation.
     * 
     * @return an instance of {@link ContentDao}
     */
    public ContentDao createExternalFileMediaDaoInstance() {
        return new Rmt2OrmExternalFileMediaDaoImpl();
    }
}
