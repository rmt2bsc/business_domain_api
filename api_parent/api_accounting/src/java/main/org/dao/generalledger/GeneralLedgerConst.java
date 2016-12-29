package org.dao.generalledger;

public class GeneralLedgerConst {

    public static final int DEBIT_BAL_TYPE = 1;

    public static final int CREDIT_BAL_TYPE = 2;

    /** Asset GL Account Type Code */
    public static final int ACCT_TYPE_ASSET = 1;

    /** Liability GL Account Type Code */
    public static final int ACCT_TYPE_LIABILITY = 2;

    /** Capital/ Owners Equity GL Account Type Code */
    public static final int ACCT_TYPE_CAPITAL = 3;

    /** Revenue GL Account Type Code */
    public static final int ACCT_TYPE_REVENUE = 4;

    /** Expense GL Account Type Code */
    public static final int ACCT_TYPE_EXPENSE = 5;

    // General ledger module messages
    public static final String MSG_ACCT_NAME_INVALID = "Account Name must be entered";

    public static final String MSG_ACCTCATG_NAME_INVALID = "Account Category Name is invalid";

    public static final String MSG_DUP_ACCTCATG_NAME = "Duplicate Account Category Name";

    public static final String MSG_DUP_ACCT_NAME = "Duplicate GL Account Name";

    public static final String MSG_ACCTTYPE_NOTEXIST = "Account Type does not exist";

    public static final String MSG_ACCTNOERROR_ACCTTYPE = "Account Number cannot be created without a valid account type code";

    public static final String MSG_ACCTNOERROR_ACCTCAT = "Account Number cannot be created without a valid account category type code";

    public static final String MSG_ACCTNOERROR_ACCTSEQ = "Account Number cannot be created without a valid account sequence number";

    public static final String MSG_ACCTCATG_NOTEXIST = "Account Category does not exist";

    public static final String MSG_ACCT_NOTEXIST = "General Ledger Account does not exist";

    public static final String MSG_FUNCNAME_INVALID = "Function Name must be provided to obtain a count of Account associations";

    public static final String MSG_CRED3_INVALID = "Function Name must be provided to obtain a count of Account associations";

}