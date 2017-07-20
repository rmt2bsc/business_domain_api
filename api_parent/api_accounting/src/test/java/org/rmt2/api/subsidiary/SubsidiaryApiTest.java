package org.rmt2.api.subsidiary;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.rmt2.api.BaseAccountingDaoTest;
import org.rmt2.dao.AccountingMockDataUtility;

/**
 * Common subsidiary testing functionality.
 * 
 * @author rterrell
 * 
 */
public class SubsidiaryApiTest extends BaseAccountingDaoTest {
    protected List<Customer> mockCustomerNotFoundFetchResponse;
    protected List<Customer> mockCustomerFetchAllResponse;
    protected List<Customer> mockCustomerFetchSingleResponse;
    protected List<Creditor> mockCreditorNotFoundFetchResponse;
    protected List<Creditor> mockCreditorFetchAllResponse;
    protected List<Creditor> mockCreditorFetchSingleResponse;
    protected List<VwBusinessAddress> mockBusinessContactFetchSingleResponse;
    protected List<VwBusinessAddress> mockBusinessContactFetchAllResponse;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        APP_NAME = "accounting";
        super.setUp();
        this.mockBusinessContactFetchSingleResponse = this
                .createMockSingleContactFetchResponse();
        this.mockBusinessContactFetchAllResponse = this
                .createMockFetchAllContactResponse();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<Creditor> createMockNotFoundSearchResultsResponse() {
        List<Creditor> list = null;
        return list;
    }

    private List<VwBusinessAddress> createMockFetchAllContactResponse() {
        List<VwBusinessAddress> list = new ArrayList<VwBusinessAddress>();
        VwBusinessAddress p = AccountingMockDataUtility
                .createMockOrmBusinessContact(1351, "Company1", 2222,
                        "94393 Hall Ave.", "Building 123", "Suite 300",
                        "Room 45", "Dallas", "TX", 75232);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmBusinessContact(1400,
                "Company2", 4444, "9382 Frank St.", null, null, null, "Irving",
                "TX", 75240);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmBusinessContact(1500,
                "Company3", 5555, "6718 Bernard Dr", "Building 4353",
                "Suite 982", null, "Shreveport", "LA", 71118);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmBusinessContact(1600,
                "Company4", 6666, "9328 Forest Ave", "Building 854",
                "Suite 9212", "Room 555", "FLower Mound", "TX", 75028);
        list.add(p);

        p = AccountingMockDataUtility.createMockOrmBusinessContact(1700,
                "Company5", 7777, "8327 Spring Ave", null, null, null, "Dallas",
                "TX", 75232);
        list.add(p);
        return list;
    }

    private List<VwBusinessAddress> createMockSingleContactFetchResponse() {
        List<VwBusinessAddress> list = new ArrayList<VwBusinessAddress>();
        VwBusinessAddress p = AccountingMockDataUtility
                .createMockOrmBusinessContact(1351, "ABC Company", 2222,
                        "94393 Hall Ave.", "Building 123", "Suite 300",
                        "Room 45", "Dallas", "TX", 75232);
        list.add(p);
        return list;
    }

    /**
     * Setup mocks to retrieve single matching creditor and common business
     * contact data.
     * 
     * @param busContactCriteria
     * @param creditorCriteria
     */
    protected void setupSingleSubsidiaryContactInfoFetch(
            VwBusinessAddress busContactCriteria, Creditor creditorCriteria) {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(eq(busContactCriteria))).thenReturn(
                            this.mockBusinessContactFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Business Contact for creditor fetch test case setup failed");
        }

        try {
            when(this.mockPersistenceClient.retrieveList(eq(creditorCriteria)))
                    .thenReturn(this.mockCreditorFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Creditor fetch test case setup failed");
        }
    }

    /**
     * Setup mocks to retrieve multiple matching creditor and common business
     * contact data.
     * 
     * @param busContactCriteria
     * @param creditorCriteria
     */
    protected void setupMultipleSubsidiaryContactInfoFetch(
            VwBusinessAddress busContactCriteria, Creditor creditorCriteria) {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(eq(busContactCriteria))).thenReturn(
                            this.mockBusinessContactFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Business Contact for creditor fetch test case setup failed");
        }

        try {
            when(this.mockPersistenceClient.retrieveList(eq(creditorCriteria)))
                    .thenReturn(this.mockCreditorFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Creditor fetch test case setup failed");
        }
    }

    /**
     * Setup mocks to retrieve single matching customer and common business
     * contact data.
     * 
     * @param busContactCriteria
     * @param customerCriteria
     */
    protected void setupSingleSubsidiaryContactInfoFetch(
            VwBusinessAddress busContactCriteria, Customer customerCriteria) {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(eq(busContactCriteria))).thenReturn(
                            this.mockBusinessContactFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Business Contact for customer fetch test case setup failed");
        }

        try {
            when(this.mockPersistenceClient.retrieveList(eq(customerCriteria)))
                    .thenReturn(this.mockCustomerFetchSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Customer fetch test case setup failed");
        }
    }

    /**
     * Setup mocks to retrieve multiple matching customer and common business
     * contact data.
     * 
     * @param busContactCriteria
     * @param customerCriteria
     */
    protected void setupMultipleSubsidiaryContactInfoFetch(
            VwBusinessAddress busContactCriteria, Customer customerCriteria) {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(eq(busContactCriteria))).thenReturn(
                            this.mockBusinessContactFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(
                    "Single Business Contact for customer fetch test case setup failed");
        }

        try {
            when(this.mockPersistenceClient.retrieveList(eq(customerCriteria)))
                    .thenReturn(this.mockCustomerFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Single Customer fetch test case setup failed");
        }
    }

}