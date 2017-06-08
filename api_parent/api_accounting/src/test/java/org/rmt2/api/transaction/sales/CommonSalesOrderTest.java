package org.rmt2.api.transaction.sales;

import org.dao.transaction.sales.SalesOrderDao;
import org.dao.transaction.sales.SalesOrderDaoFactory;
import org.junit.After;
import org.junit.Before;
import org.modules.transaction.sales.SalesApi;
import org.modules.transaction.sales.SalesApiFactory;
import org.rmt2.api.CommonAccountingTest;

/**
 * @author Roy Terrell
 * 
 */
public class CommonSalesOrderTest extends CommonAccountingTest {

    private SalesApiFactory apiFactory;

    private SalesOrderDaoFactory daoFactory;

    protected SalesApi api;

    protected SalesOrderDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.apiFactory = new SalesApiFactory();
        this.daoFactory = new SalesOrderDaoFactory();
        this.api = this.apiFactory.createApi();
        this.api.setApiUser("Testuser");
        this.dao = this.daoFactory.createRmt2OrmDao();
        this.dao.setDaoUser(this.api.getApiUser());
        this.createData();

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
        this.api.close();
        this.dao.close();
        this.dao = null;
        this.daoFactory = null;
        super.tearDown();
    }

}
