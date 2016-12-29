package org;

public class AccountingConst {

    /** Subsidiary type */
    public enum SubsidiaryType {
        CREDITOR, CUSTOMER
    }

    /**
     * Vendor creditor type value
     */
    public static int CREDITORTYPE_VENDOR = 1;

    /**
     * General Creditor type value
     */
    public static int CREDITORTYPE_CREDITOR = 2;

    public static final String SUBSIDIARY_ACCT_ABBREV_CUSTOMER = "U";

    public static final String SUBSIDIARY_ACCT_ABBREV_CREDITOR = "R";

    public static final int ACCT_TYPE_ASSET = 1;

    public static final int ACCT_TYPE_LIABILITY = 2;

    public static final int ACCT_TYPE_OWNEREQUITY = 3;

    public static final int ACCT_TYPE_REVENUE = 4;

    public static final int ACCT_TYPE_EXPENSE = 5;

    public static final String ACCT_NAME_ACCTRCV = "Accounts Receivable";

    public static final String ACCT_NAME_ACCTPAY = "Accounts Payable";

    public static final int ACCT_ACTIVE = 1;

    public static final int ACCT_INACTIVE = 0;
}