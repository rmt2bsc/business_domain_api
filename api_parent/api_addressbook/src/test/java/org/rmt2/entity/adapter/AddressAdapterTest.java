package org.rmt2.entity.adapter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test GL Account related adapters
 * 
 * @author roy.terrell
 *
 */
public class AddressAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddresAdapter() {
        Assert.fail("Implement test case");
//        GlAccounts o = AccountingMockDataFactory.createMockOrmGlAccounts(100,
// 200, 300, 1, "GL_100", "ACCT_RECV",
//                "234", "Accounts Receivable", 1);
//        AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(o);
//        
//        Assert.assertEquals("234", dto.getAcctCode());
//        Assert.assertEquals("Accounts Receivable", dto.getAcctDescription());
//        Assert.assertEquals("ACCT_RECV", dto.getAcctName());
//        Assert.assertEquals("GL_100", dto.getAcctNo());
//        Assert.assertEquals(300, dto.getAcctCatgId());
//        Assert.assertEquals(100, dto.getAcctId());
//        Assert.assertEquals(1, dto.getAcctSeq());
//        Assert.assertEquals(200, dto.getAcctTypeId());
//        Assert.assertEquals(1, dto.getBalanceTypeId());
//        try {
//            Assert.assertEquals(0, dto.getEntityId());
//            Assert.fail("Expected UnsupportedOperationException to be thrown");
//        }
//        catch (UnsupportedOperationException e) {
//            // Test succeeded...
//        }
//        try {
//            Assert.assertNull(dto.getEntityName());
//            Assert.fail("Expected UnsupportedOperationException to be thrown");
//        }
//        catch (UnsupportedOperationException e) {
//            // Test succeeded...
//        }
//
//        try {
//            dto = Rmt2AccountDtoFactory.createAccountInstance(null);
//            dto.setAcctCode("234");
//            dto.setAcctDescription("Accounts Receivable");
//            dto.setAcctName("ACCT_RECV");
//            dto.setAcctNo("GL_100");
//            dto.setAcctCatgId(300);
//            dto.setAcctId(100);
//            dto.setAcctSeq(1);
//            dto.setAcctTypeId(200);
//            dto.setBalanceTypeId(1);
//
//            Assert.assertEquals("234", dto.getAcctCode());
//            Assert.assertEquals("Accounts Receivable", dto.getAcctDescription());
//            Assert.assertEquals("ACCT_RECV", dto.getAcctName());
//            Assert.assertEquals("GL_100", dto.getAcctNo());
//            Assert.assertEquals(300, dto.getAcctCatgId());
//            Assert.assertEquals(100, dto.getAcctId());
//            Assert.assertEquals(1, dto.getAcctSeq());
//            Assert.assertEquals(200, dto.getAcctTypeId());
//            Assert.assertEquals(1, dto.getBalanceTypeId());
//            try {
//                Assert.assertEquals(0, dto.getEntityId());
//                Assert.fail("Expected UnsupportedOperationException to be thrown");
//            } catch (UnsupportedOperationException e) {
//                // Test succeeded...
//            }
//            try {
//                Assert.assertNull(dto.getEntityName());
//                Assert.fail("Expected UnsupportedOperationException to be thrown");
//            } catch (UnsupportedOperationException e) {
//                // Test succeeded...
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail("An exception occurred setting properties for AccountDto Adapater");
//        }

    }
    
    @Test
    public void testCommonContactAddressAdapter() {
        Assert.fail("Implement test case");
    }
    
    
    @Test
    public void testBusinessAddressAdapter() {
        Assert.fail("Implement test case");
    }
    
    @Test
    public void testPersonAddressAdapter() {
        Assert.fail("Implement test case");
    }
}
