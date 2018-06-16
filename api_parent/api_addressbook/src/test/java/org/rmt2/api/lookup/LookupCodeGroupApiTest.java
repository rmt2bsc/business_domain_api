package org.rmt2.api.lookup;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
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
import org.modules.AddressBookConstants;
import org.modules.lookup.LookupDataApi;
import org.modules.lookup.LookupDataApiException;
import org.modules.lookup.LookupDataApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseAddressBookDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class LookupCodeGroupApiTest extends BaseAddressBookDaoTest {

    private List<GeneralCodesGroup> mockSingleFetchResponse;
    private List<GeneralCodesGroup> mockCriteriaFetchResponse;
    private List<GeneralCodesGroup> mockFetchAllResponse;
    private List<GeneralCodesGroup> mockNotFoundFetchResponse;

    @Before
    public void setUp() throws Exception {
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
        List<GeneralCodesGroup> list = null;
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
        p.setCodeGrpId(101);
        p.setDescription("Group 2");
        list.add(p);

        p = new GeneralCodesGroup();
        p.setCodeGrpId(102);
        p.setDescription("Group 3");
        list.add(p);

        p = new GeneralCodesGroup();
        p.setCodeGrpId(103);
        p.setDescription("Group 4");
        list.add(p);
        return list;
    }

    private LookupGroupDto createMockDto(int grpId, String description) {
        LookupGroupDto dto = Rmt2AddressBookDtoFactory.getNewCodeGroupInstance();
        dto.setGrpId(grpId);
        dto.setGrpDescr(description);
        
        return dto;
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
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<LookupGroupDto> results = null;
        try {
            results = api.getGroup(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
        
        for (int ndx = 0; ndx < results.size(); ndx++) {
            LookupGroupDto obj = results.get(ndx);
            Assert.assertEquals(obj.getGrpId(), (100 + ndx));
            Assert.assertEquals(obj.getGrpDescr(), "Group " + (ndx + 1));
        }
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
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
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
    public void testFetchByGroupId() {
        try {
            when(this.mockPersistenceClient.retrieveObject(any(GeneralCodesGroup.class)))
                    .thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup group fetch by group id test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        LookupGroupDto results = null;
        try {
            results = api.getGroup(100);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(100, results.getGrpId());
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
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
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
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<LookupGroupDto> results = null;
        try {
            results = api.getGroup(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testUpdate() {
        GeneralCodesGroup mockGeneralCodesGroup = new GeneralCodesGroup();
        mockGeneralCodesGroup.setCodeGrpId(555);
        mockGeneralCodesGroup.setDescription("Test Group 3");
        GeneralCodesGroup mockSelectCriteria = new GeneralCodesGroup();
        mockSelectCriteria.setCodeGrpId(555);
        LookupGroupDto mockUpdateDto = this.createMockDto(555, "Modified Group 3");
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockSelectCriteria))).thenReturn(mockGeneralCodesGroup);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code Group update test case failed setting up mock retrieve call");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(GeneralCodesGroup.class)))
                    .thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code Group update test case failed setting up mock update call");
        }
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateGroup(mockUpdateDto);
        } catch (Exception e) {
            Assert.fail("Lookup Code Group API update test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
        Assert.assertEquals(555, mockUpdateDto.getGrpId());
        Assert.assertEquals("Modified Group 3", mockUpdateDto.getGrpDescr());
    }

    @Test
    public void testUpdateWithoutDescription() {
        GeneralCodesGroup mockSelectCriteria = new GeneralCodesGroup();
        LookupGroupDto mockUpdateDto = this.createMockDto(555, null);
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateGroup(mockUpdateDto);
            Assert.fail("Expecting validation error");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsert() {
        LookupGroupDto mockDto = this.createMockDto(0, "Test Group 3");
        try {
            when(this.mockPersistenceClient.insertRow(any(GeneralCodesGroup.class), any(Boolean.class)))
                    .thenReturn(555);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code Group insert test case failed setting up mock insert call");
        }
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateGroup(mockDto);
        } catch (Exception e) {
            Assert.fail("Lookup Code Group API insert test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(555, rc);
        Assert.assertEquals(555, mockDto.getGrpId());
        Assert.assertEquals("Test Group 3", mockDto.getGrpDescr());
    }

    @Test
    public void testInsertWithoutDescription() {
        GeneralCodesGroup mockSelectCriteria = new GeneralCodesGroup();
        LookupGroupDto mockUpdateDto = this.createMockDto(0, null);
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateGroup(mockUpdateDto);
            Assert.fail("Expecting validation error");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDelete() {
        GeneralCodesGroup mockSelectCriteria = new GeneralCodesGroup();
        mockSelectCriteria.setCodeGrpId(555);
        LookupGroupDto mockUpdateDto = this.createMockDto(555, "Modified Group 3");
        try {
            when(this.mockPersistenceClient.deleteRow(eq(mockSelectCriteria))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code Group delete test case failed setting up mock deleteRow call");
        }
       
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteGroup(mockUpdateDto.getGrpId());
        } catch (Exception e) {
            Assert.fail("Lookup Code Group API delete test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }
    
    @Test
    public void testDeleteWithInvalidGroupId() {
        GeneralCodesGroup mockSelectCriteria = new GeneralCodesGroup();
        LookupGroupDto mockUpdateDto = this.createMockDto(0, "Modified Group 3");
        try {
            when(this.mockPersistenceClient.deleteRow(mockSelectCriteria)).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code Group delete test case failed setting up mock deleteRow call");
        }
       
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.deleteGroup(mockUpdateDto.getGrpId());
            Assert.fail("Expecting an exception to be thrown...Lookup Code Group API delete test case failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchUsingNullCriteria() {
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getGroup(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (LookupDataApiException e) {
            Assert.assertNotNull(e);
            String errMsg = e.getErrorStack();
            Assert.assertTrue(errMsg.contains("Lookup group criteria object cannot be null"));
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchAllWithException() {
        LookupGroupDto criteria = Rmt2AddressBookDtoFactory.getNewCodeGroupInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodesGroup.class)))
            .thenThrow(DatabaseException.class);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All lookup code group fetch test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<LookupGroupDto> results = null;
        try {
            results = api.getGroup(criteria);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof LookupDataApiException);
            Assert.assertTrue(e.getCause() instanceof LookupDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertWithException() {
        LookupGroupDto mockDto = this.createMockDto(0, "Test Group 3");
        try {
            when(this.mockPersistenceClient.insertRow(any(GeneralCodesGroup.class), any(Boolean.class)))
            .thenThrow(DatabaseException.class);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code Group insert test case failed setting up mock insert call");
        }
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateGroup(mockDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof LookupDataApiException);
            Assert.assertTrue(e.getCause() instanceof LookupDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithException() {
        GeneralCodesGroup mockGeneralCodesGroup = new GeneralCodesGroup();
        mockGeneralCodesGroup.setCodeGrpId(555);
        mockGeneralCodesGroup.setDescription("Test Group 3");
        GeneralCodesGroup mockSelectCriteria = new GeneralCodesGroup();
        mockSelectCriteria.setCodeGrpId(555);
        LookupGroupDto mockUpdateDto = this.createMockDto(555, "Modified Group 3");
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockSelectCriteria))).thenReturn(mockGeneralCodesGroup);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code Group update test case failed setting up mock retrieve call");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(GeneralCodesGroup.class)))
            .thenThrow(DatabaseException.class);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code Group update test case failed setting up mock update call");
        }
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateGroup(mockUpdateDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof LookupDataApiException);
            Assert.assertTrue(e.getCause() instanceof LookupDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDeleteWithException() {
        GeneralCodesGroup mockSelectCriteria = new GeneralCodesGroup();
        mockSelectCriteria.setCodeGrpId(555);
        LookupGroupDto mockUpdateDto = this.createMockDto(555, "Modified Group 3");
        try {
            when(this.mockPersistenceClient.deleteRow(eq(mockSelectCriteria)))
            .thenThrow(DatabaseException.class);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code Group delete test case failed setting up mock deleteRow call");
        }
       
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteGroup(mockUpdateDto.getGrpId());
        } catch (Exception e) {
            Assert.assertTrue(e instanceof LookupDataApiException);
            Assert.assertTrue(e.getCause() instanceof LookupDaoException);
            e.printStackTrace();
        }
    }
}
