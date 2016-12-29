package transaction;

import java.util.List;

import junit.framework.Assert;

import org.dao.subsidiary.CreditorDao;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dto.CreditorXactHistoryDto;
import org.dto.CustomerXactHistoryDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.CustomerApi;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.transaction.XactApi;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactApiFactory;

import com.CommonAccountingTest;

/**
 * @author Roy Terrell
 * 
 */
public class CreateActivityFromExisitingTransXactApiTest extends
        CommonAccountingTest {

    private XactApiFactory f;
    private XactApi api;

    private SubsidiaryDaoFactory subFactory;
    private CreditorDao sharedDao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.subFactory = new SubsidiaryDaoFactory();
        this.sharedDao = subFactory.createRmt2OrmCreditorDao();

        // Setup Xact API with shared DAO
        this.f = new XactApiFactory();
        this.api = this.f.createDefaultXactApi(sharedDao);
        this.api.setApiUser("TestUser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.subFactory = null;
        this.f = null;
        this.api = null;
        this.sharedDao.close();
        super.tearDown();
    }

    @Test
    public void testCreateCustomerActivityFromExistingTrans() {
        Object results = null;
        try {
            results = this.api.createSubsidiaryTransaction(21, 17749, 34.00);
        } catch (XactApiException e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertNotNull(results);
    }

    @Test
    public void testCreateCreditorActivityFromExistingTrans() {
        Object results = null;
        try {
            results = this.api.createSubsidiaryTransaction(1, 12394, 22.00);
        } catch (XactApiException e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertNotNull(results);
    }

    @Test
    public void testQueryExistingCreditorTransHistory() {
        SubsidiaryApiFactory apiFactory = new SubsidiaryApiFactory();
        CreditorApi api = apiFactory.createCreditorApi();
        List<CreditorXactHistoryDto> results;
        try {
            results = api.getTransactionHistory(7);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 5);
    }

    @Test
    public void testQueryExistingCustomerTransHistory() {
        SubsidiaryApiFactory apiFactory = new SubsidiaryApiFactory();
        CustomerApi api = apiFactory.createCustomerApi();
        List<CustomerXactHistoryDto> results;
        try {
            results = api.getTransactionHistory(1);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 5);
    }
}
