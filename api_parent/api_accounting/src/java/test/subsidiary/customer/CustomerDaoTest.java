package subsidiary.customer;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.dao.subsidiary.CustomerDao;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dto.CustomerDto;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.CommonAccountingTest;
import com.api.config.ConfigConstants;

/**
 * Tests the customer DAO library.
 * 
 * @author rterrell
 * 
 */
public class CustomerDaoTest extends CommonAccountingTest {
    private SubsidiaryDaoFactory f;

    private CustomerDao dao;

    private List<Integer> acctKeys;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.f = new SubsidiaryDaoFactory();
        this.dao = this.f.createRmt2OrmCustomerDao();
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
        super.tearDown();
    }

    protected void createData() {
        int newId = 0;
        this.acctKeys = new ArrayList<Integer>();
        CustomerDto dto = Rmt2SubsidiaryDtoFactory.createCustomerInstance(null,
                null);

        // dto.setAcctTypeId(7);
        // dto.setAcctCatgId(701);
        // dto.setAcctName("Test Account #1");
        // dto.setAcctCode("CODE_1");
        // dto.setAcctDescription("Test DESCRIPTION");
        // dto.setBalanceTypeId(1);
        // newId = this.dao.maintain(dto);
        // Assert.assertTrue(newId > 0);
        // this.acctKeys.add(newId);
        //
        // dto = Rmt2AccountDtoFactory.createAccountInstance(null);
        // dto.setAcctTypeId(5);
        // dto.setAcctCatgId(501);
        // dto.setAcctName("Test Account #2");
        // dto.setAcctCode("CODE_2");
        // dto.setAcctDescription("Test DESCRIPTION");
        // dto.setBalanceTypeId(2);
        // newId = this.dao.maintainAccount(dto);

        // Assert.assertTrue(newId > 0);
        // this.acctKeys.add(newId);

    }

    protected void deleteData() {
        int rc = 0;
        for (int key : this.acctKeys) {
            rc = this.dao.delete(key);
            Assert.assertTrue(rc > 0);
        }
        if (this.prevSoaphost != null) {
            this.prevSoaphost = System.setProperty(ConfigConstants.SOAP_HOST,
                    this.prevSoaphost);
        }

    }

    @Test
    public void testCriteriaQuery() {
        CustomerDto criteria = Rmt2SubsidiaryDtoFactory.createCustomerInstance(
                null, null);
        criteria.setContactName("Miller");
        try {
            this.dao.fetch(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }

    @Test
    public void testBusinessIdListQuery() {
        List<String> busIdList = new ArrayList<String>();
        busIdList.add("1109");
        busIdList.add("1110");
        busIdList.add("1111");
        busIdList.add("1112");
        busIdList.add("1113");
        busIdList.add("1114");
        SubsidiaryContactInfoDto criteria = Rmt2SubsidiaryDtoFactory
                .createSubsidiaryInstance(null);
        criteria.setContactIdList(busIdList);
        try {
            this.dao.fetch(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
