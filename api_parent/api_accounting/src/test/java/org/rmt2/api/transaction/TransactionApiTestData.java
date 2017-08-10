package org.rmt2.api.transaction;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactCategory;
import org.dao.mapping.orm.rmt2.XactCodeGroup;
import org.dao.mapping.orm.rmt2.XactCodes;
import org.dao.mapping.orm.rmt2.XactType;
import org.dao.mapping.orm.rmt2.XactTypeItem;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.rmt2.api.subsidiary.SubsidiaryApiTestData;
import org.rmt2.dao.AccountingMockDataUtility;

import com.api.persistence.DaoClient;
import com.api.persistence.PersistenceClient;
import com.util.RMT2Date;

/**
 * Base transaction testing facility that is mainly responsible for setting up
 * mock data.
 * <p>
 * All derived transaction related Api unit tests should inherit this class to 
 * prevent duplicating common functionality.
 * 
 * @author rterrell
 * 
 */
public class TransactionApiTestData extends SubsidiaryApiTestData {
    protected DaoClient mockDaoClient;
    protected PersistenceClient mockPersistenceClient;
    
    protected List<XactCategory> mockCategoryNotFoundFetchResponse;
    protected List<XactCategory> mockCategoryFetchAllResponse;
    protected List<XactCategory> mockCategoryFetchSingleResponse;

    protected List<XactCodes> mockCodeNotFoundFetchResponse;
    protected List<XactCodes> mockCodeFetchAllResponse;
    protected List<XactCodes> mockCodeFetchSingleResponse;

    protected List<XactCodeGroup> mockCodeGroupNotFoundFetchResponse;
    protected List<XactCodeGroup> mockCodeGroupFetchAllResponse;
    protected List<XactCodeGroup> mockCodeGroupFetchSingleResponse;

    protected List<XactType> mockXactTypeNotFoundFetchResponse;
    protected List<XactType> mockXactTypeFetchAllResponse;
    protected List<XactType> mockXactTypeFetchSingleResponse;

    protected List<Xact> mockXactNotFoundFetchResponse;
    protected List<Xact> mockXactFetchAllResponse;
    protected List<Xact> mockXactFetchSingleResponse;

    protected List<XactTypeItem> mockXactTypeItemNotFoundFetchResponse;
    protected List<XactTypeItem> mockXactTypeItemFetchAllResponse;
    protected List<XactTypeItem> mockXactTypeItemFetchSingleResponse;

    protected List<XactTypeItemActivity> mockXactTypeItemActivityNotFoundFetchResponse;
    protected List<XactTypeItemActivity> mockXactTypeItemActivityFetchAllResponse;
    protected List<XactTypeItemActivity> mockXactTypeItemActivityFetchSingleResponse;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        // Mock database connection since the common transaction Api expects 
        // derived Api modules to obtain and pass in an instance of DaoClient.
        this.mockDaoClient = Mockito.mock(DaoClient.class);
        this.mockPersistenceClient = Mockito.mock(PersistenceClient.class);
        when(this.mockDaoClient.getClient()).thenReturn(this.mockPersistenceClient);
        
        this.mockCategoryFetchAllResponse = this
                .createMockXactCategoryFetchAllsponse();
        this.mockCategoryFetchSingleResponse = this
                .createMockXactCategorySingleFetchResponse();
        this.mockCategoryNotFoundFetchResponse = this
                .createMockXactCategoryNotFoundResponse();

        this.mockCodeFetchAllResponse = this.createMockXactCodeFetchAllsponse();
        this.mockCodeFetchSingleResponse = this
                .createMockXactCodeSingleFetchResponse();
        this.mockCodeNotFoundFetchResponse = this
                .createMockXactCodeNotFoundResponse();

        this.mockCodeGroupFetchAllResponse = this
                .createMockXactCodeGroupFetchAllsponse();
        this.mockCodeGroupFetchSingleResponse = this
                .createMockXactCodeGroupSingleFetchResponse();
        this.mockCodeGroupNotFoundFetchResponse = this
                .createMockXactCodeGroupNotFoundResponse();

        this.mockXactTypeFetchAllResponse = this
                .createMockXactTypeFetchAllsponse();
        this.mockXactTypeFetchSingleResponse = this
                .createMockXactTypeSingleFetchResponse();
        this.mockXactTypeNotFoundFetchResponse = this
                .createMockXactTypeNotFoundResponse();

