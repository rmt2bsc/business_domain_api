package generalledger;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.dto.AccountDto;
import org.dto.AccountTypeDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.generalledger.GeneralLedgerApiFactory;
import org.modules.generalledger.GlAccountApi;

/**
 * Tests the general ledger API library.
 * 
 * @author rterrell
 * 
 */
public class AccountApiTest {
    private GeneralLedgerApiFactory f;

    private GlAccountApi api;

    private List<Integer> acctKeys;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new GeneralLedgerApiFactory();
        this.api = this.f.createApi();
        this.api.setApiUser("Testuser");
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
        this.api = null;
        this.f = null;
    }

    private void createData() throws Exception {
        int newId = 0;
        this.acctKeys = new ArrayList<Integer>();
        AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(null);

        dto.setAcctTypeId(7);
        dto.setAcctCatgId(701);
        dto.setAcctName("Test Account #1");
        dto.setAcctCode("CODE_1");
        dto.setAcctDescription("Test DESCRIPTION");
        dto.setBalanceTypeId(1);
        newId = this.api.updateAccount(dto);
        Assert.assertTrue(newId > 0);
        this.acctKeys.add(newId);

        dto = Rmt2AccountDtoFactory.createAccountInstance(null);
        dto.setAcctTypeId(5);
        dto.setAcctCatgId(501);
        dto.setAcctName("Test Account #2");
        dto.setAcctCode("CODE_2");
        dto.setAcctDescription("Test DESCRIPTION");
        dto.setBalanceTypeId(2);
        newId = this.api.updateAccount(dto);
        Assert.assertTrue(newId > 0);
        this.acctKeys.add(newId);

    }

    private void deleteData() throws Exception {
        int rc = 0;
        for (int key : this.acctKeys) {
            rc = this.api.deleteAccount(key);
            Assert.assertTrue(rc > 0);
        }
    }

    @Test
    public void fetchAllStockAccounts() {
        List<AccountDto> list = null;
        try {
            list = this.api.getAccount(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Assert.assertTrue(list.get(0) instanceof AccountDto);
    }

    @Test
    public void fetchStockAccountById() {
        int acctId = 20;
        String acctName = "Accounts Payable";
        String acctCode = "ACCTPAY";

        AccountDto acct = null;
        try {
            acct = this.api.getAccount(acctId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(acct);
        Assert.assertTrue(acct instanceof AccountDto);
        Assert.assertEquals(acctId, acct.getAcctId());
        Assert.assertEquals(acctName, acct.getAcctName());
        Assert.assertEquals(acctCode, acct.getAcctCode());

    }

    @Test
    public void fetchAccountByName() {
        String acctName = "Test Account #1";
        List<AccountDto> list = null;
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        criteria.setAcctName(acctName);
        try {
            list = this.api.getAccount(criteria);
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
    public void fetchAllStockAccountTypes() {
        List<AccountTypeDto> list = null;
        try {
            list = this.api.getAccountType(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Assert.assertTrue(list.get(0) instanceof AccountTypeDto);
    }

    @Test
    public void addAccountWithDuplicateName() {
        int newId = 0;
        AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(null);
        dto.setAcctTypeId(7);
        dto.setAcctCatgId(701);
        dto.setAcctName("Test Account #1");
        dto.setAcctCode("CODE_1");
        dto.setAcctDescription("Test DESCRIPTION");
        dto.setBalanceTypeId(1);
        try {
            newId = this.api.updateAccount(dto);
            System.out.println("New account id: " + newId);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Account was created with a duplicate account name");
    }

    @Test
    public void updateAccount() {
        String origAcctName = "Test Account #1";
        String newAcctName = "RoyTerrell";
        int acctId = this.acctKeys.get(0);
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        List<AccountDto> results = null;
        criteria.setAcctId(acctId);
        int rc = 0;
        try {
            results = this.api.getAccount(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountDto dto = results.get(0);
            Assert.assertEquals(origAcctName, dto.getAcctName());

            dto.setAcctName(newAcctName);
            rc = this.api.updateAccount(dto);
            Assert.assertEquals(1, rc);

            results = this.api.getAccount(criteria);
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
    public void updateAccountWithDuplicateName() {
        String origAcctName = "Test Account #1";
        String newAcctName = "Test Account #2";
        int acctId = this.acctKeys.get(0);
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        List<AccountDto> results = null;
        criteria.setAcctId(acctId);
        try {
            results = this.api.getAccount(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountDto dto = results.get(0);
            Assert.assertEquals(origAcctName, dto.getAcctName());

            dto.setAcctName(newAcctName);
            this.api.updateAccount(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Account was successfully changed with a duplicate account name");
    }

    @Test
    public void updateAccountWithInvalidAccountCategory() {
        String origAcctName = "Test Account #1";
        int invalidCatgId = 777;
        int acctId = this.acctKeys.get(0);
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        List<AccountDto> results = null;
        criteria.setAcctId(acctId);
        try {
            results = this.api.getAccount(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountDto dto = results.get(0);
            Assert.assertEquals(origAcctName, dto.getAcctName());

            dto.setAcctCatgId(invalidCatgId);
            this.api.updateAccount(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Account was successfully changed with an invalid account category id");
    }

    @Test
    public void updateAccountWithInvalidAccountType() {
        String origAcctName = "Test Account #1";
        int invalidTypeId = 777;
        int acctId = this.acctKeys.get(0);
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        List<AccountDto> results = null;
        criteria.setAcctId(acctId);
        try {
            results = this.api.getAccount(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountDto dto = results.get(0);
            Assert.assertEquals(origAcctName, dto.getAcctName());

            dto.setAcctTypeId(invalidTypeId);
            this.api.updateAccount(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Account was successfully changed with an invalid account type id");
    }
}
