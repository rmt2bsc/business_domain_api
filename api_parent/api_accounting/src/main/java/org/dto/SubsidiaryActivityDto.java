package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an transaction activity
 * of a subsidiary such as customer and creditor entity.
 * 
 * @author rterrell
 * 
 */
public interface SubsidiaryActivityDto extends CommonAccountingDto {

    /**
     * Sets the internal unique identifier of the subsidiary activity id.
     */
    void setActivityId(int value);

    /**
     * Gets the internal unique identifier of the subsidiary activity id.
     */
    int getActivityId();

    /**
     * Sets the internal unique identifier of the subsidiary account such as
     * customer or creditor.
     */
    void setSubsidiaryId(int value);

    /**
     * Gets the internal unique identifier of the subsidiary account such as
     * customer or creditor
     */
    int getSubsidiaryId();

    /**
     * Sets the value of transaction id
     */
    void setXactId(int value);

    /**
     * Gets the value of transaction id
     */
    int getXactId();

    /**
     * Sets the value of subsidiary activity amount
     */
    void setActivityAmount(double value);

    /**
     * Gets the value of subsidiary activity amount
     */
    double getActivityAmount();

}
