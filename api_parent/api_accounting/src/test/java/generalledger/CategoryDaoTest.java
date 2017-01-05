package generalledger;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.dao.generalledger.GeneralLedgerDao;
import org.dao.generalledger.GeneralLedgerDaoFactory;
import org.dto.AccountCategoryDto;
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
public class CategoryDaoTest {
    private GeneralLedgerDaoFactory f;

    private GeneralLedgerDao dao;

    private List<Integer> keys;

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
        this.keys = new ArrayList<Integer>();
        AccountCategoryDto dto = Rmt2AccountDtoFactory
                .createAccountCategoryInstance(null);

        dto.setAcctTypeId(7);
        dto.setAcctCatgId(0);
        dto.setAcctCatgDescription("Test Category #1");
        newId = this.dao.maintainCategory(dto);
        Assert.assertTrue(newId > 0);
        this.keys.add(dto.getAcctCatgId());

        dto = Rmt2AccountDtoFactory.createAccountCategoryInstance(null);
        dto.setAcctTypeId(5);
        dto.setAcctCatgId(0);
        dto.setAcctCatgDescription("Test Category #2");
        newId = this.dao.maintainCategory(dto);
        Assert.assertTrue(newId > 0);
        this.keys.add(dto.getAcctCatgId());
    }

    private void deleteData() {
        int rc = 0;
        for (int key : this.keys) {
            rc = this.dao.deleteCategory(key);
            Assert.assertTrue(rc > 0);
        }
    }

    @Test
    public void testStockFetchAll() {
        List<AccountCategoryDto> list = null;
        try {
            list = this.dao.fetchCategory(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Assert.assertTrue(list.get(0) instanceof AccountCategoryDto);
    }

    @Test
    public void testStockFetchById() {
        int id = 301;
        String descr = "Capital";

        List<AccountCategoryDto> list = null;
        AccountCategoryDto criteria = Rmt2AccountDtoFactory
                .createAccountCategoryInstance(null);
        criteria.setAcctCatgId(id);
        try {
            list = this.dao.fetchCategory(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.get(0) instanceof AccountCategoryDto);
        AccountCategoryDto dto = list.get(0);
        Assert.assertEquals(id, dto.getAcctCatgId());
        Assert.assertEquals(descr, dto.getAcctCatgDescription());
    }

    @Test
    public void testFetchByExactDescripton() {
        int id = 301;
        String descr = "Capital";

        List<AccountCategoryDto> list = null;
        AccountCategoryDto criteria = Rmt2AccountDtoFactory
                .createAccountCategoryInstance(null);
        criteria.setAcctCatgDescription(descr);
        try {
            list = this.dao.fetchCategory(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.get(0) instanceof AccountCategoryDto);
        AccountCategoryDto dto = list.get(0);
        Assert.assertEquals(id, dto.getAcctCatgId());
        Assert.assertEquals(descr, dto.getAcctCatgDescription());
    }

    @Test
    public void testFetchByDescripton() {
        String descr = "Other";

        List<AccountCategoryDto> list = null;
        AccountCategoryDto criteria = Rmt2AccountDtoFactory
                .createAccountCategoryInstance(null);
        criteria.setAcctCatgDescription(descr);
        try {
            list = this.dao.fetchCategory(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(list);
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.get(0) instanceof AccountCategoryDto);
    }

    @Test
    public void testAddWithDuplicateName() {
        int newId = 0;
        AccountCategoryDto dto = Rmt2AccountDtoFactory
                .createAccountCategoryInstance(null);
        dto.setAcctTypeId(7);
        dto.setAcctCatgId(701);
        dto.setAcctCatgDescription("Test Category #1");
        try {
            newId = this.dao.maintainCategory(dto);
            System.out.println("New category id: " + newId);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Category was created with a duplicate name");
    }

    @Test
    public void testUpdate() {
        String origDescr = "Test Category #1";
        String newDescr = "RoyTerrell";
        int catgId = this.keys.get(0);
        AccountCategoryDto criteria = Rmt2AccountDtoFactory
                .createAccountCategoryInstance(null);
        List<AccountCategoryDto> results = null;
        criteria.setAcctCatgId(catgId);
        int rc = 0;
        try {
            results = this.dao.fetchCategory(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountCategoryDto dto = results.get(0);
            Assert.assertEquals(origDescr, dto.getAcctCatgDescription());

            dto.setAcctCatgDescription(newDescr);
            rc = this.dao.maintainCategory(dto);
            Assert.assertEquals(1, rc);

            results = this.dao.fetchCategory(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            dto = results.get(0);
            Assert.assertEquals(newDescr, dto.getAcctCatgDescription());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateWithInvalidAccountType() {
        String origDescr = "Test Category #1";
        int invalidTypeId = 777;
        int acctId = this.keys.get(0);
        AccountCategoryDto criteria = Rmt2AccountDtoFactory
                .createAccountCategoryInstance(null);
        List<AccountCategoryDto> results = null;
        criteria.setAcctCatgId(acctId);
        try {
            results = this.dao.fetchCategory(criteria);
            Assert.assertNotNull(results);
            Assert.assertEquals(1, results.size());
            AccountCategoryDto dto = results.get(0);
            Assert.assertEquals(origDescr, dto.getAcctCatgDescription());

            dto.setAcctTypeId(invalidTypeId);
            this.dao.maintainCategory(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed, because GL Category was successfully changed with an invalid account type id");
    }
}
