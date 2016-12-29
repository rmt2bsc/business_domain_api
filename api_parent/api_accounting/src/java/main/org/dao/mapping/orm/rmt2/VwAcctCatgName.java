package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the vw_acct_catg_name database table/view.
 * 
 * @author auto generated.
 */
public class VwAcctCatgName extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // VwAcctCatgNameView

    /**
     * The property name constant equivalent to property, AcctTypeId, of
     * respective DataSource view.
     */
    public static final String PROP_ACCTTYPEID = "AcctTypeId";
    /**
     * The property name constant equivalent to property, AcctBaltypeId, of
     * respective DataSource view.
     */
    public static final String PROP_ACCTBALTYPEID = "AcctBaltypeId";
    /**
     * The property name constant equivalent to property, Description, of
     * respective DataSource view.
     */
    public static final String PROP_DESCRIPTION = "Description";
    /**
     * The property name constant equivalent to property, AcctId, of respective
     * DataSource view.
     */
    public static final String PROP_ACCTID = "AcctId";
    /**
     * The property name constant equivalent to property, Name, of respective
     * DataSource view.
     */
    public static final String PROP_NAME = "Name";
    /**
     * The property name constant equivalent to property, GlCatgDescription, of
     * respective DataSource view.
     */
    public static final String PROP_GLCATGDESCRIPTION = "GlCatgDescription";
    /**
     * The property name constant equivalent to property, AcctCatgId, of
     * respective DataSource view.
     */
    public static final String PROP_ACCTCATGID = "AcctCatgId";
    /**
     * The property name constant equivalent to property, AcctNo, of respective
     * DataSource view.
     */
    public static final String PROP_ACCTNO = "AcctNo";
    /**
     * The property name constant equivalent to property, AcctSeq, of respective
     * DataSource view.
     */
    public static final String PROP_ACCTSEQ = "AcctSeq";

    /**
     * The javabean property equivalent of database column
     * vw_acct_catg_name.acct_type_id
     */
    private int acctTypeId;
    /**
     * The javabean property equivalent of database column
     * vw_acct_catg_name.acct_baltype_id
     */
    private int acctBaltypeId;
    /**
     * The javabean property equivalent of database column
     * vw_acct_catg_name.description
     */
    private String description;
    /**
     * The javabean property equivalent of database column
     * vw_acct_catg_name.acct_id
     */
    private int acctId;
    /**
     * The javabean property equivalent of database column
     * vw_acct_catg_name.name
     */
    private String name;
    /**
     * The javabean property equivalent of database column
     * vw_acct_catg_name.gl_catg_description
     */
    private String glCatgDescription;
    /**
     * The javabean property equivalent of database column
     * vw_acct_catg_name.acct_catg_id
     */
    private int acctCatgId;
    /**
     * The javabean property equivalent of database column
     * vw_acct_catg_name.acct_no
     */
    private String acctNo;
    /**
     * The javabean property equivalent of database column
     * vw_acct_catg_name.acct_seq
     */
    private int acctSeq;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public VwAcctCatgName() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable acctTypeId
     */
    public void setAcctTypeId(int value) {
        this.acctTypeId = value;
    }

    /**
     * Gets the value of member variable acctTypeId
     */
    public int getAcctTypeId() {
        return this.acctTypeId;
    }

    /**
     * Sets the value of member variable acctBaltypeId
     */
    public void setAcctBaltypeId(int value) {
        this.acctBaltypeId = value;
    }

    /**
     * Gets the value of member variable acctBaltypeId
     */
    public int getAcctBaltypeId() {
        return this.acctBaltypeId;
    }

    /**
     * Sets the value of member variable description
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of member variable description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of member variable acctId
     */
    public void setAcctId(int value) {
        this.acctId = value;
    }

    /**
     * Gets the value of member variable acctId
     */
    public int getAcctId() {
        return this.acctId;
    }

    /**
     * Sets the value of member variable name
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of member variable name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of member variable glCatgDescription
     */
    public void setGlCatgDescription(String value) {
        this.glCatgDescription = value;
    }

    /**
     * Gets the value of member variable glCatgDescription
     */
    public String getGlCatgDescription() {
        return this.glCatgDescription;
    }

    /**
     * Sets the value of member variable acctCatgId
     */
    public void setAcctCatgId(int value) {
        this.acctCatgId = value;
    }

    /**
     * Gets the value of member variable acctCatgId
     */
    public int getAcctCatgId() {
        return this.acctCatgId;
    }

    /**
     * Sets the value of member variable acctNo
     */
    public void setAcctNo(String value) {
        this.acctNo = value;
    }

    /**
     * Gets the value of member variable acctNo
     */
    public String getAcctNo() {
        return this.acctNo;
    }

    /**
     * Sets the value of member variable acctSeq
     */
    public void setAcctSeq(int value) {
        this.acctSeq = value;
    }

    /**
     * Gets the value of member variable acctSeq
     */
    public int getAcctSeq() {
        return this.acctSeq;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}