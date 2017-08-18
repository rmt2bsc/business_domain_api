package org.modules.transaction;

/**
 * Constants defined to supplement the transaction module.
 * 
 * @author Roy Terrell
 * 
 */
public class XactConst {

    /** Bad Debts numerical Transaction Category Code */
    public static final int CATG_BADDEBT = 1;

    /** Cash Receipts numerical Transaction Category Code */
    public static final int CATG_CASHRECT = 2;

    /** Cash Payments numerical Transaction Category Code */
    public static final int CATG_CASHPAY = 3;

    /** Credit Sales numerical Transaction Category Code */
    public static final int CATG_CREDSALES = 4;

    /** Fees numerical Transaction Category Code */
    public static final int CATG_FEES = 6;

    /** Interest numerical Transaction Category Code */
    public static final int CATG_INTEREST = 7;

    /** Purchases numerical Transaction Category Code */
    public static final int CATG_PURCH = 8;

    /** Sales Returns numerical Transaction Category Code */
    public static final int CATG_SALESRET = 9;

    /** Bank Deposits numerical Transaction Category Code */
    public static final int CATG_DEPOSIT = 10;

    /** Bank Withdrawals numerical Transaction Category Code */
    public static final int CATG_WITHDRAW = 11;

    /** Write Offs numerical Transaction Category Code */
    public static final int CATG_WRITEOFF = 12;

    /** Purchases Returns numerical Transaction Category Code */
    public static final int CATG_PURCHRTN = 13;

    /** Sales Discounts numerical Transaction Category Code */
    public static final int CATG_SALESDISC = 14;

    /** Purchases Discounts numerical Transaction Category Code */
    public static final int CATG_PURCHDISC = 15;

    /** Sale of Merchandise numerical Transaction Category Code */
    public static final int CATG_MNDSESALES = 16;

    /** Reversal Transaction Category Code */
    public static final int CATG_REVERSE = 17;

    /** Cancellation Transaction Category Code */
    public static final int CATG_CANCEL = 18;

    // ////////////////////////////////////////////////////////////
    // Transaction Category String Codes
    // ////////////////////////////////////////////////////////////
    /** Bad Debts string Transaction Category Code */
    public static final String CATG_BADDEBT_STR = "BADDEBT";

    /** Cash Receipts string Transaction Category Code */
    public static final String CATG_CASHRECT_STR = "CASHRECT";

    /** Cash Payments string Transaction Category Code */
    public static final String CATG_CASHPAY_STR = "CASHPAY";

    /** Credit Sales string Transaction Category Code */
    public static final String CATG_CREDSALES_STR = "CREDSALES";

    /** Fees string Transaction Category Code */
    public static final String CATG_FEES_STR = "FEES";

    /** Interest string Transaction Category Code */
    public static final String CATG_INTEREST_STR = "INTEREST";

    /** Purchases string Transaction Category Code */
    public static final String CATG_PURCH_STR = "PURCH";

    /** Sales Returns string Transaction Categsory Code */
    public static final String CATG_SALESRET_STR = "SALESRET";

    /** Bank Deposits string Transaction Category Code */
    public static final String CATG_DEPOSIT_STR = "DEPOSIT";

    /** Bank Withdrawals string Transaction Category Code */
    public static final String CATG_WITHDRAW_STR = "WITHDRAW";

    /** Write Offs string Transaction Category Code */
    public static final String CATG_WRITEOFF_STR = "WRITEOFF";

    /** Purchases Returns string Transaction Category Code */
    public static final String CATG_PURCHRTN_STR = "PURCHRTN";

    /** Sales Discounts string Transaction Category Code */
    public static final String CATG_SALESDISC_STR = "SALESDISC";

    /** Purchases Discounts string Transaction Category Code */
    public static final String CATG_PURCHDISC_STR = "PURCHDISC";

    /** Sale of Merchandise string Transaction Category Code */
    public static final String CATG_MNDSESALES_STR = "REVERSE";

    /** Reverse string Transaction Category Code */
    public static final String CATG_REVERSE_STR = "MNDSESALES";

    /** Cancel string Transacton Category Code */
    public static final String CATG_CANCEL_STR = "CANCEL";

    /** Multiplier used to reverse transactions */
    public final static double REVERSE_MULTIPLIER = -1;

    /** Cash Tender */
    public final static int TENDER_CASH = 11;

    /** Check Tender */
    public final static int TENDER_CHECK = 12;

    /** Credit Card tender */
    public final static int TENDER_CREDITCARD = 13;

    /** Money Order tender */
    public final static int TENDER_MONEYORDER = 14;

    /** Finance Company Credit */
    public final static int TENDER_COMPANY_CREDIT = 15;

    /** Debit Card tender */
    public final static int TENDER_DEBITCARD = 16;

    /** Insurance tender */
    public final static int TENDER_INSURANCE = 17;

    /** Other tender */
    public final static int TENDER_OTHER = 18;

    /****************************************************************
     * Transaction Type Codes
     ****************************************************************/
    public final static int XACT_TYPE_CASHSALES = 1;

    public final static int XACT_TYPE_CASHPAY = 2;

    public final static int XACT_TYPE_SALESONACCTOUNT = 10;

    public final static int XACT_TYPE_CASHDISBACCT = 20;

    public final static int XACT_TYPE_CASHDISBEXP = 60;

    public final static int XACT_TYPE_CASHDISBASSET = 70;

    public final static int XACT_TYPE_SALESRETURNS = 90;

    public final static int XACT_TYPE_INVPURCHASES = 130;

    public final static int XACT_TYPE_CREDITCHARGE = 180;

    /***************************************************
     * Transaction Sub Types
     ***************************************************/
    public final static int XACT_SUBTYPE_NOT_ASSIGNED = 0;
    
    public final static int XACT_SUBTYPE_REVERSE = 999;

    public final static int XACT_SUBTYPE_CANCEL = 888;

    public final static int XACT_SUBTYPE_FINAL = -100;

}