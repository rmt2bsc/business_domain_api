import org.dao.mapping.orm.rmt2.Customer;
import org.dao.transaction.purchases.vendor.VendorPurchasesConst;
import org.junit.Test;

import com.util.RMT2String;

public class TestDummy {

    @Test
    public void testInstance() {
        methodCall(null);
        Customer cust = null;
        if (cust instanceof Customer) {
            System.out.println("this is true");
        }
    }

    public static void methodCall(Object obj) {
        System.out.println("object");
    }

    public static void methodCall(String str) {
        System.out.println("String");
    }

    // public static void methodCall(Integer intt) {
    // System.out.println("Integer");
    //
    // }
    
    @Test
    public void testSqlReplacement() {
        int poId = 12938;
        String sql = RMT2String.replace(VendorPurchasesConst.SQL_UNASSIGNED_PO_ITEMS, String.valueOf(poId), "?");
        System.out.print(sql);
    }
}
