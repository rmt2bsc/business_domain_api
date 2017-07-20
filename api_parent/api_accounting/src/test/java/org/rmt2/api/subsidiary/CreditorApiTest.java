package org.rmt2.api.subsidiary;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dto.CreditorDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.CreditorApiException;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseAccountingDaoTest;
import org.rmt2.dao.AccountingMockDataUtility;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests Vendor Items entity query use cases belonging to the Inventory Api.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class CreditorApiTest extends BaseAccountingDaoTest {
//    private List<VwVendorItems> mockSingleFetchResponse;
//    private List<VwVendorItems> mockCriteriaFetchResponse;
    private List<Creditor> mockFetchAllResponse;
    private List<Creditor> mockNotFoundFetchResponse;
    private List<Creditor> mockCreditorFetchResponse;
    private List<VwBusinessAddress> mockBusinessContactFetchResponse;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        APP_NAME = "accounting";
        super.setUp();
//        this.mockSingleFetchResponse = this.createMockSingleFetchResponse();
//        this.mockCriteriaFetchResponse = this
//                .createMockFetchUsingCriteriaResponse();
        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this.createMockNotFoundSearchResultsResponse();
        this.mockCreditorFetchResponse = this.createMockSingleCreditorFetchResponse();
        this.mockBusinessContactFetchResponse = this.createMockSingleContactFetchResponse();
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

//    private List<VwVendorItems> createMockSingleFetchResponse() {
//        List<VwVendorItems> list = new ArrayList<VwVendorItems>();
//        VwVendorItems p = AccountingMockDataUtility.createMockOrmVwVendorItems(
//                100, "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23);
//        list.add(p);
//        return list;
//    }
//
//    /**
//     * Use for the following selection criteria: where last name begins with 'C'
//     * 
//     * @return
//     */
//    private List<VwVendorItems> createMockFetchUsingCriteriaResponse() {
//        List<VwVendorItems> list = new ArrayList<VwVendorItems>();
//        VwVendorItems p = AccountingMockDataUtility.createMockOrmVwVendorItems(
//                100, "111-111-111", "11111111", 1234, "Item # 1", 5, 1.23);
//        list.add(p);
//
//        p = AccountingMockDataUtility.createMockOrmVwVendorItems(200,
//                "222-222-222", "22222222", 1234, "Item # 2", 15, 0.99);
//        list.add(p);
//
//        return list;
//    }
//
    private List<Creditor> createMockFetchAllResponse() {
        List<Creditor> list = new ArrayList<Creditor>();
        Creditor o = AccountingMockDataUtility.createMockOrmCreditor(200, 1351,
                333, "C1234589", "123-456-789", 22);
        list.add(o);
        
        o = AccountingMockDataUtility.createMockOrmCreditor(201, 1400,
                444, "C1400444", "7437437JDJD8484", 22);
        list.add(o);
        
        o = AccountingMockDataUtility.createMockOrmCreditor(202, 1500,
                555, "C1500555", "ABC123", 22);
        list.add(o);
        
        o = AccountingMockDataUtility.createMockOrmCreditor(203, 1600,
                666, "C1600666", "XYZ321", 22);
        list.add(o);
        
        o = AccountingMockDataUtility.createMockOrmCreditor(204, 1700,
                777, "C1700777", "7654312", 22);
        list.add(o);
        return list;
    }

    private List<Creditor> createMockSingleCreditorFetchResponse() {
        List<Creditor> list = new ArrayList<Creditor>();
        Creditor o = AccountingMockDataUtility.createMockOrmCreditor(200, 1351,
                333, "C1234589", "123-456-789", 22);
        list.add(o);
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
    
    private void setupSubsidiaryContactInfoFetch(VwBusinessAddress busContactCriteria, Creditor creditorCriteria) {
//      VwBusinessAddress busCriteria = new VwBusinessAddress();
      try {
          when(this.mockPersistenceClient
                  .retrieveList(eq(busContactCriteria)))
                          .thenReturn(this.mockBusinessContactFetchResponse);
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail("Single Business Contact fetch test case setup failed");
      }

//      Creditor creditorCriteria = new Creditor();
      creditorCriteria.setCreditorId(1351);
      try {
          when(this.mockPersistenceClient.retrieveList(eq(creditorCriteria)))
                  .thenReturn(this.mockCreditorFetchResponse);
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail("Single Creditor fetch test case setup failed");
      }
  }
    

    @Test
    public void testFetchAllNoContactData() {
        try {
            when(this.mockPersistenceClient
                    .retrieveList(any(Creditor.class)))
                            .thenReturn(this.mockFetchAllResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all creditors test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }

    @Test
    public void testFetchSingleNoContactData() {
        Creditor mockCriteria = new Creditor();
        mockCriteria.setCreditorId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockCreditorFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        criteria.setCreditorId(200);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }
    
    @Test
    public void testFetchSingleNotFound() {
        Creditor mockCriteria = new Creditor();
        mockCriteria.setCreditorId(999);
        try {
            when(this.mockPersistenceClient.retrieveList(eq(mockCriteria)))
                            .thenReturn(this.mockNotFoundFetchResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch single creditor test case setup failed");
        }

        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(APP_NAME);
        CreditorDto criteria = Rmt2SubsidiaryDtoFactory.createCreditorInstance(null, null);
        criteria.setCreditorId(999);
        List<CreditorDto> results = null;
        try {
            results = api.get(criteria);
        } catch (CreditorApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchWithNullCriteriaObject() {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(APP_NAME);
        try {
            api.get(null);
            Assert.fail("Expected exception due to null selection criteria object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

  

}