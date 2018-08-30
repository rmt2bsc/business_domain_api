package org.modules.transaction;

import java.util.Date;

import org.dto.XactCustomCriteriaDto;

/**
 * Manages the custom selection criteria values for a given transaction.
 * 
 * @author roy.terrell
 *
 */
class XactCustomCriteriaImpl implements XactCustomCriteriaDto {

    private String targetLevel;
    private String xactReasonFilterOption;
    private double fromItemAmount;
    private double toItemAmount;
    private String fromItemAmountRelOp;
    private String toItemAmountRelOp;
    private double fromXactAmount;
    private double toXactAmount;
    private String fromXactAmountRelOp;
    private String toXactAmountRelOp;
    private Date fromXactDate;
    private Date toXactDate;
    private String fromXactDateRelOp;
    private String toXactDateRelOp;
    
    
    /**
     * Default constructor
     */
    public XactCustomCriteriaImpl() {
        
    }


    /**
     * @return the targetLevel
     */
    @Override
    public String getTargetLevel() {
        return targetLevel;
    }


    /**
     * @param targetLevel the targetLevel to set
     */
    @Override
    public void setTargetLevel(String targetLevel) {
        this.targetLevel = targetLevel;
    }


    /**
     * @return the xactReasonFilterOption
     */
    @Override
    public String getXactReasonFilterOption() {
        return xactReasonFilterOption;
    }


    /**
     * @param xactReasonFilterOption the xactReasonFilterOption to set
     */
    @Override
    public void setXactReasonFilterOption(String xactReasonFilterOption) {
        this.xactReasonFilterOption = xactReasonFilterOption;
    }


    /**
     * @return the fromItemAmount
     */
    @Override
    public double getFromItemAmount() {
        return fromItemAmount;
    }


    /**
     * @param fromItemAmount the fromItemAmount to set
     */
    @Override
    public void setFromItemAmount(double fromItemAmount) {
        this.fromItemAmount = fromItemAmount;
    }


    /**
     * @return the toItemAmount
     */
    @Override
    public double getToItemAmount() {
        return toItemAmount;
    }


    /**
     * @param toItemAmount the toItemAmount to set
     */
    @Override
    public void setToItemAmount(double toItemAmount) {
        this.toItemAmount = toItemAmount;
    }


    /**
     * @return the fromItemAmountRelOp
     */
    @Override
    public String getFromItemAmountRelOp() {
        return fromItemAmountRelOp;
    }


    /**
     * @param fromItemAmountRelOp the fromItemAmountRelOp to set
     */
    @Override
    public void setFromItemAmountRelOp(String fromItemAmountRelOp) {
        this.fromItemAmountRelOp = fromItemAmountRelOp;
    }


    /**
     * @return the toItemAmountRelOp
     */
    @Override
    public String getToItemAmountRelOp() {
        return toItemAmountRelOp;
    }


    /**
     * @param toItemAmountRelOp the toItemAmountRelOp to set
     */
    @Override
    public void setToItemAmountRelOp(String toItemAmountRelOp) {
        this.toItemAmountRelOp = toItemAmountRelOp;
    }


    /**
     * @return the fromXactAmount
     */
    @Override
    public double getFromXactAmount() {
        return fromXactAmount;
    }


    /**
     * @param fromXactAmount the fromXactAmount to set
     */
    @Override
    public void setFromXactAmount(double fromXactAmount) {
        this.fromXactAmount = fromXactAmount;
    }


    /**
     * @return the toXactAmount
     */
    @Override
    public double getToXactAmount() {
        return toXactAmount;
    }


    /**
     * @param toXactAmount the toXactAmount to set
     */
    @Override
    public void setToXactAmount(double toXactAmount) {
        this.toXactAmount = toXactAmount;
    }


    /**
     * @return the fromXactAmountRelOp
     */
    @Override
    public String getFromXactAmountRelOp() {
        return fromXactAmountRelOp;
    }


    /**
     * @param fromXactAmountRelOp the fromXactAmountRelOp to set
     */
    @Override
    public void setFromXactAmountRelOp(String fromXactAmountRelOp) {
        this.fromXactAmountRelOp = fromXactAmountRelOp;
    }


    /**
     * @return the toXactAmountRelOp
     */
    @Override
    public String getToXactAmountRelOp() {
        return toXactAmountRelOp;
    }


    /**
     * @param toXactAmountRelOp the toXactAmountRelOp to set
     */
    @Override
    public void setToXactAmountRelOp(String toXactAmountRelOp) {
        this.toXactAmountRelOp = toXactAmountRelOp;
    }


    /**
     * @return the fromXactDate
     */
    @Override
    public Date getFromXactDate() {
        return fromXactDate;
    }


    /**
     * @param fromXactDate the fromXactDate to set
     */
    @Override
    public void setFromXactDate(Date fromXactDate) {
        this.fromXactDate = fromXactDate;
    }


    /**
     * @return the toXactDate
     */
    @Override
    public Date getToXactDate() {
        return toXactDate;
    }


    /**
     * @param toXactDate the toXactDate to set
     */
    @Override
    public void setToXactDate(Date toXactDate) {
        this.toXactDate = toXactDate;
    }


    /**
     * @return the fromXactDateRelOp
     */
    @Override
    public String getFromXactDateRelOp() {
        return fromXactDateRelOp;
    }


    /**
     * @param fromXactDateRelOp the fromXactDateRelOp to set
     */
    @Override
    public void setFromXactDateRelOp(String fromXactDateRelOp) {
        this.fromXactDateRelOp = fromXactDateRelOp;
    }


    /**
     * @return the toXactDateRelOp
     */
    @Override
    public String getToXactDateRelOp() {
        return toXactDateRelOp;
    }


    /**
     * @param toXactDateRelOp the toXactDateRelOp to set
     */
    @Override
    public void setToXactDateRelOp(String toXactDateRelOp) {
        this.toXactDateRelOp = toXactDateRelOp;
    }
   
}
