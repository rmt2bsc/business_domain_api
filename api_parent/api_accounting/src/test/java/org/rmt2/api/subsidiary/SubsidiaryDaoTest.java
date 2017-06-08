package org.rmt2.api.subsidiary;

import org.dao.subsidiary.SubsidiaryDao;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.CommonAccountingTest;

/**
 * Tests the subsidiary DAO library.
 * 
 * @author Roy Terrell
 * 
 */
public class SubsidiaryDaoTest extends CommonAccountingTest {
    private SubsidiaryDaoFactory f;

    private SubsidiaryDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.f = new SubsidiaryDaoFactory();
        this.dao = this.f.createRmt2OrmSubsidiaryDao();
        this.dao.setDaoUser("Testuser");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dao.close();
        this.dao = null;
        this.f = null;
        super.tearDown();
    }

    @Test
    public void testNotFound() {
        SubsidiaryContactInfoDto criteria = Rmt2SubsidiaryDtoFactory
                .createSubsidiaryInstance(null);
        criteria.setContactName("Roy Terrell");
        try {
            this.dao.fetch(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }

    @Test
    public void testFound() {
        SubsidiaryContactInfoDto criteria = Rmt2SubsidiaryDtoFactory
                .createSubsidiaryInstance(null);
        criteria.setContactName("Miller");
        try {
            this.dao.fetch(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }
}
