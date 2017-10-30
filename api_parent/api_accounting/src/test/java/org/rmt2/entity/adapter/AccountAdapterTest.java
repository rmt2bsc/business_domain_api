package org.rmt2.entity.adapter;

import org.dao.mapping.orm.rmt2.GlAccountCategory;
import org.dao.mapping.orm.rmt2.GlAccountTypes;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dao.mapping.orm.rmt2.VwAccount;
import org.dto.AccountCategoryDto;
import org.dto.AccountDto;
import org.dto.AccountExtDto;
import org.dto.AccountTypeDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.AccountingMockDataUtility;

/**
 * Test GL Account related adapters
 * 
 * @author roy.terrell
 *
 */
public class AccountAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAccountAdapter() {
        GlAccounts o = AccountingMockDataUtility.createMockOrmGlAccounts(100,
                200, 300, 1, "GL_100", "ACCT_RECV", "234",
                "Accounts Receivable", 1);
        AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(o);
        
        Assert.assertEquals("234", dto.getAcctCode());
        Assert.assertEquals("Accounts Receivable", dto.getAcctDescription());
        Assert.assertEquals("ACCT_RECV", dto.getAcctName());
        Assert.assertEquals("GL_100", dto.getAcctNo());
        Assert.assertEquals(300, dto.getAcctCatgId());
        Assert.assertEquals(100, dto.getAcctId());
        Assert.assertEquals(1, dto.getAcctSeq());
        Assert.assertEquals(200, dto.getAcctTypeId());
        Assert.assertEquals(1, dto.getBalanceTypeId());
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
    
    @Test
    public void testAccountTypeAdapter() {
        GlAccountTypes o1 = AccountingMockDataUtility.createMockOrmGlAccountTypes(100, 1, "Asset");
        AccountTypeDto dto = Rmt2AccountDtoFactory.createAccountTypeInstance(o1);
        
        Assert.assertEquals("Asset", dto.getAcctTypeDescription());
        Assert.assertEquals(100, dto.getAcctTypeId());
        Assert.assertEquals(1, dto.getBalanceTypeId());
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
    
    
    @Test
    public void testAccountCategoryAdapter() {
        GlAccountCategory o = AccountingMockDataUtility.createMockOrmGlAccountCategory(100, 1, "Category1");
        AccountCategoryDto dto= Rmt2AccountDtoFactory.createAccountCategoryInstance(o);
        
        Assert.assertEquals("Category1", dto.getAcctCatgDescription());
        Assert.assertEquals(1, dto.getAcctTypeId());
        Assert.assertEquals(100, dto.getAcctCatgId());
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
    
    @Test
    public void testAccountViewAdapter() {
        VwAccount o = AccountingMockDataUtility.createMockOrmVwAccounts(100,
                200, 300, 1, "GL_100", "ACCT_RECV", "234",
                "Accounts Receivable", 1);
        AccountExtDto dto = Rmt2AccountDtoFactory.createAccountExtInstance(o);
        
        Assert.assertEquals("234", dto.getAcctCode());
        Assert.assertEquals("Accounts Receivable", dto.getAcctDescription());
        Assert.assertEquals("ACCT_RECV", dto.getAcctName());
        Assert.assertEquals("GL_100", dto.getAcctNo());
        Assert.assertEquals(300, dto.getAcctCatgId());
        Assert.assertEquals(100, dto.getAcctId());
        Assert.assertEquals(1, dto.getAcctSeq());
        Assert.assertEquals(200, dto.getAcctTypeId());
        Assert.assertEquals(1, dto.getBalanceTypeId());
        Assert.assertEquals("AccountTypeDescription100", dto.getAcctTypeDescription());
        Assert.assertEquals("AccountCategoryDescription300", dto.getAcctCatgDescription());
        try {
            Assert.assertEquals(0, dto.getEntityId());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
        try {
            Assert.assertNull(dto.getEntityName());
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        }
        catch (UnsupportedOperationException e) {
            // Test succeeded...
        }
    }
}
