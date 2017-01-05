package transaction;

import java.util.List;

import junit.framework.Assert;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dao.transaction.XactDao;
import org.dao.transaction.XactDaoFactory;
import org.dto.XactCategoryDto;
import org.dto.XactCodeDto;
import org.dto.XactCodeGroupDto;
import org.dto.XactDto;
import org.dto.XactTypeDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.XactTypeItemDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.CommonAccountingTest;

/**
 * Tests various transacton queries DAO library.
 * 
 * @author rterrell
 * 
 */
public class QueryExistingXactDataDaoTest extends CommonAccountingTest {
    private XactDaoFactory f;

    private XactDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.f = new XactDaoFactory();
        this.dao = this.f.createRmt2OrmXactDao();
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

    }

    protected void deleteData() {

    }

    @Test
    public void testXactQuery() {
        VwXactList nullList = null;
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance(nullList);
        criteria.setXactId(12129);
        List<XactDto> results = null;
        try {
            results = this.dao.fetchXact(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results.size());
        return;
    }

    @Test
    public void testCategoryQuery() {
        XactCategoryDto criteria = Rmt2XactDtoFactory
                .createXactCategoryInstance(null);
        criteria.setXactCatgDescription("c");
        List<XactCategoryDto> results = null;
        try {
            results = this.dao.fetchCategory(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(results.size() > 2);
        return;
    }

    @Test
    public void testGroupQuery() {
        XactCodeGroupDto criteria = Rmt2XactDtoFactory
                .createXactCodeGroupInstance(null);
        criteria.setEntityName("t");
        List<XactCodeGroupDto> results = null;
        try {
            results = this.dao.fetchGroup(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(results.size() > 1);
        return;
    }

    @Test
    public void testCodeQuery() {
        XactCodeDto criteria = Rmt2XactDtoFactory.createXactCodeInstance(null);
        criteria.setEntityName("c");
        List<XactCodeDto> results = null;
        try {
            results = this.dao.fetchCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(results.size() > 5);
        return;
    }

    @Test
    public void testTypeQuery() {
        XactTypeDto criteria = Rmt2XactDtoFactory.createXactTypeInstance(null);
        criteria.setXactTypeDescription("p");
        List<XactTypeDto> results = null;
        try {
            results = this.dao.fetchType(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(results.size() > 4);
        return;
    }

    @Test
    public void testXactTypeItemQuery() {
        XactTypeItemDto criteria = Rmt2XactDtoFactory
                .createXactTypeItemInstance(null);
        criteria.setXactItemName("t");
        List<XactTypeItemDto> results = null;
        try {
            results = this.dao.fetchXactTypeItem(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(results.size() > 10);
        return;
    }

    @Test
    public void testXactTypeItemActivityQuery() {
        XactTypeItemActivityDto criteria = Rmt2XactDtoFactory
                .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        criteria.setXactTypeItemActvName("walmart");
        List<XactTypeItemActivityDto> results = null;
        try {
            results = this.dao.fetchXactTypeItemActivity(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(results.size() > 100);
        return;
    }
}
