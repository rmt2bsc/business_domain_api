package org.rmt2.api;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.ProjClient;
import org.junit.After;
import org.junit.Before;

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
public class ProjectAdminApiTestData extends BaseProjectTrackerDaoTest {
    protected List<ProjClient> mockProjClientFetchMultiple;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockProjClientFetchMultiple = this.createMockMultipleProjClient();

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

    private List<ProjClient> createMockMultipleProjClient() {
        List<ProjClient> list = new ArrayList<ProjClient>();
        ProjClient o = ProjectTrackerMockDataUtility.createMockOrmProjClient(
                1000, 1350, "1000 Company", 70.00, 80.00, "000-111", "steve",
                "gadd", "0000000000", "stevegadd@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjClient(1001, 1351,
                "1001 Company", 80.00, 90.00, "111-111", "billy", "cobham",
                "1111111111", "billycobham@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjClient(1002, 1352,
                "1002 Company", 90.00, 100.00, "222-111", "dennis", "chambers",
                "2222222222", "dennischamabers@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjClient(1003, 1353,
                "1003 Company", 100.00, 110.00, "333-111", "harvey", "mason",
                "3333333333", "harveymason@gte.net");
        list.add(o);

        o = ProjectTrackerMockDataUtility.createMockOrmProjClient(1004, 1354,
                "1004 Company", 110.00, 120.00, "444-111", "raford", "griffin",
                "4444444444", "rayfordgriffin@gte.net");
        list.add(o);
        return list;
    }

}