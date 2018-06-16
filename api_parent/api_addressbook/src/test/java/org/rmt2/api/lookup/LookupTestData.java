package org.rmt2.api.lookup;

import org.dao.mapping.orm.rmt2.GeneralCodes;
import org.dao.mapping.orm.rmt2.GeneralCodesGroup;
import org.dao.mapping.orm.rmt2.VwCodes;

public class LookupTestData {

    /**
     * 
     * @return
     */
    public static final GeneralCodesGroup createCodeGroupMockObject() {
        GeneralCodesGroup p = new GeneralCodesGroup();
        p.setCodeGrpId(100);
        p.setDescription("Group 1");
        return p;
    }
    
    /**
     * 
     * @return
     */
    public static final GeneralCodes createCodeMockObject() {
        GeneralCodes p = new GeneralCodes();
        p.setCodeGrpId(100);
        p.setCodeId(1000);
        p.setLongdesc("This is Code 1000");
        p.setShortdesc("Code 1000");
        return p;
    }
    
    /**
     * 
     * @return
     */
    public static final VwCodes createExtCodeMockObject() {
        VwCodes p = new VwCodes();
        p.setGroupId(100);
        p.setGroupDesc("Group 1");
        p.setCodeId(1000);
        p.setCodeLongdesc("This is Code 1000");
        p.setCodeShortdesc("Code 1000");
        return p;
    }
    
}
