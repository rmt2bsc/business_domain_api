package org.modules.subsidiary;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.AccountingConst;
import org.AccountingConst.SubsidiaryType;

/**
 * A helper class for subsidiary processes.
 * 
 * @author Roy Terrell
 * 
 */
public class SubsidiaryHelper {

    /**
     * Default constructor
     */
    public SubsidiaryHelper() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructs an account number for the subsidiary.
     * <p>
     * The format of the account number is:
     * 
     * <pre>
     *    &lt;U&gt;&lt;business id&gt; - &lt;current year&gt; &lt;current month&gt; &lt;current da&gt;
     * </pre>
     * 
     * @param businessId
     *            The business id of the subsidiary
     * @param subType
     *            an enum type of
     *            {@link org.modules.subsidiary.AccountingConst.SubsidiaryType}
     *            which identifies the type of subsidiary.
     * @return The account number.
     */
    public String buildAccountNo(int businessId, SubsidiaryType subType) {
        // Get current date
        Calendar cal = new GregorianCalendar();
        int yr = cal.get(Calendar.YEAR);
        int mm = cal.get(Calendar.MONTH) + 1;
        int dd = cal.get(Calendar.DAY_OF_MONTH);

        // Build account number
        StringBuffer acctNo = new StringBuffer(10);
        switch (subType) {
            case CREDITOR:
                // The character, "R", identifies creditors
                acctNo.append(AccountingConst.SUBSIDIARY_ACCT_ABBREV_CREDITOR);
            case CUSTOMER:
                // The character, "U", identifies customers
                acctNo.append(AccountingConst.SUBSIDIARY_ACCT_ABBREV_CUSTOMER);
        }
        acctNo.append(businessId);
        acctNo.append("-");
        acctNo.append(yr);
        acctNo.append((mm < 10 ? "0" + mm : mm));
        acctNo.append((dd < 10 ? "0" + dd : dd));
        return acctNo.toString();
    }

}
