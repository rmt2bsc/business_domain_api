package org.dto;

import java.util.Date;

/**
 * Data Transfer Object (DTO) contract that represents values that can be used
 * to build custom selection criteria for a transaction entity.
 * 
 * @author rterrell
 * 
 */
public interface XactCustomCriteriaDto {
    
    public static final String ADV_SRCH_BEGIN = "BEGIN";
    
    public static final String ADV_SRCH_END = "END";

    public static final String ADV_SRCH_CONTATIN = "CONTAIN";

    public static final String ADV_SRCH_EXACT = "EXACT";

    /**
     * Sets the value of target level
     */
    void setTargetLevel(String value);

    /**
     * Gets the value of target level
     */
    String getTargetLevel();

    /**
     * Sets the value of transaction reason filter option
     */
    void setXactReasonFilterOption(String value);

    /**
     * Gets the value of transaction reason filter option
     */
    String getXactReasonFilterOption();
    
    /**
     * Set to xact amount
     * @param value
     */
    void setToXactAmount(Double value);
    
    /**
     * @return to xact amount
     */
    Double getToXactAmount();
    
    /**
     * Sets the relational operator for to xact amount
     */
    void setToXactAmountRelOp(String value);

    /**
     * Get the relational operator for to xact amount
     */
    String getToXactAmountRelOp();
    
    /**
     * Set from xact amount
     * @param value
     */
    void setFromXactAmount(Double value);
    
    /**
     * @return from xact amount
     */
    Double getFromXactAmount();
    
    /**
     * Sets the relational operator for from xact amount
     */
    void setFromXactAmountRelOp(String value);

    /**
     * Get the relational operator for from xact amount
     */
    String getFromXactAmountRelOp();
    
    /**
     * Sets the value of TO xactDate
     */
    void setToXactDate(Date value);

    /**
     * Gets the value of To xactDate
     */
    Date getToXactDate();

    /**
     * Sets the relational operator for to xact date
     */
    void setToXactDateRelOp(String value);

    /**
     * Get the relational operator for to xact date
     */
    String getToXactDateRelOp();
    
    /**
     * Sets the value of from xactDate
     */
    void setFromXactDate(Date value);

    /**
     * Gets the value of from xactDate
     */
    Date getFromXactDate();

    /**
     * Sets the relational operator for from xact date
     */
    void setFromXactDateRelOp(String value);

    /**
     * Get the relational operator for from xact date
     */
    String getFromXactDateRelOp();
    
    /**
     * Set to item amount
     * @param value
     */
    void setToItemAmount(Double value);
    
    /**
     * @return to item amount
     */
    Double getToItemAmount();
    
    /**
     * Sets the relational operator for to item amount
     */
    void setToItemAmountRelOp(String value);

    /**
     * Get the relational operator for to item amount
     */
    String getToItemAmountRelOp();
    
    /**
     * Set from item amount
     * @param value
     */
    void setFromItemAmount(Double value);
    
    /**
     * @return from item amount
     */
    Double getFromItemAmount();
    
    /**
     * Sets the relational operator for from item amount
     */
    void setFromItemAmountRelOp(String value);

    /**
     * Get the relational operator for from item amount
     */
    String getFromItemAmountRelOp();

}
