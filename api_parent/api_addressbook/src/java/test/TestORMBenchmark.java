
import java.util.List;

import junit.framework.Assert;

import org.dao.lookup.LookupDao;
import org.dao.lookup.LookupDaoFactory;
import org.dto.LookupCodeDto;
import org.dto.LookupGroupDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the RMT2 ORM Code DAO implementation.
 * 
 * @author rterrell
 * 
 */
public class TestORMBenchmark {

    private LookupDaoFactory f;

    private LookupDao dao;

    private List<LookupCodeDto> codes;

    private List<LookupGroupDto> groups;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new LookupDaoFactory();
        this.dao = this.f.createRmt2OrmDao();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    @Test
    public void testSqlBenchmark() {
        List<LookupCodeDto> results = null;
        LookupCodeDto criteria = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        criteria.setGrpId(8);
        try {
            results = this.dao.fetchCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of lookup(s) using DTO as criteria failed");
        }
        // Cause thread to sleep in order to allow the results to be completely
        // gathered before verifying.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertNotNull(results.get(0).getCodeLongName());
    }

}