        this.mockXactFetchAllResponse = this.createMockXactFetchAllsponse();
        this.mockXactFetchSingleResponse = this
                .createMockXactSingleFetchResponse();
        this.mockXactNotFoundFetchResponse = this
                .createMockXactNotFoundResponse();

        this.mockXactTypeItemFetchAllResponse = this
                .createMockXactTypeItemFetchAllsponse();
        this.mockXactTypeItemFetchSingleResponse = this
                .createMockXactTypeItemSingleFetchResponse();
        this.mockXactTypeItemNotFoundFetchResponse = this
                .createMockXactTypeItemNotFoundResponse();

        this.mockXactTypeItemActivityFetchAllResponse = this
                .createMockXactTypeItemActivityFetchAllsponse();
        this.mockXactTypeItemActivityFetchSingleResponse = this
                .createMockXactTypeItemActivitySingleFetchResponse();
        this.mockXactTypeItemActivityNotFoundFetchResponse = this
                .createMockXactTypeItemActivityNotFoundResponse();

        return;
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<XactTypeItemActivity> createMockXactTypeItemActivityNotFoundResponse() {
        List<XactTypeItemActivity> list = null;
        return list;
    }

    private List<XactTypeItemActivity> createMockXactTypeItemActivityFetchAllsponse() {
        List<XactTypeItemActivity> list = new ArrayList<XactTypeItemActivity>();
        XactTypeItemActivity o = AccountingMockDataUtility
                .createMockOrmXactTypeItemActivity(7001, 111111, 601, 31.11,
                        "Item 1");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactTypeItemActivity(7002,
                111111, 602, 20.00, "Item 1");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactTypeItemActivity(7003,
                111111, 603, 20.00, "Item 1");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactTypeItemActivity(7004,
                111111, 604, 20.00, "Item 1");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactTypeItemActivity(7005,
                111111, 605, 20.00, "Item 1");
        list.add(o);
        return list;
    }

    private List<XactTypeItemActivity> createMockXactTypeItemActivitySingleFetchResponse() {
        List<XactTypeItemActivity> list = new ArrayList<XactTypeItemActivity>();
        XactTypeItemActivity o = AccountingMockDataUtility
                .createMockOrmXactTypeItemActivity(7001, 111111, 601, 1.11,
                        "Item 1");
        list.add(o);
        return list;
    }

    private List<XactTypeItem> createMockXactTypeItemNotFoundResponse() {
        List<XactTypeItem> list = null;
        return list;
    }

    private List<XactTypeItem> createMockXactTypeItemFetchAllsponse() {
        List<XactTypeItem> list = new ArrayList<XactTypeItem>();
        XactTypeItem o = AccountingMockDataUtility
                .createMockOrmXactTypeItem(601, 301, "Transaction Type Item 1");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactTypeItem(602, 301,
                "Transaction Type Item 2");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactTypeItem(603, 301,
                "Transaction Type Item 3");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactTypeItem(604, 301,
                "Transaction Type Item 4");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactTypeItem(605, 301,
                "Transaction Type Item 5");
        list.add(o);
        return list;
    }

    private List<XactTypeItem> createMockXactTypeItemSingleFetchResponse() {
        List<XactTypeItem> list = new ArrayList<XactTypeItem>();
        XactTypeItem o = AccountingMockDataUtility
                .createMockOrmXactTypeItem(601, 301, "Transaction Type Item 1");
        list.add(o);
        return list;
    }

    private List<Xact> createMockXactNotFoundResponse() {
        List<Xact> list = null;
        return list;
    }

    private List<Xact> createMockXactFetchAllsponse() {
        List<Xact> list = new ArrayList<Xact>();
        Xact o = AccountingMockDataUtility.createMockOrmXact(111111, 301, 3333,
                RMT2Date.stringToDate("2017-01-13"), 111.11, 200, null);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXact(222222, 302, 4444,
                RMT2Date.stringToDate("2017-02-14"), 222.11, 200,
                "Check No 1234");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXact(333333, 301, 3333,
                RMT2Date.stringToDate("2017-03-15"), 333.11, 200, null);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXact(444444, 301, 3333,
                RMT2Date.stringToDate("2017-04-16"), 444.11, 200, null);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXact(555555, 302, 3333,
                RMT2Date.stringToDate("2017-05-17"), 555.11, 200,
                "1111-1111-1111-1111");
        list.add(o);
        return list;
    }

    private List<Xact> createMockXactSingleFetchResponse() {
        List<Xact> list = new ArrayList<Xact>();
        Xact o = AccountingMockDataUtility.createMockOrmXact(111111, 301, 3333,
                RMT2Date.stringToDate("2017-01-13"), 111.11, 200, null);
        list.add(o);
        return list;
    }

