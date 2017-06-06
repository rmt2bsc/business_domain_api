package org.rmt2.api.postal;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.GeneralCodes;
import org.dto.LookupCodeDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class IpInfoApiTest extends BaseDaoTest {
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
        List<GeneralCodes> list = new ArrayList<GeneralCodes>();
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
        Assert.fail("test Failed");
    }

    @Test
    public void testFetchSingle() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testFetchByUid() {
        Assert.fail("test Failed");
    }

    @Test
    public void testFetchUsingCriteria() {
        Assert.fail("test Failed");
    }

    @Test
    public void testNotFoundlFetch() {
        Assert.fail("test Failed");
    }

    @Test
    public void testFetchUsingCriteriaNullResults() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testFetchUsingNullCriteria() {
        Assert.fail("test Failed");
    }

    @Test
    public void testUpdate() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testUpdateWithoutGroupId() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testUpdateWithoutLongDescription() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testUpdateWithoutShortDescription() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testUpdateWithoutAnyDescriptions() {
        Assert.fail("test Failed");
    }

    @Test
    public void testInsert() {
        Assert.fail("test Failed");
    }

    @Test
    public void testDelete() {
        Assert.fail("test Failed");
    }

    @Test
    public void testDeleteWithInvalidCodeId() {
        Assert.fail("test Failed");
    }
}
