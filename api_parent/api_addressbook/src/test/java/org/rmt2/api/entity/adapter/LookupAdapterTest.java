package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.GeneralCodes;
import org.dao.mapping.orm.rmt2.GeneralCodesGroup;
import org.dao.mapping.orm.rmt2.VwCodes;
import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.lookup.LookupTestData;

/**
 * Test Lookup related adapters
 * 
 * @author roy.terrell
 *
 */
public class LookupAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCodeGroupAdapter() {
        GeneralCodesGroup o = LookupTestData.createCodeGroupMockObject();
        LookupGroupDto dto = Rmt2AddressBookDtoFactory.getCodeInstance(o);

        Assert.assertEquals(100, dto.getGrpId());
        Assert.assertEquals("Group 1", dto.getGrpDescr());

        try {
            GeneralCodesGroup nullObject = null;
            dto = Rmt2AddressBookDtoFactory.getCodeInstance(nullObject);
            dto.setGrpId(100);
            dto.setGrpDescr("Group 1");

            Assert.assertEquals(100, dto.getGrpId());
            Assert.assertEquals("Group 1", dto.getGrpDescr());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for LookupGroupDto Adapater");
        }

    }

    @Test
    public void testCodeAdapter() {
        GeneralCodes o = LookupTestData.createCodeMockObject();
        LookupCodeDto dto = Rmt2AddressBookDtoFactory.getCodeInstance(o);

        Assert.assertEquals(100, dto.getGrpId());
        Assert.assertEquals(1000, dto.getCodeId());
        Assert.assertEquals("This is Code 1000", dto.getCodeLongName());
        Assert.assertEquals("Code 1000", dto.getCodeShortName());
        try {
            GeneralCodes nullObject = null;
            dto = Rmt2AddressBookDtoFactory.getCodeInstance(nullObject);
            dto.setGrpId(100);
            dto.setCodeId(1000);
            dto.setCodeLongName("This is Code 1000");
            dto.setCodeShortDesc("Code 1000");
            
            Assert.assertEquals(100, dto.getGrpId());
            Assert.assertEquals(1000, dto.getCodeId());
            Assert.assertEquals("This is Code 1000", dto.getCodeLongName());
            Assert.assertEquals("Code 1000", dto.getCodeShortName());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for LookupCodeDto Adapater");
        }
    }
    
    @Test
    public void testExtCodeAdapter() {
        VwCodes o = LookupTestData.createExtCodeMockObject();
        LookupExtDto dto = Rmt2AddressBookDtoFactory.getCodeInstance(o);

        Assert.assertEquals(100, dto.getGrpId());
        Assert.assertEquals("Group 1", dto.getGrpDescr());
        Assert.assertEquals(1000, dto.getCodeId());
        Assert.assertEquals("This is Code 1000", dto.getCodeLongName());
        Assert.assertEquals("Code 1000", dto.getCodeShortName());
        try {
            VwCodes nullObject = null;
            dto = Rmt2AddressBookDtoFactory.getCodeInstance(nullObject);
            dto.setGrpId(100);
            dto.setGrpDescr("Group 1");
            dto.setCodeId(1000);
            dto.setCodeLongName("This is Code 1000");
            dto.setCodeShortDesc("Code 1000");
            
            Assert.assertEquals(100, dto.getGrpId());
            Assert.assertEquals("Group 1", dto.getGrpDescr());
            Assert.assertEquals(1000, dto.getCodeId());
            Assert.assertEquals("This is Code 1000", dto.getCodeLongName());
            Assert.assertEquals("Code 1000", dto.getCodeShortName());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for LookupExtDto Adapater");
        }

    }
}
