package lookup;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.dao.lookup.LookupDao;
import org.dao.lookup.LookupDaoFactory;
import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the RMT2 ORM Code DAO implementation.
 * 
 * @author rterrell
 * 
 */
public class TestRMT2OrmLookupCodeDao {

    private LookupDaoFactory f;

    private LookupDao dao;

    private List<LookupCodeDto> codes;

    private List<LookupGroupDto> groups;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new LookupDaoFactory();
        this.dao = this.f.createRmt2OrmDao();
        this.dao.setDaoUser("testuser");
        this.createData();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteData();
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    /**
     * Creates data for general_codes_group and general_codes tables.
     * <p>
     * This method provides several means of testing the insert capabilities of
     * the Code DAO implementation. Three groups are created, and three lookup
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
            newGrpId = dao.maintainGroup(grp);
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
                newCodeId = dao.maintainCode(code);
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
            rc = this.dao.deleteCode(codeId);
            Assert.assertTrue(rc > 0);
        }

        // Delete all groups
        for (int ndx = 0; ndx < this.groups.size(); ndx++) {
            int rc = 0;
            LookupGroupDto dto = this.groups.get(ndx);
            int grpId = dto.getGrpId();
            rc = this.dao.deleteGroup(grpId);
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
    public void getSingleCode() {
        LookupCodeDto dto = null;
        try {
            dto = this.dao.fetchCode(this.codes.get(0).getCodeId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of single lookup record faile");
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(this.codes.get(0).getCodeShortName(),
                dto.getCodeShortName());
    }

    @Test
    public void getMultipleCodesUsingDto() {
        List<LookupCodeDto> results = null;
        LookupCodeDto criteria = Rmt2AddressBookDtoFactory.getNewCodeInstance();
        criteria.setGrpId(this.groups.get(1).getGrpId());
        try {
            results = this.dao.fetchCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of lookup(s) using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void getSingleCodeGroup() {
        LookupGroupDto dto = null;
        try {
            dto = (LookupGroupDto) this.dao.fetchGroup(this.groups.get(2)
                    .getGrpId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of single lookup group record faile");
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(this.groups.get(2).getGrpDescr(), dto.getGrpDescr());
    }

    @Test
    public void getMultipleCodeGroupsUsingDto() {
        List<LookupGroupDto> results = null;
        LookupGroupDto criteria = Rmt2AddressBookDtoFactory
                .getNewCodeGroupInstance();
        criteria.setGrpDescr("Test Group Description #");
        try {
            results = this.dao.fetchGroup(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of lookup group(s) using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void getMultipleCodeExtUsingDto() {
        List<LookupExtDto> results = null;
        LookupExtDto criteria = Rmt2AddressBookDtoFactory
                .getNewCodeExtInstance();
        criteria.setCodeShortDesc("DESC #");
        try {
            results = this.dao.fetchCodeExt(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of lookup extention(s) using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(9, results.size());
    }

    @Test
    public void updateGroup() {
        String modDescr = "MOdified Description";
        LookupGroupDto dto = this.dao.fetchGroup(this.groups.get(1).getGrpId());
        Assert.assertNotNull(dto);
        dto.setGrpDescr(modDescr);
        dto.setUpdateUserId("testuser");

        // Perform update
        int rc = 0;
        rc = this.dao.maintainGroup(dto);
        Assert.assertEquals(1, rc);

        // Validate update
        dto = this.dao.fetchGroup(this.groups.get(1).getGrpId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(modDescr, dto.getGrpDescr());
    }

    @Test
    public void updateCode() {
        String modDescr = "royroy2";
        LookupCodeDto dto = this.dao.fetchCode(this.codes.get(1).getCodeId());
        Assert.assertNotNull(dto);
        dto.setCodeShortDesc(modDescr);
        dto.setUpdateUserId("testuser");

        // Perform update
        int rc = 0;
        rc = this.dao.maintainCode(dto);
        Assert.assertEquals(1, rc);

        // Validate update
        dto = this.dao.fetchCode(this.codes.get(1).getCodeId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(modDescr, dto.getCodeShortName());
    }
}
