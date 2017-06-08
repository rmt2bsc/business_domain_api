package org.rmt2.api.generalledger;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dto.AccountDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.generalledger.GeneralLedgerApiException;
import org.modules.generalledger.GeneralLedgerApiFactory;
import org.modules.generalledger.GlAccountApi;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseAccountingDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests the general ledger API library.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class AccountApiTest extends BaseAccountingDaoTest {
    private List<GlAccounts> mockSingleFetchResponse;
    private List<GlAccounts> mockCriteriaFetchResponse;
    private List<GlAccounts> mockFetchAllResponse;
    private List<GlAccounts> mockNotFoundFetchResponse;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        APP_NAME = "accounting";
        super.setUp();
        this.mockSingleFetchResponse = this.createMockSingleFetchResponse();
        this.mockCriteriaFetchResponse = this
                .createMockFetchUsingCriteriaResponse();
        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this
                .createMockNotFoundSearchResultsResponse();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<GlAccounts> createMockNotFoundSearchResultsResponse() {
        List<GlAccounts> list = null;
        return list;
    }

    private List<GlAccounts> createMockSingleFetchResponse() {
        List<GlAccounts> list = new ArrayList<GlAccounts>();
        GlAccounts p = this.createMockOrm(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<GlAccounts> createMockFetchUsingCriteriaResponse() {
        List<GlAccounts> list = new ArrayList<GlAccounts>();
        GlAccounts p = this.createMockOrm(100, 200, 300, 1, "GL_100",
                "ACCT_RECV", "234", "Accounts Receivable", 1);
        list.add(p);

        p = this.createMockOrm(100, 200, 300, 1, "GL_101", "CASH", "230",
                "CASH", 1);
        list.add(p);

        return list;
    }

    private List<GlAccounts> createMockFetchAllResponse() {
        List<GlAccounts> list = new ArrayList<GlAccounts>();
        GlAccounts p = this.createMockOrm(100, 1, 120, 1, "GL_100", "ACCT_RECV",
                "134", "Accounts Receivable", 1);
        list.add(p);

        p = this.createMockOrm(200, 2, 150, 1, "GL_201", "ACCT_PAY", "230",
                "Accounts Payable", 2);
        list.add(p);

        p = this.createMockOrm(300, 3, 160, 1, "GL_301", "OWNER_CAP", "330",
                "Owner's Capital", 2);
        list.add(p);

        p = this.createMockOrm(400, 4, 170, 1, "GL_401", "CRED_PURCH", "430",
                "Creditor Purchases", 1);
        list.add(p);
        return list;
    }

    private AccountDto createMockDto(int acctId, int acctTypeId, int acctCatgId,
            int acctSeq, String acctNo, String acctName, String acctCode,
            String acctDescription, int acctBalTypeId) {
        GlAccounts orm = this.createMockOrm(acctId, acctTypeId, acctCatgId,
                acctSeq, acctNo, acctName, acctCode, acctDescription,
                acctBalTypeId);
        AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(orm);
        return dto;
    }

    private GlAccounts createMockOrm(int acctId, int acctTypeId, int acctCatgId,
            int acctSeq, String acctNo, String acctName, String acctCode,
            String acctDescription, int acctBalTypeId) {
        GlAccounts orm = new GlAccounts();
        orm.setAcctId(acctId);
        orm.setAcctTypeId(acctTypeId);
        orm.setAcctBaltypeId(acctBalTypeId);
        orm.setAcctCatgId(acctCatgId);
        orm.setAcctNo(acctNo);
        orm.setAcctSeq(acctSeq);
        orm.setCode(acctCode);
        orm.setDescription(acctDescription);
        orm.setName(acctName);
        return orm;
    }


    @Test
    public void testFetchAll() {
        AccountDto criteria = Rmt2AccountDtoFactory.createAccountInstance(null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GlAccounts.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All GL Acccount fetch test case failed");
        }

        GeneralLedgerApiFactory f = new GeneralLedgerApiFactory();
        GlAccountApi api = f.createApi(APP_NAME);
        List<AccountDto> results = null;
        try {
            results = api.getAccount(criteria);
        } catch (GeneralLedgerApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
    }

    @Test
    public void testFetchWithNullCriteriaObject() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testFetchSingle() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testFetchByUid() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testFetchUsingCriteria() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testNotFoundlFetch() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testUpdate() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testInsert() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testDelete() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testDeleteWithZeroUid() {
        Assert.fail("Test Failed");
    }
}
