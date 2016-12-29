package lookup;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.lookup.LookupDataApi;
import org.modules.lookup.LookupDataApiException;
import org.modules.lookup.LookupDataApiFactory;

/**
 * Test the RMT2 ORM Code DAO implementation.
 * 
 * @author rterrell
 * 
 */
public class TestLdapLookupCodeApi {

    private LookupDataApiFactory f;

    private LookupDataApi api;

    private List<LookupCodeDto> codes;

    private List<LookupGroupDto> groups;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new LookupDataApiFactory();
        this.api = this.f.createApi();
        this.api.setApiUser("testuser");
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
        this.api = null;
        this.f = null;
    }

    /**
     * Creates data for general_codes_group and general_codes tables using the
     * API implementation.
     * <p>
     * This method provides several means of testing the insert capabilities of
     * the Code API implementation. Three groups are created, and three lookup
     * records are created for each group.
     * 
     * @param dao
     * @throws Exception
     */
    protected void createData() throws Exception {
        this.groups = new ArrayList<LookupGroupDto>();
        this.codes = new ArrayList<LookupCodeDto>();

        String grpName[] = { "Test Group Description #1",
                "Test Group Description #2", "Test Group Description #3" };
        int grpCount = 0;
        for (String name : grpName) {
            // create a group
            int newGrpId = 0;
            LookupGroupDto grp = Rmt2AddressBookDtoFactory
                    .getNewCodeGroupInstance();
            grp.setGrpDescr(name);
            grp.setUpdateUserId("testuser");
            newGrpId = this.api.updateGroup(grp);
            Assert.assertTrue(newGrpId > 0);
            Assert.assertEquals(newGrpId, grp.getGrpId());
            this.groups.add(grp);
            grpCount++;

            // Create the groups lookup detail records
            for (int codeCount = 0; codeCount < 3; codeCount++) {
                int newCodeId = 0;
                LookupCodeDto code = this.createCodeDto(newGrpId, "DESC #"
                        + codeCount, "Code description #" + codeCount
                        + " for group " + newGrpId);
                newCodeId = this.api.updateCode(code);
                Assert.assertTrue(newCodeId > 0);
                Assert.assertEquals(newCodeId, code.getCodeId());
                this.codes.add(code);
            }
        }
    }

    /**
     * This method removes the data created by the method, createData().
     * <p>
     * This method provides several means of testing the delete capabilities of
     * the Code DAO implementation. First, the data from the general_codes table
     * is deleted. Lastly, the data from the general_codes_group table is
     * deleted.
     * 
     * @throws Exception
     */
    protected void deleteData() throws Exception {
        // Delete all codes
        for (int ndx = 0; ndx < this.codes.size(); ndx++) {
            int rc = 0;
            LookupCodeDto dto = this.codes.get(ndx);
            int codeId = dto.getCodeId();
            rc = this.api.deleteCode(codeId);
            Assert.assertTrue(rc > 0);
        }

        // Delete all groups
        for (int ndx = 0; ndx < this.groups.size(); ndx++) {
            int rc = 0;
            LookupGroupDto dto = this.groups.get(ndx);
            int grpId = dto.getGrpId();
            rc = this.api.deleteGroup(grpId);
            Assert.assertTrue(rc > 0);
        }
    }

    private LookupCodeDto createCodeDto(int grpId, String shortName,
            String longName) {
        LookupCodeDto dto = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        dto.setGrpId(grpId);
        dto.setCodeShortDesc(shortName);
        dto.setCodeLongName(longName);
        dto.setUpdateUserId("testuser");
        return dto;
    }

    @Test
    public void testSingleCodeGroupFetch() {
        LookupGroupDto dto = null;
        try {
            dto = (LookupGroupDto) this.api.getGroup(this.groups.get(2)
                    .getGrpId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of single lookup group record failed");
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(this.groups.get(2).getGrpDescr(), dto.getGrpDescr());
    }

    @Test
    public void testSingleCodeGroupFetchUsingDto() {
        String descr = "Test Group Description #2";
        List<LookupGroupDto> results = null;
        LookupGroupDto criteria = Rmt2AddressBookDtoFactory
                .getNewCodeGroupInstance();
        criteria.setGrpDescr(descr);
        try {
            results = this.api.getGroup(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of lookup group(s) using DTO criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(descr, results.get(0).getGrpDescr());
    }

    @Test
    public void testtMultipleCodeGroupFetchUsingDto() {
        List<LookupGroupDto> results = null;
        LookupGroupDto criteria = Rmt2AddressBookDtoFactory
                .getNewCodeGroupInstance();
        criteria.setGrpDescr("Test Group Description #");
        try {
            results = this.api.getGroup(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of lookup group(s) using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void testFetchAllCodeGroups() {
        List<LookupGroupDto> results = null;
        try {
            results = this.api.getGroup(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of all lookup groups using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void testSingleCodeFetch() {
        LookupCodeDto dto = null;
        try {
            dto = (LookupCodeDto) this.api.getCode(this.codes.get(2)
                    .getCodeId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of single lookup record failed");
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(this.codes.get(2).getCodeShortName(),
                dto.getCodeShortName());
    }

    @Test
    public void testSingleCodeFetchUsingDto() {
        int codeId = this.codes.get(3).getCodeId();
        List<LookupCodeDto> results = null;
        LookupCodeDto criteria = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        criteria.setCodeId(codeId);
        try {
            results = this.api.getCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of sngle lookup using DTO criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(this.codes.get(3).getCodeLongName(), results.get(0)
                .getCodeLongName());
    }

    @Test
    public void testMultipleCodeFetchUsingDto() {
        String descr = "Code description #";
        List<LookupCodeDto> results = null;
        LookupCodeDto criteria = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        criteria.setCodeLongName(descr);
        try {
            results = this.api.getCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of multiple codes using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(9, results.size());
    }

    @Test
    public void testFetchAllCodes() {
        List<LookupCodeDto> results = null;
        try {
            results = this.api.getCode(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of all codes using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void getMultipleCodeExtUsingDto() {
        List<LookupExtDto> results = null;
        LookupExtDto criteria = Rmt2AddressBookDtoFactory
                .getNewCodeExtInstance();
        criteria.setCodeShortDesc("DESC #");
        try {
            results = this.api.getCodeExt(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of multiple lookup extention objects using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(9, results.size());
    }

    @Test
    public void testGroupUpdate() {
        int rc = 0;
        LookupGroupDto dto = null;
        String modDescr = "Modified Description";
        try {
            dto = this.api.getGroup(this.groups.get(1).getGrpId());
            Assert.assertNotNull(dto);
            dto.setGrpDescr(modDescr);
            dto.setUpdateUserId("testuser");

            // Perform update
            rc = this.api.updateGroup(dto);
        } catch (LookupDataApiException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(1, rc);
        // Validate update
        try {
            dto = this.api.getGroup(this.groups.get(1).getGrpId());
        } catch (LookupDataApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(modDescr, dto.getGrpDescr());
    }

    @Test
    public void testCodeUpdate() {
        String modDescr = "royroy2";
        LookupCodeDto dto = null;
        int rc = 0;
        try {
            dto = this.api.getCode(this.codes.get(1).getCodeId());
            Assert.assertNotNull(dto);
            dto.setCodeShortDesc(modDescr);
            dto.setUpdateUserId("testuser");
            // Perform update
            rc = this.api.updateCode(dto);
        } catch (LookupDataApiException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(1, rc);
        // Validate update
        try {
            dto = this.api.getCode(this.codes.get(1).getCodeId());
        } catch (LookupDataApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(modDescr, dto.getCodeShortName());
    }

}
