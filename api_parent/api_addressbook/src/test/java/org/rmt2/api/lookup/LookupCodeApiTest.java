package org.rmt2.api.lookup;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.GeneralCodes;
import org.dto.LookupCodeDto;
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
import org.rmt2.api.BaseAddressBookDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class LookupCodeApiTest extends BaseAddressBookDaoTest {
    private List<GeneralCodes> mockSingleFetchResponse;
    private List<GeneralCodes> mockCriteriaFetchResponse;
    private List<GeneralCodes> mockFetchAllResponse;
    private List<GeneralCodes> mockNotFoundFetchResponse;

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

    private List<GeneralCodes> createMockNotFoundSearchResultsResponse() {
        List<GeneralCodes> list = null;
        return list;
    }

    private List<GeneralCodes> createMockSingleFetchResponse() {
        List<GeneralCodes> list = new ArrayList<GeneralCodes>();
        GeneralCodes p = new GeneralCodes();
        p.setCodeGrpId(200);
        p.setCodeId(1000);
        p.setLongdesc("This is Code 1000");
        p.setShortdesc("Code 1000");

        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<GeneralCodes> createMockFetchUsingCriteriaResponse() {
        List<GeneralCodes> list = new ArrayList<GeneralCodes>();
        GeneralCodes p = new GeneralCodes();
        p.setCodeGrpId(300);
        p.setCodeId(500);
        p.setLongdesc("This is Code 1");
        p.setShortdesc("Code 1");
        list.add(p);

        p = new GeneralCodes();
        p.setCodeGrpId(300);
        p.setCodeId(501);
        p.setLongdesc("This is Code 2");
        p.setShortdesc("Code 2");
        list.add(p);

        return list;
    }

    private List<GeneralCodes> createMockFetchAllResponse() {
        List<GeneralCodes> list = new ArrayList<GeneralCodes>();
        GeneralCodes p = new GeneralCodes();
        p.setCodeGrpId(100);
        p.setCodeId(500);
        p.setLongdesc("This is Code 1");
        p.setShortdesc("Code 1");
        list.add(p);

        p = new GeneralCodes();
        p.setCodeGrpId(200);
        p.setCodeId(501);
        p.setLongdesc("This is Code 2");
        p.setShortdesc("Code 2");
        list.add(p);

        p = new GeneralCodes();
        p.setCodeGrpId(300);
        p.setCodeId(503);
        p.setLongdesc("This is Code 3");
        p.setShortdesc("Code 3");
        list.add(p);

        p = new GeneralCodes();
        p.setCodeGrpId(400);
        p.setCodeId(504);
        p.setLongdesc("This is Code 4");
        p.setShortdesc("Code 4");
        list.add(p);
        return list;
    }

    private LookupCodeDto createMockDto(int grpId, int codeId, String description, String shortDescription) {
        LookupCodeDto dto = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        dto.setGrpId(grpId);
        dto.setCodeId(codeId);
        dto.setCodeLongName(description);
        dto.setCodeShortDesc(shortDescription);
        return dto;
    }
    @Test
    public void testFetchAll() {
        LookupCodeDto criteria = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodes.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All lookup code fetch test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        List<LookupCodeDto> results = null;
        try {
            results = api.getCode(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
    }

    @Test
    public void testFetchSingle() {
        LookupCodeDto criteria = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        criteria.setCodeId(1000);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodes.class)))
                    .thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Single lookup code fetch test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        List<LookupCodeDto> results = null;
        try {
            results = api.getCode(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }
    
    @Test
    public void testFetchByCodeId() {
        try {
            when(this.mockPersistenceClient.retrieveObject(any(GeneralCodes.class)))
                    .thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup code fetch by code id test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        LookupCodeDto results = null;
        try {
            results = api.getCode(1000);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1000, results.getCodeId());
    }

    @Test
    public void testFetchUsingCriteria() {
        LookupCodeDto criteria = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        criteria.setGrpId(300);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodes.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All lookup code fetch criteria test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        List<LookupCodeDto> results = null;
        try {
            results = api.getCode(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (LookupCodeDto dto : results) {
            Assert.assertEquals(300, dto.getGrpId());
        }
    }

    @Test
    public void testNotFoundlFetch() {
        LookupCodeDto criteria = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        criteria.setGrpId(3854);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodes.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All lookup code fetch not found test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        List<LookupCodeDto> results = null;
        try {
            results = api.getCode(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchUsingCriteriaNullResults() {
        LookupCodeDto criteria = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        criteria.setGrpId(3854);
        try {
            when(this.mockPersistenceClient.retrieveList(any(GeneralCodes.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All lookup code fetch not found test case failed");
        }

        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        List<LookupCodeDto> results = null;
        try {
            results = api.getCode(criteria);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
    @Test
    public void testFetchUsingNullCriteria() {
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        try {
            api.getCode(null);
            Assert.fail("Expected an exception to be thrown");
        } catch (LookupDataApiException e) {
            Assert.assertNotNull(e);
            String errMsg = e.getErrorStack();
            Assert.assertTrue(errMsg.contains("Lookup code criteria object cannot be null"));
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        GeneralCodes mockGeneralCodes = new GeneralCodes();
        mockGeneralCodes.setCodeGrpId(555);
        mockGeneralCodes.setCodeId(300);
        mockGeneralCodes.setLongdesc("Test Code 3");
        mockGeneralCodes.setShortdesc("Code 3");
        GeneralCodes mockSelectCriteria = new GeneralCodes();
        mockSelectCriteria.setCodeId(300);
        LookupCodeDto mockUpdateDto = this.createMockDto(555, 300, "Modified Code 33", "Code 33");
        try {
            when(this.mockPersistenceClient.retrieveObject(eq(mockSelectCriteria))).thenReturn(mockGeneralCodes);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code update test case failed setting up mock retrieve call");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(GeneralCodes.class)))
                    .thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code update test case failed setting up mock update call");
        }
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCode(mockUpdateDto);
        } catch (Exception e) {
            Assert.fail("Lookup Code API update test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
        Assert.assertEquals(555, mockUpdateDto.getGrpId());
        Assert.assertEquals(300, mockUpdateDto.getCodeId());
        Assert.assertEquals("Modified Code 33", mockUpdateDto.getCodeLongName());
        Assert.assertEquals("Code 33", mockUpdateDto.getCodeShortName());
    }
    
    @Test
    public void testUpdateWithoutGroupId() {
        LookupCodeDto mockUpdateDto = this.createMockDto(0, 300, "Modified Code 33", "Code 33");
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCode(mockUpdateDto);
            Assert.fail("Expected Validation error");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithoutLongDescription() {
        LookupCodeDto mockUpdateDto = this.createMockDto(555, 300, null, "Code 33");
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCode(mockUpdateDto);
            Assert.fail("Expected Validation error");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithoutShortDescription() {
        LookupCodeDto mockUpdateDto = this.createMockDto(555, 300, "Modified Code 33", null);
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCode(mockUpdateDto);
            Assert.fail("Expected Validation error");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithoutAnyDescriptions() {
        LookupCodeDto mockUpdateDto = this.createMockDto(555, 300, null, null);
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCode(mockUpdateDto);
            Assert.fail("Expected Validation error");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        GeneralCodes mockGeneralCodes = new GeneralCodes();
        mockGeneralCodes.setCodeGrpId(555);
        mockGeneralCodes.setCodeId(333);
        mockGeneralCodes.setLongdesc("Test Code 3");
        mockGeneralCodes.setShortdesc("Code 3");
        LookupCodeDto mockUpdateDto = this.createMockDto(555, 0, "Modified Code 33", "Code 33");
        try {
            when(this.mockPersistenceClient.insertRow(any(GeneralCodes.class), any(Boolean.class)))
                    .thenReturn(333);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code update test case failed setting up mock update call");
        }
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCode(mockUpdateDto);
        } catch (Exception e) {
            Assert.fail("Lookup Code API update test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(333, rc);
        Assert.assertEquals(555, mockUpdateDto.getGrpId());
        Assert.assertEquals(333, mockUpdateDto.getCodeId());
        Assert.assertEquals("Modified Code 33", mockUpdateDto.getCodeLongName());
        Assert.assertEquals("Code 33", mockUpdateDto.getCodeShortName());
    }

    @Test
    public void testDelete() {
        LookupCodeDto mockUpdateDto = this.createMockDto(555, 333, "Modified Code 33", "Code 33");
        try {
            when(this.mockPersistenceClient.deleteRow(any(GeneralCodes.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code delete test case failed setting up mock deleteRow call");
        }
       
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteGroup(mockUpdateDto.getCodeId());
        } catch (Exception e) {
            Assert.fail("Lookup Code API delete test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testDeleteWithInvalidCodeId() {
        LookupCodeDto mockUpdateDto = this.createMockDto(555, -1, "Modified Code 33", "Code 33");
        try {
            when(this.mockPersistenceClient.deleteRow(any(GeneralCodes.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Lookup Code delete test case failed setting up mock deleteRow call");
        }
       
        LookupDataApiFactory f = new LookupDataApiFactory();
        LookupDataApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteCode(mockUpdateDto.getCodeId());
            Assert.fail("Expected validation error");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
