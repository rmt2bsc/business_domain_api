package org.modules.transaction;

import java.util.List;

import org.dao.transaction.XactDao;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;

import com.api.persistence.DaoClient;

/**
 * The default implementation of the shared DAO base transaction API.
 * 
 * @author Roy Terrell
 * 
 */
class DefaultXactApiImpl extends AbstractXactApiImpl {

    /**
     * 
     */
    public DefaultXactApiImpl() {
        super();
        return;
    }

    /**
     * 
     */
    protected DefaultXactApiImpl(DaoClient connection) {
        super(connection);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.AbstractXactApiImpl#update(org.dto .XactDto
     * , java.util.List)
     */
    @Override
    public int update(XactDto xact, List<XactTypeItemActivityDto> xactItems)
            throws XactApiException {
        XactDao dao = this.getXactDao();
        return super.update(xact, xactItems);
    }

}
