package org.rmt2.api.lookup;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.GeneralCodesGroup;
import org.dto.LookupGroupDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.lookup.LookupDataApi;
import org.modules.lookup.LookupDataApiException;
import org.modules.lookup.LookupDataApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class LookupCodeGroupApiTest extends BaseDaoTest {

    private List<GeneralCodesGroup> mockSingleFetchResponse;
    private List<GeneralCodesGroup> mockCriteriaFetchResponse;
    private List<GeneralCodesGroup> mockFetchAllResponse;
    private List<GeneralCodesGroup> mockNotFoundFetchResponse;

    @Before
    public void setUp() throws Exception {
        APP_NAME = "addressbook";
        super.setUp();
        this.mockSingleFetchResponse = this.createMockSingleFetchResponse();
        this.mockCriteriaFetchResponse = this.createMockFetchUsingCriteriaResponse();
        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this.createMockNotFoundSearchResultsResponse();
    }

    @After
    public void tearDown() throws Exception {
    }

    private List<GeneralCodesGroup> createMockNotFoundSearchResultsResponse() {
        List<GeneralCodesGroup> list = new ArrayList<GeneralCodesGroup>();
        return list;
    }

    private List<GeneralCodesGroup> createMockSingleFetchResponse() {
        List<GeneralCodesGroup> list = new ArrayList<GeneralCodesGroup>();
        GeneralCodesGroup p = new GeneralCodesGroup();
        p.setCodeGrpId(100);
        p.setDescription("Group 1");

        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<GeneralCodesGroup> createMockFetchUsingCriteriaResponse() {
        List<GeneralCodesGroup> list = new ArrayList<GeneralCodesGroup>();
        GeneralCodesGroup p = new GeneralCodesGroup();
        p.setCodeGrpId(100);
        p.setDescription("Group 1");
        list.add(p);

        p = new GeneralCodesGroup();
        p.setCodeGrpId(200);
        p.setDescription("Group 2");
        list.add(p);

        return list;
    }

    private List<GeneralCodesGroup> createMockFetchAllResponse() {
        List<GeneralCodesGroup> list = new ArrayList<GeneralCodesGroup>();
        GeneralCodesGroup p = new GeneralCodesGroup();
        p.setCodeGrpId(100);
        p.setDescription("Group 1");
        list.add(p);

        p = new GeneralCodesGroup();
        p.setCodeGrpId(200);
        p.setDescription("Group 2");
        list.add(p);

        p = new GeneralCodesGroup();
        p.setCodeGrpId(300);
        p.setDescription("Group 3");
        list.add(p);

        p = new GeneralCodesGroup();
        p.setCodeGrpId(400);
        p.setDescription("Group 4");
        list.add(p);
        return list;
    }

    @Test
    public void testFetchAll() {
        LookupGroupDto criteria = Rmt2AddressBookDtoFactory.getNewCodeGroupInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodesGroup.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All lookup code group fetch test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        List<LookupGroupDto> results = null;
        try {
            results = api.getGroup(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
    }

    @Test
    public void testFetchSingle() {
        LookupGroupDto criteria = Rmt2AddressBookDtoFactory.getNewCodeGroupInstance();
        criteria.setGrpId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodesGroup.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Single lookup code group fetch test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        List<LookupGroupDto> results = null;
        try {
            results = api.getGroup(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void testFetchUsingCriteria() {
        LookupGroupDto criteria = Rmt2AddressBookDtoFactory.getNewCodeGroupInstance();
        criteria.setGrpDescr("Group");
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodesGroup.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup code group fetch test using selection criteria case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        List<LookupGroupDto> results = null;
        try {
            results = api.getGroup(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void testNotFoundlFetch() {
        LookupGroupDto criteria = Rmt2AddressBookDtoFactory.getNewCodeGroupInstance();
        criteria.setGrpDescr("Terrell");
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodesGroup.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Not Found: person contact fetch test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        List<LookupGroupDto> results = null;
        try {
            results = api.getGroup(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsert() {
        fail("Not yet implemented");
    }

    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }
}
