package transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dao.transaction.XactDao;
import org.dao.transaction.XactDaoFactory;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.CommonAccountingTest;

/**
 * Test transaction creation DAO library.
 * 
 * @author Roy Terrell
 * 
 */
public class CreateXactDaoTest extends CommonAccountingTest {
    private XactDaoFactory f;

    private XactDao dao;

    private XactDto xact;
    private List<XactTypeItemActivityDto> items;

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
        this.xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        this.items = new ArrayList<XactTypeItemActivityDto>();

        // Setup transaction
        this.xact.setXactTypeId(60);
        this.xact.setXactDate(new Date());
        this.xact.setXactAmount(55.00);
        this.xact.setXactTenderId(11);
        this.xact.setXactNegInstrNo(null);
        this.xact.setXactBankTransInd(null);
        this.xact.setXactConfirmNo("8438243243");
        this.xact.setXactEntityRefNo("DJF8888JJJ");
        this.xact.setXactReason("This is a test");

        // Setup transction items
        XactTypeItemActivityDto item = Rmt2XactDtoFactory
                .createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setXactItemId(6);
        item.setActivityAmount(30.00);
        item.setXactTypeItemActvName("Ribeye Steak Dinner");
        this.items.add(item);

        item = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setXactItemId(11);
        item.setActivityAmount(10.00);
        item.setXactTypeItemActvName("Crown Royal");
        this.items.add(item);

        item = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setXactItemId(55);
        item.setActivityAmount(2.00);
        item.setXactTypeItemActvName("Taxes");
        this.items.add(item);

        item = Rmt2XactDtoFactory.createXactTypeItemActivityInstance((XactTypeItemActivity) null);
        item.setXactItemId(61);
        item.setActivityAmount(7.00);
        item.setXactTypeItemActvName("Tip");
        this.items.add(item);
    }

    protected void deleteData() {

    }

    @Test
    public void testTransactionAdd() {
        int xactId = 0;
        try {
            xactId = this.dao.maintain(this.xact, this.items);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(xactId > 0);
        return;
    }
}
