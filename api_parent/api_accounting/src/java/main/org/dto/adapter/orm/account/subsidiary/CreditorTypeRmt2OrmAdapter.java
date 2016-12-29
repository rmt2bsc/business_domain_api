package org.dto.adapter.orm.account.subsidiary;

import org.dao.mapping.orm.rmt2.CreditorType;
import org.dto.CreditorTypeDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table, <i>creditor_type</i>.
 * 
 * @author rterrell
 * 
 */
class CreditorTypeRmt2OrmAdapter extends TransactionDtoImpl implements
        CreditorTypeDto {

    private CreditorType ct;

    /**
     * Create a CreditorTypeRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected CreditorTypeRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a CreditorTypeRmt2OrmAdapter that adapts data coming from the
     * creditor_type table
     * 
     * @param creditor
     *            an instance of {@link CreditorType} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected CreditorTypeRmt2OrmAdapter(CreditorType creditor) {
        if (creditor == null) {
            creditor = new CreditorType();
        }
        this.ct = creditor;
        this.dateCreated = creditor.getDateCreated();
        this.dateUpdated = creditor.getDateUpdated();
        this.updateUserId = creditor.getUserId();

        return;
    }

    /**
     * Set the creditor type internal unique id
     */
    @Override
    public void setEntityId(int value) {
        this.ct.setCreditorTypeId(value);
    }

    /**
     * Get the creditor type internal unique id
     */
    @Override
    public int getEntityId() {
        return this.ct.getCreditorTypeId();
    }

    /**
     * Set the creditor type description
     */
    @Override
    public void setEntityName(String value) {
        this.ct.setDescription(value);
    }

    /**
     * Return the creditor type description
     */
    @Override
    public String getEntityName() {
        return this.ct.getDescription();
    }

}
