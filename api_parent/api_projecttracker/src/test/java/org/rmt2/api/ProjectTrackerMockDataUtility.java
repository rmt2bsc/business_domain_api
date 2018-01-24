package org.rmt2.api;

import org.dao.mapping.orm.rmt2.ProjClient;

public class ProjectTrackerMockDataUtility {

    public static final ProjClient createMockOrmProjClient(int clientId,
            int businessId, String name, double billRate, double otBillRate,
            String acctNo, String firstName, String lastName, String phone, 
            String email) {
        ProjClient orm = new ProjClient();
       
        orm.setName(name);
        return orm;
    }

}
