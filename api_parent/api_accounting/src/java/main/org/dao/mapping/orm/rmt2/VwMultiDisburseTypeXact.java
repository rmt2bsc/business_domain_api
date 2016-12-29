package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the vw_multi_disburse_type_xact database table/view.
 * 
 * @author auto generated.
 */
public class VwMultiDisburseTypeXact extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // VwMultiDisburseTypeXactView

    /**
     * The property name constant equivalent to property, XactId, of respective
     * DataSource view.
     */
    public static final String PROP_XACTID = "XactId";
    /**
     * The property name constant equivalent to property, XactAmount, of
     * respective DataSource view.
     */
    public static final String PROP_XACTAMOUNT = "XactAmount";
    /**
     * The property name constant equivalent to property, PostedDate, of
     * respective DataSource view.
     */
    public static final String PROP_POSTEDDATE = "PostedDate";
    /**
     * The property name constant equivalent to property, ConfirmNo, of
     * respective DataSource view.
     */
    public static final String PROP_CONFIRMNO = "ConfirmNo";
    /**
     * The property name constant equivalent to property, NegInstrNo, of
     * respective DataSource view.
     */
    public static final String PROP_NEGINSTRNO = "NegInstrNo";
    /**
     * The property name constant equivalent to property, TenderId, of
     * respective DataSource view.
     */
    public static final String PROP_TENDERID = "TenderId";
    /**
     * The property name constant equivalent to property, TenderDescription, of
     * respective DataSource view.
     */
    public static final String PROP_TENDERDESCRIPTION = "TenderDescription";
    /**
     * The property name constant equivalent to property, XactTypeId, of
     * respective DataSource view.
     */
    public static final String PROP_XACTTYPEID = "XactTypeId";
    /**
     * The property name constant equivalent to property, XactTypeName, of
     * respective DataSource view.
     */
    public static final String PROP_XACTTYPENAME = "XactTypeName";
    /**
     * The property name constant equivalent to property, Reason, of respective
     * DataSource view.
     */
    public static final String PROP_REASON = "Reason";
    /**
     * The property name constant equivalent to property, XactDate, of
     * respective DataSource view.
     */
    public static final String PROP_XACTDATE = "XactDate";
    /**
     * The property name constant equivalent to property, CreateDate, of
     * respective DataSource view.
     */
    public static final String PROP_CREATEDATE = "CreateDate";
    /**
     * The property name constant equivalent to property, UserId, of respective
     * DataSource view.
     */
    public static final String PROP_USERID = "UserId";
    /**
     * The property name constant equivalent to property, XactCatgId, of
     * respective DataSource view.
     */
    public static final String PROP_XACTCATGID = "XactCatgId";
    /**
     * The property name constant equivalent to property,
     * XactTypeItemXactTypeId, of respective DataSource view.
     */
    public static final String PROP_XACTTYPEITEMXACTTYPEID = "XactTypeItemXactTypeId";
    /**
     * The property name constant equivalent to property, CreditorId, of
     * respective DataSource view.
     */
    public static final String PROP_CREDITORID = "CreditorId";
    /**
     * The property name constant equivalent to property, AccountNumber, of
     * respective DataSource view.
     */
    public static final String PROP_ACCOUNTNUMBER = "AccountNumber";
    /**
     * The property name constant equivalent to property, CreditorTypeId, of
     * respective DataSource view.
     */
    public static final String PROP_CREDITORTYPEID = "CreditorTypeId";
    /**
     * The property name constant equivalent to property, CreditLimit, of
     * respective DataSource view.
     */
    public static final String PROP_CREDITLIMIT = "CreditLimit";

    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.xact_id
     */
    private int xactId;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.xact_amount
     */
    private double xactAmount;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.posted_date
     */
    private java.util.Date postedDate;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.confirm_no
     */
    private String confirmNo;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.neg_instr_no
     */
    private String negInstrNo;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.tender_id
     */
    private int tenderId;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.tender_description
     */
    private String tenderDescription;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.xact_type_id
     */
    private int xactTypeId;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.xact_type_name
     */
    private String xactTypeName;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.reason
     */
    private String reason;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.xact_date
     */
    private java.util.Date xactDate;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.create_date
     */
    private java.util.Date createDate;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.user_id
     */
    private String userId;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.xact_catg_id
     */
    private int xactCatgId;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.xact_type_item_xact_type_id
     */
    private int xactTypeItemXactTypeId;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.creditor_id
     */
    private int creditorId;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.account_number
     */
    private String accountNumber;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.creditor_type_id
     */
    private int creditorTypeId;
    /**
     * The javabean property equivalent of database column
     * vw_multi_disburse_type_xact.credit_limit
     */
    private double creditLimit;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public VwMultiDisburseTypeXact() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable xactId
     */
    public void setXactId(int value) {
        this.xactId = value;
    }

    /**
     * Gets the value of member variable xactId
     */
    public int getXactId() {
        return this.xactId;
    }

    /**
     * Sets the value of member variable xactAmount
     */
    public void setXactAmount(double value) {
        this.xactAmount = value;
    }

    /**
     * Gets the value of member variable xactAmount
     */
    public double getXactAmount() {
        return this.xactAmount;
    }

    /**
     * Sets the value of member variable postedDate
     */
    public void setPostedDate(java.util.Date value) {
        this.postedDate = value;
    }

    /**
     * Gets the value of member variable postedDate
     */
    public java.util.Date getPostedDate() {
        return this.postedDate;
    }

    /**
     * Sets the value of member variable confirmNo
     */
    public void setConfirmNo(String value) {
        this.confirmNo = value;
    }

    /**
     * Gets the value of member variable confirmNo
     */
    public String getConfirmNo() {
        return this.confirmNo;
    }

    /**
     * Sets the value of member variable negInstrNo
     */
    public void setNegInstrNo(String value) {
        this.negInstrNo = value;
    }

    /**
     * Gets the value of member variable negInstrNo
     */
    public String getNegInstrNo() {
        return this.negInstrNo;
    }

    /**
     * Sets the value of member variable tenderId
     */
    public void setTenderId(int value) {
        this.tenderId = value;
    }

    /**
     * Gets the value of member variable tenderId
     */
    public int getTenderId() {
        return this.tenderId;
    }

    /**
     * Sets the value of member variable tenderDescription
     */
    public void setTenderDescription(String value) {
        this.tenderDescription = value;
    }

    /**
     * Gets the value of member variable tenderDescription
     */
    public String getTenderDescription() {
        return this.tenderDescription;
    }

    /**
     * Sets the value of member variable xactTypeId
     */
    public void setXactTypeId(int value) {
        this.xactTypeId = value;
    }

    /**
     * Gets the value of member variable xactTypeId
     */
    public int getXactTypeId() {
        return this.xactTypeId;
    }

    /**
     * Sets the value of member variable xactTypeName
     */
    public void setXactTypeName(String value) {
        this.xactTypeName = value;
    }

    /**
     * Gets the value of member variable xactTypeName
     */
    public String getXactTypeName() {
        return this.xactTypeName;
    }

    /**
     * Sets the value of member variable reason
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of member variable reason
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * Sets the value of member variable xactDate
     */
    public void setXactDate(java.util.Date value) {
        this.xactDate = value;
    }

    /**
     * Gets the value of member variable xactDate
     */
    public java.util.Date getXactDate() {
        return this.xactDate;
    }

    /**
     * Sets the value of member variable createDate
     */
    public void setCreateDate(java.util.Date value) {
        this.createDate = value;
    }

    /**
     * Gets the value of member variable createDate
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * Sets the value of member variable userId
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of member variable userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Sets the value of member variable xactCatgId
     */
    public void setXactCatgId(int value) {
        this.xactCatgId = value;
    }

    /**
     * Gets the value of member variable xactCatgId
     */
    public int getXactCatgId() {
        return this.xactCatgId;
    }

    /**
     * Sets the value of member variable xactTypeItemXactTypeId
     */
    public void setXactTypeItemXactTypeId(int value) {
        this.xactTypeItemXactTypeId = value;
    }

    /**
     * Gets the value of member variable xactTypeItemXactTypeId
     */
    public int getXactTypeItemXactTypeId() {
        return this.xactTypeItemXactTypeId;
    }

    /**
     * Sets the value of member variable creditorId
     */
    public void setCreditorId(int value) {
        this.creditorId = value;
    }

    /**
     * Gets the value of member variable creditorId
     */
    public int getCreditorId() {
        return this.creditorId;
    }

    /**
     * Sets the value of member variable accountNumber
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of member variable accountNumber
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Sets the value of member variable creditorTypeId
     */
    public void setCreditorTypeId(int value) {
        this.creditorTypeId = value;
    }

    /**
     * Gets the value of member variable creditorTypeId
     */
    public int getCreditorTypeId() {
        return this.creditorTypeId;
    }

    /**
     * Sets the value of member variable creditLimit
     */
    public void setCreditLimit(double value) {
        this.creditLimit = value;
    }

    /**
     * Gets the value of member variable creditLimit
     */
    public double getCreditLimit() {
        return this.creditLimit;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}