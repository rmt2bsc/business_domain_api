package subsidiary.creditor;

import java.util.ArrayList;
import java.util.List;

import org.dao.subsidiary.CreditorDao;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dto.CreditorDto;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.CommonAccountingTest;

/**
 * Tests the customer DAO library.
 * 
 * @author rterrell
 * 
 */
public class CreditorDaoTest extends CommonAccountingTest {
    private SubsidiaryDaoFactory f;

    private CreditorDao dao;

    // private List<Integer> acctKeys;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.f = new SubsidiaryDaoFactory();
        this.dao = this.f.createRmt2OrmCreditorDao();
        this.dao.setDaoUser("Testuser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dao.close();
        this.dao = null;
        this.f = null;
        super.tearDown();
    }

    @Test
    public void testCriteriaQuery() {
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(
                null, null);
        criteria.setContactName("office");
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
        busIdList.add("1445");
        busIdList.add("1446");
        busIdList.add("1447");
        busIdList.add("1448");
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
