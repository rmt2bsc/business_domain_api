package org.rmt2.api.generalledger;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.dao.generalledger.GeneralLedgerDao;
import org.dao.generalledger.GeneralLedgerDaoFactory;
import org.dto.AccountDto;
import org.dto.AccountTypeDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the general ledger DAO library.
 * 
 * @author rterrell
 * 
 */
public class AccountDaoTest {
    private GeneralLedgerDaoFactory f;

    private GeneralLedgerDao dao;

    private List<Integer> acctKeys;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new GeneralLedgerDaoFactory();
        this.dao = this.f.createRmt2OrmDao();
        this.dao.setDaoUser("Testuser");
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    private void createData() {
        int newId = 0;
        this.acctKeys = new ArrayList<Integer>();
        AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(null);

        dto.setAcctTypeId(7);
        dto.setAcctCatgId(701);
        dto.setAcctName("Test Account #1");
        dto.setAcctCode("CODE_1");
        dto.setAcctDescription("Test DESCRIPTION");
        dto.setBalanceTypeId(1);
        newId = this.dao.maintainAccount(dto);
        Assert.assertTrue(newId > 0);
        this.acctKeys.add(newId);

        dto = Rmt2AccountDtoFactory.createAccountInstance(null);
        dto.setAcctTypeId(5);
        dto.setAcctCatgId(501);
        dto.setAcctName("Test Account #2");
        dto.setAcctCode("CODE_2");
        dto.setAcctDescription("Test DESCRIPTION");
        dto.setBalanceTypeId(2);
        newId = this.dao.maintainAccount(dto);
        Assert.assertTrue(newId > 0);
        this.acctKeys.add(newId);

    }

    private void deleteData() {
        int rc = 0;
        for (int key : this.acctKeys) {
            rc = this.dao.deleteAccount(key);
            Assert.assertTrue(rc > 0);
        }
    }

    @Test
    public void testTypeStockFetchAll() {
        List<AccountTypeDto> list = null;
        try {
            list = this.dao.fetchType(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Assert.assertTrue(list.get(0) instanceof AccountTypeDto);
    }

    @Test
    public void testStockFetchAll() {
        List<AccountDto> list = null;
        try {
            list = this.dao.fetchAccount(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Assert.assertTrue(list.get(0) instanceof AccountDto);
    }

    @Test
    public void testStockFetchById() {
        int acctId = 20;
        String acctName = "Accounts Payable";
        String acctCode = "ACCTPAY";

        List<AccountDto> list = null;
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        criteria.setAcctId(acctId);
        try {
            list = this.dao.fetchAccount(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.get(0) instanceof AccountDto);
        AccountDto dto = list.get(0);
        Assert.assertEquals(acctId, dto.getAcctId());
        Assert.assertEquals(acctName, dto.getAcctName());
        Assert.assertEquals(acctCode, dto.getAcctCode());

    }

    @Test
    public void testFetchByName() {
        String acctName = "Test Account #1";
        List<AccountDto> list = null;
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        criteria.setAcctName(acctName);
        try {
            list = this.dao.fetchAccount(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.get(0) instanceof AccountDto);
        AccountDto dto = list.get(0);
        Assert.assertEquals(acctName, dto.getAcctName());
    }

    @Test
    public void addWithDuplicateName() {
        int newId = 0;
        AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(null);
        dto.setAcctTypeId(7);
        dto.setAcctCatgId(701);
        dto.setAcctName("Test Account #1");
        dto.setAcctCode("CODE_1");
        dto.setAcctDescription("Test DESCRIPTION");
        dto.setBalanceTypeId(1);
        try {
            newId = this.dao.maintainAccount(dto);
            System.out.println("New account id: " + newId);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Account was created with a duplicate account name");
    }

    @Test
    public void update() {
        String origAcctName = "Test Account #1";
        String newAcctName = "RoyTerrell";
        int acctId = this.acctKeys.get(0);
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        List<AccountDto> results = null;
        criteria.setAcctId(acctId);
        int rc = 0;
        try {
            results = this.dao.fetchAccount(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountDto dto = results.get(0);
            Assert.assertEquals(origAcctName, dto.getAcctName());

            dto.setAcctName(newAcctName);
            rc = this.dao.maintainAccount(dto);
            Assert.assertEquals(1, rc);

            results = this.dao.fetchAccount(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            dto = results.get(0);
            Assert.assertEquals(newAcctName, dto.getAcctName());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void updateWithDuplicateName() {
        String origAcctName = "Test Account #1";
        String newAcctName = "Test Account #2";
        int acctId = this.acctKeys.get(0);
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        List<AccountDto> results = null;
        criteria.setAcctId(acctId);
        try {
            results = this.dao.fetchAccount(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountDto dto = results.get(0);
            Assert.assertEquals(origAcctName, dto.getAcctName());

            dto.setAcctName(newAcctName);
            this.dao.maintainAccount(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Account was successfully changed with a duplicate account name");
    }

    @Test
    public void updateWithInvalidAccountCategory() {
        String origAcctName = "Test Account #1";
        int invalidCatgId = 777;
        int acctId = this.acctKeys.get(0);
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        List<AccountDto> results = null;
        criteria.setAcctId(acctId);
        try {
            results = this.dao.fetchAccount(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountDto dto = results.get(0);
            Assert.assertEquals(origAcctName, dto.getAcctName());

            dto.setAcctCatgId(invalidCatgId);
            this.dao.maintainAccount(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Account was successfully changed with an invalid account category id");
    }

    @Test
    public void updateWithInvalidAccountType() {
        String origAcctName = "Test Account #1";
        int invalidTypeId = 777;
        int acctId = this.acctKeys.get(0);
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        List<AccountDto> results = null;
        criteria.setAcctId(acctId);
        try {
            results = this.dao.fetchAccount(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountDto dto = results.get(0);
            Assert.assertEquals(origAcctName, dto.getAcctName());

            dto.setAcctTypeId(invalidTypeId);
            this.dao.maintainAccount(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Account was successfully changed with an invalid account type id");
    }
}