    private List<XactType> createMockXactTypeNotFoundResponse() {
        List<XactType> list = null;
        return list;
    }

    private List<XactType> createMockXactTypeFetchAllsponse() {
        List<XactType> list = new ArrayList<XactType>();
        XactType o = AccountingMockDataUtility.createMockOrmXactType(301, 1000,
                "Transaction Type 1", "transaction type code 1", 1, -1, 400,
                401, 200, 222, 1);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactType(302, 2000,
                "Transaction Type 2", "transaction type code 2", 1, -1, 400,
                401, 200, 222, 1);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactType(303, 3000,
                "Transaction Type 3", "transaction type code 3", 1, -1, 400,
                401, 200, 222, 1);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactType(304, 4000,
                "Transaction Type 4", "transaction type code 4", 1, -1, 400,
                401, 200, 222, 1);
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactType(305, 5000,
                "Transaction Type 5", "transaction type code 5", 1, -1, 400,
                401, 200, 222, 1);
        list.add(o);
        return list;
    }

    private List<XactType> createMockXactTypeSingleFetchResponse() {
        List<XactType> list = new ArrayList<XactType>();
        XactType o = AccountingMockDataUtility.createMockOrmXactType(301, 1000,
                "Transaction Type 1", "transaction type code 1", 1, -1, 400,
                401, 200, 222, 1);
        list.add(o);
        return list;
    }

    private List<XactCodeGroup> createMockXactCodeGroupNotFoundResponse() {
        List<XactCodeGroup> list = null;
        return list;
    }

    private List<XactCodeGroup> createMockXactCodeGroupFetchAllsponse() {
        List<XactCodeGroup> list = new ArrayList<XactCodeGroup>();
        XactCodeGroup o = AccountingMockDataUtility
                .createMockOrmXactCodeGroup(10, "Code Group 10");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCodeGroup(11,
                "Code Group 11");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCodeGroup(12,
                "Code Group 12");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCodeGroup(13,
                "Code Group 13");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCodeGroup(14,
                "Code Group 14");
        list.add(o);
        return list;
    }

    private List<XactCodeGroup> createMockXactCodeGroupSingleFetchResponse() {
        List<XactCodeGroup> list = new ArrayList<XactCodeGroup>();
        XactCodeGroup o = AccountingMockDataUtility
                .createMockOrmXactCodeGroup(10, "Code Group 10");
        list.add(o);
        return list;
    }

    private List<XactCategory> createMockXactCategoryNotFoundResponse() {
        List<XactCategory> list = null;
        return list;
    }

    private List<XactCategory> createMockXactCategoryFetchAllsponse() {
        List<XactCategory> list = new ArrayList<XactCategory>();
        XactCategory o = AccountingMockDataUtility.createMockOrmXactCategory(
                1000, "transaction category 1", "catg code 1");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCategory(2000,
                "transaction category 2", "catg code 2");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCategory(3000,
                "transaction category 3", "catg code 3");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCategory(4000,
                "transaction category 4", "catg code 4");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCategory(5000,
                "transaction category 5", "catg code 5");
        list.add(o);
        return list;
    }

    private List<XactCategory> createMockXactCategorySingleFetchResponse() {
        List<XactCategory> list = new ArrayList<XactCategory>();
        XactCategory o = AccountingMockDataUtility.createMockOrmXactCategory(
                1000, "transaction category 1", "catg code 1");
        list.add(o);
        return list;
    }

    private List<XactCodes> createMockXactCodeNotFoundResponse() {
        List<XactCodes> list = null;
        return list;
    }

    private List<XactCodes> createMockXactCodeFetchAllsponse() {
        List<XactCodes> list = new ArrayList<XactCodes>();
        XactCodes o = AccountingMockDataUtility.createMockOrmXactCode(200, 10,
                "Transaction code 1");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCode(201, 10,
                "Transaction code 2");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCode(202, 10,
                "Transaction code 3");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCode(203, 10,
                "Transaction code 4");
        list.add(o);

        o = AccountingMockDataUtility.createMockOrmXactCode(204, 10,
                "Transaction code 5");
        list.add(o);
        return list;
    }

    private List<XactCodes> createMockXactCodeSingleFetchResponse() {
        List<XactCodes> list = new ArrayList<XactCodes>();
        XactCodes o = AccountingMockDataUtility.createMockOrmXactCode(200, 10,
                "Transaction code 1");
        list.add(o);
        return list;
    }
}